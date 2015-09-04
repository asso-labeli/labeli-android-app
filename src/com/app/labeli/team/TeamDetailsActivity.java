package com.app.labeli.team;

import net.tools.APIConnection;

import com.app.labeli.R;
import com.tools.DeviceTools;
import com.tools.FileTools;
import com.tools.HTMLTools;

import android.app.ProgressDialog;
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
 * > @TeamDetailsActivity
 *
 * Activity to show informations on a team
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class TeamDetailsActivity extends FragmentActivity{

	private ImageView imageView;
	private TextView textViewName, textViewAuthor, textViewDescription;
	private ProgressDialog pDialog;
	private Team team;
	
	Animation animFadeIn, animFadeOut;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_team_details);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Détail");

		team = this.getIntent().getExtras().getParcelable("team");

		imageView = (ImageView) findViewById(R.id.activity_team_details_picture);
		textViewName = (TextView) findViewById(R.id.activity_team_details_name);
		textViewAuthor = (TextView) findViewById(R.id.activity_team_details_author);
		textViewDescription = (TextView) findViewById(R.id.activity_team_details_description);

		textViewName.setText(team.getName());
		textViewAuthor.setText("par " + team.getAuthor().getFirstName() + " " + team.getAuthor().getLastName());
		textViewDescription.setText(HTMLTools.HTMLToText(team.getDescription()));
		
		if (!team.getPictureURL().equals(""))
			new ImageLoader().execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.team_details, menu);
	    return super.onCreateOptionsMenu(menu);
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
				pDialog = new ProgressDialog(TeamDetailsActivity.this);
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Boolean doInBackground(Void... v) {
			return APIConnection.loadImage(TeamDetailsActivity.this, team, 
					DeviceTools.getWidthScreen(TeamDetailsActivity.this));
		}

		@Override
		protected void onPostExecute(Boolean b) {
			prepareImageView(FileTools.getAbsolutePathLocalFileFromURL(TeamDetailsActivity.this, team.getPictureURL()));
			// Need to be checked because pDialog is use in ProjectLoader AND
			// 	ImageLoader
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}
}
