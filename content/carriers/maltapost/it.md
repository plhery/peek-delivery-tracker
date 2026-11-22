---
title: Tracciamento MaltaPost: numeri, stati e ritiro
description: Traccia una spedizione MaltaPost a Malta: formato del codice, significato degli stati, ritiro in ufficio postale o locker Easipik, dogana e contatti.
slug: tracciamento-maltapost
published: 2026-11-22
updated: 2026-11-22
---

MaltaPost è l'operatore postale nazionale di Malta e consegna a ogni indirizzo di Malta e Gozo sei giorni su sette. Traccia un invio sulla sua [pagina di tracciamento](https://www.maltapost.com/tracking) oppure incolla il numero nel riquadro qui sopra. Il codice arriva dal mittente: ricevuta di spedizione, etichetta sotto il codice a barre o avviso di consegna.

## Numero di tracciamento MaltaPost: com'è fatto

Le raccomandate proprie di MaltaPost hanno 13 caratteri: [due lettere, nove cifre](guide:tracking-number-formats) e `MT`. Il modulo per la riconsegna indica i prefissi locali RH, RR, RT e RI.

:::anatomy RR 482917367 MT
- RR | Tipo di invio: RR, RH, RT o RI per le raccomandate locali
- 482917367 | Nove cifre, l'ultima è la cifra di controllo
- MT | Spedito da Malta
:::

Un invio dall'estero mantiene il codice assegnato dalla posta estera (`RR482917367GB`, `CP482917367CN` o altro formato) e il tracking di MaltaPost lo accetta. La pagina può mostrare sia un «Foreign Barcode» sia un «Local Barcode». I pacchi SendOn sono tracciabili con entrambi.

Le FAQ di MaltaPost chiedono di inserire il codice in lettere maiuscole. Se compare «Tracking number not recognised.», verifica il numero con il mittente. Alcuni codici, come quelli che iniziano per U, non sono tracciabili a Malta oppure la consegna è affidata a un'altra azienda locale. Gli invii spediti senza codice di tracciamento non si possono tracciare né localizzare. Numero smarrito? Vedi [come trovare un numero di tracciamento](guide:find-tracking-number).

## Stati di tracciamento MaltaPost spiegati

Ogni scansione indica data, ora, evento e sede di scansione, talvolta con una riga «Reason:» e una «Action:». Per una spedizione diretta a te:

| MaltaPost indica | Cosa significa per te |
| --- | --- |
| `Item created` | Spedito. Per un invio dall'estero indica l'accettazione presso la posta estera. |
| `Item arrived at inbound mail sorting centre` | Arrivato a Malta. |
| `Item to be submitted for clearance` | Avviato alla dogana. |
| `Item undergoing border agency clearance` | Controllo in corso da parte della dogana o di un'autorità di frontiera. |
| `Item held at inbound mail sorting centre` | Trattenuto. Con «Awaiting payment of charges» non si muove finché non paghi. |
| `Item returned from local Customs office` | Sdoganato dalla dogana. |
| `Item to be forwarded to the delivery centre` | In viaggio verso il centro di consegna. |
| `Item scheduled for physical delivery` | In consegna. |
| `Item could not be processed further. Unsuccessful attempt-Recipient notified` | Nessuno poteva ritirarlo: c'è un avviso nella cassetta delle lettere. |
| `Item arrived at collection point` | Pronto per il ritiro nell'ufficio postale indicato nella scansione. |
| `Item delivered/collected by recipient` | Consegnato, oppure ritirato da te. |
| `Item could not be processed further. Item not collected. Item returned to sender` | Non ritirato in tempo; in restituzione al mittente. |

Una barra di avanzamento riassume il percorso in fasi come `Arrived in Country`, `Ready for Collection` e `Attempted Delivery`.

Tre righe non sono movimenti: `Declaration Submitted`, `Payment Submitted` e `Online payment complete` registrano solo quanto fatto online. `Forwarded to the next processing centre` indica uno spostamento tra i centri MaltaPost. MaltaPost avverte che data e ora sono quelle della scansione, non sempre dell'evento effettivo.

## Avviso di consegna, ritiro e locker Easipik MaltaPost

MaltaPost tenta la consegna a domicilio delle raccomandate. Se non c'è nessuno, lascia un avviso nella cassetta delle lettere e ritiri l'invio nell'ufficio postale indicato. Gli avvisi hanno colori diversi, ciascuno con le modalità di ritiro o riconsegna.

**Ritiro:** porta l'avviso e un documento d'identità originale (carta d'identità, passaporto, patente, permesso di soggiorno o single work permit). Chi ritira al posto tuo deve portare l'avviso, il tuo documento originale e il proprio, oppure una delega. Gli uffici postali aprono in genere dal lunedì al sabato dalle 7:30 alle 13:00; alcuni hanno orari diversi.

**Consegna mancata?** Il [modulo Missed a Delivery](https://www.maltapost.com/delivery/missed-delivery) chiede colore dell'avviso, numero di tracciamento e foto dell'avviso. Puoi richiedere la riconsegna a un indirizzo, a un altro ufficio postale o a un locker Easipik, oppure estendere la giacenza oltre i 12 giorni lavorativi standard. Una seconda consegna tramite corriere a un indirizzo costa 5,50 € per un invio tracciato. Dopo la finestra di riconsegna MaltaPost può custodire l'invio fino a 12 settimane. Gli invii non ritirati tornano al mittente.

**Locker Easipik:** locker 24/7 a Malta e Gozo per pacchi fino a 40 × 40 × 60 cm e 30 kg. MaltaPost invia il codice via SMS ed e-mail; il pacco resta 72 ore dopo l'invio del codice, poi passa allo sportello dell'ufficio postale locale. Easipik è gratuito per consegne MaltaPost; per altri corrieri costa 3,50 €.

## Oneri doganali, ritardi e invii smarriti MaltaPost

**Fermo per oneri.** Su merci da fuori UE sono dovuti dazi e IVA e la legge vieta a MaltaPost di consegnarle prima del pagamento. Nella pagina di tracking «Declare Items» permette di caricare i documenti e «Pay Charges» di pagare; dopo il pagamento l'invio viene rilasciato e consegnato il giorno lavorativo successivo. Approfondisci in [pacchi fermi in dogana](guide:customs).

**Nessun aggiornamento o non arrivato.** Per invii dall'estero le FAQ di MaltaPost indicano che il reclamo va aperto tramite la posta di origine: chiedi quindi al negozio o al mittente di avviarlo. MaltaPost considera ritardo quattro o più giorni lavorativi dalla spedizione; richieste e reclami sono ammessi fino a sei mesi dall'invio, con obiettivo di risposta entro 90 giorni per casi internazionali. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Danneggiato.** Controlla raccomandata o pacco davanti al personale MaltaPost alla consegna, altrimenti MaltaPost non accetta responsabilità. L'indennizzo va al mittente, che può rinunciarvi a tuo favore.

## Numero di assistenza clienti e contatti MaltaPost

Canali indicati nella pagina assistenza clienti di MaltaPost:

- **Telefono:** (+356) 2122 4421 oppure numero verde 800 722 44 da rete fissa. Dal lunedì al venerdì 8:30–14:30, sabato 7:30–12:30, esclusi festivi.
- **E-mail:** info@maltapost.com, anche per sdoganare autonomamente o tramite il proprio spedizioniere.
- **Modulo di contatto** dopo le FAQ, oppure messaggi Facebook.
- **Sede centrale:** 305, Triq Ħal Qormi, Marsa MTP 1001, dal lunedì al venerdì 8:00–16:00.
- **Assistenza Easipik** (locker e codici PIN): 2124 3324.

> MaltaPost avverte di SMS ed e-mail falsi che invitano a cliccare un link e pagare una tariffa prima della consegna. Verifica che il dominio sia maltapost.com e non un sito simile come malta-post.com, e segnala le truffe alla Malta Cyber Crime Unit.

## Tracciare gli invii MaltaPost con Peek

Per un numero terminante in `MT` con cifra di controllo valida, [Peek](/) interroga subito MaltaPost e [Chronopost](carrier:chronopost) e mostra «MaltaPost ha questo pacco.» quando MaltaPost lo conosce, oppure «Non ancora trovato presso … Peek continuerà a controllare dopo l’aggiunta.». Per un codice con 5 lettere, 9 cifre e `MT`, Peek interroga solo MaltaPost. Con cifra di controllo errata MaltaPost non viene interrogata e nemmeno la scelta manuale aiuta (Peek segnala «non emette numeri come questo»): verifica il codice con il mittente. Un link di tracciamento `maltapost.com` con `barcode=` seleziona sempre MaltaPost qualunque sia il codice.

Peek segue prima un codice estero presso la posta d'origine. Quando questa indica Malta come destinazione (o nessun paese se segui da Malta), Peek interroga anche MaltaPost con lo stesso numero e passa su di essa appena arriva una nuova scansione, mantenendo la cronologia precedente. MaltaPost accetta questi numeri, codici SendOn inclusi: per seguirlo subito su MaltaPost sceglila con «Cambia» (Peek traccia oltre 4.500 corrieri) oppure incolla un link maltapost.com.

Gli orari di MaltaPost non includono il fuso orario: Peek li legge sull'orologio del luogo di scansione (ora maltese a Malta) e li mostra nel fuso del tuo dispositivo; su peektracker.com e nelle app per iPhone e Android la cronologia termina con «Orari nel tuo fuso orario:» e il suo nome. Gli stati mantengono le diciture inglesi di MaltaPost. Peek può mostrare l'ufficio postale dove l'invio attende o è stato ritirato; mancano data prevista e peso, e un invio escluso dal tracking da MaltaPost non è tracciabile. Quando un invio non si muove più, la nota della sua scheda propone «Chiedi aiuto a MaltaPost», che apre il servizio clienti.

Finché tieni il pacco aperto sullo schermo, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché il tracker di MaltaPost risponde; dalle 6:00 ora maltese del giorno in cui MaltaPost registra `Item scheduled for physical delivery`, può controllare ogni 2 minuti. Se risponde solo un servizio di tracciamento, controlla ogni ora, ogni 3 ore quando l'ultima scansione ha almeno un giorno, e fino a ogni 15 minuti nel giorno di consegna finché tieni il pacco aperto o hai le notifiche attive. A schermo chiuso senza avvisi, e dopo due giorni senza novità (12 ore finché mostra «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando subentra un altro corriere, Peek segue il suo ritmo. Un invio rimasto quattro giorni senza nuove scansioni in «Etichetta creata», «Preso in carico» o «In transito» mostra nella tua lista il giorno della sua ultima scansione («Nessun aggiornamento da …»). Puoi impostare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande sul tracciamento MaltaPost

### MaltaPost è aperta il sabato?

Sì. Gli uffici postali aprono in genere il sabato dalle 7:30 alle 13:00 e la linea telefonica risponde dalle 7:30 alle 12:30, esclusi i festivi.

### Quanto impiega MaltaPost a consegnare?

MaltaPost non fornisce tempi generali per invii dall'estero. La posta internazionale arrivata prima delle 16:30 nei feriali (13:30 il sabato) viene lavorata in giornata per la consegna il giorno lavorativo successivo; i pacchi SendOn impiegano circa 7–12 giorni dal Regno Unito, 10–12 da Germania o Italia e circa 15 dagli Stati Uniti.

### Esiste un numero assistenza MaltaPost 24/7?

No. Il servizio clienti risponde nei feriali 8:30–14:30 e il sabato mattina; i locker Easipik sono accessibili 24/7.

### Com'è fatto un numero di tracciamento internazionale MaltaPost?

Un invio spedito da Malta all'estero ha due lettere, nove cifre e `MT`, come `RR482917367MT`. Un invio in arrivo a Malta mantiene il proprio numero estero, accettato dal tracking MaltaPost.

:::sources
- [MaltaPost: Tracking](https://www.maltapost.com/tracking) – campo tracking, colonne, fasi, codici esteri e locali, pulsanti dogana
- [MaltaPost: Frequently Asked Questions](https://www.maltapost.com/faqs?l=1) – maiuscole, avvisi, orari uffici, Easipik 72 ore e linea assistenza, pagamento dogana, lavorazione in giornata, tempi SendOn
- [MaltaPost: Missed a Delivery?](https://www.maltapost.com/delivery/missed-delivery) – opzioni riconsegna, 12 giorni lavorativi, 12 settimane, tariffa 5,50 €, prefissi locali
- [MaltaPost: Packet Collection](https://www.maltapost.com/packets) – documento e avviso per il ritiro, ritiro per terzi
- [MaltaPost: courier it](https://www.maltapost.com/courier) – tentativo di consegna e avviso
- [MaltaPost: Easipik](https://www.maltapost.com/easipik) – locker, codice SMS ed e-mail, dimensioni, tariffa 3,50 € terzi
- [MaltaPost: Parcel Lockers](https://www.maltapost.com/lockers) – locker a Malta e Gozo
- [MaltaPost: Post Offices Opening Hours](https://www.maltapost.com/postoffices) – orari per ufficio
- [MaltaPost: Customs](https://www.maltapost.com/customs) – dichiarazione, pagamento, sdoganamento via info@maltapost.com
- [MaltaPost: How to lodge an Enquiry](https://www.maltapost.com/how-to-lodge-an-enquiry?l=1) – ritardo da 4 giorni lavorativi, limite 6 mesi, 90 giorni
- [MaltaPost: Committed to Deliver](https://www.maltapost.com/committed-to-deliver) – indennizzo al mittente, verifica alla consegna
- [MaltaPost: Customer Service](https://www.maltapost.com/customers-service?l=1) – telefono, numero verde, orari, e-mail, sede centrale
- [MaltaPost: Scam Protection](https://www.maltapost.com/scamprotection?l=1) – avviso SMS ed e-mail truffa
- [MaltaPost: SendOn](https://www.maltapost.com/sendon) – servizio inoltro, consegna a domicilio o Easipik
- [MaltaPost: About us](https://www.maltapost.com/about-us) – operatore postale nazionale, sei giorni su sette
- [MCA: How can I track and trace registered articles?](https://www.mca.org.mt/content/post-how-can-i-track-and-trace-registered-articles) – numero sotto il codice a barre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maltapost) – formati, link e dati letti da Peek
:::
