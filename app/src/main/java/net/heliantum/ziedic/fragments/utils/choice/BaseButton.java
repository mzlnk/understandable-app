package net.heliantum.ziedic.fragments.utils.choice;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.Nameable;
import net.heliantum.ziedic.utils.font.Font;

/**
 * Created by Marcin on 2017-05-07.
 */

public abstract class BaseButton {

    protected static final int ITEM_CHOSEN = 255;
    protected static final int ITEM_NOT_CHOSEN = 150;

    protected ImageView image;
    protected TextView text;

    protected DataParams dataParams;
    protected Nameable enumType;

    public BaseButton(Context context, DataParams dataParams, Nameable enumType) {
        this.dataParams = dataParams;
        this.image = new ImageView(context);
        this.text = new TextView(context);
        this.enumType = enumType;
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
        image.setImageResource(R.drawable.f_words_choice_base_test_selected);
        image.setClickable(true);
        setChoiceState();
        setOnClickListener();
    }

    private void prepareText() {
        text.setText(enumType.getName());
        text.setGravity(Gravity.CENTER);
        text.setTypeface(Font.TYPEFACE_MONTSERRAT);
    }

    protected abstract void setChoiceState();
    protected abstract void setOnClickListener();

}
