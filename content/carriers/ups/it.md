---
title: UPS tracking: numero, stati del pacco e consegna
description: Tracking UPS: com'è fatto il numero 1Z, cosa vogliono dire Exception e le altre scansioni, UPS Access Point, dazi e i tuoi diritti se il pacco si perde.
slug: ups-tracking
published: 2026-10-13
updated: 2026-10-27
---

I numeri di tracking UPS (United Parcel Service) di solito iniziano con `1Z`. Per seguire il pacco incolla il numero, o un link di tracciamento UPS, nel box qui sopra; il tracking di UPS vero e proprio è su ups.com.

## Numero di tracking UPS: dove si trova e com'è fatto

Il numero te lo dà il negozio che spedisce il pacco, di solito nell'e-mail di spedizione o nella pagina dell'ordine. Lo contiene anche un link di tracciamento di ups.com, dopo `tracknum=`. Non trovi niente? Leggi [dove si trova il codice tracking e come fare senza](guide:find-tracking-number).

| Numero (inventato) | Che cos'è |
| --- | --- |
| `1Z1234567812345674` | Il numero di collo UPS: `1Z` seguito da 16 lettere e cifre, 18 caratteri in tutto. L'ultima è una cifra di controllo, calcolata sui 15 caratteri che la precedono. |
| `H1234567890` | Una lettera (H, J, K o V) e 10 cifre. Può essere un numero UPS, ma la stessa forma la usano anche altri corrieri. |

## Stati del tracking UPS: cosa vuol dire «Exception»

La pagina di tracking di UPS in inglese mostra una barra di avanzamento (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) e, sotto, le scansioni. Peek le legge in inglese e, sopra, mostra lo stato in italiano. Ecco le principali, più o meno nell'ordine in cui un pacco le incontra:

| UPS scrive | Peek mostra | Cosa vuol dire per te |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Registrato | Il mittente ha creato l'etichetta. UPS non ha ancora il pacco. |
| `Drop-Off`, `Pickup Scan` | Preso in carico | UPS ha il pacco: il mittente l'ha lasciato in un UPS Access Point, oppure UPS è passato a ritirarlo. Conta qui anche il primo `Arrived at Facility`. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | In transito | Viaggia nella rete di UPS. `Export Scan` e `Import Scan` segnano il passaggio di una frontiera. |
| `Your package is prepared for clearance.` | In dogana | È in sdoganamento. |
| `Duties or taxes are due on this package.` | In dogana | Devi pagare dazi o tasse prima che UPS consegni (vedi sotto). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | In consegna | Ce l'ha un autista. |
| `The receiver was not available at the time of the final delivery attempt.` | Tentativo di consegna | Anche l'ultimo tentativo è andato a vuoto. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Pronto per il ritiro | Ti aspetta in un UPS Access Point. |
| `DELIVERED` | Consegnato | Consegnato, oppure ritirato da te in un Access Point. |
| `Return to Sender` | Restituito al mittente | Torna al mittente. |

`Exception` è lo stato generico di UPS e può voler dire anche solo un ritardo. Conta la scansione sotto: `We're sorry this package may experience a temporary delay.` è proprio un ritardo, mentre `The company or receiver name is incorrect. This will delay delivery.` vuol dire che la consegna aspetta i tuoi dati (Peek mostra «Problema segnalato»). Peek si basa sulla scansione più recente, non sullo stato generico.

## Consegna UPS mancata e UPS Access Point

Se il corriere non riesce a consegnare, la scansione dice che il destinatario non c'era o che l'attività era chiusa, e UPS riprova. Dopo l'ultimo tentativo, una scansione successiva dice come prosegue: UPS tiene il pacco, lo porta in un Access Point o lo rimanda al mittente. Approfondisci in [mancata consegna per destinatario assente](guide:missed-delivery).

UPS può anche portare il pacco in un UPS Access Point. Lì le scansioni diventano `Delivered to UPS Access Point™` e poi `UPS Access Point™ possession`, e Peek mostra nome e indirizzo del punto. Non aspettare troppo: quando un pacco non ritirato torna indietro, la scansione dice `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Se un Access Point chiude, UPS ritira i suoi pacchi e la scansione indica che la consegna in un altro Access Point è in sospeso. Vedi [pacco non ritirato](guide:uncollected-parcel).

## Dazi e dogana sui pacchi UPS

Un pacco che arriva da fuori UE, o dall'estero se abiti in Svizzera, può richiedere dazi o tasse prima della consegna. Il tracking mostra allora `Duties or taxes are due on this package.` e `Your package is being held until duties and taxes are paid. Awaiting customer action.`

- **Italia**: secondo Your Europe, in tutta l'UE agli ordini fino a 150 euro comprati online da paesi extra UE si applica un dazio di 3 euro per articolo merceologico (i prodotti con lo stesso codice merci e lo stesso paese di origine contano come uno), in aggiunta all'IVA e alle spese di consegna. A riscuoterlo è il venditore o il vettore.
- **Svizzera (Ticino)**: secondo l'UDSC, nel traffico postale e di corriere l'imposizione la fa il trasportatore (DHL, UPS, La Posta ecc.), e solo lui può aiutarti a cercare un invio. Per questo lavoro riscuote spese di imposizione, e anche per qualsiasi domanda devi rivolgerti a lui.

Altro in [pacco fermo in dogana](guide:customs).

## Pacco UPS in ritardo, smarrito o danneggiato

Se il tracking è fermo, leggi [tracking non si aggiorna](guide:tracking-not-updating).

Se hai comprato da un negozio, il tuo interlocutore è lui, anche se il pacco lo porta UPS. Secondo Your Europe, nell'UE il commerciante deve consegnare entro 30 giorni, salvo una data diversa concordata, ed è responsabile dei danni alla merce dalla spedizione alla consegna. Se il pacco non arriva, sollecita il venditore e concedigli un'ulteriore proroga ragionevole; se non consegna nemmeno entro quella, puoi recedere dal contratto ed essere rimborsato il prima possibile.

In Italia anche il corriere espresso è un servizio postale: l'AGCOM lo ha scritto in una delibera del 2018 che cita UPS tra gli operatori postali. L'Autorità decide le controversie tra utenti e fornitori di servizi postali per i pacchi fino a 31,5 kg e prevede una procedura gratuita in tre passi: un reclamo scritto all'operatore, che deve rispondere entro 45 giorni; se la risposta non ti soddisfa o non arriva, la conciliazione, che si chiude entro 60 giorni e in cui può assisterti un'associazione dei consumatori; infine, entro 90 giorni dalla conciliazione, la richiesta all'AGCOM di definire la controversia. Al giudice ordinario puoi rivolgerti in qualsiasi momento.

**Risulta `DELIVERED` ma il pacco non c'è?** Controlla su ups.com dove UPS l'ha lasciato, poi contatta il venditore. Approfondisci in [pacco consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Seguire un pacco UPS con Peek

[Peek](/) riconosce subito UPS da un numero `1Z` la cui ultima cifra supera il controllo di UPS. Se non lo supera, propone solo UPS, che lo rifiuta come sbagliato. Da UPS cerca solo numeri `1Z`: se con «Cambia corriere» scegli UPS per un numero come `H1234567890`, rispondono solo servizi di tracking. Puoi incollare i link di ups.com con un numero e qualsiasi link che contenga un numero `1Z`.

Per leggere i dati, il suo server apre la pagina di tracciamento di UPS in un browser, ogni 10 minuti di giorno, ogni 5 minuti quando il pacco è in consegna e di notte ogni una o due ore. Se UPS rifiuta la richiesta, subentrano servizi di tracking; se riesce a leggere solo il testo della pagina, mostra lo stato e la cronologia arriva da quei servizi. Vedi lo stato, ogni scansione con luogo e ora esatta su una mappa, il giorno di consegna previsto (mai una fascia oraria), l'ora di consegna, il paese di destinazione e, finché il pacco aspetta, nome e indirizzo dell'Access Point. Le notifiche le scegli tu: ogni scansione, solo i passaggi importanti o «Solo la consegna». Non vedi chi ha firmato, il tuo indirizzo, la prova di consegna, il punto dove è stato lasciato né la foto. Peso e mittente UPS non li fornisce.

Qualunque sia la lingua di Peek, le scansioni compaiono come le scrive UPS in inglese: la consegna si legge «DELIVERED». Se negli Stati Uniti UPS affida un pacco Ground Saver al servizio postale americano e ne indica il numero, il pacco viene seguito su USPS appena USPS mostra progressi («Consegna con USPS»).

## Domande sul tracking UPS

### Perché il tracking UPS non funziona?

Controlla prima il numero: l'ultimo carattere di un numero `1Z` è una cifra di controllo, un solo carattere sbagliato di solito la fa saltare e UPS rifiuta il numero. Se vedi solo `Shipper created a label, UPS has not received the package yet.`, UPS non ha ancora il pacco: il tracking va avanti quando il mittente lo consegna o UPS lo ritira (`Drop-Off`, `Pickup Scan`). Oppure il numero è di un altro corriere: controlla quale nomina l'e-mail di spedizione.

### Dove trovo la prova di consegna UPS?

Sulla pagina di tracking di ups.com: UPS può indicare dove ha lasciato il pacco (per esempio `Left at: Front Door`) e offrire una prova di consegna. Peek non conserva né l'una né l'altra.

### Posso modificare la consegna UPS su Peek?

No: Peek legge soltanto il tracking di UPS. Le modifiche che fai con UPS compaiono nelle scansioni, per esempio `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Un SMS di UPS è una truffa?

Può esserlo. La Polizia Postale ha segnalato una campagna di smishing contro chi compra online e sceglie note aziende di spedizione: dopo gli SMS veri del corriere, nella stessa chat arriva un messaggio falso che dice che il pacco è al centro di spedizione e invita a cliccare un link per le istruzioni di ritiro. Può seguire una telefonata da un numero falsificato, di un finto addetto alla consegna. Non dare dati personali o bancari tramite link ricevuti via SMS o e-mail: cerca il numero tu stesso su ups.com o in Peek, e se ti chiama un presunto corriere verifica contattando direttamente la società di spedizione. I veri dazi compaiono nel tracking come scansioni doganali. Altro in [truffa pacco via SMS](guide:delivery-scam-texts).

:::sources
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – 30 giorni, proroga, recesso e rimborso, responsabilità del commerciante, dazio di 3 euro per articolo
- [AGCOM: FAQ Contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – reclamo, 45 giorni, conciliazione in 60 giorni, definizione entro 90 giorni, pacchi fino a 31,5 kg, procedura gratuita
- [AGCOM: Delibera n. 572/18/CONS](https://www.agcom.it/sites/default/files/migration/delibera/Delibera%20572-18-CONS.pdf) – il corriere espresso è un servizio postale, UPS citato tra gli operatori postali
- [UDSC: Spese di imposizione e tracciabilità dei colli](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – imposizione fatta dal trasportatore (DHL, UPS, La Posta), ricerca dell'invio, spese di imposizione (Svizzera)
- [Polizia Postale: Nuova campagna di smishing «Il tuo pacco è stato consegnato al centro di spedizione»](https://www.commissariatodips.it/notizie/articolo/nuova-campagna-di-smishing-il-tuo-pacco-e-stato-consegnato-al-centro-di-spedizione/index.html) – SMS falso nella stessa chat del corriere, telefonata con spoofing, verificare con la società di spedizione
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – forme del numero, cifra di controllo, scansioni di UPS, cosa legge Peek da UPS
:::
