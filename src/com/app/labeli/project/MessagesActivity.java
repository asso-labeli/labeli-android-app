package com.app.labeli.project;

import java.util.ArrayList;
import java.util.Collections;

import net.tools.APIConnection;

import com.app.labeli.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * > @MessagesActivity
 *
 * Activity to show messages on a project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class MessagesActivity extends FragmentActivity{
	private ListView listView;
	private EditText editText;
	private Button buttonSend;
	private ProgressDialog pDialog;
	private ArrayList<Message> messages;
	private Project p;
	// Used to save position of edited message
	private int currentPosition;

	private static final int ACTIVITY_EDIT_MESSAGE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_messages);

		editText = (EditText)findViewById(R.id.activity_messages_edit_text);
		buttonSend = (Button)findViewById(R.id.activity_messages_button_send);
		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (editText.getText().length() != 0){
					new AddMessage().execute(editText.getText().toString());
				}
			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Messages");

		p = getIntent().getExtras().getParcelable("project");

		new LoadMessages().execute();
	}

	public void prepareListView(ArrayList<Message> al){
		sortByInversedDate(al);
		messages = al;
		listView = (ListView) findViewById(R.id.activity_messages_list_view);

		if (al != null)
			listView.setAdapter(new ListAdapterMessage(getApplicationContext(),
					al));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				if (APIConnection.isLogged() &&
						messages.get(position).getAuthor().equals(APIConnection.getLoggedUser())){
					AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

					builder.setTitle(R.string.activity_messages_dialog_item_title)
					.setItems(R.array.activity_messages_dialog_items, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Edit case
							if (which == 0){
								Intent intent = new Intent(getApplicationContext(), 
										EditMessageActivity.class);
								intent.putExtra("message", messages.get(position));
								startActivityForResult(intent, ACTIVITY_EDIT_MESSAGE);
								currentPosition = position;
							} else if (which == 1){
								new DeleteMessage().execute(messages.get(position).getId());
							}
						}
					});

					builder.create().show();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_messages, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case (ACTIVITY_EDIT_MESSAGE) :
			if (resultCode == Activity.RESULT_OK) {
				Message m = (Message)data.getExtras().get("message");
				messages.set(currentPosition, m);
				sortByInversedDate(messages);
			}
		break;
		}
	}

	public void sortByInversedDate(ArrayList<Message> m){
		Collections.sort(m);
		Collections.reverse(m);
	}

	private class AddMessage extends AsyncTask<String, Void, Message>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Message doInBackground(String... params){
			if (params[0] != null)
				return APIConnection.createMessage(p.getId(), params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Message m) {
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

	private class LoadMessages extends AsyncTask<Void, Void, ArrayList<Message>>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MessagesActivity.this);
			pDialog.setMessage("Chargement des messages");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected ArrayList<Message> doInBackground(Void... api){
			return APIConnection.getMessages(p.getId());
		}

		@Override
		protected void onPostExecute(ArrayList<Message> a) {
			pDialog.dismiss();
			prepareListView(a);
		}
	}

	private class DeleteMessage extends AsyncTask<String, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(String... params){
			if (APIConnection.deleteMessage(params[0])){
				removeMessageWithID(params[0]);
				return true;
			}
			
			return false;
		}

		@Override
		protected void onPostExecute(Boolean success) {
			if (!success)
				Toast.makeText(getApplicationContext(), "Erreur lors de la suppression du message", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Message supprimé", Toast.LENGTH_LONG).show();
				prepareListView(messages);
			}
		}
	}

	private void removeMessageWithID(String id){
		Message tmp = null;
		for (Message m : messages)
			if (m.getId().equals(id)){
				tmp = m;
				break;
			}

		if (tmp != null)
			messages.remove(tmp);
	}
}
