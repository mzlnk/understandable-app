package net.heliantum.understandable.fragments.irregular_verbs.utils.choice;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.irregular_verbs.IrregularVerbsLearningMode;
import net.heliantum.understandable.data.params.IrregularVerbsDataParams;

import java.util.List;

/**
 * Created by Marcin on 2017-07-08.
 */

public class IrregularVerbsModeButton extends IrregularVerbsBaseButton {

    private List<IrregularVerbsModeButton> allModes;
    private IrregularVerbsLearningMode mode;

    public IrregularVerbsModeButton(Context context, IrregularVerbsDataParams dataParams, IrregularVerbsLearningMode mode, List<IrregularVerbsModeButton> allModes) {
        super(context, dataParams, mode);
        this.mode = mode;
        this.allModes = allModes;
        prepare();
        setSize();
        setImage();
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getText() {
        return text;
    }

    private IrregularVerbsLearningMode getMode() {
        return mode;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(mode)) {
            image.setImageAlpha(ITEM_CHOSEN);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == ITEM_NOT_CHOSEN) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    dataParams.setMode(mode);
                    for(IrregularVerbsModeButton m : allModes) {
                        if(m.getMode().equals(mode)) {
                            continue;
                        }
                        m.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                    }
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_mode_icon_size);
        int textSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_mode_icon_text);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setImage() {
        super.image.setImageResource(mode.getResId());
    }
}
