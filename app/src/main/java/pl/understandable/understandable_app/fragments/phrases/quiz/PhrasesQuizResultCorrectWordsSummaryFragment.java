package pl.understandable.understandable_app.fragments.phrases.quiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.understandable.understandable_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesQuizResultCorrectWordsSummaryFragment extends Fragment {


    public PhrasesQuizResultCorrectWordsSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_phrases_quiz_result_correct_words_summary, container, false);
    }

}
