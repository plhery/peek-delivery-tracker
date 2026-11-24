---
title: Tracciamento Kuehne+Nagel: numeri, stati e consegne
description: Traccia spedizioni strada, aria e mare Kuehne+Nagel per riferimento o link myKN: formati dei codici, tappe, passaggi doganali e opzioni di contatto.
slug: tracciamento-kuehne-nagel
published: 2026-11-24
updated: 2026-11-24
---

Kuehne+Nagel è uno dei principali operatori logistici al mondo, con quasi 92.000 dipendenti in quasi 1.300 sedi in circa 100 paesi. Movimenta merci via mare, aria e strada e il suo [tracciamento pubblico myKN](https://mykn.kuehne-nagel.com/public-tracking/) consente di consultare spedizioni di ogni modalità.

Il tracciamento pubblico su myKN copre le spedizioni avviate negli ultimi 90 giorni. Poiché Kuehne+Nagel movimenta merci attraverso stazioni stradali, aeroporti, stazioni di consolidamento container (CFS), terminal ferroviari e porti marittimi, una singola spedizione può mostrare sia tappe fisiche del percorso sia avvisi separati di prenotazione, dogana o documenti.

## Formati dei codici di tracciamento Kuehne+Nagel

Peek accetta riferimenti Kuehne+Nagel da 6 a 35 lettere e cifre, con almeno una cifra, rimuovendo spazi e trattini. myKN cerca i numeri di tracciamento interni Kuehne+Nagel, lettere di vettura e prenotazioni nella stessa casella dei riferimenti cliente. Peek riconosce tre formati:

- **`RWM` seguito da 9 cifre** (12 caratteri in totale, come `RWM482917360`) — numero di tracciamento stradale Kuehne+Nagel, che Peek riconosce come esclusivo di Kuehne+Nagel.
- **`RAPI` seguito da 8 cifre** (12 caratteri in totale, come `RAPI48291736`) — numero di tracciamento Kuehne+Nagel, anch’esso riconosciuto come esclusivo di Kuehne+Nagel.
- **2 cifre, `A` e 9 cifre** (12 caratteri in totale, come `26A482917360`) — numero di spedizione stradale. La forma coincide anche con [Australia Post](carrier:australia-post) e [Colis Privé](carrier:colis-prive), quindi Peek interroga in ordine Australia Post, Colis Privé e Kuehne+Nagel (Kuehne+Nagel per secondo in Svizzera e Germania).

Per il trasporto aereo e marittimo, i mittenti possono fornire anche una lettera di vettura Kuehne+Nagel, una polizza di carico house (*house bill of lading*) o un riferimento cliente diverso da questi tre schemi. Se incolli un link `mykn.kuehne-nagel.com/public-tracking/shipments?query=...`, Peek estrae il riferimento e lo instrada direttamente a Kuehne+Nagel. Per un quadro più ampio sui codici di corrieri e merci, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats).

## Come funzionano le tappe Kuehne+Nagel

Il tracciamento pubblico di Kuehne+Nagel separa la spedizione in due flussi: fermate fisiche lungo la tratta (origine, terminal di partenza, hub di transito, gateway, terminal di arrivo e destinazione) e avvisi informativi di trasporto (prenotazioni, pratiche doganali e prove di consegna).

| Dicitura Kuehne+Nagel | Cosa significa |
| --- | --- |
| Booking submission / Booking confirmation | L’ordine è registrato nel sistema Kuehne+Nagel prima che la merce si muova; una modifica tardiva della prenotazione non fa mai retrocedere una spedizione già partita. |
| Ready for pickup / Arrival (at origin) | La merce è pronta presso il mittente oppure il camion di ritiro è arrivato, prima della conferma di carico. |
| Pickup | Kuehne+Nagel ha ritirato la merce dal mittente. |
| Arrival / Departure / Load on vehicle / Load on vessel | Scansioni fisiche in terminal, piattaforma cross-dock, scalo ferroviario, aeroporto o porto lungo la tratta principale. |
| Customs entry start / Customs duties payment / Entry documents required | Lo sdoganamento export o import è in corso, oppure le autorità hanno richiesto documenti, dazi o ispezioni di agenzie (come FDA o PGA). |
| Customs entry release / Export customs entry release / Import customs entry release | La dogana o l’agenzia di frontiera competente ha svincolato il carico per proseguire. |
| In delivery | Il mezzo stradale è in giro per la consegna finale oppure è arrivato al luogo di consegna. |
| Delivery / Freight collection / Proof of delivery available | La merce è stata consegnata al destinatario o ritirata al terminal; `Delivery with discrepancies` indica comunque consegna segnalando una mancanza o un danno. |
| Delivery attempt failed / Delivery not possible | Un tentativo di consegna non è riuscito, ad esempio in caso di rifiuto del destinatario. |
| Shipment on hold / Delivery instructions pending / Missing/incomplete document | La spedizione è ferma in filiale o in dogana in attesa di documenti mancanti, pagamenti o istruzioni di consegna. |

Alcune diciture meritano particolare attenzione:

- **Available at CY / Available at CFS / Ready for collection / Available at rail terminal** indicano che il carico è arrivato a un deposito container marittimo, stazione CFS, aeroporto o terminal ferroviario ed è pronto per passaggio o ritiro. Kuehne+Nagel o un partner può ancora svolgere la consegna finale su strada, perciò queste scansioni restano in transito anziché segnare la consegna finale.
- **Freight handover** significa che Kuehne+Nagel ha affidato la merce a uno spedizioniere doganale o a un vettore successivo, non al destinatario finale.
- **Avvisi documentali e amministrativi** come `HAWB available`, `MAWB available`, `Transport document available`, `VGM closure`, `Delivery appointment submission` e `Customs billing invoice available` registrano atti amministrativi senza cambiare la fase fisica della spedizione.
- **Empty container gate-in e partenza del veicolo a destinazione** avvengono dopo il completamento della consegna e restano nello stato consegnato.

## Consegna, dogana e ritardi

`Delivery appointment submission` registra una data di consegna concordata per un momento successivo. Non è una scansione di uscita in consegna: la spedizione passa in consegna solo quando appare `In delivery` oppure l’evento `Arrival` del camion all’indirizzo di consegna.

Se il tracciamento mostra `Awaiting consignee's feedback`, `Awaiting consignee's payment` o `Entry documents required`, la dogana attende risposta, pagamento o documenti dal destinatario. `Unable to file customs entry - missing details or documentation` significa che la dichiarazione doganale non può essere presentata finché non arrivano. Vedi la [guida alla dogana](guide:customs) per come funzionano sdoganamento e dazi.

Le date pianificate mostrate su myKN sono stime e non scansioni effettive. Per consigli generali sul tracciamento fermo, leggi [quando il tracciamento smette di aggiornarsi](guide:tracking-not-updating).

## Tracciare Kuehne+Nagel con Peek

Quando incolli un numero `RWM` o `RAPI` o un link `mykn.kuehne-nagel.com/public-tracking/shipments?query=...` in Peek, Peek riconosce subito Kuehne+Nagel. Per un numero `26A...`, Peek interroga i corrieri sopra elencati a turno e mostra «Kuehne+Nagel ha questo pacco.» non appena Kuehne+Nagel risponde. Per una lettera di vettura o un riferimento cliente fuori da questi schemi, seleziona Kuehne+Nagel tramite **Cambia** quando aggiungi il pacco oppure **Cambia corriere** nella pagina di tracciamento.

Peek interroga direttamente la ricerca pubblica, le tappe di percorso e gli avvisi di trasporto di Kuehne+Nagel tramite l’[adattatore open source Kuehne+Nagel di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel). Registra solo le tappe realmente raggiunte (ignorando le date previste) e legge il peso totale in chilogrammi e il paese di destinazione quando disponibili. Quando la ricerca trova la spedizione con un riferimento cliente, Peek affianca il numero interno di Kuehne+Nagel sotto «Kuehne+Nagel lo traccia con il numero»; quando un riferimento corrisponde a più spedizioni, Peek non può sapere qual è la tua. Se il sistema di scansione indica il fuso UTC, Peek conserva quell’istante esatto; altrimenti data la scansione nel fuso del luogo (ora di Berlino per Amburgo, ora di Zurigo per Basilea), una scansione senza luogo nel fuso adiacente oppure su ora di Zurigo, mostrando tutto sull’orologio del tuo dispositivo.

Peek converte le tappe Kuehne+Nagel in fasi chiare: **Etichetta creata** per prenotazioni e avvisi pronto per il ritiro, **Preso in carico** per il ritiro, **In transito** per movimenti in hub, porti, scali ferroviari e passaggi intermedi, **In dogana** per sdoganamento e blocchi doganali, **In consegna** per `In delivery` e arrivo del camion a destinazione, **Tentativo di consegna** per tentativi falliti, **Problema segnalato** per fermi, ritiri falliti, annullamenti e documenti mancanti, e **Consegnato** per consegna, ritiro merce o prova di consegna. Gli avvisi contabili restano nella cronologia senza far arretrare la fase attuale, e una pratica doganale successiva alla consegna non annulla mai una consegna completata.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Kuehne+Nagel mostra `In delivery` o `Arrival` del camion all’indirizzo di consegna, ogni 2 minuti dalle 6:00 sull’orologio dell’ultima scansione finché il tracciamento Kuehne+Nagel risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando Kuehne+Nagel non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Kuehne+Nagel.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Kuehne+Nagel.»

Puoi scegliere gli avvisi di notifica per **Ogni scansione**, **Passaggi importanti** o **Solo la consegna** dalle impostazioni del pacco.

## Contattare Kuehne+Nagel

Il sito di Kuehne+Nagel offre un elenco delle filiali e un modulo di contatto:

- **Sito globale e portale di tracciamento:** [kuehne-nagel.com](https://www.kuehne-nagel.com/) e [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- **Elenco delle sedi locali:** [Kuehne+Nagel locations worldwide](https://www.kuehne-nagel.com/company/locations) per trovare la sede nazionale o locale più vicina
- **Modulo di richiesta online:** [Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert) per ricevere supporto via email
- **Altre reti merci:** se il tuo ordine è suddiviso su più vettori, confronta con [DHL Freight](carrier:dhl-freight) e [DSV](carrier:dsv).

## Domande frequenti

### Perché il mio numero di tracciamento Kuehne+Nagel non trova risultati?

Il tracciamento pubblico di Kuehne+Nagel copre solo le spedizioni avviate negli ultimi 90 giorni. Controlla che non manchino caratteri e, se la prenotazione è appena stata creata, attendi la prima tappa effettiva. Se un riferimento corrisponde a più spedizioni insieme, chiedi al mittente il codice `RWM`, `RAPI` o altro numero interno Kuehne+Nagel.

### Cosa significano «Service complete» o «Freight handover» su Kuehne+Nagel?

`Freight handover` significa che Kuehne+Nagel ha consegnato il carico a uno spedizioniere doganale o a un vettore successivo, quindi la spedizione è ancora in viaggio verso di te. `Service complete` indica la fine della tratta affidata a Kuehne+Nagel — che può essere un passaggio in porto o aeroporto anziché la consegna a domicilio — quindi verifica se esiste un codice separato del corriere locale.

### Perché alcune date presenti su myKN mancano dallo storico scansioni di Peek?

La vista pubblica del percorso su Kuehne+Nagel mostra date previste accanto alle scansioni effettive e avvisi futuri senza data. Peek registra solo le tappe che Kuehne+Nagel contrassegna come effettivamente raggiunte, così le stime programmate non compaiono come movimenti conclusi.

### Per quanto tempo resta disponibile il tracciamento pubblico Kuehne+Nagel?

Il tracciamento pubblico su `mykn.kuehne-nagel.com` copre le spedizioni avviate negli ultimi 90 giorni. Per consultare altro storico, myKN offre la registrazione gratuita.

:::sources
- [Sito globale Kuehne+Nagel](https://www.kuehne-nagel.com/)
- [myKN Public Tracking](https://mykn.kuehne-nagel.com/public-tracking/)
- [Sedi Kuehne+Nagel nel mondo](https://www.kuehne-nagel.com/company/locations)
- [Kuehne+Nagel Talk to an expert](https://www.kuehne-nagel.com/contact/talk-to-an-expert)
- [adattatore Kuehne+Nagel di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuehne-nagel)
:::
