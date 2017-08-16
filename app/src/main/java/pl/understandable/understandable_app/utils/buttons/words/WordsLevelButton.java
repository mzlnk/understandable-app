package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLevel;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-06.
 */

public class WordsLevelButton extends WordsBaseButton {

    private WordsLearningLevel level;

    public WordsLevelButton(Context context, WordsDataParams dataParams, WordsLearningLevel level) {
        super(context, dataParams, level, false);
        this.level = level;
        prepare();
        setSize();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(level)) {
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
                    dataParams.addLevel(level);
                } else {
                    image.setImageAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    dataParams.removeLevel(level);
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_choice_icon_size);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.f_choice_icon_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = super.text.getTextSize() * factor;
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
    }

    private void setImage() {
        super.image.setImageResource(level.getResId());
    }

}
