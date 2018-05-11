package com.treebricks.ewuhub.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.preference.Preference;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.ui.ApplicationHome;


public class EwuhubFirebaseMessagingService extends FirebaseMessagingService
{
    private static final String TAG = "EwuHub Notification";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification().getBody());


        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(getApplicationContext(), Preference.class), PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());

        Notification notification = builder
                .setSmallIcon(R.drawable.ic_notification)
                .setColor(Color.parseColor("#1A237E"))
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(remoteMessage.getNotification().getBody())
                        .setBigContentTitle(remoteMessage.getNotification().getTitle())
                        .setSummaryText("Thanks for using EwuHub"))
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setVisibility(View.VISIBLE)
                .build();

        notificationManager.notify(001, notification);
    }

}
