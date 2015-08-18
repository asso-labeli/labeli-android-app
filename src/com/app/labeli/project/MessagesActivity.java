package com.app.labeli.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MessagesActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private ListView listView;
	private EditText editText;
	private Button buttonSend;
	private ArrayList<Message> messages;
	private Project p;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_messages);

		editText = (EditText)findViewById(R.id.activity_messages_edit_text);
		buttonSend = (Button)findViewById(R.id.activity_messages_button_send);
		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (editText.getText().length() != 0){
					new AddMessage(editText.getText().toString()).execute();
				}
			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Messages");

		p = getIntent().getExtras().getParcelable("project");
		Log.i("Coucou", p.getId());

		new LoadMessages().execute();
	}

	public void prepareListView(ArrayList<Message> al){
		sortByInversedDate(al);
		messages = al;
		listView = (ListView) findViewById(R.id.activity_messages_list_view);

		if (al != null)
			listView.setAdapter(new ListAdapterMessage(getApplicationContext(),
					al));
		listView.setOnItemClickListener(new MessageItemClickListener());
	}

	private class MessageItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

		}

	}

	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_messages, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.activity_messages_menu_refresh:
			new LoadMessages().execute();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void sortByInversedDate(ArrayList<Message> m){
		Collections.sort(m);
		Collections.reverse(m);
	}

	private class AddMessage extends AsyncTask<Void, Void, String>
	{
		private String content;
		private Message m;

		public AddMessage(String content){
			this.content = content;
			m = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected String doInBackground(Void... params)
		{
			m = APIConnection.createMessage(p.getId(), content);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			if (m == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de la création du message", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Message posté", Toast.LENGTH_LONG).show();
				
				// Close keyboard
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, 0);
				
				// Remove focus from editText
				View current = MessagesActivity.this.getCurrentFocus();
				if (current != null) current.clearFocus();
				
				editText.setText("");
				
				// Add new message
				messages.add(m);
				prepareListView(messages);
			}
		}
	}

	private class LoadMessages extends AsyncTask<Void, Void, String>
	{
		ArrayList<Message> a;
		ProgressDialog pDialog;

		public LoadMessages(){
			a = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MessagesActivity.this);
			pDialog.setMessage("Chargement des messages");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Void... api)
		{
			a = APIConnection.getMessages(p.getId());
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			prepareListView(a);
		}
	}
}
