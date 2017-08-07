package pl.understandable.understandable_app.fragments.custom_words.spelling;

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

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CustomWordsSpellingResultIncorrectWordsSummaryFragment extends Fragment {

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TextView title;
    private TableLayout wordsTable;
    private Button back;

    public CustomWordsSpellingResultIncorrectWordsSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_custom_words_spelling_result_incorrect_words_summary, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_words_summary);
        title = (TextView) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_words_summary_title);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_words_summary_table);
        back = (Button) rootView.findViewById(R.id.f_custom_words_spelling_result_incorrect_words_summary_back);
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
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        back.setTypeface(Font.TYPEFACE_MONTSERRAT);
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
        boolean color = true;
        for(CustomWordEntity word : CustomWordsSpellingData.getSpellingData().incorrectAnswers) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            t1.setText(word.getPolishWord());
            t1.setTextColor(textColor);
            t1.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t1.setLayoutParams(new TableRow.LayoutParams(0, MATCH_PARENT, 0.5F));
            t2.setText(word.getEnglishWord());
            t2.setTextColor(textColor);
            t2.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t2.setLayoutParams(new TableRow.LayoutParams(0, MATCH_PARENT, 0.5F));

            if(color) {
                row.setBackgroundColor(list1Color);
            } else {
                row.setBackgroundColor(list2Color);
            }
            color = !color;

            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
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

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
