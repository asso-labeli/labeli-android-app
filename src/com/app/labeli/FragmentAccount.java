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
 * {@link FragmentAccount#newInstance} factory method to create an instance
 * of this fragment.
 *
 */
public class FragmentAccount extends Fragment {

	public FragmentAccount() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		getActivity().getActionBar().setTitle("Mon Profil");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View v = inflater.inflate(R.layout.fragment_account, container,
				false);
		
		return v;
	}
	
}
