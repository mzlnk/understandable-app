package net.heliantum.understandable.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.heliantum.understandable.R;
import net.heliantum.understandable.fragments.custom_words.CustomWordsSetsListFragment;
import net.heliantum.understandable.fragments.start.StartFragment;
import net.heliantum.understandable.utils.FragmentUtil;

import static net.heliantum.understandable.utils.FragmentUtil.*;

/**
 * Created by Marcin on 2017-07-07.
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
            case F_WORDS_CHOICE_CATEGORY:
            case F_THEME_CHOICE:
            case F_WORDS_REPETITION_RESULT:
            case F_WORDS_QUIZ_RESULT:
            case F_WORDS_LIST:
            case F_IRREGULAR_VERBS_CHOICE_MODE:
            case F_IRREGULAR_VERBS_REPETITION_RESULT:
            case F_IRREGULAR_VERBS_LIST:
            case F_DOWNLOAD_CUSTOM_WORDS_SET:
            case F_CUSTOM_WORDS_SETS_LIST:
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragments, new StartFragment(), F_START).commit();
                break;
        }
    }

}
