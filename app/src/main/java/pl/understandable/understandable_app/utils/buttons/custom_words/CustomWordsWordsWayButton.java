package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin on 2018-02-28.
 */

public class CustomWordsWordsWayButton extends CustomWordsBaseButton {

    private List<CustomWordsWordsWayButton> allWays;
    private CustomWordsLearningWordsWay way;

    public CustomWordsWordsWayButton(Context context, CustomWordsDataParams dataParams, CustomWordsLearningWordsWay way, List<CustomWordsWordsWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    private CustomWordsLearningWordsWay getWay() {
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
                    dataParams.setWordsWay(way);
                    for(CustomWordsWordsWayButton w : allWays) {
                        if(w.getWay().equals(way)) {
                            continue;
                        }
                        w.getImage().setAlpha(ITEM_NOT_CHOSEN);
                        w.setChecked(false);
                    }
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(way.getResId());
    }

}
