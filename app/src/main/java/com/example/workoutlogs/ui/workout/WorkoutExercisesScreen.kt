// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 20:30:00 CEST
// Scope: Composable screen for displaying exercises in WorkoutLogs app
// Note: Replace the existing WorkoutExercisesScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// with this file. Updated Menu button for upward menu.
// Sourced from https://github.com/KropSdnir/WorkoutLogs.
// Verify this file is applied correctly by checking the Timestamp and BottomAppBar content
// (upward menu).
// If issues:
// 1. Share local WorkoutExercisesScreen.kt.
// 2. Run 'gradlew :app:assembleDebug --stacktrace' and share stack trace.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff.

package com.example.workoutlogs.ui.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
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

@Composable
fun WorkoutExercisesScreen(
    navController: NavController,
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    val exercises by viewModel.exercises.collectAsState()
    val selectedExercises by viewModel.selectedExercises.collectAsState()
    val categories by viewModel.categories.collectAsState()
    var searchQuery by remember { mutableStateOf(viewModel.searchQuery) }
    var selectedCategory by remember { mutableStateOf(viewModel.selectedCategory) }
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
                                    navController.navigate("home") // Navigate to home for calendar
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
                        text = "Exercises",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.updateSearchQuery(it)
                    },
                    label = { Text("Search Exercises") },
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    Button(
                        onClick = { viewModel.toggleShowSelectedOnly() },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Selected")
                    }
                    Button(
                        onClick = { navController.navigate("workout_exercises") },
                        enabled = selectedExercises.isNotEmpty()
                    ) {
                        Text("Add to Workout")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (selectedCategory.isBlank()) "All Categories" else selectedCategory)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("All Categories") },
                            onClick = {
                                selectedCategory = ""
                                viewModel.updateSelectedCategory("")
                                expanded = false
                            }
                        )
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    viewModel.updateSelectedCategory(category)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            if (exercises.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No exercises yet. Add one!")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(exercises) { exercise ->
                        ExerciseItem(
                            exercise = exercise,
                            onToggleSelection = { viewModel.toggleExerciseSelection(exercise.id, !exercise.isSelected) },
                            onDetailsClick = { navController.navigate("exercise_details/${exercise.id}") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onToggleSelection: () -> Unit,
    onDetailsClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleSelection() },
        colors = CardDefaults.cardColors(
            containerColor = if (exercise.isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
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
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Details") },
                        onClick = {
                            expanded = false
                            onDetailsClick()
                        }
                    )
                }
            }
        }
    }
}