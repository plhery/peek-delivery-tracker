---
title: Paack tracking: tracciare pacco, codice postale e stati
description: Traccia un pacco Paack in Italia: numero d'ordine e codice postale, significato degli stati, fasce orarie, PaackGo Point, assistenza via chat e Colis Privé.
slug: paack-tracking
published: 2026-11-17
updated: 2026-11-17
---

Paack consegna gli ordini dei negozi online nell'ultimo miglio, a domicilio o in un punto di ritiro PaackGo Point, in oltre 50 città tra Italia, Spagna, Francia e Portogallo (oltre ai pacchi britannici di [Evri](carrier:evri) International). Sulla [pagina di tracciamento Paack](https://paack.co/shipments/tracking?lng=it) inserisci **numero d'ordine** e **codice postale** di consegna, oppure incolla il numero qui sopra, scegli Paack e aggiungi il CAP. Paack controlla i due dati in coppia: se uno è errato, non mostra nulla.

## Numero di tracciamento Paack: dove trovarlo e che formato ha

La pagina italiana di Paack chiede «Numero d'ordine» e «Codice postale» prima di «Monitora il mio ordine». Di solito Paack usa il numero d'ordine del negozio o il codice a barre dell'etichetta, senza formato fisso: cercalo nelle e-mail del venditore, nel link inviato da Paack all'ingresso in magazzino o sull'etichetta. Non c'è? Chiedi al negozio o leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

Alcune etichette seguono uno schema riconoscibile, mentre i numeri d'ordine possono somigliare a quelli di altri corrieri (esempi inventati):

| Numero d'ordine o etichetta | Che cos'è e a cosa assomiglia |
| --- | --- |
| `PK123456789012345678` | `PK` e 18 cifre: pacco [Evri](carrier:evri) International consegnato da Paack; su Peek si segue anche senza CAP. |
| `534012345678901234` | `5340` e 14 cifre (18 totali): etichetta ricorrente sui pacchi Paack. |
| `C26111512345678` | `C` o `E`, data a 6 cifre (`AAMMGG`) e 8 cifre: pacco [ordini SHEIN](guide:shein-orders), usato anche da [Ecoscooting](carrier:ecoscooting) in Spagna. |
| `123456789AB` | Lettere e cifre senza schema fisso (a 12 caratteri alfanumerici somiglia a Colis Privé). |
| `12345678` | 8 cifre: usato anche da Mondial Relay, Colisweb e Heppner. |
| `123456789012` | 12 cifre: usato anche da [FedEx](carrier:fedex), [Poste Italiane](carrier:poste-italiane), [BRT](carrier:brt) e Colis Privé. |

Se il negozio indica Paack come corriere, il codice va cercato su Paack qualunque sia la forma; vedi [formati dei numeri di tracciamento](guide:tracking-number-formats). Un numero o CAP errato dà `Order not found` / `Incorrect order number or postal code`. Finché Paack non registra il pacco in magazzino, le FAQ consigliano di sentire il venditore.

## Stati del tracking Paack: cosa significano

Paack riassume il percorso in quattro tappe (dati ricevuti, centro di distribuzione Paack, in consegna, consegnato) e nella cronologia aggiunge i passaggi intermedi. Peek li mostra in italiano, lasciando in inglese tre righe tecniche:

| Stato su Peek | Cosa significa per te |
| --- | --- |
| `Dati della spedizione ricevuti` | Il negozio ha inviato i dati dell'ordine a Paack, che non ha ancora il pacco. |
| `Spedizione presa in carico` | Prima scansione del pacco nel centro di distribuzione Paack. |
| `In transito` | Il pacco è nella rete Paack, non ancora uscito in consegna. |
| `Courier assigned` | Corriere assegnato alla consegna; ricompare prima di ogni nuovo tentativo. |
| `In consegna` | Sul mezzo del corriere per la consegna nella fascia oraria indicata. |
| `Delivery attempt failed`, `Problema di consegna` | Non consegnato: destinatario assente, oppure PaackGo Point chiuso, pieno o che ha rifiutato il collo. |
| `New delivery attempt scheduled` | Nuovo tentativo fissato nella nuova fascia mostrata. Se registrato nello stesso istante del tentativo fallito, fa parte di quel passaggio e Peek non lo ripete. |
| `Pronto per il ritiro` | Ti aspetta al PaackGo Point; la pagina di Paack indica l'ultimo giorno utile. |
| `Consegnato` | Consegnato a domicilio o ritirato al PaackGo Point. |
| `In restituzione al mittente`, `Restituito al mittente` | Torna al negozio o è già rientrato (più tentativi falliti, scadenza superata, rifiuto o mancato pagamento al punto). |
| `Problema con la spedizione` | Problema sul pacco, come danno o smarrimento al PaackGo Point: contatta il negozio. |

Altre diciture sono spiegate in [stati di tracciamento](guide:tracking-statuses).

## Fasce orarie Paack, assenza e PaackGo Point

**Fascia oraria.** La pagina di Paack indica giorno e fascia oraria previsti nell'ora locale dell'indirizzo di consegna (o un arco di più giorni); dopo un passaggio a vuoto mostra quelli del nuovo tentativo. Se il servizio scelto dal venditore lo prevede puoi selezionare una fascia specifica, altrimenti puoi lasciare istruzioni al corriere via chat.

**Se non sei in casa.** Ogni spedizione Paack include **almeno due tentativi di consegna** (il numero esatto dipende dall'accordo col venditore), il secondo il giorno seguente o al primo giorno lavorativo utile; vedi [consegna mancata](guide:missed-delivery). Salvo tua diversa indicazione, in tua assenza Paack può lasciare il pacco al **vicino più vicino disponibile** o al **custode del condominio**. Se al primo passaggio l'indirizzo risulta errato, Paack ti avvisa via e-mail per aggiornarlo sulla pagina di tracking o in chat (se il venditore lo consente e il pacco non è già in consegna). Dopo vari tentativi falliti l'ordine torna al mittente — per questo vedi cambiare l'indirizzo sul tracking — e devi accordarti col venditore. Paack non lascia bolle cartacee: registra le ricevute in digitale e le rende disponibili al venditore.

**PaackGo Point.** All'arrivo nel punto di ritiro, la pagina di Paack mostra nome, indirizzo, codice di ritiro, QR code e scadenza. Oltre il termine il pacco rientra al negozio; vedi [pacco non ritirato](guide:uncollected-parcel).

## Pacco Paack in ritardo, non consegnato o danneggiato

Nell'UE il venditore risponde dei beni «dalla spedizione fino alla ricezione» e, salvo diverso accordo, consegna entro 30 giorni; rimborsi e rispedizioni passano sempre dal negozio:

- **Fermo o in ritardo:** consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating), poi contatta il venditore concedendo un termine supplementare ragionevole.
- **Consegnato ma non ricevuto:** chiedi a familiari, vicini e custode, poi al venditore (che accede alla ricevuta elettronica Paack); vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).
- **Danneggiato, perso o restituito al mittente:** rientrato al negozio, il pacco non è più in mano a Paack; scrivi al venditore e vedi [pacco danneggiato](guide:damaged-parcel) o [pacco perso](guide:lost-parcel).

## Assistenza e contatti Paack in Italia

Dal centro assistenza italiano Paack (novembre 2026):

- **Chat online:** in basso a destra nelle [FAQ Paack](https://paackcustomerservice.freshdesk.com/it/support/home) e sulla [pagina di tracciamento](https://paack.co/shipments/tracking?lng=it), per istruzioni al corriere o modifiche d'indirizzo consentite dal venditore.
- **Telefono ed e-mail:** Paack non pubblica un telefono né un'e-mail clienti per l'Italia (il modulo sul sito è riservato ai clienti commerciali); invia però e-mail automatiche con il link di tracking e in caso di problemi d'indirizzo.

## Tracciare un pacco Paack con Peek

[Peek](/) riconosce subito come Paack i codici `PK` con 18 cifre ([Evri](carrier:evri) International) e li segue senza CAP. Per le etichette `5340` più 14 cifre, «Cambia» elenca Paack per primo sotto «Compatibili con questo numero» (in Belgio dopo bpost); nella stessa sezione compare anche per i numeri SHEIN (`C` o `E`, data a sei cifre e 8 cifre), mentre Peek interroga prima [Ecoscooting](carrier:ecoscooting). Gli altri numeri Paack non hanno forma fissa e senza CAP Peek non può chiederli a Paack: i codici con lettere restano senza corriere («Peek lo cercherà dopo l’aggiunta.»), quelli di sole cifre vengono proposti ad altri corrieri di pari lunghezza (come [FedEx](carrier:fedex), [Poste Italiane](carrier:poste-italiane) o [BRT](carrier:brt)). Premi «Cambia» e scegli Paack, o incolla un link con `tracking_number=` da `mydeliveries.paack.app` o `paack.co/shipments/tracking`.

Inserisci quindi «Codice postale di consegna» (facoltativo solo per i numeri `PK`; un CAP presente nel link non viene usato). I CAP già usati compaiono sotto il campo («Usa …»), anche in «Cambia corriere». Il CAP va a Paack e, se serve, ai servizi di tracciamento; se numero e CAP non combaciano, l'ordine appare sconosciuto. Peek mette il codice in maiuscolo togliendo spazi, punti e trattini: se non esce nulla, prova anche il codice invariato sul sito Paack.

Vedi stato, cronologia e finestra prevista nell'ora locale di consegna (o primo e ultimo giorno su finestre di più giorni; ora delle Canarie per i CAP spagnoli `35` e `38`), mentre gli eventi sono nel tuo fuso («Orari nel tuo fuso orario: …» su peektracker.com e su iPhone). Al PaackGo Point Peek mostra «Pronto per il ritiro» con nome e indirizzo (predisposto dal codice Paack, non ancora visto dal vivo), mai codice di ritiro, QR o scadenza; il ritiro appare come «Consegnato». Paack non fornisce luoghi di scansione né corrieri successivi. Per un `PK` senza CAP la cronologia viene da Evri International (in inglese, con luoghi e peso, senza fascia oraria); con il CAP Peek interroga prima la pagina Paack per orari esatti e fascia, passando a Evri se Paack non trova la coppia. Nome del negozio, tuo nome e indirizzo sono omessi. Peek controlla ogni 10 minuti, o ogni 2 minuti il giorno di consegna dalle 6:00 locali o mezz'ora prima della fascia finché risponde Paack o Evri (15 minuti via servizi di tracciamento), con avvisi per «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna», e ogni ora dopo due giorni senza novità o 12 ore su «Etichetta creata» o «Preso in carico».

## Domande frequenti sul tracking Paack

### Che cos'è Paack e cosa c'entra con Colis Privé?

Paack è un corriere ultimo miglio attivo in Italia, Spagna, Francia e Portogallo. Il 16 ottobre 2026 Colis Privé ha annunciato che, dopo l'unione dell'estate 2026, Colis Privé e Paack adotteranno gradualmente da gennaio 2027 l'unico marchio **Paack** (con rete congiunta in Francia, Belgio, Lussemburgo, Spagna e Portogallo); fino ad allora consegne e tracking restano invariati presso il corriere indicato dal negozio.

### Perché Paack chiede il codice postale e risponde «Order not found»?

Paack mostra l'ordine solo a chi inserisce sia il numero d'ordine (o etichetta) sia il CAP di consegna. Se uno dei due contiene un errore — o usi il CAP di fatturazione — risponde `Order not found`.

### Cosa succede se Paack riprogramma la consegna?

Riprova il giorno seguente o al primo giorno lavorativo utile. Peek mostra `Delivery attempt failed` e la nuova fascia; una riprogrammazione registrata nello stesso istante non viene duplicata, mentre una successiva appare come `New delivery attempt scheduled` (`Problema di consegna` se il PaackGo Point era chiuso, pieno o ha rifiutato il collo).

### Quanto tempo impiega Paack per consegnare?

Paack offre ai negozi consegne in giornata, il giorno successivo o su fascia programmata, visibili sul tracking appena preso in carico il collo; prima fa fede il termine del venditore (nell'UE entro 30 giorni salvo diverso accordo).

:::sources
- [Paack: Segui il tuo ordine](https://paack.co/shipments/tracking?lng=it) – campi «Numero d'ordine» e «Codice postale» sulla pagina italiana
- [Paack Support: Home Assistenza](https://paackcustomerservice.freshdesk.com/it/support/home) – due tentativi, vicino, custode del condominio, modifica indirizzo e istruzioni via chat
- [Paack Support: Stato del mio ordine](https://paackcustomerservice.freshdesk.com/it/support/solutions/folders/44001208238) – cambio indirizzo per reso al mittente, ricevute elettroniche senza bolla cartacea
- [Paack Support: Altre domande](https://paackcustomerservice.freshdesk.com/it/support/solutions/folders/44001208240) – minimo due tentativi di consegna, avvisi e-mail per problemi di indirizzo
- [Paack Support: Informazioni su Paack](https://paackcustomerservice.freshdesk.com/it/support/solutions/folders/44001208241) – presenza locale in oltre 50 città in Spagna, Francia, Italia e Portogallo, consegne in giornata o il giorno successivo
- [Colis Privé: Colis Privé and Paack unite under one brand: Paack (inglese)](https://colisprive.fr/en/blog/colis-prive-and-paack-unite-under-one-brand-paack/) – annuncio del 16 ottobre 2026: partnership dell'estate 2026, marchio unico Paack da gennaio 2027, continuità del tracking
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – responsabilità del venditore fino alla ricezione e consegna entro 30 giorni nell'UE
- [Universal Parcel Scraper: Paack (inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – formati `PK`, `5340` e SHEIN, verifica del CAP, stati su Peek, fascia oraria e PaackGo Point
:::
