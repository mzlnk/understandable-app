package net.heliantum.ziedic.fragments;


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

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.RepetitionData;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.data.BaseWordsData;
import net.heliantum.ziedic.utils.ToastUtil;

import java.util.List;

public class WordsRepetitionFragment extends Fragment {

    private View rootView;
    private RelativeLayout mainLayout;

    private List<LanguageEntity> words;
    private LanguageEntity currentWord;

    private Button repeat;
    private Button finish;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private final int NUMB_WORDS = BaseWordsData.allChosenWords.size();

    public WordsRepetitionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        words = BaseWordsData.allChosenWords;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_words_repetition, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_repetition_fragment_layout);

        setAnimation();

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);

        repeat = (Button) rootView.findViewById(R.id.f_words_repetition_repeat);
        finish = (Button) rootView.findViewById(R.id.f_words_repetition_finish);

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RepetitionData.existsInToRepeatWords(RepetitionData.currentWord)) {
                    RepetitionData.removeCurrentWordFromRepeat();
                    ToastUtil.showToastMessage(getContext(), "Usunieto z powtórzenia", 800);
                } else {
                    RepetitionData.addCurrentWordToRepeat();
                    ToastUtil.showToastMessage(getContext(), "Dodano do powtórzenia", 800);
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsRepetitionResultFragment wordsRepetitionResultFragment = new WordsRepetitionResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionResultFragment).commit();
            }
        });

        setTypeface();

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setTypeface() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        repeat.setTypeface(typeFace);
        finish.setTypeface(typeFace);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            currentWord = words.get(position);
            RepetitionData.setCurrentWord(currentWord);
            RepetitionData.addCurrentWordToSeen();
            return getFragmentByDirection();
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }

        private WordsRepetitionExampleFragment getFragmentByDirection() {
            return new WordsRepetitionExampleFragment();
        }
    }
}
