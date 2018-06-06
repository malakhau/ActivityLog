package com.log.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;
import java.util.Random;

public class PlotSteps extends AppCompatActivity {

    Button backButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_steps);

        backButt = findViewById(R.id.backToActivity);
        back();

        drawGraph(7);
    }

    private void drawGraph(int days) {
        Calendar calendar = Calendar.getInstance();
        DataPoint[] dates = new DataPoint[days];

        Random r = new Random();

        for(int i=days-1; i>=0; i--) {
            dates[i] = new DataPoint(calendar.getTime(), (r.nextInt(10)));
            calendar.add(Calendar.DATE, -1);
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dates);
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getViewport().setMinX(dates[0].getX());
        graph.getViewport().setMaxX(dates[days-1].getX());

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);

        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    private void back(){
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlotSteps.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}