package com.app.labeli;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * > @FragmentPresentation
 *
 * Default fragment to show informations on 
 * 	association.
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class FragmentPresentation extends Fragment {

	// TODO: Rename and change types and number of parameters
	public static FragmentPresentation newInstance(String param1, String param2) {
		FragmentPresentation fragment = new FragmentPresentation();

		return fragment;
	}

	public FragmentPresentation() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getActivity().getActionBar().setTitle("Label[i]");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_presentation, container,
				false);
	}

}
