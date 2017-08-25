package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-24.
 */

public enum HouseSubcategory implements Identifiable {

    HOUSE_TYPES("rodzaje domów"),
    IN_HOUSE("wnętrze domu"),
    HOUSE_DESCRIPTION("opis domu"),
    NEIGHBOURHOOD("okolica"),
    HOUSE_PHRASES("wyrażenia");

    private String name;

    private HouseSubcategory(String name) {
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
