// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/db/WorkoutLogsDatabase.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Room database configuration for workout, exercise, and calendar entities
package com.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.workoutlogs.data.db.dao.ExerciseDao
import com.workoutlogs.data.db.dao.WorkoutDao
import com.workoutlogs.data.model.CalendarEntry
import com.workoutlogs.data.model.Exercise
import com.workoutlogs.data.model.Workout

@Database(entities = [Workout::class, Exercise::class, CalendarEntry::class], version = 1, exportSchema = false)
abstract class WorkoutLogsDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
}