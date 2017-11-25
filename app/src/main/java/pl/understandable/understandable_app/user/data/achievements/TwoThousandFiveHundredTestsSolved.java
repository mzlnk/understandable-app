package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TwoThousandFiveHundredTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.TWO_THOUSAND_FIVE_HUNDRED_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Uzależniony";
    }

    @Override
    public String getDescription() {
        return "2500 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getAllTestsSolved() >= 2500;
    }

}
