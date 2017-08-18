package pl.understandable.understandable_app.fragments.theme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.themes.ThemeType;
import pl.understandable.understandable_app.utils.buttons.themes.ThemeButton;
import pl.understandable.understandable_app.utils.font.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka
 */

public class ThemeChoiceFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TableLayout themesLayout;
    private TextView title;

    private List<ThemeButton> themes = new ArrayList<>();

    public ThemeChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the themesLayout for this fragment
        View rootView = inflater.inflate(R.layout.f_theme_choice, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_theme_choice);
        themesLayout = (TableLayout) rootView.findViewById(R.id.f_theme_choice_theme_names_layout);
        title = (TextView) rootView.findViewById(R.id.f_theme_choice_title);
    }

    private void prepareLayout() {
        setFonts();
        initCategoriesButtons();
        addCategoryButtonsToTable();
    }

    public void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void initCategoriesButtons() {
        for(ThemeType theme : ThemeType.values()) {
            themes.add(new ThemeButton(getContext(), theme, themes));
        }
    }

    private void addCategoryButtonsToTable() {
        TableRow currentImageRow = new TableRow(getContext());
        TableRow currentTextRow = new TableRow(getContext());

        int x = 0;
        for (ThemeButton theme : themes) {
            currentImageRow.addView(theme.getImage());
            currentTextRow.addView(theme.getText());
            if (x == 2) {
                themesLayout.addView(currentImageRow);
                themesLayout.addView(currentTextRow);
                currentImageRow = new TableRow(getContext());
                currentTextRow = new TableRow(getContext());
                x = 0;
            } else {
                x++;
            }
        }
        if (x != 0) {
            themesLayout.addView(currentImageRow);
            themesLayout.addView(currentTextRow);
        }
    }
}
