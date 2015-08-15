package com.app.labeli.team;

import java.util.ArrayList;

import com.app.labeli.MainActivity;
import com.app.labeli.R;

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

public class FragmentTeam extends Fragment {

	private ListView listView;
	private ProgressDialog pDialog;
	private ArrayList<Team> teams;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			super.onCreate(savedInstanceState);
		else {
			new TeamLoader().execute();
		}

		getActivity().getActionBar().setTitle("Equipes");

		return inflater.inflate(R.layout.fragment_project, container, false);
	}

	public void prepareListView(ArrayList<Team> al){
		teams = al;
		listView = (ListView) this.getView().findViewById(R.id.fragment_project_list_view);

		listView.setAdapter(new ListAdapterTeam(this.getActivity().getApplicationContext(),
				al));
		listView.setOnItemClickListener(new TeamItemClickListener());
	}

	private class TeamItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					TeamDetailsActivity.class);
			intent.putExtra("team", teams.get(position));
			startActivity(intent);
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private class TeamLoader extends AsyncTask<Void, Void, String>
	{
		

		public TeamLoader(){
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentTeam.this.getActivity());
			pDialog.setMessage("Chargement des équipes");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Void... params)
		{
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}
}
