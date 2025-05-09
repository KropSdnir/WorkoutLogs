package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.database.CalendarDao
import com.example.workoutlogs.data.database.CalendarEntry
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