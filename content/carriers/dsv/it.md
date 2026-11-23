---
title: Tracking DSV: numeri di spedizione, stati e DB Schenker
description: Traccia una spedizione DSV o ex DB Schenker (strada, aereo o mare): quale numero usare, significato degli stati, consegna, reclami e contatti.
slug: tracking-dsv
published: 2026-11-23
updated: 2026-11-23
---

DSV è un gruppo danese di trasporti e logistica che movimenta merci su strada, via aerea e via mare, e nel 2025 ha acquisito Schenker (DB Schenker) da Deutsche Bahn. Su dsv.com il riquadro «Track your shipment» offre due campi: «Track DSV shipment» apre il tracciamento DSV, myDSV, e «Track Schenker shipment» l'ex portale DB Schenker. Non occorre un account. Il numero viene fornito da chi ha prenotato il trasporto, di solito il mittente o il negozio: incollalo nel box qui sopra.

## Numero di tracking DSV: com'è fatto e dove trovarlo

Il numero lo detiene l'azienda che ha prenotato DSV: cercalo nell'e-mail di spedizione o nella pagina dell'ordine del negozio, oppure chiedilo al mittente. Altro in [dove trovare il numero di tracking](guide:find-tracking-number).

Finché DSV integra Schenker nei propri sistemi mantiene due portali: una spedizione prenotata con Schenker può comparire solo nel portale Schenker. Se uno non dà risultati, prova l'altro.

| Numero (inventato) | Che cos'è |
| --- | --- |
| `DEKLM482917365` | 14 caratteri: un codice Paese europeo come DE, SE, DK o PL, tre lettere e nove cifre. Ex numero stradale DB Schenker e il formato che il catalogo di Peek assegna a DSV. |
| `KLM4829173` | Tre lettere e sette o otto cifre: altro ex numero stradale DB Schenker. Peek non seleziona DSV da questo formato. |
| `094827361549283711` | 18 cifre (20 con `00` davanti): codice a barre di un collo di una ex spedizione stradale DB Schenker. |
| Lettere e cifre | Numero di spedizione, prenotazione o *house bill* myDSV. Peek interroga myDSV con qualunque codice di 5–40 lettere e cifre che includa almeno una cifra. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Link al tracciamento myDSV, come aperto da «Track DSV shipment» su dsv.com. |

Un riferimento cliente o d'ordine, o la lettera di vettura aerea principale (*master air waybill*), può trovare una spedizione nella ricerca myDSV, ma Peek non segue tali riferimenti: usa il numero di spedizione, prenotazione o *house bill* DSV. Peek non può neppure scegliere una singola spedizione quando un codice ne abbina più d'una.

## Significato degli stati di tracking DSV

myDSV registra ogni scansione, dalla prenotazione alla prova di consegna. La vista pubblica fornisce dati essenziali, quella privata su richiesta tutti i dettagli. Le tappe myDSV lette da Peek:

| DSV mostra | Cosa vuol dire per te | Stato in Peek |
| --- | --- | --- |
| `Booking` | DSV ha la prenotazione, con data ma senza orario. | «Etichetta creata» |
| `Creation` | La spedizione è stata creata nel sistema stradale DSV. | «Etichetta creata» |
| `Actual pickup` | DSV ha ritirato la merce dal mittente. | «Preso in carico» |
| `Cargo received` | La merce è arrivata a DSV, in un terminal o dopo il ritiro. | «Preso in carico» |
| `Actual Departure`, `Actual departure - Origin Port` | Partita da un porto o aeroporto sulla tratta principale. | «In transito» |
| `Actual arrival`, `Actual arrival- Destination Port` | Arrivata a un porto o aeroporto sulla tratta principale. | «In transito» |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV ha ceduto la merce al termine del proprio servizio, concludendo la spedizione (la prima conserva la grafia di DSV). | «Consegnato» |
| `Actual delivery` | Consegnata. | «Consegnato» |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` e `Requested delivery` sono stime, non scansioni: Peek le esclude dalla cronologia e mostra `Estimated delivery` come giorno previsto di consegna (o intervallo) finché la spedizione non è consegnata.

L'ex portale DB Schenker copre le spedizioni su strada con proprie diciture:

| Il portale DB Schenker mostra | Cosa vuol dire per te | Stato in Peek |
| --- | --- | --- |
| `Booked` | Il mittente ha prenotato il trasporto. | «Etichetta creata» |
| `Out for collection` | Un autista è diretto al mittente per il ritiro. | «Etichetta creata» |
| `Collected` | Ritirata dal mittente. | «Preso in carico» |
| `Delivered to terminal by shipper` | Consegnata dal mittente a un terminal. | «Preso in carico» |
| `Arrived`, `Departed` | Arrivata o partita da un terminal. | «In transito» |
| `Customs clearance initiated` | Sdoganamento avviato. | «In dogana» |
| `Customs clearance finalised` | Sdoganata e di nuovo in viaggio. | «In transito» |
| `Out for Delivery` | Sul mezzo per la consegna. | «In consegna» |
| `Not delivered` | Consegna fallita; può seguire il motivo. | «Tentativo di consegna» |
| `To consignee's disposal`, `Ready for pick up` | Disponibile per il ritiro. | «Pronto per il ritiro» |
| `Delivered` | Consegnata. | «Consegnato» |
| `Picked up by consignee`, `Picked up at agent` | Ritirata dal destinatario o presso un agente. | «Consegnato» |
| `ePOD available` | Prova di consegna elettronica disponibile. | «Consegnato» |

Lungo il percorso compaiono anche `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` e `Vessel/Train Unloaded`: tutti «In transito» in Peek. `Not Collected` (ritiro fallito) e `Not loaded` (carico mancato, con motivo) appaiono come «Problema segnalato». Se il portale aggiunge una motivazione accanto a una tappa, Peek la riporta di seguito. Altro in [significato degli stati di tracciamento](guide:tracking-statuses).

## Come consegna DSV

**Pallet e carichi parziali.** DSV definisce la propria rete stradale groupage la più estesa d'Europa: oltre 36 Paesi, più di 400 terminal e spedizioni fino a 2.500 kg, quindi un pallet o un ordine voluminoso può arrivare su camion da un terminal DSV.

**Ordini online.** Il servizio Parcel E-commerce di DSV raccoglie i pacchi dei negozi, li consolida oltre confine e affida l'ultimo miglio a reti locali: potrebbe consegnare un altro corriere. Se l'e-mail del negozio ne indica uno o fornisce un secondo codice, traccia anche quello: la lettura DSV di Peek non mostra un corriere successivo.

**Notifiche e prova di consegna.** Su myDSV gli utenti possono attivare avvisi (es. ritiro o sdoganamento), vedere gli orari stimati di arrivo per il trasporto su strada e scaricare la prova di consegna al termine. Se non hai prenotato tu DSV, chiedi al mittente. Le pagine DSV non indicano quanti tentativi effettua né per quanto un terminal custodisce la merce: chiedi alla filiale DSV incaricata.

**Dogana.** Nella maggior parte dei Paesi in cui è presente, DSV è doganalista autorizzato; altrove collabora con agenti. Per assistenza allo sdoganamento DSV invita a contattare DSV nel Paese interessato. Nell'UE gli acquisti online da fuori UE prevedono dal 1° luglio 2026 un dazio di 3 € per tipo di prodotto sugli ordini fino a 150 € e dal 1° novembre 2026 una tariffa di gestione dell'Unione di 2 € per tipo di prodotto di qualunque valore. Altrove valgono le regole del tuo Paese: vedi [pacchi fermi in dogana](guide:customs).

## Spedizione DSV in ritardo, ferma o danneggiata

**Nessun movimento.** Se un portale si ferma, prova l'altro su dsv.com: una spedizione Schenker può non essere ancora su myDSV. Poi chiedi al mittente che ha prenotato DSV. Vedi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Merce danneggiata o mancante.** Per un ordine online parti dal negozio che ha prenotato DSV. Il servizio eClaims di DSV raccoglie reclami e documenti, mostra lo stato della pratica e ti mette in contatto con i liquidatori locali; non indica una scadenza specifica, quindi non aspettare. Vedi [pacchi danneggiati](guide:damaged-parcel) e [pacchi smarriti](guide:lost-parcel).

**Uso improprio del nome DSV.** DSV segnala che aziende o individui estranei usano talvolta il suo nome per proporre servizi di trasporto. Se dubiti di un sito o di un contatto, verifica presso la filiale DSV locale; per SMS sospetti su un pacco vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Contatti e assistenza clienti DSV

Su dsv.com:

- **Modulo di contatto:** sulla [pagina di aiuto e contatti DSV](https://www.dsv.com/en/support/help-and-contact).
- **Filiali locali:** DSV opera in oltre 90 Paesi; [trova la filiale DSV locale](https://www.dsv.com/en/support/find-dsv) per Paese.
- **Reclami:** tramite eClaims dalla [pagina Claims di DSV](https://www.dsv.com/en/support/self-services/claims).
- **DSV A/S:** Hedehusene, Danimarca, +45 43 20 30 40 come da note legali (non è una linea di tracciamento).

Le pagine di supporto DSV non indicano alcun numero di telefono per il tracking.

## Tracciare una spedizione DSV con Peek

Incolla un numero di 14 caratteri come `DEKLM482917365` e Peek interroga DSV mentre lo aggiungi («Verifica dei servizi di tracciamento…»), mostrando «DSV ha questo pacco.» appena DSV lo riconosce. Se DSV non lo elenca ancora, compare «In attesa della prima scansione di DSV» con la nota «Probabilmente DSV» mentre Peek continua a verificare; dopo una settimana, Peek suggerisce di controllare il numero o scegliere un altro corriere.

Peek non individua DSV da altri formati. Incollali comunque: Peek traccia oltre 4.500 corrieri. Per scegliere DSV manualmente, premi «Cambia» e cerca DSV (anche «Schenker» lo trova: «Noto anche come DB Schenker»). Su un pacco già aggiunto usa «Cambia corriere». Un link di mydsv.com (`?query=`) assegna subito DSV, così come un link del portale Schenker su dsv.com con `refNumber=`.

Peek interroga prima myDSV. Un ex codice stradale o barcode DB Schenker ignoto a myDSV passa al portale Schenker, che Peek legge solo per spedizioni su strada: per spedizioni aeree o marittime presenti solo lì usa «Track Schenker shipment» su dsv.com.

Peek mostra lo stato, ogni tappa nella dicitura DSV con città e Paese e il giorno di consegna previsto. DSV riporta l'ora locale senza fuso orario: Peek ricava il fuso dal luogo (città, porto o codice aeroporto) o dalle scansioni vicine, altrimenti usa l'ora di Copenaghen, quindi talvolta può essere impreciso. Il nome del destinatario, quando DSV lo indica, lo vedi solo tu, sotto «Destinatario», e la città dell'indirizzo di consegna può chiudere il percorso della tua mappa mentre la spedizione è in viaggio; i link condivisi non mostrano né l'uno né l'altra. Peek omette nome del servizio, città del mittente, via, codici postali e barra di avanzamento DSV, e dal portale Schenker mittente, riferimenti, merci e firmatario.

Se il corriere svedese Early Bird affida il pacco a DSV o DB Schenker, Peek indica DSV come corriere incaricato e lo segue appena DSV pubblica scansioni più recenti.

Finché la spedizione è aperta in Peek, o nel giorno della consegna con le notifiche attive, Peek la controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00, ora locale, il giorno in cui DSV la registra in consegna, con notifiche su «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e ogni 6 ore dopo sette giorni senza novità su «Etichetta creata». Se la spedizione resta quattro giorni senza una nuova riga su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …» con il giorno di quella riga e un link «Chiedi aiuto a DSV». Non può contattare DSV o il mittente, prenotare consegne né aprire reclami.

## Domande sul tracking DSV

### DSV e DB Schenker sono la stessa azienda?

Sì. DSV, fondata nel 1976, ha completato l'acquisizione di Schenker da Deutsche Bahn il 30 aprile 2025, e dbschenker.com è confluito in dsv.com. Le spedizioni Schenker conservano per ora un portale separato.

### Come si traccia ora una spedizione DB Schenker?

Inserisci il numero sotto «Track Schenker shipment» nel riquadro «Track your shipment» di dsv.com, oppure incollalo qui sopra; se non ha 14 caratteri con codice Paese iniziale, seleziona DSV manualmente. Peek consulta myDSV e poi il portale Schenker per i trasporti su strada.

### Cosa significa «handover of cargo» nel tracking DSV?

DSV ha consegnato la merce al termine del proprio incarico e la spedizione è completata; Peek mostra «Consegnato». Se non ti è arrivato nulla, chiedi al mittente dove DSV ha effettuato la consegna e leggi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

### DSV ha un numero di telefono per l'assistenza clienti?

Le pagine di supporto non ne riportano alcuno per il tracking. Usa il modulo di contatto o cerca la filiale DSV locale su dsv.com. Il numero nelle note legali di DSV A/S (+45 43 20 30 40 in Danimarca) non è una linea di tracciamento.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – tracking senza account, due campi, fusione di dbschenker.com in dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – vista pubblica e privata, registro scansioni, notifiche, prova di consegna
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – portali DSV e Schenker, eClaims, sistemi Schenker fino alla migrazione
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – modulo di contatto, due versioni myDSV nella transizione
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – oltre 90 Paesi, ricerca filiali
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – filiali per Paese
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – funzionamento di eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ Paesi, 400+ terminal, fino a 2.500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – consolidamento transfrontaliero, reti locali ultimo miglio
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – doganalista autorizzato, agenti, contattare DSV nel Paese
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – fondata nel 1976, strada, aereo e mare, acquisizione Schenker nel 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – completata il 30 aprile 2025, integrazione
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S in Danimarca, telefono, uso improprio del nome DSV
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per tipo di prodotto fino a 150 €, tariffa di 2 €
- [Commissione europea: guida al dazio di 3 € e alla tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per tipo di prodotto di qualunque valore
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formati e link, due portali, diciture e stadi DSV, orari locali, cosa legge Peek
:::
