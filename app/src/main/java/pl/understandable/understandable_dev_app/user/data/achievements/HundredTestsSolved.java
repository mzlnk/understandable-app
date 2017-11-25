package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class HundredTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.HUNDRED_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Amator";
    }

    @Override
    public String getDescription() {
        return "100 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getAllTestsSolved() >= 100;
    }

}
