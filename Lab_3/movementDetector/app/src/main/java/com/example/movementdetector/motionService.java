
package com.example.movementdetector;

import android.app.Service;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class motionService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private static final String CHANNEL_ID = "motion_channel";

    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        createNotificationChannel();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (accelerometer != null) {
            sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (Math.abs(x) > 10 || Math.abs(y) > 10 || Math.abs(z) > 10) {
            showNotification();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Motion Detection",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(
                    "movemnt is detected");

            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void showNotification() {
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Dont touch my phone")
                        .setContentText("movement detected")
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setAutoCancel(true);
        notificationManager.notify(0, notification.build());


    }




    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
