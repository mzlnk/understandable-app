package pl.understandable.understandable_app.fragments.irregular_verbs.repetition;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.entities_data.irregular_verbs_data.IrregularVerbsListData;
import pl.understandable.understandable_app.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import pl.understandable.understandable_app.user.ExpManager;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.data.UserStatistics;
import pl.understandable.understandable_app.user.requests.AddExp;
import pl.understandable.understandable_app.user.requests.AddTestSolved;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class IrregularVerbsRepetitionResultFragment extends Fragment {

    private IrregularVerbsRepetitionData repetitionData = IrregularVerbsRepetitionData.getRepetitionData();

    private RelativeLayout mainLayout;
    private TextView wordsSeen, wordsToRepeat;
    private TextView wordsSeenInfo, wordsToRepeatInfo;
    private TextView mainTitle, wordsToRepeatTitle;
    private Button tryAgain, viewWordsToRepeat;

    public IrregularVerbsRepetitionResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_irregular_verbs_repetition_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        addUserStats();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_irregular_verbs_repetition_result);
        wordsSeen = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_words_seen_amount);
        wordsToRepeat = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_words_to_repeat_amount);
        wordsSeenInfo = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_words_seen_info);
        wordsToRepeatInfo = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_words_to_repeat_info);
        mainTitle = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_title);
        wordsToRepeatTitle = (TextView) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_words_to_repeat_title);
        tryAgain = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_try_again);
        viewWordsToRepeat = (Button) rootView.findViewById(R.id.f_irregular_verbs_repetition_result_view_words_to_repeat);
    }

    private void prepareLayout() {
        setAnimation();
        setFonts();
        prepareButtons();
        setStats();
    }

    private void setAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade01);
        mainLayout.setAnimation(anim);
    }

    private void setFonts() {
        Typeface typeface = Font.TYPEFACE_MONTSERRAT;
        wordsSeen.setTypeface(typeface);
        wordsToRepeat.setTypeface(typeface);
        wordsSeenInfo.setTypeface(typeface);
        wordsToRepeatInfo.setTypeface(typeface);
        mainTitle.setTypeface(typeface);
        wordsToRepeatTitle.setTypeface(typeface);
        viewWordsToRepeat.setTypeface(typeface);
        tryAgain.setTypeface(typeface);
    }

    private void prepareButtons() {
        ThemeUtil themeUtil = new ThemeUtil(getContext());
        if(themeUtil.isDefaultTheme()) {
            viewWordsToRepeat.setBackgroundResource(R.drawable.field_rounded_pink);
            tryAgain.setBackgroundResource(R.drawable.field_rounded_light_pink);
        } else {
            viewWordsToRepeat.setBackgroundResource(R.drawable.field_rounded_gray);
            tryAgain.setBackgroundResource(R.drawable.field_rounded_light_gray);
        }
    }

    private void setStats() {
        wordsSeen.setText(String.valueOf(repetitionData.wordsSeen.size()) + "/" + String.valueOf(repetitionData.getEntities().size()));
        wordsToRepeat.setText(String.valueOf(repetitionData.wordsToRepeat.size()));
    }

    private void addListeners() {
        viewWordsToRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrregularVerbsRepetitionResultWordsToRepeatFragment fragment = new IrregularVerbsRepetitionResultWordsToRepeatFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repetitionData.resetStats();
                IrregularVerbsRepetitionFragment repetitionFragment = new IrregularVerbsRepetitionFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, repetitionFragment, redirectTo(F_START)).commit();
            }
        });
    }

    private void addUserStats() {
        int amount = IrregularVerbsRepetitionData.getRepetitionData().wordsSeen.size();
        RequestExecutor.offerRequest(new AddExp(getContext(), ExpManager.ExpRatio.IRREGULAR_VERBS_REPETITION, amount));
        RequestExecutor.offerRequest(new AddTestSolved(UserStatistics.IRREGULAR_VERBS, UserStatistics.REPETITION));
    }

}
