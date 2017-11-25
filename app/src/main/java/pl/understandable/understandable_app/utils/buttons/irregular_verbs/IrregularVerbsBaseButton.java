package pl.understandable.understandable_app.utils.buttons.irregular_verbs;

import android.content.Context;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public abstract class IrregularVerbsBaseButton extends BaseButton<IrregularVerbsDataParams> {

    public IrregularVerbsBaseButton(Context context, IrregularVerbsDataParams dataParams, Identifiable enumType, boolean checked) {
        super(context, dataParams, enumType, checked);
    }

}
