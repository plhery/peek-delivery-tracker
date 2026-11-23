---
title: Tracciamento CouriersPlease: numeri, stati e aiuto
description: Traccia un pacco CouriersPlease in Australia: formato del numero di spedizione, stati, punti HUBBED, riconsegna, Delivery Choices e reclami.
slug: tracciamento-couriersplease
published: 2026-11-23
updated: 2026-11-23
---

CouriersPlease (Couriers Please Pty Limited, parte di FMH Group) è un corriere australiano che consegna pacchi nelle grandi città e nei centri regionali dell’Australia tramite affiliati locali (*Franchise Partners*). Per seguire una spedizione, inserisci il numero sulla [pagina di tracciamento CouriersPlease](https://www.couriersplease.com.au/tools-track) — che accetta fino a 10 codici separati da virgole — oppure incollalo nel riquadro qui sopra. Il codice arriva dal negozio o dal mittente che ha prenotato la spedizione.

## Numero di tracciamento CouriersPlease: com’è fatto

CouriersPlease lo chiama *consignment number* (numero di spedizione o di tracciamento). La pagina per prenotare una riconsegna indica che può iniziare con `CP` ed essere lungo fino a 20 caratteri. Per CouriersPlease, Peek accetta da 8 a 30 lettere e cifre (con almeno una cifra), coprendo spedizioni, etichette di colli, coupon prepagati e codici SSCC usati da alcuni mittenti. Non tutti i numeri CouriersPlease iniziano quindi con `CP`.

| Numero (inventato) | Che cos’è |
| --- | --- |
| `CPABC4829173640` | Tipico numero di spedizione CouriersPlease: `CP`, lettere e poi cifre. |
| `CPA48291736405` | Altro formato CouriersPlease con prefisso `CP`, leggermente più corto. |
| `CP482917367AU` | Non è CouriersPlease: due lettere, nove cifre e `AU` indicano un invio postale internazionale, come quelli di [Australia Post](carrier:australia-post). |

**Dove trovarlo.** Controlla prima l’e-mail di conferma d’ordine inviata dal negozio. Le e-mail ufficiali di CouriersPlease provengono da `noreply@couriersplease.com.au` (solo se il mittente ha trasmesso il tuo indirizzo) e gli SMS riportano come mittente `Courierspls`. Se hai trovato un avviso cartaceo di mancata consegna, contiene un *redelivery card number* accettato anch’esso sulla pagina di riconsegna. Altre indicazioni utili sono nella guida su [come trovare il numero di tracciamento](guide:find-tracking-number).

**Codice non valido o nessuna scansione?** Verifica eventuali errori di battitura e chiedi al negozio conferma che il pacco sia partito con CouriersPlease. Finché non compaiono eventi di scansione, il tracker ufficiale segnala il numero come non valido o assente.

## Significato degli stati CouriersPlease

Nella [guida per i destinatari](https://www.couriersplease.com.au/faqs), CouriersPlease elenca cinque tappe principali: notifica del pacco da ritirare, ritiro presso il mittente, arrivo al deposito, uscita in consegna e consegna o deposito in un punto di ritiro. Nel tracciamento i testi restano in inglese; ecco i principali stati gestiti da CouriersPlease e mappati da [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), nell’ordine più comune:

| Stato CouriersPlease | Che cosa significa per te |
| --- | --- |
| `Shipment information received` | Il mittente ha creato la spedizione. CouriersPlease potrebbe non avere ancora fisicamente il pacco. |
| `Your parcel has been picked up` | CouriersPlease ha ritirato il pacco dal mittente. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | Il ritiro presso il mittente non è riuscito; il venditore deve riprenotarlo. |
| `Arrived at facility for processing` | Arrivato in un centro di lavorazione CouriersPlease. |
| `Parcel requires further processing` | Il pacco richiede un’ulteriore lavorazione nel centro, ma resta regolarmente in rete. |
| `Processed at delivery centre` | Lavorato presso il centro di consegna. |
| `In transit to delivery centre` | In viaggio verso un centro di consegna (spesso con le due città tra parentesi). |
| `Arrived at delivery centre` | Arrivato al centro di consegna; non devi fare nulla. |
| `In transit to regional delivery centre` | Affidato a un partner regionale o in transito verso un centro regionale. |
| `Onboard for delivery` | A bordo del furgone dell’autista o del Franchise Partner per la consegna. |
| `Delivery rescheduled for next business day` | Rientrato in deposito non consegnato e riprogrammato per il giorno lavorativo successivo. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | Nessuno ha potuto ritirarlo e non c’era un posto sicuro; inviato a un punto di ritiro. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Era richiesta la firma ma nessuno era presente; reindirizzato a un punto di ritiro. |
| `Unable to deliver` | Impossibile consegnare; serve una verifica con l’assistenza. |
| `Processing for collection at a nearby collection point` | Arrivato al punto di ritiro, ma non ancora pronto per il ritiro. |
| `Pickup at Collection Point – Ready for collection` | Pronto per il ritiro al punto di raccolta con il tuo PIN. |
| `Delivered with Authority to Leave (ATL)` | Lasciato in un punto sicuro al tuo indirizzo senza firma. |
| `Delivered` | Consegnato all’indirizzo indicato. |
| `Parcel has been collected from` | Ritirato presso il punto indicato subito dopo la dicitura. |
| `Collection period expired – Return to sender in progress` | Periodo di giacenza scaduto; rientro al mittente in corso. |

`Shipment information received` è lo stato che crea più dubbi: i dati della spedizione esistono già, ma il pacco può essere ancora in attesa presso il negozio. Se la situazione non cambia, chiedi al venditore di confermare l’affidamento al corriere.

Avvisi come `Delivery rescheduled` o le righe `Delivery instructions –` (nuova data, punto di ritiro, vicino, autorizzazione al deposito o nuovo indirizzo) registrano soltanto un’istruzione o una nuova data senza spostare ancora il pacco.

## Consegna, punti HUBBED, riconsegna e Delivery Choices

**Tempi stimati.** Secondo la [guida per i destinatari](https://www.couriersplease.com.au/faqs), la consegna richiede in genere 1–3 giorni lavorativi tra aree metropolitane, qualche giorno in più da una costa all’altra (per esempio da est a ovest), tempi maggiori per aree regionali o remote e una settimana extra nei periodi di punta come Natale. Tutte le tempistiche sono stime e includono gestione dell’ordine, passaggio in deposito ed eventuale riconsegna o ritiro; meteo, traffico o imprevisti possono causare ritardi.

**Se non sei in casa (Authority to Leave).** In consegna l’autista bussa o suona al campanello, a meno che il mittente abbia chiesto la consegna senza contatto. In base alla clausola 5.3 delle [Condizioni di trasporto](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) e alla pagina [Delivery Choices](https://www.couriersplease.com.au/delivery-choices), i pacchi vengono gestiti di default con *Authority to Leave* (ATL): se qualcuno è presente, l’autista firma `SIG` aggiungendo il nome di chi riceve; in caso di assenza cerca un posto sicuro. Se non esiste uno spazio sicuro (per esempio appartamenti o ingressi molto esposti) o se è richiesta la firma, il pacco va a un punto di ritiro vicino — salvo pacchi oltre 10 kg e/o 100 cm cubici (circa le dimensioni di un bagaglio a mano), per i quali viene richiesto di fissare una riconsegna. Nelle aree regionali, CouriersPlease precisa che i pacchi vengono sempre lasciati in un luogo sicuro (ATL).

**Punti di ritiro HUBBED.** CouriersPlease collabora con HUBBED offrendo oltre 3.000 punti di ritiro in tutta l’Australia con orari estesi. Puoi cercarli per sobborgo o CAP sul [ricercatore di punti di ritiro](https://www.couriersplease.com.au/resources/find-a-collection-point) (filtrando tra *Drop Off*, *Redirections* e *Failed deliveries*). Se il mittente ha comunicato la tua e-mail, CouriersPlease ti avvisa entro 24 ore; altrimenti lascia un avviso cartaceo. Per la clausola 5.4 delle Condizioni di trasporto, la consegna avviene quando CouriersPlease invia il codice PIN: da quel momento hai **7 giorni** per ritirare il pacco, trascorsi i quali resta in giacenza al deposito più vicino per altri **5 giorni** prima di essere rispedito a spese del cliente. Se non hai ricevuto il PIN, puoi richiederlo sulla [pagina Get PIN di HUBBED](https://hubbed.com/getpin/) (per te, la tua azienda o un’altra persona).

**Riconsegna.** Puoi prenotarla sulla [pagina di riconsegna](https://www.couriersplease.com.au/book/redelivery) inserendo il numero di spedizione o della cartolina di avviso. La guida per i destinatari precisa inoltre che, se il pacco è stato portato a un punto di ritiro, puoi richiedere la riconsegna entro 6 giorni. Il centro assistenza di CouriersPlease dice invece che un pacco già dirottato a un punto di ritiro non può essere riconsegnato al tuo indirizzo e va ritirato lì; nel dubbio, chiedi all’assistenza prima di prenotarla.

**Delivery Choices.** Quando il mittente fornisce sia l’e-mail sia il cellulare al momento della prenotazione, ricevi un link sicuro con cinque opzioni su [Delivery Choices](https://www.couriersplease.com.au/delivery-choices):

- richiedere la firma (*Signature required*: in caso di assenza il pacco va a un punto di ritiro o al deposito);
- ritirare in un punto vicino con orari ampliati (*Collect nearby*);
- lasciare a un vicino entro tre abitazioni a destra o a sinistra (*Leave with a neighbour*);
- cambiare l’indirizzo di consegna (*Change the address*);
- scegliere un altro giorno entro tre giorni dalla data originaria (*Choose another day*).

## Pacco CouriersPlease in ritardo, smarrito o danneggiato

**Ritardo o tracciamento fermo.** CouriersPlease spiega che la maggior parte dei pacchi in ritardo arriva entro 24–48 ore lavorative rispetto alla stima iniziale. Controlla gli avvisi di servizio sulla home di couriersplease.com.au; se qualcosa non torna, apri il *Virtual Assistant* (icona chat in basso a destra), scegli *Assistance with a Delivery* e invia una sola richiesta, perché richieste multiple rallentano la gestione. Altri consigli in [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Segnato come consegnato ma non ricevuto.** Controlla posti riparati attorno all’ingresso, chiedi a conviventi, vicini o portineria e segnala poi il caso al negozio e al *Virtual Assistant*. Vedi anche [consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

**Danni, perdita e copertura Parcel Protect.** Per le spedizioni nazionali australiane, [Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) offre una copertura gratuita automatica fino a 1.000 AUD, escludendo però i primi 100 AUD di danno, i pacchi consegnati con *Authority to Leave* (ATL), l’imballaggio inadeguato e varie categorie escluse (beni di valore, contanti, gioielli, carte regalo, quadri con vetro, specchi, fiori, piante, deperibili, effetti personali ed elettronica, merci pericolose). Il reclamo scritto per perdita o danno va notificato entro **14 giorni lavorativi** dalla consegna o dalla data in cui la merce avrebbe dovuto essere consegnata (la prima delle due). Se il trasporto è stato prenotato tramite un intermediario terzo, il reclamo va aperto tramite quell’intermediario. Come destinatario contatta prima il negozio, titolare del conto. Fuori da Parcel Protect, la clausola 7.3 delle Condizioni di trasporto limita la responsabilità standard alla ripetizione del servizio, al suo costo o a 100 AUD (fatti salvi i diritti previsti dall’Australian Consumer Law). Approfondisci su [pacco danneggiato](guide:damaged-parcel).

## Contatti CouriersPlease e avvisi contro le truffe

- **Virtual Assistant:** clicca sull’icona della chat in basso a destra su [couriersplease.com.au](https://www.couriersplease.com.au/faqs) e seleziona *Assistance with a Delivery* (oppure digita `feedback`). Invia una sola segnalazione per spedizione.
- **Help Centre:** raggiungibile dalla voce *Help centre* del sito couriersplease.com.au.
- **Sede legale:** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> Come ricorda la pagina [protezione dalle truffe](https://www.couriersplease.com.au/scam-protection), le e-mail ufficiali di CouriersPlease arrivano da `noreply@couriersplease.com.au` e gli SMS dal mittente `Courierspls`. CouriersPlease non chiede mai di cambiare coordinate bancarie, pagare commissioni di sblocco, inserire carte o password né rispondere `Y` per attivare un link (diffida di domini falsi come `couriersplease.addliik.com` o `no-reply@wufoo.com`). Nel dubbio elimina il messaggio, verifica direttamente su couriersplease.com.au e segnala la truffa a CouriersPlease e a Scamwatch.

## Tracciare i pacchi CouriersPlease con Peek

[Peek](/) riconosce subito CouriersPlease per i numeri con formato `CP` (come `CPABC4829173640` o `CPA48291736405`) e per i link di couriersplease.com.au/tools-track contenenti `no=` o `trackingCode=`. Non servono account, CAP né numero di telefono. Se il codice CouriersPlease non inizia per `CP`, incolla il link di tracciamento oppure scegli CouriersPlease con «Cambia» quando lo aggiungi, o con «Cambia corriere» su un pacco già salvato.

Peek legge direttamente il tracker ufficiale di CouriersPlease e mostra ogni scansione nel testo inglese del corriere, con l’orario che dà CouriersPlease (ora di Sydney, anche fuori dal Nuovo Galles del Sud), lo Stato australiano quando presente e la data stimata (*Delivery ETA*) non appena il pacco supera la sola registrazione iniziale (fino a consegna o reso). Rimuove il codice HTML e i link di supporto accodati ai messaggi e non mostra indirizzo del punto di ritiro, codice PIN HUBBED o foto di consegna.

Peek interroga direttamente CouriersPlease fino a ogni 10 minuti mentre tieni aperto il pacco o nel giorno di consegna con avvisi attivi, e ogni 2 minuti dalle 6 ora di Sydney nel giorno in cui CouriersPlease registra l’uscita in consegna (`Onboard for delivery`), finché il suo tracker risponde; altrimenti ogni ora. Peek conta quel giorno sull’ora di Sydney, quella delle scansioni CouriersPlease: a Perth questi controlli iniziano quindi alle 3 o alle 4 e finiscono alle 21 o alle 22. Dopo 12 ore in «Etichetta creata» o «Preso in carico», o dopo due giorni senza nuovi eventi, torna a controllare ogni ora anche a schermata aperta, e ogni 6 ore dopo sette giorni ancora in «Etichetta creata». Quando usa servizi di tracciamento di supporto, controlla ogni ora, ogni 3 ore dopo un giorno senza aggiornamenti e fino a ogni 15 minuti il giorno della consegna.

Se un pacco in «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza nuove scansioni, Peek lo segnala con il giorno dell’ultima scansione (ad esempio «Nessun aggiornamento da lun 12 ott»), mostra «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a CouriersPlease.» e offre il link «Chiedi aiuto a CouriersPlease» al centro assistenza di CouriersPlease. Oltre tre settimane senza novità compare: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a CouriersPlease.»

Puoi impostare le notifiche su «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può prenotare una riconsegna, richiedere un PIN HUBBED o inviare un reclamo Parcel Protect: per queste operazioni rivolgiti a CouriersPlease o al negozio.

## Domande sul tracciamento CouriersPlease

### Quanto impiega CouriersPlease per consegnare?

Secondo la guida per i destinatari, occorrono in media 1–3 giorni lavorativi tra aree metropolitane, alcuni giorni extra da una costa all’altra, tempi più lunghi per le zone regionali o remote e una settimana in più nei picchi come Natale.

### Perché il mio tracciamento CouriersPlease non si aggiorna?

Nei trasferimenti tra Stati australiani, con partner regionali o durante la lavorazione in hub possono passare diversi giorni tra una scansione e l’altra. CouriersPlease indica che la maggior parte dei pacchi in ritardo arriva entro 24–48 ore lavorative dalla stima iniziale; oltre quel termine, usa il *Virtual Assistant* su couriersplease.com.au.

### Come funzionano i punti di ritiro HUBBED e il PIN?

Se non è possibile lasciare un pacco fino a 10 kg e 100 cm cubici in un luogo sicuro o serve la firma, CouriersPlease lo affida a uno degli oltre 3.000 punti HUBBED. Per le Condizioni di trasporto hai 7 giorni dall’invio del PIN per ritirarlo, seguiti da 5 giorni di giacenza in deposito prima del rientro. Se il PIN manca, puoi chiederlo su hubbed.com/getpin/.

### Cosa fare se il pacco è danneggiato o lasciato con Authority to Leave?

Contatta subito il negozio mittente. I reclami scritti con Parcel Protect vanno presentati entro 14 giorni lavorativi (fino a 1.000 AUD esclusi i primi 100 AUD), ma Parcel Protect non copre i pacchi consegnati con *Authority to Leave* (ATL).

:::sources
- [CouriersPlease: Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – tracciamento fino a 10 numeri separati da virgole
- [CouriersPlease: Guide to receiving](https://www.couriersplease.com.au/faqs) – cinque tappe di tracciamento, tempi stimati, limiti 10 kg / 100 cm, 6 giorni per la riconsegna, 24–48 ore lavorative nei ritardi, Virtual Assistant
- [CouriersPlease: Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – numero di spedizione o cartolina di riconsegna, prefisso CP, fino a 20 caratteri
- [CouriersPlease: How do I arrange a redelivery?](https://support.couriersplease.com.au/hc/en-au/articles/31312747333785-How-do-I-arrange-a-redelivery) – nessuna riconsegna per un pacco già dirottato a un punto di ritiro
- [CouriersPlease: Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – ricerca per sobborgo/CAP e filtri dei punti di ritiro
- [CouriersPlease: Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – ATL predefinita, cinque opzioni di consegna, regola regionale ATL
- [CouriersPlease: Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – massimale 1.000 AUD, esclusione primi 100 AUD e ATL, termine 14 giorni lavorativi, reclami tramite intermediari terzi
- [CouriersPlease: Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – clausole 5.3 (ATL / SIG), 5.4 (7 giorni dopo PIN + 5 giorni in deposito), 7.3 (limite di responsabilità) e sede legale
- [CouriersPlease: Scam protection](https://www.couriersplease.com.au/scam-protection) – mittenti ufficiali noreply@couriersplease.com.au e Courierspls, segnali di phishing, Scamwatch
- [CouriersPlease: Our Story](https://www.couriersplease.com.au/our-story) – FMH Group, Franchise Partners, oltre 3.000 punti HUBBED
- [HUBBED: Get PIN](https://hubbed.com/getpin/) – richiesta del codice PIN di ritiro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento di numeri e link CouriersPlease, mappa degli stati e campi letti da Peek
:::
