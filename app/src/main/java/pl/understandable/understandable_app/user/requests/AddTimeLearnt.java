package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.activities.MainActivity;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddTimeLearnt implements Request {

    private long time;

    public AddTimeLearnt(long time) {
        this.time = time;
    }

    @Override
    public void executeRequest() {
        if(UserManager.isUserSignedIn() && SyncManager.getSyncParams().isSyncOnline()) {
            if(isUserActive()) {
                UserManager.getUser().getStats().addTimeLearnt(time);
                UserManager.setSyncRequired(true);
                UserManager.addElementToSync(UserManager.SyncElement.GENERAL);
            }
        }
    }

    @Override
    public long getCooldownInMillis() {
        return 0;
    }

    private boolean isUserActive() {
        return MainActivity.userActive;
    }

}
