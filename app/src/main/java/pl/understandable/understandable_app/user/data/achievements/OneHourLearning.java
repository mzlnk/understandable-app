package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class OneHourLearning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.ONE_HOUR_LEARNING;
    }

    @Override
    public String getName() {
        return "Czy to już godzina?";
    }

    @Override
    public String getDescription() {
        return "1 godzina spędzona na nauce";
    }

    private static final long ONE_HOUR_IN_MILLIS = 3600000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= ONE_HOUR_IN_MILLIS;
    }

}
