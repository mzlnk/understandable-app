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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.BaseWordsData;
import net.heliantum.ziedic.database.entity.LanguageEntity;

public class WordsListFragment extends Fragment {

    private View rootView;
    private RelativeLayout mainLayout;

    private TableLayout wordsTable;

    public WordsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.f_words_list, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_list_fragment_layout);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_words_list_words_table);

        setAnimation();
        addWordsToList();

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void addWordsToList() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        boolean color = true;

        for(LanguageEntity word : BaseWordsData.allChosenWords) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            t1.setText(word.getPolishWord());
            t1.setTextColor(Color.BLACK);
            t1.setTypeface(typeface);
            t2.setText(word.getEnglishWord());
            t2.setTextColor(Color.BLACK);
            t2.setTypeface(typeface);

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
