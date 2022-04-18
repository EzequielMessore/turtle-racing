package br.com.messore.tech.core.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationManagerCompat

private const val CHANNEL_NAME = "turtle_channel"
private const val CHANNEL_ID = "turtle notifications"

fun Application.createNotificationChannel() {
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
        description = "This channel will be responsible to send notification about the run's"
    }

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

private fun Context.buildNotification(title: String, builder: Builder.() -> Unit): Builder {
    return Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_turtle)
        .setContentTitle(title)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .also(builder)
}

fun Context.createNotification(title: String, content: String): Builder {
    return buildNotification(title) {
        setContentText(content)
    }
}

fun Context.createBigTextNotification(title: String, bigText: String): Builder {
    return buildNotification(title) {
        setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
    }
}

fun Context.showNotification(id: Int, notification: Builder) {
    val manager = NotificationManagerCompat.from(this)
    manager.notify(id, notification.build())
}
