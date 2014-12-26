package com.app.labeli.event;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.app.callback.APICallback;
import com.app.labeli.ListAdapterNavigationDrawer;
import com.app.labeli.MainActivity;
import com.app.labeli.R;
import com.app.labeli.R.layout;
import com.app.labeli.member.FragmentMember;
import com.app.labeli.member.ItemMember;
import com.tools.APIDataParser;
import com.tools.FileTools;

import labeli.Labeli;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentEvent extends Fragment {

	private ListView listView;
	private ProgressDialog pDialog;
	private ArrayList<ItemEvent> events;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			super.onCreate(savedInstanceState);
		else {
			new EventLoader().execute(MainActivity.api);
		}
		
		getActivity().getActionBar().setTitle("Evénements");

		return inflater.inflate(R.layout.fragment_event, container, false);
	}

	public void prepareListView(ArrayList<ItemEvent> al){
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

	private class EventLoader extends AsyncTask<Labeli, Void, String>
	{
		APICallback a;

		public EventLoader(){
			a = new APICallback();
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

		protected String doInBackground(Labeli... api)
		{
			api[0].async.getEvents(a);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (a.getArray() != null)
				new ImageEventLoader(APIDataParser.parseEventList(a.getArray())).execute();
		}
	}

	private class ImageEventLoader extends AsyncTask<Void, Void, String>
	{
		ArrayList<ItemEvent> a;
		RequestQueue q;

		public ImageEventLoader(ArrayList<ItemEvent> a){
			this.a = a;
			this.q = Volley.newRequestQueue(getActivity());
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentEvent.this.getActivity());
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Void... v)
		{
			for (ItemEvent im : a){
				if (im.getPictureURL() != null && !im.getPictureURL().equals("")){
					Bitmap bm = null;

					final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(getActivity(), im.getPictureURL()));
					String wallpaperURLStr = "http://labeli.org/" + im.getPictureURL();
					String localFile = FileTools.getLocalFileFromURL(im.getPictureURL());

					if (!dataFile.exists()){
						Log.i("Net", "Chargement de " + wallpaperURLStr);

						RequestFuture<Bitmap> f = RequestFuture.newFuture();
						ImageRequest ir = new ImageRequest(wallpaperURLStr.replace(" ", "%20"), f, 0, 0, null, null);
						ir.setRetryPolicy(new DefaultRetryPolicy(
								5000, 
								DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
								DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
						q.add(ir);

						try {
							bm = f.get();
							FileTools.writeBitmapToFile(getActivity(), localFile, bm);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block 
							e.printStackTrace();
						}
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			prepareListView(a);
			pDialog.dismiss();
		}

	}



}
