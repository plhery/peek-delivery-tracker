---
title: Tracciamento SpeedX: numeri SPX, stati e assistenza
description: Traccia un pacco SpeedX negli Stati Uniti: formato del codice SPX, significato degli stati in inglese, foto di consegna con ZIP code, tentativi e contatti.
slug: tracciamento-speedx
published: 2026-11-21
updated: 2026-11-21
---

SpeedX (Speed Xpress Inc., con sede a New Hyde Park nello Stato di New York) è un corriere dell'ultimo miglio che consegna a domicilio gli ordini online negli **Stati Uniti**. Puoi seguire un pacco su [tracking.speedx.io](https://tracking.speedx.io/) o incollare il codice nel riquadro qui sopra. Il numero arriva dal negozio da cui hai acquistato: SpeedX genera l'etichetta per il venditore, che quindi possiede il codice già prima che SpeedX riceva fisicamente il pacco.

## Codice di tracciamento SpeedX: formato e dove trovarlo

SpeedX indica che tutti i suoi codici iniziano con `SPX`. In pratica sono composti da `SPX`, tre lettere e 12 o 18 cifre. Ecco i formati con caratteri inventati:

| Formato inventato | Che cos'è |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 lettere e 12 cifre (18 caratteri). |
| `SPXLGA482910573649182736` | `SPX`, 3 lettere e 18 cifre (24 caratteri). |
| `SPXPH482910573649` | `SPXPH` seguito da cifre: non è SpeedX negli Stati Uniti, ma SPX Express nelle Filippine, un'altra società. |

Peek ignora minuscole e spazi.

**Dove si trova.** Cercalo nell'e-mail di spedizione del negozio o nella pagina dell'ordine. Se non compare, SpeedX consiglia di chiederlo al rivenditore; [come trovare il numero di tracciamento](guide:find-tracking-number) elenca gli altri posti in cui guardare. Negli Stati Uniti SpeedX offre anche avvisi SMS quando il pacco esce in consegna, attivabili dal suo sito.

**Codici simili.** `SPX` seguito solo da cifre non rientra in nessuno dei due formati di SpeedX. Se un codice `SPX…` ha una cifra in più o in meno, confrontalo con l'e-mail del venditore. I codici SpeedX non si sovrappongono nemmeno a quelli di UniUni, [GOFO](carrier:gofo) o [USPS](carrier:usps) negli Stati Uniti. Se il numero continua a non risultare, SpeedX suggerisce di verificarlo con il negozio perché l'ordine potrebbe viaggiare con un altro corriere dell'ultimo miglio. Per altri schemi consulta [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento SpeedX

La pagina di tracciamento di SpeedX è solo in inglese. Questi sono i passaggi abituali in ordine di apparizione:

| Stato di SpeedX | Cosa significa per il pacco | Peek mostra |
| --- | --- | --- |
| `Shipping Label Created` | Il negozio ha creato l'etichetta; SpeedX non ha ancora il pacco. | «Etichetta creata» |
| `Pickup Requested` | Il venditore ha prenotato il ritiro presso SpeedX, che non detiene ancora il collo. | «Etichetta creata» |
| `Parcel Picked Up` | Un autista SpeedX ha ritirato il pacco dal mittente. | «Preso in carico» |
| `Arrived at Origin Facility` / `Processing at Origin Facility` | Arrivo e smistamento nel primo centro operativo SpeedX. | «In transito» |
| `In transit to destination facility` | In viaggio tra impianti SpeedX negli Stati Uniti. | «In transito» |
| `Processing at Transit Facility` | Smistato in un centro intermedio lungo il percorso. | «In transito» |
| `Arrival at Destination Facility` | Ha raggiunto il centro che serve la tua zona negli Stati Uniti. | «In transito» |
| `Processing at Designated Facility` | Smistato lì per essere assegnato al giro di un autista. | «In transito» |
| `Out for Delivery` | A bordo del mezzo per la consegna in giornata. | «In consegna» |
| `Attempted Delivery: No access` | L'autista non è riuscito a raggiungere la porta: cancello chiuso, condominio senza accesso, lavori o maltempo. | «Tentativo di consegna» |
| `Delivered` | Consegnato. La pagina di SpeedX indica anche dove è stato lasciato (ad esempio locale posta) e allega una foto. | «Consegnato» |
| `Delivered to PUDO` / `Waiting for collection from Parcelshop` | Dopo il giro il pacco è stato lasciato in un negozio convenzionato (*parcelshop*) in attesa del tuo ritiro. | «Pronto per il ritiro» |
| `Self picked up from Parcelshop` | Il pacco è stato ritirato al *parcelshop*. | «Consegnato» |
| `Parcel Void` | SpeedX ha segnalato un'anomalia sulla spedizione. Contatta il negozio. | «Problema segnalato» |
| `Exception- Parcel not found at the sort facility` | Pacco non rintracciato nel centro di smistamento. Rivolgiti al venditore. | «Problema segnalato» |
| `Parcel Disposal` | SpeedX ha registrato un problema senza specificarne la causa. Chiedi al negozio come procedere. | «Problema segnalato» |

Finché compare soltanto `Shipping Label Created` (o un ritiro non riuscito presso il mittente come `Pickup Failure:Closed Business Hours`), SpeedX non ha ancora ricevuto la merce, che resta presso il negozio o il suo magazzino.

Un tentativo fallito può indicare altri motivi, come `Attempted Delivery:Building Closed/Weekend/Holiday` per un'attività chiusa («Tentativo di consegna»). Sotto il tentativo SpeedX aggiunge una nota sul passo successivo: `The package is undeliverable and schedule to redelivery` indica un nuovo giro di consegna, mentre `The package is undeliverable and return to facility` segnala il rientro al deposito. Peek mostra questa nota subito dopo la descrizione.

Quando l'ultimo evento è un problema (`Parcel Void`, `Parcel Disposal` o pacco non trovato), l'intestazione sulla pagina di SpeedX diventa `Issue Reported` con una categoria come `Last Mile Intercept` o `Last Mile Undelivered`. SpeedX non ne pubblica il significato: senti il venditore.

## Consegna SpeedX negli Stati Uniti, tentativi e ritiro

Tutte le condizioni seguenti si riferiscono alla rete di SpeedX negli **Stati Uniti**:

**Pacchi spediti dall'estero.** SpeedX recapita negli Stati Uniti anche ordini partiti da altri Paesi: il suo servizio transfrontaliero li riceve nei principali hub aeroportuali statunitensi e cura lo sdoganamento presso la dogana USA tramite il proprio brokeraggio prima di smistarli nella rete di recapito. Sui dazi vedi [pacco fermo in dogana](guide:customs).

**Fine settimana.** Negli Stati Uniti SpeedX consegna il sabato dalle 8:00 alle 20:00 (ora locale) in tutte le zone servite e la domenica, con gli stessi orari, in alcune aree selezionate. Non offre fasce orarie su appuntamento né avvisi quando l'autista è vicino.

**Firma e foto di consegna.** La maggior parte dei pacchi SpeedX negli Stati Uniti non richiede firma; gli articoli con limiti d'età, come gli alcolici, possono richiedere la firma di un adulto di almeno 21 anni. Se non c'è nessuno in casa, l'autista lascia il pacco e scatta una foto: per vederla apri il tracciamento su `speedx.io` e inserisci il tuo codice postale statunitense (*ZIP code*) di consegna.

**Istruzioni e cambio indirizzo.** SpeedX raccoglie istruzioni di consegna (codici cancello, punto sicuro dove lasciare il pacco, orari d'ufficio o recapito telefonico) tramite il suo chatbot Miles indicando il numero di tracciamento; le note arrivano in tempo reale anche all'autista, con cui non si può parlare direttamente. Se avvisi in tempo, negli Stati Uniti SpeedX può correggere via o numero d'interno; per cambiare città, Stato o codice ZIP occorre rivolgersi al venditore.

**Consegna mancata.** Se ritieni errato un tentativo di consegna segnalato, controlla prima l'indirizzo sul sito del negozio e invia poi correzioni o indicazioni di accesso nella chat con Miles: SpeedX prevede una nuova consegna entro 1–2 giorni lavorativi. SpeedX non pubblica il numero massimo di tentativi né i giorni di giacenza. Vedi anche [mancata consegna](guide:missed-delivery).

**Ritiro.** SpeedX precisa che negli Stati Uniti non è consentito ritirare i pacchi nei suoi magazzini: deve consegnarli il corriere. Alcuni invii vengono però lasciati dopo il giro presso un punto convenzionato (*parcelshop*) con lo stato `Waiting for collection from Parcelshop`, dove puoi andare a ritirarli.

## Pacco SpeedX fermo, segnato consegnato o smarrito

Per le spedizioni gestite da SpeedX negli **Stati Uniti**:

**Nessun movimento.** SpeedX invita a contattare il venditore se lo stato non cambia per tre giorni lavorativi. Maggiori dettagli in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato consegnato ma assente.** La stessa pagina di supporto di SpeedX spiega che a volte un pacco viene segnato consegnato poco prima di arrivare davvero, oppure lasciato in un punto poco visibile della proprietà. Negli Stati Uniti controlla prima la foto di consegna, poi portico anteriore e posteriore, cassetta o locale posta (*mailroom*), garage, lati della casa e punti riparati, chiedendo anche a familiari o vicini. Segnala quindi l'accaduto a SpeedX tramite Miles e contatta il venditore per i passi successivi. Consulta [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrimento, danni o indirizzo errato.** SpeedX non accetta reclami presentati dai destinatari: per chiedere un reclamo, un rimborso o una sostituzione rivolgiti al venditore con numero d'ordine, codice di tracciamento ed eventuali messaggi (oltre a foto in caso di danni). SpeedX non indica una scadenza pubblica. Vedi [pacco smarrito](guide:lost-parcel).

**Resi.** Una volta consegnato il pacco, il reso passa dal negozio da cui hai comprato; SpeedX non ritira resi a domicilio negli Stati Uniti. Se non è ancora stato consegnato, puoi chiedere a Miles di bloccare la consegna e restituire il pacco al venditore. Vedi [rispedito al mittente](guide:returned-to-sender).

## Assistenza clienti SpeedX: chat, telefono ed e-mail

Recapiti per i destinatari negli **Stati Uniti** indicati nella [pagina di supporto di SpeedX](https://speedx.io/customer-service-and-support/):

- **Chat:** Miles, il chatbot di SpeedX, attivo tutti i giorni dalle 8:00 alle 20:00 ET (ora della costa Est USA) dal portale di tracciamento dopo aver inserito il numero.
- **Telefono negli USA:** `1-949-667-6769`, dal lunedì al venerdì dalle 9:00 alle 21:00 EST e nel fine settimana dalle 11:00 alle 20:00 EST. Un'altra risposta della stessa pagina afferma però che SpeedX non dispone di numero telefonico e rimanda a Miles, quindi è frequente venire indirizzati alla chat.
- **E-mail:** `speedxops@speedx.io`.

## Tracciare i pacchi SpeedX con Peek

[Peek](/) riconosce subito SpeedX da un codice `SPX` di entrambi i formati (`SPX` + 3 lettere + 12 o 18 cifre) o da un link `tracking.speedx.io` che contiene il numero, mostrando «Corriere rilevato» con «Cambia».

Peek legge direttamente la pagina di tracciamento di SpeedX e mostra lo stato attuale, ogni scansione con la relativa località sulla mappa all'orario registrato da SpeedX (convertito nel fuso del tuo dispositivo, con «Orari nel tuo fuso orario: …» in fondo alla cronologia su `peektracker.com` e nelle app per iPhone e Android), oltre al giorno stimato di consegna pubblicato da SpeedX e all'ora esatta della consegna. Su un link condiviso, la scansione di consegna mostra soltanto `Delivered`. Peek scarta il tuo indirizzo, la nota su dove è stato lasciato il pacco e la foto di consegna: per vedere la foto entra su `speedx.io` inserendo il tuo codice ZIP statunitense. Non è previsto passaggio di consegna a un altro corriere.

Finché SpeedX non conosce ancora il codice, Peek mostra «In attesa di SpeedX»; se dopo due giorni né SpeedX né altre fonti rispondono, mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» Se passano quattro giorni senza nuove scansioni mentre il pacco è su «Etichetta creata», «Preso in carico» o «In transito», Peek propone «Chiedi aiuto a SpeedX» con link alla pagina di supporto.

Mentre tieni il pacco aperto sullo schermo, o nel giorno di consegna con le notifiche attive, Peek controlla novità fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 del mattino (ora locale) nel giorno in cui SpeedX scansiona il pacco in consegna (`Out for Delivery`), finché risponde il tracciamento di SpeedX (tramite i soli servizi di tracciamento esterni: ogni ora, ogni 3 ore dopo una giornata senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive) —; negli altri casi, e dopo due giorni senza aggiornamenti (o 12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi scegliere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Se il sito di SpeedX non risponde, le altre fonti esterne raramente dispongono dei dati di SpeedX, quindi potrebbero non esserci novità finché non torna raggiungibile.

## Domande frequenti sul tracciamento SpeedX

### Il tracciamento SpeedX è affidabile?

Sì: SpeedX è il marchio di Speed Xpress Inc., azienda statunitense di consegne dell'ultimo miglio fondata nel 2022 nello Stato di New York che recapita ordini e-commerce negli Stati Uniti.

### Posso ritirare di persona il mio pacco SpeedX?

Non nei magazzini di SpeedX negli Stati Uniti: l'azienda non consente il ritiro presso le proprie sedi e richiede la consegna tramite corriere. Se però la cronologia riporta `Waiting for collection from Parcelshop`, dopo il giro il pacco è stato depositato in un punto convenzionato (*parcelshop*) dove puoi ritirarlo.

### Quanto impiega SpeedX a consegnare?

SpeedX non pubblica tempi garantiti per i destinatari. Ai venditori propone consegne negli Stati Uniti il giorno successivo (*next-day*) e in due giorni (*2nd-day*), ma l'eventuale data stimata arriva dal negozio e SpeedX avverte che può differire dalla consegna effettiva.

### Perché il mio numero di tracciamento SpeedX non funziona?

Quando `tracking.speedx.io` non mostra ancora dati (`No tracking information for … is available at this time`), il mittente potrebbe non aver ancora inviato i dati del pacco a SpeedX oppure il codice è errato. SpeedX consiglia di verificare il numero con il negozio, perché la spedizione potrebbe essere affidata a un altro corriere.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – prefisso SPX, glossario, SMS, consegne sabato e domenica, firma dai 21 anni, foto tramite codice ZIP, istruzioni e cambi indirizzo con Miles, nessun ritiro in deposito, 3 giorni lavorativi senza aggiornamenti, reclami tramite venditore, resi, telefono, e-mail e orari
- [SpeedX: Tracking](https://tracking.speedx.io/) – portale di tracciamento in inglese di SpeedX
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – recapito dell'ultimo miglio negli USA, etichette generate da SpeedX, servizi next-day e 2nd-day e foto di consegna
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – presa in carico negli hub aeroportuali USA, brokeraggio doganale proprio e smistamento nella rete SpeedX
- [SpeedX: About Us](https://speedx.io/about-us/) – fondazione nel 2022 a New York e copertura nelle città statunitensi
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – ragione sociale Speed Xpress Inc. e sede a New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati SPX riconosciuti, lettura degli stati, note sui tentativi, avviso di numero senza dati e privacy sui link condivisi
:::
