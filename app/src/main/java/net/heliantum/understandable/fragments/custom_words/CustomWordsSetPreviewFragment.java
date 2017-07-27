package net.heliantum.understandable.fragments.custom_words;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.heliantum.understandable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomWordsSetPreviewFragment extends Fragment {


    public CustomWordsSetPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_custom_words_set_preview, container, false);
    }

}
