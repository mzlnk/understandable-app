package pl.understandable.understandable_dev_app.user.data.achievements;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FirstPhrasesTestSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIRST_PHRASES_TEST_SOLVED;
    }

    @Override
    public String getName() {
        return "Że się tak wyrażę";
    }

    @Override
    public String getDescription() {
        return "Pierwszy rozwiązany test z wyrażeń";
    }

    @Override
    public boolean isAchievable() {
        return false;
    }

}
