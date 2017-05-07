package net.heliantum.ziedic.fragments.utils.wordschoice;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.heliantum.ziedic.data.DataParams;
import net.heliantum.ziedic.data.enums.LearningWay;

import java.util.List;

/**
 * Created by Marcin on 2017-05-07.
 */

public class WayButton extends BaseButton {

    private List<WayButton> allWays;
    private LearningWay way;

    public WayButton(Context context, DataParams dataParams, LearningWay way, List<WayButton> allWays) {
        super(context, dataParams, way);
        this.way = way;
        this.allWays = allWays;
        prepare();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    private LearningWay getWay() {
        return way;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(way)) {
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
                    dataParams.setWay(way);
                    for(WayButton w : allWays) {
                        if(w.getWay().equals(way)) {
                            continue;
                        }
                        w.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                    }
                }
            }
        });
    }

}
