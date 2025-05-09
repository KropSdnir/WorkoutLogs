// File: app/src/main/java/com/example/workoutlogs/data/repository/ExerciseRepository.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 08:00:00
// Scope: Repository for exercise data operations in WorkoutLogs app

package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.Exercise
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }
}