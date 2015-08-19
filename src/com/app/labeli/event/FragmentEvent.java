package com.app.labeli.event;

import java.util.ArrayList;

import com.app.labeli.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * > @FragmentEvent
 *
 * Fragment to show all Events
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class FragmentEvent extends Fragment {

	private ListView listView;
	private ProgressDialog pDialog;
	private ArrayList<Event> events;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			super.onCreate(savedInstanceState);
		else {
			new EventLoader().execute();
		}
		
		getActivity().getActionBar().setTitle("Evénements");

		return inflater.inflate(R.layout.fragment_event, container, false);
	}

	public void prepareListView(ArrayList<Event> al){
		events = al;
		listView = (ListView) this.getView().findViewById(R.id.fragment_event_list_view);

		listView.setAdapter(new ListAdapterEvent(this.getActivity().getApplicationContext(),
				al));
		listView.setOnItemClickListener(new EventItemClickListener());
	}

	private class EventItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					EventDetailsActivity.class);
			intent.putExtra("event", events.get(position));
			startActivity(intent);
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private class EventLoader extends AsyncTask<Void, Void, String>
	{
		
		Event e;

		public EventLoader(){
			e = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentEvent.this.getActivity());
			pDialog.setMessage("Chargement des évènements");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Void... params)
		{
			// TODO Get Event
			//e = APIConnection.getEvent();
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			//if (a.getArray() != null)
				prepareListView(null);
		}
	}
}
