package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningLanguageWay;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsLanguageWayButton extends CustomWordsBaseButton {

    private List<CustomWordsLanguageWayButton> allWays;
    private CustomWordsLearningLanguageWay way;

    public CustomWordsLanguageWayButton(Context context, CustomWordsDataParams dataParams, CustomWordsLearningLanguageWay way, List<CustomWordsLanguageWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    private CustomWordsLearningLanguageWay getWay() {
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
                    dataParams.setLanguageWay(way);
                    for(CustomWordsLanguageWayButton w : allWays) {
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
