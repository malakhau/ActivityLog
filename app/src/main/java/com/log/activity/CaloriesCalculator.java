package com.log.activity;

public class CaloriesCalculator {
    public static float calculateBurnedKiloCalories(float velocityKmPerH, float weightKg, float timeActivityH) {
        float MET = chooseMET(velocityKmPerH);
        return MET * weightKg * timeActivityH;
    }

    private static float chooseMET(float velocity) {
        if (velocity < 2.7) {
            return 1.0f;
        } else if (velocity < 4) {
            return 2.3f;
        } else if (velocity < 4.8) {
            return 2.9f;
        } else if (velocity < 5.5) {
            return 3.3f;
        } else if (velocity < 9.2) {
            return 7.0f;
        } else {
            return 4.0f;
        }
    }
}
