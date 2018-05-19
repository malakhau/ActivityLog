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
        return 0f;
    }

    float entireDistance(List<PointF> points) {
        return 0f;
    }
}
