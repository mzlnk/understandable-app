package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class OneHourLearning extends Achievement {

    private static final long ONE_HOUR_IN_MILLIS = 3600000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= ONE_HOUR_IN_MILLIS;
    }

}
