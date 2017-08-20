package pl.understandable.understandable_dev_app.fragments.irregular_verbs.repetition;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import pl.understandable.understandable_dev_app.database.entity.enums.IrregularVerbEnum;
import pl.understandable.understandable_dev_app.utils.ColorUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
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
        prepareButtons();
        setWords();
    }

    private void setFonts() {
        Typeface typeFace = Font.TYPEFACE_MONTSERRAT;
        wordPolish.setTypeface(typeFace);
        wordEnglish1.setTypeface(typeFace);
        wordEnglish2.setTypeface(typeFace);
        wordEnglish3.setTypeface(typeFace);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            wordPolish.setBackgroundResource(R.drawable.field_rounded_light_gray);
            wordEnglish1.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
            wordEnglish2.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
            wordEnglish3.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
        } else {
            wordPolish.setBackgroundResource(R.drawable.field_rounded_dark_gray);
            wordEnglish1.setBackgroundResource(R.drawable.field_rounded_gray);
            wordEnglish2.setBackgroundResource(R.drawable.field_rounded_gray);
            wordEnglish3.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void setWords() {
        wordPolish.setText(repetitionData.getEntities().get(position).getPolish());
        wordEnglish1.setText(repetitionData.getEntities().get(position).getEnglish(IrregularVerbEnum.INFINITVE));
        wordEnglish2.setText(repetitionData.getEntities().get(position).getEnglish(IrregularVerbEnum.SIMPLE_PAST));
        wordEnglish3.setText(repetitionData.getEntities().get(position).getEnglish(IrregularVerbEnum.PAST_PARTICIPLE));
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
