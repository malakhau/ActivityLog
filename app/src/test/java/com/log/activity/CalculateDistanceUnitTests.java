package com.log.activity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class CalculateDistanceUnitTests {

    private LocationRecorder lr;
    private List<Point> locations;

    @Before
    public void setUp() {
        lr = new LocationRecorder();
        locations = new ArrayList<>(
                Arrays.asList(
                        new Point(1f, 2f, new Date()),
                        new Point(1f, 5f, new Date()),
                        new Point(0f, 4f, new Date()),
                        new Point(4f, 2f, new Date()),
                        new Point(-2f, -2f, new Date()),
                        new Point(2f, 2f, new Date())
                )
        );
    }

    @Test
    public void calculateDistanceBetweenTwoPoints_isCorrect() {
        assertEquals(3.0f, lr.distanceBetweenTwoPoints(locations.get(0), locations.get(1)));
        assertEquals(4.472135955f, lr.distanceBetweenTwoPoints(locations.get(2), locations.get(3)));
        assertEquals(5.656854249f, lr.distanceBetweenTwoPoints(locations.get(4), locations.get(5)));
    }

    @Test
    public void calculateEntireDistance_isCorrect() {
        assertEquals(21.754306317f, lr.entireDistance(locations));
    }
}
