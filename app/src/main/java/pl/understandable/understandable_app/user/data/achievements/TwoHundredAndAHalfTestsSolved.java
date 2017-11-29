package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TwoHundredAndAHalfTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.TWO_HUNDRED_AND_A_HALF_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Obeznany";
    }

    @Override
    public String getDescription() {
        return "250 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getAllTestsSolved() >= 250;
    }

}
