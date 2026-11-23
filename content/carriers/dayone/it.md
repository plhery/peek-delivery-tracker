---
title: Tracciamento DayOne: numeri DOT, stati e chi consegna
description: Traccia un pacco DayOne dalla Cina: com’è fatto un numero DOT, significato degli stati su DayOneTrack, chi consegna e chi contattare in caso di ritardo.
slug: tracciamento-dayone
published: 2026-11-23
updated: 2026-11-23
---

DayOne è il magazzino che spedisce dalla Cina gli ordini online di un negozio. Non è un corriere: si definisce «the order fulfillment warehouse, not a courier delivery service provider». Altre aziende trasportano il pacco, e il tracker di DayOne, [DayOneTrack](https://www.dayonetrack.com/), in inglese e cinese, ne mostra le scansioni. Cerca lì il tuo numero DOT oppure incollalo nella casella qui sopra. Il codice te lo fornisce il negozio nei dettagli di spedizione dell’ordine.

## Numero di tracciamento DayOne: com’è fatto

DayOne non pubblica un formato ufficiale. Ecco la forma che Peek riconosce come DayOne, con cifre inventate:

| Numero | Cos’è |
| --- | --- |
| `DOT0482917364058291` | DOT e 16 cifre: un numero DayOne. |
| `DOT1482917364058291` | DOT1 e altre 15 cifre: anch’esso di DayOne. Il tracker DayOne cerca questi numeri in un altro sistema. |
| `DOT048291736405829` | Solo 15 cifre: non è il formato DayOne e Peek non nomina DayOne. Controlla se manca una cifra. |

In Peek minuscole, spazi e trattini non contano se incolli il codice da solo. In un messaggio intero, tieni DOT unito alle cifre: con uno spazio subito dopo DOT, Peek può leggere solo le cifre e proporre un altro corriere.

**Dove trovarlo.** Nell’e-mail di spedizione del negozio o nella pagina dell’ordine. Se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Sul tracker di DayOne.** Puoi cercare fino a 10 numeri alla volta, uno per riga. Il tracker cerca solo numeri con DOT0 o DOT1: qualsiasi altro codice, come quello della posta locale, mostra `Not found`. Accanto al codice, un risultato può mostrare un `Reference Number` (il riferimento interno del negozio) e il paese di destinazione del pacco.

## Stati di tracciamento DayOne spiegati

Il tracker di DayOne assegna a ogni pacco uno stato complessivo, in inglese e cinese, ed elenca le scansioni sotto `Package Track`, dalla più recente:

| Stato DayOne | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Not found` (查询不到) | DayOne non ha ancora nulla con questo codice. Aggiunge: «The order information has not been updated yet». | «In attesa di DayOne» |
| `Info received` (收到信息) | I dati di spedizione sono arrivati. Il pacco potrebbe non aver ancora lasciato il magazzino. | «Etichetta creata» |
| `In transit` (运输途中) | In viaggio. | «In transito» |
| `Pick up` (到达待取) | Arrivato e in attesa del tuo ritiro. | «Pronto per il ritiro» |
| `Out for Delivery` (派送途中) | In consegna. | «In consegna» |
| `Undelivered` (投递失败) | Un tentativo di consegna non è riuscito. | «Tentativo di consegna» |
| `Delivered` (成功签收) | Consegnato. | «Consegnato» |
| `Alert` (可能异常) | Potrebbe esserci un problema. | «Problema segnalato» |
| `Expired` (运输过久) | In cinese, «in transito troppo a lungo»: il pacco viaggia da molto tempo. | lo stato della scansione più recente |

Peek si basa sulla scansione più recente che capisce. Usa lo stato generale di DayOne solo quando non riconosce nessuna scansione.

Ogni scansione riporta ora, luogo e testo dell’azienda che ha il pacco: Yanwen in Cina, poi la posta del tuo paese. Le scansioni tipiche, nell’ordine abituale:

| DayOne mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order processed by shipper` | Yanwen ha i dati dell’ordine. Nessuno ha ancora scansionato il pacco. | «Etichetta creata» |
| `Yanwen Pickup Scan`, `Processing information input` | Yanwen ha il pacco in Cina e lo sta lavorando. | «Preso in carico» |
| `Yanwen facility - Outbound`, `Arrived at domestic terminal station` | Ha lasciato lo smistamento Yanwen ed è arrivato a un terminal in Cina prima della dogana export. | «In transito» |
| `Port of departure - Received by carrier`, `International shipment release - Export`, `Port of departure - Departure` | Al porto di partenza, superata la dogana export cinese (non la tua), poi imbarcato sul volo. | «In transito» |
| `Port of destination - Arrival`, `ARRIVED AT DESTINATION AIRPORT` | È atterrato nel tuo paese o nelle vicinanze. La dogana d’importazione deve ancora arrivare. | «In transito» |
| `CLEARED BY CUSTOMS`, `COLLECTED FROM AIRPORT TERMINAL`, `ARRIVED AT FACILITY IN DESTINATION COUNTRY` | La dogana del tuo paese l’ha sdoganato ed è stato portato dall’aeroporto a un centro postale. | «In transito» |
| `RECEIVED BY AUSTRALIA POST FOR TRANSPORTATION TO PROCESSING FACILITY` | In Australia: affidato ad Australia Post. | «In transito» |
| `ITEM PROCESSED AT POST FACILITY`, `PROCESSED THROUGH AUSTRALIA POST FACILITY`, `TRANSFERRED` | Smistato dalla posta e inoltrato. | «In transito» |
| `WITH AUSTRALIA POST FOR DELIVERY TODAY` | Oggi in consegna. | «In consegna» |
| `DELIVERED WITH SAFE DROP` | Consegnato e lasciato in un punto sicuro all’indirizzo. | «Consegnato» |

`SHIPPING INFORMATION RECEIVED`, `SHIPPING INFORMATION RECEIVED BY AUSTRALIA POST` e `SHIPPING INFORMATION APPROVED BY AUSTRALIA POST` indicano che la posta ha i dati del pacco. Possono comparire dopo scansioni successive: non è un passo indietro, e Peek mantiene il progresso più avanzato.

Se l’unica scansione è `Shipment has begun. In transit to destination city`, DayOne ha passato il pacco a [YunExpress](carrier:yunexpress), e il resto della cronologia è sul sito YunExpress con un codice YunExpress (`YT` e 16 cifre). `Clearence processing completed - Export` è lo sdoganamento export di YunExpress, con la sua ortografia.

## Chi consegna i pacchi DayOne

DayOne non indica chi consegni nel tuo paese. Le sue scansioni mostrano chi ha il pacco: Yanwen in Cina, poi la posta del tuo paese, come [Australia Post](carrier:australia-post) per le spedizioni verso l’Australia.

DayOne non pubblica regole per tentativi di consegna, avvisi o punti di ritiro: valgono quelle del corriere finale. Dopo `Pick up` o `Undelivered`, cerca un avviso o un messaggio di quell’azienda. Vedi [consegne mancate](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

**Dogana nell’UE.** I beni comprati online fuori dall’UE pagano un dazio di 3 € per articolo sui pacchi fino a 150 € dal 1° luglio 2026, e una tariffa di gestione dell’Unione di 2 € per articolo, di qualsiasi valore, dal 1° novembre 2026. Un articolo sono i beni che condividono classificazione tariffaria, descrizione e, se richiesta, origine. Il sito DayOne non dice nulla su dazi, tariffe o su chi li riscuota. Altrove valgono le regole del tuo paese: leggi [pacchi fermi in dogana](guide:customs).

## Pacco DayOne fermo, smarrito o restituito

DayOne rimanda al negozio. Il tracker avvisa che per logistica, smarrimenti, rimborsi e rispedizioni devi rivolgerti all’assistenza clienti del negozio da cui hai ordinato.

**`Not found`.** DayOne non ha ancora nulla per quel numero. Verifica il codice: DOT e 16 cifre. Se è giusto, l’ordine potrebbe non essere ancora partito. Peek mostra «In attesa di DayOne» e continua a controllare. Se dopo due giorni DayOne non riconosce ancora il numero e nessun altro ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e da quel momento controlla una volta al giorno, poi una volta a settimana trascorsa una settimana dall’aggiunta. Se dopo qualche giorno non compare nulla, chiedi al negozio.

**Solo `Info received` o `Order processed by shipper`.** Nessuno ha ancora scansionato il pacco. Se resta così per giorni, chiedi al negozio quando è partito.

**Nessuna nuova scansione per giorni.** I pacchi restano spesso in silenzio sui lunghi voli e in dogana. Se un pacco ancora su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza una nuova scansione, Peek lo segnala con «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da …»), spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DayOne.» e propone «Chiedi aiuto a DayOne». Dopo tre settimane senza nuovi aggiornamenti, Peek mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a DayOne.» Vedi [perché il tracciamento si ferma](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**`Alert` o `Expired`.** Leggi le scansioni più recenti, poi chiedi aiuto al negozio comunicando il codice DOT.

**Smarrito, danneggiato o restituito.** DayOne non pubblica procedure di reclamo per i destinatari. Chiedi rimborso o nuova spedizione al negozio. Vedi [pacchi smarriti](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

**Consegnato ma non c’è.** `DELIVERED WITH SAFE DROP` indica che il pacco è stato lasciato in un punto sicuro all’indirizzo. Controlla intorno a casa, chiedi ai vicini e poi avvisa il negozio. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Contatti DayOne

Indicazioni sul [tracker di DayOne](https://www.dayonetrack.com/):

- **Per il tuo pacco:** il negozio in cui hai ordinato, come richiesto da DayOne.
- **E-mail commerciale:** info@dayone-shipping.com, indicata da DayOne per collaborazioni e richieste aziendali diverse dal supporto pacchi.

Il tracker di DayOne non fornisce telefono, chat, modulo di contatto né indirizzo. Dichiara di memorizzare solo numeri di lettera di vettura e nessun dettaglio degli ordini.

## Tracciare un pacco DayOne con Peek

[Peek](/) identifica subito DayOne da un codice DOT di 16 cifre («Corriere rilevato»). Funziona anche un link `dayonetrack.com` con `trackNo=`. Per qualsiasi altro codice incollalo comunque: Peek segue oltre 4.500 corrieri. Quando il formato corrisponde a più corrieri, Peek mostra «Verifica dei servizi di tracciamento…» durante la ricerca. Se Peek non nomina DayOne per il tuo codice, tocca «Cambia» e scegli DayOne sotto «Tutti i corrieri» (lo trova anche cercando «Day One»), oppure usa «Cambia corriere» su un pacco già aggiunto.

Per un codice `DOT0`, Peek legge direttamente il tracker di DayOne: stato, ogni scansione con il luogo, paese di destinazione e ora di consegna. Per un codice `DOT1` interroga invece i servizi di tracciamento.

Quando DayOne indica il fuso orario di una scansione, Peek lo usa. Se manca, ricava l’orologio dal luogo o dalle scansioni vicine (quello del tuo paese appena compare una scansione di arrivo). Mostra ogni orario nel fuso del tuo dispositivo. Omette CAP, nome di chi ha ritirato o firmato il pacco consegnato e `Reference Number` del negozio. DayOne non fornisce data prevista di consegna né peso.

Il tracker di DayOne non mostra il codice del corriere finale, ma la sua risposta a volte lo contiene. In tal caso Peek interroga anche quell’azienda: la posta del tuo paese per un codice postale (due lettere, nove cifre, due lettere) come Royal Mail, USPS, Canada Post, An Post o Australia Post, oppure l’unico corriere legato al formato, come YunExpress per un codice `YT`. Appena quel corriere registra una scansione almeno recente quanto l’ultima di DayOne, Peek passa a seguirlo lì, mostra quel codice accanto al DOT e conserva la cronologia di DayOne.

Per un codice `DOT0`, mentre tieni il pacco aperto in Peek o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e dalle 6:00 (ora locale) del giorno in cui DayOne scansiona il pacco in consegna fino a ogni 2 minuti finché il tracker proprio di DayOne risponde; dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora, e se resta su «Etichetta creata» per sette giorni, ogni 6 ore. Per un codice `DOT1` (tramite servizi di tracciamento), Peek controlla ogni ora, ogni 3 ore dopo un giorno senza nuove scansioni, ogni 6 ore dopo sette giorni su «Etichetta creata», e fino a ogni 15 minuti il giorno in cui il pacco è in consegna, mentre il pacco è aperto in Peek o le notifiche sono attive. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare DayOne né il negozio al posto tuo.

## Domande sul tracciamento DayOne

### DayOne è un corriere?

No. DayOne afferma di essere «the order fulfillment warehouse, not a courier delivery service provider». Altre società trasportano l’ordine: dalle scansioni di DayOne, Yanwen in Cina e poi la posta del tuo paese.

### Dove posso tracciare un numero DOT?

Sul tracker di DayOne, [dayonetrack.com](https://www.dayonetrack.com/), in inglese o cinese, fino a 10 numeri alla volta. Oppure incollandolo in Peek.

### Perché il tracker di DayOne dice Not found?

DayOne non ha ancora nulla con quel codice. Verifica di aver inserito DOT e tutte le 16 cifre. Se il codice è esatto, attendi uno o due giorni, poi chiedi al negozio se il pacco è partito.

### Perché gli orari sul tracker di DayOne non sono in ordine?

Ogni scansione mostra l’ora locale del luogo in cui è stata fatta, senza fuso orario, e il tracker ordina le righe in base a quegli orari. Nell’inverno boreale Pechino è 8 ore avanti rispetto a Londra e 13 rispetto a New York, quindi una scansione nel tuo paese può sembrare più vecchia dell’ultima in Cina. Sydney è 3 ore avanti rispetto a Pechino nell’estate australe, perciò lì sembrano più recenti. Peek mostra ogni ora nel fuso del tuo dispositivo.

:::sources
- [DayOne: Dayone Tracking](https://www.dayonetrack.com/) – magazzino, non corriere; post-vendita al negozio; informativa privacy; e-mail aziendale; inglese e cinese; 10 numeri alla volta; ricerche DOT0 e DOT1; stati; campi risultato; orari
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio 3 € dal 1° luglio 2026, tariffa di gestione dell’Unione 2 € dal 1° novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articolo a prescindere dal valore, cos’è un articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato del numero, link e ricerche, testi e codici delle scansioni, fusi orari, numero del corriere finale, passaggio di consegna, cosa legge Peek
:::
