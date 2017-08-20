package pl.understandable.understandable_dev_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningLevel;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-06.
 */

public class WordsLevelButton extends WordsBaseButton {

    private WordsLearningLevel level;

    public WordsLevelButton(Context context, WordsDataParams dataParams, WordsLearningLevel level) {
        super(context, dataParams, level, false);
        this.level = level;
        prepare();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(level)) {
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
                    dataParams.addLevel(level);
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    dataParams.removeLevel(level);
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(level.getResId());
    }

}
