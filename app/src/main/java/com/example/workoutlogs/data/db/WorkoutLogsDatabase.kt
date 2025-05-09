package com.example.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogs.data.db.converters.LocalDateConverter
import com.example.workoutlogs.data.db.dao.CalendarDao

// File: app/src/main/java/com/example/workoutlogs/data/db/WorkoutLogsDatabase.kt
// Timestamp: Updated on 2025-05-09 05:50:00
// Scope: Room database definition for WorkoutLogs app

@Database(entities = [CalendarEntry::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class WorkoutLogsDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao
}