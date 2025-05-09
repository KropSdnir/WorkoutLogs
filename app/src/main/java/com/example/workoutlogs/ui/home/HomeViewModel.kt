// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeViewModel.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 07:45:00
// Scope: ViewModel for HomeScreen to manage workout entries in WorkoutLogs app

package com.example.workoutlogs.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogs.data.model.CalendarEntry
import com.example.workoutlogs.data.repository.CalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {

    val workoutEntries: StateFlow<List<CalendarEntry>> = calendarRepository.getAllEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addWorkoutEntry(entry: CalendarEntry) {
        viewModelScope.launch {
            calendarRepository.insert(entry)
        }
    }
}