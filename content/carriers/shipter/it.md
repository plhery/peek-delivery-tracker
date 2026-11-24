---
title: Tracciamento Shipter: codici H3 dalla Corea del Sud
description: Traccia pacchi aerei internazionali Shipter (H3 Networks) dalla Corea del Sud: numeri H3...KR, consegne locali, stati e link di tracciamento.
slug: tracciamento-shipter
published: 2026-11-24
updated: 2026-11-24
---

Shipter, gestito da **H3 NETWORKS Co., Ltd.** in Corea del Sud, spedisce pacchi B2B e B2C dalla Corea verso oltre **100 paesi** attraverso più di 40 canali logistici, su una rete aerea che collega oltre 25 aeroporti ogni settimana ([shipter.kr](https://www.shipter.kr/)). Il servizio B2B copre trasporto, dogana e magazzinaggio; quello B2C copre consegna all’estero, dogana e ultimo miglio. Amazon Korea lo ha scelto come partner logistico ufficiale nella propria rete SPN (*Service Provider Network*), è registrato come corriere su eBay, Kogan e Allegro, il suo tracciamento è collegato a AfterShip e 17TRACK e opera anche come partner per spedizioni destinate a influencer.

Puoi controllare un pacco su [shipter.kr/tracking](https://www.shipter.kr/tracking) oppure incollare il codice `H3...KR` nel riquadro qui sopra.

## Formato del codice di tracciamento Shipter

I codici Shipter iniziano con `H3` e terminano con `KR`, con due lettere e cinque o sei cifre in mezzo:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `H3AU482917KR` | 12 caratteri: `H3`, due lettere, sei cifre e `KR`. Peek interroga solo Shipter. |
| `H3FR48291KR` | 11 caratteri: `H3`, due lettere, cinque cifre e `KR`. Peek interroga solo Shipter. |

Un link `shipter.kr/tracking/<codice>` o `shiptertracking.com/trackingResult/<codice>` assegna subito il pacco a Shipter. Per i pacchi spediti dalla Corea del Sud tramite Korea Post, vedi [Korea Post](carrier:korea-post); per altri formati, consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Shipter

La cronologia Shipter unisce i propri eventi di esportazione dalla Corea del Sud e di trasporto aereo internazionale alle scansioni inoltrate dal corriere locale dell’ultimo miglio nel paese di destinazione, in inglese.

| Dicitura Shipter / partner | Cosa significa |
| --- | --- |
| `The shipment information has been received by shipter, but the package has not been picked up yet.` | Preavviso ricevuto dal venditore coreano; Shipter non ha ancora scansionato il pacco fisico. |
| `The parcel has been scanned by shipter and ready to be shipped.` | Shipter ha ricevuto e scansionato il pacco nella propria sede in Corea. |
| `The parcel has been shipped.` / `The flight has been departed.` / `Depart origin airport` | Spedito dalla Corea del Sud su un volo cargo internazionale. |
| `Arrived at destination airport` / `Collected from airport terminal` | Atterrato nel paese di destinazione e ritirato dal terminal cargo dell’aeroporto. |
| `Import customs cleared` | La dogana di destinazione ha rilasciato il pacco per il prosieguo della consegna locale. |
| `Arrived at destination` / `In transit` | Inserito nel flusso di smistamento a destinazione prima delle scansioni del corriere locale. |
| `Parcel received in processing centre (...)` / `Parcel depart from ... depot` / `The item is ready for shipment...` | Lavorato e spedito nella rete del corriere locale nel paese di destinazione. |
| `Your parcel has been received at ... depot..` / `Your parcel is currently in transit to ...` | Inoltrato dal partner locale nel paese di destinazione, con deposito e città. |
| `Out for delivery` / `Your parcel is with your local courier and ready for delivery..` | In consegna oggi con il corriere locale. |
| `Your parcel was delivered` / `Delivered.` | Consegnato all’indirizzo del destinatario. |

Due avvisi inoltrati dal partner locale, `Your parcel will be entrusted to last mile .it is being prepared by your sender` e `Shipping information received`, compaiono dopo la scansione di accettazione di Shipter. Non indicano che il pacco sia tornato indietro.

## Transito aereo internazionale, dogana e passaggi locali

Shipter spedisce i pacchi via aerea dalla Corea del Sud e li affida a un corriere locale nel paese di destinazione:

- **Volo e aeroporto:** dopo `Depart origin airport`, Shipter mostra `Arrived at destination airport` all’atterraggio, `Collected from airport terminal` quando il pacco lascia il terminal cargo e `Import customs cleared` a sdoganamento import completato. Consulta la [guida alla dogana](guide:customs) e il [tracciamento fermo](guide:tracking-not-updating).
- **Consegna locale dell’ultimo miglio:** un corriere nel paese di destinazione consegna il pacco e Shipter riporta nella propria cronologia le sue scansioni di deposito, `Out for delivery` e consegna.
- **Nessuna data di consegna stimata:** l’API pubblica di tracciamento Shipter non pubblica una data stimata di consegna; segui l’avanzamento controllando `Import customs cleared`, l’arrivo al deposito locale e `Out for delivery`.

## Tracciare Shipter con Peek

Incolla il codice `H3...KR` oppure un link `shipter.kr/tracking/...` o `shiptertracking.com/trackingResult/...` in Peek. Per un codice di 11 o 12 caratteri, Peek interroga solo Shipter. Mostra «Shipter ha questo pacco.» appena Shipter risponde. Se Peek indica un altro corriere, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella sua pagina e seleziona Shipter.

Peek legge il tracciamento Shipter tramite l’[adattatore open source Shipter di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter) e riprova in caso di errore di rete. Mostra il testo inglese di ogni scansione con il deposito o il luogo indicato e l’orario di consegna. Shipter fornisce gli orari con offset GMT e Peek li mostra con l’ora del tuo dispositivo; un orario senza offset resta come indicato da Shipter.

Peek traduce le scansioni Shipter in fasi chiare: «Etichetta creata» per il preavviso prima del ritiro, «Preso in carico» appena Shipter scansiona il pacco in Corea, «In transito» per partenza del volo, arrivo in aeroporto, sdoganamento e passaggi nei centri locali, «In consegna» quando il corriere locale parte per la consegna e «Consegnato» a consegna avvenuta. Gli avvisi tardivi dell’ultimo miglio (`Shipping information received` e `Your parcel will be entrusted to last mile...`) restano nella cronologia senza far retrocedere lo stato.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Shipter.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Shipter.»

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Shipter mostra `Out for delivery` o `Your parcel is with your local courier and ready for delivery..`, ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracker Shipter risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Shipter non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Peek può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Contattare Shipter

Shipter opera per le aziende che spediscono tramite la sua rete:

- **Sito ufficiale e richieste di servizio:** [shipter.kr](https://www.shipter.kr/) (H3 NETWORKS Co., Ltd.; *이용문의* per richieste di servizio)
- **Tracker ufficiale online:** [shipter.kr/tracking](https://www.shipter.kr/tracking)
- **Contatta prima il venditore:** ogni azienda cliente ha un responsabile CS dedicato presso Shipter, che gestisce problemi, resi e reclami con i propri clienti. Se il pacco è in ritardo, danneggiato o smarrito, contatta il negozio che lo ha spedito indicando il codice `H3...KR`.

## Domande frequenti

### Che aspetto ha un codice di tracciamento Shipter?

Inizia con `H3` e termina con `KR`, con due lettere e cinque o sei cifre in mezzo: 11 o 12 caratteri, come `H3AU482917KR`. Il sito di Shipter non spiega cosa rappresentino le due lettere.

### Perché «Shipping information received» è comparso giorni dopo la spedizione del pacco?

È un avviso del partner dell’ultimo miglio nel paese di destinazione che Shipter inoltra dopo la propria scansione di accettazione. Non riporta indietro la spedizione: Peek mantiene la fase della scansione precedente.

### Perché il mio pacco Shipter mostra aggiornamenti di un altro corriere?

Shipter gestisce l’export dalla Corea del Sud e il trasporto aereo internazionale, poi affida il pacco a un corriere locale dell’ultimo miglio nel paese di destinazione e mostra le scansioni di deposito e consegna di quel partner nella cronologia Shipter.

### Shipter fornisce una data stimata di consegna?

No. Il feed pubblico di Shipter mostra eventi datati di export, volo, dogana e corriere locale con offset GMT, senza data di consegna stimata.

:::sources
- [Shipter (H3 NETWORKS Co., Ltd.)](https://www.shipter.kr/)
- [Shipter Tracking](https://www.shipter.kr/tracking)
- [universal-parcel-scraper Shipter adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shipter)
:::
