package com.app.labeli;

import labeli.Labeli;

import com.app.callback.APICallback;
import com.app.labeli.event.FragmentEvent;
import com.tools.APIDataParser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FragmentPresentation.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link FragmentConnection#newInstance} factory method to create an instance
 * of this fragment.
 *
 */
public class FragmentConnection extends Fragment {

	private EditText editTextLogin, editTextPassword;
	private Button button;
	private ProgressDialog pDialog;

	public FragmentConnection() {
		// Required empty public constructor
	}

	public void connectToAPI(View v){
		if (editTextLogin.length() == 0)
			Toast.makeText(getActivity(), "Veuillez rentrer un identifiant", Toast.LENGTH_SHORT).show();
		else if (editTextPassword.length() == 0)
			Toast.makeText(getActivity(), "Veuillez rentrer un mot de passe", Toast.LENGTH_SHORT).show();
		else {
			new InitConnection(editTextLogin.getText().toString(), editTextPassword.getText().toString())
			.execute(MainActivity.api);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		getActivity().getActionBar().setTitle("Connexion");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View v = inflater.inflate(R.layout.fragment_connection, container,
				false);
		
		editTextLogin = (EditText) v.findViewById(R.id.fragment_connection_edit_text_login);
		editTextPassword = (EditText) v.findViewById(R.id.fragment_connection_edit_text_password);
		button = (Button) v.findViewById(R.id.fragment_connection_button_connection);

//		button.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View arg0) {
//				connectToAPI(arg0);
//			}
//
//		});
		
		return v;
	}
	
	private class InitConnection extends AsyncTask<Labeli, Void, String>
	{
		APICallback a;
		String username, password;
		
		public InitConnection(String username, String password){
			a = new APICallback();
			this.username = username;
			this.password = password;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentConnection.this.getActivity());
			pDialog.setMessage("Connexion");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Labeli... api)
		{
			api[0].async.login(username, password, a);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			Log.i("Test", " " + a.getArray());
			pDialog.dismiss();
			new GetUser().execute(MainActivity.api);
		}
	}
	
	private class GetUser extends AsyncTask<Labeli, Void, String>
	{
		APICallback a;
		
		public GetUser(){
			a = new APICallback();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FragmentConnection.this.getActivity());
			pDialog.setMessage("Connexion");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(Labeli... api)
		{
			api[0].async.getCurrentUser(a);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			Log.i("Test", " " + a.getArray());
			pDialog.dismiss();
		}
	}

}
