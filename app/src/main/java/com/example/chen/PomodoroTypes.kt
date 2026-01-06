package com.example.chen

/**
 * Shared types for Pomodoro timer functionality
 * These are public so they can be accessed by PomodoroAlarmReceiver
 */

// Notification and alarm constants
const val POMODORO_CHANNEL_ALERT = "pomodoro_finish_alert"
const val POMODORO_CHANNEL_SILENT = "pomodoro_finish_silent"
const val POMODORO_NOTIFICATION_ID = 1201

enum class TimerMode {
    POMODORO,
    COUNTDOWN,
    COUNT_UP
}

enum class PomodoroPhase {
    WORK,
    SHORT_BREAK,
    LONG_BREAK
}

enum class TimerStatus {
    IDLE,
    RUNNING,
    PAUSED,
    FINISHED
}
