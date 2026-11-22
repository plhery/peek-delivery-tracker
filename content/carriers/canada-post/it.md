---
title: Tracking Canada Post: codici, stati, avviso e dogana
description: Segui un pacco Canada Post: codici in CA e a 16 cifre, significato degli stati in inglese, avvisi di giacenza, consegna con Poste Italiane e dogana.
slug: canada-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Canada Post (Canada Post Corporation / Postes Canada) è la società federale della Corona che gestisce il servizio postale pubblico del Canada. Puoi rintracciare un pacco sulla [pagina Track di Canada Post](https://www.canadapost-postescanada.ca/track-reperage/en), nella sua app gratuita oppure incollando il codice nel riquadro qui sopra. Il numero di tracciamento arriva sempre dal mittente — di solito nell'e-mail di spedizione del negozio — perché Canada Post non può cercare una spedizione tramite nome o indirizzo. Se il codice finisce in `CA` e lo segui dall'estero, Peek può interrogare con lo stesso numero anche la posta del tuo Paese.

## Codice di tracking Canada Post: com'è fatto

Peek riconosce due formati principali per Canada Post, qui mostrati con cifre inventate:

| Esempio (inventato) | Cosa indica |
| --- | --- |
| `4018 2930 5746 1826` | 16 cifre numeriche; l'ultima è una cifra di controllo. Usano 16 cifre anche DHL eCommerce, [Correos Express](carrier:correos-express), [TNT](carrier:tnt) ed [Evri](carrier:evri). |
| `RR482910576CA` | Codice postale internazionale di 13 caratteri: 2 lettere (come `RR` o `EE482910576CA`), 9 cifre e `CA`; l'ultima cifra prima di `CA` è di controllo. |

Sul proprio territorio Canada Post trova un pacco anche con il codice a 15 cifre dell'avviso di giacenza (*Delivery Notice Card*, ad esempio `482910573649182`) o con un riferimento composto solo da cifre; in Peek (un riferimento deve avere almeno 11 cifre) basta selezionare manualmente Canada Post come spiegato più avanti.

Purolator, altro corriere canadese molto diffuso, usa codici propri a 12 cifre oppure 3 lettere e 9 cifre: per questi Peek non interroga Canada Post in automatico. Se Canada Post non riconosce il tuo numero, verifica quale corriere indica il venditore e leggi [dove trovare il codice di tracking](guide:find-tracking-number) e [formati dei codici di tracking](guide:tracking-number-formats).

## Stati di tracciamento Canada Post in inglese

Peek legge le scansioni di Canada Post in inglese. Ecco le diciture più frequenti, parola per parola, con la fase mostrata da Peek:

**In Canada e verso l'estero**

| Canada Post scrive | Cosa significa per te | In Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | Il mittente ha creato l'etichetta, ma Canada Post non ha ancora il pacco. | «Etichetta creata» |
| `Item accepted at the Post Office` | Consegnato allo sportello di un ufficio postale canadese. | «Preso in carico» |
| `Item picked up by Canada Post` | Ritirato da Canada Post presso il mittente. | «Preso in carico» |
| `Item processed` | In lavorazione nella rete Canada Post (può ripetersi più volte). | «In transito» |
| `International item being forwarded to destination country` | Inoltrato dal Canada verso il Paese di destinazione (`Item has arrived in foreign country` all'arrivo). | «In transito» |
| `Item presented to customs` | Presentato alla dogana del Paese di destinazione. | «In dogana» |
| `Item out for delivery` | In uscita oggi per la consegna in Canada. | «In consegna» |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Consegnato nella cassetta postale comunitaria, armadietto pacchi o cassetta condominiale. | «Consegnato» |
| `Delivered to recipient's safe drop location` | Lasciato in un luogo sicuro presso il recapito (come `Delivered to mailroom` o `Delivered to your concierge or building manager`). | «Consegnato» |
| `Notice card left indicating where and when to pick up item` | Tentativo di consegna effettuato; l'avviso indica ufficio postale e orario per il ritiro. | «Tentativo di consegna» |
| `Item available for pickup at Post Office` | Disponibile per il ritiro all'ufficio postale. | «Pronto per il ritiro» |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Ultimo sollecito: ritiralo entro 10 giorni o tornerà al mittente. | «Pronto per il ritiro» |
| `Recipient not located at address provided. Item being returned to sender.` | Destinatario irreperibile all'indirizzo fornito; reso avviato. | «Problema segnalato» |
| `Item has been returned and is enroute to the Sender` | In viaggio di ritorno verso il mittente. | «In transito» |

`Item re-routed due to processing error; Possible delay` e `Delivery may be delayed due to transportation delay` segnalano un ritardo («Problema segnalato»), mentre `Expected delivery date updated` aggiorna la data stimata senza spostamento fisico. Per i pacchi **dall'estero verso il Canada**, `International item mailed in originating country` appare come «Preso in carico», `International item has left originating country and is en route to Canada` come «In transito», `Item has arrived in Canada and will be presented for review` come «In dogana» e `Item was released by Customs and is now with Canada Post for processing` torna «In transito». Nella cronologia le consegne compaiono come «Consegnato», mentre le altre righe restano nell'inglese di Canada Post.

## Pacchi dal Canada verso Italia e Svizzera: chi consegna e dogana

**Chi consegna.** Con un codice postale internazionale che termina in `CA` e cifra di controllo valida, Peek interroga con lo stesso numero anche la posta del Paese da cui segui il pacco — [Poste Italiane](carrier:poste-italiane) dall'Italia, la [Posta Svizzera](carrier:swiss-post) dalla Svizzera, [USPS](carrier:usps) dagli Stati Uniti o [Royal Mail](carrier:royal-mail) dal Regno Unito (Parcelforce per i codici che iniziano con `E`). Poiché Canada Post non comunica il Paese di destinazione, conta la regione impostata sullo smartphone o il Paese da cui hai aggiunto il pacco via browser. Appena la tua posta pubblica una scansione almeno recente quanto l'ultima di Canada Post, Peek segue lì la spedizione mantenendo la cronologia canadese e, per un altro giorno, continua a interrogare anche Canada Post al massimo ogni 55 minuti. I codici a 16 cifre restano sempre su Canada Post.

**Dogana in Italia e in Svizzera.** Il Canada è fuori dall'UE e dallo spazio doganale svizzero:

- **Italia (UE)**: si applica l'IVA all'importazione fin dal primo euro (salvo riscossione anticipata tramite IOSS). Dal 1° luglio 2026 sugli acquisti online extra-UE fino a 150 € si applica un dazio doganale UE di 3 € per categoria di articolo, e dal 1° novembre 2026 si aggiunge una tassa di gestione dell'Unione di 2 € per articolo qualunque sia il valore dell'ordine. Livigno resta fuori dal territorio doganale e IVA UE, mentre Campione d'Italia e le acque italiane del Lago di Lugano sono fuori dall'area IVA UE.
- **Svizzera**: secondo l'UDSC (BAZG) l'IVA all'importazione è dell'8,1 % (2,6 % ridotta per libri, alimentari o medicinali), ma i tributi fino a 5 CHF non vengono riscossi.

Approfondisci in [pacco fermo in dogana](guide:customs).

## Consegna mancata, armadietti e dogana in Canada

Per le spedizioni ricevute **in Canada**, valgono queste regole di Canada Post e della Canada Border Services Agency (CBSA):

- **Tentativo alla porta e avviso**: se serve la firma o il collo non entra in cassetta, il portalettere suona alla porta (nei condomini con citofono la chiamata al citofono vale come tentativo). Se non risponde nessuno, lascia una *Delivery Notice Card* (dopo `Delivery Attempt Made / Rescheduled for delivery next business day` riprova il giorno lavorativo successivo). Dopo 5 giorni di calendario arriva un avviso finale e dopo **15 giorni di calendario** complessivi in ufficio postale il pacco torna al mittente. Per il ritiro porta un documento d'identità con foto rilasciato dallo Stato che riporti l'indirizzo sul pacco (un delegato può ritirare con il proprio documento e il tuo avviso firmato). Negli armadietti pacchi (*parcel lockers*) la chiave viene lasciata nella tua cassetta; dopo **5 giorni** il pacco passa all'ufficio postale. Vedi [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).
- **Dogana canadese (CBSA)**: le spedizioni postali fino a **20 CAN$** sono esenti da dazi e tasse, e i regali da familiari o amici all'estero fino a **60 CAN$** non pagano la GST. Oltre tale soglia si pagano il 5 % di GST (o HST/PST provinciali) ed eventuali dazi indicati sul modulo **E14**, più **9,95 CAN$** di commissione Canada Post: puoi saldare online col tasto *Pay* su Track o nell'app (con carta di credito; nell'app anche con Apple Pay o Google Pay) prima che il pacco esca in consegna, con carta alla porta o all'ufficio postale, oppure contestare l'importo chiedendo un riesame o tramite il modulo **B2G** sul retro dell'E14.

## Pacco Canada Post in ritardo, fermo, smarrito o non ricevuto

**Quattro giorni senza aggiornamenti.** Dopo quattro giorni senza nuove scansioni su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra nell'elenco il giorno dell'ultima scansione («Nessun aggiornamento da …») e nella scheda spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Canada Post.», aggiungendo il link «Chiedi aiuto a Canada Post». Secondo la CBSA, la posta in dogana in Canada non è tracciabile nel dettaglio; vedi [tracking che non si aggiorna](guide:tracking-not-updating).

**In ritardo o smarrito.** Per Canada Post la maggior parte dei pacchi in ritardo arriva entro **5 giorni lavorativi** dalla data stimata. Oltre quel termine apri un ticket di ricerca sulla [pagina pacchi smarriti](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) entro **90 giorni** dalla spedizione (o **6 mesi** per invii verso USA o estero). L'indennizzo spetta solo al mittente, quindi per acquisti online contatta anche il negozio; vedi [pacco smarrito](guide:lost-parcel).

**Consegnato ma non c'è.** Per il *Canada Post Corporation Act*, la corrispondenza lasciata secondo l'uso abituale a domicilio, in cassetta o a persona ragionevolmente legittimata conta come consegnata. Controlla su Track il punto preciso (`Delivered to mailroom`, portineria o *safe drop*) e l'eventuale firma; se in Italia ha consegnato [Poste Italiane](carrier:poste-italiane), verifica la sua prova di consegna e leggi [pacco consegnato ma non ricevuto](guide:delivered-not-received). Al rientro dal mittente Peek mostra «Restituito al mittente» ([pacco restituito al mittente](guide:returned-to-sender)).

## Assistenza clienti e contatti Canada Post

- **Telefono**: `1-866-607-6301` in Canada, `+1-416-979-3033` dall'estero, TTY `1-800-267-2797`. Dal lunedì al venerdì 7:00–23:00 ET; sabato e domenica 9:00–21:00 ET.
- **Online**: chat e apertura ticket sulla [pagina di supporto Canada Post](https://www.canadapost-postescanada.ca/cpc/en/support.page).

> Gli SMS che chiedono pagamenti per sbloccare un pacco sono truffe. Gli SMS autentici di Canada Post provengono da `272727` o `55555` (se iscritto) oppure da `613-734-1002` e `613-734-1004`, con link verso `canadapost-postescanada.ca`. In Canada gli SMS sospetti si inoltrano al `7726`.

## Seguire un pacco Canada Post con Peek

[Peek](/) riconosce subito Canada Post per i codici internazionali in `CA` con cifra di controllo valida. Con un numero a 16 cifre valido, mostra «Verifica dei servizi di tracciamento…», interroga insieme Canada Post, DHL eCommerce, TNT, Correos Express ed Evri e sceglie chi riconosce il codice. Per le 15 cifre di un avviso di giacenza o un riferimento numerico, tocca «Cambia» e seleziona Canada Post sotto «Tutti i corrieri». Funzionano anche i link con `searchFor=` (quelli con `#/search` valgono solo per i codici `CA`). Per i pacchi spediti dall'estero verso il Canada, Peek interroga Canada Post appena la posta di origine indica il Canada come destinazione.

Peek interroga direttamente Canada Post mostrando stato, cronologia con località (città e provincia in Canada), giorno previsto, fascia oraria quando il pacco è in consegna in Canada e Canada Post la indica, ora di consegna e nome del mittente quando è completo, escludendo opzioni di consegna, indirizzi, firme e foto. Gli orari sono convertiti nel tuo fuso («Orari nel tuo fuso orario: ….»).

Mentre tieni il pacco aperto sullo schermo o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti quando Canada Post segnala il pacco «In consegna» in giornata — dalle 6:00 ora locale o da 30 minuti prima dell'inizio della fascia oraria comunicata —; negli altri casi, e dopo 12 ore senza novità su «Etichetta creata» o «Preso in carico» (48 ore nelle altre fasi), controlla ogni ora. Tramite i servizi di tracciamento controlla fino a ogni 15 minuti in quel giorno «In consegna» alle stesse condizioni, altrimenti ogni ora, o ogni 3 ore dopo un giorno senza aggiornamenti. Quando il pacco passa alla posta di destinazione, segue il ritmo di quell'operatore. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti sul tracking Canada Post

### Perché Peek mostra «In attesa di Canada Post»?

Canada Post restituisce la stessa risposta sia per un numero non ancora scansionato sia per uno inesistente o scaduto (oltre un anno). Se dopo due giorni nessuna fonte risponde, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a controllare una volta al giorno, poi una volta a settimana trascorsa una settimana dall'aggiunta. Controlla la cifra di controllo finale (o quella prima di `CA`).

### Chi consegna in Italia o in Svizzera un pacco partito con Canada Post?

Con un codice che termina in `CA`, consegna [Poste Italiane](carrier:poste-italiane) in Italia e la [Posta Svizzera](carrier:swiss-post) in Svizzera; Peek le interroga in automatico con lo stesso codice. Un codice a 16 cifre resta su Canada Post.

### Posso tracciare il pacco con le 15 cifre dell'avviso lasciato dal postino?

Sì: incolla le 15 cifre dell'avviso, premi «Cambia» e scegli Canada Post in «Tutti i corrieri».

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – app gratuita, avvisi di giacenza, documenti per il ritiro e codici non trovati
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – codice disponibile solo dal mittente, assenza ricerca per nome/indirizzo e carte a 15 cifre
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – stati di giacenza, delega al ritiro, secondo tentativo e dogana
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – tentativo al citofono, luogo sicuro, avviso finale a 5 giorni e custodia di 15 giorni
- [Canada Post: Parcel lockers FAQ](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – chiave in cassetta e limite di 5 giorni nell'armadietto
- [Canada Post: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – commissione di 9,95 CAN$, pagamento online e Apple Pay / Google Pay nell'app
- [Canada Post: Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – attesa di 5 giorni lavorativi e termini di 90 giorni e 6 mesi
- [Canada Post: Support and contact](https://www.canadapost-postescanada.ca/cpc/en/support.page) – numeri in Canada e dall'estero, orari e chat
- [Canada Post: Recognizing spam and fraudulent messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – numeri SMS autentici e inoltro al 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – società federale della Corona
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – controllo doganale postale e modulo E14
- [CBSA: Paying duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – franchigie di 20 CAN$ e 60 CAN$, GST/HST/PST
- [CBSA: Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – riesame e modulo B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – definizione legale di consegna
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – dazio di 3 € per categoria di articolo fino a 150 €
- [Commissione europea: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione dal 1° luglio 2026
- [Commissione europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tassa di gestione di 2 € per articolo dal 1° novembre 2026
- [Commissione europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – regime di Livigno e Campione d'Italia
- [UDSC (BAZG): Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e franchigia di 5 CHF
- [Universal Parcel Scraper: Canada Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/canada-post) – formati dei codici, mappatura stati e passaggio alle poste nazionali
:::
