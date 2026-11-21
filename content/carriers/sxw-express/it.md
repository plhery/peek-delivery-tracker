---
title: Tracciamento SXW Express: numeri SXW, stati e dogana
description: Traccia un pacco SXW Express (Shengxingwang) da Shenzhen: formato del codice SXW, tracker ufficiale, significato degli stati, corriere finale e dogana.
slug: tracciamento-sxw-express
published: 2026-11-21
updated: 2026-11-21
---

SXW Express è il nome usato da Peek per `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`), azienda di logistica transfrontaliera con sede a **Shenzhen, in Cina**, che spedisce merci all'estero per esportatori e venditori online. Se un negozio cinese ha affidato il tuo ordine a SXW, cerca un codice composto da `SXW` e dodici cifre e incollalo nel riquadro qui sopra. Puoi controllarlo anche sul tracker ufficiale accessibile dal riquadro *Express Tracking* su `sxwexpress.cn`, lo stesso letto direttamente da Peek.

## Numero di tracciamento SXW Express: formato ed esempi

Il sito di SXW Express non descrive il formato dei propri codici. La struttura riconosciuta da Peek come esclusiva di SXW Express conta 15 caratteri: `SXW` seguito da dodici cifre. Esempi con cifre inventate:

| Numero (inventato) | Cosa indica |
| --- | --- |
| `SXW482910573649` | `SXW` e dodici cifre: il formato di SXW Express. Peek seleziona subito SXW Express. |
| `SXW48291057364` | Solo undici cifre: non corrisponde al formato SXW. Controlla se manca una cifra nel messaggio del venditore. |
| `GFUS48291057364918` | `GFUS` e quattordici cifre: numero [GOFO Express](carrier:gofo), che la scheda SXW può riportare per la consegna negli **Stati Uniti**. |

Spazi, trattini e lettere minuscole non fanno differenza quando incolli il codice in Peek. È il venditore a prenotare la spedizione con SXW e a comunicarti il numero; se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Verifica sul sito di SXW.** Nella home page di `sxwexpress.cn`, la scheda *Express Tracking* presenta il campo *Enter your tracking number here!* e il pulsante *Track*, che apre in una nuova scheda il tracker di SXW in cinese, dotato anche di una pagina in inglese (`sxwexpress.cn:8082/en/trackIndex.htm`). Accanto al pulsante, il logo 17TRACK rimanda a `17track.net`, un servizio esterno separato dal tracker di SXW.

## Significato degli stati di tracciamento SXW Express

Sul tracker di SXW tre voci provengono in cinese dal sistema interno di SXW — e Peek le traduce in inglese — mentre le altre sono scritte in inglese dalla linea di spedizione. Ecco le diciture principali in ordine:

| Tracker SXW / mostrato su Peek | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | Il venditore ha inviato i dati elettronici della spedizione; nessuno ha ancora preso in consegna il pacco. | «Etichetta creata» |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | La stessa registrazione elettronica nel sistema SXW (tradotta in inglese su Peek). | «Etichetta creata» |
| `到达收货点` → `Arrived at the receiving point` | Prima scansione fisica presso il punto di ricezione SXW (in inglese su Peek). | «Preso in carico» |
| `Parcel picked up`, `Item has been collected` | Pacco ritirato dalla linea di trasporto incaricata. | «Preso in carico» |
| `货物离开操作中心` → `Goods leave the operation center` | La merce ha lasciato il centro operativo di SXW (in inglese su Peek). | «In transito» |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Ingresso, smistamento e partenza da un centro di smistamento a Shenzhen. | «In transito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Partenza da un hub di transito lungo il percorso. | «In transito» |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Dichiarazione doganale di esportazione conclusa in **Cina** prima del volo (a volte scritto `Clearence`). Il pacco non è bloccato nella tua dogana. | «In transito» |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | Decollo del volo dall'aeroporto cinese. | «In transito» |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Atterraggio all'aeroporto del Paese di destinazione. | «In transito» |
| `Start customs clearence` | Avvio dello sdoganamento all'importazione a destinazione (scritto `clearence`). | «In dogana» |
| `Clearance processing completed - Import`, `Released From Import Customs` | Sdoganamento all'importazione completato; il pacco prosegue. | «In transito» |
| `Returned parcel has been recieved by shipper` | Pacco restituito e ripreso dal mittente in origine (scritto `recieved`). | «Restituito al mittente» |

Poiché convivono il sistema di SXW e quello della linea aerea, due righe possono dire quasi la stessa cosa (`货物电子信息已经收到` e `Parcel Data Received`, oppure `到达收货点` e `Parcel picked up`). Su alcune tratte SXW riporta anche scansioni del corriere finale (ad esempio `Arrived at GOFO Regional Hub` negli USA, `Outward MC - Handheld Acceptance` di Royal Mail nel Regno Unito o `Onboard for delivery` e `Delivered - Left in a safe place` di Australia Post). Più informazioni in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

**Orari e fusi.** Peek interpreta gli orari registrati in Cina come ora di Pechino (`Asia/Shanghai`, UTC+8) convertendoli nel fuso del tuo dispositivo; a partire dalla prima riga la cui località termina con la sigla di un altro Paese (come `…, US`, `…, GB` o `AU`), Peek mantiene l'ora locale di destinazione. Su peektracker.com e nelle app iPhone e Android, sotto la cronologia compare «Orari nel tuo fuso orario: …».

## Chi consegna i pacchi SXW Express e quali regole doganali valgono

Il sito di SXW Express dichiara consegne porta a porta `DDU/DDP` e recapito locale di ultimo miglio (*local last-mile delivery*) in Europa, America, Sud-Est asiatico, Medio Oriente e Africa. L'elenco prodotti cita linee [UPS](carrier:ups) da Hong Kong (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), una *ARAMEX Special Line*, un *Middle East and Europe small package* e uno *US Express Mail Small Packet - F*, senza indicare quale corriere consegni nel singolo Paese.

**Passaggi di consegna letti da Peek.** Per le spedizioni dirette negli **Stati Uniti**, la scheda SXW può affiancare un codice [GOFO Express](carrier:gofo) (`GFUS` + 14 cifre): Peek mostra allora «Consegna con GOFO Express» (a meno che il canale di spedizione nomini un altro vettore come UPS), indica «Annunciato a GOFO Express, non ancora ricevuto» prima della presa in carico e passa a seguire GOFO non appena compare una scansione GOFO pari o successiva all'ultima di SXW. Allo stesso modo, se il canale nomina la [Posta Svizzera](carrier:swiss-post) con un numero postale svizzero valido per la **Svizzera** o il **Liechtenstein**, Peek passa il tracciamento alla Posta Svizzera dopo la prima scansione. Tentativi di recapito e ritiro seguono le regole del corriere locale: chiedi al venditore nome e codice del vettore finale, e vedi [mancata consegna](guide:missed-delivery).

**Dogana in Italia, nell'UE e in Svizzera.** Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € pagano un dazio doganale UE di 3 € per articolo (*per item*), e dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo indipendentemente dal valore (un articolo riunisce le merci dello stesso pacco con uguale classificazione tariffaria, descrizione e, se richiesta, origine; oltre 150 € valgono i dazi ordinari), più l'IVA all'importazione (secondo l'ambito territoriale della Commissione europea, **Livigno** è fuori sia dal territorio doganale sia dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** fanno parte del territorio doganale UE ma non dell'area IVA UE). In **Svizzera**, secondo l'UDSC, l'IVA sull'importazione è dell'8,1 % (2,6 % aliquota ridotta), ma «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi», escluse eventuali spese del corriere. Chiedi al venditore se ha spedito in `DDP` (oneri assolti) o `DDU`, e leggi [pacco fermo in dogana](guide:customs).

## Pacco SXW Express fermo, in ritardo o non arrivato

**Solo `Parcel Data Received` o `The electronic cargo information has been received`.** Il venditore ha creato l'ordine, ma il pacco non è ancora stato scansionato. Se dopo qualche giorno non cambia nulla, chiedi al negozio se lo ha effettivamente spedito.

**«In attesa di SXW Express».** Finché SXW elenca la spedizione senza scansioni reali (o solo con tappe scritte in anticipo) e i servizi di tracciamento non hanno dati, Peek mantiene «In attesa di SXW Express»: nell'app iPhone o Android o con un account, Peek interroga SXW ogni 6 ore dopo le prime ore; senza account, nelle prime 6 ore, poi quando apri il link (al massimo ogni 6 ore, e una volta al giorno dopo due giorni) e ogni giorno fino a 30 giorni con le notifiche attive. Se invece la pagina SXW risponde di non conoscere affatto quel numero e nessun servizio lo trova, dopo due giorni Peek mostra: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.»

**`Customs declaration completed`.** È la dichiarazione di esportazione in Cina prima del volo («In transito»), non un fermo nella dogana del tuo Paese.

**Nessun aggiornamento, segnato consegnato ma assente, smarrito o reso.** Se Peek mostra un numero GOFO Express o Posta Svizzera, controlla quel tracciamento; altrimenti basati sulla stima del negozio e contatta il venditore una volta superata. Approfondisci in [perché il tracciamento non si aggiorna](guide:tracking-not-updating), [tracciare un pacco dalla Cina](guide:tracking-from-china), [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti SXW Express

Il sito `sxwexpress.cn` non riporta alcun numero di telefono, email o indirizzo stradale: la pagina *Contact US* contiene soltanto il titolo e un riquadro *Online Support* vuoto, e l'accesso membri su `sxwexpress.cn:8082` serve agli account aziendali con SXW (non occorre per tracciare).

Il cliente contrattuale di SXW Express è il venditore: rivolgiti prima al negozio da cui hai acquistato e apri una contestazione sul marketplace se non ricevi risposta. Per una consegna negli Stati Uniti affidata a GOFO Express, contatta GOFO per il recapito finale.

## Tracciare un pacco SXW Express con Peek

[Peek](/) riconosce subito un codice formato da `SXW` e dodici cifre mostrando «Corriere rilevato», poiché nessun altro corriere del catalogo usa questa forma (se il venditore ti ha dato un codice diverso, Peek copre oltre 3.500 corrieri; per impostare SXW a mano premi «Cambia» o «Cambia corriere» cercando `SXW Express`, `Shengxingwang` o `胜兴旺`).

Peek interroga direttamente il tracker ufficiale di SXW senza account, CAP o telefono. Mostra lo stato, ogni scansione reale (con le tre righe cinesi tradotte in inglese), la località quando indicata, il servizio e il Paese di destinazione, scartando nomi, indirizzo, codici postali, numero di colli, peso, riferimento cliente, dettagli di pagamento, firmatario, firma e prova di consegna. Il tracker di SXW non fornisce una data stimata, quindi Peek non ne mostra.

**Tappe scritte in anticipo.** Peek trattiene qualsiasi voce datata nel futuro sull'ora di Pechino, le righe di tracciamento simulato (`CO_C_CR`) e ogni tappa registrata più di 12 ore prima dell'orario che riporta (o più di 6 ore prima nello stesso secondo di una simile tappa): sono previsioni, non scansioni. Prima che scocchi l'ora di una tappa trattenuta, Peek la omette mostrando: «Il sito di SXW Express riporta tappe scritte in anticipo, persino la consegna. Peek non può confermarle, quindi non le mostra. Cerca su quel sito il corriere che consegna nel tuo Paese e il suo numero di tracciamento; se non c’è, chiedi al venditore.» Una volta trascorso l'orario, Peek le elenca in grigio sotto «Scritto in anticipo da SXW Express» con l'avviso corrispondente («… quindi le mostra in grigio …») fino alla consegna effettiva o al reso; non cambiano mai lo stato né inviano notifiche. Se il tracker di SXW non risponde o non conosce il numero, Peek ripiega sui servizi di tracciamento.

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla gli aggiornamenti fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 locali nel giorno in cui passa «In consegna» finché risponde il tracker diretto di SXW (dopo il passaggio a GOFO Express o alla Posta Svizzera, Peek segue il ritmo del corriere finale; tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive). Negli altri casi, e dopo due giorni senza novità (o 12 ore su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Puoi scegliere «Ogni scansione», «Passaggi importanti» (dogana d'importazione come `Start customs clearence`, problemi, consegna, tentativi falliti, ritiro e resi — `Customs declaration completed` non invia avvisi) oppure «Solo la consegna» / «Solo giorno di consegna». Peek non può contattare SXW Express né il venditore per te.

## Domande frequenti sul tracciamento SXW Express

### Che cos'è SXW Express?

È il nome con cui Peek indica `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), operatore logistico transfrontaliero di Shenzhen attivo in trasporto marittimo e aereo, ferrovia Cina-Europa, corriere espresso internazionale e consegne DDU/DDP.

### Dove posso tracciare da solo un pacco SXW Express?

Nel riquadro *Express Tracking* sulla home page di `sxwexpress.cn`: inserisci il numero e premi *Track*. Il tracker ufficiale si apre in cinese e offre anche una pagina in inglese; il logo 17TRACK accanto porta invece a un servizio terzo separato.

### Cosa significa «Customs declaration completed» sul tracciamento SXW?

Che la dichiarazione doganale di esportazione in Cina è completata prima della partenza del volo. Peek la mostra come «In transito»: è la dogana in uscita dalla Cina, non quella del tuo Paese, e il pacco non è bloccato.

### Chi consegna i pacchi SXW Express nel mio Paese?

Il sito di SXW non lo specifica per Paese. Verso gli Stati Uniti la scheda può fornire un numero GOFO Express che Peek segue dopo la prima scansione GOFO (e verso la Svizzera la Posta Svizzera se indicata nel canale); altrimenti chiedi al venditore il nome del corriere finale e il suo codice.

### Chi devo contattare se un pacco SXW Express non arriva?

Rivolgiti al venditore da cui hai comprato: ha stipulato lui la spedizione con SXW e `sxwexpress.cn` non pubblica telefono né email. Se il venditore non risponde, apri una controversia sul marketplace.

:::sources
- [SXW Express: home page](http://www.sxwexpress.cn/) – ragione sociale, scheda Express Tracking e tasto Track, link 17TRACK e servizio DDU/DDP
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – sede a Shenzhen, spedizioni marittime, aeree, ferroviarie ed espresse, DDU/DDP e ultimo miglio locale
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – linee UPS da Hong Kong, ARAMEX Special Line e piccoli pacchi Europa/Medio Oriente e USA
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – pagina contatti priva di telefono, email o indirizzo e pannello Online Support vuoto
- [SXW Express: 会员登录 (accesso membri)](http://www.sxwexpress.cn:8082/) – nome cinese dell'azienda sul sistema di tracciamento
- [SXW Express: trace (tracker in inglese)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – pagina inglese del tracker ufficiale
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcolo per articolo
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia e Lago di Lugano
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera e franchigia di 5 franchi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato SXW, traduzione inglese delle voci cinesi, fusi orari, tappe scritte in anticipo e passaggio a GOFO e Posta Svizzera
:::
