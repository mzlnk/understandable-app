package net.heliantum.understandable.fragments.words.utils.choice;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import net.heliantum.understandable.R;
import net.heliantum.understandable.data.enums.words.WordsLanguageType;
import net.heliantum.understandable.data.params.WordsDataParams;

/**
 * Created by Marcin on 2017-05-07.
 */

public class WordsTypeButton extends WordsBaseButton {

    private WordsLanguageType type;

    public WordsTypeButton(Context context, WordsDataParams dataParams, WordsLanguageType type) {
        super(context, dataParams, type);
        this.type = type;
        prepare();
        setSize();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(type)) {
            image.setImageAlpha(255);
        } else {
            image.setImageAlpha(150);
        }
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(image.getImageAlpha() == 150) {
                    image.setImageAlpha(255);
                    dataParams.addType(type);
                } else {
                    image.setImageAlpha(150);
                    dataParams.removeType(type);
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_type_icon_size);
        int textSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_type_icon_text);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setImage() {
        super.image.setImageResource(type.getResId());
    }

}