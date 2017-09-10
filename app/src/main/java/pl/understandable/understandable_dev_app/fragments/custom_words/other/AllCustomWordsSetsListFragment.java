package pl.understandable.understandable_dev_app.fragments.custom_words.other;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_dev_app.database.repository.temp.AllCustomWordsSetsRepository;
import pl.understandable.understandable_dev_app.dialogs.CustomWordsSetPreviewDialog;
import pl.understandable.understandable_dev_app.utils.ColorUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_DOWNLOAD_CUSTOM_WORDS_SET;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class AllCustomWordsSetsListFragment extends Fragment {

    private static final String SEARCH_TARGET_PARAM = "all.custom.words.sets.list.searchTargetParam";
    private static final String PAGE_PARAM = "all.custom.words.sets.list.pageParam";
    private static final String ANIMATION_PARAM = "all.custom.words.sets.list.animationParam";

    private static final int ELEMENTS_PER_PAGE = 30;

    private int textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsSetsTable;
    //private TextView title;
    private EditText searchField;
    private Button search;
    private Button previousPage, nextPage;

    private List<CustomWordsSetEntity> wordsSets;

    private String searchTarget = "";
    private int page = 0;
    private boolean animation = true;

    public AllCustomWordsSetsListFragment() {
        // Required empty public constructor
    }

    public static AllCustomWordsSetsListFragment newInstance(String searchTarget, int page) {
        AllCustomWordsSetsListFragment fragment = new AllCustomWordsSetsListFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_TARGET_PARAM, searchTarget);
        args.putInt(PAGE_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }

    public static AllCustomWordsSetsListFragment newInstance(String searchTarget, int page, boolean animation) {
        AllCustomWordsSetsListFragment fragment = new AllCustomWordsSetsListFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_TARGET_PARAM, searchTarget);
        args.putInt(PAGE_PARAM, page);
        args.putBoolean(ANIMATION_PARAM, animation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            searchTarget = getArguments().getString(SEARCH_TARGET_PARAM);
            page = getArguments().getInt(PAGE_PARAM);
            animation = getArguments().getBoolean(ANIMATION_PARAM, true);
        }
        wordsSets = AllCustomWordsSetsRepository.getWordSets(searchTarget);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_all_custom_words_sets_list, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_all_custom_words_sets_list);
        wordsSetsTable = (TableLayout) rootView.findViewById(R.id.f_all_custom_words_sets_list_table);
        //title = (TextView) rootView.findViewById(R.id.f_all_custom_words_sets_list_title);
        searchField = (EditText) rootView.findViewById(R.id.f_all_custom_words_sets_list_search_field);
        search = (Button) rootView.findViewById(R.id.f_all_custom_words_sets_list_button_search);
        previousPage = (Button) rootView.findViewById(R.id.f_all_custom_words_sets_list_button_previous_page);
        nextPage = (Button) rootView.findViewById(R.id.f_all_custom_words_sets_list_button_next_page);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        prepareSearchField();
        addWordsSetsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        if(animation) {
            mainLayout.setAnimation(anim);
        }
        wordsSetsTable.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        //title.setTypeface(typeface);
        searchField.setTypeface(typeface);
        search.setTypeface(typeface);
        previousPage.setTypeface(typeface);
        nextPage.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            search.setBackgroundResource(R.drawable.field_rounded_light_pink);
            previousPage.setBackgroundResource(R.drawable.field_rounded_pink);
            nextPage.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            search.setBackgroundResource(R.drawable.field_rounded_light_gray);
            previousPage.setBackgroundResource(R.drawable.field_rounded_gray);
            nextPage.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void prepareSearchField() {
        if(searchTarget != null && !searchTarget.isEmpty()) {
            searchField.setText(searchTarget);
        }
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isNightTheme()) {
            Drawable bottomLine = searchField.getBackground();
            bottomLine.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            if(Build.VERSION.SDK_INT > 16) {
                searchField.setBackground(bottomLine);
            } else {
                searchField.setBackgroundDrawable(bottomLine);
            }
        }
    }

    private void addWordsSetsToList() {
        int firstElement = page * ELEMENTS_PER_PAGE;
        int lastElement = firstElement + ELEMENTS_PER_PAGE;
        for(int i = firstElement; i < lastElement; i++) {
            if(i > (wordsSets.size() - 1)) {
                break;
            }
            final CustomWordsSetEntity wordsSet = wordsSets.get(i);
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            prepareCell(t1, wordsSet.getId(), 0.3F);
            prepareCell(t2, wordsSet.getName(), 0.7F);

            ThemeUtil themeUtil = new ThemeUtil(getContext());
            if(themeUtil.isDefaultTheme()) {
                row.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
            } else {
                row.setBackgroundResource(R.drawable.field_rounded_gray);
            }

            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            int marginInPixels = getContext().getResources().getDimensionPixelSize(R.dimen.f_custom_words_sets_list_row_margin);
            layoutParams.setMargins(0, 0, 0, marginInPixels);
            row.setLayoutParams(layoutParams);

            row.setMinimumHeight(getContext().getResources().getDimensionPixelSize(R.dimen.f_custom_words_sets_list_row_height));
            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
            row.addView(t2);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomWordsSetPreviewDialog dialog = new CustomWordsSetPreviewDialog(getContext(), getFragmentManager(), wordsSet.getId());
                    dialog.show();
                }
            });
            wordsSetsTable.addView(row);
        }
    }

    private void prepareCell(TextView textView, String content, float weight) {
        textView.setText(content);
        textView.setTextColor(textColor);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);
        textView.setGravity(Gravity.CENTER);

        TypedValue outValue = new TypedValue();
        getContext().getResources().getValue(R.dimen.f_list_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = textView.getTextSize() * factor;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
        textView.setLayoutParams(new TableRow.LayoutParams(0, MATCH_PARENT, weight));
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

    private void addListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                AllCustomWordsSetsListFragment fragment = AllCustomWordsSetsListFragment.newInstance(searchField.getText().toString(), 0, false);
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_DOWNLOAD_CUSTOM_WORDS_SET)).commit();
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });
        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPage = page - 1;
                if(newPage < 0) {
                    return;
                }
                FragmentManager fragmentManager = getFragmentManager();
                AllCustomWordsSetsListFragment fragment = AllCustomWordsSetsListFragment.newInstance(searchTarget, newPage, false);
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_DOWNLOAD_CUSTOM_WORDS_SET)).commit();
            }
        });
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPage = page + 1;
                if(newPage * ELEMENTS_PER_PAGE > wordsSets.size()) {
                    return;
                }
                FragmentManager fragmentManager = getFragmentManager();
                AllCustomWordsSetsListFragment fragment = AllCustomWordsSetsListFragment.newInstance(searchTarget, newPage, false);
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_DOWNLOAD_CUSTOM_WORDS_SET)).commit();
            }
        });
    }

}
