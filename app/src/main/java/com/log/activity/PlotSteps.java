package com.log.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlotSteps extends AppCompatActivity {

    Button backButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_steps);

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        Log.i("Date", thisDate);
        backButt = findViewById(R.id.backToActivity);
        Toast.makeText(this, "Date:" + thisDate, Toast.LENGTH_SHORT).show();
        back();
    }

    public void back(){
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlotSteps.this, MainActivity.class);
                startActivity(i);                                          }
        });
    }
}