package pl.understandable.understandable_app.utils.buttons.user_stats;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2017-12-10.
 */

public abstract class UserStatsBaseButton extends BaseButton {

    public UserStatsBaseButton(Context context, Identifiable enumType) {
        super(context, enumType, true);
    }

}
