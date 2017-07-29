package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningMode;
import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWay;

/**
 * Created by Marcin on 2017-07-29.
 */

public class CustomWordsDataParams extends BaseDataParams {

    public CustomWordsLearningMode mode = CustomWordsLearningMode.REPETITION;
    public CustomWordsLearningWay way = CustomWordsLearningWay.RANDOM;

    public void setMode(CustomWordsLearningMode mode) {
        this.mode = mode;
    }

    public void setWay(CustomWordsLearningWay way) {
        this.way = way;
    }

    public boolean isChosen(CustomWordsLearningWay way) {
        return this.way.equals(way);
    }

    public boolean isChosen(CustomWordsLearningMode mode) {
        return this.mode.equals(mode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mode.name()).append(";");
        sb.append(way.name()).append(";");
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
                this.setWay(CustomWordsLearningWay.valueOf(str));
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
