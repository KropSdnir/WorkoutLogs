// app/src/main/java/com/example/workoutlogs/data/db/AppDatabase.kt
// Timestamp: 2025-05-14 06:22:00
// Scope: Room database definition for WorkoutLogs app

package com.example.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.workoutlogs.data.db.converters.LocalDateConverter
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.db.dao.WorkoutLogDao
import com.example.workoutlogs.data.model.Exercise
import com.example.workoutlogs.data.model.WorkoutLog

@Database(entities = [Exercise::class, WorkoutLog::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutLogDao(): WorkoutLogDao
}