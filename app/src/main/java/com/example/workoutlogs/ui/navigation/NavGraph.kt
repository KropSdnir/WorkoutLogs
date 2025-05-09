// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 07:45:00
// Scope: Navigation graph setup for WorkoutLogs app

package com.example.workoutlogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.settings.SettingsScreen
import com.example.workoutlogs.ui.workout.WorkoutScreen
import com.example.workoutlogs.ui.workout.WorkoutExercisesScreen
import com.example.workoutlogs.ui.workout.ExerciseNewScreen

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
        composable("workout") {
            WorkoutScreen(navController = navController)
        }
        composable("workout_exercises") {
            WorkoutExercisesScreen(navController = navController)
        }
        composable("exercise_new") {
            ExerciseNewScreen(navController = navController)
        }
    }
}