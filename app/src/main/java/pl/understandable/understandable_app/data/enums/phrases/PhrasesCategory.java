package pl.understandable.understandable_app.data.enums.phrases;

/**
 * Created by Marcin on 2017-08-08.
 */

public enum PhrasesCategory {

    CONVERSATION("uczestniczenie w rozmowie"),
    OPINION("wyrazanie opinii"),
    ADVICE("udzielanie rady"),
    DESCRIPTION("opisywanie ilustracji"),
    EVENT("relacjonowanie zdarzeń"),
    CHOICE("dokonywanie wyboru"),
    COMPLAINT("skargi i zażalenia"),
    DESTINATION("pytanie o drogę"),
    SHOP("w sklepie"),
    RESTOURANT("w restouracji");

    private String name;

    private PhrasesCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
