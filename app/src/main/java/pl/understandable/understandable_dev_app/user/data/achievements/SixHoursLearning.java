package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class SixHoursLearning extends Achievement {

    private static final long SIX_HOURS_IN_MILLIS = 21600000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= SIX_HOURS_IN_MILLIS;
    }

}
