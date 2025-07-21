# MatchMaking Android App

## 🌟 Overview

MatchMaking is an Android application designed to help users find with potential matches. Users can
browse through profiles indicate their interest by accepting or declining matches. The app is built
with a focus on modern Android development practices, ensuring a robust and maintainable codebase.
It also supports offline functionality, allowing users to view previously loaded profiles even
without an internet connection.

## ✨ Features

* **Browse Profiles:** View a list of potential matches fetched from a remote API.
* **Profile Details:** See more information about each person (e.g., name, email, photo).
* **Accept/Decline:** Users can mark profiles as "accepted" or "declined."
* **Offline Support:** Previously loaded profiles are stored locally using Room DB, making them
  accessible even when offline.
* **Clean Architecture:** Follows modern architectural patterns (e.g., MVVM) for separation of
  concerns.
* **Modern Tech Stack:** Built with Kotlin, Jetpack libraries, and Hilt for dependency injection.

## 🛠️ Tech Stack & Libraries

* **Language:** [Kotlin]
* **Architecture:** MVVM (Model-View-ViewModel)
* **UI:** Android XML Layouts, RecyclerView, Material Components
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Networking:**
    * [Retrofit]
    * [OkHttp] (with HttpLoggingInterceptor)
    * network requests and logging.
* **Database:**
    * [Room Persistence Library] - For local data storage and offline support.
* **Dependency Injection:**
    * [Hilt] - For managing dependencies.
* **Jetpack Components:**
* ViewModel
* LiveData / Flow
* Navigation Component
* Lifecycle-aware components
* **Image Loading:** (Specify if you use one, e.g., Glide, Coil, Picasso)
    * [Glide]
* **JSON Parsing:**
    * [Gson]

## 🏗️ Project Structure

The project follows a standard Android project structure, with a focus on a modular design and clear
separation of concerns:

* **`data`**: Contains data sources (network API services, Room database), model classes (entities, DTOs).
    * `remote`: Networking related classes (ApiService, Retrofit setup).
    * `local` / `db`: Room database entities, DAOs, and Database class.
    * `repository`: Repository implementations abstracting data sources.
* **`di`**: Dependency injection modules using Hilt (e.g., `AppModule.kt`).
* **`domain`**: Contains use cases and domain-specific models if you separate them from data layer models.
* **`ui`**: Contains Activities, Fragments, ViewModels, Adapters, and other UI-related components.
    * `matchlist`: Example package for features related to showing match lists.
    * `profiledetails`: Example package for profile detail screens.
* **`utils`**: Utility classes and extension functions.

