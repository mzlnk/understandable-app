package pl.understandable.understandable_app.utils.buttons.themes;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public abstract class ThemeBaseButton extends BaseButton {

    public ThemeBaseButton(Context context, Identifiable enumType, boolean checked) {
        super(context, enumType, checked);
    }

}
