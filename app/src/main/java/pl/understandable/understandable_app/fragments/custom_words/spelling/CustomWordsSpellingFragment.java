package pl.understandable.understandable_app.fragments.custom_words.spelling;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsHaveLearntButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsSpellingCheckAnswerButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsSpellingShowAnswerButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsHaveLearntButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsSpellingCheckAnswerButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsSpellingShowAnswerButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_CUSTOM_WORDS_SET_PREVIEW;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsSpellingFragment extends Fragment {

    private final int NUMB_WORDS = CustomWordsSpellingData.getSpellingData().getEntities().size();

    private CustomWordsSpellingData spellingData;

    private RelativeLayout mainLayout;
    private Button finish;
    private CustomWordsSpellingCheckAnswerButton checkAnswer;
    private CustomWordsSpellingShowAnswerButton showAnswer;
    private CustomWordsHaveLearntButton haveLearnt;
    private EditText answerField;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private TableLayout optionsTable;

    public CustomWordsSpellingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spellingData = CustomWordsSpellingData.getSpellingData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_spelling, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_spelling);
        answerField = (EditText) rootView.findViewById(R.id.f_custom_words_spelling_answer_field);
        pager = (ViewPager) rootView.findViewById(R.id.f_custom_words_spelling_view_pager);
        optionsTable = (TableLayout) rootView.findViewById(R.id.f_custom_words_spelling_options_table);
        finish = (Button) rootView.findViewById(R.id.f_custom_words_spelling_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAnswerField();
        prepareAdapter();
        initOptionButtons();
        addOptionButtonsToTable();
    }

    private void prepareAdapter() {
        pagerAdapter = new CustomWordsSpellingFragment.ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeFace = Font.TYPEFACE_MONTSERRAT;
        finish.setTypeface(typeFace);
        answerField.setTypeface(typeFace);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            finish.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            finish.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void prepareAnswerField() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isNightTheme()) {
            Drawable bottomLine = answerField.getBackground();
            bottomLine.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            if(Build.VERSION.SDK_INT > 16) {
                answerField.setBackground(bottomLine);
            } else {
                answerField.setBackgroundDrawable(bottomLine);
            }
        }
    }

    private void initOptionButtons() {
        checkAnswer = new CustomWordsSpellingCheckAnswerButton(getContext(), answerField);
        showAnswer = new CustomWordsSpellingShowAnswerButton(getContext());
        haveLearnt = new CustomWordsHaveLearntButton(getContext(), CustomWordsSpellingData.getSpellingData());
    }

    private void addOptionButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        currentImageRow.addView(checkAnswer.getImage());
        currentImageRow.addView(showAnswer.getImage());
        currentImageRow.addView(haveLearnt.getImage());

        currentTextRow.addView(checkAnswer.getText());
        currentTextRow.addView(showAnswer.getText());
        currentTextRow.addView(haveLearnt.getText());

        optionsTable.addView(currentImageRow);
        optionsTable.addView(currentTextRow);
    }

    private void addListeners() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                spellingData.setCurrentWord(spellingData.getEntities().get(position));
                spellingData.setCurrentWordPosition(position);
                spellingData.addCurrentWordToSeen();
                spellingData.addToIncorrectAnswers();
                answerField.setText("");
                haveLearnt.updateChoiceState();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomWordsSpellingResultFragment wordsRepetitionResultFragment = new CustomWordsSpellingResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionResultFragment, redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, spellingData.getParams().id)).commit();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CustomWordsSpellingExampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }
    }

}
