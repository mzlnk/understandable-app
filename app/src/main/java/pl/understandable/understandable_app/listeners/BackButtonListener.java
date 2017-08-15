package pl.understandable.understandable_app.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetsListFragment;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.*;

/**
 * Created by Marcin Zielonka on 2017-07-07.
 */

public class BackButtonListener {

    FragmentManager fragmentManager;

    public BackButtonListener(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void onBackPressed() {
        Fragment visibleFragment = FragmentUtil.getVisibleFragment();
        if(visibleFragment == null) {
            return;
        }
        String tag = visibleFragment.getTag();
        if(tag == null) {
            return;
        }
        switch(tag) {
            case F_CUSTOM_WORDS_SET_PREVIEW:
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new CustomWordsSetsListFragment(), F_CUSTOM_WORDS_SETS_LIST).commit();
                break;
            case F_GRAMMAR_SET_PREVIEW:
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new GrammarSetsListFragment(), F_GRAMMAR_SETS_LIST).commit();
                break;
            case F_THEME_CHOICE:
            case F_WORDS_CHOICE_CATEGORY:
            case F_WORDS_REPETITION_RESULT:
            case F_WORDS_QUIZ_RESULT:
            case F_WORDS_LIST:
            case F_IRREGULAR_VERBS_CHOICE_MODE:
            case F_IRREGULAR_VERBS_REPETITION_RESULT:
            case F_IRREGULAR_VERBS_LIST:
            case F_DOWNLOAD_CUSTOM_WORDS_SET:
            case F_CUSTOM_WORDS_SETS_LIST:
            case F_CUSTOM_WORDS_CHOICE_WAY:
            case F_CUSTOM_WORDS_REPETITION_RESULT:
            case F_CUSTOM_WORDS_QUIZ_RESULT:
            case F_CUSTOM_WORDS_LIST:
            case F_GRAMMAR_SETS_LIST:
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new StartFragment(), F_START).commit();
                break;
        }
    }

}
