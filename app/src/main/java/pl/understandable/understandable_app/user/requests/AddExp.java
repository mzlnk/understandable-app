package pl.understandable.understandable_app.user.requests;

import android.content.Context;

import pl.understandable.understandable_app.user.ExpManager;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.user.data.User;

/**
 * Created by Marcin Zielonka on 2017-12-17.
 */

public class AddExp implements Request {

    private Context context;
    private int amount;
    private int[] extraAmounts;
    private ExpManager.ExpRatio ratio;

    public AddExp(Context context, ExpManager.ExpRatio ratio, int amount, int... extraAmounts) {
        this.context = context;
        this.ratio = ratio;
        this.amount = amount;
        this.extraAmounts = extraAmounts;
    }

    @Override
    public void executeRequest() {
        if(UserManager.isUserSignedIn() && SyncManager.getSyncParams().isSyncOnline()) {
            long exp = ExpManager.convertToExp(ratio, amount, extraAmounts);
            User.AddExpResponse response = UserManager.getUser().addExp(exp);
            UserManager.setSyncRequired(true);
            UserManager.addElementToSync(UserManager.SyncElement.GENERAL);
            if(exp > 0) {
                RequestExecutor.offerRequest(new ShowAddExpMessage(context, exp));
            }
            if(response.getResponse().equals(User.AddExpResponse.AddExpReponseType.LEVEL_UP)) {
                RequestExecutor.offerRequest(new ShowLevelUpMessage(context, response.getPreviousLevel(), response.getCurrentLevel()), 4500L);
            }
        }
    }

    @Override
    public long getCooldownInMillis() {
        return 0;
    }

}
