// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 18:15:00 CEST
// Scope: Main activity with navigation setup for WorkoutLogs app
// Note: Replace the existing MainActivity.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/MainActivity.kt
// with this file. Updated to use NavGraph.kt for navigation.
// Verify the NavGraph call and theme setup.
// If errors persist:
// 1. Share the full MainActivity.kt to verify setup.
// 2. Run 'gradlew :app:compileDebugKotlin --stacktrace' and share the stack trace.
// 3. Uninstall app, clean project, delete .idea folder, invalidate caches, sync Gradle.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff output.

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