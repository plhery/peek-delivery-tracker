---
title: Tracciamento DHL eCommerce: codici GM, stati e contatti
description: Traccia un pacco DHL eCommerce (ex DHL Global Mail): formato GM, stati spiegati, consegna con USPS o posta locale e contatti.
slug: tracciamento-dhl-ecommerce
published: 2026-11-23
updated: 2026-11-23
---

DHL eCommerce, in precedenza DHL Global Mail, è la divisione pacchi di DHL dedicata ai negozi online. Smista il pacco nei propri centri di distribuzione e lo affida poi a un corriere nel tuo paese: [USPS](carrier:usps) negli Stati Uniti, altrove la posta locale o un altro vettore. Puoi tracciarlo con il codice ricevuto nell’e-mail del negozio sul sito Webtrack di DHL eCommerce oppure incollandolo qui sopra.

## Formato del numero di tracciamento DHL eCommerce

I codici DHL eCommerce hanno almeno dieci caratteri e di solito iniziano con `GM`. Webtrack accetta fino a 10 codici alla volta, separati da virgole. Nessun codice? Chiedi al negozio.

| Formato (inventato) | Che cos’è |
| --- | --- |
| `GM4829103756182947` | `GM` e da 16 a 18 cifre: il formato abituale di DHL eCommerce. |
| `GM4829103756` | `GM` e da 8 a 15 cifre: un numero `GM` più corto. |
| `9261482910375618294751` | 22 cifre con inizio `9261` o `9361`: il codice USPS assegnato a un pacco statunitense dopo il passaggio a USPS. |
| `7394028156342819` | 16 o 17 cifre: un altro formato DHL eCommerce. |
| `0031482910375618294751829364728` | `0031` e 27 cifre: un altro formato DHL eCommerce. |

La riga «Delivery by» su Webtrack indica il vettore dell’ultimo miglio, il cui codice può comparire in seguito.

Codici simili: 16 cifre possono appartenere anche a TNT, Evri, Canada Post o Correos Express, e un `9261` o `9361` a USPS. Un codice di 14 cifre può essere di [DHL eCommerce UK](carrier:dhl-ecommerce-uk), rete separata con tracciamento proprio; un codice `JVGL` di [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl); [DHL Express](carrier:dhl-express) e [DHL Paket](carrier:dhl) usano formati diversi. Altro in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento DHL eCommerce spiegati

Webtrack mostra uno stato sintetico (`En Route`, `Delivered`, `Exception`…) sopra una cronologia («Tracking History») in maiuscolo. Le scansioni più frequenti in ordine:

| DHL eCommerce indica | Cosa significa per te |
| --- | --- |
| `LABEL CREATED` | Il negozio ha creato l’etichetta. DHL eCommerce non ha ancora il pacco. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | In viaggio verso DHL oppure ancora in attesa presso il negozio. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce ha preso in carico il pacco. |
| `PROCESSED` | Smistato nel centro di distribuzione. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Ha lasciato il primo centro DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Arrivato al centro DHL eCommerce nella tua area. |
| `CUSTOMS CLEARANCE` | In dogana. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Consegnato al corriere finale. Per pacchi negli USA gli aggiornamenti possono fermarsi fino a 3 giorni. |
| `OUT FOR DELIVERY` | Oggi con l’autista o il portalettere. |
| `DELIVERY ATTEMPT FAILED` | Nessuno ha potuto ritirarlo. Cerca un avviso del corriere locale. |
| `DELIVERED` | Consegnato. |
| `RETURNED TO SENDER` | Sta tornando al negozio. |

I pacchi USA mostrano anche le scansioni USPS dopo il passaggio, come `ARRIVAL AT POST OFFICE`.

Uno stato crea spesso equivoci: `Picked up at parcelshop` indica che il mittente lo ha consegnato in un punto di raccolta, non che sia pronto al ritiro per te.

## Come consegna DHL eCommerce: USPS, poste locali e assenze

Negli USA, le FAQ Webtrack spiegano che USPS consegna all’orario abituale della posta. Per i pacchi internazionali subentra il gestore locale («local delivery service provider»), cioè la posta locale o un altro corriere.

Avvisi, sportelli di ritiro, giacenza e riconsegna seguono dunque le regole di quel vettore finale. DHL consiglia di controllare la cassetta postale per il pacco o un avviso e di dare eventuali istruzioni a USPS o al corriere locale.

Cambio indirizzo: secondo le FAQ Webtrack, una volta partito dal negozio DHL eCommerce non può modificarlo; occorre chiedere al negozio.

## Pacco DHL eCommerce in ritardo, smarrito o restituito

**Ancora nessuna scansione.** Secondo le FAQ Webtrack gli eventi compaiono di norma entro 24–48 ore; se dopo 48 ore non c’è nulla, contatta il negozio. Quando Webtrack non trova il codice, consiglia di ricontrollarlo e poi contattare il venditore.

**Nessun aggiornamento per giorni.** Le FAQ citano meteo, festività, dogana, scioperi e altri fattori esterni. Negli USA la stessa scansione di passaggio prevede 1–3 giorni di attesa. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Non arrivato.** Se il pacco non arriva entro la finestra prevista, le FAQ Webtrack consigliano di scrivere al negozio. Nell’UE Your Europe ricorda che il venditore risponde dei danni fino alla consegna e deve consegnare entro 30 giorni salvo diverso accordo; altrimenti sollecitalo con un termine aggiuntivo e poi puoi recedere e chiedere il rimborso.

**Dogana.** `CUSTOMS CLEARANCE` indica che il pacco è in dogana. Vedi [pacchi fermi in dogana](guide:customs) per eventuali oneri.

**Restituito.** `RETURNED TO SENDER` significa che torna al negozio: chiedi rimborso o rispedizione.

## Assistenza clienti e numero DHL eCommerce

Rivolgiti prima al negozio, come indicato nelle FAQ Webtrack. La pagina «Contact Customer Service» di Webtrack elenca una scheda per paese:

- **Stati Uniti e Canada** (spedizioni dagli USA): +1 317-554-5191, lun–ven 8:00–20:00 EST (la scheda Canada indica 8:00–21:00 ET).
- **Germania:** rimanda al [servizio clienti DHL Germania](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), lun–dom 7:00–20:00 (ora tedesca).
- **Irlanda:** 818 370050 (dall’Irlanda), lun–ven 8:00–18:00 (ora irlandese).
- **Australia:** +61 37 0356818, lun–ven 8:00–18:00.
- **Austria e Svizzera:** +49 228 92 95 95 95, lun–ven 9:00–18:00 (dalle 8:00 in Austria, ora dell’Europa centrale).
- **Regno Unito:** indica la linea DHL eCommerce UK, 024 7693 7770. Vedi la pagina [DHL eCommerce UK](carrier:dhl-ecommerce-uk).

Il modulo «Contact Support» di Webtrack prevede una risposta entro 24–72 ore. Il costo delle chiamate non è indicato.

## Tracciare un pacco DHL eCommerce con Peek

[Peek](/) riconosce subito DHL eCommerce («Corriere rilevato») per `GM` e da 16 a 18 cifre. Per un codice `GM` più breve (8–15 cifre) interroga insieme DHL eCommerce, Colis Privé e Aramex, e assegna il pacco a DHL eCommerce se lo conosce. Sui formati condivisi (16 o 17 cifre, `9261`/`9361`) interroga insieme i corrieri probabili (es. TNT per 16 cifre; per `9261`/`9361`, Austrian Post insieme a DHL eCommerce e, solo se nessuno dei due lo conosce, servizi di tracciamento, la pagina di USPS e la pagina globale DHL). Se solo DHL eCommerce lo conosce, Peek lo segnala; se lo riconoscono più marchi, li elenca con «Conosce questo numero» così puoi scegliere. Con «Cambia» (o «Cambia corriere») accetta per DHL eCommerce qualsiasi codice di almeno 10 lettere e cifre. Funzionano anche link `webtrack.dhlglobalmail.com`, `ecommerceportal.dhl.com` e `dhl.com` quando il codice corrisponde a DHL eCommerce, tranne un codice `GM` breve.

Webtrack viene interrogato per primo. Se non elenca il pacco, Peek apre la pagina globale su `dhl.com` tramite browser, più lenta e soggetta a blocchi automatici.

Peek mostra stato, ogni scansione con città e paese sulla mappa, giorno previsto quando DHL lo fornisce, mittente e orario di consegna; da Webtrack anche peso e corriere finale. Esclude indirizzo del destinatario e riferimento cliente. Le diciture mantengono l’inglese americano di DHL e ogni consegna appare come «Consegnato». Gli orari sono letti sull’orologio locale di ciascuna scansione (una riga senza luogo prende quello di una riga vicina) e convertiti nel tuo fuso orario («Orari nel tuo fuso orario:»).

Quando Webtrack indica USPS, il pacco passa su USPS con il suo numero appena USPS mostra una scansione recente almeno quanto l’ultima di DHL eCommerce, mostrando «Consegna con USPS» (oppure «Annunciato a USPS, non ancora ricevuto» finché USPS non registra scansioni). Peek fa lo stesso con gli altri corrieri finali indicati da Webtrack, come La Poste, Austrian Post, Poczta Polska, Correos o Poste Italiane. I pacchi letti dalla pagina globale DHL non passano a una posta locale. Finché il pacco è aperto in Peek, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti e, il giorno in cui entra «In consegna», dalle 6:00 secondo l’orologio dell’ultima scansione, ogni 2 minuti finché Webtrack risponde (ogni 5 minuti quando l’ultimo controllo ha aperto la pagina di `dhl.com` in un browser). Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DHL eCommerce.» e «Chiedi aiuto a DHL eCommerce». Peek segue oltre 4.500 corrieri, ma non può contattare DHL eCommerce né il negozio per te.

## Domande sul tracciamento DHL eCommerce

### DHL eCommerce è la stessa cosa di DHL Express?

No. DHL eCommerce è la divisione pacchi di DHL per i negozi online e di solito affida la consegna finale alla posta locale o a un altro corriere. DHL Express ha un tracciamento proprio e Peek li segue separatamente.

### DHL eCommerce consegna tramite USPS?

Negli Stati Uniti sì: secondo le FAQ Webtrack è USPS a effettuare la consegna finale. All’estero consegna la posta locale o un altro corriere.

### Perché non ci sono ancora dati di tracciamento DHL eCommerce?

Secondo le FAQ Webtrack gli eventi compaiono in genere entro 24–48 ore. Se dopo 48 ore non vedi nulla, contatta il negozio che ha spedito il pacco.

### DHL eCommerce è affidabile?

Sì. È la divisione pacchi e-commerce di DHL, precedentemente chiamata DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – sito di tracciamento, stati, riquadro dettagli, fino a 10 codici, consigli se non trovato, modulo contatti, aggiornamenti e-mail
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – schede paese, telefoni, orari, FAQ su 24–48 ore, consegna USPS, ritardi, pacchi smarriti, cambio indirizzo, istruzioni
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilità del venditore fino alla consegna, 30 giorni, proroga, rimborso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ex DHL Global Mail, formati di numero e codice USPS, diciture e fasi, tracciamento separato di DHL Express, cosa legge e mostra Peek
:::
