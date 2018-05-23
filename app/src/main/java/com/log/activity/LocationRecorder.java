package com.log.activity;


import java.util.ArrayList;
import java.util.List;

public class LocationRecorder {
    private List<Point> locations = new ArrayList<>();

    void addLocationAndTime(Point point) {
        locations.add(point);
    }

    float distanceBetweenTwoPoints(Point point1, Point point2) {
        return (float)(Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2)));
    }

    float entireDistance(List<Point> points) {
        float entireDistance = 0f;
        for(int i=0; i+1 < points.size(); i++) {
            entireDistance += distanceBetweenTwoPoints(points.get(i), points.get(i+1));
        }
        return entireDistance;
    }
}
