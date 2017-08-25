package pl.understandable.understandable_dev_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageSubcategory;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-25.
 */

public class WordsSubcategoryButton extends WordsBaseButton {

    private WordsLanguageSubcategory subcategory;

    public WordsSubcategoryButton(Context context, WordsDataParams dataParams, WordsLanguageSubcategory subcategory) {
        super(context, dataParams, subcategory, false);
        this.subcategory = subcategory;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(subcategory)) {
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
                    dataParams.addSubcategory(subcategory);
                } else {
                    image.setAlpha(ITEM_NOT_CHOSEN);
                    image.setImageResource(R.drawable.f_words_choice_unchecked);
                    setChecked(false);
                    dataParams.removeSubcategory(subcategory);
                }
            }
        });
    }

}
