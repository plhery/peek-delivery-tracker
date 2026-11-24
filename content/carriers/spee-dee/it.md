---
title: Tracciamento Spee-Dee Delivery: codici SP, stati e aiuto
description: Traccia pacchi Spee-Dee Delivery Service nel Midwest USA: codici SP da 18 e 20 caratteri, codici riutilizzati, area servita e numero 800-862-5578.
slug: tracciamento-spee-dee
published: 2026-11-24
updated: 2026-11-24
---

Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) è un’azienda familiare di consegne di seconda generazione fondata nel 1978 da Don Weeres come servizio su chiamata. Si definisce leader indiscusso della consegna dell’ultimo miglio nel Midwest statunitense con una rete di consegna il giorno successivo, e offre inoltre trasporto LTL, magazzinaggio, punti di spedizione aperti al pubblico ed EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Puoi controllare una spedizione sulla pagina [Track a Shipment di Spee-Dee](https://speedeedelivery.com/track-a-shipment/) oppure incollare il codice a barre `SP` nel riquadro qui sopra.

## Formati dei codici di tracciamento Spee-Dee

Peek riconosce i codici a barre Spee-Dee come `SP` seguito da 16 o 18 cifre:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 cifre (18 caratteri totali) — codice a barre Spee-Dee standard; nota che Spee-Dee riutilizza nel tempo le sequenze da 16 cifre. |
| `SP482917360514829173` | `SP` + 18 cifre (20 caratteri totali) — formato lungo del codice a barre Spee-Dee. |

Entrambi i formati `SP` identificano Spee-Dee con elevata sicurezza e non entrano in conflitto con i grandi corrieri nazionali USA come [UPS](carrier:ups), [FedEx](carrier:fedex) o [USPS](carrier:usps). Se hai un link `speedeedelivery.com/...?tracking=...`, Peek estrae subito il codice `SP`. Per altri formati statunitensi, consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Area di servizio Spee-Dee nel Midwest

Secondo le [mappe di copertura Spee-Dee](https://speedeedelivery.com/services/service-area-maps/), Spee-Dee offre consegne regolari via terra in **tutto** il territorio di sei stati del Midwest e in città e CAP (*ZIP Code*) selezionati di quattro stati confinanti:

- **Copertura statale completa:** Illinois, Iowa, Minnesota, Nord Dakota, Sud Dakota e Wisconsin.
- **Città e ZIP Code selezionati:** Kansas (inclusi gli ZIP estesi `660` e `666`), Michigan, Missouri (inclusi gli ZIP estesi `634–635`, `644–646` e `650–653`) e Nebraska.

Per i tempi di transito, la pagina *Service Area Maps* mostra una mappa *Days in Transit* per ciascun CAP di partenza, e il file *Zip Transit File* indica i tempi per ogni ZIP Code servito.

## Significato degli stati di tracciamento Spee-Dee

La tabella di avanzamento di Spee-Dee mostra tre colonne — `Date / Time`, `Location` e `Activity` — dalla più recente alla più vecchia, con un riquadro riepilogativo in alto con `Status:`, `Delivered on:`, `Signed by:` e `Delivered to:` a consegna avvenuta.

| Attività Spee-Dee | Cosa significa |
| --- | --- |
| `ARRIVAL SCAN` | Scansionato in un terminal o centro di smistamento Spee-Dee lungo la rete via terra del Midwest. |
| `OUT FOR DELIVERY` *(spesso seguito da un numero di giro tra parentesi)* | Caricato sul furgone locale Spee-Dee per la consegna odierna a domicilio o in azienda. |
| `Delivered` | Consegna completata; il riepilogo sul sito Spee-Dee riporta anche ora di consegna, indirizzo e firma del destinatario quando raccolta. |
| `No packages were found matching the barcode supplied.` | Il codice a barre non è ancora stato scansionato nel sistema Spee-Dee oppure contiene un refuso. |

### Codici a barre da 16 cifre riutilizzati

Spee-Dee riutilizza vecchi codici a barre `SP` da 16 cifre per nuove spedizioni. Sulla pagina originale di Spee-Dee possono ancora comparire righe di un pacco consegnato in precedenza sotto le scansioni del tuo pacco attuale. Quando verifichi un codice riutilizzato sul sito Spee-Dee, osserva le date in cima alla tabella e fermati appena incontri un precedente `Delivered` di una vecchia spedizione.

## Ritardi, scansioni mancanti e reclami

Alcuni punti aiutano se il tracciamento sembra anomalo:

- **Nessuna scansione finora:** se la pagina di Spee-Dee mostra `No packages were found matching the barcode supplied.`, Spee-Dee non ha ancora scansionato il pacco oppure il codice è errato. Controlla il codice, chiedi al venditore se ha consegnato il pacco a Spee-Dee o leggi [tracciamento fermo](guide:tracking-not-updating).
- **Limiti sui ricaricamenti frequenti:** il server di Spee-Dee limita le ricerche al minuto e restituisce temporaneamente HTTP 503 se interrogato troppo spesso in rapida sequenza. Attendere un minuto o lasciare controllare Peek automaticamente evita il blocco.
- **Reclami per mancata consegna o danni:** il [modulo assistenza clienti Spee-Dee](https://speedeedelivery.com/customer-service/) prevede le voci dedicate **Claims – Non-Delivery** e **Claims – Damage** e chiede di includere l’**indirizzo di consegna completo** nelle richieste di tracciamento. Vedi anche [consegnato ma non ricevuto](guide:delivered-not-received) se il pacco risulta consegnato senza essere arrivato.

## Tracciare Spee-Dee con Peek

Incolla un qualsiasi codice a barre Spee-Dee da 18 caratteri (`SP` + 16 cifre) o 20 caratteri (`SP` + 18 cifre), oppure un link `speedeedelivery.com`, in Peek e Peek riconoscerà subito Spee-Dee. Se devi cambiare corriere, usa «Cambia» mentre aggiungi il pacco o «Cambia corriere» nella schermata di tracciamento.

Peek legge la pagina di avanzamento di Spee-Dee tramite l’[adattatore open source Spee-Dee di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), e passa attraverso una rete remota se l’host di Spee-Dee rifiuta la connessione diretta. Per un **codice a barre riutilizzato**, conserva solo le righe successive alla consegna precedente avvenuta almeno un giorno prima. Scarta i commenti HTML di debug e il numero di giro dopo `OUT FOR DELIVERY`, registrando città o terminal e orario di ogni scansione. Spee-Dee fornisce orari locali senza scarto da UTC: Peek data ogni scansione sul fuso del luogo (ora di Chicago per St. Cloud, Fargo, Milwaukee o Sioux Falls, ora di Detroit per Detroit), una scansione senza luogo su quello della scansione vicina, altrimenti sull’ora di New York, e mostra tutti gli orari con l’ora del tuo dispositivo. Il tracker Spee-Dee non fornisce data stimata di consegna né peso.

Peek mappa `ARRIVAL SCAN` su «In transito», `OUT FOR DELIVERY` su «In consegna» e `Delivered` su «Consegnato», e classifica eventuali altre attività secondo le proprie regole condivise.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Spee-Dee mostra `OUT FOR DELIVERY`, ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente, oppure ogni 5 minuti quando l’ultimo controllo ha dovuto aprire un browser, finché il tracker Spee-Dee risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando Spee-Dee non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Spee-Dee.», con il link «Chiedi aiuto a Spee-Dee» alla pagina di assistenza clienti. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Spee-Dee.»

Nelle preferenze di notifica di Peek scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna» per regolare gli avvisi.

## Contattare Spee-Dee Delivery

Spee-Dee sottolinea sulla sua [pagina Customer Service](https://speedeedelivery.com/customer-service/) che chi telefona parla direttamente con una persona anziché con un risponditore automatico:

- **Assistenza clienti numero verde:** `800-862-5578`
- **Telefono locale:** `320-251-6697`
- **Fax:** `320-251-1846` (fax LTL: `320-240-7995`)
- **Modulo online di contatto e reclami:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (includi il codice `SP` e l’indirizzo completo di consegna per domande sul tracciamento)
- **Indirizzo fisico:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Indirizzo postale:** P.O. Box 1417, St. Cloud, MN 56302
- **Azienda e tracciamento:** [Chi è Spee-Dee](https://speedeedelivery.com/about-us/) e [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Domande frequenti

### Perché la mia pagina Spee-Dee mostra una consegna di mesi fa?

Spee-Dee riutilizza nel tempo i codici a barre `SP` da 16 cifre, quindi la sua pagina grezza può mostrare una vecchia spedizione già consegnata sotto le scansioni del pacco attuale. Peek rileva automaticamente il confine e nasconde le righe delle consegne precedenti.

### Quali stati USA copre Spee-Dee Delivery?

Spee-Dee serve l’intero territorio di Illinois, Iowa, Minnesota, Nord Dakota, Sud Dakota e Wisconsin, più città e ZIP Code selezionati di Kansas, Michigan, Missouri e Nebraska.

### Spee-Dee Delivery mostra una data di consegna stimata sul tracker?

No. La pagina di tracciamento Spee-Dee elenca le scansioni di arrivo ai terminal, `OUT FOR DELIVERY` e i dettagli della consegna finale, senza data stimata. Puoi consultare i tempi abituali via terra per CAP su [Service Area Maps di Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### Cosa devo includere quando contatto Spee-Dee per un pacco?

Tieni pronti il codice completo `SP` da 18 o 20 caratteri e l’indirizzo completo di consegna — il modulo clienti Spee-Dee richiede esplicitamente l’indirizzo completo di consegna per le richieste di tracciamento.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
