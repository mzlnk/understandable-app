package pl.understandable.understandable_app.fragments.custom_words.repetition;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.understandable.understandable_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomWordsRepetitionResultWordsToRepeatFragment extends Fragment {


    public CustomWordsRepetitionResultWordsToRepeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_custom_words_repetition_result_words_to_repeat, container, false);
    }

}
