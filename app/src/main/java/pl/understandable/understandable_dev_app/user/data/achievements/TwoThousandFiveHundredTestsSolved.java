package pl.understandable.understandable_dev_app.user.data.achievements;

import pl.understandable.understandable_dev_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class TwoThousandFiveHundredTestsSolved extends Achievement {

    @Override
    public boolean isAchievable() {
        return User.getUser().getStats().getAllTestsSolved() >= 2500;
    }

}
