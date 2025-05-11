// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 23:30:00 CEST
// Scope: Composable screen for the home page of WorkoutLogs app
// Note: Replace the existing HomeScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/home/HomeScreen.kt
// with this file. Fixed long-click calendar (moved combinedClickable to Icon),
// retained ModalBottomSheet menu and plus dropdown.
// Retains centered SimpleCalendarView, long-press calendar (resets to today),
// bottom sheet menu.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content
// (bottom sheet menu, long-press calendar, centered date).
// If issues:
// 1. Share local HomeScreen.kt if long-click or navigation fails.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share Logcat for long-click or navigation issues.
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
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
                    IconButton(onClick = { showFullCalendar = !showFullCalendar }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Toggle Calendar",
                            modifier = Modifier.combinedClickable(
                                onClick = { showFullCalendar = !showFullCalendar },
                                onLongClick = { selectedDate = LocalDate.now() }
                            )
                        )
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
                            onDismissRequest = { showDropdown = false },
                            offset = DpOffset(0.dp, (-50).dp)
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