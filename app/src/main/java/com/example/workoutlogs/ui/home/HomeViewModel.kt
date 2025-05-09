// File: WorkoutLogs/app/src/main/java/com/workoutlogs/ui/home/HomeViewModel.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: ViewModel for Home Screen, managing calendar and tab state
package com.workoutlogs.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val showCalendar = mutableStateOf(false)
    val selectedTab = mutableStateOf(0)

    fun toggleCalendar() {
        showCalendar.value = !showCalendar.value
    }

    fun selectTab(index: Int) {
        selectedTab.value = index
    }
}