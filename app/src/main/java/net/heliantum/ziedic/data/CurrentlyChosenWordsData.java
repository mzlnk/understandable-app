package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntites;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class CurrentlyChosenWordsData {

    private static List<LanguageCategory> categories = new ArrayList<>();
    private static List<LanguageType> types = new ArrayList<>(Arrays.asList(LanguageType.values()));

    private static LearningMode mode = LearningMode.REPETITION;
    private static LearningWay way = LearningWay.RANDOM;

    public static List<LanguageCategory> getCategories() {
        return CurrentlyChosenWordsData.categories;
    }

    public static List<LanguageType> getTypes() {
        return CurrentlyChosenWordsData.types;
    }

    public static LearningMode getMode() {
        return CurrentlyChosenWordsData.mode;
    }

    public static LearningWay getWay() {
        return CurrentlyChosenWordsData.way;
    }

    public static void addCategory(LanguageCategory category) {
        if(!exists(category)) {
            CurrentlyChosenWordsData.categories.add(category);
        }
    }

    public static void addType(LanguageType type) {
        if(!exists(type)) {
            CurrentlyChosenWordsData.types.add(type);
        }
    }

    public static void removeCategory(LanguageCategory category) {
        if(exists(category)) {
            CurrentlyChosenWordsData.categories.remove(category);
        }
    }

    public static void removeType(LanguageType type) {
        if(exists(type)) {
            CurrentlyChosenWordsData.types.remove(type);
        }
    }

    public static void setMode(LearningMode mode) {
        CurrentlyChosenWordsData.mode = mode;
    }

    public static void setWay(LearningWay way) {
        CurrentlyChosenWordsData.way = way;
    }

    public static List<LanguageEntity> getChosenWords() {
        return LanguageEntites.getSpecifiedEntities(categories, types);
    }

    public static boolean exists(LanguageCategory category) {
        for(LanguageCategory c : CurrentlyChosenWordsData.categories) {
            if(c.equals(category)) return true;
        }
        return false;
    }

    public static boolean exists(LanguageType type) {
        for(LanguageType t : CurrentlyChosenWordsData.types) {
            if(t.equals(type)) return true;
        }
        return false;
    }

}
