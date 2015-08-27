package com.app.labeli;

import java.util.ArrayList;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.app.labeli.event.FragmentEvent;
import com.app.labeli.member.FragmentMember;
import com.app.labeli.project.FragmentProject;
import com.app.labeli.team.FragmentTeam;

/**
 * > @MainActivity
 *
 * Base activity of the application
 *
 * @author Florian "Aamu Lumi" Kauder 
 * for the project @Label[i]
 */

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

		// Get base layout (DrawerLayout to create a LeftDrawer)
		mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
		// Get ListView (corresponding to LeftDrawer)
		mDrawerList = (ListView) findViewById(R.id.activity_main_left_drawer);

		// Loading menu
		loadLeftMenu();

		// Activation of ActionBar's name to use it as button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Launch FragmentPresentation (default fragment)
		FragmentPresentation ex = new FragmentPresentation();
		ex.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction()
				.add(R.id.activity_main_content_frame, ex).commit();
	}

	public void loadLeftMenu() {
		// Create a new ArrayList which will contain items (@link
		// com.app.labeli.ItemNavigationDrawer)
		navDrawerItems = new ArrayList<ItemNavigationDrawer>();

		// If user isn't logged, load the guest menu
		if (!APIConnection.isLogged()) {
			// Get names and icons from res/strings.xml
			navMenuTitles = getResources().getStringArray(
					R.array.nav_drawer_not_connected);
			navMenuIcons = getResources().obtainTypedArray(
					R.array.nav_drawer_icons_not_connected);

			// Add them to the ArrayList
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[0],
					navMenuIcons.getResourceId(0, -1)));
			// NEWS (will come with an update)
			// navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[1],
			// navMenuIcons.getResourceId(1, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[2],
					navMenuIcons.getResourceId(2, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[3],
					navMenuIcons.getResourceId(3, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[4],
					navMenuIcons.getResourceId(4, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[5],
					navMenuIcons.getResourceId(5, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[6],
					navMenuIcons.getResourceId(6, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[7],
					navMenuIcons.getResourceId(7, -1)));
		}
		// Else load member menu
		else {
			// Get connected user names and icons
			navMenuTitles = getResources().getStringArray(
					R.array.nav_drawer_connected);
			navMenuIcons = getResources().obtainTypedArray(
					R.array.nav_drawer_icons_connected);

			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[0],
					navMenuIcons.getResourceId(0, -1)));
			// NEWS
			// navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[1],
			// navMenuIcons.getResourceId(1, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[2],
					navMenuIcons.getResourceId(2, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[3],
					navMenuIcons.getResourceId(3, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[4],
					navMenuIcons.getResourceId(4, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[5],
					navMenuIcons.getResourceId(5, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[6],
					navMenuIcons.getResourceId(6, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[7],
					navMenuIcons.getResourceId(7, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[8],
					navMenuIcons.getResourceId(8, -1)));
			navDrawerItems.add(new ItemNavigationDrawer(navMenuTitles[9],
					navMenuIcons.getResourceId(9, -1)));
		}

		// Free icons resources array
		navMenuIcons.recycle();

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ListAdapterNavigationDrawer(this,
				navDrawerItems));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				selectItem(position);
			}
		});

		// Create a new ActionBarDrawerToggle (the thing to open the LeftDrawer)
		// It takes :
		// - The fragmentActivity
		// - The DrawerLayout
		// - Icon to show next to ActionBar's title
		// - String when drawer is opened
		// - String when drawer is closed
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				mDrawerList.bringToFront();
				mDrawerLayout.requestLayout();
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the ActionBarDrawerToggle to work on the DrawerLayout
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

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	private void selectItem(int position) {
		// Update the main content by replacing fragments
		switch (position) {
		case 0:
			fragment = new FragmentPresentation();
			break;
		case 1:
			fragment = new FragmentProject();
			break;
		case 2:
			fragment = new FragmentEvent();
			break;
		case 3:
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

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.replace(R.id.activity_main_content_frame, fragment, fragment.getClass()
				.getName());
		// Keep fragment in the app history (for back button)
		transaction.addToBackStack(fragment.getClass().getName());

		transaction.commit();

		// Set the opened fragment in the LeftDrawer
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	private class Logout extends AsyncTask<Void, Void, String> {
		ProgressDialog pDialog;
		boolean success;

		public Logout() {
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

		@Override
		protected String doInBackground(Void... params) {
			success = APIConnection.logout();
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			Log.i("Coucou", "Oui");
			pDialog.dismiss();
			if (!success)
				Toast.makeText(MainActivity.this,
						"Erreur lors de la déconnexion", Toast.LENGTH_SHORT)
						.show();
			else {
				Toast.makeText(MainActivity.this, "Déconnexion réussie",
						Toast.LENGTH_SHORT).show();
				MainActivity.this.loadLeftMenu();
			}
		}
	}
}
