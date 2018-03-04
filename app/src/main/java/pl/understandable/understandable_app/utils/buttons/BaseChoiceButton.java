package pl.understandable.understandable_app.utils.buttons;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.params.BaseDataParams;
import pl.understandable.understandable_app.utils.ColorUtil;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public abstract class BaseChoiceButton<P extends BaseDataParams> extends BaseButton {

    protected P dataParams;

    public BaseChoiceButton(Context context, P dataParams, Identifiable enumType, boolean checked) {
        super(context, enumType, checked);
        this.context = context;
        this.dataParams = dataParams;
    }

}
