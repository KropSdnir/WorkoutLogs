package com.example.workoutlogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.settings.SettingsScreen

// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Timestamp: Updated on 2025-05-09 06:30:00
// Scope: Navigation graph for WorkoutLogs app using Compose Navigation

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}