package net.heliantum.understandable.fragments.words.choice;


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
import android.widget.Toast;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.data.enums.words.WordsLanguageCategory;
import net.heliantum.understandable.fragments.words.utils.choice.WordsCategoryButton;
import net.heliantum.understandable.utils.FragmentUtil;
import net.heliantum.understandable.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

public class WordsChoiceCategoryFragment extends Fragment {

    private static final String DATA_PARAM = "words.category.dataParam";

    private RelativeLayout mainLayout;
    private TableLayout categoriesLayout;
    private TextView title;
    private Button submit;

    private List<WordsCategoryButton> categories = new ArrayList<>();
    private WordsDataParams dataParams;

    public WordsChoiceCategoryFragment() {
        // Required empty public constructor
    }

    public static WordsChoiceCategoryFragment newInstance(String param) {
        WordsChoiceCategoryFragment fragment = new WordsChoiceCategoryFragment();
        Bundle args = new Bundle();
        args.putString(DATA_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            dataParams = WordsDataParams.fromString(getArguments().getString(DATA_PARAM));
        }
        if(dataParams == null) {
            dataParams = new WordsDataParams();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the categoriesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_words_choice_category, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_words_choice_category);
        categoriesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_category_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_category_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_category_submit);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        initCategoriesButtons();
        addCategoryButtonsToTable();
    }

    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    public void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
        submit.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initCategoriesButtons() {
        for(WordsLanguageCategory category : WordsLanguageCategory.values()) {
            categories.add(new WordsCategoryButton(getContext(), dataParams, category));
        }
    }

    private void addCategoryButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (WordsCategoryButton categoryButton : categories) {
            currentImageRow.addView(categoryButton.getImage());
            currentTextRow.addView(categoryButton.getText());
            if (x == 3) {
                categoriesLayout.addView(currentImageRow);
                categoriesLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            categoriesLayout.addView(currentImageRow);
            categoriesLayout.addView(currentTextRow);
        }
    }

    private void addListeners() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataParams.categories.size() > 0) {
                    WordsChoiceTypeFragment typeFragment = WordsChoiceTypeFragment.newInstance(dataParams.toString());
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment, FragmentUtil.F_WORDS_CHOICE_TYPE).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 kategorię", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
