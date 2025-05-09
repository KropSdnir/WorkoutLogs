// File: app/src/main/java/com/example/workoutlogs/data/db/dao/CalendarDao.kt
// Timestamp: Updated on 2025-05-09 20:00:00
// Scope: DAO for accessing calendar entries in Room database

package com.example.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutlogs.data.model.CalendarEntry
import java.time.LocalDate

@Dao
interface CalendarDao {
    @Insert
    suspend fun insert(entry: CalendarEntry)

    @Query("SELECT * FROM calendar_entries WHERE date = :date")
    suspend fun getEntryByDate(date: LocalDate): CalendarEntry?
}