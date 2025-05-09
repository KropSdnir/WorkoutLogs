// File: app/src/main/java/com/example/workoutlogs/data/db/WorkoutLogsDatabase.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 09:15:00
// Scope: Room database definition for WorkoutLogs app (temporary for schema export)

package com.example.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogs.data.db.converters.LocalDateConverter
import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.CalendarEntry

@Database(
    entities = [CalendarEntry::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class)
abstract class WorkoutLogsDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao
    abstract fun exerciseDao(): ExerciseDao
}