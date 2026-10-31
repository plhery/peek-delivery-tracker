---
title: HYWL tracking: numero TRK e stati di Huiyi Logistics
description: Tracking HYWL (Huiyi Logistics, 汇一物流) per i pacchi dalla Cina: com'è fatto il numero TRK, cosa vogliono dire i passaggi in inglese e in cinese e a chi rivolgersi.
slug: hywl-tracking
published: 2026-11-01
updated: 2026-11-01
---

Huiyi Logistics (HYWL, 汇一物流) è 深圳市汇一物流有限公司, un'azienda di logistica di Shenzhen, in Cina, che spedisce all'estero i pacchi dei venditori online. Se un negozio ha spedito il tuo ordine con HYWL, il numero è TRK seguito da dodici caratteri, oppure quattro lettere, due cifre, una D, quattro cifre e quattro lettere: incollalo nel box qui sopra. Il tracking di HYWL è sul suo sito, in cinese, e i passaggi sono in inglese, in cinese o in entrambe le lingue. HYWL non dice quale corriere consegna il pacco in Italia.

## Numero di tracking HYWL: com'è fatto

I numeri di HYWL hanno due forme, entrambe di 15 caratteri. Con caratteri inventati:

| Numero | Cos'è |
| --- | --- |
| `TRK000000000000` | TRK e dodici caratteri, ognuno una cifra o una lettera dalla A alla F: la forma che il sito di HYWL usa come esempio. |
| `AAAA00D0000AAAA` | Quattro lettere, due cifre, D, quattro cifre e quattro lettere: l'altra forma di HYWL. |
| `TRK00000000000` | Solo undici caratteri dopo TRK: non è un numero HYWL. Controlla che non ne manchi uno. |

Nessun altro corriere del catalogo di Peek usa queste forme, ma la forma da sola non basta: prima di attribuire un numero a HYWL, Peek lo verifica sul tracking di HYWL (vedi sotto). In Peek spazi, trattini e minuscole non contano. Il tracking di HYWL invece legge il numero esattamente come lo scrivi, quindi lì scrivilo in maiuscolo.

**Dove trovarlo.** Il venditore prenota la spedizione con HYWL e ti passa il numero, nell'e-mail di spedizione o nella pagina dell'ordine. Se non lo trovi, leggi [dove si trova il codice tracking](guide:find-tracking-number).

**Un link al tracking.** Un link al tracking di HYWL con `?number=` o `?trackingNumber=` seguito dal numero apre direttamente quel pacco. Incollalo intero in Peek: ne legge il numero e assegna subito il pacco a Huiyi Logistics.

## Stati del tracking HYWL: cosa significano

Nei dati di HYWL ogni passaggio ha uno stato e una descrizione. Il suo tracking mostra in alto lo stato del pacco, poi l'ora, la descrizione e il luogo di ogni passaggio, in ordine cronologico. Gli stati:

| Stato di HYWL | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `Picked Up` | HYWL ha il pacco. | «Preso in carico» |
| `In Transit` | Tutto il viaggio, dal punto di raccolta di HYWL in Cina fino agli spostamenti in Italia. | «In transito» |
| `Customs` | Un passaggio doganale. Quelli presenti nel codice di Peek dicono che lo sdoganamento all'esportazione in Cina è concluso. | «In transito» per quelli, «In dogana» per gli altri |
| `Out for Delivery` | In consegna. | «In consegna» |
| `Delivered` | Consegnato. | «Consegnato» |

Le descrizioni dicono di più. Nell'ordine in cui compaiono:

| HYWL scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `Shipment accepted, received by SZHYWL`, `SZHYWL已收取邮件` | HYWL (SZHYWL) ha preso in carico il pacco. È il primo passaggio, con una città e il codice di un ufficio. | «Preso in carico» |
| `Departed Pickup Point`, `离开收寄点` | Ha lasciato il punto di raccolta di HYWL. | «In transito» |
| `Arrival at Origin Processing Center`, `Departure from Origin Processing Center` | In un centro di smistamento in Cina, poi in partenza da lì. | «In transito» |
| `邮件到达处理中心`, `邮件离开处理中心` | Arrivato in un centro di smistamento, poi ripartito. | «In transito» |
| `Received by Carrier`, `承运商接收` | HYWL ha passato il pacco al vettore della sua linea. Non è una nuova spedizione. | «In transito» |
| `Departure from`, `启运` | Partito dal luogo indicato tra parentesi. | «In transito» |
| `In Transit`, `快件在该国内转运中, 干线中转` | In viaggio. Il cinese dice che si sta spostando in Cina, sulla tratta principale. | «In transito» |
| `Customs Clearance Completed`, `出口清关完成` | Lo sdoganamento all'esportazione in Cina è concluso. Non è la dogana italiana. | «In transito» |
| `Departure from International Hub`, `Arrival at Transit Hub` | Partito da un hub internazionale, poi arrivato a un hub di transito lungo il percorso. | «In transito» |
| `Arrival at` | Arrivato nel Paese di destinazione, indicato dal suo codice tra parentesi. | «In transito» |
| `邮件在该国内转运中` | In viaggio nel Paese di destinazione. | «In transito» |
| `Arrival at Processing Center`, `Departure from Processing Center` | In un centro di smistamento in Italia, poi in partenza, con la tua città tra parentesi. | «In transito» |
| `Dispatching`, `出门投递` | In consegna. | «In consegna» |
| `Delivered`, `已妥投` | Consegnato. | «Consegnato» |

**Luoghi.** In Italia i passaggi di HYWL riportano la tua città e il CAP, il primo passaggio una città e il codice di un ufficio. Peek tiene solo la città e taglia ogni nome che segue `signed by` o `签收人`.

**Orari.** Il tracking di HYWL mostra gli orari senza fuso orario. I passaggi con il luogo scritto in cinese, o a Shenzhen, Guangzhou, Dongguan, Huizhou o Hong Kong, sono nell'ora di Pechino: le 20:00 a Pechino sono le 13:00 in Italia con l'ora solare e le 14:00 con l'ora legale.

**Passaggi con una data futura.** La pagina di tracking di HYWL dice che il suo personale crea gli ordini e ne aggiorna il tracciamento in un gestionale interno. I suoi dati possono contenere passaggi con una data successiva al momento in cui guardi: il tracking di HYWL li mostra lo stesso, e anche lo stato in alto può essere in anticipo. Peek tiene da parte questi passaggi del tracking di HYWL finché la loro ora non è passata: quelli in Cina secondo l'ora di Pechino, gli altri leggendo l'orario come UTC. Peek si basa sui passaggi che tiene, non sullo stato di HYWL. Un passaggio con una data futura non è ancora successo, nemmeno `Delivered`.

## Chi consegna i pacchi HYWL in Italia

Il sito di HYWL dice che offre linee con consegna all'estero, ma non nomina i corrieri che consegnano, e il suo tracking non indica né il corriere né il suo numero. Nemmeno il codice di Peek ne nomina uno, per nessun Paese. Quindi Peek non sa chi consegna i pacchi HYWL in Italia e non può passare il pacco al tracking di quel corriere.

I passaggi di HYWL arrivano fino alla consegna: `Dispatching`, poi `Delivered`. Peek non ha trovato tempi di consegna di HYWL, né regole su tentativi di consegna, avvisi di passaggio, punti di ritiro o giacenza: sono quelle del corriere che consegna. Se la consegna non va a buon fine, chiedi al venditore quale corriere consegna il tuo ordine e il suo numero di tracking, poi leggi [mancata consegna](guide:missed-delivery).

## Pacco HYWL fermo o che non arriva

**`暂时无法获取轨迹，请确认单号是否正确或稍后再试。`** Cioè: per ora non è possibile recuperare il tracciamento, verifica che il numero sia giusto o riprova più tardi. Il tracking di HYWL lo scrive quando non trova il numero o non riesce a rispondere. Ricontrolla il numero carattere per carattere, in maiuscolo; se dopo qualche giorno non compare ancora niente, chiedi al venditore se il pacco è partito davvero.

**`已查到单号，暂无更多轨迹节点。`** Numero trovato, per ora nessun altro passaggio: l'ordine esiste, ma HYWL non ha ancora registrato passaggi. Chiedi al venditore quando ha affidato il pacco a HYWL.

**Il tracking di HYWL mostra più di Peek.** Peek tiene da parte i passaggi datati dopo il suo ultimo controllo, quindi la pagina di HYWL può mostrarne alcuni che Peek non ha. Guarda gli orari: un passaggio con una data futura non è ancora successo. Regolati sulla data di consegna stimata dal negozio e, quando è passata, scrivi al venditore. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Niente dopo `Arrival at`.** Il pacco è arrivato in Italia. Se ti chiedono di pagare spese d'importazione, leggi [pacco fermo in dogana](guide:customs). Se resta fermo per giorni, chiedi al venditore quale corriere lo consegna e con che numero.

**`Delivered`, ma non è arrivato niente.** Prima guarda l'ora del passaggio: se è ancora nel futuro, la consegna non è avvenuta. Se è già passata, avvisa il venditore e chiedi quale corriere ha consegnato. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o rispedito al mittente.** Il venditore ha prenotato la spedizione con HYWL, quindi chiedi a lui, o al marketplace, un rimborso o un nuovo invio. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## HYWL contatti: telefono e assistenza clienti

Al 30 ottobre 2026 il sito di HYWL è in cinese ed è pensato per i venditori che prenotano spedizioni. Riporta un indirizzo nel distretto di Yantian, a Shenzhen, e 待补充 (da aggiungere) dove dovrebbero esserci il telefono e l'e-mail.

La spedizione l'ha prenotata il venditore: è a lui che devi chiedere del tuo ordine e, se non risponde, al marketplace.

## Tracciare un pacco HYWL con Peek

Dalla sola forma, [Peek](/) non può essere sicuro che un numero sia di HYWL. Incollalo lo stesso: Peek segue più di 3.500 corrieri. Mentre lo aggiungi, Peek interroga il tracking di HYWL («Verifica dei servizi di tracciamento…») e, se HYWL conosce il numero, mostra «Huiyi Logistics ha questo pacco.» Altrimenti premi «Cambia» e scegli Huiyi Logistics sotto «Compatibili con questo numero» (lo trovi anche scrivendo HYWL o 汇一物流), oppure usa «Cambia corriere» su un pacco già aggiunto.

Se aggiungi un numero TRK senza scegliere il corriere e HYWL non lo conosce ancora, Peek mostra «Probabilmente Huiyi Logistics» e «In attesa della prima scansione di Huiyi Logistics», e continua a chiedere. Dopo una settimana ti chiede di verificare il numero con il mittente o di scegliere un altro corriere.

Peek mostra lo stato, ogni passaggio con il suo luogo e, quando i dati di HYWL lo indicano, il Paese di destinazione. Legge i passaggi in Cina con l'ora di Pechino, ricava il fuso degli altri dal loro luogo o dalla tratta in cui si trovava il pacco e mostra ogni orario nel fuso del tuo dispositivo, dal più recente. Non mostra i CAP né il Paese di partenza. HYWL non indica data di consegna prevista, peso, servizio, corriere che consegna e relativo numero, nomi di mittente e destinatario né prova di consegna, quindi Peek non li mostra.

Quando il tracking di HYWL non risponde, Peek si rivolge ai servizi di tracciamento e mostra la cronologia che hanno, con le loro parole. Nelle loro copie Peek non tiene da parte i passaggi con una data futura, quindi guarda l'ora di ogni passaggio.

Peek resta sul tracking di HYWL: se il venditore ti dà il numero del corriere che consegna, aggiungilo come pacco separato. Peek controlla ogni 10 minuti di giorno, ogni 2 minuti quando il pacco è in consegna e di notte ogni una o due ore. Per le notifiche scegli tra «Ogni scansione», «Passaggi importanti» e «Solo la consegna»: «Passaggi importanti» comprende `Dispatching` e `Delivered`, non la dogana d'esportazione né lo smistamento di HYWL. Peek non può contattare HYWL o il venditore al posto tuo.

## Domande sul tracking HYWL

### Che cos'è HYWL (汇一物流)?

È 深圳市汇一物流有限公司 (Huiyi Logistics), un'azienda di logistica di Shenzhen, in Cina. Dice di essere stata fondata nel 2013 e spedisce all'estero per conto di venditori dell'e-commerce transfrontaliero e aziende di import-export. I suoi numeri iniziano con TRK, oppure hanno una D tra le cifre.

### Dove posso tracciare un pacco HYWL?

Nel box qui sopra, oppure sul sito di HYWL, szhywl.it.com, in cinese: scrivi il numero in maiuscolo e premi 官网查询. L'altro pulsante, 17TRACK 查询, apre lo stesso numero su 17TRACK, un servizio di tracking.

### Cosa vogliono dire 出门投递 e 已妥投 nel tracking HYWL?

出门投递 significa in consegna (`Dispatching` nella versione inglese di HYWL), 已妥投 consegnato (`Delivered`). Guarda l'ora del passaggio: il tracking di HYWL mostra anche passaggi con una data futura, che non sono ancora successi.

### Chi consegna i pacchi HYWL in Italia?

HYWL non lo dice, e il suo tracking non indica né il corriere né il suo numero; nemmeno il codice di Peek ne nomina uno. Chiedi al venditore quale corriere consegna il tuo ordine.

### A chi mi rivolgo se il pacco HYWL non arriva?

Al venditore da cui hai comprato: è lui che ha prenotato la spedizione con HYWL. Il sito di HYWL non riporta né telefono né e-mail, solo 待补充 (da aggiungere).

:::sources
- [Huiyi Logistics: 深圳市汇一物流有限公司 (home page)](https://www.szhywl.it.com/) – nome dell'azienda, Shenzhen, 2013, clienti e servizi, consegna all'estero, i due pulsanti del tracking, indirizzo a Yantian, telefono ed e-mail 待补充
- [Huiyi Logistics: 物流轨迹查询 (tracking)](https://www.szhywl.it.com/track.html) – tracking pubblico, ordini e passaggi gestiti nel gestionale interno, i suoi messaggi, orari mostrati come registrati, link che apre un pacco
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomi, forme dei numeri e loro corrispondenza debole, link, codici, descrizioni e loro fasi, luoghi e CAP, ora di Pechino, passaggi con data futura tenuti da parte, nessun corriere che consegna, cosa Peek mostra e lascia fuori
:::
