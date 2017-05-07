package net.heliantum.ziedic.fragments.utils.wordschoice;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.ziedic.R;
import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.utils.SizeUtil;
import net.heliantum.ziedic.utils.font.Font;

/**
 * Created by Marcin on 2017-04-08.
 */

public class CategoryButton extends BaseButton {

    private LanguageCategory category;

    public CategoryButton(Context context, DataParams dataParams, LanguageCategory category) {
        super(context, dataParams, category);
        this.category = category;
        prepare();
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

}
