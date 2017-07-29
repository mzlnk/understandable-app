package pl.understandable.understandable_app.fragments.irregular_verbs.utils.choice;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.utils.ColorUtil;
import pl.understandable.understandable_app.utils.font.Font;

/**
 * Created by Marcin on 2017-07-08.
 */

public abstract class IrregularVerbsBaseButton {

    protected static final int ITEM_CHOSEN = 255;
    protected static final int ITEM_NOT_CHOSEN = 110;

    protected ColorUtil colorUtil;

    protected Context context;
    protected ImageView image;
    protected TextView text;

    protected IrregularVerbsDataParams dataParams;
    protected Identifiable enumType;

    public IrregularVerbsBaseButton(Context context, IrregularVerbsDataParams dataParams, Identifiable enumType) {
        this.context = context;
        this.dataParams = dataParams;
        this.image = new ImageView(context);
        this.text = new TextView(context);
        this.enumType = enumType;
        this.colorUtil = new ColorUtil(context);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    protected void prepare() {
        prepareImage();
        prepareText();
    }

    private void prepareImage() {
        image.setImageResource(R.drawable.f_words_choice_base);
        image.setClickable(true);
        setChoiceState();
        setOnClickListener();
    }

    private void prepareText() {
        text.setText(enumType.getName());
        setTextColor();
        text.setGravity(Gravity.CENTER);
        text.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    private void setTextColor() {
        text.setTextColor(colorUtil.getColor(R.attr.text_1_color));
    }

    protected abstract void setChoiceState();
    protected abstract void setOnClickListener();

}
