package net.heliantum.understandable.fragments.irregular_verbs.repetition;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import net.heliantum.understandable.database.entity.enums.IrregularVerbEnum;
import net.heliantum.understandable.utils.ColorUtil;

/**
 * A simple {@link Fragment} subclass.
 */

public class IrregularVerbsRepetitionExampleFragment extends Fragment {

    public static final String POSITION_PARAM = "irregular.verbs.repetiton.example.positionParam";

    private IrregularVerbsRepetitionData repetitionData;

    private int wordPolishColor, wordEnglishColor, wordPolishHiddenColor, wordEnglishHiddenColor;

    private Button wordPolish, wordEnglish1, wordEnglish2, wordEnglish3;

    public int position;

    public IrregularVerbsRepetitionExampleFragment() {
        // Required empty public constructor
    }

    public static IrregularVerbsRepetitionExampleFragment newInstance(int position) {
        IrregularVerbsRepetitionExampleFragment fragment = new IrregularVerbsRepetitionExampleFragment();
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
        repetitionData = IrregularVerbsRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the wordLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_irregular_verbs_repetition_example, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        wordPolish = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_example_field_polish);
        wordEnglish1 = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_example_field_english_1);
        wordEnglish2 = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_example_field_english_2);
        wordEnglish3 = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_example_field_english_3);
    }

    private void prepareLayout() {
        setFonts();
        setWords();
    }

    private void setFonts() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        wordPolish.setTypeface(typeFace);
        wordEnglish1.setTypeface(typeFace);
        wordEnglish2.setTypeface(typeFace);
        wordEnglish3.setTypeface(typeFace);
    }

    private void setWords() {
        wordPolish.setText(repetitionData.currentWord.getPolishWord());
        wordEnglish1.setText(repetitionData.currentWord.getEnglishWord(IrregularVerbEnum.INFINITVE));
        wordEnglish2.setText(repetitionData.currentWord.getEnglishWord(IrregularVerbEnum.SIMPLE_PAST));
        wordEnglish3.setText(repetitionData.currentWord.getEnglishWord(IrregularVerbEnum.PAST_PARTICIPLE));
        wordPolish.setTextColor(wordPolishHiddenColor);
        wordEnglish1.setTextColor(wordEnglishHiddenColor);
        wordEnglish2.setTextColor(wordEnglishHiddenColor);
        wordEnglish3.setTextColor(wordEnglishHiddenColor);
    }


    private void addListeners() {
        wordPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wordPolish.getCurrentTextColor() == wordPolishHiddenColor) wordPolish.setTextColor(wordPolishColor);
                else wordPolish.setTextColor(wordPolishHiddenColor);
            }
        });

        wordEnglish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wordEnglish1.getCurrentTextColor() == wordEnglishHiddenColor) wordEnglish1.setTextColor(wordEnglishColor);
                else wordEnglish1.setTextColor(wordEnglishHiddenColor);
            }
        });

        wordEnglish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wordEnglish2.getCurrentTextColor() == wordEnglishHiddenColor) wordEnglish2.setTextColor(wordEnglishColor);
                else wordEnglish2.setTextColor(wordEnglishHiddenColor);
            }
        });

        wordEnglish3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wordEnglish3.getCurrentTextColor() == wordEnglishHiddenColor) wordEnglish3.setTextColor(wordEnglishColor);
                else wordEnglish3.setTextColor(wordEnglishHiddenColor);
            }
        });
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        wordPolishColor = colorUtil.getColor(R.attr.text_button_color);
        wordEnglishColor = colorUtil.getColor(R.attr.text_1_color);
        wordPolishHiddenColor = colorUtil.getColor(R.attr.irregular_verbs_repetition_example_field_1_color);
        wordEnglishHiddenColor = colorUtil.getColor(R.attr.irregular_verbs_repetition_example_field_2_color);
    }

}
