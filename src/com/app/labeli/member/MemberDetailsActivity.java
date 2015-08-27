package com.app.labeli.member;

import net.tools.MySingleton;

import com.app.labeli.R;
import com.tools.FileTools;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * > @MemberDetailsActivity
 *
 * Activity to show informations on a member
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class MemberDetailsActivity extends FragmentActivity {

	private Member item;
	private ImageView imageView;
	private ProgressDialog pDialog;
	private TextView textViewName, textViewStatus, textViewGroup, textViewBirthday, textViewBiography;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_details);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Détail");

		item = getIntent().getExtras().getParcelable("member");

		imageView = (ImageView) findViewById(R.id.activity_member_details_image_view_portrait);
		textViewName = (TextView) findViewById(R.id.activity_member_details_text_view_name);
		textViewStatus = (TextView) findViewById(R.id.activity_member_details_text_view_status);
		textViewGroup = (TextView) findViewById(R.id.activity_member_details_text_view_group);
		textViewBirthday = (TextView) findViewById(R.id.activity_member_details_text_view_birthday);
		textViewBiography = (TextView) findViewById(R.id.activity_member_details_text_view_biography);

		textViewName.setText(item.getFirstName() + " " + item.getLastName());
		textViewStatus.setText(item.getRole());
		if (!item.getDescription().equals(""))
			textViewGroup.setText("Groupe : " + item.getUniversityGroup());
		
		if (item.getBirthday() != null)
			textViewBirthday.setText("Anniversaire : " + item.getBirthdayAsString());
		
		if (item.getDescription().equals(""))
			textViewBiography.setText("Pas de biographie");
		else
			textViewBiography.setText(item.getDescription());
		
		if (!item.getPictureURL().equals(""))
			new LoadImage().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.member_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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

	private class LoadImage extends AsyncTask<Void, Void, String>
	{

		public LoadImage(){
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MemberDetailsActivity.this);
			pDialog.setMessage("Chargement des images");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(Void... v)
		{
			MySingleton.loadImage(MemberDetailsActivity.this, item);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			prepareImageView(FileTools.getAbsolutePathLocalFileFromURL(MemberDetailsActivity.this, item.getPictureURL()));
			pDialog.dismiss();
		}

	}

}
