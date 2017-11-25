package pl.understandable.understandable_app.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.NavigationActivity;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetPreviewFragment;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.fragments.custom_words.other.DownloadCustomWordsSetFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetPreviewFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetsListFragment;
import pl.understandable.understandable_app.fragments.phrases.choice.PhrasesChoiceCategoryFragment;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.*;

/**
 * Created by Marcin Zielonka on 2017-07-07.
 */

public class BackButtonListener {

    private static final long MAX_CLICK_DELAY_IN_MILLIS_TO_EXIT_APP = 2000;
    private static long lastClick = 0;

    private NavigationActivity activity;
    private FragmentManager fragmentManager;

    public BackButtonListener(NavigationActivity activity) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
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
        if(activity.drawer.isDrawerOpen(GravityCompat.START)) {
            activity.drawer.closeDrawer(GravityCompat.START);
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(tag.equals(APP_EXIT)) {
            long click = System.currentTimeMillis();
            if(click - lastClick < MAX_CLICK_DELAY_IN_MILLIS_TO_EXIT_APP) {
                activity.finishAffinity();
            } else {
                Toast.makeText(activity.getApplicationContext(), "Naciśnij ponownie, aby wyjść", Toast.LENGTH_SHORT).show();
                lastClick = click;
            }
            return;
        }
        if(tag.equals(F_START)) {
            transaction.replace(R.id.layout_for_fragments, new StartFragment(), redirectTo(APP_EXIT)).commit();
            return;
        }
        if(tag.equals(F_GRAMMAR_SETS_LIST)) {
            transaction.replace(R.id.layout_for_fragments, new GrammarSetsListFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_CUSTOM_WORDS_SETS_LIST)) {
            transaction.replace(R.id.layout_for_fragments, new CustomWordsSetsListFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_PHRASES_CHOICE_CATEGORY)) {
            transaction.replace(R.id.layout_for_fragments, new PhrasesChoiceCategoryFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_DOWNLOAD_CUSTOM_WORDS_SET)) {
            transaction.replace(R.id.layout_for_fragments, new DownloadCustomWordsSetFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.contains(F_CUSTOM_WORDS_SET_PREVIEW)) {
            String id = tag.substring(F_CUSTOM_WORDS_SET_PREVIEW.length() + 1);
            transaction.replace(R.id.layout_for_fragments, CustomWordsSetPreviewFragment.newInstance(id), redirectTo(F_START)).commit();
            return;
        }
        if(tag.contains(F_GRAMMAR_SET_PREVIEW)) {
            String params = tag.substring(F_GRAMMAR_SET_PREVIEW.length() + 1);
            System.out.println("params: " + params);
            String[] decomposedParams = decomposeParams(params, 2);
            transaction.replace(R.id.layout_for_fragments, GrammarSetPreviewFragment.newInstance(decomposedParams[0], decomposedParams[1]), redirectTo(F_GRAMMAR_SETS_LIST)).commit();
            return;
        }
    }

    private String[] decomposeParams(String source, int numberOfParams) {
        String[] result = new String[numberOfParams];
        int i = 0;
        int p = 0;
        for(; p < numberOfParams - 1; p++) {
            String str = "";
            for(; i < source.length(); i++) {
                if(source.charAt(i) == ':') {
                    result[p] = str;
                    break;
                } else {
                    str += source.charAt(i);
                }
            }
        }
        result[p] = source.substring(i + 1);
        return result;
    }

}
