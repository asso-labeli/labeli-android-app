package com.app.labeli.member;

import java.io.File;
import java.util.concurrent.ExecutionException;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.app.labeli.R;
import com.app.labeli.R.id;
import com.app.labeli.R.layout;
import com.app.labeli.R.menu;
import com.tools.FileTools;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MemberDetailsActivity extends FragmentActivity {

	private ItemMember item;
	private ImageView imageView;
	private ProgressDialog pDialog;
	private TextView textViewName, textViewStatus, textViewGroup, textViewBirthday, textViewBiography;

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
		
		if (item.getBirthday() != 0)
			textViewBirthday.setText("Anniversaire : " + item.getBirthdayAsString());
		
		if (item.getDescription().equals(""))
			textViewBiography.setText("Pas de biographie");
		else
			textViewBiography.setText(item.getDescription());
		
		if (!item.getPictureURL().equals(""))
			new ImageLoader(item.getPictureURL()).execute();
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

	private class ImageLoader extends AsyncTask<Void, Void, String>
	{
		String courtUrl;
		RequestQueue q;

		public ImageLoader(String courtUrl){
			this.courtUrl = courtUrl;
			this.q = Volley.newRequestQueue(MemberDetailsActivity.this);
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

		protected String doInBackground(Void... v)
		{
			if (courtUrl != null && !courtUrl.equals("")){
				Bitmap bm = null;
				
				final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(MemberDetailsActivity.this, courtUrl));
				String wallpaperURLStr = "http://labeli.org/" + courtUrl;
				String localFile = FileTools.getLocalFileFromURL(courtUrl);

				if (!dataFile.exists()){
					Log.i("Net", "Chargement de " + wallpaperURLStr);

					RequestFuture<Bitmap> f = RequestFuture.newFuture();
					ImageRequest ir = new ImageRequest(wallpaperURLStr.replace(" ", "%20"), f, 0, 0, null, null);
					ir.setRetryPolicy(new DefaultRetryPolicy(
							5000, 
							DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
							DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
					q.add(ir);

					try {
						bm = f.get();
						FileTools.writeBitmapToFile(MemberDetailsActivity.this, localFile, bm);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String file_url) {
			prepareImageView(FileTools.getAbsolutePathLocalFileFromURL(MemberDetailsActivity.this, courtUrl));
			pDialog.dismiss();
		}

	}

}
