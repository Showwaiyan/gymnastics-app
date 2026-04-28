# OpenCode / Agent Instructions for Gymnasticsapp

## Project Overview
- Basic Android Application using Kotlin.
- UI is built using traditional XML layouts (`app/src/main/res/layout`), not Jetpack Compose.
- Build system: Gradle with Kotlin DSL (`build.gradle.kts`).

## Development Guidelines
- **Build & Verification:** Use the Gradle wrapper for all tasks. 
  - Build: `./gradlew assembleDebug`
  - Test: `./gradlew test` or `./gradlew connectedAndroidTest`
  - Lint: `./gradlew lint`
- **Dependencies:** Managed via Gradle Version Catalogs. If you need to add a dependency, update `gradle/libs.versions.toml` rather than hardcoding versions in the `build.gradle.kts` files.
- **Language:** Stick to Kotlin and ensure compatibility with Java 11 (`JavaVersion.VERSION_11`).
- **UI Architecture:** Use standard Android Views. Do not introduce Jetpack Compose dependencies unless explicitly requested by the user.

## Repository Quirks
- A `kls_database.db` file exists in the repository root. Ensure you do not accidentally check this into source control if it's auto-generated, or confirm its purpose before modifying it in scripts.
