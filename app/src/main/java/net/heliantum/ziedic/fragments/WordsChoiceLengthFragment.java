package net.heliantum.ziedic.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.BaseWordsData;
import net.heliantum.ziedic.corrupted.data.QuizData;
import net.heliantum.ziedic.corrupted.data.RepetitionData;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceLengthFragment extends Fragment {

    private Typeface typeface;

    private View rootView;
    private RelativeLayout mainLayout;

    private TextView amountInfo;
    private SeekBar amountAdjust;

    public WordsChoiceLengthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.f_word_choice_length, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_length_fragment_layout);
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");

        amountInfo = (TextView) rootView.findViewById(R.id.f_words_choice_length_size_info);
        amountAdjust = (SeekBar) rootView.findViewById(R.id.f_words_choice_length_size_adjust);

        BaseWordsData.generateWordsList();

        setAnimation();
        adjustSeekBar();

        TextView title = (TextView) rootView.findViewById(R.id.f_words_choice_length_title);
        Button back = (Button) rootView.findViewById(R.id.f_words_choice_length_back);
        Button submit = (Button) rootView.findViewById(R.id.f_words_choice_length_submit);

        title.setTypeface(typeface);
        back.setTypeface(typeface);
        submit.setTypeface(typeface);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                BaseWordsData.resizeWordsList();
                QuizData.addChosenWordsToQuizData();
                QuizData.resetStats();
                RepetitionData.resetStats();
                RepetitionData.setCurrentWord(RepetitionData.allChosenWords.get(0));
                RepetitionData.addCurrentWordToSeen();

                if(BaseWordsData.allChosenWords.size() > 0) {
                    switch (BaseWordsData.mode) {
                        case REPETITION:
                            transaction.replace(R.id.layout_for_fragments, new WordsRepetitionFragment());
                            break;
                        case LIST:
                            transaction.replace(R.id.layout_for_fragments, new WordsListFragment());
                            break;
                        case QUIZ:
                            if(BaseWordsData.allChosenWords.size() >= 4) {
                                transaction.replace(R.id.layout_for_fragments, new QuizFragment());
                            } else {
                                transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment());
                                transaction.addToBackStack(null);
                            }
                            break;
                    }
                } else {
                    transaction.replace(R.id.layout_for_fragments, new NoWordsErrorFragment());
                    transaction.addToBackStack(null);
                }

                transaction.commit();
            }
        });

        return rootView;
    }

    private void adjustSeekBar() {
        final StartPosition startPos = new StartPosition();
        switch (BaseWordsData.mode) {
            case QUIZ:
                startPos.setPos(4);
                break;
            default:
                startPos.setPos(1);
        }

        amountAdjust.setMax(BaseWordsData.allChosenWords.size() - startPos.getPos());
        BaseWordsData.setSize(startPos.getPos());
        amountAdjust.setProgress(BaseWordsData.size - startPos.getPos());
        amountInfo.setText(String.valueOf(BaseWordsData.size));

        amountAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                amountInfo.setText(String.valueOf(i + startPos.getPos()));
                BaseWordsData.setSize(i + startPos.getPos());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private class StartPosition {

        private int pos;

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return pos;
        }
    }

}
