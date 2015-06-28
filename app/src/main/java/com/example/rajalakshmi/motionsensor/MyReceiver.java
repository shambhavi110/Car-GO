package com.example.rajalakshmi.motionsensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        context.startService(new Intent(context,MyService.class));
        context.startService(new Intent(context, MyService1.class));
        Toast.makeText(context,"CAR GO, on the go!!",Toast.LENGTH_LONG).show();
    }
}
