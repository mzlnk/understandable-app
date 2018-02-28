package pl.understandable.understandable_app.data.enums.words;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2018-02-28.
 */

public enum WordsLearningOrderWay implements Identifiable {

    ALPHABETICAL("ułóż alfabetycznie", R.drawable.f_words_choice_way_alphabetical_order),
    NO_ORDER("", 0);

    private String name;
    private int resId;

    private WordsLearningOrderWay(String name, int resId) {
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
