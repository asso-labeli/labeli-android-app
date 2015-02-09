package com.app.labeli.project;

import java.util.ArrayList;
import com.app.callback.APICallback;
import com.app.labeli.MainActivity;
import com.app.labeli.R;
import com.tools.APIDataParser;
import labeli.Labeli;
import android.content.Intent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentProject extends Fragment {

	private ListView listView;
	private ProgressDialog pDialog;
	private ArrayList<ItemProject> projects;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			super.onCreate(savedInstanceState);
		else {
			new ProjectLoader().execute(MainActivity.api);
		}

		getActivity().getActionBar().setTitle("Projets");

		return inflater.inflate(R.layout.fragment_project, container, false);
	}

	public void prepareListView(ArrayList<ItemProject> al){
		projects = al;
		listView = (ListView) this.getView().findViewById(R.id.fragment_project_list_view);

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

	private class ProjectLoader extends AsyncTask<Labeli, Void, String>
	{
		APICallback a;

		public ProjectLoader(){
			a = new APICallback();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentProject.this.getActivity());
			pDialog.setMessage("Chargement des projets");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Labeli... api)
		{
			api[0].async.getProjects(a);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (a.getArray() != null)
				prepareListView(APIDataParser.parseProjectList(a.getArray()));
		}
	}

}
