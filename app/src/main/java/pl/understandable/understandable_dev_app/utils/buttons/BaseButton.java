package pl.understandable.understandable_dev_app.utils.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;
import pl.understandable.understandable_dev_app.data.params.BaseDataParams;
import pl.understandable.understandable_dev_app.utils.ColorUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public abstract class BaseButton<P extends BaseDataParams> {

    protected static final float ITEM_CHOSEN = 1.0F;
    protected static final float ITEM_NOT_CHOSEN = 0.43F;

    protected ColorUtil colorUtil;

    protected Context context;
    protected ImageView image;
    protected TextView text;

    protected P dataParams;
    protected Identifiable enumType;

    protected boolean checked;

    public BaseButton(Context context, P dataParams, Identifiable enumType, boolean checked) {
        this.context = context;
        this.dataParams = dataParams;
        this.image = new ImageView(context);
        this.text = new TextView(context);
        this.enumType = enumType;
        this.colorUtil = new ColorUtil(context);
        this.checked = checked;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    protected void prepare() {
        prepareImage();
        prepareText();
        setSize();
    }

    private void prepareImage() {
        image.setImageResource(R.drawable.f_words_choice_unchecked);
        image.setClickable(true);
        setChoiceState();
        setOnClickListener();
    }

    private void prepareText() {
        text.setText(enumType.getName());
        setTextColor();
        text.setGravity(Gravity.CENTER);
        text.setTypeface(Font.TYPEFACE_MONTSERRAT, Typeface.BOLD);
    }

    private void setSize() {
        int imageSize = (int) context.getResources().getDimension(R.dimen.f_choice_icon_size);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        image.setLayoutParams(layoutParams);
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.f_choice_icon_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = text.getTextSize() * factor;
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
    }

    private void setTextColor() {
        text.setTextColor(colorUtil.getColor(R.attr.text_1_color));
    }

    protected abstract void setChoiceState();
    protected abstract void setOnClickListener();

}
