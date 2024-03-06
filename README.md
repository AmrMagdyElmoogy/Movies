# Movies Application
Welcome to the Movie Application! This app allows you to explore currently playing and top-rated films, providing a brief history of each film.

# Features

- **Now Playing Films:** Discover the latest films currently playing in theaters.
- **Top Rating Films:** Explore top-rated films based on user reviews.
- **Film Details:** Get a brief history of each film, including an overview of the storyline and key details.

# Themes

The app offers a customizable user experience with both dark and light themes. Choose the theme that suits your preference by navigating to the settings.
## Dark Theme
![](https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/dark_theme.png)
## Light Theme 
![](https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/light_theme.png)


# Architecture

The Movie Application is built using the MVVM architecture with a Single Activity and Unidirectional Data Flow patterns. This design ensures a clean and scalable structure for the application.

### MVVM Architecture

- **Model:** Represents the data and business logic.
- **View:** Displays the UI and interacts with the user.
- **ViewModel:** Manages the UI-related data and communicates with the Model.

![](https://github.com/AmrMagdyElmoogy/Movies/blob/main/app/src/main/res/drawable/arch.png)

### Single Activity

The app follows a single-activity architecture, reducing complexity and improving navigation.

### Unidirectional Data Flow

The app implements a unidirectional data flow to ensure a predictable and maintainable codebase. Data flows in a single direction, making it easier to debug and understand.
