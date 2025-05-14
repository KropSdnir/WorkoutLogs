// app/src/main/java/com/example/workoutlogs/data/db/dao/WorkoutLogDao.kt
// Timestamp: 2025-05-14 19:10:00
// Scope: Room DAO for workout log operations in WorkoutLogs app

package com.example.workoutlogs.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutlogs.data.model.WorkoutLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {
    @Query("SELECT * FROM workout_logs")
    fun getAllWorkoutLogs(): Flow<List<WorkoutLog>>

    @Insert
    suspend fun insert(workoutLog: WorkoutLog)

    @Query("DELETE FROM workout_logs WHERE id = :id")
    suspend fun deleteById(id: Int)
}