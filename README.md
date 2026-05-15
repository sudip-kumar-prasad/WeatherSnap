# WeatherSnap

WeatherSnap is a production-quality Android application built with Kotlin and Jetpack Compose. It provides real-time weather reports with a focus on field data collection, featuring a custom camera with image compression and persistent storage.

## Features
- **City Search**: Autocomplete with debouncing and local caching.
- **Live Weather**: Powered by Open-Meteo API.
- **Custom Camera**: Built using CameraX (no external intents).
- **Image Compression**: Automatic JPEG compression (60% quality) with size tracking.
- **Persistent Storage**: Room Database for offline-capable report management.
- **Premium UI**: Dark olive theme with Material 3, glassmorphism-inspired elements, and smooth animations.

## Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **Architecture**: MVVM (ViewModel + StateFlow)
- **Dependency Injection**: Hilt
- **Asynchronous**: Coroutines + Flow
- **Networking**: Retrofit + OkHttp
- **Local DB**: Room
- **Camera**: CameraX
- **Image Loading**: Coil

## Setup
1. Clone the repository.
2. Open in Android Studio Hedgehog or later.
3. Run on a physical device (recommended for camera features) or an emulator with camera support.
4. No API keys are required as it uses the Open-Meteo public API.

## Developer Judgment Challenge: Lifecycle Safety
**Approach**: 
Used `SavedStateHandle` in `CreateReportViewModel` to persist the "draft" state of a report. This includes the serialized weather data, image path, field notes, and size metrics.

**Why**: 
Standard ViewModel state (`mutableStateOf` or `MutableStateFlow`) survives configuration changes (like rotation) but is lost during **process death** (when the OS kills the app in the background to reclaim memory). `SavedStateHandle` ensures that if a user captures a photo and enters notes, then takes a phone call that kills the app process, their draft will still be there when they return.

**Tradeoffs**: 
- **Serialization Overhead**: Complex objects like `WeatherData` must be serialized (to JSON in this case) to be stored in the bundle, which adds a slight performance cost.
- **State Management**: The draft must be explicitly cleared after a successful save to prevent "phantom" drafts from reappearing the next time the screen is opened.

**Temp file cleanup**: 
- The original (large) photo captured by CameraX is deleted immediately after the compressed version is created.
- Compressed images are stored in the `cacheDir`. If a user discards a report (navigates away without saving), the temporary file is deleted in `ViewModel.onCleared()` using a `draftDiscarded` flag to ensure no "orphan" images are left in the cache.

## Project Structure
```
com.weathersnap/
├── di/                 # Hilt modules
├── data/
│   ├── remote/         # Retrofit API & Models
│   ├── local/          # Room Entity, DAO, DB
│   └── repository/     # Repository implementations
├── domain/
│   └── model/          # Clean domain models
├── ui/
│   ├── theme/          # Custom M3 theme
│   ├── navigation/     # NavHost & Transitions
│   └── [feature]/      # Screens & ViewModels
└── util/               # Helpers (Compressor, Mapper)
```

## License
MIT License
