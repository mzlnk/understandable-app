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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.data.ChosenWordsData;
import net.heliantum.ziedic.data.enums.LearningWay;

import java.util.List;
import java.util.Random;

public class WordsRepetitionFragment extends Fragment {

    private List<LanguageEntity> words;
    private LanguageEntity currentWord;

    private Button repeat;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private static Random r = new Random();
    private final int NUMB_WORDS = ChosenWordsData.getAllChosenWords().size();

    public WordsRepetitionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        words = ChosenWordsData.getAllChosenWords();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_words_repetition, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);

        repeat = (Button) rootView.findViewById(R.id.f_words_repetition_repeat);

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Dodano do powtórzenia", Toast.LENGTH_SHORT).show();
            }
        });

        setTypeface();

        return rootView;
    }

    private void setTypeface() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        repeat.setTypeface(typeFace);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            currentWord = words.get(position);
            return getFragmentByDirection();
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }

        private WordsRepetitionExampleFragment getFragmentByDirection() {

            switch(ChosenWordsData.getWay()) {
                case POLISH_TO_ENGLISH:
                    return WordsRepetitionExampleFragment.newInstance(currentWord.getPolishWord(), currentWord.getEnglishWord());
                case ENGLISH_TO_POLISH:
                    return WordsRepetitionExampleFragment.newInstance(currentWord.getEnglishWord(), currentWord.getPolishWord());
                default:
                    if(r.nextBoolean())
                        return WordsRepetitionExampleFragment.newInstance(currentWord.getPolishWord(), currentWord.getEnglishWord());
                    else
                        return WordsRepetitionExampleFragment.newInstance(currentWord.getEnglishWord(), currentWord.getPolishWord());
            }
        }
    }
}
