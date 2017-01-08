package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import net.heliantum.ziedic.R;

public class WordsRepetitionExampleFragment extends Fragment {

    private static final String WORD_0_PARAM = "word0Param";
    private static final String WORD_1_PARAM = "word1Param";

    private String word0;
    private String word1;

    private TextView word0View, word1View;
    private TableLayout layout;

    public WordsRepetitionExampleFragment() {
        // Required empty public constructor
    }

    public static WordsRepetitionExampleFragment newInstance(String word1, String word2) {
        WordsRepetitionExampleFragment fragment = new WordsRepetitionExampleFragment();
        Bundle args = new Bundle();
        args.putString(WORD_0_PARAM, word1);
        args.putString(WORD_1_PARAM, word2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word0 = getArguments().getString(WORD_0_PARAM);
            word1 = getArguments().getString(WORD_1_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_repetition_example, container, false);

        word0View = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_0);
        word1View = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_1);
        layout = (TableLayout) rootView.findViewById(R.id.f_words_repetition_example_clickable_layout);

        word0View.setText(word0);
        word1View.setText(word1);

        word0View.setTextColor(Color.BLACK);
        word1View.setTextColor(Color.WHITE);

        setTypeface();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word1View.getCurrentTextColor() == Color.rgb(75, 71, 71)) word1View.setTextColor(Color.WHITE);
                else word1View.setTextColor(Color.rgb(75, 71, 71));
            }
        });

        return rootView;
    }

    private void setTypeface() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        word0View.setTypeface(typeFace);
        word1View.setTypeface(typeFace);
    }

}
