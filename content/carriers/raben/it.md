---
title: Tracciamento Raben: numero, stati e consegna
description: Traccia un pallet o spedizione groupage Raben: formato del numero, significato degli stati su myRaben, scarico, reclami danni e contatti.
slug: tracciamento-raben
published: 2026-11-22
updated: 2026-11-22
---

Raben Group è un gruppo logistico europeo a conduzione familiare fondato nel 1931 nei Paesi Bassi e oggi con sede vicino a Poznań, in Polonia. La sua rete su strada trasporta pallet e groupage (carichi parziali) tramite filiali in 17 paesi europei e consegna anche per negozi online: un ordine ingombrante può quindi arrivare con un camion Raben. Traccialo con il numero di spedizione nel campo «Shipment tracking» su raben-group.com oppure incollalo qui sopra.

## Numero di spedizione Raben: com'è fatto e dove trovarlo

Raben assegna un numero proprio a ogni spedizione. Lo possiede l'azienda che ha prenotato il trasporto, di solito il negozio o il produttore: cercalo nell'e-mail di spedizione o nella pagina dell'ordine. Le società polacche di Raben spiegano di ricevere i dati del destinatario dal committente del trasporto, quindi chiedi al mittente se non hai alcun numero. Approfondisci in [come trovare il numero di tracciamento](guide:find-tracking-number).

| Numero (inventato) | Che cos'è |
| --- | --- |
| `274829173648291` | 27 seguito da altre 13 cifre: il formato di un numero di spedizione Raben nel catalogo di Peek. |
| `27 4829 1736 4829 1` | Lo stesso con spazi. Spazi e trattini non contano su Peek. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Un link diretto a una spedizione sul tracker di Raben, come lo apre la ricerca su raben-group.com. Incolla l'intero link in Peek. |

Anche [BRT](carrier:brt) in Italia e Yunda in Cina usano numeri di 15 cifre, perciò questo formato non è sempre Raben. La ricerca sul sito Raben trova una spedizione anche per riferimento o etichetta collo, ma Peek segue soltanto il numero di spedizione vero e proprio.

## Gli stati di tracciamento Raben spiegati

Il tracker di Raben, myRaben, mostra lo stato, ogni passaggio con luogo e ora, data di consegna prevista, peso, numero di colli e volume, barra di avanzamento, note e contatti della filiale Raben incaricata. I passaggi nell'ordine tipico:

| Raben indica | Cosa significa per te | Stato su Peek |
| --- | --- | --- |
| `registered in the system` | Il mittente ha prenotato il trasporto. Raben ha l'ordine, non ancora la merce. | «Etichetta creata» |
| `arrived to the pick-up place` | Il camion è arrivato dal mittente per il ritiro. | «Etichetta creata» |
| `collected` | Raben ha ritirato la spedizione dal mittente. | «Preso in carico» |
| `loaded` | Caricata su un camion, verso il deposito successivo o per il giro di consegna. | «In transito» |
| `unloaded` | Scaricata al deposito successivo. | «In transito» |
| `Inventory in a warehouse` | Registrata nel magazzino di un deposito. | «In transito» |
| `shipment in depo` | In deposito. | «In transito» |
| `in progress - advice required` | In viaggio. La consegna va prima preavvisata al destinatario. | «In transito» |
| `in progress - fix delivery` | In viaggio, per la consegna in un giorno fisso. | «In transito» |
| `in delivery` | In giro di consegna. | «In consegna» |
| `arrived to the delivery place` | Il camion ha raggiunto l'indirizzo di consegna. | «In consegna» |
| `delivered` | Consegnata. | «Consegnato» |
| `delivered D&D` | Consegnata e scaricata dall'autista. | «Consegnato» |
| `not delivered - self collection` | Non consegnata: segnata per ritiro in deposito, anche se può ancora seguire un giro di consegna. Chiedi alla filiale Raben sul tracker prima di recarti lì. | «Pronto per il ritiro» |
| `not delivered - consignee not available` | L'autista non ti ha trovato. | «Tentativo di consegna» |

**Altre mancate consegne.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (serve un camion con sponda idraulica) e `not delivered - wrong/missing docs` spiegano il motivo. Peek mostra «Tentativo di consegna».

**Fermi.** `not delivered - breakdown lorry` e `not delivered - strike` indicano ritardi su strada; `not delivered - at sender disposal` significa che Raben trattiene la merce in attesa di istruzioni del mittente; `cancelled` indica trasporto annullato. Peek mostra «Problema segnalato».

**Consegnata con riserva.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` e `delivered with delay` indicano che la consegna è avvenuta, specificando l'anomalia. Peek mostra «Consegnato».

**Cambi di programma.** `new delivery date agreed with consignee` e `shipment parameters updated` aggiornano data o dati: la merce non si è mossa.

**Dogana.** Le spedizioni soggette a dogana mostrano voci come `customs: clearance in progress` e `customs clearance completed`; `customs: lack of invoices / incomplete invoices` segnala fatture mancanti, quindi chiedi al negozio. Vedi [pacchi fermi in dogana](guide:customs) e [significato degli stati](guide:tracking-statuses).

## Come consegna Raben: pallet, scarico e tempi

Le FAQ sul trasporto pallet di Raben Polonia illustrano il funzionamento. I servizi extra vengono prenotati dal cliente di Raben, cioè l'azienda che ha ordinato il trasporto (spesso il negozio):

- **Scarico.** Lo scarico spetta al destinatario: Raben non ne risponde né è tenuta a fornire muletto o rampa. Il committente può prenotare lo scarico manuale a cura dell'autista (HUN) o lo scarico al punto di consegna (UNLO). Per un articolo pesante, chiedi al negozio cosa ha prenotato.
- **Tempi.** In Polonia un pallet richiede un giorno lavorativo come standard, o due a seconda del servizio acquistato; all'estero dipende dal paese di destinazione. Il cliente può anche prenotare un giorno fisso (FIX), consegna entro le 8:00, 10:00, 12:00 o 16:00 (ND 08–ND 16) oppure giorno e ora concordati (WXACT).
- **Se sei assente.** Quando una consegna fallisce per responsabilità del destinatario, Raben può addebitare il costo al committente.
- **Foto di consegna.** Con Picture Confirming Delivery (PCD), servizio gratuito scelto dal cliente, non serve essere presenti. L'autista lascia la merce nel punto indicato e fotografa etichetta, spedizione ed entrambe insieme; le foto valgono come documento di consegna.

## Spedizione Raben in ritardo, danneggiata o non consegnata

**Nessun movimento.** Se i passaggi si fermano per giorni, chiedi al negozio o alla filiale Raben indicata sul tracker. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**In ritardo.** Nell'UE il commerciante deve consegnare entro 30 giorni salvo diverso accordo, ricorda il portale UE Your Europe. Dopo quel termine puoi concedere tempo aggiuntivo e poi annullare con rimborso.

**Danneggiata o smarrita.** Il venditore risponde dei danni dalla spedizione fino alla consegna e Your Europe invita a contattare prima il venditore. Vedi [pacco danneggiato](guide:damaged-parcel) e [pacco smarrito](guide:lost-parcel). Le regole di Raben:

- **Polonia.** Raben Logistics Polska accetta reclami scritti dal mittente o dal destinatario: tramite myClaim, modulo online utilizzabile senza account myRaben, oppure per posta, fax o e-mail. Contesta i danni visibili alla consegna e segnala i danni occulti entro 7 giorni dalla consegna (nei trasporti internazionali domeniche e festivi non contano). Raben risponde entro 30 giorni dal ricevimento dei documenti completi. Una spedizione nazionale si considera smarrita dal 31° giorno dopo la data di consegna prevista.
- **Germania.** Raben Germania chiede di controllare imballaggio e integrità alla consegna e di far annotare in dettaglio eventuali danni visibili sul documento di consegna controfirmato dall'autista. Segnala il danno per iscritto alla filiale Raben competente; per danni occulti appena scoperti e al più tardi entro 7 giorni dalla consegna.

## Contatti e servizio clienti Raben

Recapiti indicati sui siti Raben:

- **Referente della spedizione.** Raben invita a contattare la persona dedicata mostrata sulla pagina di stato della spedizione, che Peek non riporta.
- **Orari.** Il servizio clienti di Raben Polonia lavora dal lunedì al venerdì dalle 8:00 alle 16:00 (ora polacca), punta a rispondere entro 3 ore e risponde in polacco di default.
- **Filiali.** La [pagina contatti](https://www.raben-group.com/contact) di Raben trova il deposito tramite codice postale ed elenca le filiali per paese; ogni sito nazionale, come polska.raben-group.com, ha i propri contatti.

## Tracciare una spedizione Raben con Peek

Il formato a 15 cifre è condiviso con [BRT](carrier:brt) e altri corrieri, quindi [Peek](/) non riconosce Raben dal solo numero. Incollalo comunque: Peek segue oltre 4.500 corrieri. Durante l'aggiunta, Peek interroga i corrieri compatibili («Verifica dei servizi di tracciamento…») e mostra «Raben ha questo pacco.» quando il tracker Raben riconosce il numero; se più corrieri lo trovano, scegli tu. Se nessuno lo trova ancora, compare «Nessuna cronologia di tracciamento per ora» mentre Peek continua a controllare, oppure «In attesa di Raben» una volta scelto Raben. Per impostarlo tu stesso, premi «Cambia» e seleziona «Raben» sotto «Compatibili con questo numero», oppure cercalo (anche «Raben Group» lo trova). Su una spedizione già aggiunta usa «Cambia corriere».

Un link di myraben.com o oftc.myraben.com assegna subito la spedizione a Raben. Se il tuo numero Raben ha un formato diverso, scegli Raben a mano: Peek interroga il tracker Raben con qualsiasi stringa da 4 a 40 lettere e cifre contenente almeno una cifra.

Peek legge il tracker Raben in inglese: stato, ogni passaggio con la dicitura Raben e città/paese, il giorno previsto di consegna (data senza ora) oppure — appena il giro dell'autista ne imposta una — la fascia oraria con gli orari, il peso e l'ora di consegna. Gli orari appaiono nel tuo fuso orario («Orari nel tuo fuso orario:»). I passaggi che cambiano solo data o parametri mantengono lo stato precedente. Peek omette numero di colli, volume, barra di avanzamento, note e contatti della filiale. Il tracker pubblico non fornisce nomi o indirizzi di mittente o destinatario, punto di ritiro, servizio né prova di consegna.

Peek interroga direttamente Raben fino a ogni 10 minuti, e fino a ogni 2 minuti il giorno in cui Raben scansiona la spedizione in consegna — dalle 6:00 (ora polacca) o da mezz'ora prima dell'apertura di una fascia oraria prevista — mentre la spedizione è aperta in Peek o nel giorno di consegna con le notifiche attive. Dopo due giorni senza novità (12 ore mentre mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna». Dopo quattro giorni senza scansioni in transito, fuori da dogana o problemi segnalati, Peek mostra «Nessun aggiornamento da…» e ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Raben.». Peek non può contattare Raben o il negozio al posto tuo, prenotare lo scarico né aprire un reclamo.

## Domande frequenti sul tracciamento Raben

### Che cos'è Raben Group?

Un gruppo logistico europeo a conduzione familiare fondato nel 1931 a Winterswijk, nei Paesi Bassi, con sede a Robakowo vicino a Poznań in Polonia. Trasporta pallet e carichi parziali su strada in 17 paesi, di solito prenotati dal mittente.

### Come si traccia una spedizione Raben?

Inserisci il numero di spedizione nel campo «Shipment tracking» su raben-group.com e premi «Search»: il tracker Raben si apre in una nuova scheda. Oppure incolla il numero o il link del tracker qui sopra.

### L'autista Raben scaricherà il mio ordine?

Secondo le regole di Raben Polonia, solo se l'azienda che ha prenotato il trasporto ha acquistato il servizio: lo scarico spetta al destinatario e lo scarico da parte dell'autista è un servizio extra (HUN o UNLO). Un passaggio `delivered D&D` indica consegna con scarico dell'autista.

### Quanto tempo impiega Raben per consegnare?

In Polonia uno o due giorni lavorativi a seconda del servizio scelto dal mittente; all'estero dipende dal paese di destinazione. Il tracker Raben mostra la data prevista di consegna.

### Chi devo contattare per una consegna Raben danneggiata?

Prima il negozio: nell'UE il venditore è responsabile fino a quando ricevi la merce. In Polonia puoi anche presentare reclamo scritto direttamente a Raben Logistics Polska; segnala i danni occulti entro 7 giorni dalla consegna.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – campo tracciamento, «Search», nuova scheda, 17 paesi
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – un numero per spedizione, data prevista, contatto sulla pagina di stato
- [Raben Group: Contact](https://www.raben-group.com/contact) – ricerca deposito, filiali per paese
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – groupage
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – consegne per negozi online
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – servizio gratuito, foto, documento di consegna
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, azienda familiare, 17 paesi
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo vicino a Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – scarico, HUN, UNLO, tempi, FIX, ND, WXACT, costo mancata consegna, PCD, orari servizio clienti, polacco
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – dati destinatario forniti dal committente
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – chi reclama, modalità, danni visibili e occulti, smarrimento, risposta in 30 giorni
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – controlli alla consegna, segnalazione scritta, 7 giorni
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 giorni, responsabilità del venditore, contattare prima il venditore
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – formato numero, link, riferimenti, cosa mostra e legge Peek, stati
:::
