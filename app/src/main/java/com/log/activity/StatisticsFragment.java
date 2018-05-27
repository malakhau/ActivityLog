package com.log.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class StatisticsFragment extends Fragment {

    private Button startButton, stopButton;
    private TextView timerTextView, caloriesTextView, velocityTextView, distanceTextView;
    private BroadcastReceiver broadcastReceiver;
    private LocationRecorder locationRecorder;
    private SharedPreferences sharedPref;

    private float mWeight = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        startButton = view.findViewById(R.id.startTracking);
        stopButton = view.findViewById(R.id.stopTracking);
        stopButton.setEnabled(false);

        timerTextView = view.findViewById(R.id.timerTextView);
        caloriesTextView = view.findViewById(R.id.calBurnedtextView);
        velocityTextView = view.findViewById(R.id.velocityTextView);
        distanceTextView = view.findViewById(R.id.distanceTextView);

        if(!runtime_permissions())
            enable_buttons();

        return view;
    }

    private void enable_buttons() {

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext().getApplicationContext(), GPS_Service.class);
                getActivity().startService(i);
                getLocation();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);

                timerTextView.setText("00:00:00");
                caloriesTextView.setText("0.00");
                velocityTextView.setText("0.00");
                distanceTextView.setText("0.000");


                mWeight = sharedPref.getFloat(getString(R.string.saved_width), mWeight);
                if (mWeight == 0) {
                    Toast.makeText(
                            getContext(),
                            "Set your weight in options to get information about calories burned.",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext().getApplicationContext(),GPS_Service.class);
                getActivity().stopService(i);
                getActivity().unregisterReceiver(broadcastReceiver);
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });

    }

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }

    public void getLocation(){

        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    float longitude = (float)intent.getExtras().get("longitude");
                    float latitude = (float)intent.getExtras().get("latitude");
                    Point p = new Point(longitude, latitude, Calendar.getInstance().getTime().getTime());
                    locationRecorder.addLocationAndTime(p);
                    calculateAndSetStatistics();
                }
            };
        }
        getContext().registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    private void calculateAndSetStatistics() {
        locationRecorder.calculateAverageVelocityAndEntireDistance();
        float velocityKmPerH = locationRecorder.convertVelocityMetersPerSecundToKiloMetersPerHour(
                locationRecorder.getAvrageVelocityInMetersPerSecund());
        float entireDistanceInKm = locationRecorder.getEntireDistanceInMeters() / 1000.0f;
        long timeInMilliSeconds = locationRecorder.calculateTimeBetweenStartAndEndInMilliSeconds();
        float kiloCaloriesBurned = CaloriesCalculator.calculateBurnedKiloCalories(
                velocityKmPerH, mWeight, timeInMilliSeconds);

        long second = (timeInMilliSeconds / 1000) % 60;
        long minute = (timeInMilliSeconds / (1000 * 60)) % 60;
        long hour = (timeInMilliSeconds / (1000 * 60 * 60)) % 24;
        String time = String.format("%02d:%02d:%02d", hour, minute, second);
        timerTextView.setText(time);
        caloriesTextView.setText(new DecimalFormat("####.##").format(kiloCaloriesBurned));
        velocityTextView.setText(new DecimalFormat("##.##").format(velocityKmPerH));
        distanceTextView.setText(new DecimalFormat("##.###").format(entireDistanceInKm));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(broadcastReceiver);
    }

}
