package pl.understandable.understandable_dev_app.data.enums.words;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_dev_app.R;
import pl.understandable.understandable_dev_app.data.enums.Identifiable;

import static pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageCategory.*;

/**
 * Created by Marcin Zielonka on 2017-08-25.
 */

public enum WordsLanguageSubcategory implements Identifiable {

    MUSIC("muzyka", CULTURE),
    LITERATURE("literatura", CULTURE),
    FILM("film", CULTURE),
    PAINTING("malarstwo", CULTURE),
    ART_DESCRIPTION("opis sztuki", CULTURE),
    THEATRE("teatr", CULTURE),
    PLANTS("roślinność", ENVIRONMENT),
    LANDSCAPE("elementy geograficzne", ENVIRONMENT),
    WEATHER("pogoda", ENVIRONMENT),
    ECOLOGY("ekologia", ENVIRONMENT),
    ANIMALS("zwierzęta", ENVIRONMENT),
    ANIMALS_SOUND("odgłosy zwierząt", ENVIRONMENT),
    FAMILY_MEMBERS("członkowie rodziny", FAMILY),
    FAMILY_EVENTS("wydarzenia rodzinne", FAMILY),
    STAGES_OF_LIFE("etapy życia", FAMILY),
    DAILY_ACTIVITIES("czynności codzienne", FAMILY),
    VEGETABLES("warzywa", FOOD),
    FRUIT("owoce", FOOD),
    MEAT("mięso", FOOD),
    OTHER_FOOD("pozostałe produkty", FOOD),
    DIET("dieta", FOOD),
    FOOD_DESCRIPTION("opis jedzenia", FOOD),
    COOKING("gotowanie", FOOD),
    DRINKS("napoje", FOOD),
    SEASONING("przyprawy", FOOD),
    FISH("ryby", FOOD),
    HUMAN_BODY("ciało człowieka", HEALTH),
    DISEASES("choroby", HEALTH),
    MEDICAL_EQUIPMENT("sprzęt medyczny", HEALTH),
    HEALTH_PHRASES("wyrażenia", HEALTH),
    HOUSE_TYPES("rodzaje domów", HOUSE),
    IN_HOUSE("wnętrze domu", HOUSE),
    HOUSE_DESCRIPTION("opis domu", HOUSE),
    NEIGHBOURHOOD("okolica", HOUSE),
    HOUSE_PHRASES("wyrażenia", HOUSE),
    OUTSIDE_HOUSE("na zewnątrz domu", HOUSE),
    COLOURS("kolory", HOUSE),
    HOUSE_ROOMS("pomieszczenia", HOUSE),
    BODY_PARTS("części ciała", HUMAN),
    APPEARANCE("wygląd zewnętrzny", HUMAN),
    CLOTHES("ubrania", HUMAN),
    PERSONALITY("osobowość", HUMAN),
    FEELING("uczucia", HUMAN),
    PERSONAL_INFO("dane osobowe", HUMAN),
    HUMAN_PHRASES("wyrażenia", HUMAN),
    POLITICS("władza i polityka", NATION),
    LAW("prawo", NATION),
    ORGANISATIONS("organizacje", NATION),
    COUNTRIES("nazwy krajów", NATION),
    SCHOOL_TYPES("rodzaje szkół", SCHOOL),
    SCHOOL_SUBJECTS("przedmioty szkolne", SCHOOL),
    EDUCATION_SYSTEM("system edukacji", SCHOOL),
    UNIVERSITY("na uniwersytecie", SCHOOL),
    SCHOOL_EQUIPMENT("wyposażenie szkolne", SCHOOL),
    SCHOOL_PHRASES("wyrażenia", SCHOOL),
    MATHS("matematyka", SCIENCE),
    PHYSICS("fizyka i astronomia", SCIENCE),
    CHEMISTRY("chemia", SCIENCE),
    IT("technologia komputerowa", SCIENCE),
    SCIENCE_PHRASES("wyrażenia", SCIENCE),
    SHOP_TYPES("typy sklepów", SHOPPING),
    IN_BANK("w banku", SHOPPING),
    IN_SHOP("w sklepie", SHOPPING),
    SHOPPING_PHRASES("wyrażenia", SHOPPING),
    RELATIONSHIP("stosunki", SOCIAL_LIFE),
    FREE_TIME("czas wolny", SOCIAL_LIFE),
    LIFESTYLE("styl życia", SOCIAL_LIFE),
    SOCIAL_LIFE_PHRASES("wyrażenia", SOCIAL_LIFE),
    IN_THE_CITY("w mieście", SOCIAL_LIFE),
    CRIMES("przestępstwa", SOCIETY),
    SOCIAL_PROBLEMS("problemy społeczne", SOCIETY),
    ECONOMY("ekonomia", SOCIETY),
    SPORT_TYPES("rodzaje sportów", SPORT),
    SPORT_EQUIPMENT("sprzęt sportowy", SPORT),
    SPORT_PEOPLE("osoby zw. ze sportem", SPORT),
    SPORT_PHRASES("wyrażenia", SPORT),
    MEANS_OF_TRANSPORT("środki transportu", TRAVELLING),
    TRIPS("wycieczki", TRAVELLING),
    BICYCLE("podróż rowerem", TRAVELLING),
    TRAIN_TRAVEL("podróż pociągiem", TRAVELLING),
    AIR_TRAVEL("podróż samolotem", TRAVELLING),
    CAR_TRAVEL("podróż samochodem", TRAVELLING),
    ACCIDENTS("wypadki", TRAVELLING),
    JOBS("zawody", WORK),
    JOB_DESCRIPTION("opis zawodów", WORK),
    AT_WORK("w pracy", WORK),
    MONEY("zarobki i pieniądze", WORK);

    private String name;
    private WordsLanguageCategory category;

    private WordsLanguageSubcategory(String name, WordsLanguageCategory category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return R.drawable.f_words_choice_unchecked;
    }

    public WordsLanguageCategory getCategory() {
        return category;
    }

    public static List<WordsLanguageSubcategory> getSubcategories(WordsLanguageCategory category) {
        List<WordsLanguageSubcategory> result = new ArrayList<>();
        for(WordsLanguageSubcategory e : WordsLanguageSubcategory.values()) {
            if(e.getCategory().equals(category)) {
                result.add(e);
            }
        }
        return result;
    }

}
