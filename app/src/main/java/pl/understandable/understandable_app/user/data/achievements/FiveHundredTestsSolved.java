package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FiveHundredTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIVE_HUNDRED_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Zaznajomiony";
    }

    @Override
    public String getDescription() {
        return "500 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getAllTestsSolved() >= 500;
    }

}
