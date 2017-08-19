package pl.understandable.understandable_app.utils;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import pl.understandable.understandable_app.data.enums.LearningWay;
import pl.understandable.understandable_app.database.entity.BaseWordEntity;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;

/**
 * Created by Marcin on 2017-08-19.
 */

public class EntitySortUtil {

    private static final int POLISH_TO_ENGLISH = 0;
    private static final int ENGLISH_TO_POLISH = 1;
    private static final int RANDOM = 2;

    private static final Collator c = Collator.getInstance(new Locale("pl", "PL"));

    public static void sort(List<? extends BaseWordEntity> list, LearningWay way) {
        Comparator<BaseWordEntity> alphaOrder;
        if(way.getWay() == ENGLISH_TO_POLISH) {
            alphaOrder = new Comparator<BaseWordEntity>() {

                @Override
                public int compare(BaseWordEntity entity1, BaseWordEntity entity2) {
                    int result = c.compare(entity1.getEnglish(), entity2.getEnglish());
                    if(result == 0) {
                        return entity1.getEnglish().compareTo(entity2.getEnglish());
                    }
                    return result;
                }
            };
        } else {
            alphaOrder = new Comparator<BaseWordEntity>() {
                @Override
                public int compare(BaseWordEntity entity1, BaseWordEntity entity2) {
                    int result = c.compare(entity1.getPolish(), entity2.getPolish());
                    if(result == 0) {
                        return entity1.getPolish().compareTo(entity2.getPolish());
                    }
                    return result;
                }
            };
        }
        Collections.sort(list, alphaOrder);
    }

    public static void sort(List<IrregularVerbEntity> list) {
        Comparator<IrregularVerbEntity> alphaOrder = new Comparator<IrregularVerbEntity>() {
            @Override
            public int compare(IrregularVerbEntity entity1, IrregularVerbEntity entity2) {
                int result = c.compare(entity1.getPolish(), entity2.getPolish());
                if(result == 0) {
                    return entity1.getPolish().compareTo(entity2.getPolish());
                }
                return result;
            }
        };
        Collections.sort(list, alphaOrder);
    }

}
