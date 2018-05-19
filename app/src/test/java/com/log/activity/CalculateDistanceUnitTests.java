package com.log.activity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculateDistanceUnitTests {

    @Test
    public void calculateDistanceBetweenTwoPoints_isCorrect() {
        float[][] locations = {
                {1f,2f},{1f,5f},
                {0f,4f},{4f,2f},
                {-2f,-2f},{2f,2f}
        };
        assertEquals(3.0f, distanceBetweenTwoPoints(locations[0], locations[1]));
        assertEquals(4.472135955f, distanceBetweenTwoPoints(locations[2], locations[3]));
        assertEquals(5.656854249f, distanceBetweenTwoPoints(locations[4], locations[5]));
    }

    @Test
    public void calculateEntireDistance_isCorrect() {
        float[][] locations = {
                {1f,2f},{1f,5f},
                {0f,4f},{4f,2f},
                {-2f,-2f},{2f,2f}
        };
        assertEquals(13,128990204f, entireDistance(locations));
    }
}
