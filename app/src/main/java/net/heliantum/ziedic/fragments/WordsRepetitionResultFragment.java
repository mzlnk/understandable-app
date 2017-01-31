package net.heliantum.ziedic.fragments;


import android.graphics.Color;
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
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.RepetitionData;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.utils.ToastUtil;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class WordsRepetitionResultFragment extends Fragment {

    private View rootView;
    private RelativeLayout mainLayout;

    private TableLayout wordsToRepeatTable;
    private ScrollView wordsToRepeatField;
    private TextView wordsSeen, wordsToRepeat;
    private TextView wordsSeenInfo, wordsToRepeatInfo;
    private TextView mainTitle, wordsToRepeatTitle;

    private Button tryAgain;

    private long firstTapTime = 0;
    private static final long DOUBLE_TAP_PERIOD = 500;

    public WordsRepetitionResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_words_repetition_result, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_repetition_result_fragment_layout);
        wordsToRepeatTable = (TableLayout) rootView.findViewById(R.id.f_words_repetition_result_words_to_repeat_table);
        wordsSeen = (TextView) rootView.findViewById(R.id.f_words_repetition_result_words_seen);
        wordsToRepeat = (TextView) rootView.findViewById(R.id.f_words_repetition_result_words_to_repeat);
        wordsSeenInfo = (TextView) rootView.findViewById(R.id.f_words_repetition_result_words_seen_info);
        wordsToRepeatInfo = (TextView) rootView.findViewById(R.id.f_words_repetition_result_words_to_repeat_info);
        mainTitle = (TextView) rootView.findViewById(R.id.f_words_repetition_result_title);
        wordsToRepeatTitle = (TextView) rootView.findViewById(R.id.f_words_repetition_result_words_to_repeat_title);
        tryAgain = (Button) rootView.findViewById(R.id.f_words_repetition_result_try_again);
        wordsToRepeatField = (ScrollView) rootView.findViewById(R.id.f_words_repetition_result_words_to_repeat_field);

        setAnimation();
        setTypeface();
        setStats();
        addWordsToRepeatToTable();

        wordsToRepeatTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeNow = System.currentTimeMillis();
                if(timeNow - firstTapTime <= DOUBLE_TAP_PERIOD) {
                    WordsRepetitionResultWordsToRepeatFragment wordsRepetitionResultWordsToRepeatFragment = new WordsRepetitionResultWordsToRepeatFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_for_fragments, wordsRepetitionResultWordsToRepeatFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    firstTapTime = timeNow;
                }
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RepetitionData.resetStats();
                WordsRepetitionFragment wordsRepetitionFragment = new WordsRepetitionFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionFragment).commit();
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setTypeface() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        wordsSeen.setTypeface(typeface);
        wordsToRepeat.setTypeface(typeface);
        wordsSeenInfo.setTypeface(typeface);
        wordsToRepeatInfo.setTypeface(typeface);
        mainTitle.setTypeface(typeface);
        wordsToRepeatTitle.setTypeface(typeface);
        tryAgain.setTypeface(typeface);
    }

    private void setStats() {
        wordsSeen.setText(String.valueOf(RepetitionData.wordsSeen.size()) + "/" + String.valueOf(RepetitionData.allChosenWords.size()));
        wordsToRepeat.setText(String.valueOf(RepetitionData.wordsToRepeat.size()));
    }

    private void addWordsToRepeatToTable() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        boolean color = true;

        for(LanguageEntity word : RepetitionData.wordsToRepeat) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            t1.setText(word.getPolishWord());
            t1.setTextColor(Color.BLACK);
            t1.setTypeface(typeface);
            t1.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.4F));
            t2.setText(word.getEnglishWord());
            t2.setTextColor(Color.BLACK);
            t2.setTypeface(typeface);
            t2.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.4F));

            if(color) {
                row.setBackgroundColor(Color.rgb(224, 224, 244));
            }

            color = !color;

            TextView space = new TextView(getContext());
            space.setText("");
            space.setTextColor(Color.WHITE);
            space.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.1F));

            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
            row.addView(space);
            row.addView(t2);
            wordsToRepeatTable.addView(row);
        }
    }

}
