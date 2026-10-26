package com.example.simplenotificationj;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10002" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super q      .onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void createNotification(View view) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("CHANNEL_DESCRIPTION");
            notificationManager.createNotificationChannel(channel);
        }

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationOpenActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Build notification
        // Actions are just fake
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)//hide notification after it is selected
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_launcher_background, "Call", pIntent)
                .addAction(R.drawable.ic_launcher_background, "More", pIntent)
                .addAction(R.drawable.ic_launcher_background, "And more", pIntent);

        notificationManager.notify(0, noti.build());

    }
}