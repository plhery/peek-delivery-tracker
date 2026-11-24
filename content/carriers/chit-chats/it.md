---
title: Tracciamento Chit Chats: codici 10 car., USPS/Canada Post
description: Traccia un pacco Chit Chats dal Canada verso USA, Canada o estero: codici a 10 caratteri, passaggio a USPS o Canada Post, ritardi e contatti.
slug: tracciamento-chit-chats
published: 2026-11-24
updated: 2026-11-24
---

**Chit Chats** (*Chit Chats Express*) è una società canadese di spedizioni e-commerce fondata nel 2001 da Derek Nolan. Dispone di 66 sedi in tutto il Canada, accetta pacchi anche per invio postale (SmartDrop) e ritiro, e ha spedito oltre 50 milioni di colli per più di 100.000 mittenti ([chitchats.com](https://chitchats.com/)). I suoi pacchi sono consegnati da corrieri partner: il tracker indica il partner e fornisce il relativo codice di tracciamento, come [USPS](carrier:usps) negli Stati Uniti o [Canada Post](carrier:canada-post) in Canada. Puoi tracciare una spedizione su [chitchats.com/tracking](https://chitchats.com/tracking) oppure incollare il codice spedizione Chit Chats di 10 caratteri nel campo qui sopra.

## Codici di tracciamento Chit Chats (ID spedizione da 10 caratteri)

Ogni ordine Chit Chats riceve un **ID spedizione** alfanumerico maiuscolo di 10 caratteri (lettere e cifre, di solito con una lettera e una cifra diversa da zero all’inizio):

| Formato (inventato) | Che cos’è |
| --- | --- |
| `K4N8V3T6R2` | ID spedizione standard Chit Chats da 10 caratteri. |
| `P7M3X9D5Q8` | ID spedizione alternativo Chit Chats da 10 caratteri per spedizioni USA, Canada o internazionali. |

Quando Chit Chats consegna il pacco al partner dell’ultimo miglio, la pagina di tracciamento mostra anche il codice nativo di quel partner — come un codice a barre Intelligent Mail [USPS](carrier:usps) (che può avere un prefisso `420` + CAP di instradamento che Peek rimuove pulitamente) oppure un codice [Canada Post](carrier:canada-post). Vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di Chit Chats

Il tracker di Chit Chats mostra prima le proprie scansioni e poi quelle del corriere partner ([USPS](carrier:usps), [Canada Post](carrier:canada-post) o un altro):

| Chit Chats o il partner indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | Il venditore ha acquistato l’affrancatura online ma non ha ancora consegnato il pacco in una filiale Chit Chats. | «Etichetta creata» |
| `Received by Chit Chats` | Pacco fisico scansionato in una filiale o punto di consegna Chit Chats in Canada. | «Preso in carico» |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Smistato in una struttura Chit Chats; `In transit` è il passaggio al corriere partner. | «In transito» |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Preso in carico nella rete di smistamento del corriere partner (USPS, Canada Post o partner estero). | «In transito» / «Preso in carico» |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | In transito nei centri di smistamento nazionali o internazionali del partner. | «In transito» |
| `Out for Delivery`, `Item out for delivery` | In consegna oggi con il corriere partner locale. | «In consegna» |
| `Notice card left indicating where and when to pick up item` | Il partner ha tentato la consegna senza trovare nessuno e ha lasciato un avviso di ritiro. | «Tentativo di consegna» |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | In giacenza all’ufficio postale in attesa del ritiro (anche un avviso finale indica che il pacco è ancora ritirabile e non è ancora tornato al mittente). | «Pronto per il ritiro» |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Consegnato all’indirizzo del destinatario dal corriere partner. | «Consegnato» |

## Spedizioni verso gli USA, dogana e passaggi ai partner

- **Spedizioni negli Stati Uniti:** Chit Chats offre spedizioni verso gli USA con sdoganamento, dazi prepagati (DDP) e supporto per i documenti CUSMA, stima 2–6 giorni lavorativi e pubblica gli aggiornamenti tariffari USA 2026 su [chitchats.com](https://chitchats.com/). Vedi [pacchi fermi in dogana](guide:customs).
- **Tracciamento sul corriere partner:** una volta consegnato il pacco al partner, la pagina contatti di Chit Chats suggerisce di usare il codice del vettore direttamente sulla pagina di tracciamento del partner.

## Pacco Chit Chats in ritardo, fermo o smarrito

- **Contatta prima il venditore online:** la [pagina contatti](https://chitchats.com/contact) di Chit Chats spiega che il team vede le stesse informazioni di tracciamento visibili a te e può assistere solo il mittente per questioni di account, quindi i destinatari devono rivolgersi al venditore.
- **Pacco in ritardo:** superata la data di consegna stimata, la pagina di tracciamento di Chit Chats consiglia di contattare il venditore. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Consegnato o non trovato:** controlla cassetta postale, portico o ufficio postale locale (soprattutto se Canada Post ha lasciato un avviso), poi avvisa subito il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Chit Chats e contatti canadesi

Canali di supporto ufficiali pubblicati su [Contatti Chit Chats](https://chitchats.com/contact) e [Supporto Chit Chats](https://support.chitchats.com/en/support/home):

- **Numero verde (inglese e francese — fatturazione e filiali):** `1-844-842-8777` (oltre ai numeri locali di ciascuna delle 66 filiali canadesi negli orari di apertura).
- **Portale aiuto e supporto:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) e [chitchats.com/contact](https://chitchats.com/contact).
- **Tracker pubblico:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Fai attenzione a SMS falsi che chiedono un piccolo pagamento online per sdoganare un pacco tra Canada e Stati Uniti. Verifica sempre eventuali avvisi doganali o postali tramite il venditore o sul tracciamento ufficiale USPS o Canada Post. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Chit Chats con Peek

[Peek](/) interroga automaticamente Chit Chats come unico candidato quando inserisci un ID spedizione Chit Chats da 10 caratteri (`K4N8V3T6R2` o `P7M3X9D5Q8`, inventati) e mostra «Chit Chats ha questo pacco.» quando Chit Chats lo conferma; Peek riconosce direttamente anche i link incollati `chitchats.com/tracking/<10-car>`. Se devi selezionare Chit Chats manualmente, tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco (cercare «ChitChats» o «Chit Chats Express» lo trova ugualmente).

Peek legge direttamente il tracker di Chit Chats e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Consegnato»), il testo delle scansioni di Chit Chats e del partner e la città di ogni scansione senza CAP; i luoghi con numero civico vengono omessi. Mostra anche il giorno stimato di consegna, il peso in chilogrammi e le dimensioni. Il nome del destinatario compare sotto «Destinatario» nella tua pagina del pacco e la città di destinazione può chiudere il percorso sulla tua mappa durante il viaggio; i link condivisi non mostrano né l’uno né l’altro. Quando Chit Chats indica un partner noto a Peek, come USPS o Canada Post, insieme al relativo codice (un numero USPS senza prefisso ZIP di instradamento), Peek verifica che abbia il pacco: la scheda mostra quindi «Consegna con» seguito dal nome del corriere ed elenca entrambi i codici. Le scansioni proprie di Chit Chats hanno un orario esatto. Quelle del partner arrivano con un orario etichettato da Chit Chats come ora del Pacifico ma che può discostarsene di ore, perciò Peek le data sul fuso orario del rispettivo luogo (New York per `NEW YORK, NY`, Toronto per `TORONTO, ON`), una scansione senza luogo sul fuso della scansione vicina oppure su Vancouver, e le mostra tutte sull’orologio del tuo dispositivo. Peek omette nome del servizio, codici stato e città del mittente.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui il tracker mostra `Out for Delivery` o `Item out for delivery`, ogni 2 minuti dalle 6:00 sull’orologio dell’ultima scansione, finché il tracker di Chit Chats risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Chit Chats non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Chit Chats.», con il link «Chiedi aiuto a Chit Chats» verso la pagina contatti di Chit Chats. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Chit Chats.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può aprire reclami, modificare indirizzi né contattare Chit Chats o i suoi partner per tuo conto.

## Domande frequenti sul tracciamento Chit Chats

### Com’è fatto un codice di tracciamento Chit Chats?

Gli ID spedizione Chit Chats sono composti da 10 lettere maiuscole e cifre (per esempio `K4N8V3T6R2` o `P7M3X9D5Q8`, inventati). Dopo il passaggio al partner, il pacco riceve anche un codice del corriere partner (come USPS o Canada Post).

### Chi consegna un pacco Chit Chats a casa mia?

Lo consegna un corriere partner, come [USPS](carrier:usps) negli Stati Uniti o [Canada Post](carrier:canada-post) in Canada. Il tracker di Chit Chats indica il partner e fornisce il suo codice di tracciamento.

### Perché il mio pacco Chit Chats si è fermato dopo `Departed from Chit Chats facility`?

Il tracker di Chit Chats non indica un motivo. Il suo stato `In transit` corrisponde al passaggio al corriere partner, e le scansioni del partner compaiono appena quest’ultimo registra il pacco. Se la data di consegna stimata è passata, contatta il venditore.

### Chi devo contattare se manca il mio pacco Chit Chats?

Contatta il venditore che ha spedito il pacco. Secondo [chitchats.com/contact](https://chitchats.com/contact), il team di Chit Chats può assistere solo il mittente per questioni legate all’account. Il numero verde `1-844-842-8777` è riservato a fatturazione, filiali e assistenza in francese.

:::sources
- [Chit Chats: sito ufficiale](https://chitchats.com/) – consolidatore canadese di spedizioni e-commerce verso USA, Canada ed estero
- [Chit Chats: chi siamo](https://chitchats.com/about) – fondata nel 2001 da Derek Nolan, oltre 50 M di pacchi, 66 filiali in Canada e oltre 100.000 mittenti
- [Chit Chats: portale di tracciamento](https://chitchats.com/tracking) – tracciamento con ID spedizione da 10 caratteri
- [Chit Chats: contatti](https://chitchats.com/contact) – numero verde `1-844-842-8777`, politica di contatto del venditore ed elenco filiali (URL di aiuto collegato da Peek)
- [Chit Chats: portale aiuto e supporto](https://support.chitchats.com/en/support/home) – centro assistenza ufficiale
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – rilevamento ID 10 caratteri, regola link `chitchats.com/tracking/`, mappa stati propri e partner, estrazione ETA/peso/dimensioni/servizio e pulizia prefisso 420 USPS
:::
