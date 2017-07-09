package net.heliantum.understandable.fragments.words.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.words_data.WordsListData;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.utils.ColorUtil;
import net.heliantum.understandable.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class WordsListFragment extends Fragment {

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;
    private TextView title;

    public WordsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_words_list, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_list);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_words_list_table);
        title = (TextView) rootView.findViewById(R.id.f_words_list_title);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        addWordsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void addWordsToList() {
        boolean color = true;
        for(LanguageEntity word : WordsListData.getListData().getWords()) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            prepareCell(t1, word.getPolishWord());
            prepareCell(t2, word.getEnglishWord());

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

    private void prepareCell(TextView textView, String content) {
        textView.setText(content);
        textView.setTextColor(textColor);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getContext().getResources().getDimension(R.dimen.f_words_list_text)); todo: fix it
        textView.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.5F));
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
