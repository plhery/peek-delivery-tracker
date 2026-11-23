---
title: Tracciamento Purolator: PIN, stati e ritiri
description: Traccia un pacco Purolator in Canada: formato del PIN, significato degli stati, ritiro dopo consegna mancata, dogana dagli USA e contatti.
slug: tracciamento-purolator
published: 2026-11-23
updated: 2026-11-23
---

Purolator è un corriere canadese per pacchi e merci, controllato a maggioranza da Canada Post. Per tracciare una spedizione inserisci il PIN su [purolator.com](https://www.purolator.com/en/shipping/tracker) oppure incollalo qui sopra. Il PIN arriva dal mittente: per un ordine online è nella conferma di spedizione del negozio.

## Codice di tracciamento Purolator: il PIN e il suo formato

Purolator chiama il codice di tracciamento PIN (Package Identification Number). Ogni spedizione ne riceve uno e Purolator lo chiede come riferimento per qualsiasi contatto sulla spedizione: PIN e codice di tracciamento sono quindi la stessa cosa.

| Formato (inventato) | Che cos’è |
| --- | --- |
| `482917364051` | 12 cifre; Peek verifica l’ultima come cifra di controllo. |
| `BKM482917364` | 3 lettere e 9 cifre; anche questo formato è chiamato PIN da Purolator. |

Il sito di Purolator accetta anche un riferimento fino a 15 caratteri, se impostato dal mittente (numero d’ordine, codice o frase): funziona come un PIN.

Dodici cifre è una lunghezza molto diffusa: la usano anche DPD France, Mondial Relay, Colis Privé, BRT, FedEx e vari corrieri cinesi, quindi controlla quale vettore nomina l’e-mail del negozio. Un codice da 16 cifre non è mai Purolator (spesso è [Canada Post](carrier:canada-post)) e 13 caratteri terminanti in `CA` (`RR482917367CA`) appartengono a Canada Post. Vedi [formati dei numeri di tracciamento](guide:tracking-number-formats).

Una spedizione può ricevere un nuovo PIN lungo il percorso: la cronologia mostra in tal caso una scansione con il nuovo codice.

## Cosa significano gli stati di tracciamento Purolator

Il portale mostra un titolo (`Shipment Created`, poi `Delivered`) sopra l’elenco delle scansioni. Ecco le diciture inglesi (sul sito francese compaiono in francese):

| Stato Purolator | Cosa significa per te |
| --- | --- |
| `Shipper created a label` | Il mittente ha creato l’etichetta. Purolator non ha ancora il pacco. |
| `Label information electronically submitted` | Stessa cosa: ricevuti i dati dell’etichetta, non il pacco. |
| `Picked up by Purolator` | Ritirato da Purolator. |
| `Arrived at sort facility` | Arrivato a un terminale Purolator; il luogo indica città e provincia del terminale. |
| `Departed sort facility` | Partito dal terminale verso il successivo o verso il deposito locale. |
| `Delayed in transit due to missed connection` | Ritardo interno alla rete Purolator; il pacco prosegue. |
| `Cleared customs` | Dall’estero: sdoganato e di nuovo in viaggio. |
| `On vehicle for delivery` | Sul furgone in consegna oggi. |
| `Attempted delivery - receiver unavailable` | Il corriere è passato ma nessuno poteva ritirare. |
| `Address correction required - missing entry code` | Il corriere non è riuscito ad accedere allo stabile. |
| `Available for pickup for 5 business days from arrival date at the counter` | In giacenza allo sportello pronto per il ritiro. |
| `Shipment delivered` | Consegnato a te o ritirato allo sportello. |

`Delayed in transit due to rail delay` è analogo alla coincidenza persa: un ritardo della rete Purolator, nessun problema al pacco. `Address correction required - resolution in progress` segnala che Purolator sta correggendo l’indirizzo; spesso segue `Shipment scheduled for delivery next business day` quando il pacco riparte.

`Shipment unavailable - En route to Purolator pickup location` significa che il pacco è in viaggio verso lo sportello di ritiro: potrai ritirarlo solo quando comparirà `Available for pickup…`.

## Tentativo di consegna, ritiro e firme con Purolator

Quando non c’è nessuno in casa, il tracciamento mostra di solito `Attempted delivery - receiver unavailable`, poi `Available for pickup for 5 business days from arrival date at the counter` nella stessa città e `Shipment delivered` una volta ritirato. I 5 giorni lavorativi di giacenza sono indicati nella dicitura stessa, quindi non aspettare troppo.

I punti di ritiro sono i Purolator Shipping Centres, gli agenti autorizzati e i negozi PostNet. La Guida ai Servizi Purolator elenca oltre 130 Shipping Centres e più di 550 agenti autorizzati in Canada, ricercabili tramite il [trova centri](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition).

La firma dipende dall’opzione scelta dal mittente:

- **Nessuna firma** è lo standard per le consegne residenziali. «Signature required» è un’opzione del mittente.
- **Adult signature required:** serve un documento ufficiale con foto e data di nascita alla porta. Senza documento il pacco non viene lasciato e va a uno Shipping Centre o agente, dove è richiesto un documento ufficiale con foto e indirizzo valido. Questi pacchi non vengono mai lasciati alla porta.

Il mittente può anche scegliere «Hold for pickup» affinché il pacco vada direttamente in deposito invece che al tuo indirizzo.

## Tracciamento Purolator bloccato, in ritardo o smarrito

Nessun risultato di solito significa che Purolator non ha ancora scansionato il pacco: la guida Purolator spiega che i dettagli compaiono solo dopo la presa in carico fisica. Controlla anche le cifre: un solo errore basta a non trovare nulla.

I ritardi compaiono come scansioni dedicate (coincidenza persa, ritardo ferroviario); le pagine di aiuto meno recenti citano anche meteo e guasti meccanici. Il pacco prosegue poi il viaggio. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

Smarrito, danneggiato o segnato consegnato ma non ricevuto? Contatta prima il negozio: il mittente è titolare del contratto con Purolator e ti dirà come aprire il reclamo.

Le merci spedite via corriere da Stati Uniti o Messico possono beneficiare dell’esenzione da dazi fino a 150 CAD e da imposte sulle vendite e accise fino a 40 CAD secondo le regole della Canada Border Services Agency (CBSA). Non è consentito dividere un ordine in più pacchi per restare sotto soglia. L’importatore paga dazi e tasse e la CBSA non regolamenta le commissioni di sdoganamento (brokerage fees), fissate dal corriere. Vedi [pacchi fermi in dogana](guide:customs).

## Numero assistenza clienti Purolator

Il numero di Purolator in Canada, secondo la Guida ai Servizi in vigore dal 30 giugno 2026, è **1 888 SHIP-123 (1 888 744-7123)**. Tieni pronto il PIN, richiesto a ogni contatto.

Hai ricevuto un SMS di mancata consegna con link? Non toccarlo: digita tu stesso il PIN su purolator.com. Il Governo canadese invita a inoltrare gli SMS di spam al 7726 (SPAM).

## Tracciare i pacchi Purolator con Peek

[Peek](/) verifica 3 lettere e 9 cifre (salvo alcuni prefissi di altri corrieri) su Colis Privé, TNT Australia e Purolator insieme, senza nominare Purolator dalla sola forma; quando Purolator riconosce il codice, Peek mostra «Purolator ha questo pacco.». Anche i codici da 12 cifre sono condivisi: se la cifra di controllo è valida, Peek interroga sei corrieri — Purolator, BRT, Colis Privé, Sending, Paquetexpress e Metropolitan Warehouse —, cinque insieme e il sesto se nessuno dei cinque lo conosce (Purolator per primo per gli utenti in Canada), e sceglie chi conferma il pacco. Se rispondono più marchi diversi o nessuno, scegli Purolator con «Cambia». Funzionano anche i link purolator.com con `pin=`, `pins=` o `searchValue=`.

Peek mostra stato, ogni scansione con città, provincia e paese sulla mappa, giorno previsto di consegna, paese di destinazione e, per colli singoli, il peso. Gli stati restano nell’inglese di Purolator. Il nome del destinatario e l’indirizzo esatto dello sportello sono omessi (compare solo `Available for pickup…`), e Peek non consente di modificare la consegna.

Gli orari vengono convertiti nel fuso orario del tuo dispositivo. Sui pacchi transfrontalieri Purolator li invia senza fuso orario: Peek li legge allora sull’ora della città di scansione continuando a mostrare la cronologia Purolator. Se il sito Purolator risponde talvolta con una verifica anziché con i dati, subentrano i servizi di tracciamento. Mentre tieni il pacco aperto in Peek, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e ogni 2 minuti dalle 6:00 (ora locale) nel giorno in cui Purolator lo scansiona in consegna, finché il tracciamento diretto di Purolator risponde (tramite i servizi di tracciamento, quel giorno fino a ogni 15 minuti mentre lo tieni aperto o con le notifiche attive). Altrimenti controlla ogni ora. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora anche se lo tieni aperto, e ogni 6 ore dopo sette giorni su «Etichetta creata». Se un pacco fermo su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza nuove scansioni, Peek mostra «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da…» con la data), «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Purolator.» e il link «Chiedi aiuto a Purolator». Peek può avvisarti con «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento Purolator

### Il PIN Purolator è uguale al numero di tracciamento?

Sì. PIN è il nome che Purolator usa per il numero di tracciamento, di solito 12 cifre oppure 3 lettere e 9 cifre.

### Perché il mio codice Purolator non funziona?

Di solito Purolator non ha ancora scansionato il pacco: il tracciamento parte solo dopo la presa in carico fisica. Altrimenti controlla eventuali errori di battitura, verifica il corriere col negozio o cerca un nuovo PIN nella cronologia.

### Quanto tempo tiene Purolator un pacco per il ritiro?

5 giorni lavorativi dal giorno di arrivo allo sportello, come indicato nello stato `Available for pickup for 5 business days from arrival date at the counter`. Porta un documento ufficiale con foto se è richiesta la firma di un adulto.

### Si può tracciare Purolator senza codice di tracciamento?

Solo con il riferimento del mittente, se ne ha impostato uno: il portale Purolator lo accetta come un PIN. Altrimenti chiedi il PIN al negozio.

:::sources
- [Purolator: Tracker](https://www.purolator.com/en/shipping/tracker) – formati PIN, diciture stati, intestazioni, giacenza 5 giorni lavorativi
- [Purolator: Trouver un centre d'expédition](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition) – ricerca centri di spedizione
- [Purolator: Guide des services de Purolator](https://web.purolator.com/app/guides/pdf/fr/Purolator_Service_Guide_French.pdf) – telefono, Shipping Centres e agenti, Hold for pickup, firme, firma adulto
- [Purolator: Tracking Details](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingdetails_E.htm) – nessuna scansione prima del ritiro, nuovo PIN, ritardi meteo e meccanici
- [Purolator: Tracking - By PIN / By Reference](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingRef_E.htm) – tracciamento con PIN e riferimento di 15 caratteri
- [Purolator: Preparing a Shipment - Shipment Confirmation](https://eshiponline.purolator.com/ShipOnline/Help/Default/shipstep3_E.htm) – cos’è il PIN
- [CBSA: Memorandum D8-2-16](https://www.cbsa-asfc.gc.ca/publications/dm-md/d8/d8-2-16-eng.html) – soglie 150 CAD e 40 CAD, divieto di frazionamento
- [CBSA: Step 4, import guide](https://www.cbsa-asfc.gc.ca/import/guide-4-eng.html) – pagamento a carico dell’importatore, brokerage fees non regolate
- [Government of Canada: Mobile spam](https://ised-isde.canada.ca/site/canada-anti-spam-legislation/en/hung-mobile-spam) – inoltrare SMS spam al 7726
- [Wikipedia: Purolator Inc.](https://en.wikipedia.org/wiki/Purolator_Inc) – proprietà maggioritaria Canada Post
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek, cifre di controllo, candidati e servizi di tracciamento
:::
