package pl.understandable.understandable_app.user.data.achievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public enum AchievementId {

    APP_THEME_CHANGED("theme"),
    FIRST_WORDS_TEST_SOLVED("ftswords"),
    FIRST_IRREGULAR_VERBS_TEST_SOLVED("ftsirregular"),
    FIRST_PHRASES_TEST_SOLVED("ftsphrases"),
    FIRST_DOWNLOADED_TEST_SOLVED("ftdownload"),
    FIFTY_TESTS_SOLVED("ts50"),
    HUNDRED_TESTS_SOLVED("ts100"),
    TWO_HUNDRED_AND_A_HALF_TESTS_SOLVED("ts250"),
    FIVE_HUNDRED_TESTS_SOLVED("ts500"),
    THOUSAND_TESTS_SOLVED("ts1000"),
    TWO_THOUSAND_FIVE_HUNDRED_TESTS_SOLVED("ts2500"),
    FIVE_THOUSAND_TESTS_SOLVED("ts5000"),
    TEN_THOUSAND_TESTS_SOLVED("ts10000"),
    FIRST_TEST_DOWNLOADED("ftsdownload"),
    QUIZ_CORRECTLY_SOLVED("quiz100"),
    ONE_HOUR_LEARNING("learning1h"),
    SIX_HOURS_LEARNING("learning6h"),
    TWELVE_HOURS_LEARNING("learning12h"),
    ONE_DAY_LEARNING("learning1d"),
    ONE_WEEK_LEARNING("learnign1w"),
    LEARNING_AT_NIGHT("lnight"),
    LEARNING_IN_THE_MORNING("lmorning");

    private String id2;

    private AchievementId(String id2) {
        this.id2 = id2;
    }

    public String getId2() {
        return id2;
    }

}
