package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningMode;
import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningOrderWay;
import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningWordsWay;
import pl.understandable.understandable_app.database.repository.IrregularVerbEntityRepository;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public class IrregularVerbsDataParams extends BaseDataParams {

    public IrregularVerbsLearningMode mode = IrregularVerbsLearningMode.REPETITION;
    public IrregularVerbsLearningOrderWay orderWay = IrregularVerbsLearningOrderWay.NO_ORDER;
    public IrregularVerbsLearningWordsWay wordsWay = IrregularVerbsLearningWordsWay.ALL_WORDS;

    public int size = 1;

    public void setMode(IrregularVerbsLearningMode mode) {
        this.mode = mode;
    }

    public void setOrderWay(IrregularVerbsLearningOrderWay orderWay) {
        this.orderWay = orderWay;
    }

    public void setWordsWay(IrregularVerbsLearningWordsWay wordsWay) {
        this.wordsWay = wordsWay;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isChosen(IrregularVerbsLearningMode mode) {
        return this.mode.equals(mode);
    }

    public boolean isChosen(IrregularVerbsLearningOrderWay orderWay) {
        return this.orderWay.equals(orderWay);
    }

    public boolean isChosen(IrregularVerbsLearningWordsWay wordsWay) {
        return this.wordsWay.equals(wordsWay);
    }

    public int getMaximumAvailableWordsAmount() {
        return IrregularVerbEntityRepository.getAllEntities(this).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mode.name()).append(";");
        sb.append(wordsWay.name()).append(";");
        sb.append(orderWay.name()).append(";");
        sb.append(size).append(";");
        System.out.println("dataParams: output: " + sb.toString());
        return sb.toString();
    }

    @Override
    public IrregularVerbsDataParams fromString(String input) {
        System.out.println("dataParams: input: " + input);
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                this.setMode(IrregularVerbsLearningMode.valueOf(str));
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
                this.setWordsWay(IrregularVerbsLearningWordsWay.valueOf(str));
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
                this.setOrderWay(IrregularVerbsLearningOrderWay.valueOf(str));
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
                this.setSize(NumberUtils.toInt(str, 1));
                i++;
                break;
            } else {
                str += c;
            }
        }
        return this;
    }

}
