---
title: Tracciamento BYHD: codici BYHD, stati e corriere finale
description: Traccia un pacco BYHD (博益弘达) da Shenzhen: formato del numero BYHD, stati noti, secondo codice del corriere locale e tracciamento con Peek.
slug: tracciamento-byhd
published: 2026-11-22
updated: 2026-11-22
---

BYHD (博益弘达, Bo Yi Hong Da in pinyin) è il nome abbreviato di 深圳市博益弘达物流有限公司, una società di logistica di Shenzhen, in Cina, che spedisce pacchi all’estero per venditori online. Le sue lettere di vettura sono composte da `BYHD` e nove cifre: incolla la tua nel riquadro in alto oppure inseriscila nella casella 快递查询 (ricerca pacchi) sul sito di BYHD, byhd56.com, che apre il tracker. Aspettati una cronologia breve da BYHD e un secondo numero: quello usato dal corriere che consegna il pacco.

## Numero di tracciamento BYHD: come è fatto

BYHD non pubblica un formato ufficiale. Le spedizioni che il suo tracker riconosce hanno `BYHD` seguito da nove cifre. Con cifre inventate:

| Numero | Cos’è |
| --- | --- |
| `BYHD482917365` | `BYHD` e nove cifre: una lettera di vettura BYHD. |
| `BYHD48291736` | Solo otto cifre: non segue il formato BYHD. Controlla se manca una cifra. |
| `RR482917367CN` | Due lettere, nove cifre e `CN`: un numero [China Post](carrier:china-post), non BYHD. |

Minuscole e spazi non contano quando lo incolli in Peek.

**Dove trovarlo.** BYHD lavora per i venditori (il sito offre accesso, registrazione e preventivi per gli speditori), quindi il codice arriva dal negozio presso cui hai ordinato, nei dettagli di spedizione. Se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Un secondo numero sul tracker BYHD.** Il tracker di BYHD individua l’ordine tramite il codice BYHD ma non lo mostra di nuovo. Il codice sotto 跟踪号码 (numero di tracciamento) è quello assegnato dal corriere finale, mentre 参考号 (riferimento) è il riferimento del venditore. BYHD non specifica di quale azienda sia, ma la forma spesso lo rivela: vedi [formati dei numeri di tracciamento](guide:tracking-number-formats).

**Riferimento del negozio.** Il tracker BYHD trova un ordine anche con il riferimento del venditore, scritto esattamente come indicato, trattini inclusi.

## Stati di tracciamento BYHD spiegati

BYHD non pubblica un elenco di stati e il suo tracker mescola inglese e cinese. Le righe note finora, nell’ordine abituale:

| BYHD scrive | Cosa significa per te |
| --- | --- |
| `The electronic cargo information has been received` (o `货物电子信息已经收到`) | Il venditore ha creato l’ordine e inviato i dati a BYHD. BYHD non ha ancora scansionato il pacco. |
| `到达收货点` | «Arrivato al punto di ricezione»: BYHD ha preso in carico il pacco. |
| `Goods leaving the operation center` | Il pacco ha lasciato il centro operativo di BYHD. |

La prima riga non è una scansione reale: Hualei (华磊), la piattaforma di spedizionieri dietro il tracker BYHD — come per [Lingdong](carrier:lingdong) e Yilong Logistics — la inserisce automaticamente per un ordine ancora senza scansioni.

Gli orari di BYHD non indicano il fuso orario. Sono in ora di Pechino: quindi una riga alle 08:00 lì è avvenuta la sera precedente sull’orologio di New York.

## Chi consegna i pacchi BYHD

Il tracker BYHD fornisce il numero dell’azienda che consegna nel tuo paese, ma non il suo nome, e il sito non dice quale partner consegna in quale paese. BYHD non pubblica tempi di consegna né regole su tentativi, punti di ritiro o giacenza: valgono le regole del corriere finale. Se il formato del numero non basta a identificarlo, chiedi al venditore. Ti vengono chiesti oneri di importazione? Vedi [pacchi fermi in dogana](guide:customs).

## Pacco BYHD che non arriva o tracciamento fermo

**Solo `The electronic cargo information has been received`.** Il venditore ha registrato il pacco ma BYHD non lo ha ancora scansionato. Se dopo qualche giorno non segue nulla, chiedi al venditore se lo ha spedito davvero.

**Fermo su `Goods leaving the operation center`.** Fin qui arrivano le righe conosciute di BYHD, quindi il silenzio successivo non significa che il pacco si sia fermato. Traccialo con il codice del corriere finale mostrato dal tracker BYHD oppure chiedilo al venditore. Altro in [tracciare un pacco dalla Cina](guide:tracking-from-china) e [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Nessun risultato.** Per un codice sconosciuto, il tracker BYHD mostra un risultato con quel numero sotto 参考号 (riferimento) e tutti gli altri campi vuoti. Peek la legge come un codice sconosciuto e mostra «In attesa di BYHD», continua a controllare e, due giorni dopo la prima risposta di questo tipo, ti chiede di controllare il numero, scegliere un altro corriere o eliminare il pacco. Confronta comunque tu stesso il codice carattere per carattere con il messaggio del venditore.

**Smarrito, danneggiato o restituito.** BYHD non pubblica procedure di reclamo e la pagina delle note legali è vuota. Poiché il venditore ha prenotato la spedizione con BYHD, chiedi al venditore o al marketplace un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender) e [consegnato ma non ricevuto](guide:delivered-not-received).

## Contatti e assistenza clienti BYHD

BYHD non indica alcun servizio clienti per i destinatari. Il sito, solo in cinese, riporta un numero di cellulare di un referente senza orari e un indirizzo nel distretto di Longhua, Shenzhen (深圳市龙华区). Non fornisce email, modulo di contatto né uffici fuori dalla Cina. Per qualsiasi richiesta sull’ordine rivolgiti al venditore.

## Tracciare un pacco BYHD con Peek

Incolla un numero BYHD in [Peek](/) e viene riconosciuto subito: nessun altro corriere del catalogo Peek usa questo formato. Non esiste un link di tracciamento BYHD da incollare perché il tracker usa un modulo. Un riferimento del venditore non ha un formato riconoscibile, ma Peek segue oltre 4.500 corrieri: aggiungilo comunque, cambia corriere e seleziona BYHD (lo trovi anche cercando 博益弘达 o Boyi Hongda). Peek però rimuove i trattini, quindi cerca eventuali riferimenti con trattini direttamente nella casella di BYHD.

Peek legge il tracker di BYHD e mostra ogni riga con il testo di BYHD, il luogo se presente e il paese di destinazione quando il record lo riporta. `The electronic cargo information has been received` appare come «Etichetta creata», `到达收货点` come «Preso in carico» e `Goods leaving the operation center` come «In transito». Gli orari sono letti sull’ora di Pechino e mostrati nel tuo fuso orario (la cronologia termina con «Orari nel tuo fuso orario:» seguito dal nome). Una riga datata dopo il controllo attende fino a quel momento, e una riga che risulta scritta oltre dodici ore prima dell’orario che porta (o oltre sei ore prima nello stesso secondo di una riga del genere) viene esclusa come previsione, non come scansione reale. In questi casi Peek avvisa: «Il sito di BYHD mostra in anticipo tutto il percorso, consegna compresa. Quei passaggi non sono scansioni reali.» (o, per una consegna scritta in anticipo, «Il sito di BYHD mostra una consegna scritta in anticipo, non una scansione reale. Se non è arrivato nulla, contatta il venditore.»). Nomi, indirizzo, CAP, peso, spese, riferimento venditore, firma e prova di consegna restano fuori da Peek, e non c’è data prevista perché BYHD non la fornisce.

**Il numero del corriere finale.** Se BYHD non ha ancora scansionato l’ordine, Peek legge questo secondo codice dalla pagina BYHD e interroga un corriere: le poste del paese di destinazione (come [USPS](carrier:usps) o [Royal Mail](carrier:royal-mail)) se è un numero postale internazionale (due lettere, nove cifre e due lettere), oppure l’unico corriere compatibile con quel formato se Peek lo traccia direttamente. Appena quel corriere mostra una scansione almeno recente quanto l’ultima riga BYHD, Peek segue il pacco lì mantenendo le righe di BYHD. Quando BYHD registra scansioni proprie, Peek resta su BYHD senza interrogare altri corrieri: il secondo numero appare nei dettagli sotto «BYHD lo traccia con il numero» così puoi aggiungerlo separatamente in Peek per seguire la consegna finale.

Mentre tieni il pacco aperto sullo schermo — oppure il giorno della consegna con notifiche attive — Peek controlla direttamente BYHD fino a ogni 10 minuti (ogni 2 minuti solo se BYHD indica il pacco in consegna, cosa che nessuna delle sue righe note fa). Altrimenti, e appena BYHD resta fermo (dopo 12 ore sulla prima riga o prima scansione, o dopo due giorni senza novità in seguito), controlla ogni ora. Se il record di BYHD mostra ancora solo la prima riga dopo due giorni, Peek interroga anche servizi di tracciamento e ne mostra la cronologia se va oltre. Poiché le righe note di BYHD sono tutte fasi iniziali, solo «Ogni scansione» le notifica; «Passaggi importanti» e «Solo la consegna» attendono fasi successive. Dopo quattro giorni senza scansioni in transito, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a BYHD.».

## Domande sul tracciamento BYHD

### Cos’è BYHD (博益弘达)?

È 深圳市博益弘达物流有限公司, una società logistica del distretto di Longhua a Shenzhen con sito byhd56.com. Il profilo descrive spedizioni transfrontaliere via mare e aria e servizi per venditori online come primo tratto Amazon FBA e spedizioni da magazzini esteri verso Europa, America, Sud-est asiatico e Medio Oriente. Le sue lettere di vettura sono `BYHD` e nove cifre.

### Perché il tracker BYHD mostra un numero di tracciamento diverso?

È il numero del corriere finale, non quello di BYHD: il tracker individua l’ordine con il numero BYHD ma mostra quello assegnato dal corriere che consegna. Traccialo presso quel corriere oppure aggiungilo in Peek come pacco a sé.

### Chi consegna i pacchi BYHD?

BYHD non lo dichiara: il tracker non riporta nomi di corrieri e il sito non indica partner per alcun paese. Il secondo numero sul tracker appartiene al corriere finale; la sua struttura o il venditore ti diranno quale.

### Quanto tempo impiega BYHD per consegnare?

BYHD non pubblica tempi di consegna. Fai riferimento alla stima indicata dal negozio per il tuo ordine.

### Chi devo contattare per un pacco BYHD mancante?

Il venditore da cui hai acquistato, perché ha prenotato la spedizione con BYHD. BYHD elenca solo il cellulare di un referente e un indirizzo a Shenzhen, senza assistenza ai destinatari.

:::sources
- [BYHD: 深圳市博益弘达物流有限公司 (home)](http://www.byhd56.com/) – ragione sociale, casella 快递查询 e tracker, accesso, registrazione e tariffe per speditori, indirizzo a piè di pagina
- [BYHD: 公司介绍 (profilo aziendale)](http://www.byhd56.com/about.html) – sede a Shenzhen, servizi transfrontalieri per negozi online, rotte marittime e aeree, regioni servite
- [BYHD: 联系我们 (contatti)](http://www.byhd56.com/contact.html) – numero di cellulare di un referente, nessun orario
- [BYHD: 法律声明 (note legali)](http://www.byhd56.com/legal.html) – pagina priva di contenuto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – piattaforma Hualei, formato BYHD, secondo codice e riferimento venditore, ricerca con trattini, diciture e resa in Peek, ora di Pechino, risposta per codice sconosciuto, dati mostrati, trattenuti ed esclusi
:::
