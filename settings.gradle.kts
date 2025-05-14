// File: settings.gradle.kts
// Timestamp: Updated on 2025-05-09 04:49:00
// Scope: Configures repositories and project structure for WorkoutLogs

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WorkoutLogs"
include(":app")