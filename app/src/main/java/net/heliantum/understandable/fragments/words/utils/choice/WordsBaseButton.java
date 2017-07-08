package net.heliantum.understandable.fragments.words.utils.choice;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.data.enums.Identifiable;
import net.heliantum.understandable.utils.ColorUtil;
import net.heliantum.understandable.utils.font.Font;

/**
 * Created by Marcin on 2017-05-07.
 */

public abstract class WordsBaseButton {

    protected static final int ITEM_CHOSEN = 255;
    protected static final int ITEM_NOT_CHOSEN = 110;

    protected ColorUtil colorUtil;

    protected Context context;
    protected ImageView image;
    protected TextView text;

    protected WordsDataParams dataParams;
    protected Identifiable enumType;

    public WordsBaseButton(Context context, WordsDataParams dataParams, Identifiable enumType) {
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