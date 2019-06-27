package com.packagename;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage.getNotification().getBody());


        }

      private void sendNotification(String messageBody){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
          Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
          NotificationCompat.Builder notificaationBuilder = new NotificationCompat.Builder(this);
          notificaationBuilder.setSmallIcon(R.mipmap.nitifi);
          notificaationBuilder.setContentTitle("Kl Guessing");
          notificaationBuilder.setContentText(messageBody);
          notificaationBuilder.setAutoCancel(true);
          notificaationBuilder.setSound(defaultSoundUri);
          notificaationBuilder.setContentIntent(pendingIntent);

          NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
          notificationManager.notify(0,notificaationBuilder.build());
      }

    }
