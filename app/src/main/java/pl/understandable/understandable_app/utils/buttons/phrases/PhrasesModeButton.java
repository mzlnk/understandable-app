package pl.understandable.understandable_app.utils.buttons.phrases;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningMode;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhrasesModeButton extends PhrasesBaseButton {

    private List<PhrasesModeButton> allModes;
    private PhrasesLearningMode mode;

    public PhrasesModeButton(Context context, PhrasesDataParams dataParams, PhrasesLearningMode mode, List<PhrasesModeButton> allModes) {
        super(context, dataParams, mode, false);
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

    private PhrasesLearningMode getMode() {
        return mode;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(mode)) {
            image.setImageAlpha(ITEM_CHOSEN);
            this.setChecked(true);
        } else {
            image.setImageAlpha(ITEM_NOT_CHOSEN);
            this.setChecked(false);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChecked()) {
                    image.setImageAlpha(ITEM_CHOSEN);
                    setChecked(true);
                    dataParams.setMode(mode);
                    for(PhrasesModeButton m : allModes) {
                        if(m.getMode().equals(mode)) {
                            continue;
                        }
                        m.getImage().setImageAlpha(ITEM_NOT_CHOSEN);
                        m.setChecked(false);
                    }
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_mode_icon_size);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.f_choice_icon_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = super.text.getTextSize() * factor;
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
    }

    private void setImage() {
        super.image.setImageResource(mode.getResId());
    }

}
