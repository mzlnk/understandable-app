package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TenThousandTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.TEN_THOUSAND_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Bezwględnie Wyuczony";
    }

    @Override
    public String getDescription() {
        return "10000 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getAllTestsSolved() >= 2500;
    }

}
