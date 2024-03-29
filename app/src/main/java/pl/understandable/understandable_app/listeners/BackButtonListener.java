package pl.understandable.understandable_app.listeners;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.widget.Toast;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetPreviewFragment;
import pl.understandable.understandable_app.fragments.custom_words.other.CustomWordsSetsListFragment;
import pl.understandable.understandable_app.fragments.custom_words.other.DownloadCustomWordsSetFragment;
import pl.understandable.understandable_app.fragments.custom_words.quiz.CustomWordsQuizResultFragment;
import pl.understandable.understandable_app.fragments.custom_words.repetition.CustomWordsRepetitionResultFragment;
import pl.understandable.understandable_app.fragments.custom_words.spelling.CustomWordsSpellingResultFragment;
import pl.understandable.understandable_app.fragments.grammar.fill_gap.GrammarFillGapResultFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetPreviewFragment;
import pl.understandable.understandable_app.fragments.grammar.preview.GrammarSetsListFragment;
import pl.understandable.understandable_app.fragments.grammar.quiz.GrammarQuizResultFragment;
import pl.understandable.understandable_app.fragments.irregular_verbs.repetition.IrregularVerbsRepetitionResultFragment;
import pl.understandable.understandable_app.fragments.phrases.choice.PhrasesChoiceCategoryFragment;
import pl.understandable.understandable_app.fragments.phrases.quiz.PhrasesQuizResultFragment;
import pl.understandable.understandable_app.fragments.phrases.repetition.PhrasesRepetitionResultFragment;
import pl.understandable.understandable_app.fragments.start.StartFragment;
import pl.understandable.understandable_app.fragments.user.UserFragment;
import pl.understandable.understandable_app.fragments.words.list.WordsListFragment;
import pl.understandable.understandable_app.fragments.words.quiz.WordsQuizResultFragment;
import pl.understandable.understandable_app.fragments.words.repetition.WordsRepetitionResultFragment;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingResultFragment;
import pl.understandable.understandable_app.utils.FragmentUtil;

import static pl.understandable.understandable_app.utils.FragmentUtil.*;

/**
 * Created by Marcin Zielonka on 2017-07-07.
 */

public class BackButtonListener {

    private static final long MAX_CLICK_DELAY_IN_MILLIS_TO_EXIT_APP = 2000;
    private static long lastClick = 0;

    private MainActivity activity;
    private FragmentManager fragmentManager;

    public BackButtonListener(MainActivity activity) {
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
        if(tag.equals(F_USER)) {
            transaction.replace(R.id.layout_for_fragments, new UserFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_WORDS_QUIZ_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new WordsQuizResultFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_WORDS_SPELLING_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new WordsSpellingResultFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_WORDS_REPETITION_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new WordsRepetitionResultFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_IRREGULAR_VERBS_REPETITION_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new IrregularVerbsRepetitionResultFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_PHRASES_REPETITION_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new PhrasesRepetitionResultFragment(), redirectTo(F_START)).commit();
            return;
        }
        if(tag.equals(F_PHRASES_QUIZ_RESULT)) {
            transaction.replace(R.id.layout_for_fragments, new PhrasesQuizResultFragment(), redirectTo(F_PHRASES_CHOICE_CATEGORY)).commit();
            return;
        }
        if(tag.contains(F_CUSTOM_WORDS_REPETITION_RESULT)) {
            String id = tag.substring(F_CUSTOM_WORDS_REPETITION_RESULT.length() + 1);
            transaction.replace(R.id.layout_for_fragments, new CustomWordsRepetitionResultFragment(), redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, id)).commit();
            return;
        }
        if(tag.contains(F_CUSTOM_WORDS_QUIZ_RESULT)) {
            String id = tag.substring(F_CUSTOM_WORDS_QUIZ_RESULT.length() + 1);
            transaction.replace(R.id.layout_for_fragments, new CustomWordsQuizResultFragment(), redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, id)).commit();
            return;
        }
        if(tag.contains(F_CUSTOM_WORDS_SPELLING_RESULT)) {
            String id = tag.substring(F_CUSTOM_WORDS_SPELLING_RESULT.length() + 1);
            transaction.replace(R.id.layout_for_fragments, new CustomWordsSpellingResultFragment(), redirectTo(F_CUSTOM_WORDS_SET_PREVIEW, id)).commit();
            return;
        }
        if(tag.contains(F_GRAMMAR_QUIZ_RESULT)) {
            String params = tag.substring(F_GRAMMAR_QUIZ_RESULT.length() + 1);
            String[] decomposedParams = decomposeParams(params, 2);
            String id = decomposedParams[0];
            String name = decomposedParams[1];
            transaction.replace(R.id.layout_for_fragments, new GrammarQuizResultFragment(), redirectTo(F_GRAMMAR_SET_PREVIEW, id, name)).commit();
            return;
        }
        if(tag.contains(F_GRAMMAR_FILL_GAP_RESULT)) {
            String params = tag.substring(F_GRAMMAR_FILL_GAP_RESULT.length());
            String[] decomposedParams = decomposeParams(params, 2);
            String id = decomposedParams[0];
            String name = decomposedParams[1];
            transaction.replace(R.id.layout_for_fragments, new GrammarFillGapResultFragment(), redirectTo(F_GRAMMAR_SET_PREVIEW, id , name)).commit();
            return;
        }
        if(tag.contains(F_CUSTOM_WORDS_SET_PREVIEW)) {
            String id = tag.substring(F_CUSTOM_WORDS_SET_PREVIEW.length() + 1);
            transaction.replace(R.id.layout_for_fragments, CustomWordsSetPreviewFragment.newInstance(id), redirectTo(F_CUSTOM_WORDS_SETS_LIST)).commit();
            return;
        }
        if(tag.contains(F_GRAMMAR_SET_PREVIEW)) {
            String params = tag.substring(F_GRAMMAR_SET_PREVIEW.length() + 1);
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
