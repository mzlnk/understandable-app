package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FiveThousandTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIVE_THOUSAND_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Zapalony Uczeń";
    }

    @Override
    public String getDescription() {
        return "5000 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getAllTestsSolved() >= 5000;
    }

}
