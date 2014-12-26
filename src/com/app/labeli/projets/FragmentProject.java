package com.app.labeli.projets;

import java.util.ArrayList;
import com.app.callback.APICallback;
import com.app.labeli.MainActivity;
import com.app.labeli.R;
import com.tools.APIDataParser;
import labeli.Labeli;
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

		return inflater.inflate(R.layout.fragment_event, container, false);
	}

	public void prepareListView(ArrayList<ItemProject> al){
		projects = al;
		listView = (ListView) this.getView().findViewById(R.id.fragment_event_list_view);

		listView.setAdapter(new ListAdapterProject(this.getActivity().getApplicationContext(),
				al));
		listView.setOnItemClickListener(new ProjectItemClickListener());
	}

	private class ProjectItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			//			Intent intent = new Intent(getActivity().getApplicationContext(), 
			//					ProjectDetailsActivity.class);
			//			intent.putExtra("event", projects.get(position));
			//			startActivity(intent);
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

//	private class ImageProjectLoader extends AsyncTask<Void, Void, String>
//	{
//		ArrayList<ItemProject> a;
//		RequestQueue q;
//
//		public ImageProjectLoader(ArrayList<ItemProject> a){
//			this.a = a;
//			this.q = Volley.newRequestQueue(getActivity());
//		}
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			pDialog = new ProgressDialog(FragmentProject.this.getActivity());
//			pDialog.setMessage("Chargement des images");
//			pDialog.setIndeterminate(false);
//			pDialog.setCancelable(false);
//			pDialog.show();
//		}
//
//		protected String doInBackground(Void... v)
//		{
//			for (ItemProject ip : a){
//				if (ip.getPictureURL() != null && !ip.getPictureURL().equals("")){
//					Bitmap bm = null;
//
//					final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(getActivity(), ip.getPictureURL()));
//					String wallpaperURLStr = "http://labeli.org/" + ip.getPictureURL();
//					String localFile = FileTools.getLocalFileFromURL(ip.getPictureURL());
//
//					if (!dataFile.exists()){
//						Log.i("Net", "Chargement de " + wallpaperURLStr);
//
//						RequestFuture<Bitmap> f = RequestFuture.newFuture();
//						ImageRequest ir = new ImageRequest(wallpaperURLStr.replace(" ", "%20"), f, 0, 0, null, null);
//						ir.setRetryPolicy(new DefaultRetryPolicy(
//								5000, 
//								DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
//								DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//						q.add(ir);
//
//						try {
//							bm = f.get();
//							FileTools.writeBitmapToFile(getActivity(), localFile, bm);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ExecutionException e) {
//							// TODO Auto-generated catch block 
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(String file_url) {
//			prepareListView(a);
//			pDialog.dismiss();
//		}
//
//	}



}
