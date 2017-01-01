package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.CurrentlyChosenWordsData;
import net.heliantum.ziedic.data.LanguageCategory;

public class WordsChoiceCategoryFragment extends Fragment {

    private View rootView;
    private TableLayout layout;

    public WordsChoiceCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_words_choice_category, container, false);
        layout = (TableLayout) rootView.findViewById(R.id.f_words_choice_category_names_layout);

        addCategories();

        return rootView;
    }

    public void addCategories() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int imageSize = (int)(width * 0.2);
        int textSize = (int)(width * 0.08);
        int fontSize = (int)(width / 45D);

        TableRow.LayoutParams imageParams = new TableRow.LayoutParams(imageSize, imageSize);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(textSize, (textSize / 2));
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");
        int pos = 0;

        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        for(final LanguageCategory category : LanguageCategory.values()) {

            final ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.f_words_choice_base_test_selected);
            image.setLayoutParams(imageParams);
            image.setImageAlpha(150);
            image.setClickable(true);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(image.getImageAlpha() == 150) {
                        image.setImageAlpha(255);
                        CurrentlyChosenWordsData.addCategory(category);
                    } else {
                        image.setImageAlpha(150);
                        CurrentlyChosenWordsData.removeCategory(category);
                    }
                }
            });

            TextView text = new TextView(getContext());
            text.setText(category.getName());
            text.setTypeface(font);
            text.setTextColor(Color.BLACK);
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            text.setGravity(Gravity.CENTER);
            text.setLayoutParams(textParams);

            currentImageRow.addView(image);
            currentTextRow.addView(text);
            if(pos == 3) {
                layout.addView(currentImageRow);
                layout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                pos = 0;
            } else {
                pos++;
            }
        }
        if(pos != 3) {
            layout.addView(currentImageRow);
            layout.addView(currentTextRow);
        }
    }

}
