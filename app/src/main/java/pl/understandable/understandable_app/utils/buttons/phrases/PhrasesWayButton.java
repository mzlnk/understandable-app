package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWay;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhrasesWayButton extends PhrasesBaseButton {

    private List<PhrasesWayButton> allWays;
    private PhrasesLearningWay way;

    public PhrasesWayButton(Context context, PhrasesDataParams dataParams, PhrasesLearningWay way, List<PhrasesWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    private PhrasesLearningWay getWay() {
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
                    dataParams.setWay(way);
                    for(PhrasesWayButton w : allWays) {
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
