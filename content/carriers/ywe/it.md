---
title: Tracciamento YWE (YanwenExpress): numero, stati e aiuto
description: Traccia un pacco YWE (YanwenExpress) negli Stati Uniti: formato del numero YWE a 17 caratteri, significato delle scansioni fino a casa e chi contattare.
slug: tracciamento-ywe
published: 2026-11-18
updated: 2026-11-20
---

YWE (YanwenExpress, gestito da Yanwen Express LLC) è un corriere di consegna dell’ultimo miglio negli Stati Uniti: porta i pacchi dai propri hub statunitensi fino alla porta del destinatario per negozi online, rivenditori e marketplace. I suoi codici sono formati dalle lettere YWE seguite da 14 cifre: incolla il tuo nel riquadro qui sopra, oppure cercalo sul portale di tracciamento di YWE su [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Numero di tracciamento YWE: come si presenta

Un numero di tracciamento YWE conta 17 caratteri in tutto: il prefisso YWE seguito da 14 cifre. Esempi con cifre inventate:

| Numero | Che cos’è |
| --- | --- |
| `YWE48291057364911` | YWE e 14 cifre: un numero di tracciamento YWE valido. |
| `YWE4829105736491` | Solo 13 cifre: non rispetta il formato di YWE. Controlla se manca una cifra; se nessun corriere riconosce il codice, la sua pagina su peektracker.com mostra «Inizia come un numero di tracciamento di YWE, ma manca un carattere: forse c’è un errore di battitura. Confrontalo con il messaggio di spedizione.» |
| `YWLAX482910573649` | YW, tre lettere e 12 cifre: un numero [Yanwen](carrier:yanwen), non un numero YWE. |

Spazi e lettere minuscole non fanno differenza quando incolli il numero in Peek.

**Dove trovarlo.** Nell’e-mail di conferma spedizione del negozio oppure nella pagina del tuo ordine. Se non riesci a trovarlo, consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

**Un link di tracciamento.** Un link alla pagina di ricerca di YWE che termina con `?label=` e un codice YWE a 14 cifre apre direttamente quel pacco sotto YWE quando incolli l’intero URL in Peek. Il sito di YWE accetta fino a 30 numeri per volta (se ne inserisci di più, mantiene i primi 30).

## Stati del tracciamento YWE spiegati

Il tracciamento di YWE elenca ogni scansione con data e ora, scostamento da GMT e città e stato degli Stati Uniti, a partire dalla più recente. Le diciture sono in inglese. Nell’ordine abituale con cui compaiono negli Stati Uniti:

| Dicitura YWE | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.` | Il mittente ha registrato la spedizione presso YWE, ma YWE non ha ancora ricevuto fisicamente il pacco. | «Etichetta creata» |
| `Container picked up by YWE,LAX01 Awaiting Item.` | Negli Stati Uniti YWE ha ritirato il container aereo con cui è arrivato il pacco; il singolo collo non è ancora stato scansionato. | «In transito» |
| `Container received` | L’hub statunitense di YWE ha preso in carico il container. | «In transito» |
| `Parcel received by in LAX01.` | Prima scansione individuale del pacco nell’hub statunitense di YWE. `Parcel received by NJC01.`, senza «in», indica la stessa presa in carico in un altro centro. | «In transito» |
| `Parcel departed from Facility.` | Il pacco è partito dal centro di smistamento. | «In transito» |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit - departure`, `In transit to next facility.` | Passaggio in un hub di transito intermedio negli Stati Uniti, prima della filiale locale di recapito. | «In transito» |
| `Parcel received by local  Facility.` | Arrivo nella filiale di consegna locale negli Stati Uniti che copre la zona del destinatario. | «In transito» |
| `Parcel is out for delivery.` | In consegna sul furgone negli Stati Uniti. | «In consegna» |
| `Parcel has been delivered.(Front door)` | Consegnato negli Stati Uniti. Tra parentesi tonde è indicato il punto in cui il pacco è stato lasciato. | «Consegnato» |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | Tentativo di consegna non riuscito negli Stati Uniti per il motivo indicato tra parentesi quadre; il pacco rientra nel magazzino statunitense di YWE, non dal mittente. | «Tentativo di consegna» |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Lo stesso rientro al magazzino statunitense di YWE formulato come consegna fallita, da cui il pacco può uscire nuovamente in consegna. | «Tentativo di consegna» |
| `Return to sender` | È iniziato il reso verso il mittente, ma il pacco non è ancora tornato nei suoi magazzini. | «Problema segnalato» |
| `Returned to sender, shipment has been received in sender warehouse.` | Rientrato e ricevuto nel magazzino del mittente. | «Restituito al mittente» |

**Codici hub, causali e luoghi di consegna.** `LAX01` e `NJC01` sono codici degli hub statunitensi di YWE: le scansioni registrate in altri impianti riportano il rispettivo codice. Allo stesso modo, `[Restricted Address]` o `[Wrong Address]` sono esempi di causali di mancata consegna e `(Front door)` è un esempio del punto in cui viene lasciato il pacco.

**La scheda Alert non indica sempre un’anomalia.** Il portale di YWE suddivide i pacchi nelle schede `Awaiting`, `Transit`, `Delivered`, `Alert` e `Returned` in base all’ultima scansione. Inserisce `Parcel received by local  Facility.` (con doppio spazio prima di `Facility.`), cioè l’arrivo alla stazione di consegna locale, sotto `Alert` anche se si tratta di un passaggio ordinario. Peek lo mostra come «In transito».

**Altre diciture.** Una scansione non presente in tabella compare in Peek esattamente come scritta da YWE, e Peek ne ricava lo stato dalle parole quando possibile.

**Orari.** Ogni scansione include lo scostamento rispetto a GMT dell’hub statunitense che l’ha registrata. Peek legge l’istante con quello scostamento e converte tutti gli orari nel fuso orario del tuo dispositivo; su peektracker.com e nelle app per iPhone e Android la cronologia si chiude con «Orari nel tuo fuso orario:» seguito dal nome del fuso.

## Chi consegna i pacchi YWE

**Stati Uniti.** Negli Stati Uniti YWE gestisce direttamente l’ultimo miglio attraverso quelle che sul proprio sito definisce «Delivery Service Partner networks nationwide»: squadre di recapito locali partner diffuse sul territorio statunitense che si uniscono alla sua rete per consegnare o ritirare i pacchi. Nel codice di Peek la cronologia di YWE va dall’hub statunitense di YWE fino alla porta di casa e non nomina nessun altro corriere.

Nella pagina istituzionale YWE dichiara di essere partita a Los Angeles nel 2024 con l’obiettivo di coprire la maggior parte degli Stati Uniti entro la fine del 2026.

**Tentativi di consegna e ritiro negli Stati Uniti.** YWE non pubblica regole sul numero massimo di tentativi negli Stati Uniti, sugli avvisi di giacenza né su eventuali punti di ritiro. Dopo un tentativo andato a vuoto negli Stati Uniti, la scansione segnala che il pacco torna al magazzino di YWE (`Parcel will be returned to the warehouse.`). Per chiedere una nuova consegna o comunicare istruzioni di accesso, scrivi via e-mail a YWE (vedi sotto) indicando numero di tracciamento, indirizzo statunitense completo ed eventuali note per il corriere. Vedi anche [consegna mancata](guide:missed-delivery).

**Italia, Svizzera e altri paesi fuori dagli Stati Uniti.** YWE non dichiara alcun servizio al di fuori degli Stati Uniti. Se il venditore ti ha fornito un codice YWE per una spedizione diretta in Italia, in Svizzera o in un altro paese non statunitense, chiedi al negozio quale corriere effettua davvero la consegna a destinazione.

**YWE e Yanwen.** [Yanwen](carrier:yanwen) è censito in Peek come corriere distinto, con propri formati di codice e un proprio sito di ricerca, e il sito di YWE non specifica se le due realtà siano collegate. Quando il tracciamento di Yanwen indica `YanwenExpress` come distributore negli Stati Uniti e fornisce un numero YWE, Peek mostra «Consegna con YWE» e interroga anche il portale di YWE. Finché presso YWE risulta soltanto il preavviso senza scansioni proprie, compare «Annunciato a YWE, non ancora ricevuto». Non appena YWE pubblica una scansione almeno pari all’ultima di Yanwen, Peek prosegue il tracciamento su YWE mantenendo le scansioni precedenti di Yanwen.

## Pacco YWE non consegnato o tracciamento fermo

**`NOT_FOUND`.** Il portale di YWE mostra questo codice quando non ha scansioni associate al numero cercato ed elenca le possibili ragioni: il pacco non è ancora stato accettato o scansionato, il codice è errato oppure la spedizione risale a così tanto tempo fa che i dati non sono più disponibili. Peek mostra «In attesa di YWE» e continua a verificare. Ricontrolla il codice nel messaggio del venditore: YWE seguito da 14 cifre. Se dopo alcuni giorni non compare nulla, chiedi al negozio se l’ordine è stato effettivamente spedito.

**Fermo su `Shipping Order Created.`** YWE ha ricevuto i dati elettronici dell’ordine ma non ha ancora il pacco. Poiché YWE registra la prima scansione nel proprio hub negli Stati Uniti, chiedi al venditore dove si trovi adesso la merce e quando sia partita. Se proviene dalla Cina verso gli Stati Uniti, leggi [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Fermo su `Container picked up by YWE,LAX01 Awaiting Item.`** YWE ha preso in carico il container aereo negli Stati Uniti, ma non ha ancora scansionato singolarmente il tuo pacco all’apertura del carico.

**`Parcel delivery abnormal` o `Parcel delivery failed`.** La consegna negli Stati Uniti non è andata a buon fine per il motivo riportato tra parentesi quadre e il pacco rientra nel magazzino di YWE. Invia un’e-mail a YWE con il codice di tracciamento, l’indirizzo completo negli Stati Uniti ed eventuali istruzioni di recapito, e avvisa anche il venditore.

**`Return to sender`.** Il pacco sta tornando indietro. Quando appare `Returned to sender, shipment has been received in sender warehouse.`, il magazzino del mittente lo ha ricevuto: chiedi al venditore il rimborso o una nuova spedizione. Approfondisci in [pacco restituito al mittente](guide:returned-to-sender).

**Risulta consegnato negli Stati Uniti, ma non c’è.** Le parentesi tonde nella scansione di consegna, ad esempio `(Front door)`, precisano dove è stato lasciato il pacco. Sui pacchi consegnati negli Stati Uniti il sito di YWE mostra il link `view receipt`: inserisci lo ZIP code statunitense nel campo `Recipient's postal code` per visualizzare le foto di consegna quando presenti. Poi scrivi a YWE e contatta il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Pacco smarrito e reclami.** Secondo l’informativa sulla privacy del portale clienti di YWE, la registrazione di un account è riservata a clienti aziendali (`corporate/business clients`): il cliente contrattuale di YWE è quindi il venditore o il marketplace che ha prenotato la consegna negli Stati Uniti, non chi riceve il pacco. Il sito di YWE non prevede moduli di rimborso per i destinatari. Per ottenere il rimborso o una rispedizione rivolgiti al negozio o alla piattaforma di acquisto. Consulta [pacco smarrito](guide:lost-parcel) e [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

## Contatti YWE e assistenza clienti

Il sito di YWE, interamente in inglese, riporta:

- **E-mail**: un indirizzo nel piè di pagina di ogni pagina di `yanwenexpress.com`. Facendo clic si apre un riquadro che elenca cosa includere: la tua domanda, il numero di tracciamento del pacco e, nel testo dell’e-mail, l’indirizzo completo del destinatario, eventuali istruzioni di consegna, recapito telefonico e indirizzo e-mail del destinatario e la richiesta specifica.
- **Telefono**: nessuno pubblicato. La pagina `Customer support` riporta la frase «Calling us for further enquiries», ma non indica né un numero telefonico né orari di servizio.
- **FAQ**: presenti nella pagina `Customer support`, ma rivolte ai partner logistici (`Delivery Service Partner`) anziché ai destinatari in attesa di un pacco.

Per informazioni sull’ordine o sui rimborsi contatta il venditore; per la consegna negli Stati Uniti scrivi all’indirizzo e-mail nel piè di pagina del sito di YWE.

## Tracciare un pacco YWE con Peek

[Peek](/) riconosce subito un codice YWE dalla sua struttura — YWE e 14 cifre — che nessun altro corriere del catalogo utilizza, e interroga direttamente il sistema di YWE. Funziona anche incollando un link `yanwenexpress.com/tracking.html?label=` con un numero YWE. Non servono account, CAP o numero di telefono.

Peek mostra lo stato aggiornato, ogni scansione con città, stato e paese e l’orario di consegna. Le scansioni duplicate vengono scartate, così come l’eventuale nome del firmatario riportato nella riga di consegna.

Sul proprio sito YWE può mostrare anche una fascia oraria di consegna di due ore durante il transito negli Stati Uniti, la mappa con la posizione dell’autista e le foto di recapito consultabili tramite ZIP code. Peek tralascia questi elementi e gli indirizzi stradali delle singole scansioni, e non mostra una data di consegna stimata.

Dato che il tracciamento di YWE non cita altri corrieri, su un pacco aggiunto direttamente col numero YWE Peek non mostra la riga «Consegna con» e resta collegato al portale di YWE fino al recapito. Se il sito di YWE non è raggiungibile, nel frattempo Peek interroga servizi di tracciamento.

Mentre tieni il pacco aperto, oppure nel giorno di consegna con le notifiche attive, Peek controlla gli aggiornamenti fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 del mattino, ora locale, nel giorno in cui il pacco esce in consegna — finché il portale di YWE risponde (tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo una giornata senza nuove scansioni e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive); negli altri casi, e dopo due giorni senza novità (12 ore finché lo stato resta «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare YWE né il venditore al posto tuo.

## Domande frequenti sul tracciamento YWE

### Che cos’è YWE (YanwenExpress)?

È una società di consegna dell’ultimo miglio negli Stati Uniti gestita da Yanwen Express LLC. Recapita pacchi per e-commerce, negozi e marketplace tramite una rete di team di consegna locali partner negli USA e dichiara di aver avviato l’attività a Los Angeles nel 2024.

### YWE è la stessa cosa di Yanwen?

Peek li gestisce come due corrieri distinti: Yanwen utilizza propri codici e un proprio sito di tracciamento, e il sito di YWE non precisa se tra le due aziende vi sia un legame societario. Quando il tracciamento di Yanwen indica `YanwenExpress` come distributore negli Stati Uniti fornendo un codice YWE, Peek prosegue il monitoraggio anche su YWE.

### Dove posso tracciare un pacco YWE?

Nel campo di ricerca in alto in questa pagina oppure sul portale ufficiale di YWE su `yanwenexpress.com`, che consente di cercare fino a 30 numeri contemporaneamente. Inserisci il codice intero: YWE seguito dalle 14 cifre.

### Cosa significa «Container picked up by YWE»?

Significa che negli Stati Uniti YWE ha preso in consegna il container aereo con cui è viaggiato il tuo pacco. La sigla dopo la virgola, come `LAX01`, identifica l’hub statunitense di YWE. La prima scansione del singolo collo (`Parcel received by in LAX01.`) arriva in seguito, allo smistamento del container. Peek mostra «In transito» per entrambe.

### Chi devo contattare se un pacco YWE non arriva?

Prima di tutto il venditore o il marketplace presso cui hai acquistato, perché solo le aziende clienti possono aprire un account con YWE. Per chiarimenti operativi sulla consegna negli Stati Uniti puoi scrivere a YWE all’indirizzo e-mail indicato nel piè di pagina del sito allegando il numero di tracciamento; non è pubblicato alcun numero di telefono.

:::sources
- [YanwenExpress: home page](https://www.yanwenexpress.com/) – consegne dell’ultimo miglio negli Stati Uniti tramite reti di Delivery Service Partner, fino a 30 numeri per ricerca, e-mail nel piè di pagina e dati richiesti
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – avvio a Los Angeles nel 2024 e piano di copertura della maggior parte degli Stati Uniti entro fine 2026
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – scansioni con scostamento GMT, città e stato, categorie Awaiting, Transit, Delivered, Alert e Returned, codice NOT_FOUND e possibili cause, finestra di consegna, mappa dell’autista, «view receipt» col codice postale del destinatario e limite di 30 codici
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – invito telefonico privo di numero o orari e FAQ dedicate ai Delivery Service Partner
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – squadre di consegna negli Stati Uniti che entrano nella rete per ultimo miglio e ritiri
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC e registrazione account riservata a clienti aziendali
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomi, formato YWE a 14 cifre, portale e link `?label=`, testi delle scansioni e stati normalizzati, codici hub, classificazione sotto Alert, scostamenti GMT, dati mantenuti o omessi da Peek e passaggio da Yanwen
:::
