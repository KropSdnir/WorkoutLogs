package com.example.workoutlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.navigation.NavGraph
import com.example.workoutlogs.ui.theme.WorkoutLogsTheme
import dagger.hilt.android.AndroidEntryPoint

// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Timestamp: Updated on 2025-05-09 06:30:00
// Scope: Main entry point for WorkoutLogs app with Hilt, Compose, and Navigation setup

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutLogsTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}