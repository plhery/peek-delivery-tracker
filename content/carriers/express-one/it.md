---
title: Tracciamento Express One: stati, punti e contatti
description: Traccia un pacco Express One in Ungheria: dove trovare il codice, significato degli stati, punti ritiro, locker, consegne mancate e contatti.
slug: tracciamento-express-one
published: 2026-11-22
updated: 2026-11-22
---

Express One Hungary è un corriere ungherese di proprietà di [Austrian Post](carrier:austrian-post), che consegna per negozi online con circa 700 corrieri e oltre 2.300 punti di ritiro e armadietti. Traccia un pacco con il suo ID pacco su [tracking.expressone.hu](https://tracking.expressone.hu/) oppure incollalo qui sopra. Il codice arriva nell'e-mail inviata da Express One quando il pacco è registrato ed è entrato nella sua rete.

## Numero di tracciamento Express One

Express One ti invia un preavviso via e-mail quando il mittente ha registrato il pacco e questo è fisicamente arrivato da Express One; l'SMS viene inviato solo se il mittente paga l'opzione. In Ungheria i messaggi riportano fascia di consegna, contatto del corriere, eventuale contrassegno e link per modificare la consegna. I pacchi diretti all'estero non ricevono preavvisi. Nessun messaggio? Chiedi al negozio e consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

Il tracker accetta anche il riferimento del mittente o il numero documento, ma richiedono il Company ID a 5 cifre del mittente, quindi usa l'ID pacco (il codice a barre).

Express One non pubblica un formato ufficiale. Gli ID pacco rilevati finora hanno due forme (cifre inventate):

| Numero | A chi appartiene |
| --- | --- |
| `0482917364829` | 13 cifre: un ID pacco Express One, ma anche DPD France e altri corrieri usano questa lunghezza. |
| `148291736482917364821011` | 24 cifre terminanti con un codice postale ungherese: Express One. Anche Austrian Post, [InPost](carrier:inpost), [bpost](carrier:bpost) e [Ciblex](carrier:ciblex) usano 24 cifre. |
| `48291736482917` | 14 cifre: DPD, mai Express One. |
| `48291736482` | 11 o 12 cifre: GLS, mai Express One. |
| `RR482917367HU` | Due lettere, 9 cifre, `HU`: Magyar Posta, mai Express One. |

Segui sempre il corriere indicato nell'e-mail del negozio; di più in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Express One

tracking.expressone.hu appare in inglese o in ungherese in base alla lingua del browser, con pulsante EN/HU. Ogni scansione riporta codice, dicitura, data e ora, e le scansioni di hub o deposito aggiungono il luogo tra parentesi, come «(HU - Budapest)». Ecco i testi inglesi:

| Stato Express One | Cosa significa per te |
| --- | --- |
| `Shipping details received, shipment not yet handed over to EXOHU` | Il mittente ha [registrato il pacco](guide:tracking-statuses); Express One non lo ha ancora ricevuto. |
| `Shipment (pan) data uploaded` | I dati del pacco sono nel sistema Express One, di solito subito dopo una scansione all'hub. |
| `Arrived at central hub` | Arrivato all'hub centrale di Budapest. |
| `Departed central hub` | Ha lasciato l'hub. |
| `Arrived at depot` | Arrivato al deposito che consegna al tuo indirizzo. |
| `Out for delivery` | In consegna oggi sul furgone del corriere. |
| `Delivery schedule updated` | Il piano di consegna è stato ricalcolato; il pacco non si è mosso. |
| `Processing in progress` | La consegna è stata spostata a un orario successivo, senza indicarne il motivo. |
| `Addressee not present at delivery address` | Il corriere è passato e non c'era nessuno. |
| `Addressee unavailable` | Anche il secondo tentativo è fallito. |
| `Obstacles of nature` | Il maltempo ha fermato il giro; vale come tentativo e il pacco ripartirà in consegna. |
| `Successful delivery` | Consegnato. |

EXOHU sta per Express One Hungary: il primo stato indica che esiste solo l'etichetta. La barra sopra la cronologia usa diciture proprie, da `Shipment data uploaded` a `Successfully delivered`. In ungherese consegnato si legge `Sikeres kézbesítés`.

## Consegna Express One, punti ritiro e mancate consegne

**Quando arriva.** Il preavviso indica una fascia di 4 ore. La mattina della consegna un messaggio dà una finestra di 2 ore definita da Express One puramente indicativa: il corriere può passare prima o dopo. Quel giorno il tracker mostra quante fermate mancano prima di te. Non ci sono consegne nel fine settimana, tranne la consegna al sabato a pagamento per mittenti aziendali.

**Modifiche.** Il pulsante «Modification» nell'SMS o e-mail del mattino permette di cambiare orario o indirizzo, oppure deviare il pacco a un parcel shop o locker anche un'ora prima dell'arrivo del corriere. Le modifiche sono ammesse solo il primo giorno di consegna e una sola volta. Alcune opzioni dipendono dal mittente (un pacco ingombrante non può andare in un locker). Le condizioni generali indicano che una modifica conta come tentativo e può costare extra; un post del blog Express One del 2024 afferma che il reindirizzamento è gratuito.

**Alla consegna.** I destinatari privati firmano e forniscono il numero di un documento d'identità. Il contrassegno si paga in contanti (fino a 300.000 Ft), con carta (fino a 1.000.000 Ft) o in anticipo tramite link nel preavviso; presso un Exopoint il limite contanti è 250.000 Ft. Non puoi aprire il pacco prima di firmare e pagare.

**Consegna mancata.** Secondo le condizioni generali, Express One ti avvisa via e-mail o SMS entro il giorno lavorativo successivo, effettua un secondo tentativo gratuito e tiene il pacco fino al 5° giorno lavorativo. Nel frattempo puoi chiedere una nuova data o un punto di ritiro. Dopo il secondo tentativo fallito il pacco rientra al mittente; un articolo di blog del 2024 dice che attende prima 5 giorni lavorativi al deposito centrale di Budapest.

**Punti ritiro e armadietti.** Molti negozi consentono di scegliere un punto o armadietto Exopoint al checkout. La pagina punti ritiro di Express One indica fino a una settimana di giacenza, 24/7. I post sul blog riportano durate diverse: 3 giorni in EXOBOX, 2 in Alzabox, fino a 7 presso un punto Packeta (2025), 72 ore in locker (2026), 5 giorni in un punto ritiro (2024), con accesso 24/7 solo negli armadietti EXOBOX e Alzabox. Il ritiro al centro logistico di Budapest (H-1239 Budapest, Európa street 12, building L1) richiede appuntamento con il servizio clienti.

## Pacco Express One in ritardo, danneggiato o smarrito

**Nessun aggiornamento o non trovato.** Express One non fissa un tempo di attesa. Il tracker segnala che il numero può essere errato, più vecchio di 180 giorni o non ancora registrato dal mittente, e invita a riprovare più tardi. Le condizioni le permettono di cancellare i dati se il pacco non arriva entro 5 giorni lavorativi, e definiscono puramente indicativi i tempi stimati. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Danneggiato o articolo errato.** Avvisa subito il negozio: Express One avvia il reclamo su segnalazione del mittente e risponde del trasporto, non del contenuto. Segnala danni visibili alla consegna davanti al corriere; segnala eventuali danni occulti per iscritto entro 3 giorni lavorativi dalla consegna, altrimenti il diritto decade.

**Smarrito.** Di norma reclama il mittente, ma puoi farlo anche tu se il pacco ti è stato consegnato o se il mittente ti cede il diritto per iscritto. Il reclamo scritto per smarrimento totale va inviato entro 6 mesi dal 15° giorno successivo alla spedizione, con numero di lettera di vettura, prova di contenuto e valore e modulo Express One. Express One risponde entro 30 giorni di calendario; se non sei soddisfatto puoi rivolgerti entro 30 giorni alla NMHH, l'autorità ungherese per media e comunicazioni.

## Contatti e servizio clienti Express One

Recapiti dalla pagina contatti inglese di Express One, nei giorni lavorativi dalle 8:00 alle 17:00 (le FAQ indicano fino alle 18:00), ora ungherese:

- **Servizio clienti:** +36 1 8 777 400, ugyfelszolgalat@expressone.hu, anche se il corriere non risponde. Nei periodi intensi Express One consiglia di scrivere.
- **Servizio clienti internazionale:** +36 1 8 777 410, intcs@expressone.hu; reclami a intcsreklamacio@expressone.hu.
- **Reclami danni:** +36 1 8 777 470, karugy@expressone.hu.
- **Modulo di contatto:** sulla [pagina contatti di Express One](https://expressone.hu/en/kapcsolat), con oggetti come «I am expecting a parcel» e «Delivery complaint»; entrambi richiedono il numero del pacco.

La pagina Parcel Tracking di Express One elenca inoltre il +36 1 8 777 444 e un chatbot.

## Tracciare pacchi Express One con Peek

Un codice a 13 cifre non viene chiesto automaticamente a Express One: [Peek](/) lo sottopone prima ad altri corrieri. Se nessuno lo riconosce, mostra «non ancora trovato · Peek continua a controllare»: premi «Cambia» e scegli «Express One». Per un numero a 24 cifre, invece, Peek interroga prima Express One quando cerchi dall'Ungheria, davanti ad [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) e [Ciblex](carrier:ciblex); fuori dall'Ungheria chiede prima agli altri tre corrieri e poi a Express One. Oppure incolla un link tracking.expressone.hu con `?plc_number=`, che seleziona subito Express One con qualsiasi formato.

Le scansioni di Express One riportano l'orario senza fuso orario: Peek le interpreta sull'orologio del luogo della scansione (ora ungherese per le scansioni in Ungheria) e le mostra nel tuo fuso orario («Orari nel tuo fuso orario:»). Legge lo stato e, nelle scansioni di hub, città e paese. Le diciture restano quelle di Express One, eccetto `Out for delivery`; `Successful delivery` vale come consegnato e una tratta di rientro come reso. Una scansione sconosciuta mostra la dicitura senza fase. Non sono presenti consegna prevista, peso, punto di ritiro, barra di avanzamento né firma.

Peek interroga direttamente Express One fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 sull'orologio dell'ultima scansione (ora ungherese in Ungheria) del giorno in cui Express One scansiona il pacco in consegna, mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive; altrimenti ogni ora. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in transito, fuori da dogana o problemi segnalati, Peek mostra «Nessun aggiornamento da…», ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Express One.» e propone «Chiedi aiuto a Express One».

## Domande frequenti su Express One

### Cosa significa «shipment not yet handed over to EXOHU»?

Il mittente ha creato la spedizione, ma Express One Hungary non ha ancora ricevuto il pacco. Se resta così per giorni, chiedi al negozio.

### Quanto tempo resta un pacco Express One in un punto ritiro?

Fino a una settimana a qualsiasi ora, afferma la pagina punti ritiro di Express One; gli articoli del blog indicano da 2 a 7 giorni a seconda del punto, con accesso 24 ore su 24 solo nei locker EXOBOX e Alzabox.

### Express One in Slovacchia o Slovenia è la stessa azienda?

No. Appartengono anch'esse ad Austrian Post ma sono società distinte; questa pagina e «Express One» in Peek riguardano la società ungherese.

:::sources
- [Express One: Parcel Tracking](https://expressone.hu/en/csomagkovetes) – fermate del corriere, call centre, chatbot
- [Express One: Tracking](https://tracking.expressone.hu/) – tipi di ID, Company ID, messaggio non trovato, 180 giorni, stati, lingue
- [Express One: Frequently asked questions](https://expressone.hu/en/gyik) – modifica, finestra 2 ore, sabato, contrassegno, danni, articolo errato
- [Express One: GYIK](https://expressone.hu/gyik) – limiti del contrassegno
- [Express One: Contact](https://expressone.hu/en/kapcsolat) – telefoni, e-mail, orari, modulo, centro logistico
- [Express One: Parcel point](https://expressone.hu/en/csomagpont) – 2.300 punti e locker, una settimana, 24/7, scelta al checkout
- [Express One: Home](https://expressone.hu/en) – Austrian Post, 700 corrieri, deviazione fino a un'ora prima
- [Express One: General Terms and Conditions](https://expressone.hu/downloads/General_Terms_and_Conditions_2026_08_15.pdf) – tentativi, giacenza, documento, costo modifica, reclami, NMHH, cancellazione dati
- [Express One: Merre jár a csomagom?](https://expressone.hu/blog/merre-jar-a-csomagom-csomagkovetes-es-ertesitesi-rendszerek-alkalmazasa) – preavviso dopo registrazione e arrivo, fasce di 4 e 2 ore, opzione SMS
- [Express One: Csomag átirányítás és a kézbesítés átütemezése](https://expressone.hu/blog/csomag-atiranyitas-es-a-kezbesites-atutemezese) – deviazione gratuita, 5 giorni al deposito di Budapest o in un punto ritiro
- [Express One: Minden, amit az EXOPOINT megoldásokról tudni érdemes](https://expressone.hu/blog/minden-amit-az-exopoint-megoldasokrol-tudni-erdemes) – orari di apertura, 3, 2 e 7 giorni
- [Express One: Csomagküldés csomagautomatába](https://expressone.hu/blog/csomagkuldes-csomagautomataba-miert-valasztjak-egyre-tobben) – 72 ore in locker
- [Express One: eBOX GYIK](https://expressone.hu/ebox/faq) – contenuto notifiche, nessuna per export, 250.000 Ft in contanti all'Exopoint
- [Austrian Post: Austrian Post Group acquires Hungarian parcel delivery provider](https://news.post.at/presse/en/post/id/2255390/) – controllata
- [Austrian Post: International subsidiaries](https://post.at/en/j/c/careers-austrian-post-subsidiaries-international) – società Express One in altri paesi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek
:::
