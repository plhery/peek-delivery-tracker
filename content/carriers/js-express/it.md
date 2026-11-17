---
title: JS Express tracking: numero JSE…YQ, stati e consegna
description: Traccia un pacco JS Express (急速国际) dalla Cina: com'è fatto il codice JSE…YQ, cosa significano gli stati in inglese e chi consegna in Italia.
slug: js-express-tracking
published: 2026-11-17
updated: 2026-11-17
---

JS Express (急速国际, anche JS EXP) è un operatore logistico cinese per i negozi online transfrontalieri, gestito a Suzhou da 急创速供应链管理（苏州）有限公司. Se un negozio ha spedito il tuo ordine con JS Express, il codice inizia con JSE e finisce con YQ: incollalo nel riquadro qui sopra. JS Express porta il pacco fuori dalla Cina fino in Italia e poi lo affida a un corriere locale per la consegna a domicilio.

## Codice di tracciamento JS Express: com'è fatto

Le lettere di vettura di JS Express sono formate da JSE, due lettere, dieci cifre e YQ: 17 caratteri in tutto. Con cifre inventate:

| Codice | Che cos'è |
| --- | --- |
| `JSEAA0000000000YQ` | JSE, due lettere, dieci cifre e YQ: un codice di spedizione JS Express. |
| `JSEAA000000000YQ` | Solo nove cifre: non è il formato di JS Express. Controlla se manca una cifra. |
| `JXCAA0000000000YQ` | Finisce anche questo in YQ, ma inizia con JXC: è un codice Juxi Express (che Peek verifica prima su [DHLink](carrier:dhlink) quando a JXC seguono YI, ZO, US o ZI), non JS Express. |

Altri spedizionieri cinesi usano la stessa struttura cambiando le tre lettere iniziali: FEI per 17FEIA, WNB per WanbExpress, SFC per SFC, CJP per CJ Packet. JSE identifica JS Express. Spazi e minuscole non danno problemi a Peek: uno zero digitato al posto della seconda delle due lettere viene letto come una O, e una O tra le dieci cifre come uno zero. Il sito di JS Express richiede invece il codice intero: una parte non restituisce nulla.

**Dove trovarlo.** Il negozio ti invia il codice nell'e-mail di conferma della spedizione o lo mostra nel riepilogo dell'ordine. JS Express dichiara di collaborare a stretto contatto con Amazon, Walmart, Shopify, eBay, AliExpress, Wish e Shopee. Se non riesci a recuperarlo, leggi [come trovare il codice di tracciamento](guide:find-tracking-number).

**Nessun link diretto.** Le pagine di ricerca di JS Express accettano il codice solo tramite modulo, quindi nessun link apre direttamente un singolo pacco: copia il codice. La pagina inglese, «Express Query», risponde subito; quella cinese chiede prima un codice immagine (验证码).

## Stati del tracking JS Express: cosa significano

La pagina di JS Express elenca ogni scansione con data e ora, un luogo tra 【parentesi】 e un messaggio, dal più recente al meno recente, con «Latest» in cima. I testi sono quasi sempre in inglese. Nell'ordine in cui compaiono lungo il viaggio:

| JS Express scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Electronic notification of order creation received.` | Il negozio ha inviato i dati dell'ordine a JS Express, che però non ha ancora ricevuto il pacco. | «Etichetta creata» |
| `PRE-ADVICE` | Un partner ha ricevuto i dati elettronici della spedizione prima che il magazzino di JS Express abbia il pacco. | «Etichetta creata» |
| `Item has been collected` | Il magazzino di JS Express in Cina ha preso il pacco: è la prima vera scansione. | «Preso in carico» |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Smistamento presso il centro di JS Express in Cina. | «In transito» |
| `Domestic allocation has begun`, `Under domestic allocation` | Ancora nel magazzino di JS Express in Cina, dopo lo smistamento e prima della partenza. | «In transito» |
| `Waybill Generated for` | Il corriere nel Paese di destinazione ha generato la propria etichetta mentre il pacco è ancora in Cina. | «Etichetta creata», oppure la fase già raggiunta, di solito «In transito» |
| `Arrive at transit center` | Nel centro di transito di JS Express in Cina, tra lo smistamento e l'uscita. | «In transito» |
| `Item departed from origin.`, `Arrive at international airport to abroad` | In uscita dal magazzino e poi arrivato all'aeroporto internazionale in Cina. | «In transito» |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | Su alcune linee, un centro di esportazione in Cina smista e imballa il pacco prima dell'arrivo al punto di uscita dal Paese. | «In transito» |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | Una struttura in Cina pesa e misura il collo, poi lo passa alla linea incaricata del volo. | «In transito» |
| `Start on-site transportation and shipment` | All'aeroporto in Cina: la movimentazione in scalo è iniziata prima dello svincolo doganale di esportazione cinese. | «In transito» |
| `Released From Export Customs` | La dogana di esportazione cinese ha svincolato la spedizione. Non è ancora la dogana italiana. | «In transito» |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | Il volo è partito, talvolta con uno scalo intermedio e un volo in coincidenza. | «In transito» |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Atterrato all'aeroporto di arrivo. NOA è l'avviso di arrivo trasmesso dal gestore di terra aeroportuale. | «In transito» |
| `The customs clearance agent is queuing up to pick up the goods` | Lo spedizioniere doganale attende al terminal merci dell'aeroporto per ritirare la partita: né lui né la dogana l'hanno ancora in mano. | «In transito» |
| `The customs clearance agent has picked up the goods` | L'agente doganale ha ritirato la merce al terminal merci per lo sdoganamento all'importazione. | «In dogana» |
| `The goods have been picked up abroad` | Il partner a destinazione ha preso in consegna la spedizione in aeroporto. Non è un nuovo ritiro dal venditore. | «In transito» |
| `Customs clearance agent received the shipment in destnation country.` | L'agente doganale nel Paese di arrivo ha ricevuto il collo dopo il volo per l'importazione («destnation» è il refuso di JS Express). | «In dogana» |
| `Inbound to customs temporary warehouse` | In un magazzino di temporanea custodia doganale, in attesa dello sdoganamento. | «In dogana» |
| `Released From Import Customs`, `Released from customs: customs cleared.` | La dogana di importazione ha svincolato il pacco. | «In transito» |
| `Depart from the destination distribution center.` | Un centro di distribuzione nel Paese di arrivo inoltra il pacco dopo la dogana. | «In transito» |
| `In transit, it's progressing through post network` | L'operatore postale fa avanzare il pacco nella propria rete, prima del giro di consegna. | «In transito» |
| `collection by overseas warehouse` | Il magazzino di JS Express a destinazione ha ricevuto il pacco dopo lo sdoganamento. | «In transito» |
| `Load cargo overseas`, `Vehicles depart from` | Quel magazzino carica il pacco per il corriere locale e il mezzo parte dal luogo indicato accanto alla scansione. | «In transito» |
| `Delivery to local courier`, `Last Mile Delivery` | Affidato al corriere che consegna in Italia, prima della sua prima scansione. Non è ancora stato consegnato a te. | «In transito» |
| `Delivery in progress` | Su alcune tratte: il corriere locale ha il pacco nella propria filiale prima dell'uscita del furgone. Nonostante le parole, non è ancora in consegna. | «In transito» |
| `到达待取` | Stato di JS Express in cinese: arrivato e in attesa di ritiro. Come luogo compare solo il Paese. | «Pronto per il ritiro» |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | Avviso di JS Express dopo una mancata consegna per indirizzo incompleto o errato; invita a contattare la sede locale (Peek rimuove i numeri di telefono). | «Tentativo di consegna» |

Lo stesso avviso sull'indirizzo può comparire anche in cinese (`收件人地址不详或有误，派送失败，逾期退回或销毁的具体费用以账单为准，麻烦让收件人联系下当地`), con un recapito telefonico in fondo: Peek lo traduce in inglese e mostra «Tentativo di consegna».

**Scansioni di una posta partner europea.** Su alcune linee postali europee compaiono `Package scanned at carrier facility` e `Departure to country of destination` in un hub europeo, poi `Arrival from abroad`, `Departure to distribution network`, `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days.` e `Arrival distribution office` nel Paese di arrivo, tutte mostrate come «In transito». Se il destinatario è assente, `Not Delivered - Addressee not present- Message left` segnala l'avviso lasciato («Tentativo di consegna»), seguito da `Item arrival at collection point for pick-up` quando il pacco è disponibile al punto di ritiro («Pronto per il ritiro»).

**Un corriere senza nome in maiuscolo.** Su altre linee, JS Express riporta le scansioni del corriere finale tutte in maiuscolo senza indicarne il nome. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` e `SHIPMENT PROCESSED` segnano il passaggio nell'hub, e `DEPARTED FROM DEPOT` la partenza verso la filiale di zona («In transito»). `RECEIVED AT LOCAL DEPOT` indica quella filiale prima del giro o dopo un tentativo andato a vuoto: non è un punto di ritiro per il destinatario, quindi Peek mantiene lo stato della riga precedente. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` e `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` sono tentativi falliti («Tentativo di consegna»); dopo il terzo ti viene chiesto di concordare la riconsegna, e `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` conferma che il pacco riparte secondo la tua richiesta («In transito»). `DELIVERY EXCEPTION` e `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, dopo `ARRIVE AT THE WAREHOUSE` («In transito»), indicano un blocco in attesa di conferma dei dati («Problema segnalato»).

**Righe che non sono scansioni.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, `货物电子信息已经收到`, `Your package is being prepared by the sender and will be handed over to us soon` e, in un hub europeo, `Shipment Data Uploaded` seguito da `Processed` registrano solo i dati elettronici trasmessi a un partner, spesso giorni prima del pacco fisico. Anche `Consignee Contacted`, scritto nello stesso secondo di un tentativo fallito, annota una chiamata o un messaggio del corriere senza cambiare lo stato.

**Orari non in ordine.** Gli orari non indicano il fuso e mescolano orologi diversi: ora di Pechino in Cina e, di norma, ora locale negli aeroporti e presso i corrieri europei. Affidati all'ordine delle righe, non solo all'ora scritta.

## Chi consegna i pacchi JS Express in Italia

JS Express elenca l'Italia tra i Paesi serviti dalla sua linea Europa (insieme a Regno Unito, Francia, Germania, Spagna, Svizzera e Danimarca, esclusi alcuni CAP remoti). Nella cronologia di JS Express conosciuta da Peek, però, non compare il nome di un corriere finale in Italia.

La scheda interna di JS Express può contenere il codice del corriere locale senza mai indicarne il nome, e la pagina web di JS Express non mostra né l'uno né l'altro. Quando la forma di quel secondo codice appartiene con certezza a un unico corriere attivo in Italia, Peek mostra «Consegna con …» insieme al suo codice e lo interroga direttamente. Se invece è un [codice postale internazionale](guide:tracking-number-formats) valido (due lettere, nove cifre e due lettere), Peek interroga [Poste Italiane](carrier:poste-italiane). Se Peek non indica alcun corriere, chiedi al negozio chi effettua la consegna in Italia e con quale codice.

Tentativi di consegna, avvisi di giacenza e punti di ritiro seguono le regole del corriere locale: JS Express non ne pubblica. Poiché una riga `到达待取` riporta solo il Paese, controlla il tracciamento del corriere locale o chiedi al venditore dove ritirare il pacco. Vedi anche [mancata consegna e avviso di giacenza](guide:missed-delivery) e [pacco non ritirato in tempo](guide:uncollected-parcel).

## Pacco JS Express bloccato, in dogana o non arrivato

**`未获取到任何数据,请提供有效单号!`** La pagina inglese di JS Express risponde con questa frase in cinese quando non conosce il codice («nessun dato trovato, fornisci un numero valido»). `No track data for now!` significa che non ci sono ancora scansioni. In entrambi i casi Peek mostra «In attesa di JS Express» su peektracker.com e continua a controllare. Se dopo due giorni JS Express continua a non riconoscere il codice e nessun altro servizio risponde, compare «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.», e da quel momento Peek controlla una volta al giorno e, passata una settimana dall'aggiunta, una volta a settimana. Confronta il codice carattere per carattere con quello ricevuto dal negozio; se dopo qualche giorno non compare nulla, chiedi al venditore se ha davvero affidato il pacco.

**Fermo su `Electronic notification of order creation received.`** JS Express non ha ancora preso in carico il pacco in magazzino. Chiedi al negozio quando lo ha consegnato allo spedizioniere.

**Fermo su `Inbound to customs temporary warehouse`.** Il pacco attende lo sdoganamento all'importazione (su alcune tratte compaiono `Start Custom clearance Process` e poi `customs clearance completed`; un `Under customs clearance, please wait patiently.` prima del volo riguarda invece la dogana di esportazione cinese, «In transito»). JS Express vende la sua linea Europa in modalità DDP: riscuote l'IVA in anticipo dal venditore e non rilascia ricevuta fiscale. In generale, per gli acquisti online da Paesi extra-UE fino a 150 € si applica dal 1° luglio 2026 un dazio doganale di 3 € per voce doganale, e dal 1° novembre 2026 si aggiunge nelle vendite a distanza la Tassa di Gestione dell'Unione di 2 € per voce doganale, qualunque sia il valore; Poste Italiane cura direttamente lo sdoganamento solo per i flussi postali UPU. Se un corriere ti chiede un pagamento che avevi già versato all'acquisto, verifica con il venditore prima di pagare. Approfondisci in [pacco fermo in dogana](guide:customs), [tracciamento fermo](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Nessun aggiornamento dopo `Delivery to local courier`.** Il pacco è passato al corriere locale. Se Peek mostra il secondo codice, cercalo sul sito di quel corriere; altrimenti chiedi al negozio chi consegna.

**Consegnato ma non ricevuto.** Contatta prima il corriere che ha effettuato la consegna in Italia e avvisa subito anche il negozio. Leggi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito, danneggiato o rispedito indietro.** Il cliente di JS Express è il venditore: secondo le condizioni di JS Express, qualsiasi richiesta di risarcimento va presentata per iscritto dal cliente entro 30 giorni dalla presa in carico del pacco da parte di JS Express, e le pratiche per le spedizioni assicurate passano dal suo conto cliente. Chiedi quindi subito il rimborso o una nuova spedizione al negozio o al marketplace senza aspettare. Se un pacco non può essere consegnato (destinatario assente, rifiuto, indirizzo errato o blocco doganale), JS Express organizza il rientro a spese del mittente se questi lo richiede; altrimenti la spedizione viene abbandonata e distrutta dalla dogana a destinazione. Su alcune tratte, `Refused` seguito da `Returned` significa che il pacco rifiutato è rientrato al centro di smistamento del corriere, non ancora dal venditore («Problema segnalato»). Vedi [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti JS Express

Al 16 novembre 2026, la pagina dei contatti in cinese di JS Express riporta questi recapiti (la pagina inglese è più vecchia e indica ancora numeri e indirizzi precedenti):

- **Linea telefonica:** 400-0353-518, un numero cinese indicato anche per le ricerche internazionali. JS Express non pubblica orari e non specifica se sia raggiungibile dall'estero.
- **QQ e WeChat:** QQ aziendale 4000353518 e account ufficiale WeChat `js-express`.
- **Reclami:** un modulo sulla pagina inglese «Complaint» e lo stesso numero 4000353518.
- **Sedi:** sede centrale e magazzino a Suzhou, altri magazzini in Cina e uffici della rete a Hong Kong e a Walnut, in California.

Poiché i reclami verso JS Express spettano al venditore che ha acquistato la spedizione, rivolgiti al negozio per il tuo ordine e al corriere locale per la consegna in Italia.

## Tracciare un pacco JS Express con Peek

[Peek](/) riconosce un codice JS Express dalla sua forma (JSE, due lettere, dieci cifre e YQ), non usata da nessun altro corriere del suo catalogo, e interroga direttamente il tracking di JS Express. Se hai solo il codice del corriere locale, incolla quello: Peek segue più di 3.500 corrieri e, se non riconosce il vettore dal formato, puoi sceglierlo con «Cambia».

Peek legge la pagina inglese di JS Express e mostra l'intera cronologia, il luogo di ogni scansione e il Paese di destinazione, che quella pagina tralascia. `到达待取` compare in inglese come `Arrived, awaiting collection`, così come l'avviso cinese sull'indirizzo errato. I nomi di chi ritira la consegna e i numeri di telefono vengono rimossi, e restano fuori anche le foto di consegna e il numero d'ordine del negozio. Come consegna prevista Peek mostra il giorno stimato da JS Express e lo nasconde non appena il pacco passa «In consegna», dato che il giro avviene in giornata. Le righe che non sono scansioni non cambiano lo stato.

Le scansioni in Cina sono lette sull'ora di Pechino e le altre nel fuso ricavato dal luogo o dal Paese di destinazione, tutte convertite nel fuso orario del tuo dispositivo, dalla più recente alla più vecchia.

Quando il secondo codice appartiene senza dubbi a un corriere attivo in Italia, Peek mostra «Consegna con …» insieme a quel codice e lo controlla; se è un codice postale internazionale valido, interroga Poste Italiane. Finché quel corriere ha solo il preavviso elettronico senza scansioni proprie, compare «Annunciato a …, non ancora ricevuto». Non appena pubblica una scansione almeno recente quanto l'ultima di JS Express, Peek segue il pacco lì mostrando entrambi i codici e conservando le scansioni di JS Express.

Peek controlla ogni 10 minuti, e ogni 2 minuti (ogni 5 quando deve aprire la pagina di JS Express in un browser) dalle 6:00 ora locale nel giorno in cui il pacco è «In consegna», finché risponde il tracking di JS Express (tramite servizi di tracciamento, ogni 15 minuti). Quando la consegna passa a un altro corriere, Peek segue il ritmo di chi consegna il pacco: ogni ora con Royal Mail e Poste Italiane, ogni 30 minuti con PostNL e USPS, anche quando è in consegna. Dopo due giorni senza novità (o 12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla una volta all'ora. Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» (dogana, problemi, tentativi, «Pronto per il ritiro», uscita in consegna, consegna e reso) oppure «Solo la consegna». Peek non può contattare JS Express né il venditore al posto tuo.

## Domande frequenti sul tracking JS Express

### Che cos'è JS Express (急速国际)?

È una società logistica cinese per il commercio elettronico transfrontaliero gestita da 急创速供应链管理（苏州）有限公司. Dichiara di essere stata registrata a Hong Kong nel 2010 e di aver aperto il centro operativo di Suzhou nel 2011.

### Dove posso tracciare un pacco JS Express?

Nel riquadro in alto in questa pagina oppure sulla pagina inglese «Express Query» di js-exp.com. La pagina in cinese richiede prima un codice immagine.

### Cosa significa 未获取到任何数据 sul tracking di JS Express?

Significa «nessun dato trovato»: il sistema di JS Express non riconosce quel codice, per un errore di battitura oppure perché non ha ancora ricevuto la spedizione. Verificalo con il negozio.

### Chi consegna i pacchi JS Express in Italia?

Un corriere locale, anche se nella cronologia di JS Express nota a Peek non compare il nome del vettore italiano. Se la scheda contiene un codice postale internazionale valido, Peek interroga Poste Italiane; altrimenti chiedi al venditore chi effettua la consegna e con quale codice.

### Chi devo contattare se il mio pacco JS Express non arriva?

Il negozio da cui hai acquistato: è il venditore ad aver spedito con JS Express e a poter aprire un reclamo scritto entro 30 giorni. Il numero 400-0353-518 di JS Express è una linea telefonica cinese.

:::sources
- [JS Express: 急速国际 (home page)](https://www.js-exp.com/) – società operativa
- [JS Express: Introduction (in inglese)](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, e-commerce transfrontaliero
- [JS Express: 企业介绍 (presentazione)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, filiali e piattaforme partner
- [JS Express: Express Query (in inglese)](https://www.js-exp.com/EN/index.php?page=19) – ricerca inglese tramite modulo senza link diretto e messaggi di risposta
- [JS Express: 快件查询 (ricerca spedizioni)](https://www.js-exp.com/index.php?page=19) – codice immagine sulla pagina cinese
- [JS Express: Insurance Claims (in inglese)](https://www.js-exp.com/EN/index.php?page=29) – reclami del cliente entro 30 giorni e pacchi non consegnabili
- [JS Express: 投保理赔 (assicurazione e rimborsi)](https://www.js-exp.com/index.php?page=29) – richiesta scritta del cliente entro 30 giorni dalla ricezione da parte di JS Express, conto cliente, rientro a pagamento o distruzione
- [JS Express: Complaint (in inglese)](https://www.js-exp.com/EN/index.php?page=32) – modulo reclami e linea 4000353518
- [JS Express: Contact Us (in inglese)](https://www.js-exp.com/EN/index.php?page=35) – recapiti e indirizzi precedenti
- [JS Express: 联系我们 (contatti)](https://www.js-exp.com/index.php?page=35) – numero 400-0353-518 anche per ricerche internazionali, QQ, WeChat, sedi in Cina, Hong Kong e California
- [JS Express: 急速欧洲专线 (linea Europa)](https://www.js-exp.com/index.php?aid=35) – Italia tra i Paesi serviti, modalità DDP con IVA riscossa in anticipo, esclusione di alcuni CAP remoti
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – dazio doganale di 3 € per voce doganale sugli ordini extra-UE fino a 150 €
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – dazio dal 1° luglio 2026, Tassa di Gestione dell'Unione per voce doganale, sdoganamento diretto per i soli flussi UPU
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e tassa di gestione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per voce doganale nelle vendite a distanza qualunque sia il valore
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato JSE…YQ, testi e fasi delle scansioni, scansioni di posta partner europea e di corriere senza nome in maiuscolo, righe informative, fusi orari, apertura nel browser, secondo codice e passaggio a Poste Italiane o corriere locale
:::
