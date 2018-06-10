package com.log.activity;

import android.app.Activity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Random;

public class Graph {
    public static void drawDistanceGraph(int days, GraphView graph, Activity activity) {
        Calendar calendar = Calendar.getInstance();
        DataPoint[] dates = new DataPoint[days];

        Random r = new Random();

        for(int i=days-1; i>=0; i--) {
            dates[i] = new DataPoint(calendar.getTime(), (r.nextInt(13)));
            calendar.add(Calendar.DATE, -1);
        }
        calendar.clear();

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dates);
        graph.removeAllSeries();
        graph.addSeries(series);

        graph.getGridLabelRenderer().resetStyles();
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(activity));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Distance [km]");

        graph.getViewport().setMinX(dates[0].getX());
        graph.getViewport().setMaxX(dates[days-1].getX());

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);

        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    public static void drawWeightGraph(int days, GraphView graph, Activity activity) {
        Calendar calendar = Calendar.getInstance();
        DataPoint[] dates = new DataPoint[days];

        Random r = new Random();

        float weight = 56;
        for(int i=days-1; i>=0; i--) {
            dates[i] = new DataPoint(calendar.getTime(), weight);
            calendar.add(Calendar.DATE, -1);

            if(r.nextFloat() >= 0.7){
                weight -= round(r.nextFloat()/2, 1);
            }
            else {
                weight += round(r.nextFloat()/2, 1);
            }
        }
        calendar.clear();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dates);
        graph.removeAllSeries();
        graph.addSeries(series);

        graph.getGridLabelRenderer().resetStyles();
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(activity));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Weight [kg]");

        graph.getViewport().setMinX(dates[0].getX());
        graph.getViewport().setMaxX(dates[days-1].getX());

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);

        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}