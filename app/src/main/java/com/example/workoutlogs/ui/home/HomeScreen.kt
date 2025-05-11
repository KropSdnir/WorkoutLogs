// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 22:00:00 CEST
// Scope: Composable screen for the home page of WorkoutLogs app
// Note: Replace the existing HomeScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// with this file. Fixed pointerInput errors (type mismatch, too many arguments) using detectTapGestures for long-press.
// Retains centered SimpleCalendarView, long-press calendar button (resets to today),
// and upward menu for Menu button.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content
// (upward menu, long-press calendar, centered date).
// If issues:
// 1. Share local HomeScreen.kt if calendars or menu fail.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.DpOffset
import androidx.navigation.NavController
import com.example.workoutlogs.ui.common.FullCalendarView
import com.example.workoutlogs.ui.common.SimpleCalendarView
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

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
                    IconButton(
                        onClick = { showFullCalendar = !showFullCalendar },
                        modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { selectedDate = LocalDate.now() }
                            )
                        }
                    ) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Toggle Calendar")
                    }
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Box {
                        IconButton(onClick = { showDropdown = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Workout")
                        }
                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Weight") },
                                onClick = {
                                    showDropdown = false
                                    navController.navigate("workout")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Cardio") },
                                onClick = {
                                    showDropdown = false
                                    navController.navigate("cardio_details")
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