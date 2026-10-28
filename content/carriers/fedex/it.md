---
title: FedEx tracking: numero, stati e consegna del pacco
description: Tracking FedEx: com'è fatto il numero di 12 cifre, cosa vogliono dire «Delivery exception» e le altre scansioni, i dazi e i tuoi diritti se il pacco si perde.
slug: fedex-tracking
published: 2026-10-13
updated: 2026-10-28
---

Per seguire il pacco incolla il numero di tracking FedEx, o un link di tracking FedEx, nel box qui sopra. Il tracking di FedEx stesso è su fedex.com e nell'app FedEx Mobile.

## Numero di tracking FedEx: dove si trova e quante cifre ha

Cercalo nell'e-mail di spedizione o nella pagina dell'ordine del negozio. Anche un link di tracking di fedex.com contiene il numero, dopo `trknbr=` o `tracknumbers=`. Ancora niente? Altri consigli in [dove si trova il codice tracking e come fare senza](guide:find-tracking-number).

| Numero (inventato) | Che cos'è |
| --- | --- |
| `123456789012` | 12 cifre: un numero di tracking FedEx. L'ultima cifra è una cifra di controllo calcolata sulle 11 precedenti. |
| `123456789012343` | 15 cifre: una forma che Peek collega anche a FedEx. |
| `61290000000000000014` | 20 cifre che iniziano con `6129` o `7489`: FedEx Ground Economy, negli Stati Uniti. |

Attenzione ai numeri simili: 12 cifre le usano anche [BRT](carrier:brt), DPD France, Mondial Relay e diversi corrieri asiatici, 15 cifre anche BRT e DPD France. Se fedex.com non trova il numero, controlla quale corriere nomina l'e-mail del negozio.

## Stati del tracking FedEx: cosa significa «Delivery exception»

Peek legge le scansioni di FedEx in inglese, come le scrive FedEx, e sopra mostra lo stato in italiano. Ecco le principali, più o meno nell'ordine in cui un pacco le incontra:

| FedEx scrive | Peek mostra | Cosa vuol dire per te |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Registrato | Il mittente ha inviato a FedEx i dati della spedizione. |
| `Picked up`, `Tendered at FedEx location` | Preso in carico | FedEx ha il pacco: l'ha ritirato un corriere, oppure il mittente l'ha consegnato in una sede FedEx. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | In transito | Viaggia nella rete di FedEx. |
| `Clearance in progress`, `Clearance delay` | In dogana | È in sdoganamento (vedi sotto). |
| `International shipment release - Import` | In transito | La dogana l'ha rilasciato nel paese di destinazione. |
| `On FedEx vehicle for delivery` | In consegna | È sul mezzo per la consegna. |
| `Delivery exception`, `Customer not available or business closed` | Tentativo di consegna | La consegna non è andata a buon fine. |
| `Ready for pickup` | Pronto per il ritiro | Ti aspetta in un punto di ritiro. |
| `Delivered` | Consegnato | È stato consegnato. |
| `Shipment exception` | Problema segnalato | FedEx segnala un problema con la spedizione. |
| `Returning to shipper` | Restituito al mittente | Torna al mittente. |

Quando FedEx segna un pacco come in ritardo, Peek mostra «Problema segnalato», tranne se l'ultima scansione è `Package received after FedEx cutoff`: FedEx ha ricevuto il pacco dopo l'orario limite del giorno, e il pacco parte il giorno lavorativo successivo.

## Consegna FedEx mancata e punto di ritiro

Se la consegna non riesce, una scansione può dire perché: `Customer not available or business closed` se non c'era nessuno, `Local delivery restriction - Delivery not attempted` se una restrizione locale ha impedito il tentativo.

Se chiedi a FedEx di tenere il pacco in un punto di ritiro, la scansione può dire `Delivery option requested` mentre il pacco è in viaggio, e `Ready for pickup` quando è arrivato. Peek mostra allora nome e indirizzo del punto, se FedEx li fornisce: se la richiesta arriva dopo la spedizione, la pagina di FedEx mostra solo la città a chi non ha effettuato l'accesso. La pagina di FedEx può chiamare l'ultimo tratto `Heading to pickup location`, che Peek conta come «In consegna». Queste modifiche le fai con FedEx, non in Peek. Altro in [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

## Dazi e dogana sui pacchi FedEx

Un pacco che arriva da fuori UE, o dall'estero se abiti in Svizzera, può richiedere dazi o tasse. Finché è in dogana, il tracking mostra `Clearance in progress` o `Clearance delay`, poi `International shipment release - Import` quando la dogana lo rilascia.

- **Italia**: secondo Your Europe, in tutta l'UE agli ordini fino a 150 euro comprati online da paesi extra UE si applica un dazio di 3 euro per articolo, in aggiunta all'IVA e alle spese di consegna. A riscuoterlo è il venditore o il vettore.
- **Svizzera (Ticino)**: secondo l'UDSC, nel traffico postale e di corriere l'imposizione la fa il trasportatore, e solo lui può aiutarti a cercare un invio. Per questo lavoro riscuote spese di imposizione, e anche per qualsiasi domanda devi rivolgerti a lui.

Altro in [pacco fermo in dogana](guide:customs).

## Pacco FedEx in ritardo, smarrito o consegnato ma non ricevuto

Se il tracking è fermo, leggi [tracking non si aggiorna](guide:tracking-not-updating).

Se hai comprato da un negozio, il tuo interlocutore è lui, anche se il pacco lo porta FedEx. Secondo Your Europe, nell'UE il commerciante deve consegnare entro 30 giorni, salvo una data diversa concordata, ed è responsabile dei danni alla merce dalla spedizione alla consegna. Se il pacco non arriva, sollecita il venditore e concedigli un'ulteriore proroga ragionevole; se non consegna nemmeno entro quella, puoi recedere dal contratto ed essere rimborsato il prima possibile.

**Risulta consegnato ma il pacco non c'è?** La scansione di consegna di FedEx riporta il nome di chi ha firmato, che Peek non conserva. Controlla su fedex.com, poi contatta il venditore. Altri passi in [pacco consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Seguire un pacco FedEx con Peek

[Peek](/) riconosce i numeri FedEx di 12 e 15 cifre quando la cifra di controllo è giusta. Le stesse forme le usano altri corrieri, quindi spesso il pacco viene attribuito da un servizio di tracking o dopo una breve attesa. Un numero che non supera il controllo non viene proposto a FedEx: scegli tu il corriere con «Cambia corriere». I numeri Ground Economy di 20 cifre vengono riconosciuti subito, i codici a barre dell'etichetta vengono letti per il numero che contengono, e funzionano anche i link di fedex.com con `trknbr=` o `tracknumbers=`. I numeri FedEx di 13 cifre delle spedizioni nazionali in Polonia li segue come FedEx Poland.

Il server di Peek apre la pagina di monitoraggio di fedex.com in un browser. Quando FedEx rifiuta la richiesta, o chiede di verificare il destinatario, subentrano servizi di tracking.

Vedi lo stato, ogni scansione con luogo e ora esatta su una mappa, il giorno di consegna previsto, l'ora di consegna e, finché il pacco aspetta, nome e indirizzo della sede FedEx, se FedEx li fornisce. Controlla ogni 10 minuti di giorno, ogni 5 minuti quando il pacco è in consegna e di notte ogni una o due ore, e le notifiche le scegli tu: ogni scansione, solo i passaggi importanti o «Solo la consegna». Non mostra il tuo nome e indirizzo, il mittente, la firma, il peso e le dimensioni. Le scansioni sono in inglese come le scrive FedEx, tranne poche, tra cui quella di consegna: FedEx ci mette il nome di chi ha firmato, quindi compare «Consegnato». Modifiche della consegna, dazi e reclami passano solo da FedEx o dal mittente.

Negli Stati Uniti FedEx consegna da sé la maggior parte dei pacchi Ground Economy; quando dice che il servizio postale americano ne ha uno, il pacco viene seguito su USPS («Consegna con USPS»).

## Domande sul tracking FedEx

### Cosa significa delivery exception su FedEx?

Peek classifica questa scansione di FedEx come un tentativo di consegna fallito e mostra «Tentativo di consegna». Il motivo può comparire in una scansione come `Customer not available or business closed` (nessuno in casa o attività chiusa).

### Perché il tracking FedEx dice solo «Shipment information sent to FedEx»?

Questa prima scansione vuol dire solo che il mittente ha inviato a FedEx i dati della spedizione. Il tracking va avanti quando FedEx ha il pacco: `Picked up` o `Tendered at FedEx location`.

### Un SMS di FedEx è una truffa?

Può esserlo. La Polizia Postale ha segnalato una campagna di smishing contro chi compra online e sceglie note aziende di spedizione: dopo gli SMS veri del corriere, nella stessa chat arriva un messaggio falso che dice che il pacco è al centro di spedizione e invita a cliccare un link. Non dare dati personali o bancari tramite link ricevuti via SMS o e-mail: cerca il numero tu stesso su fedex.com o in Peek, e se ti chiama un presunto corriere verifica contattando direttamente la società di spedizione. Altro in [truffa pacco via SMS](guide:delivery-scam-texts).

:::sources
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – 30 giorni, proroga, recesso e rimborso, responsabilità del commerciante, dazio di 3 euro per articolo riscosso dal venditore o dal vettore
- [UDSC: Spese di imposizione e tracciabilità dei colli](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – imposizione fatta dal trasportatore, ricerca dell'invio, spese di imposizione (Svizzera)
- [Polizia Postale: Nuova campagna di smishing «Il tuo pacco è stato consegnato al centro di spedizione»](https://www.commissariatodips.it/notizie/articolo/nuova-campagna-di-smishing-il-tuo-pacco-e-stato-consegnato-al-centro-di-spedizione/index.html) – SMS falso nella stessa chat del corriere, verificare con la società di spedizione
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – forme del numero, cifre di controllo, scansioni di FedEx, cosa legge Peek da FedEx
:::
