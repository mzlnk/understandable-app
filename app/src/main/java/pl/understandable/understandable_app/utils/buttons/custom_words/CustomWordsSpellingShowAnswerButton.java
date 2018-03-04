package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.fragments.custom_words.spelling.CustomWordsSpellingExampleFragment;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class CustomWordsSpellingShowAnswerButton extends BaseButton {

    private CustomWordsSpellingData spellingData = CustomWordsSpellingData.getSpellingData();

    public CustomWordsSpellingShowAnswerButton(Context context) {
        super(context, WordsSetOptions.SHOW_ANSWER, false);
        prepare();
    }

    @Override
    protected void setChoiceState() {
        image.setAlpha(ITEM_CHOSEN);
        this.setChecked(true);
        image.setImageResource(WordsSetOptions.SHOW_ANSWER.getResId());
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView answer = CustomWordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition);
                if(answer.getCurrentTextColor() == CustomWordsSpellingExampleFragment.hiddenWordColor) {
                    answer.setTextColor(CustomWordsSpellingExampleFragment.word2Color);
                } else {
                    answer.setTextColor(CustomWordsSpellingExampleFragment.hiddenWordColor);
                }
            }
        });
    }

}
