---
title: Tracciamento GOFO Canada: codici GFCA, stati e ritardi
description: Traccia un pacco GOFO in Canada: formato del codice GFCA, significato degli stati in Peek e cosa fare in caso di ritardo, consegna mancata o smarrimento.
slug: tracciamento-gofo-ca
published: 2026-11-23
updated: 2026-11-23
---

GOFO Canada è la rete canadese di GOFO, un corriere presente in diversi paesi che usa lo stesso sistema di tracciamento di GOFO negli Stati Uniti. GOFO indica che la rete canadese è attiva a Toronto, con Vancouver e altre aree in arrivo. I codici iniziano con `GFCA`: inserisci il tuo sulla pagina canadese di tracciamento su gofo.com oppure incollalo qui sopra. Non hai ancora il codice? Leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Numero di tracciamento GOFO Canada: com’è fatto

Peek riconosce un codice come GOFO Canada quando ha questo formato, in maiuscolo o minuscolo, con o senza spazi:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `GFCA48291736405829` | `GFCA` seguito da 14 cifre: la lettera di vettura GOFO Canada. Una lettera `O` digitata subito dopo `GFCA` viene letta da Peek come uno zero. |

GOFO assegna a ogni pacco anche un numero di tracciamento che ripete la lettera di vettura `GFCA` oppure corrisponde al riferimento del negozio. Peek segue solo la lettera di vettura `GFCA`: incolla quindi quel codice.

**Codici simili.** Le altre reti GOFO hanno prefissi propri: `GFUS` con 14 cifre, `GF` con 13, `CR` con 12 o `MGP` con 13 appartengono a [GOFO Express](carrier:gofo) negli Stati Uniti; `GFFR` e `CIFR` a [GOFO France](carrier:gofo-fr); `GFIT` e `CIIT` a [GOFO Italy](carrier:gofo-it); `GFNL` e `GFES` alle reti olandese e spagnola. Nessuno di questi è un codice GOFO Canada. Altri consigli in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento GOFO Canada spiegati

GOFO Canada utilizza gli stessi codici di scansione di GOFO US e Peek li interpreta allo stesso modo. Le fasi tipiche in ordine:

| Scansione GOFO | Fase in Peek | Cosa significa per te |
| --- | --- | --- |
| `Shipping Label Created` | Etichetta creata | Il negozio ha creato l’etichetta. GOFO non ha ancora il pacco. |
| `Arrived at GOFO Regional Hub` | In transito | Arrivato in un hub regionale GOFO. |
| `Loaded on Vehicle` | In transito | Caricato per il trasferimento tra centri GOFO verso la stazione di consegna. |
| `Arrived at GOFO Delivery Station` | In transito | Nella stazione GOFO incaricata della consegna. GOFO indica la consegna di solito entro 1–2 giorni di calendario. |
| `Out for Delivery` | In consegna | Con il corriere per la consegna. |
| `Delivered` | Consegnato | Consegnato. |

Poiché i codici sono condivisi, le diciture usate negli USA compaiono allo stesso modo anche in Canada. `Delivery Exception, Business Closed` indica un tentativo fallito, mostrato come «Tentativo di consegna» (il motivo dopo la virgola cambia). `Label Cancelled` significa che l’etichetta è stata annullata e nessun pacco viaggerà con quel codice; Peek mostra «Problema segnalato».

GOFO aggiunge talvolta telefono ed e-mail di assistenza ad alcune righe; Peek rimuove questa riga proprio come fa il sito di GOFO. Una consegna appare come semplice `Delivered`, senza eventuali testi dopo la virgola.

## Pacco GOFO Canada in ritardo, mancato o smarrito

**Nessun movimento.** Se vedi solo `Shipping Label Created`, GOFO non ha ancora il pacco: deve consegnarlo il negozio. Peek continua a interrogare GOFO e lo segnala sulla pagina del pacco se trascorrono quattro giorni senza nuove scansioni prima della consegna. Approfondisci in [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Consegna mancata.** Un tentativo non riuscito appare come «Tentativo di consegna» con la causale GOFO. GOFO indica che un pacco già in consegna riceve in genere un nuovo tentativo entro 24 ore; in caso di indirizzo incompleto (come un interno mancante), può tornare al magazzino locale per la verifica. Il seguito dipende da GOFO e dal negozio: leggi [consegna mancata](guide:missed-delivery). Se ricevi un SMS che chiede un pagamento per riconsegnare un pacco GOFO, leggi prima [SMS truffa sulle consegne](guide:delivery-scam-texts).

**Consegnato ma non ricevuto.** Peek mostra l’orario della scansione di consegna, ma non la foto né i dettagli aggiunti dopo la virgola. La pagina ufficiale gofo.com/ca/track mostra la prova di consegna — quasi sempre una foto del punto di rilascio — inserendo il nome del destinatario dell’ordine. Controlla prima attorno a casa, cassetta o zona cassette, vicini, conviventi, portineria o sicurezza dello stabile. Se manca ancora, segnalalo a GOFO (contatti sotto) allegando se possibile una foto dell’esterno dell’edificio e contatta il negozio con numero d’ordine e codice `GFCA`. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**In ritardo o smarrito.** Rivolgiti al negozio che ha spedito l’ordine: possiede i dati della spedizione e GOFO precisa che il mittente può aprire una verifica per tuo conto. Vedi [pacco smarrito](guide:lost-parcel). Se il pacco torna al mittente, consulta [restituito al mittente](guide:returned-to-sender): GOFO segnala che un reso non può essere intercettato, quindi chiedi al negozio una sostituzione o una nuova spedizione.

Ordine su Temu o Shein? Vedi [ordini Temu](guide:temu-orders) e [ordini Shein](guide:shein-orders). Per i dazi di importazione vedi [dogana](guide:customs).

## Assistenza clienti GOFO Canada

Per un ordine in ritardo, mancante o danneggiato, parti dal negozio tenendo a portata di mano numero d’ordine e codice `GFCA`. Per problemi di consegna o domande sul tracciamento, GOFO indica questi canali nella [pagina assistenza canadese](https://www.gofo.com/ca/customer-service):

- **E-mail:** cs@mail.gofo.com
- **Telefono (linea destinatari):** +1 (949) 688-6032, dal lunedì al venerdì 9:00–21:00, sabato e domenica 11:00–19:00 (Eastern Time).
- **WhatsApp:** +1 (787) 469-5811, dal lunedì al venerdì 9:00–21:00, sabato e domenica 11:00–20:00 (Eastern Time).
- **Online:** modulo di richiesta (chiede il numero di lettera di vettura) e chat nella stessa pagina.

## Tracciare un pacco GOFO Canada con Peek

[Peek](/) riconosce subito un numero GOFO Canada dalla forma: `GFCA` e 14 cifre, maiuscole o minuscole, con o senza spazi. Funziona anche un link di tracciamento GOFO Canada su gofo.com contenente `searchID=` e il numero. Peek segue solo la lettera di vettura `GFCA`, non il riferimento del negozio.

Peek legge il tracciamento diretto di GOFO Canada: stato, ogni scansione con orario, città e provincia indicati da GOFO, quei luoghi su una mappa, orario di consegna e paese di destinazione. Le scansioni restano nel testo inglese di GOFO senza la riga di supporto. Peek mostra la data prevista di consegna di GOFO finché il pacco non è in consegna, consegnato, restituito o pronto per il ritiro, e omette foto di consegna, coordinate e peso.

Mentre tieni il pacco aperto in Peek, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e ogni 2 minuti dalle 6:00 (ora locale) nel giorno in cui GOFO lo scansiona in consegna, finché il tracciamento diretto di GOFO Canada risponde. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e ogni 6 ore dopo sette giorni su «Etichetta creata». Se un pacco fermo su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza nuove scansioni, Peek mostra «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da…» con la data), «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a GOFO Canada.» e il link «Chiedi aiuto a GOFO Canada». Peek può avvisarti con «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Non può fissare una riconsegna né contattare GOFO o il negozio per te.

## Domande sul tracciamento GOFO Canada

### GOFO Canada è affidabile?

Corporations Canada registra GOFO EXPRESS CANADA INC. come società federale attiva numero 1807362-7, con sede legale a Mississauga, Ontario, continuata sotto il Canada Business Corporations Act a luglio 2026 dall’Ontario. GOFO gestisce anche reti negli USA, in Francia, Italia, Paesi Bassi e Spagna, tracciate separatamente in Peek.

### Cosa significa «Loaded on Vehicle»?

GOFO ha caricato il pacco per il tragitto tra due suoi centri, prima che arrivi alla stazione locale che effettuerà la consegna. Di solito seguono `Arrived at GOFO Delivery Station` e poi `Out for Delivery`.

### Perché il mio codice GOFO non funziona?

Controlla prima il prefisso: un codice GOFO Canada inizia con `GFCA` seguito da 14 cifre. I codici `GFUS`, `GFFR` o `GFIT` appartengono alle reti statunitense, francese o italiana di GOFO, che Peek segue sotto il rispettivo nome. Se un codice `GFCA` non trova nulla, confrontalo con l’e-mail di spedizione e chiedi al negozio.

:::sources
- [Corporations Canada: GOFO EXPRESS CANADA INC.](https://ised-isde.canada.ca/cc/lgcy/fdrlCrpDtls.html?corpId=18073627) – società federale attiva 1807362-7, continuata dall’Ontario l’8 luglio 2026, sede a Mississauga
- [Universal Parcel Scraper: GOFO Canada](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-ca) – formato codice, link, testi e stati condivisi con GOFO US, riga assistenza, dati mostrati ed esclusi, link di aiuto GOFO Canada
- [GOFO Canada: Customer Service](https://www.gofo.com/ca/customer-service) – e-mail, linea destinatari e orari, WhatsApp, modulo e chat; prova di consegna, dove controllare, nuovo tentativo entro 24 ore, 1–2 giorni dalla stazione, reclami tramite mittente, resi non intercettabili
- [GOFO Canada](https://www.gofo.com/ca) – attivo a Toronto, Vancouver e ulteriore copertura in preparazione
:::
