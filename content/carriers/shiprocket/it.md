---
title: Tracciamento Shiprocket: codici AWB, stati e RTO
description: Traccia un pacco Shiprocket o ShiprocketX: formato AWB, significato degli stati, consegne mancate (NDR), RTO e contatti.
slug: tracciamento-shiprocket
published: 2026-11-22
updated: 2026-11-22
---

Shiprocket, con sede a Gurugram, è una piattaforma indiana di spedizioni e non un corriere: i negozi online prenotano tramite essa corrieri come Delhivery, Blue Dart o Xpressbees, e Shiprocket fornisce all’acquirente una pagina di tracciamento. ShiprocketX è il servizio per spedizioni dall’India all’estero. Traccia il pacco sulla [pagina di tracciamento Shiprocket](https://www.shiprocket.in/shipment-tracking/) con AWB, ID ordine o numero di cellulare, sul link `shiprocket.co` inviato dal venditore oppure nel riquadro sopra. Shiprocket indica che l’AWB viene inviato via e-mail e SMS alla conferma dell’ordine.

## Numero di tracciamento Shiprocket

Shiprocket chiama AWB (*air waybill number*) il numero di tracciamento. In India coincide con il codice del corriere. Un pacco ShiprocketX viaggia con l’AWB di Shiprocket: 13 cifre che iniziano con `832`. Shiprocket non pubblica un formato ufficiale. Con cifre inventate:

| Numero (inventato) | Che cos’è |
| --- | --- |
| `8324829103756` | `832` e altre 10 cifre: un AWB ShiprocketX. |
| `7394028156342` | 13 o 14 cifre: AWB di un corriere indiano come Delhivery o Xpressbees. |
| `74829103753` | 11 cifre: il formato di un AWB Blue Dart. |
| `FMPC4829103756` | Tre lettere, C, P o R, poi 10 cifre: un AWB Ekart. |

**Dove trovarlo.** Controlla l’e-mail e l’SMS ricevuti alla conferma dell’ordine. Il tracciamento Shiprocket accetta anche ID ordine o numero di cellulare, verifica un codice monouso (OTP) e apre myShiprocket per seguire gli acquisti. Altri suggerimenti in [come trovare un numero di tracciamento](guide:find-tracking-number).

**Numeri simili.** Poiché anche le lettere di vettura Delhivery possono avere 13 cifre, un codice che inizia con `832` è probabilmente, ma non con certezza, di Shiprocket. Il portale Shiprocket risponde per qualunque corriere prenotato tramite la piattaforma, quindi il link `shiprocket.co/tracking/` funziona a prescindere dalla forma del codice.

## Stati di tracciamento Shiprocket spiegati

La pagina mostra stato, corriere o servizio accanto all’AWB e poi le scansioni dalla più recente, ciascuna con attività, spesso località, giorno, mese e ora, senza l’anno. Quasi tutte le righe riprendono il testo del corriere: quello locale in India o il vettore finale all’estero. Righe comuni in ordine:

| La pagina Shiprocket indica | Cosa significa per te |
| --- | --- |
| `Order Received` | Il venditore ha creato l’ordine. Nessun corriere ha ancora il pacco. |
| `Manifested - Out for Pickup` | Il corriere sta andando a ritirare il pacco dal venditore. |
| `In Transit - Shipment picked up` | Il corriere lo ha ritirato. |
| `In Transit - Shipment Recieved at Origin Center` | Al primo hub del corriere (scritto così sulla pagina). |
| `Dispatched - Delivering At Shiprocket Warehouse` | ShiprocketX: il corriere porta il pacco all’hub di esportazione di Shiprocket, non da te. |
| `Received At DC` | Arrivato all’hub di esportazione di Shiprocket. |
| `AWB Handed over to Flight` | Consegnato alla compagnia aerea. |
| `CUSTOM CLEARED OVERSEAS` | Sdoganato. |
| `Received by carrier` | Lo ha preso in carico il corriere del tuo paese. |
| `RTO DELIVERED` | Restituito al venditore. |

Anche `Out For Pickup` e `PICKUP EMPLOYEE IS OUT TO P/U SHIPMENT` indicano che il pacco non è ancora stato ritirato. `Reached At Warehouse` e `Connection Aligned` sono passaggi nell’hub di esportazione a Delhi. `Customer has made payment or Provided ID/Tax information` è uno step doganale all’estero (dazi pagati o dati fiscali forniti; vedi [pacchi fermi in dogana](guide:customs)). Ogni riga che inizia con `RTO` (*return to origin*) indica il rientro al venditore.

**Pacco più vecchio.** Dopo un certo periodo la pagina mostra solo una vista archiviata: ultimo stato (`DELIVERED`, `FULFILLED` per ordine consegnato e chiuso, o `RTO DELIVERED`), giorno, servizio e AWB con gli ultimi caratteri nascosti. La cronologia completa è visibile solo all’acquirente dopo un codice via telefono. Dopo circa un anno la pagina risponde `AWB Not Found`.

## Chi consegna i pacchi Shiprocket

**In India**, il corriere scelto dal venditore. Shiprocket dichiara di lavorare con 42 corrieri partner, tra cui Delhivery, Blue Dart, Xpressbees, Ekart, DTDC, Shadowfax, India Post e Amazon Shipping, precisando che i tempi dipendono da corriere, codice PIN e servizio. La pagina indica il servizio, come `Delhivery Surface`.

**All’estero**, un corriere nazionale porta il pacco ShiprocketX all’hub di esportazione di Delhi; da lì vola fuori dall’India, passa la dogana e viene affidato a un corriere nel paese di destinazione, sempre con l’AWB Shiprocket. Negli storici osservati su Shiprocket, negli USA subentrava USPS. La pagina riporta il servizio, ad esempio `SRX Economy` o `SRX Premium`. Ai venditori Shiprocket indica 12–15 giorni lavorativi per il servizio economy verso USA, Regno Unito, Canada e altri paesi e 10–12 giorni per i servizi con dazi prepagati verso gli USA: stime commerciali, non una data garantita per il tuo ordine. Nei menu ShiprocketX compare anche come «ShipX».

## Consegna mancata (NDR) e reso al mittente (RTO)

Un NDR (*non-delivery report*) segnala che il corriere non ha potuto consegnare. Il venditore lo vede su Shiprocket pochi minuti dopo e decide se riprovare o far rientrare il pacco.

- **Messaggi.** Se il venditore usa il flusso NDR per acquirenti, un ordine segnato «customer requested future delivery» o «customer refused the delivery» ti invia un SMS — e su alcuni piani una chiamata automatica — per confermare la consegna. Il venditore può anche farti inviare un link SMS per chiedere un nuovo tentativo e correggere indirizzo o telefono.
- **Pagina di tracciamento.** Shiprocket indica che gli acquirenti possono richiedere lì un nuovo tentativo e leggere il motivo di ogni passaggio.
- **Tentativo mai avvenuto.** Avvisa il venditore, che con la tua conferma può segnalare un «fake attempt» a Shiprocket.
- **Tre tentativi.** I corrieri effettuano fino a tre tentativi. Poi l’ordine passa in RTO e torna all’indirizzo di ritiro o magazzino del venditore.

Una volta in rientro, chiedi al venditore una nuova spedizione o il rimborso. Vedi [consegna mancata](guide:missed-delivery) e [restituito al mittente](guide:returned-to-sender).

## Pacco Shiprocket fermo o non arrivato

**Nessun aggiornamento.** Shiprocket precisa che i corrieri aggiornano il tracciamento dopo la scansione, cosa che può richiedere qualche ora; oltre un giorno senza novità contatta il venditore. Finché compare solo `Order Received` o una riga di ritiro, il corriere non ha ancora preso il pacco. Altro in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Chi interviene.** Il cliente di Shiprocket è il venditore. Alla domanda «Whom shall I contact if I have concerns?», Shiprocket risponde di rivolgersi al venditore da cui hai acquistato. Anche per smarrimento, danno o mancata consegna rivolgiti al venditore: la copertura ShiprocketX fino a 5.000 INR è riservata ai venditori. Vedi [pacco smarrito](guide:lost-parcel) e [consegnato ma non ricevuto](guide:delivered-not-received).

## Numero assistenza clienti e contatti Shiprocket

Recapiti pubblicati da Shiprocket:

- **Telefono:** 9643887070, 9711451060 e 8510042020 (numeri indiani, +91 dall’estero). Il sito di tracciamento li elenca per problemi di tracciamento insieme alla live chat come assistenza 24/7; la pagina contatti indica gli stessi numeri dalle 9:00 alle 21:00 (ora indiana).
- **E-mail:** support@shiprocket.in.
- **Escalation:** il supporto risponde entro 5 giorni lavorativi. In caso contrario scrivi a escalation@shiprocket.com, poi a grievance@shiprocket.com; ciascuno risponde entro 3 giorni lavorativi.

Gran parte di questo supporto è pensata per i venditori. Il sito di tracciamento avvisa inoltre: se un messaggio da una fonte non verificata chiede un pagamento, non rispondere e non condividere dati di pagamento; blocca e segnala il mittente. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare pacchi Shiprocket con Peek

Per un codice di 13 cifre che inizia con `832`, [Peek](/) interroga fin dall’inizio Shiprocket (insieme a Delhivery se il tuo paese è l’India) e, solo se Shiprocket non conosce il codice, servizi di tracciamento e altri corrieri: la schermata di aggiunta mostra «Verifica dei servizi di tracciamento…» e poi «Shiprocket ha questo pacco.» Poiché anche Delhivery usa 13 cifre, Peek non assegna Shiprocket dalla sola forma; senza risposta, mostra «In attesa della prima scansione di Shiprocket» e continua a interrogarlo, e puoi selezionare Shiprocket con «Cambia» (o «Cambia corriere»). Un link `shiprocket.co/tracking/` identifica Shiprocket qualunque sia la forma del codice.

Peek legge la pagina di tracciamento Shiprocket e mostra lo stato, ogni scansione con località se presente, la data stimata di Shiprocket durante il viaggio e l’orario di consegna per spedizioni consegnate in India. Le scansioni all’estero non portano fuso orario: Peek legge ciascuna sull’ora locale del luogo (una riga senza luogo sull’ora di una riga vicina, a volte l’ora indiana) e la mostra nel tuo fuso orario («Orari nel tuo fuso orario:»). Le righe mantengono il testo della pagina, tranne corrispondenze esatte come `Delivered`. Peek scarta venditore, riferimento ordine e indirizzo destinatario, non riceve peso né prova di consegna e non passa al corriere estero.

Per un pacco datato mostra l’ultimo stato archiviato con il giorno e cerca una cronologia più ampia nei servizi di tracciamento. Peek controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 secondo l’orologio dell’ultima scansione (ora indiana in India) il giorno in cui entra «In consegna», mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive, quando la pagina Shiprocket risponde. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un invio in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …» e ricorda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Shiprocket.» Peek segue oltre 4.500 corrieri — tra cui [Delhivery](carrier:delhivery), [Blue Dart](carrier:blue-dart), Xpressbees, [Ekart](carrier:ekart), DTDC, Shadowfax e [India Post](carrier:india-post) con i rispettivi codici —, ma non può verificarti come acquirente, richiedere un nuovo tentativo né contattare Shiprocket o il venditore per te.

## Domande sul tracciamento Shiprocket

### Shiprocket è un corriere?

No. Shiprocket è una piattaforma di spedizioni: i venditori vi prenotano corrieri come Delhivery, Blue Dart o Xpressbees, che trasportano il pacco. I pacchi ShiprocketX viaggiano con l’AWB di Shiprocket e vengono consegnati da un corriere nel paese di destinazione.

### Cosa significa RTO nel tracciamento Shiprocket?

RTO sta per *return to origin* (rientro al mittente): il pacco non è stato consegnato dopo fino a tre tentativi — o prima su richiesta del venditore — e sta tornando al venditore. `RTO DELIVERED` significa che il venditore lo ha ricevuto indietro.

### Qual è il numero assistenza di Shiprocket?

Il sito di tracciamento Shiprocket elenca 9643887070, 9711451060 e 8510042020 per problemi di tracciamento. Per qualsiasi problema con l’ordine, Shiprocket invita gli acquirenti a contattare il venditore.

### Perché il tracciamento Shiprocket dice AWB Not Found?

Shiprocket non riconosce il codice inserito: verifica errori di battitura o cifre mancanti e attendi qualche minuto se l’ordine è appena stato effettuato, come suggerito dalla pagina. Anche le spedizioni più vecchie di circa un anno non risultano più.

:::sources
- [Shiprocket: Track your orders easily](https://www.shiprocket.in/shipment-tracking/) – tracciamento per AWB, ID ordine o cellulare, OTP e myShiprocket, AWB via e-mail e SMS, contattare il venditore, aggiornamenti dopo scansioni, ShipX
- [Shiprocket: AWB Not Found](https://shiprocket.co/tracking) – controlli per codice non trovato, avviso pagamenti, numeri telefonici, live chat, supporto 24/7
- [Shiprocket: Contact us](https://www.shiprocket.in/contact/) – sede Gurugram, orari telefonici, e-mail, escalation e tempi di risposta
- [Shiprocket: FAQs](https://www.shiprocket.in/faq/) – piattaforma per venditori, stime di consegna, RTO, aggiornamenti dopo scansioni
- [Shiprocket: Native courier integrations](https://www.shiprocket.in/carrier-integrations/) – 42 corrieri partner e relativi nomi
- [Shiprocket: ShiprocketX](https://www.shiprocket.in/x/) – servizi internazionali, tempi di consegna, servizi duty-paid, copertura fino a 5.000 INR
- [Shiprocket: NDR management](https://www.shiprocket.in/ndr-management/) – NDR e RTO, flusso acquirente NDR, SMS e chiamate automatiche
- [Shiprocket Support: How to Process an NDR?](https://support.shiprocket.in/support/solutions/articles/43000664210-how-to-process-an-ndr-) – nuovi tentativi, link SMS, tentativi falsi, tre tentativi prima dell’RTO
- [Shiprocket: Product highlights from March 2024](https://www.shiprocket.in/blog/product-highlights-from-march-2024/) – richiesta di nuovo tentativo e motivazioni sulla pagina di tracciamento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati AWB, testi di stato, hub di esportazione a Delhi, USPS negli USA, vista archiviata, cosa mostra Peek
:::
