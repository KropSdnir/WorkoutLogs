// File: app/src/main/java/com/example/workoutlogs/ui/workout/ExerciseViewModel.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 13:30:00
// Scope: ViewModel for managing exercise data in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {
    var name by mutableStateOf("")
    var category by mutableStateOf("")
    var notes by mutableStateOf("")

    val exercises: StateFlow<List<Exercise>> = exerciseRepository.getAllExercises()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateName(newName: String) {
        name = newName
    }

    fun updateCategory(newCategory: String) {
        category = newCategory
    }

    fun updateNotes(newNotes: String) {
        notes = newNotes
    }

    fun saveExercise(onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (name.isNotBlank() && category.isNotBlank()) {
                val exercise = Exercise(
                    name = name,
                    category = category,
                    notes = notes.takeIf { it.isNotBlank() }
                )
                exerciseRepository.insertExercise(exercise)
                println("Exercise saved: $exercise") // Debug log
                name = ""
                category = ""
                notes = ""
                onSuccess()
            }
        }
    }
}