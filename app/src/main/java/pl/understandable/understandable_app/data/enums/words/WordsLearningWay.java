package pl.understandable.understandable_app.data.enums.words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;
import pl.understandable.understandable_app.data.enums.LearningWay;

/**
 * Created by Marcin Zielonka on 2016-11-12.
 */


public enum WordsLearningWay implements Identifiable, LearningWay {

    POLISH_TO_ENGLISH(0, "z polskiego na angielski", R.drawable.f_words_choice_way_polish_to_english),
    ENGLISH_TO_POLISH(1, "z angielskiego na polski", R.drawable.f_words_choice_way_english_to_polish),
    RANDOM(2, "losowo", R.drawable.f_words_choice_way_random);

    private int id;
    private String name;
    private int resId;

    private WordsLearningWay(int id, String name, int resId) {
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

    @Override
    public int getWay() {
        return id;
    }

}
