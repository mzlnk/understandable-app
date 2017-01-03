package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntites;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class CurrentlyChosenWordsData {

    private static final Random r = new Random();

    private static List<LanguageEntity> chosenWords = new ArrayList<>();

    private static List<LanguageCategory> categories = new ArrayList<>();
    private static List<LanguageType> types = new ArrayList<>(Arrays.asList(LanguageType.values()));

    private static LearningMode mode = LearningMode.REPETITION;
    private static LearningWay way = LearningWay.RANDOM;

    private static int size = 0;

    public static List<LanguageEntity> getChosenWords() {
        return CurrentlyChosenWordsData.chosenWords;
    }

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

    public static int getSize() {
        return CurrentlyChosenWordsData.size;
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

    public static void setSize(int size) {
        CurrentlyChosenWordsData.size = size;
    }

    public static void generateWordsList() {
        CurrentlyChosenWordsData.chosenWords = LanguageEntites.getSpecifiedEntities(categories, types);
    }

    public static void resizeWordsList() {
        List<LanguageEntity> all = new ArrayList<>(chosenWords);
        chosenWords.clear();
        for(int i = 0; i < size; i++) {
            LanguageEntity item = all.get(r.nextInt(all.size()));
            chosenWords.add(item);
            all.remove(item);
        }
    }

}
