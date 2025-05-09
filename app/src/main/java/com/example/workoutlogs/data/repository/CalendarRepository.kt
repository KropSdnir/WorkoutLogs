// File: app/src/main/java/com/example/workoutlogs/data/repository/CalendarRepository.kt
// Timestamp: Updated on 2025-05-09 22:00:00
// Scope: Repository for accessing and managing calendar entries in Room database

package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.model.CalendarEntry
import java.time.LocalDate
import javax.inject.Inject

class CalendarRepository @Inject constructor(
    private val calendarDao: CalendarDao
) {
    suspend fun insertCalendarEntry(entry: CalendarEntry) {
        calendarDao.insert(entry)
    }

    suspend fun getCalendarEntry(date: LocalDate): CalendarEntry? {
        return calendarDao.getEntryByDate(date)
    }
}