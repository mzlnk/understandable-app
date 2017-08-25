package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-25.
 */

public enum ScienceSubcategory implements Identifiable {

    MATHS("matematyka"),
    PHYSICS("fizyka i astronomia"),
    CHEMISTRY("chemia"),
    IT("technologia komputerowa"),
    SCIENCE_PHRASES("wyrażenia");


    private String name;

    private ScienceSubcategory(String name) {
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
