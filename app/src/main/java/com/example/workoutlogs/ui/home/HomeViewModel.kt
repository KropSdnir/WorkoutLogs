package com.example.workoutlogs.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workoutlogs.data.model.CalendarEntry
import com.example.workoutlogs.data.repository.CalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// File: app/src/main/java/com/example/workoutlogs/ui/home/HomeViewModel.kt
// Timestamp: Updated on 2025-05-09 06:10:00
// Scope: ViewModel for fetching CalendarEntry data for HomeScreen in WorkoutLogs app

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {
    val workoutEntries: StateFlow<List<CalendarEntry>> =
        calendarRepository.getAllEntries().stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}