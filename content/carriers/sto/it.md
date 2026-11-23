---
title: Tracciamento STO Express: numeri 申通快递, stati e contatti
description: Traccia un pacco STO Express (申通快递) in Cina: formati dei codici, cosa significano 签收 e le altre scansioni STO, stazioni, locker e assistenza.
slug: tracciamento-sto-express
published: 2026-11-22
updated: 2026-11-22
---

STO Express (申通快递, Shentong) è un corriere espresso cinese fondato nel 1993 che si definisce la prima azienda privata di spedizioni espresse della Cina. Un codice STO appartiene di solito a un pacco che viaggia in Cina, spesso un ordine online diretto a te, a un conoscente o al magazzino di uno spedizioniere. Puoi tracciarlo sul [sito di STO](https://www.sto.cn/pc), nel campo `查快递` (solo in cinese), oppure nella casella qui sopra.

## Numero di tracciamento STO Express: com’è fatto

I codici STO (`快递单号`) sono composti solo da cifre. La ricerca STO di Peek accetta 12, 13 o 15 cifre; le lettere di vettura più recenti ne hanno 15. Con cifre inventate:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `774829173654281` | `77` e 13 cifre: una lettera di vettura STO Express. Anche [BRT](carrier:brt) e Yunda usano 15 cifre, quindi Peek verifica con STO. |
| `482917365420` | 12 cifre: un formato STO più vecchio, condiviso con [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) e molti altri. |
| `4829173654281` | 13 cifre: un altro formato STO più vecchio, che Peek non collega automaticamente a STO. |

Gli spazi non contano in Peek.

**Dove trovarlo.** Lo comunica il venditore nei dettagli di spedizione ed è stampato sull’etichetta (`快递面单`) del pacco. Se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Sul sito di STO.** Inserisci il codice nella casella `查快递` su `sto.cn` e premi `查询` (cerca). Il contratto di servizio STO precisa che sito, app e account WeChat offrono il tracciamento per un anno dalla data di spedizione.

## Stati di tracciamento STO Express spiegati

Ogni scansione nel tracciamento STO ha un tipo in cinese, un orario sull’ora cinese (UTC+8) e la sede STO che l’ha registrata. Peek mostra la maggior parte di questi tipi in inglese:

| Scansione STO | In Peek | Stato in Peek | Cosa significa per te |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Preso in carico | STO ha ritirato il pacco dal mittente. |
| `发件` | `Departed`, seguito da `for` e dal centro successivo quando STO lo indica | In transito | Ha lasciato una sede STO verso la successiva. |
| `到件` | `Arrived` | In transito | Arrivato a un centro di smistamento o filiale. |
| `派件` | «In consegna» | In consegna | Un corriere lo ha in consegna. |
| `驿站代收` | `At a parcel station for collection` | Pronto per il ritiro | Una stazione pacchi (`驿站`) lo ha preso in custodia per te. Vai a ritirarlo. |
| `柜机代收` | `In a parcel locker for collection` | Pronto per il ritiro | Si trova in un locker automatico. Vai a ritirarlo. |
| `客户签收` | «Consegnato» | Consegnato | Qualcuno ha firmato. STO registra questo stato anche quando firma una stazione o un locker. |
| `签收` | «Consegnato» | Consegnato | Firmato. |

Gli altri tipi di scansione mantengono in Peek il testo cinese di STO. Un tipo che indica un reso (`退回` o `退件`) appare come `Return to the sender started` («Problema segnalato»). Le scansioni di consegna successive riguardano il rientro: `Out for delivery back to the sender`, `In a parcel locker or station on its way back` e `Returned to the sender`.

**Inviato a uno spedizioniere?** Allora `签收` significa che il magazzino dello spedizioniere ha firmato per ricezione e il tratto di STO è concluso. Da lì subentra il codice dello spedizioniere.

## Consegna STO Express in Cina: corrieri, stazioni e locker

STO dichiara che la propria rete copre il 99,7 % dei distretti cinesi. Il contratto di servizio consente di consegnare un pacco in quattro modi:

- a te personalmente;
- a una persona convivente o a qualcuno designato da te;
- presso un punto di raccolta (`代收点`) o un locker (`自提柜`) nella zona dell’indirizzo;
- in un altro modo richiesto da te o dal mittente.

Consegnato il pacco in uno di questi modi con l’imballaggio esterno integro, il contratto considera concluso il servizio di STO. Perciò «Pronto per il ritiro» in Peek è normale: il pacco attende in una stazione o un locker vicino. Il codice di ritiro (`取件码`) e l’indirizzo del punto di ritiro sono nel tracciamento ufficiale STO, non in Peek.

Per modificare i dati del destinatario, l’assistenza online STO offre una voce apposita (`修改收件人信息`): chiedi lì (contatti sotto) o al venditore.

## Pacco STO che non si aggiorna, smarrito o restituito

**Ancora nulla.** Il tracciamento STO non restituisce nulla per un codice non ancora noto né oltre il limite di un anno. Se rimane vuoto, controlla il numero con il venditore. Nel frattempo, un pacco affidato a STO Express mostra «In attesa di STO Express»; due giorni dopo la prima risposta vuota di STO, Peek ti chiede di controllare il numero, scegliere un altro corriere o eliminare il pacco.

**Nessun aggiornamento.** Quando un pacco in «Etichetta creata», «Preso in carico» o «In transito» non riceve nuove scansioni per 4 giorni, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a STO Express.», con il link «Chiedi aiuto a STO Express» all’assistenza online STO, dove la mancanza di aggiornamenti (`快递单号没有记录更新`) è tra gli argomenti disponibili. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**Smarrito o danneggiato.** Chiedi prima al venditore: è lui ad aver prenotato STO, non tu. Secondo il contratto STO, il mittente — o chi da lui incaricato — presenta reclamo entro 30 giorni dalla scoperta di smarrimento o danno, con prove. Senza assicurazione (`保价`), STO rimborsa il danno effettivo fino a 500 yuan, o fino a 100 yuan per documenti. Un pacco assicurato è rimborsato fino al valore dichiarato. Senza assicurazione né prova di valore, STO paga tre volte la tariffa di spedizione. Se il ritardo è imputabile a STO, storna le spese di spedizione. Vedi [pacchi smarriti](guide:lost-parcel).

**Restituito.** STO può rispedire indietro un pacco se l’indirizzo non è chiaro, se il destinatario rifiuta la consegna o se due tentativi falliscono senza colpa di STO; le spese spettano al mittente. In Peek compaiono `Return to the sender started` e poi il percorso di ritorno. Vedi [restituito al mittente](guide:returned-to-sender).

**Consegnato, ma non c’è.** `签收` e `客户签收` indicano che qualcuno ha firmato: tu, un convivente, un delegato oppure una stazione o un locker. Controlla stazioni e locker vicini, la portineria e, se previsto, lo spedizioniere. Per reclamare usa il pulsante di reclamo (`投诉反馈`) nell’assistenza online STO. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Contatti e assistenza clienti STO Express

Canali pubblicati da STO:

- **Assistenza clienti online** su `page.sto.cn`, raggiungibile da `sto.cn` tramite `在线咨询` (richiesta online): assistente chat con operatori umani dalle 8:30 alle 22:00 (ora di Pechino), con pulsanti per spedire, consultare tariffe e sporgere reclamo (`投诉反馈`).
- **WeChat:** account di servizio e mini-programma per spedire pacchi. STO è presente anche su Douyin. Tutti e tre compaiono in fondo a `sto.cn`.

Questa pagina non riporta numeri di telefono: Peek elenca solo i recapiti letti nel testo delle pagine ufficiali di STO, dove non ne compare alcuno. Per l’ordine rivolgiti al venditore.

## Tracciare un pacco STO Express con Peek

La forma da sola non basta a Peek per confermare STO. Incolla comunque il codice: [Peek](/) segue oltre 4.500 corrieri.

Per un codice di 15 cifre che inizia con `77`, Peek interroga subito STO Express e BRT mostrando «Verifica dei servizi di tracciamento…». Se STO ha il pacco, appare «STO Express ha questo pacco.» e Peek lo registra lì. Altrimenti indica «non ancora trovato · Peek continua a controllare». Aggiungilo lo stesso: il pacco mostrerà «Probabilmente STO Express» e «In attesa della prima scansione di STO Express», e Peek continuerà a chiedere a STO. Dopo una settimana senza novità, ti chiede di verificare il codice con il mittente o scegliere un altro corriere.

Peek interroga automaticamente STO solo per i numeri `77`. Per un codice di 12 cifre interroga altri corrieri con quel formato (tra cui BRT) e i servizi di tracciamento; se trovano il pacco indicando STO Express, Peek lo registra lì e interroga STO al controllo successivo.

Per far leggere subito il tracciamento STO, tocca «Cambia» e scegli STO Express sotto «Compatibili con questo numero» (12 cifre, oppure 15 che iniziano con `77`). Per 13 cifre o 15 non inizianti con `77`, sceglilo sotto «Tutti i corrieri» o cerca `STO` o `申通`. Su un pacco già aggiunto usa «Cambia corriere». La ricerca STO di Peek accetta solo 12, 13 o 15 cifre: per altre lunghezze avvisa «non emette numeri come questo».

Peek legge il tracciamento proprio di STO, lo stesso dietro l’assistenza online. Per ogni scansione mostra il passaggio (tabella sopra), la città della sede STO (il nome cinese se Peek non può localizzarla) e l’ora, letta sull’ora cinese e mostrata nel tuo fuso orario («Orari nel tuo fuso orario:»). Una scansione ripetuta compare una volta sola e il peso viene mostrato quando registrato dalle bilance STO. Omette il testo integrale STO e, con esso, nomi e telefoni di corrieri e stazioni, indirizzi e codici di ritiro e il nome di chi ha firmato. STO non fornisce una data di consegna prevista, quindi Peek non la mostra. Peek segue solo il codice STO: quello dello spedizioniere va aggiunto come pacco separato.

Mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente STO fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Pechino nel giorno in cui STO lo scansiona in consegna, finché il tracker di STO risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore, a meno che tu non lo tenga aperto o la consegna sia prevista per quel giorno. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Non può contattare STO, lo spedizioniere né il venditore per te.

## Domande sul tracciamento STO Express

### STO Express è affidabile?

Sì. Fondata nel 1993, STO Express si definisce il primo corriere espresso privato cinese e pioniere del franchising nel settore. È quotata sul mercato cinese delle azioni A e dichiara 75 centri di smistamento, 5.075 filiali indipendenti e oltre 98.000 negozi locali.

### Cosa significa 签收 nel tracciamento STO?

Firmato: Peek mostra `签收` e `客户签收` come «Consegnato». Hai firmato tu, un convivente, una persona delegata oppure una stazione o un locker. `驿站代收` e `柜机代收` indicano che il pacco attende in una stazione o locker per il ritiro.

### Perché il mio codice STO non mostra alcun tracciamento?

STO non ha ancora scansionato il pacco, il numero è sbagliato oppure è stato spedito più di un anno fa: STO conserva il tracciamento per un anno. Verifica il numero con il venditore o aggiungilo in Peek, che continuerà a controllare.

### Dov’è il codice di ritiro del mio pacco STO?

Nel tracciamento STO su `sto.cn`, con la stazione o il locker che custodisce il pacco. Peek non mostra né il codice di ritiro (`取件码`) né l’indirizzo; mostra `At a parcel station for collection` o `In a parcel locker for collection` quando il pacco ti aspetta.

### Come contatto STO Express?

Tramite l’assistenza clienti online sul sito di STO: gli operatori rispondono dalle 8:30 alle 22:00 (ora di Pechino), con pulsante reclami (`投诉反馈`). Per un ordine online rivolgiti prima al venditore.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – campo di tracciamento, account di servizio e mini-programma WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – fondata nel 1993, primo corriere espresso privato, modello franchising, quotazione azioni A, centri di smistamento, filiali, negozi, copertura distretti
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – tracciamento un anno, modalità di consegna, resi, rimborsi con e senza assicurazione, reclamo entro 30 giorni, ritardo
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, operatori umani 8:30–22:00, reclami, argomenti nessun aggiornamento e modifica dati destinatario
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – formati di codici, tipi di scansione, resi, ora cinese, peso, codice di ritiro, firmatario, campi mostrati e omessi, nessuna data prevista
:::
