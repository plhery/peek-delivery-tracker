---
title: Tracking DPD Austria: stati, punti Pickup e contatti
description: Traccia un pacco DPD in Austria: numero a 14 cifre e PIN, stati myDPD, giacenza nei punti Pickup, dogana e assistenza DPD Austria.
slug: tracking-dpd-austria
published: 2026-11-23
updated: 2026-11-23
---

DPD Austria è un corriere privato con sede a Leopoldsdorf. All'interno dell'Austria consegna di norma entro un giorno lavorativo dal momento in cui riceve il pacco dal negozio. Traccia una spedizione su [myDPD](https://www.mydpd.at/), il sito e l'app di DPD Austria, con il numero pacco a 14 cifre ricevuto nell'e-mail del negozio, oppure incollalo nel box qui sopra.

## Numero pacco DPD Austria e PIN

Il numero pacco DPD ha 14 cifre. Te lo invia il negozio oppure DPD via e-mail se il negozio ha fornito il tuo indirizzo; con un account myDPD lo trovi sotto «My parcels». Il PIN (*Parcel Information Number*) si trova sull'avviso bianco e rosso lasciato da DPD in caso di assenza. myDPD accetta entrambi; Peek richiede il numero pacco. I formati riconosciuti da Peek:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `06204819273645` | 14 cifre che iniziano da 0620 a 0630, 0632 a 0635, 0643 a 0645 o 0649: un numero pacco emesso da un deposito DPD Austria. |
| `01904819273645` | 14 cifre che iniziano da 0101 a 0199: Peek lo legge prima come numero [DPD Germany](carrier:dpd-de). DPD Austria può comunque consegnarlo (vedi sotto). |

Se l'etichetta riporta una lettera o una cifra in più dopo le 14 cifre, omettila. Se l'e-mail del negozio cita [Austrian Post](carrier:austrian-post), GLS Austria o un altro corriere, il numero appartiene a loro. Nessun numero? Leggi [dove trovare il numero di tracking](guide:find-tracking-number).

## Significato degli stati di tracking DPD Austria

myDPD indica ogni scansione con il relativo deposito, in tedesco o in inglese. Nell'ordine tipico di un pacco (una *Paketstation* è un armadietto automatico):

| myDPD mostra | Cosa vuol dire per te |
| --- | --- |
| `Auftragsdaten übermittelt` | Il mittente ha inviato i dati a DPD, ma non ancora il pacco. Le FAQ inglesi di DPD lo chiamano «Order data transmitted to DPD». |
| `Paket unterwegs` | DPD ha il pacco: ritirato al primo deposito o scansionato in un hub lungo il percorso. |
| `Im Paketzustellzentrum` | Nel deposito incaricato della consegna. Segue la consegna. |
| `Unfortunately, parcel was loaded incorrectly and will be delivered soon.` | Caricato sul mezzo sbagliato. Un altro deposito lo scansionerà prima della consegna. |
| `In Zustellung` | Con l'autista, in consegna oggi. |
| `Empfänger leider nicht angetroffen.`, `Unfortunately, consignee not present. Notification was left.` | Nessuno in casa. L'autista ha lasciato un avviso. |
| `Unfortunately, parcel was not delivered.` | Non consegnato in questo giro. Rientra in deposito per un nuovo tentativo. |
| `In Zustellung an Pickup Paketstation`, `In delivery to Pickup parcel shop` | Dopo un tentativo fallito, l'autista lo porta a un locker o negozio Pickup. |
| `Paket wurde in der Pickup Paketstation hinterlegt`, `Parcel was handed over to Pickup parcel shop`, `Takeover by Pickup parcel shop` | In attesa al locker o negozio Pickup. DPD invia un codice di ritiro via e-mail o SMS, con l'indirizzo per il locker. |
| `Paket durch Empfänger abgeholt` | Ritirato dal punto Pickup. |
| `Paket wurde erfolgreich abgestellt` | Lasciato nel luogo consentito con autorizzazione di deposito (*deposit okay*), come il garage. |
| `Parcel was not picked up` | Non ritirato in tempo dal negozio Pickup. Il rientro al mittente parte di solito nello stesso minuto. |
| `Parcel will be returned to the sender.` | Il pacco avvia il rientro al mittente. |
| `Parcel was picked up by driver from Pickup parcel shop` | Un autista lo ha ritirato da un negozio Pickup: all'inizio è la spedizione consegnata dal mittente; dopo la giacenza è il ritorno al mittente. |

## Consegna DPD in Austria: Predict, modifiche e punti Pickup

**Predict.** Se il negozio comunica a DPD la tua e-mail o il cellulare, DPD ti avvisa in anticipo del giorno previsto. Il giorno della consegna Live-Tracking mostra una fascia oraria e una mappa aggiornata ogni 15 minuti. La fascia non è garantita perché traffico e meteo possono variarla. Per un pacco dall'estero segui la stima del negozio.

**Modificare la consegna.** Registrandoti su myDPD puoi modificare la consegna fino a poco prima dell'arrivo con numero pacco o PIN: altro giorno o indirizzo, un vicino indicato da te, un negozio o locker Pickup oppure un'autorizzazione di deposito (ad esempio in terrazza). Con un account puoi anche impostare un punto Pickup preferito o un permesso di deposito permanente per tutti i pacchi.

**Nessuno in casa.** DPD può consegnare il pacco a chiunque lo accetti presso l'abitazione o l'ufficio, senza controllare il documento. Dopo un primo tentativo fallito, DPD può lasciarlo a un vicino o al negozio/locker Pickup più vicino, indicandoti nome e indirizzo. L'avviso cartaceo e myDPD mostrano dove si trova. Vedi [consegne mancate](guide:missed-delivery).

**Ritiro.**
- Negozio Pickup: 7 giorni di calendario, con promemoria dopo 3 e 5 giorni. Mostra il codice di ritiro dell'e-mail o SMS di DPD, oppure documento d'identità e firma. DPD non può rinviare il codice e myDPD non lo visualizza. Valgono gli orari del negozio.
- Locker Pickup (*Paketstation*): 5 giorni di calendario, con promemoria dopo 3 giorni. Serve il codice ricevuto via e-mail o SMS. I locker sono aperti 24 ore su 24.

Scaduto il termine, il pacco torna al mittente: chiedi al negozio una nuova spedizione. Vedi [pacchi non ritirati](guide:uncollected-parcel) e [restituito al mittente](guide:returned-to-sender).

## Pacco DPD in ritardo, perso, danneggiato o in dogana

Per un rimborso o una nuova spedizione rivolgiti al negozio, che detiene il contratto con DPD.

**Nessun aggiornamento.** `Auftragsdaten übermittelt` per giorni indica che il negozio non ha ancora affidato il pacco a DPD: DPD consiglia di contattare il mittente. Se Live-Tracking resta fermo per diverse ore nel giorno della consegna, contatta DPD con il numero pacco. Vedi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Consegnato ma non ricevuto.** Come richiesto da DPD, controlla prima il luogo di deposito autorizzato e il vicino segnato sull'avviso. Ancora nulla? Usa il modulo DPD per destinatari («Where was my parcel delivered/handed over.») e avvisa il negozio. Altro in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso.** Se nessuno trova il pacco, contatta l'assistenza clienti DPD e il negozio. Vedi [pacchi smarriti](guide:lost-parcel).

**Danneggiato.** DPD apre pratiche per danni solo su richiesta del mittente: avvisa quindi il negozio, che reclama presso DPD e offre rimborso o sostituzione secondo le proprie condizioni. Le condizioni DPD prevedono la segnalazione scritta entro sei giorni di calendario al massimo per danni occulti e subito dopo la consegna per danni visibili. Vedi [pacchi danneggiati](guide:damaged-parcel).

**Dogana.** Gli acquisti online da fuori UE prevedono dal 1° luglio 2026 un dazio UE di 3 € per articolo sui pacchi fino a 150 € e dal 1° novembre 2026 una tariffa di gestione dell'Unione di 2 € per articolo di qualunque valore. Un articolo raggruppa beni con stessa classificazione tariffaria, descrizione e, se richiesta, origine. Le pagine DPD Austria non spiegano se e come addebita tali importi: chiedi al negozio se erano inclusi nel prezzo. Vedi [pacchi fermi in dogana](guide:customs).

## Contatti e assistenza clienti DPD Austria

Peek non può contattare DPD o il negozio per te. Sulle pagine di DPD Austria:

- **Telefono:** 05 03 73 420 (+43 5 03 73 420 dall'estero), linea privati, lunedì–venerdì dalle 7:00 alle 18:00 (ora austriaca).
- **Online:** [modulo di contatto DPD Austria per destinatari](https://www.dpd.com/at/en/support/contact-form-consignee/) con numero pacco o PIN, nome ed e-mail. DPD chiede di usare un solo canale per volta per non rallentare la risposta.
- **Posta:** DPD Direct Parcel Distribution Austria GmbH, Arbeitergasse 46, 2333 Leopoldsdorf.

> Circolano e-mail ed SMS falsi a nome DPD. DPD raccomanda di non pagare, non aprire allegati, non cliccare sui link di accesso ed eliminare il messaggio. In caso di perdita economica, DPD consiglia di sporgere denuncia alla polizia. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco DPD Austria con Peek

Incolla il numero pacco a 14 cifre in [Peek](/). Non serve il codice postale.

Se il numero rientra negli intervalli dei depositi DPD Austria, Peek interroga DPD Austria tra i primi corrieri, ovunque ti trovi, e mostra «DPD Austria ha questo pacco.» non appena myDPD lo conosce e l'ultima scansione è in Austria. Se DPD non lo elenca ancora, compare «non ancora trovato · Peek continua a controllare». Aggiungilo comunque: la scheda indica «Probabilmente DPD Austria» e «In attesa della prima scansione di DPD Austria» mentre Peek continua a verificare.

Altri numeri a 14 cifre non indirizzano subito a DPD Austria perché appartengono a più filiali DPD e ad altri corrieri. Se il tuo pacco è con DPD Austria, premi «Cambia» e scegli DPD Austria (presente anche come «DPD Österreich» o «DPD AT»), oppure usa «Cambia corriere» su un pacco già aggiunto. Un link myDPD (`?p=` o `/meine-pakete?ipn=`) o dpdgroup.com/at/ con `parcelNumber=` identifica subito DPD Austria.

**Da un'altra filiale DPD.** Per un pacco da [DPD Netherlands](carrier:dpd-nl), DPD Belgium o DPD Hungary, una volta che DPD lo registra in un deposito di consegna in Austria o oltre, Peek interroga DPD Austria con lo stesso numero e lo segue lì appena myDPD mostra una scansione altrettanto recente. Fino ad allora la scheda indica già DPD Austria come corriere incaricato della consegna. Un pacco di [DPD Germany](carrier:dpd-de) resta sotto DPD Germany: quando arriva in Austria, Peek legge anche myDPD e ne mostra le scansioni se sono più dettagliate.

**Cosa vedi.** Lo stato, la cronologia nei testi di myDPD con città e Paese di ogni deposito e l'ora di consegna in ora austriaca (le scansioni in altri fusi mantengono la propria ora locale). Un pacco al punto Pickup appare come «Pronto per il ritiro». Peek omette nomi, indirizzi, fascia oraria, punto Pickup, firmatario e opzioni di consegna e non mostra una data prevista; myDPD non fornisce il peso. Per il punto Pickup controlla myDPD o l'avviso, per il codice l'e-mail o l'SMS di DPD.

Finché il pacco è aperto in Peek, o nel giorno della consegna con le notifiche attive, Peek lo controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00, ora austriaca, il giorno in cui DPD lo scansiona in consegna. Puoi scegliere le notifiche tra «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora, e ogni 6 ore dopo sette giorni senza novità su «Etichetta creata». Se il pacco resta quattro giorni senza una nuova riga su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …» con il giorno di quella riga e un link «Chiedi aiuto a DPD Austria». Per modificare la consegna usa myDPD.

## Domande sul tracking DPD in Austria

### Quanto impiega DPD a consegnare in Austria?

Di solito un giorno lavorativo in Austria dal momento in cui DPD riceve il pacco dal negozio. Prima di allora myDPD può mostrare `Auftragsdaten übermittelt`.

### Per quanto tempo DPD tiene un pacco al negozio o locker Pickup?

7 giorni di calendario in un negozio Pickup e 5 in un locker Pickup (*Paketstation*). Poi torna al mittente.

### Che cos'è il codice PIN DPD?

Il *Parcel Information Number* stampato sull'avviso lasciato da DPD se non sei in casa. myDPD lo accetta; in Peek usa il numero pacco a 14 cifre.

### Cosa significa Auftragsdaten übermittelt?

Il negozio ha trasmesso a DPD i dati del pacco, ma DPD non ha ancora il pacco. Se resta così per giorni, chiedi al negozio.

### Qual è il numero di telefono di DPD Austria?

05 03 73 420 per i clienti privati, dal lunedì al venerdì dalle 7:00 alle 18:00 (ora austriaca).

:::sources
- [DPD Austria: Receiving parcels](https://www.dpd.com/at/en/receiving-parcels/) – Predict, Live-Tracking, modifiche, deposito autorizzato, locker
- [DPD Austria: myDPD](https://www.mydpd.at/) – sito e app di tracking
- [DPD Austria: FAQ receiving parcels](https://www.dpd.com/at/en/support/faq/faq-receiving-parcels/) – numero pacco, PIN, un giorno lavorativo, modifiche
- [DPD Austria: FAQ Pickup](https://www.dpd.com/at/en/support/faq/pickup/) – giacenza, codice ritiro, documento
- [DPD Austria: General terms and conditions](https://www.dpd.com/at/en/gtc/) – giacenza, promemoria, reso
- [DPD Austria: General terms and conditions, national (PDF)](https://www.dpd.com/wp-content/uploads/sites/224/2022/01/AGB0447_AGBs_DPDA_national_en.pdf) – vicino o Pickup dopo primo tentativo, Predict, fascia, segnalazione danni
- [DPD Austria: What does the status "Order data transmitted to DPD" mean?](https://www.dpd.com/at/en/faq/was-bedeuter-der-status-auftragsdaten-an-dpd-uebermittelt/) – chiedere al mittente
- [DPD Austria: Status not changing in live tracking](https://www.dpd.com/at/en/faq/der-paketstatus-meines-pakets-hat-sich-schon-lange-nicht-mehr-veraendert-woran-kann-das-liegen/) – diverse ore senza variazioni
- [DPD Austria: Parcel shown as delivered but not delivered](https://www.dpd.com/at/en/faq/ein-paket-das-mir-als-zugestellt-angezeigt-wird-ist-noch-nicht-geliefert-worden-woran-liegt-das/) – verifiche iniziali
- [DPD Austria: My parcel can't be located](https://www.dpd.com/at/en/faq/mein-paket-ist-unauffindbar-was-soll-ich-tun/) – assistenza clienti
- [DPD Austria: Parcel arrives damaged](https://www.dpd.com/at/en/faq/was-ist-zu-tun-wenn-ein-paket-beschadigt-beim-empfanger-ankommt/) – prima il mittente
- [DPD Austria: Contact](https://www.dpd.com/at/en/support/contact/) – telefono, orari
- [DPD Austria: Contact form for consignees](https://www.dpd.com/at/en/support/contact-form-consignee/) – un solo canale per volta
- [DPD Austria: Security](https://www.dpd.com/at/en/security/) – e-mail ed SMS falsi
- [DPD Austria: Legal notice](https://www.dpd.com/at/en/legal-notice/) – azienda, indirizzo, numero dall'estero
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026, tariffa di 2 € dal 1° novembre 2026
- [Commissione europea: guida al dazio di 3 € e alla tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek, testi myDPD, intervalli depositi, passaggi di mano
:::
