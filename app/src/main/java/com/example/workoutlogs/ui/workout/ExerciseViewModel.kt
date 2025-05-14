// app/src/main/java/com/example/workoutlogs/ui/workout/ExerciseViewModel.kt
// Timestamp: 2025-05-14 21:25:00 CEST
// Scope: ViewModel for managing exercises and persistent added exercises in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    private val _selectedExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val selectedExercises: StateFlow<List<Exercise>> = _selectedExercises.asStateFlow()

    private val _addedExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val addedExercises: StateFlow<List<Exercise>> = _addedExercises.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: String get() = _searchQuery.value

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: String get() = _selectedCategory.value

    private val _showSelectedOnly = MutableStateFlow(false)
    val showSelectedOnly: StateFlow<Boolean> = _showSelectedOnly.asStateFlow()

    init {
        loadExercises()
        loadCategories()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            exerciseRepository.getAllExercises().collect { exercises ->
                val allExercises = exercises.map { exercise ->
                    Exercise(
                        id = exercise.id,
                        name = exercise.name,
                        category = exercise.category,
                        notes = exercise.notes,
                        isSelected = _selectedExercises.value.any { it.id == exercise.id }
                    )
                }
                _exercises.value = allExercises
                if (_showSelectedOnly.value) {
                    _exercises.value = allExercises.filter { it.isSelected }
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            exerciseRepository.getAllCategories().collect { categoryList ->
                _categories.value = listOf("") + categoryList
            }
        }
    }

    fun insertExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.insertExercise(exercise)
        }
    }

    fun toggleExerciseSelection(exerciseId: Int, isSelected: Boolean) {
        viewModelScope.launch {
            val currentSelected = _selectedExercises.value.toMutableList()
            val exercise = _exercises.value.find { it.id == exerciseId }
            exercise?.let {
                if (isSelected) {
                    if (!currentSelected.contains(it)) {
                        currentSelected.add(it.copy(isSelected = true))
                    }
                } else {
                    currentSelected.removeAll { e -> e.id == exerciseId }
                }
                _selectedExercises.value = currentSelected
            }
            if (_showSelectedOnly.value) {
                _exercises.value = _exercises.value.filter { e ->
                    !e.isSelected || _selectedExercises.value.any { it.id == e.id }
                }
            }
        }
    }

    fun addSelectedExercises() {
        viewModelScope.launch {
            val currentAdd = _addedExercises.value.toMutableList()
            val newExercises = _selectedExercises.value.filter { selected ->
                !currentAdd.any { it.id == selected.id }
            }
            currentAdd.addAll(newExercises)
            _addedExercises.value = currentAdd
            _selectedExercises.value = emptyList()
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            combine(
                exerciseRepository.getAllExercises(),
                _searchQuery,
                _selectedCategory
            ) { exercises, search, category ->
                exercises.map { exercise ->
                    Exercise(
                        id = exercise.id,
                        name = exercise.name,
                        category = exercise.category,
                        notes = exercise.notes,
                        isSelected = _selectedExercises.value.any { it.id == exercise.id }
                    )
                }.filter { exercise ->
                    (search.isEmpty() || exercise.name.contains(search, ignoreCase = true)) &&
                            (category.isEmpty() || exercise.category == category)
                }
            }.collect { filteredExercises ->
                _exercises.value = filteredExercises
            }
        }
    }

    fun updateSelectedCategory(category: String) {
        _selectedCategory.value = category
        updateSearchQuery(_searchQuery.value)
    }

    fun toggleShowSelectedOnly() {
        _showSelectedOnly.value = !_showSelectedOnly.value
        viewModelScope.launch {
            if (_showSelectedOnly.value) {
                _exercises.value = _exercises.value.filter { it.isSelected }
            } else {
                loadExercises()
            }
        }
    }
}