// File: app/src/main/java/com/example/workoutlogs/di/DatabaseModule.kt
// Version: 0.0.1 first full boot
// Timestamp: Updated on 2025-05-09 13:00:00
// Scope: Hilt module for providing database dependencies in WorkoutLogs app

package com.example.workoutlogs.di

import android.content.Context
import androidx.room.Room
import com.example.workoutlogs.data.db.WorkoutLogsDatabase
import com.example.workoutlogs.data.db.dao.CalendarDao
import com.example.workoutlogs.data.db.dao.ExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WorkoutLogsDatabase {
        return Room.databaseBuilder(
            context,
            WorkoutLogsDatabase::class.java,
            "workout_logs_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCalendarDao(database: WorkoutLogsDatabase): CalendarDao {
        return database.calendarDao()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(database: WorkoutLogsDatabase): ExerciseDao {
        return database.exerciseDao()
    }
}