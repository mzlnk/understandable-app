package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class AddFollowedTest implements Request {

    private String code;

    public AddFollowedTest(String code) {
        this.code = code;
    }

    @Override
    public void executeRequest() {
        if(UserManager.isUserSignedIn() && SyncManager.getSyncParams().isSyncOnline()) {
            UserManager.getUser().addDownloadedTest(code);
            UserManager.setSyncRequired(true);
            UserManager.addElementToSync(UserManager.SyncElement.GENERAL);
        }
    }

    @Override
    public long getCooldownInMillis() {
        return 0;
    }

}
