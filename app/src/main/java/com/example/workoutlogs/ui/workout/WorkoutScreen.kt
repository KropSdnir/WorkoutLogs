// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 06:35:00 CEST
// Scope: Composable screen for managing workouts in WorkoutLogs app
// Note: Replace the existing WorkoutScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// with this file. Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Fixed imports to resolve 'Menu' and package import errors.
// BottomAppBar includes Menu, Home, "Workouts" title, and plus icon (navigates to exercise_new).
// Verify this file is applied correctly by checking the Timestamp and BottomAppBar content.
// If errors persist:
// 1. Search project for 'BottomAppBar' or 'Row' to verify no custom composables.
// 2. Uninstall app, clean project, delete .idea folder, invalidate caches, sync Gradle.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff output, MainActivity.kt, and stack trace from 'gradlew :app:assembleDebug --stacktrace'.

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WorkoutScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate("drawer") }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    Text(
                        text = "Workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(onClick = { navController.navigate("exercise_new") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text("Workout Screen Placeholder")
        }
    }
}