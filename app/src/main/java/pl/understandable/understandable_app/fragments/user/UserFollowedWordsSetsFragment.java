package pl.understandable.understandable_app.fragments.user;

import android.graphics.Typeface;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;
import pl.understandable.understandable_app.database.repository.temp.FollowedCustomWordsSetsRepository;
import pl.understandable.understandable_app.dialogs.CustomWordsSetPreviewDialog;
import pl.understandable.understandable_app.dialogs.user_dialogs.FollowedCustomWordsSetPreviewDialog;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_USER;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class UserFollowedWordsSetsFragment extends Fragment {

    private RelativeLayout mainLayout;
    private TextView title;
    private TableLayout wordsSetsTable;
    private Button back;

    private int textColor;

    public UserFollowedWordsSetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_user_followed_words_sets, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_user_followed_words_sets);
        wordsSetsTable = (TableLayout) rootView.findViewById(R.id.f_user_followed_words_sets_table);
        title = (TextView) rootView.findViewById(R.id.f_user_followed_tests_title);
        back = (Button) rootView.findViewById(R.id.f_user_followed_words_sets_button_back);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        addWordsSetsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        back.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            back.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            back.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void addWordsSetsToList() {
        for(final CustomWordsSetEntity wordsSet : FollowedCustomWordsSetsRepository.getWordsSets()) {

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
                    FollowedCustomWordsSetPreviewDialog dialog = new FollowedCustomWordsSetPreviewDialog(getContext(), getFragmentManager(), wordsSet.getId());
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                UserFragment fragment = new UserFragment();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, fragment, redirectTo(F_START)).commit();
            }
        });
    }

}
