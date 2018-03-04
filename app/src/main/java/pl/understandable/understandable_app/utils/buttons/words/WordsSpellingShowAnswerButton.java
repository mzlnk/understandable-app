package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingExampleFragment;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class WordsSpellingShowAnswerButton extends BaseButton {

    private WordsSpellingData spellingData = WordsSpellingData.getSpellingData();

    public WordsSpellingShowAnswerButton(Context context) {
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
                TextView answer = WordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition);
                if(answer.getCurrentTextColor() == WordsSpellingExampleFragment.hiddenWordColor) {
                    answer.setTextColor(WordsSpellingExampleFragment.word2Color);
                } else {
                    answer.setTextColor(WordsSpellingExampleFragment.hiddenWordColor);
                }
            }
        });
    }

}
