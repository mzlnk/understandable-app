package net.heliantum.ziedic.data;

import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.data.enums.LearningMode;
import net.heliantum.ziedic.data.enums.LearningWay;
import net.heliantum.ziedic.database.entity.LanguageEntites;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class ChosenWordsData {

    private static final Random r = new Random();

    private static List<LanguageEntity> allChosenWords = new ArrayList<>();

    private static List<LanguageCategory> categories = new ArrayList<>();
    private static List<LanguageType> types = new ArrayList<>(Arrays.asList(LanguageType.values()));

    private static LearningMode mode = LearningMode.REPETITION;
    private static LearningWay way = LearningWay.RANDOM;

    private static int size = 0;

    public static List<LanguageEntity> getAllChosenWords() {
        return ChosenWordsData.allChosenWords;
    }

    public static List<LanguageCategory> getCategories() {
        return ChosenWordsData.categories;
    }

    public static List<LanguageType> getTypes() {
        return ChosenWordsData.types;
    }

    public static LearningMode getMode() {
        return ChosenWordsData.mode;
    }

    public static LearningWay getWay() {
        return ChosenWordsData.way;
    }

    public static int getSize() {
        return ChosenWordsData.size;
    }

    public static void addCategory(LanguageCategory category) {
        if(!exists(category)) {
            ChosenWordsData.categories.add(category);
        }
    }

    public static void addType(LanguageType type) {
        if(!exists(type)) {
            ChosenWordsData.types.add(type);
        }
    }

    public static void removeCategory(LanguageCategory category) {
        if(exists(category)) {
            ChosenWordsData.categories.remove(category);
        }
    }

    public static void removeType(LanguageType type) {
        if(exists(type)) {
            ChosenWordsData.types.remove(type);
        }
    }

    public static void setMode(LearningMode mode) {
        ChosenWordsData.mode = mode;
    }

    public static void setWay(LearningWay way) {
        ChosenWordsData.way = way;
    }

    public static boolean exists(LanguageCategory category) {
        for(LanguageCategory c : ChosenWordsData.categories) {
            if(c.equals(category)) return true;
        }
        return false;
    }

    public static boolean exists(LanguageType type) {
        for(LanguageType t : ChosenWordsData.types) {
            if(t.equals(type)) return true;
        }
        return false;
    }

    public static void setSize(int size) {
        ChosenWordsData.size = size;
    }

    public static void generateWordsList() {
        ChosenWordsData.allChosenWords = LanguageEntites.getSpecifiedEntities(categories, types);
    }

    public static void resizeWordsList() {
        List<LanguageEntity> all = new ArrayList<>(allChosenWords);
        allChosenWords.clear();
        for(int i = 0; i < size; i++) {
            LanguageEntity item = all.get(r.nextInt(all.size()));
            allChosenWords.add(item);
            all.remove(item);
        }
    }

}
