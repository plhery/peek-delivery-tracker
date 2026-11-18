---
title: Tracciamento Speedaf Express: numero, stati e contatti
description: Traccia un pacco Speedaf Express dalla Cina verso Svizzera, Africa o Medio Oriente: formato del numero, stati di spedizione, dogana e contatti.
slug: tracciamento-speedaf
published: 2026-11-18
updated: 2026-11-18
---

Speedaf Express (速达非) è una rete di corrieri fondata nel 2019 che spedisce per via aerea i pacchi dai propri magazzini in Cina verso Africa, Medio Oriente, Pakistan e alcuni Paesi europei, curando direttamente la consegna finale. Nell'area italofona compare nell'elenco Paesi di Speedaf solo la Svizzera: né l'Italia né San Marino sono serviti. Per seguire una spedizione, inserisci il numero sotto «Track» su [speedaf.com](https://www.speedaf.com/cn-en/send-parcel), nell'app clienti di Speedaf oppure nel riquadro qui sopra. Il codice si trova nei dettagli di spedizione dell'ordine.

## Numero di tracciamento Speedaf: formato ed esempi

Un numero Speedaf inizia con la sigla di due lettere del Paese di destinazione: `NG` per la Nigeria, `KE` per il Kenya o `PK` per il Pakistan (per la Svizzera Peek non ha un formato numerico dedicato). Si incontrano due schemi principali, qui con cifre inventate:

| Esempio | Significato |
| --- | --- |
| `NG482910573R` | Sigla del Paese, 9 cifre e una lettera finale, di solito `R` e talvolta `E`. |
| `KE482910573649` | Sigla del Paese seguita da 12 cifre. |
| `PK482910573649T` | Lo stesso schema a 12 cifre chiuso da una `T`. |
| `LP48291057364918` | Non è un codice Speedaf: è il riferimento [Cainiao](carrier:cainiao) di un ordine AliExpress. |

Il tracciamento su speedaf.com accetta fino a 20 numeri alla volta («Up to 20 numbers»), uno per riga. Digitali in maiuscolo: sul sito ufficiale le minuscole risultano sconosciute. Se non trovi il codice, leggi [dove trovare il numero di tracciamento](guide:find-tracking-number) o [ordini AliExpress](guide:aliexpress-orders).

## Cosa significano gli stati di tracciamento Speedaf

Speedaf registra le scansioni in inglese, altrimenti in cinese e talvolta anche nella lingua del Paese di destinazione. Le sedi compaiono tra parentesi quadre piene come 【queste】 all'interno della riga. Nell'ordine in cui si presentano per un pacco partito dalla Cina:

| Speedaf scrive | Peek mostra | Cosa significa per te |
| --- | --- | --- |
| `processing, order has been inbounded` | Preso in carico | Il magazzino cinese di Speedaf indicato subito prima ha preso in carico il pacco. |
| `processing assembling operation`, `processing, order has been outbounded` | In transito | Inserito nel sacco e uscito dal magazzino. |
| `clearance in progress` | In dogana | Dichiarazione doganale di esportazione in Cina. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | In transito | Consegnato in aeroporto, decollato e atterrato nel Paese di destinazione. |
| `Clearance handover`, `Customs clearance in progress` | In dogana | Preso in consegna dall'aeroporto e in sdoganamento nel Paese di destinazione. |
| `pending Tax` | In dogana | Sono dovuti dazi o imposte prima dello svincolo doganale (vedi sotto). |
| `Customs clearance tax paid` | In dogana | Tasse pagate; la pratica doganale prosegue. |
| `Customs Clearance Completed` | In transito | La dogana ha svincolato la spedizione. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | In transito | Arrivato nel magazzino centrale o centro di distribuzione Speedaf del Paese di arrivo. |
| `Received in` | In transito | Ricevuto nella prima sede Speedaf del Paese di arrivo, non una nuova partenza. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | In transito | In movimento tra gli hub di Speedaf e la filiale che effettua la consegna. |
| `scanned for delivery` | In consegna | Un corriere lo ha preso in carico per il giro di consegna. |
| `Issue Parcel Reason【Rescheduled delivery】` | Tentativo di consegna | Consegna spostata al giorno indicato tra parentesi subito dopo. |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | Problema segnalato | Il pacco è stato rifiutato alla consegna. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | In dogana | Il corriere è rientrato perché i dazi non erano ancora stati pagati. |
| `Please come to collect with the Pick-Up Code` | Pronto per il ritiro | Ti aspetta nella sede Speedaf indicata prima, da ritirare con il codice di ritiro. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | Consegnato | Consegnato o ritirato; la riga può riportare il punto di ritiro. |
| `Transported to the warehouse，【Return Overdue】` | Problema segnalato | Sta rientrando verso il magazzino di Speedaf. |
| `Warehouse received` | Restituito al mittente | Il reso è rientrato nel magazzino di Speedaf. |

Un semplice `In Transit` può nascondere una giacenza interna: il codice associato indica un pacco trattenuto per verifica d'inventario. `Products not for personal use` e `Clearance Exception` segnalano una richiesta di chiarimenti o un blocco in dogana; `Long Time Tax No Paid,Email To Sender` indica che i dazi sono rimasti insoluti a lungo e Speedaf sta scrivendo al mittente. In cinese compaiono soprattutto `进行处理，订单已操作入库` («Preso in carico»), `清关中` («In dogana»), `已做派件扫描` («In consegna») e `包裹【已签收】` («Consegnato»).

## Consegna Speedaf: domicilio, punti di ritiro e tentativi

Speedaf impiega propri corrieri per l'ultimo miglio, con consegna a domicilio e pagamento alla consegna (COD). La pagina «Look for Service Site» mostra le sue sedi su mappa.

Le regole di recapito dipendono dalle condizioni di ciascun Paese, scritte per il mittente; quelle di Nigeria ed Emirati Arabi Uniti disciplinano le spedizioni nazionali locali:

- **Emirati Arabi Uniti:** ogni spedizione comprende 3 tentativi di consegna gratuiti («Every shipment will have 3 free delivery attempts»); se non va a buon fine, torna al mittente.
- **Kenya, Uganda e Pakistan:** il pacco torna al mittente se non riesce a essere consegnato al terzo tentativo («cannot be delivered successfully upon a third attempt»), oppure se rifiuti il pacco o gli oneri dovuti. Tutti e tre i testi precisano che Speedaf consegna all'indirizzo indicato, ma non necessariamente in mano al destinatario nominato («but not necessarily to the named receiver in person»).
- **Nigeria ed Emirati Arabi Uniti:** un invio è considerato non consegnabile se rifiuti il pacco o il contrassegno, se l'indirizzo è errato o fuori copertura, o se la nuova data richiesta supera il tempo di giacenza di Speedaf («exceeds Speedaf holding time»). Nessuno dei due testi specifica quanti giorni duri tale giacenza.

Speedaf non pubblica una scadenza generale per il ritiro presso le proprie sedi. Se non eri presente al passaggio del corriere, consulta [consegna mancata: cosa fare](guide:missed-delivery).

## Dazi doganali Speedaf: pending Tax

Se sono dovuti dazi o imposte, il tracciamento si ferma su `pending Tax`. Per le spedizioni dirette in **Sudafrica**, la pagina di tracciamento di Speedaf mostra accanto alla lettera di vettura il link «I want to pay taxes», sostituito da «Taxes paid» una volta saldato l'importo. Per gli altri Paesi il tracciamento di Speedaf non mostra questo link di pagamento.

Se un SMS o un'e-mail ti chiede di pagare oneri, confronta prima lo stato direttamente su speedaf.com: leggi [SMS truffa sui pacchi](guide:delivery-scam-texts) e [pacco bloccato in dogana](guide:customs).

## Pacco Speedaf in ritardo, smarrito o segnato consegnato ma non ricevuto

**Tracciamento fermo.** Se la cronologia non avanza da giorni, vedi [tracciare un pacco dalla Cina](guide:tracking-from-china) e [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Chi può aprire un reclamo.** Nelle condizioni per **Kenya, Uganda e Pakistan**, il reclamo intercorre solo tra il mittente e Speedaf («The claim is only between the shipper and SPEEDAF»); in **Nigeria ed Emirati Arabi Uniti**, i reclami vengono liquidati esclusivamente con il mittente («Claims will only be settled with the Shipper.»). Per un acquisto online il mittente è il negozio: chiedi rimborso o nuova spedizione al venditore o al marketplace.

**Avvisa subito il venditore.** Le condizioni nazionali di Speedaf fissano termini stretti:

- **Pacco danneggiato:** per iscritto entro 7 giorni di calendario dalla consegna in **Kenya e Uganda**, entro 72 ore in **Nigeria ed Emirati Arabi Uniti**, entro 48 ore in **Arabia Saudita**.
- **Consegnato ma non ricevuto:** contestazione della prova di consegna entro 5 giorni di calendario in **Nigeria ed Emirati Arabi Uniti**, oppure entro 5 giorni lavorativi in **Pakistan**.
- **Smarrito:** in **Nigeria ed Emirati Arabi Uniti**, reclamo sul tracciamento entro 14 giorni di calendario dal ritiro. In assenza di scansioni per oltre 24 ore, Speedaf cerca il pacco per 7 giorni lavorativi e lo dichiara smarrito se non viene ritrovato.

Il tracciamento di Speedaf riporta chi ha firmato alla consegna: controllalo per primo. Altri consigli in [consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Contatti Speedaf: telefono e assistenza clienti

Ogni sito nazionale di Speedaf elenca i propri recapiti a piè di pagina o tramite il selettore del Paese (per Svizzera, Irlanda e Zambia scegli il Paese e poi «English»; per la Svizzera il sito è disponibile in inglese, cinese e francese, non in italiano). Gli orari sono nell'ora locale di ciascun Paese:

| Paese | Telefono | E-mail | Orari |
| --- | --- | --- | --- |
| Svizzera | +41 798559737, WhatsApp +41 786030610 | ch.info@speedaf.com | lun.-ven. 8:00-18:30 |
| Nigeria | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (anche WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | lun.-ven. 8:00-17:00, sab. 8:00-14:00 |
| Ghana | +233 30 824 9100 | gh.info@speedaf.com | lun.-sab. 8:30-17:30 |
| Kenya | +254 741 000 666, +254 741 000 888, WhatsApp +254 741000666 | kenya.info@speedaf.com | lun.-ven. 8:00-18:00, sab. 8:00-14:00 |
| Uganda | +256 709 198 795 | ug.info@speedaf.com | lun.-ven. 9:00-17:00, sab. 9:00-14:00 |
| Zambia | +260 971269390 | zm.info@speedaf.com | lun.-ven. 7:45-17:15, sab. 8:00-13:00 |
| Sudafrica | +27 108247184, +27 726393849, WhatsApp +27 664562613 | cs.southafrica@speedaf.com | non indicati |
| Pakistan | +92 311 118 7654, WhatsApp +92 3450010817 | pk.info@speedaf.com | lun.-dom. 9:00-18:00 |
| Emirati Arabi Uniti | +971 800 178 | ae.info@speedaf.com | lun.-sab. 9:00-18:00 |
| Arabia Saudita | nessun telefono | sa.info@speedaf.com | non indicati |
| Irlanda | +35312633068, WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | lun.-ven. 8:30-17:30 |
| Egitto | +20 15818, +20 1050383370 | eg.info@speedaf.com | sab.-gio. 9:00-18:00 |
| Marocco | +212 522 100 888 | ma.info@speedaf.com | lun.-ven. 9:00-18:00 |
| Cina | +86 400 888 0975 | info@speedaf.com | lun.-ven. 9:30-18:30 |

L'Help Center di Speedaf riporta inoltre un numero per gli altri Paesi presenti in elenco: Kazakistan `+7 778 371 9358`, Mauritius `4687316`, Maldive `+960 7678788`, Serbia `+41 798559737` (lo stesso recapito della Svizzera), Montenegro `+382 60021040` e Albania `+355 45620111`. Ogni sito nazionale mette a disposizione anche un modulo sotto «Customer Support».

## Tracciare un pacco Speedaf con Peek

[Peek](/) riconosce un codice Speedaf direttamente dal formato quando inizia con `NG`, `GH`, `KE`, `UG`, `RW`, `ZM`, `ZA`, `EG`, `MA`, `SA`, `AE`, `PK`, `KZ`, `MU`, `RE`, `MV` o `TR`, seguito da 9 cifre e da `R` o `E`. Se dopo una di queste sigle o dopo `IE` (Irlanda) ci sono invece 12 cifre (anche con `T` finale), il formato da solo non basta: Peek interroga Speedaf mostrando «Verifica dei servizi di tracciamento…», seguito da «Speedaf ha questo pacco.» se Speedaf conosce il numero. Peek non ha alcun formato numerico per Svizzera, Albania, Montenegro o Serbia: per questi Paesi e per qualsiasi codice fuori da entrambi gli schemi, seleziona «Speedaf» tramite «Cambia». Un link di speedaf.com contenente `tradeNo=` viene letto come Speedaf qualunque sia il codice. Peek converte automaticamente il numero in maiuscolo, quindi puoi incollarlo anche in minuscolo.

Peek legge direttamente il tracciamento di Speedaf: stato, cronologia, Paese di destinazione e ora di consegna. Ogni scansione porta l'orario locale della sede che l'ha registrata (UTC+8 nei magazzini cinesi, poi il fuso del Paese di arrivo) e viene mostrata nel fuso orario del tuo dispositivo; su peektracker.com e nell'app per iPhone una riga sotto la cronologia indica questo fuso («Orari nel tuo fuso orario: …»). Poiché le sedi compaiono solo nel testo delle righe, non c'è mappa e Speedaf non fornisce una data di consegna prevista. Se Speedaf conosce il numero ma non espone ancora scansioni, Peek continua a verificare mostrando «In attesa di Speedaf» su peektracker.com, oppure «In attesa della prima scansione di Speedaf» per un codice a 12 cifre lasciato in automatico. Se un pacco assegnato a Speedaf resta ancora privo di scansioni dopo due giorni e nessun'altra fonte risponde, peektracker.com mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a controllarlo una volta al giorno, poi una volta a settimana trascorso sette giorni dall'aggiunta.

Le righe compaiono come scritte da Speedaf, in inglese o altrimenti in cinese, senza il nome del corriere, dell'addetto alla scansione e senza numeri di telefono. Il nome di chi ha firmato alla consegna resta visibile nella scansione finale per chi ha aggiunto il pacco, mentre viene nascosto su un link condiviso (dove la consegna appare come `Delivered`). Peek esclude inoltre foto della prova di consegna, link per il pagamento dei dazi e conteggio dei colli, e segue solo la lettera di vettura inserita, non altri colli della stessa spedizione.

Finché tieni aperto il pacco, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 locali nel giorno in cui lo stato passa a «In consegna», finché risponde direttamente il tracciamento di Speedaf (tramite i servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno della consegna a pacco aperto o con notifiche attive). Fuori da questi casi, e dopo due giorni senza novità — o dopo 12 ore se il pacco è ancora su «Etichetta creata» o «Preso in carico» —, Peek controlla ogni ora. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può pagare dazi, riprogrammare una consegna né contattare Speedaf o il negozio al posto tuo.

## Domande frequenti sul tracciamento Speedaf

### Speedaf Express è affidabile?

Sì. Speedaf è una società logistica fondata nel 2019, con ZTO tra gli azionisti originari, che gestisce il proprio tracciamento su speedaf.com e una rete diretta di corrieri per l'ultimo miglio in quelli che definisce mercati emergenti.

### In quali Paesi consegna Speedaf?

Oltre alla Cina, l'elenco Paesi di Speedaf comprende Egitto, Sudafrica, Marocco, Emirati Arabi Uniti, Ghana, Nigeria, Kenya, Uganda, Arabia Saudita, Pakistan, Kazakistan, Mauritius, Maldive, Svizzera, Montenegro, Albania, Irlanda, Serbia e Zambia. L'Italia non ne fa parte; Peek riconosce anche i codici con prefisso `RE` (Riunione), `RW` (Ruanda) e `TR` (Turchia).

### Quanto tempo impiega una spedizione Speedaf?

Nella pagina eParcel, per i pacchi dalla Cina verso Nigeria, Ghana, Kenya, Uganda, Marocco, Egitto, Arabia Saudita, Emirati Arabi Uniti e Pakistan, Speedaf indica un tempo di transito indicativo («Full Reference Transit Time») di 5-10 giorni lavorativi. L'Help Center precisa che la durata dipende dalla destinazione, e le condizioni per Kenya, Uganda e Pakistan specificano che i tempi di consegna non sono garantiti.

### Cosa significa pending Tax sul tracciamento Speedaf?

Significa che sono dovuti dazi o imposte prima che la dogana del Paese di arrivo svincoli il pacco. Una volta effettuato il pagamento compaiono `Customs clearance tax paid` e poi `Customs Clearance Completed`.

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – fondazione nel 2019 e partecipazione di ZTO
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – elenco Paesi e contatti per Svizzera, Irlanda, Zambia e Cina
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – spedizioni dalla Cina e tempo indicativo di 5-10 giorni lavorativi
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – corrieri propri per l'ultimo miglio e pagamento alla consegna
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – consegna a domicilio e contrassegno (COD)
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – tempi per destinazione, tracciamento su sito e app, recapiti telefonici per Paese
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – fino a 20 numeri e link per il pagamento tasse in Sudafrica
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – mappa delle sedi
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – modulo di assistenza clienti
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – telefoni, e-mail, orari e WhatsApp
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – telefono, e-mail e orari
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – telefoni, e-mail, orari e WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – telefono, e-mail e orari
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – telefoni, e-mail e WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – telefono, e-mail, orari e WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – telefono, e-mail e orari
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – indirizzo e-mail
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – telefoni, e-mail e orari
- [Speedaf Maroc: Accueil](https://www.speedaf.com/ma-fr) – telefono, e-mail e orari
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – tentativi di consegna, rifiuto, destinatario, reclami e scadenze
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – condizioni analoghe per l'Uganda
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – tentativi, destinatario, reclami e contestazione della consegna
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – spedizioni nazionali: tentativi, invii non consegnabili e termini di reclamo
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – spedizioni nazionali: invii non consegnabili, termini di reclamo e ricerche per smarrimento
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – segnalazione danni entro 48 ore
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati numerici, codici di stato, pulizia delle righe e visualizzazione in Peek
:::
