// app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Timestamp: 2025-05-14 20:35:00 CEST
// Scope: Composable screen for managing workouts and displaying selected exercises in WorkoutLogs app
// Note: Updated to display selected exercises at top, retains SimpleCalendarView at bottom,
// BottomAppBar, and ModalBottomSheet for FullCalendarView and menu.

package com.example.workoutlogs.ui.workout

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.ui.common.FullCalendarView
import com.example.workoutlogs.ui.common.SimpleCalendarView
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController, viewModel: ExerciseViewModel = hiltViewModel()) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showMenuSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val selectedExercises by viewModel.selectedExercises.collectAsState()

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
                        text = "Workout",
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
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (selectedExercises.isEmpty()) {
                    item {
                        Text(
                            "No exercises added",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    items(selectedExercises) { exercise ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(exercise.name, style = MaterialTheme.typography.titleMedium)
                                Text("Category: ${exercise.category}", style = MaterialTheme.typography.bodyMedium)
                                exercise.notes?.let {
                                    Text("Notes: $it", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
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