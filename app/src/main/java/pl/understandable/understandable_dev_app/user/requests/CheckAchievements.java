package pl.understandable.understandable_dev_app.user.requests;

import pl.understandable.understandable_dev_app.user.Request;
import pl.understandable.understandable_dev_app.user.RequestExecutor;
import pl.understandable.understandable_dev_app.user.SyncHandler;
import pl.understandable.understandable_dev_app.user.data.User;
import pl.understandable.understandable_dev_app.user.data.achievements.Achievement;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class CheckAchievements implements Request {

    @Override
    public void executeRequest() {
        if(SyncHandler.getSyncStatus()) {
            for (Achievement achievement : User.getUser().getAllAchievements()) {
                if (!achievement.isAchieved() && achievement.isAchievable()) {
                    RequestExecutor.offerRequest(new ShowAchievement(achievement.getId()));
                    achievement.setAchieved(true);
                    User.getUser().setSyncRequired(true);
                }
            }
        }
    }

}
