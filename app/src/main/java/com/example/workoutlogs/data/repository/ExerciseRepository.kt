// app/src/main/java/com/example/workoutlogs/data/repository/ExerciseRepository.kt
// Timestamp: 2025-05-14 21:25:00 CEST
// Scope: Repository for managing exercise data in WorkoutLogs app

package com.example.workoutlogs.data.repository

import com.example.workoutlogs.data.db.dao.ExerciseDao
import com.example.workoutlogs.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExerciseRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun getAllCategories(): Flow<List<String>>
    suspend fun insertExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)
    suspend fun getExerciseById(id: Int): Exercise?
}

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {
    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises()
    }

    override fun getAllCategories(): Flow<List<String>> {
        return exerciseDao.getAllCategories()
    }

    override suspend fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    override suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }

    override suspend fun getExerciseById(id: Int): Exercise? {
        return exerciseDao.getExerciseById(id)
    }
}