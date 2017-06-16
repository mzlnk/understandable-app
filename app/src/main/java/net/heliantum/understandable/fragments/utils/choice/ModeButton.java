package net.heliantum.understandable.fragments.utils.choice;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.understandable.data.DataParams;
import net.heliantum.understandable.data.enums.LearningMode;

import java.util.List;

/**
 * Created by Marcin on 2017-05-07.
 */

public class ModeButton extends BaseButton {

    private List<ModeButton> allModes;
    private LearningMode mode;

    public ModeButton(Context context, DataParams dataParams, LearningMode mode, List<ModeButton> allModes) {
        super(context, dataParams, mode);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    private LearningMode getMode() {
        return mode;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(mode)) {
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
                    dataParams.setMode(mode);
                    for(ModeButton m : allModes) {
                        if(m.getMode().equals(mode)) {
                            continue;
                        }
                        m.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                    }
                }
            }
        });
    }

}
