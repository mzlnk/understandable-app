package pl.understandable.understandable_app.fragments.grammar.fill_gap;

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
import android.text.Html;
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
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarFillGapData;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_GRAMMAR_SET_PREVIEW;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class GrammarFillGapFragment extends Fragment {

    private final int NUMB_WORDS = GrammarFillGapData.getFillGapData().getEntities().size();

    private GrammarFillGapData fillGapData;

    private RelativeLayout mainLayout;
    private Button check, showAnswer, finish;
    private EditText answerField;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    public GrammarFillGapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillGapData = GrammarFillGapData.getFillGapData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_grammar_fill_gap, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_fill_gap);
        answerField = (EditText) rootView.findViewById(R.id.f_grammar_fill_gap_answer_field);
        pager = (ViewPager) rootView.findViewById(R.id.f_grammar_fill_gap_view_pager);
        check = (Button) rootView.findViewById(R.id.f_grammar_fill_gap_check);
        showAnswer = (Button) rootView.findViewById(R.id.f_grammar_fill_gap_show_answer);
        finish = (Button) rootView.findViewById(R.id.f_grammar_fill_gap_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAnswerField();
        prepareAdapter();
    }

    private void prepareAdapter() {
        pagerAdapter = new GrammarFillGapFragment.ScreenSlidePagerAdapter(this.getFragmentManager());
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
                fillGapData.setCurrentWord(fillGapData.getEntities().get(position));
                fillGapData.setCurrentWordPosition(position);
                fillGapData.addCurrentWordToSeen();
                fillGapData.addToIncorrectAnswers();
                GrammarFillGapExampleFragment.sentences.get(position).setText(fillGapData.currentWord.getSentence());
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
                String correct = fillGapData.currentWord.getGap();
                if(answer.equalsIgnoreCase(correct)) {
                    Toast.makeText(getContext(), "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    fillGapData.addToCorrectAnswers();
                } else {
                    Toast.makeText(getContext(), "Niepoprawna odpowiedź", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView answer = GrammarFillGapExampleFragment.sentences.get(fillGapData.currentWordPosition);
                if(answer.getText().toString().contains("_")) {
                    String gap = fillGapData.currentWord.getGap();
                    String text = answer.getText().toString().replace("_", "<font color='blue'>" + gap + "</font>");
                    answer.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
                } else {
                    answer.setText(fillGapData.currentWord.getSentence());
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrammarFillGapResultFragment grammarFillGapResultFragment = new GrammarFillGapResultFragment();
                FragmentManager manager = getFragmentManager();
                String id = fillGapData.getParams().id;
                String name = fillGapData.getParams().name;
                manager.beginTransaction().replace(R.id.layout_for_fragments, grammarFillGapResultFragment, redirectTo(F_GRAMMAR_SET_PREVIEW, id, name)).commit();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GrammarFillGapExampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }
    }

}
