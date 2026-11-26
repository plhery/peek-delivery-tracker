---
title: Tracciamento Blue Dart: numeri waybill e stati spiegati
description: Traccia una spedizione Blue Dart: formato del codice a 11 cifre, significato di Shipment Further Connected e come la segue Peek.
slug: tracciamento-blue-dart
published: 2026-11-22
updated: 2026-11-22
---

Blue Dart è un corriere indiano: il catalogo di Peek lo elenca per l'India e legge le sue scansioni secondo l'ora indiana. Una spedizione Blue Dart usa un numero di lettera di vettura (*waybill*) di 11 cifre fornito dal mittente. Per un ordine online può comparire, per esempio, nell'e-mail di spedizione del negozio o nella pagina dell'ordine. Incollalo nel campo qui sopra oppure inseriscilo sul sito bluedart.com. Poiché Peek non ha potuto verificare il sito di Blue Dart per questa pagina, le informazioni qui sotto derivano da ciò che il codice di Peek sa del tracciamento di Blue Dart.

## Codice waybill Blue Dart: come è fatto

Peek conosce un solo formato per Blue Dart: 11 cifre, di cui l'ultima è una cifra di controllo calcolata dalle prime dieci. Con cifre inventate:

| Codice (inventato) | Che cos'è |
| --- | --- |
| `48291057360` | 11 cifre con cifra di controllo valida: la forma di una waybill Blue Dart. Anche le reti GLS, PostLogistics, Japan Post, Mainfreight e altri usano 11 cifre, quindi il formato da solo non basta per individuare Blue Dart. |
| `48291057361` | Lo stesso formato con cifra di controllo errata. Peek non propone Blue Dart per questo codice: controlla eventuali errori di battitura. |
| `4829105736` | 10 cifre: non è una waybill Blue Dart. Se scegli Blue Dart per questo numero, Peek avvisa che Blue Dart «non emette numeri come questo». Lo stesso vale per 12 cifre o codici con lettere. |

Le waybill da 11 cifre di Aramex e la posta raccomandata di Japan Post usano la stessa cifra di controllo, quindi anche un numero valido può appartenere a loro: controlla quale corriere nomina il negozio. Spazi e trattini non contano in Peek. Il sito di Blue Dart consente ricerche anche con altri riferimenti, ma Peek richiede la waybill. Non la trovi? Vedi [come trovare il codice di tracciamento](guide:find-tracking-number).

## Stati del tracciamento Blue Dart spiegati

Il tracciamento di Blue Dart mostra lo stato attuale della spedizione e sotto le scansioni, dalla più recente alla più vecchia, ognuna con luogo, data e ora. Ecco le diciture che Peek conosce dal tracciamento di Blue Dart, più o meno nell'ordine in cui una spedizione le incontra:

| Blue Dart scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Pickup Employee Is Out To P/U Shipment` | Un corriere Blue Dart è in viaggio per ritirare (*P/U*) la spedizione dal mittente. Blue Dart non l'ha ancora presa in carico. | «Etichetta creata» |
| `Shipment Picked Up` | Blue Dart ha ritirato la spedizione dal mittente. | «Preso in carico» |
| `Shipment Arrived`, `Shipment Arrived At Hub` | Arrivata in una sede o in un hub Blue Dart, indicato come luogo della scansione. | «In transito» |
| `Paper Work Inscan` | Scansionata in un hub tra l'arrivo e il collegamento successivo. | «In transito» |
| `Clubbed Canvas Bag Scan` | Scansionata e inserita in un sacco con altre spedizioni. | «In transito» |
| `Shipment Further Connected` | Inoltrata da quella sede alla tappa successiva del percorso. | «In transito» |
| `Delivery Scheduled For Next Working Day` | Arrivata alla sede di consegna dopo l'orario limite giornaliero: partirà con il giro del giorno lavorativo successivo. | «In transito» |
| `Shipment Out For Delivery` | In consegna. | «In consegna» |
| `Cnee Refused Id/Otp Not Shared-Incorrect` | Non consegnata per rifiuto del destinatario (*Cnee*, *consignee*) oppure perché documento o codice monouso (*OTP*) non sono stati forniti o risultano errati. | «Tentativo di consegna» |
| `Delivery Delayed`, `Delay Caused Beyond Control` | La consegna è in ritardo; la seconda dicitura attribuisce la causa a fattori fuori dal controllo di Blue Dart. | «Problema segnalato» |
| `Shipment Delivered` | Consegnata. | «Consegnato» |
| `Shipment Returned Back To Shipper` | La spedizione è tornata al mittente. Blue Dart la indica come stato finale a reso completato. | «Restituito al mittente» |

Una dicitura che Peek non conosce ancora viene mostrata così come la scrive Blue Dart e vale come «In transito», a meno che il pacco non sia già in consegna o oltre: in tal caso lo stato non cambia. Altro in [cosa significano gli stati del tracciamento](guide:tracking-statuses).

## Consegna Blue Dart in India: documento e codice OTP

Poiché Peek non ha potuto verificare le regole di consegna di Blue Dart — come il numero di tentativi o i giorni di giacenza — questa pagina non ne indica alcuna.

**Documento o OTP.** La dicitura `Cnee Refused Id/Otp Not Shared-Incorrect` mostra che una consegna Blue Dart può richiedere un documento o un codice monouso (*OTP*). Se hai ricevuto un codice per la spedizione, tienilo pronto il giorno della consegna. Se questa riga compare senza che tu abbia rifiutato nulla, chiedi al negozio di organizzare una nuova consegna. Vedi [consegne mancate](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

Un messaggio su una consegna fallita ti chiede di pagare o inviare un codice tramite un link? Leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Spedizioni Blue Dart: chi consegna e dogana

**Una waybill Blue Dart.** Peek non conosce alcun corriere a cui Blue Dart affidi le spedizioni, in India o all'estero. Se il negozio ti dà anche il codice di un altro corriere, aggiungi anche quello.

**Spedita con ICC Worldwide.** Il tracciamento di ICC Worldwide può citare Blue Dart accanto a un numero Blue Dart. Peek segue la spedizione su Blue Dart solo quando quel numero ha 11 cifre: interroga Blue Dart e, appena compare una scansione recente almeno quanto l'ultima di ICC Worldwide, sposta lì il monitoraggio conservando la cronologia di ICC Worldwide. Con qualsiasi altro numero, Peek resta su ICC Worldwide. Aggiungi la spedizione con il codice di ICC Worldwide, poi premi «Cambia» e cerca ICC Worldwide: Peek non lo propone automaticamente dal numero.

**Dogana.** Il codice di Peek conosce Blue Dart solo in India e non ha potuto controllare il sito di Blue Dart per le spedizioni estere. Per un pacco fermo in dogana vedi [pacchi fermi in dogana](guide:customs).

## Tracciamento Blue Dart fermo, in ritardo o restituito

Per un rimborso o una nuova spedizione rivolgiti al negozio: ha prenotato lui Blue Dart.

**Ancora nessuna scansione.** Per un numero senza dati, il tracciamento di Blue Dart risponde `There is no information on the Waybill/Reference Number/Order Number currently.` Peek continua a controllare. Verifica le 11 cifre nel messaggio del negozio; se dopo qualche giorno non compare nulla, chiedi al negozio se ha affidato la spedizione.

**Ferma su `Pickup Employee Is Out To P/U Shipment`.** Blue Dart non ha ancora ritirato la spedizione dal mittente. Chiedi al negozio quando avverrà il ritiro.

**`Delivery Delayed` o `Delay Caused Beyond Control`.** Peek mostra «Problema segnalato». Se poi non cambia nulla, contatta il negozio.

**Giorni senza aggiornamenti.** Quando una spedizione in viaggio non registra scansioni per quattro giorni, Peek la segnala con il giorno di quella scansione: «Nessun aggiornamento da lun 12 ott». Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnata ma non trovata.** Chiedi in casa o alla portineria dello stabile, poi avvisa il negozio. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrita o restituita.** Chiedi al negozio un rimborso o una nuova spedizione: vedi [pacchi smarriti](guide:lost-parcel). `Shipment Returned Back To Shipper` indica il rientro al mittente: vedi [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Blue Dart

Poiché Peek non ha potuto verificare il sito di Blue Dart, questa pagina non riporta numeri di telefono né indirizzi e-mail: indichiamo solo contatti letti sulle pagine del corriere. Per una spedizione ferma, Peek collega la pagina di aiuto di Blue Dart come «Chiedi aiuto a Blue Dart».

Per un ordine online chiedi prima al negozio che ha scelto Blue Dart. Peek non può contattare Blue Dart né il negozio al posto tuo.

## Tracciare una spedizione Blue Dart con Peek

Incolla la waybill in [Peek](/). Poiché diversi corrieri usano 11 cifre, Peek non mostra subito «Corriere rilevato» da un codice Blue Dart da solo:

- **In India** (la regione del telefono o, sul web, il paese da cui ti colleghi), Peek mostra «Verifica dei servizi di tracciamento…» mentre interroga i corrieri compatibili, Blue Dart per primo. Quando Blue Dart conosce il codice, vedi «Blue Dart ha questo pacco.».
- **Fuori dall'India**, Peek interroga Blue Dart per ultimo, quando gli altri corrieri e i servizi di tracciamento non hanno trovato il numero. Per assegnare subito la spedizione a Blue Dart, premi «Cambia» e scegli Blue Dart sotto «Compatibili con questo numero».

Se nessun corriere conosce ancora il codice, aggiungilo comunque o seleziona Blue Dart tu stesso: Peek segue oltre 4.500 corrieri. Peek propone Blue Dart solo se la cifra di controllo torna. Se non torna ma sei sicuro del codice, cerca Blue Dart nell'elenco dei corrieri: Peek lo interroga comunque. Anche un link di bluedart.com con la waybill (`trackNo=`) funziona: Peek estrae il numero e rileva subito Blue Dart («Corriere rilevato»).

Associata a Blue Dart, una spedizione mostra «In attesa di Blue Dart» fino alla prima scansione, oppure «Controlla il numero di tracciamento» se dopo due giorni Blue Dart non conosce ancora il numero; aggiunta senza corriere, «Nessuna cronologia di tracciamento per ora». Peek legge il tracciamento pubblico di Blue Dart con la sola waybill, senza account: lo stato della spedizione e ogni scansione nelle parole di Blue Dart con il relativo luogo, letto nell'ora indiana. Esclude i dati del destinatario e non mostra una data prevista di consegna. Il link «Apri il sito Blue Dart» sulla pagina del pacco apre la waybill sul tracciamento di Blue Dart.

Finché tieni il pacco aperto sullo schermo, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui Blue Dart lo mette in consegna — finché il tracciamento di Blue Dart risponde. Altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando la spedizione è seguita da un servizio di tracciamento, Peek controlla ogni ora, fino a ogni 15 minuti il giorno della consegna finché la tieni aperta o hai le notifiche attive, e ogni 3 ore dopo un giorno senza novità. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento Blue Dart

### Come traccio una spedizione Blue Dart?

Inserisci la waybill di 11 cifre sul tracciamento su bluedart.com oppure incollala in Peek. In India Peek interroga Blue Dart per primo; altrove per ultimo, oppure puoi premere «Cambia» e scegliere Blue Dart.

### Com'è fatto un numero waybill Blue Dart?

11 cifre, di cui l'ultima è una cifra di controllo. Anche Aramex e altri usano 11 cifre: controlla che il messaggio del negozio indichi Blue Dart.

### Cosa significa «Shipment Further Connected» su Blue Dart?

Blue Dart ha inoltrato la spedizione da una delle sue sedi a quella successiva lungo il percorso. Peek mostra «In transito».

### Cosa significa «Cnee Refused Id/Otp Not Shared-Incorrect»?

Una consegna fallita per rifiuto del destinatario o per un documento d'identità o codice monouso (OTP) non fornito o errato. Peek mostra «Tentativo di consegna». Se non hai rifiutato nulla, chiedi al negozio una nuova consegna.

### Chi devo contattare per una spedizione Blue Dart mancante?

Il negozio o il mittente che ha prenotato Blue Dart. Poiché Peek non ha potuto verificare il sito di Blue Dart, questa pagina non elenca contatti di Blue Dart.

:::sources
- [Universal Parcel Scraper: Blue Dart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/blue-dart) – India, waybill di 11 cifre e cifra di controllo, tracciamento e risposta di assenza dati, diciture e fasi, ora indiana, dati omessi, link di tracciamento e pagina di aiuto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri corrieri a 11 cifre, cifra di controllo di Aramex e Japan Post, corrieri interrogati in India e all'estero, collegamento ICC Worldwide–Blue Dart e regola delle 11 cifre, nessun passaggio da Blue Dart
:::
