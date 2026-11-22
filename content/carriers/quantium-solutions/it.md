---
title: Tracciamento Quantium Solutions: codici, stati e supporto
description: Traccia un pacco Quantium Solutions: formati di codice supportati, significato di ogni stato, servizi di consegna, dogana e contatti dell’assistenza.
slug: tracciamento-quantium-solutions
published: 2026-11-22
updated: 2026-11-22
---

Quantium Solutions è una società di logistica e-commerce presente in 10 paesi dell’area Asia-Pacifico. Trasporta ordini online verso la regione, al suo interno e in uscita verso il resto del mondo. Puoi tracciare un pacco sulla pagina [Track Your Shipments](https://track.quantiumworldwide.com/) di Quantium oppure incollando il codice nella casella qui sopra. Il codice viene fornito dal negozio nell’e-mail di spedizione o nella pagina dell’ordine.

## Numero di tracciamento Quantium Solutions: com’è fatto

Il tracker Quantium richiede un «Tracking/Reference number» e ne accetta diversi insieme, uno per riga. Il codice di Peek riconosce due formati di codice Quantium, nessuno dei quali esclusivo di Quantium. Con cifre inventate:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `4829173654` | Dieci cifre: nel codice di Peek, il numero Quantium per una spedizione in Malesia. Dieci cifre corrispondono a vari corrieri, tra cui DHL, quindi Peek non può indicare Quantium dal formato da solo. |
| `UQ482917367TH` | `UQ`, nove cifre e `TH`: nel codice di Peek, il numero Quantium per una spedizione in Thailandia. Ha il formato postale internazionale, perciò Peek indica Thailand Post. |
| `RA482917367SG` | Due lettere, nove cifre e `SG`: un codice Singapore Post. Il codice di Peek segnala che Quantium gestisce alcuni pacchi sotto numeri SingPost. Peek indica Singapore Post. |

La ricerca Quantium di Peek accetta solo questi due formati (dieci cifre o formato postale S10). Spazi e minuscole non contano. Per un riferimento di forma diversa usa direttamente il tracker Quantium. Non trovi il codice? Leggi [come trovare un numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento Quantium Solutions spiegati

Ogni riga sul tracker Quantium ha un passaggio principale e spesso una nota con il nome del passaggio usato dal partner che gestisce il pacco. Peek mostra il passaggio principale seguito dalla nota dopo i due punti quando aggiunge dettagli. Le righe riconosciute da Peek, nell’ordine abituale:

| Quantium scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipment created` | Il negozio ha prenotato la spedizione. Quantium non ha ancora il pacco. | «Etichetta creata» |
| `Shipment received` | Quantium ha il pacco. | «Preso in carico» |
| `Shipment under processing at origin hub` | In smistamento presso un hub nel paese del mittente. | «In transito» |
| `Shipment despatched to transshipment hub` | In viaggio verso un hub di trasbordo per la tratta successiva. | «In transito» |
| `DEPART ORIGIN` | Ha lasciato il punto di partenza. | «In transito» |
| `Shipment received at transshipment hub: Arrive Hub` | All’hub di trasbordo. | «In transito» |
| `Shipment despatched to overseas: Depart Hub` | Ha lasciato l’hub verso il tuo paese. | «In transito» |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | In attesa del volo, poi in volo verso il tuo paese. | «In transito» |
| `Shipment arrived at destination port: Arrive Destination` | Arrivato nel tuo paese. | «In transito» |
| `IN TRANSIT: Customs Clearance Begins` | La dogana del tuo paese ha iniziato lo sdoganamento. | «In dogana» |
| `Shipment released from customs/ government check point: Customs Cleared` | La dogana ha sbloccato il pacco. | «In transito» |
| `Shipment received at delivery branch: Handed Over for Delivery` | È arrivato alla filiale locale di consegna. | «In transito» |
| `IN TRANSIT` | In movimento. Le scansioni di smistamento del partner nel tuo paese arrivano così. | «In transito» |
| `Shipment out for delivery` | In consegna. | «In consegna» |
| `Shipment delivered` | Consegnato. Quando la nota indica chi ha firmato o ritirato il pacco, Peek legge anche quel nome. | «Consegnato» |

Una riga sconosciuta a Peek appare come la scrive Quantium, e Peek ricava lo stato dalle parole quando riesce. Per un codice sconosciuto il tracker Quantium risponde «No record!». Altro in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

**Orari.** Il tracker Quantium indica data e ora senza fuso orario, sull’orologio di chi ha effettuato la scansione: il paese di partenza all’inizio e poi il partner nel tuo paese. Due righe di paesi diversi non si possono confrontare ora per ora.

## Chi consegna i pacchi Quantium Solutions

Quantium propone ai negozi quattro servizi transfrontalieri:

| Servizio | Tracciamento | Canale di consegna | Tempi di transito | Dazi e imposte |
| --- | --- | --- | --- | --- |
| Q-Simple | nessuno | postale | 4–17 giorni | a carico del destinatario (DDU) |
| Q-Smart | tracciato | postale | 4–10 giorni | a carico del destinatario (DDU) |
| Q-Speed | tracciato, prioritario | postale | 3–7 giorni | a carico del destinatario (DDU) |
| Q-Sure | tracciato | commerciale | 2–5 giorni | a carico del mittente o del destinatario (DDP o DDU) |

Peek non mostra quale servizio usi il tuo pacco: chiedi al negozio.

**In Asia.** I servizi last-mile di Quantium offrono consegna a domicilio, ritiro presso uffici postali, locker e oltre 3.000 punti di ritiro in Asia, con pagamento alla consegna in contanti o elettronico. La consegna Regular richiede 2–4 giorni.

**In Australia.** La società australiana Quantium Solutions (Australia) Pty Ltd spedisce in Australia tramite corriere — tra cui CouriersPlease — e tramite [Australia Post](carrier:australia-post) eParcel ed express eParcel, come indicato nella pagina sul supplemento carburante. Quantium elenca CouriersPlease anche tra i suoi partner ufficiali. Il corriere che consegna stabilisce i tentativi e dove attende un pacco mancato.

Se non c’era nessuno, vedi [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

## Dogana e dazi su un pacco Quantium

Su Q-Simple, Q-Smart e Q-Speed Quantium indica dazi e imposte a carico del destinatario: Delivered Duty Unpaid (DDU). Q-Sure può viaggiare in DDP (paga il mittente) o DDU. Chiedi al negozio quale opzione vale per il tuo ordine. Eventuali importi dovuti dipendono dalle norme del tuo paese: vedi [pacchi fermi in dogana](guide:customs).

**Verso l’UE.** Gli acquisti online da fuori UE prevedono un dazio doganale temporaneo di 3 € per articolo nei pacchi fino a 150 €, dal 1° luglio 2026, e una tariffa unionale di gestione di 2 € per articolo a prescindere dal valore, dal 1° novembre 2026. Un articolo indica le merci di un pacco con stessa classificazione tariffaria, descrizione e, se richiesta, origine. Quantium comunica alle aziende mittenti che la tariffa di 2 € si applica a ogni riga di dichiarazione doganale, sia in IOSS sia in DAP e qualunque sia il valore della spedizione, con IVA aggiuntiva dove prevista dal paese di destinazione. Chiedi al negozio se era già compresa nel prezzo.

## Tracciamento Quantium Solutions fermo, pacco smarrito o restituito

**Solo `Shipment created`.** Il negozio ha prenotato la spedizione ma Quantium non ha ancora scansionato il pacco. Se resta così per giorni, chiedi al negozio se l’ha davvero spedito.

**Silenzio in transito.** Dopo quattro giorni senza nuove righe su un pacco in viaggio, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e il messaggio «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Quantium Solutions.», insieme al link «Chiedi aiuto a Quantium Solutions» verso il modulo di supporto. Quantium pubblica avvisi di ritardo nella pagina News (come quello sulla forte foschia in Malesia e a Singapore del 7 ottobre 2026). Il tracker può anche indicare il codice del partner sotto «Tracking No.»: traccialo anche presso quel partner. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato, ma non c’è.** Controlla intorno a casa e chiedi ai vicini, poi contatta il negozio e Quantium. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o danneggiato.** Il sito di Quantium non indica procedure di reclamo per i destinatari. Il negozio è il cliente di Quantium, quindi chiedi lì rimborso o sostituzione. Vedi [pacchi smarriti](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Restituito.** Quantium offre ai negozi il reso all’origine. Vedi [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Quantium Solutions

Canali pubblicati da Quantium:

- **Online:** il [Modulo di assistenza clienti](https://www.quantiumsolutions.com/contact-support/), per tracciamento, aiuto generale e segnalazioni. Chiede nome, e-mail, paese di spedizione e numero di tracciamento; il campo «Customer ID» riporta «Corporate Customer only». Quantium dichiara che l’operatore locale risponde entro il giorno lavorativo successivo.
- **Malesia:** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Thailandia:** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Altrove:** la pagina [Contact Us](https://www.quantiumsolutions.com/contact-us/) elenca caselle di supporto per Hong Kong, Indonesia, Taiwan e Vietnam. Per Singapore indica solo un contatto commerciale e non riporta contatti australiani. Non sono pubblicati orari di apertura.

Per un ordine online rivolgiti prima al negozio: ha prenotato la spedizione ed è il cliente di Quantium.

## Tracciare un pacco Quantium Solutions con Peek

[Peek](/) non riconosce un codice Quantium dalla sola forma. Incollalo comunque: Peek segue oltre 4.500 corrieri. Per dieci cifre interroga i corrieri con quel formato e poi i servizi di tracciamento («Verifica dei servizi di tracciamento…»). Per un codice che termina in `SG` o `TH` indica Singapore Post o Thailand Post («Corriere rilevato»). Per leggere le righe proprie di Quantium, tocca «Cambia» e scegli Quantium Solutions; su un pacco già aggiunto usa «Cambia corriere». Scegliendo Quantium per un codice di forma diversa, Peek indica «non emette numeri come questo».

Anche un link al tracker Quantium con `trackno=` funziona: Peek legge il codice e identifica subito Quantium Solutions.

Peek mostra lo stato Quantium, ogni riga nelle parole di Quantium (tabella sopra), il paese di destinazione, oltre al nome del destinatario (sotto «Destinatario»), l’indirizzo (senza la riga telefonica) e il firmatario quando una nota di consegna lo indica. Tralascia riferimento cliente, codice del partner, dati del mittente e origine. Quantium non fornisce luoghi per riga, data prevista di consegna, peso né prova di consegna, quindi Peek non li mostra. «Apri il sito Quantium Solutions» apre il codice sul tracker Quantium.

Poiché gli orari di Quantium non hanno fuso orario, Peek li legge tutti su un solo orologio: quello della destinazione per pacchi verso Singapore, Malesia, Thailandia o Australia (ora di Sydney per l’Australia), altrimenti ora di Singapore. Li converte poi nel fuso del tuo dispositivo («Orari nel tuo fuso orario:»), per cui una riga scansionata in un altro paese può risultare spostata di qualche ora.

Quando il codice ha formato postale con cifra di controllo valida e Quantium indica il paese di destinazione, Peek interroga anche le poste di quel paese con lo stesso codice, come [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) o NZ Post. Segue il pacco lì appena quel servizio postale mostra nuove scansioni e mantiene la cronologia di Quantium. Non vale per tutti i paesi (non per Singapore, Malesia, Thailandia, Hong Kong o Vietnam, ad esempio) e mai per i codici a dieci cifre.

Mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente Quantium fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Singapore nel giorno in cui Quantium lo scansiona in consegna, finché il tracker di Quantium risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Non può contattare Quantium né il negozio per te.

## Domande sul tracciamento Quantium Solutions

### Quantium Solutions è uguale a Quantium Worldwide?

Sì: tracker e caselle di supporto sono su `quantiumworldwide.com`, e Peek la riconosce anche come Quantium o Quantium Solutions International.

### Perché Peek mostra Singapore Post o Thailand Post per il mio codice Quantium?

Il tuo numero usa il formato postale internazionale che identifica le poste nazionali: `SG` per Singapore Post, `TH` per Thailand Post. Per vedere le righe di Quantium, tocca «Cambia» e scegli Quantium Solutions.

### Cosa significa «Customs Clearance Begins»?

La dogana del tuo paese ha avviato lo sdoganamento e Peek mostra «In dogana». Al rilascio la riga diventa `Shipment released from customs/ government check point: Customs Cleared`. Con un servizio DDU eventuali dazi e imposte spettano a te: vedi [pacchi fermi in dogana](guide:customs).

### Quanto impiega Quantium Solutions a consegnare?

Quantium indica 2–5 giorni per Q-Sure e fino a 17 giorni per Q-Simple (tabella sopra). In Asia la consegna Regular richiede 2–4 giorni.

### Posso tracciare un pacco Q-Simple?

Non lungo il percorso. Quantium vende Q-Simple come servizio economico senza tracciamento, quindi non aspettarti scansioni e chiedi al negozio quando è partito.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – tracker, colonna «Tracking No.», «No record!»
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – presenza in 10 paesi Asia-Pacifico
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – attività di Quantium, CouriersPlease tra i partner
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – i quattro servizi, reso all’origine
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – opzioni di consegna in Asia
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – ritardi per foschia
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – tariffa di 2 € per riga di dichiarazione
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – campi del modulo, tempi di risposta
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – telefoni e caselle e-mail
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 €, tariffa di gestione di 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definizione di articolo
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – formati di codice, link, diciture, orologi, campi letti
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rilevamento, orologio usato per Quantium, passaggio alle poste di destinazione
:::
