package com.log.activity;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;


public class CaloriesBurnedUnitTests {

    @Test
    public void calculateBurnedKiloCalories_isCorrect() {
        assertEquals(50f, CaloriesCalculator.calculateBurnedKiloCalories(1,50,1));
        assertEquals(70f, CaloriesCalculator.calculateBurnedKiloCalories(9,10,1));
        assertEquals(54.449997f, CaloriesCalculator.calculateBurnedKiloCalories(4.9f,11,1.5f));
    }
}
