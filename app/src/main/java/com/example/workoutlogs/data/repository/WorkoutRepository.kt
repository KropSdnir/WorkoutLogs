// File: WorkoutLogs/app/src/main/java/com/workoutlogs/data/repository/WorkoutRepository.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Repository for accessing workout data from Room database
package com.workoutlogs.data.repository

import com.workoutlogs.data.db.dao.WorkoutDao
import com.workoutlogs.data.model.Workout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRepository @Inject constructor(private val workoutDao: WorkoutDao) {
    suspend fun insertWorkout(workout: Workout) {
        workoutDao.insert(workout)
    }

    fun getAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.getAllWorkouts()
    }
}