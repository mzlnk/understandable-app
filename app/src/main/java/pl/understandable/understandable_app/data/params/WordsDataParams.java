package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.words.WordsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.enums.words.WordsSubcategory;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.words.WordsLearningMethod;
import pl.understandable.understandable_app.data.enums.words.WordsLearningMode;
import pl.understandable.understandable_app.data.enums.words.WordsCategory;
import pl.understandable.understandable_app.data.enums.words.WordsType;
import pl.understandable.understandable_app.database.repository.WordEntityRepository;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class WordsDataParams extends BaseDataParams {

    public List<WordsCategory> categories = new ArrayList<>();
    public List<WordsType> types = new ArrayList<>();
    public List<WordsSubcategory> subcategories = new ArrayList<>();
    public WordsLearningMethod method = WordsLearningMethod.ALL;
    public WordsLearningMode mode = WordsLearningMode.REPETITION;
    public WordsLearningLanguageWay laguageWay = WordsLearningLanguageWay.RANDOM;
    public WordsLearningWordsWay wordsWay = WordsLearningWordsWay.ALL_WORDS;
    public WordsLearningOrderWay orderWay = WordsLearningOrderWay.NO_ORDER;
    public int size = 1;

    public void setCategories(List<WordsCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(WordsCategory category) {
        if(!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(WordsCategory category) {
        if(categories.contains(category)) {
            categories.remove(category);
        }
    }

    public void setTypes(List<WordsType> types) {
        this.types = types;
    }

    public void addType(WordsType type) {
        if(!types.contains(type)) {
            types.add(type);
        }
    }

    public void removeType(WordsType type) {
        if(types.contains(type)) {
            types.remove(type);
        }
    }

    public void setSubcategories(List<WordsSubcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public void addSubcategory(WordsSubcategory subcategory) {
        if(!subcategories.contains(subcategory)) {
            subcategories.add(subcategory);
        }
    }

    public void removeSubcategory(WordsSubcategory subcategory) {
        if(subcategories.contains(subcategory)) {
            subcategories.remove(subcategory);
        }
    }

    public void setMethod(WordsLearningMethod method) {
        this.method = method;
    }

    public void setMode(WordsLearningMode mode) {
        this.mode = mode;
    }

    public void setLaguageWay(WordsLearningLanguageWay laguageWay) {
        this.laguageWay = laguageWay;
    }

    public void setWordsWay(WordsLearningWordsWay wordsWay) {
        this.wordsWay = wordsWay;
    }

    public void setOrderWay(WordsLearningOrderWay orderWay) {
        this.orderWay = orderWay;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isChosen(WordsCategory category) {
        return categories.contains(category);
    }

    public boolean isChosen(WordsType type) {
        return types.contains(type);
    }

    public boolean isChosen(WordsSubcategory subcategory) {
        return subcategories.contains(subcategory);
    }

    public boolean isChosen(WordsLearningMethod method) {
        return this.method.equals(method);
    }

    public boolean isChosen(WordsLearningLanguageWay laguageWay) {
        return this.laguageWay.equals(laguageWay);
    }

    public boolean isChosen(WordsLearningWordsWay wordsWay) {
        return this.wordsWay.equals(wordsWay);
    }

    public boolean isChosen(WordsLearningOrderWay orderWay) {
        return this.orderWay.equals(orderWay);
    }

    public boolean isChosen(WordsLearningMode mode) {
        return this.mode.equals(mode);
    }

    public int getMaximumAvailableWordsAmount() {
        switch(method) {
            case SUBCATEGORIES:
                return WordEntityRepository.getSpecifiedEntitiesBySubcategory(this).size();
            case TYPES:
                return WordEntityRepository.getSpecifiedEntitiesByType(this).size();
            case ALL:
                return WordEntityRepository.getSpecifiedEntitiesByCategory(this).size();
            default:
                return WordEntityRepository.getSpecifiedEntitiesByCategory(this).size();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(WordsCategory category : categories) {
            sb.append(category.name()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        for(WordsType type : types) {
            sb.append(type.name()).append(",");
        }
        if(types.size() == 0) {
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        for(WordsSubcategory subcategory : subcategories) {
            sb.append(subcategory.name()).append(",");
        }
        if(subcategories.size() == 0) {
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        sb.append(method.name()).append(";");
        sb.append(mode.name()).append(";");
        sb.append(laguageWay.name()).append(";");
        sb.append(wordsWay.name()).append(";");
        sb.append(orderWay.name()).append(";");
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
                    this.addCategory(WordsCategory.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addCategory(WordsCategory.valueOf(str));
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
                    this.addType(WordsType.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addType(WordsType.valueOf(str));
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
                    this.addSubcategory(WordsSubcategory.valueOf(str));
                }
                str = "";
                i++;
                break;
            }
            if(c == ',') {
                if(!str.isEmpty()) {
                    this.addSubcategory(WordsSubcategory.valueOf(str));
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
                    this.setMethod(WordsLearningMethod.valueOf(str));
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
                    this.setLaguageWay(WordsLearningLanguageWay.valueOf(str));
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
                    this.setWordsWay(WordsLearningWordsWay.valueOf(str));
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
                    this.setOrderWay(WordsLearningOrderWay.valueOf(str));
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
