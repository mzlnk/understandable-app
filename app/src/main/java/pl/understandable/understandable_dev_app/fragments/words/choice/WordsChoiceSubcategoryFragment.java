package pl.understandable.understandable_dev_app.fragments.words.choice;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageSubcategory;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;
import pl.understandable.understandable_dev_app.utils.ColorUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.buttons.words.WordsSubcategoryButton;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Marcin Zielonka
 */

public class WordsChoiceSubcategoryFragment extends Fragment {

    private static final String DATA_PARAM = "words.choice.subcategory.dataParam";

    private RelativeLayout mainLayout;
    private LinearLayout subcategoriesMainLayout;
    private TextView title;
    private Button submit, back;

    private Map<WordsLanguageCategory, List<WordsSubcategoryButton>> subcategories = new HashMap<>();
    private WordsDataParams dataParams;

    private int i = 0;

    public WordsChoiceSubcategoryFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceSubcategoryFragment newInstance(String param) {
        WordsChoiceSubcategoryFragment fragment = new WordsChoiceSubcategoryFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = new WordsDataParams().fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new WordsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the subcategoriesMainLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_subcategory, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_subcategory);
        subcategoriesMainLayout = (LinearLayout) rootView.findViewById(R.id.f_words_choice_subcategory_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_subcategory_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_subcategory_submit);
        back = (Button) rootView.findViewById(R.id.f_words_choice_subcategory_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        initSubcategoryButtons();
        addSubcategoryButtonsToTable();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        submit.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
            submit.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
            submit.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void initSubcategoryButtons() {
        for(WordsLanguageCategory category : WordsLanguageCategory.values()) {
            List<WordsSubcategoryButton> subcategoryButtons = new ArrayList<>();
            boolean active = false;
            if(dataParams.categories.contains(category)) {
                active = true;
            }
            for(WordsLanguageSubcategory subcategory : WordsLanguageSubcategory.getSubcategories(category)) {
                subcategoryButtons.add(new WordsSubcategoryButton(getContext(), dataParams, subcategory, active));
            }
            subcategories.put(category, subcategoryButtons);
        }
    }


    private void addSubcategoryButtonsToTable() {
        for(WordsLanguageCategory category : subcategories.keySet()) {
            if(dataParams.categories.contains(category)) {
                addSubcategoriesToScrollView(category, true);
            }
        }
        for(WordsLanguageCategory category : subcategories.keySet()) {
            if(!dataParams.categories.contains(category)) {
                addSubcategoriesToScrollView(category, false);
            }
        }
    }

    private void addSubcategoriesToScrollView(WordsLanguageCategory category, boolean active) {
        int width = (int)((double)getLayoutWidth() / 4.0D);

        TextView title = prepareTextView(category.getName(), active);
        subcategoriesMainLayout.addView(title);

        HorizontalScrollView subcategoriesScrollView = prepareScrollView();
        LinearLayout subcategoriesLayout = prepareSubcategoriesLayout();

        List<WordsSubcategoryButton> subcategoryButtons = subcategories.get(category);

        for(WordsSubcategoryButton subcategory : subcategoryButtons) {
            LinearLayout container = prepareSubcategoryButtonContainer(width);
            container.addView(subcategory.getImage());
            container.addView(subcategory.getText());
            subcategoriesLayout.addView(container);
        }

        subcategoriesScrollView.addView(subcategoriesLayout);
        subcategoriesMainLayout.addView(subcategoriesScrollView);
        if(i != (subcategories.size() - 1)) {
            subcategoriesMainLayout.addView(prepareLine());
        }
        i++;
    }

    private TextView prepareTextView(String title, boolean active) {
        TextView textView = new TextView(getContext());
        ColorUtil colorUtil = new ColorUtil(getContext());
        float textSize = getResources().getDimension(R.dimen.f_choice_subcategory_subtitle);
        int margin = getResources().getDimensionPixelSize(R.dimen.f_border_margin_medium);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, margin, 0, margin);

        textView.setText(title);
        textView.setTextColor(colorUtil.getColor(R.attr.text_1_color));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);

        if(!active) {
            textView.setAlpha(0.15F);
        }

        return textView;
    }

    private HorizontalScrollView prepareScrollView() {
        HorizontalScrollView scrollView = new HorizontalScrollView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(layoutParams);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);
        return scrollView;
    }

    private LinearLayout prepareSubcategoriesLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private LinearLayout prepareSubcategoryButtonContainer(int width) {
        LinearLayout container = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);
        return container;
    }

    private View prepareLine() {
        View line = new View(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 5);
        ColorUtil colorUtil = new ColorUtil(getContext());
        int margin = getResources().getDimensionPixelSize(R.dimen.f_border_margin_medium);
        layoutParams.setMargins(0, margin, 0, 0);
        line.setLayoutParams(layoutParams);
        line.setBackgroundColor(colorUtil.getColor(R.attr.bottom_line_color));
        return line;
    }

    private int getLayoutWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        int margin = getResources().getDimensionPixelSize(R.dimen.f_border_margin_medium);
        return (dm.widthPixels - 2 * margin);
    }

    private void addListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                WordsChoiceMethodFragment methodFragment = WordsChoiceMethodFragment.newInstance(dataParams.toString());
                manager.beginTransaction().replace(R.id.layout_for_fragments, methodFragment).commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataParams.subcategories.size() > 0) {
                    WordsChoiceWayFragment wayFragment = WordsChoiceWayFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, wayFragment).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 podkategorię", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
