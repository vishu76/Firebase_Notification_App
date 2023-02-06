package com.learningbot.firebasenotificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private Button shownotificaion;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shownotificaion=findViewById(R.id.button);
        createNotificationChannel();

       /* //for firebase notification by target topics
      FirebaseMessaging.getInstance().subscribeToTopic("Offers")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String message="Done";
                        if (!task.isSuccessful()) {
                            message="Failed";
                        }

                    }
                });


*/

        /*FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()) {
                    Log.i(getString(R.string.DEBUG_TAG), "Task Failed");
                    return;
                }
                Log.i(getString(R.string.DEBUG_TAG), "The result: "+task.getResult().getToken());
            }
        });
*/
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast

                        //String msg = getString(R.string.DEBUG_TAG, token);
                        String msg= new String(token);
                        Log.i(TAG,"Your Device Token : "+ msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        shownotificaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Second_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, getString(R.string.NEWS_CHANNEL_ID))
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                       // .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_icon_large))
                        .setContentTitle("Notification Title")
                        .setContentText("This is text, that will be shown as part of notification")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("This is text, that will be shown as part of notification"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setChannelId(getString(R.string.NEWS_CHANNEL_ID))
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(getResources().getInteger(R.integer.notificationId), builder.build());
            }
        });



    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.NEWS_CHANNEL_ID),getString(R.string.CHANNEL_NEWS), NotificationManager.IMPORTANCE_DEFAULT );
            notificationChannel.setDescription("hello everyone welcome ");
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}