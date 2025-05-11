// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 19:30:00 CEST
// Scope: Main activity with navigation setup for WorkoutLogs app
// Note: Replace the existing MainActivity.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/MainActivity.kt
// with this file if changes occurred. No changes since R65.
// Verified NavGraph call for navigation.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify the NavGraph call and theme setup.
// If issues:
// 1. Share local MainActivity.kt.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.navigation.NavGraph
import com.example.workoutlogs.ui.theme.WorkoutLogsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLogsTheme {
                WorkoutLogsApp()
            }
        }
    }
}

@Composable
fun WorkoutLogsApp() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}