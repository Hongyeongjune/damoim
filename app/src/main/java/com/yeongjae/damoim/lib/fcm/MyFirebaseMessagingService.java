package com.yeongjae.damoim.lib.fcm;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignIn;
import com.yeongjae.damoim.util.LogUtils;
import com.yeongjae.damoim.view.signin.SignInActivity;

import java.util.List;

import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "Channel ID";
    private final long[] VIBRATE = {500, 1000, 500, 1000};

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        LogUtils.logDebug(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification() != null) {
            notifyBackground(remoteMessage);
        }

    }

    private void notifyBackground(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationCompat.setContentIntent(pendingIntent);
        notificationCompat.setWhen(System.currentTimeMillis());
        notificationCompat.setSmallIcon(R.drawable.common_google_signin_btn_icon_light);
        notificationCompat.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationCompat.setContentText(remoteMessage.getNotification().getBody());
        notificationCompat.setVibrate(VIBRATE);
        notificationCompat.setSound(uri);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notificationCompat.build());

    }
}
