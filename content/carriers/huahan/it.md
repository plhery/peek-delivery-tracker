---
title: Tracciamento HuaHan Logistics: codice HHW…YQ e consegna
description: Traccia un pacco HuaHan Logistics (华翰物流) dalla Cina: formato del numero HHW…YQ, significato degli stati, corriere locale, dogana e verifica su Peek.
slug: tracciamento-huahan-logistics
published: 2026-11-18
updated: 2026-11-21
---

HuaHan Logistics (华翰物流, nota anche come HuaHan Express o 华翰速递) è un operatore logistico cinese per l’e-commerce transfrontaliero che spedisce i pacchi fuori dalla Cina per via aerea o postale e li affida a un corriere locale nel paese di destinazione per la consegna finale. Se un negozio online ha spedito il tuo ordine con HuaHan, cerca un codice che inizia con `HHW` e termina con `YQ` e incollalo nel riquadro in alto. Il sistema di tracciamento pubblico di HuaHan su [hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html) può indicare anche il corriere incaricato dell’ultimo miglio e il relativo numero di tracciamento locale.

## Numero di tracciamento HuaHan: come riconoscerlo

Peek riconosce le lettere di vettura HuaHan nel formato composto da `HHW`, due lettere, dieci cifre e `YQ`: 17 caratteri complessivi. Esempi con cifre inventate:

| Codice | Che cos’è |
| --- | --- |
| `HHWCN4829105736YQ` | `HHW`, due lettere, dieci cifre e `YQ`: una lettera di vettura HuaHan. |
| `HHWCN482910573YQ` | Solo nove cifre: non corrisponde alla struttura di HuaHan. Verifica se manca una cifra. |
| `WNBAA4829105736YQ` | Termina anch’esso con `YQ`, ma inizia con `WNB`: è un numero WanbExpress. |
| `JXCAA4829105736YQ` | Inizia con `JXC`: è un numero Juxi Express. |

Molti spedizionieri cinesi chiudono i propri codici con `YQ`, distinguendosi per le lettere iniziali: `HHW` identifica esclusivamente HuaHan e nessun altro corriere nel catalogo di Peek usa questo prefisso. Spazi, trattini e lettere minuscole non fanno differenza quando incolli il codice in Peek.

**Dove trovarlo.** Il venditore lo comunica nell’e-mail di conferma della spedizione o nella pagina del tuo ordine. Se non riesci a trovarlo, consulta [come trovare il numero di tracciamento](guide:find-tracking-number). La pagina pubblica di ricerca di HuaHan ([hh-exp.cn/WebTrack/CargoTrack.html](https://www.hh-exp.cn/WebTrack/CargoTrack.html), intitolata `运单追踪`) è in cinese e accetta fino a 20 codici separati da virgola (`多个单号请用英文逗号隔开（最多支持 20 个单号同时查询）`) dopo una verifica preliminare: cliccare nell’ordine indicato o risolvere una semplice somma; la home [hh-exp.cn](https://www.hh-exp.cn/) ospita invece il portale d’accesso per i venditori (`华翰物流订单管理系统`), con un collegamento diretto al tracciamento pubblico.

**Un secondo codice.** La scheda di HuaHan può riportare anche il numero assegnato al pacco dal corriere locale. Talvolta HuaHan aggiunge dopo un trattino basso un suffisso interno estraneo al codice del corriere finale: ometti `_` e ciò che segue quando cerchi la spedizione sul sito del corriere locale.

## Stati di tracciamento HuaHan spiegati

Il tracker di HuaHan elenca ogni scansione con data e ora, dicitura e località, dalla più recente alla più vecchia. Le tappe gestite da HuaHan sono in inglese, talvolta precedute dal cinese oppure solo in cinese in alcuni passaggi di magazzino e aeroporto. Ecco le voci principali nell’ordine consueto del viaggio:

| Dicitura su HuaHan | Cosa significa per te | Stato in Peek |
| --- | --- | --- |
| `快件电子信息已经收到 Shipment information received`, `Shipment information received` | Il venditore ha trasmesso i dati della spedizione a HuaHan, che non ha ancora ricevuto fisicamente il pacco (quando compare solo `Shipment information received`, Peek lo traduce in «Dati della spedizione ricevuti»). | «Etichetta creata» |
| `快件到达作业中心 Shipment arrived at facility`, `Arrived at origin facility`, `Has entered the warehouse` | Il centro operativo di HuaHan in Cina (a Shenzhen nelle cronologie presenti nel codice di Peek) ha preso in carico il pacco. | «Preso in carico» |
| `快件操作完成 Shipment departed from facility`, `Departed from origin facility`, `货物离开操作中心`, `Shipment received`, `Shipments were sent` | Lavorato e in uscita dal centro d’origine, oppure transitato nel magazzino di smistamento HuaHan prima dell’esportazione. | «In transito» |
| `Transfer to Origin airport`, `快件从转运仓发出至机场，出口报关中（…）` | In trasferimento verso l’aeroporto di partenza con dichiarazione doganale d’esportazione in corso. | «In transito» |
| `Export customs clearance completed`, `快件抵达机场，预计…号航班飞`, `航班延误，重新配载至…号航班飞` | Sdoganamento all’esportazione in Cina completato (non è la dogana del tuo paese) e volo programmato o riprotezione su altro volo. | «In transito» |
| `Departed from airport of Origin` | L’aereo è decollato. | «In transito» |
| `Arrived at destination airport`, `拆板中` | Il volo è atterrato nell’aeroporto di arrivo o di scalo; apertura del bancale aereo in corso (`拆板中`). | «In transito» |
| `Import Customs clearance started` | La dogana d’importazione del paese di destinazione ha preso in esame la spedizione. | «In dogana» |
| `Import Customs clearance completed`, `目的国清关完成` | La dogana d’importazione ha svincolato il pacco. | «In transito» |
| `Has been given to local carrier`, `转单：…` | HuaHan ha affidato il pacco al corriere locale (o riporta dopo `转单：` il codice di trasferimento). Seguono le scansioni di quest’ultimo. | «In transito» |

**Le righe del corriere locale.** Dopo l’affidamento, il tracker di HuaHan inoltra gli eventi dell’operatore che consegna a destino. Peek li classifica in base al codice interno di HuaHan o alla formulazione del messaggio:

| Dopo la consegna al corriere locale | Cosa significa per te | Stato in Peek |
| --- | --- | --- |
| `Ready to Ship, Parcel received and processed` | Il corriere locale ha ricevuto e smistato il pacco. | «In transito» |
| `The parcel is in the central warehouse waiting to be processed.`, `In Transit, Shipment arrived to sorting centre / Shipment in the sorting centre`, `In Transit, Shipment remained at sorting hub` | Nel magazzino centrale o in un centro di smistamento del corriere locale. | «In transito» |
| `In Transit, Arrived to HU HUB`, `In Transit, Parcel has entered the Linehaul network.` | Passaggio in un hub europeo (qui in Ungheria) e instradamento sulla rete di linea. | «In transito» |
| `In Transit, Picked Up` (seguito dal tedesco `Einrollung` di DPD), `In Transit, Scanned By DPD At parcel delivery centre.`, `In Transit, At parcel delivery centre.` | Ingresso nella filiale di consegna DPD (non è un ritiro al tuo domicilio) e scansione giornaliera finché il pacco resta in filiale. | «In transito» |
| `Out for Delivery, Parcel is at courier, delivery expected soon.` | Il pacco è sul furgone del corriere per la consegna. | «In consegna» |
| `The courier will deliver the parcel soon. Additional details are available in the SAMEDAY app, email or SMS.` | In consegna con Sameday, corriere attivo in Romania e Bulgaria. | «In consegna» |
| `Delivery Attempted`, `Delivery Attempted, Unfortunately we have not been able to deliver your parcel. Pickup parcelshop delivery after first delivery attempt.` | Tentativo di consegna non riuscito con DPD; con la dicitura estesa il pacco viene indirizzato a un punto Pickup. | «Tentativo di consegna» |
| `Awaiting Pickup, Parcel was deposited in Pickup parcel station` | Depositato da DPD presso un punto di ritiro o armadietto Pickup. | «Pronto per il ritiro» |
| `The parcel has been successfully delivered.`, `Delivered` | Consegnato (la voce singola `Delivered` appare in Peek come «Consegnato»). | «Consegnato» |

HuaHan registra uscita in consegna, punto di ritiro e recapito sotto propri codici (`CB`, `PB`, `CC`), ma Peek controlla prima le parole dell’evento: HuaHan usa infatti il codice di consegna `CB` anche per l’uscita da un centro di distribuzione [USPS](carrier:usps) (`Departed USPS Facility`), che Peek mantiene «In transito».

**Due messaggi che traggono in inganno.** `We will pick up your parcel from the sender soon.` può comparire all’inizio, prima ancora che HuaHan abbia ricevuto la merce in Cina: è il preavviso registrato dal corriere locale, non un ritiro da te; Peek mostra «Etichetta creata». Allo stesso modo, `The parcel was redirected. As soon as it is in the couriers possession, it will be processed as requested.` annota una richiesta di reindirizzamento mentre il pacco si trova ancora in Cina: non sposta la spedizione e Peek non gli assegna uno stato.

**Dati elettronici, non movimenti fisici.** `The instruction data for this shipment have been provided by the sender to DHL electronically.` (con o senza punto finale) è la trasmissione telematica a [DHL](carrier:dhl); `Pendiente de recepción en CTT Express` è il preavviso di [CTT Express](carrier:ctt-express), `Pre-Shipment Info Sent to USPS` quello di USPS e `货物电子信息已经收到` conferma in cinese la ricezione dei dati dell’ordine. Nessuno di questi è uno spostamento fisico: Peek mostra «Etichetta creata» o conserva lo stato già raggiunto.

**Una scansione duplicata su due righe.** A volte il tracker di HuaHan elenca lo stesso evento del corriere finale due volte nello stesso minuto: una riga con località e testo originale del corriere, e una senza località con una formula generica di HuaHan come `Delivered, Parcel was delivered successfully`. Peek conserva soltanto la riga provvista di località.

**Tramite la rete postale.** HuaHan può instradare un pacco tramite [China Post](carrier:china-post). Il tracker riporta allora in cinese le scansioni di China Post con l’ufficio tra parentesi `【】` e, più avanti, quelle della posta di destinazione copiate da China Post:

| Scansione postale inoltrata | Cosa significa per te | Stato in Peek |
| --- | --- | --- |
| `中国邮政已收取邮件` | China Post ha preso in consegna il pacco da HuaHan. | «In transito» |
| `邮件已在【…】完成分拣，准备发出`, `邮件到达【…】`, `邮件离开【…】，正在发往【…】`, `邮件离开【…】，正在发往下一站` | Smistato, arrivato o ripartito presso un ufficio di China Post indicato tra `【】`. | «In transito» |
| `送交出口海关` | Consegnato alla dogana d’esportazione cinese (non alla dogana del tuo paese). | «In dogana» |
| `出口海关/放行`, `已交承运商运输` | Svincolato dalla dogana d’esportazione e affidato al vettore aereo. | «In transito» |
| `到达寄达地`, `到达寄达地处理中心`, `到达境外经转局` | Arrivato nel paese di destinazione, nel suo centro di smistamento postale o in un ufficio di transito estero. | «In transito» |
| `境外进口海关留存待验(等待提交给边境机构/安全部门)`, `境外进口互换局留存`, `送交境外进口海关` | Trattenuto all’ufficio di scambio o sottoposto alla dogana d’importazione locale. | «In dogana» |
| `寄达地海关允许进口（无需缴税）` | Ammesso all’importazione dalla dogana locale senza dazi da pagare. | «In transito» |
| `离开【…】处理中心`, `已到达【…】投递局` | Uscito dal centro di lavorazione locale e giunto al centro di recapito. | «In transito» |

**Tramite Landmark Global.** Per altre spedizioni HuaHan inoltra in inglese le voci di [Landmark Global](carrier:landmark-global). Le prime due arrivano prima della presa in carico in Cina; le successive seguono il passaggio alla rete di [bpost](carrier:bpost):

| Voce Landmark Global inoltrata | Cosa significa per te | Stato in Peek |
| --- | --- | --- |
| `Shipment Data Uploaded` | Dati del mittente registrati da Landmark Global. | «Etichetta creata» |
| `Processed` | Elaborazione nello stesso minuto: solo un avviso, e Peek mantiene la fase precedente. | La fase precedente |
| `Incoming scan at facility` | Arrivo in un deposito dopo il passaggio alla rete di bpost. | «In transito» |
| `Outgoing scan at facility` | Partenza da quel deposito (codificata da HuaHan sotto l’uscita in consegna, ma mostrata «In transito» da Peek perché il giro di consegna può avvenire giorni dopo). | «In transito» |
| `Out for delivery (On the way)` | In giro di consegna. | «In consegna» |
| `Delivery attempt (Delivery not possible)` | Tentativo di consegna non andato a buon fine. | «Tentativo di consegna» |

## Chi consegna i pacchi HuaHan e in quanto tempo

Sul proprio sito istituzionale ([hh-exp.com](https://www.hh-exp.com/) e [hh-exp.com/gyhh](https://www.hh-exp.com/gyhh)), HuaHan (`深圳市华翰鸿运国际物流技术有限公司`, fondata nel 2008 con sede a Shenzhen) dichiara di servire oltre 150 paesi e regioni in Europa, Nord e Sud America e Australia, con linee dedicate ai piccoli pacchi e-commerce di merci speciali (`特货小包专线`, come cosmetici, batterie e sigarette elettroniche) e canali postali (`特货邮路`). Nella home page indica una tempistica complessiva orientativa di 6–12 giorni lavorativi (`整体时效6-12个工作日`), senza pubblicare tabelle per singolo paese né l’elenco dei corrieri partner: fai riferimento alla stima indicata dal venditore al momento dell’ordine.

Dopo `Has been given to local carrier`, il recapito passa a un corriere del paese di arrivo. Nelle cronologie lette dal codice di Peek compaiono [DHL](carrier:dhl), DPD, [CTT Express](carrier:ctt-express), Sameday, [USPS](carrier:usps), [China Post](carrier:china-post) (con consegna tramite l’operatore postale del paese di destinazione) e [Landmark Global](carrier:landmark-global) con recapito tramite la rete [bpost](carrier:bpost). Peek segue sempre il corriere e il codice effettivamente riportati sulla pratica del tuo pacco.

**Tentativi di consegna e giacenza.** Numero di passaggi, avviso di giacenza e giorni utili per il ritiro dipendono dalle regole del corriere locale: rivolgiti a quest’ultimo con il suo codice locale per concordare una riconsegna. Vedi [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

## Dogana, oneri d’importazione e falsi SMS

HuaHan opera per i venditori e-commerce e le sue pagine non specificano se gli oneri doganali del tuo acquisto siano già stati assolti dal mittente: controlla nella ricevuta dell’ordine se IVA e dazi erano compresi nel prezzo. Approfondisci in [pacchi fermi in dogana](guide:customs) e [riconoscere gli SMS truffa sulle consegne](guide:delivery-scam-texts).

- **Unione europea.** Dal 1° luglio 2026, nell’**Unione europea** le merci acquistate a distanza fuori dal territorio doganale UE in spedizioni di valore intrinseco fino a 150 € scontano un dazio doganale di 3 € per categoria di articoli; dal 1° novembre 2026 si aggiunge una commissione di gestione dell’Unione di 2 € per categoria di articoli, indipendentemente dal valore della spedizione (una categoria raggruppa le merci con la stessa classificazione tariffaria, descrizione e, ove richiesta, origine, a prescindere dal numero di pezzi).
- **Italia.** In **Italia**, Livigno resta fuori sia dal territorio doganale sia dal territorio IVA dell’UE, mentre Campione d’Italia e le acque italiane del lago di Lugano rientrano nel territorio doganale dell’UE ma sono fuori dal territorio IVA dell’UE. Per i pacchi provenienti da Paesi extra UE gestiti da [Poste Italiane](https://www.poste.it/ricevere-spedizioni-da-estero) in **Italia**, gli importi dovuti (IVA, dazio, commissione di gestione dell’Unione e Diritti Postali) si pagano alla consegna, in contanti o tramite POS al corriere fino a 999 €, oppure all’Ufficio Postale al momento del ritiro, o ancora online prima della consegna sull’App Poste Italiane e su `poste.it`, anche dal link che Poste Italiane invia per notifica (in App, per e-mail, in Bacheca `poste.it` o per SMS) se il mittente ha fornito i tuoi contatti; nella sua [guida antitruffa](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe), Poste Italiane ricorda che né Poste Italiane né le Forze dell’Ordine richiedono mai, tramite e-mail, SMS, lettere, telefonate o social network, dati personali, credenziali di accesso ai servizi bancari, codici delle carte di pagamento e codici dispositivi o di autorizzazione.
- **Svizzera.** In **Svizzera**, l’Ufficio federale della dogana e della sicurezza dei confini (UDSC) non riscuote dazi né IVA sull’importazione fino a 5 franchi svizzeri ciascuno (pari a una base imponibile di 62 CHF con aliquota IVA dell’8,1 % o di 193 CHF al tasso ridotto del 2,6 %), al netto delle spese di sdoganamento applicate separatamente dalla posta o dal corriere per il servizio svolto.

## Pacco HuaHan in ritardo, fermo o non trovato

**Codice non trovato (`没有找到记录`).** Se HuaHan non ha ancora registrato il numero, il suo tracker risponde `没有找到记录` («nessun record trovato»); Peek mostra «In attesa di HuaHan Logistics» e continua a verificare. Se dopo due giorni né HuaHan né altre fonti riconoscono il codice, compare «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» Confronta ogni carattere (`HHW`, due lettere, dieci cifre, `YQ`) con il messaggio del venditore.

**Fermo su `Shipment information received`.** Il negozio ha prenotato la spedizione, ma HuaHan non ha ancora scansionato il pacco in magazzino. Chiedi al venditore quando lo ha consegnato al corriere.

**Fermo su `Import Customs clearance started`.** La dogana del tuo paese sta controllando il pacco. Se lo stato non cambia per molti giorni senza comunicazioni dal corriere locale, contatta il venditore.

**Nessun aggiornamento dopo `Has been given to local carrier`.** Il corriere locale ha preso in carico il pacco e il suo tracciamento diretto è spesso più aggiornato di quello di HuaHan. Controlla nome e codice locale sul sito di HuaHan o sulla scheda del pacco in Peek.

**Nessuna novità da giorni.** Quando una spedizione in viaggio non riceve nuove scansioni da quattro giorni, Peek avvisa con il giorno dell’ultima scansione («Nessun aggiornamento da lun 12 ott») ricordando che un silenzio è frequente durante i voli intercontinentali, in dogana o nel fine settimana; dopo tre settimane senza novità consiglia invece di contattare il venditore. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Risulta consegnato, ma non c’è.** Controlla cassetta postale, portineria, vicini e punti di ritiro vicini, poi senti il corriere locale e il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o rispedito al mittente.** Il contratto di trasporto con HuaHan fa capo al venditore: rivolgiti al negozio o al marketplace per ottenere un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Numero di telefono e assistenza clienti HuaHan

Nella pagina ufficiale dei contatti ([hh-exp.com/lxwm](https://www.hh-exp.com/lxwm)), HuaHan indica per la **Cina**:

- **Numero verde nazionale (`官方热线`):** `400-015-6080` (numerazione cinese 400, senza orari di servizio pubblicati; il cellulare `13794450408` è riservato alle richieste commerciali dei mittenti `业务咨询`).
- **Sede centrale:** 10° piano, edificio C, Bairuida Building, 4001 Xuegang Avenue, Bantian, distretto di Longgang, Shenzhen (`深圳市龙岗区坂田雪岗大道4001号百瑞达大厦C座10楼`), con filiali a Yiwu, Zhengzhou e Hangzhou.

HuaHan non pubblica un indirizzo e-mail di assistenza per i destinatari privati. Per l’ordine o un rimborso contatta il venditore; per il recapito finale rivolgiti al corriere locale.

## Tracciare un pacco HuaHan con Peek

[Peek](/) riconosce subito HuaHan Logistics da un codice `HHW…YQ` («Corriere rilevato») e interroga il tracker di HuaHan con il solo codice completo, senza account, CAP o telefono. Se il sito di HuaHan non è raggiungibile, Peek interroga i servizi di tracciamento.

Peek mostra lo stato, ogni scansione con la relativa località e il paese di destinazione registrato. Poiché HuaHan stampa gli orari senza fuso orario, Peek mantiene l’ora locale stampata sulle scansioni proprie di HuaHan prima dell’affidamento e colloca le scansioni successive a `Has been given to local carrier` nel fuso del paese di destinazione quando questo ne ha uno solo, convertendo gli orari dotati di fuso in quello del tuo dispositivo («Orari nel tuo fuso orario: …» su peektracker.com e nelle app per iPhone e Android). HuaHan non fornisce data di consegna stimata né peso, che quindi non compaiono in Peek.

Quando HuaHan indica un corriere locale noto a Peek con un numero valido, la scheda del pacco lo segnala («Consegna con DHL», o «Annunciato a DHL, non ancora ricevuto» finché consta il solo preavviso elettronico) e interroga anche quel corriere. Se HuaHan fornisce un codice postale internazionale (due lettere, nove cifre, due lettere) senza un corriere noto, Peek interroga le poste del paese di destinazione. Non appena il corriere locale presenta una scansione almeno recente quanto l’ultima di HuaHan, Peek prosegue il tracciamento presso di lui conservando la cronologia HuaHan.

Finché tieni aperto il pacco, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e nel giorno di uscita in consegna dalle 6:00 locali fino a ogni 2 minuti se il sito di HuaHan risponde direttamente, oppure ogni 5 minuti quando il controllo apre un browser; tramite i soli servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno di consegna mentre il pacco è aperto o le notifiche sono attive. Negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora e adotta la cadenza del corriere locale non appena quest’ultimo subentra. Puoi attivare gli avvisi per «Ogni scansione», «Passaggi importanti» (include `Import Customs clearance started`, le scansioni doganali postali come `送交出口海关` e `送交境外进口海关`, l’uscita in consegna, il punto di ritiro e il recapito, ma non lo sdoganamento export di routine `快件从转运仓发出至机场，出口报关中`) o «Solo la consegna». Peek non può contattare HuaHan né il venditore al posto tuo.

## Domande frequenti sul tracciamento HuaHan

### Che cos’è HuaHan Logistics (华翰物流)?

È una società cinese di logistica per l’e-commerce internazionale (`深圳市华翰鸿运国际物流技术有限公司`), fondata nel 2008 a Shenzhen e attiva anche come HuaHan Express (`华翰速递`). Spedisce pacchi dalla Cina verso oltre 150 paesi affidandoli a corrieri locali per la consegna finale.

### Dove posso tracciare un pacco HuaHan?

Nel campo di ricerca in cima a questa pagina oppure sul tracker pubblico di HuaHan su `hh-exp.cn/WebTrack/CargoTrack.html` (fino a 20 codici separati da virgola, previa verifica: cliccare nell’ordine indicato o risolvere una semplice somma). Inserisci sempre l’intero codice `HHW…YQ` da 17 caratteri.

### Cosa significa «Has been given to local carrier» su HuaHan?

Lo sdoganamento d’importazione è concluso e HuaHan ha consegnato il pacco al corriere che effettua il recapito nel tuo paese. Le righe successive della cronologia provengono da quel corriere locale.

### Chi consegna i pacchi HuaHan nel mio paese?

Dipende dal canale scelto dal mittente: nel codice di Peek compaiono DHL, DPD, CTT Express, Sameday, USPS, China Post (con recapito tramite la posta del paese di destinazione) e Landmark Global con consegna sulla rete bpost. Controlla la scheda del pacco in Peek o il tracker di HuaHan per vedere l’operatore associato alla tua spedizione.

### Chi devo contattare se il mio pacco HuaHan non arriva?

Il venditore o il marketplace presso cui hai acquistato, poiché è il committente della spedizione con HuaHan. Se il pacco è già passato al corriere locale nel tuo paese, contatta quest’ultimo con il suo numero di tracciamento locale per gestire la consegna.

:::sources
- [HuaHan Express: 运单追踪 (tracciamento pubblico spedizioni, in cinese)](https://www.hh-exp.cn/WebTrack/CargoTrack.html) – ricerca pubblica fino a 20 codici separati da virgola, colonne di stato e dettaglio delle scansioni
- [HuaHan Logistics: 华翰物流订单管理系统 (portale clienti, in cinese)](https://www.hh-exp.cn/) – portale di accesso per i venditori e collegamento al tracciamento pubblico
- [HuaHan Express: Sito ufficiale (in cinese)](https://www.hh-exp.com/) – fondazione nel 2008, linee e-commerce e merci speciali verso oltre 150 paesi e regioni, tempistica orientativa di 6–12 giorni lavorativi e numero 400-015-6080
- [HuaHan Express: 关于华翰 (profilo aziendale, in cinese)](https://www.hh-exp.com/gyhh) – storia dal 2008, ragione sociale completa, linee speciali e postali e filiali
- [HuaHan Express: 联系我们 (contatti, in cinese)](https://www.hh-exp.com/lxwm) – sede di Shenzhen, filiali di Yiwu, Zhengzhou e Hangzhou, hotline ufficiale 400-015-6080 e contatto commerciale
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per categoria di articoli fino a 150 € dal 1° luglio 2026 e commissione di gestione UE di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – commissione di 2 € a prescindere dal valore e definizione di categoria di articoli
- [Commissione europea: Territorial Scope (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA dell’UE; Campione d’Italia e acque italiane del lago di Lugano nel territorio doganale dell’UE e fuori dal territorio IVA dell’UE
- [Poste Italiane: Ricevere spedizioni dall’estero](https://www.poste.it/ricevere-spedizioni-da-estero) – pagamento in Italia degli importi dovuti alla consegna (fino a 999 €), all’Ufficio Postale al ritiro oppure online prima della consegna
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – avviso in Italia sul fatto che Poste Italiane e le Forze dell’Ordine non richiedono dati personali, credenziali o codici delle carte tramite e-mail, SMS, lettere, telefonate o social network
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – franchigia di riscossione in Svizzera fino a 5 CHF di dazio o IVA e spese di sdoganamento separate dei trasportatori
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `HHW…YQ`, verifica aritmetica, codici e diciture di HuaHan, China Post, Landmark Global/bpost, DPD, Sameday, DHL, CTT Express e USPS, orologi e passaggio al corriere locale
:::
