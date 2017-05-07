package net.heliantum.ziedic.fragments.utils.wordschoice;

import android.content.Context;
import android.view.View;

import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LanguageType;

/**
 * Created by Marcin on 2017-05-07.
 */

public class TypeButton extends BaseButton {

    private LanguageType type;

    public TypeButton(Context context, DataParams dataParams, LanguageType type) {
        super(context, dataParams, type);
        this.type = type;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(type)) {
            image.setImageAlpha(255);
        } else {
            image.setImageAlpha(150);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == 150) {
                    image.setImageAlpha(255);
                    dataParams.addType(type);
                } else {
                    image.setImageAlpha(150);
                    dataParams.removeType(type);
                }
            }
        });
    }

}
