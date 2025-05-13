// app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutScreen.kt
// 2025-05-13 18:45:00 CEST
// Composable screen for displaying workout details in WorkoutLogs app
package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.DpOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutlogs.data.model.Exercise
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    val selectedExercises by viewModel.selectedExercises.collectAsState(initial = emptyList())
    var showMenu by remember { mutableStateOf(false) }
    val calendarState = rememberWeekCalendarState(
        startDate = LocalDate.now().minusDays(365),
        endDate = LocalDate.now().plusDays(365),
        firstDayOfWeek = java.time.DayOfWeek.MONDAY
    )

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
                            offset = DpOffset(0.dp, (-150).dp)
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
                                    navController.navigate("home")
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
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Workout",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    IconButton(onClick = { navController.navigate("exercise_new") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            WeekCalendar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                state = calendarState,
                dayContent = { day ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            )
            if (selectedExercises.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No exercises selected. Add from Exercises screen.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(selectedExercises) { exercise ->
                        ExerciseItem(exercise)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* No action for now */ },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Category: ${exercise.category}",
                    style = MaterialTheme.typography.bodyMedium
                )
                exercise.notes?.let {
                    Text(
                        text = "Notes: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}