package pl.understandable.understandable_app.utils;

import android.app.Application;

import com.google.android.gms.analytics.HitBuilders;

import pl.understandable.understandable_app.App;

/**
 * Created by Marcin Zielonka on 2018-03-22.
 */

public class GoogleAnalyticsManager {

    public static class Tracker {

        public static void trackEvent(Application application, String action, String category) {
            App app = (App) application;
            com.google.android.gms.analytics.Tracker tracker = app.getDefaultTracker();
            tracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).build());
        }

    }

    public static class Action {

        public static final String GA_A_APP_OPEN = "a_app_open";
        public static final String GA_A_CUSTOM_WORDS_LIST = "a_custom_words_list";
        public static final String GA_A_CUSTOM_WORDS_QUIZ = "a_custom_words_quiz";
        public static final String GA_A_CUSTOM_WORDS_SPELLING = "a_custom_words_spelling";
        public static final String GA_A_CUSTOM_WORDS_REPETITION = "a_custom_words_repetition";
        public static final String GA_A_CUSTOM_WORDS_DOWNLOAD_WORDS_SET = "a_custrom_words_download_words_set";
        public static final String GA_A_WORDS_LIST = "a_words_list";
        public static final String GA_A_WORDS_QUIZ = "a_words_quiz";
        public static final String GA_A_WORDS_SPELLING = "a_words_spelling";
        public static final String GA_A_WORDS_REPETITION = "a_words_repetition";
        public static final String GA_A_IRREGULAR_VERBS_LIST = "a_words_list";
        public static final String GA_A_IRREGULAR_VERBS_REPETITION = "a_phrases_repetition";
        public static final String GA_A_PHRASES_LIST = "a_phrases_list";
        public static final String GA_A_PHRASES_QUIZ = "a_phrases_quiz";
        public static final String GA_A_PHRASES_REPETITION = "a_phrases_repetition";
        public static final String GA_A_THEME_CHANGE = "a_theme_change";
        public static final String GA_A_USER_PROFILE = "a_user_profile";
        public static final String GA_A_USER_PROFILE_ACHIEVEMENTS = "a_user_profile_achievements";
        public static final String GA_A_USER_PROFILE_STATS = "a_user_profile_achievements";

    }

    public static class Category {

        public static final String GA_C_OPEN = "c_open";
        public static final String GA_C_APP = "c_app";
        public static final String GA_C_DOWNLOAD = "c_download";

    }
}
