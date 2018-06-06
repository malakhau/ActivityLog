package com.log.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;

public class LastYear extends AppCompatActivity {

    Button backButt, distanceButt, weightButt;
    GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_year);

        backButt = findViewById(R.id.backToActivity);
        distanceButt = findViewById(R.id.distanceButton);
        weightButt = findViewById(R.id.weightButton);

        distance();
        weight();
        back();

        graph = (GraphView) findViewById(R.id.graph);
        Graph.drawDistanceGraph(356, graph, this);
    }

    private void distance() {
        distanceButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Graph.drawDistanceGraph(356, graph, LastYear.this);
            }
        });
    }

    private void weight() {
        weightButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Graph.drawWeightGraph(356, graph, LastYear.this);
            }
        });
    }

    private void back(){
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LastYear.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}