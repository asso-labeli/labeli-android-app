package com.app.labeli.member;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * > @AddProjectActivity
 *
 * Activity to add a new project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class AddMemberActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private EditText editTextLastName, editTextFirstName, editTextEmail;
	private Button buttonValidate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_member);
		
		// Remove any focus
		View current = getCurrentFocus();
		if (current != null) current.clearFocus();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Nouveau membre");
		
		editTextLastName = (EditText)findViewById(R.id.activity_add_member_edit_text_last_name);
		editTextFirstName = (EditText)findViewById(R.id.activity_add_member_edit_text_first_name);
		editTextEmail = (EditText)findViewById(R.id.activity_add_member_edit_text_email);
		
		buttonValidate = (Button)findViewById(R.id.activity_add_member_button_validate);
		buttonValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				checkInput(arg0);
			}
		});
	}
	
	public void checkInput(View arg){
		if (editTextLastName.length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un nom", Toast.LENGTH_SHORT).show();
		else if (editTextFirstName.length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un prénom", Toast.LENGTH_SHORT).show();
		else if (editTextEmail.length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer une adresse e-mail", Toast.LENGTH_SHORT).show();
		else 
			new AddMember(editTextFirstName.getText().toString(), 
					editTextLastName.getText().toString(), 
					editTextEmail.getText().toString()).execute();
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
	
	private class AddMember extends AsyncTask<Void, Void, String>
	{
		private String firstName, lastName, email;
		private Member m;

		public AddMember(String firstName, String lastName, String email){
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			m = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params)
		{
			m = APIConnection.createUser(firstName, lastName, email);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			if (m == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de la création du membre", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Membre crée ! Un e-mail a été envoyé avec le mot de passe !", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
}
