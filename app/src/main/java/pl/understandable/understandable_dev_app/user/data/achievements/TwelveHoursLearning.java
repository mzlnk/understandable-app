package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TwelveHoursLearning extends Achievement {

    private static final long TWELVE_HOUR_IN_MILLIS = 43200000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= TWELVE_HOUR_IN_MILLIS;
    }

}
