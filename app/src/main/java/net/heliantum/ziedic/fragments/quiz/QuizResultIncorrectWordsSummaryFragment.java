package net.heliantum.ziedic.fragments.quiz;


import android.graphics.Color;
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
import net.heliantum.ziedic.data.ListData;
import net.heliantum.ziedic.data.QuizData;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * A simple {@link Fragment} subclass.
 */

public class QuizResultIncorrectWordsSummaryFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView title;
    private TableLayout wordsTable;
    private Button back;

    public QuizResultIncorrectWordsSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_quiz_result_incorrect_words_summary, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_quiz_result_incorrect_words_summary_fragment_layout);
        title = (TextView) rootView.findViewById(R.id.f_quiz_result_incorrect_words_summary_title);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_quiz_result_incorrect_words_summary_table);
        back = (Button) rootView.findViewById(R.id.f_quiz_result_incorrect_words_summary_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        addWords();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void addWords() {
        boolean color = true;
        for(LanguageEntity word : QuizData.getQuizData().incorrectAnswers) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            t1.setText(word.getPolishWord());
            t1.setTextColor(Color.BLACK);
            t1.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t1.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.4F));
            t2.setText(word.getEnglishWord());
            t2.setTextColor(Color.BLACK);
            t2.setTypeface(Font.TYPEFACE_MONTSERRAT);
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

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

}
