// File: build.gradle.kts
// Timestamp: Updated on 2025-05-09 04:49:00
// Scope: Top-level build file for configuring plugins for WorkoutLogs

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}