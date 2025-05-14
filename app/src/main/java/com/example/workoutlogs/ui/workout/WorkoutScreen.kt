// app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// Timestamp: 2025-05-14 05:14:00
// Scope: Composable screen for managing workouts and displaying selected exercises in WorkoutLogs app

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.data.model.WorkoutLog
import com.example.workoutlogs.ui.common.FullCalendarView
import com.example.workoutlogs.ui.common.SimpleCalendarView
import com.example.workoutlogs.ui.viewmodel.ExerciseViewModel
import com.example.workoutlogs.ui.viewmodel.WorkoutViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController, viewModel: WorkoutViewModel = hiltViewModel()) {
    val exerciseViewModel: ExerciseViewModel = hiltViewModel()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showFullCalendar by remember { mutableStateOf(false) }
    var showMenuSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val workoutLogs by viewModel.workoutLogs.collectAsState(initial = emptyList())
    val logsByDate = workoutLogs.groupBy { it.date }

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
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val selectedDateLogs = logsByDate[selectedDate] ?: emptyList()
                if (selectedDateLogs.isEmpty()) {
                    item {
                        Text(
                            text = "No exercises added for ${selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                } else {
                    items(selectedDateLogs, key = { it.id }) { log ->
                        WorkoutLogItem(
                            log = log,
                            exercise = exerciseViewModel.getExerciseById(log.exerciseId)
                        )
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

@Composable
fun WorkoutLogItem(log: WorkoutLog, exercise: Exercise?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = exercise?.name ?: "Unknown Exercise",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Category: ${exercise?.category ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Sets: ${log.sets ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Reps: ${log.reps ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Weight: ${log.weight ?: "N/A"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}