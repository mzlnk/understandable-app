package net.heliantum.understandable.data.params;

import net.heliantum.understandable.data.enums.words.WordsLearningMode;
import net.heliantum.understandable.data.enums.words.WordsLearningWay;

/**
 * Created by Marcin on 2017-07-29.
 */

public class CustomWordsDataParams extends BaseDataParams {

    public WordsLearningMode mode = WordsLearningMode.REPETITION;
    public WordsLearningWay way = WordsLearningWay.RANDOM;

    public void setMode(WordsLearningMode mode) {
        this.mode = mode;
    }

    public void setWay(WordsLearningWay way) {
        this.way = way;
    }

    public boolean isChosen(WordsLearningWay way) {
        return this.way.equals(way);
    }

    public boolean isChosen(WordsLearningMode mode) {
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
                this.setMode(WordsLearningMode.valueOf(str));
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
                this.setWay(WordsLearningWay.valueOf(str));
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
