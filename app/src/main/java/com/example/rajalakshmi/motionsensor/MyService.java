package com.example.rajalakshmi.motionsensor;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service implements SensorEventListener {

    PowerManager.WakeLock mWakeLock;
    SensorManager mSensorManager;
    Sensor mAccelerometer;
    float mAccel,mAccelLast,mAccelCurrent;
    MediaPlayer mp;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Motion Sensor");
        mWakeLock.acquire();

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;

        mp = MediaPlayer.create(this, R.raw.music);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        return 0;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mSensorManager.unregisterListener(this);
        mWakeLock.release();
    }
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt(x*x + y*y + z*z);
            if(mAccelCurrent > 30)
            {
                Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(1000);
                mp.start();
                NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.icon)
                                .setContentTitle("Danger Zone!")
                                .setContentText("Do you need Help?\nClick ME!!");
                int mNotificationId = 001;
                
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        }
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}