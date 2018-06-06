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

        for(int i=days-1; i>=0; i--) {
            dates[i] = new DataPoint(calendar.getTime(), (r.nextInt(80 - 75) + 75));
            calendar.add(Calendar.DATE, -1);
        }
        calendar.clear();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dates);
        graph.removeAllSeries();
        graph.addSeries(series);

        graph.getGridLabelRenderer().resetStyles();
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(activity));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getViewport().setMinX(dates[0].getX());
        graph.getViewport().setMaxX(dates[days-1].getX());

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);

        graph.getGridLabelRenderer().setHumanRounding(false);
    }
}