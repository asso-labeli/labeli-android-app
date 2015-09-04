package com.app.labeli.project;

import java.util.ArrayList;

import net.tools.APIConnection;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.labeli.R;

/**
 * > @FragmentProject
 *
 * Fragment to show all projects
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class FragmentProject extends Fragment {

	private ListView listView;
	private ProgressDialog pDialog;
	private ArrayList<Project> projects;

	private static final int ADD_PROJECT = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getActionBar().setTitle("Projets");
		setHasOptionsMenu(true);

		refresh();

		return inflater.inflate(R.layout.fragment_project, container, false);
	}

	@Override
	public void onStart() {
		//refresh();
		super.onStart();
	}

	public void refresh(){
		new ProjectLoader().execute();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.fragment_project, menu);

		MenuItem itemAdd = menu.findItem(R.id.fragment_project_menu_addProject);
		if (APIConnection.isLogged() 
				&& APIConnection.loggedUserIsAdmin())
			itemAdd.setVisible(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.fragment_project_menu_addProject :
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					AddProjectActivity.class);
			startActivityForResult(intent, ADD_PROJECT);
			return true;
		case R.id.fragment_project_menu_refresh :
			refresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			switch(requestCode) {
			case (ADD_PROJECT) :
				refresh();
			break;
			}
		}
	}

	public void prepareListView(ArrayList<Project> al){
		projects = al;
		listView = (ListView) this.getView().findViewById(R.id.fragment_project_list_view);

		if (al != null)
			listView.setAdapter(new ListAdapterProject(this.getActivity().getApplicationContext(),
					al));
		listView.setOnItemClickListener(new ProjectItemClickListener());
	}

	private class ProjectItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					ProjectDetailsActivity.class);
			intent.putExtra("project", projects.get(position));
			startActivity(intent);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private class ProjectLoader extends AsyncTask<Void, Void, ArrayList<Project>>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentProject.this.getActivity());
			pDialog.setMessage("Chargement des projets");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected ArrayList<Project> doInBackground(Void... v){
			return APIConnection.getProjects();
		}

		@Override
		protected void onPostExecute(ArrayList<Project> a){
			pDialog.dismiss();
			prepareListView(a);
		}
	}

}
