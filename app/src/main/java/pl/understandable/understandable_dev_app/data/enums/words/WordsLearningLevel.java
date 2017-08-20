package pl.understandable.understandable_dev_app.data.enums.words;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-05.
 */

public enum WordsLearningLevel implements Identifiable {

    A1("początkujący - A1", R.drawable.f_words_choice_level_a1),
    A2("początkujący - A2", R.drawable.f_words_choice_level_a2),
    B1("średniozaawansowany - B1", R.drawable.f_words_choice_level_b1),
    B2("średniozaawansowany - B2", R.drawable.f_words_choice_level_b2),
    C1("zaawansowany - C1", R.drawable.f_words_choice_level_c1),
    C2("zaawansowany - C2", R.drawable.f_words_choice_level_c2);

    private String name;
    private int resId;

    private WordsLearningLevel(String name, int resId) {
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
