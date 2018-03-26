package pl.understandable.understandable_app.utils;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import pl.understandable.understandable_app.activities.MainActivity;

import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-07.
 */

public class FragmentUtil {

    public static final String APP_EXIT = "app_exit";
    public static final String F_START = "f_start";
    public static final String F_CUSTOM_WORDS_SET_PREVIEW = "f_custom_words_set_preview";
    public static final String F_CUSTOM_WORDS_SETS_LIST = "f_custom_words_sets_list";
    public static final String F_GRAMMAR_SET_PREVIEW = "f_grammar_set_preview";
    public static final String F_GRAMMAR_SETS_LIST = "f_grammar_sets_list";
    public static final String F_PHRASES_CHOICE_CATEGORY = "f_phrases_choice_category";
    public static final String F_DOWNLOAD_CUSTOM_WORDS_SET = "f_download_custom_words_set";
    public static final String F_USER = "f_user";
    public static final String F_WORDS_QUIZ_RESULT = "f_words_quiz_result";
    public static final String F_WORDS_SPELLING_RESULT = "f_words_spelling_result";
    public static final String F_WORDS_REPETITION_RESULT = "f_words_repetition_result";
    public static final String F_GRAMMAR_QUIZ_RESULT = "f_grammar_quiz_result";
    public static final String F_GRAMMAR_FILL_GAP_RESULT = "f_grammar_fill_gap_result";
    public static final String F_IRREGULAR_VERBS_REPETITION_RESULT = "f_irregular_verbs_repetition_result";
    public static final String F_PHRASES_REPETITION_RESULT = "f_phrases_repetition_result";
    public static final String F_PHRASES_QUIZ_RESULT = "f_phrases_quiz_result";
    public static final String F_CUSTOM_WORDS_REPETITION_RESULT = "f_custom_words_repetition_result";
    public static final String F_CUSTOM_WORDS_QUIZ_RESULT = "f_custom_words_quiz_result";
    public static final String F_CUSTOM_WORDS_SPELLING_RESULT = "f_custom_words_spelling_result";

    public static String redirectTo(String target, String... params) {
        String result = new String(target);
        if(params.length > 0) {
            for(int i = 0; i < params.length; i++) {
                result += ":" + params[i];
            }
        }
        return result;
    }

    public static Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.getActivity().getSupportFragmentManager();
        @SuppressLint("RestrictedApi") List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null) {
            for(Fragment fragment : fragments) {
                if(fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
        }
        return null;
    }

}
