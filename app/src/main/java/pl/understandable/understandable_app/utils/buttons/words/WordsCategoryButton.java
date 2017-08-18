package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.themes.ThemeType;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-04-08.
 */

public class WordsCategoryButton extends WordsBaseButton {

    private WordsLanguageCategory category;

    public WordsCategoryButton(Context context, WordsDataParams dataParams, WordsLanguageCategory category) {
        super(context, dataParams, category, false);
        this.category = category;
        prepare();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(category)) {
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
                    dataParams.addCategory(category);
                } else {
                    image.setImageAlpha(ITEM_NOT_CHOSEN);
                    setChecked(false);
                    dataParams.removeCategory(category);
                }
            }
        });
    }

    private void setImage() {
        super.image.setImageResource(category.getResId());
    }

}
