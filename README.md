
# CineMates
![GitHub last commit](https://img.shields.io/github/last-commit/Indisparte/CineMates?style=for-the-badge)

<p align="center">
  <img src="https://github.com/Indisparte/CineMates/blob/main/assets/logo.png?raw=true" alt="CineMates Logo"/>
</p>

## ❓ Why this Project

Questa app è nata per un progetto di un esame universitario di gruppo (*io e altri miei due colleghi*) per la laurea triennale in informatica dell'Università di Napoli "Federico II". 

In seguito ho deciso di riprendere l'app e di stravolgerla da quelle che erano le iniziali linee guida, in modo da cimentarmi in un progetto più sostanzioso e di acquisire maggiore dimistichezza con lo sviluppo di applicazioni native Android (*Java*) ma anche l'ingegneria del software in generale.


## 🔎 Project Overview
L'applicazione è ancora nella sua fase iniziale ma molto più matura di quella che era quando l'ho ripresa. 

CineMates ha lo scopo di essere un'app personalizzabile e mia intenzione è quella di rendere ogni feature customizabile dall'utente.

Ecco **alcune feature già sviluppate o in corso d'opera**:
- E' possibile visualizzare nella schermata home tutti i **film in tendenza della settimana** nonchè le **persone in tendenza**, oltre a visualizzare anche i **film in uscita** e i **più votati**. 

    L'idea era quella di permettere all'utente di poter scegliere quale sezione visualizzare prima o magari quale non fosse di suo interesse o addirittura aggiungerne un'altra. 

    Per questa esigenza mi sono trovato a sviluppare una **custom sectionable recyclerview** (*Una recyclerview i cui items sono delle sezioni composte da un titolo e da una recyclerview in cui vanno inseriti altri items*). In questo modo ho già preparato il terreno per sviluppi futuri volendo portare maggiore eterogeneità all'interno della home e poter gestire ogni sezione come un unico item.

- E' possibile effettuare una ricerca di film o di attori utilizzando un'unica query, il tutto poi è distinguibile dalla presenza di due tab che dividono i risultati di ricerca.
    E' possibile inoltre anche scoprire film tramite la ricerca per genere effettuabile dalla sezione discover, in cui tramite delle chips è possibile selezionare un genere e visualizzare i film correlati. 
    
    Questa funzionalità è da approfondire in quanto l'idea funziona ma voglio poter dare la possibilità all'utente di poter scegliere più di un genere e di poter ordinare il risultato utilizzando altri filtri come la durata, la popolarità.

- Di ogni film è possibile visualizzare molte informazioni tra cui anche la collezione di cui eventualmente fà parte, film simili e i trailer o clip. Grazie ad un sistema di tab, è possibile anche conoscere il cast di quel film e dare una rapida occhiata ai poster .

    In questo modo è possibile anche informarsi sugli attori, cliccando su uno dei membri del cast infatti si accede ad una sezione dedicata a quella specifica persona, in cui visualizzeremo dettagli anche simpatici, oltre ai film in cui è presente.

    Tornando ad analizzare la schermata di info del film, è possibile inserire questo film per ora in tre liste: preferiti, da vedere e visti. In futuro prevedo la possibilità di creare delle liste personalizzate in cui, così come in tutte le altre liste, prevedo la massima personalizzazione, dai colori alla possibilità di scegliere un film in maniera random da queste liste.

- Nella sezione profilo poche sono le informazioni che ci vengono indicate, abbiamo la lista dei nostri attori o film preferiti e una statistica semplice delle ore trascorse a vedere film e due contatori per i film visti e da vedere.

    Prevedo in futuro di aggiungere la possibilità di indicare anche dei generi preferiti e di analizzare i film preferiti e visti secondo uno schema a torta che mostra quali generi prediligiamo. E magari anche altre statistiche.


## 🗝 API Key
Trova un file chiamato `local.properties` nell file`.gradle` nella home directory.

- Aggiungi `TMDB_API_KEY = "YOUR-API-KEY"` (*L'API di The Movie DB ottenibile [qui](https://www.themoviedb.org/?language=en)*)
- Aggiungi `YT_API_KEY = "YOUR-API-KEY"` (*L'API di Youtube ottenibile [qui](https://console.cloud.google.com/apis/dashboard)*)

## 👨🏽‍🎓 What I learned
- Recuperare dati da Internet con l'**API** di [TheMovieDB](https://developers.themoviedb.org/3/getting-started)
- Usare adapters e layout di lista personalizzati per popolare le viste di lista
- Incorporare librerie per semplificare la quantità di codice da scrivere
- Utilizzare il pattern **MVVM**
- Incorporare il **data binding** e il **view binding**
- Utilizzare il componente **Navigation**, **View Pager** , **Live Data**
- Utilizzare Dagger Hilt per la **dependency injection**
- Creare delle view personalizzare uniche per ridurre codice boilerplate

## 📽 Image Resources

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

