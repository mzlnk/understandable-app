package pl.understandable.understandable_app.data.enums.irregular_verbs;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-07-08.
 */

public enum IrregularVerbsLearningMode implements Identifiable {

    REPETITION("powtarzanie", R.drawable.f_words_choice_mode_repetition),
    LIST("lista",R.drawable.f_words_choice_mode_list);

    private String name;
    private int resId;

    private IrregularVerbsLearningMode(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return resId;
    }

}
