package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLearningWay;
import pl.understandable.understandable_app.data.params.WordsDataParams;

import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-07.
 */

public class WordsWayButton extends WordsBaseButton {

    private List<WordsWayButton> allWays;
    private WordsLearningWay way;

    public WordsWayButton(Context context, WordsDataParams dataParams, WordsLearningWay way, List<WordsWayButton> allWays) {
        super(context, dataParams, way, false);
        this.way = way;
        this.allWays = allWays;
        prepare();
        setImage();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    private WordsLearningWay getWay() {
        return way;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(way)) {
            image.setImageAlpha(ITEM_CHOSEN);
            this.setChecked(true);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    dataParams.setWay(way);
                    for(WordsWayButton w : allWays) {
                        if(w.getWay().equals(way)) {
                            continue;
                        }
                        w.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
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
