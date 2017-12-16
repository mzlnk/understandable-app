package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.achievements.Achievement;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class CheckAchievements implements Request {

    @Override
    public void executeRequest() {
        if(UserManager.isUserSignedIn() && SyncManager.getSyncParams().isSyncOnline()) {
            for (Achievement achievement : UserManager.getUser().getAllAchievements().values()) {
                if (!achievement.isAchieved() && achievement.isAchievable()) {
                    RequestExecutor.offerRequest(new ShowAchievement(achievement.getId()));
                    achievement.setAchieved(true);
                    UserManager.setSyncRequired(true);
                    UserManager.addElementToSync(UserManager.SyncElement.ACHIEVEMENTS);
                }
            }
        }
    }

}
