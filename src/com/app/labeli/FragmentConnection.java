package com.app.labeli;

import com.app.labeli.event.FragmentEvent;
import com.app.labeli.member.Member;

import net.tools.*;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
			.execute();
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

		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				connectToAPI(arg0);
			}
		});
		
		return v;
	}
	
	private class InitConnection extends AsyncTask<Void, Void, String>
	{
		String username, password;
		boolean success;
		
		public InitConnection(String username, String password){
			this.username = username;
			this.password = password;
			this.success = false;
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

		protected String doInBackground(Void... params)
		{
			success = APIConnection.login(username, password);
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (!success)
				Toast.makeText(getActivity(), "Mauvais identifiant / mot de passe", Toast.LENGTH_SHORT).show();
			else {
				Member loggedUser = APIConnection.getLoggedUser();
				Toast.makeText(getActivity(), "Bonjour " + loggedUser.getFirstName() + " " + loggedUser.getLastName(), Toast.LENGTH_SHORT).show();
				((MainActivity)getActivity()).loadLeftMenu();
				
				Fragment fragment = new FragmentAccount();
				Bundle args = new Bundle();
				fragment.setArguments(args);

				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
				transaction.addToBackStack(fragment.getClass().getName());

				transaction.commit();
			}
		}
	}
}
