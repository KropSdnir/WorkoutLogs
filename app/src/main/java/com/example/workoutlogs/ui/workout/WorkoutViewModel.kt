// app/src/main/java/com/example/workoutlogs/ui/workout/WorkoutViewModel.kt
// Timestamp: 2025-05-14 05:22:00
// Scope: ViewModel for managing workout logs in WorkoutLogs app

package com.example.workoutlogs.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.data.model.WorkoutLog
import com.example.workoutlogs.data.db.dao.WorkoutLogDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutLogDao: WorkoutLogDao
) : ViewModel() {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    val workoutLogs: StateFlow<List<WorkoutLog>> = workoutLogDao.getAllWorkoutLogs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addWorkoutLog(exercises: List<Exercise>) {
        viewModelScope.launch {
            exercises.forEach { exercise ->
                workoutLogDao.insert(
                    WorkoutLog(
                        exerciseId = exercise.id,
                        date = selectedDate.value,
                        sets = null,
                        reps = null,
                        weight = null
                    )
                )
            }
        }
    }

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }
}