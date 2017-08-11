package pl.understandable.understandable_app.fragments.phrases.choice;

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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.CustomWordsSetsRepository;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetPreviewFragment;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.FragmentUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PhrasesChoiceCategoryFragment extends Fragment {

    private int textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;
    private TextView title;

    private PhrasesDataParams dataParams;

    public PhrasesChoiceCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataParams = new PhrasesDataParams();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_phrases_choice_category, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_choice_category);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_phrases_choice_category_categories_table);
        title = (TextView) rootView.findViewById(R.id.f_phrases_choice_category_title);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        addWordsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        title.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void addWordsToList() {
        for(final PhrasesCategory category : PhrasesCategory.values()) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            prepareCell(t1, category.getName(), 1F);

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

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataParams.setCategory(category);
                    FragmentManager fragmentManager = getFragmentManager();
                    PhrasesChoiceModeFragment fragment = PhrasesChoiceModeFragment.newInstance(dataParams.toString());
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_PHRASES_CHOICE_MODE).commit();
                }
            });

            wordsTable.addView(row);
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

}
