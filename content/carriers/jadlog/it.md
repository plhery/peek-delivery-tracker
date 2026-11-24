---
title: Tracciamento Jadlog: 14 cifre, Pickup, Predict e stati
description: Traccia un pacco Jadlog in Brasile: codici a 14 cifre, stati in portoghese e Geopost, punti Jadlog Pickup, SMS Predict da 1 ora e assistenza.
slug: tracciamento-jadlog
published: 2026-11-24
updated: 2026-11-24
---

Jadlog è uno dei maggiori operatori brasiliani di spedizioni espresse e logistica e-commerce ed è la controllata brasiliana del gruppo europeo Geopost (insieme a DPD, Chronopost, SEUR, BRT e Speedy). In Brasile opera con oltre 500 franchising che coprono circa 5.000 comuni, più di 7.000 veicoli e oltre 4.000 negozi partner Jadlog Pickup. Puoi controllare una spedizione nel riquadro `RASTREIE SUA ENCOMENDA` sulla [home page di Jadlog](https://www.jadlog.com.br/jadlog/home), oppure incollare il codice a 14 cifre nel campo qui sopra.

## Codice di tracciamento Jadlog: formato a 14 cifre e dove trovarlo

Cerca il codice spedizione (*código de rastreio* o *remessa*) nell’e-mail di spedizione del negozio online, nella pagina ordini del marketplace o sull’etichetta. I codici Jadlog sono composti da 14 cifre consecutive:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `48291736405821` | Codice pacco Jadlog a 14 cifre. Condivide la struttura a 14 cifre con i corrieri europei Geopost/DPD come [DPD Germania](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) e [BRT](carrier:brt). |

Peek traccia solo con il numero a 14 cifre e non chiede mai il CPF. Se il codice ha 15 cifre e inizia con `8880`, appartiene a [J&T Express Brazil](carrier:j-and-t-br); un codice `BR` di 15 caratteri appartiene a [SPX Express Brazil](carrier:spx-br); un codice `AJ` di 17 caratteri appartiene a [Anjun Logistics](carrier:anjun); uno di 13 caratteri che termina in `BR` appartiene a [Correios](carrier:correios-br). Consulta [formati dei numeri di tracciamento](guide:tracking-number-formats) per altri formati.

## Cosa significano gli stati di tracciamento Jadlog

Poiché Jadlog fa parte di Geopost, gli aggiornamenti compaiono in portoghese sul sito Jadlog oppure in inglese quando vengono letti tramite l’API di Geopost.

| Jadlog / Geopost mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | Il venditore ha creato la spedizione elettronicamente e chiesto il ritiro; Jadlog non ha ancora scansionato il pacco fisico. | «Etichetta creata» |
| `EMISSAO` | Jadlog ha emesso il documento di trasporto e preso in carico il pacco nella propria rete. | «Preso in carico» |
| `Em transferência` | Il pacco viaggia tra franchising Jadlog, centri di smistamento o tratte nazionali in Brasile. | «In transito» |
| `Em rota de entrega` | Un corriere locale ha caricato il pacco ed è in giro per consegnarlo al tuo indirizzo. | «In consegna» |
| `ENTREGUE` | Jadlog ha registrato il pacco come consegnato. | «Consegnato» |

Quando un negozio stampa le etichette in blocco prima che il furgone Jadlog effettui il ritiro, `Order created` o `COLETA SOLICITADA` può restare l’unico evento per un paio di giorni lavorativi. Una volta comparso `Em transferência`, anche le lunghe tratte stradali tra stati brasiliani possono creare pause di più giorni prima della scansione nel franchising di destinazione.

## Servizi Jadlog: Predict, punti Pickup ed export internazionale

Jadlog descrive i propri servizi principali su [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) e nelle pagine dedicate:

- **Jadlog.com e Jadlog Package:** consegne e-commerce (Jadlog.com gestisce pacchi fino a 30 kg; Jadlog Package copre spedizioni stradali B2C e B2B).
- **Jadlog Rodo, RodoEconômico, Cargo e DOC:** trasporto stradale più pesante fino a 50 kg (Rodo), servizio stradale economico, cargo aereo B2B e spedizione documenti.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** attivo nelle aree metropolitane e ad alto volume, invia un SMS automatico con una fascia oraria di consegna di 1 ora per farti sapere quando attendere l’autista.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** oltre 4.000 negozi partner in Brasile (copertura oltre il 90% della popolazione) per ritiro, spedizione e resi e-commerce (*Reversa*), con avvisi via SMS ed e-mail.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** esportazioni dal Brasile verso 154 paesi (300 destinazioni) attraverso la rete internazionale Geopost.

**Cosa Jadlog non pubblica in modo generale.** Il sito Jadlog non indica un numero unico di tentativi di consegna, tempi garantiti o giorni fissi di giacenza nei punti Pickup validi per ogni negozio. Controlla l’SMS o l’e-mail ricevuta per il tuo pacco Pickup — oppure la pagina ordine dello store — per le scadenze esatte di ritiro e riconsegna.

## Pacco Jadlog bloccato, in ritardo o non ricevuto

Negli acquisti online il venditore è il cliente contrattuale di Jadlog e gestisce rimborsi, modifiche d’indirizzo e nuove spedizioni.

**Fermo su `Order created` o `COLETA SOLICITADA`.** Il venditore ha generato i dati della spedizione, ma Jadlog non ha ancora ritirato né registrato il pacco in rete. Chiedi al negozio se la spedizione ha lasciato il magazzino.

**Silenzio in transito (`Em transferência`).** I trasferimenti su gomma tra stati brasiliani, i fine settimana e i rallentamenti nei centri regionali mettono spesso in pausa gli aggiornamenti tra una filiale e l’altra. Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato `ENTREGUE` ma mancante.** Controlla prima con familiari, vicini e la *portaria* (portineria/reception) del palazzo, e verifica se al checkout avevi scelto un punto Jadlog Pickup. Se nessuno lo ha ricevuto, contatta subito il negozio affinché apra una verifica con Jadlog. Consulta [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato, smarrito o restituito.** Conserva l’imballo esterno e scatta foto chiare dei danni e dell’etichetta prima di scrivere al venditore; consulta [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Contattare Jadlog e tutele per i consumatori

La pagina [Atendimento di Jadlog](https://www.jadlog.com.br/jadlog/atendimento) non pubblica un numero telefonico per destinatari né un indirizzo e-mail generico di assistenza. Le opzioni pratiche sono:

- **Contattare prima il negozio online:** lo store dispone del contratto di trasporto (`CT-e` / fattura) necessario per aprire reclami, cambiare indirizzo o autorizzare un rimborso.
- **Ricerca franchising locale:** cerca la filiale della tua zona su [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) se ti servono informazioni su un deposito locale.
- **Richieste privacy e dati personali (LGPD):** Jadlog mette a disposizione un portale dedicato su [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Mediazione consumatori:** se una controversia con un negozio o corriere aderente non si risolve direttamente, i consumatori in Brasile con account gov.br argento o oro possono presentare un reclamo gratuito su [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), dove le aziende aderenti si impegnano a rispondere entro 10 giorni.

> Diffida di link via SMS, WhatsApp o e-mail che chiedono di pagare una piccola «tassa di sblocco» o «tariffa di riconsegna» per un pacco nazionale Jadlog. Verifica prima qualsiasi addebito inatteso direttamente presso il negozio in cui hai ordinato. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco Jadlog con Peek

Poiché i numeri a 14 cifre sono condivisi tra i corrieri Geopost/DPD nel mondo, [Peek](/) usa la tua posizione per decidere chi interrogare per primo. In Brasile Peek interroga prima Jadlog tra i candidati a 14 cifre (prima delle filiali europee DPD, Chronopost, SEUR e BRT) e mostra «Jadlog ha questo pacco.» non appena Jadlog lo conferma. Fuori dal Brasile Peek usa Jadlog come alternativa: controlla prima i corrieri europei Geopost e poi Jadlog se nessuno trova il numero. Incollare un link `jadlog.com.br/jadlog/captcha?cte=` identifica direttamente Jadlog ovunque; altrimenti tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e seleziona Jadlog (anche cercando «DPD Brazil»).

Peek interroga direttamente l’API pubblica di Geopost per Jadlog senza richiedere codice postale né CPF. Mostra la fase corrente («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Consegnato»), la cronologia sintetica delle scansioni (in inglese dall’API Geopost o in portoghese quando arriva dal tracker locale Jadlog) e l’orario di consegna. Data prevista di consegna, peso, indirizzi dei punti Jadlog Pickup e nomi o firme del destinatario non compaiono in Peek.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui il pacco va in consegna, ogni 2 minuti dalle 6:00 ora locale, finché il tracciamento di Jadlog risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di Jadlog non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Jadlog.» Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Jadlog.»

Puoi scegliere tra le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo il tracciamento e non può prenotare una riconsegna né contattare un franchising Jadlog al posto tuo.

## Domande sul tracciamento Jadlog

### Come si presenta un numero di tracciamento Jadlog?

Un codice pacco Jadlog è composto da 14 cifre (per esempio `48291736405821`, cifre inventate). Puoi tracciarlo con quel codice a 14 cifre su Peek o sulla [home page di Jadlog](https://www.jadlog.com.br/jadlog/home).

### Che cos’è Jadlog Predict?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) è un servizio automatico di avvisi via SMS disponibile nelle aree metropolitane e ad alto volume che comunica al destinatario la fascia prevista di consegna di 1 ora.

### Come funziona Jadlog Pickup?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) comprende oltre 4.000 negozi partner in Brasile dove i destinatari possono ritirare pacchi o consegnare resi. Jadlog invia notifiche via SMS ed e-mail per seguire la spedizione.

### Perché Jadlog è collegata a DPD e Geopost?

Jadlog fa parte del gruppo europeo Geopost insieme a DPD, Chronopost, SEUR, BRT e Speedy. Per questo utilizza numeri pacco Geopost a 14 cifre e si collega alla rete internazionale Geopost in 154 paesi.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – tracker `RASTREIE SUA ENCOMENDA` e panoramica della rete brasiliana
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – appartenenza al gruppo Geopost, oltre 500 franchising, oltre 4.000 partner Pickup, oltre 7.000 veicoli, 7.000 collaboratori diretti e indiretti, 154 paesi
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (fino a 30 kg), Package, Rodo (fino a 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – oltre 4.000 negozi partner, copertura >90% della popolazione, notifiche SMS/e-mail e resi Reversa
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – avvisi SMS automatici con fascia di consegna di 1 ora
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – esportazione verso 154 paesi / 300 destinazioni tramite Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – ricerca franchising e filiali in Brasile
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – ambito della pagina pubblica di assistenza
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portale per richieste sui dati personali
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – piattaforma pubblica di mediazione (risposta entro 10 giorni, account gov.br argento o oro)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – rilevamento a 14 cifre e priorità Brasile (alias `DPD Brazil`), regola link `captcha?cte=`, API ospiti Geopost, mappatura stati e frequenza di controllo
:::
