---
title: Tracciamento Posti: codice JJFI, stati, locker e contatti
description: Traccia un pacco Posti in Finlandia: formato del codice JJFI, significato degli stati in inglese, giacenza nei locker, dogana e contatti.
slug: tracciamento-posti
published: 2026-11-22
updated: 2026-11-22
---

Posti è l'azienda postale e di spedizioni della Finlandia. Traccia un pacco sulla [pagina di tracciamento di Posti](https://www.posti.fi/en/tracking) o nell'app OmaPosti con il suo codice (*item ID*), oppure incolla il numero nel riquadro qui sopra. Il codice arriva dal mittente e compare anche sull'avviso di arrivo Posti in OmaPosti, via SMS o via e-mail.

## Numero di tracciamento Posti: l'item ID JJFI

Posti chiama il numero *item ID* (a volte *shipment ID*). Per i pacchi Posti inizia con `JJFI`. Il sito di Posti ha una versione inglese («EN» in alto).

| Numero (inventato) | Che cos'è |
| --- | --- |
| `JJFI63482917465283914` | Un pacco Posti: `JJFI` seguito da 17 cifre, 21 caratteri in totale. |
| `RR482917367FI` | Una raccomandata o spedizione tracciata impostata in Finlandia, in formato internazionale: due lettere, nove cifre, `FI`. |
| `RR482917367CN` | Un pacco dall'estero mantiene il numero del mittente. Questo finisce in `CN` (China Post); Posti lo traccia con lo stesso codice. |
| `00064382917465283912` | Un codice a barre SSCC a 20 cifre; `64` dopo le prime tre cifre è il prefisso della Finlandia. |

Se il mittente ha fornito a Posti il tuo numero di telefono, il pacco compare da solo in OmaPosti; altrimenti aggiungilo a mano. Il tracking di Posti risponde solo a un codice esatto, mai a nome o indirizzo.

Il codice di ritiro e il codice di apertura del locker (6 o 9 caratteri) non sono numeri di tracciamento. Un numero che inizia con `JJEE` appartiene a Omniva, le poste estoni, e uno che termina in `SE` a PostNord in Svezia. Non sai quale corriere sia? Vedi [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento Posti in inglese

Il tracking di Posti mostra le diciture in inglese, finlandese o svedese a seconda della lingua della pagina, spesso con una riga di spiegazione. Ecco quelle più comuni, grossomodo in ordine:

| Posti scrive | Cosa significa per te |
| --- | --- |
| `We have received information about an upcoming delivery from the sender` | Il mittente ha annunciato il pacco. Posti non lo ha ancora. |
| `Item received for transport` | Posti ha preso in carico il pacco. |
| `Item is in transport in country of origin.` | Spedito dall'estero. Nessuna stima di consegna ancora. |
| `Item arrived in the destination country.` | Arrivato in Finlandia e prosegue come consegna Posti. |
| `The item is in sorting` | In centro di smistamento. I pacchi vengono smistati una o due volte, di più su tratte lunghe. |
| `Item accepted from transport` | Ancora in viaggio. Non consegnato, nonostante la dicitura. |
| `Item is routed to an outlet because the locker was full.` | Il locker scelto era pieno, quindi va a un punto Posti. |
| `Item is in delivery transportation.` | Sul veicolo di consegna diretto da te. Peek mostra «In consegna». |
| `Item is ready for a pick up` | Pronto per il ritiro. L'avviso di arrivo indica luogo e codice. |
| `We sent the recipient a text message about the item` | Posti ti ha inviato un SMS. Il pacco non si è mosso. |
| `The item has been delivered` | Consegnato, anche dopo che lo hai ritirato. |
| `Item delivered to the recipient.` | Consegnato al destinatario. |

`Item accepted from transport` confonde molti destinatari. Posti precisa che è una marcatura di trasporto: la consegna è ancora in corso e nessuno ha consegnato il pacco. Le scansioni all'estero riportano il luogo `ULKOMAILLA` («all'estero» in finlandese) in ogni lingua.

## Locker Posti, ritiro e consegne mancate

**Avviso di arrivo.** Quando un pacco raggiunge un Parcel Locker o un punto di ritiro, Posti invia dettagli e codice di apertura su OmaPosti, via SMS ed e-mail. Ritiralo entro 5 giorni. Alcuni pacchi attendono più a lungo, come quelli dall'estero (di solito 14 giorni) o con avviso per lettera (9 giorni): fa fede l'ultimo giorno indicato sul tuo avviso.

**Più tempo.** In OmaPosti, «Extend pickup time» aggiunge fino a 14 giorni dalla scadenza iniziale, al costo di 1,90 € secondo la pagina inglese di Posti o 1,90 € al giorno secondo quella finlandese.

**Al locker.** Inserisci il codice di apertura (sui locker con tastiera: C, codice, poi OK). Chiunque abbia il codice può ritirare per te senza documento. Raccomandate e assicurazioni richiedono una delega. Gli invii senza codice attendono in un punto Posti: porta l'item ID e un documento d'identità.

**Perché è andato altrove.** Un pacco troppo grande per il locker, che richiede consegna personale o ha un numero di telefono errato va a un punto Posti.

**Consegna a domicilio.** Se Posti non riesce a consegnare a casa, porta il pacco al punto di ritiro più vicino e ti avvisa in OmaPosti, via SMS o e-mail. Uno *Small parcel* viene lasciato alla porta o in cassetta se non c'è nessuno. Su OmaPosti puoi passare alla consegna a domicilio solo finché il pacco è ancora presso il mittente.

**Modifiche.** Solo il mittente può cambiare l'indirizzo. Il servizio clienti può spostare il pacco a un altro punto di ritiro a pagamento. Un pacco non ritirato torna al mittente.

## Pacco Posti in ritardo, smarrito, danneggiato o in dogana

**Nessun aggiornamento.** Finché un pacco dall'estero non arriva in Finlandia, Posti non può stimare l'arrivo. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Smarrito o in ritardo.** Se il tracking non mostra traccia del pacco, contatta il servizio clienti Posti per una richiesta di chiarimento; possono farlo mittente o destinatario. Se non viene ritrovato, il risarcimento spetta al mittente, poiché Posti rimborsa chi ha pagato il trasporto, quasi sempre il venditore. Per la legge finlandese il negozio online è responsabile del trasporto e di solito invia una sostituzione. L'autorità finlandese dei consumatori (KKV) considera perso un pacco nazionale 14 giorni dopo il termine concordato, o 28 giorni dal ritiro se non era concordato alcun termine.

**Consegnato ma non c'è.** Controlla la cassetta per uno *Small parcel*, poi contatta il servizio clienti. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Segnalalo subito all'autista alla consegna, scatta foto, usa il modulo danni di Posti e avvisa il mittente.

**Dogana.** Un pacco da fuori UE può richiedere sdoganamento prima della consegna, sul sito delle Dogane finlandesi o in OmaPosti. Resta 20 giorni nel magazzino di Posti, poi torna indietro. Quando sono dovute tasse allo sdoganamento, Posti aggiunge una tariffa di gestione di 3,10 € (3,30 € in filiale), pagata sul sito ufficiale di Posti. Dal 1° luglio 2026 si applica inoltre un dazio fisso di 3 € per articolo fino a 150 €, e dal 1° novembre 2026 le Dogane finlandesi riscuotono anche 2 € per articolo. Per acquisti sotto i 150 € con IVA già addebitata dal negozio (IOSS), lo sdoganamento spetta al mittente e non paghi nulla all'arrivo; se mancano i dati di pagamento per dazio e tassa, Posti restituisce il pacco al mittente. Le tariffe aggiornate sono sulla [pagina dogana di Posti](https://www.posti.fi/en/receiving/customs-clearance).

> Posti non invia mai link per pagare dogana o spese di gestione. Un messaggio falso può persino contenere un codice di tracciamento autentico: cancellalo senza aprire il link.

## Servizio clienti e numero di telefono Posti

Canali pubblicati su posti.fi:

- **Telefono:** 0100 5577, oppure +358 100 5577 dall'estero. Tariffa di rete mobile o fissa locale. Gli orari sono sulla [pagina del servizio clienti Posti](https://www.posti.fi/en/customer-service).
- **Chat:** nell'app OmaPosti.
- **Moduli:** richiesta di risarcimento, segnalazione di danno e pacco non consegnabile (quando Posti non ha potuto consegnarlo né a te né di ritorno al mittente).

Posti non pubblica alcun indirizzo e-mail per i clienti.

## Tracciare i pacchi Posti con Peek

[Peek](/) riconosce subito come Posti un numero che inizia con `JJFI`. Un link di tracciamento posti.fi (`/tracking/`, `/seuranta/` o `/uppfoljning/` seguito dal numero, anche dopo una lingua come `/en/`, oltre a `/itemtracking/posti/search_by_shipment_id` con `ShipmentId=`) assegna Posti a qualsiasi codice. Non serve il CAP.

Per un codice postale terminante in `FI`, Peek chiede prima a Posti insieme a [Chronopost](carrier:chronopost) e mostra Posti appena lo riconosce. Se Posti non lo conosce ancora, compare «Non ancora trovato presso … Peek continuerà a controllare dopo l’aggiunta.». Aggiunto a un account Peek, viene controllato ogni ora per 6 ore e poi via via meno spesso; senza account i controlli cessano dopo 6 ore a meno di attivare «Avvisami quando viene trovato», che mantiene un controllo al giorno fino a 30 giorni dall'aggiunta. Per un SSCC come quello sopra, Peek interroga Posti con Matkahuolto, Swiss Post Cargo, PostNord e NZ Post. Con cifra di controllo errata o più corrieri concordi, scegli Posti con «Cambia» (Peek traccia oltre 4.500 corrieri). Un codice estero si segue su Posti via «Cambia» o da un link posti.fi; altrimenti Peek lo segue prima presso le poste d'origine e, quando queste indicano la Finlandia (o nessun paese se segui dalla Finlandia), interroga anche Posti passando su di essa appena arriva una scansione almeno altrettanto recente, mantenendo la cronologia precedente.

In Estonia, Lettonia e Lituania, Posti opera come SmartPosti: la ricerca corrieri di Peek trova Posti anche così («Noto anche come SmartPosti») e i link di tracking di smartposti.ee o itella.ee (e i rispettivi .lv e .lt) puntano a Posti per qualsiasi codice. Peek conserva la risposta di Posti solo quando una sola spedizione corrisponde esattamente al codice inserito; se un codice SmartPosti non dà esito, cercalo su smartposti.ee.

Peek legge Posti in inglese e mostra ogni stato con la spiegazione di Posti, la città di ogni scansione sulla mappa, peso, dimensioni e nome e indirizzo del punto di ritiro. Gli orari appaiono nel fuso del tuo dispositivo; su peektracker.com e nelle app per iPhone e Android la cronologia termina con «Orari nel tuo fuso orario:» e il suo nome. Nella scheda del pacco, «Chiedi aiuto a Posti» rimanda all'assistenza Posti. Scarta il codice di ritiro e il tuo indirizzo, e non mostra consegna prevista né mittente. Un numero riferito a più pacchi non viene tracciato e i pacchi vecchi risultano non trovati.

Finché tieni il pacco aperto sullo schermo, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 ora locale nel giorno in cui Posti scansiona il pacco in consegna finché il tracking di Posti risponde (ogni 15 minuti tramite servizi di tracciamento). Altrimenti, e dopo due giorni senza novità (12 ore finché mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando subentra un altro corriere, Peek segue il suo ritmo. Puoi impostare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento Posti

### Come traccio un pacco Posti in inglese?

Apri posti.fi/en/tracking e inserisci l'item ID: gli stati compaiono in inglese.

### Per quanto tempo Posti tiene un pacco in un locker?

Posti chiede di ritirarlo entro 5 giorni; l'avviso riporta l'ultimo giorno esatto. In OmaPosti puoi estendere fino a 14 giorni per 1,90 € (1,90 € al giorno secondo la pagina finlandese di Posti).

### Quanto impiega Posti a consegnare?

Uno *Small parcel* arriva in 2–5 giorni lavorativi da quando Posti lo riceve. Per gli altri invii, il tracking di Posti indica una stima di consegna.

### Il servizio clienti Posti ha una chat?

Sì, nell'app OmaPosti.

:::sources
- [Posti: Picking up an item](https://www.posti.fi/en/receiving/picking-up-an-item) – codice JJFI, avvisi, giacenza (14 giorni estero, 9 giorni lettera), estensione, locker, codici, delega, resi, telefono, chat
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – in finlandese: estensione 1,90 €/giorno
- [Posti: Track item](https://www.posti.fi/en/tracking) – diciture di stato, spiegazioni, ULKOMAILLA, lingue
- [Posti: Home delivery](https://www.posti.fi/en/receiving/home-delivery) – consegna mancata, Small parcel, tempi di consegna, consegnato non ricevuto
- [Posti: Customs clearance](https://www.posti.fi/en/receiving/customs-clearance) – tariffa gestione, Dogane finlandesi, tassa di 2 € dal 1° novembre 2026, sdoganamento IOSS del mittente, resi se mancano dati pagamento
- [Posti: Ordering from abroad and customs clearance](https://www.posti.fi/en/receiving/customs-clearance/ordering-from-abroard-and-custom-clearance) – 20 giorni in magazzino, OmaPosti, tariffa 3,10 €, dazio 3 € e tassa 2 €
- [Posti: What does Posti compensate for and to whom](https://www.posti.fi/en/customer-service/damage-situations/what-does-posti-compensate-for-and-to-whom) – richiesta chiarimento, risarcimento, procedura danni
- [Posti: Claim for compensation](https://www.posti.fi/en/customer-service/damage-situations/claim-for-compensation) – modulo risarcimento
- [Posti: Report a damaged item](https://www.posti.fi/en/customer-service/damage-situations/report-damage) – modulo danni, avvisare il mittente
- [Posti: Undeliverable parcel](https://www.posti.fi/en/customer-service/damage-situations/undeliverable-parcel) – modulo pacco non consegnabile
- [Posti: Security and phishing messages](https://www.posti.fi/en/customer-service/security-and-phishing-messages) – messaggi falsi, nessun link di pagamento
- [Posti: Privacy statement for Item Tracking](https://www.posti.fi/en/customer-service/terms-and-statements/data-protection/privacy-statements/privacy-statement-for-item-tracking) – tracking solo per codice esatto, costo chiamata
- [Posti: Changes to parcel storage periods](https://www.posti.fi/en/latest-news-at-posti/changes-to-parcel-storage-periods-to-improve-availability-at-posti-parcel-lockers-11-8-2025) – giacenza di 5 giorni
- [Posti: Customer Service](https://www.posti.fi/en/customer-service) – pagina servizio clienti
- [SmartPosti: Meist](https://www.smartposti.ee/ariklient/meist) – parte del gruppo Posti, SmartPosti in Lettonia e Lituania
- [SmartPosti: Saadetise jälgimine](https://www.smartposti.ee/saadetise-jalgimine) – pagina tracciamento SmartPosti
- [KKV: Postal and parcel services](https://www.kkv.fi/en/consumer-affairs/telephone-and-internet-subscriptions-postal-and-parcel-services/postal-and-parcel-services/) – quando un pacco risulta smarrito
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € e tassa UE di gestione di 2 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/posti) – formati, link SmartPosti/Posti e dati letti da Peek
:::
