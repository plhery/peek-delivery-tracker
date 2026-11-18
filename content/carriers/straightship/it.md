---
title: Tracciamento Straightship: codici STRTD, stati e consegna
description: Traccia un pacco Straightship in Canada: formato dei numeri STRTD, STRCA e STSH, significato degli stati, dogana CBSA, ordini SHEIN e servizio clienti.
slug: tracciamento-straightship
published: 2026-11-18
updated: 2026-11-18
---

Straightship (Straightship Solutions Limited) è un’azienda di logistica con sede a Burnaby, nella Columbia Britannica (**Canada**), che trasporta merci dall’Asia al Nord America e consegna pacchi in tutto il Canada con una propria flotta di autisti. Il suo sito annovera SHEIN tra i partner. Se un ordine online riporta un codice che inizia con `STRTD` o `STRCA`, la spedizione è affidata a Straightship; anche un codice che inizia con `STSH` può appartenere a Straightship. Puoi seguirlo su [straightship.com](https://www.straightship.com/new/), nel riquadro «Track Your Shipment», oppure direttamente nel campo qui sopra. Il negozio comunica il codice nell’e-mail di spedizione o nella pagina dell’ordine.

## Numero di tracciamento Straightship: come si presenta

Straightship non pubblica specifiche ufficiali sui propri codici. I formati riconosciuti da Peek appaiono così, con cifre di fantasia:

| Codice | Struttura |
| --- | --- |
| `STRTD482910573` | `STRTD` seguito da 9 cifre. |
| `STRTD4829105736491827` | `STRTD` seguito da 16 cifre (esistono anche da 17). |
| `STRCA4829105736491` | `STRCA` seguito da 13 cifre. |
| `STSHYW48291057364` | `STSH`, due lettere e 11 cifre. |

Lettere minuscole e spazi non fanno differenza in Peek. Quando il tracker di Straightship non riconosce un codice, risponde `No results found!`: controlla il numero nell’e-mail del negozio. Non trovi affatto il codice? Leggi [come trovare il numero di tracciamento](guide:find-tracking-number) e, per un ordine SHEIN, [tracciare gli ordini SHEIN](guide:shein-orders).

## Stati di tracciamento Straightship spiegati

Il tracker di Straightship è in inglese. Mostra in alto un riepilogo con la voce «Package Status» e sotto una cronologia con ora, stato e città di ogni scansione. Ecco le diciture che un pacco incontra di solito in **Canada**:

| Dicitura di Straightship | Cosa significa per te |
| --- | --- |
| `Package information is created in the system` | Il negozio ha generato l’etichetta. Straightship non ha ancora il pacco: è presso il venditore o in viaggio dall’Asia. |
| `Package Received with Origin Scan warehouse` | Prima scansione di Straightship in Canada, con città e provincia. |
| `Package is received at a Straightship station` | Arrivato in una delle filiali di Straightship (le pagine meno recenti riportano `Arrived at Facility`). |
| `Package arrived at transit station` | In una filiale di transito verso quella locale. |
| `Package is in transit to a Straightship station` | In viaggio tra due filiali, prima di quella incaricata del recapito. |
| `Loading` | Caricamento in filiale prima del giro di consegna; può comparire due volte a pochi secondi di distanza. |
| `Package will be delivered before 9:00pm` | In consegna oggi: il recapito o un tentativo segue in giornata. |
| `Attempted Delivery` | L’autista ha tentato la consegna senza riuscirci. |
| `Shipment delayed` | Ritardo segnalato senza causale sul tracker. |
| `Delivered` | Consegnato, con città e provincia (mostrato come «Consegnato» in Peek). |
| `Package is being returned to shipper` | In restituzione verso il mittente. |

Due righe di tentativo fallito spiegano cosa fare: `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` indica che l’indirizzo era chiuso; la stessa frase con `unable to leave package at safe location` al posto di `consignee closed` significa che il corriere non ha trovato un punto sicuro dove lasciare il pacco. Entrambe invitano a scrivere a `SS@straightship.com` per concordare una nuova consegna.

Tre aspetti generano spesso dubbi:

- **Il riepilogo resta indietro.** La voce «Package Status» può rimanere su `ReadyForShipping` (o `Ready For Shipping`) mentre il pacco viaggia già tra i depositi. Fai fede alla riga più recente della cronologia sottostante; i valori successivi del riepilogo sono `Shipped`, `Delivered` e `ReturnedToSender`.
- **«In progress» non significa in consegna.** `Delivery is in progress` e `Your order is in Progress` compaiono dopo una consegna non riuscita, talvolta giorni dopo e prima di un reso. Scrivi a Straightship se compaiono.
- **Gli orari sono in UTC.** Sotto la cronologia il tracker specifica che ogni orario è espresso in Tempo Coordinato Universale (UTC): una scansione serale in Canada porta già la data del giorno seguente.

Un pacco avviato al reso può comunque essere nuovamente scansionato in filiale e rimesso in consegna: `Package is being returned to shipper` non è sempre l’ultima riga.

## Consegna Straightship in Canada: chi porta il pacco

Il tracker di Straightship riguarda il **Canada** (l’intestazione riporta «Canada B2C Shipment Tracking»). Straightship dichiara che la propria rete copre oltre il 95 % della popolazione canadese e il 90 % dei codici postali del Canada con oltre 85 hub di distribuzione, promettendo la consegna entro le 21:00 (`before 9:00pm`) nel giorno di uscita. In **Canada** consegna anche per l’operatore logistico cinese [JS Express](carrier:js-express): il tracciamento di JS Express mostra le scansioni canadesi di Straightship e, quando la pratica JS Express contiene un numero `STRTD` o `STRCA`, Peek interroga direttamente anche Straightship.

Per i pacchi introdotti in **Canada** dagli Stati Uniti, la pagina inglese di Straightship elenca diverse opzioni per l’ultimo miglio — la propria rete, Canada Post, Purolator o [UPS](carrier:ups) — mentre la versione cinese indica la propria rete, Canada Post, [FedEx](carrier:fedex) o UPS. Per questo servizio Straightship dichiara consegne in 6–10 giorni e sdoganamento entro 24 ore. Il suo tracker mostra però soltanto i depositi Straightship senza nominare altri corrieri o secondi codici.

In **Canada**, Straightship non pubblica regole sul numero massimo di tentativi, sul rilascio di un avviso cartaceo o sui giorni di giacenza in deposito:

- **Dopo un tentativo fallito**, invia un’e-mail a `SS@straightship.com` con il numero di tracciamento, come richiesto dalla riga del tracker.
- **Per aggiornare indirizzo o telefono**, compila il modulo contatti scegliendo «Address and/or contact details update».
- **Prova di consegna:** la home page indica che il tracker mostra l’ultimo stato e, ove disponibile, la foto della consegna («POD image (Where available)»).

## Dogana canadese su un pacco Straightship dall’Asia

In **Canada**, Straightship cura lo sdoganamento per le aziende mittenti e la sua pagina dei servizi in cinese indica che può riscuotere le imposte in Canada (`DDU`). Se ti viene chiesto un pagamento per un pacco Straightship, verifica prima con il venditore: le FAQ di SHEIN Canada confermano che tutti gli ordini diretti in **Canada** viaggiano con dazi assolti (`DDP`, *delivered duty paid*), senza costi doganali a tuo carico. Altri servizi di tracciamento possono mostrare `Customs Released` prima della prima scansione in Canada (voce assente sul tracker di Straightship), oppure `Customs Held For Examination` durante un’ispezione doganale o `Customs Rejected` — che non è sempre definitivo: su una spedizione seguita da Peek la dogana ha esaminato il pacco e lo ha svincolato due giorni dopo. Peek classifica entrambe le voci come «In dogana»; se una resta ferma per giorni, contatta il venditore.

Gli importi dovuti in **Canada** dipendono dal valore e dalla provenienza secondo le soglie corriere della Canada Border Services Agency (CBSA): tramite corriere non è dovuto nulla fino a 20 CAD dalla Cina o da qualsiasi paese diverso da Stati Uniti e Messico, mentre oltre tale soglia si applicano dazi e imposte. Dagli Stati Uniti o dal Messico non si paga nulla fino a 40 CAD, si pagano solo le imposte tra 40,01 CAD e 150 CAD, e sia dazi sia imposte oltre 150 CAD (escluse merci soggette a regimi speciali come alcol e tabacco).

Per i pacchi spediti via corriere in **Canada**, la CBSA consente ai corrieri autorizzati di contabilizzare dazi e tasse per tuo conto: la CBSA precisa che il corriere ti contatterà prima della consegna per organizzare il pagamento trattenendo il pacco fino al saldo, e che le commissioni di sdoganamento del corriere sono distinte dai tributi doganali e fuori dal controllo della CBSA. La CBSA non contatta mai i cittadini per telefono, SMS o e-mail per chiedere il Social Insurance Number (`SIN`) o il numero della carta di credito: messaggi simili sono truffe. Maggiori dettagli in [pacchi fermi in dogana](guide:customs).

## Pacco Straightship in ritardo, smarrito o restituito

**Chi può intervenire.** I clienti di Straightship sono i venditori e le piattaforme che spediscono con essa, e Straightship non pubblica una procedura di reclamo o rimborso per i destinatari. Per un rimborso o una nuova spedizione rivolgiti al negozio (per SHEIN vedi [tracciare gli ordini SHEIN](guide:shein-orders)).

**Fermo prima del Canada.** `Package information is created in the system` senza righe successive significa che Straightship non ha ancora ricevuto il pacco in Canada; potrebbe essere ancora in viaggio dall’Asia. Chiedi al venditore quando è partito e consulta [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Nessun aggiornamento in Canada.** Se tutto tace per diversi giorni dopo una scansione in filiale o compare `Shipment delayed`, usa il modulo contatti di Straightship selezionando «Delivery Status Enquiry». Approfondisci in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato consegnato, ma assente.** Cerca prima la foto di consegna sul tracker di Straightship (non sempre presente), poi controlla vicini, locale posta del condominio e punti riparati vicino alla porta. Per il **Canada**, SHEIN aggiunge che in rari casi un pacco può risultare consegnato fino a 48 ore prima dell’arrivo effettivo; trascorso tale termine, invia il modulo Straightship con «Delivered Order Not Received» e, su un ordine SHEIN, premi il pulsante «Parcel Delivered, Not Received» nella sezione «My orders» per avviare la richiesta di rimborso. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Restituito al mittente.** `Package is being returned to shipper` indica che il pacco sta tornando al venditore: chiedi al negozio il rimborso o una nuova spedizione e consulta [restituito al mittente](guide:returned-to-sender).

## Numero di telefono e assistenza clienti Straightship

Sul proprio sito ufficiale Straightship indica:

- **Telefono in Canada:** `+1 (800) 452-3304`, dal lunedì al venerdì dalle 08:00 alle 21:00 e il sabato dalle 08:00 alle 16:00, ora della costa Est (`EST`); nella home page compare anche `+31 800 0200244` per i Paesi Bassi (`NL`).
- **E-mail:** `SS@straightship.com`, l’indirizzo riportato nelle righe di mancata consegna.
- **Modulo di contatto:** sulla [pagina contatti](https://www.straightship.com/new/contact.html), con numero di tracciamento obbligatorio. Straightship chiede di attendere 2 giorni lavorativi per la risposta.

Per l’ordine in sé, un reso o un rimborso contatta il venditore.

## Tracciare un pacco Straightship con Peek

[Peek](/) riconosce subito Straightship da un codice `STRTD` o `STRCA` nei formati sopra indicati, esclusivi nel suo catalogo. Da un codice `STSH` non può invece decidere in base alla sola forma, perché coincide con alcuni numeri di Colis Privé: Peek chiede quindi a Straightship se ha in carico il pacco e lo seleziona in caso affermativo. Se Straightship non viene proposto, tocca «Cambia» e sceglilo sotto «Compatibili con questo numero». Puoi incollare anche un link di tracciamento `straightship.com` (`/track-your-shipment/tracking-id/` seguito dal codice). Peek interroga Straightship solo per questi formati noti: se selezioni manualmente Straightship con «Cambia» per un codice di forma diversa, Peek avvisa «Questo corriere non emette numeri di tracciamento come questo.»; per altri formati usa direttamente il sito di Straightship.

Peek legge il tracker ufficiale di Straightship e mostra stato, cronologia con città e provincia di ogni scansione e ora di recapito. Legge gli orari come UTC, come dichiarato dal sito, e li converte nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …» su peektracker.com e nell’app iPhone). `Package will be delivered before 9:00pm` vale come «In consegna», le righe di tentativo come «Tentativo di consegna» e `Delivery is in progress` come «In transito». Peek non mostra una data di consegna stimata e tralascia il riferimento cliente e la foto di consegna, visibili sul tracker di Straightship. Poiché Straightship non indica altri corrieri, Peek segue il codice Straightship fino alla consegna.

Finché tieni aperto il pacco, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora del Pacifico nel giorno in cui Straightship scansiona il pacco in consegna, consegne serali incluse — mentre risponde il tracker di Straightship; tramite i soli servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno di consegna mentre il pacco è aperto o le notifiche sono attive. Negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Straightship né il venditore per te.

## Domande frequenti sul tracciamento Straightship

### Straightship è affidabile?

Sì. Straightship Solutions Limited è una società di logistica con sede a Burnaby, nella Columbia Britannica (Canada). Il suo sito elenca SHEIN, Canada Post, FedEx, COSCO e [YTO](carrier:yto) tra i partner, cita Walmart e Costco tra i rivenditori serviti e dichiara oltre 85 hub di consegna in Canada e più di 500.000 pacchi Amazon gestiti ogni giorno.

### Cosa significa «Package will be delivered before 9:00pm»?

Il pacco è in consegna oggi in Canada. L’autista effettua il recapito o registra un tentativo in giornata; se nessuno può ritirarlo, segue una riga che invita a scrivere a `SS@straightship.com`.

### Perché Straightship mostra ancora «ReadyForShipping»?

Il riepilogo «Package Status» può restare su `ReadyForShipping` anche mentre il pacco si sposta già tra le filiali Straightship. Fai sempre riferimento alla riga più in alto della cronologia dettagliata sottostante.

### Quanto impiega Straightship a consegnare?

Straightship non pubblica tempi di consegna per le spedizioni dall’Asia; per il servizio dagli Stati Uniti al Canada indica 6–10 giorni. Basati sulla stima fornita dal negozio per il tuo ordine; quando compare `Package will be delivered before 9:00pm`, il passaggio avviene quel giorno stesso.

### Chi consegna i pacchi Straightship in Canada?

Gli autisti stessi di Straightship partendo dai suoi depositi in Canada, per quanto mostra il suo tracker che non nomina altri corrieri. Per i pacchi dagli Stati Uniti al Canada, le pagine dei servizi indicano anche Canada Post e UPS, oltre a Purolator nella versione inglese e FedEx in quella cinese.

:::sources
- [Straightship: Home (in inglese)](https://www.straightship.com/new/) – spedizioni dall’Asia al Nord America, flotta propria, riquadro di ricerca, foto POD, partner, telefono, e-mail e orari EST
- [Straightship: About (in inglese)](https://www.straightship.com/new/about.html) – trasporto porta a porta dall’Asia al Nord America
- [Straightship: Services (in inglese)](https://www.straightship.com/new/services.html) – copertura di oltre il 95 % della popolazione e del 90 % dei codici postali canadesi, linea USA–Canada in 6–10 giorni, dogana entro 24 ore e opzioni Canada Post, Purolator e UPS
- [Straightship: 服务 (servizi, in cinese)](https://www.straightship.com/new/cn/services.html) – lo stesso servizio USA–Canada con rete propria, Canada Post, FedEx o UPS nell’ultimo miglio
- [Straightship: Resources (in inglese)](https://www.straightship.com/new/resources.html) – oltre 85 hub di consegna in Canada, più di 500.000 pacchi Amazon al giorno, Walmart e Costco
- [Straightship: Contact (in inglese)](https://www.straightship.com/new/contact.html) – causali di contatto, numero di tracciamento obbligatorio e risposta in 2 giorni lavorativi
- [Straightship: Privacy Policy (in inglese)](https://www.straightship.com/new/privacy.html) – ragione sociale Straightship Solutions Limited e sedi a Burnaby (Columbia Britannica)
- [CBSA: Importing casual goods by courier (in inglese)](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – contabilizzazione di dazi e imposte tramite corriere in Canada e commissioni di sdoganamento fuori dal controllo della CBSA
- [CBSA: Protect yourself against fraud (in inglese)](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – pagamento concordato prima della consegna dal corriere e nessuna richiesta di SIN o carte da parte della CBSA
- [CBSA: Low-value shipment thresholds (in inglese)](https://www.cbsa-asfc.gc.ca/services/cusma-aceum/lvs-efv-eng.html) – soglie per spedizioni via corriere in Canada (20 CAD da altri paesi, 40 CAD e 150 CAD da USA e Messico)
- [SHEIN Canada: Are there any additional fee for customs? (in inglese)](https://ca.shein.com/faq/detail?id=2307) – ordini SHEIN verso il Canada consegnati con dazi assolti (DDP)
- [SHEIN Canada: My order is marked as delivered, but I haven't received it (in inglese)](https://ca.shein.com/faq/detail?id=2362) – stato consegnato fino a 48 ore in anticipo e pulsante Parcel Delivered, Not Received
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati `STRTD`, `STRCA` e `STSH`, diciture delle scansioni, nota UTC, valori del riepilogo, `Customs Released` e controlli doganali inoltrati, passaggio da JS Express e campi mostrati in Peek
:::
