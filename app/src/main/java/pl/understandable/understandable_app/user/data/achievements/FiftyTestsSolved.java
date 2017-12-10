package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class FiftyTestsSolved extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.FIFTY_TESTS_SOLVED;
    }

    @Override
    public String getName() {
        return "Początkujący";
    }

    @Override
    public String getDescription() {
        return "50 rozwiązanych testów";
    }

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getAllTestsSolved() >= 50;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_ts50;
    }

}
