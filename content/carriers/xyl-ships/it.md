---
title: Tracciamento XYL-Ships: codici NUDE e XLWL e stati
description: Traccia un pacco XYL-Ships (FXYL) dalla Cina: formati NUDE e XLWL, significato degli stati di transito e consegna, pause, dogana UE e chi contattare.
slug: tracciamento-xyl-ships
published: 2026-11-24
updated: 2026-11-24
---

XYL-Ships (XYL-ships International Logistics, indicato anche come FXYL) è un operatore cinese di logistica transfrontaliera con qualifiche di agenzia di trasporto aereo riconosciute dalla Civil Aviation Administration of China (CAAC) e dalla IATA. Oltre al trasporto commerciale via aria, mare, ferrovia e strada, movimenta piccoli pacchi e-commerce singoli dalla Cina verso destinazioni internazionali e li affida a poste o corrieri locali per la consegna finale. Puoi controllare una spedizione tramite l’opzione «Track Order» su [xylships.com](https://xylships.com/), oppure incollare il codice di tracciamento nel riquadro qui sopra.

## Codici di tracciamento XYL-Ships: formati NUDE e XLWL

Cerca il numero di spedizione nell’e-mail di spedizione del negozio online o nella pagina dei tuoi ordini. I piccoli pacchi XYL-Ships usano solitamente un codice alfanumerico di 16 o 17 caratteri che inizia con `NUDE` o `XLWL` e termina con `EX` o `EXP`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `NUDE4829173640EX` | `NUDE`, 10 cifre ed `EX`. |
| `XLWL4829173640EX` | `XLWL`, 10 cifre ed `EX`. |
| `XLWL4829173640EXP` | `XLWL`, 10 cifre ed `EXP`. |

Poiché questi codici sono esclusivi di XYL-Ships, non si confondono con i codici a barre delle poste nazionali. Se il negozio ti ha fornito invece un codice postale internazionale S10 (due lettere, nove cifre e due lettere del paese come `CN`) o il codice di un altro corriere, consulta [formati dei numeri di tracciamento](guide:tracking-number-formats) per capire a chi appartiene.

## Cosa significano gli stati di tracciamento XYL-Ships

XYL-Ships pubblica gli aggiornamenti in inglese, seguendo il pacco dallo smistamento in Cina al trasporto internazionale fino al partner locale di distribuzione nel paese di destinazione:

| XYL-Ships mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipment inbound in sorting center` | XYL-Ships ha ricevuto e scansionato il pacco nel centro di smistamento di origine. | «In transito» |
| `Departed from sorting center`, `Arrived at sorting warehouse`, `Leaving warehouse` | In movimento tra i magazzini di smistamento in Cina prima della consegna al vettore di linea. | «In transito» |
| `Hand over to transportation company`, `Departed from origin transportation center`, `Arrived at destination transportation center` | Consegnato al vettore internazionale, partito dalla Cina e arrivato al centro d’ingresso del paese di destinazione. | «In transito» |
| `Released and the package is being transferred`, `Package is being transferred locally` | Sdoganato nel paese di destinazione e avviato al trasferimento nazionale. | «In transito» |
| `Transfer again, send to the transportation partner department`, `Arrival at transportation partner department, waiting for processing`, `Leaving transportation partner department, sent to the transit hub` | Trasferito al reparto operativo del partner locale e spedito verso il relativo hub di transito. | «In transito» |
| `Arriving at the transit hub, waiting for distribution`, `Leave the transit hub, transport to the destination city`, `Arriving at the destination city, about to distribute to the post office` | Smistato nell’hub regionale e trasportato verso la tua città per la presa in carico locale. | «In transito» |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | Il corriere locale ha preso in carico il pacco, ma il giro di consegne non è ancora iniziato. | «In transito» |
| `Parcel currently being delivered` | In consegna oggi con il corriere locale. | «In consegna» |
| `Delivered` | Registrato come consegnato a destinazione. | «Consegnato» |

Fai attenzione a due diciture facili da fraintendere nella parte finale. `Arriving at the destination city, about to distribute to the post office` indica che il pacco sta passando alla rete postale o al partner locale, non che ti aspetti già allo sportello di un ufficio postale. Allo stesso modo, `The courier has received this parcel for delivery and will set a delivery time shortly` resta in «In transito» perché la consegna non è ancora partita: attendi `Parcel currently being delivered` per aspettarti l’arrivo in giornata.

## Come funziona la consegna internazionale XYL-Ships

Su [xylships.com](https://xylships.com/), XYL-Ships presenta trasporto aereo commerciale (incluso il trasporto aereo interno rapido da 6 e 12 ore e il consolidamento FBA porta a porta), consolidamento marittimo, trasporto ferroviario e stradale, oltre alla gestione transfrontaliera di piccoli pacchi e-commerce singoli — citando un caso cliente con il 99% di consegne completate entro 20 giorni.

**Consegna locale dell’ultimo miglio.** Come mostrano le stesse righe di tracciamento XYL-Ships, arrivato nel paese di destinazione il pacco viene trasferito a un partner locale o al servizio postale per la consegna finale.

**Regole di consegna non pubblicate.** `xylships.com` non pubblica il numero di tentativi di consegna, regole sui locker, giorni di giacenza, garanzie sui tempi o termini di reclamo. Una volta entrato nel tuo paese, le modalità pratiche (consegna al vicino, deposito in luogo sicuro o giorni di ritiro con avviso) dipendono dal servizio postale o dal corriere locale.

**Dazi di importazione e dogana.** Poiché le spedizioni XYL-Ships partono dalla Cina, i pacchi diretti nell’UE, nel Regno Unito, negli Stati Uniti o in altre aree sono soggetti all’IVA di importazione e alle regole doganali locali. Per i pacchi che entrano nell’Unione Europea si applica un dazio doganale fisso di 3 € per tipologia di articolo fino a 150 €, più una commissione UE di gestione di 2 € per tipologia di articolo a prescindere dal valore. Consulta [pacchi fermi in dogana](guide:customs) per sapere come i controlli doganali incidono sulla consegna.

## Pacco XYL-Ships bloccato, fermo o non ricevuto

Se hai acquistato su un negozio online che spedisce tramite XYL-Ships, il venditore è la tua controparte contrattuale e il primo riferimento per ritardi, rimborsi o sostituzioni.

**Lunghi silenzi in transito.** Pause di diversi giorni sono normali tra `Departed from origin transportation center` e `Arrived at destination transportation center` mentre i pacchi attendono il volo, lo sdoganamento e la scansione d’ingresso nel paese di destinazione. Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Nessun codice locale in Peek.** Peek segue il pacco anche presso il partner locale usando il codice `NUDE` o `XLWL` e non mostra un riferimento locale separato. Se la consegna fallisce e ti serve il nome o il codice del corriere locale, chiedilo al venditore.

**Risulta `Delivered` ma non si trova.** Controlla prima cassetta postale, ingresso, portineria e vicini. Se non compare comunque, avvisa subito il venditore e chiedi la prova di consegna. Consulta [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato, smarrito o restituito.** Conserva le foto dell’imballaggio danneggiato e contatta subito il negozio, oppure apri una contestazione di protezione acquisti sul marketplace o sul metodo di pagamento prima della scadenza. Consulta [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Contattare XYL-Ships (e perché chiedere prima al venditore)

`xylships.com` è un sito aziendale di spedizioni con pochissime informazioni di assistenza per i privati:

- **E-mail:** `fzxyl9527@163.com` (unico recapito indicato nel piè di pagina di [xylships.com](https://xylships.com/)).
- **Telefono, indirizzo postale e portale reclami:** nessuno pubblicato su `xylships.com`.

Poiché XYL-Ships lavora con mittenti commerciali anziché con singoli destinatari, rivolgiti prima al negozio online o al marketplace per pacchi mancanti, errori di indirizzo o rimborsi: il commerciante ha accesso diretto al proprio account di spedizione e alle procedure di tutela dell’acquirente.

> Diffida di SMS o e-mail inattesi che chiedono di aprire un link di pagamento per «sbloccare» o «riprogrammare» un pacco XYL-Ships. Verifica eventuali oneri doganali direttamente presso le poste o la dogana del tuo paese, mai tramite un messaggio non verificato. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco XYL-Ships con Peek

[Peek](/) riconosce automaticamente i codici `NUDE` (`NUDE` + 10 cifre + `EX`) e `XLWL` (`XLWL` + 10 cifre + `EX` o `EXP`) come XYL-Ships, con alta affidabilità. Poiché XYL-Ships non utilizza URL dedicati in Peek, incolla direttamente il numero anziché un link; se dovessi scegliere il corriere manualmente, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e scegli XYL-Ships (anche cercando «FXYL»).

Peek legge direttamente il feed JSON di XYL-Ships e mostra la fase («In transito», «In consegna» o «Consegnato»), le descrizioni in inglese di XYL-Ships, la località quando è presente nel feed e gli orari con fuso orario esplicito (mostrati nell’ora del tuo dispositivo). Peek elimina indirizzi di mittente e destinatario e riferimenti interni del cliente. Non mostra data prevista, peso, numero del corriere locale o foto di consegna.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui XYL-Ships mostra `Parcel currently being delivered`, ogni 2 minuti dalle 6:00 secondo l’orario dell’ultima scansione di XYL-Ships, finché il sistema di XYL-Ships risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua. Quando il tracciamento di XYL-Ships non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a XYL-Ships.» Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a XYL-Ships.»

Puoi scegliere tra le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo il tracciamento e non può indicare il corriere locale né contattare XYL-Ships al posto tuo.

## Domande sul tracciamento XYL-Ships

### Che cos’è XYL-Ships (FXYL)?

XYL-Ships International Logistics è uno spedizioniere cinese e operatore e-commerce transfrontaliero con qualifiche aeree CAAC e IATA. I negozi lo usano per spedire piccoli pacchi dalla Cina prima di affidarli ai corrieri locali nel tuo paese.

### Cosa significano i codici `NUDE...EX` e `XLWL...EX`?

Entrambi i prefissi sono formati XYL-Ships: `NUDE` o `XLWL` seguiti da 10 cifre ed `EX` (o `EXP` per alcuni pacchi `XLWL`). Incolla il codice completo in Peek o nel riquadro Track Order su [xylships.com](https://xylships.com/).

### Perché il tracciamento XYL-Ships dice `about to distribute to the post office`?

Significa che il pacco è arrivato nella tua città di destinazione ed è in fase di passaggio alle poste o al partner locale per l’ultimo miglio. Attendi `Parcel currently being delivered` prima di aspettarti la consegna in giornata.

### Chi consegna i pacchi XYL-Ships nel mio paese?

XYL-Ships trasferisce i pacchi a un partner locale o al servizio postale dopo l’ingresso nel paese di destinazione e non ne indica il nome nel tracciamento; Peek non mostra un secondo codice. Se la consegna non riesce, chiedi al venditore i dettagli del corriere locale.

:::sources
- [XYL-Ships International Logistics](https://xylships.com/) – qualifiche di agenzia di trasporto aereo CAAC e IATA, servizi aerei/marittimi/ferroviari/stradali e piccoli pacchi e-commerce transfrontalieri, accesso Track Order ed e-mail `fzxyl9527@163.com`
- [Universal Parcel Scraper: xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – rilevamento formati `NUDE` e `XLWL`, alias FXYL, integrazione JSON, mappatura stati in inglese, campi scartati e frequenza di controllo
:::
