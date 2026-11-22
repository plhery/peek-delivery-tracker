---
title: Tracciamento Border Express (BEX): codici e stati
description: Traccia un pacco Border Express (BEX) in Australia: numeri di spedizione e codici a barre dell’etichetta, significato degli stati e uso con Peek.
slug: tracciamento-border-express
published: 2026-11-22
updated: 2026-11-22
---

Border Express, o BEX, è un corriere che il catalogo Peek elenca solo in Australia. Se un negozio online ha spedito il tuo ordine con Border Express, il numero da tracciare è il numero di spedizione (*consignment number*): incollalo nella casella qui sopra. Il negozio lo fornisce nell’e-mail di spedizione o nella pagina dell’ordine. Il tracker ufficiale di Border Express è Quick Track su BEX Online (`bexonline.borderexpress.com.au`). Poiché Peek non ha potuto verificare il sito web di Border Express per questa pagina, quanto segue si basa su ciò che il codice di Peek conosce di quel tracker.

## Numero di tracciamento Border Express: com’è fatto

Border Express traccia una consegna tramite il numero di spedizione (*consignment number*). Il codice di Peek conosce tre formati. Con caratteri inventati:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `48291736` | 8 cifre: un numero di spedizione. |
| `KLMN482917` | 4 lettere (prefisso del mittente) seguite da cifre: anch’esso un numero di spedizione. Peek accetta da 5 a 8 cifre dopo le lettere. |
| `KLMNBX00000482917001` | 20 caratteri: il codice a barre sull’etichetta di un singolo collo. Le 4 lettere del mittente, `BX`, le cifre della spedizione portate a 11 con zeri iniziali e un numero di collo a 3 cifre. |

Una spedizione può comprendere più colli, ciascuno con un proprio codice a barre sull’etichetta. Tutte le etichette iniziano con le stesse lettere e cifre, e il numero di spedizione le lega insieme: è ciò che cerca il tracker di Border Express, che non accetta codici a barre di etichetta. Peek invece li accetta e ricava da essi il numero di spedizione (vedi sotto).

Peek non riconosce un codice Border Express dalla sola forma: il catalogo non ha regole per i numeri di spedizione Border Express, e la regola per il codice a barre d’etichetta è solo una corrispondenza debole. Altri corrieri usano le stesse forme: per 8 cifre Peek suggerisce [Mondial Relay](carrier:mondial-relay), Colisweb e [Heppner](carrier:heppner), tutti in Europa, oltre a TForce Freight (merci in Nord America) quando l’ultima cifra supera il controllo. Quattro lettere seguite da 8 cifre corrispondono anche ai codici da 12 caratteri di Colis Privé. Se il negozio ha indicato Border Express, scegli Border Express manualmente (sotto). Spazi e minuscole non contano quando incolli il codice. Non hai alcun numero? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento Border Express spiegati

Il tracker Border Express mostra una barra di avanzamento e un elenco di passaggi dal più recente al più vecchio, ciascuno con località e stato federato. I passaggi noti al codice di Peek, nell’ordine consueto:

| Border Express scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipment Data Received` | Il mittente ha inviato a Border Express i dati del pacco. Border Express non ha ancora il pacco. La barra di avanzamento mostra `Data Received`. | «Etichetta creata» |
| `Picked Up` | Border Express ha ritirato il pacco dal mittente. Il tracker di solito riporta questo passaggio senza orario, e Peek tralascia i passaggi senza orario. | «Preso in carico», solo quando il passaggio ha un orario |
| `In Transit` | In viaggio nella rete Border Express. | «In transito» |
| `On Board For Delivery` | Sul veicolo di consegna. La barra di avanzamento mostra `On Board`. | «In consegna» |
| `Partially Delivered` | Alcuni colli della spedizione sono consegnati; gli altri sono ancora in viaggio. | «In transito» |
| `Partially Delivered - Final` | La consegna è chiusa: la spedizione termina con i colli arrivati. Se ne manca qualcuno, chiedi al mittente. | «Consegnato» |
| `Delivered` | Consegnato. | «Consegnato» |

Peek mantiene i passaggi nell’inglese di Border Express, con il proprio stato sopra. Un passaggio fuori da questa tabella appare così com’è scritto e Peek ne deduce lo stato dalle parole quando possibile.

## Consegna Border Express in Australia

Il tracker Border Express non nomina altri corrieri per una spedizione, perciò Peek segue il pacco su Border Express dal primo passaggio alla consegna, senza passarlo a un altro corriere.

Questa pagina non indica regole su tentativi di consegna, avvisi, consegna senza firma, ritiro in deposito o giacenza, perché Peek non ha potuto verificarle sul sito Border Express. Chiedi al negozio mittente o a Border Express. Vedi [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

## Pacco Border Express che non si aggiorna, smarrito o consegnato ma assente

**Numero non trovato.** Finché il tracker Border Express non riconosce il numero, un pacco registrato sotto Border Express mostra «In attesa di Border Express» e Peek continua a controllare. Se dopo due giorni Border Express non lo conosce ancora e nessun’altra fonte risponde, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a controlli giornalieri, poi settimanali dopo una settimana. Controlla il numero nell’e-mail del negozio: 8 cifre, oppure 4 lettere e 5–8 cifre. Se dopo qualche giorno non compare nulla, chiedi al negozio se è stato davvero spedito.

**Fermo su «Etichetta creata».** Border Express ha i dati del pacco ma ancora nessuna scansione datata. Il suo tracker potrebbe mostrare `Picked Up` senza orario, che Peek omette: controlla lì prima. Se mostra solo `Shipment Data Received`, chiedi al negozio quando lo ha affidato.

**Nessun aggiornamento da giorni.** Quando un pacco in viaggio resta quattro giorni senza nuovi passaggi, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Border Express.», con il link «Chiedi aiuto a Border Express» alla pagina di assistenza clienti. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato parzialmente.** Controlla quanto ricevuto rispetto all’ordine. Il resto della spedizione è ancora in viaggio e il tracker Border Express mostra le scansioni di ogni collo, che Peek omette.

**Consegnato, ma non è arrivato nulla.** Controlla intorno al tuo indirizzo e chiedi ai vicini. Una volta consegnato, Peek legge anche il nome del firmatario se indicato dal tracker Border Express. Poi contatta il negozio. Altro in [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito, danneggiato o restituito.** È il negozio ad aver prenotato Border Express: rivolgiti al negozio per un rimborso o una nuova spedizione. Vedi [pacchi smarriti](guide:lost-parcel), [pacchi danneggiati](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti Border Express

Poiché Peek non ha potuto verificare il sito Border Express, questa pagina non elenca telefono né e-mail: Peek riporta solo recapiti letti sulle pagine ufficiali di un corriere. Il negozio ha prenotato la consegna, quindi rivolgiti prima a lui.

Sulla scheda del pacco in Peek, «Apri il sito Border Express» apre il codice sul tracker Border Express. Prima di pagare importi richiesti da un SMS sui pacchi, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Border Express con Peek

Incolla il codice in [Peek](/), che segue oltre 4.500 corrieri. Può mostrare «Verifica dei servizi di tracciamento…» mentre chiede ad altre fonti. Tocca poi «Cambia» e scegli Border Express digitando `Border Express` nella ricerca (anche `BEX` lo trova, ma elenca prima BEX Express, un corriere serbo). Per un codice a barre d’etichetta, Border Express appare sotto «Compatibili con questo numero». Su un pacco già aggiunto usa «Cambia corriere». Non servono account, codice postale né telefono.

Se aggiungi il codice senza scegliere un corriere, Peek interroga solo i servizi di tracciamento e mai Border Express, mostrando «Nessuna cronologia di tracciamento per ora» finché uno non riconosce il pacco. Solo se un servizio di tracciamento nomina Border Express Peek prosegue interrogando Border Express. Scegliere Border Express di persona è quindi il modo diretto per avere i suoi passaggi.

Anche un link Border Express funziona e identifica subito Border Express («Corriere rilevato»): `borderexpress.com.au/tracking` con `cn=`, oppure un link `bexonline.borderexpress.com.au/bxo/track`. Se scegli Border Express per un codice di forma diversa, Peek indica «non emette numeri come questo», e dopo l’aggiunta (se nessun servizio di tracciamento ha il pacco) mostra «Controlla il numero di tracciamento»: cerca eventuali caratteri mancanti o in più.

**Codici a barre d’etichetta.** Il tracker Border Express non li accetta, quindi Peek ricava il numero di spedizione dal codice a barre: le quattro lettere, poi le 11 cifre dopo `BX` senza gli zeri iniziali (mantenendo almeno sei cifre) e scartando le ultime 3 cifre del collo. La scheda mostra quel numero sotto «Border Express lo traccia con il numero» e «Apri il sito Border Express» apre Quick Track con quel numero di spedizione. Se Border Express non lo trova così, usa il numero di spedizione presente nell’e-mail del negozio.

**Cosa vedi.** Peek mostra lo stato, ogni passaggio datato con località e stato federato, il giorno previsto di consegna fino alla consegna, l’ora di consegna, la località del destinatario (sobborgo, stato e codice postale) e il firmatario una volta consegnato. Tralascia barra di avanzamento, data di spedizione, numero di colli con le loro singole scansioni e località del mittente; non mostra peso né prova di consegna. Ogni passaggio conserva l’orario ufficiale del proprio stato federato, così i passaggi su fusi orari diversi restano nell’ordine corretto e compaiono nel tuo fuso («Orari nel tuo fuso orario:»).

**Frequenza.** Mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente Border Express fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Sydney nel giorno in cui Border Express lo scansiona in consegna, finché il suo tracker risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), ogni ora. Se il tracker Border Express non è raggiungibile, Peek interroga i servizi di tracciamento e mostra quanto riportano, nelle loro parole. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Border Express né il negozio per te.

## Domande sul tracciamento Border Express

### Che cos’è un numero di spedizione Border Express?

Il codice con cui Border Express traccia una consegna: 8 cifre, oppure le 4 lettere del mittente seguite da cifre. Ogni collo della consegna ricade sotto questo numero, indicato dal negozio nell’e-mail di spedizione o nella pagina dell’ordine.

### Posso tracciare Border Express con il codice a barre dell’etichetta?

In Peek sì: incolla il codice a barre di 20 caratteri (4 lettere, `BX` e 14 cifre) e scegli Border Express. Peek ne ricava il numero di spedizione. Il tracker ufficiale Border Express accetta invece solo il numero di spedizione.

### Cosa significa «On Board For Delivery» su Border Express?

Il pacco è a bordo del veicolo di consegna. Peek lo mostra come «In consegna».

### Perché il tracciamento Border Express indica «Partially Delivered»?

La spedizione comprende più colli e solo alcuni sono stati consegnati; gli altri sono ancora in viaggio, perciò Peek mostra «In transito». `Partially Delivered - Final` è diverso: Border Express ha chiuso la spedizione con i colli arrivati e Peek mostra «Consegnato».

### Cosa significa «Shipment Data Received» su Border Express?

Il mittente ha inviato a Border Express i dati del pacco, ma Border Express non ha ancora effettuato scansioni. Peek mostra «Etichetta creata».

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – nomi, Australia, numeri di spedizione e codici a barre d’etichetta, link di tracciamento, Quick Track, diciture e fasi, passaggi senza orario, fusi statali, stima e ora di consegna, località destinatario e firmatario, campi omessi, nessun altro corriere
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati di altri corrieri (Mondial Relay, Colisweb, Heppner, Colis Privé), nome di BEX Express, nessun passaggio da o verso Border Express
:::
