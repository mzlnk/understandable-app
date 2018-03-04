package pl.understandable.understandable_app.utils.buttons.words;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.understandable.understandable_app.data.entities_data.words_data.WordsSpellingData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.fragments.words.spelling.WordsSpellingExampleFragment;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class WordsSpellingCheckAnswerButton extends BaseButton{

    private WordsSpellingData spellingData = WordsSpellingData.getSpellingData();

    private EditText answerField;

    public WordsSpellingCheckAnswerButton(Context context, EditText answerField) {
        super(context, WordsSetOptions.CHECK_ANSWER, false);
        this.answerField = answerField;
        prepare();
    }

    @Override
    protected void setChoiceState() {
        image.setAlpha(ITEM_CHOSEN);
        this.setChecked(true);
        image.setImageResource(WordsSetOptions.CHECK_ANSWER.getResId());
    }

    @Override
    protected void setOnClickListener() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = answerField.getText().toString();
                String correct = (String) WordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition).getText();
                if(answer.equalsIgnoreCase(correct)) {
                    Toast.makeText(context, "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    spellingData.addToCorrectAnswers();
                } else {
                    Toast.makeText(context, "Niepoprawna odpowiedź", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
