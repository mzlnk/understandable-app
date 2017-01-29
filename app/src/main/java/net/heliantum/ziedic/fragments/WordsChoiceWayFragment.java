package net.heliantum.ziedic.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.BaseWordsData;
import net.heliantum.ziedic.data.enums.LearningWay;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceWayFragment extends Fragment {

    private Typeface typeface;

    private View rootView;
    private RelativeLayout mainLayout;
    private TableLayout layout;

    private List<ImageView> ways = new ArrayList<>();

    public WordsChoiceWayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_words_choice_way, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_way_fragment_layout);
        layout = (TableLayout) rootView.findViewById(R.id.f_words_choice_way_names_layout);
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");

        setAnimation();
        addWays();

        TextView title = (TextView) rootView.findViewById(R.id.f_words_choice_way_title);
        Button submit = (Button) rootView.findViewById(R.id.f_words_choice_way_submit);
        Button back = (Button) rootView.findViewById(R.id.f_words_choice_way_back);

        title.setTypeface(typeface);
        submit.setTypeface(typeface);
        back.setTypeface(typeface);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceModeFragment modeFragment = new WordsChoiceModeFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void addWays() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int imageSize = (int)(width * 0.2);
        int textSize = (int)(width * 0.08);
        int fontSize = (int)(width / 45D);

        TableRow.LayoutParams imageParams = new TableRow.LayoutParams(imageSize, imageSize);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(textSize, (textSize / 2));
        int pos = 0;

        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        for(final LearningWay way : LearningWay.values()) {

            final ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.f_words_choice_base_test_selected);
            image.setLayoutParams(imageParams);
            if(BaseWordsData.way.equals(way)) {
                image.setImageAlpha(255);
            } else {
                image.setImageAlpha(150);
            }
            image.setClickable(true);
            ways.add(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(image.getImageAlpha() == 150) {
                        image.setImageAlpha(255);
                        BaseWordsData.setWay(way);
                        for(ImageView way : ways) {
                            if(way.equals(image)) continue;
                            way.setImageAlpha(150);
                        }
                    }
                }
            });

            TextView text = new TextView(getContext());
            text.setText(way.getName());
            text.setTypeface(typeface);
            text.setTextColor(Color.BLACK);
            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            text.setGravity(Gravity.CENTER);
            text.setLayoutParams(textParams);

            currentImageRow.addView(image);
            currentTextRow.addView(text);
            if(pos == 2) {
                layout.addView(currentImageRow);
                layout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                pos = 0;
            } else {
                pos++;
            }
        }
        if(pos != 0) {
            layout.addView(currentImageRow);
            layout.addView(currentTextRow);
        }
    }

}
