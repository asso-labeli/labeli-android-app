package com.app.labeli;

import java.util.ArrayList;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.event.FragmentEvent;
import com.app.labeli.member.FragmentMember;
import com.app.labeli.member.Member;
import com.app.labeli.project.FragmentProject;
import com.app.labeli.team.FragmentTeam;

import net.tools.APIConnection;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ArrayList<ItemNavigationDrawer> navDrawerItems;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private Fragment fragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);	
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		loadLeftMenu();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Launch FragmentPresentation
		FragmentPresentation ex = new FragmentPresentation();
		ex.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.content_frame, ex).commit();
	}

	public void loadLeftMenu(){
		navDrawerItems = new ArrayList<ItemNavigationDrawer>();
		if (APIConnection.getLoggedUser() == null){
			navMenuTitles = getResources().getStringArray(R.array.nav_drawer_not_connected);
			navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons_not_connected);

			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
			// NEWS
			//navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[6], navMenuIcons.getResourceId(6, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[7], navMenuIcons.getResourceId(7, -1), true, "50+"));
		}
		else {
			navMenuTitles = getResources().getStringArray(R.array.nav_drawer_connected);
			navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons_connected);

			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
			// NEWS
			//navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[6], navMenuIcons.getResourceId(6, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[7], navMenuIcons.getResourceId(7, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[8], navMenuIcons.getResourceId(8, -1), true, "50+"));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[9], navMenuIcons.getResourceId(9, -1), true, "50+"));
		}

		navMenuIcons.recycle();

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ListAdapterNavigationDrawer(this,
				navDrawerItems));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle(title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.show_home) {
			return true;
		} 

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void finish(){
		super.finish();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {

		// update the main content by replacing fragments
		switch(position){
		case 0:
			fragment = new FragmentPresentation();
			break;
		case 1 :
			fragment = new FragmentProject();
			break;
		case 2:
			fragment = new FragmentEvent();
			break;
		case 3 :
			fragment = new FragmentTeam();
			break;
		case 4:
			fragment = new FragmentMember();
			break;
		case 6:
			fragment = new FragmentConnection();
			break;
		case 8:
			mDrawerLayout.closeDrawer(mDrawerList);
			new Logout().execute();
			fragment = new FragmentPresentation();
			break;
		default:
			fragment = new FragmentPresentation();
			break;
		}
		Bundle args = new Bundle();
		fragment.setArguments(args);

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
		transaction.addToBackStack(fragment.getClass().getName());

		transaction.commit();

		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);

	}
	
	private class Logout extends AsyncTask<Void, Void, String>
	{
		ProgressDialog pDialog;
		boolean success;
		
		public Logout(){
			Log.i("Coucou", "Oui");
			this.success = false;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Déconnexion");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			Log.i("Coucou", "Oui");
		}

		protected String doInBackground(Void... params)
		{
			success = APIConnection.logout();
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			Log.i("Coucou", "Oui");
			pDialog.dismiss();
			if (!success)
				Toast.makeText(MainActivity.this, "Erreur lors de la déconnexion", Toast.LENGTH_SHORT).show();
			else {
				Toast.makeText(MainActivity.this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();
				MainActivity.this.loadLeftMenu();
			}
		}
	}
}
