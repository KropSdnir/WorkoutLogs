// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 19:30:00 CEST
// Scope: Navigation graph for WorkoutLogs app
// Note: Replace the existing NavGraph.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// with this file. Added cardio_details route for plus icon dropdown.
// Verified workout_exercises route for WorkoutScreen plus icon.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp and NavHost routes.
// If issues:
// 1. Share local NavGraph.kt and ExerciseNewScreen.kt.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        composable("cardio_details") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Cardio Detail Screen Placeholder")
            }
        }
        composable("drawer") {
            CategoriesScreen(navController)
        }
    }
}