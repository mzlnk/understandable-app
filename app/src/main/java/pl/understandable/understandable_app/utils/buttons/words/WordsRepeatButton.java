package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.entities_data.RepetitionData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class WordsRepeatButton extends BaseButton {

    private RepetitionData<WordEntity> repetitionData;

    public WordsRepeatButton(Context context, RepetitionData<WordEntity> repetitionData) {
        super(context, WordsSetOptions.REPEAT, false);
        this.repetitionData = repetitionData;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(isCurrentWordInWordsToRepeat()) {
            image.setAlpha(ITEM_CHOSEN);
            this.setChecked(true);
        } else {
            image.setAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
        image.setImageResource(WordsSetOptions.REPEAT.getResId());
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    repetitionData.getWordsToRepeat().remove(repetitionData.getCurrentWord());
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    repetitionData.getWordsToRepeat().add(repetitionData.getCurrentWord());
                }
            }
        });
    }

    public void updateChoiceState() {
        setChoiceState();
    }

    private boolean isCurrentWordInWordsToRepeat() {
        for(WordEntity entity : repetitionData.getWordsToRepeat()) {
            if(entity.getId() == repetitionData.getCurrentWord().getId()) {
                return true;
            }
        }
        return false;
    }

}
