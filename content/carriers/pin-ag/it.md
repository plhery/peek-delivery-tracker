---
title: Tracciamento PIN AG: raccomandate, pacchi e ritiro
description: Traccia una lettera o un pacco PIN AG in Germania: quali invii sono tracciati, significato degli stati in tedesco, ritiro in deposito e contatti.
slug: tracciamento-pin-ag
published: 2026-11-23
updated: 2026-11-23
---

PIN AG è un operatore postale di Berlino, separato da Deutsche Post, con circa 1.300 dipendenti. Consegna lettere, raccomandate (Einschreiben), pacchi e ordini di Amazon, medimops e rebuy. Solo parte di questi invii è tracciabile. Controlla una spedizione sulla [pagina di tracciamento PIN AG](https://sendungsverfolgung.pin-ag.de/) (Sendungsverfolgung) oppure incolla il numero nella casella qui sopra. Il numero proviene dal mittente: un negozio lo mette nell’e-mail di spedizione e per una raccomandata va richiesto a chi l’ha spedita.

## Quali invii PIN AG si possono tracciare?

PIN traccia solo gli invii che richiedono prova di consegna, dal momento in cui li riceve fino al recapito. Lo decide il mittente scegliendo il prodotto. Le pagine di aiuto di PIN elencano:

| Tracciati | Non tracciati |
| --- | --- |
| Raccomandate: Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein e -Persönlich-Rückschein | Lettere standard (Standardsendungen) |
| Notifiche formali (Postzustellaufträge): atti ufficiali, per esempio di tribunali o enti | Invii di libri e merci (Bücher- und Warensendungen) |
| Pacchi (Pakete) | Ordini medimops e rebuy |
| Invii Amazon Prime, in tutta la Germania | Invii Amazon ordinari fuori Berlino |
| Invii Amazon dentro Berlino | Posta pubblicitaria (Werbepost) |

Le FAQ sulla pagina di tracciamento PIN differiscono su un punto: affermano che gli invii Amazon ordinari (non Prime) non si possono tracciare, senza eccezione per Berlino. Un ordine Amazon ordinario può quindi non mostrare alcuno stato.

## Numero di tracciamento PIN AG

I formati che Peek conosce (cifre inventate):

| Numero (inventato) | Cos’è |
| --- | --- |
| `0100482917364058` | 16 cifre che iniziano con `0100`: un numero di pacco PIN, come lo legge il codice di Peek. Le 16 cifre corrispondono anche a [DHL eCommerce](carrier:dhl-ecommerce) e [TNT](carrier:tnt). |
| `4829173640582` | 13 cifre: una raccomandata o una notifica formale. Anche molti altri corrieri usano 13 cifre. |
| `482917364058(2)` | Le stesse 13 cifre come può stamparle un’etichetta, con l’ultima cifra tra parentesi. Peek le legge come `4829173640582`. |

Se l’e-mail del negozio nomina [DHL](carrier:dhl) o un altro corriere, il numero è loro. Nessun numero? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Stati di tracciamento PIN AG spiegati

La pagina di tracciamento PIN mostra una barra di avanzamento in quattro fasi (`Sortierzentrum`, `Zustelldepot`, `In Zustellung`, poi `Zugestellt`, oppure `Unzustellbar` se non consegnabile) insieme allo stato in tedesco. Cosa significano:

| PIN mostra | Cosa significa per te |
| --- | --- |
| `Sortierzentrum` | L’invio è al centro di smistamento PIN. |
| `Zustelldepot` | È arrivato al deposito di consegna della tua zona. |
| `In Zustellung` | In consegna. Di solito PIN consegna in giornata: i portalettere operano fino al pomeriggio. Se la consegna fallisce, ritentano il giorno dopo. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Consegnato. |
| `Unzustellbar` | Impossibile consegnare. PIN restituisce al mittente gli invii non consegnabili. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN lo ha registrato al centro di smistamento e lo consegnerà nei prossimi giorni senza altri aggiornamenti: PIN non documenta il recapito di questo tipo di invio. |

Quest’ultimo stato compare alla fase `In Zustellung`, ma l’invio non è ancora uscito in consegna.

## Consegna PIN AG: cassetta, delegato e deposito

**Chi consegna.** A Berlino PIN consegna una lettera di norma il giorno dopo la lavorazione al centro di smistamento. Quando PIN affida l’invio a un partner locale (Zustellpartner) fuori Berlino, la consegna può richiedere un po’ più di tempo e lo stato può smettere di cambiare, anche per una raccomandata ([perché il tracciamento si ferma](guide:tracking-not-updating)). Un invio Amazon ordinario fuori Berlino viaggia come lettera semplice, quindi lo stato non cambia mai.

**Come.** PIN lascia l’invio in cassetta o te lo consegna a mano. Se non è possibile, può affidarlo a un sostituto: un familiare, qualcuno del nucleo domestico, un dipendente presso la tua azienda oppure il proprietario o locatore dell’abitazione. Non lo fa se serve consegna personale o se tu o il mittente l’avete vietato. Le raccomandate seguono le regole della rispettiva formula. PIN non può consegnare presso una Packstation.

**Ritiro in deposito.** Se PIN non riesce a consegnare, trattiene l’invio presso un deposito PIN (Zustelldepot) o un partner e ti avvisa con una cartolina (Benachrichtigungsschein).
- Recati dal giorno lavorativo successivo negli orari di apertura. La cartolina indica indirizzo e orari del deposito, così come la pagina di tracciamento PIN inserendo il codice.
- Porta un documento ufficiale con foto (obbligatorio) e la cartolina. Senza cartolina servono il numero di tracciamento e il giorno del tentativo di consegna.
- Un’altra persona può ritirarlo con la delega sulla cartolina (Vollmacht) e il proprio documento con foto.
- PIN lo custodisce per 7 giorni lavorativi (dal lunedì al sabato) dal giorno successivo al tentativo. Una notifica formale resta in giacenza 3 mesi e risulta notificata il giorno del deposito.
- Scaduto il termine, l’invio torna automaticamente al mittente.

## Invio PIN AG in ritardo, smarrito o restituito

Per sostituzione o rimborso rivolgiti al mittente: secondo PIN è la via più rapida.

- **Non arrivato dopo 8 giorni lavorativi.** Conta dal lunedì al sabato dalla data di spedizione. Chiedi al mittente un nuovo invio o un rimborso. Puoi anche inviare a PIN una richiesta di ricerca (Nachforschungsauftrag) con il modulo online. La ricerca può richiedere fino a 21 giorni e PIN potrebbe non trovare l’invio. Per le condizioni PIN, un invio si presume smarrito se non viene consegnato entro 20 giorni dalla presa in carico e non è rintracciabile. Vedi [pacchi smarriti](guide:lost-parcel).
- **`In Zustellung` per più di tre giorni.** PIN invita a contattarla tramite il modulo collegato sulla pagina di tracciamento.
- **Consegnato ma non c’è.** Chiedi a chi vive con te: PIN può consegnare a un sostituto. Per gli ordini Amazon, PIN indica che la pagina di tracciamento mostra dove è avvenuta la consegna, per esempio presso un vicino, inserendo codice e CAP. Anche un errore tecnico può segnare l’invio come consegnato in anticipo: attendi un giorno, perché spesso arriva l’indomani. Maggiori dettagli in [consegnato ma non ricevuto](guide:delivered-not-received).
- **Danneggiato.** Segnalalo a PIN tramite il modulo «Hinweis zur Briefzustellung», usato anche per posta in ritardo, errata o irregolare. PIN risponde solo in alcuni casi. Se il contenuto è danneggiato, contatta il mittente.
- **Restituito.** PIN rispedisce indietro gli invii se manca una cassetta con nome o una fessura, se nessuno li ritira in tempo o se sono troppo danneggiati per la consegna. Gli ordini Amazon tornano ad Amazon anche se l’indirizzo è errato o incompleto, se il tuo nome non è sulla cassetta, se l’invio non rispetta il formato lettera concordato con Amazon o se è indirizzato a una Packstation. PIN non può cambiare indirizzo durante la consegna: chiedi una nuova spedizione ad Amazon. Vedi [restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti PIN AG

Peek non può contattare PIN AG né il mittente al posto tuo. Recapiti indicati da PIN AG:

- **ServiceCenter:** 030 577978-800, dal lunedì al venerdì dalle 8:00 alle 18:00 (ora tedesca).
- **Moduli online:** richiesta di ricerca (Nachforschungsauftrag) e segnalazione consegna (Hinweis zur Briefzustellung) dalle pagine di aiuto PIN, oltre ai moduli di contatto collegati sulla pagina di tracciamento.
- **Sede centrale:** PIN AG, Alt-Moabit 91, 10559 Berlino. Telefono 030 577978-0, e-mail info@pin-ag.de.

## Tracciare un invio PIN AG con Peek

Incolla il numero in [Peek](/), con o senza parentesi: Peek unisce automaticamente un’ultima cifra tra parentesi alle altre.

- **16 cifre che iniziano con 0100.** Poiché anche altri corrieri usano 16 cifre, Peek interroga i più probabili in base al tuo paese (regione del telefono o, sul web, fuso orario del browser). In Germania interroga prima PIN AG. Durante la ricerca mostra «Verifica dei servizi di tracciamento…». Se PIN ha l’invio, compare «PIN AG ha questo pacco.» e Peek lo registra lì. Fuori dalla Germania, Peek interroga PIN AG solo dopo che gli altri corrieri non hanno trovato il numero.
- **Non ancora in elenco.** Aggiungilo lo stesso. In Germania, se Peek ha chiesto a PIN ma il codice non risulta ancora, compare «In attesa della prima scansione di PIN AG» e «Probabilmente PIN AG», e Peek continua a chiedere a PIN. Dopo una settimana Peek ti invita a verificare il numero con il mittente o scegliere un altro corriere.
- **13 cifre.** Peek non riconosce PIN da questa sola forma. Incollalo ugualmente: Peek segue oltre 4.500 corrieri. Premi «Cambia» e scegli PIN AG (scrivi PIN). Su un pacco già aggiunto usa «Cambia corriere».
- **Un link alla pagina di tracciamento PIN** con il tuo numero riconosce subito PIN AG («Corriere rilevato»).

**Cosa mostra Peek.** Lo stato PIN e ogni passaggio con l’orario, nelle parole tedesche di PIN e sull’ora tedesca. `Sortierzentrum`, `Zustelldepot` e lo stato senza ulteriori aggiornamenti compaiono come «In transito», `In Zustellung` come «In consegna», `Zugestellt` come «Consegnato» e `Unzustellbar` come «Problema segnalato». Un invio in attesa al deposito (la pagina PIN mostra allora indirizzo e orari del deposito) appare come «Pronto per il ritiro», qualunque fase indichi PIN. PIN non fornisce luoghi di scansione, data stimata di consegna, peso né nome del firmatario. Peek omette il tuo indirizzo e quello del deposito: per vedere il deposito usa «Apri il sito PIN AG», che apre il codice direttamente sulla pagina PIN. Una lettera standard non ha tracciamento in PIN, quindi neppure Peek può seguirla.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e dalle 6:00 (ora tedesca) del giorno in cui PIN registra l’invio in consegna fino a ogni 2 minuti finché il tracker proprio di PIN risponde. Dopo due giorni senza novità (12 ore finché l’invio resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora; e se resta su «Etichetta creata» per sette giorni, ogni 6 ore.

Se un invio ancora su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza una nuova scansione, Peek lo segnala con «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da …»), spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a PIN AG.» e propone «Chiedi aiuto a PIN AG». Puoi attivare gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento PIN AG

### Perché non posso tracciare la mia lettera PIN AG?

PIN traccia raccomandate, notifiche formali, pacchi e invii Amazon Prime, e le pagine di aiuto aggiungono gli invii Amazon a Berlino. Altre lettere, come quelle standard, non hanno tracciamento per scelta del mittente. PIN prevede che una lettera standard arrivi nei giorni lavorativi successivi.

### Quanto impiega PIN AG a consegnare?

A Berlino, di solito un giorno dopo la lavorazione nel centro di smistamento PIN. Quando subentra un partner di consegna fuori Berlino può volerci un po’ di più. Se non arriva nulla dopo 8 giorni lavorativi (lunedì-sabato) dalla spedizione, contatta il mittente.

### Per quanto tempo PIN AG trattiene la mia lettera al deposito?

7 giorni lavorativi (dal lunedì al sabato), dal giorno seguente al tentativo di consegna. Le notifiche formali attendono 3 mesi. Poi l’invio torna al mittente.

### Perché il tracciamento PIN AG non trova alcuno stato per il mio numero?

Le FAQ di PIN riportano il messaggio `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.` L’invio potrebbe essere di un tipo non tracciato come una lettera standard, il numero potrebbe avere un errore o parentesi, oppure superare i sei mesi: PIN cancella i dati di tracciamento dopo sei mesi per tutela dei dati.

### PIN AG consegna presso una Packstation?

No, PIN AG non può consegnare alle Packstation. Di norma Amazon spedisce quegli ordini con Deutsche Post; se comunque uno arriva a PIN, viene restituito ad Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – fasi di avanzamento; FAQ: invii tracciati, nessuno stato trovato, consegna in giornata, secondo tentativo il giorno dopo, moduli di contatto
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – invii tracciati, tempi di consegna, 8 giorni lavorativi, richiesta di ricerca, ritiro in deposito, resi, moduli
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – invii Amazon, consegnato ma non c’è, resi, Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – numero di telefono e orari
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – cassetta, sostituti, deposito, invii non consegnabili, smarrimento dopo 20 giorni
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – consegna lettere, circa 1.300 dipendenti, Berlino
- [PIN AG: Impressum](https://pin-ag.de/impressum) – indirizzo sede centrale, telefono ed e-mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek, fasi e diciture di PIN, formati dei numeri
:::
