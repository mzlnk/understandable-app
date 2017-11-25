package pl.understandable.understandable_app.user.data.achievements;

import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TwelveHoursLearning extends Achievement {

    @Override
    public AchievementId getId() {
        return AchievementId.TWELVE_HOURS_LEARNING;
    }

    @Override
    public String getName() {
        return "Wpół do dwunastej";
    }

    @Override
    public String getDescription() {
        return "12 godzin spędzonych na nauce";
    }

    private static final long TWELVE_HOUR_IN_MILLIS = 43200000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= TWELVE_HOUR_IN_MILLIS;
    }

}
