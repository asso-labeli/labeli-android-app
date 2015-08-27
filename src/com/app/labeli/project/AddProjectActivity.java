package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * > @AddProjectActivity
 *
 * Activity to add a new project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class AddProjectActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private Spinner spinnerType;
	private EditText editTextName, editTextAuthor;
	private Button buttonValidate;

	private static final int AUTHOR_SELECTION = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_project);
		
		// Remove any focus
		View current = getCurrentFocus();
		if (current != null) current.clearFocus();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Nouveau projet");

		spinnerType = (Spinner) findViewById(R.id.activity_add_project_spinner_type);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.project_type_array, R.layout.spinner_white);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerType.setAdapter(adapter);

		editTextName = (EditText)findViewById(R.id.activity_add_project_edit_text_name);
		editTextAuthor = (EditText)findViewById(R.id.activity_add_project_edit_text_author);
		editTextAuthor.setKeyListener(null);
		editTextAuthor.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				Intent intent = new Intent(getApplicationContext(), 
						AuthorSelectionActivity.class);
				startActivityForResult(intent, AUTHOR_SELECTION);
			}
		});
		
		buttonValidate = (Button)findViewById(R.id.activity_add_project_button_validate);
		buttonValidate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				checkInput(arg0);
			}
		});
	}
	
	public void checkInput(View arg){
		if (editTextName.length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un nom", Toast.LENGTH_SHORT).show();
		else if (editTextAuthor.length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez choisir un auteur", Toast.LENGTH_SHORT).show();
		else 
			new AddProject(editTextName.getText().toString(), 
					String.valueOf(spinnerType.getSelectedItemPosition()), 
					editTextAuthor.getText().toString()).execute();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case (AUTHOR_SELECTION) :
			if (resultCode == Activity.RESULT_OK) {
				Member m = (Member)data.getExtras().get("member");
				editTextAuthor.setText(m.getUsername());
			}
			break;
		}
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
	
	private class AddProject extends AsyncTask<Void, Void, String>
	{
		private String name, type, authorUsername;
		private Project p;

		public AddProject(String name, String type, String authorUsername){
			this.name = name;
			this.type = type;
			this.authorUsername = authorUsername;
			p = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params)
		{
			p = APIConnection.createProject(name, type, authorUsername);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			if (p == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de la création du projet", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Projet crée", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
}
