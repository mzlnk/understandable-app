package pl.understandable.understandable_app.fragments.grammar.quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.grammar.GrammarQuizData;
import pl.understandable.understandable_app.database.entity.GrammarQuizEntity;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_GRAMMAR_QUIZ_RESULT;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class GrammarQuizResultCorrectExamplesSummaryFragment extends Fragment {

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TextView title;
    private TableLayout wordsTable;
    private Button back;

    public GrammarQuizResultCorrectExamplesSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_grammar_quiz_result_correct_examples_summary, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_quiz_result_correct_examples_summary);
        title = (TextView) rootView.findViewById(R.id.f_grammar_quiz_result_correct_examples_summary_title);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_grammar_quiz_result_correct_examples_summary_table);
        back = (Button) rootView.findViewById(R.id.f_grammar_quiz_result_correct_examples_summary_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        addWords();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void addWords() {
        boolean color = false;

        TableRow infoRow = new TableRow(getContext());
        TextView infoT = new TextView(getContext());
        prepareCell(infoT, null, null, true);
        infoT.setGravity(Gravity.CENTER);
        infoRow.setBackgroundColor(list1Color);
        infoRow.setMeasureWithLargestChildEnabled(true);
        infoRow.addView(infoT);
        wordsTable.addView(infoRow);

        for(GrammarQuizEntity entity : GrammarQuizData.getQuizData().correctAnswers) {
            TableRow row = new TableRow(getContext());
            TextView t = new TextView(getContext());;
            prepareCell(t, entity.getQuestion(), entity.getCorrectAnswer(), false);

            if(color) {
                row.setBackgroundColor(list1Color);
            } else {
                row.setBackgroundColor(list2Color);
            }
            color = !color;

            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t);
            wordsTable.addView(row);
        }
    }

    private void prepareCell(TextView textView, String question, String gap, boolean infoRow) {
        if(!infoRow) {
            String text = question.replace("_", "<font color='#CD3278'>" + gap + "</font>");
            textView.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        } else {
            textView.setText(String.valueOf("Poprawny przykład"));
        }
        textView.setTextColor(textColor);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, MATCH_PARENT, 1F);

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.f_list_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = textView.getTextSize() * factor;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);

        int margin = getResources().getDimensionPixelSize(R.dimen.f_list_margin);
        params.setMargins(margin, margin, margin, margin);

        textView.setLayoutParams(params);
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                String id = GrammarQuizData.getQuizData().getParams().id;
                String name = GrammarQuizData.getQuizData().getParams().name;
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new GrammarQuizResultFragment(), redirectTo(F_GRAMMAR_QUIZ_RESULT, id, name)).commit();
            }
        });
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
