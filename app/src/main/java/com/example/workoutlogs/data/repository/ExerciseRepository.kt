// app/src/main/java/com/example/workoutlogs/data/repository/ExerciseRepository.kt
// Timestamp: 2025-05-14 19:25:00
// Scope: Repository for managing exercise data in WorkoutLogs app

package com.example.workoutlogs.data.repository

import android.util.Log
import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    suspend fun insertExercise(exercise: Exercise) {
        try {
            exerciseDao.insertExercise(exercise)
            Log.d("ExerciseRepository", "Inserted exercise: ${exercise.name}")
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "Error inserting exercise: ${e.message}", e)
        }
    }

    fun getSelectedExercises(): Flow<List<Exercise>> {
        return exerciseDao.getSelectedExercises()
    }

    fun getCategories(): Flow<List<String>> {
        return exerciseDao.getCategories()
    }

    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    suspend fun updateSelection(id: Int, isSelected: Boolean) {
        try {
            exerciseDao.updateSelection(id, isSelected)
            Log.d("ExerciseRepository", "Updated selection for exercise ID $id to $isSelected")
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "Error updating selection: ${e.message}", e)
        }
    }
}