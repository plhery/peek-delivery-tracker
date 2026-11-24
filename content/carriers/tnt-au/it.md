---
title: Tracciamento TNT Australia: codici, stati e assistenza
description: Traccia spedizioni nazionali TNT Australia: codici da 3 lettere + 9 cifre, stati di deposito, riconsegna lunedì–venerdì e supporto 13 11 50.
slug: tracciamento-tnt-australia
published: 2026-11-24
updated: 2026-11-24
---

TNT Australia (*TNT Express Australia*, in integrazione con FedEx Express) trasporta pacchi, documenti e pallet in tutta l’Australia attraverso i propri depositi ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Le spedizioni internazionali hanno un tracciamento separato (vedi [TNT](carrier:tnt)); quelle nazionali australiane dispongono di una ricerca dedicata su [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Puoi controllare una spedizione nazionale australiana su [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) oppure incollare il codice di spedizione da 12 caratteri nel riquadro qui sopra.

## Formati dei codici di tracciamento TNT Australia

Peek traccia le spedizioni nazionali di TNT Australia tramite un codice di 12 caratteri:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `SYD482917360` | **3 lettere maiuscole + 9 cifre** (12 caratteri in totale) — codice standard di spedizione nazionale TNT Australia tracciato su `tntexpress.com.au`. |

Poiché 3 lettere seguite da 9 cifre corrispondono anche ai codici di [Purolator](carrier:purolator), Peek interroga i due corrieri uno dopo l’altro: prima Purolator in Canada, prima TNT Australia altrove. Mostra «TNT Australia ha questo pacco.» non appena TNT Australia risponde. Alcuni prefissi, come `RWM` di Kuehne+Nagel, sono esclusi dal formato di TNT Australia. Un link diretto `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` o `trackref.aspx?con=...` seleziona subito TNT Australia.

L’[Help Center di TNT Australia](https://www.tnt.com/express/en_au/site/support.html) chiama numero di spedizione (*shipment / consignment number*) il numero di **9 cifre** sul lato sinistro della fattura, circa 3 cm sotto il codice cliente. Il tracker di TNT Australia su Peek accetta però soltanto il formato 3 lettere + 9 cifre. Per altri formati, consulta la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento TNT Australia

La pagina di dettaglio nazionale di TNT Australia mostra quattro colonne — `Status`, `Date`, `Time` e `Depot` — insieme a una data `Estimated Delivery by:` in alto quando disponibile.

| Dicitura TNT Australia | Cosa significa |
| --- | --- |
| `Your shipment data is lodged` | Il mittente ha inviato elettronicamente i dati della spedizione; TNT Australia non ha ancora ritirato il pacco. |
| `We've collected your shipment` | TNT Australia ha ritirato la spedizione dal mittente, prima della prima scansione in deposito. |
| `We're processing your shipment` | Scansionato in un deposito TNT Australia di origine, transito o consegna. |
| `Your shipment's with a driver` | Affidato al corriere per la consegna odierna a domicilio o in azienda. |
| `New delivery address required` | Il giro di consegna non ha potuto recapitare il pacco all’indirizzo indicato; il collo è fermo al deposito locale in attesa di un indirizzo aggiornato o del ritiro in sede. |
| `We've delivered your shipment` | Consegnato all’indirizzo del destinatario. |
| `Your shipment was collected` | Il destinatario ha ritirato di persona il pacco presso il deposito TNT che lo custodiva (diverso da `We've collected your shipment`, che riguarda il ritiro dal mittente). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | Il destinatario ha chiesto una consegna su appuntamento e TNT l’ha confermata. Sono avvisi, non scansioni: mantengono lo stato del pacco e la conferma può comparire dopo la consegna. |

## Riconsegna, ritiro in deposito e ritardi

L’[Help Center di TNT Australia](https://www.tnt.com/express/en_au/site/support.html) indica cosa fare se perdi una consegna o devi riprogrammarla:

- **Se il corriere ha lasciato un avviso:** utilizza il servizio TNT Re-delivery Service per prenotare una nuova data di consegna.
- **Se non c’è alcun avviso (o la consegna non è ancora stata tentata):** contatta TNT Australia comunicando codice di spedizione, indirizzo completo e data desiderata per la riconsegna.
- **Riconsegne solo dal lunedì al venerdì:** TNT Australia specifica che gli ordini possono essere riconsegnati unicamente dal lunedì al venerdì.
- **`New delivery address required`:** il pacco rimane nel deposito indicato nella colonna `Depot` finché non fornisci un altro indirizzo o non lo ritiri; contatta TNT Australia. Vedi anche le guide su [consegna mancata](guide:missed-delivery) e [tracciamento fermo](guide:tracking-not-updating).

Se il tuo ordine in Australia viaggia con un altro corriere domestico, confronta il tracciamento con [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease) o [Border Express](carrier:border-express).

## Tracciare TNT Australia con Peek

Incolla in Peek il codice nazionale da 3 lettere + 9 cifre oppure un link `tntexpress.com.au`. Se Peek lo assegna a un altro corriere, usa «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella schermata del pacco.

Peek legge direttamente la pagina nazionale di TNT Australia tramite l’[adattatore open source TNT Australia di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), con un nuovo tentativo automatico in caso di timeout di rete. Rileva ogni dicitura di scansione, deposito e orario, oltre alla data `Estimated Delivery by:` fino alla consegna. TNT Australia indica gli orari dei depositi senza scarto da UTC: Peek data ciascuna scansione sul fuso del relativo deposito (ora di Sydney per Sydney, ora di Perth per Perth e così via), una scansione senza deposito sul fuso della scansione vicina, altrimenti sull’ora di Sydney, e mostra poi tutto con l’ora del tuo dispositivo. La tabella nazionale non espone peso, nome del servizio o codici stato.

Peek traduce le diciture di TNT Australia in fasi chiare: «Etichetta creata» (`Your shipment data is lodged`), «Preso in carico» (`We've collected your shipment`), «In transito» (`We're processing your shipment`), «In consegna» (`Your shipment's with a driver`), «Problema segnalato» (`New delivery address required`) e «Consegnato» (`We've delivered your shipment` o `Your shipment was collected`). Gli avvisi di prenotazione (`Book-In Delivery Requested` e `Book-In Delivery Arranged`) restano nella cronologia senza annullare una consegna completata né cambiare la fase corrente.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui TNT Australia mostra `Your shipment's with a driver`, ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracciamento TNT Australia risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando TNT Australia non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a TNT Australia.», insieme al link «Chiedi aiuto a TNT Australia» verso l’Help Center di TNT Australia. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a TNT Australia.»

Scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna» nelle impostazioni di notifica di Peek per decidere quando ricevere gli avvisi.

## Contattare TNT Australia

- **Telefono (dall’Australia):** `13 11 50` (pubblicato su tutto il sito TNT Australia)
- **Help Center e riconsegna:** [Supporto TNT Australia](https://www.tnt.com/express/en_au/site/support.html)
- **Portale di tracciamento nazionale:** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (traccia fino a 10 spedizioni nazionali, Sameday Local Jobs o 1 riferimento elettronico alla volta)
- **Sito TNT Australia e tracciamento internazionale:** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) e [panoramica Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Tieni pronti il codice di spedizione nazionale da 12 caratteri e l’indirizzo di consegna quando chiami `13 11 50` o programmi una riconsegna nei giorni feriali.

## Domande frequenti

### Che differenza c’è tra `We've collected your shipment` e `Your shipment was collected`?

`We've collected your shipment` appare all’inizio del tragitto, quando TNT Australia ritira il pacco dal mittente. `Your shipment was collected` appare alla fine, quando il destinatario ritira di persona un pacco fermo al deposito TNT, per cui Peek lo segna come «Consegnato».

### Perché `Book-In Delivery Arranged` è comparso dopo che il pacco era già stato consegnato?

`Book-In Delivery Arranged` è la conferma di una consegna prenotata da parte di TNT Australia: è un avviso, non una scansione, e può essere registrato anche dopo la consegna effettiva. Peek mantiene il pacco su «Consegnato» senza riportarlo indietro.

### TNT Australia consegna o riconsegna nel fine settimana?

L’[Help Center di TNT Australia](https://www.tnt.com/express/en_au/site/support.html) dichiara che le consegne riprogrammate possono essere riconsegnate solo tra **lunedì e venerdì**.

### Il mio numero TNT ha 9 cifre senza lettere: perché non viene tracciato come TNT Australia su Peek?

Il tracker TNT Australia di Peek utilizza codici da 3 lettere seguite da 9 cifre, il formato nazionale letto su `tntexpress.com.au`. L’Help Center TNT chiama numero di spedizione (*shipment / consignment number*) un codice di 9 cifre presente in fattura: provalo su [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) o chiama `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
