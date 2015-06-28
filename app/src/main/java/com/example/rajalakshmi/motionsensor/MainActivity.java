package com.example.rajalakshmi.motionsensor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    public  int u,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder alb = new AlertDialog.Builder(this);
        alb.setMessage("Start Service by enabling GPS/Start Service\nCar Go , Get Set Go!!");
        alb.setTitle("Car Go!");
        final AlertDialog al;
        alb.setPositiveButton("GOOO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        al = alb.create();
        al.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,prefsActivity.class);
            startActivity(intent);
        }
        if(id==R.id.action_start)
        {
            startService(new Intent(getBaseContext(), MyService.class));
            startService(new Intent(getBaseContext(), MyService1.class));
            Toast.makeText(this,"CAR GO, on the go!!",Toast.LENGTH_LONG).show();
        }
        if(id==R.id.action_stop)
        {
            stopService(new Intent(getBaseContext(), MyService.class));
            stopService(new Intent(getBaseContext(), MyService1.class));
            Toast.makeText(this,"Stopped",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
