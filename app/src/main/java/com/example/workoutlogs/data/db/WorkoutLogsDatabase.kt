// File: app/src/main/java/com/example/workoutlogs/data/db/WorkoutLogsDatabase.kt
// Timestamp: Updated on 2025-05-09 20:00:00
// Scope: Room database for storing calendar entries and other data

package com.example.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogs.data.model.CalendarEntry
import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.db.converters.LocalDateConverter

@Database(entities = [CalendarEntry::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class WorkoutLogsDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao
}