---
title: Tracciamento Mainfreight: codici merce e stati
description: Traccia una spedizione Mainfreight in Nuova Zelanda, Australia, Stati Uniti o Europa: numeri, stati in italiano, dogana, reclami e Peek.
slug: tracciamento-mainfreight
published: 2026-11-22
updated: 2026-11-22
---

Mainfreight trasporta merci via strada, aria e mare. Il catalogo di Peek lo elenca per Nuova Zelanda (il suo paese d’origine), Australia e Stati Uniti; il suo tracker copre inoltre il trasporto stradale europeo e i movimenti container. Per tracciare merce o un collo voluminoso affidato a Mainfreight, incolla nel riquadro in alto il numero fornito da chi ha prenotato il trasporto. Il tracker ufficiale di Mainfreight è su www.mainfreight.com.

## Numero di tracciamento Mainfreight: come è fatto

Con cifre inventate:

| Numero (inventato) | Cos’è |
| --- | --- |
| `48291736542` | 11 cifre: una spedizione stradale in Nuova Zelanda, Australia o Stati Uniti, l’unico formato che il catalogo di Peek collega a Mainfreight. |
| Lettere e cifre | Un numero di pratica (`S` e 8 cifre per aereo/mare, `CJ` e 9 cifre per container), house bill o riferimento del mittente. Scelto Mainfreight, Peek interroga codici da 4 a 40 caratteri. |
| Master bill | Le 11 cifre di una lettera di vettura aerea o una polizza marittima principale: Peek trova la pratica aerea o marittima se vi corrisponde un solo invio. |

Il catalogo di Peek riconosce Mainfreight anche come Mainfreight Group, Mainfreight Transport e Mainfreight Air & Ocean.

**Dove trovarlo.** Nell’email di spedizione o nella pagina dell’ordine; per merce tra aziende chiedilo al mittente. Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Altri corrieri con 11 cifre.** Undici cifre corrispondono anche a [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), DHL eCommerce Poland, PostLogistics e altri. Se la tua email indica uno di questi, traccialo lì. Altri dettagli in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Mainfreight

**Trasporto stradale in Nuova Zelanda e Australia:**

| Mainfreight scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Information Received` | Mainfreight ha i dati, non ancora la merce. | «Etichetta creata» |
| `Pickup Allocated to Driver` | Un autista è incaricato del ritiro; la merce è ancora dal mittente. | «Etichetta creata» |
| `Picked Up` | Ritirata dal mittente. | «Preso in carico» |
| `In Transit` | In viaggio. | «In transito» |
| `At Delivery Depot` | Presso il deposito che effettuerà la consegna. | «In transito» |
| `On Delivery Vehicle` | Sul camion per la consegna. | «In consegna» |
| `Delivered` | Consegnata. | «Consegnato» |

**Trasporto stradale negli Stati Uniti.** I passaggi americani aggiungono la località dopo la dicitura; Peek la mostra accanto al passaggio.

| Mainfreight scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Booked` | Trasporto prenotato. | «Etichetta creata» |
| `Pickup assigned to driver` | Autista assegnato al ritiro. | «Etichetta creata» |
| `Goods picked up` | Merce ritirata dal mittente. | «Preso in carico» |
| `Departed from Terminal`, `Arrived at Terminal` | Partita da un terminal o arrivata a un terminal. | «In transito» |
| `On drivers vehicle en-route` | Sul camion diretto al tuo indirizzo. | «In consegna» |
| `Goods delivered` | Consegnata. | «Consegnato» |
| `Collected by Customer` | Ritirata dal cliente al terminal. | «Consegnato» |

**Spedizioni aeree e marittime:**

| Mainfreight scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Booking Confirmation Sent` | Prenotazione confermata. | «Etichetta creata» |
| `Pickup Cartage Advised` | Camion programmato per il ritiro. | «Etichetta creata» |
| `Pick up Cartage Complete` | Merce ritirata per l’esportazione. | «Preso in carico» |
| `Origin Customs Cleared` | Dogana export completata prima della partenza. | «In transito» |
| `Departure from First Load Port`, `Arrival at Final Discharge Port` | Partita dal primo porto o aeroporto, o arrivata all’ultimo. | «In transito» |
| `Customs Clearance Commenced` | Sdoganamento import avviato. | «In dogana» |
| `Customs Clearance Finalised` | Sdoganata e di nuovo in movimento. | «In transito» |
| `Cargo Available` | Svincolata a destinazione prima della consegna. | «In transito» |
| `Cargo Delivered`, `Full Container Delivered` | Consegnata. | «Consegnato» |

Partenze e arrivi pianificati contrassegnati come stimati (`(Estimated)`) non sono ancora avvenuti e Peek li omette.

**Altri passaggi.** Gli spostamenti di container tra siti, come `Picked Up (Site Movement)`, mostrano «In transito», mentre `Final Delivery (Empty to Customer)` mostra «Consegnato». Sul trasporto stradale europeo, `Order received` mostra «Etichetta creata», `Shipment loaded into line haul truck` «In transito» e `Out for delivery` «In consegna». Senza singoli passaggi, Peek usa lo stato generale del tracker come `Created` o `Final Delivery`.

Il codice di Peek non conosce diciture specifiche Mainfreight per consegna mancata, anomalia o reso. Altri testi seguono le regole generali di Peek o appaiono così come scritti senza cambiare fase. Vedi [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Come consegna Mainfreight

Peek non ha potuto leggere il sito web di Mainfreight, quindi questa pagina non fornisce tempi di consegna, appuntamenti, tentativi o regole per il ritiro in deposito. Chiedi a chi ha prenotato il trasporto come e quando avverrà la consegna.

Il codice di Peek non affida le spedizioni Mainfreight ad altri corrieri. Il tracker può dare un giorno previsto di consegna per spedizioni aeree, marittime e trasporto stradale europeo, che Peek mostra fino all’uscita in consegna. Non fornisce a Peek prova di consegna né nome di chi ha ritirato la merce.

**Dogana.** Per merci in ingresso in Nuova Zelanda, NZ Customs precisa:

- Gli articoli acquistati su siti esteri fino a 1.000 NZ$ non pagano dazi e il venditore estero riscuote la GST, salvo facciano parte di una spedizione unica superiore a 1.000 NZ$. Tabacco e alcol pagano dazi e GST a prescindere dal valore.
- La GST è pari al 15% del valore più eventuali dazi, nolo e assicurazione. Alle importazioni non esenti si applica il «Customs and MPI Goods Levy»; il broker o spedizioniere lo versa e può addebitartelo.
- Le spedizioni da 1.000 NZ$ o più richiedono un Customs Number e la dogana non svincola la merce prima del pagamento degli oneri.

Altrove, vedi [pacchi fermi in dogana](guide:customs) e chiedi a chi ha prenotato Mainfreight chi sostiene gli oneri.

## Spedizione Mainfreight in ritardo, ferma, danneggiata o smarrita

**Ancora nessun dato.** Una spedizione salvata sotto Mainfreight che il tracker non elenca ancora mostra «In attesa di Mainfreight» mentre Peek continua a chiedere. Undici cifre senza risposta da alcun corriere mostrano «Nessuna cronologia di tracciamento per ora». Controlla le cifre e chiedi al mittente se Mainfreight ha già ritirato la merce.

**Nessun movimento.** Dopo quattro giorni senza nuovi passaggi su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Mainfreight.», con il link «Chiedi aiuto a Mainfreight» al modulo contatti solo in quell’avviso. Chiedi spiegazioni al negozio o al mittente. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Danneggiata, incompleta o smarrita.** Avvisa prima il negozio o il mittente che ha prenotato il trasporto. In Nuova Zelanda e Australia valgono le condizioni di Mainfreight e la legge locale. Per il trasporto stradale interstatale negli Stati Uniti, la legge federale prevede:

- Il vettore risponde della perdita o del danno effettivo; la responsabilità può essere limitata a un valore dichiarato dal mittente o concordato per iscritto. Non può concedere meno di 9 mesi per presentare reclamo né meno di 2 anni per agire in giudizio dal rifiuto scritto di una qualsiasi parte del reclamo.
- Il reclamo è una richiesta scritta che identifica la spedizione, attribuisce la responsabilità al vettore e indica un importo determinato o determinabile. Una riserva di danno o ammanco sulla ricevuta di consegna non basta da sola. Il vettore può richiedere polizza di carico, prova dei costi di trasporto e fattura.
- Il vettore conferma per iscritto il ricevimento entro 30 giorni e paga, rifiuta o formula un’offerta ferma entro 120 giorni; altrimenti aggiorna per iscritto a quella scadenza e poi ogni 60 giorni.

Se risulta `Delivered` ma non è arrivato nulla, vedi [consegnato ma non ricevuto](guide:delivered-not-received). Altri consigli in [pacco danneggiato](guide:damaged-parcel) e [pacco smarrito](guide:lost-parcel).

## Assistenza clienti e contatti Mainfreight

Peek non ha potuto leggere il sito web di Mainfreight, quindi questa pagina non elenca telefono, email o filiali: Peek riporta solo contatti letti direttamente sulle pagine ufficiali del corriere.

Rivolgiti prima a chi ha prenotato il trasporto: il negozio o fornitore per un ordine, altrimenti l’azienda mittente, che detiene la pratica e può intervenire presso Mainfreight.

## Tracciare una spedizione Mainfreight con Peek

Da Nuova Zelanda, Australia o Stati Uniti, Peek interroga Mainfreight su codici di 11 cifre durante l’aggiunta, insieme a GLS France, GLS Germany, GLS Switzerland e PostLogistics («Verifica dei servizi di tracciamento…») e indica Mainfreight se solo Mainfreight trova la spedizione; se più corrieri la conoscono, Peek ti chiede di scegliere. Da altri paesi non interroga Mainfreight automaticamente all’aggiunta: selezionalo manualmente.

Nessun altro codice indica automaticamente Mainfreight a Peek. Incollalo comunque: Peek segue oltre 4.500+ corrieri. Cambia poi corriere e seleziona Mainfreight (per 11 cifre appare tra i compatibili, altrimenti cercalo per nome). Un link al tracker di Mainfreight (mainfreight.com/tracking con `trackingnumber=` o mainfreight.com/track/…) con il numero individua subito Mainfreight («Corriere rilevato»).

Peek non richiede account né CAP. Se la ricerca di Mainfreight non trova 11 cifre, Peek prova in parallelo i servizi neozelandese, australiano e statunitense; se più di uno conosce il numero, Peek non può distinguere quale sia la tua spedizione. Per un house bill, un master bill o un riferimento del mittente, Peek indica il codice principale di Mainfreight come «Mainfreight lo traccia con il numero».

Peek mostra lo stato, ogni passaggio nella dicitura di Mainfreight con il luogo e il peso nelle tue unità se disponibile. Esclude contatti di filiale, CAP, riferimento mittente e documenti. «Apri il sito Mainfreight» apre il numero sul tracker di Mainfreight. Se il tracker non è raggiungibile o non leggibile, Peek interroga [servizi di tracciamento](guide:universal-tracker) e mostra la loro cronologia con le loro parole.

I passaggi stradali con fuso orario neozelandese, australiano o statunitense (`NZDT`, `AEST`, `PST` ecc.) indicano l’orario esatto. Gli altri non hanno fuso: Peek li legge sull’ora del luogo o dei passaggi vicini quando la conosce, altrimenti sull’ora della Nuova Zelanda (così per il trasporto europeo e le tappe aeree e marittime, il cui orario può allora scostarsi di diverse ore), e li mostra nel tuo fuso orario (la cronologia termina con «Orari nel tuo fuso orario:» seguito dal nome).

Mentre tieni la spedizione aperta sullo schermo — oppure il giorno della consegna con notifiche attive — Peek controlla direttamente Mainfreight fino a ogni 10 minuti, e dalle 6:00 sull’ora dell’ultima scansione di Mainfreight ogni 2 minuti il giorno in cui Mainfreight la segnala in consegna, finché il suo tracker risponde. Altrimenti, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Mainfreight, fissare una consegna o aprire un reclamo al posto tuo.

## Domande sul tracciamento Mainfreight

### Come è fatto un numero di tracciamento Mainfreight?

Per il trasporto stradale in Nuova Zelanda, Australia e Stati Uniti è un numero di 11 cifre (come `48291736542`). Numeri di pratica, house bill e riferimenti del mittente combinano lettere e cifre: scegli Mainfreight manualmente in Peek.

### Cosa significa «At Delivery Depot» su Mainfreight?

La merce ha raggiunto il deposito incaricato della consegna e Peek mostra «In transito». Poi segue `On Delivery Vehicle`, quando parte sul camion verso di te. Se resta lì per giorni, chiedi al mittente quando è prevista la consegna.

### Cosa significa «Pickup Allocated to Driver»?

Un autista è stato incaricato del ritiro, ma la merce è ancora dal mittente, quindi Peek mostra «Etichetta creata». Il passaggio successivo è `Picked Up`.

:::sources
- [Universal Parcel Scraper: Mainfreight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mainfreight) – nomi e paesi, formato a 11 cifre, altri riferimenti e master bill, link, ricerca e servizi, diciture e fasi, passaggi stimati, orari, peso, giorno previsto, campi esclusi da Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – corrieri che condividono 11 cifre, interrogazione di Mainfreight solo da Nuova Zelanda, Australia e Stati Uniti, nessun passaggio di mano, servizi di tracciamento come fallback
- [NZ Customs: Duty and GST](https://www.customs.govt.nz/sending-and-receiving/duty-and-gst) – 1.000 NZ$, GST riscossa dai venditori esteri, spedizioni uniche, tabacco e alcol, calcolo GST, Goods Levy, Customs Number, svincolo dopo pagamento
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilità per perdita o danno effettivo, limite di valore, 9 mesi per reclamare e 2 anni per agire in giudizio
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – contenuto del reclamo, annotazioni sulla ricevuta di consegna, documenti, 30, 120 e 60 giorni
:::
