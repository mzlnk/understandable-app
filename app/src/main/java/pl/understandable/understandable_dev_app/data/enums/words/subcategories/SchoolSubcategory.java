package pl.understandable.understandable_dev_app.data.enums.words.subcategories;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2017-08-24.
 */

public enum SchoolSubcategory implements Identifiable {

    SCHOOL_TYPES("rodzaje szkół"),
    SCHOOL_SUBJECTS("przedmioty szkolne"),
    EDUCATION_SYSTEM("system edukacji"),
    UNIVERSITY("na uniwersytecie"),
    LEARNING("nauka"),
    SCHOOL_EQUIPMENT("wyposażenie szkolne"),
    SCHOOL_EXAMS("egzaminy"),
    SCHOOL_PHRASES("wyrażenia");

    private String name;

    private SchoolSubcategory(String name) {
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
