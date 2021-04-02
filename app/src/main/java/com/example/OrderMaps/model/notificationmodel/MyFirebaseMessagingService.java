package com.example.OrderMaps.model.notificationmodel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.OrderMaps.model.apimodel.FcmTokenResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.rebository.Repository;
import com.example.OrderMaps.ui.activities.HomeActivity;
import com.example.splashactivity.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    Context context;
    PreferencesManager preferencesManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title = "";
        String body = "";


        if (remoteMessage.getData().size() > 0) {
            //remoteMessage.getData().get("")
            title = remoteMessage.getData().get("title");
            body = remoteMessage.getData().get("body");

        }

        Map<String, String> data = remoteMessage.getData();

        data.forEach((k, v) -> Log.d("aseel", "onMessageReceived: " + k + " " + v));
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManagerCompat notifcationManager = NotificationManagerCompat.from(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "app_notification", "app_channel", NotificationManager.IMPORTANCE_MAX);


            notificationChannel.setDescription("your desc");
            notificationChannel.setName("your name");
            notifcationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), getString(R.string.notification_channel_id));
        builder.setSmallIcon(R.drawable.logo).setContentTitle(title)
                .setContentText(body).setSound(soundUri).setChannelId("app_notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);

        //Intent intent = Intent(this , MainActivity::class.java)
        Intent intent = new Intent(this, HomeActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        builder.setContentIntent(pendingIntent);
        int id = (int) System.currentTimeMillis();
        notifcationManager.notify(id, builder.build());

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        preferencesManager = new PreferencesManager(this);
        preferencesManager.savefcmtoken(s);
        if (preferencesManager.islogin()) {
            Repository repository = Repository.getInstance();
            repository.storetoken(s, "Bearer " + preferencesManager.fetchtoken()).subscribe(new Observer<Response<FcmTokenResponse>>() {
                @Override
                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull Response<FcmTokenResponse> fcmTokenResponseResponse) {

                }

                @Override
                public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });

        }
    }
}


