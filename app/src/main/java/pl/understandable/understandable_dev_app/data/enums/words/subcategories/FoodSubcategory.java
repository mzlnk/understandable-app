package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-24.
 */

public enum FoodSubcategory implements Identifiable {

    VEGETABLES("warzywa"),
    FRUIT("owoce"),
    MEAT("mięso"),
    OTHER_FOOD("pozostałe produkty"),
    DIET("dieta"),
    FOOD_DESCRIPTION("opis jedzenia"),
    COOKING("gotowanie");

    private String name;

    private FoodSubcategory(String name) {
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
