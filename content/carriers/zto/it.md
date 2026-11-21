---
title: Tracciamento ZTO Express: numeri 中通快递, stati e contatti
description: Traccia un pacco ZTO Express (中通快递) in Cina: formato dei numeri, significato di 已签收 e degli altri stati, stazioni e armadietti, tempi e numero 95311.
slug: tracciamento-zto-express
published: 2026-11-21
updated: 2026-11-21
---

ZTO Express (`中通快递`, Zhongtong) è un corriere espresso cinese fondato a **Shanghai** l'8 maggio 2002; nel 2025 ha movimentato 38,52 miliardi di pacchi. Un numero ZTO Express appartiene di solito a una spedizione interna alla **Cina continentale**, spesso un ordine online (ZTO trasporta i pacchi Taobao dal 2008) diretto a un recapito cinese o al magazzino di uno spedizioniere internazionale. Puoi seguirlo sul [tracker in cinese di ZTO](https://www.zto.com/check) oppure incollare il codice nel riquadro qui sopra. Le spedizioni inviate all'estero dal gruppo viaggiano invece con ZTO International (`中通国际`), che usa codici propri con prefisso `771` su `ztoglobal.com`.

## Numero di tracciamento ZTO Express: formato ed esempi

ZTO lo chiama numero di lettera di vettura (`运单号`). Peek conosce due formati per ZTO Express. Esempi con cifre inventate:

| Numero (inventato) | Cosa indica |
| --- | --- |
| `784920573280` | 12 cifre: lettera di vettura ZTO Express, formato condiviso anche con [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [DHL](carrier:dhl), [BRT](carrier:brt) e altri. |
| `73184920573649` | 14 cifre che iniziano con `7`: anche questo ZTO Express, usato pure da [BRT](carrier:brt), [SEUR](carrier:seur), [Chronopost](carrier:chronopost), [DPD UK](carrier:dpd-uk) e altre filiali DPD. |
| `77148291057364` | `771` più 11 cifre: formato di ZTO International (`中通国际`). Peek interroga per primo ZTO International. |

Gli spazi non contano quando incolli il codice su Peek. Il portale di ZTO accetta fino a 10 numeri insieme, separati da virgole o spazi (`最多可查询10条，以逗号、空格隔开`).

**Dove trovarlo.** Il mittente (di norma il negozio) conserva la propria copia della lettera di vettura (`发件联`) e ti comunica il codice nei dettagli dell'ordine. Non lo trovi? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Sul sito di ZTO.** Inserisci il numero su `zto.com/check` e premi `查询` (cerca). Il tracker può chiederti prima di risolvere un puzzle di verifica; inquadrando il codice QR sulla pagina con WeChat ricevi gli aggiornamenti in tempo reale.

## Significato degli stati di tracciamento ZTO Express

Ogni scansione porta un breve nome di fase in cinese, che Peek traduce in inglese:

| ZTO scrive | Peek mostra | Stato su Peek | Cosa significa per il tuo pacco |
| --- | --- | --- | --- |
| `已取件` | `Collected` | «Preso in carico» | Un corriere ZTO ha ritirato il pacco dal mittente. |
| `已收件` | `Received` | «Preso in carico» | Una filiale ZTO ha preso in carico il pacco. |
| `运输中` | `In transit` | «In transito» | In viaggio tra gli hub di smistamento e le filiali ZTO. |
| `派件中` | `Out for delivery` | «In consegna» | Un addetto ZTO ha il pacco nel giro di consegna. |
| `已代收` | `Held at a collection point for the recipient` | «Pronto per il ritiro» | Un punto di raccolta (come una stazione pacchi) custodisce il collo per il ritiro. |
| `已签收` | `Signed for` | «Consegnato» | Qualcuno ha firmato la ricezione (`签收`): tu, un delegato o il magazzino destinatario. |
| `异常通知` | `Exception notice` | «Problema segnalato» | ZTO ha registrato un imprevisto. Peek mostra solo la fase; la frase completa è sul tracker di ZTO. |

Altre voci, come resi (`退回件`) o reindirizzamenti, mantengono su Peek la dicitura cinese originale. Approfondisci in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

**Diretto a uno spedizioniere?** In quel caso `已签收` significa che il magazzino dello spedizioniere in Cina ha firmato la consegna e la tratta interna di ZTO Express è conclusa: subentra il numero internazionale dello spedizioniere. ZTO gestisce anche un servizio di consolidamento internazionale (`中通国际集运`) che raggruppa più colli in magazzino per spedirli insieme; nella Cina continentale risponde al `95311` selezionando il reparto internazionale (`95311转国际`).

## Consegna ZTO Express in Cina: corrieri, stazioni e smart locker

Le regole seguenti riguardano le consegne effettuate nella **Cina continentale**:

Il corriere ZTO recapita all'indirizzo oppure deposita il pacco in una stazione di quartiere (`驿站`), un punto di raccolta (`代收点`) o un armadietto automatico (`智能快件箱`) nelle vicinanze, modalità previste dal contratto di servizio ZTO. Le *Misure di amministrazione del mercato espresso* cinesi (`快递市场管理办法`), in vigore dal 1° marzo 2024, stabiliscono però che per armadietti e stazioni serve il tuo consenso e che alla consegna puoi controllare il pacco rispetto alla lettera di vettura, aprendolo o rifiutandolo se l'imballaggio esterno è visibilmente danneggiato o il contenuto è fragile.

Per i pacchi non consegnati a mano (porta, locker, stazione), il contratto ZTO invita a registrare un video (`录制视频`) del ritiro, dell'apertura e della verifica del contenuto; una consegna eseguita come pattuito si considera confermata in assenza di contestazioni scritte entro tre giorni (`三日`).

Dalle FAQ ufficiali di ZTO in Cina:

- **Manca il codice per aprire l'armadietto?** Cerca la filiale competente tramite la [ricerca filiali ZTO](https://www.zto.com/service/station) (`服务网点查询`) e chiamala, oppure comunica il numero di vettura al `95311` per avere il recapito del corriere o della filiale.
- **Richiesto un pagamento per sosta prolungata nel locker?** ZTO indica di toccare salta (`跳过`) al momento del ritiro per non pagare nulla.
- **Chiesto un supplemento di consegna?** La filiale di recapito non può addebitare alcun costo di consegna al destinatario (`派件网点不可以向收件客户收取任何派送费`).
- **Fuori area di consegna?** Ritira il pacco in filiale (`自提`) o chiedi al mittente di rispedirlo con un altro corriere.
- **Indirizzo errato?** Non è più modificabile quando il pacco è in consegna o consegnato; prima di allora il mittente deve chiederlo alla filiale di partenza.

## Pacco ZTO fermo, smarrito o restituito

**Ancora nessun risultato.** Se il tracker di ZTO non trova nulla, verifica il codice con il venditore. Nel frattempo un pacco salvato sotto ZTO Express mostra «In attesa di ZTO Express», mentre uno senza corriere assegnato mostra «Nessuna cronologia di tracciamento per ora».

**Nessun aggiornamento.** Quando un pacco ancora in viaggio non riceve nuove scansioni da 4 giorni, Peek mostra «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a ZTO Express.» e aggiunge il link «Chiedi aiuto a ZTO Express» verso la pagina contatti di ZTO. Leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Smarrito o danneggiato (nella Cina continentale).** Contatta il venditore: secondo il contratto di ZTO, il mittente (o l'avente diritto designato) presenta reclamo scritto entro 30 giorni (`三十日`) da quando viene a conoscenza di perdita o danno. Senza assicurazione (`保价`), ZTO rimborsa al massimo 500 yuan (`500元`); con assicurazione, fino al valore assicurato. Sono esclusi ammanchi o danni interni con imballo esterno integro. Vedi [pacco smarrito](guide:lost-parcel).

**Restituito al mittente.** Nella Cina continentale, ZTO può rispedire indietro un pacco in caso di indirizzo impreciso, rifiuto del destinatario o due tentativi di consegna falliti senza colpa di ZTO (`两次派送未成功的`). Vedi [pacco restituito al mittente](guide:returned-to-sender).

**Risulta consegnato ma non c'è.** `已代收` indica la giacenza presso un punto di ritiro, mentre `已签收` significa che qualcuno ha firmato (portineria, stazione o magazzino dello spedizioniere). Per reclamare nella Cina continentale comunica il numero al servizio clienti al `95311` o usa il [modulo reclami ZTO](https://www.zto.com/after/complaint/problemTypes) (`投诉反馈`, richiede accesso); se l'esito non soddisfa o tarda, le norme cinesi consentono il ricorso (`申诉`) alle autorità postali (`邮政管理部门`). Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Numero di telefono e assistenza clienti ZTO Express

Recapiti ufficiali di ZTO Express nella **Cina continentale**:

- **`95311`:** numero nazionale del servizio clienti (`全国统一客服热线`, *Customer Service Hotline* sul sito inglese di ZTO), attivo dalle `08:30` alle `17:30` (ora cinese) secondo le FAQ.
- **`95720`:** numero dedicato in uscita da cui chiamano i corrieri ZTO (`中通业务员专属外呼号码`).
- **Assistenza online** (`在线客服`) su `zto.com`, oltre a mini-programma WeChat (`微信小程序`), account ufficiale WeChat e app ZTO.

Per l'ordine di acquisto rivolgiti prima al venditore da cui hai comprato.

## Tracciare un pacco ZTO Express con Peek

Incolla il codice su [Peek](/): segue oltre 3.500 corrieri. Incollare da solo un numero ZTO non basta perché Peek interroghi subito ZTO Express: prima chiede ad alcuni degli altri corrieri che condividono il formato (come [DHL](carrier:dhl) e [BRT](carrier:brt) per 12 cifre; DPD, [Chronopost](carrier:chronopost), [Hermes](carrier:hermes-de), [SEUR](carrier:seur) e [BRT](carrier:brt) per 14 cifre; ZTO International per primo sui numeri `771`) e poi ai servizi di tracciamento, mostrando «Verifica dei servizi di tracciamento…». Se questi trovano il pacco e indicano ZTO Express, Peek lo archivia sotto ZTO Express e interroga ZTO al controllo successivo; altrimenti compare «non ancora trovato · Peek continua a controllare». Per far leggere subito a Peek il tracker di ZTO Express, tocca «Cambia» e scegli **ZTO Express** sotto «Compatibili con questo numero», oppure cerca `ZTO` o `中通快递` (`中通` mostra anche ZTO International); su un pacco già salvato usa «Cambia corriere». ZTO Express accetta su Peek solo 12 cifre o 14 cifre inizianti per `7`: per ogni altro codice compare «non emette numeri come questo».

Peek legge il tracker ufficiale di ZTO e apre un browser quando ZTO richiede una verifica. Per ciascuna scansione mostra la fase in inglese (tabella sopra), la città o il nome cinese della sede ZTO e l'orario convertito nel fuso del tuo dispositivo (su peektracker.com e nelle app iPhone e Android, sotto la cronologia appare «Orari nel tuo fuso orario: …»). Tralascia la frase integrale di ogni scansione e con essa i recapiti dei corrieri, i numeri di telefono e nome e indirizzo del destinatario, e non mostra una data stimata. Poiché il tracker web pubblico di ZTO può omettere le primissime tracce dell'ordine, possono mancare anche su Peek. Aggiungi l'eventuale codice internazionale dello spedizioniere come pacco separato.

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla gli aggiornamenti fino a ogni 10 minuti — e fino a ogni 2 minuti (ogni 5 minuti quando la ricerca su ZTO ha aperto un browser) dalle 6:00 locali nel giorno in cui ZTO scansiona il pacco «In consegna», finché risponde il tracker di ZTO (tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive). Negli altri casi, e dopo due giorni senza novità (o 12 ore su «Etichetta creata» o «Preso in carico»), Peek verifica ogni ora. Notifiche a scelta tra «Ogni scansione», «Passaggi importanti» e «Solo la consegna» / «Solo giorno di consegna». Peek non può contattare ZTO, lo spedizioniere né il venditore per conto tuo.

## Domande frequenti sul tracciamento ZTO Express

### ZTO Express è affidabile?

Sì. Fondata a Shanghai l'8 maggio 2002, ZTO Express è quotata a New York dal 27 ottobre 2016 e a Hong Kong dal 29 settembre 2020 (prima società espresso quotata su entrambe le borse). Nel 2025 contava oltre 31.000 punti di servizio coprendo più del 99 % dei distretti e delle contee cinesi.

### ZTO Express e ZTO International sono la stessa cosa?

Fanno capo allo stesso gruppo, ma usano servizi e portali separati. Il contratto di ZTO Express copre solo spedizioni interne alla Cina continentale (esclusi Hong Kong, Macao e Taiwan) tracciate su `zto.com/check`; ZTO International, ramo internazionale attivo dal 2014, gestisce le spedizioni transfrontaliere con numeri `771` su `ztoglobal.com`.

### Quanto impiega ZTO Express a consegnare in Cina?

Secondo le FAQ di ZTO (tempi indicativi), di norma la consegna avviene il giorno successivo dentro la stessa provincia e tra Jiangsu, Zhejiang, Shanghai e Anhui, in 3–5 giorni tra le altre province e in 5–7 giorni verso alcune aree remote. Se il pacco va a uno spedizioniere, va poi aggiunto il trasporto internazionale.

### Cosa significa «已签收» sul tracciamento ZTO?

Firmato alla consegna: Peek mostra `Signed for` con stato «Consegnato». Può aver firmato il destinatario, un delegato, la portineria o il magazzino di uno spedizioniere. `已代收` è diverso («Pronto per il ritiro»): una stazione o punto di raccolta custodisce il pacco in attesa del tuo ritiro.

### Qual è il numero di telefono di ZTO Express?

Il `95311`, linea nazionale del servizio clienti nella Cina continentale, attiva dalle `08:30` alle `17:30` (ora della Cina). Lì, le chiamate dal `95720` arrivano dai corrieri ZTO.

:::sources
- [ZTO Express: 运单查询 (ricerca spedizioni)](https://www.zto.com/check) – tracker in cinese, fino a 10 numeri e aggiornamenti WeChat
- [ZTO Express: 关于中通 (chi siamo)](https://www.zto.com/introduce/about/home) – fondazione nel 2002, quotazioni, 38,52 miliardi di pacchi e rete 2025, Taobao dal 2008
- [ZTO Express: 首页 (home)](https://www.zto.com/) – 95311, 95720, app e WeChat
- [ZTO Express: Home (inglese)](https://en.zto.com/) – indicazione Customer Service Hotline: 95311
- [ZTO Express: 联系我们 (contatti)](https://www.zto.com/introduce/about/contact) – assistenza clienti online
- [ZTO Express: 常见问题 (FAQ)](https://www.zto.com/after/answers) – orari 08:30–17:30, locker, divieto di costi di consegna, fuori zona, cambio indirizzo e tempi
- [ZTO Express: 服务网点查询 (ricerca filiali)](https://www.zto.com/service/station) – filiale competente per un indirizzo
- [ZTO Express: 投诉反馈 (reclami)](https://www.zto.com/after/complaint/problemTypes) – modulo reclami dietro login
- [ZTO Express: 快递服务协议 (contratto di servizio)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – ambito Cina continentale, recapito, video e termine di 3 giorni, resi, massimale di 500 yuan e reclamo entro 30 giorni
- [ZTO Express: 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – divisione internazionale del gruppo dal 2014
- [ZTO Express: 中通集运 (consolidamento)](https://www.zto.com/introduce/service/concentrated) – consolidamento internazionale e contatto 95311
- [Ministero dei Trasporti cinese: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – verifica alla consegna, consenso per locker/stazioni e ricorso postale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati numerici, fasi in inglese, verifica via browser, assenza di riconoscimento automatico e campi scartati
:::
