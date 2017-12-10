package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

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
        return UserManager.getUser().getStats().getAllTestsSolved() >= 100;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ts100;
    }

}
