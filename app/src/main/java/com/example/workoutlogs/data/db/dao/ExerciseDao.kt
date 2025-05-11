// File: app/src/main/java/com/example/workoutlogs/data/db/dao/ExerciseDao.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-10 00:23:00
// Scope: Room DAO for exercise-related database operations in WorkoutLogs app

package com.example.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE isSelected = 1")
    fun getSelectedExercises(): Flow<List<Exercise>>

    @Query("UPDATE exercises SET isSelected = :isSelected WHERE id = :id")
    suspend fun updateSelection(id: Int, isSelected: Boolean)

    @Query("SELECT DISTINCT category FROM exercises")
    fun getCategories(): Flow<List<String>>
}