package pl.understandable.understandable_app.user.requests;

import pl.understandable.understandable_app.user.Request;
import pl.understandable.understandable_app.user.SyncHandler;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-11-25.
 */

public class RemoveDownloadedTestCode implements Request {

    private String code;

    public RemoveDownloadedTestCode(String code) {
        this.code = code;
    }

    @Override
    public void executeRequest() {
        if(SyncHandler.getSyncStatus()) {
            User.getUser().removeDownloadedTest(code);
            User.getUser().setSyncRequired(true);
        }
    }

}
