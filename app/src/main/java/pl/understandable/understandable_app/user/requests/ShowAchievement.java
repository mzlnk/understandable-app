package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.data.achievements.AchievementId;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class ShowAchievement implements Request {

    private AchievementId id;

    public ShowAchievement(AchievementId id) {
        this.id = id;
    }

    @Override
    public void executeRequest() {
        //todo: show achievement on main thread in dialog
    }

}
