package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningOrderWay;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin Zielonka on 2018-02-28.
 */

public class CustomWordsOrderWayButton extends CustomWordsBaseButton {

    private CustomWordsLearningOrderWay way;

    public CustomWordsOrderWayButton(Context context, CustomWordsDataParams dataParams, CustomWordsLearningOrderWay way) {
        super(context, dataParams, way, false);
        this.way = way;
        prepare();
        setImage();
    }

    private CustomWordsLearningOrderWay getWay() {
        return way;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(way)) {
            image.setAlpha(ITEM_CHOSEN);
            this.setChecked(true);
        } else {
            image.setAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    dataParams.setOrderWay(way);
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    dataParams.setOrderWay(CustomWordsLearningOrderWay.NO_ORDER);
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(way.getResId());
    }

}
