package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val channelId = "channel_id_100"
    private val notificationId = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val intent = Intent(applicationContext,MainActivity2::class.java)

            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Notification Title"
                val descriptionText = "Notification Description"
                val importance  = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(channelId,name,importance).apply {
                    description = descriptionText
                }
                val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
            val builder = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
                .setContentTitle("Welcome")
                .setContentText("Hello.. please tap on the notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, builder.build())
            }

        }

    }
}