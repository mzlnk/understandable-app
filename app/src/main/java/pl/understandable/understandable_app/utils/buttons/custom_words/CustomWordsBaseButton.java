package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.utils.buttons.BaseChoiceButton;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public abstract class CustomWordsBaseButton extends BaseChoiceButton<CustomWordsDataParams> {

    public CustomWordsBaseButton(Context context, CustomWordsDataParams dataParams, Identifiable enumType, boolean checked) {
        super(context, dataParams, enumType, checked);
    }

}
