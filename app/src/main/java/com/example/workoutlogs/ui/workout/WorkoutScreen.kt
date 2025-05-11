// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 23:00:00 CEST
// Scope: Composable screen for managing workouts in WorkoutLogs app
// Note: Replace the existing WorkoutScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// with this file. Fixed Modifier.clickable error (no onLongClick) using combinedClickable,
// added ExperimentalMaterial3Api for ModalBottomSheet, retained plus navigation.
// Retains centered SimpleCalendarView, long-press calendar, bottom sheet menu,
// and plus to workout_exercises.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content
// (bottom sheet menu, long-press calendar, plus to workout_exercises, centered date).
// If issues:
// 1. Share local WorkoutScreen.kt if calendars, menu, or navigation fail.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showMenuSheet by remember { mutableStateOf(false) }

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
                    IconButton(onClick = { showMenuSheet = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    IconButton(
                        onClick = { showFullCalendar = !showFullCalendar },
                        modifier = Modifier.combinedClickable(
                            onClick = { showFullCalendar = !showFullCalendar },
                            onLongClick = { selectedDate = LocalDate.now() }
                        )
                    ) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Toggle Calendar")
                    }
                    Text(
                        text = "Workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            navController.navigate("workout_exercises") {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = false
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
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
            if (showFullCalendar) {
                FullCalendarView(
                    selectedDate = selectedDate,
                    onDateSelected = { date ->
                        selectedDate = date
                        showFullCalendar = false
                    }
                )
            } else {
                SimpleCalendarView(
                    selectedDate = selectedDate,
                    onClick = { showFullCalendar = true }
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Screen Placeholder")
            }
        }
        if (showMenuSheet) {
            ModalBottomSheet(
                onDismissRequest = { showMenuSheet = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextButton(
                        onClick = {
                            showMenuSheet = false
                            navController.navigate("home")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Home")
                    }
                    TextButton(
                        onClick = {
                            showMenuSheet = false
                            showFullCalendar = !showFullCalendar
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Calendar")
                    }
                    TextButton(
                        onClick = {
                            showMenuSheet = false
                            navController.navigate("workout")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Workout")
                    }
                    TextButton(
                        onClick = {
                            showMenuSheet = false
                            navController.navigate("workout_exercises")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Exercises")
                    }
                    TextButton(
                        onClick = {
                            showMenuSheet = false
                            navController.navigate("settings")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Settings")
                    }
                }
            }
        }
    }
}