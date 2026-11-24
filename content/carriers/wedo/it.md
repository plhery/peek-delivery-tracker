---
title: Tracciamento One by Allegro CZ (WE|DO): codici e ritiro
description: Traccia pacchi One by Allegro CZ (WE|DO, IN TIME, Uloženka) in Cechia: codici da 11 caratteri, One Box 48 ore, One Point 5 giorni e assistenza.
slug: tracciamento-one-by-allegro-cz
published: 2026-11-24
updated: 2026-11-24
---

One by Allegro CZ è la società di consegne ceca del gruppo Allegro (il sito [onedelivery.cz](https://onedelivery.cz/) appartiene ad Allegro Retail a.s.). Consegna ordini di allegro.cz e di altri negozi online attraverso tre servizi: **One Box** (oltre 5.244 locker self-service inclusi i partner AlzaBox e OX Box), **One Point** (1.960 negozi convenzionati per il ritiro) e **One Courier** (consegna a domicilio in tutta la Repubblica Ceca con oltre 350 furgoni e corrieri). La ricerca di Peek lo trova anche come WE|DO, IN TIME o Uloženka.

Puoi verificare un pacco ceco su [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) oppure incollare il codice di 11 caratteri nel riquadro qui sopra.

## Formato del codice di tracciamento One by Allegro CZ (WE|DO)

Le spedizioni ceche One by Allegro / WE|DO usano un **codice alfanumerico compatto da 11 caratteri**:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `2BM48291736` | 1 cifra + 1 lettera + 1 lettera/cifra + 8 cifre (11 caratteri totali) — codice standard One by Allegro CZ (WeDo). |
| `48P48291736` | 2 cifre + 1 lettera + 8 cifre (11 caratteri totali) — formato alternativo ceco One by Allegro (WeDo). |

I prefissi che iniziano per `7D` + cifra o `7X` + cifra sono esclusi da questa regola; tutti gli altri codici compatibili da 11 caratteri identificano **One by Allegro CZ** senza ambiguità con alta affidabilità. Peek legge anche link diretti da `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` e `trace.intime.cz/index.php?orderNumber=...`. I pacchi polacchi Allegro One hanno un tracciamento separato — vedi [Allegro One](carrier:allegro-one) e la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati One by Allegro CZ

L’API pubblica di tracciamento di One by Allegro CZ restituisce diciture in inglese, ciascuna con l’ora e lo scarto da UTC. Ecco le più comuni:

| Dicitura One by Allegro CZ | Cosa significa |
| --- | --- |
| `Parcel has been prepared by the sender` | Il negozio ha creato l’etichetta di spedizione; il corriere non ha ancora ritirato il pacco. |
| `Parcel has been picked up by the courier` | One by Allegro CZ ha ritirato il pacco dal mittente. |
| `Parcel has been dispatched to its destination point` | Partito dall’hub di origine verso la regione o la rete di ritiro di destinazione. |
| `Parcel has been accepted at the sorting center` | Lavorato in un centro di smistamento One by Allegro. |
| `Parcel has been accepted at the branch` | Arrivato alla filiale locale che serve il tuo indirizzo o punto di ritiro. |
| `Parcel has been released for delivery` | Caricato sul furgone per la consegna a domicilio o il deposito nel tuo One Box / One Point oggi. |
| `Parcel is awaiting pick-up` | Pronto per il ritiro nel tuo locker One Box o punto One Point (controlla SMS, email o app per il PIN di ritiro). |
| `Parcel has been delivered` | Consegnato al destinatario oppure ritirato dal locker o punto di ritiro. |

Se il tracker restituisce un messaggio insolito che potrebbe contenere testo personale del destinatario, Peek lo sostituisce con `Tracking update` conservandone data e ora nella cronologia.

## Regole di consegna One Box, One Point e One Courier

One by Allegro CZ pubblica scadenze di ritiro e regole di consegna su [onedelivery.cz](https://onedelivery.cz/):

- **Locker One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** accessibili **24 ore su 24, 7 giorni su 7** su oltre 5.244 locker (One Box, AlzaBox e OX Box). Ritira entro **48 ore** dal deposito inserendo il numero di telefono del destinatario e il PIN numerico inviato via SMS, email, app One by Allegro o app Allegro (allo schermo del locker non serve alcuna app). Il contrassegno (*dobírka*) si paga tramite link di pagamento nella notifica o al terminale carte del One Box.
- **Punti One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** nei 1.960 negozi partner durante l’orario di apertura, i pacchi restano di norma **5 giorni lavorativi** (prorogabili gratuitamente fino a **3 ulteriori giorni lavorativi** tramite il servizio clienti; durante il picco natalizio la giacenza One Point scende da 5 a **3 giorni**, mentre One Box resta a 48 ore). I limiti massimi One Point sono **20 kg**, lato lungo **1,2 m** e volume **0,2 m³**. Il contrassegno può essere pagato in contanti o con carta dove il negozio dispone di POS.
- **Consegna a domicilio One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** consegna standard il **giorno lavorativo successivo (`D+1`)** al ritiro, nei **giorni feriali tra le 08:00 e le 18:00**, fino alla prima porta chiudibile a chiave (i corrieri non sono tenuti a portare i pacchi ai piani superiori e le caselle postali non sono servite). Il giorno della consegna ricevi notifica via SMS, email o app. Il contrassegno si paga in contanti (si richiede l’importo esatto) o con carta senza sovrapprezzo. Puoi cambiare data di consegna o dirottare un pacco idoneo verso un punto di ritiro dall’app mobile One by Allegro o tramite assistenza; le modifiche di indirizzo passano dal servizio clienti.

Se perdi la finestra di ritiro o cerchi un pacco, consulta le guide su [locker per pacchi](guide:parcel-lockers), [pacco non ritirato](guide:uncollected-parcel) e [tracciamento fermo](guide:tracking-not-updating). Per altre reti ceche, confronta con [Packeta](carrier:packeta), [PPL](carrier:ppl) e [Česká pošta](carrier:ceska-posta).

## Tracciare One by Allegro CZ con Peek

Incolla il codice da 11 caratteri One by Allegro CZ, oppure un link `onedelivery.cz` o `trace.wedo.cz`, in Peek e Peek riconosce subito **One by Allegro CZ**. Se mai dovessi cambiare corriere, usa «Cambia» mentre aggiungi il pacco o «Cambia corriere» nella schermata di tracciamento.

Peek interroga direttamente l’endpoint pubblico di One by Allegro CZ (`edge.onedelivery.cz`) tramite l’[adattatore open source One by Allegro CZ di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), con nuovo tentativo automatico in caso di timeout di rete. Registra orari esatti con lo scarto da UTC e l’istante di consegna (`delivered_at`), scartando messaggi liberi che potrebbero contenere dati personali. L’API pubblica non include data stimata di consegna, città del deposito né peso.

Peek assegna queste diciture alle fasi: «Etichetta creata» (`Parcel has been prepared by the sender`), «Preso in carico» (`Parcel has been picked up by the courier`), «In transito» (spedizione, centro smistamento e filiale), «In consegna» (`Parcel has been released for delivery`), «Pronto per il ritiro» (`Parcel is awaiting pick-up`) e «Consegnato» (`Parcel has been delivered`). Legge anche le altre diciture Allegro One, come `Parcel has been returned to the sender`.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui One by Allegro CZ mostra `Parcel has been released for delivery`, ogni 2 minuti dalle 6:00 ora ceca finché il tracker risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando One by Allegro CZ non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a One by Allegro CZ.», con il link «Chiedi aiuto a One by Allegro CZ» alla pagina contatti. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a One by Allegro CZ.»

Nelle impostazioni di avviso di Peek scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna» per ricevere le notifiche desiderate.

## Contattare One by Allegro CZ

- **Modulo richieste e assistenza:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (clicca su *Dotaz na zásilku* per domande di acquirenti e venditori, PIN smarriti, proroga gratuita di 3 giorni in One Point o cambi indirizzo)
- **Pagina ufficiale di tracciamento:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Informazioni sui servizi:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) e [One Courier](https://onedelivery.cz/one-courier)
- **Sito principale:** [onedelivery.cz](https://onedelivery.cz/)

## Domande frequenti

### WE|DO, IN TIME e One by Allegro CZ sono lo stesso corriere?

Peek li tratta come un unico corriere: la ricerca trova One by Allegro CZ come WE|DO, IN TIME o Uloženka, e link `trace.wedo.cz` e `trace.intime.cz` nonché codici da 11 caratteri vengono tutti tracciati sotto One by Allegro CZ. Il sito `onedelivery.cz` non riporta la storia dei marchi.

### Quanto tempo rimane un pacco in un One Box o in un One Point?

In **One Box** (inclusi AlzaBox e OX Box), i pacchi attendono **48 ore** dal deposito nel cassetto. Nei negozi **One Point**, la giacenza standard è di **5 giorni lavorativi** (estendibile gratis fino a 3 giorni lavorativi tramite assistenza clienti), ridotta a **3 giorni** durante il picco prenatalizio.

### Posso pagare il contrassegno (dobírka) con carta?

Sì. I corrieri One Courier accettano contanti (importo esatto richiesto) o carta senza commissioni per il destinatario; One Box accetta pagamenti con carta tramite il link nell’avviso o al terminale del locker; One Point accetta contanti o carta se il negozio dispone di POS.

### Cosa faccio se ho perso il codice di ritiro One Box o One Point?

I codici di ritiro arrivano via SMS, email e nelle app One by Allegro e Allegro. Se non riesci a trovarlo da nessuna parte, contatta il servizio clienti dal modulo su [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
