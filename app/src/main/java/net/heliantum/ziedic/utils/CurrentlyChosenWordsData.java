package net.heliantum.ziedic.utils;

import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class CurrentlyChosenWordsData {

    public static List<LanguageEntity> chosenWords = new ArrayList<>();
    public static LanguageLearningDirection direction = LanguageLearningDirection.RANDOM;

}
