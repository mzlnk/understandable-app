package pl.understandable.understandable_app.utils.buttons.achievements;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2017-12-03.
 */

public abstract class AchievementBaseButton extends BaseButton {

    public AchievementBaseButton(Context context, Identifiable enumType, boolean checked) {
        super(context, enumType, checked);
    }

}
