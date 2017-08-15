package pl.understandable.understandable_app.fragments.custom_words.repetition;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_app.utils.ColorUtil;

public class CustomWordsRepetitionExampleFragment extends Fragment {

    public static final String POSITION_PARAM = "custom.words.repetiton.example.positionParam";

    private CustomWordsRepetitionData repetitionData;

    private int word1Color, word2Color, hiddenWordColor;

    private LinearLayout wordLayout;
    private TextView word0, word1;

    public int position;

    public CustomWordsRepetitionExampleFragment() {
        // Required empty public constructor
    }

    public static CustomWordsRepetitionExampleFragment newInstance(int position) {
        CustomWordsRepetitionExampleFragment fragment = new CustomWordsRepetitionExampleFragment();
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
        repetitionData = CustomWordsRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_repetition_example, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        word0 = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_example_word_1);
        word1 = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_example_word_2);
        wordLayout = (LinearLayout) rootView.findViewById(R.id.f_custom_words_repetition_example_clickable_layout);
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
        word0.setText(repetitionData.getEntities().get(position).getPolish());
        word1.setText(repetitionData.getEntities().get(position).getEnglish());
    }

    private void setWordsEnglishToPolish() {
        word0.setText(repetitionData.getEntities().get(position).getEnglish());
        word1.setText(repetitionData.getEntities().get(position).getPolish());
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
