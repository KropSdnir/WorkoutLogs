// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 11:24:00 GMT+03:00
// Scope: Composable screen for displaying exercises in WorkoutLogs app
// Note: Replace the existing WorkoutExercisesScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// with this file. The BottomAppBar uses a Row to provide RowScope for Modifier.weight(1f).
// If the error persists:
// 1. Share lines 130–145 of your WorkoutExercisesScreen.kt to identify the weight error at line 138.
// 2. Comment out the current BottomAppBar content and uncomment the fallback version below.
// 3. Search project for 'BottomAppBar' to verify no custom composable is used.
// 4. Clean project, invalidate caches, check Compose/Kotlin versions, sync Gradle (see instructions).
// 5. Share gradle/libs.versions.toml, app/build.gradle.kts, and git diff output.

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

    Scaffold(
        bottomBar = {
            BottomAppBar {
                // Row provides RowScope for weight modifier
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
                    Spacer(modifier = Modifier.weight(1f)) // Spacer balances space
                    Text("Exercises", style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = { navController.navigate("exercise_new") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }

                // Fallback BottomAppBar (uncomment if error persists):
                /*
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
                    Text("Exercises", style = MaterialTheme.typography.titleMedium)
                    IconButton(onClick = { navController.navigate("exercise_new") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
                */
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Row 1: Search Bar and Selected Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.updateSearchQuery(it)
                    },
                    label = { Text("Search Exercises") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.toggleShowSelectedOnly() },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("Selected")
                }
            }

            // Row 2: Category Dropdown and Add to Workout
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier.weight(1f)
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
                                -UU text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    viewModel.updateSelectedCategory(category)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        // Selected exercises are already tracked via isSelected
                        navController.navigate("workout_exercises")
                    },
                    enabled = selectedExercises.isNotEmpty()
                ) {
                    Text("Add to Workout")
                }
            }

            // Exercise List
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
            horizontalArrangement = Arrangement.SpaceBetween, // Fixed typo from SpaceSevere
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