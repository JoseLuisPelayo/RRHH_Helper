# RRHH Helper

RRHH Helper is an Android application built with Kotlin and Jetpack Compose that helps Human Resources teams and employees estimate the Spanish IRPF (income tax) withholding that applies to a salary. The app guides the user through a two-step form where they provide professional and personal details, and then generates an easy-to-read summary with the calculated deductions.

## Features

- **Two-step guided form** – Collects salary, contract and personal context data through Compose form components with validation feedback.
- **IRPF estimation** – Computes social security contributions, deductions and an estimated withholding percentage based on the provided information.
- **Result summary** – Presents both the captured data and the calculated result with contextual warnings that the figure is an approximation.
- **Navigation 3 back stack** – Uses the experimental `androidx.navigation3` library to navigate between the professional form, personal form and result screens.
- **State persistence** – Relies on `rememberSaveable` and Kotlin serialization to keep the `FormData` across configuration changes and navigation steps.

## Tech stack

- [Kotlin](https://kotlinlang.org/) with JVM target 11
- [Jetpack Compose](https://developer.android.com/jetpack/compose) Material 3 UI toolkit
- [androidx.navigation3](https://developer.android.com/jetpack/androidx/releases/navigation3) for back-stack driven navigation
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for serializing form state
- Gradle with Kotlin DSL

## Module structure

```
RRHH_Helper/
├── app/                      # Android application module
│   ├── build.gradle.kts       # Module configuration and dependencies
│   └── src/main/
│       ├── java/com/example/rrhh_helper/
│       │   ├── MainActivity.kt         # Entry point that sets up the Compose navigation
│       │   ├── domain/                 # Business logic and validation
│       │   │   ├── FormData.kt         # Serializable data model for the form
│       │   │   ├── FormValidator.kt    # Validation helpers for salary and age
│       │   │   └── IRPFCalculator.kt   # Tax calculation utilities
│       │   └── ui/
│       │       ├── form/components/    # Reusable form controls and constants
│       │       └── screens/            # Compose screens for each step and result
│       └── res/                        # Android resources (strings, icons, themes)
└── gradle/                    # Version catalogs and wrapper files
```

## Screens

1. **Datos profesionales** – Captures gross annual salary, payment schedule, age, contract type, professional group and relocation status with validation for salary and age inputs.
2. **Datos personales** – Collects the tax residence, disability level, marital status and spouse income data, keeping previous inputs when navigating back.
3. **Resultado** – Shows a summary of all answers, warns users to review their data, and computes the IRPF deductions on demand.

## Getting started

### Prerequisites

- Android Studio Ladybug (AGP 8.13.0) or newer
- Android SDK Platform 36
- JDK 11

### Build and run

1. Clone this repository:
   ```bash
   git clone https://github.com/<your-user>/RRHH_Helper.git
   cd RRHH_Helper
   ```
2. Open the project in Android Studio.
3. Let Gradle sync finish so the Compose and Navigation dependencies are downloaded.
4. Select a device or emulator running Android 7.0 (API 24) or higher.
5. Click **Run** to install and launch the app.

### Run unit tests

The project uses the standard Gradle test task:

```bash
./gradlew test
```

## Future improvements

- Expand validation rules to cover remaining personal data fields.
- Externalize deduction tables to a repository layer to simplify updates.
- Add instrumentation tests that cover the multi-step navigation flow.

## License

This project is provided without a specific license. Add your preferred license text here.
