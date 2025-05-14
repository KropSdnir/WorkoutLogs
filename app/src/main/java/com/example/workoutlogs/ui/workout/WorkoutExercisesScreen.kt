// app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutExercisesScreen.kt
// Timestamp: 2025-05-14 19:10:00
// Scope: Composable screen for selecting and managing exercises in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workoutlogs.data.model.Exercise
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutExercisesScreen(
    navController: NavController,
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    workoutViewModel: WorkoutViewModel = hiltViewModel()
) {
    val exercises by exerciseViewModel.exercises.collectAsState()
    val selectedExercises by exerciseViewModel.selectedExercises.collectAsState()
    val categories by exerciseViewModel.categories.collectAsState()
    val searchQuery by remember { mutableStateOf(exerciseViewModel.searchQuery) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedExerciseId by remember { mutableStateOf<Int?>(null) }
    var sets by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Exercises") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("exercise_new") }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Exercise"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { exerciseViewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search exercises...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { exerciseViewModel.updateSearchQuery("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilterChip(
                    selected = exerciseViewModel.showSelectedOnly,
                    onClick = { exerciseViewModel.toggleShowSelectedOnly() },
                    label = { Text("Selected Only") }
                )
                var expanded by remember { mutableStateOf(false) }
                Box {
                    FilterChip(
                        selected = exerciseViewModel.selectedCategory.isNotEmpty(),
                        onClick = { expanded = true },
                        label = {
                            Text(
                                exerciseViewModel.selectedCategory.ifEmpty {
                                    "All Categories"
                                }
                            )
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("All Categories") },
                            onClick = {
                                exerciseViewModel.updateSelectedCategory("")
                                expanded = false
                            }
                        )
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    exerciseViewModel.updateSelectedCategory(category)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(exercises, key = { it.id }) { exercise ->
                    ExerciseItem(
                        exercise = exercise,
                        isSelected = exercise.isSelected,
                        onToggleSelection = { isSelected ->
                            exerciseViewModel.toggleExerciseSelection(exercise.id, isSelected)
                        },
                        onClick = {
                            selectedExerciseId = exercise.id
                            showDialog = true
                        }
                    )
                }
            }
            if (selectedExercises.isNotEmpty()) {
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Add Selected to Workout")
                }
            }
        }
        if (showDialog && selectedExerciseId != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Workout Log") },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = sets,
                            onValueChange = { sets = it },
                            label = { Text("Sets") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = reps,
                            onValueChange = { reps = it },
                            label = { Text("Reps") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = weight,
                            onValueChange = { weight = it },
                            label = { Text("Weight (kg)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            workoutViewModel.addWorkoutLog(
                                exerciseId = selectedExerciseId!!,
                                date = LocalDate.now(),
                                sets = sets.toIntOrNull(),
                                reps = reps.toIntOrNull(),
                                weight = weight.toDoubleOrNull()
                            )
                            showDialog = false
                            sets = ""
                            reps = ""
                            weight = ""
                            selectedExerciseId = null
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    isSelected: Boolean,
    onToggleSelection: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exercise.category,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Checkbox(
                checked = isSelected,
                onCheckedChange = onToggleSelection
            )
        }
    }
}