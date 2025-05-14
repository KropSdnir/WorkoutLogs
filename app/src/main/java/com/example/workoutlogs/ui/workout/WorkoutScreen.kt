// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-12 02:00:00 CEST
// Scope: Composable screen for managing workouts in WorkoutLogs app
// Note: Replace the existing WorkoutScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// with this file. Updated ModalBottomSheet for FullCalendarView to appear above BottomAppBar,
// retains SimpleCalendarView at bottom, long-press calendar, bottom sheet menu, plus navigation.
// Sourced from R83 provided code.
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar visibility,
// FullCalendarView overlay above BottomAppBar, and other functionality.
// If issues:
// 1. Share local WorkoutScreen.kt if BottomAppBar is covered, navigation, or calendar position fails.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share Logcat for navigation issues (search for "WorkoutScreen: Navigating", "Navigation error").
// 4. Share gradle/libs.versions.toml, app/build.gradle.kts, WorkoutExercisesScreen.kt.

package com.example.workoutlogs.ui.workout

import android.util.Log
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
    val sheetState = rememberModalBottomSheetState()

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
                    IconButton(onClick = { showFullCalendar = true }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Toggle Calendar",
                            modifier = Modifier.combinedClickable(
                                onClick = { showFullCalendar = true },
                                onLongClick = { selectedDate = LocalDate.now() }
                            )
                        )
                    }
                    Text(
                        text = "Workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            try {
                                Log.d("WorkoutScreen", "Navigating to workout_exercises")
                                Log.d("WorkoutScreen", "NavController state: current=${navController.currentDestination?.route}")
                                navController.navigate("workout_exercises")
                                Log.d("WorkoutScreen", "Navigation triggered successfully")
                            } catch (e: Exception) {
                                Log.e("WorkoutScreen", "Navigation error: ${e.message}", e)
                            }
                        }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(androidx.compose.ui.unit.LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(androidx.compose.ui.unit.LayoutDirection.Ltr)
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Screen Placeholder")
            }
            SimpleCalendarView(
                selectedDate = selectedDate,
                onClick = { showFullCalendar = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = innerPadding.calculateBottomPadding())
            )
        }
        if (showMenuSheet) {
            ModalBottomSheet(
                onDismissRequest = { showMenuSheet = false },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-innerPadding.calculateBottomPadding()))
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
                            showFullCalendar = true
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
        if (showFullCalendar) {
            ModalBottomSheet(
                onDismissRequest = { showFullCalendar = false },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-innerPadding.calculateBottomPadding()))
            ) {
                FullCalendarView(
                    selectedDate = selectedDate,
                    onDateSelected = { date ->
                        selectedDate = date
                        showFullCalendar = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}