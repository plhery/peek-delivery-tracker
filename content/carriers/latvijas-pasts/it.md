---
title: Tracciamento Latvijas Pasts: numeri, stati e locker
description: Traccia un pacco Latvijas Pasts (poste lettoni): formato del codice, significato degli stati, locker, mancate consegne e contatti.
slug: tracciamento-latvijas-pasts
published: 2026-11-23
updated: 2026-11-23
---

Latvijas Pasts è la posta nazionale statale della Lettonia e gestisce la rete di locker più estesa del Paese. Puoi tracciare una spedizione su [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB), che si apre in lettone ma permette di cambiare lingua (incluso l’inglese), oppure incollare il codice nel campo qui sopra. Il numero arriva via e-mail dal negozio o dal mittente, oppure nell’SMS del corriere prima della consegna.

## Numero di tracciamento Latvijas Pasts

Una spedizione affidata a Latvijas Pasts ha un [codice postale internazionale](guide:tracking-number-formats) di 13 caratteri: due lettere, nove cifre e `LV` finale. Le prime due lettere identificano il servizio:

| Prefisso (esempi inventati) | Tipo di spedizione |
| --- | --- |
| `RR482917367LV` | Raccomandata (Standard +): consegnata con firma. |
| `LA482917367LV` | Spedizione tracciabile (Standard): registrata a ogni passaggio e lasciata in cassetta senza firma. |
| `CA482917367LV` | Pacco: ricevi un avviso per ritirarlo in ufficio postale. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Le spedizioni Simple o Economy non vengono registrate in alcuna fase, quindi non sono tracciabili e non prevedono risarcimenti in caso di danni. Se non hai un codice, l’invio potrebbe appartenere a queste categorie; vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

Dove cercarlo: l’SMS del corriere riporta numero di spedizione, orario stimato e link per reindirizzare il pacco. L’SMS del locker include indirizzo, scadenza per il ritiro e codice di apertura.

Altre sigle finali indicano poste diverse: `…LT` è [Lietuvos paštas](carrier:lietuvos-pastas), `…EE` è [Omniva](carrier:omniva). Una spedizione dall’estero mantiene il codice originario, riconosciuto anche da mans.pasts.lv/track non appena Latvijas Pasts riceve il pacco o i suoi dati; le scansioni estere mostrano il Paese invece di un ufficio lettone.

## Cosa significano gli stati di Latvijas Pasts

Il sito mostra ogni passaggio con data, ora e ufficio postale lettone. Nelle diciture inglesi lette da Peek, ecco gli stati più frequenti nell’ordine tipico:

| Stato Latvijas Pasts | Cosa significa per te |
| --- | --- |
| `Electronic data about shipment received` | [I dati del mittente](guide:tracking-statuses) sono arrivati a Latvijas Pasts, che non ha ancora il pacco. |
| `Item information received` | Il mittente ha registrato la spedizione; Latvijas Pasts non l’ha ancora ricevuta. |
| `Item received from sender` | Latvijas Pasts ha preso in carico il pacco. |
| `Item posted / received from customer` | Spedito in ufficio postale; anche la scansione di accettazione di una posta estera usa questo testo. |
| `Item sent to the processing facility` | Partito dall’ufficio di spedizione verso lo smistamento. |
| `Item arrived at office of exchange` | Arrivato all’ufficio di scambio internazionale, a Riga o all’estero. |
| `Item sent to the Customs` | Presentato in dogana all’ufficio di scambio in uscita dal Paese di origine. |
| `Item returned from Customs` | Rilasciato dalla dogana: non è un reso al mittente. |
| `Item sent abroad` | Partito dall’ufficio di scambio verso il Paese di destinazione. |
| `Item sent abroad (EDI-received)` | La posta estera ha spedito il pacco verso la Lettonia; Latvijas Pasts lo rileva dai dati elettronici. |
| `Item accepted for temporary storage` | Spedizione dall’estero in deposito temporaneo sotto controllo doganale in attesa di sdoganamento. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Ti è stato inviato un SMS sullo sdoganamento (il refuso è di Latvijas Pasts). Il pacco è ancora in deposito temporaneo. |
| `Customs clearance completed, item to be delivered to recipient` | Sdoganamento completato; il pacco prosegue verso l’ufficio di consegna. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Arrivato in un centro di smistamento. |
| `Item sent to next processing facility` | In viaggio verso il centro di smistamento successivo. |
| `Item sent to delivery facility` | In viaggio verso l’ufficio che si occuperà della consegna o del ritiro. |
| `Item received at delivery office` | Arrivato all’ufficio di consegna; da lì può ancora uscire per la consegna a domicilio. |
| `Item redirected` | Reindirizzato altrove, per esempio verso un altro punto di ritiro (vedi sotto). |
| `Item address re-labelled` | Rietichettato per il nuovo indirizzo, verso cui riparte. |
| `Item handed over for delivery` | In consegna. |
| `Unsuccessful delivery attempt` | Consegna non riuscita (vedi sotto). Se compare allo stesso secondo di `Item address re-labelled`, registra un reindirizzamento: il pacco prosegue e Peek mostra «In transito», non «Tentativo di consegna». |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Pronto per il ritiro: l’ufficio di consegna ha stampato l’invito al ritiro o lo ha inviato via SMS. |
| `Item arrived at pick-up point` | Scansione di una posta estera: il pacco attende in un punto di ritiro all’estero (mostra il Paese, non il punto). |
| `Item delivered` | Consegnato, anche quando la posta del Paese di destinazione consegna un invio partito dalla Lettonia. |

`Item returned from Customs` preoccupa spesso, ma significa che il pacco è rientrato dal controllo doganale e continua il viaggio, non che torna al mittente. `Item awaiting next planned cycle` indica l’attesa della prossima partenza all’ufficio di scambio. I pacchi di negozi online che transitano per un hub dedicato usano anche diciture specifiche (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). Per dazi e oneri UE sugli acquisti extra-UE vedi [pacchi fermi in dogana](guide:customs).

Se il sito risponde `No information received on the tracking of the postal item. Please, try again!`, ricontrolla il codice o attendi: il pacco potrebbe non essere stato ancora scansionato, e un invio estero compare quando la posta d’origine invia i dati. `Postal item number entered incorrectly. Please check it and enter it again.` segnala un errore di digitazione. Se un codice corretto resta sconosciuto per giorni, verifica con il mittente che la spedizione sia tracciata: Simple ed Economy non compaiono mai.

## Locker, corriere e mancate consegne

**Locker (pakomāti).** Quando il pacco entra nel locker ricevi un SMS con indirizzo, scadenza e codice a sei cifre (e un’e-mail se il mittente ha inserito il tuo indirizzo). Resta disponibile per **7 giorni di calendario**; al terzo giorno il codice viene reinviato e arriva un promemoria prima della scadenza. Trascorsi sette giorni, la spedizione è considerata non consegnata e torna al mittente — salvo i casi con giacenza complessiva più lunga, nei quali viene spostata al centro assistenza clienti più vicino e custodita per **30 giorni di calendario**. Se il codice non arriva, chiedi al mittente di contattare Latvijas Pasts: il numero di telefono viene corretto solo su richiesta del mittente. Limiti massimi: 30 kg e 38 × 38 × 58 cm (fino a 60 cm di lunghezza nei locker esterni).

**Locker Unisend.** Un pacco spedito dalla Lettonia verso un locker Unisend in Lituania o Estonia resta in giacenza 7 giorni di calendario, con codice a sei cifre via SMS ed e-mail (su numeri lettoni, lituani o estoni) reinviato dopo 48 ore. Se non viene ritirato, torna direttamente al mittente.

**Consegna con corriere.** Latvijas Pasts indica fino a tre tentativi di consegna. Se non sei in casa e non hai reindirizzato il pacco, il corriere lascia un avviso e ripassa; se falliscono anche i tentativi successivi, il pacco va in giacenza e puoi ritirarlo all’ufficio postale principale o concordare un’altra opzione con l’assistenza (la durata della giacenza è fissata dal mittente). Per una spedizione Express Mail (EMS) mancata resta l’avviso «You have received a shipment» in cassetta: chiama il numero indicato tenendo pronto il codice.

**Ufficio postale o Customer Centre.** Ricevi un SMS o un avviso cartaceo con la sede esatta. Porta un documento d’identità valido; la patente basta per raccomandate o assicurate fino a 150 €. Puoi ritirare anche senza l’avviso conoscendo il numero di spedizione, oppure chiamare il numero sull’avviso per richiedere la consegna a domicilio pagando il servizio alla consegna. Un delegato può ritirare con la tua autorizzazione e passaporto o carta d’identità. Per raccomandate e assicurate arriva un secondo avviso dopo 5 giorni e il pacco torna al mittente se non viene ritirato entro 30 giorni dal primo. Se sull’etichetta di un negozio estero compare solo parte del tuo nome, porta documento e prova dell’ordine.

**Reindirizzamento.** Se il mittente ha fornito il tuo numero o ti sei iscritto agli SMS, il messaggio contiene un link per dirottare la spedizione verso un locker (0,00 €, IVA inclusa), un punto di assistenza clienti (1,89 €, IVA inclusa), un altro indirizzo tramite corriere (4,99 €, IVA inclusa) o il servizio gratuito «Postman at home» ove disponibile. Il reindirizzamento non è ammesso se il pacco è già in un locker o sotto controllo doganale, per raccomandate o assicurate con prova di consegna verso un locker, per contrassegni oltre 38 × 38 × 58 cm verso un locker o senza numero di telefono. Con l’iscrizione agli SMS puoi anche indicare un locker preferito.

## Pacco Latvijas Pasts smarrito, danneggiato o in ritardo

Per un pacco da locker smarrito o danneggiato, il reclamo spetta al mittente (entro 6 mesi secondo le condizioni dei locker): per gli acquisti online contatta quindi il negozio. Vanno indicati contenuto e valore con ricevuta, fattura o prova di pagamento e foto dei danni; Latvijas Pasts esamina la pratica entro un mese e il mittente può cedere l’indennizzo al destinatario.

Per raccomandate o assicurate consegnate dal corriere, controlla il contenuto davanti al corriere per firmare un verbale. Se apri il pacco da solo e scopri un danno, scatta foto e chiedi al mittente sostituzione o rimborso: Latvijas Pasts precisa che in tal caso il risarcimento non è a suo carico.

Se una spedizione inviata all’estero non arriva, il mittente può chiedere una ricerca internazionale: a pagamento entro il primo mese, gratuita trascorsi 30 giorni dalla spedizione.

Tracciamento fermo o pacco in dogana? Leggi [tracciamento che non si aggiorna](guide:tracking-not-updating) e [pacchi fermi in dogana](guide:customs).

## Contatti e assistenza clienti Latvijas Pasts

I canali pubblicati su pasts.lv:

- **Telefono:** +371 27008001 (anche +371 67008001 nelle condizioni dei locker).
- **E-mail:** info@pasts.lv.
- **Orari:** lunedì–venerdì 8:00–19:00, sabato 9:00–17:00, domenica chiuso.
- **Segnala un problema:** modulo su [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), gestito negli orari del supporto.

> Latvijas Pasts avvisa contro SMS ed e-mail falsi che chiedono di confermare l’indirizzo o pagare dazi su siti contraffatti, oltre a finte vendite di «pacchi non ritirati». Le sue e-mail terminano sempre con @pasts.lv, il sito ufficiale è pasts.lv e non richiede mai dati bancari o di carte via SMS o e-mail né conferme Smart-ID.

## Tracciare Latvijas Pasts con Peek

[Peek](/) riconosce subito come Latvijas Pasts ogni codice postale che inizia con C, E, L o R e termina con `LV` se la cifra di controllo è valida. Per gli altri codici validi che finiscono in `LV`, come `VV482917367LV`, interroga insieme Latvijas Pasts e [Chronopost](carrier:chronopost) mostrando «Latvijas Pasts ha questo pacco.» quando il codice risulta trovato, oppure «Non ancora trovato presso … Peek continuerà a controllare dopo l’aggiunta.». Con una cifra di controllo errata Latvijas Pasts non viene interrogato e sceglierlo manualmente non funziona (Peek segnala che «non emette numeri come questo»): verifica il codice con il mittente. Un link mans.pasts.lv/track contenente il numero seleziona sempre Latvijas Pasts, e cercarlo come «Latvian Post» nel selettore funziona allo stesso modo. Finché Latvijas Pasts non conosce il codice, il pacco resta su «In attesa di Latvijas Pasts»; se dopo due giorni nessuno ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.», passando a controlli giornalieri e poi settimanali trascorsa una settimana.

Un pacco dall’estero viene seguito prima presso la posta di origine. Quando quella posta indica la Lettonia come destinazione (o non indica destinazione e ti trovi in Lettonia), Peek interroga anche Latvijas Pasts con lo stesso numero e vi passa non appena registra una nuova scansione, conservando la cronologia precedente. Per seguirlo subito con Latvijas Pasts, seleziona «Latvijas Pasts» tramite «Cambia» in aggiunta o «Cambia corriere» sul pacco già salvato.

Gli orari di Latvijas Pasts non riportano il fuso: Peek li legge sull’orologio del luogo della scansione (ora lettone per gli uffici lettoni) e conserva i testi inglesi di Latvijas Pasts, incluso `Item delivered`. Può indicare l’ufficio dove il pacco attende il ritiro, ma non fornisce data di consegna prevista né peso.

Peek controlla fino a ogni 10 minuti mentre il pacco è aperto in Peek o nel giorno della consegna con le notifiche attive; il giorno in cui Latvijas Pasts mette il pacco in consegna, dalle 6:00 ora locale (ora lettone per gli uffici lettoni), controlla ogni 2 minuti finché il tracker ufficiale di Latvijas Pasts risponde. Altrimenti controlla ogni ora. Dopo 12 ore su «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora anche con il pacco aperto, e ogni 6 ore trascorsi sette giorni su «Etichetta creata». Se il tracker di Latvijas Pasts non risponde e Peek deve ricorrere ai servizi di tracciamento, controlla ogni ora, ogni 3 ore passato un giorno senza nuove scansioni e, nel giorno della consegna, fino a ogni 15 minuti mentre il pacco è aperto o ha le notifiche attive. Quando un altro corriere subentra nella consegna, Peek ne segue il ritmo.

Se passano quattro giorni senza nuove scansioni mentre il pacco è su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala indicando il giorno dell’ultima scansione, per esempio «Nessun aggiornamento da lun 12 ott». Compare il messaggio «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Latvijas Pasts.» insieme al link «Chiedi aiuto a Latvijas Pasts». Oltre le tre settimane senza novità, Peek mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Latvijas Pasts.»

Per gli avvisi puoi scegliere tra «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti su Latvijas Pasts

### Cos’è Latvijas Pasts?

È la posta nazionale statale della Lettonia, attiva nei servizi postali, logistici e finanziari in Lettonia e all’estero sul sito pasts.lv.

### Unisend è Latvijas Pasts?

No. Unisend è il marchio pacchi di Lietuvos paštas, la posta lituana. I due operatori condividono una rete di locker che consente di spedire dalla Lettonia verso i locker Unisend in Lituania ed Estonia.

### Posso tracciare una spedizione internazionale Latvijas Pasts?

Sì, se è stata spedita con tracciamento. mans.pasts.lv/track mostra anche le scansioni della posta estera indicando il Paese al posto di un ufficio e offre il pulsante «Pārrobežu izsekošana» verso un sito esterno. All’estero consegna la posta del Paese di destinazione.

### La posta ordinaria dalla Lettonia ha un numero di tracciamento?

Non sempre. Le spedizioni Simple ed Economy non sono registrate e non sono tracciabili; raccomandate, spedizioni tracciabili, pacchi ed EMS sì.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – prefissi per servizio, nessuna indennità Simple, ricerca internazionale, orari, telefono
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – telefono, orari
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – modalità di ricezione, delega
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS corriere, tre tentativi, giacenza, ufficio principale, Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS locker, 7 giorni, promemoria, 30 kg e 38 × 38 × 58 cm, locker preferito
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – secondo avviso dopo 5 giorni, reso dopo 30 giorni, avviso EMS, nome incompleto
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – avvisi, documenti, patente fino a 150 €, ritiro senza avviso, consegna a domicilio a pagamento
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – link di reindirizzamento, opzioni, tariffe IVA inclusa, esclusioni
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – reclamo del mittente, documenti, esame entro un mese
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – verifica davanti al corriere, reclamo al mittente
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – codice a sei cifre, e-mail, reinvio al 3° giorno, reso dopo 7 giorni o 30 giorni in centro clienti, 6 mesi per reclami, locker Unisend, info@pasts.lv e +371 67008001
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – avviso su SMS ed e-mail truffa
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – pagina di tracciamento, cambio lingua, tracciamento internazionale
- [Latvijas Pasts: Complaint form](https://mans.pasts.lv/complaint) – modulo segnalazione problemi
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – posta statale, servizi
- [Latvijas Pasts: Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend marchio di Lietuvos paštas, rete condivisa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek, testi di stato e fasi, numeri rifiutati, passaggio dalla posta estera
:::
