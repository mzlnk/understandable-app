package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.enums.words.WordsLearningOrderWay;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class WordsOrderWayButton extends WordsBaseButton {

    private WordsLearningOrderWay way;

    public WordsOrderWayButton(Context context, WordsDataParams dataParams, WordsLearningOrderWay way) {
        super(context, dataParams, way, false);
        this.way = way;
        prepare();
        setImage();
    }

    private WordsLearningOrderWay getWay() {
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
                    dataParams.setOrderWay(WordsLearningOrderWay.NO_ORDER);
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(way.getResId());
    }

}
