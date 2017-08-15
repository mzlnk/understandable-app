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
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsSpellingFragment extends Fragment {

    private final int NUMB_WORDS = CustomWordsSpellingData.getSpellingData().getEntities().size();

    private CustomWordsSpellingData spellingData;

    private RelativeLayout mainLayout;
    private Button check, showAnswer, finish;
    private EditText answerField;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

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
        check = (Button) rootView.findViewById(R.id.f_custom_words_spelling_check);
        showAnswer = (Button) rootView.findViewById(R.id.f_custom_words_spelling_show_answer);
        finish = (Button) rootView.findViewById(R.id.f_custom_words_spelling_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAnswerField();
        prepareAdapter();
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
        check.setTypeface(typeFace);
        showAnswer.setTypeface(typeFace);
        finish.setTypeface(typeFace);
        answerField.setTypeface(typeFace);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            check.setBackgroundResource(R.drawable.field_rounded_pink);
            showAnswer.setBackgroundResource(R.drawable.field_rounded_pink);
            finish.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            check.setBackgroundResource(R.drawable.field_rounded_gray);
            showAnswer.setBackgroundResource(R.drawable.field_rounded_gray);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = answerField.getText().toString();
                String correct = (String) CustomWordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition).getText();
                if(answer.equalsIgnoreCase(correct)) {
                    Toast.makeText(getContext(), "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    spellingData.addToCorrectAnswers();
                } else {
                    Toast.makeText(getContext(), "Niepoprawna odpowiedź", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView answer = CustomWordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition);
                if(answer.getCurrentTextColor() == CustomWordsSpellingExampleFragment.hiddenWordColor) {
                    answer.setTextColor(CustomWordsSpellingExampleFragment.word2Color);
                } else {
                    answer.setTextColor(CustomWordsSpellingExampleFragment.hiddenWordColor);
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomWordsSpellingResultFragment wordsRepetitionResultFragment = new CustomWordsSpellingResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionResultFragment, FragmentUtil.F_CUSTOM_WORDS_SPELLING_RESULT).commit();
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
