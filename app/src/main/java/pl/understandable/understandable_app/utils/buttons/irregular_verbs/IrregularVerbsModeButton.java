package pl.understandable.understandable_app.utils.buttons.irregular_verbs;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningMode;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;

import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public class IrregularVerbsModeButton extends IrregularVerbsBaseButton {

    private List<IrregularVerbsModeButton> allModes;
    private IrregularVerbsLearningMode mode;

    public IrregularVerbsModeButton(Context context, IrregularVerbsDataParams dataParams, IrregularVerbsLearningMode mode, List<IrregularVerbsModeButton> allModes) {
        super(context, dataParams, mode, false);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setImage();
    }

    private IrregularVerbsLearningMode getMode() {
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
                    for(IrregularVerbsModeButton m : allModes) {
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
