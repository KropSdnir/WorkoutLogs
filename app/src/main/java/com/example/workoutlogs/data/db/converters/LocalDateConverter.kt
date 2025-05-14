package com.example.workoutlogs.data.db.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// File: app/src/main/java/com/example/workoutlogs/data/db/converters/LocalDateConverter.kt
// Timestamp: Updated on 2025-05-09 06:10:00
// Scope: Room type converter for LocalDate in WorkoutLogs app

class LocalDateConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, formatter) }
    }
}