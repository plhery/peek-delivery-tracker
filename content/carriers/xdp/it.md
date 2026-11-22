---
title: Tracciamento XDP: codici di spedizione, stati e consegna
description: Traccia un pacco XDP nel Regno Unito: dove trovare il numero di spedizione, perché XDP chiede il codice postale, significato degli stati e contatti.
slug: tracciamento-xdp
published: 2026-11-22
updated: 2026-11-22
---

XDP (XDP Express) è una rete britannica di pacchi e merci che consegna articoli grandi e pesanti per conto dei negozi: mobili, materassi, attrezzature da giardino e palestra, spesso con uno o due operatori. Traccia un pacco sulla [pagina di tracciamento di XDP](https://xdp.co.uk/track/) o nell’app XDP usando il numero di spedizione (*consignment number*) e il codice postale di consegna, oppure incolla il codice nella casella qui sopra. Il codice proviene dal negozio che spedisce, non da XDP.

## Numero di tracciamento XDP: il numero di spedizione

XDP lo chiama *consignment number*. La pagina di tracciamento lo dice chiaramente: lo ricevi dall’azienda che ti invia l’articolo tramite XDP, quindi cercalo nell’e-mail di spedizione del negozio o nella pagina dell’ordine. Gli SMS e le e-mail propri di XDP arrivano dopo, quando il pacco è già in viaggio verso di te.

XDP non pubblica un formato; i codici appaiono così:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, tre lettere e sei cifre: un numero di spedizione XDP. |
| `0ZDBKLM482917` | Lo stesso codice con uno `0` davanti, che può essere omesso. |

Il tracker XDP richiede anche il codice postale di consegna o ritiro. Un codice postale errato e un numero digitato male restituiscono la stessa risposta (`Consignment not found for consignment number`), quindi controllali entrambi.

Nessun altro corriere britannico usa la forma `ZDB`. Un codice [DPD UK](carrier:dpd-uk) ha 14 cifre e una lettera di vettura APC Overnight 22 cifre: se l’e-mail cita uno di essi, traccialo lì. Altro in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento XDP

Tracker, app e FAQ di XDP sono solo in inglese. Numeri di percorso e orari variano da pacco a pacco.

| Stato XDP | Cosa significa per te |
| --- | --- |
| `Order received` | Il negozio ha inviato a XDP i dati dell’ordine, ma XDP non ha ancora l’articolo. |
| `At Collection Depot` | XDP ha il tuo articolo ed è diretto al deposito di consegna. |
| `At Delivery Depot` | Presso il deposito che effettuerà la consegna. |
| `Allocated to Delivery Vehicle - Route 03` | Assegnato a un giro di consegna. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Consegna prevista oggi in questa fascia di due ore. |
| `Next stop: On time` | Sei la prossima fermata del corriere. XDP indica `On time`, `Late by …` o `Early by …` rispetto alla fascia; Peek premette `Next stop:`. |
| `Delivered` | Consegnato. |
| `Carded` | L’autista ha provato, ma non c’era nessuno per ritirarlo. |
| `Consignment Split` | Il deposito ha ricevuto solo una parte del tuo ordine dal negozio. |
| `Check Address` | L’autista non trova l’indirizzo e ha bisogno di ulteriori informazioni. |
| `Service Fail` | Un imprevisto fuori dal controllo di XDP (guasto, traffico o maltempo) lo ha ritardato. |
| `Return` | Tornato al mittente: contatta il negozio. |

L’app XDP usa formulazioni leggermente diverse (`Delivery scheduled`). Dopo `Consignment Split`, XDP indica che gli articoli rimanenti seguono nei giorni successivi. `RETURN LABEL CREATED` avvia il reso al mittente, mentre `Damaged` segnala un probabile danno (vedi sotto).

## Consegna XDP: fasce orarie, consegne mancate e ritiro

XDP invia una fascia di consegna di due ore il giorno stesso una volta caricato il furgone, di solito entro mezzogiorno (ora del Regno Unito). Nei periodi intensi un deposito può pianificarla 24 ore o più prima. Segue un messaggio «You Are Next» via SMS, e-mail e app. Per le consegne con due operatori (2Man), XDP ti contatta prima per la data e invia la fascia di due ore il giorno stesso. Con il servizio 1+ l’autista può portare l’articolo nella stanza che scegli al piano terra, previa firma di una liberatoria inviata prima da XDP; con 2Man, fino a un piano di scale interne (gli attrezzi da palestra solo al piano terra).

Nell’app XDP puoi:

- scegliere **Change Delivery Date** per riprogrammare la consegna o, in certi casi, selezionare una data;
- aggiungere istruzioni di consegna, come lasciare l’articolo a un vicino o in portico, capanno, garage o dietro un cancello;
- lasciare note per l’autista e vedere la prova di consegna.

XDP avvisa che chiedendo «Left Safe» ti assumi la piena responsabilità dell’articolo dal momento della consegna.

**Consegne mancate.** `Carded` indica che l’autista non ha trovato nessuno. XDP dichiara tre nuovi tentativi di consegna prima del reso al mittente. Se non è stato lasciato alcun avviso, l’autista non può ripassare in giornata: riprogramma con Change Delivery Date.

**Nessun ritiro in deposito.** L’app di XDP precisa che non è previsto il ritiro presso i depositi o hub. Le FAQ rimandano al negozio se desideri ritirare.

## Pacco XDP non consegnato, in ritardo o danneggiato

XDP lavora per il mittente, non per te. Per qualsiasi problema consiglia di controllare prima il tracciamento nell’app o sul sito e poi contattare il fornitore (il negozio), titolare del contratto e unico abilitato ai reclami.

**In ritardo o fermo.** XDP non garantisce consegne il giorno successivo, tanto meno nei picchi di festività, Black Friday e Cyber Monday. Gli autisti bloccati nel traffico potrebbero non aggiornare sempre la fascia oraria. Vedi [tracciamento fermo](guide:tracking-not-updating).

**Danneggiato.** XDP invita ad accettare la consegna anziché rifiutarla e avvisare subito il mittente. Annota il danno sulla prova di consegna al momento della firma: la guida procedurale XDP per i mittenti specifica che il reclamo viene respinto se la firma non indica riserve, e note come «unchecked» o «unseen» non valgono.

**Smarrito.** Le scadenze del mittente con XDP sono strette: 33 giorni dalla spedizione per perdita totale o parziale e 7 giorni dalla consegna per danni, fine settimana inclusi. Avvisa subito il negozio. Se risulta `Delivered` e non hai ricevuto nulla, vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Restituito.** Dopo `Return`, XDP consiglia di contattare il fornitore.

## Contatti e assistenza clienti XDP

Canali XDP per i destinatari:

- **Modulo Delivery Query** sulla [pagina contatti XDP](https://xdp.co.uk/contact/). Il modulo «New Business Enquiry» è solo commerciale.
- **E-mail:** trackingupdates@xdp.co.uk.
- **Live chat** su `xdp.co.uk` e nell’app XDP; XDP non pubblica orari.
- **Modulo contatti in app**, che richiede nome, codice postale, e-mail e, facoltativamente, numero di spedizione.

Il sito XDP non pubblica numeri di telefono. L’app accenna a chiamate a tariffa maggiorata di 7 pence al minuto senza indicare alcun numero; i numeri riportati su altri siti non sono pubblicati da XDP.

## Tracciare pacchi XDP con Peek

[Peek](/) riconosce subito un codice `ZDB` (con o senza `0` iniziale) come XDP. Funzionano anche i link di `xdp.co.uk` (`/track/` seguito dal codice o `track.php?c=`), ma un eventuale codice postale nel link non viene acquisito.

Peek offre per XDP un campo facoltativo «Codice postale di consegna». Senza codice postale XDP non viene affatto interrogato e il pacco viene seguito solo tramite [servizi di tracciamento universali](guide:universal-tracker). Con il codice postale, Peek legge direttamente la pagina XDP e lo trasmette a XDP e, se necessario, ai servizi di tracciamento. XDP risponde a un codice postale errato come a un numero sconosciuto: controlla entrambi.

Gli orari di XDP non hanno fuso orario: Peek li legge sull’ora del Regno Unito e li converte nel tuo fuso («Orari nel tuo fuso orario:»). Elenca la cronologia XDP (scartando avvisi e-mail/SMS e scansioni duplicate di più colli) e può mostrare il peso, escludendo codici di deposito o autista, azienda mittente, riferimenti e numero di pezzi. Non ci sono data prevista, località per scansione né campo fascia separato (tranne dentro testi come `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Con il codice postale, mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente XDP fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora del Regno Unito nel giorno in cui passa in consegna, finché il tracker XDP risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), ogni ora. Senza codice postale, tramite i soli servizi universali, controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti il giorno della consegna se il pacco è aperto in Peek o le notifiche sono attive. In entrambi i casi, dopo sette giorni senza novità su «Etichetta creata», controlla ogni 6 ore. Dopo quattro giorni senza nuove scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a XDP.», con il link «Chiedi aiuto a XDP» alla pagina contatti. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento XDP

### Perché il mio tracciamento XDP non funziona?

Di solito per il codice postale: il tracker XDP richiede numero di spedizione e codice postale di consegna o ritiro, e un codice sbagliato mostra «Consignment not found». Verificali entrambi nell’e-mail del negozio.

### Quanto impiega XDP a consegnare?

XDP indica ai mittenti 1–3 giorni per il servizio 1Man e 3–5 giorni per 1+ e 2Man, salvo trasporto marittimo verso isole. Il giorno della consegna include una fascia di due ore.

### Posso ritirare il pacco in un deposito XDP?

No. L’app XDP precisa che non è offerto il ritiro presso depositi o hub, e le FAQ rimandano al negozio. Puoi riprogrammare la consegna o chiedere la consegna a un vicino.

### XDP è un corriere reale?

Sì. X D P Limited è registrata presso Companies House dal 1995, con sede a Curdworth vicino a Sutton Coldfield, e consegna nella Gran Bretagna continentale, in Irlanda del Nord, nelle Isole del Canale e sull’Isola di Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – numero di spedizione dal mittente, codice postale, avvisi del tracker
- [XDP: FAQs](https://xdp.co.uk/faqs/) – significato degli stati, contattare il fornitore, ritiro in deposito, riprogrammazione in app
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – modulo Delivery Query, e-mail
- [XDP: About Us](https://xdp.co.uk/about-us/) – fondata nel 1995, copertura, fascia di due ore, prova di consegna
- [XDP: Home](https://xdp.co.uk/) – merci voluminose e fuori formato, servizi, app
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1–3 giorni, notifiche, telefono proxy
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3–5 giorni, stanza a scelta, You Are Next, live chat ed e-mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – data concordata, fascia di due ore
- [XDP: App (web version)](https://app.xdp.co.uk/) – fascia oraria, tre tentativi, Change Delivery Date, left safe, nessun ritiro in deposito, danni, resi, chiamate, modulo contatti
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – nessuna garanzia giorno successivo, periodi di picco, scadenze reclami
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 e 7 giorni, annotare i danni sulla prova di consegna
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – codice postale e avvisi spedizione non trovata
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – registrazione, sede
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – l’app XDP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
