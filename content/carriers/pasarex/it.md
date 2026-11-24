---
title: Tracciamento PasarEX: codici Amazon AMZPSR, dogana e aiuto
description: Traccia importazioni PasarEX (Pasar Express) in Colombia, Cile, Panama e Barbados: codici AMZPSR, stati doganali, reclami PQR e numeri di telefono.
slug: tracciamento-pasarex
published: 2026-11-24
updated: 2026-11-24
---

PasarEX (marchio commerciale di *PASAR EXPRESS SAS*, società colombiana fondata nel 1993) offre corriere espresso, trasporto aereo e consegna dell’ultimo miglio per piattaforme e-commerce. I suoi siti coprono **Colombia**, **Cile**, **Panama** e **Barbados**, e i suoi servizi includono un centro logistico a Miami e spedizioni multimodali dalla Cina.

PasarEX sdogana i pacchi direttamente come spedizioniere doganale, quindi il tracciamento segue la spedizione dal manifesto fino all’arrivo dell’importazione consolidata, alla dogana, ai magazzini e alla consegna locale.

## Formati dei codici di tracciamento PasarEX

Peek accetta codici PasarEX da 6 a 30 lettere e cifre con almeno una cifra. Uno schema appartiene solo a PasarEX:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 cifre (15 caratteri) — un codice Amazon cercato sul tracker PasarEX; nessun altro corriere in Peek usa questa forma. |

Se la conferma d’ordine include un URL diretto di tracciamento (`tracking.pasarex.com/?awb=...`), incollarlo in Peek estrae la lettera di vettura e assegna subito PasarEX. Per altri formati e-commerce, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats) e la [guida agli ordini Amazon](guide:amazon-orders).

## Cosa significano gli stati di tracciamento PasarEX

PasarEX raggruppa le righe di scansione sotto macro-intestazioni sul proprio tracker (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), con frasi di scansione in spagnolo o inglese corredate da città e data/ora.

| Testo scansione PasarEX | Cosa significa |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | I dati elettronici del manifesto hanno raggiunto PasarEX prima dell’arrivo fisico del pacco al suo hub di importazione. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | Il carico aereo consolidato è arrivato alla stazione di ingresso PasarEX (come Bogotá) prima della dogana. |
| `Entry to station for customs processing.` | Il pacco è entrato nella stazione doganale per lo sdoganamento import. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX ha sdoganato la spedizione e l’ha trasferita alle operazioni nazionali. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Registrato in un magazzino PasarEX o in una stazione di destinazione tra le tratte nazionali. |
| `Package in transit` / `Package in ground transit` | In movimento su gomma o su tratta nazionale tra le stazioni PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Avviso di servizio su possibili ritardi per disagi locali; la spedizione prosegue successivamente. |
| `Shipment left to local route for final delivery` | In giro oggi sul percorso locale con il corriere per la consegna finale. |
| `Shipment delivered in perfect condition` | Consegnato all’indirizzo del destinatario. |

## Sdoganamento, reclami PQR e tempi attesi

Poiché PasarEX sdogana direttamente le importazioni transfrontaliere prima della distribuzione nazionale:

- **Prima dell’arrivo del pacco:** finché il tracciamento mostra solo `Information (Manifest) of the shipment received.` o `Orden de pedido recibida - paquete no arribado`, l’ordine è prenotato ma il pacco non è ancora arrivato a PasarEX. Metti in conto una pausa finché non compare `Reception of consolidated cargo in import.` o `Carga consolidada para importación recibida`. Vedi la [guida alla dogana](guide:customs) e [quando il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **Regole PQR e indennizzo in Colombia (Risoluzione CRC 5050 del 2016):** su [PasarEX Colombia Support](https://pasarex.com/co/soporte/), Pasar Express SAS pubblica le scadenze di legge ai sensi della Legge 1369/2009 e della Risoluzione 5050/2016. I destinatari devono presentare reclami per manomissione o danno entro **5 giorni lavorativi** dalla consegna, mentre i mittenti hanno **10 giorni di calendario** (nazionale) o **6 mesi** (internazionale) per smarrimento, manomissione o danno (i contratti aziendali possono prevedere condizioni proprie). Servono copie della lettera di vettura aerea (*Guía Aérea*), fattura commerciale, fattura Pasar Express, elenco articoli, descrizione imballo e fotografie. PasarEX risponde di norma alle PQR entro **15 giorni lavorativi** (prorogabili se occorrono ulteriori verifiche), e gli utenti dispongono di **10 giorni lavorativi** dalla notifica per presentare ricorso (*recurso de reposición y en subsidio de apelación*).

## Tracciare PasarEX con Peek

Incolla il codice oppure un link `tracking.pasarex.com/?awb=...` in Peek, che segue oltre 4.500 corrieri. Un link va subito a PasarEX. Per un numero isolato Peek non interroga mai PasarEX d’ufficio: può interrogare altri corrieri con la stessa forma, poi interroga i servizi di tracciamento («Verifica dei servizi di tracciamento…») e assegna il pacco a PasarEX solo se un servizio di tracciamento nomina esclusivamente PasarEX con tappe datate. Tocca quindi «Cambia» e scegli PasarEX (sotto «Compatibili con questo numero» per un codice `AMZPSR`), oppure usa «Cambia corriere» su un pacco già aggiunto.

Poiché il tracker PasarEX richiede la spunta di un reCAPTCHA a ogni ricerca, Peek lo legge tramite l’[adattatore open source PasarEX di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) con un assistente browser. Peek raccoglie testo, città e ora di ogni scansione elencandole dalla più recente anziché secondo le intestazioni PasarEX. PasarEX fornisce orari locali senza scarto da UTC: Peek data ciascuno nel fuso orario della sua città (ora di New York per Miami, ora di Bogotá per Bogotá, Medellín e Cali, e ora di Santiago, Panama o Barbados per quelle sedi), una scansione senza città nel fuso della scansione vicina, altrimenti sull’ora di Bogotá, e mostra tutto con l’ora del tuo dispositivo. Il tracker PasarEX non pubblica data stimata di consegna, peso né prova di consegna.

Peek traduce le frasi di PasarEX in fasi chiare: **Etichetta creata** per la ricezione del manifesto prima dell’arrivo fisico, **In transito** dall’arrivo del carico consolidato attraverso sdoganamento, ingresso in magazzino e transito su strada (PasarEX passa direttamente dal manifesto all’arrivo import consolidato senza una scansione separata **Preso in carico**), **In dogana** durante la lavorazione doganale, **In consegna** quando parte sulla rotta locale, e **Consegnato** a consegna avvenuta.

Peek controlla il tracker PasarEX al massimo ogni ora — il minimo intervallo consentito da PasarEX tra due controlli, anche dopo un tentativo fallito, che il pacco sia aperto o meno; non effettua controlli ogni 2 minuti nel giorno in cui il pacco è in consegna. Dopo sette giorni su «Etichetta creata» controlla ogni 6 ore. Quando il tracker PasarEX non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora e ogni 3 ore dopo una giornata ferma.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a PasarEX.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a PasarEX.»

Scegli **Ogni scansione**, **Passaggi importanti** o **Solo la consegna** nelle impostazioni degli avvisi di Peek per decidere quali aggiornamenti ricevere.

## Contattare PasarEX

PasarEX pubblica linee telefoniche e indirizzi email per ogni paese:

- **Colombia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (anche linea PQR Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; email `sac@pasar.net` (supporto) e `pqr@pasar.net` (PQR/reclami)
- **Cile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; email `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; email `pagestionpe@pasar.net` e `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Panoramica aziendale:** [PasarEX Colombia](https://pasarex.com/co/) e [Nosotros](https://pasarex.com/co/nosotros/)

## Domande frequenti

### Che cos’è un numero di tracciamento `AMZPSR`?

`AMZPSR` seguito da 9 cifre (15 caratteri totali, come `AMZPSR482917360`) è un codice Amazon cercato dal tracker PasarEX. Nessun altro corriere in Peek ha questa forma, ma Peek non interroga PasarEX da solo: seleziona PasarEX sotto «Compatibili con questo numero».

### Perché il mio pacco PasarEX dice `Orden de pedido recibida - paquete no arribado`?

Significa che PasarEX ha ricevuto il manifesto della spedizione, ma il pacco fisico non è ancora giunto alla stazione di importazione PasarEX di destinazione. Le scansioni riprendono all’arrivo del volo consolidato.

### Perché Peek aggiorna PasarEX meno spesso di altri corrieri?

PasarEX consente al massimo un controllo all’ora sul proprio tracker, anche dopo un errore, quindi Peek lo interroga ogni ora. Il suo tracker richiede inoltre un reCAPTCHA a ogni ricerca.

### Quanto tempo ho per presentare un reclamo per danni con PasarEX in Colombia?

In base alla Risoluzione CRC 5050/2016 pubblicata su [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), i destinatari dispongono di **5 giorni lavorativi** dalla consegna per segnalare danni o contenuto mancante, mentre i mittenti hanno 10 giorni di calendario (nazionale) o 6 mesi (internazionale) per smarrimento o danno.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [adattatore PasarEX di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
