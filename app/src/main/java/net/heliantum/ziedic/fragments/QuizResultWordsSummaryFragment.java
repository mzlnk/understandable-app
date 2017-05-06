package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.QuizData;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class QuizResultWordsSummaryFragment extends Fragment {

    public static final boolean CORRECT_WORDS_SUMMARY = true;
    public static final boolean INCORRECT_WORDS_SUMMARY = false;

    private View rootView;
    private RelativeLayout layout;

    private TextView title;
    private TableLayout wordsTable;
    private Button back;

    public QuizResultWordsSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_quiz_result_words_summary, container, false);
        layout = (RelativeLayout) rootView.findViewById(R.id.fragment_quiz_result_words_summary_fragment_layout);
        title = (TextView) rootView.findViewById(R.id.f_quiz_result_words_summary_title);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_quiz_result_words_summary_table);
        back = (Button) rootView.findViewById(R.id.f_quiz_result_words_summary_back);

        setAnimation();
        setTypeface();
        setTitle();
        addWords();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        layout.setAnimation(anim);
    }

    private void setTypeface() {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        title.setTypeface(typeFace);
        back.setTypeface(typeFace);
    }

    private void setTitle() {
        if(QuizData.wordsSummaryStatus) {
            title.setText("Odpowiedzi poprawne");
        } else {
            title.setText("Odpowiedzi błędne");
        }
        title.setTextColor(Color.BLACK);
    }

    private void addWords() {
        List<LanguageEntity> words = new ArrayList<>();
        if(QuizData.wordsSummaryStatus) {
            words.addAll(QuizData.correctWords);
        } else {
            words.addAll(QuizData.incorrectWords);
        }

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        boolean color = true;

        for(LanguageEntity word : words) {
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
            space.setText("     ");
            space.setTextColor(Color.WHITE);
            space.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.1F));

            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
            row.addView(space);
            row.addView(t2);
            wordsTable.addView(row);
        }
    }

}
