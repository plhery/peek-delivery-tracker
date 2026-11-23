---
title: Tracciamento PostNord: numeri, stati e ritiro
description: Traccia un pacco PostNord in Svezia o Danimarca: formato del numero, stati in inglese, tempi di giacenza, dogana e contatti.
slug: tracciamento-postnord
published: 2026-11-23
updated: 2026-11-23
---

PostNord è il gruppo postale e pacchi di Svezia e Danimarca, nato dalla fusione tra Post Danmark e la svedese Posten AB. Traccia un pacco sulla [pagina di tracciamento di PostNord](https://tracking.postnord.com/en/) o nell’app PostNord con il numero di spedizione, oppure incollalo nel campo qui sopra. Il numero arriva dal negozio che ha spedito il pacco.

## Formato del numero di tracciamento PostNord

Secondo PostNord Danimarca un numero di spedizione ha di solito da 13 a 20 caratteri: in genere 20 cifre che iniziano con `00`, oppure 13 caratteri che terminano con un codice paese di due lettere. I formati più comuni (inventati):

| Numero (inventato) | Che cos’è |
| --- | --- |
| `00073482917364058292` | 20 cifre che iniziano con `00`: codice a barre pacco, usato su PostNord Home o Service Point. |
| `RR482917367SE` | Due lettere, nove cifre, `SE`: raccomandata o piccolo pacco spedito dalla Svezia. |
| `RR482917367DK` | Lo stesso formato terminante in `DK`: spedizione tracciata dalla Danimarca. |
| `48291736405SE` | 11 cifre e `SE`: pacco Service Point svedese. |
| `19482917364058` | 14 cifre: alcuni pacchi PostNord Home. Anche altri corrieri usano questa lunghezza. |

Nessun numero? Chiedilo al negozio che ha spedito il pacco. Quando un pacco ti aspetta, anche il link con il codice di ritiro nell’SMS o nell’e-mail di PostNord mostra il numero di spedizione.

Numeri simili: un codice che termina con `NO` appartiene a [Bring](carrier:bring), la posta norvegese; uno che termina con `FI` è stato spedito dalla Finlandia, dove consegna [Posti](carrier:posti). PostNord consegna anche alcuni pacchi DPD con il numero di DPD.

## Stati di tracciamento PostNord in inglese

Il tracciamento PostNord è disponibile in inglese, svedese, danese, finlandese e norvegese. Le diciture danesi sono scritte separatamente e possono differire leggermente. Ecco gli stati in inglese, in ordine:

| PostNord indica | Cosa significa per te |
| --- | --- |
| `We have received a notification from your shipper that they are preparing an item for you. The tracking information will be updated when the parcel is handed over to PostNord.` | Il negozio ha creato l’etichetta. PostNord non ha ancora il pacco. |
| `The shipment item has been dropped off by sender.` | PostNord ha preso in carico il pacco. |
| `The shipment item is under transportation.` | In viaggio tra i terminal di PostNord. |
| `The shipment item has arrived at the country of destination.` | Un pacco dall’estero è arrivato nel tuo paese. |
| `The shipment item is being customs cleared by us.` | In sdoganamento; possono seguire oneri. |
| `The shipment item has arrived at the distribution terminal.` | Arrivato al terminal che serve la tua zona. |
| `The delivery of the shipment item is in progress.` | In consegna. |
| `Delivery was not possible. The recipient will get a notification with information on where the shipment item will be available for collection.` | Consegna non riuscita. Un avviso indica dove ritirarlo. |
| `The shipment item has been delivered to a service point.` | Pronto per il ritiro presso il punto di servizio indicato nella scansione. |
| `The shipment item has been delivered.` | Consegnato, oppure ritirato dopo una scansione al punto di servizio. |
| `The address is incomplete.` | PostNord non può consegnare all’indirizzo indicato. Contatta il negozio. |

Lo stato più facile da fraintendere è `The shipment item has been delivered to a service point.`: il pacco ti aspetta al punto di ritiro, non ti è ancora stato consegnato. `The shipment item is being held at a distribution terminal awaiting the booked delivery date or, awaiting an agreement of delivery with the recipient.` non segnala un problema: il pacco attende la data di consegna concordata.

## Consegna PostNord non riuscita: punti di ritiro e locker

Le regole qui sotto riguardano PostNord Danimarca. In Svezia, Norvegia e Finlandia consulta postnord.se, postnord.no, postnord.fi o l’app.

**Orari di consegna.** Giorni feriali tra le 7:00 e le 21:00 (ora danese), senza una fascia più precisa. I pacchi per un Pakkeshop (punto di ritiro) o un locker arrivano dal lunedì al sabato.

**Assenza.** Un pacco che non entra nella cassetta delle lettere va in un Pakkeshop. Dal link FlexChange nell’SMS o nell’e-mail di PostNord, oppure in «Opdatér levering» nell’app, puoi autorizzare il corriere a lasciarlo al tuo indirizzo a tuo rischio o scegliere un altro Pakkeshop. Non è possibile scegliere il giorno di consegna.

Un avviso, SMS, e-mail o messaggio nell’app indica dove ritirarlo; i terminal non consegnano pacchi al pubblico.

**Giacenza.** 7 giorni in un Pakkeshop (14 se hai ricevuto una lettera postale di avviso), senza proroga. 3 giorni in un locker, estendibili a 7 dall’app. Poi il pacco torna al mittente.

**Ritiro.** Mostra il codice di ritiro ricevuto via SMS, e-mail o nell’app PostNord; non serve un documento. Senza codice, indica le ultime 4 cifre del numero del pacco e mostra un documento di identità. I locker si aprono tramite l’app via Bluetooth. Un’altra persona può ritirarlo con il tuo avviso o messaggio inoltrato e il proprio documento.

PostNord non può correggere un nome o un indirizzo errati: un pacco non consegnabile rientra al mittente.

## Pacco PostNord in ritardo, smarrito o danneggiato

**Fermo al primo stato.** Se per diversi giorni lavorativi compare solo la notifica del negozio, PostNord Danimarca chiede di contattare il mittente, che deve avviare una ricerca. Approfondisci in [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Smarrito o danneggiato.** Contatta il negozio: in Danimarca è il mittente a presentare il reclamo. In Svezia l’autorità dei consumatori Konsumentverket precisa che il negozio resta responsabile del pacco fino a quando non lo ricevi; reclama per iscritto al negozio e documenta subito i danni. Senza una data promessa, un ordine è considerato in ritardo dopo almeno 30 giorni.

**Segnato consegnato ma non ricevuto.** In Danimarca contatta PostNord via chat nell’app o con il modulo di contatto. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Dogana in Danimarca.** La merce proveniente da fuori UE paga il 25 % di IVA e la tariffa di importazione di PostNord pari a 225 DKK. Dal 1° luglio 2026 il dazio si applica per qualsiasi valore: 3 € (circa 22 DKK) per categoria di articolo (stesso codice doganale) sotto i 150 € (circa 1.150 DKK), e dazio sul valore oltre tale soglia. Si aggiunge la tariffa UE di gestione di 2 € per categoria di articolo sugli ordini extra-UE: se il mittente non la prepaga, PostNord la riscuote con l’addebito di importazione. Paga entro 10 giorni nell’app, via SMS o per lettera, altrimenti il pacco torna indietro; la consegna avviene entro cinque giorni lavorativi dal pagamento. Addebitato di nuovo dopo aver pagato l’IVA al checkout? Il negozio non ha trasmesso i dati IVA: paga e chiedi il rimborso al negozio, oppure rifiuta il pacco. Altro in [pacchi fermi in dogana](guide:customs).

## Servizio clienti PostNord: chat, modulo e app

Dalle pagine di supporto di PostNord:

- **Danimarca:** chat dal lunedì al venerdì, dalle 9:00 alle 16:00 (ora danese), nell’app PostNord sotto «support» o in Tracking sotto il pacco; domande doganali in chat dal lunedì al venerdì, dalle 10:00 alle 14:00 (ora danese). Un chatbot risponde a tutte le ore ed è disponibile un modulo di contatto. PostNord Danimarca non ha più una linea telefonica.
- **Svezia:** assistenza su postnord.se o nell’app PostNord.
- **Norvegia e Finlandia:** assistenza su postnord.no o postnord.fi, oppure nell’app.

> PostNord avvisa di falsi SMS ed e-mail a suo nome: un pacco «bloccato» per numero civico mancante, una piccola tariffa extra di consegna o un pacco «in attesa in dogana». PostNord invia raramente link di pagamento, tranne per l’IVA e gli oneri di importazione su merci extra-UE. Nel dubbio, usa l’app PostNord.

## Tracciare un pacco PostNord con Peek

[Peek](/) riconosce subito come PostNord un numero postale che finisce con `SE` o `DK`, oppure 11 cifre più `SE`. Per un numero di 20 cifre che inizia con `00` e il prefisso svedese o danese, Peek interroga prima PostNord insieme a [Swiss Post Cargo](carrier:swiss-post-cargo) e NZ Post — e a [Matkahuolto](carrier:matkahuolto) per il prefisso svedese — e indica «PostNord ha questo pacco.» quando lo conosce solo PostNord. Con `73402216` dopo le prime tre cifre il numero è di [Early Bird](carrier:early-bird), un corriere svedese che affida alcuni pacchi a PostNord: Peek interroga allora prima Early Bird e PostNord insieme agli altri. Altri numeri da 10 a 20 cifre, come un codice DPD consegnato da PostNord, funzionano dopo aver premuto «Cambia». I link di tracking.postnord.com funzionano con qualsiasi numero.

Peek mostra gli stati di PostNord in inglese così come sono scritti, con il luogo di ogni scansione, il mittente, il peso, le dimensioni, il paese di destinazione e, mentre il pacco attende il ritiro, l’indirizzo del punto di servizio. Esclude gli avvisi SMS ed e-mail e segnala `The shipment item has been stored.` come problema. Mentre tieni il pacco aperto in Peek, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e ogni 2 minuti dalle 6:00 ora locale nel giorno in cui PostNord lo scansiona in consegna, finché il tracciamento diretto di PostNord risponde (ogni 15 minuti tramite i servizi di tracciamento). Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora, e ogni 6 ore dopo sette giorni su «Etichetta creata». Se un pacco fermo su «Etichetta creata», «Preso in carico» o «In transito» non riceve nuove scansioni per quattro giorni, Peek mostra «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da…» con la data), «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a PostNord.» e il link «Chiedi aiuto a PostNord». Peek traccia ogni scansione su mappa e può avvisarti con «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Rimuove il tuo nome e non mostra la data di consegna prevista, anche quando il tracciamento PostNord la indica.

## Domande sul tracciamento PostNord

### Perché il mio numero di tracciamento PostNord non funziona?

Di solito il negozio non ha ancora consegnato il pacco a PostNord. Verifica il codice con il negozio; se nulla si muove per alcuni giorni lavorativi, tocca al negozio avviare una ricerca.

### Per quanto tempo PostNord conserva i pacchi?

In Danimarca 7 giorni in un Pakkeshop e 3 giorni in un locker, estendibili a 7 nell’app PostNord.

### A che ora consegna PostNord?

PostNord Danimarca consegna i pacchi nei giorni feriali tra le 7:00 e le 21:00 (ora danese), senza una fascia oraria più precisa.

### PostNord è affidabile?

Sì, è il gruppo postale ufficiale di Svezia e Danimarca. I messaggi che chiedono piccole somme a suo nome sono però spesso truffe.

:::sources
- [PostNord: Track and trace](https://tracking.postnord.com/en/) – diciture degli stati, intestazioni, lingue del tracciamento
- [PostNord: Contact PostNord](https://group.postnord.com/contact/) – assistenza clienti per paese
- [PostNord: Phishing tips](https://group.postnord.com/cybersecurity/phishing-tips/) – messaggi falsi, link di pagamento
- [PostNord: The PostNord brand](https://group.postnord.com/aboutus/strategy/the-brand/) – fusione Post Danmark e Posten AB
- [PostNord: About us](https://group.postnord.com/aboutus/) – logistica nordica
- [PostNord Denmark: Hvor er min pakke? Track & Trace](https://kundeservice.postnord.dk/s/article/Hvor-er-min-pakke-Track-Trace-dk?language=da) – lunghezza del numero, chiedere al mittente, app
- [PostNord Denmark: Hvorfor kan jeg ikke oprette en reklamation?](https://kundeservice.postnord.dk/s/article/Hvorfor-kan-jeg-ikke-oprette-en-reklamation-dk?language=da) – formati dei numeri, pacchi DPD
- [PostNord Denmark: Sådan henter du pakker med hentekode](https://kundeservice.postnord.dk/s/article/S%C3%A5dan-henter-du-pakker-med-hentekode?language=da) – codice di ritiro, numero nel link
- [PostNord Denmark: I hvilke tidsrum og på hvilke dage leverer I forsendelser?](https://kundeservice.postnord.dk/s/article/I-hvilke-tidsrum-og-p%C3%A5-hvilke-dage-leverer-I-forsendelser-dk?language=da) – giorni e orari di consegna
- [PostNord Denmark: Jeg skal modtage en forsendelse i dag, men jeg er ikke hjemme](https://kundeservice.postnord.dk/s/article/Jeg-skal-modtage-en-forsendelse-i-dag-men-jeg-er-ikke-hjemme-dk?language=da) – assenza, deposito alla porta
- [PostNord Denmark: Kan jeg udsætte leveringen?](https://kundeservice.postnord.dk/s/article/Kan-jeg-uds%C3%A6tte-leveringen-dk?language=da) – nessuna scelta del giorno, Opdatér levering
- [PostNord Denmark: FlexChange](https://kundeservice.postnord.dk/s/article/FlexChange-dk?language=da) – modifiche alla consegna
- [PostNord Denmark: Hvornår kan jeg hente min forsendelse i PostNord Pakkeshop?](https://kundeservice.postnord.dk/s/article/Hvorn%C3%A5r-kan-jeg-hente-min-forsendelse-i-PostNord-Pakkeshop-ex?language=da) – avvisi, nessun ritiro al terminal
- [PostNord Denmark: Frist for at hente i PostNord Pakkeshop eller pakkeboks](https://kundeservice.postnord.dk/s/article/Frist-for-at-hente-i-PostNord-Pakkeshop-eller-pakkeboks-dk?language=da) – tempi di giacenza
- [PostNord Denmark: Hvor lang tid har jeg til at hente?](https://kundeservice.postnord.dk/s/article/Hvor-lang-tid-har-jeg-til-at-hente-Kan-jeg-uds%C3%A6tte-afhentningen-dk?language=da) – estensione nel locker
- [PostNord Denmark: Legitimation / ID](https://kundeservice.postnord.dk/s/article/Legitimation-ID-dk?language=da) – documento d’identità e codice di ritiro
- [PostNord Denmark: Sådan bestiller du til og henter i pakkeboks](https://kundeservice.postnord.dk/s/article/S%C3%A5dan-bestiller-du-til-og-henter-i-pakkeautomat-dk?language=da) – locker tramite app
- [PostNord Denmark: Jeg har brug for hjælp til at hente min pakke i pakkeboksen eller pakketårnet](https://kundeservice.postnord.dk/s/article/Jeg-har-brug-for-hj%C3%A6lp-til-at-hente-min-pakke-i-pakkeboksen-eller-pakket%C3%A5rnet-dk?language=da) – Bluetooth per i locker
- [PostNord Denmark: Kan en anden hente min forsendelse?](https://kundeservice.postnord.dk/s/article/Kan-en-anden-hente-min-forsendelse-i-en-PostNord-Pakkeshop-dk?language=da) – ritiro per conto di altri
- [PostNord Denmark: Kan jeg ændre adressen på forsendelsen?](https://kundeservice.postnord.dk/s/article/Kan-jeg-%C3%A6ndre-adressen-p%C3%A5-forsendelsen-dk?language=da) – indirizzo errato
- [PostNord Denmark: Hvis din pakke er registreret hos afsender](https://kundeservice.postnord.dk/s/article/Hvis-din-pakke-er-registreret-hos-afsender-men-der-endnu-ikke-er-opdateringer-dk?language=da) – nessun aggiornamento, ricerca del mittente
- [PostNord Denmark: Reklamation](https://kundeservice.postnord.dk/s/article/Reklamation-dk?language=da) – reclamo del mittente
- [PostNord Denmark: Min pakke er beskadiget](https://kundeservice.postnord.dk/s/article/Min-pakke-er-beskadiget-dk?language=da) – danni
- [PostNord Denmark: Min pakke mangler indhold](https://kundeservice.postnord.dk/s/article/Min-pakke-mangler-indhold-dk?language=da) – contenuto mancante, 7 giorni
- [PostNord Denmark: Jeg har ikke modtaget min pakke](https://kundeservice.postnord.dk/s/article/Jeg-har-ikke-modtaget-min-pakke-Hvad-g%C3%B8r-jeg-dk?language=da) – consegnato ma non ricevuto
- [PostNord Denmark: Hvad er moms, told og gebyr?](https://kundeservice.postnord.dk/s/article/Hvad-er-moms-told-og-gebyr-dk?language=da) – IVA, tariffa di importazione di 225 DKK, dazio
- [PostNord Denmark: Hvor lang tid tager toldbehandlingen?](https://kundeservice.postnord.dk/s/article/Hvor-lang-tid-tager-toldbehandlingen-dk?language=da) – 10 giorni per pagare, consegna dopo il pagamento
- [PostNord Denmark: Nye EU-toldregler](https://kundeservice.postnord.dk/s/article/Nye-EU-toldregler-ex?language=da) – dazio di 3 € sotto 150 € e tariffa UE di gestione di 2 € per categoria di articolo
- [PostNord Denmark: Jeg har forudbetalt moms](https://kundeservice.postnord.dk/s/article/Jeg-har-forudbetalt-moms-Hvorfor-skal-jeg-betale-igen-dk?language=da) – IVA prepagata (IOSS)
- [PostNord Denmark: Kontakt kundeservice](https://kundeservice.postnord.dk/s/article/Kontakt-kundeservice-dk-ex?language=da) – chat, modulo, chatbot, nessun telefono
- [PostNord Denmark: Chat med Kundeservice - Åbningstider](https://kundeservice.postnord.dk/s/article/Chat-med-Kundeservice-%C3%85bningstider-dk?language=da) – orari di chat e dogana
- [PostNord Denmark: Driftsinformation og advarsler om fupbeskeder](https://kundeservice.postnord.dk/s/article/Driftsinformation-og-advarsler-om-fupbeskeder-dk?language=da) – esempi di truffe
- [Konsumentverket: Fått ett skadat paket och varor saknas](https://www.konsumentverket.se/fragor-och-svar/3221791/fatt-ett-skadat-paket-och-varor-saknas-vad-galler/) – responsabilità del negozio, reclamo scritto
- [Konsumentverket: Försenad leverans av vara](https://www.konsumentverket.se/konsumentratt-process/forsenad-leverans-vara/) – ritardo dopo 30 giorni
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek
:::
