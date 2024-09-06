package sumit.app.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val CHANNEL_ID = "sample_channel"
    private val NOTIFICATION_ID = 1

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            // Process the data here
            Log.d("asdf", "Payload -> ${remoteMessage.data}")
            showNotification(remoteMessage.data["title"], remoteMessage.data["body"])
        }

        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d("asdf", "Notification -> ${remoteMessage.notification}")
//            val messageBody = it.body
//            val messageTitle = it.title
//            Log.d("asdf", "Channel Id >  ${it.channelId}")
//            showNotification(messageTitle, messageBody)
//        }
    }

    private fun showNotification(title: String?, message: String?, flag: String? = null) {
        createNotificationChannel()

        // Create an Intent for the activity you want to start.
        val resultIntent = Intent(this, MainActivity::class.java)

        resultIntent.putExtra("flag", 1)
        resultIntent.putExtra("title", title)
        resultIntent.putExtra("body", title)
        // Create the TaskStackBuilder.
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack.
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack.
            getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

//        // Create an explicit intent for an Activity in your app
//        val intent = Intent(this, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }


//        val pendingIntent: PendingIntent =
//            PendingIntent.getActivity(this, 0, intent,  PendingIntent.FLAG_IMMUTABLE)

        // Build the notification
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)  // Set your icon here
            .setContentTitle(title ?: "N/A")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText(message ?: "N/A")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(true)  // Automatically remove notification when tapped

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // Show the notification
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        Log.d("asdf", "Hooray! I have archived it.")
        // Only create the notification channel on API 26+ (Android O and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Sample Channel"
            val descriptionText = "This is a sample notification channel."
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}