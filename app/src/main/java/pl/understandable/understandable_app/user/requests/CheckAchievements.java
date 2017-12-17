package pl.understandable.understandable_app.user.requests;

import android.content.Context;

import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.achievements.Achievement;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class CheckAchievements implements Request {

    private Context context;

    public CheckAchievements(Context context) {
        this.context = context;
    }

    @Override
    public void executeRequest() {
        if(UserManager.isUserSignedIn() && SyncManager.getSyncParams().isSyncOnline()) {
            for (Achievement achievement : UserManager.getUser().getAllAchievements().values()) {
                if (!achievement.isAchieved() && achievement.isAchievable()) {
                    RequestExecutor.offerRequest(new ShowAchievementMessage(context, achievement));
                    achievement.setAchieved(true);
                    UserManager.setSyncRequired(true);
                    UserManager.addElementToSync(UserManager.SyncElement.ACHIEVEMENTS);
                }
            }
        }
    }

    @Override
    public long getCooldownInMillis() {
        return 0;
    }

}
