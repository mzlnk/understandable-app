package pl.understandable.understandable_app.fragments.custom_words.repetition;

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
import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsRepetitionData;
import pl.understandable.understandable_app.user.ExpManager;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.data.UserStatistics;
import pl.understandable.understandable_app.user.requests.AddExp;
import pl.understandable.understandable_app.user.requests.AddTestSolved;
import pl.understandable.understandable_app.utils.ThemeUtil;
import pl.understandable.understandable_app.utils.font.Font;

import static pl.understandable.understandable_app.utils.FragmentUtil.F_CUSTOM_WORDS_REPETITION_RESULT;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_CUSTOM_WORDS_SET_PREVIEW;
import static pl.understandable.understandable_app.utils.FragmentUtil.F_START;
import static pl.understandable.understandable_app.utils.FragmentUtil.redirectTo;

/**
 * Created by Marcin Zielonka
 */

public class CustomWordsRepetitionResultFragment extends Fragment {

    private CustomWordsRepetitionData repetitionData = CustomWordsRepetitionData.getRepetitionData();

    private RelativeLayout mainLayout;
    private TextView wordsSeen, wordsToRepeat;
    private TextView wordsSeenInfo, wordsToRepeatInfo;
    private TextView mainTitle, wordsToRepeatTitle;
    private Button tryAgain, viewWordsToRepeat;

    public CustomWordsRepetitionResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f_custom_words_repetition_result, container, false);
        loadViewsFromXml(rootView);
        prepareLayout();
        addListeners();
        addUserStats();

        return rootView;
    }

    private void loadViewsFromXml(View rootView) {
        mainLayout = (RelativeLayout) rootView.findViewById(R.id.f_custom_words_repetition_result);
        wordsSeen = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_words_seen_amount);
        wordsToRepeat = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_words_to_repeat_amount);
        wordsSeenInfo = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_words_seen_info);
        wordsToRepeatInfo = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_words_to_repeat_info);
        mainTitle = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_title);
        wordsToRepeatTitle = (TextView) rootView.findViewById(R.id.f_custom_words_repetition_result_words_to_repeat_title);
        tryAgain = (Button) rootView.findViewById(R.id.f_custom_words_repetition_result_try_again);
        viewWordsToRepeat = (Button) rootView.findViewById(R.id.f_custom_words_repetition_result_view_words_to_repeat);
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
                CustomWordsRepetitionResultWordsToRepeatFragment fragment = new CustomWordsRepetitionResultWordsToRepeatFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_for_fragments, fragment, redirectTo(F_CUSTOM_WORDS_REPETITION_RESULT, repetitionData.getParams().id));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repetitionData.resetStats();
                CustomWordsRepetitionFragment wordsRepetitionFragment = new CustomWordsRepetitionFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.layout_for_fragments, wordsRepetitionFragment, redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, repetitionData.getParams().id)).commit();
            }
        });
    }

    private void addUserStats() {
        if(!CustomWordsRepetitionData.getRepetitionData().areStatsUpdated()) {
            int amount = CustomWordsRepetitionData.getRepetitionData().wordsSeen.size();
            RequestExecutor.offerRequest(new AddExp(getContext(), ExpManager.ExpRatio.CUSTOM_WORDS_REPETITION, amount));
            RequestExecutor.offerRequest(new AddTestSolved(UserStatistics.CUSTOM_WORDS, UserStatistics.REPETITION));
            CustomWordsRepetitionData.getRepetitionData().setStatsUpdated(true);
        }
    }

}
