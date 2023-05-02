

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
-->

# CineMates
![GitHub last commit][last-commit-shield]
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<p align="center">
  <img src="https://github.com/Indisparte/CineMates/blob/main/assets/logo.png?raw=true" alt="CineMates Logo"/>
</p>

## 🔎 Project Overview
The application is still in its initial state, lacking all the necessary tests to make it a secure and stable application.

The purpose of this application is to provide the user with a tool to track down all information regarding movies and TV series.

The project is constantly changing as a personal gym in Android development.

## 🗝 API Key.
Find a file called `local.properties` in the `.gradle` file in the home directory.

- Add `TMDB_API_KEY = "YOUR-API-KEY"` (*The API of The Movie DB obtainable [here](https://www.themoviedb.org/?language=en)*)
- Add `YT_API_KEY = "YOUR-API-KEY"` (*The API of Youtube obtainable [here](https://console.cloud.google.com/apis/dashboard)*)

## 👨‍💻 What I learned.
- Retrieve data from the Internet with **Retrofit** and the **API** of [TheMovieDB](https://developers.themoviedb.org/3/getting-started)
- Use **ListAdapters** and **DiffUtil**
- Use the **MVVM** pattern
- Incorporate **data binding** and **view binding**
- Use the **Navigation**, **ViewPager2**  component.
- **Flow** and **MutableStateFlow**
- Use **Dagger Hilt** for dependency injection.
- **Clean Architecture**
- Create unique customize views to reduce boilerplate code
  - HorizontalChipView(https://github.com/Indisparte/CineMates/blob/develop/HorizontalChipView/README.md)
  - LinearLayoutInfo
  - MoreLessTextView
  - RecyclerViewEmptyStateSupport
- [Version Catalogue](https://developer.android.com/build/migrate-to-catalogs)
- 
## 📽 Screenshots

**Home** | **Movie details** | **Actor details** | **Tv Details** | **Search** |
:-----------------------------:|:-----------------------------:|:-----------------------------:|:-----------------------------:|:-----------------------------:
![](https://github.com/Indisparte/CineMates/blob/develop/assets/Gif/home.png) | ![](https://github.com/Indisparte/CineMates/blob/develop/assets/Gif/movie_details.png) | ![](https://github.com/Indisparte/CineMates/blob/develop/assets/Gif/actor_details.png) | ![](https://github.com/Indisparte/CineMates/blob/develop/assets/Gif/tv_details.png)| ![](https://github.com/Indisparte/CineMates/blob/develop/assets/Gif/search.png) 


## 🤝 Contributing 
Feel free to open an [Issues](https://github.com/Indisparte/CineMates/issues) or submit a [Pull requests](https://github.com/Indisparte/CineMates/pulls) for any bugs/enhancement.

If you have 💡 ideas 💡 you can submit them [here](https://github.com/Indisparte/CineMates/discussions/categories/ideas).

## 📚 Libraries
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) 
- [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/)
- [Retrofit](http://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide) 
- [Hilt Library](https://developer.android.com/training/dependency-injection/hilt-android)
- [YouTube Library](https://developers.google.com/youtube/android/player)
- [HorizontalChipView](https://github.com/Indisparte/CineMates/blob/develop/HorizontalChipView/README.md)
- [LinearLayoutInfo](https://github.com/Indisparte/CineMates/blob/develop/LinearLayoutInfo/README.md)
- [MoreLessTextView]
- [RecyclerViewEmptyStateSupport]
- [PosterView]
- 
<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Indisparte/CineMates.svg?style=for-the-badge
[contributors-url]: https://github.com/Indisparte/CineMates/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Indisparte/CineMates.svg?style=for-the-badge
[forks-url]: https://github.com/Indisparte/CineMates/network/members
[stars-shield]: https://img.shields.io/github/stars/Indisparte/CineMates.svg?style=for-the-badge
[stars-url]: https://github.com/Indisparte/CineMates/stargazers
[issues-shield]: https://img.shields.io/github/issues/Indisparte/CineMates.svg?style=for-the-badge
[issues-url]: https://github.com/Indisparte/CineMates/issues
[license-shield]: https://img.shields.io/github/license/Indisparte/CineMates.svg?style=for-the-badge
[license-url]: https://github.com/Indisparte/repo_name/blob/master/LICENSE.txt
[last-commit-shield]: https://img.shields.io/github/last-commit/Indisparte/CineMates.svg?style=for-the-badge
