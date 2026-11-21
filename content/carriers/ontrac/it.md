---
title: Tracciamento OnTrac: codici, stati e mancate consegne
description: Traccia un pacco OnTrac o LaserShip negli USA: formati C, D, LX e 1LS, significato degli stati, tentativi falliti, ritardi e assistenza via chat Wink.
slug: tracciamento-ontrac
published: 2026-11-21
updated: 2026-11-21
---

OnTrac è un corriere statunitense dell'ultimo miglio che consegna ordini e-commerce per rivenditori e marchi in `35` Stati USA e a Washington, D.C. (`70 %` della popolazione statunitense), e dalla fusione del 2021 include LaserShip. Puoi seguire una spedizione su [ontrac.com/tracking](https://www.ontrac.com/tracking/) inserendo il numero di tracciamento oppure incollandolo direttamente nel campo qui sopra.

## Formato del numero di tracciamento OnTrac e dove trovarlo

Il codice si trova nella pagina dell'ordine o nell'e-mail di spedizione del negozio (il semplice numero d'ordine non funziona). Secondo le FAQ di OnTrac, i codici iniziano con `C`, `D`, `1LS`, `LS`, `LX` o `BN` ([come trovare il numero di tracciamento](guide:find-tracking-number)). Ecco le forme riconosciute da Peek, con cifre inventate:

| Forma (inventata) | Cos'è |
| --- | --- |
| `C14829105736497` | `C1` e 13 cifre con cifra di controllo finale (15 caratteri): riconosciuto subito come OnTrac. |
| `D10482910573645` | `D10` e 12 cifre con cifra di controllo (15 caratteri): riconosciuto subito come OnTrac (`C24829105736495` e gli altri `C`/`D` validi a 14 cifre vengono verificati presso OnTrac). |
| `LX48291057` | Formato LaserShip: `L`, poi `A`, `E`, `H`, `I`, `N` o `X` e 8 cifre (10 caratteri). |
| `1LS482910573649` | Formato LaserShip: `1LS` e da 12 a 14 lettere o cifre. |
| `AD482910573` | Riferimento del mittente (`AD` + 9 cifre, che Peek propone anche a [GLS Italy](carrier:gls-it)): scegliendo OnTrac, viene convertito nel codice OnTrac effettivo. |

Il vecchio dominio `lasership.com` reindirizza ora a `ontrac.com`, dove si tracciano anche i colli LaserShip. Per i prefissi `LS` o `BN` (o formati diversi), usa direttamente `ontrac.com/tracking/`, perché selezionando manualmente OnTrac in Peek comparirà «non emette numeri come questo». Un numero appena emesso può restare vuoto all'inizio: OnTrac invita ad attendere **1–3 giorni** dall'e-mail di spedizione, poiché alcuni venditori impiegano fino a 24 ore per trasmettere i dati.

## Cosa significano gli stati di tracciamento OnTrac

Il sito OnTrac è solo in inglese. Queste sono le scansioni e le voci di ritardo lette da Peek:

| Stato OnTrac | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `On its way to OnTrac` | OnTrac ha i dati dell'etichetta ma non ancora il pacco (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` o `Waiting for package from shipper.`). | «Etichetta creata» |
| `Origin Scan` | OnTrac ha preso in carico il collo ed è diretto verso il centro locale (i passaggi intermedi riportano `Arrived at Facility`). | «In transito» |
| `Arrived at Final Servicing Facility` | Arrivato nella filiale locale che curerà il recapito. | «In transito» |
| `Loaded onto vehicle` | Caricato su un veicolo di linea o di distribuzione. | «In transito» |
| `Out for Delivery` | Oggi a bordo col corriere per la consegna. | «In consegna» |
| `Package Delivered` | Consegnato. | «Consegnato» |
| `Delivery Pending` | Il corriere non ha potuto lasciare il pacco (motivi di sicurezza o firma richiesta); OnTrac riproverà. | «Tentativo di consegna» |
| `Business closed. Please provide hours` | Attività chiusa al passaggio; fino a 3 tentativi, OnTrac chiede gli orari di apertura. | «Tentativo di consegna» |
| `Incomplete address. Please contact us` | Indirizzo incompleto; invia i dettagli per evitare il reso. | «Tentativo di consegna» |
| `Details needed. Please contact us` | Spedizione ferma in attesa di precisazioni su indirizzo o accesso. | «Problema segnalato» |
| `Order has been transferred to another carrier` | Affidato a un altro corriere non specificato da OnTrac (anche se l'intestazione su `ontrac.com` segna *Delivered*). | «In transito» |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Ritardo operativo, strada chiusa o area non raggiungibile, con nuova data prevista. | «In transito» |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Ritardo per maltempo sulla rete OnTrac. | «In transito» |
| `Misrouted. Delivery date updated` | Disguido di smistamento risolto con aggiornamento della data. | «In transito» |

Approfondisci le fasi generali in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Orari di consegna OnTrac e mancate consegne

**Orari.** OnTrac consegna alle abitazioni di norma tra le **`08:00` e le `21:00`** (ora locale) nel giorno previsto, e in alcune aree effettua consegne anche **sabato e domenica** nella stessa fascia (limiti standard nei termini aggiuntivi: peso fatturabile fino a `50 lbs`, lunghezza `48 in`, secondo lato `30 in` e volume `8.640 pollici cubi`).

**Mancata consegna.** OnTrac effettua fino a **3 tentativi di consegna** (il successivo entro **1–2 giorni lavorativi**, mai in giornata) e poi restituisce o gestisce il collo secondo le regole per le spedizioni non consegnabili ([mancata consegna](guide:missed-delivery)).

**Istruzioni di accesso e cambio indirizzo.** Se risulta un tentativo mentre eri in casa, controlla l'indirizzo sul sito del venditore e invia codici cancello o istruzioni all'assistente chat **Wink** col numero di tracciamento (se il pacco è già in consegna, varranno dal tentativo successivo). Qualsiasi **cambio di indirizzo** richiede invece l'approvazione del venditore ([cambiare indirizzo di consegna](guide:change-delivery-address)).

**Fermo deposito e SMS NotifyMe.** Il sito di OnTrac non indica punti di ritiro né armadietti propri, e OnTrac trattiene un pacco in filiale solo su richiesta (porta numero di tracciamento e documento d'identità con foto). Da `ontrac.com/tracking/` puoi attivare `Get Text Alerts` (`NotifyMe`) per ricevere SMS a uscita in consegna, tentativo e recapito (disattivazione inviando `STOP` al `(469) 529-1133`) e visualizzare la foto di consegna (`VPOD`).

## Pacco OnTrac in ritardo, bloccato o consegnato ma assente

- **Nessun aggiornamento:** se lo stato non cambia per **3 giorni lavorativi**, OnTrac invita a sentire prima il rivenditore e poi **Wink** ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).
- **Risulta consegnato ma non c'è:** controlla indirizzo e foto di consegna su `ontrac.com/tracking/` (o senti l'amministrazione se lasciato in un locker condominiale), poi scegli `Tracking shows delivered; no package` nel modulo `Have a question?` o apri un ticket con **Wink**. Riceverai un numero di pratica via e-mail con riscontro entro **48 ore**; trascorsi oltre **2 giorni lavorativi** senza novità, contatta il venditore ([consegnato ma non ricevuto](guide:delivered-not-received)).
- **Danneggiato o smarrito:** i termini OnTrac prevedono che i reclami formali siano inviati dal mittente (`cargoclaims@ontrac.com`); chiedi sostituzione o rimborso al negozio ([pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel)).

## Assistenza clienti OnTrac: chat, SMS e orari

Nelle pagine [Support](https://www.ontrac.com/support/) e [FAQ](https://www.ontrac.com/faq/) OnTrac non elenca numeri telefonici vocali né e-mail per i destinatari, ma mette a disposizione:

- **Chat con Wink:** icona rossa in basso a destra su `ontrac.com` (serve un numero di tracciamento valido).
- **Linea SMS automatica:** invia `start` via SMS al **`703-348-8441`** (solo SMS per tracciare o aprire una pratica; costi secondo operatore; `STOP` per disattivare).
- **Modulo web:** sezione `Have a question?` sotto l'esito del tracciamento su `ontrac.com/tracking/`.
- **Orari del centro assistenza:** lunedì–venerdì `08:00–21:00` ora della costa Est USA (*Eastern Time*), sabato–domenica `08:00–20:00` *Eastern Time*; chiuso a Natale, il 26 dicembre e a Capodanno.

Per riconoscere eventuali messaggi contraffatti, consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare i pacchi OnTrac con Peek

[Peek](/) riconosce subito OnTrac («Corriere rilevato») per i codici `C1` e `D10` con cifra di controllo valida, per i codici LaserShip `LX` e `1LS` e per i link `ontrac.com` contenenti `number=`. Per gli altri `C` o `D` validi a 14 cifre mostra «Verifica dei servizi di tracciamento…» e poi «OnTrac ha questo pacco.». Per un riferimento `AD` a 9 cifre, tocca «Cambia» e scegli OnTrac: OnTrac trova il pacco e Peek mostra anche il suo codice OnTrac («OnTrac lo traccia con il numero») (prima della prima scansione appare «In attesa di OnTrac»).

Peek legge il servizio JSON di OnTrac e mostra stato, scansioni con città e Stato USA sulla mappa (fino alle 100 più recenti), consegna stimata, ora di consegna, peso e dimensioni, escludendo destinatario, firma e foto di consegna. Quando l'evento include il fuso orario, l'istante viene convertito nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»); senza fuso, Peek la legge sull'ora dello Stato USA della scansione e la converte allo stesso modo. `Order has been transferred to another carrier` resta «In transito» su Peek senza passaggi successivi, poiché OnTrac non indica il corriere subentrante.

A pacco aperto o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle `06:00` ora locale nel giorno della scansione `Out for Delivery` finché il servizio OnTrac risponde; altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza novità, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può contattare OnTrac o il negozio per te.

## Domande frequenti sul tracciamento OnTrac

### Con quali lettere iniziano i numeri di tracciamento OnTrac?

Con `C`, `D`, `1LS`, `LS`, `LX` o `BN` secondo OnTrac; i codici `C` e `D` gestiti da Peek sono lunghi 15 caratteri (lettera più 14 cifre).

### Perché il tracciamento OnTrac non si aggiorna?

Attendi da 1 a 3 giorni dopo l'e-mail di spedizione perché compaiano i primi aggiornamenti. Se una spedizione attiva non cambia stato per 3 giorni lavorativi, contatta il venditore e poi Wink.

### A che ora consegna OnTrac?

Tra le `08:00` e le `21:00` nel tuo fuso locale alla data prevista, inclusi sabato e domenica nelle zone coperte nel fine settimana.

### OnTrac offre il tracciamento in tempo reale?

OnTrac mostra data, ora e città di ogni scansione (più la foto di consegna sul sito) e invia avvisi SMS `NotifyMe` per uscita in consegna, tentativo ed esito.

### OnTrac è un corriere affidabile?

Sì: nato nel 1991 come *California Overnight* e fuso nel 2021 con LaserShip (fondata nel 1986), gestisce oltre `102` sedi e `18` hub di smistamento negli USA ed è corriere raccomandato per i venditori Amazon MFN.

:::sources
- [OnTrac: FAQ](https://www.ontrac.com/faq/) – prefissi, orari 08:00–21:00 e weekend, nuovi tentativi in 1–2 giorni lavorativi, Wink, SMS 703-348-8441, 102+ sedi e 18 centri di smistamento
- [OnTrac: Support](https://www.ontrac.com/support/) – attesa iniziale di 1–3 giorni, indagini entro 48 ore, NotifyMe e orari/chiusure (incluso il 26 dicembre)
- [OnTrac: Tracking](https://www.ontrac.com/tracking/) – ricerca spedizioni, foto di consegna e modulo Have a question?
- [OnTrac: Script della pagina di tracciamento](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – etichette principali, colonne cronologia e causali modulo
- [OnTrac: Codici di stato](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – significato di tentativi, fermi, trasferimenti e ritardi
- [OnTrac: Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 tentativi, limiti di 50 lbs / 48 in e reclami riservati al mittente
- [OnTrac: About OnTrac](https://www.ontrac.com/about/) – storia di LaserShip (1986) e OnTrac (1991), fusione del 2021 e rete coast-to-coast
- [OnTrac: Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 Stati e D.C., 70 % della popolazione USA e prova visiva VPOD
- [OnTrac: Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – corriere partner per i venditori Amazon MFN
- [OnTrac: NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – avvisi SMS e disattivazione con STOP al (469) 529-1133
- [Universal Parcel Scraper: OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – formati C, D, LX, 1LS e riferimento AD, 18 diciture di stato, fusi orari, peso/dimensioni e trasferimento NFRP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento corrieri e servizi di tracciamento
:::
