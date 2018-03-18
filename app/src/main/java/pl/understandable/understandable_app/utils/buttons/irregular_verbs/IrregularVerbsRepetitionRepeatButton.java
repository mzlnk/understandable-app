package pl.understandable.understandable_app.utils.buttons.irregular_verbs;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.entities_data.irregular_verbs_data.IrregularVerbsRepetitionData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class IrregularVerbsRepetitionRepeatButton extends BaseButton {

    private IrregularVerbsRepetitionData repetitionData = IrregularVerbsRepetitionData.getRepetitionData();

    public IrregularVerbsRepetitionRepeatButton(Context context) {
        super(context, WordsSetOptions.REPEAT, false);
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
                    repetitionData.getWordsToRepeat().add(repetitionData.currentWord);
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    repetitionData.getWordsToRepeat().remove(repetitionData.currentWord);
                }
            }
        });
    }

    public void updateChoiceState() {
        setChoiceState();
    }

    private boolean isCurrentWordInWordsToRepeat() {
        for(IrregularVerbEntity entity : repetitionData.getWordsToRepeat()) {
            if(entity.getId() == repetitionData.currentWord.getId()) {
                return true;
            }
        }
        return false;
    }

}
