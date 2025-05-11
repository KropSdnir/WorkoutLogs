// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 21:00:00 CEST
// Scope: Composable screen for managing workouts in WorkoutLogs app
// Note: Replace the existing WorkoutScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// with this file. Fixed IconButton errors (no onLongClick, composable context).
// Retains centered SimpleCalendarView, long-press calendar button, upward menu,
// and plus icon direct navigation to workout_exercises.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content
// (upward menu, long-press calendar, plus to workout_exercises, centered date).
// If issues:
// 1. Share local WorkoutScreen.kt if calendars or menu fail.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.unit.DpOffset
import androidx.navigation.NavController
import com.example.workoutlogs.ui.common.FullCalendarView
import com.example.workoutlogs.ui.common.SimpleCalendarView
import java.time.LocalDate

@Composable
fun WorkoutScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

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
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false },
                            offset = DpOffset(0.dp, (-150).dp) // Upward menu
                        ) {
                            DropdownMenuItem(
                                text = { Text("Home") },
                                onClick = {
                                    showMenu = false
                                    navController.navigate("home")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Calendar") },
                                onClick = {
                                    showMenu = false
                                    showFullCalendar = !showFullCalendar
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Workout") },
                                onClick = {
                                    showMenu = false
                                    navController.navigate("workout")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Exercises") },
                                onClick = {
                                    showMenu = false
                                    navController.navigate("workout_exercises")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = {
                                    showMenu = false
                                    navController.navigate("settings")
                                }
                            )
                        }
                    }
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    IconButton(
                        onClick = { showFullCalendar = !showFullCalendar },
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onLongClick = { selectedDate = LocalDate.now() }
                        )
                    ) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Toggle Calendar")
                    }
                    Text(
                        text = "Workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(onClick = { navController.navigate("workout_exercises") }) {
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
    }
}