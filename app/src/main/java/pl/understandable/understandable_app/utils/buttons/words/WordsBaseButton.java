package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;

import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.utils.buttons.BaseButton;
import pl.understandable.understandable_app.utils.buttons.BaseChoiceButton;

/**
 * Created by Marcin Zielonka on 2017-05-07.
 */

public abstract class WordsBaseButton extends BaseChoiceButton<WordsDataParams> {

    public WordsBaseButton(Context context, WordsDataParams dataParams, Identifiable enumType, boolean checked) {
        super(context, dataParams, enumType, checked);
    }

}
