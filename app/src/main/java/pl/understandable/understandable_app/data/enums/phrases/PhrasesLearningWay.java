package pl.understandable.understandable_app.data.enums.phrases;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin on 2017-08-11.
 */

public enum PhrasesLearningWay implements Identifiable {

    POLISH_TO_ENGLISH(0, "z poskiego na angielski", R.drawable.f_words_choice_way_polish_to_english),
    ENGLISH_TO_POLISH(1, "z angielskiego na polski", R.drawable.f_words_choice_way_english_to_polish),
    RANDOM(2, "losowo", R.drawable.f_words_choice_way_random);

    private int id;
    private String name;
    private int resId;

    private PhrasesLearningWay(int id, String name, int resId) {
        this.id = id;
        this.name = name;
        this.resId = resId;
    }

    public int getId() {
        return id;
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
