package pl.understandable.understandable_dev_app.data.params;

import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningLevel;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningMode;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningWay;
import pl.understandable.understandable_dev_app.database.repository.WordEntityRepository;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class WordsDataParams extends BaseDataParams {

    public List<WordsLanguageCategory> categories = new ArrayList<>();
    public List<WordsLanguageType> types = new ArrayList<>();
    public List<WordsLearningLevel> levels = new ArrayList<>();
    public WordsLearningMode mode = WordsLearningMode.REPETITION;
    public WordsLearningWay way = WordsLearningWay.RANDOM;
    public int size = 1;

    public void setCategories(List<WordsLanguageCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(WordsLanguageCategory category) {
        if(!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(WordsLanguageCategory category) {
        if(categories.contains(category)) {
            categories.remove(category);
        }
    }

    public void setTypes(List<WordsLanguageType> types) {
        this.types = types;
    }

    public void addType(WordsLanguageType type) {
        if(!types.contains(type)) {
            types.add(type);
        }
    }

    public void removeType(WordsLanguageType type) {
        if(types.contains(type)) {
            types.remove(type);
        }
    }

    public void setLevels(List<WordsLearningLevel> levels) {
        this.levels = levels;
    }

    public void addLevel(WordsLearningLevel level) {
        if(!levels.contains(level)) {
            levels.add(level);
        }
    }

    public void removeLevel(WordsLearningLevel level) {
        if(levels.contains(level)) {
            levels.remove(level);
        }
    }

    public void setMode(WordsLearningMode mode) {
        this.mode = mode;
    }

    public void setWay(WordsLearningWay way) {
        this.way = way;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isChosen(WordsLanguageCategory category) {
        return categories.contains(category);
    }

    public boolean isChosen(WordsLanguageType type) {
        return types.contains(type);
    }

    public boolean isChosen(WordsLearningLevel level) {
        return levels.contains(level);
    }

    public boolean isChosen(WordsLearningWay way) {
        return this.way.equals(way);
    }

    public boolean isChosen(WordsLearningMode mode) {
        return this.mode.equals(mode);
    }

    public int getMaximumAvailableWordsAmount() {
        return WordEntityRepository.getSpecifiedEntities(categories, types, levels).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(WordsLanguageCategory category : categories) {
            sb.append(category.name()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        for(WordsLanguageType type : types) {
            sb.append(type.name()).append(",");
        }
        if(types.size() == 0) {
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        for(WordsLearningLevel level : levels) {
            sb.append(level.name()).append(",");
        }
        if(levels.size() == 0) {
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        sb.append(mode.name()).append(";");
        sb.append(way.name()).append(";");
        sb.append(size).append(";");
        System.out.println("dataParams: output: " + sb.toString());
        return sb.toString();
    }

    @Override
    public WordsDataParams fromString(String input) {
        System.out.println("dataParams: input: " + input);
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.addCategory(WordsLanguageCategory.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addCategory(WordsLanguageCategory.valueOf(str));
                }
                str = "";
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.addType(WordsLanguageType.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addType(WordsLanguageType.valueOf(str));
                }
                str = "";
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.addLevel(WordsLearningLevel.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addLevel(WordsLearningLevel.valueOf(str));
                }
                str = "";
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.setMode(WordsLearningMode.valueOf(str));
                }
                str = "";
                i++;
                break;
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.setWay(WordsLearningWay.valueOf(str));
                }
                str = "";
                i++;
                break;
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.setSize(NumberUtils.toInt(str, 1));
                }
                i++;
                break;
            } else {
                str += c;
            }
        }
        return this;
    }

}