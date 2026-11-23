---
title: Tracking GLS Austria: stati, Parcel Shop e contatti
description: Traccia un pacco GLS in Austria: numero e Track ID, significato degli stati tedeschi, giacenza Parcel Shop, dogana e contatti.
slug: tracking-gls-austria
published: 2026-11-23
updated: 2026-11-23
---

GLS Austria (GLS Österreich) ha sede ad Ansfelden e dispone di 11 depositi. Consegna dal lunedì al venerdì, e i pacchi spediti in Austria arrivano in genere entro 24 ore. Traccia un pacco sulla [pagina di tracking di GLS Austria](https://gls-group.com/AT/en/parcel-tracking/) con il numero pacco o il Track ID riportato sull'avviso GLS, oppure incolla il numero nel box qui sopra. Il numero proviene dal mittente: parti quindi dall'e-mail di spedizione del negozio. Sulla pagina tedesca di GLS gli stati compaiono in tedesco: la tabella sotto li spiega.

## Numero pacco GLS Austria e Track ID

Il tracciamento austriaco di GLS accetta numeri pacco e Track ID, anche più di uno separati da spazio, virgola o punto e virgola. Il Track ID compare sull'avviso lasciato o inviato via e-mail dopo un tentativo di consegna, che indica anche dove si trova il pacco. GLS non pubblica un formato ufficiale. I formati che Peek riconosce per GLS Austria:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `48291736504` | 11 cifre. Coincide anche con [GLS Germany](carrier:gls-de), [GLS Switzerland](carrier:gls-ch), [GLS France](carrier:gls-fr), GLS Poland, PostLogistics e altri. |
| `482917365042` | 12 cifre, l'ultima è una cifra di controllo. Coincide anche con GLS Germany, GLS Switzerland, GLS France, GLS Poland, BRT e altri. |
| Track ID | Sull'avviso di passaggio GLS. La pagina GLS lo accetta; in Peek usa il numero pacco. |

Se l'e-mail del negozio cita [Austrian Post](carrier:austrian-post) o un altro corriere, il numero appartiene a loro. Nessun numero? Leggi [dove trovare il numero di tracking](guide:find-tracking-number).

## Significato degli stati di tracking GLS Austria

[La pagina tedesca di tracking di GLS Austria](https://gls-group.com/AT/de/paket-verfolgen/) mostra uno stato principale, una barra di avanzamento (`Datenerfassung`, `Unterwegs`, `Ziel-Paketzentrum`, `In Zustellung`, `Zugestellt`) e, inserito il codice postale di consegna, le singole scansioni:

| GLS mostra | Cosa vuol dire per te |
| --- | --- |
| `Daten übermittelt`, `Die Paketdaten wurden im GLS IT-System erfasst; das Paket wurde noch nicht an GLS übergeben.` | Il mittente ha inserito i dati. GLS non ha ancora il pacco. |
| `Das Paket wurde vom Versender zur Abholung durch GLS bereitgestellt.` | Il mittente lo ha preparato per il ritiro GLS. |
| `Das Paket wurde durch GLS abgeholt.`, `Das Paket wurde durch GLS übernommen.` | GLS ha preso in carico il pacco. |
| `Unterwegs`, `Das Paket hat das Paketzentrum verlassen.`, `Das Paket ist im Paketzentrum eingetroffen.` | In transito tra centri pacchi GLS. |
| `Ziel-Paketzentrum` | Al deposito di consegna: il passo successivo è la consegna. |
| `In Zustellung`, `Das Paket wird voraussichtlich im Laufe des Tages zugestellt.` | Sul furgone, previsto per oggi. |
| `Nicht zugestellt`, `Das Paket konnte nicht zugestellt werden, da der Empfänger nicht angetroffen wurde.` | Non consegnato: nessuno era in casa. |
| `Der Empfänger wurde mittels E-Mail über den Zustell-/Abholversuch informiert.` | GLS ti ha informato via e-mail sul tentativo. |
| `Eingelagert`, `Das Paket befindet sich im Ziel-Paketzentrum. Es konnte nicht zugestellt werden, da der Empfänger nicht angetroffen wurde.` | Rientrato al deposito dopo un tentativo fallito e messo in giacenza. |
| `Das Paket ist im GLS PaketShop eingetroffen.`, `Zugestellt im PaketShop` | Ti aspetta al GLS Parcel Shop indicato nel tracciamento. |
| `Zugestellt`, `Das Paket wurde erfolgreich zugestellt.` | Consegnato. |

`Zugestellt im PaketShop` dice consegnato, ma devi comunque ritirare il pacco al Parcel Shop.

## Consegna GLS in Austria: vicini, Parcel Shop e locker

**Quando.** Dal lunedì al venerdì in orario lavorativo. Le condizioni GLS contano i giorni lavorativi escluso il sabato, non garantiscono le 24 ore e non consentono di scegliere un'ora esatta. Se il mittente usa FlexDeliveryService, GLS invia via e-mail un link di tracking che mostra il giorno previsto alla presa in carico e, il giorno stesso, quante fermate mancano.

**Nessuno in casa.** L'autista tenta prima presso un vicino (che firma) oppure in un Parcel Shop o Parcel Locker vicino, e GLS lascia un avviso o invia un'e-mail con il luogo. I pacchi non deviabili (aziendali o espressi) vengono riprovati il giorno lavorativo successivo e, in caso di ulteriore esito negativo, rientrano in deposito. GLS fa al massimo due tentativi. Alcuni pacchi, come quelli con obbligo di documento d'identità con foto, vengono consegnati solo di persona.

**Ritiro.**
- Parcel Shop: dal giorno successivo al tentativo, fino a otto giorni lavorativi, con avviso e documento con foto.
- Parcel Locker: fino a tre giorni lavorativi, seguendo le istruzioni dell'avviso.
- Deposito: dal giorno successivo, entro quattro giorni lavorativi. Scrivi prima a GLS tramite il modulo destinatari e porta un documento con foto.

Poi il pacco rientra automaticamente al mittente. Un delegato può ritirarlo con delega scritta (sul retro dell'avviso o modulo PDF GLS) e proprio documento con foto. GLS conta 1.000 Parcel Shop e 1.500 locker in Austria; i locker sono aperti 24 ore su 24.

**Modificare la consegna.** Una volta ricevuti avviso o e-mail, la pagina di tracking mostra gratis le opzioni consentite: Parcel Shop o locker, altro giorno entro sei giorni di calendario dalla spedizione (stessa fascia), deposito oppure un punto di deposito autorizzato come porta o giardino. Un indirizzo diverso è disponibile solo tramite FlexDeliveryService del mittente. Per la consegna entro le 12 occorre che il mittente prenoti ExpressService.

**Autorizzazione di deposito.** Può essere concessa una tantum o permanente, gratis, sull'avviso, tramite modulo PDF o account GLS-ONE; si revoca con lettera firmata o e-mail al deposito più vicino. Lasciato il pacco lì, GLS non risponde più di perdita o danni. Non vale, tra l'altro, per contrassegno o merci pericolose.

## Pacco GLS in ritardo, smarrito, danneggiato o in dogana

Per rimborso o sostituzione rivolgiti al negozio.

**Nessun aggiornamento.** `Daten übermittelt` per giorni indica che il negozio non ha ancora consegnato il pacco: chiedi al mittente. `Eingelagert` indica giacenza in deposito: scegli un'opzione nella sezione «Not at home?» se disponibile oppure compila il modulo destinatari prima di passare. Altrimenti vedi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Consegnato ma non ricevuto.** Verifica sul tracking se è in un Parcel Shop, controlla cassetta ed e-mail per un avviso che nomini un vicino e controlla il punto di deposito autorizzato. Poi avvisa GLS tramite il modulo destinatari («Where was my parcel delivered / handed over?»). Altro in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Se l'imballo è visibilmente rovinato puoi rifiutarlo: GLS lo riprende e informa il mittente. Se è danneggiato solo il contenuto, contatta il mittente. GLS chiede entrambi gli indirizzi, descrizione del contenuto e foto di merce, imballo ed etichette. Vedi [pacchi danneggiati](guide:damaged-parcel).

**Dogana.** Gli acquisti online da fuori UE prevedono dal 1° luglio 2026 un dazio di 3 € per articolo sui pacchi fino a 150 € e dal 1° novembre 2026 una tariffa di gestione dell'Unione di 2 € per articolo di qualunque valore. Un articolo raggruppa beni con stessa classificazione tariffaria, descrizione e, se richiesta, origine. Le pagine GLS Austria non dicono se né come addebita tali importi: chiedi al negozio se erano inclusi nel prezzo. Vedi [pacchi fermi in dogana](guide:customs).

## Contatti e assistenza clienti GLS Austria

Peek non può contattare GLS o il negozio per te:

- **Modulo destinatari:** [modulo GLS Austria per destinatari](https://gls-group.com/AT/en/contact-form/recipient-request/), assistenza clienti dal lunedì al venerdì dalle 8:00 alle 18:00 (ora austriaca). Richiede numero pacco o Track ID, codice postale, telefono ed e-mail.
- **Telefono:** +43 5 9876 3000 nei dati societari di GLS Austria. +43 059876 1000 è il call center GLS-ONE per i mittenti. GLS non elenca un numero telefonico dedicato ai destinatari.
- **Posta:** General Logistics Systems Austria GmbH, Traunuferstraße 105a, 4052 Ansfelden.

> GLS non chiede mai ai destinatari via e-mail di pagare merci online, con carta o bonifico: il contrassegno si paga in contanti all'autista e non va pagato nulla per un pacco inatteso. Sono autentici gls-group.com e i siti a cui rimanda. Inoltra le e-mail false come allegato a security@gls-group.com (per un SMS, screenshot e numero mittente) e rivolgiti alla polizia se hai pagato. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco GLS Austria con Peek

Incolla il numero in [Peek](/). Diverse società GLS usano questi formati, quindi Peek interroga le più probabili in base al tuo Paese (regione del telefono o Paese di connessione sul web). In Austria vedrai di solito «GLS Austria ha questo pacco.». Altrove nomina spesso un'altra filiale come GLS Germany, che interroga lo stesso servizio GLS ma chiede il codice postale: tienila e inserisci il codice postale, oppure premi «Cambia» e seleziona GLS Austria. Se nessuna società GLS conosce ancora il codice, aggiungilo comunque o scegli GLS Austria dall'elenco: Peek traccia oltre 4.500 corrieri. Un link incollato da gls-group.com o gls-group.eu con /AT/ nel percorso viene tracciato come GLS Austria.

Il campo «Codice postale di consegna» è segnato «Facoltativo», ma GLS mostra le scansioni solo con esso, come indica la riga sotto il campo: «GLS Austria mostra le scansioni solo con il codice postale di consegna.» Senza codice postale Peek verifica solo se GLS conosce il numero, interroga intanto altri servizi di tracciamento e propone sulla scheda «Aggiungi codice postale». Con un account Peek prova una volta l'ultimo codice postale fornito per un pacco GLS Austria o altrimenti quello del pacco più recente. Se GLS non trova il pacco con quel codice, Peek lo segnala e offre «Modifica codice postale». Finché GLS attende il codice postale o non trova il pacco con il tuo, e nessun'altra fonte risponde per un giorno, Peek controlla una volta al giorno; l'aggiunta o la modifica avviano subito un controllo. Il codice postale viene inviato a GLS e, se necessario, ai servizi di tracciamento, e non appare mai nei link condivisi.

Con il codice postale vedi stato, luogo di ogni scansione su mappa, giorno previsto, peso, ora di consegna e, per un Parcel Shop, nome e indirizzo. Nome, indirizzo, contatti, firma e riferimento del mittente sono esclusi. Peek legge il servizio inglese di GLS anziché la pagina tedesca: `In Zustellung` compare come `In delivery` e `Das Paket wurde erfolgreich zugestellt.` come `The parcel has been delivered.`. L'arrivo al Parcel Shop appare come «Pronto per il ritiro». Gli orari sono letti in ora austriaca. Peek controlla GLS circa ogni ora, giorno di consegna compreso (4 ore dopo un tentativo fallito), e può avvisarti dei cambiamenti.

## Domande sul tracking GLS in Austria

### Quanto impiega GLS a consegnare in Austria?

Di norma 24 ore in Austria, dal lunedì al venerdì, senza garanzia. GLS Austria pubblica i tempi solo per spedizioni in partenza dall'Austria: per un pacco dall'estero segui la stima del negozio.

### GLS consegna il sabato in Austria?

No: solo dal lunedì al venerdì.

### Per quanto tempo GLS tiene il mio pacco?

Fino a otto giorni lavorativi al Parcel Shop, tre al locker e quattro al deposito. Poi torna al mittente.

### Che cos'è il GLS Track ID?

Il codice sull'avviso di passaggio GLS. La pagina di tracciamento lo accetta come un numero pacco e mostra le opzioni di consegna disponibili.

### Posso scegliere un orario di consegna con GLS?

Non un orario preciso. Puoi spostare la consegna a un altro giorno nella stessa fascia; per la consegna entro mezzogiorno il mittente deve prenotare ExpressService.

:::sources
- [GLS Austria: Parcel tracking](https://gls-group.com/AT/en/parcel-tracking/) – numeri e Track ID, luoghi di deposito
- [GLS Austria: Paket verfolgen](https://gls-group.com/AT/de/paket-verfolgen/) – pagina tedesca, scansioni solo con codice postale di consegna
- [GLS Austria: Receiving parcels](https://gls-group.com/AT/en/receiving-parcels/) – 24 ore, vicini, secondo tentativo, giacenze
- [GLS Austria: FAQ receiving parcels](https://gls-group.com/AT/en/faq/receiving-parcels/) – più numeri, nessun orario esatto, documento, danni
- [GLS Austria: Parcel Shops and Parcel Lockers](https://gls-group.com/AT/en/receiving-parcels/parcelshops-parcel-lockers/) – numeri di sedi, documento, reso automatico
- [GLS Austria: Redirect parcel](https://gls-group.com/AT/en/receiving-parcels/redirect-parcel/) – opzioni, giorni lavorativi, altro giorno, ExpressService
- [GLS Austria: FlexDeliveryService](https://gls-group.com/AT/en/receiving-parcels/redirect-parcel/flexdeliveryservice/) – e-mail, tracciamento live
- [GLS Austria: Drop-off permission](https://gls-group.com/AT/en/receiving-parcels/drop-off-permission/) – singola o permanente, eccezioni, responsabilità
- [GLS Austria: Recipient request](https://gls-group.com/AT/en/contact-form/recipient-request/) – orari, campi, consegnato non ricevuto
- [GLS Austria: Corporate information](https://gls-group.com/AT/en/corporate-information/) – azienda, indirizzo, telefono
- [GLS Austria: Customer information](https://gls-group.com/AT/en/customer-information/) – call center GLS-ONE
- [GLS Austria: Special terms and conditions](https://gls-group.com/AT/en/terms-and-conditions/) – niente sabato, nessuna garanzia, due tentativi
- [GLS Austria: Standard delivery times](https://gls-group.com/AT/en/standard-delivery-time/) – 24 ore, tempi solo dall'Austria
- [GLS Austria: Security advice](https://gls-group.com/AT/en/press/gls-informs/security-advice/) – truffe, segnalazioni
- [GLS Austria: Home](https://gls-group.com/AT/de/home/) – 11 depositi, Ansfelden
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per articolo fino a 150 € dal 1° luglio 2026, tariffa di 2 € dal 1° novembre 2026
- [Commissione europea: guida al dazio di 3 € e alla tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articolo di qualunque valore, definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati, cosa legge Peek, testi tedeschi e inglesi di GLS Austria, codice postale
- [Peek: Routing](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – controlli giornalieri in attesa del codice postale o non trovato, controllo immediato all'aggiunta o modifica
:::
