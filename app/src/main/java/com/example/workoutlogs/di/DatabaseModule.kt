package com.example.workoutlogs.di

import android.content.Context
import androidx.room.Room
import com.example.workoutlogs.data.db.WorkoutLogsDatabase
import com.example.workoutlogs.data.db.dao.CalendarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// File: app/src/main/java/com/example/workoutlogs/di/DatabaseModule.kt
// Timestamp: Updated on 2025-05-09 07:00:00
// Scope: Hilt module for providing Room database and DAO in WorkoutLogs app

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWorkoutLogsDatabase(@ApplicationContext context: Context): WorkoutLogsDatabase {
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
}