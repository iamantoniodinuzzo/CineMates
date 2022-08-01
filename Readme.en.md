
# CineMates
![GitHub last commit](https://img.shields.io/github/last-commit/Indisparte/CineMates?style=for-the-badge)

<p align="center">
  <img src="https://github.com/Indisparte/CineMates/blob/main/assets/logo.png?raw=true" alt="CineMates Logo"/>
</p>

## ❓ Why this Project.

This app was born for a group university exam project (*me and two other colleagues of mine*) for a bachelor's degree in computer science at the University of Naples "Federico II". 

I later decided to take the app back and twist it from what were the initial guidelines, in order to try my hand at a more substantial project and to become more familiar with native Android (*Java*) application development but also software engineering in general.


## 🔎 Project Overview
The application is still in its early stages but much more mature than it was when I picked it up. 

CineMates aims to be a customizable app and my intention is to make every feature customizable by the user.

Here are **some features already developed or in progress**:
- It is possible to display on the home screen all the **trending movies of the week** as well as the **trending people**, in addition to also displaying the **releasing movies** and the **highest rated movies**. 

    The idea was to allow the user to be able to choose which section to view first or perhaps which section was not of interest to them or even add another section. 

    For this requirement I found myself developing a **custom sectionable recyclerview** (*A recyclerview whose items are sections consisting of a title and a recyclerview into which other items are to be inserted*). In this way I have already set the stage for future developments by wanting to bring more heterogeneity within the home and be able to manage each section as a single item.

- It is possible to search for movies or actors using a single query, all then distinguishable by the presence of two tabs that divide the search results.
    It is also possible to discover movies via genre search that can be done from the discover section, where via chips it is possible to select a genre and view related movies. 
    
    This feature is to be explored further as the idea works but I want to be able to give the user the ability to be able to choose more than one genre and be able to sort the result using other filters such as duration, popularity.

- A lot of information can be displayed about each movie including also the collection it is possibly part of, similar movies, and trailers or clips. Thanks to a tab system, it is also possible to learn about the cast of that film and take a quick look at the posters .

    In this way it is also possible to find out about the actors, clicking on one of the cast members in fact leads to a section dedicated to that specific person, in which we will display even nice details, as well as the movies in which he/she is present.

    Going back to analyze the movie info screen, it is possible to put this movie for now in three lists: favorite, to see and seen. In the future I foresee the possibility of creating custom lists in which, as in all other lists, I foresee maximum customization, from colors to the possibility of choosing a movie randomly from these lists.

- In the profile section little information is given to us, we have the list of our favorite actors or movies and a simple statistic of hours spent watching movies and two counters for movies seen and to be seen.

    I plan in the future to add the ability to indicate favorite genres as well and to analyze favorite and viewed movies according to a pie chart showing which genres we prefer. And maybe other statistics as well.


## 🗝 API Key.
Find a file called `local.properties` in the `.gradle` file in the home directory.

Add `apiKey = "YOUR-API-KEY"` to this file.

## 👨🏽🎓 What I learned.
- Retrieve data from the Internet with the **API** of [TheMovieDB](https://developers.themoviedb.org/3/getting-started)
- Use adapters and custom list layouts to populate list views
- Embed libraries to simplify the amount of code to write
- Use the **MVVM** pattern
- Incorporate **data binding** and **view binding**
- Use the **Navigation**, **View Pager** , **Live Data** component.
- Use Dagger Hilt for **dependency injection**.
- Create unique customize views to reduce boilerplate code

## 📽 Image Resources

**Personalize** | **Movie details** | **Actor details** | **Filters** |
:-----------------------------:|:---------------------:|:-----------------------------:|:-----------------------------:
![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/personalization.gif) | ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/movie_details.gif) | ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/actor_details.gif) | ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/filterable.gif) 

## Libraries
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) 
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/)
- [Retrofit](http://square.github.io/retrofit/) per la comunicazione con l'API
- [Glide](https://github.com/bumptech/glide) per il caricamento delle immagini
- [Hilt Library](https://developer.android.com/training/dependency-injection/hilt-android) per la Dependency Injection
- [YouTube Library](https://developers.google.com/youtube/android/player) per le anteprime di YouTube 

