// File: app/src/main/java/com/example/workoutlogs/ui/workout/ExerciseDetailsScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 05:24:00 GMT+03:00
// Scope: Composable screen for exercise details in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ExerciseDetailsScreen(
    navController: NavController,
    exerciseId: String?
) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row {
                    IconButton(onClick = { navController.navigate("drawer") }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    Spacer(Modifier.weight(1f))
                    Text("Exercise Details", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { /* No-op */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        }
    ) { padding ->
        Text(
            "Exercise Details Placeholder (ID: $exerciseId)",
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}