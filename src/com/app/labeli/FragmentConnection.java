package com.app.labeli;

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

	public FragmentConnection() {
		// Required empty public constructor
	}

	public void connectToAPI(View v){
		if (editTextLogin.length() == 0)
			Toast.makeText(getActivity(), "Veuillez rentrer un identifiant", Toast.LENGTH_SHORT).show();
		else if (editTextPassword.length() == 0)
			Toast.makeText(getActivity(), "Veuillez rentrer un mot de passe", Toast.LENGTH_SHORT).show();
		else {
			
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

}
