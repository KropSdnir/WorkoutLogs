// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-12 01:30:00 CEST
// Scope: Main activity for WorkoutLogs app, sets up navigation and theme
// Note: Replace the existing MainActivity.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/MainActivity.kt
// with this file. Fixed compilation error by adding rememberNavController() for NavGraph.
// Retains @AndroidEntryPoint for Hilt compatibility.
// Sourced from R82 provided code.
// Verify this file is applied correctly by checking the Timestamp, @AndroidEntryPoint,
// rememberNavController(), and successful compilation.
// If issues:
// 1. Share local MainActivity.kt if compilation or navigation fails.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share Logcat for navigation issues.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, NavGraph.kt.

package com.example.workoutlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.navigation.NavGraph
import com.example.workoutlogs.ui.theme.WorkoutLogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLogsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}