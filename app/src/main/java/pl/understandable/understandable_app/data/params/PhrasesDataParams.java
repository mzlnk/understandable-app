package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningMode;
import pl.understandable.understandable_app.data.enums.phrases.PhrasesLearningWay;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhrasesDataParams extends BaseDataParams {

    public PhrasesCategory category = PhrasesCategory.CONVERSATION;
    public PhrasesLearningMode mode = PhrasesLearningMode.REPETITION;
    public PhrasesLearningWay way = PhrasesLearningWay.RANDOM;

    public void setCategory(PhrasesCategory category) {
        this.category = category;
    }

    public void setMode(PhrasesLearningMode mode) {
        this.mode = mode;
    }

    public void setWay(PhrasesLearningWay way) {
        this.way = way;
    }

    public boolean isChosen(PhrasesLearningWay way) {
        return this.way.equals(way);
    }

    public boolean isChosen(PhrasesLearningMode mode) {
        return this.mode.equals(mode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(category.name()).append(";");
        sb.append(mode.name()).append(";");
        sb.append(way.name()).append(";");
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
                    this.setWay(PhrasesLearningWay.valueOf(str));
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
