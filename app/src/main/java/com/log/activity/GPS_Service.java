package com.log.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.Random;

public class GPS_Service extends Service {

    private LocationListener locationListener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null;}

    @Override
    public void onCreate(){

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds
        final Random random = new Random();

        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent("location_update");
                Bundle extras = new Bundle();
                extras.putFloat("longitude", (random.nextFloat() / 10000.0f));
                extras.putFloat("latitude", (random.nextFloat() / 10000.0f));
                intent.putExtras(extras);
                sendBroadcast(intent);
                handler.postDelayed(this, delay);
            }
        }, delay);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//                Intent intent = new Intent("location_update");
//                Bundle extras = new Bundle();
//                extras.putFloat("longitude", (float)location.getLongitude());
//                extras.putFloat("latitude", (float)location.getLatitude());
//                intent.putExtras(extras);
//                sendBroadcast(intent);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 20, locationListener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(locationListener);
        }
    }
}
