package pl.understandable.understandable_app.fragments.words.spelling;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class WordsSpellingExampleFragment extends Fragment {

    public static final String POSITION_PARAM = "words.spelling.example.positionParam";
    public static Map<Integer, TextView> answers = new HashMap<>();
    public static int word1Color, word2Color, hiddenWordColor;

    private WordsSpellingData spellingData;

    private TextView word0, word1;

    public int position;

    public WordsSpellingExampleFragment() {
        // Required empty public constructor
    }

    public static WordsSpellingExampleFragment newInstance(int position) {
        WordsSpellingExampleFragment fragment = new WordsSpellingExampleFragment();
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
        spellingData = WordsSpellingData.getSpellingData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_spelling_example, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        answers.remove(position);
    }

    private void loadViewsFromXml(View rootView) {
        word0 = (TextView) rootView.findViewById(R.id.f_words_spelling_example_word_1);
        word1 = (TextView) rootView.findViewById(R.id.f_words_spelling_example_word_2);
        answers.put(position, word1);
    }

    private void prepareLayout() {
        setFonts();
        setWords();
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        word0.setTypeface(typeface);
        word1.setTypeface(typeface);
    }

    private void setWords() {
        switch(spellingData.getParams().way) {
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
        word0.setText(spellingData.getEntities().get(position).getPolish());
        word1.setText(spellingData.getEntities().get(position).getEnglish());
    }

    private void setWordsEnglishToPolish() {
        word0.setText(spellingData.getEntities().get(position).getEnglish());
        word1.setText(spellingData.getEntities().get(position).getPolish());
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        word1Color = colorUtil.getColor(R.attr.text_1_color);
        word2Color = colorUtil.getColor(R.attr.text_2_color);
        hiddenWordColor = colorUtil.getColor(R.attr.background_color);
    }

}
