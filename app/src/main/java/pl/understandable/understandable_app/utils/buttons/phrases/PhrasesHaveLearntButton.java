package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.entities_data.CurrentWordData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.repository.PhraseEntityRepository;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-18.
 */

public class PhrasesHaveLearntButton extends BaseButton {

    private CurrentWordData<PhraseEntity> currentWordData;

    public PhrasesHaveLearntButton(Context context, CurrentWordData<PhraseEntity> currentWordData) {
        super(context, WordsSetOptions.HAVE_LEARNT, false);
        this.currentWordData = currentWordData;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(currentWordData.getCurrentWord().isLearnt()) {
            image.setAlpha(ITEM_CHOSEN);
            this.setChecked(true);
        } else {
            image.setAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
        image.setImageResource(WordsSetOptions.HAVE_LEARNT.getResId());
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    currentWordData.getCurrentWord().setLearnt(true);
                    PhraseEntityRepository.updateEntity(currentWordData.getCurrentWord());
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    currentWordData.getCurrentWord().setLearnt(false);
                    PhraseEntityRepository.updateEntity(currentWordData.getCurrentWord());
                }
            }
        });
    }

    public void updateChoiceState() {
        setChoiceState();
    }

}
