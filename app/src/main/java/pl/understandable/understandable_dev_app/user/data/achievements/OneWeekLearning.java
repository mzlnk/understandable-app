package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class OneWeekLearning extends Achievement {

    private static final long ONE_WEEK_IN_MILLIS = 604800000;

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getTimeLearnt() >= ONE_WEEK_IN_MILLIS;
    }

}
