package pl.understandable.understandable_app.fragments.custom_words.quiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.understandable.understandable_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomWordsQuizResultIncorrectWordsSummary extends Fragment {


    public CustomWordsQuizResultIncorrectWordsSummary() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_custom_words_quiz_result_incorrect_words_summary, container, false);
    }

}
