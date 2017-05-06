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
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.BaseWordsData;
import net.heliantum.ziedic.data.enums.LanguageType;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsChoiceTypeFragment extends Fragment {

    private Typeface typeface;

    private View rootView;
    private RelativeLayout mainLayout;
    private TableLayout layout;

    public WordsChoiceTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_words_choice_type, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_type_fragment_layout);
        layout = (TableLayout) rootView.findViewById(R.id.f_words_choice_type_names_layout);
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular-PL.ttf");

        setAnimation();
        addTypes();

        TextView title = (TextView) rootView.findViewById(R.id.f_words_choice_type_title);
        Button submit = (Button) rootView.findViewById(R.id.f_words_choice_type_submit);
        Button back = (Button) rootView.findViewById(R.id.f_words_choice_type_back);

        title.setTypeface(typeface);
        submit.setTypeface(typeface);
        back.setTypeface(typeface);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsChoiceCategoryFragment categoryFragment = new WordsChoiceCategoryFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, categoryFragment).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BaseWordsData.types.size() > 0) {
                    WordsChoiceWayFragment modeFragment = new WordsChoiceWayFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, modeFragment).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 rodzaj", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void addTypes() {
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

        for(final LanguageType type : LanguageType.values()) {

            final ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.f_words_choice_base_test_selected);
            image.setLayoutParams(imageParams);
            if(BaseWordsData.isChosen(type)) {
                image.setImageAlpha(255);
            } else {
                image.setImageAlpha(150);
            }
            image.setClickable(true);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(image.getImageAlpha() == 150) {
                        image.setImageAlpha(255);
                        BaseWordsData.addType(type);
                    } else {
                        image.setImageAlpha(150);
                        BaseWordsData.removeType(type);
                    }
                }
            });

            TextView text = new TextView(getContext());
            text.setText(type.getName());
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
