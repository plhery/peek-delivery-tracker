---
title: Tracciamento SEKO Logistics: codici, OmniParcel e consegne
description: Traccia spedizioni SEKO Logistics e SEKO OmniParcel: formati dei codici, numeri GX, significato degli stati, numeri USA e UK e date stimate.
slug: tracciamento-seko-logistics
published: 2026-11-24
updated: 2026-11-24
---

SEKO Logistics è nata nel 1976 con un singolo ufficio a Chicago e oggi conta oltre 150 uffici in più di 60 paesi e 15 hub principali. I suoi servizi includono trasporto aereo, marittimo e stradale, e-commerce, *White Glove & Specialty Delivery* e *Home Delivery & Last Mile*. Le spedizioni si tracciano su [sekologistics.com/en/track](https://www.sekologistics.com/en/track/), mentre i pacchi e-commerce transfrontalieri usano il [tracker OmniParcel](https://track.omniparcel.com/) di SEKO.

Poiché il portale standard di SEKO e OmniParcel operano come due sistemi separati con formati di codice e diciture diverse, sapere quale formato hai ti aiuta a leggere correttamente la cronologia.

## Formati dei codici SEKO Logistics e OmniParcel

Le spedizioni SEKO Logistics non adottano un unico prefisso esclusivo, ma rientrano in due famiglie gestite da portali distinti:

- **Numeri di spedizione SEKO Logistics:** Peek accetta da 8 a 20 lettere e cifre con una sequenza di almeno 7 cifre. Tra le forme osservate figurano 3 lettere seguite da 7 cifre (come `WFY4829173`), 5 lettere seguite da 7 cifre oppure 9 cifre da sole (come `482917360`). Peek non riconosce mai SEKO da un codice nudo: per 3 lettere + 7 cifre interroga [Australia Post](carrier:australia-post), per 5 lettere + 7 cifre [Colis Privé](carrier:colis-prive) e per 9 cifre [DPD Ireland](carrier:dpd-ie) (fuori dall’Irlanda solo come ultima risorsa). Seleziona SEKO Logistics manualmente o incolla il link di tracciamento.
- **Numeri SEKO OmniParcel (`GX` + 17 o 18 cifre):** le spedizioni e-commerce transfrontaliere su `track.omniparcel.com` usano `GX` seguito da 17 o 18 cifre (come `GX482917360514829173`). La pagina principale di SEKO non riconosce i codici `GX`; risponde solo il tracker OmniParcel.

Se incolli un link `sekologistics.com/en/track/?trackingNumbers=...` o `track.omniparcel.com/...`, Peek estrae il numero e lo instrada automaticamente a SEKO Logistics. Per approfondire le differenze tra codici logistici e postali, consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento SEKO Logistics e OmniParcel

La pagina di tracciamento principale di SEKO formula spesso gli eventi come `Status Changed to [...]` (talvolta con un trattino finale al posto delle parentesi), mentre OmniParcel elenca hub internazionali, voli, dogana e corrieri dell’ultimo miglio.

| Dicitura SEKO / OmniParcel | Cosa significa |
| --- | --- |
| `Shipment Data Received` / `Shipment Created` / `Tracking number allocated & order ready` | I dati della spedizione sono giunti a SEKO o OmniParcel, prima del ritiro fisico o dell’ingresso in magazzino. |
| `Arrived at PU location` | L’autista addetto al ritiro è arrivato dal mittente; la merce non risulta ancora ritirata. |
| `Picked Up From Customer` / `At Pickup Terminal` / `Received at Origin Warehouse` | SEKO ha ritirato la spedizione o l’ha registrata al terminal o magazzino di origine. |
| `In Transit` / `In Transit to Delivery Terminal` / `At Transfer Terminal` / `Processed through Export Hub` / `Flight - Freight departed to destination` | In viaggio sulla linea nazionale o via cargo aereo internazionale verso il paese o la stazione di consegna. |
| `At Delivery Terminal` / `At Delivery Terminal Delivery Appt Set` / `On Hand Destination Station` / `Delivered To Warehouse` | Arrivato al terminal di destinazione SEKO o al magazzino locale prima della consegna finale; `Delivery Appt Set` indica che è stato fissato un appuntamento di consegna a domicilio. |
| `Received by Destination Customs` / `Customs held for inspection and clearance` | In lavorazione o ispezione presso la dogana di destinazione su una spedizione transfrontaliera OmniParcel. |
| `Customs cleared` / `Received by Last Mile Provider` / `Received by carrier` | Sdoganato e affidato all’hub locale di destinazione o al corriere dell’ultimo miglio. |
| `Shipment is Out for Delivery` / `Driver on site Delivery Location` / `The order is on the way to the recipient.` | In consegna oggi con il corriere, oppure autista arrivato sul posto. |
| `Awaiting collection` / `Customer to collect from carrier` / `Delivered to locker/collection point` | In attesa che il destinatario ritiri il pacco presso deposito, punto di ritiro o locker. |
| `Attempted delivery` / `Attempted Delivery - Refused by customer` | Un tentativo di consegna non è andato a buon fine. |
| `Temporary Origin Hold` / `Temporary Destination Hold` | Un fermo temporaneo mette in pausa la spedizione al terminal di origine o di destinazione. |
| `Shipment Delivered` / `Delivered - Authority to Leave / Safe Drop` | Consegnato all’indirizzo del destinatario (o lasciato con autorizzazione al deposito sicuro). |
| `Return to sender` | In rientro verso il mittente. |

Due frasi del portale principale SEKO meritano attenzione:

- **`Delivered To Warehouse`** indica che la merce ha raggiunto il magazzino di destinazione di SEKO, non casa tua — la consegna non è ancora avvenuta.
- **`POD Image Attached` e `POD IMAGED`** registrano l’archiviazione del documento di prova di consegna (a volte giorni dopo la consegna), mentre **`Return Data Received`** indica che un reso è stato registrato senza provare che la spedizione sia già ripartita. Nessuna delle due cambia la fase fisica di consegna.

## Consegna a domicilio, dogana transfrontaliera e ritardi

Il sito di SEKO elenca *White Glove & Specialty Delivery* e *Home Delivery & Last Mile*, oltre alla logistica sicura per elettronica e hardware, accanto agli ordini transfrontalieri OmniParcel.

- **Appuntamenti di consegna a domicilio:** `At Delivery Terminal Delivery Appt Set` e `On Hand DEST Delv Appt Set` segnalano che è stato fissato un appuntamento di consegna mentre la spedizione attende al terminal locale. Durante il transito la pagina SEKO indica spesso una data `Expected Arrival` o un intervallo `Estimated delivery between`.
- **Passaggi transfrontalieri OmniParcel:** sugli ordini e-commerce internazionali (`GX...`), le scansioni OmniParcel vanno dal magazzino di origine all’export, al volo e alla dogana di destinazione fino al corriere locale dell’ultimo miglio (`Received by Last Mile Provider`). Le scansioni possono fermarsi durante voli e dogana; vedi la [guida alla dogana](guide:customs) e il [tracciamento fermo](guide:tracking-not-updating) se gli aggiornamenti si interrompono.
- **Orari e duplicati:** la pagina SEKO indica gli orari al minuto senza fuso orario e talvolta riporta lo stesso stato due volte nello stesso minuto con maiuscole diverse; Peek ne conserva uno solo e data gli eventi SEKO (che non indicano un luogo) sull’ora di Chicago. OmniParcel mostra gli orari in UTC+10:00 se non cambi il menu a tendina del fuso orario; Peek interroga OmniParcel in UTC così ogni scansione conserva il proprio istante esatto. Peek mostra ogni scansione sull’orologio del tuo dispositivo.

Se il tuo ordine internazionale usa un altro grande corriere per un altro tratto, confronta il tracciamento con [DHL Express](carrier:dhl-express) o [FedEx](carrier:fedex).

## Tracciare SEKO Logistics con Peek

Poiché i codici SEKO si sovrappongono ad altri corrieri e non hanno un prefisso universale, incolla quando possibile il link completo `sekologistics.com/en/track/?trackingNumbers=...` o `track.omniparcel.com/...`. Se incolli un numero nudo — `WFY4829173`, `482917360` o un codice OmniParcel `GX...` — seleziona SEKO Logistics manualmente tramite «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella schermata del pacco.

Peek interroga direttamente entrambi i portali tramite l’[adattatore open source SEKO di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko): i codici `GX` + 17–18 cifre vengono inviati automaticamente a OmniParcel (interrogato in UTC, leggendo luoghi, nome del servizio, peso in chilogrammi e paese di destinazione), mentre i numeri di spedizione SEKO vanno alla pagina SEKO (data di arrivo prevista o finestra di consegna, nome del servizio, paese di destinazione ed eventi deduplicati, escludendo le righe amministrative `POD Image Attached` / `POD IMAGED` e scartando nomi dei firmatari e CAP).

Peek traduce gli eventi SEKO e OmniParcel in fasi standard: «Etichetta creata» per dati ricevuti, creazione e arrivo dell’autista al ritiro; «Preso in carico» per ritiro e ingresso nel magazzino di origine; «In transito» per hub, volo, trasferimento, terminal di consegna, arrivo in magazzino, sdoganamento completato e passaggio all’ultimo miglio; «In dogana» per ispezioni doganali a destinazione; «In consegna» per uscita in consegna e autista sul posto; «Pronto per il ritiro» per giacenza in punto di ritiro o locker; «Tentativo di consegna» per tentativi falliti; «Problema segnalato» per fermi all’origine o a destinazione; «Consegnato» per consegna e deposito autorizzato; e «Restituito al mittente» per i resi.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui la pagina SEKO mostra `Shipment is Out for Delivery`, `Out for Delivery` o `Driver on site Delivery Location`, oppure OmniParcel mostra `Out for delivery` o `The order is on the way to the recipient.`, ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracker SEKO risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando SEKO non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a SEKO Logistics.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a SEKO Logistics.»

Nelle impostazioni di avviso di Peek, scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna» per personalizzare le notifiche.

## Contattare SEKO Logistics

SEKO pubblica le linee regionali del servizio clienti direttamente sulla [pagina di tracciamento](https://www.sekologistics.com/en/track/) e sulla pagina [Contact Us](https://www.sekologistics.com/en/contact-us/):

- **Linea US Home Delivery:** (866) 268-5263
- **Servizio clienti Regno Unito:** +44 1784 417120
- **Tracciamento ufficiale online:** [SEKO Track My Shipment](https://www.sekologistics.com/en/track/) (accetta fino a 30 codici alla volta) e [OmniParcel Tracker](https://track.omniparcel.com/)
- **Richiesta online sulla spedizione:** [Contatta SEKO Logistics](https://www.sekologistics.com/en/contact-us/) (seleziona il modulo «Track My Shipment»)
- **Panoramica aziendale:** [Chi è SEKO Logistics](https://www.sekologistics.com/en/about/) e [sekologistics.com](https://www.sekologistics.com/en/)

Tieni a portata di mano il codice di tracciamento SEKO quando chiami o compili il modulo Track My Shipment.

## Domande frequenti

### Perché il mio codice `GX` non funziona su `sekologistics.com/en/track/`?

I codici che iniziano con `GX` seguiti da 17 o 18 cifre appartengono alla piattaforma OmniParcel di SEKO e rispondono solo su [track.omniparcel.com](https://track.omniparcel.com/). Su Peek, selezionando SEKO Logistics i codici `GX` vengono inviati automaticamente a OmniParcel e i codici SEKO standard al tracker principale di SEKO.

### «Delivered To Warehouse» significa che il mio ordine è stato consegnato all’indirizzo sbagliato?

No. Nel tracciamento SEKO Logistics, `Delivered To Warehouse` significa che la spedizione è arrivata al magazzino di destinazione SEKO prima della consegna finale. Attendi `Out for delivery` e `Shipment Delivered` per la conferma della consegna a domicilio.

### Perché Peek mi ha chiesto di scegliere un corriere per il mio codice SEKO?

I codici SEKO (come 3 lettere + 7 cifre oppure 9 cifre) combaciano anche con altri corrieri, e Peek non riconosce mai SEKO da un codice nudo, codici `GX` inclusi. Selezionare SEKO Logistics tramite «Cambia» o incollare il link ufficiale SEKO o OmniParcel indica a Peek esattamente dove cercare.

### Come prenoto un appuntamento di consegna a domicilio SEKO Logistics?

Chiama la US Home Delivery Hotline al `(866) 268-5263` o il servizio clienti UK al `+44 1784 417120`, oppure usa il modulo Track My Shipment su [sekologistics.com/en/contact-us](https://www.sekologistics.com/en/contact-us/).

:::sources
- [SEKO Logistics](https://www.sekologistics.com/en/)
- [SEKO Logistics — Track My Shipment](https://www.sekologistics.com/en/track/)
- [SEKO Logistics — About](https://www.sekologistics.com/en/about/)
- [SEKO Logistics — Contact Us](https://www.sekologistics.com/en/contact-us/)
- [SEKO OmniParcel Tracker](https://track.omniparcel.com/)
- [universal-parcel-scraper SEKO adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seko)
:::
