---
title: Tracciamento DTDC: formati AWB, stati RTO e assistenza
description: Traccia un pacco DTDC in India o nel mondo: codici AWB N, 7D e 7X, scansioni hub, resi RTO, tempi WorldWyde e contatti assistenza.
slug: tracciamento-dtdc
published: 2026-11-24
updated: 2026-11-24
---

DTDC (*DTDC Express Limited*), fondata il 26 luglio 1990 con sede a Bengaluru, è una delle principali reti di logistica espressa dell’India. Con oltre 16.500 partner che raggiungono il 96% della popolazione indiana, Express Parcel attivo su oltre 15.300 codici postali, presenza locale in 7 paesi e copertura internazionale verso oltre 220 destinazioni, DTDC gestisce spedizioni espresse nazionali, *Express Premium* il giorno successivo, *AirMax*, *Raftaar*, 3PL, trasporto LTL e spedizioni transfrontaliere *DTDC WorldWyde*. Puoi verificare una spedizione sulla [pagina di tracciamento DTDC](https://www.dtdc.com/track-your-shipment/) o su [MyDTDC](https://web.mydtdc.in/), oppure incollare il codice AWB qui sopra.

## Codici di tracciamento DTDC (formati AWB)

DTDC invia il numero Air Waybill (AWB) via e-mail e SMS alla conferma dell’ordine. Gli AWB nazionali e internazionali di DTDC seguono questi formati alfanumerici comuni:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `N48291736` | Numero spedizione standard DTDC da 9 caratteri (`N` + 8 cifre). |
| `7D482917364` | Numero spedizione DTDC da 11 caratteri (`7D` + 9 cifre). |
| `7X482917364` | Numero spedizione DTDC da 11 caratteri (`7X` + 9 cifre). |

DTDC emette anche codici da 9 e 11 caratteri che iniziano con altre singole lettere (come `B`, `D`, `H`, `P`, `T`, `U`, `V` o `X` seguite da 8 o 10 cifre), condivisi con altri corrieri regionali e che possono richiedere la selezione manuale di DTDC se compaiono più candidati. Su [dtdc.com/track-your-shipment/](https://www.dtdc.com/track-your-shipment/) puoi scegliere tra **Shipment Number** (AWB) e **Reference Number**. Per altri formati in India e all’estero consulta [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento DTDC

DTDC pubblica tappe e movimenti di hub (come `Arrived at DELHI`, `Dispatched from MATHURA` o `Received at ... Processing Centre`) sull’ora standard indiana (`Asia/Kolkata`, UTC+5:30).

| DTDC mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Booked`, `Booked at Origin Branch`, `Pickup Awaited`, `Pickup Scheduled`, `Pickup requested`, `Not picked` | Spedizione prenotata elettronicamente o allo sportello; in attesa di ritiro fisico o ingresso in hub. | «Etichetta creata» |
| `Picked Up`, `Pickup Completed` | DTDC ha ritirato il pacco dal mittente. | «Preso in carico» |
| `In Transit`, `On The Way`, `At Processing Centre`, `At Destination`, `Reattempt Initiated`, `Arrived at ...`, `Dispatched from ...` | In transito tra centri di smistamento DTDC, giunto al centro di destinazione o messo in coda per un nuovo tentativo. | «In transito» |
| `Out For Delivery` | Affidato al corriere locale per la consegna a domicilio oggi. | «In consegna» |
| `Undelivered` | Tentativo di consegna non riuscito (ad esempio destinatario assente o problema di indirizzo). | «Tentativo di consegna» |
| `RTO Booked`, `Return Approval awaited`, `Return Initiated` / `Return - Initiated` | Attivato il rientro al mittente (RTO). | «Problema segnalato» |
| `Delivered` (tratta di andata) | Consegnato al destinatario. | «Consegnato» |
| `Delivered` (su tratta `RTO`) | Restituito e riconsegnato al mittente originale. | «Restituito al mittente» |

**Attenzione ai resi `RTO`.** Quando un pacco non può essere consegnato, DTDC apre una tratta Return To Origin (`RTO`) che registra un proprio stato `Delivered` quando il pacco rientra dal mittente. Controlla se l’ultima tratta è di andata o `RTO` prima di supporre che sia stato consegnato a te.

## Servizi nazionali e internazionali DTDC

Secondo [DTDC Express Parcel](https://www.dtdc.com/express-parcel/), [DTDC International Shipping](https://www.dtdc.com/international-shipping/), [About Us](https://www.dtdc.com/about-us/) e le [FAQ DTDC](https://www.dtdc.com/faq/):

- **Servizi nazionali in India:** *Express Parcel* (consegna quotidiana a giorno definito su oltre 15.300 codici postali con contrassegno, autenticazione consegna e logistica inversa), *Express Premium* (urgente giorno successivo), *AirMax*, *Raftaar*, *Personal Courier* e B2B *3PL* / *LTL*.
- **Domeniche e festivi:** le [FAQ DTDC](https://www.dtdc.com/faq/) segnalano che alcune filiali locali DTDC operano anche di domenica e nei giorni festivi in base agli orari di sede.
- **Tempi internazionali (*DTDC WorldWyde*):** oltre 220 destinazioni con sdoganamento interno e KYC/CSB-V digitale — *WorldWyde Express Premium* (**4–5 giorni**), *WorldWyde Express Standard* (**7–8 giorni**), *WorldWyde Imports Standard* (**10–12 giorni**) e *WorldWyde B2C Standard* (**15–20 giorni**). Le tempistiche reali dipendono anche dalla dogana di destinazione; consulta [pacchi fermi in dogana](guide:customs).

Le pagine dei servizi DTDC non pubblicano un numero fisso di tentativi di consegna né giorni di giacenza. Un tentativo fallito appare come `Undelivered` e uno nuovo come `Reattempt Initiated`.

## Pacco DTDC in ritardo, non consegnato o smarrito

Negli acquisti online contatta prima il venditore affinché autorizzi correzioni d’indirizzo, riprogrammazioni o rimborsi, soprattutto se compaiono `Return Approval awaited` o `RTO Booked`.

- **Modifica indirizzo, nuova data o ritiro autonomo:** la pagina [Track Your Shipment](https://www.dtdc.com/track-your-shipment/) consente di aprire una contestazione, chattare col supporto o chiedere una richiamata, mentre il modulo [Customer Care](https://www.dtdc.com/customer-care/) raccoglie richieste di cambio indirizzo/telefono, nuova data di consegna o ritiro personale.
- **Risulta `Delivered` ma non ricevuto:** verifica prima se sia un rientro `RTO` al mittente; se si tratta della consegna di andata, chiedi a familiari, vicini e portineria e apri subito una segnalazione con DTDC Customer Care e il venditore.
- Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating), [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti ufficiali DTDC

Recapiti pubblicati su [DTDC Customer Care](https://www.dtdc.com/customer-care/) e [dtdc.com](https://www.dtdc.com/in/):

- **Telefono assistenza clienti:** `+91-9606 911 811`.
- **E-mail supporto:** `customersupport@dtdc.com`.
- **Assistenza online:** modulo richieste/reclami su [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/), assistente virtuale **DIVA** sul sito e app mobile **MyDTDC**.

> DTDC avverte chiaramente su [dtdc.com](https://www.dtdc.com/in/) che **`www.dtdc.com` è l’unico sito ufficiale DTDC** e declina responsabilità per offerte, numeri di telefono o pagamenti effettuati su siti imitazione. Non pagare mai somme né condividere codici OTP tramite link sconosciuti. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco DTDC con Peek

[Peek](/) riconosce automaticamente `N` + 8 cifre (`N48291736`, inventato) e `7D` / `7X` + 9 cifre (`7D482917364`, `7X482917364`) come DTDC con alta affidabilità, oltre ai link incollati `dtdc.com` con parametri `trackingId=` o `awb=`. Per codici alfanumerici da 9 o 11 caratteri con altre lettere iniziali, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e seleziona DTDC.

Peek interroga DTDC in due passaggi diretti: prima dal feed anonimo MyDTDC e, se necessario, dalla pagina di tracciamento `dtdc.com`. Mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), le descrizioni delle scansioni DTDC, la località di hub o filiale e gli orari indiani (`Asia/Kolkata`) convertiti nel fuso del tuo dispositivo. Aspetto fondamentale: quando DTDC segna `Delivered` su una tratta `RTO`, Peek lo mappa come «Restituito al mittente» anziché «Consegnato». Peek scarta indirizzi stradali, telefoni/contatti, metadati di prenotazione e valori di peso non etichettati; il tracker DTDC non fornisce data prevista né dettagli del punto di ritiro.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui DTDC mostra `Out For Delivery`, ogni 2 minuti dalle 6:00 ora indiana, finché il tracker DTDC risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di DTDC non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti il giorno della consegna mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DTDC.», con il link «Chiedi aiuto a DTDC» alla pagina Customer Care di DTDC. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a DTDC.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti e non può riprogrammare consegne DTDC, cambiare indirizzo né aprire contestazioni al posto tuo.

## Domande sul tracciamento DTDC

### Come si presenta un numero di tracciamento (AWB) DTDC?

I codici spedizione comuni di DTDC hanno 9 caratteri che iniziano per `N` (`N48291736`, inventato) o 11 caratteri che iniziano per `7D` o `7X` (`7D482917364`, `7X482917364`), anche se DTDC impiega pure altri prefissi di una lettera da 9 e 11 caratteri.

### Cosa significano `RTO Booked` o `Return Initiated` nel tracciamento DTDC?

`RTO` sta per Return To Origin (rientro al mittente). Indica che il pacco non è stato consegnato (o è stata richiesta approvazione al reso) e sta tornando al mittente. Contatta subito il venditore se desideri ancora l’ordine.

### Quanto impiega una spedizione internazionale DTDC?

Su [dtdc.com/international-shipping/](https://www.dtdc.com/international-shipping/), DTDC indica 4–5 giorni per *WorldWyde Express Premium*, 7–8 giorni per *Express Standard*, 10–12 giorni per *Imports Standard* e 15–20 giorni per *B2C Standard*, salvo sdoganamento.

### Come contatto l’assistenza clienti DTDC?

Chiama `+91-9606 911 811`, scrivi a `customersupport@dtdc.com` oppure usa il modulo richieste e l’assistente DIVA su [dtdc.com/customer-care/](https://www.dtdc.com/customer-care/).

:::sources
- [DTDC: Home](https://www.dtdc.com/in/) – avviso unico sito ufficiale (`www.dtdc.com`), supporto DIVA e panoramica servizi
- [DTDC: About Us](https://www.dtdc.com/about-us/) – fondata il 26 luglio 1990, sede Bengaluru, 16.500+ partner, 96% popolazione indiana, 7 paesi, 220+ destinazioni e 5,6M+ utenti MyDTDC
- [DTDC: Track Your Shipment](https://www.dtdc.com/track-your-shipment/) – ricerca AWB / riferimento, avviso AWB via SMS/e-mail, reclamo, chat e richiamata
- [DTDC: Express Parcel](https://www.dtdc.com/express-parcel/) – 15.300+ codici postali, contrassegno, autenticazione consegna e logistica inversa
- [DTDC: International Shipping (WorldWyde)](https://www.dtdc.com/international-shipping/) – 220+ destinazioni, tempi WorldWyde (4–5, 7–8, 10–12, 15–20 giorni) e sdoganamento CSB-V/KYC
- [DTDC: FAQ](https://www.dtdc.com/faq/) – apertura filiali domeniche/festivi e domande sul tracciamento
- [DTDC: Customer Care](https://www.dtdc.com/customer-care/) – `+91-9606 911 811`, `customersupport@dtdc.com`, modulo online per indirizzo, data e ritiro personale (URL di aiuto collegato da Peek)
- [Portale MyDTDC](https://web.mydtdc.in/) – portale web di tracciamento MyDTDC
- [Universal Parcel Scraper: dtdc](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dtdc) – rilevamento alta affidabilità `N`/`7D`/`7X`, regole link `dtdc.com`, doppia interrogazione, gestione tratte RTO, fuso `Asia/Kolkata`, campi scartati e URL di aiuto
:::
