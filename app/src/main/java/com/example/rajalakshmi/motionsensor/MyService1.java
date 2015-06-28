package com.example.rajalakshmi.motionsensor;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyService1 extends Service implements LocationListener {
    public MyService1() {
    }
    LocationManager lm;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate()
    {

       lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000, 0, this);
        return 0;
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d("Lat & Long: ",location.getLatitude() + ", " + location.getLongitude());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
