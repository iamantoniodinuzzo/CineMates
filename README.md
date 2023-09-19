
<h1 align="center">CineMates</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="Last Commit" src="https://img.shields.io/github/last-commit/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="Contributors" src="https://img.shields.io/github/contributors/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a>
  <a href="https://github.com/iamantoniodinuzzo/CineMates/network/members"><img alt="Forks" src="https://img.shields.io/github/forks/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a> <br>
  <a href="https://github.com/iamantoniodinuzzo/CineMates/stargazers"><img alt="Stargazers" src="https://img.shields.io/github/stars/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a>
  <a href="https://github.com/iamantoniodinuzzo/CineMates/issues"><img alt="Issues" src="https://img.shields.io/github/issues/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a>
  <a href="https://github.com/iamantoniodinuzzo/CineMates/blob/main/LICENSE.txt"><img alt="License" src="https://img.shields.io/github/license/iamantoniodinuzzo/CineMates.svg?style=for-the-badge"/></a> 
</p>

<p align="center">  
🎥 <b>CineMates</b> demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on MVVM architecture and Modularization.
</p>
</br>


<p align="center">
  <img src="/assets/logo.png" alt="Logo"/>
</p>

## Download ⬇️
> 👷‍♂️ Section in preparation

## Tech stack 🧱
- Minimum SDK level 22
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - **Lifecycle**: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - **ViewModel**: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - **DataBinding**: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - **Room**: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- **Architecture**
  - **MVVM Architecture** (View - DataBinding - ViewModel - Model)
  - **Repository Pattern**
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Material-Components](https://github.com/material-components/material-components-android): Material design components for building ripple animation, and CardView.
- [Glide](https://github.com/bumptech/glide): Loading images from network.
- **Custom Views**
  - [LinearLayoutInfoView](https://github.com/iamantoniodinuzzo/CineMates/blob/feat/documentation/core/ui/src/main/java/com/indisparte/ui/custom_view/LinearLayoutInfoView.kt): A simple way to display a simple section of text (vertical or horizontal) consisting of a title and a value.
  - [MoreLessTextView](https://github.com/iamantoniodinuzzo/CineMates/blob/feat/documentation/core/ui/src/main/java/com/indisparte/ui/custom_view/MoreLessTextView.kt): A simple way to display an expandable TextView as needed.
  - [PosterView](https://github.com/iamantoniodinuzzo/CineMates/blob/feat/documentation/core/ui/src/main/java/com/indisparte/ui/custom_view/PosterView.kt): A simple way to display a media poster featuring an image and a title.
  - [StatefulRecyclerView](https://github.com/iamantoniodinuzzo/CineMates/blob/feat/documentation/core/ui/src/main/java/com/indisparte/ui/custom_view/StatefulRecyclerView.kt): A RecyclerView that handles the loading, error, and empty states.
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.

## Open API 🍽️

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_square_2-d537fb228cf3ded904ef09b136fe3fec72548ebc1fea3fbbd1ad9e36364db38b.svg" align="right" width="19%"/>

**CineMates** using the __[TheMovieDB](https://developer.themoviedb.org/reference/intro/getting-started)__ for constructing RESTful API.<br>
TheMovieDB provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Movie and Tv Series.


## Screenshots 🖼️
> 👷‍♂️ Section in preparation


## Contributing 🤝 
Feel free to open an __[Issues](https://github.com/iamantoniodinuzzo/CineMates/issues)__ 🐛 or submit a __[Pull requests](https://github.com/iamantoniodinuzzo/CineMates/pulls)__ ❔ for any bugs/enhancement.

If you have ideas 💡 you can submit them __[here](https://github.com/Indisparte/CineMates/discussions/categories/ideas)__.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/iamantoniodinuzzo/CineMates/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/iamantoniodinuzzo)__ on GitHub for my next creations! 🤩

