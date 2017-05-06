package net.heliantum.ziedic.fragments;


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
import android.widget.Toast;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.corrupted.data.BaseWordsData;
import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.fragments.interfaces.Fragmentable;
import net.heliantum.ziedic.fragments.utils.wordschoicecategory.CategoryButton;
import net.heliantum.ziedic.utils.font.Font;
import net.heliantum.ziedic.utils.font.FontUtil;

import java.util.ArrayList;
import java.util.List;

public class WordsChoiceCategoryFragment extends Fragment implements Fragmentable {

    private View rootView;
    private RelativeLayout mainLayout;

    private TableLayout categoriesLayout;
    private TextView title;
    private Button submit;

    private List<CategoryButton> categories = new ArrayList<>();

    public WordsChoiceCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the categoriesLayout for this fragment
        rootView = inflater.inflate(R.layout.fragment_words_choice_category, container, false);
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.fragment_words_choice_category_fragment_layout);
        categoriesLayout = (TableLayout) rootView.findViewById(R.id.f_words_choice_category_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_words_choice_category_title);
        submit = (Button) rootView.findViewById(R.id.f_words_choice_category_submit);

        setAnimation();
        setSize();
        setFonts();
        setTheme();

        initCategories();
        addCategoriesToTable();

        addButtonListeners();

        return rootView;
    }

    @Override
    public void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    @Override
    public void setSize() {
        //todo: code here
    }

    @Override
    public void setFonts() {
        Font titleFont = new Font(Font.TYPEFACE_MONTSERRAT, 26.0F, Color.BLACK);
        Font submitFont = new Font(Font.TYPEFACE_MONTSERRAT, 18.0F, Color.WHITE);
        FontUtil.setFont(title, titleFont);
        FontUtil.setFont(submit, submitFont);
    }

    @Override
    public void setTheme() {
        //todo: code here
    }

    private void initCategories() {
        for(LanguageCategory category : LanguageCategory.values()) {
            categories.add(new CategoryButton(getContext(), category));
        }
    }

    private void addCategoriesToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (CategoryButton categoryButton : categories) {

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

    private void addButtonListeners() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BaseWordsData.categories.size() > 0) {
                    WordsChoiceTypeFragment typeFragment = new WordsChoiceTypeFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.layout_for_fragments, typeFragment).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getContext(), "Wybierz przynajmniej 1 kategorię", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
