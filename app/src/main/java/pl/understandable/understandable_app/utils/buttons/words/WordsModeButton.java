package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLearningMode;
import pl.understandable.understandable_app.data.params.WordsDataParams;

import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-07.
 */

public class WordsModeButton extends WordsBaseButton {

    private List<WordsModeButton> allModes;
    private WordsLearningMode mode;

    public WordsModeButton(Context context, WordsDataParams dataParams, WordsLearningMode mode, List<WordsModeButton> allModes) {
        super(context, dataParams, mode, false);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setImage();
    }

    private WordsLearningMode getMode() {
        return mode;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(mode)) {
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
                    dataParams.setMode(mode);
                    for(WordsModeButton m : allModes) {
                        if(m.getMode().equals(mode)) {
                            continue;
                        }
                        m.getImage().setAlpha(ITEM_NOT_CHOSEN);
                        m.setChecked(false);
                    }
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(mode.getResId());
    }

}
