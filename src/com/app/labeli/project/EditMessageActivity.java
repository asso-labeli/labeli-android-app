package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.iangclifton.android.floatlabel.FloatLabel;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * > @EditProjectActivity
 *
 * Activity to edit a project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class EditMessageActivity extends FragmentActivity{
	private FloatLabel floatLabelContent;
	private Button buttonValidate;
	private Message message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		message = this.getIntent().getExtras().getParcelable("message");

		setContentView(R.layout.activity_edit_message);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Edition");
		
		floatLabelContent = (FloatLabel)findViewById(R.id.activity_edit_message_float_label_content);
		floatLabelContent.setText(message.getContent());
		
		buttonValidate = (Button)findViewById(R.id.activity_edit_message_button_validate);
		buttonValidate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkInput(arg0);
			}
		});
	}
	
	public void checkInput(View arg){
		if (floatLabelContent.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un message", Toast.LENGTH_SHORT).show();
		else 
			new EditMessage().execute(floatLabelContent.getEditText().getText().toString());
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private class EditMessage extends AsyncTask<String, Void, Message>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Message doInBackground(String... params){
			return APIConnection.editMessage(message.getId(), params[0]);
		}

		@Override
		protected void onPostExecute(Message m) {
			if (m == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de l'édition du message", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Message edité", Toast.LENGTH_LONG).show();
				
				// Close keyboard
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, 0);
				Log.i("Coucou", " " + imm.isActive() + " " + imm.isAcceptingText() + " ");
				
				Intent intent = new Intent();
				intent.putExtra("message", m);
				setResult(RESULT_OK, intent);
				finish();
			}
		}
	}
}
