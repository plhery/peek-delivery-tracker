---
title: Tracciamento TSJY (通世集运): numeri d’ordine e stati
description: Traccia un pacco consolidato TSJY (通世集运) dalla Cina: formato di un numero TSJY…CN, significato degli stati in cinese e quando subentra 17FEIA.
slug: tracciamento-tsjy
published: 2026-11-23
updated: 2026-11-23
---

TSJY (通世集运) è un servizio di consolidamento pacchi in Cina gestito da 苏州通世供应链有限公司, azienda logistica di Suzhou. Acquisti su siti cinesi come Taobao, Tmall, Pinduoduo, JD o Douyin e i negozi spediscono gli articoli al magazzino TSJY di Suzhou. TSJY li custodisce, li riunisce in un unico pacco e li spedisce all’estero. Traccia il tuo codice TSJY sul [sito di TSJY](https://www.tsjy56.com/), sotto 订单查询 (ricerca ordini), oppure incollalo nella casella qui sopra.

## Numero di tracciamento TSJY: com’è fatto

Il sito TSJY non descrive i propri numeri, ma hanno un formato fisso. TSJY sono le iniziali di 通世集运, Tongshi Jiyun. Tutti gli esempi qui sotto sono inventati:

| Numero | Cos’è |
| --- | --- |
| `TSJY482917365CN` | TSJY, nove cifre e CN: un numero d’ordine TSJY. |
| `TSJY48291736CN` | Solo otto cifre: non è il formato TSJY e Peek non riconosce TSJY. Controlla se manca una cifra. |
| `FEIKM4829173650YQ` | FEI, due lettere, dieci cifre e YQ: un numero [17FEIA](carrier:17feia), della linea che trasporta alcuni invii TSJY all’estero. |

In Peek minuscole, spazi e trattini non contano.

**Dove trovarlo.** È il numero dell’ordine di spedizione (订单号) che invii sulla pagina web di TSJY (网页下单) o su WeChat (微信下单) quando gli articoli sono arrivati in magazzino. Se qualcun altro ha prenotato la spedizione, chiediglielo.

**Due numeri.** La casella di ricerca TSJY accetta anche il codice della linea (运单号). Da smartphone ogni scheda mostra il codice TSJY e, dopo una barra, quello della linea. Tocca la scheda per aprire le righe di tracciamento (轨迹详情).

## Stati di tracciamento TSJY spiegati

Il tracker TSJY è in cinese. Da smartphone ogni scheda mostra uno stato sintetico per l’intero ordine:

| La scheda mostra | Cosa significa per te |
| --- | --- |
| `等待处理` | In attesa di elaborazione: TSJY ha ricevuto il tuo ordine di spedizione. |
| `开始拣货`, `正在拣货`, `拣货完成`, `开始打包`, `已称重`, `已录单` | Il magazzino preleva i prodotti, imballa e pesa il pacco e registra l’ordine. |
| `等待出库`, `已出库`, `运输途中` | In attesa di uscita dal magazzino; uscito; in transito. |
| `已送达`, `已完成` | Consegnato; completato. |
| `运输异常` | Problema lungo il tragitto. Chiedi a TSJY. |

Peek non legge questo stato della scheda, ma le righe di tracciamento (轨迹) all’interno. Prima compaiono i passaggi di TSJY nel proprio magazzino, in cinese:

| TSJY mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `订单已提交，等待系统确认` | Ordine inviato, in attesa di conferma del sistema. Non è ancora stato prelevato nulla. | «Etichetta creata» |
| `仓库开始拣货` | Il magazzino inizia il prelievo dei tuoi articoli. | «Preso in carico» |
| `仓库拣货完成` | Prelievo completato. | «Preso in carico» |
| `订单打包完成，等待出库` | Imballato, in attesa di uscire dal magazzino. Può comparire due volte in un minuto. | «Preso in carico» |
| `订单已出库，开始国际运输` | Ha lasciato il magazzino TSJY e inizia il trasporto internazionale. | «In transito» |

Sulla linea 17FEIA, TSJY riporta poi le righe di 17FEIA, in inglese:

| La linea mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Item information received.` | 17FEIA ha ricevuto i dati dell’ordine. Il pacco può essere ancora da TSJY. | «Preso in carico» |
| `The package is on the way to the facility.`, `Warehouse receipt.` | In viaggio verso il magazzino 17FEIA in Cina, poi ricevuto lì. | «In transito» |
| `The order has information pending customer confirmation.`, `Order Confirmed.` | Il magazzino 17FEIA trattiene il pacco finché l’ordine non è confermato. | «In transito» |
| `Order dispatched from warehouse.` | Ha lasciato il magazzino 17FEIA. | «In transito» |
| `Export declaration in progress.` | Dichiarazione di esportazione dalla Cina in corso, passaggio normale prima del volo. Non è un blocco doganale e non è la dogana del tuo paese. | «In transito» |
| `Flight has departed.` | Volo partito. | «In transito» |
| `Flight delayed.` | Il volo è in ritardo. Il pacco non si è mosso. | lo stato della riga precedente |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Scalo lungo il tragitto. | «In transito» |
| `Flight Arrived at Clearance Port.` | Atterrato dove avverrà lo sdoganamento all’importazione. | «In transito» |
| `Broker picked up Shipment and delivered to warehouse.` | Uno spedizioniere doganale l’ha portato nel proprio magazzino per lo sdoganamento. Non è una consegna a casa tua. | «In dogana» |
| `Customs Clearance is in progress.` | La dogana di importazione lo sta esaminando. | «In dogana» |
| `Customs Clearance Completed.` | La dogana lo ha rilasciato. | «In transito» |

Su un’altra linea può comparire anche la sua registrazione: `已下单` (ordine inserito), in cinese e senza luogo, tra l’imballaggio TSJY e l’uscita. Come `Item information received.` di 17FEIA, compare mentre il pacco è ancora da TSJY: «Preso in carico». Dopo l’uscita da TSJY, `已出库` (uscito dal magazzino), anch’esso in cinese e senza luogo, significa che il magazzino di quella linea ha inoltrato il pacco il giorno successivo all’arrivo: «In transito».

Peek scarta la riga di 17FEIA con la data stimata di partenza, che non è una scansione, e ogni riga datata nel futuro. Interpreta le altre righe, come quelle del corriere finale, in base alle parole chiave, ad esempio consegna o dogana.

**Orari.** La pagina dettagli di TSJY mostra ogni ora sul fuso del tuo dispositivo, e Peek mostra allo stesso modo i passaggi propri di TSJY. Peek legge le righe di magazzino di 17FEIA sull’ora di Pechino (UTC+8), come le scrive 17FEIA. Quando il luogo di una riga successiva termina con un codice paese come `(DE)`, Peek usa l’orario di quel paese se ha un solo fuso orario.

## Chi consegna i pacchi TSJY

Il sito TSJY dichiara di spedire in oltre 220 paesi e regioni, via aerea express (加急空运) o via mare economica (经济海运). Non indica chi consegna in ciascun paese né i tempi di transito. La pagina dettagli riporta la linea (渠道, canale) usata per ogni ordine.

Peek conosce una linea per nome: [17FEIA](carrier:17feia) (一起飞), azienda logistica cinese con proprio tracciamento su www.17feia.com. Il tracciamento di 17FEIA può indicare l’azienda che consegna nel tuo paese con il relativo numero.

TSJY non pubblica regole per tentativi di consegna o punti di ritiro: valgono quelle del corriere finale. Vedi [consegne mancate](guide:missed-delivery).

**Dogana nell’UE.** I beni comprati online e introdotti nell’UE da fuori pagano un dazio temporaneo di 3 € per articolo sui pacchi fino a 150 € dal 1° luglio 2026 e una tariffa di gestione dell’Unione di 2 € per articolo, di qualsiasi valore, dal 1° novembre 2026. Un articolo sono i beni con la stessa classificazione tariffaria, descrizione e, se richiesta, origine. Entrambi valgono per le vendite a distanza: merci spedite dal venditore o per suo conto. Le linee guida della Commissione precisano che i beni non sono spediti dal venditore quando l’acquirente organizza il trasporto senza intervento del venditore, come può succedere prenotando TSJY in autonomia: decide la dogana del tuo paese. Il sito TSJY non spiega come vengano pagati dazi o tariffe. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco TSJY fermo, smarrito o restituito

Con TSJY in genere il cliente sei tu perché hai prenotato la spedizione. Per un pacco in ritardo, smarrito o danneggiato rivolgiti quindi prima a TSJY.

**Articoli non ancora in magazzino.** Prima di arrivare da TSJY, gli acquisti viaggiano con i corrieri cinesi dei negozi: tracciali sul sito in cui li hai comprati. Il sito TSJY indica che il suo tracciamento inizia all’ingresso in magazzino e prosegue fino alla firma di consegna.

**Nessun risultato per il numero.** Per un codice sconosciuto la pagina mobile TSJY dice `未找到相关的轨迹信息。` (nessuna informazione di tracciamento trovata). Controlla eventuali refusi. Il pacco mostra «In attesa di TSJY» e Peek continua a controllare. Se dopo due giorni TSJY non riconosce ancora il numero e nessun altro ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a controlli giornalieri, poi settimanali trascorsa una settimana dall’aggiunta.

**Fermo in magazzino.** Se i passaggi interni di TSJY restano fermi per diversi giorni, chiedi alla sua assistenza clienti.

**Silenzio dopo il volo.** Dopo `Flight has departed.` può non comparire nulla finché non atterra o arriva in dogana. Se un pacco ancora su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza una nuova riga, Peek lo segnala con «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da …»), spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a TSJY.» e propone «Chiedi aiuto a TSJY». Vedi [perché il tracciamento si ferma](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**Smarrito o danneggiato.** Il sito TSJY promette il rimborso integrale in caso di smarrimento o danneggiamento (丢破必赔), ma le pagine pubbliche non indicano regole né termini: contatta subito l’assistenza clienti e conserva scatola e merce. Vedi [pacchi smarriti](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Consegnato ma non c’è, o restituito.** Controlla intorno a casa e chiedi ai vicini, poi avvisa TSJY. Il sito non dice nulla sui resi. Vedi [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti TSJY

Sulla [home page di TSJY](https://www.tsjy56.com/), nella sezione 客服中心 (centro assistenza clienti):

- **Telefono ed e-mail:** un numero di assistenza (客户服务电话) e un indirizzo e-mail (客服邮箱). Non sono indicati orari di apertura.
- **WeChat:** un codice QR per aggiungere il supporto WeChat (添加微信客服), oltre all’account di servizio e al mini-programma WeChat.
- **Magazzino:** a Huaqiao (花桥镇), Suzhou, con indirizzo completo sulla pagina.

Il sito è solo in cinese e non menziona assistenza in inglese.

## Tracciare un pacco TSJY con Peek

[Peek](/) identifica subito TSJY da un codice `TSJY…CN` («Corriere rilevato»): nessun altro corriere seguito da Peek usa questo formato. Funziona anche un link alla pagina risultati TSJY con un codice TSJY dopo `nos=`; se ce ne sono più d’uno, Peek prende il primo. Un link TSJY che contiene il codice della linea o un numero fuori formato non viene registrato sotto TSJY: Peek lo legge da solo, come se lo avessi incollato.

Per qualunque altro numero, incollalo comunque: Peek segue oltre 4.500 corrieri. Se il formato corrisponde a più corrieri, Peek mostra «Verifica dei servizi di tracciamento…» durante la ricerca; se non corrisponde a nessuno, lo cerca dopo l’aggiunta. Se Peek non nomina il corriere, tocca «Cambia» e sceglilo sotto «Tutti i corrieri», oppure usa «Cambia corriere» in seguito. Se scegli TSJY per un altro codice, Peek indica che TSJY «non emette numeri come questo».

Peek legge il tracker di TSJY: ogni riga con luogo e ora. Omette origine, paese di destinazione e nome della linea. TSJY non fornisce data di consegna, peso, codice di ritiro né firmatario. Lo stato in Peek segue la riga più recente che comprende.

Quando TSJY fornisce un codice 17FEIA, o un numero di linea la cui forma punta a un corriere noto a Peek, Peek interroga quel corriere. Finché quel corriere non mostra una scansione almeno recente quanto l’ultima di TSJY, Peek resta sul tracciamento TSJY mostrando già chi consegna («Consegna con 17FEIA», oppure «Annunciato a 17FEIA, non ancora ricevuto» finché 17FEIA ha solo l’annuncio senza proprie scansioni) e quel numero. Poi Peek segue il pacco lì e conserva la cronologia di TSJY.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché il tracker proprio di TSJY risponde (quando subentra 17FEIA o il corriere finale, Peek segue la cadenza di quel corriere, inclusa fino a ogni 2 minuti dalle 6:00 ora locale il giorno della consegna se il suo tracker diretto risponde). Dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora; e se resta su «Etichetta creata» per sette giorni, ogni 6 ore. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare TSJY, 17FEIA né i negozi al posto tuo.

## Domande sul tracciamento TSJY

### Cos’è TSJY (通世集运)?

Un servizio di consolidamento (集运) per chi vive all’estero e acquista su siti cinesi. Il sito afferma che è stato fondato nel 2005, conserva i pacchi gratuitamente per 180 giorni e spedisce in oltre 220 paesi e regioni.

### Dove posso tracciare un numero TSJY?

Sul [sito di TSJY](https://www.tsjy56.com/), sotto 订单查询: digita il numero TSJY o quello della linea e tocca 查询. Oppure incollalo in Peek.

### Cosa significa 订单打包完成，等待出库?

In cinese vuol dire «ordine imballato, in attesa di uscire dal magazzino»: il pacco è imballato nel magazzino TSJY e attende la linea di spedizione. Peek mostra «Preso in carico». Poi segue `订单已出库，开始国际运输`: è partito e il viaggio internazionale è iniziato.

### Perché il mio pacco TSJY mostra un numero 17FEIA?

[17FEIA](carrier:17feia) è la linea che porta il pacco all’estero: la scheda risultati TSJY riporta il suo codice `FEI…YQ` dopo una barra. Appena 17FEIA si aggiorna, Peek segue il pacco lì.

### Come contatto TSJY?

Dalla sezione 客服中心 della home page: numero di telefono, e-mail e WeChat, tutto in cinese.

:::sources
- [TSJY: 通世集运 (home page)](https://www.tsjy56.com/) – azienda, servizi, garanzia di risarcimento, copertura tracciamento, ricerca ordini e risultati su mobile, ordini, contatti, magazzino
- [TSJY: 轨迹查询 (tracking results)](https://www.tsjy56.com/orderResult.html) – risultati su mobile: schede con entrambi i codici, stati scheda, messaggio nessun risultato
- [TSJY: 轨迹详情 (track details)](https://www.tsjy56.com/orderDetail.html) – nome e numero del canale (渠道), righe di tracciamento, orari sull’orologio del dispositivo
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio 3 € dal 1° luglio 2026, tariffa di gestione dell’Unione 2 € dal 1° novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articolo a prescindere dal valore, cos’è un articolo, vendite a distanza e trasporto organizzato dal cliente
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato numero, link, diciture e fasi, fusi orari, passaggio alla linea, cosa legge Peek
:::
