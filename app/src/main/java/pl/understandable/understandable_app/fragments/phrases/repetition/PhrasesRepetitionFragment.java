package pl.understandable.understandable_app.fragments.phrases.repetition;

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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesRepetitionData;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.ToastUtil;

public class PhrasesRepetitionFragment extends Fragment {

    private final int NUMB_WORDS = PhrasesRepetitionData.getRepetitionData().getEntities().size();

    private PhrasesRepetitionData repetitionData;

    private RelativeLayout mainLayout;
    private Button repeat, finish;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    public PhrasesRepetitionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repetitionData = PhrasesRepetitionData.getRepetitionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.f_phrases_repetition, container, false);
        loadViewFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_repetition);
        pager = (ViewPager) rootView.findViewById(R.id.f_phrases_repetition_view_pager);
        repeat = (Button) rootView.findViewById(R.id.f_phrases_repetition_repeat);
        finish = (Button) rootView.findViewById(R.id.f_phrases_repetition_finish);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareAdapter();
    }

    private void prepareAdapter() {
        pagerAdapter = new PhrasesRepetitionFragment.ScreenSlidePagerAdapter(this.getFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
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
                    ToastUtil.showToastMessage(getContext(), "Usunieto z powtórzenia", 800);
                } else {
                    repetitionData.addCurrentWordToRepeat();
                    ToastUtil.showToastMessage(getContext(), "Dodano do powtórzenia", 800);
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhrasesRepetitionResultFragment phrasesRepetitionResultFragment = new PhrasesRepetitionResultFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, phrasesRepetitionResultFragment, FragmentUtil.F_PHRASES_REPETITION_RESULT).commit();
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhrasesRepetitionExampleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUMB_WORDS;
        }
    }

}
