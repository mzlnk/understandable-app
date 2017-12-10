package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SixHoursLearning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.SIX_HOURS_LEARNING;
    }

    @Override
    public String getName() {
        return "Już szósta minęłą";
    }

    @Override
    public String getDescription() {
        return "6 godzin spędzonych na nauce";
    }

    private static final long SIX_HOURS_IN_MILLIS = 21600000;

    @Override
    public boolean isAchievable() {
        return UserManager.getUser().getStats().getTimeLearnt() >= SIX_HOURS_IN_MILLIS;
    }

    @Override
    public int getResId() {
        return R.drawable.f_user_achievements_learning6h;
    }

}
