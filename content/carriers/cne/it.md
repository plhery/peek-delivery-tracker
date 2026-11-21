---
title: Tracciamento CNE Express: numeri 3A5V, stati e consegna
description: Traccia un pacco CNE Express (递一物流) dalla Cina continentale: codici 3A5V, significato degli stati, corriere finale, dogana UE e Svizzera e uso di Peek.
slug: tracciamento-cne-express
published: 2026-11-21
updated: 2026-11-21
---

CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) è un operatore logistico transfrontaliero fondato nel 2003 a Shanghai, nella Cina continentale, con linee verso oltre 100 Paesi e regioni. Spedisce per via aerea dalla Cina continentale gli ordini dei venditori su Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify e SHEIN, affidandoli nel Paese d'arrivo a un partner locale per l'ultimo miglio. Controlla il numero sul [tracker inglese di CNE](https://www.cne.com/en/track) oppure incollalo qui sopra; nel catalogo di Peek lo trovi anche cercando `CNE` o `递一物流`.

## Numero di tracciamento CNE Express: formato ed esempi

CNE non pubblica uno schema ufficiale dei suoi codici. Ecco il formato che Peek riconosce subito come CNE Express, con cifre inventate:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `3A5V482910573` | `3A5V` e 9 cifre: un numero CNE Express. |
| `3A5V48291057` | Solo 8 cifre: non ha il formato `3A5V` di CNE. Peek non assegna CNE e lo verifica presso Colis Privé (di cui rispetta i 12 caratteri) e i servizi di tracciamento. Controlla se manca una cifra. |
| `LX482910576CN` | Due lettere, 9 cifre e `CN`: una spedizione postale [China Post](carrier:china-post), i cui servizi (*International E-packet*, *China Post Packet*) sono venduti anche da CNE. |

Minuscole, spazi e trattini vengono ignorati da Peek. Il tracker di CNE accetta più numeri contemporaneamente, separati da spazi o virgole.

**Dove trovarlo.** Nell'email di spedizione del venditore o nella pagina dell'ordine: vedi [come trovare il numero di tracciamento](guide:find-tracking-number), [ordini Temu](guide:temu-orders) e [ordini SHEIN](guide:shein-orders). Sotto `Shipment Information`, il tracker di CNE può mostrare anche l'`Order number` del venditore e il `Last mile number` del corriere finale con sito e telefono.

## Stati del tracciamento CNE Express spiegati

Sopra le righe di movimento il tracker di CNE mostra uno stato complessivo in inglese:

| Stato CNE | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipping` | In viaggio. | lo stato della riga più recente |
| `Customs inspection` | Trattenuto in dogana (`扣关` sulla pagina cinese di CNE). | «In dogana» |
| `Delivered` | Consegnato. | «Consegnato» |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Indirizzo errato, pacco smarrito o distrutto o altro imprevisto. | «Problema segnalato» |
| `Returned` | Rispedito al venditore (`退件` in cinese). | «Restituito al mittente» |
| `Not delivered` | La pagina cinese riporta `未发送` («non spedito»): il pacco non è ancora partito. | lo stato della riga più recente |

Possono comparire anche `Time out` e `Unknown`, per i quali Peek segue le righe della cronologia. Ogni riga riporta orario, dicitura e località:

| CNE scrive | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `Parcel Infomation Received` | Il venditore ha inviato i dati del collo (grafia di CNE); CNE non ha ancora il pacco. | «Etichetta creata» |
| `Picked Up by Courier` | CNE ha ritirato il pacco dal venditore nella Cina continentale. | «Preso in carico» |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Ingresso, smistamento e carico in un impianto CNE. | «In transito» |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Partenza da un centro logistico. | «In transito» |
| `Arrived Export Airport`, `Released From Export Customs` | All'aeroporto di partenza nella Cina continentale e svincolo dalla dogana di esportazione. | «In transito» |
| `Shipment Departed From Original Country` | Il volo è partito dalla Cina continentale. | «In transito» |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Scalo in un aeroporto di transito. | «In transito» |
| `Arrived at Import Airport`, `Arrived at destination` | Arrivato nel Paese di destinazione: smistamento e dogana d'importazione devono ancora avvenire (non è ancora nella filiale locale). | «In transito» |
| `The shipment is picked up by customs broker` | Preso in carico dal broker per lo sdoganamento all'importazione. | «In dogana» |
| `Released From Import Customs`, `Pickup from airport` | Svincolato dalla dogana d'importazione e ritirato dall'aeroporto. | «In transito» |
| `Handled by local carrier` | Consegnato da CNE alla rete di recapito locale; seguirà la presa in carico del vettore. | «In transito» |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Presso il corriere finale o nel centro di smistamento locale. | «In transito» |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Smistato nel Paese d'arrivo e inviato alla sede locale prima del giro di consegna. | «In transito» |
| `The shipment has been processed in the parcel center of origin.` | Scansione di smistamento DHL in Germania ritrasmessa da CNE dopo l'affidamento. | «In transito» |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Stati spagnoli di [CTT Express](carrier:ctt-express) ritrasmessi con spiegazione dopo la virgola: preavviso («Etichetta creata», senza arretramento se già in movimento), invio («In transito»), uscita («In consegna») e recapito («Consegnato»). | in base alla fase |
| `On Board with Courier`, `Your shipment is out for delivery` | Sul furgone del corriere per la consegna odierna. | «In consegna» |
| `Your shipment delivery attempt failed [Recipient not at home]` | Nessuno in casa al passaggio; seguirà un nuovo tentativo. | «Tentativo di consegna», o «Problema segnalato» finché il riepilogo indica `Exception` |
| `Sorting failed []` | Errore di smistamento (le parentesi quadre vuote sono di CNE). | «Problema segnalato» |
| `It was returned to customer's address by CNE` | Restituito da CNE all'indirizzo del venditore mittente. | «Restituito al mittente» |

Dopo il passaggio al partner locale, le sue voci appaiono spesso come «etichetta；spiegazione» (o con virgola per CTT Express): Peek legge solo l'etichetta iniziale, mantiene «In transito» una presa in carico del partner codificata come `Shipping` e usa il codice stato di CNE per le frasi non mappate. Uno stato di riepilogo finale o bloccante (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) prevale sull'ultima riga.

## Chi consegna i pacchi CNE Express in Italia e in Svizzera

CNE gestisce linee dirette verso Europa, America, Australia e Brasile scegliendo corrieri locali per la consegna finale: la sua pagina partner elenca tra gli altri [Poste Italiane](carrier:poste-italiane), [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-de), [La Poste](carrier:la-poste), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), [Correos](carrier:correos-spain), Austrian Post, POST Luxembourg e [USPS](carrier:usps).

Peek associa direttamente tre codici fornitore di CNE quando sono accompagnati dal relativo numero di trasferimento: [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) e [USPS](carrier:usps), mostrando «Consegna con …» (o «Annunciato a …, non ancora ricevuto» finché quel vettore non registra scansioni proprie). Con un altro codice fornitore dotato di numero di trasferimento (come `US SPX`, non associato a un singolo corriere), per un codice postale internazionale valido Peek interroga le poste del Paese di destinazione ([Poste Italiane](carrier:poste-italiane) in Italia, [Posta Svizzera](carrier:swiss-post) in Svizzera) o l'unico corriere individuato dal formato del numero; senza fornitore indicato, il secondo codice può essere un riferimento del negozio e non viene seguito. Tentativi di recapito e giacenza seguono le regole del corriere finale ([mancata consegna](guide:missed-delivery)).

**Dogana in Italia, nell'UE e in Svizzera.** Nell'**Unione europea**, i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € scontano dal 1° luglio 2026 un dazio doganale UE di 3 € per articolo (*per item*) e dal 1° novembre 2026 anche una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo qualunque sia il valore (stessa voce tariffaria, descrizione e, se richiesta, origine; oltre 150 € dazi ordinari). Con avviso del 22 ottobre 2026 CNE ha comunicato ai venditori che dalle `00:00` (ora di Pechino) del 26 ottobre 2026, sulle rotte verso i 27 Paesi UE, riscuote in anticipo dai venditori questi 2 € per pacco e codice HS oltre ai 3 € già riscossi così da luglio 2026, sdoganando in base ai dati dichiarati dal venditore. L'avviso non parla dell'IVA all'importazione in **Italia** (dovuta salvo pagamento già avvenuto all'acquisto tramite IOSS; secondo la Commissione europea, **Livigno** è fuori dal territorio doganale e dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** rientrano nel territorio doganale UE ma non nell'area IVA UE) né di eventuali diritti del corriere finale. In **Svizzera**, secondo l'UDSC, l'IVA è dell'8,1 % (2,6 % ridotta), ma «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi». Vedi [pacco fermo in dogana](guide:customs). Gli avvisi di ritardo per Paese escono in cinese su `新闻公告`.

## Pacco CNE Express fermo, smarrito o restituito

I clienti di CNE sono i venditori: per ritardi, smarrimenti, danni o resi contatta per primo il negozio o il marketplace.

**«In attesa di CNE Express».** Finché CNE risponde che l'ordine non esiste (`订单不存在`), Peek mostra «In attesa di CNE Express»; se dopo due giorni CNE non riconosce ancora il numero e nessun servizio di tracciamento risponde, Peek avvisa: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» Con `Not delivered` (`未发送`) o `Parcel Infomation Received`, chiedi al venditore se resta fermo per alcuni giorni.

**Nessun aggiornamento da quattro giorni.** Dopo quattro giorni senza nuove righe in viaggio, Peek segnala «Nessun aggiornamento da quattro giorni» con la nota su voli lunghi, dogana e fine settimana e il pulsante «Chiedi aiuto a CNE Express» verso la pagina contatti di CNE. Leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**In dogana, con problema, restituito o segnato consegnato ma assente.** Per `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` o `Returned`, rivolgiti al venditore ([pacco smarrito](guide:lost-parcel), [pacco restituito al mittente](guide:returned-to-sender)). Sul tracker di CNE, `View POD` mostra la prova di consegna inserendo nome o CAP del destinatario ([consegnato ma non ricevuto](guide:delivered-not-received)).

## Assistenza clienti e contatti CNE Express

Sulla pagina [Contact us di CNE](https://www.cne.com/en/about/contact):

- **Linea nazionale**: `400-021-5600` (*National Service Hotline*, numero di servizio nella Cina continentale).
- **Sede centrale di Shanghai**: `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (Cina continentale).
- **Orari**: `08:30-18:00 Mon-Sun` senza fuso specificato (i centri di smistamento di Jiaxing e Dongguan nella Cina continentale lavorano `7 x 24 Hours`).
- **Modulo online**: *Consulting form* sulla stessa pagina scegliendo `Complaint`, con telefono, email, nome, numero di tracciamento e messaggio (non è pubblicata un'email di assistenza clienti).

**Attenzione agli SMS truffa.** Il dominio ufficiale di CNE Express è `www.cne.com` (`cne.com`). Prima di aprire link ricevuti via SMS o email, consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco CNE Express con Peek

[Peek](/) riconosce subito CNE Express da un numero `3A5V` («Corriere rilevato»), e qualsiasi link `cne.com` con `?no=` registra il pacco sotto CNE Express (prendendo il primo codice se ce ne sono diversi). Per un altro codice alfanumerico CNE da 6 a 40 caratteri, se Peek mostra «Verifica dei servizi di tracciamento…» o non indica CNE, tocca «Cambia» e scegli CNE Express in «Tutti i corrieri» (o usa «Cambia corriere» in seguito); fuori da 6–40 caratteri alfanumerici Peek segnala che «non emette numeri come questo».

Peek legge lo stato, ogni scansione con la sua località, il Paese di destinazione e il corriere finale con il numero di trasferimento. Legge `Arrived at destination` e `Handled by local carrier` sull'ora di Pechino (`UTC+8`) e gli stati spagnoli ritrasmessi di [CTT Express](carrier:ctt-express) in `UTC+02:00` (o `UTC+01:00` per i passaggi finali di [CTT](carrier:ctt) verso il Portogallo); legge le altre righe sull'orologio del loro luogo o della tratta in cui si trovava il pacco, e mostra ogni orario nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»); tralascia il numero d'ordine del venditore, la data di consegna di riepilogo senza fuso e `View POD` (CNE non fornisce stima di consegna né peso). «Apri il sito CNE Express» apre il tuo codice su `cne.com`. Appena il vettore finale interrogato mostra una scansione almeno recente quanto l'ultima di CNE, Peek passa a seguirlo lì conservando lo storico di CNE.

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 locali nel giorno in cui passa «In consegna» finché risponde il tracker di CNE (dopo il passaggio al corriere finale ne segue la cadenza; tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive) —; negli altri casi e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare CNE né il venditore per te.

## Domande frequenti sul tracciamento CNE Express

### CNE Express è affidabile?

Sì. `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) è stata fondata nel 2003 a Shanghai (Cina continentale), ha un centro operativo a Shenzhen e hub di smistamento a Jiaxing e Dongguan con capacità superiore a 2 milioni di spedizioni al giorno dal 2020, e spedisce per venditori su Amazon, eBay, Walmart, TikTok, Temu e SHEIN.

### Chi consegna i pacchi CNE Express?

Le poste o un corriere locale scelto da CNE (come Poste Italiane, DHL, GLS, DPD, La Poste o Royal Mail nella sua pagina partner), di cui il tracker di CNE può indicare il `Last mile number`.

### Cosa significa `Arrived at destination` su CNE Express?

Il pacco ha raggiunto il Paese di destinazione, ma possono ancora seguire smistamento e dogana d'importazione: non è ancora nella sede locale di recapito. CNE registra questa riga sull'ora di Pechino (`UTC+8`, 7 ore avanti rispetto a Roma o Zurigo in inverno), mentre altre righe all'estero riportano l'ora locale senza fuso e possono apparire fuori ordine.

### Quanto tempo impiega CNE Express a consegnare?

CNE indica ai venditori tempi di riferimento variabili per tratta: `3-8 business days` per *CNE Priority Express* (verso USA, Regno Unito, Francia e Germania) e `5-20 business days` per *CNE Standard Express*. Fai fede alla stima indicata dal negozio.

### Come posso contattare CNE Express?

Chiama il `400-021-5600` (linea della Cina continentale) o lo `021-5109 6677` di Shanghai, oppure compila il *Consulting form* su `cne.com/en/about/contact` selezionando `Complaint`. Per il tuo ordine chiedi prima al venditore.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – tracker in inglese, etichette di stato, Shipment Information e View POD
- [CNE: 运单追踪 (tracciamento in cinese)](https://www.cne.com/track) – stati cinesi 未发送 e 扣关
- [CNE: Home](https://www.cne.com/en) – fondazione nel 2003 a Shanghai, destinazioni, piattaforme e linea 400
- [CNE: 首页 (home page in cinese)](https://www.cne.com/) – 递一物流 e 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – centri di smistamento di Dongguan e Jiaxing, oltre 2 milioni di spedizioni al giorno dal 2020
- [CNE: Partner](https://www.cne.com/en/about/partner) – partner logistici globali
- [CNE: Contact us](https://www.cne.com/en/about/contact) – linea 400, sede di Shanghai, orari 08:30-18:00 e Consulting form (Complaint)
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – servizi China Post venduti da CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – tempi di riferimento 3-8 business days e rotte US/GB/FR/DE
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – tempi di riferimento 5-20 business days
- [CNE: 新闻公告 (notizie e avvisi)](https://www.cne.com/news/notice) – avvisi di ritardo per destinazione
- [CNE: 关于欧盟国家2026年11月税改通知 (avviso riforma UE)](https://www.cne.com/news/article/rDe4ANa6KYOW) – riscossione anticipata dai venditori dei 2 € dal 26 ottobre 2026 oltre ai 3 € per codice HS e sdoganamento sui dati del venditore
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € qualunque sia il valore, definizione di articolo ed esenzione IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia e Lago di Lugano
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e non riscossione fino a 5 franchi
- [Universal Parcel Scraper: CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – formato 3A5V, link ?no=, stati e codici, fornitori finali, orologi Pechino / CTT Express / locali e passaggio
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rilevamento Colis Privé e China Post e passaggio al vettore finale
:::
