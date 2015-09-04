package com.app.labeli.project;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.tools.DeviceTools;
import com.tools.FileTools;
import com.tools.HTMLTools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * > @ProjectDetailsActivity
 *
 * Activity to show informations on a project
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ProjectDetailsActivity extends FragmentActivity{
	private ImageView imageView;
	private TextView textViewName, textViewAuthor, textViewDescription;
	private ProgressDialog pDialog;
	private Project project;

	private static final int EDIT_PROJECT = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_project_details);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Détail");

		project = this.getIntent().getExtras().getParcelable("project");

		refresh(true);
	}

	public void refresh(boolean refreshDataWithApi){
		if (refreshDataWithApi)
			new ProjectLoader().execute();
		else
			updateViews();
	}

	public void updateViews(){
		imageView = (ImageView) findViewById(R.id.activity_project_details_picture);
		// Image max : 1/3 of screen
		imageView.setMaxHeight((int)(DeviceTools.getHeightScreen(this)/3));

		textViewName = (TextView) findViewById(R.id.activity_project_details_name);
		textViewAuthor = (TextView) findViewById(R.id.activity_project_details_author);
		textViewDescription = (TextView) findViewById(R.id.activity_project_details_description);

		textViewName.setText(project.getName());
		textViewAuthor.setText("par " + project.getAuthor().getFirstName() + " " + project.getAuthor().getLastName());
		textViewDescription.setText(HTMLTools.HTMLToText(project.getDescription()));

		if (!project.getPictureURL().equals(""))
			new ImageLoader().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_project_details, menu);

		MenuItem editItem = menu.findItem(R.id.activity_project_details_menu_edit);
		if (APIConnection.isLogged() && 
				(APIConnection.loggedUserIsAdmin() || 
						APIConnection.getLoggedUser().equals(project.getAuthor())))
			editItem.setVisible(true);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch(requestCode) {
			case (EDIT_PROJECT) :
				refresh(true);
			break;
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		Intent intent;

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.activity_project_details_menu_messages:
			intent = new Intent(getApplicationContext(), 
					MessagesActivity.class);
			intent.putExtra("project", project);
			startActivity(intent);
			return true;
		case R.id.activity_project_details_menu_edit :
			intent = new Intent(getApplicationContext(), 
					EditProjectActivity.class);
			intent.putExtra("project", project);
			startActivityForResult(intent, EDIT_PROJECT);
			return true;
		case R.id.activity_project_details_menu_refresh :
			refresh(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void prepareImageView(String url){
		Bitmap myBitmap = BitmapFactory.decodeFile(url);

		imageView.setImageBitmap(myBitmap);
	}

	private class ImageLoader extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Need to be checked because pDialog is use in ProjectLoader AND
			// 	ImageLoader
			if (pDialog == null || !pDialog.isShowing())
				pDialog = new ProgressDialog(ProjectDetailsActivity.this);
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Boolean doInBackground(Void... v) {
			return APIConnection.loadImage(ProjectDetailsActivity.this, project, 
					DeviceTools.getWidthScreen(ProjectDetailsActivity.this));
		}

		@Override
		protected void onPostExecute(Boolean b) {
			prepareImageView(FileTools.getAbsolutePathLocalFileFromURL(ProjectDetailsActivity.this, project.getPictureURL()));
			// Need to be checked because pDialog is use in ProjectLoader AND
			// 	ImageLoader
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}

	private class ProjectLoader extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ProjectDetailsActivity.this);
			pDialog.setMessage("Chargement du projet");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... api) {
			project = APIConnection.getProject(project.getId());
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			updateViews();
			// Need to be checked because pDialog is use in ProjectLoader AND
			// 	ImageLoader
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
}
