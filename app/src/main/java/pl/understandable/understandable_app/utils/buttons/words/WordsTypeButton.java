package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-05-07.
 */

public class WordsTypeButton extends WordsBaseButton {

    private WordsLanguageType type;

    public WordsTypeButton(Context context, WordsDataParams dataParams, WordsLanguageType type) {
        super(context, dataParams, type, false);
        this.type = type;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(type)) {
            image.setAlpha(ITEM_CHOSEN);
            image.setImageResource(R.drawable.f_words_choice_checked);
            this.setChecked(true);
        } else {
            image.setAlpha(ITEM_NOT_CHOSEN);
            image.setImageResource(R.drawable.f_words_choice_unchecked);
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
                    image.setImageResource(R.drawable.f_words_choice_checked);
                    setChecked(true);
                    dataParams.addType(type);
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    image.setImageResource(R.drawable.f_words_choice_unchecked);
                    setChecked(false);
                    dataParams.removeType(type);
                }
            }
        });
    }

}
