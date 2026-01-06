# Repository Guidelines

## Project Structure and Module Organization
- `app/src/main/java/com/example/chen`: Kotlin sources and Compose UI entry points (MainActivity).
- `app/src/main/java/com/example/chen/ui/theme`: Compose theme, colors, and typography.
- `app/src/main/res`: Android resources (values, drawables, mipmaps, xml).
- `app/src/test/java`: Local unit tests (JUnit4).
- `app/src/androidTest/java`: Instrumented tests (AndroidX Test, Espresso, Compose UI tests).
- `gradle/libs.versions.toml`: Version catalog for dependencies and plugins.

## Build, Test, and Development Commands
Use the Gradle wrapper (`./gradlew` on macOS/Linux, `.\gradlew.bat` on Windows).

- `./gradlew assembleDebug`: Build a debug APK.
- `./gradlew installDebug`: Install debug build to a connected device/emulator.
- `./gradlew test`: Run local unit tests.
- `./gradlew connectedAndroidTest`: Run instrumentation tests on a device/emulator.
- `./gradlew lint`: Run Android Lint checks.

## Coding Style and Naming Conventions
- Kotlin and Jetpack Compose are used. Keep 4-space indentation and Kotlin/Android Studio
  default formatting.
- Composables use PascalCase (`GreetingCard`); functions and properties use camelCase
  (`loadUser`).
- Resource files and IDs use snake_case (`activity_main.xml`, `primary_color`).
- Keep UI code in Compose where possible and centralize colors/typography in `ui/theme`.

## Testing Guidelines
- Unit tests live under `app/src/test/java` and use JUnit4.
- Instrumented tests live under `app/src/androidTest/java` and use AndroidX Test, Espresso,
  and Compose UI testing APIs.
- Name tests with `*Test` and mirror the app package structure.

## Commit and Pull Request Guidelines
- No Git history is present in this repo, so no established commit convention exists.
- Use short, imperative commit summaries (e.g., "Add onboarding screen") and include scope
  if helpful.
- PRs should describe the change, link issues if applicable, and include screenshots for UI
  updates.
- Note which commands you ran (e.g., `./gradlew test`, `./gradlew lint`).

## Configuration and Local Setup
- Set your Android SDK path in `local.properties` (do not commit this file).
- The project targets Java 11; ensure your JDK matches the Gradle configuration.
