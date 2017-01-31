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
import net.heliantum.ziedic.data.RepetitionData;

import java.util.Random;

public class WordsRepetitionExampleFragment extends Fragment {

    private View rootView;

    private TextView word0View, word1View;
    private TableLayout layout;

    public int position;

    public static final String POSITION_PARAM = "position";

    public WordsRepetitionExampleFragment() {
        // Required empty public constructor
    }

    public static WordsRepetitionExampleFragment newInstance(int position) {
        WordsRepetitionExampleFragment fragment = new WordsRepetitionExampleFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_PARAM, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            position = getArguments().getInt(POSITION_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_words_repetition_example, container, false);

        word0View = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_0);
        word1View = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_1);
        layout = (TableLayout) rootView.findViewById(R.id.f_words_repetition_example_clickable_layout);

        setWords();
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

    private void setWords() {
        switch(RepetitionData.way) {
            case POLISH_TO_ENGLISH:
                word0View.setText(RepetitionData.allChosenWords.get(position).getPolishWord());
                word1View.setText(RepetitionData.allChosenWords.get(position).getEnglishWord());
                break;
            case ENGLISH_TO_POLISH:
                word0View.setText(RepetitionData.allChosenWords.get(position).getPolishWord());
                word1View.setText(RepetitionData.allChosenWords.get(position).getEnglishWord());
                break;
            case RANDOM:
                if(new Random().nextBoolean()) {
                    word0View.setText(RepetitionData.allChosenWords.get(position).getPolishWord());
                    word1View.setText(RepetitionData.allChosenWords.get(position).getEnglishWord());
                } else {
                    word0View.setText(RepetitionData.allChosenWords.get(position).getPolishWord());
                    word1View.setText(RepetitionData.allChosenWords.get(position).getEnglishWord());
                }
        }
        word0View.setTextColor(Color.BLACK);
        word1View.setTextColor(Color.WHITE);
    }

}
