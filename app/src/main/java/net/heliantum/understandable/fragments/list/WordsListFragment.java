package net.heliantum.understandable.fragments.list;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.ListData;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.utils.font.Font;

public class WordsListFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;

    public WordsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.f_words_list, container, false);
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
            t1.setTextColor(Color.BLACK);
            t1.setTypeface(Font.TYPEFACE_MONTSERRAT);
            t2.setText(word.getEnglishWord());
            t2.setTextColor(Color.BLACK);
            t2.setTypeface(Font.TYPEFACE_MONTSERRAT);

            if(color) {
                row.setBackgroundColor(Color.rgb(224, 224, 244));
            }
            color = !color;

            row.addView(t1);
            row.addView(t2);
            wordsTable.addView(row);
        }
    }

}
