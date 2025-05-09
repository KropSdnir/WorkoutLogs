package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.db.CalendarEntry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// File: app/src/main/java/com/example/workoutlogs/data/repository/CalendarRepository.kt
// Timestamp: Updated on 2025-05-09 05:36:00
// Scope: Repository for managing CalendarEntry data with Room

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