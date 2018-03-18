package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningMode;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningLanguageWay;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningOrderWay;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWordsWay;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhrasesDataParams extends BaseDataParams {

    public PhrasesCategory category = PhrasesCategory.CONVERSATION;
    public PhrasesLearningMode mode = PhrasesLearningMode.REPETITION;
    public PhrasesLearningLanguageWay languageWay = PhrasesLearningLanguageWay.RANDOM;
    public PhrasesLearningWordsWay wordsWay = PhrasesLearningWordsWay.ALL_WORDS;
    public PhrasesLearningOrderWay orderWay = PhrasesLearningOrderWay.NO_ORDER;

    public void setCategory(PhrasesCategory category) {
        this.category = category;
    }

    public void setMode(PhrasesLearningMode mode) {
        this.mode = mode;
    }

    public void setLanguageWay(PhrasesLearningLanguageWay languageWay) {
        this.languageWay = languageWay;
    }

    public void setWordsWay(PhrasesLearningWordsWay wordsWay) {
        this.wordsWay = wordsWay;
    }

    public void setOrderWay(PhrasesLearningOrderWay orderWay) {
        this.orderWay = orderWay;
    }

    public boolean isChosen(PhrasesLearningLanguageWay way) {
        return this.languageWay.equals(way);
    }

    public boolean isChosen(PhrasesLearningMode mode) {
        return this.mode.equals(mode);
    }

    public boolean isChosen(PhrasesLearningWordsWay way) {
        return this.wordsWay.equals(way);
    }

    public boolean isChosen(PhrasesLearningOrderWay way) {
        return this.orderWay.equals(way);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(category.name()).append(";");
        sb.append(mode.name()).append(";");
        sb.append(languageWay.name()).append(";");
        sb.append(wordsWay.name()).append(";");
        sb.append(orderWay.name()).append(";");
        System.out.println("dataParams: output: " + sb.toString());
        return sb.toString();
    }

    @Override
    public PhrasesDataParams fromString(String input) {
        System.out.println("dataParams: input: " + input);
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                if(!str.isEmpty()) {
                    this.setCategory(PhrasesCategory.valueOf(str));
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
                    this.setMode(PhrasesLearningMode.valueOf(str));
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
                    this.setLanguageWay(PhrasesLearningLanguageWay.valueOf(str));
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
                    this.setWordsWay(PhrasesLearningWordsWay.valueOf(str));
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
                    this.setOrderWay(PhrasesLearningOrderWay.valueOf(str));
                }
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
