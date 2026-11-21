---
title: Tracciamento Allegro One: One Box, One Punkt e One Kurier
description: Traccia un pacco Allegro One in Polonia: formati del numero, significato degli stati in inglese, ritiro a One Box e One Punkt, ritardi, reclami e contatti.
slug: tracciamento-allegro-one
published: 2026-11-21
updated: 2026-11-21
---

Allegro One è la rete di consegna di Allegro, il marketplace polacco gestito da Allegro sp. z o.o. (con sede a Poznań): armadietti automatici Allegro One Box, punti di ritiro Allegro One Punkt e corrieri a domicilio Allegro One Kurier, riuniti in Allegro Delivery in Polonia. Segui la spedizione su allegro.pl o nell'app Allegro in *Moje przesyłki* (Le mie spedizioni), sulla pagina di tracciamento di Allegro Delivery o incollando il codice qui sopra. Trovi il numero nei dettagli dell'acquisto, nell'app e nell'e-mail inviata appena viene registrato.

## Dove trovare il codice Allegro One

Allegro mostra corriere, numero e stato in tre punti:

- ***Moje przesyłki*** su allegro.pl o nell'app, dalla preparazione fino alla consegna.
- ***Moje zakupy*** (I miei acquisti), nei dettagli dell'ordine.
- **L'e-mail** inviata quando il venditore inserisce il numero.

Questi sono i formati che Peek può seguire con Allegro One (caratteri inventati):

| Formato (inventato) | Cos'è |
| --- | --- |
| `A0K4829105` | Allegro One: `A0` e 8 lettere o cifre (10 caratteri). |
| `AD0K4829105736491` | Allegro Delivery: `AD0` e 14 cifre o consonanti (17 caratteri), associato anch'esso ad Allegro One. |
| `ADK4829105` | Formato precedente di Allegro Delivery, ancora su alcuni resi: `AD` e 8 lettere o cifre. |
| `ALE482910573649` | Vecchio formato Allegro One: `ALE` e 12 cifre. |

Un codice `AD` con 12 cifre indica un ordine di ritiro corriere presso il venditore, non un pacco. Non c'è ancora il codice? Lo inserisce il venditore, salvo invio automatico da un corriere integrato. Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

In Allegro Delivery operano anche partner logistici: DPD consegna agli Allegro One Punkt; DPD, [DHL](carrier:dhl) e ORLEN Paczka agli Allegro One Box (con One Kurier in alcune città), oltre alle opzioni Allegro DPD, Allegro DHL e Allegro ORLEN Paczka. Tutti viaggiano con codice Allegro e si seguono su Allegro con le stesse diciture. Un pacco spedito fuori da Allegro Delivery sul contratto diretto del venditore con [InPost](carrier:inpost), DPD Polska, ORLEN Paczka, DHL o Poczta Polska porta il codice di quel corriere e si traccia sul suo sito. Se il corriere non è integrato con Allegro, lo segui direttamente presso il trasportatore.

## Stati Allegro One: cosa significano

Il tracciamento di Allegro fornisce le scansioni in polacco o in inglese. In italiano, Peek legge le diciture inglesi di Allegro:

| Stato Allegro One | Cosa significa | Fase in Peek |
| --- | --- | --- |
| `Parcel has been prepared by the sender` | Etichetta creata; Allegro One non ha ancora il pacco. | «Etichetta creata» |
| `Parcel has been dispatched` | Pacco spedito (`Parcel has been dispatched at point` in un punto, `Parcel has been picked up by the courier` col corriere). | «Preso in carico» |
| `Parcel has been accepted at the sorting center` | Al centro di smistamento (`Dispatched from sorting center` in uscita). | «In transito» |
| `Parcel has been dispatched to its destination point` | In viaggio verso l'armadietto, il punto o la filiale della tua zona. | «In transito» |
| `Parcel has been released for delivery` | In consegna, anche quando è diretto a una One Box. | «In consegna» |
| `No space in the parcel locker` | Armadietto pieno: tentativo fallito, il corriere riporta indietro il pacco. | «Tentativo di consegna» |
| `Notice` | Nessuno in casa; lasciato avviso (*Awizo*). | «Tentativo di consegna» |
| `Parcel is awaiting pick-up` | Pronto per il ritiro nella One Box o nel One Punkt. | «Pronto per il ritiro» |
| `Parcel is awaiting pick-up — pick-up deadline is about to expire` | Scadenza di ritiro vicina (`Time for pick-up has been extended` se prorogata). | «Pronto per il ritiro» |
| `Time for parcel pick-up has expired` | Tempo scaduto; il pacco torna al venditore. | «Problema segnalato» |
| `Parcel has been returned to the sender` | In rientro al venditore («Problema segnalato» se seguono altre scansioni, altrimenti «Restituito al mittente»). | «Problema segnalato» / «Restituito al mittente» |
| `Parcel has been delivered` | Consegnato o ritirato da te presso One Box o One Punkt. | «Consegnato» |

Non esiste uno stato distinto dopo il prelievo dall'armadietto: compare comunque `Parcel has been delivered`. `Parcel may be delivered with delay` e `Parcel delivery time has been arranged with the recipient` sono semplici avvisi («In transito»), `Parcel has been redirected to a different point` registra il cambio di punto scelto da te, e anche dopo `No parcel` o `Parcel has been lost` possono arrivare nuove scansioni se il pacco riappare.

## Consegna in Polonia: One Box, One Punkt e One Kurier

Allegro Delivery consegna solo in Polonia per acquisti effettuati su Allegro.

**Tempi e misure.** Col corriere *Next Day* (*Dostawa jutro*), Allegro prevede la consegna il giorno lavorativo successivo o il sabato non festivo dopo la spedizione (garantita entro 3 giorni lavorativi). Alcuni venditori offrono la consegna *Same Day* (*Dostawa Dzisiaj*) a una One Box nella città del loro magazzino, con ritiro tra le 16:00 e le 22:00 dello stesso giorno (garantita in 2 giorni lavorativi). One Box e One Punkt accettano fino a 20 kg (reale o volumetrico) e 64 × 41 × 38 cm (59 × 41 × 38 cm negli armadietti DPD Pickup); One Kurier arriva a 31,5 kg, 120 cm sul lato lungo e 220 cm come somma dei tre lati.

**Avviso e ritiro.** All'arrivo nell'armadietto o nel punto, Allegro o il partner ti avvisa via e-mail, SMS o notifica nell'app Allegro con luogo e scadenza. Alla **One Box**, inserisci il codice di ritiro da solo o col telefono, inquadra il QR di SMS/e-mail o apri lo sportello dall'app Allegro. Al **One Punkt**, indica numero del pacco e codice di ritiro (o un documento d'identità in caso di problemi tecnici).

**Giacenza.** La guida Allegro indica 2 giorni di calendario (48 ore) in Allegro One Box e 2 giorni lavorativi in Allegro One Punkt (48 ore escluse domeniche e festività di legge nei punti secondo il regolamento; 3 giorni lavorativi con ORLEN Paczka, 2 con DHL e DPD). In punti e armadietti selezionati è possibile prorogare la sosta. Scaduto il termine, il pacco torna al venditore. Vedi [pacco non ritirato](guide:uncollected-parcel).

**Armadietto pieno.** Allegro può usare un armadietto sostitutivo entro 3 km in linea d'aria (6 km in aree rurali): vi resta fino a 24 ore prima di passare per 48 ore nell'armadietto scelto.

**One Kurier, deviazione e contrassegno.** One Kurier consegna a domicilio in città selezionate. Se le misure lo permettono, puoi deviare una volta un pacco corriere (escluso contrassegno) verso un armadietto o punto abilitato della stessa filiale su allegro.pl o nell'app prima dell'uscita in consegna (+1 giorno lavorativo). Se non c'è nessuno (*Awizo*, `Notice`), hai 48 ore per ritirarlo dove indicato o concordare una nuova consegna a pagamento. Il contrassegno (*Pobranie*, +4,99 PLN, massimo 6.000 PLN) si paga in contanti, BLIK o carta (VISA, MasterCard, Maestro), salvo limiti tecnici al solo contante. Vedi [consegna mancata](guide:missed-delivery).

## Pacco in ritardo, smarrito o danneggiato

**Fermo.** Controlla *Moje przesyłki*; se manca il codice chiedilo al venditore e leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Il venditore non risponde.** Dal 28 ottobre 2026 apri un *problem z zakupem* (problema con l'acquisto) da un'ora fino a due anni dopo il pagamento (su Allegro Lokalnie e con *Magazyn Allegro* resta la *Dyskusja*). Il venditore ha 24 ore per la prima risposta (esclusi weekend e festivi) e 7 giorni per ritardo, perdita o articolo mancante. Premi *Pomoc Allegro* per far intervenire Allegro; se dopo 7 giorni non è risolto, Allegro ti propone *Allegro Ochrona Kupujących* (Protezione Acquirenti).

**Consegnato ma assente.** Chiedi in famiglia, ai vicini o in portineria e segui [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Reclami.** Allegro riceve i reclami di Allegro Delivery online (dal conto o confermando i dati della spedizione) o secondo la legge postale polacca. Un pacco è considerato perso se non viene consegnato né avvisato entro 30 giorni dalla spedizione. Puoi reclamare se il pacco ti è stato consegnato o se il mittente ti cede il diritto. Per danni visibili rifiuta il pacco o fai redigere un verbale (*Protokół szkody*); i danni occulti vanno segnalati entro 7 giorni dall'accettazione, gli altri entro 12 mesi dalla spedizione. Risposta di solito in 7 giorni (massimo 30), ricorso entro 14 giorni e conciliazione presso il Presidente dell'UKE. Vedi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

## Contatti Allegro One

L'assistenza clienti per Allegro Delivery è gestita da Allegro:

- **Reclami consegna:** direttamente ad Allegro, come sopra.
- **One Box bloccata:** numero dell'assistenza tecnica sull'armadietto.
- **Ritardo:** apri un *problem z zakupem* col venditore e usa *Pomoc Allegro*.

## Tracciare Allegro One con Peek

[Peek](/) riconosce subito Allegro One dai codici `A0` o `AD` con 8 caratteri e dal formato `AD0` di 17 caratteri (e link allegro.pl con `?numer=`), senza CAP né account. Per `ALE` a 12 cifre, tocca «Cambia» (o «Cambia corriere») e scegli Allegro One sotto «Tutti i corrieri»; per formati non emessi (come `AD` + 12 cifre), Peek avvisa che «non emette numeri come questo».

In italiano, Peek legge le diciture inglesi di Allegro e converte ogni istante UTC nel tuo fuso orario; su peektracker.com e nelle app iPhone e Android chiude la cronologia con «Orari nel tuo fuso orario:» e il nome del fuso. Allegro non invia luoghi, nome dell'armadietto, stima di consegna, peso, mittente o partner, quindi non compaiono punti sulla mappa. Senza cronologia, Peek mostra «In attesa di Allegro One» e dopo due giorni senza risposta «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» (controllo giornaliero, poi settimanale dopo sette giorni).

Con il pacco aperto sullo schermo o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 (ora di Varsavia) quando è in consegna finché risponde Allegro; altrimenti e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), ogni ora. Tramite servizi di tracciamento controlla ogni ora (ogni 3 ore dopo un giorno fermo) e fino a ogni 15 minuti nel giorno di consegna a pacco aperto o notifiche attive. Avvisi a scelta: «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento Allegro One

### Cos'è Allegro One Box?

L'armadietto automatico di Allegro in Polonia, parte di Allegro One insieme a One Punkt e One Kurier. Riceve spedizioni Allegro Delivery portate da Allegro One, DPD, DHL e ORLEN Paczka.

### Quanto resta un pacco in Allegro One Box?

2 giorni di calendario (48 ore), prorogabili in alcuni armadietti, e 2 giorni lavorativi in un One Punkt. Poi torna al venditore.

### Quanto costa la consegna Allegro One?

La fissa il venditore nell'inserzione (anche 0 PLN). Con Allegro Smart! alle condizioni attuali è gratuita da 49,90 PLN di spesa presso uno stesso venditore.

### Quali sono le misure massime per Allegro One Box?

Fino a 20 kg e 64 × 41 × 38 cm per One Box e One Punkt; fino a 31,5 kg, 120 cm di lunghezza e 220 cm totali con One Kurier.

:::sources
- [Allegro: The Allegro Delivery program — information for sellers](https://help.allegro.com/en/sell/a/the-allegro-delivery-program-information-for-sellers-MR7exl1wYS4) – opzioni, partner, Same Day (16:00–22:00), dimensioni, contrassegno, giacenza, reclami e linea tecnica
- [Allegro: Program dostaw Allegro Delivery – informacje dla sprzedających](https://help.allegro.com/pl/sell/a/program-dostaw-allegro-delivery-informacje-dla-sprzedajacych-9gzevkzEGs1) – dettagli in polacco su Allegro Delivery e spedizioni a 0 PLN
- [Allegro: How to correctly dispatch parcels with orders](https://help.allegro.com/en/sell/a/how-to-correctly-dispatch-parcels-with-orders-a-guide-for-sellers-GRv326wwXF1) – dove vedere il pacco, codici automatici e corrieri non integrati
- [Allegro: Regulamin Allegro](https://policies.allegro.com/doc/f86a1a793dc4f5bc4b695b0ff0d54d974badab4169f5aaaddad89585c2c90690) – e-mail di spedizione con corriere e codice
- [Allegro: Regulamin usługi Allegro Delivery](https://policies.allegro.com/doc/e5cf7a904b3b08d908db62830a8bdac17b22cf354baf748e7853adff00fea84c) – Allegro sp. z o.o., servizio in Polonia, Next Day / Same Day, ritiro, armadietto sostitutivo, deviazione, Awizo, verbale di danno, perdita a 30 giorni, reclami e UKE
- [Allegro: How to solve problems with purchases in the Messages tab](https://help.allegro.com/en/sell/a/how-to-solve-problems-with-purchases-in-the-messages-tab-in-the-sales-center-or-in-the-message-center-Bv0zrVqDVIX) – problemi d'acquisto dal 28 ottobre 2026, tempi 24 ore / 7 giorni e Protezione Acquirenti
- [Allegro: Jak rozwiązywać problemy z zakupem w zakładce Wiadomości](https://help.allegro.com/pl/sell/a/jak-rozwiazywac-problemy-z-zakupem-w-zakladce-wiadomosci-w-sales-center-lub-w-centrum-wiadomosci-x5ZVrvzXPuP) – pulsante Pomoc Allegro
- [Allegro: We changed the Allegro Smart! Terms & Conditions for buyers](https://help.allegro.com/en/sell/a/we-changed-the-allegro-smart-terms-conditions-for-buyers-wwkzRYxRdio) – consegna gratuita da 49,90 PLN presso un venditore
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati dei codici, stati e dati letti da Peek
:::
