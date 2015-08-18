package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
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

public class EditProjectActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private Spinner spinnerType, spinnerStatus;
	private EditText editTextName, editTextAuthor, editTextDescription;
	private Button buttonValidate;
	private Project project;

	private static final int AUTHOR_SELECTION = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		project = this.getIntent().getExtras().getParcelable("project");

		setContentView(R.layout.activity_edit_project);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Edition");

		spinnerType = (Spinner) findViewById(R.id.activity_edit_project_spinner_type);
		ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
				R.array.project_type_array, R.layout.spinner_white);
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerType.setAdapter(adapterType);
		spinnerType.setSelection(project.getType());
		
		spinnerStatus = (Spinner) findViewById(R.id.activity_edit_project_spinner_status);
		ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(this,
				R.array.project_status_array, R.layout.spinner_white);
		adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerStatus.setAdapter(adapterStatus);
		spinnerStatus.setSelection(project.getStatus());

		editTextName = (EditText)findViewById(R.id.activity_edit_project_edit_text_name);
		editTextName.setText(project.getName());
		
		editTextDescription = (EditText)findViewById(R.id.activity_edit_project_edit_text_description);
		if (project.getDescription() != null)
			editTextDescription.setText(project.getDescription());
		
		editTextAuthor = (EditText)findViewById(R.id.activity_edit_project_edit_text_author);
		editTextAuthor.setText(project.getAuthor().getUsername());
		editTextAuthor.setKeyListener(null);
		editTextAuthor.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				Intent intent = new Intent(getApplicationContext(), 
						AuthorSelectionActivity.class);
				startActivityForResult(intent, AUTHOR_SELECTION);
			}
		});
		
		buttonValidate = (Button)findViewById(R.id.activity_edit_project_button_validate);
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
			new EditProject(editTextName.getText().toString(),
					spinnerStatus.getSelectedItemPosition(),
					editTextDescription.getText().toString(),
					spinnerType.getSelectedItemPosition(), 
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

	public void onBackPressed() {
		super.onBackPressed();
	}

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
	
	private class EditProject extends AsyncTask<Void, Void, String>
	{
		private String name, authorUsername, description;
		private int status, type;
		private Project p;

		public EditProject(String name, int status, String description, int type, String authorUsername){
			this.name = name;
			this.status = status;
			this.description = description;
			this.type = type;
			this.authorUsername = authorUsername;
			p = null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected String doInBackground(Void... params)
		{
			p = APIConnection.editProject(project.getId(), name, status, description, type, authorUsername);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			if (p == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de l'édition du projet", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Projet edité", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
}
