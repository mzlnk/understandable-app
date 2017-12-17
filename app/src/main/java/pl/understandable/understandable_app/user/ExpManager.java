package pl.understandable.understandable_app.user;

/**
 * Created by Marcin Zielonka on 2017-12-17.
 */

public class ExpManager {

    public static long convertToExp(ExpRatio ratio, int amount, int... extraAmounts) {
        long exp = (long)(amount * ratio.getRatio());
        if(extraAmounts != null) {
            for (int i = 0; i < extraAmounts.length; i++) {
                int extraAmount = extraAmounts[i];
                double extraRatio = i < ratio.getExtraRatios().length ? ratio.getExtraRatios()[i] : 0;
                exp += (long) (extraAmount * extraRatio);
            }
        }
        System.out.println("[EXP] amount: " + amount + ", ratio: " + ratio.getRatio() + " -> exp: " + exp);
        return exp;
    }

    public static enum ExpRatio {

        WORDS_LIST(0.1D),
        WORDS_REPETITION(0.8D),
        WORDS_SPELLING(0.1D, 1.1D),
        WORDS_QUIZ(1D, 0.3D),
        IRREGULAR_VERBS_LIST(0.1D),
        IRREGULAR_VERBS_REPETITION(0.85D),
        PHRASES_LIST(0.1D),
        PHRASES_QUIZ(1D, 0.3D),
        PHRASES_REPETITON(0.8D),
        CUSTOM_WORDS_LIST(0.1D),
        CUSTOM_WORDS_REPETITION(0.8D),
        CUSTOM_WORDS_SPELLING(0.1D, 1.1D),
        CUSTOM_WORDS_QUIZ(1D, 0.3D);

        private double ratio;
        private double[] extraRatios;

        private ExpRatio(double ratio, double... extraRatios) {
            this.ratio = ratio;
            this.extraRatios = extraRatios;
        }

        public double getRatio() {
            return ratio;
        }

        public double[] getExtraRatios() {
            return extraRatios;
        }

    }

}
