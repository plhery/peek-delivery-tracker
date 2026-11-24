---
title: Tracciamento DHL Freight: codici ActiveTracing e aiuto
description: Traccia una spedizione stradale o ferroviaria DHL Freight su ActiveTracing: codici JND, codici ordine, ID spedizione, SSCC, appuntamenti e aiuto.
slug: tracciamento-dhl-freight
published: 2026-11-24
updated: 2026-11-24
---

DHL Freight (*DHL Freight GmbH*, parte di DHL Group) è la divisione europea di trasporto merci su strada e ferrovia di DHL, con filiali in oltre 40 paesi e più di 45 milioni di tonnellate movimentate ogni anno tra reti LTL (groupage), PTL (carico parziale), FTL (carico completo), temperatura controllata, alta sicurezza e ferrovia europea. Oltre al trasporto B2B su pallet (come *EuroConnect*), DHL Freight consegna anche ordini residenziali ingombranti (*DHL Home Delivery*) e spedizioni nazionali svedesi *DHL Service Point*. Puoi cercare una spedizione su [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento DHL Freight: codici ordine, ID spedizione e targhe JND

DHL ActiveTracing accetta diversi tipi di riferimento e conserva lo storico delle spedizioni online per **sei mesi**:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `JND48291736405829173` | **DHL Package ID / License Plate** di 20 caratteri (`JND` + 17 cifre). |
| `FRA-EC-4829173` | **Codice ordine DHL (Order code)**: codice terminale di 3 lettere + codice prodotto di 2 lettere (come `EC` per EuroConnect) + 7 cifre (con o senza trattini). |
| `4829173640` | **ID spedizione (Consignment ID)**: 9, 10 o 11 cifre (quelli a 10 cifre sono frequenti per le spedizioni nazionali svedesi *DHL Service Point*). Condivide la lunghezza a 10 cifre con [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) e [Packeta](carrier:packeta). |
| `00348291736405829177` | Identificativo collo **GS1 SSCC** a 20 cifre che inizia per `00` (cifra di controllo GS1 valida). Condivide la struttura SSCC a 20 cifre con [USPS](carrier:usps) e [NZ Post](carrier:nz-post). |

Nota che le consegne pacchi standard in Germania usano [DHL](carrier:dhl) (*DHL Paket*) e i documenti o pacchi espressi aerei internazionali usano [DHL Express](carrier:dhl-express), mentre pallet, mobili pesanti e merci europee su gomma o rotaia si tracciano sul portale ActiveTracing di DHL Freight. Vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di DHL Freight ActiveTracing

ActiveTracing fornisce per ogni riga una frase di stato, in inglese o tedesco, il terminale (per esempio `DE-KOBLENZ`) e la data e l’ora del terminale, senza indicazione di fuso orario:

| DHL Freight indica (EN / DE) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Ordine di trasporto registrato o autista inviato per il ritiro; la merce è ancora presso il mittente. | «Etichetta creata» |
| `Collected`, `Shipment handover by sender` / `Abholung` | Ritirata presso il mittente o consegnata a un DHL Service Point svedese. | «Preso in carico» |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | In movimento tra terminali DHL Freight, in traversata in traghetto, sdoganata (`Customs cleared`) o in attesa presso il terminale di destinazione per concordare un appuntamento di consegna. | «In transito» |
| `On hold - in customs clearance` | Ferma per lo sdoganamento transfrontaliero (es. Svizzera, Regno Unito o Norvegia). | «In dogana» |
| `Out for Delivery` / `In Zustellung` | Caricata sul mezzo locale per la consegna odierna. | «In consegna» |
| `Shipment in warehouse - ready for self collection by consignee` | Pronta per il ritiro diretto da parte del destinatario al terminale DHL Freight locale. | «Pronto per il ritiro» |
| `Attempted DHL delivery - ...` | Tentativo di consegna non riuscito (per esempio destinatario chiuso o rifiuto). | «Tentativo di consegna» |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Blocco per pagamento, consegna parziale con colli mancanti, disservizio per sciopero o annullamento da parte del mittente. | «Problema segnalato» |
| `New shipping instruction - shipment returned` | Merce restituita al mittente su nuove istruzioni. | «Restituito al mittente» |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Consegnata al destinatario o ritirata al terminale / Service Point. | «Consegnato» |

## Appuntamenti di consegna (*Avisierung*), dogana e regole del trasporto merci

Secondo [DHL Freight Germania](https://www.dhl.com/de-en/home/freight.html) e il [Centro assistenza DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html):

- **Appuntamenti di consegna (*Avisierung*):** una spedizione può restare al terminale di consegna su `Shipment in warehouse - Awaiting delivery appointment` finché non viene concordata una data con il destinatario; una volta fissata segue `Delivery appointment booked`. Se resta in attesa, chiedi al mittente o al terminale di consegna.
- **Conservazione di sei mesi su ActiveTracing:** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) mostra lo storico delle spedizioni degli **ultimi sei mesi** in inglese, tedesco, spagnolo, francese, svedese, polacco e italiano.
- **Dogana europea transfrontaliera:** il trasporto su strada fra UE e paesi vicini extra-UE (come Regno Unito, Svizzera o Norvegia) richiede lo sdoganamento (`On hold - in customs clearance`). Per i pacchi e-commerce extra-UE in ingresso nell’UE si applicano il dazio doganale di 3 € sulle spedizioni fino a 150 € e il contributo di gestione UE di 2 € nell’ambito della [riforma doganale dell’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en); vedi [pacchi fermi in dogana](guide:customs).

## Spedizione DHL Freight in ritardo, danneggiata o consegnata parzialmente

Il trasporto con DHL Freight è stato prenotato dal mittente, quindi contatta prima il venditore o lo spedizioniere.

- **Danni visibili o colli mancanti (`partly delivered`):** controlla merce e numero di colli all’arrivo e avvisa subito il mittente. Il Centro assistenza DHL Freight include la guida *How to Prepare a Claim*.
- **In attesa di appuntamento o pagamento:** se ActiveTracing indica `Awaiting delivery appointment` o `waiting for consignee payment`, contatta subito il terminale di destinazione indicato nell’ultima scansione (oppure il mittente).
- Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating), [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti DHL Freight

Canali di supporto pubblicati nel [Centro assistenza DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) e su [DHL Freight Germania](https://www.dhl.com/de-en/home/freight.html):

- **Centro assistenza per trasporto stradale e ferroviario europeo:** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQ, *How to Prepare a Claim*, Incoterms 2020, Termini e Condizioni e norme sulle merci pericolose.
- **Viva e ricerca terminali:** il Centro assistenza offre **Viva**, l’assistente clienti AI di DHL Freight, e *Freight Terminals and Locations* per trovare terminali e uffici per paese.
- **Portale aziende:** *myDHLFreight*, con video tutorial e FAQ nel Centro assistenza.

> Ignora link via SMS o e-mail non richiesti che domandano un pagamento online imprevisto per «sbloccare» una spedizione DHL Freight. Controlla sempre lo stato direttamente su [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) o presso il terminale DHL Freight di destinazione. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare una spedizione DHL Freight con Peek

[Peek](/) interroga DHL Freight per una targa `JND` (`JND48291736405829173`, inventato), l’unico corriere a cui la sottopone, e mostra «DHL Freight ha questo pacco.» non appena ActiveTracing risponde. Per un ID spedizione a 10 cifre (`4829173640`), Peek interroga DHL Freight tra i corrieri a dieci cifre se tracci da Germania, Svezia, Austria, Polonia, Paesi Bassi, Belgio, Francia, Italia, Spagna o Regno Unito, e solo come ultima risorsa altrove. Peek riconosce anche un link incollato di `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` o `valueShipmentOrderField=`). Per un codice ordine (`FRA-EC-4829173`), un ID spedizione di 9 o 11 cifre o un SSCC (`00348291736405829177`), tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco e scegli DHL Freight (cercare «DHL ActiveTracing», «DHL Frachtverfolgung» o «DHL Freight GmbH» lo trova ugualmente).

Peek legge direttamente la pagina inglese di ActiveTracing e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), le frasi di stato di ActiveTracing con il terminale (come `KOBLENZ, DE`), il peso lordo e il paese di destinazione. Peek data ogni riga sul fuso orario del rispettivo terminale e la mostra sull’orologio del tuo dispositivo. Il nome del servizio resta fuori da tutte le app di Peek. La città del destinatario, l’unica parte dell’indirizzo mostrata da ActiveTracing, può chiudere il percorso sulla tua mappa mentre la spedizione è in viaggio; i link condivisi non la mostrano. Peek omette origine, numero di colli, volume, data di partenza, orario di consegna separato, altri riferimenti, prova di consegna e firma; ActiveTracing non fornisce data stimata di consegna, codici di stato né nomi di mittente o destinatario.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui ActiveTracing mostra `Out for Delivery`, ogni 2 minuti (ogni 5 dopo un controllo che ha aperto un browser) dalle 6:00 sull’orologio del luogo dell’ultima scansione, finché ActiveTracing risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando ActiveTracing non risponde e Peek segue la spedizione solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DHL Freight.», con il link «Chiedi aiuto a DHL Freight» verso il Centro assistenza DHL Freight. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a DHL Freight.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può prenotare un appuntamento di consegna, pagare costi di trasporto né contattare un terminale DHL Freight al posto tuo.

## Domande frequenti sul tracciamento DHL Freight

### Che differenza c’è tra DHL Freight, DHL Parcel e DHL Express?

DHL Freight si occupa di merci europee su strada e rotaia (pallet, LTL/PTL/FTL, *DHL Home Delivery* ingombranti e trasporto nazionale svedese) tracciate su [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), mentre i pacchi standard usano [DHL](carrier:dhl) e i corrieri espressi aerei internazionali a tempo definito usano [DHL Express](carrier:dhl-express).

### Quali codici di tracciamento funzionano su DHL ActiveTracing?

ActiveTracing accetta codici ordine DHL (`FRA-EC-4829173`, inventato), ID spedizione da 9 a 11 cifre (`4829173640`), codici collo / License Plate `JND` di 20 caratteri (`JND48291736405829173`) e codici GS1 SSCC a 20 cifre (`00348291736405829177`).

### Perché la mia spedizione è ferma su `Awaiting delivery appointment`?

Il terminale di consegna trattiene la merce finché non viene fissata una data di consegna con il destinatario. Chiedi al mittente oppure al terminale indicato nell’ultima riga del tracciamento.

### Per quanto tempo DHL ActiveTracing conserva lo storico?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) indica che i dati delle spedizioni restano consultabili online per gli **ultimi sei mesi**.

:::sources
- [DHL Freight: ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – ricerca per ID spedizione, codice ordine e Package ID / License Plate, conservazione 6 mesi, *DHL Freight GmbH*
- [DHL Freight: Home Germania (inglese)](https://www.dhl.com/de-en/home/freight.html) – oltre 40 paesi, oltre 45 M tonnellate/anno, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe e rete terminali
- [DHL Freight: Centro assistenza strada e rotaia Europa (inglese)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – assistente AI Viva, ricerca terminali, How to Prepare a Claim, Incoterms 2020 e myDHLFreight (URL di aiuto collegato da Peek)
- [Commissione europea: riforma doganale dell’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio doganale di 3 € fino a 150 € e contributo di gestione UE di 2 €
- [Universal Parcel Scraper: dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – parsing di `JND`, codice ordine, ID spedizione 9–11 cifre e SSCC, regole link `activetracing.dhl.com`, mappa stati inglese/tedesco, ora locale di ogni terminale, peso e paese di destinazione letti, e URL di aiuto
:::
