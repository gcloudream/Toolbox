# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Android app built with **Kotlin** and **Jetpack Compose**. Standard Android project structure with Material 3 theming.

## Build Commands (Windows)

```powershell
.\gradlew.bat assembleDebug      # Build debug APK
.\gradlew.bat installDebug       # Install to connected device/emulator
.\gradlew.bat test               # Run unit tests
.\gradlew.bat connectedAndroidTest  # Run instrumented tests
.\gradlew.bat lint               # Run Android Lint checks
.\gradlew.bat assembleRelease    # Build release APK
```

## Project Structure

```
app/src/main/java/com/example/chen/
├── MainActivity.kt              # Main entry point, Compose UI
└── ui/theme/                    # Theme files (Color, Type, Theme)
```

## Key Configuration

- **Min SDK**: 24 | **Target SDK**: 36 | **Compile SDK**: 36
- **Java/Kotlin Target**: 11
- **Dependencies**: AndroidX Core KTX, Lifecycle Runtime, Activity Compose, Compose BOM, Material 3
- **Version Catalog**: `gradle/libs.versions.toml`

## Coding Conventions

- Composables: `PascalCase` (e.g., `GreetingCard`)
- Functions/properties: `camelCase`
- Resources: `snake_case` (e.g., `activity_main.xml`)
- 4-space indentation (Kotlin/Android Studio default)
- Centralize colors/typography in `ui/theme/`

## Testing

- Unit tests: `app/src/test/java/` (JUnit4)
- Instrumented tests: `app/src/androidTest/java/` (AndroidX Test, Espresso, Compose)
- Test class naming: `*Test` suffix

## Local Setup

- Set Android SDK path in `local.properties`
- JDK 11 required
