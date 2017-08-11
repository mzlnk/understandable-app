package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin on 2017-08-11.
 */

public abstract class PhrasesBaseButton extends BaseButton<PhrasesDataParams> {

    public PhrasesBaseButton(Context context, PhrasesDataParams dataParams, Identifiable enumType, boolean checked) {
        super(context, dataParams, enumType, checked);
    }

}
