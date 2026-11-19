---
title: Tracciamento R+L Carriers: numero PRO, stati e reclami
description: Traccia una spedizione merci R+L Carriers negli USA: formato del numero PRO, significato di ogni stato e regole federali per ritardi, danni o smarrimento.
slug: tracciamento-rl-carriers
published: 2026-11-18
updated: 2026-11-20
---

R+L Carriers è uno spedizioniere merci su strada negli **Stati Uniti**. Nel codice di Peek ritira la merce presso il mittente, la trasferisce da un terminal all'altro e la consegna a partire dal terminal di destinazione negli USA. Se un ordine ingombrante o un'altra spedizione merci ti sta arrivando negli Stati Uniti con R+L, la segui tramite il numero PRO comunicato dal negozio o dall'azienda che ha prenotato il trasporto: incollalo nel riquadro in alto. Il sistema di ricerca diretto di R+L è la pagina *Shipment Tracing* su rlcarriers.com: accetta un numero PRO o un identificativo di ritiro (*Pickup Request ID*), fino a 25 numeri contemporaneamente, e con un account MyRLC anche altri codici di riferimento.

## Numero di tracciamento R+L Carriers: il numero PRO

Il portale di tracciamento di R+L cerca le spedizioni tramite il numero PRO. Peek riconosce questi formati, mostrati qui con cifre inventate:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `48291057-3` | Nove cifre di cui l'ultima è una cifra di controllo, scritta qui dopo un trattino. |
| `482910573` | Lo stesso numero PRO scritto senza trattino. |
| `RL482910-5` | Due lettere e sette cifre, con l'ultima come cifra di controllo. Può essere scritto anche senza trattino (`RL4829105`). |
| `I482910573` | Un numero PRO come lo scrive il sistema di R+L, preceduto da una `I`. La `I` non fa parte del numero: Peek accetta il PRO con o senza di essa. |

Il sistema di R+L verifica l'ultima cifra e segnala eventuali errori di battitura (vedi sotto). Peek rimuove spazi e trattini prima di interrogare R+L. Nel suo catalogo conosce R+L Carriers anche come R&L Carriers, R and L Carriers e RL Carriers.

**Dove trovarlo.** Nell'email di spedizione del negozio o nella pagina dell'ordine; per merci spedite tra aziende, chiedilo all'impresa mittente. Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Altri corrieri con lo stesso formato.** Nove cifre da sole corrispondono anche a [TNT](carrier:tnt), TForce Freight e XPO, mentre una `I` seguita da nove cifre corrisponde ad Australia Post. Se la notifica di spedizione indica uno di questi operatori, traccia la merce lì. Approfondisci in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di R+L Carriers

Il tracciamento di R+L mostra un riepilogo della spedizione sopra la cronologia e sotto elenca ogni passaggio con data e ora. Indica i terminal statunitensi con città e sigla dello Stato. Le righe non hanno codici numerici: Peek ne legge la dicitura in inglese. Nell'ordine in cui una spedizione li incontra negli **Stati Uniti** (i puntini sostituiscono città e Stato, oppure un terminal):

| R+L scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Picked Up in …` | R+L ha ritirato la merce dal mittente, nella sua località. Peek conserva la frase intera senza usare la città del mittente come luogo della scansione. | «Preso in carico» |
| `Arrived at origin terminal` | Il carico ha raggiunto il terminal R+L nella zona del mittente. | «In transito» |
| `Departed … in route to …` | Ha lasciato un terminal diretto al successivo o al terminal di destinazione. | «In transito» |
| `Arrived at …` | È arrivato a un terminal lungo il percorso, identificato da città e Stato USA. | «In transito» |
| `Arrived at destination terminal` | Si trova nel terminal della tua zona, ultima tappa prima della consegna. | «In transito» |
| `Shipment Delayed` | Qualcosa ha rallentato il trasporto. Il riepilogo di R+L definisce allora la spedizione in ritardo (*Delayed*) mantenendo il giorno di consegna previsto. | «In transito» |
| `Delivered` | Consegnato. | «Consegnato» |

**Il riepilogo sopra la cronologia.** In cima alla pagina, il sistema di R+L classifica la spedizione come *In Transit*, *Delayed* o *Delivered*. Quando il riepilogo è più avanzato rispetto alle singole righe, Peek segue il riepilogo: una spedizione riassunta come consegnata appare «Consegnato» anche se l'ultima riga non lo riporta ancora. Se il portale mostra il riepilogo ma nessuna riga di storico, Peek visualizza solo lo stato.

Il codice di Peek non prevede diciture specifiche di R+L per «In consegna», mancata consegna, anomalia o reso. Eventuali altre formulazioni seguono le regole generali che Peek applica a tutti i corrieri. Una riga non riconosciuta da alcuna regola resta visibile così com'è scritta e vale come movimento della merce («In transito») prima della consegna; dopo la consegna non modifica più nulla. Vedi [stati di tracciamento spiegati](guide:tracking-statuses).

## Come consegna R+L Carriers negli Stati Uniti

Mentre la merce viaggia negli **Stati Uniti**, il sistema di R+L fornisce un giorno previsto di consegna che Peek mostra fino all'avvenuto recapito. Il sistema di R+L non indica alcun corriere terzo per la consegna finale e il codice di Peek non passa la spedizione a nessun altro operatore. Tutte le informazioni di questa guida riguardano trasporti merci negli Stati Uniti.

Questa pagina non riporta fasce orarie, appuntamenti di scarico, numero di tentativi né regole per il fermo deposito presso un terminal USA. Chiedi al negozio o all'azienda che ha prenotato il trasporto come e quando avverrà la consegna: sono loro i titolari della spedizione presso R+L.

## Spedizione R+L Carriers non trovata, in ritardo, danneggiata o smarrita

**Non trovata.** Per un numero PRO sconosciuto al sistema, R+L risponde `No records found for Number:` seguito dal PRO preceduto da `I`. Peek mostra «In attesa di R+L Carriers» per un PRO salvato sotto R+L Carriers e riprova più tardi. Controlla ogni cifra confrontandola con l'email del venditore; se il codice è esatto, chiedi al mittente se R+L ha già ritirato il carico.

**Cifra errata.** Se la cifra di controllo di un PRO non torna, il sistema di R+L restituisce il PRO seguito da `Pro number failed check digit validation.` Peek mostra quindi «Controlla il numero di tracciamento». Verifica il codice cifra per cifra con la conferma del negozio o i documenti di trasporto.

**In ritardo o fermo.** Dopo `Shipment Delayed`, Peek lascia la spedizione «In transito» con la data stimata da R+L. Trascorsi quattro giorni senza nuove righe su una spedizione «Preso in carico» o «In transito», Peek segnala «Nessun aggiornamento da quattro giorni». Contatta il negozio o il mittente. Consulta anche [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Merce danneggiata, mancante o smarrita negli USA.** Avvisa per primo il venditore o l'azienda mittente che ha commissionato il trasporto. Per le merci trasportate su gomma **tra Stati degli USA**, la normativa federale degli **Stati Uniti** (`49 U.S.C. 14706` e `49 CFR Part 370`) stabilisce queste regole:

- Il vettore risponde della perdita o del danno effettivo subito dalla merce nei confronti del soggetto legittimato dalla ricevuta o dalla polizza di carico (*bill of lading*). La responsabilità può essere limitata a un valore dichiarato per iscritto o elettronicamente dal mittente, oppure concordato per iscritto tra vettore e mittente.
- Il vettore non può prevedere meno di 9 mesi per presentare reclamo né meno di 2 anni per agire in giudizio, calcolati dal giorno in cui comunica per iscritto il rigetto anche parziale della richiesta. Il termine effettivo è fissato nella polizza di carico o nel contratto.
- Un reclamo consiste in una richiesta scritta presentata al vettore entro tali termini che identifichi la spedizione, attribuisca la responsabilità al vettore e domandi un importo di denaro determinato o determinabile. Una semplice annotazione di danno o ammanco sulla ricevuta di consegna non costituisce da sola un reclamo.
- Quando l'istruttoria lo richiede, al reclamo vanno allegati la polizza di carico, la prova delle spese di trasporto e la fattura. Per la perdita di un intero collo o dell'intera spedizione non accertabile altrimenti, il vettore acquisisce dal destinatario (*consignee*) una dichiarazione scritta certificata che attesti che la merce non è pervenuta da nessun'altra fonte.
- Il vettore conferma per iscritto la ricezione del reclamo entro 30 giorni. Entro 120 giorni paga, respinge o formula per iscritto un'offerta ferma di transazione; se non può ancora chiudere la pratica, comunica per iscritto lo stato della richiesta a tale scadenza e poi ogni 60 giorni.

Se il tracciamento riporta `Delivered` ma non è arrivato nulla, leggi [consegnato ma non ricevuto](guide:delivered-not-received). Vedi anche [pacco danneggiato](guide:damaged-parcel) e [pacco smarrito](guide:lost-parcel).

## Assistenza clienti e contatti R+L Carriers

Sul sito statunitense di R+L Carriers sono pubblicati questi riferimenti:

- **Telefono:** `800-543-5589`, numero riportato in alto nelle pagine di R+L.
- **R+L Cares:** modulo sul sito di R+L per problemi irrisolti, domande o segnalazioni; R+L dichiara che l'invio recapita direttamente il messaggio alla propria direzione esecutiva (*R+L Executive Leadership*).
- **Reclami merci:** il menu del sito rimanda a *Cargo Claims*, con le istruzioni di R+L per l'apertura di una pratica danni.

R+L trasporta la merce per conto di chi ha prenotato il servizio: rivolgiti prima al negozio per un acquisto online o all'azienda mittente per spedizioni commerciali.

## Tracciare una spedizione R+L Carriers con Peek

La forma di un PRO a nove cifre non basta da sola a indicare R+L, perché anche altri operatori usano nove cifre. Incollalo comunque: Peek segue oltre 3.500 corrieri e trasportatori. Per due lettere e sette cifre, Peek interroga subito R+L e mostra «R+L Carriers ha questo pacco.» quando R+L riconosce il PRO. Se digiti nove cifre con il trattino prima dell'ultima cifra, Peek elenca R+L Carriers sotto «Compatibili con questo numero» e interroga anche R+L; una volta salvata la spedizione (Peek rimuove il trattino al salvataggio) o incollando nove cifre senza trattino, Peek interroga invece TNT, TForce Freight e XPO quando superano il rispettivo controllo, ma non R+L: tocca «Cambia» e cerca R+L Carriers per nome. Su una spedizione già aggiunta usa «Cambia corriere». Se in seguito un servizio di tracciamento mostra la merce in movimento indicando R+L Carriers, Peek assegna la spedizione a R+L e interroga direttamente il sistema di R+L ai controlli successivi.

Con la `I` davanti a nove cifre, il codice è compatibile sia con R+L Carriers sia con Australia Post: Peek interroga entrambi e assegna la spedizione a chi la riconosce. Funziona anche un link alla pagina *Shipment Tracing* di R+L contenente `pro=`: Peek rileva subito R+L («Corriere rilevato»).

Peek interroga il portale di R+L con il solo numero PRO, senza account né CAP. Mostra lo stato, ogni riga di storico (con città e Stato del terminal quando la riga li nomina), il giorno di consegna previsto, il nome del destinatario nella tua scheda privata («Destinatario», nascosto nei link condivisi) e il peso nell'unità del tuo dispositivo (libbre sui dispositivi impostati sulle unità statunitensi, chilogrammi altrove; R+L fornisce libbre). Tralascia l'indirizzo del destinatario, il mittente e il soggetto fatturato, il numero di colli, i riferimenti, il tipo di servizio, il numero di polizza di carico e i documenti. Il tracciamento di R+L non fornisce a Peek alcuna prova di consegna. «Apri il sito R+L Carriers», nella scheda della spedizione, apre il PRO direttamente sul portale di R+L.

Quando il portale di R+L non è raggiungibile o non permette di leggere la spedizione, Peek consulta i [servizi di tracciamento](guide:universal-tracker) e mostra la cronologia da loro raccolta con le loro parole.

Le righe di R+L indicano data e ora senza fuso orario. Peek legge ciascuna sull'orologio della città del terminal, altrimenti su quello dello Stato del terminal (il fuso principale negli Stati USA che ne hanno più d'uno), altrimenti sulla riga precedente o successiva e in mancanza sull'ora della costa Est USA (`America/New_York`), convertendo ogni orario nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»).

Peek controlla gli aggiornamenti fino a ogni 10 minuti mentre tieni aperta la spedizione, oppure nel giorno di consegna con le notifiche attive; negli altri momenti, e dopo due giorni senza nuove righe (12 ore finché lo stato resta «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi attivare notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare R+L, il negozio o il mittente al posto tuo, fissare una consegna né presentare un reclamo.

## Domande frequenti sul tracciamento R+L Carriers

### Cos'è un numero PRO su R+L Carriers?

È il codice di tracciamento merci di R+L Carriers negli Stati Uniti: nove cifre, oppure due lettere e sette cifre, con l'ultima cifra come cifra di controllo talvolta separata da un trattino. Il portale di R+L lo scrive con una `I` iniziale. Ti viene fornito dal negozio o dall'azienda che ha spedito il carico.

### Perché R+L Carriers dice «No records found for Number»?

Il sistema di R+L non conosce quel numero PRO. Controlla ogni cifra rispetto all'email di spedizione, verifica che il vettore indicato sia davvero R+L Carriers e chiedi al mittente se R+L ha già preso in consegna la merce.

### Cosa significa «Shipment Delayed» su R+L Carriers?

Un imprevisto ha rallentato la merce lungo il tragitto negli Stati Uniti. La riga non specifica la causa; il riepilogo di R+L indica la spedizione come in ritardo conservando un giorno di consegna stimato, e Peek la mantiene «In transito». Chiedi spiegazioni al venditore o al mittente.

### Quanto tempo ho per aprire un reclamo merci con R+L Carriers?

Per il trasporto merci su strada tra Stati USA, la legge federale statunitense garantisce almeno 9 mesi per presentare reclamo scritto al vettore e almeno 2 anni per rivolgersi al giudice dopo il rifiuto scritto anche parziale della richiesta. Il termine esatto è indicato nella polizza di carico o nel contratto; questa pagina non riporta le condizioni tariffarie interne di R+L. Rivolgiti prima a chi ha prenotato il trasporto; le istruzioni di R+L si trovano sotto *Cargo Claims* sul suo sito.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – nomi, formati PRO e prefisso `I`, link di tracciamento, diciture e fasi, riepilogo, messaggi di numero sconosciuto e cifra di controllo, data prevista, destinatario, peso, fusi orari e campi esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – corrieri con formato analogo, assenza di passaggio a terzi e ricorso ai servizi di tracciamento
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – ricerca tramite numero PRO o Pickup Request ID fino a 25 spedizioni e altri riferimenti con MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – modulo indirizzato alla direzione esecutiva di R+L, numero 800-543-5589 in testata e collegamento a Cargo Claims
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilità verso l'avente diritto secondo la polizza di carico, limite di valore e termini minimi di 9 mesi e 2 anni negli USA
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – contenuto scritto del reclamo, riserve sulla ricevuta di consegna, documenti richiesti e scadenze di 30, 120 e 60 giorni
:::
