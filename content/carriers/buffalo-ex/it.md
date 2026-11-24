---
title: Tracciamento BUFFALO: codici BUFZA, dogana Sudafrica e aiuto
description: Traccia un pacco BUFFALO International Logistics dalla Cina al Sudafrica: codici BUFZA...YQ, scansioni IVA e dogana SARS, orari di consegna e aiuto.
slug: tracciamento-buffalo-ex
published: 2026-11-24
updated: 2026-11-24
---

**BUFFALO** (*BUFFALO International Logistics*, spesso chiamata *Buffaloex* o *Buffalo Express*) è un operatore di logistica e-commerce transfrontaliera tra Cina e Africa attivo da aprile 2017 ([buffaloex.com](https://www.buffaloex.com/)). Ha sede centrale a Shanghai e filiale africana a Johannesburg. I suoi magazzini integrati coprono 35.000 metri quadrati — tra cui un magazzino doganale da 6.500 m² — e gestisce 300 veicoli di proprietà. BUFFALO afferma che i propri sistemi sono integrati con le piattaforme doganali e bancarie del Sudafrica e che è prima per quota di mercato nei piccoli pacchi e-commerce dalla Cina al Sudafrica. Puoi controllare una spedizione su [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) oppure incollare il codice `BUFZA` nel campo qui sopra.

## Codici di tracciamento BUFFALO (formato `BUFZA...YQ`)

Le spedizioni e-commerce transfrontaliere dalla Cina al Sudafrica gestite da BUFFALO recano un codice a barre di 17 caratteri che inizia con `BUFZA` e termina con `YQ`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `BUFZA6482917305YQ` | Codice standard BUFFALO da 17 caratteri Cina–Sudafrica (`BUFZA` + 10 cifre + `YQ`). |
| `BUFZA4SK73918BUYQ` | Codice alternativo da 17 caratteri `BUFZA...BU...YQ` per pacchi internazionali. |

La pagina contatti di BUFFALO indirizza a ZA-BUFFALO i numeri d’ordine che iniziano con `BUFEX-`. Se hai perso il codice di tracciamento, le FAQ di BUFFALO consigliano di chiederlo al fornitore o di accedere al tuo account BUFFALO. Vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di BUFFALO

Secondo l’[assistenza BUFFALO](https://www.buffaloex.com/support.html) e il feed di tracciamento BUFFALO, le spedizioni passano attraverso magazzino cinese, volo export, pagamento di IVA e dazi doganali sudafricani, depositi di Johannesburg/regionali e consegna locale:

| BUFFALO indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order Confirmed` | Ordine creato mentre il pacco è ancora presso il fornitore, prima dell’invio al magazzino BUFFALO in Cina. | «Etichetta creata» |
| `Enter Warehouse` | Il pacco è arrivato nel magazzino BUFFALO in Cina. | «Preso in carico» |
| `Out of Warehouse`, `Air Shipping`, `International Shipping` | Partito dal magazzino BUFFALO in Cina e spedito in Sudafrica via aerea o marittima. | «In transito» |
| `Clearance`, `At Destination`, `Arrival at South Africa customs`, `Customs VAT and duty can be paid now, please pay in time`, `Tax Paid`, `Clearance finished` | Sdoganamento export in Cina in attesa del volo oppure dogana import sudafricana; IVA e dazi vanno pagati prima dello svincolo. | «In dogana» |
| `Inbound`, `AIR DC Received the parcel`, `BUFFALO Airport Warehouse received`, `Transit the parcel from AIRDC to JHBDC`, `JHB DC Received the parcel`, `Dispatching`, `WAYBILL IMPORTED`, `Transit to thirdparty Express`, `RETURNS` | Sdoganato e in movimento tra i depositi BUFFALO dell’aeroporto (`AIR DC`) e di Johannesburg (`JHB DC`), affidato a un corriere terzo sudafricano o rientrato in deposito tra un giro e l’altro. | «In transito» |
| `Driver Pick the Order`, `SCAN VEHICLE DEPARTURE` | Affidato a un autista BUFFALO o a un corriere locale per la consegna di oggi. | «In consegna» |
| `Returned to BUFFALO warehouse[...]` | Riportato al deposito BUFFALO indicato fra parentesi quadre dopo una consegna fallita del corriere locale; segui l’SMS o l’e-mail di BUFFALO oppure contatta l’assistenza clienti. | «Problema segnalato» |
| `Client Received` | Consegnato al tuo indirizzo o ritirato al banco. | «Consegnato» |

## IVA e dazi in Sudafrica, orari di consegna e modifiche dell’indirizzo

Secondo l’[assistenza BUFFALO](https://www.buffaloex.com/support.html) e [Chi è BUFFALO](https://www.buffaloex.com/about.html):

- **IVA e dazi di importazione:** quando il tracciamento mostra `Customs VAT and duty can be paid now, please pay in time`, IVA e dazi vanno saldati prima che la dogana sudafricana rilasci il pacco. I video IVA/dazi di BUFFALO mostrano il pagamento online o tramite bonifico e il caricamento della ricevuta, mentre il modulo richieste gestisce domande su pagamenti fiscali, documenti d’identità o codici importatore. Vedi [pacchi fermi in dogana](guide:customs).
- **Orari e tempi di consegna:** BUFFALO consegna in Sudafrica **dal lunedì al sabato, dalle 08:00 alle 17:00** (nessuna consegna la domenica né nei festivi). Una volta che il pacco risulta in transito, BUFFALO prevede la consegna in **1–5 giorni**, in base all’indirizzo del destinatario.
- **Modifiche a indirizzo, telefono o valore doganale:** secondo le FAQ di BUFFALO, valore dichiarato, quantità e codice SA **non** possono essere cambiati una volta entrato il pacco nel magazzino di transito, e indirizzo o telefono **non** possono essere modificati dopo l’arrivo del pacco in Sudafrica.

## Pacco BUFFALO in ritardo, rientrato in deposito o smarrito

- **Tracciamento fermo:** le FAQ di BUFFALO spiegano che il pacco è in transito e non ha ancora raggiunto il punto di controllo successivo; l’assistenza clienti può fornire dettagli. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Scansione serale `RETURNS`:** nelle scansioni di un corriere locale riportate dai servizi di tracciamento, `RETURNS` può apparire la sera dopo `SCAN VEHICLE DEPARTURE`: il pacco è rientrato al deposito dopo il giro ed è ripartito la mattina seguente. `Returned to BUFFALO warehouse[...]` segue invece una mancata consegna e richiede intervento.
- **Segnato `Client Received` ma non ricevuto:** BUFFALO consiglia di controllare prima con familiari, vicini e sicurezza del complesso o palazzo, poi contattare il servizio clienti Sudafrica (`support@buffaloex.com`). Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti BUFFALO

Contatti ufficiali pubblicati su [Contatti BUFFALO](https://www.buffaloex.com/contact.html):

- **Servizio clienti Sudafrica:** `support@buffaloex.com`, telefono `+27-10-510-9307`, WhatsApp `+27-67-758-6614`.
- **Servizio clienti Cina:** `service@bufflogistics.com`, linea `+86-400-921-9066`.
- **Modulo richieste e FAQ stati:** [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) e [buffaloex.com/support.html](https://www.buffaloex.com/support.html).
- **Uffici:** sede di Shanghai (Room 301, Building A, Lane 1156, Shenbin South Road, Minhang District, Shanghai), ufficio di Canton (Room 401, Building 3, Site 5, Greenland Airport International Center, Huadu District, Canton) e filiale di Johannesburg in Sudafrica.

> Fai attenzione agli SMS falsi che imitano la dogana sudafricana o BUFFALO con link simili. Verifica sempre eventuali importi IVA/dazi confrontando il tuo stato su [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) prima di pagare. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco BUFFALO con Peek

[Peek](/) rileva BUFFALO automaticamente come unico candidato quando inserisci un codice `BUFZA...YQ` di 17 caratteri (`BUFZA6482917305YQ` o `BUFZA4SK73918BUYQ`, inventati), e riconosce direttamente i link incollati `buffaloex.com/tracks.html?order=`, `buffaloex.com/track.html?order=` e `buffaloex.com/express/record?order=`. Se dovessi selezionare BUFFALO manualmente, tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco (cercare «Buffaloex», «Buffalo Express» o «BUFFALO International» lo trova ugualmente).

Peek non dispone di una ricerca diretta su BUFFALO e segue i pacchi BUFFALO tramite servizi di tracciamento. Legge le loro scansioni con le regole di BUFFALO per le sue diciture, così un `RETURNS` serale resta «In transito», `SCAN VEHICLE DEPARTURE` mostra «In consegna» e `Returned to BUFFALO warehouse[...]` mostra «Problema segnalato», elencando ogni scansione con la relativa fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «In consegna», «Problema segnalato», «Consegnato»).

Mentre Peek segue il pacco tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo un giorno senza nuovi aggiornamenti e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a BUFFALO.», con il link «Chiedi aiuto a BUFFALO» verso la pagina contatti di BUFFALO. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a BUFFALO.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può pagare dazi doganali SARS, inviare codici importatore né contattare BUFFALO per tuo conto.

## Domande frequenti sul tracciamento BUFFALO

### Com’è fatto un codice di tracciamento BUFFALO?

I codici di tracciamento BUFFALO dalla Cina al Sudafrica sono lunghi 17 caratteri, iniziano con `BUFZA` e terminano con `YQ` — per esempio `BUFZA6482917305YQ` o `BUFZA4SK73918BUYQ` (inventati).

### Quando consegna BUFFALO in Sudafrica?

Secondo [buffaloex.com/support.html](https://www.buffaloex.com/support.html), BUFFALO consegna dal lunedì al sabato dalle **08:00 alle 17:00** (esclusi domeniche e festivi), con consegna prevista in **1–5 giorni** una volta che il pacco risulta in transito.

### Perché il mio pacco BUFFALO è fermo alla dogana sudafricana?

Se compare `Customs VAT and duty can be paid now, please pay in time`, IVA e dazi doganali vanno pagati prima che la dogana sudafricana sblocchi il pacco. Il modulo richieste di BUFFALO risponde a domande sui pagamenti fiscali e su documenti o codici importatore.

### Come contatto il servizio clienti BUFFALO in Sudafrica?

Scrivi a `support@buffaloex.com` indicando il codice `BUFZA...YQ` oppure invia una richiesta da [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) (linea Cina `+86-400-921-9066`).

:::sources
- [BUFFALO International Logistics: sito ufficiale](https://www.buffaloex.com/) – rete logistica transfrontaliera Cina–Africa
- [BUFFALO: chi siamo](https://www.buffaloex.com/about.html) – operativa da aprile 2017, sede Shanghai, filiale Johannesburg, 35.000 m² di magazzini (6.500 m² doganale), 300 veicoli, integrazione doganale e bancaria
- [BUFFALO: supporto e FAQ tracciamento](https://www.buffaloex.com/support.html) – significato ufficiale stati (`Order Confirmed` a `Client Received`), orari lun–sab 08:00–17:00, transito 1–5 giorni e limiti modifica indirizzo
- [BUFFALO: contatti](https://www.buffaloex.com/contact.html) – `support@buffaloex.com`, `service@bufflogistics.com`, `+86-400-921-9066` e indirizzi uffici/magazzini (URL di aiuto collegato da Peek)
- [BUFFALO: tracciamento pacchi](https://www.buffaloex.com/tracks.html) – pagina ufficiale di ricerca
- [Universal Parcel Scraper: buffalo-ex](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/buffalo-ex) – regola di rilevamento `BUFZA...YQ`, regole link `buffaloex.com`, catalogo stati (`RETURNS`, `SCAN VEHICLE DEPARTURE`, `Returned to BUFFALO warehouse`), routing universale e URL di aiuto
:::
