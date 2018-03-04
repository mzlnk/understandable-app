package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.enums.words.WordsLearningLanguageWay;
import pl.understandable.understandable_app.data.params.WordsDataParams;

import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-07.
 */

public class WordsLanguageWayButton extends WordsBaseButton {

    private List<WordsLanguageWayButton> allWays;
    private WordsLearningLanguageWay way;

    public WordsLanguageWayButton(Context context, WordsDataParams dataParams, WordsLearningLanguageWay way, List<WordsLanguageWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    private WordsLearningLanguageWay getWay() {
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
                    dataParams.setLaguageWay(way);
                    for(WordsLanguageWayButton w : allWays) {
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
