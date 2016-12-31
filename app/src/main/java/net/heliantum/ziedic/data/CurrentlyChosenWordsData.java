package net.heliantum.ziedic.data;

import net.heliantum.ziedic.data.LearningWay;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class CurrentlyChosenWordsData {

    public static List<LanguageEntity> chosenWords = new ArrayList<>();
    public static LearningWay way = LearningWay.RANDOM;

}
