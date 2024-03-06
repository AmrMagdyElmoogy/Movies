# Movies Application
Welcome to the Movie Application! This app allows you to explore currently playing and top-rated films, providing a brief history of each film.

# Features

- **Now Playing Films:** Discover the latest films currently playing in theaters.
- **Top Rating Films:** Explore top-rated films based on user reviews.
- **Film Details:** Get a brief history of each film, including an overview of the storyline and key details.

# Themes

The app offers a customizable user experience with both dark and light themes.
## Dark Theme
![](https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/dark_theme.png)
## Light Theme 
![](https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/light_theme.png)


## Live Demo 

https://github.com/AmrMagdyElmoogy/Movies/assets/47532331/09de6a48-b456-4bc5-b424-4c9aded08445



# Architecture

The Movie Application is built using the MVVM architecture with a Single Activity and Unidirectional Data Flow patterns. This design ensures a clean and scalable structure for the application.

### MVVM Architecture

- **Model:** Represents the data and business logic.
- **View:** Displays the UI and interacts with the user.
- **ViewModel:** Manages the UI-related data and communicates with the Model.

### Single Activity

The app follows a single-activity architecture, reducing complexity and improving navigation.

### Unidirectional Data Flow

The app implements a unidirectional data flow to ensure a predictable and maintainable codebase. Data flows in a single direction, making it easier to debug and understand.

---

# Project Architecture Overview
<p align="center">
  <img src="https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/arch.png" alt="Image" width="100%">
</p>



Our project follows a clean architecture pattern, dividing the codebase into three distinct layers for enhanced maintainability and scalability.

## Presentation Layer

- Responsible for managing UI screen logic and business logic, orchestrated by the ViewModel.
- The ViewModel acts as a gate to the next layer and incorporates state holders observed by the UI in a reactive programming style.

## Domain Layer

- Hosts entities like "Movie" for interactions with the UI.
- Contains a Repository deciding whether to fetch data from the Remote server or the Local database.
- Synchronization ensures seamless saving of newly fetched API data into the database, making the app offline-first.

## Data Layer

The Data Layer is divided into three components:

1. **API:**
   - Manages configurations, including creating API calls, interceptors, and preparing a Retrofit object for HTTP calls.

2. **Database:**
   - Configures the database and its DAO, implementing SQL queries through Room Database and its annotations.

3. **Models:**
   - Includes DTO objects that carry data, transferred from JSON through Moshi, between processes to implement Separation of Concerns (SOC).
  
---

## Libraries and Dependencies: 
 + #### Coil 
   + Coil is a Kotlin-first image loading library for Android.It used for effectient image loading and caching. 
   
 + #### Room 
   + Room is an Android Architecture Component that provides an abstraction layer over SQLite. It supports handling with Coroutine, RxJava and LiveData.
   
 + #### Retrofit 
   + Retrofit is a popular HTTP client for Android that simplifies the process of making network requests.
   
 + #### Moshi 
   + Moshi is a modern JSON library for Kotlin and Java. It simplifies the process of serializing and deserializing JSON data.
   
 + #### Fragment KTX 
   + Fragment KTX is a set of Kotlin extensions for the AndroidX Fragment library. It simplifies working with fragments in Kotlin
   
 + #### Coroutines 
   + It is a library for writing asynchronous and concurrent code using the Kotlin programming language
   
 + #### View Model 
   + View Model is a part of the Android Architecture Components, designed to store and manage UI-related data

 + #### Hilt
   + Hilt simplifies dependency injection in Android applications with Dagger integration.
  
## Download the Release APK

1. Go to the [Releases](https://github.com/AmrMagdyElmoogy/Movies/releases) page of this repository.

2. Find the latest release version and click on it.

3. Look for the "Assets" section, and you'll find the `app-release.apk` file.

4. Download the APK file to your Android device.
