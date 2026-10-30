---
title: JRT tracking: numero JRT…MM, stati, dogana e DHL
description: Tracking JRT (今日通) per i pacchi da Xiamen: com'è fatto il numero JRT…MM, cosa vogliono dire le righe in cinese e in inglese e chi consegna in Italia.
slug: jrt-tracking
published: 2026-10-31
updated: 2026-10-31
---

JRT (今日通, Jinritong) è 厦门今日通商贸有限公司, uno spedizioniere di Xiamen, in Cina. Se un negozio cinese ha spedito il tuo ordine con JRT, ti avrà dato un numero che inizia con JRT e finisce con MM: incollalo nel box qui sopra. Il tracking di JRT segue il pacco dalla Cina al volo, alla dogana e poi al corriere che lo consegna: verso l'Europa la cronologia finisce con DHL, di cui copia le scansioni.

## Numero di tracking JRT: com'è fatto

Le lettere di vettura di JRT sono JRT, dieci cifre e MM: 15 caratteri in tutto (JRT sono le iniziali di 今日通). Con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `JRT0000000000MM` | JRT, dieci cifre e MM: una lettera di vettura JRT. |
| `JRT000000000MM` | Solo nove cifre: non è un numero JRT. Controlla che non ne manchi una. |
| `UP0000000000000000` | UP e sedici cifre: un riferimento del mittente con cui si può trovare un ordine JRT, ma non è detto che lo usino solo i clienti di JRT. |
| `RR000000005CN` | Due lettere, nove cifre e CN: un numero di [China Post](carrier:china-post), non di JRT. |

In Peek spazi e minuscole non contano. Non trovi il numero? Leggi [dove si trova il codice tracking](guide:find-tracking-number). Il tracking di JRT trova un ordine anche con il riferimento del venditore, purché scritto in modo identico, trattini compresi.

**Un link con delle cifre al posto del nome.** Il tracking di JRT non ha un dominio: gira sull'indirizzo numerico di un server, in HTTP semplice (senza HTTPS), e Peek non conosce altri siti di JRT. Un suo link contiene `trackIndex.htm`, `documentCode=` e un numero o un riferimento: puoi incollarlo in Peek così com'è. Peek però non mette un link al tracking: senza HTTPS il tuo numero viaggerebbe in chiaro.

## Stati del tracking JRT: cosa significano

Molte righe di JRT sono in cinese con accanto la traduzione inglese di JRT, e Peek le riporta per intero. Nell'ordine in cui compaiono:

| JRT scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` | Sono arrivati i dati elettronici della spedizione: il venditore ha creato l'ordine. È una riga di Hualei (华磊), la piattaforma su cui gira il tracking di JRT, non una scansione. | «Etichetta creata» |
| `Shipment information received` | I dati della spedizione sono arrivati a JRT; nessuno ha ancora scansionato il pacco. | «Etichetta creata» |
| `到达收货点` | Arrivato al punto di raccolta: la prima scansione di JRT. | «Preso in carico» |
| `货物离开操作中心` | Il pacco ha lasciato il centro operativo di JRT. | «In transito» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Consegnato in aeroporto, aspetta un volo. «Delivered» qui vuol dire all'aeroporto, non a te. | «In transito» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Sdoganato all'esportazione, aspetta il volo del giorno indicato al posto dei puntini. | «In transito» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Il volo è partito. | «In transito» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Atterrato. I pallet vengono smontati prima della dogana. | «In transito» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | All'aeroporto di arrivo, in attesa del ritiro e della dogana d'importazione. | «In dogana» |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Lo sdoganamento all'importazione è in corso. | «In dogana» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Sdoganato e partito su un camion. | «In transito» |
| `已交服务商 Delivered to service provider` | Passato al corriere che fa l'ultimo tratto. Non è la consegna a te. | «In transito» |

Le righe che JRT scrive solo in cinese compaiono in Peek con l'inglese che usano altri spedizionieri: `货物电子信息已经收到` come «The electronic cargo information has been received», `到达收货点` come «Arrived at the receiving point», `货物离开操作中心` come «Goods leave the operation center» e `快件操作完成` come «Shipment departed from facility». La riga dell'aeroporto con il volo prenotato, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, indica il giorno del volo in cinese e in inglese; Peek la mostra come «Goods delivered to airport», perché il volo deve ancora partire.

**Righe solo in inglese.** In Peek sono tutte «In transito», salvo dove indicato:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: in viaggio verso l'aeroporto.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: si sta prenotando un volo.
- `Flight is expected to take off on …` oppure `The flight is expected to depart on … and land on …`, con le date al posto dei puntini: il volo è prenotato.
- `Flight has flown`, `The flight has departed and is expected to … land`: è in volo.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (l'avviso di arrivo della compagnia aerea), `Dismantling and picking up goods in progress`: atterrato, prima della dogana.
- `Customs clearance of the cargo is in progress` («In dogana»), `Customs clearance completed`, poi `The goods have been handed over to the carrier`: passato al corriere che consegna.

Nelle date con il punto viene prima il mese: 11.12 è il 12 novembre, non l'11 dicembre.

**Le righe di DHL e di FedEx.** Verso l'Europa il tracking di JRT prosegue con le scansioni di DHL, quasi sempre in minuscolo:

| DHL scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `the instruction data for this shipment have been provided by the sender to dhl electronically` | DHL ha ricevuto i dati dell'etichetta. | «Etichetta creata» |
| `The shipment has been posted by the sender at the retail outlet` | JRT ha lasciato il pacco in una filiale Deutsche Post. | «In transito» |
| `pick-up was successful.` | DHL ha ritirato il pacco lasciato da JRT. | «In transito» |
| `the shipment has been processed in the parcel center of origin` | Smistato nel centro pacchi DHL di partenza. | «In transito» |
| `loaded to movement / tour vehicle`, `unloaded from movement / tour vehicle`, `movement / tour vehicle arrived` | Caricato su un mezzo, scaricato, mezzo arrivato. | «In transito» |
| `the shipment has been processed in the destination parcel center` | Smistato nel centro pacchi DHL vicino a te. | «In transito» |
| `not delivered-consignee could not be reached` | Consegna non riuscita: non c'era nessuno. | «Tentativo di consegna» |
| `held for consignee collection` | Il pacco ti aspetta in un punto di ritiro. | «Pronto per il ritiro» |
| `the shipment has been successfully delivered` | Consegnato. | «Consegnato» |
| `shipment will be returned to origin-not ordered` | Rifiutato dal destinatario perché non ordinato: torna indietro. | «Restituito al mittente» |

Dopo un reso sono «Restituito al mittente» le righe dei mezzi che finiscono con «(return to sender)», `returned shipment has been received by processing center` (il pacco è di nuovo al centro di JRT) e anche `the shipment has been successfully delivered`, che a quel punto è la consegna al mittente, non a te. Alcuni pacchi cominciano invece con `Shipment information sent to FedEx`: i dati dell'etichetta sono passati a FedEx, che fa l'ultimo tratto («Etichetta creata»).

Tutti gli orari del tracking di JRT sono ora di Pechino, anche quelli copiati da DHL: le 17:00 a Pechino sono le 10:00 in Italia con l'ora solare e le 11:00 con l'ora legale.

## Chi consegna i pacchi JRT in Italia

**Verso l'Europa** la cronologia di JRT finisce con DHL, a cui JRT affida il pacco in una filiale Deutsche Post. Nei dati di JRT però non ci sono né il numero DHL né il nome della linea, quindi Peek non può seguire il pacco sul tracking di DHL. Chiedi al venditore il numero DHL: con quello il tracking di DHL segue il pacco fino alla consegna (numeri e stati nella pagina [DHL Paket](carrier:dhl)).

**Con FedEx** la prima riga nomina FedEx, ma Peek non segue il pacco sul tracking di FedEx: chiedi al venditore il numero FedEx e seguilo su [FedEx](carrier:fedex).

**Senza DHL né FedEx nella cronologia** Peek non sa chi consegna il pacco: chiedi al venditore quale corriere te lo porta e con che numero. Tentativi di consegna, punti di ritiro e giacenza seguono le regole di quel corriere, non di JRT. Ti chiedono di pagare qualcosa per la dogana? Leggi [pacco fermo in dogana](guide:customs).

## Pacco JRT fermo o che non arriva

**Solo le prime righe.** Se vedi solo `货物电子信息已经收到`, `Shipment information received` o una riga dell'etichetta DHL o FedEx, il venditore ha prenotato la spedizione ma nessuno ha ancora scansionato il pacco. Se dopo qualche giorno non succede niente, chiedi al venditore se l'ha spedito davvero.

**In attesa del volo.** Le righe del volo indicano il giorno per cui è prenotato. Se quel giorno passa senza una riga di partenza, avvisa il venditore.

**Niente dopo `已交服务商`.** JRT ha finito la sua parte. Se il suo tracking non copia le scansioni di DHL, i passi successivi sono sul tracking di chi consegna: chiedi al venditore quel numero. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Nessun risultato.** Per un numero sconosciuto il tracking di JRT mostra un solo risultato, con il numero come riferimento e il resto vuoto. Peek mostra «In attesa del corriere» e continua a controllare. Confronta il numero con il messaggio del venditore, carattere per carattere.

**Consegnato, ma non è arrivato niente.** La riga di consegna è del corriere, non di JRT, anche quando JRT la copia da DHL: chiedi prima al corriere, poi avvisa il venditore. Più dettagli in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o rispedito al mittente.** Il venditore ha prenotato la spedizione con JRT, quindi chiedi a lui, o al marketplace, un rimborso o un nuovo invio. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## JRT contatti: telefono e assistenza clienti

Al 30 ottobre 2026 Peek non ha trovato contatti di JRT: oltre al tracking non conosce nessun sito dell'azienda, né un telefono, un'e-mail o un indirizzo per chi riceve. La spedizione l'ha prenotata il venditore: è a lui che devi chiedere del tuo ordine e, se non risponde, al marketplace.

## Tracciare un pacco JRT con Peek

[Peek](/) riconosce un numero JRT dalla forma (JRT, dieci cifre e MM, che nessun altro corriere del suo catalogo usa) e riconosce anche un link al tracking di JRT. Con un riferimento UP di sedici cifre mostra «Verifica dei servizi di tracciamento…», chiede al tracking di JRT se lo conosce e, se sì, sceglie JRT. Gli altri riferimenti non hanno una forma riconoscibile: incollali lo stesso, perché Peek segue più di 3.500 corrieri, poi premi «Cambia» e scegli JRT (lo trovi anche scrivendo 今日通 o Jinritong), oppure usa «Cambia corriere» su un pacco già aggiunto. Se hai usato un riferimento, il numero JRT compare poi sotto «JRT lo traccia con il numero». Peek però toglie i trattini, quindi un riferimento che li contiene non viene trovato: chiedi al venditore il numero JRT…MM.

Peek mostra lo stato, ricavato dalle righe e non dal riepilogo di JRT, ogni riga con il suo luogo e, quando JRT li indica, il nome del servizio e il Paese di destinazione. Legge gli orari sull'ora di Pechino e li mostra nel fuso orario del tuo dispositivo. Tiene da parte le righe scritte più di dodici ore prima dell'orario che riportano e quelle datate dopo il controllo. Lascia fuori nomi, indirizzo, CAP, peso, costi, firma e prova di consegna, e non indica una data di consegna prevista: JRT non la dà.

L'unico passaggio a un altro corriere che Peek ricava dai dati di JRT è verso la [Posta Svizzera](carrier:swiss-post), quando i dati nominano una linea e un numero della Posta Svizzera per un pacco diretto in Svizzera o nel Liechtenstein. Se il venditore ti dà il numero del corriere che consegna, aggiungilo come pacco a sé.

Peek controlla ogni 10 minuti di giorno, ogni una o due ore di notte e ogni ora quando JRT tace (dopo 12 ore ferme alle prime righe o due giorni senza novità). Per le notifiche scegli tra «Ogni scansione», «Passaggi importanti» e «Solo la consegna»: con «Passaggi importanti» ti avvisa per le righe di dogana di JRT e per quelle di DHL su tentativo, ritiro, consegna e reso, non per i voli né per `已交服务商`. Peek non può contattare JRT o il venditore al posto tuo.

## Domande sul tracking JRT

### Che cos'è JRT (今日通)?

È 厦门今日通商贸有限公司, Xiamen Jinritong, uno spedizioniere di Xiamen, in Cina. Le sue lettere di vettura sono JRT, dieci cifre e MM, e il suo tracking gira su Hualei (华磊), una piattaforma usata anche da altri spedizionieri cinesi.

### Perché JRT scrive «delivered» se il pacco non è arrivato?

È colpa delle traduzioni. «Cargo delivered to airport» vuol dire che JRT ha affidato il pacco all'aeroporto per il volo, e «Delivered to service provider» che l'ha passato al corriere che consegna: nessuna delle due è la consegna a te.

### Chi consegna i pacchi JRT in Italia?

Verso l'Europa la cronologia di JRT finisce con DHL, che fa l'ultimo tratto e di cui JRT copia le scansioni; alcuni pacchi passano invece a FedEx. Negli altri casi Peek non lo sa: chiedi al venditore quale corriere consegna e con che numero.

### A chi mi rivolgo se il pacco JRT non arriva?

Al venditore da cui hai comprato: è lui che ha prenotato la spedizione con JRT. Oltre al tracking, Peek non conosce siti né contatti di JRT.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome e iniziali dell'azienda, piattaforma Hualei, tracking su un indirizzo numerico in HTTP semplice, i suoi link e nessun link pubblicato, forme JRT…MM e UP, ricerca per numero o riferimento con i trattini, diciture delle righe e loro fasi, date mese.giorno, l'inglese delle righe solo in cinese, righe copiate da DHL (tentativo, ritiro, consegna, reso) e filiale Deutsche Post, riga di FedEx, nessun numero DHL nei dati, passaggio alla Posta Svizzera, ora di Pechino, risposta per un numero sconosciuto, cosa Peek mostra, tiene da parte e lascia fuori
:::
