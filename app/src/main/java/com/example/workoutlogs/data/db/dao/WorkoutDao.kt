// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/db/dao/WorkoutDao.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: DAO for CRUD operations on workouts
package com.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.workoutlogs.data.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert
    suspend fun insert(workout: Workout)

    @Query("SELECT * FROM workouts ORDER BY date DESC")
    fun getAllWorkouts(): Flow<List<Workout>>
}