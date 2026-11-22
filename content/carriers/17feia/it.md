---
title: Tracking 17FEIA: codici FEI…YQ, stati e consegna
description: Traccia un pacco 17FEIA (一起飞) dalla Cina: formato del codice FEI…YQ, significato degli stati, corriere finale, dogana e cosa mostra Peek.
slug: tracking-17feia
published: 2026-11-21
updated: 2026-11-21
---

17FEIA (一起飞, nota anche come Shanghai Hongshan International Logistics, 上海宏杉国际物流) è un'azienda logistica cinese. Riceve i pacchi nel proprio magazzino di Shenzhen o Shanghai, li spedisce fuori dalla Cina e, dopo lo sdoganamento all'importazione, li affida a un corriere per la consegna. I suoi codici iniziano con `FEI` e terminano con `YQ`. Incolla il tuo nel campo qui sopra oppure usa il tracciamento di 17FEIA su www.17feia.com. Il codice viene fornito da chi ha spedito l'ordine: il venditore o lo spedizioniere che ha inoltrato la merce dalla Cina.

## Codice di tracking 17FEIA: com'è fatto

Peek riconosce i codici 17FEIA dalla struttura `FEI`, due lettere, dieci cifre e `YQ`: 17 caratteri in tutto. Per le partenze da Shenzhen, le due lettere dopo `FEI` sono `SZ`. Tutti gli esempi sono inventati:

| Codice (inventato) | Cos'è |
| --- | --- |
| `FEISZ4829105736YQ` | `FEI`, due lettere, dieci cifre e `YQ`: un codice 17FEIA. Peek riconosce 17FEIA dal solo numero. |
| `FEISZ482910573YQ` | Solo nove cifre: non corrisponde al formato 17FEIA. Controlla se manca una cifra. |
| `FEISZ4829105736AB` | Due lettere diverse da `YQ` alla fine. Peek non assegna 17FEIA subito, ma chiede al portale 17FEIA se riconosce il codice. |
| `HHWKG4829105736YQ` | Finisce in `YQ`, ma inizia con `HHW`: un codice [HuaHan Logistics](carrier:huahan). |

In Peek minuscole, spazi e trattini non fanno differenza.

**Dove trovarlo.** Nell'e-mail di spedizione, nella pagina dell'ordine o nell'account dello spedizioniere se ne hai usato uno. Altrimenti leggi [come trovare il numero di tracciamento](guide:find-tracking-number). In seguito il record di 17FEIA può fornire a Peek anche il codice del corriere finale.

## Stati del tracking 17FEIA spiegati

Il record di 17FEIA abbina alla maggior parte delle righe una breve etichetta in cinese e una frase di testo, dalla più recente alla più vecchia. Peek mostra il testo inglese e ricava la fase dall'etichetta cinese. Ecco le righe proprie di 17FEIA nell'ordine consueto:

| Cosa dice 17FEIA | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Item information received.` | 17FEIA ha i dati del pacco, non ancora il pacco. | «Etichetta creata» |
| `The package is on the way to the facility.` (`包裹在去设施路上`) | In viaggio verso il magazzino 17FEIA, che non lo ha ancora ricevuto. | «Etichetta creata» |
| `Warehouse receipt.` (`仓库收货`) | Il magazzino 17FEIA di Shenzhen o Shanghai ha ricevuto il pacco. | «Preso in carico» |
| `The order has information pending customer confirmation.` (`订单待客户确认`) | Il magazzino attende che il cliente di 17FEIA che ha prenotato la spedizione confermi l'ordine. | «Preso in carico» |
| `Order Confirmed.` (`订单已确认`) | Ordine confermato. | «Preso in carico» |
| `Order dispatched from warehouse.` (`订单已出库`) | Il pacco ha lasciato il magazzino. | «In transito» |
| `Export declaration in progress.` (`出口报关中`) | Dichiarazione di esportazione in corso in Cina, passaggio abituale prima del volo. Non è un fermo e non riguarda la dogana del tuo Paese. | «In transito» |
| `Flight has departed.` (`航班已起飞`) | Il volo ha lasciato la Cina. | «In transito» |
| `Flight Arrived at Transit Hub.`, `Flight Departed from Transit Hub.` | Scalo lungo la rotta, poi partenza del volo successivo. | «In transito» |
| `Flight Arrived at Clearance Port.` (`航班抵达清关港`) | Atterrato dove avviene lo sdoganamento all'importazione. | «In transito» |
| `Broker picked up Shipment and delivered to warehouse.` (`清关行提货到仓`) | Uno spedizioniere doganale ha portato la spedizione nel proprio magazzino per lo sdoganamento (o dopo lo svincolo). Non è una consegna a casa tua. | «In dogana» (oppure «In transito» se segue `清关完成`) |
| `Customs Clearance is in progress.` (`清关中`) | La dogana d'importazione sta controllando la spedizione. | «In dogana» |
| `Customs inspection in Progress, Release Pending.` (`海关查验，暂未放行`) | Dopo l'arrivo al porto di sdoganamento, la dogana sta ispezionando il pacco e non lo ha ancora rilasciato. | «In dogana» |
| `Customs Clearance Completed.` (`清关完成`) | La dogana d'importazione ha sdoganato il pacco. | «In transito» |
| `Arrived at delivery hub，Awaiting carrie scan.` (`已交仓，等待上网`) | Consegnato al deposito del corriere finale. Segue la prima scansione di quel corriere («carrie» è la grafia del portale). | «In transito» |
| `包裹已做国内退回处理` (`国内退回`) | Reso interno in Cina: dopo la conferma in magazzino il pacco torna al mittente in Cina invece di lasciare il Paese. | «Restituito al mittente» |

**Due righe che non cambiano stato.** `Estimated departure date:` seguito da una data (`预计起飞日期`) è una stima inserita prima del volo, non una scansione: Peek la esclude. `Flight delayed.` (`航班延误`) segnala un ritardo del volo: Peek mantiene la fase della riga precedente.

**La barra di avanzamento.** La pagina di tracciamento di 17FEIA mostra anche una barra da `仓库收货` (ricevuto in magazzino) passando per `飞机起飞` (decollo) e `飞机落地` (atterraggio) fino a `成功签收` (firmato/consegnato). Peek non copia la barra, ma mostra una riga con etichetta `成功签收` come «Consegnato».

**Le righe del corriere finale.** Dopo il passaggio di mano, il portale 17FEIA può inoltrare le scansioni del corriere locale con etichetta `运输途中` (in transito), nelle sue parole e con il suo luogo: ad esempio DHL (`The international shipment has been processed in the export parcel center`), UPS negli Stati Uniti (`Arrived at Facility`, `Departed from Facility`, `On the Way, Delivery will be delayed by one business day.`) o un partner nel Regno Unito (`Shipment received in depot`). Peek le classifica in base al testo: un ingresso in deposito resta «In transito», mentre messa in consegna e consegna fanno avanzare lo stato.

**Perché gli orari sembrano fuori ordine.** I passaggi di magazzino in Cina (incluso un reso interno) seguono l'ora di Pechino (UTC+8). Le scansioni inoltrate dal corriere locale usano l'ora locale quando il luogo determina un solo fuso orario. Le righe di volo e dogana non indicano un luogo e il fuso fornito da 17FEIA non sempre corrisponde a quello reale: segui l'ordine delle righe.

## Chi consegna i pacchi 17FEIA

Dopo lo sdoganamento all'importazione, 17FEIA affida il pacco a un corriere che lo porta fino a te. Peek non ha potuto leggere il sito web di 17FEIA, quindi questa pagina non elenca tutti i partner né le aree coperte.

**Cosa mostra il codice di Peek.** Peek riconosce tre nomi che 17FEIA assegna ai partner: `DPD-DE` per [DPD Germany](carrier:dpd-de), `DE-DHL` per [DHL](carrier:dhl) in Germania e `FEDEX-GROUND` per [FedEx](carrier:fedex). Riconosce cinque destinazioni sul record di 17FEIA: Germania, Francia, Paesi Bassi, Polonia e Stati Uniti. Una cronologia nel codice mostra DHL in Germania prendere in carico un pacco diretto in Francia per consegnarlo all'operatore locale.

**Consegna mancata.** Tentativi e ritiro seguono le regole del corriere finale: rivolgiti a lui con il suo numero. Vedi [consegna mancata](guide:missed-delivery).

**Dogana nell'UE.** Gli acquisti online da fuori UE prevedono dal 1° luglio 2026 un dazio doganale temporaneo di 3 € per articolo sui pacchi fino a 150 € e, dal 1° novembre 2026, una tariffa di gestione dell'Unione di 2 € per articolo qualunque sia il valore. Per articolo si intendono le merci con la stessa classificazione tariffaria, descrizione e, se richiesta, origine. Peek non ha potuto verificare come vengano riscossi su un pacco 17FEIA: chiedi al venditore o al tuo spedizioniere se erano inclusi nel prezzo. Fuori dall'UE gli importi dipendono dal tuo Paese. Mentre la dogana d'importazione ispeziona il pacco senza rilasciarlo, 17FEIA mostra `Customs inspection in Progress, Release Pending.` Vedi [pacchi fermi in dogana](guide:customs) e, prima di pagare dopo un SMS o un'e-mail, [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco 17FEIA fermo, smarrito o restituito

17FEIA lavora per chi ha prenotato la spedizione: il venditore o lo spedizioniere che hai usato. In caso di ritardo, smarrimento, danneggiamento o reso, contatta prima loro.

**Codice non trovato.** Per un numero sconosciuto, il portale di 17FEIA non mostra avanzamenti. Peek indica «In attesa di 17FEIA» e continua a controllare. Se dopo due giorni 17FEIA non riconosce ancora il numero e nessun'altra fonte ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e controlla poi una volta al giorno, e una volta alla settimana trascorsa una settimana dall'aggiunta. Confronta il codice con il messaggio di spedizione: `FEI`, due lettere, dieci cifre, `YQ`.

**Fermo su `The order has information pending customer confirmation.`** Se hai usato uno spedizioniere, controlla l'account o i messaggi lì. Altrimenti chiedi al venditore.

**Silenzio dopo il volo.** Dopo `Flight has departed.`, possono passare giorni senza aggiornamenti. Dopo quattro giorni senza una nuova riga, Peek lo segnala con il giorno di quella riga («Nessun aggiornamento da …»). Vedi [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Consegnato, ma non è arrivato nulla.** Controlla attorno a casa e chiedi ai vicini, poi al corriere finale e al venditore. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** Chiedi al venditore o allo spedizioniere un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti 17FEIA

Il sito web di 17FEIA ha risposto a Peek con un controllo antibot, quindi questa pagina non elenca contatti per 17FEIA. Per il tuo ordine chiedi al venditore o allo spedizioniere che ha prenotato il trasporto: sono loro i clienti di 17FEIA. Per la consegna finale rivolgiti al corriere locale.

## Tracciare un pacco 17FEIA con Peek

[Peek](/) riconosce subito 17FEIA da un codice `FEI…YQ` («Corriere rilevato»): nessun altro corriere seguito da Peek usa questa forma. Funziona anche un link alla pagina di tracciamento di 17FEIA con il numero dopo `orderNumber=`. Non servono CAP né account.

Per qualsiasi altro codice, come quello del corriere finale, incollalo lo stesso: Peek traccia oltre 4.500 corrieri. Quando il formato corrisponde a più corrieri senza certezza, Peek mostra «Verifica dei servizi di tracciamento…» mentre interroga le sue fonti; se non corrisponde ad alcuno, lo cerca appena lo aggiungi. Per impostare tu il corriere, tocca «Cambia» e cerca `17FEIA`, `Feia` o `一起飞`, oppure usa «Cambia corriere» su un pacco già salvato. Peek interroga 17FEIA solo per codici composti da `FEI`, due lettere, dieci cifre e due lettere: per un altro formato segnala che 17FEIA «non emette numeri come questo».

Peek mostra lo stato, le righe nell'inglese di 17FEIA con il luogo se indicato, e il Paese di destinazione. Legge i passaggi di magazzino sull'ora di Pechino e le scansioni inoltrate con un Paese a fuso unico (o uno Stato USA a fuso unico) sull'ora locale del luogo, convertendoli nel fuso del tuo dispositivo. Le righe di volo e di dogana non indicano un luogo: Peek usa l'ora delle righe vicine, quella di Pechino finché una riga non segnala l'arrivo del volo, poi quella del corriere che consegna se 17FEIA ne inoltra le scansioni, altrimenti quella del tuo Paese. Questi orari possono essere sfasati di qualche ora. Peek omette la barra di avanzamento, l'origine, i CAP e il peso. 17FEIA non fornisce data prevista di consegna, nome del servizio, dati di mittente o destinatario né prova di consegna.

Quando 17FEIA indica DPD Germany, DHL o FedEx con un codice del formato di quel corriere, la scheda del pacco lo mostra (ad esempio «Consegna con DPD Germany») e riporta quel numero sotto quello di 17FEIA. Finché quel corriere ha solo l'annuncio senza una scansione propria, la scheda indica «Annunciato a …, non ancora ricevuto». Appena quel corriere registra una scansione almeno recente quanto l'ultima di 17FEIA, Peek segue il pacco lì mantenendo la cronologia di 17FEIA. Se il portale di 17FEIA non è raggiungibile, Peek interroga i servizi di tracciamento e mostra la loro cronologia nelle loro parole.

Finché tieni il pacco aperto sullo schermo o nel giorno della consegna con le notifiche attive, e mentre risponde il tracciamento di 17FEIA, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 nell'orario del corriere il giorno in cui entra in consegna; altrimenti ogni ora. Tramite i servizi di tracciamento controlla ogni ora, e fino a ogni 15 minuti il giorno della consegna finché lo tieni aperto o hai le notifiche attive. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna»; «Passaggi importanti» include la dogana al porto di sdoganamento, non la dichiarazione di esportazione in Cina. Peek non può contattare 17FEIA, il venditore o il tuo spedizioniere al posto tuo.

## Domande sul tracking 17FEIA

### Che cos'è 17FEIA (一起飞)?

Un'azienda logistica cinese, nota anche come Shanghai Hongshan International Logistics (上海宏杉国际物流). Spedisce pacchi fuori dalla Cina dal proprio magazzino di Shenzhen o Shanghai e, dopo lo sdoganamento all'importazione, li affida a un corriere per la consegna. I suoi codici iniziano con `FEI` e terminano con `YQ`.

### Dove posso tracciare un codice FEI…YQ?

Nel campo qui sopra oppure sul tracciamento di 17FEIA su www.17feia.com. Inserisci il numero intero: `FEI`, due lettere, dieci cifre e `YQ`.

### Cosa significa «Awaiting carrie scan» su 17FEIA?

La riga completa è `Arrived at delivery hub，Awaiting carrie scan.`: lo sdoganamento all'importazione è concluso e 17FEIA ha consegnato il pacco al deposito del corriere finale, che non lo ha ancora scansionato. Il tracciamento di quel corriere può presto precedere quello di 17FEIA.

### Chi consegna i pacchi 17FEIA?

Il corriere a cui 17FEIA lo affida dopo lo sdoganamento all'importazione. Il record di 17FEIA può indicarne nome e codice: se si tratta di DPD Germany, DHL in Germania o FedEx, la scheda Peek lo mostra. Altrimenti chiedi al venditore.

:::sources
- [Universal Parcel Scraper: 17FEIA](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/17feia) – nomi, formato `FEI…YQ` e partenze da Shenzhen (`SZ`), portale e link `orderNumber=`, etichette cinesi, testi inglesi e fasi (incluse ispezione doganale, `国内退回`, cambio fase di `清关行提货到仓` dopo `清关完成` e righe inoltrate DHL/UPS/deposito UK), barra di avanzamento, gestione fusi orari, partner (`DPD-DE`, `DE-DHL`, `FEDEX-GROUND`) e destinazioni
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri formati terminanti in `YQ` (`HuaHan Logistics`), servizi di tracciamento di riserva e passaggio al corriere locale
- [Commissione europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio doganale temporaneo di 3 € per articolo fino a 150 € dal 1° luglio 2026, tariffa di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tariffa di 2 € per articolo qualunque sia il valore, definizione di articolo
:::
