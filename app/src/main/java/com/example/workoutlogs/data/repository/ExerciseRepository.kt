// File: app/src/main/java/com/example/workoutlogs/data/repository/ExerciseRepository.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-10 00:23:00
// Scope: Repository for exercise-related database operations in WorkoutLogs app

package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    fun getSelectedExercises(): Flow<List<Exercise>> {
        return exerciseDao.getSelectedExercises()
    }

    suspend fun updateSelection(id: Int, isSelected: Boolean) {
        exerciseDao.updateSelection(id, isSelected)
    }

    fun getCategories(): Flow<List<String>> {
        return exerciseDao.getCategories()
    }
}