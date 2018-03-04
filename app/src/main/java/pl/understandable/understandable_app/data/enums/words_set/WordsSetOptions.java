package pl.understandable.understandable_app.data.enums.words_set;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public enum WordsSetOptions implements Identifiable {

    REPEAT("powtórz",R.drawable.f_words_set_repeat),
    PRONUNCIATION("wymowa", R.drawable.f_words_set_pronunciation),
    HAVE_LEARNT("umiem to!", R.drawable.f_words_set_have_learnt),
    SHOW_ANSWER("pokaż odpowiedź", R.drawable.f_words_set_correct_answer),
    CHECK_ANSWER("sprawdź", R.drawable.f_words_set_check_answer);

    private String name;
    private int resId;

    private WordsSetOptions(String name, int resId) {
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
