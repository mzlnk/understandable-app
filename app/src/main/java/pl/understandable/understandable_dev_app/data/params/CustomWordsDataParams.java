package pl.understandable.understandable_dev_app.data.params;

import pl.understandable.understandable_dev_app.data.enums.custom_words.CustomWordsLearningMode;
import pl.understandable.understandable_dev_app.data.enums.custom_words.CustomWordsLearningWay;
import pl.understandable.understandable_dev_app.database.repository.CustomWordEntityRepository;

/**
 * Created by Marcin Zielonka on 2017-07-29.
 */

public class CustomWordsDataParams extends BaseDataParams {

    public CustomWordsLearningMode mode = CustomWordsLearningMode.REPETITION;
    public CustomWordsLearningWay way = CustomWordsLearningWay.RANDOM;
    public String id = "";

    public CustomWordsDataParams(String id) {
        this.id = id;
    }

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

    public int getMaximumAvailableWordsAmount() {
        return CustomWordEntityRepository.getAllEntities().size();
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
