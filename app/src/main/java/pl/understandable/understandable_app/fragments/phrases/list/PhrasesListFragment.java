package pl.understandable.understandable_app.fragments.phrases.list;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.irregular_verbs_data.IrregularVerbsListData;
import pl.understandable.understandable_app.data.entities_data.phrases.PhrasesListData;
import pl.understandable.understandable_app.data.entities_data.words_data.WordsListData;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWay;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.dialogs.RateAppInfoDialog;
import pl.understandable.understandable_app.user.ExpManager;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.data.UserStatistics;
import pl.understandable.understandable_app.user.requests.AddExp;
import pl.understandable.understandable_app.user.requests.AddTestSolved;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.EntitySortUtil;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Marcin Zielonka
 */

public class PhrasesListFragment extends Fragment {

    private static final int ELEMENTS_PER_PAGE = 100;

    private int start = 0;
    private int end = ELEMENTS_PER_PAGE;

    private int list1Color, list2Color, textColor;

    private RelativeLayout mainLayout;
    private TableLayout wordsTable;
    private ScrollView scrollViewArea;
    private TextView title;
    private Button previous, next;

    public PhrasesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_phrases_list, container, false);
        initColors();
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        addUserStats();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_phrases_list);
        wordsTable = (TableLayout) rootView.findViewById(R.id.f_phrases_list_table);
        scrollViewArea = (ScrollView) rootView.findViewById(R.id.f_phrases_list_scroll_view_area);
        title = (TextView) rootView.findViewById(R.id.f_phrases_list_title);
        previous = (Button) rootView.findViewById(R.id.f_phrases_list_button_previous);
        next = (Button) rootView.findViewById(R.id.f_phrases_list_button_next);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        addWordsToList();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade00);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        title.setTypeface(typeface);
        previous.setTypeface(typeface);
        next.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            previous.setBackgroundResource(R.drawable.field_rounded_pink);
            next.setBackgroundResource(R.drawable.field_rounded_pink);
        } else {
            previous.setBackgroundResource(R.drawable.field_rounded_gray);
            next.setBackgroundResource(R.drawable.field_rounded_gray);
        }
    }

    private void addWordsToList() {
        boolean color = true;

        List<PhraseEntity> entities = PhrasesListData.getListData().getEntities();
        PhrasesLearningWay learningWay = PhrasesListData.getListData().getParams().way;
        EntitySortUtil.sort(entities, learningWay);

        for(int i = start; i < end && i < entities.size(); i++) {
            PhraseEntity word = entities.get(i);
            TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            TextView t2 = new TextView(getContext());
            if(learningWay.equals(PhrasesLearningWay.ENGLISH_TO_POLISH)) {
                prepareCell(t1, word.getEnglish());
                prepareCell(t2, word.getPolish());
            } else {
                prepareCell(t1, word.getPolish());
                prepareCell(t2, word.getEnglish());
            }

            if(color) {
                row.setBackgroundColor(list1Color);
            } else {
                row.setBackgroundColor(list2Color);
            }
            color = !color;

            row.setMeasureWithLargestChildEnabled(true);
            row.addView(t1);
            row.addView(t2);
            wordsTable.addView(row);
        }
    }

    private void prepareCell(TextView textView, String content) {
        textView.setText(content);
        textView.setTextColor(textColor);
        textView.setTypeface(Font.TYPEFACE_MONTSERRAT);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, MATCH_PARENT, 0.5F);

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.f_list_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = textView.getTextSize() * factor;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);

        int margin = getResources().getDimensionPixelSize(R.dimen.f_list_margin);
        params.setMargins(margin, margin, margin, margin);

        textView.setLayoutParams(params);
    }

    private void initColors() {
        ColorUtil colorUtil = new ColorUtil(getContext());
        list1Color = colorUtil.getColor(R.attr.list_1_color);
        list2Color = colorUtil.getColor(R.attr.list_2_color);
        textColor = colorUtil.getColor(R.attr.text_1_color);
    }

    private void addUserStats() {
        int amount = PhrasesListData.getListData().getEntities().size();
        RequestExecutor.offerRequest(new AddExp(getContext(), ExpManager.ExpRatio.PHRASES_LIST, amount));
        RequestExecutor.offerRequest(new AddTestSolved(UserStatistics.PHRASES, UserStatistics.LIST));
    }

    private void addListeners() {
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start == 0) {
                    Toast.makeText(getContext(), "To jest pierwsza strona", Toast.LENGTH_SHORT).show();
                    return;
                }
                start -= ELEMENTS_PER_PAGE;
                end -= ELEMENTS_PER_PAGE;
                wordsTable.removeAllViews();
                addWordsToList();
                scrollViewArea.scrollTo(0, 0);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start + ELEMENTS_PER_PAGE >= PhrasesListData.getListData().getEntities().size()) {
                    Toast.makeText(getContext(), "To jest ostatnia strona", Toast.LENGTH_SHORT).show();
                    return;
                }
                start += ELEMENTS_PER_PAGE;
                end += ELEMENTS_PER_PAGE;
                wordsTable.removeAllViews();
                addWordsToList();
                scrollViewArea.scrollTo(0, 0);
            }
        });
    }

}
