// File: WorkoutLogs/app/src/main/java/com/workoutlogs/di/AppModule.kt
// Timestamp: Updated on 2025-05-09 16:00:00
// Scope: Hilt module providing Room database and DataStore dependencies
package com.workoutlogs.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.workoutlogs.data.db.WorkoutLogsDatabase
import com.workoutlogs.data.db.dao.ExerciseDao
import com.workoutlogs.data.db.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWorkoutLogsDatabase(@ApplicationContext context: Context): WorkoutLogsDatabase {
        return Room.databaseBuilder(
            context,
            WorkoutLogsDatabase::class.java,
            "workout_logs_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(database: WorkoutLogsDatabase): WorkoutDao {
        return database.workoutDao()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(database: WorkoutLogsDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}