package com.example.movementdetector;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationManager;
import android.util.Log;
import android.view.View;
import android.app.NotificationChannel;
import android.os.Build;


public class motionService extends Service implements SensorEventListener {




    public motionService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){

            SensorManager sensorManager;
         Sensor accelerometer;

         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer !=null){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return START_STICKY;

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "motion_channel",
                    "Motion Detection"
            );
        }
    }


    private void showNotification(){
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Don't touch phone")
                .setContentText("Movement Detected")
                .setSmallIcon(android.R.drawable.ic_dialog_alert);

        NotificationManager manager = (NotificationManager)  getSystemService(NOTIFICATION_SERVICE);
        Log.d("Motion Server", "detected");
        manager.notify(1, builder.build());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if(Math.abs(x) > 10 ||  Math.abs(y) > 10 ||  Math.abs(z) > 10 ){
            showNotification();
            Log.d("Movement", "Phone movement");
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }






}