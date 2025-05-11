// File: app/src/main/java/com/example/workoutlogs/MainActivity.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-12 15:24:00 GMT+03:00
// Scope: Main activity with navigation setup for WorkoutLogs app
// Note: Update the existing MainActivity.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/MainActivity.kt
// by adding the "workout" route to NavHost as shown below.
// This snippet shows only the NavHost setup; ensure the rest of MainActivity.kt (e.g., setContent, theme) remains unchanged.
// Verify the NavHost includes the "workout" route for WorkoutScreen.
// If errors persist:
// 1. Share the full MainActivity.kt to verify NavHost setup.
// 2. Uninstall app, clean project, delete .idea folder, invalidate caches, sync Gradle.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff output, and stack trace from 'gradlew :app:assembleDebug --stacktrace'.

package com.example.workoutlogs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.workout.WorkoutScreen
import com.example.workoutlogs.ui.workout.WorkoutExercisesScreen
import com.example.workoutlogs.ui.workout.ExerciseNewScreen
import com.example.workoutlogs.ui.categories.CategoriesScreen
import com.example.workoutlogs.ui.details.ExerciseDetailsScreen

@Composable
fun WorkoutLogsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("workout") { WorkoutScreen(navController) }
        composable("workout_exercises") { WorkoutExercisesScreen(navController) }
        composable("exercise_new") { ExerciseNewScreen(navController) }
        composable("drawer") { CategoriesScreen(navController) }
        composable("exercise_details/{exerciseId}") { backStackEntry ->
            ExerciseDetailsScreen(navController, backStackEntry.arguments?.getString("exerciseId"))
        }
    }
}