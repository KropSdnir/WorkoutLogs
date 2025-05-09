// File: WorkoutLogs/app/src/main/java/com/workoutlogs/WorkoutLogsApplication.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Initializes Hilt for dependency injection across the app
package com.workoutlogs

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WorkoutLogsApplication : Application()