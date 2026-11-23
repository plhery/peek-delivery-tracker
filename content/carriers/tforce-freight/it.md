---
title: Tracciamento TForce Freight: numeri PRO, stati e reclami
description: Traccia una spedizione TForce Freight in USA o Canada: numero PRO, significato degli stati, consegne a domicilio e reclami merci.
slug: tracciamento-tforce-freight
published: 2026-11-22
updated: 2026-11-22
---

TForce Freight è un vettore di trasporto merci LTL (less-than-truckload): movimenta pallet e spedizioni pesanti tra i propri centri servizi negli Stati Uniti e in tutto il Canada, raggiungendo anche oltre 600 località in Messico. Se un ordine voluminoso o una spedizione merci arriva con TForce, tracciala con il numero PRO fornito dal negozio o dall'azienda che ha prenotato il trasporto, incollandolo qui sopra. Il tracker ufficiale è nella pagina Tracking di www.tforcefreight.com.

## Numero di tracciamento TForce Freight: il numero PRO

La pagina di tracciamento di TForce cerca spedizioni per numero PRO oppure per polizza di carico (BOL) o numero d'ordine d'acquisto (PO) con un intervallo di date di spedizione. Peek traccia per PRO (cifre inventate):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `482917363` | Il PRO: nove cifre. L'ultima è una cifra di controllo, pari al resto della divisione delle prime otto per 7, quindi sempre tra 0 e 6. |
| `48291736` | Un PRO senza lo 0 iniziale. Il tracker TForce aggiunge lo 0, e lo stesso fa Peek una volta scelto TForce Freight. |
| `2205482917363` | Un numero PARS usato dagli spedizionieri doganali per le merci verso il Canada: 2205 seguito dal PRO. Incollalo intero: Peek interroga TForce usando il PRO al suo interno. |

Un PRO di nove cifre che termina con 7, 8 o 9 contiene un errore di battitura. Il catalogo di Peek riconosce TForce Freight anche come UPS Freight e TForce.

**Dove trovarlo.** Nell'e-mail di spedizione del negozio o nella pagina dell'ordine; per merci tra aziende, chiedi al mittente. Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Stesso formato, altri corrieri.** Nove cifre con la stessa cifra di controllo corrispondono anche a [TNT](carrier:tnt) e XPO. TForce Logistics (TForce Final Mile), i cui numeri iniziano con `TF`, è un corriere separato su Peek. Se l'e-mail nomina uno di questi, scegli quel corriere. Di più in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di TForce Freight

Il tracker di TForce mostra uno stato accanto al PRO, come `In Transit` o `Delivered`, seguito dalla cronologia dal più recente al più vecchio, ciascuna riga con città e Stato del centro servizi, data e ora. Le righe non hanno un codice: Peek legge il testo. Nell'ordine abituale:

| TForce Freight indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | L'autista ha ritirato la merce dal mittente. | «Preso in carico» |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | È presso il centro servizi di partenza. | «In transito» |
| `DEPARTURE` | Ha lasciato un centro servizi. | «In transito» |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | È arrivato in un centro servizi intermedio o in quello incaricato della consegna. | «In transito» |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | TForce ha inviato un'e-mail al destinatario per fissare un appuntamento di consegna. Rispondi. | «In transito» |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | È stato concordato un giorno di consegna. A volte appare dopo la consegna. | «In transito» |
| `OUT FOR DELIVERY` | Sul camion per la consegna. | «In consegna» |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Non consegnata in questo giro. Ogni riga che inizia con `Shipment not delivered.` segnala un tentativo fallito seguito dal motivo. | «Tentativo di consegna» |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Consegnata. La pagina TForce mostra chi ha firmato. | «Consegnato» |

Uno stato `Delivered` accanto al PRO appare sempre come «Consegnato»; altrimenti decide la riga più recente riconosciuta da Peek e le altre compaiono così come le scrive TForce. Il sito TForce può anche inviare aggiornamenti via e-mail. Vedi [significato degli stati di tracciamento](guide:tracking-statuses).

## Come consegna TForce Freight

TForce trasporta la merce al centro servizi della tua zona e la consegna via camion. Il tracker mostra una data stimata di consegna, che Peek mantiene finché la spedizione non viene consegnata, restituita o superata la data. Il codice di Peek non passa la spedizione a un altro corriere.

Il regolamento tariffario di TForce (LTL Rules Tariff UPGF 102-T, in vigore dal 17 agosto 2026) definisce le regole di consegna:

- **Abitazioni.** Dove i camion possono accedere legalmente, TForce deve prima concordare data e orario indicativo con te o con il mittente. Un'attività svolta in casa conta come abitazione. La consegna avviene all'ingresso al piano terra, garage o posto auto coperto, oppure ritiri la merce al terminal. La consegna residenziale è un servizio extra addebitato a chi lo ha richiesto o paga il trasporto.
- **A bordo camion.** La consegna standard prevede lo scarico accanto al camion. Consegna all'interno e sponda idraulica costano extra: chiedi a chi ha prenotato il trasporto se sono inclusi.
- **Avviso di arrivo.** Quando richiesto nei documenti di spedizione, TForce avvisa il destinatario entro il giorno lavorativo successivo all'arrivo al centro di consegna, di norma via telefono o e-mail.
- **Non consegnata.** Se la merce viene rifiutata o il destinatario non è reperibile, TForce avvisa il mittente del deposito e possono scattare costi di giacenza. Un nuovo tentativo è a pagamento salvo ritiro presso il centro servizi; a domicilio solo se hai mancato un appuntamento concordato.

**Verso il Canada.** Come spiega la pagina Canada di TForce, le merci dagli USA effettuano lo sdoganamento alla frontiera tramite un agente doganale. Le merci non sdoganate attendono in un deposito doganale a destinazione con costi di giacenza. L'importatore registrato paga compenso dell'agente, dazi e imposte.

## Spedizione TForce Freight non trovata, in ritardo, danneggiata o smarrita

**Non trovata.** Per un PRO sconosciuto, il tracker TForce mostra `Not Available` e `Please check ProNumber` con il numero. Peek visualizza «In attesa di TForce Freight» se hai scelto TForce Freight, altrimenti «Nessuna cronologia di tracciamento per ora», e ritenta in seguito. Controlla ogni cifra e chiedi al mittente se TForce ha già la merce.

**Cifra errata.** Un PRO con cifra di controllo errata non appartiene a TForce e Peek non interroga mai TForce. Se selezioni comunque TForce Freight, Peek indica che «non emette numeri come questo» e la spedizione mostra «Controlla il numero di tracciamento».

**Nessun aggiornamento.** Dopo quattro giorni senza scansioni su una spedizione «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da…», ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a TForce Freight.» e propone «Chiedi aiuto a TForce Freight». Chiedi al negozio o al mittente. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Mancata consegna.** Dopo una riga `Shipment not delivered.`, Peek segnala la spedizione («Controlla le istruzioni del corriere»). Chiama TForce o chiedi a chi ha prenotato il trasporto di fissare una nuova consegna. Vedi [consegne mancate](guide:missed-delivery).

**Mancanze o danni alla consegna.** La pagina Cargo Claims Information di TForce raccomanda di ispezionare sempre la merce e avvisare subito l'autista:

- Conta i colli rispetto alla ricevuta di consegna e apri le scatole danneggiate con l'autista.
- Annota esattamente cosa manca o è danneggiato sia sulla tua copia sia su quella di TForce.
- Non rifiutare la spedizione a meno che il danno non abbia reso la merce priva di valore.
- Chiedi al referente Over, Short & Damage (OS&D) del tuo centro servizi locale di rintracciare i colli mancanti.

**Danni scoperti dopo.** Segnalali entro 5 giorni lavorativi dalla consegna e chiedi un'ispezione lasciando l'imballo intatto. Il verbale d'ispezione non costituisce reclamo.

**Presentare reclamo.** Un reclamo è una richiesta scritta di risarcimento da parte del proprietario della spedizione. Per acquisti online inizia dal negozio, titolare della prenotazione. Regole di TForce:

- Presentalo entro nove mesi dalla consegna, o dalla data di spedizione se non è arrivato nulla.
- Usa lo Standard Claim Form di TForce o una lettera con spedizione e importo, allegando fattura del fornitore, fattura di trasporto, polizza di carico ed eventuali fatture di riparazione. Spedisci per posta o fax (sotto), non entrambi.
- TForce conferma la ricezione entro 30 giorni. La responsabilità è limitata a 25,00 $ per libbra e collo (meno per alcuni beni), salvo copertura extra acquistata sulla polizza di carico.

Se risulta consegnato ma non è arrivato nulla, vedi [consegnato ma non ricevuto](guide:delivered-not-received). Altri dettagli in [pacco danneggiato](guide:damaged-parcel) e [pacco smarrito](guide:lost-parcel).

## Servizio clienti e contatti TForce Freight

Recapiti pubblicati su www.tforcefreight.com (pagine Tracking, Fact Sheet, Rules Tariffs con LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us con Email TForce Freight Customer Support e Service Information):

- **Servizio clienti:** (800) 333-7400, numero verde da USA, Canada, Guam, Porto Rico e Isole Vergini americane, per tracciamento, prova di consegna e problemi. Da altri paesi: (804) 231-8555.
- **E-mail:** modulo Email TForce Freight Customer Support accessibile dalla pagina Contact Us di TForce, con campo per il PRO.
- **Ufficio reclami:** (888) 653-9720 per conoscere lo stato di una pratica. I reclami vanno spediti a Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, oppure via fax al (866) 580-1944.
- **Il tuo centro servizi:** Service Center Directory, sotto Service Information su www.tforcefreight.com.

Il negozio o il mittente è spesso più rapido perché detiene il contratto con TForce.

## Tracciare una spedizione TForce Freight con Peek

Il formato PRO non individua in modo univoco TForce: nove cifre valgono anche per [TNT](carrier:tnt), XPO e altri corrieri. Incollalo comunque: Peek segue oltre 4.500 corrieri. Mentre compare «Verifica dei servizi di tracciamento…», Peek interroga i corrieri compatibili; se solo TForce riconosce la spedizione, mostra il suo nome con «ha questo pacco». Per un numero PARS (`2205` + PRO), Peek interroga TForce insieme a FAN Courier; per un PRO a otto cifre senza zero iniziale, scegli TForce Freight con «Cambia» sotto «Compatibili con questo numero» (o usa «Cambia corriere» in seguito). Un link al tracker TForce (`proNumbers=`) o al tracker UPS Freight (`TrackProNumber=`) riconosce subito TForce («Corriere rilevato»).

Peek interroga TForce con il solo PRO. Mostra stato, ogni riga con città e Stato, giorno stimato di consegna, paese di destinazione sulla mappa, peso nelle tue unità e, solo a te, la località del destinatario sulla mappa e il suo nome in «Destinatario»; omette mittente, appuntamento, numeri BOL e PO, numero di colli, rimorchi, codici filiale e documento di prova di consegna. «Apri il sito TForce Freight» apre il numero (o il PRO estratto da un PARS) sul sito TForce. Se il tracker non risponde o chiede un CAPTCHA, Peek può interrogare i [servizi di tracciamento](guide:universal-tracker).

TForce indica ogni riga nell'ora locale del centro servizi, senza fuso orario. Peek la legge sull'orologio della città — oppure dello Stato o provincia, di una riga adiacente o della destinazione, e in mancanza sull'ora della costa orientale USA (Eastern Time) — mostrandola nel tuo fuso orario («Orari nel tuo fuso orario:»).

Peek interroga direttamente TForce fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 sull'orologio dell'ultima scansione (Eastern Time in mancanza di luogo) del giorno in cui TForce la scansiona in consegna, mentre la spedizione è aperta in Peek o nel giorno di consegna con le notifiche attive. Dopo due giorni senza novità (12 ore mentre mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare TForce, il negozio o il mittente per te né aprire reclami.

## Domande frequenti su TForce Freight

### Che cos'è un numero PRO TForce Freight?

Il numero di tracciamento di TForce: nove cifre, di cui l'ultima è una cifra di controllo da 0 a 6. Te lo fornisce il negozio o l'azienda che ha prenotato il trasporto.

### Posso tracciare TForce Freight senza numero PRO?

Sul sito di TForce sì: con polizza di carico (BOL) o numero d'ordine d'acquisto (PO) e un intervallo di date di spedizione. Peek traccia solo con il PRO.

### TForce Freight consegna a domicilio?

Sì, dove i suoi camion possono accedere legalmente, con un costo extra. TForce concorda prima data e fascia approssimativa con te o con il mittente e consegna all'ingresso al piano terra, garage o tettoia. Anche consegna interna e sponda idraulica hanno costi extra.

### Quanto tempo ho per presentare un reclamo a TForce Freight?

Nove mesi dalla consegna, o dalla data di spedizione se non è arrivato nulla. Segnala eventuali danni scoperti dopo la consegna entro 5 giorni lavorativi.

### Qual è il numero del servizio clienti TForce Freight?

(800) 333-7400, gratuito da Stati Uniti e Canada. Dall'estero: (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – nomi, formati PRO, link, testi e fasi, risposta non trovato, dati letti da Peek, orari
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – corrieri con stesso formato, nessun passaggio ad altro corriere, servizi di tracciamento di riserva
:::
