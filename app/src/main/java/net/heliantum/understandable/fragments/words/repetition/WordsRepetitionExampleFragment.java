package net.heliantum.understandable.fragments.words.repetition;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.words_data.WordsRepetitionData;
import net.heliantum.understandable.utils.ColorUtil;

import java.util.Random;

public class WordsRepetitionExampleFragment extends Fragment {

    public static final String POSITION_PARAM = "words.repetiton.example.positionParam";

    private WordsRepetitionData repetitionData;

    private int word1Color, word2Color, hiddenWordColor;

    private LinearLayout wordLayout;
    private TextView word0, word1;

    public int position;

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
        repetitionData = WordsRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_repetition_example, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        word0 = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_0);
        word1 = (TextView) rootView.findViewById(R.id.f_words_repetition_example_word_1);
        wordLayout = (LinearLayout) rootView.findViewById(R.id.f_words_repetition_example_clickable_layout);
    }

    private void prepareLayout() {
        setFonts();
        setWords();
    }

    private void setFonts() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        word0.setTypeface(typeFace);
        word1.setTypeface(typeFace);
    }

    private void setWords() {
        switch(repetitionData.getParams().way) {
            case POLISH_TO_ENGLISH:
                setWordsPolishToEnglish();
                break;
            case ENGLISH_TO_POLISH:
                setWordsEnglishToPolish();
                break;
            case RANDOM:
                if(new Random().nextBoolean()) {
                    setWordsPolishToEnglish();
                } else {
                    setWordsEnglishToPolish();
                }
        }
        word0.setTextColor(word1Color);
        word1.setTextColor(hiddenWordColor);
    }

    private void setWordsPolishToEnglish() {
        word0.setText(repetitionData.getWords().get(position).getPolishWord());
        word1.setText(repetitionData.getWords().get(position).getEnglishWord());
    }

    private void setWordsEnglishToPolish() {
        word0.setText(repetitionData.getWords().get(position).getEnglishWord());
        word1.setText(repetitionData.getWords().get(position).getPolishWord());
    }

    private void addListeners() {
        wordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word1.getCurrentTextColor() == hiddenWordColor) word1.setTextColor(word2Color);
                else word1.setTextColor(hiddenWordColor);
            }
        });
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        word1Color = colorUtil.getColor(R.attr.text_1_color);
        word2Color = colorUtil.getColor(R.attr.text_2_color);
        hiddenWordColor = colorUtil.getColor(R.attr.background_color);
    }

}
