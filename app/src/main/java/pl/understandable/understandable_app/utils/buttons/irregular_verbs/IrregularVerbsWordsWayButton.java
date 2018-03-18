package pl.understandable.understandable_app.utils.buttons.irregular_verbs;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningWordsWay;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class IrregularVerbsWordsWayButton extends IrregularVerbsBaseButton {

    private List<IrregularVerbsWordsWayButton> allWays;
    private IrregularVerbsLearningWordsWay way;

    public IrregularVerbsWordsWayButton(Context context, IrregularVerbsDataParams dataParams, IrregularVerbsLearningWordsWay way, List<IrregularVerbsWordsWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    private IrregularVerbsLearningWordsWay getWay() {
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
                    for(IrregularVerbsWordsWayButton w : allWays) {
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
