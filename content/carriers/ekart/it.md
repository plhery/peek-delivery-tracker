---
title: Tracciamento Ekart: codici ID, stati InscannedAtDH e RTO
description: Traccia un pacco Ekart in India: formato dell'ID a 14 caratteri, significato di InscannedAtDH e RTO, tentativi di consegna e tracciamento Peek.
slug: tracciamento-ekart
published: 2026-11-21
updated: 2026-11-21
---

Ekart (noto anche come *eKart Logistics* ed *Ekart Express*), gestito in India da *Instakart Services Private Limited* dal 2009 con sede a Bengaluru (Karnataka), è uno dei principali corrieri e-commerce indiani. Ritira gli ordini online presso il venditore, li smista attraverso i propri hub e consegna in oltre 15.000 codici PIN in India. Incolla l'ID di tracciamento ricevuto dal negozio nel riquadro qui sopra oppure inseriscilo nella [pagina Track Order di ekartlogistics.in](https://www.ekartlogistics.in/track-order).

## ID di tracciamento Ekart: com'è fatto

Il codice di Peek riconosce gli ID e-commerce di Ekart come tre lettere, seguite da `C`, `P` o `R` e dieci cifre: 14 caratteri complessivi (esempi inventati):

| ID di tracciamento (inventato) | Che cos'è |
| --- | --- |
| `FMPP4829105736` | Uno dei 17 gruppi di tre lettere associati a Ekart (elencati sotto), seguito da `C`, `P` o `R` e dieci cifre: Peek individua Ekart dalla sola forma. |
| `ABCP4829105736` | Altre tre lettere iniziali nello stesso schema: può appartenere a Ekart, ma Peek interroga prima il tracker di Ekart per confermarlo (vedi sotto). |

I 17 prefissi a tre lettere collegati direttamente a Ekart sono `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` e `TEL`. Spazi e minuscole non contano in Peek. Due eccezioni: un codice che inizia con `PB` in questo formato viene letto come del corriere britannico Parcelforce, mentre uno che inizia con `HRBC` (`HRBC4829105736`) rientra in uno schema [Cainiao](carrier:cainiao) / AliExpress anziché essere proposto per Ekart — se arriva da un negozio indiano per Ekart, tocca «Cambia» e scegli Ekart.

Usa sempre l'ID di tracciamento e non il numero d'ordine del negozio ([come trovare il numero di tracciamento](guide:find-tracking-number)); per spedizioni B2B/FTL/PTL con codice LR, `ekartlogistics.in/track-order` rimanda a un collegamento separato.

## Stati del tracciamento Ekart spiegati

Il tracker di Ekart riporta per ogni scansione data, ora e città, a volte in inglese corrente (`Shipment Created`), altre volte come codice seguito da trattino e nome dell'hub (`InscannedAtDH` più l'hub). Nell'ordine tipico:

| Ekart scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | Il venditore ha prenotato la spedizione; Ekart non ha ancora il pacco. | «Etichetta creata» |
| `OutForPickupEvent` | Un addetto Ekart è diretto dal venditore per il ritiro; il collo è ancora lì. | «Etichetta creata» |
| `NotPickedEvent` | Il ritiro presso il venditore non è riuscito; il collo resta dal venditore. | «Etichetta creata» |
| `Pickup From Seller`, `PickupReceived` | Ekart ha ritirato il pacco dal venditore. | «Preso in carico» |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + hub | In movimento nella rete Ekart tra un centro e l'altro. | «In transito» |
| `Expected` | L'hub successivo è stato avvisato dell'arrivo del pacco. | «In transito» |
| `Received`, `Received at` + hub | Arrivato in un centro di smistamento Ekart. | «In transito» |
| `InscannedAtFacility` | Scansionato in ingresso presso una struttura Ekart. | «In transito» |
| `InscannedAtDH`, `PrimaryDHInscan` | Arrivato alla filiale di recapito (*Delivery Hub*, DH) della tua zona: il passo successivo è l'uscita in consegna. | «In transito» |
| `Out For Delivery` | In consegna oggi. | «In consegna» |
| `Delivered` | Consegnato a te (oppure riconsegnato al venditore se segue una riga RTO, vedi sotto). | «Consegnato» |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Tentativo di consegna non riuscito (`undelivered_update` può comparire nello stesso istante accanto all'hub). | «Tentativo di consegna» |
| `undelivered_unattempted` | Il giro di consegna si è chiuso senza tentativo effettivo. | «Tentativo di consegna» |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Mancata consegna descritta per esteso; la data dopo `Rescheduled to:` indica il nuovo tentativo previsto (un `Unsuccessful` riferito al ritiro iniziale non conta così). | «Tentativo di consegna» |
| `PickupCancel` | Il ritiro dal venditore è stato annullato prima dell'ingresso nella rete Ekart. | «Problema segnalato» |
| `ShipmentRtoConfirmed` | Rientro al mittente (*Return to Origin*, RTO) confermato: il pacco torna al venditore. | «Problema segnalato» |
| `Marked RTO: ORDER_REJECT` | Dopo giri senza consegna, il collo è contrassegnato per il reso al venditore con la causale dopo i due punti. | «Problema segnalato» |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | In viaggio di ritorno verso il venditore. | «In transito» |

**Cosa significa RTO.** Peek legge `ShipmentRtoConfirmed`, qualsiasi riga `Marked RTO` o qualsiasi codice che inizi per `Rto` o `Return` come avvio del rientro al venditore: quella riga e tutte le successive riguardano il viaggio di ritorno. Da lì in avanti `InscannedAtDH` indica l'hub del venditore e un successivo `Delivered` significa che il pacco è tornato al negozio: Peek mostra quindi «Restituito al mittente» anziché «Consegnato» ([cosa significano gli stati di tracciamento](guide:tracking-statuses)).

## Come consegna Ekart in India

Ekart opera esclusivamente in India e Peek non passa i colli Ekart ad altri corrieri. Su `ekartlogistics.in` la pagina B2C Express dichiara oltre 15.000 codici PIN serviti in consegna (le FAQ riportano 5.000 codici PIN per il ritiro e 15.000 per la consegna, mentre *About Us* cita 14.000 codici PIN, oltre 80 centri di smistamento e 3.500 hub di ultimo miglio), con opzioni in giornata o il giorno successivo sulle brevi distanze e in 48–96 ore sulle tratte più lunghe in base ai codici PIN.

Secondo le FAQ ufficiali di Ekart, vengono effettuati **cinque tentativi** nel primo miglio (ritiro dal venditore) e **tre tentativi** nell'ultimo miglio (consegna al destinatario), ed è possibile riprogrammare la consegna su richiesta del cliente purché la spedizione non sia già stata marcata `RTO` ([mancata consegna](guide:missed-delivery)).

## Tracciamento Ekart bloccato, in RTO o non consegnato

**Fermo su `Shipment Created`, `Pickup Requested` o `NotPickedEvent`.** Il venditore non ha ancora affidato il collo a Ekart (o il ritiro è fallito); `PickupCancel` indica che il ritiro è stato annullato. Chiedi al negozio quando spedirà.

**Nessuna nuova scansione per giorni.** Se un collo registrato, ritirato o in transito non riceve scansioni per quattro giorni, Peek mostra «Nessun aggiornamento da quattro giorni». Contatta il venditore ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).

**Tentativo fallito o RTO.** Dopo `undelivered_attempted`, `undelivered_unattempted` o `Unsuccessful`, controlla l'eventuale data dopo `Rescheduled to:` e verifica indirizzo e telefono con il negozio. Con `ShipmentRtoConfirmed` o `Marked RTO: ORDER_REJECT` il pacco sta tornando al venditore: chiedi rimborso o nuova spedizione ([restituito al mittente](guide:returned-to-sender)).

**Risulta consegnato ma manca, smarrito o danneggiato.** Chiedi a familiari, vicini e portineria o vigilanza, poi avvisa il negozio o il marketplace che ha prenotato Ekart ([consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel)).

## Assistenza clienti e contatti Ekart

Nelle FAQ di `ekartlogistics.in` Ekart invita i venditori ad aprire un ticket sul portale spedizionieri (risposta entro 24 ore) e nella pagina *Corporate Information* riporta la sede legale di *Instakart Services Private Limited* a Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), senza pubblicare numero telefonico né email per i destinatari. Per assistenza, nuova spedizione o rimborso sull'ordine, rivolgiti direttamente al negozio o al marketplace.

**Attenzione agli SMS truffa.** I domini ufficiali di Ekart sono `www.ekartlogistics.in` (`ekartlogistics.in`) ed `ekartlogistics.com`. Prima di pagare somme richieste via SMS, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Ekart con Peek

Incolla l'ID in [Peek](/): con uno dei 17 prefissi sopra seguito da `C`, `P` o `R` e dieci cifre, Peek assegna subito Ekart («Corriere rilevato»). Con altre lettere iniziali (`ABCP4829105736`), Peek mostra «Verifica dei servizi di tracciamento…» e interroga il tracker di Ekart: appena trova una scansione dice «Ekart ha questo pacco.» (se non c'è ancora nulla, tocca «Cambia» e scegli Ekart sotto «Compatibili con questo numero»). Anche un link `ekartlogistics.com` con `shipmenttrack/` assegna subito Ekart; un link `shiprocket.co` viene invece seguito su Shiprocket. Se scegli Ekart a mano per qualcosa di diverso da quattro lettere e dieci cifre, Peek avvisa che «non emette numeri come questo».

Sotto Ekart, un collo senza scansioni mostra «In attesa di Ekart» (o «Nessuna cronologia di tracciamento per ora» senza corriere). Peek legge il tracker di Ekart senza account né telefono: ogni riga con codici e città, orario esatto convertito nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …») senza duplicati e data di consegna prevista finché il collo viaggia verso di te (rimossa dopo consegna, annullamento o RTO).

Mentre risponde il tracker di Ekart, Peek controlla fino a ogni 10 minuti a pacco aperto o nel giorno di consegna con notifiche attive, e fino a ogni 2 minuti dalle 6:00 (ora indiana) nel giorno in cui Ekart registra `Out For Delivery` (tramite i servizi di tracciamento, fino a ogni 15 minuti in quel giorno a pacco aperto o con notifiche attive); altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza novità, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può contattare Ekart o il negozio per te.

## Domande frequenti sul tracciamento Ekart

### Come traccio il mio pacco Ekart?

Incolla l'ID di tracciamento a 14 caratteri ricevuto dal venditore nel riquadro in alto su Peek oppure inseriscilo in *Ecommerce Parcel Tracking* su `ekartlogistics.in/track-order`.

### Come si presenta un ID di tracciamento Ekart?

Tre lettere, poi `C`, `P` o `R` e dieci cifre, ad esempio `FMPP4829105736` (inventato).

### Cosa significa `RTO` nel tracciamento Ekart?

*Return to Origin*: il pacco torna al venditore. `ShipmentRtoConfirmed` o `Marked RTO: ORDER_REJECT` avvia il reso e un successivo `Delivered` indica la riconsegna al venditore («Restituito al mittente» in Peek).

### Cosa significa `InscannedAtDH` su Ekart?

Il collo è stato registrato in una filiale di consegna (*Delivery Hub*): all'andata quella che serve la tua zona, dopo un RTO quella del venditore.

### Quanti tentativi di consegna fa Ekart?

Le FAQ ufficiali di Ekart prevedono tre tentativi nell'ultimo miglio verso il destinatario (e cinque tentativi per il ritiro dal venditore). Per qualsiasi problema con l'ordine contatta prima il negozio.

:::sources
- [Ekart Logistics: Home](https://www.ekartlogistics.in/) – rete logistica e-commerce in India e oltre 15.000 codici PIN serviti
- [Ekart Logistics: Track Order](https://www.ekartlogistics.in/track-order) – tracciamento colli e-commerce e collegamento separato B2B/LR
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – tre tentativi di consegna, cinque di ritiro, riprogrammazione prima dell'RTO, 5.000 / 15.000 codici PIN e ticket per i venditori
- [Ekart Logistics: About Us](https://www.ekartlogistics.in/about-us) – avvio nel 2009, 3.500 hub di consegna, oltre 80 centri di smistamento e 14.000 codici PIN
- [Ekart Logistics: B2C Express](https://www.ekartlogistics.in/b2c-express) – consegne in giornata / giorno successivo e in 48–96 ore in India
- [Ekart Logistics: Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited e sede a Bengaluru
- [Ekart Logistics: Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – gestione del dominio ekartlogistics.com da parte di Instakart Services Private Limited
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 prefissi, link shipmenttrack, 31 codici e stati, logica RTO, orari esatti e data di consegna prevista
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – link di tracciamento shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – eccezioni PB (Parcelforce) e HRBC (Cainiao / AliExpress) e nessun passaggio esterno
:::
