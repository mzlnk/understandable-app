package pl.understandable.understandable_app.corrupted.data;

/**
 * Created by Lotos_ on 2016-11-12.
 */


public class BaseWordsData {

    /*todo: remove it
    private static final Random r = new Random();

    public static List<WordEntity> allChosenWords = new ArrayList<>();

    public static List<WordsCategory> categories = new ArrayList<>();
    public static List<WordsType> types = new ArrayList<>(Arrays.asList(WordsType.values()));
    public static WordsLearningMode mode = WordsLearningMode.REPETITION;
    public static WordsLearningLanguageWay languageWay = WordsLearningLanguageWay.RANDOM;
    public static int size = 0;

    public static void addCategory(WordsCategory category) {
        if(!isChosen(category)) {
            BaseWordsData.categories.add(category);
        }
    }

    public static void addType(WordsType type) {
        if(!isChosen(type)) {
            BaseWordsData.types.add(type);
        }
    }

    public static void removeCategory(WordsCategory category) {
        if(isChosen(category)) {
            BaseWordsData.categories.remove(category);
        }
    }

    public static void removeType(WordsType type) {
        if(isChosen(type)) {
            BaseWordsData.types.remove(type);
        }
    }

    public static void setMode(WordsLearningMode mode) {
        BaseWordsData.mode = mode;
    }

    public static void setLanguageWay(WordsLearningLanguageWay languageWay) {
        BaseWordsData.languageWay = languageWay;
    }

    public static boolean isChosen(WordsCategory category) {
        for(WordsCategory c : BaseWordsData.categories) {
            if(c.equals(category)) return true;
        }
        return false;
    }

    public static boolean isChosen(WordsType type) {
        for(WordsType t : BaseWordsData.types) {
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
