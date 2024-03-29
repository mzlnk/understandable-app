package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class OneWeekLearning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.ONE_WEEK_LEARNING;
    }

    @Override
    public String getName() {
        return "Tygodniówka";
    }

    @Override
    public String getDescription() {
        return "1 tydzień spędzony na nauce";
    }

    private static final long ONE_WEEK_IN_MILLIS = 604800000;

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getTimeLearnt() >= ONE_WEEK_IN_MILLIS;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_learning1w;
    }

}
