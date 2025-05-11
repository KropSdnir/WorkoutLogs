// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 19:30:00 CEST
// Scope: Composable screen for managing workouts in WorkoutLogs app
// Note: Replace the existing WorkoutScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// with this file. Matches HomeScreen UI per original instructions, with BottomAppBar.
// Plus icon navigates to workout_exercises with dropdown for Weight and Cardio.
// SimpleCalendarView (toggleable) and FullCalendarView (month grid) restored from ui/common/.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content
// (plus icon to workout_exercises, dropdown, calendar icon toggles SimpleCalendarView),
// and calendar displays.
// If crash persists:
// 1. Share local WorkoutScreen.kt and ExerciseNewScreen.kt.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff, Logcat output.

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.workoutlogs.ui.common.FullCalendarView
import com.example.workoutlogs.ui.common.SimpleCalendarView

@Composable
fun WorkoutScreen(navController: NavController) {
    val showCalendar = remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }

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
                    IconButton(onClick = { showCalendar.value = !showCalendar.value }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Toggle Calendar")
                    }
                    Text(
                        text = "Workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Box {
                        IconButton(onClick = { showDropdown = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                        }
                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Weight") },
                                onClick = {
                                    showDropdown = false
                                    navController.navigate("exercise_details/null")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Cardio") },
                                onClick = {
                                    showDropdown = false
                                    navController.navigate("cardio_details")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Exercises") },
                                onClick = {
                                    showDropdown = false
                                    navController.navigate("workout_exercises")
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showCalendar.value) {
                SimpleCalendarView()
            }
            FullCalendarView()
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Screen Placeholder")
            }
        }
    }
}