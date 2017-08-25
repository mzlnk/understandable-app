package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-25.
 */

public enum TravellingSubcategory implements Identifiable {

    MEANS_OF_TRANSPORT("środki transportu"),
    TRIPS("wycieczki"),
    BICYCLE("rower"),
    TRAIN_TRAVEL("podróż pociągiem"),
    AIR_TRAVEL("podróż samolotem"),
    CAR_TRAVEL("podróż samochodem"),
    ACCIDENTS("wypadki");

    private String name;

    private TravellingSubcategory(String name) {
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
