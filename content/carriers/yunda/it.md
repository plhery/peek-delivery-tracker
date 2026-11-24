---
title: Tracciamento Yunda Express: codici, stati e 95546
description: Traccia un pacco Yunda Express (韵达快递) in Cina: codici da 13 e 15 cifre, stati cinesi, gestione resi e supporto clienti 95546 a Shanghai.
slug: tracciamento-yunda
published: 2026-11-24
updated: 2026-11-24
---

**Yunda Express** (*Shanghai Yunda Freight Co., Ltd.*, in cinese *韵达快递* / *韵达速递* / *上海韵达货运有限公司*) è una delle principali reti nazionali di spedizioni espresse in Cina, con sede nel distretto di Qingpu a Shanghai ([yundaex.com](https://www.yundaex.com/cn/index.php)). Yunda offre spedizioni espresse, tra cui e-commerce ed espresso internazionale, oltre a magazzinaggio, trasporto merci (*韵达快运*), catena del freddo e servizi di supply chain. Puoi verificare una lettera di vettura nazionale su [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry) oppure incollare il codice Yunda da 13 o 15 cifre nel riquadro qui sopra.

## Codici di tracciamento Yunda Express (13 e 15 cifre)

Le lettere di vettura nazionali cinesi di Yunda Express sono sequenze puramente numeriche di **13 o 15 cifre**:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `3148291736054` | Lettera di vettura nazionale Yunda Express da 13 cifre. |
| `437391846205819` | Lettera di vettura nazionale Yunda Express da 15 cifre (comune sugli ordini e-commerce recenti). |

I numeri puri da 13 e 15 cifre coincidono con quelli di altri corrieri, per cui Peek interroga Yunda solo quando incolli un link `web.yundaex.com/infoInquiry?...&homeWaybill=` oppure selezioni tu stesso Yunda Express. Vedi la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Yunda Express

Il tracker consumer di Yunda associa cinque etichette standard cinesi alle tappe nazionali, che Peek traduce chiaramente — distinguendo anche quando il pacco torna al mittente:

| Etichetta Yunda (CN) | Significato | Peek mostra |
| --- | --- | --- |
| `已揽件` | Pacco ritirato e scansionato dal corriere o dalla filiale Yunda di origine. | «Preso in carico» |
| `运输中` | In viaggio tra hub di smistamento e centri di transito Yunda in province e città cinesi. | «In transito» |
| `派送中` | In consegna con il corriere locale Yunda (oppure in consegna di rientro al mittente dopo una scansione di reso). | «In consegna» |
| `待取件` | Depositato presso un punto di ritiro locale, un punto Cainiao o un locker in attesa di ritiro (o in attesa del mittente in caso di reso). | «Pronto per il ritiro» |
| `已签收` | Firmato o ritirato dal destinatario (o dal magazzino di consolidamento in caso di inoltro all’estero). | «Consegnato» |
| `已签收` dopo una scansione esplicita di reso (`退回件扫描` / firmato dal mittente) | Restituito e firmato dal mittente originario anziché dall’acquirente. | «Restituito al mittente» |

## Consegna interna in Cina, magazzini di consolidamento e resi

- **Acquisti dall’estero tramite magazzino in Cina:** se hai acquistato su Taobao, 1688 o Pinduoduo usando un agente o magazzino di consolidamento (come [DobroPost](carrier:dobropost) o uno spedizioniere a Guangzhou, Shenzhen, Dongguan o Yiwu), `已签收` («Consegnato») su Yunda Express significa che il pacco è arrivato al tuo **magazzino di consolidamento in Cina**, non nel tuo paese. Dopo l’accettazione, lo spedizioniere assegna un nuovo codice internazionale.
- **Punti ritiro (`待取件`):** in Cina, `待取件` indica che il pacco attende presso un punto pacchi o un locker che il destinatario lo ritiri.
- **Rilevamento dei resi al mittente:** il tracker grezzo di Yunda usa la stessa etichetta `已签收` per un reso firmato dal mittente e per una consegna normale; Peek controlla il testo della scansione cercando indicazioni esplicite di reso o firma del mittente e imposta invece «Restituito al mittente».

## Pacco Yunda Express fermo, consegnato male o smarrito

- **Ordine su marketplace cinese:** contatta prima il venditore su Taobao, Tmall, Pinduoduo o JD — può chiedere alla filiale Yunda (*网点*) che ha ritirato il pacco di rintracciarlo.
- **Nessuna scansione dopo l’etichetta:** i venditori generano spesso un codice Yunda da 15 cifre prima della consegna al corriere; le scansioni iniziano quando la filiale registra `已揽件`. Vedi [tracciamento fermo](guide:tracking-not-updating).
- **Consegnato (`已签收`) ma introvabile:** verifica se ha firmato un locker, la portineria o il magazzino di consolidamento, e apri subito un reclamo al venditore o al corriere se manca o è danneggiato. Vedi anche [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Yunda Express e contatti a Shanghai

Recapiti ufficiali pubblicati su [Yunda Contact Us](https://www.yundaex.com/cn/about_contact.php) e [yundaex.com](https://www.yundaex.com/cn/index.php):

- **Linea nazionale assistenza clienti (Cina):** **`95546`**.
- **Reclami e tracciamento online:** [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php) e [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry).
- **Fax:** `021-39297127` (linea stampa `021-39291355`).
- **Sede centrale:** *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), No. 6679 Yinggang East Road, Qingpu District, Shanghai `201700`, Cina (*上海市青浦区盈港东路6679号*).

> Diffida di SMS falsi o link WeChat che chiedono di pagare una tariffa di riconsegna o inserire dati bancari per un pacco Yunda. Verifica qualsiasi problema dall’app del tuo marketplace o tramite la linea ufficiale `95546`. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Yunda Express con Peek

Peek non interroga Yunda da un numero nudo di 13 o 15 cifre (`3148291736054` o `437391846205819`, inventati): per 13 cifre interroga tra gli altri [iMile](carrier:imile) e [FAN Courier](carrier:fan-courier), e per 15 cifre [BRT](carrier:brt). Tocca «Cambia» mentre aggiungi il pacco o «Cambia corriere» nella sua schermata, dove Yunda Express appare sotto «Compatibili con questo numero» (anche cercando «Yunda» o «uda International»), oppure incolla un link `web.yundaex.com/infoInquiry?...&homeWaybill=` in [Peek](/), che lo riconosce come Yunda.

Peek legge il tracker Yunda e mostra la fase («Preso in carico», «In transito», «In consegna», «Pronto per il ritiro», «Restituito al mittente», «Consegnato»), il testo della scansione in inglese, la città o il distretto indicato all’inizio del testo e l’orario di consegna. Yunda fornisce gli orari sull’ora di Pechino e Peek li mostra con l’ora del tuo dispositivo. Su un reso le scansioni indicano il rientro verso il mittente. Mentre il pacco è in viaggio, la tua mappa può terminare il percorso nella città del destinatario; l’indirizzo civico non viene mostrato e i link condivisi non mostrano né l’uno né l’altro. Peek esclude nomi e telefoni dei corrieri, indirizzi dei punti di ritiro, immagini delle firme e pesi senza etichetta. Il tracker Yunda non fornisce una data stimata di consegna.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Yunda mostra `派送中`, ogni 2 minuti dalle 6:00 ora di Pechino finché il tracker Yunda risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Yunda non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Yunda Express.», con il link «Chiedi aiuto a Yunda Express» alla pagina contatti di Yunda. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Yunda Express.»

Puoi scegliere gli avvisi «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può chiedere riconsegne, recuperare codici locker né contattare Yunda Express per te.

## Domande sul tracciamento Yunda Express

### Che aspetto ha un codice di tracciamento Yunda Express?

Le lettere di vettura nazionali di Yunda Express sono composte da 13 o 15 cifre numeriche (ad esempio `3148291736054` o `437391846205819`, inventati).

### Cosa significano `已揽件`, `派送中`, `待取件` e `已签收`?

`已揽件` significa ritirato, `派送中` in consegna, `待取件` pronto per il ritiro presso un punto pacchi o locker e `已签收` consegnato (salvo che sia preceduto da una scansione di reso al mittente).

### Perché il mio pacco Yunda risulta «Consegnato» se mi trovo fuori dalla Cina?

Se hai ordinato tramite un agente o magazzino di consolidamento cinese, Yunda Express gestisce solo la tratta nazionale cinese fino a quel magazzino. Attendi che lo spedizioniere registri il pacco e fornisca un codice internazionale.

### Come contatto l’assistenza clienti Yunda Express?

Chiama la linea nazionale cinese **`95546`** o utilizza la pagina ufficiale di contatti e reclami su [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php).

:::sources
- [Yunda Express: Official Website](https://www.yundaex.com/cn/index.php) – *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*) servizi espressi nazionali, cargo, catena del freddo e magazzinaggio
- [Yunda Express: Contact Us](https://www.yundaex.com/cn/about_contact.php) – linea `95546`, fax `021-39297127`, sistema reclami online e sede Qingpu Shanghai (link di aiuto in Peek)
- [Yunda Express: Waybill Inquiry](https://web.yundaex.com/infoInquiry) – portale ufficiale di tracciamento nazionale
- [Universal Parcel Scraper: yunda](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yunda) – regola codici 13/15 cifre, regola link `web.yundaex.com`, mappa 5 etichette cinesi, rilevamento reso, estrazione città e orari di Pechino
:::
