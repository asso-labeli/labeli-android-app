package com.app.labeli.project;

import java.util.ArrayList;
import java.util.Locale;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.FragmentMember;
import com.app.labeli.member.ListAdapterMember;
import com.app.labeli.member.Member;
import com.app.labeli.member.MemberDetailsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class AuthorSelectionActivity extends FragmentActivity{
	
	Animation animFadeIn, animFadeOut;
	public ListView listView;
	public TextView textView;
	private ProgressDialog pDialog;
	private ArrayList<Member> items;
	private ListAdapterMember adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_author_selection);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Sélection de l'auteur");
		
		new MemberLoader().execute();
	}
	
	public void onBackPressed() {
		setResult(RESULT_CANCELED);
        finish();
    }
	
	public void prepareListView(ArrayList<Member> al){
		listView = (ListView) findViewById(R.id.activity_author_selection_list_view);		

		adapter = new ListAdapterMember(getApplicationContext(), 
				al);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new EventItemClickListener());
	}

	public void prepareTextEdit(){
		textView = (TextView) findViewById(R.id.activity_author_selection_edit_text);
		textView.addTextChangedListener(new EventItemTextWatcher());
	}

	private class EventItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent();
			intent.putExtra("member", (Member)adapter.getItem(position));
			setResult(RESULT_OK, intent);
			finish();
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
			pDialog = new ProgressDialog(AuthorSelectionActivity.this);
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
