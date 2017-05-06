package net.heliantum.ziedic.data;

import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.data.enums.LearningMode;
import net.heliantum.ziedic.data.enums.LearningWay;
import net.heliantum.ziedic.database.repository.LanguageEntityRepository;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class DataParams {

    public List<LanguageCategory> categories = new ArrayList<>();
    public List<LanguageType> types = new ArrayList<>(Arrays.asList(LanguageType.values()));
    public LearningMode mode = LearningMode.REPETITION;
    public LearningWay way = LearningWay.RANDOM;
    public int size = 1;

    public void setCategories(List<LanguageCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(LanguageCategory category) {
        if(!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(LanguageCategory category) {
        if(categories.contains(category)) {
            categories.remove(category);
        }
    }

    public void setTypes(List<LanguageType> types) {
        this.types = types;
    }

    public void addType(LanguageType type) {
        if(!types.contains(type)) {
            types.add(type);
        }
    }

    public void removeType(LanguageType type) {
        if(types.contains(type)) {
            types.remove(type);
        }
    }

    public void setMode(LearningMode mode) {
        this.mode = mode;
    }

    public void setWay(LearningWay way) {
        this.way = way;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isChosen(LanguageCategory category) {
        return categories.contains(category);
    }

    public boolean isChosen(LanguageType type) {
        return types.contains(type);
    }

    public boolean isChosen(LearningWay way) {
        return this.way.equals(way);
    }

    public boolean isChosen(LearningMode mode) {
        return this.mode.equals(mode);
    }

    public int getMaximumAvailableWordsAmount() {
        return LanguageEntityRepository.getSpecifiedEntities(categories, types).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(LanguageCategory category : categories) {
            sb.append(category.name()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        for(LanguageType type : types) {
            sb.append(type.name()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        sb.append(mode.name()).append(";");
        sb.append(way.name()).append(";");
        sb.append(size).append(";");
        return sb.toString();
    }

    public static DataParams fromString(String input) {
        DataParams dataParams = new DataParams();
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                dataParams.addCategory(LanguageCategory.valueOf(str));
                str = "";
                break;
            }
            if(c != ',') {
                dataParams.addCategory(LanguageCategory.valueOf(str));
                str = "";
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                dataParams.addType(LanguageType.valueOf(str));
                str = "";
                break;
            }
            if(c == ',') {
                dataParams.addType(LanguageType.valueOf(str));
                str = "";
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                dataParams.setMode(LearningMode.valueOf(str));
                str = "";
                break;
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                dataParams.setWay(LearningWay.valueOf(str));
                str = "";
                break;
            } else {
                str += c;
            }
        }
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                dataParams.setSize(NumberUtils.toInt(str, 1));
                break;
            } else {
                str += c;
            }
        }
        return dataParams;
    }

}
