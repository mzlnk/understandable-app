package pl.understandable.understandable_dev_app.fragments.grammar.preview;

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
import android.widget.Toast;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.grammar.GrammarSet;
import pl.understandable.understandable_dev_app.utils.ColorUtil;
import pl.understandable.understandable_dev_app.utils.NetworkUtil;
import pl.understandable.understandable_dev_app.utils.ThemeUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.F_GRAMMAR_SETS_LIST;
import static pl.understandable.understandable_dev_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class GrammarSetsListFragment extends Fragment {

    private int textColor;

    private RelativeLayout mainLayout;
    private TableLayout grammarSetsTable;
    private TextView title;

    public GrammarSetsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_grammar_sets_list, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_grammar_sets_list);
        grammarSetsTable = (TableLayout) rootView.findViewById(R.id.f_grammar_sets_list_table);
        title = (TextView) rootView.findViewById(R.id.f_grammar_sets_list_title);
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
        for(GrammarSet grammarSet : GrammarSet.values()) {
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            prepareCell(t1, grammarSet.getName(), 1.0F);

            ThemeUtil themeUtil = new ThemeUtil(getContext());
            if(themeUtil.isDefaultTheme()) {
                row.setBackgroundResource(R.drawable.field_rounded_light_light_gray);
            } else {
                row.setBackgroundResource(R.drawable.field_rounded_gray);
            }

            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            int marginInPixels = getContext().getResources().getDimensionPixelSize(R.dimen.f_grammar_sets_list_row_margin);
            layoutParams.setMargins(0, 0, 0, marginInPixels);
            row.setLayoutParams(layoutParams);

            row.setMinimumHeight(getContext().getResources().getDimensionPixelSize(R.dimen.f_grammar_sets_list_row_height));
            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);

            final String id = grammarSet.getId();
            final String name = grammarSet.getName();

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetworkUtil networkUtil = new NetworkUtil(getContext());
                    if(!networkUtil.isNetworkAvailable()) {
                        Toast.makeText(getContext(), "Aby wyświetlić zawartość, wymagane jest połączenie z Internetem", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FragmentManager fragmentManager = getFragmentManager();
                    GrammarSetPreviewFragment fragment = GrammarSetPreviewFragment.newInstance(id, name);
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_GRAMMAR_SETS_LIST)).commit();
                }
            });


            grammarSetsTable.addView(row);
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
