---
title: Tracciamento USPS: numero, stati e pacchi dagli Stati Uniti
description: Traccia un pacco USPS dagli Stati Uniti: dove trovare il numero, cosa significano gli stati in inglese, dogana, pacchi smarriti e contatti USPS.
slug: tracciamento-usps
published: 2026-11-17
updated: 2026-11-17
---

USPS, lo United States Postal Service, è il servizio postale pubblico degli Stati Uniti: raggiunge ogni indirizzo del paese e include gratuitamente il tracciamento (USPS Tracking) nella maggior parte dei servizi nazionali per i pacchi e in molti prodotti internazionali, anche se non in tutti. Puoi inserire il codice in USPS Tracking su usps.com oppure incollarlo nel riquadro qui sopra; negli Stati Uniti si può inviare anche via SMS al `28777`. Il numero te lo fornisce il mittente, di solito nell’e-mail di spedizione del negozio.

## Numero di tracciamento USPS: dove si trova e com’è fatto

Sull’etichetta è la serie di caratteri stampata sotto il codice a barre. Come destinatario devi chiederlo al mittente: né gli uffici postali (Post Offices) né il servizio clienti USPS possono risalirvi per te. Chi utilizza Informed Delivery negli Stati Uniti vede i codici dei pacchi in arrivo nella propria bacheca. Altri consigli in [come trovare il numero di tracciamento](guide:find-tracking-number).

I codici USPS sono lunghi da 13 a 34 caratteri. I formati più comuni, con cifre inventate:

| Numero (esempio inventato) | Che cos’è |
| --- | --- |
| `9400 1000 4829 1057 3649 24` | 22 cifre che iniziano per `94`, `95`, `93`, `92` o `91`: la normale etichetta per i pacchi negli Stati Uniti. |
| `9205 5900 4829 1057 3649 2817 33` | 26 cifre che iniziano per `92`. |
| `420 10001 9400 1000 4829 1057 3649 24` | L’intero codice a barre letto dallo scanner: `420`, il CAP statunitense (ZIP Code) e poi il numero della spedizione. La parte iniziale con lo ZIP si può tralasciare. |
| `CP482910576US` | Un codice postale internazionale: 2 lettere, 9 cifre e `US`. Per i reclami internazionali USPS cita codici che iniziano da `EA` a `EZ`, da `CA` a `CZ`, da `HC` a `HZ` o da `RA` a `RZ`. |

Anche il numero sotto il codice a barre dell’avviso di mancata consegna PS Form 3849 lasciato dal portalettere negli Stati Uniti funziona in USPS Tracking.

Alcuni formati sono condivisi con altri corrieri: i codici di 22 cifre che iniziano con `9261` o `9361` possono appartenere anche a DHL eCommerce, Austrian Post o Estafeta, tra gli altri, mentre i numeri di 20 cifre sono usati anche da operatori come NZ Post. Se USPS non trova nulla, controlla quale corriere è indicato nell’e-mail del negozio. Vedi [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati della spedizione USPS: cosa significano

USPS pubblica il tracciamento in inglese. Quasi tutte queste diciture sono spiegate nell’assistenza di USPS; seguono il percorso abituale di un pacco e gli eventuali imprevisti:

| USPS scrive | Cosa significa per te | In Peek |
| --- | --- | --- |
| `Merchant Order Receipt Notification` | Avviso elettronico del negozio che ha ricevuto il tuo ordine; non c’è ancora alcuna scansione fisica di USPS. | «Etichetta creata» |
| `Shipping Label Created, USPS Awaiting Item` | Il mittente ha generato l’etichetta, ma USPS non ha ancora il pacco. Per chiarimenti chiedi al negozio. | «Etichetta creata» |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Un partner logistico ha preso il pacco e lo consegnerà a USPS. La data prevista comparirà quando USPS lo riceverà. | «Etichetta creata» |
| `USPS in Possession of Item` | USPS ha accettato o ritirato il pacco. Non devi fare nulla. | «Preso in carico» |
| `Arrived at USPS Facility` | È in lavorazione in un centro di smistamento o in viaggio verso l’ufficio postale di recapito. La riga può ripetersi nello stesso centro o in giorni diversi. | «In transito» |
| `Arrived at Post Office` | È arrivato all’ufficio postale incaricato della consegna: secondo USPS dovrebbe arrivare oggi o il giorno lavorativo postale successivo. | «In transito» |
| `Out for Delivery` | Un portalettere lo recapita oggi, non sempre quello abituale e talvolta in un orario diverso dalla posta ordinaria. | «In consegna» |
| `Delivered` | Consegnato, con data, ora e località registrate. | «Consegnato» |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Entro le 21:00 ora locale del giorno di `Out for Delivery` non è arrivata la scansione di consegna; USPS prevede il recapito nel giorno lavorativo successivo. | `Awaiting Delivery`: «In transito»; `Delivery Status Not Updated`: nessuna fase dedicata, Peek mantiene la precedente, di solito «In consegna» |
| `No Access to Delivery Location` | Il portalettere non è riuscito a raggiungere cassetta o portone. USPS ritenta automaticamente il giorno lavorativo successivo. | «Tentativo di consegna» |
| `Notice Left (No Authorized Recipient Available)` | Serviva una firma e non c’era nessuno. È stato lasciato l’avviso PS Form 3849; non è previsto un secondo tentativo automatico. | «Tentativo di consegna» |
| `Available for Pickup` | Non recapitabile a domicilio: puoi ritirarlo all’ufficio postale a partire dal giorno lavorativo successivo. | «Pronto per il ritiro» |
| `Alert` | Un ritardo dovuto ad esempio a maltempo, calamità naturali o altri eventi operativi. | «Problema segnalato» |

Con `Available for Pickup` secondo USPS non è possibile prenotare una nuova consegna: si ritira allo sportello con un documento d’identità con foto. `Notice Left (No Secure Location Available)` indica che il portalettere non ha trovato un punto sicuro dove lasciare il pacco e lo ha riportato all’ufficio postale, anche qui senza secondo tentativo automatico. `Insufficient Address` e `No Such Number` significano secondo USPS che il pacco torna al mittente, che dovrà rispedirlo pagando una nuova affrancatura; in Peek entrambi contano come «Tentativo di consegna». Nelle spedizioni verso l’estero, `Attempted Delivery Abroad` significa che le poste del paese di destinazione hanno tentato la consegna senza riuscirci («Tentativo di consegna» in Peek) e le scansioni successive mostrano il seguito. `Held at Delivery Depot/Delivery Office` indica che l’operatore postale locale trattiene l’invio nel proprio ufficio per il ritiro da parte tua, dicitura che appare pochi minuti dopo l’arrivo in sede («Pronto per il ritiro» in Peek). `Payment of charges - Item being held, addressee being notified` segnala infine che la posta di destinazione trattiene il pacco in attesa del pagamento degli oneri doganali e ne avvisa il destinatario («In dogana» in Peek).

USPS indica l’orario di ogni scansione nell’ora locale del luogo in cui è avvenuta.

## Pacco dagli Stati Uniti: consegna in Italia e in Svizzera

**Chi consegna.** Per un codice postale internazionale che termina in `US` con cifra di controllo valida, Peek interroga con lo stesso numero anche le poste del tuo paese: [Poste Italiane](carrier:poste-italiane) in Italia e la [Posta Svizzera](carrier:swiss-post) in Svizzera (in base alla regione dell’iPhone o al paese di connessione nel browser). Non appena la posta nazionale mostra una scansione almeno recente quanto l’ultima di USPS, Peek prosegue il tracciamento lì conservando la cronologia di USPS. Con codici di sole cifre, o a San Marino e in Vaticano, Peek non interroga altre poste.

**Dogana.** Ogni pacco dagli USA passa dalla dogana: vedi [pacco fermo in dogana](guide:customs). In Italia, come in tutta l’UE, sugli acquisti online da fuori UE fino a 150 € si applica dal 1° luglio 2026 un dazio fisso di 3 € per categoria di articolo, più dal 1° novembre 2026 una commissione UE (*Union handling fee*) di 2 € per categoria, qualunque sia il valore. In Svizzera (fuori dall’UE), secondo l’UDSC e la Posta Svizzera, si pagano l’IVA sul valore della merce (spedizione e servizi inclusi) e dazi sul peso lordo, con franchigia fino a 100 CHF per i regali tra privati esclusi alcol e tabacco.

**Mancata consegna negli USA.** Dopo un [tentativo a vuoto](guide:missed-delivery) negli USA resta l’avviso color pesca PS Form 3849. La riconsegna (*Redelivery*) è gratuita ma non ovunque disponibile: una sola richiesta, all’indirizzo originale e senza orario a scelta (online entro le 2:00 CST lunedì–sabato anche per il giorno stesso, via QR code, telefono o modulo firmato in cassetta). Altrimenti si ritira allo sportello con documento con foto e avviso, o tramite delegato con il retro firmato a suo nome. Sulla maggior parte dei pacchi tracciati arriva un secondo avviso dopo 5 giorni e il reso dopo 15 (5 giorni dal primo tentativo per Priority Mail Express; 30 giorni per quasi tutti i pacchi dall’estero; 5 giorni in USPS Smart Locker più 10 allo sportello).

## Pacco USPS in ritardo, smarrito o restituito

**Dagli USA verso di te.** Solo il mittente negli Stati Uniti può aprire online un’indagine internazionale con USPS; per un acquisto online rivolgiti al negozio. Lo smarrimento va segnalato tra 3 e 90 giorni dalla spedizione per Priority Mail Express International e tra 7 giorni e 6 mesi per Priority Mail International e Registered Mail (nessuna ricerca online per First-Class Package International Service non raccomandato). Vedi [pacco smarrito](guide:lost-parcel) e [tracciamento fermo](guide:tracking-not-updating).

**Da te verso gli USA.** L’indennizzo spetta al mittente estero salvo rinuncia scritta a favore del destinatario americano: apri il reclamo presso le tue poste di partenza, mentre un pacco danneggiato va esibito subito in un ufficio USPS.

**Negli Stati Uniti.** Tempi indicativi: 2–3 giorni lavorativi per Priority Mail, 2–5 per Ground Advantage, 2–8 per Parcel Select; garantito è solo Priority Mail Express. Consegne lunedì–sabato, di norma entro le 17:00 locali. Se `Out for Delivery`, `Arrived at Post Office` o `Delivery Status Not Updated` resta fermo un intero giorno lavorativo postale — o se `Delivered` è fermo da oltre un giorno lavorativo senza pacco davanti alla porta, in cassetta, in garage, presso i conviventi o l’amministrazione ([consegnato ma non ricevuto](guide:delivered-not-received)) — invia una *service request* a USPS. Dopo 7 giorni dalla spedizione mittente o destinatario possono avviare su usps.com una ricerca Missing Mail.

**Rimborsi interni negli USA.** Solo per invii assicurati con ricevuta originale di spedizione: danni entro 60 giorni, smarrimento tra 15 e 60 giorni (7–60 per Priority Mail Express). Conserva scatola e contenuto. Vedi anche [pacco restituito al mittente](guide:returned-to-sender).

## Servizio clienti USPS: telefono, contatti e SMS truffa

Al 17 novembre 2026 la pagina «Contact Us» di USPS indica:

- **1-800-ASK-USPS** (`1-800-275-8777`), lunedì–venerdì 8:00–20:30 e sabato 8:00–18:00 ora della costa Est (ET, di solito 6 ore dietro Roma e Zurigo: le 8:00 ET sono le 14:00); voce automatica sul tracciamento 24 ore su 24.
- **1-800-222-1811** per il tracciamento nazionale e internazionale, stessi orari (gli operatori vedono gli stessi dati di USPS Tracking).
- Il modulo «Email Customer Service» e una chat per problemi tecnici con usps.com.
- Negli USA, inoltro tramite `7-1-1` per persone sorde, ipoudenti o con difficoltà del linguaggio.

USPS non precisa se i numeri `1-800` funzionino dall’estero; per spedizioni dagli USA contatta prima il mittente. Secondo USPIS e USPS, negli USA USPS manda SMS o e-mail di tracciamento solo se li hai richiesti con un numero di spedizione, quei messaggi non contengono link e la riconsegna è gratuita. Queste regole valgono solo per USPS: in Europa i costi d’importazione li chiede la posta o il corriere che consegna, con i propri canali. Poste Italiane li fa pagare alla consegna (in contanti o con POS) oppure online prima della consegna con «Gestione delle spese di importazione», nell’App Poste Italiane o su poste.it; in Svizzera si pagano online nella Post-App. Negli USA inoltra gli SMS sospetti al `7726` o a `spam@uspis.gov`; vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

Pagine di USPS citate qui, lette il 17 novembre 2026: su about.usps.com «Who we are»; su faq.usps.com «Where is my package? Tracking Status Help», «USPS Tracking - The Basics», «How to find your tracking number», «PS Form 3849 Redelivery Notice», «Redelivery - The Basics», «What are the Second and Final Notice and Return Dates for Redelivery?», «Picking Up Mail that is Being Held at Your Post Office», «USPS Smart Locker», «Delayed mail and packages?» e «Scams & Scheme Alerts»; su www.usps.com «Text Tracking FAQs», «Receive Mail & Packages», «Informed Delivery», «Missing Mail and Lost Packages», «File a Claim», «File a USPS Claim: International» e «Contact Us».

## Seguire un pacco USPS con Peek

[Peek](/) riconosce subito USPS nella maggior parte delle etichette a 22 cifre con controllo valido, nella maggior parte dei numeri a 26 cifre in `92`, nei codici `420` scansionati (da cui toglie lo ZIP) e nei codici `US` che iniziano per `C` (tranne `CE` e `CV`), `E`, `L` o `R` con controllo valido. Sui formati condivisi (numeri in `9261` o `9361` e numeri di 20 cifre) mostra «Verifica dei servizi di tracciamento…», interroga prima gli altri candidati (DHL eCommerce, Austrian Post o NZ Post) e i servizi di tracciamento, e apre per ultima la pagina di USPS. Se USPS non compare, tocca «Cambia» sotto «Compatibili con questo numero» o usa dopo «Cambia corriere». Vale anche un link di tracciamento usps.com con il numero.

Peek apre il tracciamento di USPS in un browser sul proprio server; se USPS devia o rifiuta la richiesta, suppliscono i servizi di tracciamento, non sempre con l’intera cronologia. Mostra stato, tappe con luogo e orario letto nello stato USA dell’evento e convertito sul tuo dispositivo («Orari nel tuo fuso orario: …»), più il giorno previsto e l’eventuale fascia di 2 ore, omettendo firmatario e istruzioni. Le scansioni restano in inglese tranne `Delivered`, mostrato come «Consegnato».

Peek controlla USPS fino a ogni 30 minuti mentre hai il pacco aperto sullo schermo o nel giorno di consegna con notifiche attive (anche in consegna), attende un’ora dopo un errore e controlla ogni ora negli altri casi — pure dopo due giorni senza scansioni o 12 ore su «Etichetta creata» o «Preso in carico» (ogni 3 ore dopo un giorno fermo tramite i soli servizi di tracciamento). Avvisi a scelta: «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

Quando [UPS](carrier:ups) o [FedEx](carrier:fedex) passano un pacco a USPS, Peek prosegue su USPS appena mostra progressi. Per un invio postale in `IT` o `CH` diretto negli USA, Peek interroga anche USPS quando la posta d’origine indica gli USA come destinazione o quando il tuo paese per Peek sono gli USA. Riconsegna, Informed Delivery e reclami restano su usps.com.

## Domande frequenti sul tracciamento USPS

### Cosa significa «Tracking Not Available» su USPS?

USPS non trova dati per quel codice: non è ancora registrato, è errato oppure è scaduto. Controlla ogni cifra; USPS conserva la cronologia di USPS Tracking per 120 giorni e quella delle spedizioni internazionali numerate fino a 2 anni.

### Tutti i pacchi spediti dagli Stati Uniti sono tracciabili?

No. Secondo USPS il tracciamento è incluso in molti ma non in tutti i servizi internazionali e può avere limitazioni a seconda del paese di arrivo. Chiedi al venditore se la spedizione prevede un numero di tracciamento.

### USPS consegna anche la domenica?

Solo i pacchi Priority Mail Express e le spedizioni Amazon, scrive USPS. Tutto il resto viene consegnato dal lunedì al sabato.

### USPS mostra un orario esatto di consegna?

No. USPS può indicare una finestra stimata di 2 ore (*Expected Delivery Window*), non garantita, e precisa di non sapere a che ora il portalettere raggiungerà un singolo indirizzo. Peek mostra il giorno previsto e, se USPS la fornisce, anche la finestra oraria.

:::sources
- [U.S. Postal Inspection Service: Smishing – Package Tracking Text Scams (in inglese)](https://www.uspis.gov/news/scam-article/smishing/) – SMS inviati solo su richiesta con numero di tracciamento, nessun link nei messaggi reali, segnalazioni a 7726 e spam@uspis.gov
- [Commissione europea: EU customs reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € fino a 150 € dal 1° luglio 2026 e commissione di gestione di 2 € dal 1° novembre 2026
- [Commissione europea: Customs Guidance on EUR 3 customs duty (in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione per categoria di articolo e commissione indipendente dal valore
- [UDSC: Spese di imposizione e tracciabilità dei colli](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – imposizione doganale curata dal trasportatore in Svizzera
- [Posta Svizzera: Sdoganamento all’importazione](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) – IVA, dazi sul peso lordo e franchigia di 100 CHF per i regali tra privati
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati dei codici, diciture e fasi in Peek, fusi orari statunitensi e passaggio alle poste nazionali
:::
