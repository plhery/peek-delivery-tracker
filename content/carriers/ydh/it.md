---
title: Tracciamento YDH: codici YDH...YQ e AZ, passaggi e aiuto
description: Traccia un pacco YDH Express dalla Cina: codici YDH...YQ e AZ, stati DSD e CARR, passaggio ai corrieri locali, dogana e assistenza.
slug: tracciamento-ydh
published: 2026-11-24
updated: 2026-11-24
---

YDH (*YDH Express* / *YDH Cross Border*, in cinese *义达跨境*) è un operatore cinese di logistica e-commerce transfrontaliera fondato nel 2005 con sede a Shanghai. Presente in oltre 20 hub cinesi di e-commerce — tra cui Shenzhen, Canton, Hangzhou, Ningbo e Hong Kong — con uffici in Giappone, Stati Uniti, Regno Unito e Belgio, YDH spedisce piccoli pacchi e carico commerciale in oltre 200 paesi e regioni prima di affidarli ai corrieri locali. Puoi controllare una spedizione sul [portale di tracciamento YDH](https://track.ydhex.com/track_query.aspx), oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento YDH: formati YQ e AZ

Trovi il numero di lettera di vettura nei dettagli dell’ordine sul marketplace o nell’e-mail di spedizione. I codici YDH hanno 15 caratteri, iniziano con `YDH`, proseguono con 10 cifre e terminano con `YQ` o `AZ`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `YDH4829173640YQ` | Numero pacco transfrontaliero YDH: `YDH` + 10 cifre + `YQ`. |
| `YDH4829173640AZ` | Numero pacco transfrontaliero YDH: `YDH` + 10 cifre + `AZ`. |

Una volta superata la dogana nel paese di destinazione, YDH affida il pacco a un corriere locale: [USPS](carrier:usps) o [GOFO](carrier:gofo) negli Stati Uniti e [Poste Italiane](carrier:poste-italiane) in Italia sono tra i partner di cui YDH inoltra le scansioni sotto il tuo codice `YDH`. YDH può anche mostrare il numero proprio del corriere locale. Per altri formati internazionali consulta [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati e i codici YDH

YDH associa brevi codici di tappa alle proprie scansioni di magazzino, export e volo (in cinese o inglese) e inoltra anche le scansioni del partner locale con il testo originale di quest’ultimo.

| Codice / dicitura YDH | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Ordine elettronico ricevuto dal venditore prima della scansione fisica di YDH. | «Etichetta creata» |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Pacco arrivato nel magazzino o centro di smistamento YDH in Cina. | «In transito» |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | In coda per l’imbarco aereo, partito dalla Cina (o volo in ritardo) e atterrato all’aeroporto di destinazione. | «In transito» |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Sdoganato a destinazione, consegnato al corriere finale, in attesa della prima scansione locale (`D-SCAN DELAY`) o ritirato dal partner. | «In transito» |
| `OD` (`Out for Delivery`) | Caricato dal corriere di destinazione per la consegna finale. | «In consegna» |
| `DD` | Eccezione o tentativo di consegna non riuscito segnalato dal partner locale. | «Tentativo di consegna» / «Problema segnalato» |
| `CC` | Consegnato dal partner di destinazione (con nome del firmatario quando fornito). | «Consegnato» |

**Cosa significa `D-SCAN DELAY` (`DSD`).** Quando YDH consegna un lotto consolidato a un servizio postale o corriere locale (`CARR`), possono trascorrere diversi giorni prima che quel partner scansioni il singolo pacco nella propria rete. `D-SCAN DELAY` segnala quel ritardo di presa in carico: il pacco è ancora in viaggio e non smarrito.

## Come funziona la consegna transfrontaliera YDH

Su [ydhex.com](https://www.ydhex.com/), YDH cita oltre 50 compagnie aeree e marittime con cui prenota spazio e sdoganamento in 12 paesi. La [pagina contatti](https://www.ydhex.com/product/59/) elenca uffici in Giappone (Tokyo, Narita, Osaka, Kansai e Nagoya), Stati Uniti (La Puente, California), Regno Unito (Hounslow) e Belgio (Grâce-Hollogne, vicino a Liegi).

**Consegna dell’ultimo miglio.** YDH gestisce ritiro in export, tratta aerea e sdoganamento, poi passa il pacco (`CARR`) a una posta o corriere locale del tuo paese per consegna a domicilio, punto di ritiro o resi. Tentativi, luoghi sicuri e giacenza dipendono dal corriere locale.

**Dogana e dazi di importazione.** Gli ordini transfrontalieri spediti tramite YDH sono soggetti a IVA di importazione, dazi doganali ed eventuali oneri locali del tuo paese, salvo pagamento anticipato al checkout. Consulta [pacchi fermi in dogana](guide:customs).

## Pacco YDH fermo, silenzioso o non consegnato

Poiché il contratto di trasporto YDH è con il venditore o marketplace e non con l’acquirente, rivolgiti sempre prima al venditore o apri una segnalazione sulla piattaforma per ritardi, correzioni di indirizzo o rimborsi.

- **Pause durante volo o passaggio locale:** pause di vari giorni sono comuni durante l’attesa del volo (`HBYP`), in dogana (`RC`) o in attesa della prima scansione locale (`DSD` / `D-SCAN DELAY`). Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Usa il numero locale dopo il passaggio:** quando YDH mostra il codice del corriere locale, il sito di quel corriere può offrire opzioni di consegna assenti su YDH.
- **Consegnato, danneggiato o smarrito:** se risulta `CC` / consegnato senza che il pacco sia arrivato, controlla prima con i familiari, vicini, portineria e corriere locale, poi avvisa subito il venditore. Consulta [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti YDH

Dettagli ufficiali degli uffici pubblicati sulla [pagina Contatti di YDH](https://www.ydhex.com/product/59/):

- **Linee assistenza in Cina:** `800-988-1378` / `400-888-1378`.
- **Assistenza Shanghai:** `(+86) 21-52277600` / `(+86) 21-52277688` (sede: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Shanghai 201100, Cina).
- **Ufficio di Hong Kong:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Ufficio del Giappone (Tokyo):** `+81 3-5644-5733`.
- **Ufficio del Belgio (Liegi):** `+32 470 588 200`.

> Diffida di SMS o e-mail inattesi che chiedono un piccolo pagamento con carta per «sdoganare» o «aggiornare l’indirizzo» di un pacco dalla Cina. Verifica eventuali richieste doganali reali nella pagina ordini del marketplace o sul sito ufficiale delle poste del tuo paese. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco YDH con Peek

[Peek](/) riconosce automaticamente `YDH` + 10 cifre + `YQ` o `AZ` (`YDH4829173640YQ` e `YDH4829173640AZ`, cifre inventate) come YDH con alta affidabilità, e riconosce anche i link incollati `track.ydhex.com/track_query.aspx?track_number=`. Se dovessi selezionare YDH manualmente, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco (anche cercando «YDH Express», «YDH Cross Border» o «义达跨境»).

Peek legge direttamente il tracker YDH e mostra la fase («Etichetta creata», «In transito», «In consegna», «Tentativo di consegna», «Problema segnalato», «Consegnato»), il testo della scansione — di YDH o del corriere locale — con il luogo e il paese di destinazione. I codici YDH come `CARR` e `DSD` stabiliscono la fase; Peek non li visualizza. YDH data le scansioni in Cina sull’ora di Shanghai e quelle del corriere locale sul fuso orario del rispettivo luogo; Peek le mostra nell’orario del tuo dispositivo. Quando YDH fornisce il numero proprio del corriere locale e Peek riconosce quel corriere, Peek verifica che abbia il pacco: la scheda mostra quindi «Consegna con» seguito dal nome del corriere e riporta entrambi i numeri. Il nome di chi firma resta escluso da ogni app Peek, così come il riferimento cliente e l’etichetta partner di YDH. Il tracker YDH non fornisce data prevista, peso, nome del servizio, punto di ritiro né foto di consegna.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui YDH mostra `OD`, ogni 2 minuti dalle 6:00 secondo l’orario dell’ultima scansione YDH, finché il tracker YDH risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di YDH non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti il giorno della consegna mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a YDH.», con il link «Chiedi aiuto a YDH» alla pagina contatti di YDH. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a YDH.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo il tracciamento e non può contattare YDH, gestire pratiche doganali né reindirizzare il pacco presso il partner locale.

## Domande sul tracciamento YDH

### Che cos’è YDH Express?

YDH (*义达跨境*) è un operatore di logistica e-commerce transfrontaliera fondato nel 2005 con sede a Shanghai, che spedisce piccoli pacchi dalla Cina in oltre 200 paesi affidandoli poi a poste o corrieri locali per la consegna finale.

### Cosa significano i codici `YDH...YQ` e `YDH...AZ`?

Entrambi sono formati di lettera di vettura YDH Express da 15 caratteri composti da `YDH`, 10 cifre e `YQ` o `AZ` (ad esempio `YDH4829173640YQ` o `YDH4829173640AZ`, cifre inventate).

### Cosa significa `D-SCAN DELAY` (`DSD`) nel tracciamento YDH?

`D-SCAN DELAY` significa che YDH ha trasferito il pacco al corriere locale nel paese di destinazione, ma quest’ultimo non ha ancora pubblicato la prima scansione di presa in carico. La spedizione resta in transito in attesa della registrazione locale.

### Come contatto l’assistenza clienti YDH?

Contatta prima il venditore o marketplace per rimborsi o problemi di consegna. Per contatti col corriere, YDH elenca le linee in Cina (`800-988-1378` / `400-888-1378`), Shanghai (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Giappone (`+81 3-5644-5733`) e Belgio (`+32 470 588 200`) su [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Sito ufficiale](https://www.ydhex.com/) – fondata nel 2005, 20+ hub cinesi, 200+ paesi/regioni, 50+ compagnie aeree e marittime, sdoganamento in 12 paesi
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – sede a Shanghai, hotline Cina (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), uffici a Hong Kong, Giappone, USA, Regno Unito e Belgio (URL di aiuto collegato da Peek)
- [YDH: Portale tracciamento](https://track.ydhex.com/track_query.aspx) – ricerca pubblica pacchi YDH
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – rilevamento `YDH` + 10 cifre + `YQ`/`AZ`, regola link `track.ydhex.com`, codici tappa (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), numero del corriere locale, fusi orari e URL di aiuto
:::
