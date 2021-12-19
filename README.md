# CineMates
*CineMates20* è un sistema complesso e distribuito finalizzato ad offrire un moderno *social network* multi-piattaforma per appassionati di cinema.

Il sistema consiste in un back-end sicuro, performante e scalabile, e in due Client (uno su piattaforma desktop, e uno mobile) attraverso cui gli utenti possono fruire delle funzionalità del sistema in modo intuitivo, rapido e piacevole.

## Funzionalità da implementare
- [ ] Registrazione\Autenticazione degli utenti. È apprezzata la possibilità di autenticarsi utilizzando account su altre piattaforme come Google o Facebook.
- [ ] Effettuare ricerche di film, utilizzando anche le API offerte da servizi esterni come The Movie DataBase (TMDB) o Open Movie DataBase (OMDB).
- [ ] Aggiungere o rimuovere film dalla propria lista di preferiti, o di film da vedere;
- [ ] Inserire una recensione di un film visto, indicando una valutazione e una descrizione testuale.
- [ ] Creazione di liste di film personalizzate, caratterizzate anche da un titolo e da una descrizione personalizzata.
- [ ] Inviare\Ricevere richieste di collegamento a\da altri utenti.
- [ ] Visualizzare il profilo di un amico, che riporta le sue attività più recenti (e.g.: recensioni inserite, film inseriti in una lista, liste create, commenti lasciati).
- [ ] Visualizzare film preferiti\da vedere in comune con un particolare amico.
- [ ] Visualizzare, in un feed, le azioni recenti effettuate dagli amici. Esempi di azioni recenti da mostrare includono: aggiunta di un amico, aggiunta di un film a una lista,        creazione di una lista personalizzata, recensione di un film, etc.
- [ ] Scelta casuale di un film dalla lista di film da vedere, per aiutare utenti indecisi. Se l’utente non è soddisfatto del film selezionato in modo casuale, può richiedere          una nuova selezione casuale. Il Sistema deve garantire che lo stesso film non venga mai suggerito per due volte consecutive.
- [ ] Possibilità di visualizzare, commentare e valutare le liste di film personalizzate dei propri amici. È possibile esprimere anche pareri rapidi (e.g. “mi piace”, “non mi          piace”, etc.).
- [ ] Possibilità di visualizzare, commentare e valutare le recensioni inserite dagli amici. È possibile esprimere anche pareri rapidi (e.g. “mi piace”, “non mi piace”, etc.).           Le recensioni e/o i commenti che ricevono almeno tre segnalazioni per spoiler vengono oscurate di default, e gli utenti possono visualizzarle soltanto premendo su un             apposito pulsante.
- [ ] Possibilità di raccomandare un film preferito oppure una lista personalizzata ai propri amici (i quali riceveranno una notifica della segnalazione).

### Specifiche funzionalità
*CineMates20* permette agli utenti di scoprire nuovi film, e condividere con gli amici i propri interessi cinematografici. Un utente autenticato può visualizzare in un feed le azioni recenti dei propri collegamenti, in ordine cronologico. Inoltre, un utente può anche effettuare delle ricerche di film. È assolutamente indispensabile la possibilità di effettuare ricerche per titolo. La possibilità di effettuare ricerche avanzate per anno di rilascio \ genere \ attori \ registi è gradita. Una volta trovato un film, l’utente può recensirlo, inserirlo nella propria lista dei preferiti o dei film da vedere, o anche aggiungerlo ad una lista personalizzata. Ovviamente, un utente potrà visualizzare l’elenco delle proprie liste e delle proprie recensioni.

Un utente può ricercare tra gli utenti i propri amici, ed inviare una richiesta di collegamento, che sarà notificata all’amico e potrà essere accettata o respinta da quest’ultimo. Inoltre, un utente può visualizzare sia il profilo che le recensioni \ liste personalizzate dei propri amici, commentarle e eventualmente indicare una reazione rapida (“mi piace”, “non mi piace”, “arrabbiato”, “applauso”, etc..).

Un utente può anche suggerire una propria recensione \ lista personalizzata a uno o più amici, i quali riceveranno una notifica. Per limitare notifiche indesiderate, non deve essere possibile consigliare più volte la stessa cosa allo stesso amico.

Dev’essere possibile per gli utenti segnalare contenuti (commenti, recensioni, etc.) con spoiler e/o contenuti inappropriati (per esempio ingiurie, minacce, etc.). I contenuti che ricevono almeno tre segnalazioni per spoiler devono essere oscurati di default, lasciando la possibilità all’utente di richiederne espliciamente la visualizzazione. I contenuti con almeno tre segnalazioni di inappropriatezza, vengono oscurati di default fino alla decisione dell’amministratore.

Gli amministratori, infine, possono visualizzare statistiche in tempo reale sul sistema (numero di utenti, numero di ricerche, numero di liste, numero di recensioni, etc.), raccomandare un particolare film a tutti gli utenti, inviare email promozionali a tutti gli utenti, e decidere sulla sorte dei contenuti segnalati dagli utenti.

## Migliorie
- [x] Utilizzare ViewBinding
- [ ] Utilizzare DataBinding
- [x] Migliorare la navigazione
- [ ] Mgliorare il refresh dei fragment lists
- [ ] Migliorare la gestione del profilo
- [ ] Aumentare la personalizzazione
- [x] Migliorare UX
- [x] Gestire al meglio le credenziali 
- [ ] Backend sicuro con Azure/AWS