package net.heliantum.understandable.corrupted.data;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class BaseWordsData {

    /*todo: remove it
    private static final Random r = new Random();

    public static List<WordEntity> allChosenWords = new ArrayList<>();

    public static List<WordsLanguageCategory> categories = new ArrayList<>();
    public static List<WordsLanguageType> types = new ArrayList<>(Arrays.asList(WordsLanguageType.values()));
    public static WordsLearningMode mode = WordsLearningMode.REPETITION;
    public static WordsLearningWay way = WordsLearningWay.RANDOM;
    public static int size = 0;

    public static void addCategory(WordsLanguageCategory category) {
        if(!isChosen(category)) {
            BaseWordsData.categories.add(category);
        }
    }

    public static void addType(WordsLanguageType type) {
        if(!isChosen(type)) {
            BaseWordsData.types.add(type);
        }
    }

    public static void removeCategory(WordsLanguageCategory category) {
        if(isChosen(category)) {
            BaseWordsData.categories.remove(category);
        }
    }

    public static void removeType(WordsLanguageType type) {
        if(isChosen(type)) {
            BaseWordsData.types.remove(type);
        }
    }

    public static void setMode(WordsLearningMode mode) {
        BaseWordsData.mode = mode;
    }

    public static void setWay(WordsLearningWay way) {
        BaseWordsData.way = way;
    }

    public static boolean isChosen(WordsLanguageCategory category) {
        for(WordsLanguageCategory c : BaseWordsData.categories) {
            if(c.equals(category)) return true;
        }
        return false;
    }

    public static boolean isChosen(WordsLanguageType type) {
        for(WordsLanguageType t : BaseWordsData.types) {
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
        List<WordEntity> all = new ArrayList<>(allChosenWords);
        allChosenWords.clear();
        for(int i = 0; i < size; i++) {
            WordEntity item = all.get(r.nextInt(all.size()));
            allChosenWords.add(item);
            all.remove(item);
        }
    }
    */

}
