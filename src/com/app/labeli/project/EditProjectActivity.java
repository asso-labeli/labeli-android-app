package com.app.labeli.project;

import java.io.File;
import net.tools.APIConnection;
import com.app.labeli.R;
import com.app.labeli.member.Member;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.tools.FileTools;

import android.app.Activity;
import android.app.ProgressDialog;
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
	private Spinner spinnerType, spinnerStatus;
	private FloatLabel floatLabelName, floatLabelAuthor, floatLabelDescription;
	private Button buttonValidate, buttonChooseImage;
	private Project project;
	private ProgressDialog pDialog;
	private boolean imageChanged;

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

		imageChanged = false;
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
		else if (buttonChooseImage.getText().length() > 0 && imageChanged)
			new UploadImage().execute(floatLabelName.getEditText().getText().toString(), buttonChooseImage.getText().toString());
		else {
			new EditProject().execute(getProjectWithFieldsValues());
		}
	}

	public Project getProjectWithFieldsValues(){
		Project p2 = (Project) project.clone();

		p2.setName(floatLabelName.getEditText().getText().toString());
		p2.setDescription(floatLabelDescription.getEditText().getText().toString());
		p2.setStatus(spinnerStatus.getSelectedItemPosition());
		p2.setType(spinnerType.getSelectedItemPosition());
		// Little hack to set author username
		p2.getAuthor().setUsername(floatLabelAuthor.getEditText().getText().toString());

		return p2;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch(requestCode) {
			case (AUTHOR_SELECTION) :
				Member m = (Member)data.getExtras().get("member");
			floatLabelAuthor.setText(m.getUsername());
			break;
			
			case (IMAGE_SELECTION) :
				Uri selectedImageUri = data.getData();
			buttonChooseImage.setText(getPath(selectedImageUri));
			imageChanged = true;
			break;
			}
		}
	}

	public String getPath(Uri contentUri) {
		String res = null;
		String[] proj = { MediaColumns.DATA };
		Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
		if(cursor.moveToFirst()){
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

	private class EditProject extends AsyncTask<Project, Void, Project>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		/**
		 * 
		 * @param params : project with new datas
		 * @return project created by parsing response from API
		 */
		protected Project doInBackground(Project... p)
		{
			return APIConnection.editProject(p[0]);
		}

		@Override
		protected void onPostExecute(Project p) {
			if (p == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de l'édition du projet", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getApplicationContext(), "Projet edité", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}

	private class UploadImage extends AsyncTask<String, Void, String>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProjectActivity.this);
			pDialog.setMessage("Upload de l'image");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		/**
		 * 
		 * @param params : 0 = projectName, 1 = path
		 * @return
		 */
		protected String doInBackground(String... params) {
			String name = FileTools.createFilenameForProject(params[0], 
					params[1].substring(params[1].lastIndexOf('.')));
			File f = new File(params[1]);
			if (APIConnection.makeFileRequest(f, name))
				return name;
			
			return null;
			
		}

		@Override
		protected void onPostExecute(String pictureName) {
			pDialog.dismiss();
			if (pictureName == null)
				Toast.makeText(getApplicationContext(), "Erreur lors de l'upload de l'image. Veuillez réessayer.", Toast.LENGTH_LONG).show();
			else {
				Project p = getProjectWithFieldsValues();
				p.setPictureURL(pictureName);
				new EditProject().execute(p);
			}
		}
	}
}
