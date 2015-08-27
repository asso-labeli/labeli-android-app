package com.app.labeli.member;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;
import com.iangclifton.android.floatlabel.FloatLabel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

/**
 * > @AddMemberActivity
 *
 * Activity to add a new member
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class AddMemberActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private FloatLabel floatLabelLastName, floatLabelFirstName, floatLabelEmail;
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
		
		floatLabelLastName = (FloatLabel)findViewById(R.id.activity_add_member_float_label_last_name);
		floatLabelFirstName = (FloatLabel)findViewById(R.id.activity_add_member_float_label_first_name);
		floatLabelEmail = (FloatLabel)findViewById(R.id.activity_add_member_float_label_email);
		
		buttonValidate = (Button)findViewById(R.id.activity_add_member_button_validate);
		buttonValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				checkInput(arg0);
			}
		});
	}
	
	public void checkInput(View arg){
		if (floatLabelLastName.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un nom", Toast.LENGTH_SHORT).show();
		else if (floatLabelFirstName.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un prénom", Toast.LENGTH_SHORT).show();
		else if (floatLabelEmail.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer une adresse e-mail", Toast.LENGTH_SHORT).show();
		else 
			new AddMember(floatLabelFirstName.getEditText().getText().toString(), 
					floatLabelLastName.getEditText().getText().toString(), 
					floatLabelEmail.getEditText().getText().toString()).execute();
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
