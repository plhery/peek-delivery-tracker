---
title: Tracciamento CTTYEX: codici CTTY…CN, stati e ora Pechino
description: Traccia un pacco CTTYEX (诚泰通运) da Shenzhen: formato CTTY…CN, scansioni in inglese sul tracker cinese, ora di Pechino e tracciamento con Peek.
slug: tracciamento-cttyex
published: 2026-11-21
updated: 2026-11-21
---

CTTYEX è *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, abbreviato `诚泰通运`), uno spedizioniere e-commerce transfrontaliero di Shenzhen, nella Cina continentale, che spedisce pacchi all'estero per venditori online. Il sito dichiara la fondazione nel 2011, oltre 200 Paesi e regioni serviti, più di sei filiali nella Cina continentale e spedizioni per venditori su Amazon, Wish, Tophatter, VOVA e Shopify con voli charter verso Stati Uniti, Regno Unito, Canada, Germania e Australia. I suoi numeri sono formati da `CTTY`, dieci cifre e `CN` (`CTTY` per *Chengtai Tongyun*): cerca il tuo su [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) oppure incollalo nel riquadro qui sopra.

## Numero di tracciamento CTTYEX: com'è fatto

Il sito di CTTYEX non descrive il formato dei codici, ma il suo tracker utilizza uno schema fisso (esempi inventati):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, dieci cifre e `CN`: un codice CTTYEX. |
| `CTTY482910573CN` | Solo nove cifre: manca un carattere. Se nessun corriere risponde, la pagina su `peektracker.com` mostra «Inizia come un numero di tracciamento di CTTYEX, ma manca un carattere: forse c’è un errore di battitura. Confrontalo con il messaggio di spedizione.» |
| `LX482910576CN` | Due lettere, nove cifre e `CN`: un codice [China Post](carrier:china-post) e non CTTYEX, nonostante la finale `CN`. |

Peek accetta minuscole e spazi. Il sito di CTTYEX invece no: la casella di ricerca ammette solo lettere e cifre e il tracker riconosce il numero solo in maiuscolo (`CTTY…CN`, senza spazi).

**Dove trovarlo.** I clienti di CTTYEX sono i venditori online, quindi il codice ti viene fornito dal negozio nell'email di spedizione o nella pagina dell'ordine ([come trovare il numero di tracciamento](guide:find-tracking-number)).

**Sul sito di CTTYEX.** Apri `物流查询` (`search.html`), scrivi un codice per riga in maiuscolo e premi `提交` (invia): si apre il tracker `快件跟踪` (`index/tracknumber/track`), che trova solo i pacchi degli ultimi due mesi.

## Stati del tracciamento CTTYEX spiegati

Il tracker è in cinese, ma le singole scansioni sono in inglese (dalla più recente alla più vecchia) sotto una riga di riepilogo in cinese con Paese di destinazione (`目的地`) e stato generale (`状态`):

| Riepilogo CTTYEX | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `运输途中` (`In Transit` in inglese) | In transito; può comparire anche quando esiste solo la registrazione dell'ordine: leggi le scansioni sotto. | «In transito» |
| `查询不到` (`Not Found` in inglese) | Impossibile trovare: il tracker non conosce alcun pacco con questo codice e non elenca scansioni. | Non trovato |

Seguono anzitutto i passaggi nella Cina continentale, privi di località:

| CTTYEX scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order information received` | I dati dell'ordine trasmessi dal venditore sono arrivati a CTTYEX; il collo è ancora presso il venditore. | «Etichetta creata» |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Preso in carico al punto di ricezione transfrontaliero di CTTYEX nella Cina continentale. | «Preso in carico» |
| `Goods leave the operation center` | Uscito dal centro operativo di CTTYEX nella Cina continentale. | «In transito» |
| `Arrival at domestic internationality airport` | All'aeroporto di partenza nella Cina continentale prima del volo (`internationality` è la grafia di CTTYEX). | «In transito» |
| `Departure from the airport of origin domestic` | Partito in aereo dalla Cina continentale. | «In transito» |

Dopo il volo, le righe provengono dall'agente di CTTYEX nel tuo Paese e dalla rete che effettua la consegna:

| CTTYEX scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | L'aereo è atterrato nel tuo Paese. | «In transito» |
| `Import customs clearance` | Sdoganamento d'importazione (scritto dopo la città del porto/aeroporto d'ingresso); pacco non ancora svincolato. | «In dogana» |
| `transfer to local acting company International self-operation team` | Affidato dopo la dogana all'agente locale di CTTYEX nel tuo Paese. | «In transito» |
| `Leave the … processing center` | Ha lasciato un centro di lavorazione (la cui città compare al posto dei puntini). | «In transito» |
| `Internationaliy Package received at processing center` | Prima scansione della rete di consegna (`Internationaliy` è la grafia di CTTYEX; non è una nuova spedizione). | «In transito» |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | In una struttura della rete di recapito (chiamata solo `logistics carrier`), diretta alla successiva. | «In transito» |
| `Accepted at logistics carrier Regional Destination Facility` | Prima scansione della rete di consegna nella tua regione. | «In transito» |
| `Arrive at the last mile sorting and distribution center` | Nel centro di smistamento e distribuzione di ultimo miglio vicino a te. | «In transito» |
| `Leaving the sorting center` | Uscito da quel centro di smistamento. | «In transito» |
| `Arrive at the sorting and distribution center` | Arrivato in un centro di smistamento (la città è scritta dopo `Arrive`). | «In transito» |
| `package is on the way` | Segue la scansione del centro di smistamento di quella città; non indica ancora l'uscita del furgone in consegna. | «In transito» |
| `Delivered` | Consegnato dalla rete locale (la riga si apre con città e CAP). | «Consegnato» |

Negli Stati Uniti le tre righe da `Accepted at logistics carrier Regional Destination Facility` a `Leaving the sorting center` iniziano con città, Stato e codice ZIP. Ogni altra dicitura compare così come la scrive CTTYEX.

**Tutti gli orari sono ora di Pechino.** Il tracker mostra gli orari senza fuso, ma ciascuno è in ora di Pechino (`UTC+8`), **comprese le scansioni effettuate nel tuo Paese**. Poiché a Pechino la data scatta alle `16:00 UTC`, una scansione effettuata in Italia o in Svizzera dopo le `17:00` in inverno (o le `18:00` in estate) riporta sul tracker di CTTYEX già la data del giorno seguente.

## Chi consegna i pacchi CTTYEX?

CTTYEX ritira il collo nella Cina continentale e lo spedisce per via aerea; dopo l'atterraggio il suo agente cura lo sdoganamento e una rete locale consegna. Né il sito né il tracker di CTTYEX indicano il nome di quella rete (`logistics carrier`) o un secondo codice: il numero `CTTY…CN` resta quello da seguire fino alla consegna. CTTYEX non pubblica tempi di consegna, regole sui tentativi né periodi di giacenza: basati sulla stima del negozio e, se un corriere locale lascia un avviso, vedi [mancata consegna](guide:missed-delivery).

## Pacco CTTYEX bloccato, in dogana o non arrivato

**Solo `Order information received`.** CTTYEX ha ricevuto i dati ma non ancora il collo, anche se il riepilogo segna `运输途中`. Se non cambia nulla per giorni, chiedi conferma al venditore.

**Fermo dopo `Departure from the airport of origin domestic` o per quattro giorni.** Tra il decollo e l'atterraggio o la dogana nel tuo Paese non compaiono tappe intermedie; dopo quattro giorni in viaggio senza nuove scansioni, Peek mostra «Nessun aggiornamento da quattro giorni» ([perché il tracciamento non si aggiorna](guide:tracking-not-updating), [tracciare pacchi dalla Cina](guide:tracking-from-china)).

**Fermo su `Import customs clearance`.** Il tracker non riporta importi né il nome del corriere locale, quindi non può confermare SMS o email che chiedano pagamenti per un pacco CTTYEX. Leggi prima [SMS truffa sulle consegne](guide:delivery-scam-texts) e consulta [pacchi fermi in dogana](guide:customs).

**`查询不到`, smarrito o restituito.** Controlla il codice col venditore e digitalo in maiuscolo (dopo due mesi il tracker rimuove comunque i pacchi). Poiché il sito di CTTYEX non prevede reclami per i destinatari, chiedi rimborso o rispedizione al venditore o al marketplace ([pacco smarrito](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender)).

## Contatti CTTYEX

La [pagina 联系我们 (contatti) di CTTYEX](https://www.cttyvip888.com/assets/html/contact.html), solo in cinese, indica un indirizzo a Fuyong, distretto di Bao'an, Shenzhen nella Cina continentale (`深圳市宝安区福永街道怀德新村8巷12号一层`), e un indirizzo email (`cttywlvip888@163.com`), senza numero di telefono, chat, modulo o assistenza per chi riceve.

**Attenzione agli SMS truffa.** I domini ufficiali di CTTYEX sono `www.cttyvip888.com` e `cttyvip888.com` ([SMS truffa sulle consegne](guide:delivery-scam-texts)). Per il tuo ordine scrivi al venditore.

## Tracciare un pacco CTTYEX con Peek

[Peek](/) riconosce CTTYEX all'istante da un numero `CTTY…CN` («Corriere rilevato»), usato solo da CTTYEX nel catalogo, oppure incollando un link `cttyvip888.com` con il codice dopo `nos=` (se selezioni CTTYEX a mano per un codice fuori da 8–40 lettere e cifre con almeno una cifra, avvisa che «non emette numeri come questo»).

Peek legge stato, scansioni e Paese di destinazione dal tracker di CTTYEX: quando all'estero una riga si apre con la città (o città e Stato), ne fa il luogo della tappa rimuovendo il CAP, esclude duplicati, numeri di telefono e nome del firmatario e converte ogni orario dall'ora di Pechino (`UTC+8`) al fuso del tuo dispositivo («Orari nel tuo fuso orario: …»), allineando le date alla tua giornata. Lo stato segue la scansione riconosciuta più recente (e il riepilogo solo se nessuna ha una fase); non essendoci stima di consegna, peso né secondo corriere, Peek continua a seguire il collo su CTTYEX fino alla consegna.

Peek controlla fino a ogni 10 minuti a pacco aperto o nel giorno di consegna con notifiche attive; altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza novità, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può contattare CTTYEX o il venditore per te.

## Domande frequenti sul tracciamento CTTYEX

### Che cos'è CTTYEX?

*Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), uno spedizioniere di Shenzhen nella Cina continentale specializzato in pacchi e-commerce internazionali, con sito `cttyvip888.com` e codici che iniziano per `CTTY` e finiscono in `CN`.

### Dove posso tracciare un numero CTTY…CN?

Tramite `物流查询` su [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (digitato in maiuscolo senza spazi) oppure direttamente in Peek.

### Gli orari delle scansioni CTTYEX sono nel mio fuso orario?

No. Ogni orario sul tracker è in ora di Pechino (`UTC+8`), anche per le scansioni eseguite in Europa o in America, quindi una scansione del tardo pomeriggio o della sera può portare già la data del giorno dopo. Peek converte automaticamente ogni orario nel fuso del tuo dispositivo.

### Perché il tracker di CTTYEX risponde `查询不到`?

In cinese significa *impossibile trovare*: nessun pacco corrisponde a quanto digitato. Controlla eventuali refusi, usa le lettere maiuscole e senti il venditore; anche le spedizioni inviate da oltre due mesi non sono più visibili sul tracker.

### Chi contatto se un pacco CTTYEX non arriva?

Parti dal venditore: il contratto con CTTYEX è del negozio che ha spedito, mentre il sito cinese di CTTYEX riporta solo un indirizzo a Shenzhen e un'email senza supporto per i destinatari.

:::sources
- [CTTYEX: 深圳诚泰通运国际物流 (home)](https://www.cttyvip888.com/) – ragione sociale, servizi e recapiti nel piè di pagina
- [CTTYEX: 关于我们 (chi siamo)](https://www.cttyvip888.com/assets/html/about_us.html) – fondazione nel 2011, oltre 200 Paesi, più di 6 filiali nella Cina continentale, piattaforme servite e voli charter
- [CTTYEX: 物流查询 (ricerca)](https://www.cttyvip888.com/assets/html/search.html) – un codice per riga (lettere e cifre) e pulsante 提交
- [CTTYEX: 快件跟踪 (tracciamento)](https://www.cttyvip888.com/index/tracknumber/track) – ricerca dei pacchi degli ultimi 2 mesi, colonne destinazione e stato
- [CTTYEX: 联系我们 (contatti)](https://www.cttyvip888.com/assets/html/contact.html) – indirizzo a Fuyong (Bao'an, Shenzhen, Cina continentale) ed email cttywlvip888@163.com, senza telefono né modulo
- [Universal Parcel Scraper: CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – formato CTTY…CN, ricerca in maiuscolo, 23 stati e riepiloghi, ora di Pechino ovunque ed estrazione delle località
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – distinzione da China Post e visualizzazione in Peek
:::
