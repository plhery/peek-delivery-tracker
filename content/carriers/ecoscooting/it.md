---
title: Ecoscooting tracking: stati, punti di ritiro e contatti
description: Traccia un pacco Ecoscooting in Spagna o Portogallo: formati CNESP, CNPRT e 18 cifre, significato degli stati, punti di ritiro, tentativi e assistenza.
slug: ecoscooting-tracking
published: 2026-11-17
updated: 2026-11-20
---

Ecoscooting è un corriere spagnolo specializzato nella consegna locale dell'ultimo miglio in **Spagna e Portogallo** per gli ordini provenienti dalle piattaforme e-commerce internazionali (come [AliExpress](guide:aliexpress-orders) o [SHEIN](guide:shein-orders)) e dai venditori locali: non opera in Italia, ma lo incontri quando spedisci o acquisti verso un indirizzo spagnolo o portoghese. Si occupa esclusivamente della tratta finale, a domicilio o in un punto di ritiro. Puoi seguire il pacco su [ecoscooting.com](https://www.ecoscooting.com/) inserendo il numero presente nelle e-mail di Ecoscooting, oppure incollandolo nel riquadro qui sopra.

## Numero di tracciamento Ecoscooting: dove si trova e che formato ha

Il codice di tracciamento arriva nelle e-mail che Ecoscooting invia non appena il pacco inizia il suo percorso verso la destinazione finale; nell'e-mail di avvenuta consegna compare direttamente nell'oggetto. Ecoscooting non pubblica un nome mittente per gli SMS. Non lo trovi? Consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

Ecoscooting non dichiara uno schema ufficiale sul proprio sito, ma i suoi numeri si presentano in questi formati (cifre inventate):

| Formato | Che cos'è |
| --- | --- |
| `CNESP12345678901234567890` | `CNESP` seguito da 20 cifre: spedizione diretta in Spagna (se incolli la versione senza `CN`, cioè `ESP` + CAP a 5 cifre + `1234` + 11 cifre, Peek ripristina automaticamente `CN`). |
| `CNPRT00000000000000000000` | `CNPRT` seguito da 20 cifre: spedizione diretta in Portogallo (anche qui, se manca `CN` davanti a `PRT` + CAP a 7 cifre + `1234` + 9 cifre, Peek lo aggiunge). |
| `280010000123456789` | 18 cifre, etichetta spagnola di Ecoscooting: il codice postale spagnolo a 5 cifre (`01`–`52`), `0000` e poi 9 cifre. |
| `C26111512345678` | `C` o `E`, data a 6 cifre (`AAMMGG`) e 8 cifre: numero d'ordine di un pacco SHEIN, consegnato in Spagna anche da [Paack](carrier:paack). |

Codici simili possono appartenere ad altri operatori della filiera: su un ordine AliExpress vedi spesso una sigla `LP` seguita da 14 cifre, che è il riferimento logistico seguito tramite [Cainiao](carrier:cainiao). Prima della consegna locale, un pacco partito dalla Cina passa inoltre lo sdoganamento — ad esempio tramite l'agente doganale CACESA. Se `ecoscooting.com` non riconosce ancora il tuo codice, controlla il numero indicato sulla pagina dell'ordine; trovi il quadro completo in [tracciare un pacco dalla Cina](guide:tracking-from-china) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

Un numero sconosciuto su `ecoscooting.com` restituisce `No data found` con l'invito a verificare il numero d'ordine.

## Cosa significano gli stati di tracciamento Ecoscooting

Il portale `ecoscooting.com` mostra il tracciamento in inglese, spagnolo o portoghese in base alla lingua del browser (per un browser in italiano si apre in inglese, con selettore di lingua in alto). Ogni scansione ha un titolo in grassetto e una frase descrittiva sotto: qui trovi le frasi inglesi che vedi sul sito e su Peek.

| Stato di Ecoscooting | Cosa significa per te |
| --- | --- |
| `Shipment data information received` | L'ordine è registrato, ma Ecoscooting non ha ancora ricevuto fisicamente il pacco. |
| `Shipment is in transit to Cainiao Express` | Il collo viaggia ancora nella rete di [Cainiao](carrier:cainiao), prima dell'arrivo in Spagna o Portogallo. |
| `Your shipment has been accepted by delivery carrier` | Ecoscooting ha preso in carico il pacco in Spagna o in Portogallo. |
| `Your shipment has arrived at sorting center` | Il pacco è arrivato in un centro di smistamento Ecoscooting. |
| `Your shipment is out for delivery` | È sul furgone del corriere per la consegna in giornata. |
| `Your shipment delivery attempt failed [Recipient not at home]` | Il corriere è passato ma nessuno ha risposto; controlla l'e-mail per i passaggi successivi. |
| `Service point closed` | Dopo i tentativi falliti a casa, il corriere non ha potuto lasciarlo nemmeno al punto di ritiro: il pacco non ti aspetta lì e Peek mostra «Tentativo di consegna». |
| `Your package has been processed by the courier. We will notify you when it is ready for pickup at the point.` | Consegnato dal corriere al punto di ritiro, ma non ancora registrato né pronto per te. |
| `Your shipment has arrived at parcelshop and you‘ll receive pickup instructions shortly` | Pronto per il ritiro al parcelshop: il codice PIN arriva via e-mail o SMS. |
| `Your shipment has been collected by consignee at the parcelshop` | Ritirato dal destinatario presso il punto di ritiro. |
| `Your shipment has been delivered successfully` | Consegnato. |
| `Your shipment has expired at the parcelshop, and will be returned to sender` | Non ritirato entro il termine: sta tornando al mittente. |
| `Your shipment will be returned to its origin` | Il pacco viene rispedito all'origine / al mittente. |

Fai particolare attenzione al passaggio intermedio verso il punto di ritiro: sul sito di Ecoscooting il titolo in grassetto sopra quella riga è `Delivered to PUDO` (*pick-up and drop-off point*), che a prima vista sembra una consegna completata a te. Non lo è: aspetta il messaggio di arrivo al parcelshop con il codice PIN prima di recarti al negozio. Altri esempi generali sono nella guida agli [stati di tracciamento](guide:tracking-statuses).

Sopra la cronologia `ecoscooting.com` mostra anche un'intestazione riassuntiva: rimane scritta come `In transit` perfino dopo la consegna avvenuta, quindi affidati sempre all'ultima riga della cronologia. `Delivery Failure` accompagna una scansione segnalata come problema, `Exceeded pickup time` indica un pacco rimasto oltre la scadenza al punto di ritiro, mentre `No trajectory` significa che non ci sono ancora scansioni pubblicate.

## Consegne Ecoscooting, punti di ritiro e tentativi mancati

In **Spagna** Ecoscooting consegna tutti i giorni della settimana, **sabato e domenica compresi**; in **Portogallo** consegna **dal lunedì al sabato**. In nessuno dei due Paesi è possibile scegliere una fascia oraria di consegna.

A domicilio sono previsti **due tentativi di consegna**; vedi anche [consegna mancata](guide:missed-delivery). Dopo un primo passaggio andato a vuoto, Ecoscooting ti invia un'e-mail con il motivo: se l'indirizzo era errato o incompleto puoi aggiornarlo direttamente dal link nell'e-mail e il corriere riprova entro **3 giorni lavorativi**; anche per gli altri motivi il nuovo tentativo avviene entro **3 giorni lavorativi**. Dopo due tentativi falliti Ecoscooting gestisce la spedizione caso per caso avvisandoti via e-mail; in Spagna, dove disponibile, reindirizza il pacco a un punto di ritiro vicino.

Non sarai in casa? Dal link contenuto nell'e-mail inviata da Ecoscooting quando il pacco inizia il viaggio puoi autorizzare un'altra persona a firmare per te oppure chiedere la consegna in un punto di ritiro o locker.

Ecoscooting dichiara **oltre 8.000 punti di ritiro** (*pickup points*) più armadietti automatici (*lockers*). Quando il pacco è pronto al punto, ricevi via e-mail o SMS il **codice PIN**, l'indirizzo e gli orari di apertura. Hai **14 giorni** per ritirarlo: trascorso questo termine viene restituito al mittente e potrai chiedere il rimborso al venditore; vedi [pacco non ritirato](guide:uncollected-parcel). Le FAQ in spagnolo precisano che un pacco già depositato in un punto di ritiro non può essere spostato in un altro punto né riconvertito in consegna a domicilio.

## Pacco Ecoscooting non consegnato, perso o rubato

Ecoscooting ricorda di occuparsi solo del recapito locale: per articoli mancanti o errati, resi e rimborsi devi rivolgerti al negozio o alla piattaforma da cui hai acquistato (nell'UE il venditore è responsabile dei beni «dalla spedizione fino alla ricezione» e, salvo diverso accordo, deve consegnare entro 30 giorni). Se il tracking non avanza da giorni, leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating). Quando compare la riga `Your shipment has an operational anomaly occurred at the sorting center. Issue is being resolved, updates will follow`, Peek passa allo stato «Problema segnalato»: Ecoscooting segnala un imprevisto operativo al centro di smistamento in fase di risoluzione.

- **Segnato come consegnato ma non ricevuto:** cerca l'e-mail di conferma consegna di Ecoscooting (in spagnolo con oggetto *«¡Bien! Hemos entregado tu paquete …»*) e premi il pulsante **«No recibido»** (*«Não recebido»* in portoghese), oppure compila il modulo di richiesta assistenza, a cui Ecoscooting risponde entro **10 giorni lavorativi**. Approfondisci in [pacco consegnato ma non ricevuto](guide:delivered-not-received).
- **Smarrito o rubato:** se il tracciamento riporta `Package stolen` o un'eccezione `"Parcel lost"`, controlla nella posta in arrivo (e nello spam) se hai ricevuto un messaggio intitolato *«Aviso importante: Su paquete no puede ser entregado»* (in portoghese *«Aviso importante: Não foi possível entregar a sua encomenda»*). Se lo hai ricevuto, inoltralo direttamente al venditore per chiedere il rimborso; altrimenti contatta il servizio clienti Ecoscooting. Vedi anche [pacco perso](guide:lost-parcel).
- **Pacco danneggiato:** le pagine di Ecoscooting non pubblicano scadenze di reclamo né massimali di indennizzo per i danni; rivolgiti subito al venditore seguendo i consigli su [pacco danneggiato](guide:damaged-parcel).

> Le FAQ di Ecoscooting invitano a fare attenzione a telefonate ed e-mail sospette e a non comunicare mai dati riservati a terzi non affidabili. Più dettagli in [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Contatti Ecoscooting: telefono, e-mail e chat

Recapiti pubblicati su `ecoscooting.com` a novembre 2026:

- **Spagna:** `+34 912 15 93 69` oppure il numero gratuito `+34 800 00 03 69`; e-mail `customersupport@ecoscooting.com`.
- **Portogallo:** `+351 300 609 006`; e-mail `suportecliente@ecoscooting.com`.
- **Orari dell'assistenza:** dal lunedì al venerdì `09:00–18:00`, sabato e domenica `09:00–13:00`.
- **Chat e modulo di contatto:** sulla [pagina del servizio clienti Ecoscooting](https://www.ecoscooting.com/atencionCliente), dove trovi anche le istruzioni per scaricare il modulo ufficiale spagnolo di reclamo (*Hoja de reclamaciones*).

Le note legali (*Aviso legal*) indicano inoltre il recapito telefonico `931 222 879` per informazioni e reclami: la società è **Ecoscooting Delivery, S.L.** (NIF `B65874893`), con sede legale a San Fernando de Henares, vicino a Madrid.

## Tracciare un pacco Ecoscooting con Peek

[Peek](/) riconosce immediatamente come Ecoscooting i numeri `CNESP` e `CNPRT` seguiti da 20 cifre (ripristinando `CN` quando incolli il riferimento completo senza le due lettere iniziali) e quasi tutte le etichette spagnole a 18 cifre composte dal CAP (`01`–`52`), `0000` e 9 cifre — fanno eccezione quelle che iniziano per `3232`, per cui Peek interroga bpost ed Ecoscooting nello stesso primo giro (bpost per primo, tranne in Spagna e Portogallo), e quelle che iniziano per `44`, attribuite a [Quickpac](carrier:quickpac). Per un numero di pacco SHEIN (`C` o `E`, data a sei cifre e 8 cifre), Peek chiede a Ecoscooting se il collo sia nella sua rete e mostra anche [Paack](carrier:paack) tra i corrieri «Compatibili con questo numero». Altre sequenze di 18 cifre non vengono chieste a Ecoscooting (possono appartenere a bpost, GLS Spain o [Swiss Post](carrier:swiss-post)): tocca «Cambia» e seleziona Ecoscooting, oppure incolla il link di tracciamento di `ecoscooting.com`.

Quando [Cainiao](carrier:cainiao) o CACESA fornisce un codice `CNESP`/`CNPRT` oppure un'etichetta a 18 cifre che Peek riconosce come Ecoscooting, Peek prosegue automaticamente il tracciamento su Ecoscooting; altri codici a 18 cifre lo attivano solo se Cainiao o CACESA indicano Ecoscooting come corriere.

Su Peek vedi lo stato aggiornato, la cronologia con gli orari esatti di Ecoscooting (senza località, poiché il corriere non le espone), il peso, l'orario di consegna e — quando il pacco raggiunge un punto di ritiro — nome e indirizzo del negozio, conservati anche dopo il ritiro. Non c'è una data di consegna stimata. Gli orari sono convertiti nel tuo fuso orario: su peektracker.com e nelle app per iPhone e Android una nota in fondo alla cronologia lo specifica («Orari nel tuo fuso orario: …»). Quando il corriere lascia il pacco al negozio (`Delivered to PUDO`), Peek mantiene lo stato «In consegna» e passa a «Pronto per il ritiro» solo quando il punto lo registra; attendi comunque l'e-mail o l'SMS con il PIN. Peek tralascia il codice PIN, il telefono e gli orari del negozio, i codici postali e i riferimenti d'ordine. Le righe della cronologia restano nell'inglese di Ecoscooting, tranne `Out for delivery` isolato che viene mostrato come «In consegna». Poiché l'API di Ecoscooting non distingue in modo definitivo un numero inesistente da uno non ancora annunciato, un codice errato resta semplicemente senza aggiornamenti.

Peek verifica le novità fino a ogni 10 minuti, o fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui Ecoscooting registra l'uscita in consegna, finché risponde direttamente il tracker di Ecoscooting (tramite servizi di tracciamento ogni 15 minuti), con notifiche per «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna». Dopo due giorni senza novità, o dopo 12 ore se il pacco resta su «Etichetta creata» o «Preso in carico», i controlli passano a una volta all'ora.

## Domande frequenti sul tracking Ecoscooting

### Ecoscooting è un corriere affidabile?

Sì. Ecoscooting Delivery, S.L. è una società registrata in Spagna con sede a San Fernando de Henares (Madrid). Opera nella logistica in Spagna e Portogallo dichiarando oltre 8 anni di esperienza nelle consegne per piattaforme e-commerce transfrontaliere e venditori locali.

### Che cos'è la consegna con Ecoscooting?

È la tratta di recapito locale in Spagna o in Portogallo per gli ordini acquistati sulle piattaforme online internazionali: Ecoscooting porta il pacco fino alla tua porta o a un punto di ritiro, mentre la vendita, i resi e i rimborsi restano in capo al negozio.

### Ecoscooting consegna nel fine settimana?

In Spagna sì: tutti i giorni, compresi il sabato e la domenica. In Portogallo consegna dal lunedì al sabato, senza fasce orarie prenotabili in nessuno dei due Paesi.

### Quanto tempo impiega Ecoscooting a consegnare?

Nelle pagine rivolte alle aziende Ecoscooting pubblicizza consegne nazionali in **24–48 ore** una volta ricevuto il collo nella propria rete. Non è però una promessa sull'intero viaggio internazionale dell'ordine, e la pagina di tracciamento non mostra una data prevista.

:::sources
- [Ecoscooting: Customer Service / Frequent Questions](https://www.ecoscooting.com/atencionCliente) – contatti, orari, chat, tentativi falliti, 3 giorni lavorativi, delega alla firma, 14 giorni al punto di ritiro, consegnato non ricevuto, smarrimento/furto, Hoja de reclamaciones, avviso truffe e oltre 8 anni di attività (versioni inglese, spagnola e portoghese)
- [Ecoscooting: Home](https://www.ecoscooting.com/) – operatività in Spagna e Portogallo, giorni di consegna, due tentativi, deviazione al punto di ritiro in Spagna, 24/48 ore, riquadro di tracciamento e intestazioni
- [Ecoscooting: Home Deliveries](https://www.ecoscooting.com/toDoor) – due tentativi a domicilio, punto di ritiro in Spagna, 24–48 ore
- [Ecoscooting: Network of Pickup Points and Lockers](https://www.ecoscooting.com/pickUpPoint) – oltre 8.000 punti di ritiro e locker
- [Ecoscooting: Aviso legal (spagnolo)](https://www.ecoscooting.com/aviso-legal) – ragione sociale Ecoscooting Delivery, S.L., sede a San Fernando de Henares, telefono 931 222 879 e fogli ufficiali di reclamo
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – responsabilità del venditore fino alla ricezione e termine di 30 giorni nell'UE
- [Universal Parcel Scraper: Ecoscooting (inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecoscooting) – formati riconosciuti da Peek, passaggio da Cainiao/CACESA, gestione di `Delivered to PUDO` e campi letti
:::
