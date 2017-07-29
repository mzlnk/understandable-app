package net.heliantum.understandable.data.params;

import net.heliantum.understandable.data.enums.irregular_verbs.IrregularVerbsLearningMode;
import net.heliantum.understandable.database.repository.IrregularVerbEntityRepository;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by Marcin on 2017-07-08.
 */

public class IrregularVerbsDataParams extends BaseDataParams {

    public IrregularVerbsLearningMode mode = IrregularVerbsLearningMode.REPETITION;
    public int size = 1;

    public void setMode(IrregularVerbsLearningMode mode) {
        this.mode = mode;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isChosen(IrregularVerbsLearningMode mode) {
        return this.mode.equals(mode);
    }

    public int getMaximumAvailableWordsAmount() {
        return IrregularVerbEntityRepository.getAllEntities().size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mode.name()).append(";");
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
