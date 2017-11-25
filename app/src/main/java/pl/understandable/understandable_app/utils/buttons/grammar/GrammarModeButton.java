package pl.understandable.understandable_app.utils.buttons.grammar;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_app.data.enums.grammar.GrammarLearningMode;
import pl.understandable.understandable_app.data.params.GrammarDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-12.
 */

public class GrammarModeButton extends GrammarBaseButton {

    private List<GrammarModeButton> allModes;
    private GrammarLearningMode mode;

    public GrammarModeButton(Context context, GrammarDataParams dataParams, GrammarLearningMode mode, List<GrammarModeButton> allModes) {
        super(context, dataParams, mode, false);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setImage();
    }

    private GrammarLearningMode getMode() {
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
                    for(GrammarModeButton m : allModes) {
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
