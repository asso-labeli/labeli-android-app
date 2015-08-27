package com.app.labeli.project;

import net.tools.MySingleton;

import com.app.labeli.R;
import com.tools.FileTools;
import com.tools.HTMLTools;

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
import android.view.animation.Animation;
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
	
	Animation animFadeIn, animFadeOut;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_project_details);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Détail");

		project = this.getIntent().getExtras().getParcelable("project");

		imageView = (ImageView) findViewById(R.id.activity_project_details_picture);
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
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void onBackPressed() {
        super.onBackPressed();
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent intent;
		
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	finish();
	        	return true;
	        case R.id.activity_project_details_messages:
	        	intent = new Intent(getApplicationContext(), 
						MessagesActivity.class);
				intent.putExtra("project", project);
				startActivity(intent);
				return true;
	        case R.id.activity_project_details_edit :
	        	intent = new Intent(getApplicationContext(), 
						EditProjectActivity.class);
				intent.putExtra("project", project);
				startActivity(intent);
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void prepareImageView(String url){
		Bitmap myBitmap = BitmapFactory.decodeFile(url);
		
		imageView.setImageBitmap(myBitmap);
	}

	private class ImageLoader extends AsyncTask<Void, Void, String>
	{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ProjectDetailsActivity.this);
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(Void... v)
		{
			MySingleton.loadImage(ProjectDetailsActivity.this, project);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			prepareImageView(FileTools.getAbsolutePathLocalFileFromURL(ProjectDetailsActivity.this, project.getPictureURL()));
			pDialog.dismiss();
		}

	}
}
