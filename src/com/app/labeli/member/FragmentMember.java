package com.app.labeli.member;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.app.callback.APICallback;
import com.app.labeli.MainActivity;
import com.app.labeli.R;
import com.tools.APIDataParser;
import com.tools.FileTools;

import labeli.Labeli;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentMember extends Fragment {

	public ListView listView;
	public TextView textView;
	private ProgressDialog pDialog;
	private ArrayList<ItemMember> items;
	private ListAdapterMember adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		new MemberLoader().execute(MainActivity.api);
		
		getActivity().getActionBar().setTitle("Membres");

		return inflater.inflate(R.layout.fragment_member, container, false);
	}

	public void prepareListView(ArrayList<ItemMember> al){
		listView = (ListView) getView().findViewById(R.id.fragment_member_list_view);		

		adapter = new ListAdapterMember(this.getActivity().getApplicationContext(), 
				al);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new EventItemClickListener());
	}

	public void prepareTextEdit(){
		textView = (TextView) getView().findViewById(R.id.fragment_member_edit_text);
		textView.addTextChangedListener(new EventItemTextWatcher());
	}

	private class EventItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					MemberDetailsActivity.class);
			intent.putExtra("member", items.get(position));
			startActivity(intent);
		}

	}

	private class EventItemTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {
			ArrayList<ItemMember> tmp = new ArrayList<ItemMember>();
			
			if (arg0.length() > 0){
				for (ItemMember i : items){
					if ((i.getFirstName() + " " + i.getLastName()).toLowerCase(Locale.FRANCE)
							.contains(arg0.toString().toLowerCase(Locale.FRANCE)))
						tmp.add(i);
				}
			}
			else
				tmp = (ArrayList<ItemMember>) items.clone();

			adapter.updateDatas(tmp);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

	}

	private class MemberLoader extends AsyncTask<Labeli, Void, String>
	{
		APICallback a;

		public MemberLoader(){
			a = new APICallback();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentMember.this.getActivity());
			pDialog.setMessage("Chargement des membres");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Labeli... api)
		{
			api[0].async.getUsers(a);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (a.getArray() != null)
				new ImageMemberLoader(APIDataParser.parseMemberList(a.getArray())).execute();
		}
	}

	private class ImageMemberLoader extends AsyncTask<Void, Void, String>
	{
		ArrayList<ItemMember> a;
		RequestQueue q;

		public ImageMemberLoader(ArrayList<ItemMember> a){
			this.a = a;
			this.q = Volley.newRequestQueue(getActivity());
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentMember.this.getActivity());
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Void... v)
		{
			for (ItemMember im : a){
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

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String file_url) {
			prepareListView(a);
			prepareTextEdit();
			items = (ArrayList<ItemMember>) a.clone();
			pDialog.dismiss();
		}

	}


}
