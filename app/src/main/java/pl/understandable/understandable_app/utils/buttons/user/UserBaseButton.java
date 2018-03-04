package pl.understandable.understandable_app.utils.buttons.user;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2017-12-09.
 */

public abstract class UserBaseButton extends BaseButton {

    public UserBaseButton(Context context, Identifiable enumType) {
        super(context, enumType, true);
    }

}
