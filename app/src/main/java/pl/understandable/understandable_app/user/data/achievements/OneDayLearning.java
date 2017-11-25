package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class OneDayLearning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.ONE_DAY_LEARNING;
    }

    @Override
    public String getName() {
        return "Dniówka";
    }

    @Override
    public String getDescription() {
        return "1 dzień spędzony na nauce";
    }

    private static final long ONE_DAY_IN_MILLIS = 86400000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= ONE_DAY_IN_MILLIS;
    }

}
