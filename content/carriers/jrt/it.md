---
title: JRT tracking: numero JRT…MM, stati, dogana e DHL
description: Tracking JRT (今日通) per i pacchi da Xiamen: com'è fatto il numero JRT…MM, cosa vogliono dire le righe in cinese e in inglese e chi consegna in Italia.
slug: jrt-tracking
published: 2026-10-31
updated: 2026-11-09
---

JRT (今日通, Jinritong) è 厦门今日通商贸有限公司, uno spedizioniere di Xiamen, in Cina. Se un negozio cinese ha spedito il tuo ordine con JRT, ti avrà dato un numero che inizia con JRT e finisce con MM: incollalo nel box qui sopra. Il tracking di JRT segue il pacco dalla Cina al volo, alla dogana e poi al corriere che lo consegna: verso l'Europa la cronologia finisce con DHL, di cui copia le scansioni, tranne sulla linea di JRT per l'Irlanda con FedEx nel nome del prodotto, dove il pacco passa a FedEx, e su quelle per la Spagna e la Polonia, dove passa a CTT Express e a InPost.

## Numero di tracking JRT: com'è fatto

Le lettere di vettura di JRT sono JRT, dieci cifre e MM: 15 caratteri in tutto (JRT sono le iniziali di 今日通). Con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `JRT0000000000MM` | JRT, dieci cifre e MM: una lettera di vettura JRT. |
| `JRT000000000MM` | Solo nove cifre: non è un numero JRT. Controlla che non ne manchi una. |
| `JRTO0000000000MM` | Una lettera O dopo JRT: Peek lo legge come `JRT0000000000MM`, l'unica forma che il tracking di JRT riconosce. |
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
| `深圳集散中心 已收货` | Il centro di smistamento di JRT a Shenzhen ha ricevuto il pacco, dopo l'uscita dal centro operativo e prima del volo. Peek lo mostra in inglese: «Received at the Shenzhen distribution center». | «In transito» |
| `货物打包完成，已发往航司` | Imballato e in viaggio verso la compagnia aerea, prima del volo. Peek lo mostra in inglese: «Goods packed and sent to the airline». | «In transito» |
| `抵达航司已起飞` | Affidato alla compagnia aerea, e il volo è partito dall'aeroporto indicato nella riga. Peek mostra «Arrived at the airline, flight departed». | «In transito» |
| `货物已交机场,等待航班安排中 Cargo delivered to airport, pending flight arrangement` | Consegnato in aeroporto, aspetta un volo. «Delivered» qui vuol dire all'aeroporto, non a te. | «In transito» |
| `报关已放行等待…号航班起飞 Customs released,Awaiting departure on flight of the …` | Sdoganato all'esportazione, aspetta il volo del giorno indicato al posto dei puntini. | «In transito» |
| `航班起飞，以实际抵达为准 Flight departure subject to actual arrival time` | Il volo è partito. | «In transito» |
| `航班落地，拆板中 Flight landing, dismantling board in progress` | Atterrato. I pallet vengono smontati prima della dogana. | «In transito» |
| `机场内等待提货，等待清关 Waiting for cargo pickup and customs clearance at the airport.` | All'aeroporto di arrivo, in attesa del ritiro e della dogana d'importazione. | «In dogana» |
| `已提货，清关中 The goods have been picked up and are currently undergoing customs clearance` | Lo sdoganamento all'importazione è in corso. | «In dogana» |
| `清关完成，卡车发运 Customs clearance completed; truck dispatched.` | Sdoganato e partito su un camion. | «In transito» |
| `已交服务商 Delivered to service provider` | Passato al corriere che fa l'ultimo tratto. Non è la consegna a te. | «In transito» |

Sulle spedizioni verso l'Europa, le righe che JRT scrive solo in cinese compaiono in Peek con l'inglese che usano altri spedizionieri: `货物电子信息已经收到` come «The electronic cargo information has been received», `到达收货点` come «Arrived at the receiving point», `货物离开操作中心` come «Goods leave the operation center» e `快件操作完成` come «Shipment departed from facility». La riga dell'aeroporto con il volo prenotato, `货物已交机场,预计…号的航班 Goods delivered to airport, scheduled on flight of the …`, indica il giorno del volo in cinese e in inglese; Peek la mostra come «Goods delivered to airport», perché il volo deve ancora partire.

Più avanti nel viaggio verso l'Europa arrivano altre tre righe solo in cinese, che Peek mostra in inglese, tutte «In transito»: `航班已抵达` come «Flight has arrived» (l'atterraggio nell'aeroporto europeo indicato nella riga), `清关完成，中转德国中` come «Customs cleared, in transit to Germany» (sdoganato all'aeroporto di arrivo e in viaggio verso la Germania, dove passa al corriere che consegna) e `抵达德国，已交服务商` come «Arrived in Germany, handed to the delivering carrier» (affidato al corriere che consegna, per esempio DHL: non è la consegna a te).

**Via terra attraverso il Kazakistan.** Sulla linea stradale di JRT verso l'Europa il pacco lascia la Cina via terra, non in aereo, con quattro righe solo in cinese che Peek mostra in inglese, tutte «In transito»: `已装柜发车，待抵达新疆` come «Loaded into a container and on its way to Xinjiang» (dopo il centro di Shenzhen), `已抵达新疆，换装/报关，待出境` come «Arrived in Xinjiang for transloading and export declaration, waiting to leave China», `因哈萨克斯坦拥堵，外车入境新疆拥堵缓慢，在等待外车倒装后报关出境。` come «Congestion in Kazakhstan is slowing foreign trucks into Xinjiang; the parcel will be declared for export once it is reloaded onto one» (un'attesa alla frontiera, non un pacco perso) e `已出境，哈萨克斯坦阿拉木图` come «Left China, Almaty, Kazakhstan» (ha passato la frontiera ed è ad Almaty).

**Righe solo in inglese.** In Peek sono tutte «In transito», salvo dove indicato:

- `Shipment arrived at facility`, `Sending to the airport`, `The goods have been packed and sent to the airline`: in viaggio verso l'aeroporto.
- `Arriving at the airport - arranging flights`, `Arriving at the airline, in the process of booking a flight`: si sta prenotando un volo.
- `Flight is expected to take off on …` oppure `The flight is expected to depart on … and land on …`, con le date al posto dei puntini: il volo è prenotato.
- `Flight has flown`, `The flight has departed and is expected to … land`: è in volo.
- `Flight has arrived`, `The flight has arrived and is awaiting dismantling`, `The NOA has been received` (l'avviso di arrivo della compagnia aerea), `Dismantling and picking up goods in progress`: atterrato, prima della dogana.
- `Customs clearance of the cargo is in progress` («In dogana»), `Customs clearance completed` o `Customs clearance in destination country completed`, poi `The goods have been handed over to the carrier`: passato al corriere che consegna.
- `The cargo has been picked up and arrived at the warehouse，awaiting outbound shipment` (nel magazzino di JRT nel Paese di destinazione) e `It has been handed over to the service provider.` (affidato al corriere che consegna, per esempio FedEx): dopo la dogana, non è una prima scansione.

Nelle date con il punto viene prima il mese: 11.12 è il 12 novembre, non l'11 dicembre.

**Righe con la barra.** Su alcune spedizioni JRT unisce cinese e inglese con una barra, e in Peek sono tutte «In transito»: `预计…航班/Flight scheduled for …` (il volo è prenotato, con il giorno in tutte e due le parti al posto dei puntini), `航班已启运/Package in transit to destination country` (è in volo) e `航班已落地，拆板中/Package arrived at destination country` (atterrato, prima della dogana). Prima arriva `国内清关完成`: è lo sdoganamento all'esportazione in Cina, non la dogana del tuo Paese («In transito»).

**Le righe di DHL e di FedEx.** Verso l'Europa il tracking di JRT prosegue con le scansioni di DHL, quasi sempre in minuscolo:

| DHL scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `the instruction data for this shipment have been provided by the sender to dhl electronically` | DHL ha ricevuto i dati dell'etichetta. | «Etichetta creata» |
| `shipment in transit to dhl` | Il pacco sta andando a DHL, prima del volo. | «In transito» |
| `The shipment has been posted by the sender at the retail outlet` | JRT ha lasciato il pacco in una filiale Deutsche Post. | «In transito» |
| `pick-up was successful.` | DHL ha ritirato il pacco lasciato da JRT. | «In transito» |
| `the shipment has been processed in the parcel center of origin` | Smistato nel centro pacchi DHL di partenza. | «In transito» |
| `loaded to movement / tour vehicle`, `unloaded from movement / tour vehicle`, `movement / tour vehicle arrived` | Caricato su un mezzo, scaricato, mezzo arrivato. | «In transito» |
| `the shipment has been processed in the destination parcel center` | Smistato nel centro pacchi DHL vicino a te. | «In transito» |
| `not delivered-consignee could not be reached` | Consegna non riuscita: non c'era nessuno. | «Tentativo di consegna» |
| `not delivered-bad address` | Consegna non riuscita: il corriere non ha trovato l'indirizzo. | «Tentativo di consegna» |
| `held for consignee collection` | Il pacco ti aspetta in un punto di ritiro. | «Pronto per il ritiro» |
| `the shipment has been successfully delivered` | Consegnato. | «Consegnato» |
| `shipment will be returned to origin-not ordered` | Rifiutato dal destinatario perché non ordinato: torna indietro. | «Restituito al mittente» |
| `shipment will be returned to origin-bad address` | L'indirizzo non si trova: il pacco torna indietro. | «Restituito al mittente» |

Dopo un reso sono «Restituito al mittente» le righe dei mezzi che finiscono con «(return to sender)», `returned shipment has been received by processing center` (il pacco è di nuovo al centro di JRT) e anche `the shipment has been successfully delivered`, che a quel punto è la consegna al mittente, non a te. Alcuni pacchi cominciano invece con `Shipment information sent to FedEx`: i dati dell'etichetta sono passati a FedEx, che fa l'ultimo tratto («Etichetta creata»). Poi il tracking di JRT copia le scansioni di FedEx in inglese, come `Arrived at FedEx hub`, `Departed FedEx hub` e `On the way`, tutte «In transito».

**Le righe di J&T Express Messico.** Sulla linea di JRT per il Messico l'ultimo tratto lo fa J&T Express Messico: dopo la dogana il tracking di JRT copia le sue righe in spagnolo, con la località e la sede di J&T tra 【】 (qui 【…】), tutte «In transito» in Peek. `【…】【…】¡Recolección exitosa!. El mensajero de J&T asignado … recolectó tu envío.` vuol dire che il corriere di J&T, il cui nome è al posto dei puntini, ha ritirato il pacco già sdoganato: è un passaggio del viaggio, non la prima scansione del pacco né un ritiro da parte tua (sul tracking di JRT il ¡ può comparire come ?, e dopo può esserci il telefono di J&T). Poi arrivano `【…】Tu envío ha llegado a【…】` (arrivato in una sede di J&T) e `【…】Tu envío ha salido de【…】con destino a【…】` (partito da una sede di J&T verso la successiva).

Sulle spedizioni verso l'Europa le righe di JRT sono sull'ora di Pechino: le 17:00 a Pechino sono le 10:00 in Italia con l'ora solare e le 11:00 con l'ora legale. Le righe copiate da DHL sono invece sull'ora locale di DHL: quelle che cominciano con `The shipment …`, `The international shipment …` o `the instruction data …`, le righe dei mezzi, `pick-up was successful.`, `not delivered-consignee …`, `not delivered-bad address`, `held for consignee collection` e `shipment will be returned to origin …`. Se una di queste righe indica una città in Germania o nel Paese di destinazione, Peek la legge sull'ora di quella città. Altrimenti (una sigla di Paese accanto alla riga non basta) legge i dati dell'etichetta e le righe sul centro pacchi di origine, sul centro pacchi di esportazione e sul trasporto verso il Paese di destinazione sull'ora tedesca, e le altre sull'ora del Paese di destinazione (su quella tedesca se JRT non indica la destinazione). Sulla linea per il Messico le righe copiate da J&T Express Messico sono invece sull'ora di J&T a Città del Messico, 14 ore indietro rispetto a Pechino, e Peek le legge così; le righe di JRT restano sull'ora di Pechino.

## Chi consegna i pacchi JRT in Italia

**Verso l'Europa**, salvo sulla linea per l'Irlanda con FedEx (vedi sotto) e su quelle per la Spagna e la Polonia, che finiscono con CTT Express e con InPost, la cronologia di JRT finisce con DHL, a cui JRT affida il pacco in una filiale Deutsche Post. Quando JRT ha consegnato il pacco, nei suoi dati il numero DHL prende il posto di quello JRT, ma il nome della linea continua a mancare. Se Peek riconosce quel numero come DHL, mostra «Consegna con DHL» con il numero (o «Annunciato a DHL, non ancora ricevuto» finché DHL ha il pacco solo annunciato, senza una sua scansione) e passa al tracking di DHL appena DHL scansiona il pacco (vedi sotto). Altrimenti chiedi al venditore il numero DHL: con quello il tracking di DHL segue il pacco fino alla consegna (numeri e stati nella pagina [DHL Paket](carrier:dhl)).

**In Italia** i pacchi di DHL Paket partiti dalla Germania li consegna SDA, i cui servizi di corriere espresso sono passati a Poste Italiane, oppure DHL Express. Secondo DHL arrivano in 2-4 giorni lavorativi, con consegne dal lunedì al venerdì (in alcune zone anche il sabato) e un solo tentativo, dopo il quale il pacco si ritira in una filiale, dove resta in giacenza 15 giorni lavorativi. Più dettagli nella pagina DHL Paket.

**Dogana e costi.** Sulle linee aeree di JRT il pacco passa la dogana d'importazione nell'aeroporto dove atterra il volo (le righe «In dogana» qui sopra). Dal 1° novembre 2026, sugli acquisti online da fuori UE, si paga anche la tassa di gestione dell'Unione: 2 € per articolo, cioè per tipo di merce, non per pezzo. Chi te la chiede, insieme a IVA e dazi, lo spiega la guida [pacco fermo in dogana](guide:customs).

**Sulla linea per l'Irlanda con FedEx nel nome del prodotto** JRT passa il pacco a FedEx, e da quel momento nei suoi dati il numero FedEx prende il posto di quello JRT. Se quel numero ha una delle forme dei numeri FedEx, Peek mostra «Consegna con FedEx» con il numero (o «Annunciato a FedEx, non ancora ricevuto» finché FedEx ha il pacco solo annunciato) e passa al tracking di FedEx appena FedEx scansiona il pacco, come con DHL (vedi sotto). Se a consegnare è FedEx e Peek non mostra un numero FedEx, chiedilo al venditore e seguilo su [FedEx](carrier:fedex).

**Senza DHL, FedEx, CTT Express, InPost o J&T Express Messico nella cronologia** Peek non sa chi consegna il pacco: chiedi al venditore quale corriere te lo porta e con che numero. Tentativi di consegna, punti di ritiro e giacenza seguono le regole di quel corriere, non di JRT.

## Pacco JRT fermo o che non arriva

**Solo le prime righe.** Se vedi solo `货物电子信息已经收到`, `Shipment information received` o una riga dell'etichetta DHL o FedEx, il venditore ha prenotato la spedizione ma nessuno ha ancora scansionato il pacco. Se dopo qualche giorno non succede niente, chiedi al venditore se l'ha spedito davvero.

**In attesa del volo.** Le righe del volo indicano il giorno per cui è prenotato, e `航班延误，预计…号飞出 Flight delayed, estimated departure on …` un nuovo giorno. Se quel giorno passa senza una riga di partenza, avvisa il venditore.

**Niente dopo `已交服务商`.** JRT ha finito la sua parte. Se il suo tracking non copia le scansioni di DHL o di FedEx e Peek non mostra il numero DHL o FedEx, i passi successivi sono sul tracking di chi consegna: chiedi al venditore quel numero. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Nessun risultato.** Per un numero sconosciuto il tracking di JRT mostra un solo risultato, con il numero come riferimento e il resto vuoto. Peek mostra «In attesa del corriere» e continua a controllare. Confronta il numero con il messaggio del venditore, carattere per carattere.

**Indirizzo non trovato.** Dopo `not delivered-bad address` controlla l'indirizzo nell'ordine e avvisa subito il venditore. Se segue `shipment will be returned to origin-bad address`, il pacco sta tornando indietro: chiedi al venditore un rimborso o un nuovo invio.

**Consegnato, ma non è arrivato niente.** La riga di consegna è del corriere, non di JRT, anche quando JRT la copia da DHL: chiedi prima al corriere, poi avvisa il venditore. Più dettagli in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o rispedito al mittente.** Il venditore ha prenotato la spedizione con JRT, quindi chiedi a lui, o al marketplace, un rimborso o un nuovo invio. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## JRT contatti: telefono e assistenza clienti

Al 30 ottobre 2026 Peek non ha trovato contatti di JRT: oltre al tracking non conosce nessun sito dell'azienda, né un telefono, un'e-mail o un indirizzo per chi riceve. La spedizione l'ha prenotata il venditore: è a lui che devi chiedere del tuo ordine e, se non risponde, al marketplace.

## Tracciare un pacco JRT con Peek

[Peek](/) riconosce un numero JRT dalla forma (JRT, dieci cifre e MM, che nessun altro corriere del suo catalogo usa) e riconosce anche un link al tracking di JRT. Con un riferimento UP di sedici cifre mostra «Verifica dei servizi di tracciamento…», chiede al tracking di JRT se lo conosce e, se sì, sceglie JRT. Gli altri riferimenti non hanno una forma riconoscibile: incollali lo stesso, perché Peek segue più di 3.500 corrieri, poi premi «Cambia» e scegli JRT (lo trovi anche scrivendo 今日通 o Jinritong), oppure usa «Cambia corriere» su un pacco già aggiunto. Se hai usato un riferimento, il numero JRT compare poi sotto «JRT lo traccia con il numero», finché JRT non consegna il pacco e nei dati non resta il numero DHL o FedEx. Peek però toglie i trattini, quindi un riferimento che li contiene non viene trovato: chiedi al venditore il numero JRT…MM.

Peek mostra lo stato, ricavato dalle righe e non dal riepilogo di JRT, ogni riga con il suo luogo e, quando JRT lo indica, il Paese di destinazione. Legge gli orari sull'ora di Pechino (le righe di DHL descritte sopra sull'ora locale di DHL, le righe copiate da UPS e quelle con un luogo negli Stati Uniti sull'ora locale di quel luogo, quelle copiate da J&T Express Messico sull'ora di Città del Messico) e li mostra nel fuso orario del tuo dispositivo. Tiene da parte le righe scritte più di dodici ore prima dell'orario che riportano e quelle datate dopo il controllo. Lascia fuori nomi, indirizzo, CAP, peso, costi, firma e prova di consegna, e non indica una data di consegna prevista: JRT non la dà.

Verso l'Europa, quando i dati di JRT contengono un numero che Peek riconosce come DHL, Peek interroga anche DHL e passa al suo tracking appena DHL mostra una scansione recente almeno quanto l'ultima di JRT, tenendo quelle di JRT nella cronologia; fino ad allora resta sul tracking di JRT, con le scansioni di DHL che copia. Sulla linea per l'Irlanda con FedEx nel nome del prodotto Peek fa lo stesso con FedEx, quando i dati di JRT contengono un numero con una delle forme dei numeri FedEx. Peek passa il pacco anche alla [Posta Svizzera](carrier:swiss-post), quando i dati nominano una linea e un numero della Posta Svizzera per un pacco diretto in Svizzera o nel Liechtenstein. Se al posto del numero di JRT i dati riportano un altro numero, Peek interroga la posta del Paese di destinazione quando è un numero postale internazionale (due lettere, nove cifre, due lettere), altrimenti l'unico corriere alla cui forma di numero corrisponde con certezza, e passa al suo tracking allo stesso modo. Se il venditore ti dà il numero del corriere che consegna, aggiungilo come pacco a sé.

Peek controlla ogni 10 minuti e ogni ora quando JRT tace (dopo 12 ore ferme alle prime righe o due giorni senza novità). Per le notifiche scegli tra «Ogni scansione», «Passaggi importanti» e «Solo la consegna»: con «Passaggi importanti» ti avvisa per le righe di dogana di JRT e per quelle di DHL su tentativo, ritiro, consegna e reso, non per i voli né per `已交服务商`. Peek non può contattare JRT o il venditore al posto tuo.

## Domande sul tracking JRT

### Che cos'è JRT (今日通)?

È 厦门今日通商贸有限公司, Xiamen Jinritong, uno spedizioniere di Xiamen, in Cina. Le sue lettere di vettura sono JRT, dieci cifre e MM, e il suo tracking gira su Hualei (华磊), una piattaforma usata anche da altri spedizionieri cinesi.

### Perché JRT scrive «delivered» se il pacco non è arrivato?

È colpa delle traduzioni. «Cargo delivered to airport» vuol dire che JRT ha affidato il pacco all'aeroporto per il volo, e «Delivered to service provider» che l'ha passato al corriere che consegna: nessuna delle due è la consegna a te.

### Chi consegna i pacchi JRT in Italia?

Verso l'Europa la cronologia di JRT finisce con DHL, di cui JRT copia le scansioni: in Italia i pacchi di DHL Paket li consegna SDA, oggi Poste Italiane, oppure DHL Express. Alcuni pacchi passano invece a FedEx, come quelli della linea di JRT per l'Irlanda con FedEx nel nome del prodotto, o a CTT Express e a InPost, come quelli delle sue linee per la Spagna e la Polonia. Negli altri casi Peek non lo sa: chiedi al venditore quale corriere consegna e con che numero.

### A chi mi rivolgo se il pacco JRT non arriva?

Al venditore da cui hai comprato: è lui che ha prenotato la spedizione con JRT. Oltre al tracking, Peek non conosce siti né contatti di JRT.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome e iniziali dell'azienda, piattaforma Hualei, tracking su un indirizzo numerico in HTTP semplice, i suoi link e nessun link pubblicato, forme JRT…MM e UP, una O dopo JRT letta come JRT, ricerca per numero o riferimento con i trattini, diciture delle righe e loro fasi, date mese.giorno, l'inglese delle righe solo in cinese, le righe solo in cinese verso l'Europa dall'atterraggio al passaggio in Germania, righe copiate da DHL (tentativo, ritiro, consegna, reso) e filiale Deutsche Post, riga di FedEx e sue scansioni copiate, il numero DHL o FedEx nei dati dopo la consegna, il passaggio a DHL e, sulla linea per l'Irlanda, a FedEx, passaggio alla Posta Svizzera, ora di Pechino e ora locale di DHL per le sue righe copiate, secondo la città indicata, risposta per un numero sconosciuto, cosa Peek mostra, tiene da parte e lascia fuori; righe DHL con indirizzo non trovato, linea stradale dallo Xinjiang al Kazakistan, volo in ritardo; righe copiate da J&T Express Messico sulla linea per il Messico e la loro ora di Città del Messico, sdoganamento completato a destinazione (9 novembre)
- [DHL: Paket nach Italien versenden (in tedesco)](https://www.dhl.de/de/privatkunden/pakete-versenden/weltweit-versenden/land/italien.html) – consegna di SDA, un tentativo, ritiro in filiale, giacenza di 15 giorni lavorativi, dal lunedì al venerdì e in alcune zone il sabato, 2-4 giorni lavorativi
- [DHL: DHL Paket International – Informationen zu Destinationen & Zielgebieten (in tedesco)](https://www.dhl.de/dam/jcr:75e9d96c-e4f1-4282-9b80-a4ee7f334fcf/dhl-paket-international-infoblatt-destinationen-de-092026.pdf) – partner di consegna in Italia: SDA (Poste Italiane) o DHL Express
- [SDA: Home](https://www.sda.it/wps/portal/sdait.home/) – servizi di corriere espresso ex SDA passati a Poste Italiane
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tassa di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Customs Guidance on EUR 3 customs duty (in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tassa di gestione di 2 € per articolo, cioè per voce della dichiarazione doganale, qualunque sia il numero di pezzi
:::
