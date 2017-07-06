package net.heliantum.understandable.fragments.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import net.heliantum.understandable.data.ListData;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.utils.ColorUtil;
import net.heliantum.understandable.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class WordsListFragment extends Fragment {

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;

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
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_words_list_words_table);
    }

    private void prepareLayout() {
        setAnimation();
        addWordsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void addWordsToList() {
        boolean color = true;
        for(LanguageEntity word : ListData.getListData().getWords()) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            t1.setText(word.getPolishWord());
            t1.setTextColor(textColor);
            t1.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t1.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.5F));
            t2.setText(word.getEnglishWord());
            t2.setTextColor(textColor);
            t2.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t2.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 0.5F));

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

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
