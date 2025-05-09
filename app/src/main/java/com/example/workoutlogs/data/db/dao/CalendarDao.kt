package com.example.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutlogs.data.model.CalendarEntry
import kotlinx.coroutines.flow.Flow

// File: app/src/main/java/com/example/workoutlogs/data/db/dao/CalendarDao.kt
// Timestamp: Updated on 2025-05-09 06:10:00
// Scope: Room DAO for CalendarEntry operations in WorkoutLogs app

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendar_entries")
    fun getAllEntries(): Flow<List<CalendarEntry>>

    @Insert
    suspend fun insert(entry: CalendarEntry)
}