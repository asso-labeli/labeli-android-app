package com.app.labeli;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * > @FragmentAccount
 *
 * Fragment where user can check and edit his settings 
 * TODO Account module
 *
 * @author Florian "Aamu Lumi" Kauder for the project @Label[i]
 */
public class FragmentAccount extends Fragment {

	public FragmentAccount() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivity().getActionBar().setTitle("Mon Profil");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View v = inflater.inflate(R.layout.fragment_account, container, false);

		return v;
	}

}
