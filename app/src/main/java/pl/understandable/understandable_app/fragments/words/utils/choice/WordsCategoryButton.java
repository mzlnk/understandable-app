package pl.understandable.understandable_app.fragments.words.utils.choice;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableRow;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.data.params.WordsDataParams;

/**
 * Created by Marcin on 2017-04-08.
 */

public class WordsCategoryButton extends WordsBaseButton {

    private WordsLanguageCategory category;

    public WordsCategoryButton(Context context, WordsDataParams dataParams, WordsLanguageCategory category) {
        super(context, dataParams, category);
        this.category = category;
        prepare();
        setSize();
        setImage();
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(category)) {
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
                    dataParams.addCategory(category);
                } else {
                    image.setImageAlpha(ITEM_NOT_CHOSEN);
                    dataParams.removeCategory(category);
                }
            }
        });
    }

    private void setSize() {
        int imageSize = (int) super.context.getResources().getDimension(R.dimen.f_words_choice_category_icon_size);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(imageSize, imageSize);
        super.image.setLayoutParams(layoutParams);
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.f_choice_icon_text_factor, outValue, true);
        float factor = outValue.getFloat();
        float textSizeInPixels = super.text.getTextSize() * factor;
        super.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPixels);
    }

    private void setImage() {
        super.image.setImageResource(category.getResId());
    }

}
