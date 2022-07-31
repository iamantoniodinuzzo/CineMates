
# CineMates
![GitHub last commit](https://img.shields.io/github/last-commit/Indisparte/CineMates?style=for-the-badge)

![Logo](https://github.com/Indisparte/CineMates/blob/main/assets/logo.png)

## Project Overview
Molte volte ci capita di voler scoprire nuovi film andando a ricercare in internet film di un genere specifico: "film da vedere", "film mind blowing", "film per piangere". Lo scopo di *CineMates* è quello di raggruppare tutte queste ricerche e questi bisogni sotto un unico tetto. Con l'aggiunta di molteplici utilità.

## API Key Note
**Define key in build.gradle**

Trova un file chiamato `local.properties` nella file`.gradle` nella home directory.

Aggiungi `apiKey = "YOUR-API-KEY"` a questo file.

## Why this Project

Per diventare uno sviluppatore Android, devi sapere come portare particolari esperienze in vita. In particolare, è necessario sapere come costruire 
interfacce utente (UI) pulite e convincenti, recuperare dati dai servizi di rete,e ottimizzare l'esperienza per vari dispositivi mobile. 

Costruendo questa applicazione, ho imparato a comprendere gli elementi fondamentali della programmazione Android, approfondendo una delle mie passioni principali ovvero il cinema.

## What I learned
- Recuperare dati da Internet con l'**API** di [TheMovieDB](https://developers.themoviedb.org/3/getting-started)
- Usare adapters e layout di lista personalizzati per popolare le viste di lista
- Incorporare librerie per semplificare la quantità di codice da scrivere
- Utilizzare il pattern **MVVM**
- Incorporare il **data binding** e il **view binding**
- Utilizzare il componente **Navigation**, **View Pager** , **Live Data**
- Utilizzare Dagger Hilt per la **dependency injection**
- Creare delle view personalizzare uniche per ridurre codice boilerplate

## Image Resources

**Personalize**	|	**Movie details**	|	**Actor details** |  **Filters** |
:-----------------------------:|:---------------------:|:-----------------------------:|:-----------------------------:
![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/personalization.gif)  |  ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/movie_details.gif)  |  ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/actor_details.gif) | ![](https://github.com/Indisparte/CineMates/blob/main/assets/Gif/filterable.gif) 
## Libraries
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) 
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/)
- [Retrofit](http://square.github.io/retrofit/) per la comunicazione con l'API
- [Glide](https://github.com/bumptech/glide) per il caricamento delle immagini
- [Hilt Library](https://developer.android.com/training/dependency-injection/hilt-android) per la Dependency Injection
- [YouTube Library](https://developers.google.com/youtube/android/player) per le anteprime di YouTube 

