package net.heliantum.understandable.data.enums;

import net.heliantum.understandable.R;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public enum LearningWay implements Identifiable {

    POLISH_TO_ENGLISH(0, "z poskiego na angielski", R.drawable.f_words_choice_way_polish_to_english),
    ENGLISH_TO_POLISH(1, "z angielskiego na polski", R.drawable.f_words_choice_way_english_to_polish),
    RANDOM(2, "losowo", R.drawable.f_words_choice_way_random);

    private int id;
    private String name;
    private int resId;

    private LearningWay(int id, String name, int resId) {
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
