package com.log.activity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class LocationRecorderUnitTests {

    private LocationRecorder lr;
    private List<Point> points;

    @Before
    public void setUp() {
        lr = new LocationRecorder();
        points = new ArrayList<>(
                Arrays.asList(
                        new Point(0.1f, 0.2f, 1508484583200L),
                        new Point(0.1f, 0.5f, 1508484590000L),
                        new Point(0.0f, 0.4f, 1508484678000L),
                        new Point(0.4f, 0.2f, 1508485586540L),
                        new Point(-0.2f, -0.2f, 1508486586540L),
                        new Point(0.2f, 0.2f, 1508484912345L)
                )
        );
    }

    @Test
    public void calculateDistanceBetweenTwoPoints_isCorrect() {
        assertEquals(21900f, lr.distanceBetweenTwoPoints(points.get(0), points.get(1)));
        assertEquals(10323.759f, lr.distanceBetweenTwoPoints(points.get(1), points.get(2)));
        assertEquals(32646.592f, lr.distanceBetweenTwoPoints(points.get(2), points.get(3)));
        assertEquals(52641.05f, lr.distanceBetweenTwoPoints(points.get(3), points.get(4)));
        assertEquals(41295.04f, lr.distanceBetweenTwoPoints(points.get(4), points.get(5)));
    }

    @Test
    public void calculateEntireDistance_isCorrect() {
        lr.updateLocationAndTimeList(points);
        assertEquals(158806.44f, lr.calculateEntireDistance());
    }

    @Test
    public void calculateTimeBetweenTwoPoints_isCorrect() {
        assertEquals(6800,lr.timeBetweenTwoPoints(points.get(0), points.get(1)));
        assertEquals(88000,lr.timeBetweenTwoPoints(points.get(1), points.get(2)));
        assertEquals(908540,lr.timeBetweenTwoPoints(points.get(2), points.get(3)));
        assertEquals(1000000,lr.timeBetweenTwoPoints(points.get(3), points.get(4)));
        assertEquals(1674195L,lr.timeBetweenTwoPoints(points.get(4), points.get(5)));
    }

    @Test
    public void calculateVelocityBetweenTwoPoints_isCorrect() {
        assertEquals(3220.5881f,lr.calculateVelocityBetweenTwoPoints(points.get(0), points.get(1)));
        assertEquals(117.31544f,lr.calculateVelocityBetweenTwoPoints(points.get(1), points.get(2)));
        assertEquals(35.933025f,lr.calculateVelocityBetweenTwoPoints(points.get(2), points.get(3)));
        assertEquals(52.641052f,lr.calculateVelocityBetweenTwoPoints(points.get(3), points.get(4)));
        assertEquals(24.66561f,lr.calculateVelocityBetweenTwoPoints(points.get(4), points.get(5)));
    }

    @Test
    public void calculateAverageVelocityAndEntireDistance_isCorrect() {
        lr.updateLocationAndTimeList(points);
        lr.calculateAverageVelocityAndEntireDistance();
        assertEquals(482.48172f, lr.getAvrageVelocityInMetersPerSecund());
        assertEquals(158806.44f, lr.getEntireDistanceInMeters());
    }

    @Test
    public void convertVelocityMetersPerSecundToKiloMetersPerHour_isCorrect() {
        assertEquals(3.6f, lr.convertVelocityMetersPerSecundToKiloMetersPerHour(1));
        assertEquals(86.399994f, lr.convertVelocityMetersPerSecundToKiloMetersPerHour(24));
        assertEquals(-3.6f, lr.convertVelocityMetersPerSecundToKiloMetersPerHour(-1));
        assertEquals(0f, lr.convertVelocityMetersPerSecundToKiloMetersPerHour(0));
        assertEquals(182.87999f, lr.convertVelocityMetersPerSecundToKiloMetersPerHour(50.8f));
    }
}
