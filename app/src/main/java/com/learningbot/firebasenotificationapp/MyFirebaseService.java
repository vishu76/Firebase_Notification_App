package com.learningbot.firebasenotificationapp;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MyFirebaseService extends FirebaseMessagingService {

        private static final String TAG = MyFirebaseService.class.getSimpleName();

        @Override
        public void onNewToken (String s){
            super.onNewToken(s);
            Log.i(getString(R.string.DEBUG_TAG), "New Token: " + s);
        }

        @Override
        public void onMessageReceived (RemoteMessage remoteMessage){
            super.onMessageReceived(remoteMessage);
            Log.i(getString(R.string.DEBUG_TAG), "Message received");
        }
    }


