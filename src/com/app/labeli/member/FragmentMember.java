package com.app.labeli.member;

import java.util.ArrayList;
import java.util.Locale;

import net.tools.APIConnection;
import net.tools.MySingleton;

import com.app.labeli.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
	private ArrayList<Member> items;
	private ListAdapterMember adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		new MemberLoader().execute();

		getActivity().getActionBar().setTitle("Membres");

		return inflater.inflate(R.layout.fragment_member, container, false);
	}

	public void prepareListView(ArrayList<Member> al){
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
			intent.putExtra("member", (Member)adapter.getItem(position));
			startActivity(intent);
		}

	}

	private class EventItemTextWatcher implements TextWatcher {

		@SuppressWarnings("unchecked")
		@Override
		public void afterTextChanged(Editable arg0) {
			ArrayList<Member> tmp = new ArrayList<Member>();

			if (arg0.length() > 0){
				for (Member i : items){
					if ((i.getFirstName() + " " + i.getLastName()).toLowerCase(Locale.FRANCE)
							.contains(arg0.toString().toLowerCase(Locale.FRANCE)))
						tmp.add(i);
				}
			}
			else
				tmp = (ArrayList<Member>) items.clone();

			adapter.updateDatas(tmp);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}
	}

	private class MemberLoader extends AsyncTask<Void, Void, String>
	{
		ArrayList<Member> v;

		public MemberLoader(){
			v = null;
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

		protected String doInBackground(Void... params)
		{
			v = APIConnection.getUsers();
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			new ImageMemberLoader(v).execute();
		}
	}

	private class ImageMemberLoader extends AsyncTask<Void, Void, String>
	{
		ArrayList<Member> v;

		public ImageMemberLoader(ArrayList<Member> v){
			this.v = v;
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

		protected String doInBackground(Void... params)
		{
			for (Member im : v)
				MySingleton.loadImage(getActivity(), im);
			
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String file_url) {
			prepareListView(v);
			prepareTextEdit();
			items = (ArrayList<Member>) v.clone();
			pDialog.dismiss();
		}

	}


}
