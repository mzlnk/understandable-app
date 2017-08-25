package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-24.
 */

public enum FamilySubcategory implements Identifiable {

    FAMILY_MEMBERS("członkowie rodziny"),
    FAMILY_EVENTS("wydarzenia rodzinne"),
    STAGES_OF_LIFE("etapy życia");

    private String name;

    private FamilySubcategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return R.drawable.f_words_choice_unchecked;
    }

}
