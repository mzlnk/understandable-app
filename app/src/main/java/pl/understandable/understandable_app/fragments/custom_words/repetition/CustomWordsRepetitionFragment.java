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
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_app.utils.ThemeUtil;
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
    private Button repeat, finish;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

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

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_repetition);
        pager = (ViewPager) rootView.findViewById(R.id.f_custom_words_repetition_view_pager);
        repeat = (Button) rootView.findViewById(R.id.f_custom_words_repetition_repeat);
        finish = (Button) rootView.findViewById(R.id.f_custom_words_repetition_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAdapter();
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
        repeat.setTypeface(typeFace);
        finish.setTypeface(typeFace);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            repeat.setBackgroundResource(R.drawable.field_rounded_pink);
            finish.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            repeat.setBackgroundResource(R.drawable.field_rounded_gray);
            finish.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repetitionData.existsInToRepeatWords(repetitionData.currentWord)) {
                    repetitionData.removeCurrentWordFromRepeat();
                    Toast.makeText(getContext(), "Usunieto z powtórzenia", Toast.LENGTH_SHORT).show();
                } else {
                    repetitionData.addCurrentWordToRepeat();
                    Toast.makeText(getContext(), "Dodano do powtórzenia", Toast.LENGTH_SHORT).show();
                }
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
