package com.log.activity;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class LocationRecorder {
    private List<PointF> locations = new ArrayList<>();

    void addLocation(PointF point) {
        locations.add(point);
    }

    float distanceBetweenTwoPoints(PointF point1, PointF point2) {
        return (float)(Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2)));
    }

    float entireDistance(List<PointF> points) {
        float entireDistance = 0f;
        for(int i=0; i+1 < points.size(); i++) {
            entireDistance += distanceBetweenTwoPoints(points.get(i), points.get(i+1));
        }
        return entireDistance;
    }
}
