package com.ev.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/* Objective: Create notifications for Pre-Android 5.0 Devices
   Lock Screen Notifcations in Android 5.0
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager manager;

    private int NOTIF_REF = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnMaxPriorityNotification = (Button) findViewById(R.id.btnMaxPriorityNotification);
        Button btnHighPriorityNotification = (Button) findViewById(R.id.btnHighPriorityNotification);
        Button btnDefaultPriorityNotification = (Button) findViewById(R.id.btnDefaultNotification);
        Button btnLowPriorityNotification = (Button) findViewById(R.id.btnLowPriorityNotification);
        Button btnMinPriorityNotification = (Button) findViewById(R.id.btnMinPriorityNotification);
        Button btnDefaultNotification = (Button) findViewById(R.id.btnDefaultNotification);
        Button btnBigTextNotification = (Button) findViewById(R.id.btnBigTextNotification);
        Button btnBigImageNotification = (Button) findViewById(R.id.btnBigImageNotification);
        Button btnInboxTypeNotification = (Button) findViewById(R.id.btnInboxTypeNotification);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        btnMaxPriorityNotification.setOnClickListener(this);
        btnHighPriorityNotification.setOnClickListener(this);
        btnDefaultPriorityNotification.setOnClickListener(this);
        btnLowPriorityNotification.setOnClickListener(this);
        btnMinPriorityNotification.setOnClickListener(this);
        btnDefaultNotification.setOnClickListener(this);
        btnBigTextNotification.setOnClickListener(this);
        btnBigImageNotification.setOnClickListener(this);
        btnInboxTypeNotification.setOnClickListener(this);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        Notification notif;
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications");

        switch (view.getId()) {
            case R.id.btnMaxPriorityNotification:
                builder.setContentTitle("Maximum proirity notification")
                        .setPriority(Notification.PRIORITY_MAX);
                sendNotification(builder.build());
                break;
            case R.id.btnHighPriorityNotification:
                builder.setContentTitle("High proirity notification")
                        .setPriority(Notification.PRIORITY_HIGH);
                sendNotification(builder.build());
                break;
            case R.id.btnLowPriorityNotification:
                builder.setContentTitle("Low proirity notification")
                        .setPriority(Notification.PRIORITY_LOW);
                sendNotification(builder.build());
                break;
            case R.id.btnMinPriorityNotification:
                builder.setContentTitle("Minimum proirity notification")
                        .setPriority(Notification.PRIORITY_MIN);
                sendNotification(builder.build());
                break;
            case R.id.btnDefaultNotification:
                notif = getDefaultNotification(builder);
                sendNotification(notif);
                break;
            case R.id.btnBigTextNotification:
                notif = getBigTextStyle(builder);
                sendNotification(notif);
                break;
            case R.id.btnBigImageNotification:
                notif = getBigPictureStyle(builder);
                sendNotification(notif);
                break;
            case R.id.btnInboxTypeNotification:
                inboxStyleNotification();
                break;
        }
    }

    public void sendNotification(Notification notif) {
        manager.notify(NOTIF_REF++, notif);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification getDefaultNotification(Notification.Builder builder) {
        builder
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Default notification")
                .setContentText("This is a random text for default type notifications")
                .setContentInfo("Info");
        return builder.build();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification getBigPictureStyle(Notification.Builder builder) {
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.big_image);
        builder.setContentTitle("Reduced BigPicture title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(icon);

        return new Notification.BigPictureStyle(builder)
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Expanded BigPicture title")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification getBigTextStyle(Notification.Builder builder) {
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.big_image);
        builder.setContentTitle("Reduced BigText title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(icon);

        return new Notification.BigTextStyle(builder)
                .bigText("")
                .setBigContentTitle("Naruto 9 Tails")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }

    private void inboxStyleNotification() {
        int ID = 1;
        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setSummaryText(getResources().getString(R.string.summary_text));
        mBuilder.setStyle(inboxStyle);
        mBuilder.setNumber(1);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(ID, mBuilder.build());
    }

    // This method will be used to display the default notifications available in devices older
    // than version 4.1.

    public void getOldNotification(View view) {

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Nine Tails")
                .setContentText("Guy Sensai would be proud");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(
                Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
       /*
        // NotificationCompat Builder takes care of backwards compatibility and
        // provides clean API to create rich notifications
        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Notification title")
                .setContentText("Content text");

        // Obtain NotificationManager system service in order to show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIF_REF, mBuilder.build());

        return null;
        */
}