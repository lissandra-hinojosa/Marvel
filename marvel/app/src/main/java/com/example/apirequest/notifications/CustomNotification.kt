package com.example.apirequest.notifications

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.example.apirequest.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CustomNotification : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        println(token)
    }

    //Just foreground
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        remoteMessage?.notification?.let {
            createMessage(it.body?:"Null body",it.title?: "Null title")
        }
    }
    private fun createMessage(messageBody: String, messageTitle: String){
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, getString(R.string.default_channel_id))
            .setSmallIcon(R.drawable.ic_adjust_black_24dp)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
        //.setContentIntent(pendingIntent)
         val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(“ general_chanel_id ”,
//            “Channel human readable title ”,
//            NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
        notificationManager. notify (0 /* ID of notification */, notificationBuilder.build())
    }
}