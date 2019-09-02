package com.example.shinple.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.shinple.R;
import com.example.shinple.ui.login.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.android.volley.VolleyLog.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, ":MyFirebase");
            wakeLock.acquire(3000);

            String msg = remoteMessage.getData().get("message");

            sendNotification(msg);

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
                Log.d(TAG, "hello");
            } else {
                // Handle message within 10 seconds
                handleNow();
                Log.d(TAG, "world");
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */

    //앱이 맨 처음에 설치되었을 때 자동으로 호출됨
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }
    private void handleNow(){
        Log.d("TEST", "Short lived task is done.");
    }

    //token을 서버에 등록하는 함수
    public void sendRegistrationToServer(String token){
        Log.d("TEST", "token");

//        String data;    //php로 넘기기 위한 값
//
//        try {
//            data = URLEncoder.encode("mem_token", "UTF-8") + "=" + URLEncoder.encode(unum, "UTF-8");
//            data+= "&"+URLEncoder.encode("mem_name","UTF-8")+"="+ URLEncoder.encode(uname,"UTF-8");
//            data+= "&"+URLEncoder.encode("mem_password","UTF-8")+"="+ URLEncoder.encode(upassword,"UTF-8");
//            data+= "&"+URLEncoder.encode("mem_code","UTF-8")+"="+ URLEncoder.encode(ucode,"UTF-8");
//        } catch (Exception e){
//        }
//
//        String result = "";
//        BackgroundTask backgroundTask = new BackgroundTask("app/UserRegister.php",data);
//        Log.d("Tttt",data);
//        try{
//            result = backgroundTask.execute().get();
//            onResponse(result);
//            Log.d("ssss",result);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    //PHP에서 보내는 알림을 처리하는 모듈
    private void sendNotification(String body) {

        Intent intent = new Intent(this, LoginActivity.class);      //알람을 터치하면 로그인
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri ringtoneUri= RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);     //알림 아이콘 설정
        builder.setContentText(body);                              //알림 메시지
        builder.setAutoCancel(true);
        builder.setSound(ringtoneUri);                             //알림음
        builder.setVibrate(new long[]{0, 100, 200, 300});         //진동
        builder.setLights(Color.BLUE, 1,1);         //알림 색상
        builder.setContentIntent(pendingIntent);

        //알림 채널 설정
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.notify(1, builder.build());

    }

}
