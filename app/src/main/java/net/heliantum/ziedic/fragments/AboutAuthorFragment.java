package net.heliantum.ziedic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.heliantum.ziedic.R;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class AboutAuthorFragment extends Fragment {


    public AboutAuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_author, container, false);
    }

}
