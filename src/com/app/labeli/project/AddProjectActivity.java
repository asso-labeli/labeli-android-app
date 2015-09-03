package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;
import com.iangclifton.android.floatlabel.FloatLabel;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
	private Spinner spinnerType;
	private Button buttonValidate;
	private FloatLabel floatLabelName, floatLabelAuthor;
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

		floatLabelName = (FloatLabel)findViewById(R.id.activity_add_project_float_label_name);
		floatLabelAuthor = (FloatLabel)findViewById(R.id.activity_add_project_float_label_author);
		floatLabelAuthor.getEditText().setKeyListener(null);
		floatLabelAuthor.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

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
		if (floatLabelName.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un nom", Toast.LENGTH_SHORT).show();
		else if (floatLabelAuthor.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez choisir un auteur", Toast.LENGTH_SHORT).show();
		else 
			new AddProject().execute(floatLabelName.getEditText().getText().toString(), 
					String.valueOf(spinnerType.getSelectedItemPosition()), 
					floatLabelAuthor.getEditText().getText().toString());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case (AUTHOR_SELECTION) :
			if (resultCode == Activity.RESULT_OK) {
				Member m = (Member)data.getExtras().get("member");
				floatLabelAuthor.setText(m.getUsername());
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

	private class AddProject extends AsyncTask<String, Void, Project>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		/**
		 * 
		 * @param params [0] = name - [1] = type - [2] = authorUsername
		 * @return
		 */
		protected Project doInBackground(String... params) {
			return APIConnection.createProject(params[0], params[1], params[2]);
		}

		@Override
		protected void onPostExecute(Project p) {
			if (p == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de la création du projet", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Projet crée", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
}
