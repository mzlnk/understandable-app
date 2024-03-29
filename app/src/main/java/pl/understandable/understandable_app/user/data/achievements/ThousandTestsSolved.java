package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class ThousandTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.THOUSAND_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Doświadczony";
    }

    @Override
    public String getDescription() {
        return "1000 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getAllTestsSolved() >= 1000;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ts1000;
    }

}
