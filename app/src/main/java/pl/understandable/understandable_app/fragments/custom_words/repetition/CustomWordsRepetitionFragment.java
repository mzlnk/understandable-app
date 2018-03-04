package pl.understandable.understandable_app.fragments.custom_words.repetition;

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
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsRepetitionData;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsHaveLearntButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsPronunciationButton;
import pl.understandable.understandable_app.utils.buttons.custom_words.CustomWordsRepetitionRepeatButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsHaveLearntButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsPronunciationButton;
import pl.understandable.understandable_app.utils.buttons.words.WordsRepetitionRepeatButton;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_CUSTOM_WORDS_SET_PREVIEW;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsRepetitionFragment extends Fragment {

    private final int NUMB_WORDS = CustomWordsRepetitionData.getRepetitionData().getEntities().size();

    private CustomWordsRepetitionData repetitionData;

    private RelativeLayout mainLayout;
    private Button finish;
    private CustomWordsRepetitionRepeatButton repeat;
    private CustomWordsPronunciationButton pronunciation;
    private CustomWordsHaveLearntButton haveLearnt;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private TableLayout optionsTable;

    public CustomWordsRepetitionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repetitionData = CustomWordsRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_custom_words_repetition, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        pronunciation.shutdownTts();
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_repetition);
        pager = (ViewPager) rootView.findViewById(R.id.f_custom_words_repetition_view_pager);
        optionsTable = (TableLayout) rootView.findViewById(R.id.f_custom_words_repetition_options_table);
        finish = (Button) rootView.findViewById(R.id.f_custom_words_repetition_finish);
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
        pagerAdapter = new CustomWordsRepetitionFragment.ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeFace = Font.TYPEFACE_MONTSERRAT;
        finish.setTypeface(typeFace);
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
        repeat = new CustomWordsRepetitionRepeatButton(getContext());
        pronunciation = new CustomWordsPronunciationButton(getContext(), CustomWordsRepetitionData.getRepetitionData());
        haveLearnt = new CustomWordsHaveLearntButton(getContext(), CustomWordsRepetitionData.getRepetitionData());
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
                CustomWordsRepetitionResultFragment wordsRepetitionResultFragment = new CustomWordsRepetitionResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionResultFragment, redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, repetitionData.getParams().id)).commit();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CustomWordsRepetitionExampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }
    }

}
