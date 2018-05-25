package com.log.activity;


import java.util.ArrayList;
import java.util.List;

public class LocationRecorder {
    private static final int DEGREE_TO_METERS = 73000;

    private List<Point> mPoints = new ArrayList<>();
    private float mEntireDistanceInMeters = 0f;
    private float mAvrageVelocityInMetersPerSecund = 0f;

    LocationRecorder() {}

    LocationRecorder(List<Point> points) {
        mPoints = points;
    }

    void addLocationAndTime(Point point) {
        mPoints.add(point);
    }

    void updateLocationAndTimeList(List<Point> points) {
        mPoints = points;
    }

    float distanceBetweenTwoPoints(Point point1, Point point2) {
        return (float) (Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2))) * DEGREE_TO_METERS;
    }

    float calculateEntireDistance() {
        mEntireDistanceInMeters = 0f;
        for (int i = 0; i + 1 < mPoints.size(); i++) {
            mEntireDistanceInMeters += distanceBetweenTwoPoints(mPoints.get(i), mPoints.get(i + 1));
        }
        return mEntireDistanceInMeters;
    }

    long timeBetweenTwoPoints(Point point1, Point point2) {
        return Math.abs(point2.currentTime.getTime() - point1.currentTime.getTime());
    }

    float calculateAverageVelocityOnEntireDistance() {
        mAvrageVelocityInMetersPerSecund = 0f;
        float sumVelocity = 0f;
        for (int i = 0; i + 1 < mPoints.size(); i++) {
            sumVelocity += calculateVelocityBetweenTwoPoints(mPoints.get(i), mPoints.get(i + 1));
        }
        mAvrageVelocityInMetersPerSecund = sumVelocity / calculateEntireDistance();
        return mAvrageVelocityInMetersPerSecund;
    }

    void calculateAverageVelocityAndEntireDistance() {
        mEntireDistanceInMeters = 0f;
        mAvrageVelocityInMetersPerSecund = 0f;
        for (int i = 0; i + 1 < mPoints.size(); i++) {
            mEntireDistanceInMeters += distanceBetweenTwoPoints(mPoints.get(i), mPoints.get(i + 1));
        }
        long timeBetweenStartAndEndInMilliSeconds = timeBetweenTwoPoints(
                mPoints.get(0), mPoints.get(mPoints.size() - 1));
        mAvrageVelocityInMetersPerSecund = mEntireDistanceInMeters / (timeBetweenStartAndEndInMilliSeconds / 1000.0f);
    }

    float calculateVelocityBetweenTwoPoints(Point point1, Point point2) {
        float distanceInMeters = distanceBetweenTwoPoints(point1, point2);
        long timeInMilliSeconds = timeBetweenTwoPoints(point1, point2);
        float velocityMetersPerSecond = distanceInMeters / (timeInMilliSeconds / 1000.0f);
        return velocityMetersPerSecond;
    }

    float convertVelocityMetersPerSecundToKiloMetersPerHour(float velocityMetersPerSecond) {
        return velocityMetersPerSecond * (36.0f / 10.0f);
    }

    float getEntireDistanceInMeters() {
        return mEntireDistanceInMeters;
    }

    float getAvrageVelocityInMetersPerSecund() {
        return mAvrageVelocityInMetersPerSecund;
    }
}
