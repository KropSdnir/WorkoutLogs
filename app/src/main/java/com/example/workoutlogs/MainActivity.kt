// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Entry point for the app, sets up Jetpack Compose and navigation host

package com.example.workoutlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.navigation.NavGraph
import com.example.workoutlogs.ui.theme.WorkoutLogsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutLogsTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        NavGraph(navController = navController)
    }
}