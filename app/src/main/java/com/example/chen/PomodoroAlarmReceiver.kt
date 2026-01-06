package com.example.chen

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// Constants for alarm management - accessible across files in the same package
const val POMODORO_ALARM_ACTION = "com.example.chen.POMODORO_ALARM"
const val POMODORO_ALARM_REQUEST_CODE = 1202

/**
 * BroadcastReceiver for handling Pomodoro timer alarm events
 * This allows notifications to be triggered even when the app is in background
 */
class PomodoroAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == POMODORO_ALARM_ACTION) {
            // Retrieve data from intent
            val totalMillis = intent.getLongExtra("total_millis", 0L)
            val activeModeOrdinal = intent.getIntExtra("active_mode", 0)
            val pomodoroPhaseOrdinal = intent.getIntExtra("pomodoro_phase", 0)

            // Build notification content based on mode
            val mode = TimerMode.entries.getOrElse(activeModeOrdinal) { TimerMode.COUNTDOWN }
            val phase = PomodoroPhase.entries.getOrElse(pomodoroPhaseOrdinal) { PomodoroPhase.WORK }
            val totalMinutes = (totalMillis / 60000L).toInt().coerceAtLeast(1)

            val (title, message) = when (mode) {
                TimerMode.POMODORO -> {
                    val phaseLabel = when (phase) {
                        PomodoroPhase.WORK -> "工作"
                        PomodoroPhase.SHORT_BREAK -> "短休息"
                        PomodoroPhase.LONG_BREAK -> "长休息"
                    }
                    "番茄完成" to "${phaseLabel}结束，休息一下"
                }
                TimerMode.COUNTDOWN -> {
                    "倒计时结束" to "已完成 ${totalMinutes} 分钟倒计时"
                }
                TimerMode.COUNT_UP -> {
                    "计时完成" to "已完成 ${totalMinutes} 分钟"
                }
            }

            sendNotificationDirect(context, title, message)
        }
    }

    private fun sendNotificationDirect(context: Context, title: String, message: String) {
        // Reuse the notification creation logic
        createNotificationChannelsIfNeeded(context)
        sendNotification(context, title, message)
    }

    private fun createNotificationChannelsIfNeeded(context: Context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) return
        createPomodoroNotificationChannels(context)
    }

    private fun sendNotification(context: Context, title: String, message: String) {
        if (!canPostNotifications(context)) return

        val channelId = POMODORO_CHANNEL_ALERT
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = androidx.core.app.NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(androidx.core.app.NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(androidx.core.app.NotificationCompat.PRIORITY_MAX)
            .setVisibility(androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(androidx.core.app.NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        androidx.core.app.NotificationManagerCompat.from(context).notify(
            POMODORO_NOTIFICATION_ID,
            builder.build()
        )
    }

    private fun canPostNotifications(context: Context): Boolean {
        val manager = androidx.core.app.NotificationManagerCompat.from(context)
        return manager.areNotificationsEnabled()
    }

    private fun createPomodoroNotificationChannels(context: Context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) return
        val manager = context.getSystemService(android.content.Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        val audioAttributes = android.media.AudioAttributes.Builder()
            .setUsage(android.media.AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val alertChannel = android.app.NotificationChannel(
            POMODORO_CHANNEL_ALERT,
            "番茄完成提醒",
            android.app.NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, audioAttributes)
            enableVibration(false)
        }

        manager.createNotificationChannel(alertChannel)
    }
}
