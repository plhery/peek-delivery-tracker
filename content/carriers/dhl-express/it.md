---
title: DHL Express tracking: lettera di vettura, stati e dogana
description: Tracking DHL Express: dove trovi la lettera di vettura di 10 cifre, cosa vogliono dire gli stati, dazi, consegna mancata e cosa fare se il pacco si perde.
slug: dhl-express-tracking
published: 2026-10-13
updated: 2026-10-27
---

Incolla una lettera di vettura DHL Express, o un link di tracking di MyDHL+, nel box qui sopra per seguire il pacco. Il tracking ufficiale di DHL è sul suo sito (MyDHL+) e nell'app di DHL Express.

## Lettera di vettura DHL Express: dove trovi il numero di tracking

Il numero di tracking di DHL Express è la lettera di vettura (in inglese «waybill number»). Cercalo nell'e-mail di spedizione del negozio o nella pagina del tuo ordine; anche un link di tracking di MyDHL+ lo contiene, dopo `id=`. Altri posti dove cercarlo: [dove si trova il codice tracking](guide:find-tracking-number).

| Forma (cifre inventate) | Che cos'è |
| --- | --- |
| `1234567891` | La lettera di vettura: 10 cifre, il numero con cui segui tutta la spedizione. L'ultima è una cifra di controllo. |
| `JD014600001234567890` | L'identificativo del collo: `JD01` più 16 cifre, stampato come `(J)JD…` sotto il codice a barre inferiore dell'etichetta. Ogni pacco della spedizione ha il suo, che riporta alla lettera di vettura. |

Anche Mondial Relay, Relais Colis, TIPSA, Estafeta, Packeta, DHL eCommerce Iberia e [DHL Paket](carrier:dhl) usano numeri di 10 cifre, quindi guarda quale corriere nomina l'e-mail del negozio.

DHL riusa i numeri di lettera di vettura: con un numero possono comparire scansioni di una spedizione precedente, che Peek ignora.

## Stati del tracking DHL Express: cosa significano

Queste sono le scansioni che Peek legge da DHL Express, nell'inglese di DHL, più o meno nell'ordine in cui un pacco le incontra, con lo stato che Peek mostra per ciascuna. Molte finiscono con il centro DHL: per esempio `Processed at` seguito da città e Paese.

| Scansione DHL Express | Peek mostra | Cosa vuol dire |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Etichetta creata | Il mittente ha mandato a DHL i dati della spedizione. |
| `Shipment picked up`, `Shipment accepted` | Preso in carico | DHL Express ha il pacco. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | In transito | Viaggia nella rete di DHL. |
| `Scheduled to depart on the next planned movement` | In transito | Aspetta in un centro il prossimo camion o volo. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | In dogana | Sta passando la dogana (vedi sotto). |
| `On hold awaiting for payment of shipment related fees` | In dogana | Devi pagare dazi o spese: DHL tiene fermo il pacco finché non sono pagati. Non è un problema della spedizione. |
| `Payment is received and recorded for shipment related fees` | In dogana | Dazi o spese sono pagati; resta in dogana finché lo sdoganamento non è finito. |
| `Clearance processing complete at` | In transito | La dogana ha finito e il pacco riparte. |
| `Arrived at DHL Delivery Facility` | In transito | È arrivato al centro DHL che lo consegna. |
| `Shipment is out with courier for delivery` | In consegna | Un corriere te lo sta portando. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Tentativo di consegna | La consegna non è riuscita. Il «consignee» sei tu, il destinatario. |
| `Shipment is scheduled for delivery` | In transito | È prevista una consegna in un giro successivo, per esempio dopo un tentativo mancato. |
| `Awaiting collection by the consignee` | Pronto per il ritiro | Ti aspetta in un centro DHL perché tu lo ritiri. |
| `Delivered` | Consegnato | È stato consegnato. |
| `Shipment is on hold` | Problema segnalato | DHL ha fermato la spedizione per ora. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Problema segnalato | È finito nel centro sbagliato e prosegue verso quello giusto, in ritardo. |
| `Returned to shipper` | Restituito al mittente | Torna al mittente. |

## Consegna DHL Express mancata

Dopo un tentativo mancato il tracking mostra `Delivery attempted but no response at Consignee address` o `Delivery attempt could not be completed`. Se poi compare `Shipment is scheduled for delivery`, è previsto un nuovo tentativo; `Awaiting collection by the consignee` vuol dire che il pacco ti aspetta in un centro DHL perché tu lo ritiri. Per cambiare la consegna rivolgiti a DHL Express, sul suo sito o nella sua app: Peek non può cambiarla. Altri passi in [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

## Dazi e dogana sui pacchi DHL Express

Un pacco che arriva da fuori UE, o dall'estero se abiti in Svizzera, può richiedere dazi o tasse. Finché è in dogana, il tracking mostra `Customs clearance status updated…`, `Processed for clearance at` o `Clearance event`, poi `Payment is received and recorded for shipment related fees` quando le spese sono pagate e `Clearance processing complete at` quando la dogana lo rilascia.

- **Italia**: secondo Your Europe, in tutta l'UE agli ordini fino a 150 euro comprati online da paesi extra UE si applica un dazio di 3 euro per articolo, in aggiunta all'IVA e alle spese di consegna. A riscuoterlo è il venditore o il vettore.
- **Svizzera (Ticino)**: secondo l'UDSC, nel traffico postale e di corriere l'imposizione la fa il trasportatore, e solo lui può aiutarti a cercare un invio.

Altro in [pacco fermo in dogana](guide:customs).

## Pacco DHL Express fermo, in ritardo o smarrito

Se il tracking è fermo, o mostra `Shipment is on hold`, leggi [perché il tracking non si aggiorna](guide:tracking-not-updating).

Se hai comprato da un negozio, il tuo interlocutore è lui, anche se il pacco lo porta DHL Express. Secondo Your Europe, nell'UE il commerciante deve consegnare entro 30 giorni, salvo una data diversa concordata, ed è responsabile dei danni alla merce dalla spedizione alla consegna. Se il pacco non arriva, sollecita il venditore e concedigli un'ulteriore proroga ragionevole; se non consegna nemmeno entro quella, puoi recedere dal contratto ed essere rimborsato il prima possibile.

**Risulta consegnato ma il pacco non c'è?** La pagina di tracking di DHL può mostrare la firma, che Peek non conserva. Controlla sul sito di DHL, poi contatta il venditore. Altri passi in [pacco consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Seguire un pacco DHL Express con Peek

[Peek](/) riconosce una lettera di vettura di 10 cifre che supera la cifra di controllo di DHL Express, ma non può esserne sicuro: mette DHL Express al primo posto, chiede il numero a DHL Express e ad altri corrieri con numeri di 10 cifre, come ACS Courier, Geniki Taxydromiki e DHL eCommerce Iberia, poi indica chi lo conosce. ACS e Geniki usano la stessa cifra di controllo: se anche uno dei due conosce il numero, scegli tu. Un numero di 10 cifre che non supera il controllo non viene proposto a DHL Express: sceglilo con «Cambia corriere». Gli identificativi `JD01` e le etichette `JJD0146` (`JJD014612345678901234`) vengono riconosciuti subito come DHL Express e ricondotti alla loro lettera di vettura tramite la pagina di dhl.com aperta in un browser. Funzionano anche i link di mydhl.express.dhl. Da un link di dhl.com incolla invece la sola lettera di vettura; se il pacco finisce sotto «DHL», passalo a «DHL Express» con «Cambia corriere».

Peek legge DHL Express tramite il servizio dell'app mobile di DHL Express, altrimenti apre la pagina di tracking di dhl.com in un browser sul proprio server: è più lento e DHL può rifiutarlo, e allora rispondono i servizi di tracciamento. Controlla ogni 10 minuti di giorno, ogni 2-5 minuti quando il pacco è in consegna e di notte ogni una o due ore, e le notifiche arrivano per «Ogni scansione», solo per i «Passaggi importanti» o «Solo la consegna».

Mostra lo stato, ogni scansione con il suo centro DHL su una mappa e il Paese di destinazione. L'ora di ogni scansione viene collocata in base alla città del centro o, se non basta, in base alle scansioni vicine. Le scansioni restano nell'inglese di DHL, tranne `Delivered`, `Shipment picked up` e `Shipment information received`, che compaiono con le parole di Peek. Non mostra la firma, ignora le vecchie scansioni di una lettera di vettura riciclata e non indica la data di consegna prevista, il peso né il Service Point. Non può pagare i dazi né cambiare la consegna.

## Domande sul tracking DHL Express

### La lettera di vettura DHL è il numero di tracking?

Sì. Per DHL Express la lettera di vettura di 10 cifre segue tutta la spedizione, mentre l'identificativo `JD01` riguarda un singolo collo e riporta alla lettera di vettura.

### DHL e DHL Express sono la stessa cosa?

Non in Peek: DHL Express è un corriere a sé, separato da [DHL Paket](carrier:dhl) e dai corrieri DHL eCommerce, ognuno con i suoi numeri e il suo tracking. Un pacco senza lettera di vettura di 10 cifre né identificativo `JD01` di solito è di uno di loro.

### Perché la lettera di vettura DHL risulta non valida?

Spesso è il numero di un altro corriere di DHL: le lettere di vettura DHL Express hanno esattamente 10 cifre e l'ultima è una cifra di controllo, quindi un numero con un errore di battitura di solito non supera il controllo. Una lettera di vettura appena creata può mostrare solo `Shipment information received` finché DHL non ha preso in carico il pacco.

### Si può tracciare DHL Express senza lettera di vettura?

Non in Peek: servono la lettera di vettura di 10 cifre o un identificativo `JD01` dell'etichetta. Chiedi la lettera di vettura al negozio o al mittente.

### Un SMS di DHL che chiede di pagare è una truffa?

Può esserlo. La Polizia Postale ha segnalato una campagna di smishing contro chi compra online e sceglie note aziende di spedizione: dopo gli SMS veri del corriere, nella stessa chat arriva un messaggio falso che dice che il pacco è al centro di spedizione e invita a cliccare un link. Non dare dati personali o bancari tramite link ricevuti via SMS o e-mail: cerca il numero tu stesso sul sito di DHL o in Peek, e se ti chiama un presunto corriere verifica contattando direttamente la società di spedizione. Altro in [truffa pacco via SMS](guide:delivery-scam-texts).

:::sources
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – 30 giorni, proroga, recesso e rimborso, responsabilità del commerciante, dazio di 3 euro per articolo riscosso dal venditore o dal vettore
- [UDSC: Spese di imposizione e tracciabilità dei colli](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – imposizione fatta dal trasportatore, ricerca dell'invio (Svizzera)
- [Polizia Postale: Nuova campagna di smishing «Il tuo pacco è stato consegnato al centro di spedizione»](https://www.commissariatodips.it/notizie/articolo/nuova-campagna-di-smishing-il-tuo-pacco-e-stato-consegnato-al-centro-di-spedizione/index.html) – SMS falso nella stessa chat del corriere, verificare con la società di spedizione
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – forme della lettera di vettura e dell'identificativo del collo, cifra di controllo, altri numeri di 10 cifre, lettere di vettura riusate, scansioni di DHL Express e stato di Peek per ciascuna, firma sul sito di DHL, cosa legge Peek da DHL Express
:::
