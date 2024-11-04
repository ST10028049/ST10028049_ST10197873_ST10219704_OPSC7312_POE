package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

// Service class for handling push notifications from Firebase
class PushNotificationsService : FirebaseMessagingService() {

    // Method called when a message is received from Firebase Cloud Messaging.
    //This method was adapted from (Philipp Lackner.2024)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // If the message contains a notification payload, show it as a notification
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    // Method called when a new FCM token is generated for the device
    override fun onNewToken(token: String) {
        Log.d("FCM", "New token: $token")
        // Send the token to your server if necessary
    }

    // Helper function to display notifications
    private fun showNotification(title: String?, message: String?) {
        val channelId = "default_channel"
        val notificationId = 1

        // Create a notification channel for Android 8.0 (Oreo) and above
        //This method was adapted from (Mobile App Development.2024)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Default Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Build the notification with the specified icon, title, and message
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.notification)  // Set a small icon
            .setContentTitle(title ?: "New Notification")
            .setContentText(message ?: "You have a new message")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Check for the POST_NOTIFICATIONS permission
        if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Show the notification if permission is granted
            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, builder.build())
            }
        } else {
            // Log a warning if notification permission is not granted
            Log.w("MyFirebaseMessagingService", "Notification permission not granted")
        }
    }
}
//Reference list
/*Android Notifications - Part 10, Firebase Tool Assistant | Push notification configuration.2019.YouTube video, added by Codetutor, 17 May 2019.[Online].Available at: https://www.youtube.com/watch?v=gqMY7PVzdIQ&list=PLfuE3hOAeWhZ-g8rkBI0eUwDrzdNTUNay&index=4 [Accessed 2 October 2024]*/
/*How to implement FCM in Android Kotlin | Firebase Cloud Messaging | Mobile App Development.2024.YouTube video, added by Mobile App Development, 9 March 2024.[Online]. Available at: https://www.youtube.com/watch?v=Ywn5FwTQwOU [Accessed 3 October 2024]*/
/*How to Implement Firebase Push Notifications on Android (FCM + Backend).2024.YouTube video , added by Philipp Lackner, 24 January 2024.[Online].Available at:https://www.youtube.com/watch?v=q6TL2RyysV4 .[Accessed 2 October 2024] */
