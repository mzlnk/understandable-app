package pl.understandable.understandable_app.utils.buttons.custom_words;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.understandable.understandable_app.data.entities_data.custom_words_data.CustomWordsSpellingData;
import pl.understandable.understandable_app.data.enums.words_set.WordsSetOptions;
import pl.understandable.understandable_app.fragments.custom_words.spelling.CustomWordsSpellingExampleFragment;
import pl.understandable.understandable_app.utils.buttons.BaseButton;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public class CustomWordsSpellingCheckAnswerButton extends BaseButton {

    private CustomWordsSpellingData spellingData = CustomWordsSpellingData.getSpellingData();

    private EditText answerField;

    public CustomWordsSpellingCheckAnswerButton(Context context, EditText answerField) {
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
                String correct = (String) CustomWordsSpellingExampleFragment.answers.get(spellingData.currentWordPosition).getText();
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
