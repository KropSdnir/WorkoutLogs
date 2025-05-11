// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-10 00:23:00
// Scope: Navigation graph for WorkoutLogs app

package com.example.workoutlogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.workoutlogs.ui.categories.CategoriesScreen
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.workout.ExerciseDetailsScreen
import com.example.workoutlogs.ui.workout.ExerciseNewScreen
import com.example.workoutlogs.ui.workout.WorkoutExercisesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("workout_exercises") {
            WorkoutExercisesScreen(navController)
        }
        composable("exercise_new") {
            ExerciseNewScreen(navController)
        }
        composable("categories") {
            CategoriesScreen(navController)
        }
        composable("exercise_details/{exerciseId}") { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")?.toIntOrNull() ?: 0
            ExerciseDetailsScreen(navController, exerciseId)
        }
        composable("drawer") {
            // Placeholder for drawer navigation
        }
    }
}