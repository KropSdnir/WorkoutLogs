// File: app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-11 06:35:00 CEST
// Scope: Composable screen for displaying exercises in WorkoutLogs app
// Note: Replace the existing WorkoutExercisesScreen.kt at
// D:/Android/Development/WorkoutLogs/WorkoutLogs/app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// with this file if changes occurred. No changes made since Response 60.
// Combined "Selected" and "Add to Workout" buttons in one Row with search bar.
// BottomAppBar plus icon navigates to ExerciseNewScreen. Search bar uses fillMaxWidth(0.6f).
// Verify this file is applied correctly by checking the Timestamp, BottomAppBar content (plus icon navigates to exercise_new), and search Row (includes both buttons).
// If errors persist:
// 1. Search project for 'BottomAppBar' or 'Row' to verify no custom composables.
// 2. Uninstall app, clean project, delete .idea folder, invalidate caches, sync Gradle.
// 3. Share gradle/libs.versions.toml, app/build.gradle.kts, git diff output, and stack trace from 'gradlew :app:assembleDebug --stacktrace'.

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
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.navigate("drawer") }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Default.Home, contentDescription = "Home")
                }
                Spacer(modifier = Modifier.width(16.dp))
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
                        }
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