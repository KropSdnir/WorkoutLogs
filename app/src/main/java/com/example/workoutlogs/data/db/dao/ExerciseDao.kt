// app/src/main/java/com/example/workoutlogs/data/db/dao/ExerciseDao.kt
// Timestamp: 2025-05-14 19:03:00
// Scope: Room DAO for exercise operations in WorkoutLogs app

package com.example.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercisesSnapshot(): List<Exercise>

    @Query("UPDATE exercises SET isSelected = :isSelected WHERE id = :id")
    suspend fun updateSelection(id: Int, isSelected: Boolean)

    @Insert
    suspend fun insert(exercise: Exercise)
}