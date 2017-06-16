package net.heliantum.understandable.corrupted.data;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class BaseWordsData {

    /*todo: remove it
    private static final Random r = new Random();

    public static List<LanguageEntity> allChosenWords = new ArrayList<>();

    public static List<LanguageCategory> categories = new ArrayList<>();
    public static List<LanguageType> types = new ArrayList<>(Arrays.asList(LanguageType.values()));
    public static LearningMode mode = LearningMode.REPETITION;
    public static LearningWay way = LearningWay.RANDOM;
    public static int size = 0;

    public static void addCategory(LanguageCategory category) {
        if(!isChosen(category)) {
            BaseWordsData.categories.add(category);
        }
    }

    public static void addType(LanguageType type) {
        if(!isChosen(type)) {
            BaseWordsData.types.add(type);
        }
    }

    public static void removeCategory(LanguageCategory category) {
        if(isChosen(category)) {
            BaseWordsData.categories.remove(category);
        }
    }

    public static void removeType(LanguageType type) {
        if(isChosen(type)) {
            BaseWordsData.types.remove(type);
        }
    }

    public static void setMode(LearningMode mode) {
        BaseWordsData.mode = mode;
    }

    public static void setWay(LearningWay way) {
        BaseWordsData.way = way;
    }

    public static boolean isChosen(LanguageCategory category) {
        for(LanguageCategory c : BaseWordsData.categories) {
            if(c.equals(category)) return true;
        }
        return false;
    }

    public static boolean isChosen(LanguageType type) {
        for(LanguageType t : BaseWordsData.types) {
            if(t.equals(type)) return true;
        }
        return false;
    }

    public static void setSize(int size) {
        BaseWordsData.size = size;
    }

    public static void generateWordsList() {
        BaseWordsData.allChosenWords = LanguageEntites.getSpecifiedEntities(categories, types);
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
    */

}
