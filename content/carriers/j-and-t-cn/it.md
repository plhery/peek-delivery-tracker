---
title: Tracciamento J&T Express China: numeri JT, stati e contatti
description: Traccia un pacco J&T Express China (极兔速递) nella Cina continentale: formato JT, significato di 已签收 e degli altri stati, ultime 4 cifre telefoniche e Peek.
slug: tracciamento-jt-express-china
published: 2026-11-21
updated: 2026-11-21
---

J&T Express China (`极兔速递`) è la rete nella Cina continentale del gruppo J&T Express, operativa da marzo 2020. Un codice J&T China appartiene di solito a un pacco che viaggia all'interno della Cina continentale, ad esempio un ordine online diretto a un recapito locale o al magazzino di uno spedizioniere (*forwarder*). Controllalo sul [tracker cinese di J&T](https://www.jtexpress.com.cn/service.html) oppure nel riquadro qui sopra; il servizio internazionale diretto di J&T China (*J&T International Standard Express*) vola dalla Cina continentale solo verso sette Paesi del Sud-est asiatico, quindi su un ordine destinato all'Europa o al Nord America un numero J&T China può coprire solo la tratta interna nella Cina continentale fino al forwarder.

## Numero di tracciamento J&T Express China: com'è fatto

J&T lo chiama numero di lettera di vettura (`运单号`). Peek riconosce una sola forma per J&T Express China (con cifre inventate):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `JT4829105736491` | `JT` e 13 cifre: poiché nel catalogo di Peek anche J&T Express (la rete nel Sud-est asiatico) usa lo stesso schema, Peek non assegna J&T China dalla sola forma (vedi sotto). |
| `0148291057364` | Le 13 cifre senza `JT`: il tracker di J&T aggiunge `JT` da sé, ma in Peek 13 cifre nude corrispondono ad altri corrieri come DPD France o Yunda, non a J&T. Includi sempre `JT`. |

Minuscole, spazi e trattini non contano in Peek.

**Dove trovarlo.** Il venditore lo comunica nei dettagli di spedizione dell'ordine ed è stampato sull'etichetta applicata al collo ([come trovare il numero di tracciamento](guide:find-tracking-number)).

**Sul sito di J&T.** Nella pagina `运单查询` (`jtexpress.com.cn/service.html`) puoi inserire fino a 10 codici contemporaneamente separati da spazi o Invio. Per tutelare la privacy, J&T può chiedere di completare un puzzle o di digitare le ultime quattro cifre del numero di telefono del mittente o del destinatario associato alla spedizione. Le condizioni della lettera di vettura elettronica garantiscono al mittente nella Cina continentale la consultazione del tracking per un anno dalla spedizione.

## Stati del tracciamento J&T Express China spiegati

Il tracker di J&T utilizza questi stati, all'incirca in quest'ordine:

| J&T scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `已揽件` | Ritirato: J&T ha preso in carico il pacco dal mittente nella Cina continentale. | «Preso in carico» |
| `运送中` | In transito nella rete di J&T. | «In transito» |
| `发往代理点` | Inviato verso un punto affiliato (`代理点`), mostrato sul sito con l'icona di consegna. | «In transito» |
| `派件中` | In consegna: un corriere lo ha con sé nel giro di recapito. | «In consegna» |
| `已代收` | Ricevuto per tuo conto da terzi, come un punto di ritiro (`代收点`): vai a ritirarlo. | «Pronto per il ritiro» |
| `已签收` | Firmato / consegnato: recapitato a te o per tuo conto, con l'ora (`签收时间`) e, quando disponibile, la foto (`查看签收图片`). | «Consegnato» |
| `已拒收` | Rifiutato dal destinatario. | «Problema segnalato» |
| `已退回` | Restituito al mittente, con l'orario di rientro (`退回时间`). | «Restituito al mittente» |
| `已取消` | Spedizione annullata. | «Problema segnalato» |

In caso di imprevisti il tracker di J&T può mostrare anche un avviso di anomalia logistica con la spiegazione di J&T. Peek mostra queste diciture cinesi non appena inserisci le ultime quattro cifre del telefono; finché mancano, mostra gli aggiornamenti forniti dai servizi di tracciamento.

**Spedito a un forwarder?** Allora `已签收` indica che il magazzino dello spedizioniere nella Cina continentale ha firmato per il pacco e la tratta di J&T è conclusa: da lì in poi segui il codice proprio del forwarder.

## Consegna nella Cina continentale: corrieri, punti di ritiro e locker

Le condizioni della lettera di vettura elettronica di J&T nella Cina continentale consentono quattro modalità di consegna:

- direttamente a te;
- a un convivente o a una persona da te delegata;
- presso un punto di raccolta (`代收点`) o un armadietto automatico (`自提柜`) nella zona dell'indirizzo;
- in un altro modo richiesto o concordato con mittente o destinatario.

Su tua richiesta o con il tuo consenso il corriere può lasciarlo anche a un altro recapito indicato o in un punto pacchi istituito dall'amministrazione locale, dal comitato di quartiere o dall'amministrazione condominiale; se l'imballaggio esterno è integro, il servizio si considera concluso. Nella Cina continentale le Misure di amministrazione del mercato dei corrieri espressi (`快递市场管理办法`, in vigore dal 1° marzo 2024) vietano di depositare un pacco in smart locker o stazioni pacchi o di confermare la ricezione senza il consenso del destinatario, e danno diritto a controllare il collo alla consegna (aprendolo o rifiutandolo se l'imballo è visibilmente danneggiato o il contenuto è fragile).

## Pacco J&T fermo, smarrito o restituito

**Ancora nessun dato.** Se il tracker di J&T risponde `抱歉！未查到此运单` (lettera di vettura non trovata), verifica il codice con il venditore. Nel frattempo Peek mostra «Nessuna cronologia di tracciamento per ora» per un pacco senza corriere assegnato, oppure «In attesa di J&T Express China» se registrato sotto J&T Express China. Senza le ultime quattro cifre telefoniche, se nessun servizio di tracciamento trova la spedizione, dal secondo controllo Peek mostra «Aggiornamento non disponibile» con «Aggiorna i dati di tracciamento».

**Nessun aggiornamento.** Dopo quattro giorni senza nuovi passaggi in viaggio, Peek segnala «Nessun aggiornamento da quattro giorni» (con il pulsante «Chiedi aiuto a J&T Express China»). Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**Smarrito, danneggiato o restituito nella Cina continentale.** Rivolgiti anzitutto al venditore che ha prenotato J&T. Secondo le condizioni di J&T (valide solo per i pacchi ritirati e consegnati interamente nella Cina continentale), il mittente presenta reclamo entro 30 giorni allegando prova del valore: senza assicurazione (`保价`), J&T risarcisce la perdita effettiva fino a dieci volte la tariffa di spedizione; con assicurazione, fino al valore dichiarato; per il ritardo rimborsa solo il costo di trasporto ([pacco smarrito](guide:lost-parcel)). Per indirizzo non chiaro, rifiuto (`已拒收`) o mancata consegna non imputabile a J&T, il collo torna al mittente a sue spese ([pacco restituito al mittente](guide:returned-to-sender)).

**Risulta consegnato ma non c'è.** Controlla punti di ritiro, locker, portineria e l'eventuale magazzino del forwarder, poi chiama il `956025` o contatta l'assistenza online con il numero di vettura. Nella Cina continentale, se la risposta di J&T non è soddisfacente o tarda, le norme consentono di presentare reclamo (`申诉`) alle autorità postali ([consegnato ma non ricevuto](guide:delivered-not-received)).

## Assistenza clienti e contatti J&T Express China

Su `jtexpress.com.cn` (nella Cina continentale):

- **Linea telefonica**: `956025` (`服务热线`), con operatori dalle `08:30` alle `20:00` nella Cina continentale.
- **Assistenza online** (`在线客服` su `jtexpress.com.cn`): operatori dalle `08:30` alle `21:00`; separatamente J&T elenca un servizio automatico (`智能客服`) attivo 24 ore su 24 (`00:00–24:00`).
- **Email**: `jtservice@jtexpress.com` (`服务邮箱`).
- **WeChat**: mini-programma `极兔速递+` per spedire e consultare dettagli aggiuntivi.

**Attenzione agli SMS truffa.** I domini ufficiali di J&T Express China sono `www.jtexpress.com.cn` e `jtexpress.com.cn`. Prima di aprire link ricevuti via SMS o email, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts). Per l'ordine in sé, contatta prima il venditore.

## Tracciare un pacco J&T Express China con Peek

Anche se il formato `JT` a 13 cifre è condiviso con J&T Express nel Sud-est asiatico, incollalo comunque in [Peek](/) (oltre 3.500 corrieri seguiti):

- **Incollato da solo.** Peek mostra «Verifica dei servizi di tracciamento…»: se i servizi concordano su J&T Express China con tappe datate, Peek lo registra lì automaticamente; se emerge una cronologia ma il corriere non è ancora confermato, vedi «Cronologia trovata. Peek confermerà il corriere dopo il salvataggio.»; altrimenti «non ancora trovato · Peek continua a controllare».
- **Scelto a mano.** Tocca «Cambia» e scegli J&T Express China sotto «Compatibili con questo numero» o cercando `J&T` o `极兔` in «Tutti i corrieri» (oppure «Cambia corriere» su un pacco già salvato, anche qualora fosse finito sotto J&T Express).
- **Tramite link.** Un link `jtexpress.com.cn/service.html?indexIpt=…` assegna subito il pacco a J&T Express China («Corriere rilevato»).

**Con o senza le ultime 4 cifre del telefono.** Inseriscile in «Ultime 4 cifre del numero di telefono» all'aggiunta o toccando «Aggiungi le cifre» nella scheda: Peek le conserva solo per quel pacco, le invia esclusivamente a J&T e legge direttamente il tracker ufficiale (stati in cinese, ora di Pechino `UTC+8` convertita nel tuo fuso con «Orari nel tuo fuso orario: …», senza località ed escludendo nomi di corrieri/firmatari, telefoni e foto). Senza cifre (o se non coincidono), Peek interroga in sequenza ParcelsApp, Ship24 e 17TRACK; «Apri il sito J&T Express China» passa allora al servizio che ha risposto e, se questo indica un corriere finale supportato col relativo numero, Peek passa a seguirlo lì non appena compare una scansione almeno altrettanto recente.

**Controlli e notifiche.** Mentre risponde il tracker di J&T, Peek controlla fino a ogni 10 minuti a pacco aperto o nel giorno di consegna con notifiche attive, e fino a ogni 2 minuti dalle 6:00 (ora di Pechino) nel giorno «In consegna» (altrimenti, e dopo due giorni senza novità o 12 ore su «Etichetta creata» / «Preso in carico», ogni ora). Tramite un servizio di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza nuovi passi e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive. Per i colli lasciati in punto o locker (`已代收`), scegli «Passaggi importanti» o «Ogni scansione» anziché «Solo la consegna». Peek non può contattare J&T, il forwarder o il venditore per te.

## Domande frequenti sul tracciamento J&T Express China

### J&T Express China è affidabile?

Sì. J&T Express è nata in Indonesia nell'agosto 2015, è attiva nella Cina continentale da marzo 2020, nell'ottobre 2021 ha acquisito il ramo express di Best Inc nella Cina continentale, dall'ottobre 2023 è quotata sul Main Board della Borsa di Hong Kong e copre 13 Paesi.

### Come si presenta un numero di tracciamento J&T Express China?

Le lettere `JT` seguite da 13 cifre, ad esempio `JT4829105736491`. Mantieni `JT` in Peek: le sole 13 cifre rimandano ad altri corrieri.

### Cosa significa `已签收` nel tracking J&T?

Firmato / consegnato: recapitato a te, a un familiare, a un delegato, a un punto di ritiro o locker, oppure al magazzino di un forwarder nella Cina continentale. `已代收` è diverso: un punto di raccolta ha preso in consegna il pacco per te e devi ancora ritirarlo.

### J&T Express China consegna in Italia o nel resto d'Europa?

Non con il proprio servizio internazionale: *J&T International Standard Express* collega per via aerea la Cina continentale solo con Malesia, Vietnam, Singapore, Thailandia, Filippine, Indonesia e Cambogia. Su ordini verso l'Europa o il Nord America un codice J&T China può coprire solo la tratta interna nella Cina continentale fino a uno spedizioniere.

### Qual è il numero di telefono di J&T Express China?

Il `956025`, la linea clienti nella Cina continentale, con risposta tramite operatore dalle `08:30` alle `20:00` (e fino alle `21:00` via assistenza online `在线客服`).

:::sources
- [J&T Express China: 极兔速递 (home)](https://www.jtexpress.com.cn/) – fondazione nel 2015, rete in 13 Paesi, linea 956025 ed email
- [J&T Express China: 运单查询 (ricerca lettera di vettura)](https://www.jtexpress.com.cn/service.html) – fino a 10 codici, verifica a 4 cifre, stati cinesi, ora e foto di firma, avviso anomalie
- [J&T Express China: 关于极兔 (chi siamo)](https://www.jtexpress.com.cn/aboutUs.html?type=JT) – avvio nella Cina continentale marzo 2020, acquisizione Best Inc ottobre 2021, quotazione a Hong Kong ottobre 2023
- [J&T Express China: 联系我们 (contatti)](https://www.jtexpress.com.cn/aboutUs.html?type=LX) – linea 956025, casella jtservice@jtexpress.com e supporto online
- [J&T Express China: 服务时间 (orari di servizio)](https://www.jtexpress.com.cn/serviceTime.html) – operatori 08:30–20:00 / 08:30–21:00 e servizio automatico 00:00–24:00
- [J&T Express China: 电子运单契约条款 (condizioni di vettura elettronica)](https://www.jtexpress.com.cn/electronicContract.html) – quattro modalità di recapito, tracking di un anno, resi, massimali e termine di 30 giorni nella Cina continentale
- [J&T Express China: J&T International Standard Express](https://www.jtexpress.com.cn/internationalv3.html) – voli diretti dalla Cina continentale verso sette Paesi del Sud-est asiatico
- [Ministero dei Trasporti della Cina: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – consenso per locker e stazioni, verifica alla consegna e ricorso postale nella Cina continentale
- [Universal Parcel Scraper: J&T Express China](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-cn) – formato JT, alias, link indexIpt=, ultime 4 cifre telefoniche, ora di Pechino e stati
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato JT condiviso con J&T Express, corrieri a 13 cifre, ordine dei servizi di tracciamento e passaggi
:::
