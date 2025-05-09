// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/db/dao/ExerciseDao.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: DAO for CRUD operations on exercises
package com.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise)

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): Flow<List<Exercise>>
}