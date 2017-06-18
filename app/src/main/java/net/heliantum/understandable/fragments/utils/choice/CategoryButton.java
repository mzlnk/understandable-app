package net.heliantum.understandable.fragments.utils.choice;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.DataParams;
import net.heliantum.understandable.data.enums.LanguageCategory;

/**
 * Created by Marcin on 2017-04-08.
 */

public class CategoryButton extends BaseButton {

    private LanguageCategory category;

    public CategoryButton(Context context, DataParams dataParams, LanguageCategory category) {
        super(context, dataParams, category);
        this.category = category;
        prepare();
        setSize();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(category)) {
            image.setImageAlpha(ITEM_CHOSEN);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == ITEM_NOT_CHOSEN) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    dataParams.addCategory(category);
                } else {
                    image.setImageAlpha(ITEM_NOT_CHOSEN);
                    dataParams.removeCategory(category);
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_category_icon_size);
        int textSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_category_icon_text);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setImage() {
        super.image.setImageResource(category.getResId());
    }

}
