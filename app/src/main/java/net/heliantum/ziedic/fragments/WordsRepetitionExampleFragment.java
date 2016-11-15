package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.heliantum.ziedic.R;

public class WordsRepetitionExampleFragment extends Fragment {

    private static final String WORD_1_PARAM = "word1Param";
    private static final String WORD_2_PARAM = "word2Param";

    private String word1;
    private String word2;

    private TextView word1View, word2View;
    private RelativeLayout layout;

    public WordsRepetitionExampleFragment() {
        // Required empty public constructor
    }

    public static WordsRepetitionExampleFragment newInstance(String word1, String word2) {
        WordsRepetitionExampleFragment fragment = new WordsRepetitionExampleFragment();
        Bundle args = new Bundle();
        args.putString(WORD_1_PARAM, word1);
        args.putString(WORD_2_PARAM, word2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word1 = getArguments().getString(WORD_1_PARAM);
            word2 = getArguments().getString(WORD_2_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_repetition_example, container, false);

        word1View = (TextView) rootView.findViewById(R.id.word_repetition_example_1);
        word2View = (TextView) rootView.findViewById(R.id.word_repetition_example_2);
        layout = (RelativeLayout) rootView.findViewById(R.id.word_repetition_example);

        word1View.setText(word1);
        word2View.setText(word2);

        word1View.setTextColor(Color.BLACK);
        word2View.setTextColor(Color.WHITE);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word2View.getCurrentTextColor() == Color.rgb(75, 71, 71)) word2View.setTextColor(Color.WHITE);
                else word2View.setTextColor(Color.rgb(75, 71, 71));
            }
        });

        return rootView;
    }

}
