---
title: Tracciamento Whistl: chi consegna il pacco e cosa fare
description: Traccia un pacco Whistl (Parcelhub): dove trovare il riferimento, significato degli stati, quale corriere effettua la consegna finale e contatti.
slug: tracciamento-whistl
published: 2026-11-22
updated: 2026-11-22
---

Whistl, in passato TNT Post UK, è un operatore britannico di consegne che spedisce pacchi per i negozi online e li affida a corrieri come [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) o [DPD UK](carrier:dpd-uk) per l’ultimo miglio. Traccialo sul tracker Parcelhub di Whistl con il riferimento dell’e-mail di spedizione del negozio oppure incolla il link nel campo sopra. Dopo il passaggio di mano, il codice e il tracciamento del corriere finale danno di solito più dettagli.

## Numero di tracciamento Whistl: dove trovarlo

Whistl non pubblica un formato fisso. Le sue guide indicano che il tracker cerca «per UPI (di norma il codice a barre del collo) o Consignment Number»: l’UPI identifica un singolo pacco, mentre il numero di spedizione raggruppa tutti i colli spediti insieme. I riferimenti cambiano per negozio e corriere, da una lettera seguita da molte cifre (come `L48291736482917364`) fino al codice a barre del corriere finale.

Lo trovi nell’e-mail di spedizione del negozio, spesso come link al tracker Whistl. Per le spedizioni con tracciamento completo (*fully tracked*), il corriere che consegna ti invia anche e-mail e SMS il giorno della consegna, con il proprio marchio e codice.

Il tracker chiede «Tracking Reference» e un codice postale facoltativo, poi mostra per ogni pacco «Tracking Number:», le scansioni e i campi «Carrier:» e «Service:».

Un riferimento può avere la forma di un altro corriere, come il codice a barre del corriere finale (caratteri inventati):

| Formato (inventato) | A chi assomiglia |
| --- | --- |
| `L48291736482917364` | `L` e 17 cifre: riferimento Whistl che Peek verifica direttamente |
| `RR482917367GB` | Royal Mail (due lettere, nove cifre, `GB`) |
| `H00KLM0048291708` | Codice a barre Evri di 16 caratteri, vedi [tracciamento Evri](carrier:evri) |
| `48291736482917` | 14 cifre: DPD UK tra diversi altri, vedi [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Altro su come leggere un codice in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Whistl

Il tracker di Whistl, solo in inglese, converte le scansioni di ciascun corriere negli stessi «eventi normalizzati»; i pacchi con tracciamento completo riportano anche i commenti del corriere. In ordine abituale:

| Whistl indica | Cosa significa per te |
| --- | --- |
| `Shipment Created` | Il negozio ha prenotato la spedizione. Non si è ancora mossa. |
| `Item receipted and processed by Whistl` | Whistl l’ha ricevuta e lavorata in uno dei propri depositi. |
| `Handed over to Delivery Carrier` | Il corriere indicato sulla pagina si occupa ora della consegna. |
| `Handed over to Royal Mail` | Royal Mail l’ha presa in carico per l’ultimo miglio. |
| `Arrived At Depot` | Arrivato in un deposito lungo il percorso. |
| `Shipment is Out for Delivery` | In consegna oggi. |
| `DELIVERED - Left as authorised` | Lasciato dove avevi autorizzato, senza firma. |
| `Left with Neighbour` | Consegnato a un vicino. |
| `Delivered col. point` | In attesa presso un punto di ritiro. |
| `No-one to receive` | Non c’era nessuno: cerca un avviso cartaceo o un messaggio del corriere. |
| `Held by customs` | Fermo in [dogana](guide:customs). |
| `Return - Delivered` | Rientrato e riconsegnato al mittente. |

Tre aspetti generano spesso dubbi:

- **`Shipment Created` da solo** significa solo prenotato. Se non segue nulla per giorni, il negozio potrebbe non averlo ancora affidato.
- **`Handed over to …`** è spesso l’ultima riga mostrata da Whistl. Per gli invii a tracciamento parziale (*part-tracked*), Whistl dichiara che non è possibile tracciarli dopo il passaggio: segui il pacco col numero del corriere finale.
- **Problemi** come `Delayed`, `Bad Address`, `Refused`, `Lost` o `Item damaged` segnalano un disguido; `Shipment Deleted` indica che il mittente ha annullato l’invio. Righe come `Item Weight Changed` o `Label reprinted` sono semplici note e non spostano il pacco.

## Consegne Whistl e mancati recapiti

A portare il pacco è il corriere finale, non Whistl, e valgono le sue regole. Dopo una mancata consegna, il corriere lascia un avviso cartaceo o digitale con chi è passato e quando ritenterà.

La guida corrieri Parcelhub di Whistl (agosto 2025) riporta per corriere:

| Corriere | Tentativi e passaggio successivo |
| --- | --- |
| Royal Mail | 1 tentativo e avviso «something for you»; ritiro in ufficio postale o di consegna, oppure reso dopo 18 giorni. Dal maggio 2023, se non entra in cassetta o richiede firma e non ci sei, viene lasciato al sicuro o da un vicino. |
| Evri | Fino a tre tentativi; preferenze nell’app Evri; fascia di 2 ore via e-mail o SMS. |
| DPD | Avviso più SMS o e-mail per scegliere vicino, luogo sicuro, nuova data o punto ritiro; fascia di un’ora. |
| Yodel | Fino a tre tentativi; indirizzi insulari possono averne solo uno prima del ritiro in ufficio postale. |
| [Amazon Shipping](carrier:amazon-shipping) | Fino a tre tentativi in 3 giorni consecutivi. |
| DHL Parcel UK | Fino a 2 tentativi. |
| Parcelforce | 1 tentativo, con opzioni di deposito sicuro e deviazione. |
| [UPS](carrier:ups) | 1 tentativo, poi avviso digitale con istruzioni di riconsegna. |

Gli invii esteri a tracciamento parziale passano a partner internazionali, secondo le regole della posta locale. Whistl non gestisce punti ritiro propri: `Delivered col. point` indica il negozio o armadietto del corriere finale. Un pacco rifiutato torna al mittente senza nuova consegna.

Risulta consegnato ma non c’è? Le guide Whistl suggeriscono di controllare prima avviso, luoghi sicuri e vicini. Poi vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Pacco Whistl non consegnato, in ritardo o perso

La pagina contatti di Whistl invita i consumatori a «contattare il rivenditore presso cui è stato effettuato l’acquisto». Il negozio è titolare del contratto, apre una segnalazione «Where Is My Item» presso Whistl e gestisce eventuali reclami.

Secondo le guide Whistl:

- Un pacco britannico non consegnabile rientra al mittente entro 5 giorni lavorativi dall’ingresso nella rete Whistl.
- Per gli articoli a tracciamento parziale non è previsto rimborso per ritardi e Whistl non può intervenire dopo la consegna al partner in caso di mancato recapito.
- Le scadenze di reclamo del mittente dipendono dal corriere: ad esempio entro 28 giorni dalla spedizione con Evri, o per Royal Mail un’attesa minima di 10 giorni di calendario prima di reclamare ed entro 80 giorni. Avvisa presto il negozio.

Se il tracciamento si ferma, parti da [perché il tracciamento si ferma](guide:tracking-not-updating).

## Numero e assistenza clienti Whistl

Recapiti indicati nella pagina contatti di Whistl:

- **Per il tuo pacco:** il negozio dove hai acquistato.
- **Tracciamento:** il pulsante «Whistl Tracking Portal» nella pagina contatti.
- **Richieste generali:** `01628 702 965`, dalle 8:30 alle 17:30 ora del Regno Unito. Linea dedicata ai clienti aziendali Whistl.
- **E-mail Customer Services:** `packagescustomerservices@whistl.co.uk`, che «non può assistere il pubblico per richieste di tracciamento relative a singoli articoli».
- **Sede centrale:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

In Grecia, Cipro, Lussemburgo e Malta Whistl non ha uffici né linee telefoniche. Rivolgiti al negozio e, dopo il passaggio, al corriere o alla posta locale indicati nel tracker.

## Tracciare un pacco Whistl con Peek

[Peek](/) segue oltre 4.500 corrieri. Per un riferimento di 18 caratteri con `L` seguita da 17 cifre, Peek interroga direttamente Whistl e mostra «Whistl ha questo pacco.» non appena Whistl lo conosce. Gli altri riferimenti Whistl non hanno un formato proprio: incolla invece un link Whistl (`web.parcelhub.net` o `despatch.whistl.co.uk` con `/Tracking/reference/…`, oppure `trackmyitem.whistl.co.uk` con `/tracking/…`), che identifica subito Whistl («Corriere rilevato»), o aggiungi il riferimento e seleziona «Whistl» con «Cambia». Non serve il codice postale. Un riferimento con la forma di un altro corriere (come un codice Royal Mail in `…GB`) traccia quell’altro corriere, non Whistl.

Peek legge il tracker Parcelhub di Whistl. I suoi orari non indicano il fuso: Peek li legge sull’ora locale del luogo della scansione (ora britannica nel Regno Unito) e li mostra nel tuo fuso («Orari nel tuo fuso orario:» seguito dal nome). Mostra stato, servizio, il nome di chi ha firmato alla consegna, il numero canonico Whistl se diverso dal riferimento e il corriere finale con il suo codice. Non sono presenti giorno di consegna previsto, peso né punto di ritiro. Le diciture mantengono l’inglese di Whistl, tranne `Delivered`, `In transit` e `Returned to sender`, tradotti da Peek.

Quando Whistl indica Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost), UPS o [FedEx](carrier:fedex), Peek può seguire il pacco presso quel corriere. Se compare solo Evri, DPD o Yodel non prosegue automaticamente: inserisci il codice proprio del corriere, o usa il sito di Yodel, che Peek non traccia. Un riferimento relativo a più colli non è tracciato: inserisci ogni singolo numero.

Mentre hai il pacco aperto in Peek — o il giorno della consegna con le notifiche attive — Peek interroga direttamente Whistl fino a ogni 10 minuti, e fino a ogni 2 minuti (o ogni 5 minuti se deve aprire il tracker Whistl in un browser) dalle 6:00 nell’ora dell’ultima scansione (ora del Regno Unito nel Regno Unito) il giorno in cui Whistl segna «In consegna», finché il tracker risponde; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in viaggio, fuori da dogana o problemi segnalati, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Whistl.», con il link «Chiedi aiuto a Whistl» alla pagina contatti. Peek non può contattare Whistl né il negozio per te.

## Domande frequenti su Whistl

### Whistl fa parte di Royal Mail?

No. Whistl UK Ltd è un’azienda distinta, già TNT Post UK. Affida molti invii a Royal Mail per l’ultimo miglio, motivo per cui compare `Handed over to Royal Mail`.

### Chi consegna i pacchi Whistl?

Di solito un altro corriere: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce o UPS nel Regno Unito, e partner locali all’estero.

### Il tracciamento Whistl è legittimo?

Sì. Il tracciamento ufficiale Whistl opera su `web.parcelhub.net`, `despatch.whistl.co.uk` e `trackmyitem.whistl.co.uk`, e Parcelhub fa parte del gruppo Whistl.

### Perché il mio numero Whistl non funziona?

Il tracker risponde «Unable to find shipment» quando non conosce il riferimento. Verificalo con il negozio oppure traccia il codice del corriere finale ricevuto via SMS o e-mail.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – consumatori invitati a rivolgersi al venditore, telefono e orari, e-mail Customer Services, portale tracking, sede
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, servizi business, Parcelhub parte del gruppo Whistl
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI e spedizione, eventi normalizzati, SMS ed e-mail dei corrieri, avviso di mancata consegna, rifiuto, smarrimento, 5 giorni lavorativi, reclami mittente
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – passaggio a Royal Mail e partner, 18 giorni, nessun indennizzo per ritardo, link tracking, e-mail brandizzate
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – corrieri usati, tentativi e opzioni, scadenze reclamo mittente
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portali tracking, ricerca per UPI o spedizione, nessuna assistenza dopo il passaggio
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – campi del modulo, struttura risultati, solo inglese
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – stesso tracker sotto nome Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – cambio nome da TNT Post UK, ultimo miglio Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – campi letti da Peek, stati registrati, rilevamento numeri e frequenza controlli
:::
