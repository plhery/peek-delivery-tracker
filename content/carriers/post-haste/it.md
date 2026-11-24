---
title: Tracciamento Post Haste: ticket NZ, codici e assistenza
description: Traccia un pacco Post Haste Couriers in Nuova Zelanda: formati di ticket ed etichette, stati Freightways, riconsegna e filiali locali.
slug: tracciamento-post-haste
published: 2026-11-24
updated: 2026-11-24
---

**Post Haste** (*Post Haste Couriers*) è una rete di corrieri neozelandese entrata nel **gruppo Freightways** nel 1977 che opera in collaborazione con Castle Parcels ([posthaste.co.nz](https://posthaste.co.nz/)). Servendo aziende e privati nell’Isola del Nord e nell’Isola del Sud tramite oltre 20 filiali regionali e partner Delivery Link, Post Haste gestisce spedizioni locali, regionali overnight, nazionali inter-isola overnight ed economy in due giorni, oltre alle zone rurali. Puoi controllare una spedizione su [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) oppure incollare il tuo link di tracciamento o codice di spedizione nel riquadro qui sopra.

## Codici di tracciamento Post Haste (ticket, etichette e link)

Post Haste usa diversi formati di etichette Freightways a seconda che il mittente abbia utilizzato un ticket o busta prepagata, un’etichetta a debito da 8 cifre, un codice a barre SSCC da 20 cifre oppure un’etichetta stampata dal cliente (come Despatch IT):

| Formato (inventato) | Che cos’è |
| --- | --- |
| `PH48291736` | Ticket corriere o busta Express Pack: codice prodotto da 2–4 lettere (ad esempio `PE` o `PH`) + numero ticket da 5–8 cifre (inserire senza spazi). |
| `73918462` | Numero di etichetta a debito Post Haste da 8 cifre. |
| `KLM4829173` | Codice di spedizione stampato dal cliente (lettere + 6–8 cifre); alcune etichette stampate dal mittente richiedono anche il codice conto del mittente presente nel link di tracciamento dell’email (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Se il venditore ti ha inviato un link `receiver.posthaste.co.nz` o `posthaste.co.nz` via email o SMS, **incolla l’intero link direttamente in Peek**: fornisce in automatico sia il codice di spedizione sia il codice conto del mittente quando richiesto. Vedi la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Post Haste

Post Haste si appoggia al sistema condiviso di tracciamento Freightways (usato da Post Haste, Castle Parcels, New Zealand Couriers e Now Couriers) e indica tipi e sottotipi di scansione insieme al quartiere o alla regione:

| Codice o scansione Post Haste | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Label created` | Etichetta elettronica creata dal sistema del mittente prima del ritiro del corriere. | «Etichetta creata» |
| `P` (`Picked Up`) | Ritirato dal mittente da un corriere Post Haste. | «Preso in carico» |
| `STAT/RZDEL` | Avviso di zona rurale o non urbana; il passaggio a un partner rurale aggiunge di solito 2–3 giorni lavorativi. | «In transito» |
| `STAT/REDEL` | Riprogrammato e rimesso in viaggio dopo una consegna mancata. | «In transito» |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` a un partner rurale) | Affidato a un vettore rurale; è ancora in viaggio verso il tuo indirizzo e non ancora consegnato a casa. | «In transito» |
| `ONBD` | Caricato sul furgone del corriere locale per la consegna odierna. | «In consegna» |
| `NOND` / `NOND/*` | Mancata consegna (ad esempio nessuno in casa con avviso lasciato, sede chiusa o accesso impedito). | «Tentativo di consegna» |
| `D`, `3P/D` | Consegnato all’indirizzo del destinatario (incluso `3P/D` completato da un partner rurale). | «Consegnato» |

`STAT/HLDFR8` indica che il pacco è fermo presso l’ufficio merci della filiale dopo una mancata consegna. Non precisa se attende il ritiro o un altro tentativo: prenota una riconsegna o contatta la filiale.

## Tempi di consegna, zone rurali e riconsegna in Nuova Zelanda

Secondo [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) e [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Tempi pubblicati:** **locale** tra aziende nella stessa città; **regionale overnight** (in genere entro l’ora di pranzo del giorno lavorativo successivo); **nazionale overnight** all’interno e tra Isola del Nord e Isola del Sud (di solito entro mezzogiorno del giorno lavorativo successivo per aziende, entro fine giornata per privati); **economy inter-isola in due giorni**; più **consegna il sabato** nelle principali aree metropolitane.
- **Indirizzi non urbani e rurali:** Post Haste copre la maggior parte delle zone rurali della Nuova Zelanda tramite partner locali (`STAT/RZDEL`), aggiungendo due o tre giorni lavorativi rispetto ai tempi urbani.
- **Assenza e riconsegna:** la regola standard di Post Haste è **non** lasciare i pacchi presso indirizzi incustoditi salvo preventiva autorizzazione (*authority to leave*). Se perdi una consegna, organizza una riconsegna online su [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) con il riferimento di consegna a 16 cifre, oppure contatta la tua filiale se non lo possiedi. Porta un documento d’identità per il ritiro in filiale.

## Pacco Post Haste in ritardo, fermo o smarrito

- **Il passaggio rurale sembra «consegnato» su alcuni siti:** se una scansione riporta `Delivered by courier or branch` o `Delivery Handed Over`, il pacco è di solito stato affidato a un corriere rurale (`3P`) anziché consegnato al tuo cancello — calcola 2–3 giorni lavorativi per il giro rurale (`3P/D`). Peek mantiene questi passaggi su «In transito» invece di segnarli consegnati troppo presto.
- **Nessuna scansione dopo l’etichetta o fermo in deposito:** se un codice stampato dal cliente non risulta da solo, incolla il link completo `receiver.posthaste.co.nz` dell’email di spedizione così Peek può inviare il codice conto del mittente. Per pacchi fermi in filiale, contatta il deposito Post Haste locale o il venditore.
- **Reclami per smarrimento o danni:** consulta le linee guida di Post Haste su [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) e contatta per primo il negozio che ha prenotato il corriere. Vedi anche [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [restituito al mittente](guide:returned-to-sender) e [tracciamento fermo](guide:tracking-not-updating).

## Assistenza clienti e filiali Post Haste in Nuova Zelanda

Contatti e sedi ufficiali pubblicati su [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) e [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Elenco filiali locali (Isola del Nord e Isola del Sud):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) elenca telefoni e indirizzi delle filiali nell’Isola del Nord (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) e nell’Isola del Sud (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), comprese le sedi condivise con Castle Parcels.
- **Strumento online di riconsegna:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Centro assistenza destinatari e condizioni:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Diffida di SMS fasulli che sostengono che un pacco in Nuova Zelanda richieda un piccolo pagamento con carta per riprogrammare la consegna. Usa sempre [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) o verifica direttamente con la tua filiale Post Haste. Leggi la guida sugli [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Post Haste con Peek

Il modo più semplice per aggiungere una spedizione Post Haste in [Peek](/) è incollare il link completo ricevuto nell’email di spedizione (`receiver.posthaste.co.nz/?consignmentid=CU..…` o `posthaste.co.nz/track?query=`), che Peek riconosce automaticamente come Post Haste e usa per estrarre l’account del mittente quando necessario. Se inserisci un numero nudo di ticket, etichetta a debito o spedizione (`PH48291736`, `73918462` o `KLM4829173`, inventati), Peek non riconosce Post Haste dal solo codice (interroga TNT Italia per un ticket come `PH48291736` e Australia Post per una spedizione come `KLM4829173`), quindi tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella schermata del pacco e scegli **Post Haste** (anche cercando «Post Haste Couriers» o «Posthaste»). Peek offre poi un campo facoltativo «Link di tracciamento completo» per il link dell’email, che contiene il conto del mittente necessario per alcuni pacchi.

Peek legge la ricerca Freightways sotto il nome Post Haste e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Tentativo di consegna», «Consegnato»), il testo della scansione, quartiere e regione, il giorno stimato di consegna quando Post Haste lo fornisce e l’orario di consegna. I tipi e sottotipi di scansione impostano la fase e restano fuori da ogni app Peek. Gli orari arrivano come istanti UTC e compaiono sull’orologio del tuo dispositivo. Quando un’altra rete Freightways, come Castle Parcels, pubblica la scansione più recente, il pacco mostra «Consegna con» seguito dal nome di quella rete. Peek non salva firma, nome del firmatario, foto di consegna, etichetta di autorizzazione al deposito, CAP o autista.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Post Haste mostra `ONBD`, ogni 2 minuti dalle 6:00 ora della Nuova Zelanda finché il tracker Post Haste risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Post Haste non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Post Haste.», con il link «Chiedi aiuto a Post Haste» verso la pagina di assistenza per i destinatari. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Post Haste.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può prenotare riconsegne, concedere l’autorizzazione al deposito o contattare la tua filiale Post Haste.

## Domande sul tracciamento Post Haste

### Quali codici di tracciamento funzionano con Post Haste?

Post Haste traccia ticket corriere e buste Express Pack (2–4 lettere + 5–8 cifre, come `PH48291736`, inventato), etichette a debito da 8 cifre (`73918462`, inventato), codici a barre SSCC da 20 cifre e codici di spedizione stampati dal cliente (`KLM4829173`, inventato) — oppure incolla il link completo `receiver.posthaste.co.nz` dell’email di spedizione.

### Perché il mio codice di spedizione stampato dal cliente non compare su Post Haste?

Alcune etichette stampate dal mittente (come le spedizioni Despatch IT) sono legate al numero di conto del mittente. Incolla il link completo `receiver.posthaste.co.nz` dall’email di notifica spedizione affinché il codice conto sia incluso nella ricerca.

### Cosa faccio se ho perso la consegna Post Haste?

Post Haste richiede normalmente una presenza salvo preventiva autorizzazione al deposito. Usa il modulo online su [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) con il riferimento di consegna a 16 cifre oppure chiama la filiale locale elencata su [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Perché il tracciamento Post Haste indica `Delivery Handed Over` o `STAT/RZDEL`?

`STAT/RZDEL` indica un indirizzo rurale o non urbano, e una scansione di consegna a terzi significa che il pacco è passato a un vettore rurale — aggiungendo di norma 2–3 giorni lavorativi prima della consegna finale a domicilio (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – rete nazionale di corrieri neozelandese (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – acquisizione Freightways (1977) e collaborazione con Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – consegne locali, regionali overnight, nazionali overnight, economy inter-isola in due giorni, sabato e rurali
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – etichette stampate dal cliente (con conto aziendale), ticket/Express Pack, codici a barre e sacche a contratto
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – richieste online di riconsegna e obbligo di documento per il ritiro in filiale
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – elenco filiali Isola del Nord e Isola del Sud
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – politica di assenza, notifiche e linee guida su smarrimento/danni (link di aiuto in Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – ricerca Freightways ticket/etichette/SSCC/spedizioni, regole link `receiver.posthaste.co.nz`, mappa codici (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) e data stimata
:::
