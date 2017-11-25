package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningMode;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsModeButton extends CustomWordsBaseButton {

    private List<CustomWordsModeButton> allModes;
    private CustomWordsLearningMode mode;

    public CustomWordsModeButton(Context context, CustomWordsDataParams dataParams, CustomWordsLearningMode mode, List<CustomWordsModeButton> allModes) {
        super(context, dataParams, mode, false);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setImage();
    }

    private CustomWordsLearningMode getMode() {
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
                    for(CustomWordsModeButton m : allModes) {
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
