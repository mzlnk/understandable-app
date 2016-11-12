package net.heliantum.ziedic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.heliantum.ziedic.R;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class WordsChoiceFragment extends Fragment {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    public WordsChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_choice, container, false);
        return rootView;
    }

}
