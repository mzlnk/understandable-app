package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningMode;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhrasesModeButton extends PhrasesBaseButton {

    private List<PhrasesModeButton> allModes;
    private PhrasesLearningMode mode;

    public PhrasesModeButton(Context context, PhrasesDataParams dataParams, PhrasesLearningMode mode, List<PhrasesModeButton> allModes) {
        super(context, dataParams, mode, false);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setImage();
    }

    private PhrasesLearningMode getMode() {
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
                    for(PhrasesModeButton m : allModes) {
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
