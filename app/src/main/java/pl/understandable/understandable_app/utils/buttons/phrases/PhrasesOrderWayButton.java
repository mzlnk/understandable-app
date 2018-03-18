package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningOrderWay;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class PhrasesOrderWayButton extends PhrasesBaseButton {

    private PhrasesLearningOrderWay way;

    public PhrasesOrderWayButton(Context context, PhrasesDataParams dataParams, PhrasesLearningOrderWay way) {
        super(context, dataParams, way, false);
        this.way = way;
        prepare();
        setImage();
    }

    private PhrasesLearningOrderWay getWay() {
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
                    dataParams.setOrderWay(PhrasesLearningOrderWay.NO_ORDER);
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(way.getResId());
    }

}
