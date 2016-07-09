package com.treebricks.ewuhub.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.treebricks.ewuhub.R;


public class EwuhubFirebaseMessagingService extends FirebaseMessagingService
{
    private static final String TAG = "EHubNotificationService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification().getBody());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());

        Notification notification = builder.
                setSmallIcon(R.drawable.ic_notification)
                .setColor(Color.parseColor("#1A237E"))
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(remoteMessage.getNotification().getBody())
                        .setBigContentTitle(remoteMessage.getNotification().getTitle())
                        .setSummaryText("Thanks for using EwuHub"))
                .setAutoCancel(true)
                .setVisibility(View.VISIBLE)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notification);
    }
}
