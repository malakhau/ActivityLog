package com.log.activity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class LocationRecorderUnitTests {

    private LocationRecorder lr;
    private List<Point> locations;

    @Before
    public void setUp() {
        lr = new LocationRecorder();
        locations = new ArrayList<>(
                Arrays.asList(
                        new Point(0.1f, 0.2f, new Date(1508484583200L)),
                        new Point(0.1f, 0.5f, new Date(1508484583200L)),
                        new Point(0.0f, 0.4f, new Date(1508484583200L)),
                        new Point(0.4f, 0.2f, new Date(1508484583200L)),
                        new Point(-0.2f, -0.2f, new Date(1508484583200L)),
                        new Point(0.2f, 0.2f, new Date(1508484583200L))
                )
        );
    }

    @Test
    public void calculateDistanceBetweenTwoPoints_isCorrect() {
        assertEquals(21900f, lr.distanceBetweenTwoPoints(locations.get(0), locations.get(1)));
        assertEquals(32646.592f, lr.distanceBetweenTwoPoints(locations.get(2), locations.get(3)));
        assertEquals(41295.04f, lr.distanceBetweenTwoPoints(locations.get(4), locations.get(5)));
    }

    @Test
    public void calculateEntireDistance_isCorrect() {
        lr.updateLocationAndTimeList(locations);
        assertEquals(158806.44f, lr.calculateEntireDistance());
    }
}
