// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/repository/ExerciseRepository.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Repository for accessing exercise data from Room database
package com.workoutlogs.data.repository

import com.workoutlogs.data.db.dao.ExerciseDao
import com.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) {
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }

    fun getExercisesForWorkout(workoutId: Int): Flow<List<Exercise>> {
        return exerciseDao.getExercisesForWorkout(workoutId)
    }
}