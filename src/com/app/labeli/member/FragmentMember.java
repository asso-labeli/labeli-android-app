package com.app.labeli.member;

import java.util.ArrayList;
import java.util.Locale;

import net.tools.APIConnection;

import com.app.labeli.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * > @FragmentMember
 *
 * Fragment to show members
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class FragmentMember extends Fragment {

	public ListView listView;
	public TextView textView;
	private ProgressDialog pDialog;
	private ArrayList<Member> items;
	private ListAdapterMember adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getActionBar().setTitle("Membres");
		setHasOptionsMenu(true);

		// Remove any focus
		View current = getActivity().getCurrentFocus();
		if (current != null) current.clearFocus();

		refresh();

		return inflater.inflate(R.layout.fragment_member, container, false);
	}

	public void refresh(){
		new MemberLoader().execute();
	}

	@SuppressWarnings("unchecked")
	public void prepareListView(ArrayList<Member> al){
		listView = (ListView) getView().findViewById(R.id.fragment_member_list_view);		

		if (al != null){
			items = (ArrayList<Member>) al.clone();
			adapter = new ListAdapterMember(this.getActivity().getApplicationContext(), 
					al);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new EventItemClickListener());
		}
	}

	public void prepareTextEdit(){
		textView = (TextView) getView().findViewById(R.id.fragment_member_edit_text);
		textView.addTextChangedListener(new EventItemTextWatcher());
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.fragment_member, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.fragment_member_menu_add_member:
			Intent intent = new Intent(getActivity().getApplicationContext(), 
					AddMemberActivity.class);
			startActivity(intent);
			return true;
		case R.id.fragment_member_menu_refresh :
			refresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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

	private class MemberLoader extends AsyncTask<Void, Void, ArrayList<Member>> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentMember.this.getActivity());
			pDialog.setMessage("Chargement des membres");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected ArrayList<Member> doInBackground(Void... params) {
			return APIConnection.getUsers();
		}

		@Override
		protected void onPostExecute(ArrayList<Member> v) {
			prepareListView(v);
			prepareTextEdit();
			pDialog.dismiss();
		}
	}
}
