// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 18:15:00 CEST
// Scope: Navigation graph for WorkoutLogs app
// Note: Replace the existing NavGraph.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// with this file. Fixed type mismatch in exercise_details route (String? instead of Int).
// Added "workout" route for WorkoutScreen to support HomeScreen navigation.
// Verify this file is applied correctly by checking the Timestamp and NavHost routes (includes workout).
// If errors persist:
// 1. Share the full ExerciseDetailsScreen.kt to verify its signature.
// 2. Run 'gradlew :app:compileDebugKotlin --stacktrace' and share the stack trace.
// 3. Uninstall app, clean project, delete .idea folder, invalidate caches, sync Gradle.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff output.

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
import com.example.workoutlogs.ui.workout.WorkoutScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("workout") {
            WorkoutScreen(navController)
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
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")
            ExerciseDetailsScreen(navController, exerciseId)
        }
        composable("drawer") {
            CategoriesScreen(navController) // Updated to use CategoriesScreen
        }
    }
}