// File: app/src/main/java/com/example/workoutlogs/data/repository/CalendarRepository.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 07:45:00
// Scope: Repository for managing CalendarEntry data with Room in WorkoutLogs app

package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.model.CalendarEntry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepository @Inject constructor(
    private val calendarDao: CalendarDao
) {
    fun getAllEntries(): Flow<List<CalendarEntry>> {
        return calendarDao.getAllEntries()
    }

    suspend fun insert(entry: CalendarEntry) {
        calendarDao.insert(entry)
    }
}