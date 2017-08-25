package pl.understandable.understandable_dev_app.utils.buttons.words;

import android.content.Context;
import android.view.View;

import java.util.List;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningMethod;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;

/**
 * Created by Marcin Zielonka on 2017-08-25.
 */

public class WordsMethodButton extends WordsBaseButton {

    private List<WordsMethodButton> allMethods;
    private WordsLearningMethod method;

    public WordsMethodButton(Context context, WordsDataParams dataParams, WordsLearningMethod method, List<WordsMethodButton> allMethods) {
        super(context, dataParams, method, false);
        this.method = method;
        this.allMethods = allMethods;
        prepare();
    }

    private WordsLearningMethod getMethod() {
        return method;
    }

    @Override
    protected void setChoiceState() {
        if(dataParams.isChosen(method)) {
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
                    dataParams.setMethod(method);
                    for(WordsMethodButton m : allMethods) {
                        if(m.getMethod().equals(method)) {
                            continue;
                        }
                        m.getImage().setAlpha(ITEM_NOT_CHOSEN);
                        m.getImage().setImageResource(R.drawable.f_words_choice_unchecked);
                        m.setChecked(false);
                    }
                }
            }
        });
    }

}
