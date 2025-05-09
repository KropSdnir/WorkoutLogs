// File: build.gradle.kts
// Timestamp: Updated on 2025-05-09 22:00:00
// Scope: Top-level build file for configuring plugins and repositories for the WorkoutLogs app

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
}