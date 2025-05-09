// File: app/src/main/java/com/example/workoutlogs/ui/workout/ExerciseViewModel.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:00:00
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {
    var name by mutableStateOf("")
        private set
    var category by mutableStateOf("")
        private set
    var notes by mutableStateOf("")
        private set

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
                onSuccess()
            }
        }
    }
}