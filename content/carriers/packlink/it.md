---
title: Tracking Packlink: riferimento, stati e chi contattare
description: Traccia un pacco Packlink: formato ES…COM, significato degli stati, consegne mancate, reclami per danni e come contattare Packlink.
slug: tracking-packlink
published: 2026-11-23
updated: 2026-11-23
---

Packlink non è un corriere. È una piattaforma di prenotazione con sede a Madrid che si definisce un comparatore di spedizioni: privati o negozi confrontano corrieri come SEUR, Correos, Correos Express o UPS, pagano, stampano l'etichetta e quel corriere ritira e consegna il pacco. I negozi usano Packlink PRO, il servizio professionale collegato a negozi online e marketplace come eBay e Amazon. Per tracciare un pacco inserisci il riferimento Packlink o il codice del corriere sulla [pagina di tracciamento di Packlink](https://www.packlink.es/seguimiento-envios/) oppure nel riquadro in alto.

## Codice di tracciamento Packlink: come si presenta

Packlink assegna a ogni spedizione un proprio riferimento: due lettere per il mercato, l'anno, `COM` o `PRO` e dieci cifre. Il sito spagnolo di Packlink indica che i suoi riferimenti iniziano con ES. Il corriere assegna inoltre un proprio numero al pacco, e la pagina di tracciamento di Packlink accetta entrambi. Con cifre inventate:

| Codice (inventato) | Che cos'è |
| --- | --- |
| `ES2026COM4829173650` | ES, anno, COM e 10 cifre: un riferimento Packlink. |
| `ES2026PRO6194028375` | Lo stesso con PRO: sempre un riferimento Packlink. |
| `48291736405829` | 14 cifre: uno dei formati di SEUR, condiviso da DPD e altri corrieri. |
| `1Z9X482W0348291737` | 1Z seguito da 16 lettere o cifre: un codice UPS. |

**Dove trovarlo.** Packlink invia il riferimento nell'email di conferma alla prenotazione. Se qualcuno ti ha venduto un oggetto spedendolo tramite Packlink, ha ricevuto quell'email: chiedigli il riferimento o il codice del corriere. Altri posti dove cercare: [come trovare il codice di tracciamento](guide:find-tracking-number) e [acquisti di seconda mano](guide:second-hand-orders).

## Stati di tracciamento Packlink spiegati

Packlink legge ogni scansione del corriere e la classifica con un proprio codice di stato, accompagnato da una riga in inglese sulla pagina di tracciamento. Molte scansioni conservano anche il testo del corriere, come le maiuscole spagnole di SEUR o Correos Express. Righe frequenti, in ordine:

| Riga di tracciamento | Che cosa significa per te |
| --- | --- |
| `Waiting for carrier to collect the order` | Il mittente ha prenotato e ha l'etichetta. Il corriere non ha ancora il pacco. |
| `SIN RECEPCION` | Lo stesso per Correos Express: ha la prenotazione, non ancora il pacco. |
| `RECIBIDO EN ORIGEN` | Il primo deposito SEUR ha il pacco. Packlink lo classifica ancora in attesa di ritiro. |
| `Shipment accepted by carrier (scanned)` | Il corriere ha scansionato il pacco. È in viaggio. |
| `EN ARRASTRE` | In transito tra i depositi del corriere. |
| `DELEGACION DESTINO` | Presso il deposito che consegna nella tua zona. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR: non ancora giunto al deposito locale. Packlink lo segna come problema di consegna, ma il pacco sta semplicemente viaggiando. |
| `EN REPARTO` | In consegna. |
| `REPARTO FALLIDO` | Tentativo di consegna non riuscito. |
| `Package at pick-up point and ready for collection` | Ti aspetta in un punto di ritiro. Packlink lo mette tra i codici di consegna, ma devi ancora andarlo a ritirare. |
| `ENTREGADO` | Consegnato. |
| `Package returned to sender` | Il pacco è tornato al mittente. |

Altre righe sono in inglese chiaro: `Recipient not present at indicated address` e `Unsuccessful delivery and final attempt` indicano consegne mancate, mentre `Packet delivered to customs for clearance` indica un passaggio doganale per i pacchi che attraversano una frontiera doganale.

Due righe non dicono nulla sul pacco stesso. `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` significa che Packlink non ha ancora trovato informazioni presso il corriere. `Expired, there is no tracking information for the last 30 days` significa che il corriere non ha inviato aggiornamenti per un mese e Packlink ha smesso di seguirlo.

## Chi consegna un pacco Packlink

Il corriere scelto dal mittente. La pagina di tracciamento Packlink elenca [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) e Zeleris, tra gli altri. Le condizioni di Packlink stabiliscono:

- **Domicilio o punto di ritiro.** Il mittente decide al momento della prenotazione se il pacco va a un indirizzo o a un punto di ritiro.
- **Consegna mancata.** Il corriere può lasciare un avviso per fissare una nuova consegna e riporta il pacco in deposito. Non tutti lasciano avviso, quindi Packlink chiede al mittente di seguire il tracciamento e avvisarti.
- **Tentativi.** Nella maggior parte dei casi ci sono un primo tentativo e un secondo gratuito. Ogni tentativo ulteriore costa al mittente da 2 € a 15 € più IVA, a seconda del corriere e del veicolo. In alcuni paesi il corriere può lasciarlo a un vicino o in un punto di ritiro.
- **Ritiro.** Un pacco lasciato al deposito del corriere va ritirato entro il termine previsto dal corriere stesso. Scaduto quel termine, il mittente può dover pagare giacenza, reso o distruzione.
- **Rifiutato o non consegnabile.** Packlink avvisa il mittente tramite il tracciamento o per email. Il mittente ha 2 giorni lavorativi per indicare se farlo rientrare o spedirlo altrove.

Vedi anche [consegne mancate](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

**Dogana.** Se il pacco arriva da fuori UE e contiene merci comprate online, dal 1° luglio 2026 si applica un dazio UE di 3 € per tipo di prodotto sugli ordini fino a 150 €. Dal 1° novembre 2026 è dovuta anche la tariffa di gestione dell'Unione di 2 € per tipo di prodotto, indipendentemente dal valore. Le condizioni di Packlink prevedono che dazi e imposte d'importazione spettino al destinatario, ma non precisano come vengano riscossi questi due importi. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Packlink in ritardo, danneggiato o restituito

**Chi può agire.** Secondo le condizioni di Packlink solo chi ha prenotato e pagato può aprire reclami e richieste, dall'indirizzo email usato per la prenotazione. Un terzo accede a un reclamo solo con dichiarazione giurata del mittente che lo autorizza. Se sei l'acquirente, tocca quindi al venditore contattare Packlink.

**Nessun aggiornamento.** Finché appare `Waiting for carrier to collect the order`, il corriere non ha ritirato il pacco: chiedi al mittente se lo ha consegnato. Per qualsiasi ritardo o assenza di aggiornamenti è il mittente a contattare Packlink. Altro in [perché il tracking smette di aggiornarsi](guide:tracking-not-updating).

**Smarrito.** Packlink gestisce un reclamo per smarrimento solo dopo che il corriere ha cercato nei propri depositi e lo ha dichiarato smarrito. Tale ricerca richiede di solito fino a 30 giorni dalla data prevista di consegna. Vedi [pacchi smarriti](guide:lost-parcel).

**Danneggiato.** Se alla consegna ci sono danni visibili, scrivi *Con Daños* (con danni) sulla bolla o sul palmare del corriere, come chiede Packlink. Senza tale riserva, afferma Packlink, il reclamo non può essere gestito in modo soddisfacente. Conserva oggetto e imballaggio completo per l'ispezione: Packlink respinge il reclamo se l'oggetto è stato manomesso o riparato o se l'imballaggio è stato buttato. Per smarrimento o danni occulti, il mittente deve aprire reclamo entro 7 giorni per il trasporto stradale (in Spagna o all'estero), 10 giorni per via aerea nazionale e 14 giorni per via aerea internazionale, dal giorno successivo alla consegna. Vedi [pacchi danneggiati](guide:damaged-parcel).

**Restituito.** `Package returned to sender` significa che il pacco è tornato al mittente. Chiedigli una nuova spedizione o un rimborso. Vedi [restituito al mittente](guide:returned-to-sender).

**Consegnato ma non c'è.** Verifica con i vicini e al punto di ritiro, poi avvisa subito il mittente. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Numero di telefono e contatti Packlink

Canali indicati da Packlink:

- **Telefono:** nessuno. Packlink dichiara di operare solo online e di non offrire supporto telefonico. Avverte che eventuali numeri telefonici di Packlink presenti su altri siti non sono veri né verificati da Packlink: non chiamarli.
- **Modulo di contatto:** sul centro assistenza di Packlink, collegato dalla sua pagina telefono. Scegli la categoria giusta e, per una spedizione già effettuata, tieni pronto il riferimento dell'email di conferma. Packlink offre anche chat dal vivo con il servizio clienti.
- **Reclami:** tramite lo stesso centro assistenza, solo da parte di chi ha effettuato la prenotazione.

## Tracciare un pacco Packlink con Peek

Incolla il riferimento Packlink in [Peek](/). Il formato appartiene solo a Packlink, quindi la schermata di aggiunta indica subito Packlink come «Corriere rilevato». Puoi anche incollare direttamente il codice del corriere: Peek segue oltre 4.500 corrieri. Se quel codice corrisponde a più corrieri — come le 14 cifre di SEUR — Peek mostra «Verifica dei servizi di tracciamento…» mentre li interroga; premi «Cambia» per scegliere il corriere manualmente.

Finché hai il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek interroga direttamente Packlink fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Madrid nel giorno in cui il pacco va in consegna, finché il tracciamento di Packlink risponde; altrimenti ogni ora. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità in «Etichetta creata», ogni 6 ore. Dopo quattro giorni senza scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Packlink.» e il link «Chiedi aiuto a Packlink». Mostra ogni scansione nelle parole del corriere quando Packlink le fornisce, altrimenti nella riga inglese di Packlink, letta sull'ora di Madrid e convertita nel fuso orario del tuo dispositivo. Per una riga di punto di ritiro Peek mostra «Pronto per il ritiro»; per `RECIBIDO EN ORIGEN`, «Preso in carico». Il giorno stimato di consegna di Packlink appare durante il trasporto e una spedizione multicollo compare come un'unica cronologia. Peek omette le località — quindi non c'è mappa — e il numero di colli. Il tracciamento di Packlink non indica peso, mittente né destinatario.

Il tracciamento di Packlink contiene anche il codice del corriere. Quando il formato di quel codice individua con certezza un corriere — come un codice UPS che inizia con 1Z — Peek interroga anche quel corriere. Appena mostra una scansione almeno recente quanto l'ultima di Packlink, Peek segue il pacco lì conservando la cronologia di Packlink: la pagina mostra «Consegna con UPS» ed entrambi i codici nei dettagli. Nessun formato di SEUR o Correos Express è altrettanto univoco, quindi i loro pacchi restano sul tracciamento di Packlink.

Peek può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna», ma non può aprire reclami, modificare la consegna né contattare nessuno al posto tuo.

## Domande sul tracking Packlink

### Packlink è un corriere?

No. Packlink è una piattaforma dove privati e negozi confrontano e prenotano corrieri. Il corriere scelto — come SEUR, Correos Express o UPS — ritira e consegna il pacco.

### Qual è il numero di telefono di Packlink?

Nessuno: Packlink lavora solo online e avverte che eventuali numeri presenti su altri siti non sono verificati da loro. Usa il modulo del centro assistenza o la chat dal vivo.

### Dove trovo il mio numero di tracciamento Packlink?

Nell'email di conferma inviata da Packlink alla prenotazione: due lettere come ES, l'anno, COM o PRO e dieci cifre. Se sei l'acquirente, chiedilo al mittente.

### Perché il mio tracking Packlink dice Expired?

`Expired, there is no tracking information for the last 30 days` significa che il corriere non ha fornito notizie per un mese e Packlink ha smesso di seguire il pacco. Non vuol dire che sia smarrito. Chiedi al mittente di contattare Packlink.

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – tracciamento con riferimento Packlink o codice corriere, riferimenti che iniziano con ES, corrieri elencati, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – confronto e prenotazione corrieri, etichette, integrazioni Packlink PRO con eBay e Amazon
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – comparatore, nessun supporto telefonico, numeri non verificati su altri siti, modulo di contatto, chat dal vivo, riferimento nell'email di conferma
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – società a Madrid, solo chi prenota può reclamare, domicilio o punto di ritiro, avvisi, tentativi e costi, termine di ritiro, pacchi rifiutati, dazi d'importazione, termini di reclamo, riserve per danni, ricerca di 30 giorni
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per tipo di prodotto fino a 150 € dal 1° luglio 2026, tariffa di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: guida sul dazio di 3 € e la tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per tipo di prodotto qualunque sia il valore, per beni venduti a distanza
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato di riferimento, formati dei corrieri, diciture e codici di stato, ora di Madrid, ciò che Peek legge dal tracciamento Packlink, passaggio al corriere
:::
