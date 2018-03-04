package pl.understandable.understandable_app.fragments.words.repetition;

import android.graphics.Typeface;
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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import pl.understandable.understandable_app.R;;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsRepetitionData;
import pl.understandable.understandable_app.dialogs.help.HelpManager;
import pl.understandable.understandable_app.dialogs.help.RepetitionHelpDialog;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.words.WordsHaveLearntButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsPronunciationButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsRepetitionRepeatButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class WordsRepetitionFragment extends Fragment {

    private final int NUMB_WORDS = WordsRepetitionData.getRepetitionData().getEntities().size();

    private WordsRepetitionData repetitionData;

    private RelativeLayout mainLayout;
    private TableLayout optionsTable;
    private WordsRepetitionRepeatButton repeat;
    private WordsPronunciationButton pronunciation;
    private WordsHaveLearntButton haveLearnt;
    private Button finish;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    public WordsRepetitionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repetitionData = WordsRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_repetition, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        HelpManager.showHelpDialog(getContext(), new RepetitionHelpDialog(getContext()));

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        pronunciation.shutdownTts();
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_repetition);
        pager = (ViewPager) rootView.findViewById(R.id.f_words_repetition_view_pager);
        optionsTable = (TableLayout) rootView.findViewById(R.id.f_words_repetition_options_table);
        finish = (Button) rootView.findViewById(R.id.f_words_repetition_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAdapter();
        initOptionButtons();
        addOptionButtonsToTable();
    }

    private void prepareAdapter() {
        pagerAdapter = new ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        finish.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            finish.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            finish.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void initOptionButtons() {
        repeat = new WordsRepetitionRepeatButton(getContext());
        pronunciation = new WordsPronunciationButton(getContext(), WordsRepetitionData.getRepetitionData());
        haveLearnt = new WordsHaveLearntButton(getContext(), WordsRepetitionData.getRepetitionData());
    }

    private void addOptionButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        currentImageRow.addView(repeat.getImage());
        currentImageRow.addView(pronunciation.getImage());
        currentImageRow.addView(haveLearnt.getImage());

        currentTextRow.addView(repeat.getText());
        currentTextRow.addView(pronunciation.getText());
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
                repetitionData.setCurrentWord(repetitionData.getEntities().get(position));
                repetitionData.addCurrentWordToSeen();
                repeat.updateChoiceState();
                haveLearnt.updateChoiceState();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsRepetitionResultFragment wordsRepetitionResultFragment = new WordsRepetitionResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionResultFragment, redirectTo(F_START)).commit();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WordsRepetitionExampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }
    }

}
