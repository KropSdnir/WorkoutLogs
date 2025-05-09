// File: app/src/main/java/com/example/workoutlogs/ui/navigation/NavGraph.kt
// Timestamp: Updated on 2025-05-09 18:00:00
// Scope: Defines navigation routes for the app using Jetpack Navigation

package com.example.workoutlogs.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.workoutlogs.ui.cardio.CardioDetailScreen
import com.example.workoutlogs.ui.exercise.ExerciseDetailScreen
import com.example.workoutlogs.ui.home.HomeScreen
import com.example.workoutlogs.ui.settings.SettingsScreen
import com.example.workoutlogs.ui.workout.WorkoutScreen

@Composable
fun NavGraph(navController: NavHostController) {
    composable("home") {
        HomeScreen(navController = navController)
    }
    composable("workout") {
        WorkoutScreen()
    }
    composable("exercises") {
        ExerciseDetailScreen()
    }
    composable("settings") {
        SettingsScreen()
    }
    composable("exercise_detail") {
        ExerciseDetailScreen()
    }
    composable("cardio_detail") {
        CardioDetailScreen()
    }
}

@Composable
fun WorkoutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Workout Screen",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun ExerciseDetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Exercise Detail Screen",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun CardioDetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cardio Detail Screen",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}