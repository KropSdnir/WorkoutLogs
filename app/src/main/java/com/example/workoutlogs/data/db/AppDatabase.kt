// app/src/main/java/com/example/workoutlogs/data/db/AppDatabase.kt
// Timestamp: 2025-05-14 21:45:00 CEST
// Scope: Room database definition for WorkoutLogs app

package com.example.workoutlogs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.Exercise

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}