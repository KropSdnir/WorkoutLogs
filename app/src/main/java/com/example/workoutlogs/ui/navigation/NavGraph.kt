// File: WorkoutLogs/app/src/main/java/com/workoutlogs/ui/navigation/NavGraph.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Sets up navigation graph for app screens
package com.workoutlogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.workoutlogs.ui.cardio.CardioDetailScreen
import com.workoutlogs.ui.exercise.ExerciseDetailScreen
import com.workoutlogs.ui.home.HomeScreen
import com.workoutlogs.ui.settings.SettingsScreen
import com.workoutlogs.ui.workout.WorkoutScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("exercise_detail") {
            ExerciseDetailScreen()
        }
        composable("cardio_detail") {
            CardioDetailScreen()
        }
        composable("workout") {
            WorkoutScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}