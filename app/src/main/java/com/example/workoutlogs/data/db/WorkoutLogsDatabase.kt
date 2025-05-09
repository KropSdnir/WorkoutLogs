// File: app/src/main/java/com/example/workoutlogs/data/db/WorkoutLogsDatabase.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:00:00
// Scope: Room database definition for WorkoutLogs app

package com.example.workoutlogs.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogs.data.db.converters.LocalDateConverter
import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.CalendarEntry
import com.example.workoutlogs.data.model.Exercise

@Database(
    entities = [CalendarEntry::class, Exercise::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(LocalDateConverter::class)
abstract class WorkoutLogsDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao
    abstract fun exerciseDao(): ExerciseDao
}