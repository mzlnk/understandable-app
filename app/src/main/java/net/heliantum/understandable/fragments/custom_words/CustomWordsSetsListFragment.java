package net.heliantum.understandable.fragments.custom_words;

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

import net.heliantum.understandable.R;
import net.heliantum.understandable.database.entity.CustomWordsSetEntity;
import net.heliantum.understandable.database.repository.CustomWordsSetsRepository;
import net.heliantum.understandable.utils.ColorUtil;
import net.heliantum.understandable.utils.FragmentUtil;
import net.heliantum.understandable.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class CustomWordsSetsListFragment extends Fragment {

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;
    private TextView title;

    public CustomWordsSetsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_custom_words_sets_list, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_sets_list);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_custom_words_sets_list_table);
        title = (TextView) rootView.findViewById(R.id.f_custom_words_sets_list_title);
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
        boolean color = true;
        for(CustomWordsSetEntity wordsSet : CustomWordsSetsRepository.getAllEntities()) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            prepareCell(t1, wordsSet.getId(), 0.3F);
            prepareCell(t2, wordsSet.getName(), 0.7F);

            row.setBackgroundResource(R.drawable.f_custom_words_sets_list_row_background);

            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            int marginInPixels = getContext().getResources().getDimensionPixelSize(R.dimen.f_custom_words_sets_list_row_margin);
            layoutParams.setMargins(0, 0, 0, marginInPixels);
            row.setLayoutParams(layoutParams);

            row.setMinimumHeight(getContext().getResources().getDimensionPixelSize(R.dimen.f_custom_words_sets_list_row_height));
            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
            row.addView(t2);

            final String id = wordsSet.getId();

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    CustomWordsSetPreviewFragment fragment = CustomWordsSetPreviewFragment.newInstance(id);
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, FragmentUtil.F_CUSTOM_WORDS_SET_PREVIEW).commit();
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
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

}
