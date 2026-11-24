---
title: Tracciamento Thailand Post: codici TH S10, EMS e 1545
description: Traccia un pacco Thailand Post (ไปรษณีย์ไทย): codici TH S10 da 13 caratteri, stati nazionali ed EMS, contrassegno COD, dogana e numero 1545.
slug: tracciamento-thailand-post
published: 2026-11-24
updated: 2026-11-24
---

**Thailand Post** (*Thailand Post Co., Ltd.*, in thailandese *บริษัท ไปรษณีย์ไทย จำกัด*) è l’operatore postale nazionale della Thailandia ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Gestisce posta nazionale, pacchi raccomandati, EMS, Same Day, spedizioni pesanti LogisPost e contrassegno (COD) in Thailandia, oltre ai servizi internazionali [EMS](carrier:ems) World, Registered, ePacket, Courier Post (fino a 200 kg) e Courier Post Plus in tutto il mondo. Il tracciamento pubblico è disponibile su [track.thailandpost.co.th](https://track.thailandpost.co.th/) e gli aggiornamenti internazionali su [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Puoi cercare lì un invio postale thailandese oppure incollare il codice `TH` da 13 caratteri nel riquadro qui sopra.

## Codici di tracciamento Thailand Post (formato `TH` S10 da 13 caratteri)

Gli invii tracciati nazionali e internazionali di Thailand Post usano codici UPU S10 da 13 caratteri: due lettere maiuscole di servizio, 8 cifre seriali, 1 cifra di controllo S10 e il codice paese `TH`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `EE482917367TH` | Spedizione espressa [EMS](carrier:ems) Thailand Post da 13 caratteri (prefisso `EE` + 9 cifre con cifra di controllo S10 valida + `TH`). |
| `RR739184627TH` | Raccomandata o piccolo pacco Thailand Post da 13 caratteri (prefisso `RR` + 9 cifre con cifra di controllo S10 valida + `TH`). |

I prefissi nazionali Express/EMS, Raccomandata (`R...TH`), ePacket (`L...TH`) e pacchi nazionali (`E`, `R`, `C`, `W`, ecc. terminanti in `TH`) condividono tutti la stessa struttura S10 a 13 caratteri con cifra di controllo. Le lettere ordinarie prive di codice a barre a 13 caratteri non sono tracciabili online. Vedi la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento Thailand Post

Il tracker di Thailand Post abbina codici numerici `statusID` allineati UPU a descrizioni inglesi (e nomi di uffici thailandesi), includendo sia le scansioni in Thailandia sia gli aggiornamenti EDI internazionali inoltrati dalle poste di destinazione:

| Codice e dicitura Thailand Post | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `3` `Posting/Collection` | Accettato allo sportello Thailand Post o ritirato dal mittente. | «Preso in carico» |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Lavorato in un ufficio di scambio internazionale in esportazione o importazione. | «In transito» |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | In movimento tra centri di smistamento postali fino all’arrivo all’ufficio postale locale di consegna. | «In transito» |
| `31` `Item out for physical delivery` | In consegna oggi con il portalettere Thailand Post. | «In consegna» |
| `57` `Contact recipient` | Il portalettere ha provato a chiamare il destinatario (azione senza spostamento; mantiene la fase attuale). | Mantiene la fase precedente |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Depositato in un locker o punto di ritiro Thailand Post pronto per il ritiro. | «Pronto per il ritiro» |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (gruppo 4) | Tentativo di consegna non riuscito (ad esempio destinatario assente o dazi/COD da pagare). | «Tentativo di consegna» |
| `28` `Return to the origin post office` | Anomalia segnalata e pacco in rientro verso l’ufficio postale di origine. | «Problema segnalato» |
| `35/L` `The sender received the returned item` | Pacco restituito riconsegnato al mittente originario. | «Restituito al mittente» |
| `35/S` `Final delivery` | Consegnato al destinatario (può essere seguito da `36` `Successfully transfer money to seller` sugli ordini COD, senza alterare l’ora di consegna). | «Consegnato» |

## Contrassegno (COD), passaggi internazionali e dogana

- **Esportazioni internazionali (`...TH` all’estero):** dopo `15` (`Departure From Outward OE`), le poste nazionali del tuo paese — come [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post o [Australia Post](carrier:australia-post) — curano sdoganamento e consegna locale rimandando scansioni EDI a Thailand Post.
- **Dazi doganali e IVA di importazione:** i pacchi esportati dalla Thailandia o importati in Thailandia sono soggetti alle regole doganali, all’IVA e agli oneri del paese di destinazione (inclusi gli oneri UE sugli ordini extra-UE). Se il tracciamento indica `34` (`Item held, addressee notified due to Payment of charges`), controlla la cassetta postale o l’ufficio postale locale per un avviso doganale o di pagamento. Vedi la guida ai [pacchi fermi in dogana](guide:customs).
- **Contrassegno (COD) in Thailandia:** sugli invii COD nazionali Thailand Post pubblica `35/S` (`Final delivery`) al pagamento e ritiro da parte del destinatario, seguito da `36` (`Successfully transfer money to seller`) quando i fondi vengono accreditati al venditore.

## Pacco Thailand Post in ritardo, fermo o smarrito

- **Pausa silenziosa dopo l’uscita dalla Thailandia:** gli invii aerei internazionali (specialmente Raccomandate `R...TH` e pacchi economici) restano spesso fermi per giorni o settimane tra `Departure From Outward OE` e `Arrival at inward office of exchange`. Inserire lo stesso codice `TH` da 13 caratteri sul sito delle poste del paese di destinazione mostra spesso ulteriori dettagli doganali o locali. Vedi [tracciamento fermo](guide:tracking-not-updating).
- **Spedizioni smarrite, danneggiate o restituite:** contatta prima il mittente o il marketplace e conserva il codice a 13 caratteri e la ricevuta. Vedi anche [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti Thailand Post e Contact Center 1545

Canali ufficiali pubblicati su [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) e [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Thailandia):** **`1545`** (informazioni generali, ricerche invii e reclami).
- **Portale ufficiale contatti e richieste:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Tracker online e avvisi internazionali:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) e [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Diffida di SMS o messaggi LINE fasulli che sostengono che un pacco Thailand Post richieda una piccola somma tramite un link sconosciuto. Verifica sempre tracciamento e spese doganali direttamente su [track.thailandpost.co.th](https://track.thailandpost.co.th/) o tramite il THP Contact Center `1545`. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Thailand Post con Peek

[Peek](/) riconosce Thailand Post automaticamente come unico candidato per qualsiasi codice S10 `TH` valido da 13 caratteri (`EE482917367TH` o `RR739184627TH`, inventati) e riconosce direttamente i link incollati `track.thailandpost.co.th/?trackNumber=`. Se vuoi cambiare corriere manualmente (ad esempio per confrontare le scansioni delle poste di destinazione una volta arrivato all’estero), tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella sua schermata.

Peek legge il tracker di Thailand Post — e il suo secondo host quando il primo non risponde — mostrando la fase («Preso in carico», «In transito», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), la dicitura inglese della scansione, l’ufficio postale e l’orario di consegna. I codici stato impostano la fase e nessuna app Peek li mostra; il nome del destinatario compare sotto «Destinatario» sulla tua pagina del pacco, mai su un link condiviso, e nessuna app mostra il nome di chi ha ritirato il pacco. Le scansioni di Thailand Post portano l’offset di Bangkok. Una scansione EDI di una posta estera reca l’orologio locale di quell’ufficio, che Thailand Post registra come se fosse ora di Bangkok: Peek la data quindi sul fuso di quell’ufficio (ora di Tokyo per `TOKYO INTERNATIONAL`), una scansione senza luogo su quello della scansione vicina, mostrando poi tutto sull’orologio del tuo dispositivo. Peek esclude nome dell’autista, numeri di telefono, firma e nota di contatto mascherata; Thailand Post non indica una data di consegna stimata.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Thailand Post mostra `31` (`Item out for physical delivery`), ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracker Thailand Post risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Thailand Post non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Thailand Post.», con il link «Chiedi aiuto a Thailand Post» alla pagina contatti di Thailand Post. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Thailand Post.»

Puoi scegliere gli avvisi «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare, pagare importi COD né contattare Thailand Post per te.

## Domande sul tracciamento Thailand Post

### Che aspetto ha un codice di tracciamento Thailand Post?

Gli invii tracciati di Thailand Post usano codici UPU S10 da 13 caratteri che iniziano con due lettere di servizio, seguite da 9 cifre (con cifra di controllo finale) e `TH` — ad esempio `EE482917367TH` per EMS o `RR739184627TH` per raccomandata (inventati).

### Cosa significa il codice `36` (`Successfully transfer money to seller`)?

Sugli ordini nazionali thailandesi in contrassegno (COD), il codice `36` conferma che Thailand Post ha trasferito al venditore l’importo incassato dopo la consegna finale (`35/S`). È un evento finanziario e non un movimento del pacco.

### Perché il mio pacco Thailand Post ha smesso di aggiornarsi dopo `Departure From Outward OE`?

Il pacco ha lasciato l’ufficio di scambio internazionale in uscita dalla Thailandia ed è in transito aereo internazionale o in attesa di presa in carico dall’operatore postale del paese di destinazione.

### Come contatto l’assistenza clienti Thailand Post?

Chiama il THP Contact Center al **`1545`** in Thailandia oppure usa la pagina ufficiale di contatto su [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, servizi nazionali/internazionali EMS, Registered, Courier Post, LogisPost e COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – assistenza ufficiale, portale reclami e numero `1545` (link di aiuto in Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – tracciamento online pubblico ufficiale
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – stato e avvisi del servizio internazionale
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – controllo cifra S10 `TH`, failover su doppio host, mappa `statusID` UPU (`3`–`36`, `57`), orologio Bangkok vs EDI estero ed estrazione destinatario
:::
