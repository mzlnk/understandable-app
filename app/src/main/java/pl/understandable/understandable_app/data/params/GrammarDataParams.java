package pl.understandable.understandable_app.data.params;

import pl.understandable.understandable_app.data.enums.grammar.GrammarLearningMode;
import pl.understandable.understandable_app.database.repository.GrammarEntitiesRepository;

/**
 * Created by Marcin Zielonka on 2017-08-12.
 */

public class GrammarDataParams extends BaseDataParams {

    public GrammarLearningMode mode = GrammarLearningMode.QUIZ;
    public String id = "";
    public String name = "";

    public GrammarDataParams(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setMode(GrammarLearningMode mode) {
        this.mode = mode;
    }

    public boolean isChosen(GrammarLearningMode mode) {
        return this.mode.equals(mode);
    }

    public int getMaximumAvailableWordsAmount() {
        return GrammarEntitiesRepository.getGrammarEntities(mode).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mode.name()).append(";");
        System.out.println("dataParams: output: " + sb.toString());
        return sb.toString();
    }

    @Override
    public GrammarDataParams fromString(String input) {
        System.out.println("dataParams: input: " + input);
        int i = 0;
        String str = "";
        for(; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == ';') {
                this.setMode(GrammarLearningMode.valueOf(str));
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
