package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.app.labeli.member.Member;
import com.iangclifton.android.floatlabel.FloatLabel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * > @EditProjectActivity
 *
 * Activity to edit a project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class EditProjectActivity extends FragmentActivity{

	Animation animFadeIn, animFadeOut;
	private Spinner spinnerType, spinnerStatus;
	private FloatLabel floatLabelName, floatLabelAuthor, floatLabelDescription;
	private Button buttonValidate, buttonChooseImage;
	private Project project;

	private static final int AUTHOR_SELECTION = 1;
	private static final int IMAGE_SELECTION = 2;

	@Override
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

		floatLabelName = (FloatLabel)findViewById(R.id.activity_edit_project_float_label_name);
		floatLabelName.setText(project.getName());

		floatLabelDescription = (FloatLabel)findViewById(R.id.activity_edit_project_float_label_description);
		if (project.getDescription() != null)
			floatLabelDescription.setText(project.getDescription());

		floatLabelAuthor = (FloatLabel)findViewById(R.id.activity_edit_project_float_label_author);
		floatLabelAuthor.setText(project.getAuthor().getUsername());
		floatLabelAuthor.getEditText().setKeyListener(null);
		floatLabelAuthor.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {

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

		buttonChooseImage = (Button)findViewById(R.id.activity_edit_project_button_choose_image);
		buttonChooseImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				openGallery();
			}
		});
	}

	public void openGallery(){
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,"Choisissez une image "), IMAGE_SELECTION);
	}

	public void checkInput(View arg){
		if (floatLabelName.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez rentrer un nom", Toast.LENGTH_SHORT).show();
		else if (floatLabelAuthor.getEditText().length() == 0)
			Toast.makeText(getApplicationContext(), "Veuillez choisir un auteur", Toast.LENGTH_SHORT).show();
		else 
			new EditProject(floatLabelName.getEditText().getText().toString(),
					spinnerStatus.getSelectedItemPosition(),
					floatLabelDescription.getEditText().getText().toString(),
					spinnerType.getSelectedItemPosition(), 
					floatLabelAuthor.getEditText().getText().toString()).execute();
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
		case (IMAGE_SELECTION) :
			Uri selectedImageUri = data.getData();
			buttonChooseImage.setText(getPath(selectedImageUri));
		break;
		}
	}
	
	public String getPath(Uri contentUri) {
	    String res = null;
	    String[] proj = { MediaColumns.DATA };
	    Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
	    if(cursor.moveToFirst()){;
	       int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
	       res = cursor.getString(column_index);
	    }
	    cursor.close();
	    return res;
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

		@Override
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
