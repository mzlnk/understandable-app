package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningMode;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWordsWay;
import pl.understandable.understandable_app.database.repository.CustomWordEntityRepository;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsDataParams extends BaseDataParams {

    public CustomWordsLearningMode mode = CustomWordsLearningMode.REPETITION;
    public CustomWordsLearningLanguageWay languageWay = CustomWordsLearningLanguageWay.RANDOM;
    public CustomWordsLearningWordsWay wordsWay = CustomWordsLearningWordsWay.ALL_WORDS;
    public CustomWordsLearningOrderWay orderWay = CustomWordsLearningOrderWay.NO_ORDER;
    public String id = "";

    public CustomWordsDataParams(String id) {
        this.id = id;
    }

    public void setMode(CustomWordsLearningMode mode) {
        this.mode = mode;
    }

    public void setLanguageWay(CustomWordsLearningLanguageWay languageWay) {
        this.languageWay = languageWay;
    }

    public void setWordsWay(CustomWordsLearningWordsWay wordsWay) {
        this.wordsWay = wordsWay;
    }

    public void setOrderWay(CustomWordsLearningOrderWay orderWay) {
        this.orderWay = orderWay;
    }

    public boolean isChosen(CustomWordsLearningLanguageWay languageWay) {
        return this.languageWay.equals(languageWay);
    }

    public boolean isChosen(CustomWordsLearningMode mode) {
        return this.mode.equals(mode);
    }

    public boolean isChosen(CustomWordsLearningWordsWay wordsWay) {
        return this.wordsWay.equals(wordsWay);
    }

    public boolean isChosen(CustomWordsLearningOrderWay orderWay) {
        return this.orderWay.equals(orderWay);
    }

    public int getMaximumAvailableWordsAmount() {
        return CustomWordEntityRepository.getEntities(wordsWay).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mode.name()).append(";");
        sb.append(languageWay.name()).append(";");
        sb.append(wordsWay.name()).append(";");
        sb.append(orderWay.name()).append(";");
        System.out.println("dataParams: output: " + sb.toString());
        return sb.toString();
    }

    @Override
    public CustomWordsDataParams fromString(String input) {
        System.out.println("dataParams: input: " + input);
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                this.setMode(CustomWordsLearningMode.valueOf(str));
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
                this.setLanguageWay(CustomWordsLearningLanguageWay.valueOf(str));
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
                this.setWordsWay(CustomWordsLearningWordsWay.valueOf(str));
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
                this.setOrderWay(CustomWordsLearningOrderWay.valueOf(str));
                str = "";
                i++;
                break;
            } else {
                str += c;
            }
        }
        return this;
    }

}
