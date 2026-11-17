---
title: Tracciamento GOFO Express: numero GFUS, stati e aiuto
description: Traccia un pacco GOFO Express negli Stati Uniti: formati GFUS, GF, CR e MGP, significato degli stati in inglese e cosa fare se la spedizione si ferma.
slug: gofo-express-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

GOFO Express è un corriere che consegna pacchi negli Stati Uniti: ritira le spedizioni nel magazzino del mittente o tramite un partner logistico, le smista attraverso centri di raccolta e hub regionali fino alla stazione di recapito locale e le affida a un autista per la consegna finale. Sia che tu abbia spedito o acquistato un ordine diretto a un indirizzo statunitense, sia che ti trovi negli USA, incolla il numero di tracciamento nel riquadro qui sopra; GOFO mette inoltre a disposizione il proprio portale di tracciamento sul suo sito. Il codice ti viene fornito dal negozio online da cui hai acquistato — se non lo trovi, consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

## Numero di tracciamento GOFO Express: formati negli Stati Uniti

Negli Stati Uniti GOFO Express utilizza quattro serie numeriche proprie. Eccole con cifre inventate, insieme a due formati partner accettati per gli stessi pacchi:

| Numero (inventato) | Cosa identifica |
| --- | --- |
| `GFUS12345678901234` | `GFUS` e 14 cifre: la lettera di vettura (*waybill*) principale di GOFO negli Stati Uniti. Se subito dopo `GFUS` viene digitata una lettera `O` al posto dello zero, Peek la legge automaticamente come `0`. |
| `GF1234567890123` | `GF` e 13 cifre: una serie statunitense meno recente ancora gestita dal sistema di tracciamento GOFO. |
| `CR123456789012` | `CR` e 12 cifre: altra serie statunitense di GOFO, talvolta abbinata a una lettera di vettura `GFUS`. |
| `MGP1234567890123` | `MGP` e 13 cifre: codice di tracciamento secondario abbinato da GOFO ad alcune lettere di vettura `GFUS`. |
| `FC1234567890123` | `FC` e 13 cifre: codice di [DHLink](carrier:dhlink) mantenuto sui pacchi DHgate consegnati da GOFO negli Stati Uniti. |
| `YT1234567890123456` | `YT` e 16 cifre: codice di [YunExpress](carrier:yunexpress), anche quando il recapito finale negli USA è effettuato da GOFO. |

**Due numeri per lo stesso pacco.** Alcune spedizioni riportano contemporaneamente una lettera di vettura `GFUS` e un codice di tracciamento `CR` (12 cifre) o `MGP` (13 cifre): cercando l'uno o l'altro si ottiene esattamente lo stesso pacco sia su Peek sia sul portale statunitense di GOFO. Nei pacchi DHgate recapitati da GOFO per conto di [DHLink](carrier:dhlink), il numero di tracciamento è quello di DHLink, `FC` e 13 cifre: Peek interroga DHLink e anche il portale statunitense di GOFO individua la spedizione con quel codice `FC`.

**Numeri `YT` di YunExpress.** Quando GOFO consegna negli Stati Uniti un pacco gestito da [YunExpress](carrier:yunexpress), il tracciamento di YunExpress mostra anche le scansioni di GOFO e il portale americano di GOFO accetta lo stesso codice `YT`.

**Non confondere GOFO Express USA con GOFO Italy e le altre reti nazionali.** Il gruppo GOFO gestisce reti nazionali distinte in diversi paesi, ciascuna con i propri prefissi: i codici che iniziano con `GFIT` o `CIIT` appartengono a [GOFO Italy](carrier:gofo-it), quelli con `GFCA` a GOFO Canada, con `GFFR` o `CIFR` (oltre ad alcuni codici `PK` a 20 cifre) a GOFO France, con `GFES` o `CIES` a GOFO Spain e con `GFNL` o `CINL` a GOFO Netherlands. Peek li distingue all'istante e interroga la rete del paese corrispondente.

## Stati di tracciamento GOFO Express spiegati

Le scansioni di GOFO Express negli Stati Uniti sono in inglese. Ecco le diciture che un pacco può incontrare lungo il percorso, in ordine indicativo (non tutte le spedizioni passano per ogni singolo stato):

| GOFO scrive | Cosa significa per te |
| --- | --- |
| `Shipping Label Created` | Il mittente ha creato l'etichetta di spedizione; GOFO non ha ancora il pacco. |
| `Pick Up Start` | GOFO ha avviato il ritiro presso il mittente, ma non ha ancora preso in consegna il collo. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO non ha ancora ricevuto il pacco dal magazzino del venditore. |
| `Picked up by Shipping Partner, GOFO Awaiting Item` | Un partner logistico ha ritirato il pacco per conto di GOFO, che ne attende l'arrivo al centro di smistamento. |
| `Pick Up Success` | GOFO ha ritirato il pacco dal mittente. |
| `Arrived at GOFO Regional Origin Hub` | Prima scansione fisica del pacco in un hub regionale di origine di GOFO. |
| `Arrived at Sorting Center` | Arrivo in un centro di smistamento, prima del passaggio all'hub regionale. |
| `Arrived at GOFO Regional Hub` | Arrivo in un hub regionale di GOFO lungo la tratta. |
| `Processing at GOFO Facility` | Il pacco è in fase di smistamento in una struttura GOFO. |
| `Departed GOFO Regional Hub` | Partenza dall'hub regionale verso la tappa successiva. |
| `Loaded on Vehicle` | Caricato su un veicolo per il trasferimento alla struttura seguente. |
| `In Transit to Next Facility` | In viaggio verso il prossimo impianto di GOFO. |
| `Arrived at GOFO Delivery Station` | Arrivato alla stazione locale di GOFO da cui partono i giri di consegna. |
| `Departed GOFO Delivery Station` | Uscito dalla stazione di recapito locale. |
| `Out for Delivery` | In consegna oggi con l'autista. |
| `Delivered` | Consegnato. |
| `Delivery Exception, Business Closed` | Tentativo di consegna fallito perché l'attività era chiusa (la motivazione dopo la virgola cambia in base al problema riscontrato). |
| `Package Returned to GOFO Facility` | Il giorno dopo un tentativo di consegna fallito, il pacco è rientrato nella sede locale di GOFO per un nuovo tentativo — non è un reso al mittente. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Indirizzo errato o incompleto segnalato all'arrivo nell'hub o il giorno dopo un mancato recapito; la spedizione attende i dati mancanti. |
| `Address Corrected.` | L'indirizzo è stato corretto e il pacco riprende il viaggio. |
| `Label Cancelled` | L'etichetta è stata annullata dopo la creazione: con questo numero non arriverà alcun pacco. |

Quando compare `Delivery Exception, …`, Peek imposta la fase su «Tentativo di consegna» e mostra l'intera riga di GOFO sotto «Cosa dice GOFO Express». Se il giorno successivo appare `Package Returned to GOFO Facility`, il pacco torna «In transito» presso la filiale locale in attesa di un nuovo giro di consegna (e non «Restituito al mittente»). Con `Incorrect/Insufficient Address…` o `Label Cancelled`, invece, lo stato passa a «Problema segnalato»: nel primo caso avvisa subito il negozio per integrare numero d'interno, codice del cancello o orari di apertura; nel secondo chiedi al venditore il nuovo numero di spedizione. Su alcune scansioni GOFO aggiunge in coda una riga con i recapiti dell'assistenza in inglese o spagnolo: Peek rimuove questa nota ripetitiva prima di mostrare l'evento.

**Fusi orari.** GOFO registra sempre `Shipping Label Created` sul fuso del Pacifico (`America/Los_Angeles`) e abbina ogni scansione successiva al fuso statunitense dell'impianto interessato. Peek converte ogni orario nel fuso del tuo telefono o computer («Orari nel tuo fuso orario: …») nel formato orario del tuo dispositivo: un'etichetta creata alle 22:00 sulla costa occidentale degli USA appare quindi alle 07:00 del giorno seguente se controlli dall'Italia (alle 06:00 nelle poche settimane di marzo e di fine ottobre in cui Stati Uniti ed Europa non cambiano l'ora lo stesso giorno; all'01:00 sulla costa orientale statunitense).

## Pacco GOFO Express bloccato, in ritardo o non consegnato: cosa fare

**Fermo su `Shipping Label Created`, `Pick Up Start` o `Pickup Exception`.** Finché l'ultimo aggiornamento è uno di questi tre, GOFO non ha ancora ricevuto fisicamente la merce dal magazzino del venditore. Se passano diversi giorni senza novità, chiedi al negozio quando avverrà l'affidamento. Maggiori dettagli in [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Bloccato lungo il tragitto.** Se trascorrono quattro giorni senza nuove scansioni prima che il pacco esca in consegna, contatta il venditore oppure usa il link di assistenza che Peek mostra a quel punto sulla scheda della spedizione.

**Tentativo di consegna fallito o indirizzo incompleto.** Controlla il motivo riportato dopo la virgola in `Delivery Exception, …` oppure l'avviso `Incorrect/Insufficient Address…`. Se mancano numero di appartamento, citofono o orari aziendali, comunicali al negozio affinché l'indirizzo passi ad `Address Corrected.`.

**Risulta consegnato, ma non lo trovi.** Peek non mostra le foto di consegna archiviate da GOFO. Verifica prima portico, sala posta del condominio (*mailroom*), portineria o la pagina di tracciamento di GOFO per controllare se è presente una foto di recapito, poi segnala il problema al venditore. Altri consigli in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Pacco smarrito, restituito e tutele per l'acquirente.** Poiché il contratto di spedizione con GOFO fa capo al venditore, rivolgiti sempre al negozio per chiedere una nuova spedizione o il rimborso (vedi [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender)). Per le spedizioni negli Stati Uniti, la norma federale FTC sulle vendite a distanza ([16 CFR § 435.2](https://www.law.cornell.edu/cfr/text/16/435.2)) impone al venditore di spedire entro il termine promesso — o entro 30 giorni se non specificato — e, in caso di ritardo, di raccogliere il tuo consenso o rimborsarti tempestivamente. Per gli acquisti pagati con carta di credito statunitense mai consegnati come pattuito, il Fair Credit Billing Act ([15 U.S.C. § 1666](https://www.law.cornell.edu/uscode/text/15/1666)) consente di inviare una contestazione scritta all'emittente entro 60 giorni dall'invio dell'estratto conto. Se invece hai acquistato da un venditore professionale stabilito nell'Unione europea, vale il termine legale predefinito di 30 giorni per la consegna ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm)). Infine, se a un indirizzo statunitense arriva per posta merce non richiesta (*brushing*), la legge federale ([39 U.S.C. § 3009](https://www.law.cornell.edu/uscode/text/39/3009)) permette al destinatario di trattarla come un omaggio senza pagare nulla né restituirla; la norma parla di invii postali, non espressamente di una consegna GOFO.

## Assistenza clienti GOFO Express

Per qualsiasi ritardo, correzione dell'indirizzo o rimborso, contatta prima di tutto il negozio che ti ha venduto l'ordine. Se il pacco resta quattro giorni senza nuove scansioni prima di uscire in consegna (anche quando ha soltanto l'etichetta creata), Peek mostra un avviso con il link «Chiedi aiuto a GOFO Express», che apre la pagina di assistenza clienti statunitense di GOFO (`gofo.com/us/customer-service`).

## Tracciare un pacco GOFO Express con Peek

Con un codice `GFUS` a 14 cifre, [Peek](/) riconosce GOFO Express all'istante («Corriere rilevato»). Con un numero `GF` (13 cifre), `CR` (12 cifre) o `MGP` (13 cifre), Peek interroga GOFO («Verifica dei servizi di tracciamento…»); se GOFO ha in carico la spedizione, compare «GOFO Express ha questo pacco.» e il tracciamento si avvia. Eventuali spazi nel codice vengono ignorati. Poiché [Cainiao](carrier:cainiao) traccia alcuni ordini AliExpress diretti negli Stati Uniti con il codice `GFUS` di GOFO, quando GOFO mostra solo l'etichetta da due giorni Peek interroga anche Cainiao per la tratta precedente; approfondisci in [tracciare gli ordini AliExpress](guide:aliexpress-orders).

Finché GOFO non conosce ancora un numero `GF`, `CR` o `MGP`, Peek continua a controllare mostrando «In attesa della prima scansione di GOFO Express» (se sai già che appartiene a GOFO Express, puoi selezionarlo con «Cambia»). Quando la lettera di vettura è già registrata nei sistemi GOFO senza scansioni, oppure con un codice `GFUS` salvato su peektracker.com, vedi «In attesa di GOFO Express» e Peek prosegue i controlli senza segnare errore finché il codice resta in elenco. Solo se GOFO restituisce il numero come sconosciuto per due giorni su un pacco salvato sotto GOFO Express e nessun'altra fonte risponde, peektracker.com mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» passando a un controllo al giorno e, dopo una settimana, a uno alla settimana. Puoi anche incollare un link di tracciamento USA da `gofo.com` (`/us…` o home) o da `gofoexpress.com` contenente `searchID=`.

Peek mostra lo stato attuale, ogni scansione con città e stato USA indicati da GOFO (ad esempio `Jamaica, NY`), le tappe su una mappa, l'ora di consegna e il paese di destinazione. Le scansioni restano in inglese così come le scrive GOFO, mentre la fase principale appare in italiano («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Consegnato» — senza il dettaglio che GOFO aggiunge talvolta dopo la virgola alla consegna). GOFO calcola una data di consegna stimata che Peek mostra finché il pacco non passa a «In consegna», «Consegnato» o «Restituito al mittente»; Peek omette invece foto di consegna, coordinate GPS e peso.

Peek interroga GOFO fino a ogni 30 minuti (anche mentre il pacco è «In consegna»), attende 60 minuti dopo un tentativo fallito e passa a un controllo all'ora dopo due giorni senza aggiornamenti o dopo 12 ore se la spedizione resta su «Etichetta creata» o «Preso in carico». Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare né GOFO né il negozio al posto tuo.

## Domande frequenti sul tracciamento GOFO Express

### Che cos'è GOFO Express?

È un corriere espresso che gestisce ritiri in magazzino, smistamento interregionale e consegne dell'ultimo miglio negli Stati Uniti. Il gruppo opera inoltre con reti nazionali dedicate in Italia ([GOFO Italy](carrier:gofo-it)), Canada, Francia, Spagna e Paesi Bassi, ciascuna con i propri prefissi.

### Perché il mio numero di tracciamento GOFO non funziona?

Controlla prima che sia un codice statunitense di GOFO: `GFUS` (14 cifre), `GF` (13 cifre), `CR` (12 cifre) o `MGP` (13 cifre). I codici con `GFIT` o `CIIT` appartengono a [GOFO Italy](carrier:gofo-it), quelli con `GFCA`, `GFFR`/`CIFR`, `GFES`/`CIES` o `GFNL`/`CINL` alle altre reti nazionali e i `YT` a [YunExpress](carrier:yunexpress); un ordine DHgate consegnato da GOFO può essere cercato sul portale GOFO anche tramite il codice `FC` di [DHLink](carrier:dhlink). Se il venditore ha appena generato la spedizione, la lettera di vettura potrebbe essere solo in attesa della prima scansione. Se dopo due giorni GOFO continua a non riconoscerlo su peektracker.com, verifica eventuali errori di battitura; se invece compare `Label Cancelled`, l'etichetta è stata annullata e devi chiedere al negozio il codice sostitutivo.

### Cosa significa `Package Returned to GOFO Facility` su GOFO?

A dispetto della traduzione letterale, non indica che il pacco sta tornando al venditore: questa dicitura compare il giorno dopo un tentativo di consegna non riuscito (`Delivery Exception`), quando l'autista riporta il collo nella stazione locale di GOFO in vista di un nuovo tentativo, motivo per cui Peek lo mantiene «In transito».

### Perché il mio ordine AliExpress o DHgate ha un numero GOFO?

Per le consegne negli Stati Uniti, [Cainiao](carrier:cainiao) (la logistica di AliExpress) segue alcuni pacchi con il numero `GFUS` di GOFO Express quando è GOFO a consegnarli, mentre [DHLink](carrier:dhlink) affida determinati pacchi DHgate a GOFO, che li ritrova anche con il codice `FC` di DHLink. Con un codice `GFUS`, Peek interroga GOFO e controlla anche Cainiao quando GOFO mostra solo l'etichetta da due giorni.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati USA (GFUS, GF, CR e MGP), codici abbinati DHLink (FC) e YunExpress (YT), reti nazionali GOFO, scansioni e fasi, fusi orari, rimozione della riga supporto, Cainiao e dati esclusi da Peek
- [16 CFR § 435.2 – Mail, Internet, or Telephone Order Merchandise Rule (Cornell LII)](https://www.law.cornell.edu/cfr/text/16/435.2) – obbligo di spedizione entro il termine promesso o in 30 giorni negli USA e diritto al rimborso in caso di ritardo
- [15 U.S.C. § 1666 – Fair Credit Billing Act (Cornell LII)](https://www.law.cornell.edu/uscode/text/15/1666) – contestazione scritta entro 60 giorni per addebiti su carta relativi a merce non consegnata
- [39 U.S.C. § 3009 – Mailing of unordered merchandise (Cornell LII)](https://www.law.cornell.edu/uscode/text/39/3009) – merce non ordinata recapitata negli USA considerata omaggio senza obbligo di pagamento
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – termine legale predefinito di 30 giorni per gli acquisti presso venditori stabiliti nell'UE
:::
