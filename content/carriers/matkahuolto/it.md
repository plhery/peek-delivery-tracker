---
title: Tracciamento Matkahuolto: stati, ritiro e contatti
description: Traccia un pacco Matkahuolto in Finlandia: codice spedizione, stati in inglese, codici di ritiro, giacenza, locker e contatti.
slug: tracciamento-matkahuolto
published: 2026-11-22
updated: 2026-11-22
---

Matkahuolto è un corriere finlandese con punti di ritiro e locker in tutta la Finlandia, inclusi alcuni K-Market e chioschi R-kioski. Puoi tracciare un pacco su matkahuolto.fi/tracking (in inglese) o nell’app Paketit con l’ID spedizione indicato nell’e-mail del negozio o nell’SMS di Matkahuolto, oppure incollarlo nel riquadro sopra.

## Numero di tracciamento Matkahuolto e dove trovarlo

Matkahuolto lo chiama ID spedizione (*lähetystunnus* in finlandese, *shipment ID* o *consignment ID* in inglese). Si trova nell’e-mail di spedizione del negozio e in ogni SMS o e-mail di Matkahuolto. Il codice di ritiro nell’avviso di arrivo è un’altra cosa: apre il locker oppure va mostrato alla cassa del punto di ritiro.

Matkahuolto non pubblica i propri formati. Queste sono le forme note al catalogo di Peek (cifre inventate):

| Numero | Che cos’è |
| --- | --- |
| `MH482910372FI` | `MH`, 9 cifre e `FI`. |
| `MA7394028156342019482` | `MA` seguito da 19 cifre. |
| `00373482910375618296` | Un codice a barre pacco di 20 cifre (SSCC). Il tracciamento lo trova anche su 18 cifre, senza lo `00`. |

I codici che iniziano con `JJFI` e i [numeri postali](guide:tracking-number-formats) che terminano in `FI` con lettere diverse da `MH` (`RR482910372FI`) appartengono a [Posti](carrier:posti), la posta finlandese. Se Matkahuolto non trova il numero, controlla il corriere nell’e-mail del negozio.

Spostare un pacco a un altro punto di ritiro genera un nuovo ID spedizione. Un pacco GLS affidato a Matkahuolto in Finlandia mostra il numero Matkahuolto nel tracciamento GLS come «Finland parcel number», con inizio `00`.

Nessun numero? Se sei registrato su Matkahuolto o usi l’app Paketit, i pacchi indirizzati al tuo telefono o e-mail compaiono lì; altrimenti il mittente può aggiungere i tuoi recapiti. Vedi [come trovare un numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di Matkahuolto

Il tracciamento Matkahuolto è disponibile in finlandese (predefinito), svedese e inglese, con data, ora e città per ogni scansione. Le diciture inglesi in ordine:

| Stato Matkahuolto | Cosa significa per te |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | Il mittente ha [creato la spedizione](guide:tracking-statuses); Matkahuolto non ha ancora il pacco. |
| `Consignment is being processed` | Scansionato in un terminal. |
| `The consignment is on its way` | In viaggio tra terminal. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Smistato all’ultimo terminal; la tappa successiva è il punto di ritiro. |
| `We will deliver the consignment to the pickup point during the day` | In viaggio oggi verso il tuo punto di ritiro. |
| `Your pick up point has changed` | Diretto a un altro punto, indicato nella riga. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Pronto per il ritiro, con il nome del punto e l’ultimo giorno utile. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | È stato inviato l’avviso di arrivo. Non indica uno spostamento. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Ritirato dal punto di ritiro o dal locker. |

**Durante la giornata.** Matkahuolto precisa che la prima fermata del giro potrebbe non essere la tua, ma il pacco dovrebbe essere pronto al ritiro in giornata; ricevi un avviso appena arriva.

## Punti di ritiro, locker e consegne a domicilio

**Ritiro.** Serve solo il codice di ritiro dell’avviso. Nessun documento richiesto, a meno che l’avviso indichi consegna a mano (di solito alcolici o documenti). Chiunque abbia il codice può ritirare, tranne nei locker esterni gestiti via app, che si aprono solo dal telefono del destinatario nell’app Paketit. Se l’avviso non indica un locker, chiedi alla cassa del punto di ritiro.

**Avviso di arrivo.** Prima notifica dell’app Paketit, poi SMS o WhatsApp ed e-mail se fornita dal mittente.

**Giacenza.** 5 giorni, poi il pacco torna al mittente; 14 giorni con avviso cartaceo se mancano telefono ed e-mail. Puoi acquistare da 1 a 16 giorni extra nell’app o, se registrato, sul sito web, salvo blocco del mittente. Poiché la pagina GLS Finlandia dà 10 giorni ai suoi pacchi, fa sempre fede l’ultimo giorno indicato nel tuo avviso.

**Cambiare punto.** Una volta gratis nell’app: prima che Matkahuolto abbia il pacco secondo le FAQ inglesi, oppure al primo avviso di arrivo secondo la pagina svedese; non durante il transito. Dopo l’avviso puoi acquistare un trasferimento.

**Consegna a domicilio.** Gli *Home Parcels* arrivano nei giorni feriali dalle 8:00 alle 18:00 (ora finlandese); i *Business Parcels* e i pacchi [FedEx](carrier:fedex) nei giorni feriali dalle 8:00 alle 16:00 (ora finlandese), senza preavviso. L’avviso indica il giorno previsto, spostabile su uno dei cinque giorni lavorativi successivi il giorno stesso dell’avviso. Il pacco viene lasciato in un punto sicuro e riparato vicino alla porta; se non è possibile e non sei raggiungibile, va a un punto di ritiro vicino il giorno lavorativo successivo.

**Åland.** Dal 1° ottobre 2026 Åland Post consegna i pacchi Matkahuolto sulle isole Åland, con uffici postali, agenti, locker e consegne a domicilio integrati nella rete.

**Resi.** Ottieni un codice di attivazione con il tasto `Palauta` nell’app o sul sito, scrivilo sul pacco con nome e numero di telefono e consegnalo a un locker o punto di ritiro.

## Pacco Matkahuolto in ritardo, fermo o danneggiato

La maggior parte dei pacchi arriva il giorno dopo la spedizione, dice Matkahuolto, e tutti entro tre giorni dalla presa in carico (tre giorni lavorativi nelle FAQ finlandesi). I pacchi dall’estero possono transitare da altri corrieri e impiegare poco più di una settimana per raggiungere Matkahuolto.

**Nessun aggiornamento.** Il tracciamento può fermarsi in attesa di partenza o verifica dell’imballaggio. Se continua a non muoversi, Matkahuolto chiede di contattare il mittente. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Nessun avviso di arrivo.** Spesso un errore nei recapiti: controlla lo spam e chiedi al mittente di correggerli.

**Danneggiato o smarrito.** Avvisa subito negozio o mittente con foto. Il reclamo a Matkahuolto spetta all’azienda mittente, o al mittente tra privati. Segnala danni visibili al ritiro; altrimenti per iscritto entro 7 giorni, o entro 30 giorni come consumatore. La responsabilità di Matkahuolto è 20 € al kg, o il valore della merce se inferiore.

## Servizio clienti e telefono Matkahuolto

Recapiti pubblicati sulle pagine di Matkahuolto:

- **Servizi pacchi:** 0600 11300, dal lunedì al venerdì 8:00–18:00 (ora finlandese), 2,49 €/min più tariffa locale.
- **Supporto servizi pacchi:** +358 20 33 6161, dal lunedì al venerdì 8:00–10:00 e 14:00–16:00 (ora finlandese), tariffa locale, per locker, locker via app e consegne a domicilio.
- **Chat:** con il chatbot Mauno sulla [pagina assistenza pacchi](https://www.matkahuolto.fi/customer-service/parcels) o nell’app Paketit, dove un operatore può subentrare.
- **Modulo feedback:** in finlandese su matkahuolto.fi/palautelomake. Non è pubblicata alcuna e-mail per destinatari.

> Matkahuolto avverte contro messaggi falsi, mittenti contraffatti e siti clone come matkahuoltoo.fi. Specifica di non gestire mai pagamenti né agire da gateway di pagamento; l’unico link di pagamento inviato va ai mittenti per costi di reso. Verifica qualsiasi messaggio inserendo l’ID spedizione nel tracciamento o nell’app Matkahuolto; se hai pagato, chiama la banca.

## Tracciare un pacco Matkahuolto con Peek

Per un codice `MH…FI`, [Peek](/) interroga Matkahuolto, [Posti](carrier:posti) e [Chronopost](carrier:chronopost) e mostra «Matkahuolto ha questo pacco.» se lo conosce solo Matkahuolto; se lo conosce anche Posti, scegli tu. Un codice `MA` va solo a Matkahuolto. Per un codice a barre di 18 o 20 cifre con `64` (Finlandia) o `73` (Svezia) dopo la prima cifra (o dopo `00` e una cifra), Peek interroga anche Matkahuolto insieme agli altri corrieri compatibili e assegna Matkahuolto se solo lei lo conosce. Se [Posti](carrier:posti) conosce anche un codice `64`, o Bring un `73`, Peek sceglie quel corriere: passa a Matkahuolto con «Cambia». Se lo conosce anche un altro corriere come PostNord, scegli tu. Altri codici non vengono proposti a Matkahuolto, quindi sceglila con «Cambia» (o «Cambia corriere»). Un link di tracciamento `matkahuolto.fi` (`/seuranta`, `/tracking` o `/uppfoljning`) con `parcelNumber=` assegna qualunque codice a Matkahuolto, ma la ricerca accetta solo `MH…FI`, `MA` e numeri postali o SSCC/GS1 con cifra di controllo valida.

Le scansioni di Matkahuolto non hanno fuso orario: Peek le legge sull’ora della località della scansione (ora finlandese in Finlandia; una riga senza luogo su quella di una riga vicina, altrimenti sull’ora finlandese) e le mostra nel tuo fuso orario («Orari nel tuo fuso orario:»). Gli stati restano nell’inglese di Matkahuolto. Peek mostra punto di ritiro o locker con città e via, ma non riceve data prevista né peso, esclude servizi aggiuntivi e codici postali e non mostra separatamente l’ultimo giorno di ritiro (presente nel testo dello stato e nell’avviso). Per un codice `MH…FI` seguito da un paese diverso dalla Finlandia, Peek interroga anche le poste di quel paese quando ne conosce una (non per i Paesi Bassi né per Åland) e passa a loro appena mostrano una scansione datata non più vecchia dell’ultima di Matkahuolto; da lì ne segue il ritmo e conserva la cronologia di Matkahuolto. Per i codici `MA` e i codici a barre, Peek non segue la spedizione presso un altro corriere.

Peek controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 secondo l’orologio dell’ultima scansione (ora finlandese) il giorno in cui entra «In consegna», mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive, quando il tracciamento Matkahuolto risponde. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un invio in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Matkahuolto.» e «Chiedi aiuto a Matkahuolto». Peek segue oltre 4.500 corrieri, ma non può contattare Matkahuolto né il mittente per te.

## Domande sul tracciamento Matkahuolto

### Matkahuolto è affidabile?

Sì, Matkahuolto è un vero corriere finlandese. Considera truffa qualsiasi messaggio che chieda un pagamento a suo nome: Matkahuolto dichiara di non gestire mai pagamenti.

### Esiste un tracciamento Matkahuolto in inglese?

Sì. È disponibile su matkahuolto.fi/tracking, e la lingua dell’app Paketit si cambia nelle impostazioni.

### Posso tracciare un pacco Matkahuolto senza il codice?

Sì, se sei registrato o usi l’app Paketit e il mittente ha inserito telefono o e-mail: il pacco compare lì. Altrimenti chiedi l’ID spedizione al negozio.

### Quanto impiega un pacco Matkahuolto?

Di regola 1–2 giorni lavorativi verso locker e punti di ritiro, e al massimo tre giorni da quando Matkahuolto lo riceve (tre giorni lavorativi secondo le FAQ finlandesi). I pacchi dall’estero possono impiegare poco più di una settimana per raggiungerla.

:::sources
- [Matkahuolto: How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – codice ritiro, documento, avvisi, cambio punto, consegne a domicilio, danni, telefoni
- [Matkahuolto: Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – consegne a domicilio, mancata consegna, telefoni
- [Matkahuolto: Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – viste di tracciamento, tempi, pause, contattare il mittente
- [Matkahuolto: Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1–2 giorni lavorativi, avvisi, 1–16 giorni extra di giacenza
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 e 14 giorni, proroghe
- [Matkahuolto: Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – 5 giorni di giacenza, reso al mittente
- [Matkahuolto: Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – rete, locker via app
- [Matkahuolto: Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – chi reclama, termini, foto
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – termini, 20 € al kg
- [Matkahuolto: About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – messaggi falsi, mittente autentico, ID spedizione
- [Matkahuolto: Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – resi con codice di attivazione
- [Matkahuolto: Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – lingua app, chat
- [Matkahuolto: Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – chatbot Mauno
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – costo linea, tre giorni lavorativi, Business Parcels e FedEx 8:00–16:00, modulo
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – poco più di una settimana dall’estero, link di pagamento resi per mittenti
- [Matkahuolto: Track the parcel](https://www.matkahuolto.fi/tracking) – tracciamento in inglese, finlandese e svedese, testi di stato
- [Matkahuolto: Ta emot paket](https://www.matkahuolto.fi/paket/ta-emot-paket) – in svedese: cambio punto gratuito al primo avviso
- [Matkahuolto: Spåra ditt paket](https://www.matkahuolto.fi/kundservice/paket/spara-ditt-paket) – in svedese: tutti i pacchi entro tre giorni
- [Matkahuolto: Åland Post blir Matkahuoltos nya transportpartner på Åland](https://www.matkahuolto.fi/news/%C3%85land%20Post%20blir%20Matkahuoltos%20nya%20transportpartner%20p%C3%A5%20%C3%85land) – in svedese: Åland Post su Åland dal 1° ottobre 2026
- [GLS Finlandia: Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – punti Matkahuolto, Finland parcel number, 10 giorni
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
