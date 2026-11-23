---
title: Tracciamento Metropolitan Warehouse (MWD): mobili
description: Traccia un ordine Metropolitan Warehouse & Delivery (MWD): codice a 12 cifre, stati, appuntamento di consegna, reclami danni entro 72 ore e contatti.
slug: tracciamento-metropolitan-warehouse
published: 2026-11-23
updated: 2026-11-23
---

Metropolitan Warehouse & Delivery (MWD) è un’azienda familiare statunitense di trasporto mobili e logistica 3PL fondata nel 1989 a Perth Amboy, New Jersey. Attraverso 47 hub e piattaforme di cross-docking consegna a domicilio per negozi online, marchi di arredamento e designer nei 48 Stati contigui degli Stati Uniti. Inserisci il codice di 12 cifre sulla [pagina di tracciamento MWD](https://www.gomwd.com/track-a-shipment) o incollalo nel campo qui sopra.

## Numero di tracciamento MWD: 12 cifre

Il [tracker di MWD](https://main.gomwd.com/client/wixtrackorder) ha un solo campo, *Tracking #*, e accetta esattamente 12 cifre e nient’altro (cifre inventate):

| Numero | Che cos’è |
| --- | --- |
| `584927163048` | Il numero di tracciamento MWD. La pagina dell’ordine lo stampa a gruppi di quattro: `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Altri riferimenti sulla pagina dell’ordine (di MWD, del negozio o di un corriere partner). Non servono per tracciare. |

Non richiede CAP né numero di telefono, ma utilizza reCAPTCHA e può mostrare un captcha testuale da digitare. Qualsiasi formato diverso da 12 cifre restituisce `Please enter a valid tracking number of 12 digits.`; un codice sconosciuto mostra `Tracking id does not exist.`. Controllalo nell’e-mail del negozio o chiedilo al venditore ([come trovare il numero di tracciamento](guide:find-tracking-number)). MWD fornisce ai rivenditori pagine di tracciamento personalizzate, quindi potresti ricevere un link con il nome del negozio.

## Significato degli stati Metropolitan Warehouse

La cronologia di MWD compare in inglese, dal più recente al meno recente, con data, descrizione, città e Stato. Ecco gli stati documentati in [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) nel loro ordine tipico:

| Stato MWD | In Peek | Che cosa significa per te |
| --- | --- | --- |
| `Order registered` | Etichetta creata | L’ordine è registrato presso MWD. |
| `Pickup document attached` | nessuna variazione | Documento di ritiro allegato; la merce non si è ancora mossa. |
| `Pickup scheduled for 11/02/2026` | Etichetta creata | Ritiro dal fornitore programmato; MWD non ha ancora il mobile. |
| `Pickup completed, No exceptions` | Preso in carico | MWD ha ritirato l’ordine senza anomalie segnalate. |
| `Order enroute to Metro-Hub` | In transito | In viaggio verso un hub MWD. |
| `Furniture Received by Metro-Hub` | In transito | Scansionato in un hub MWD (indicato con la città). |
| `Reached Delivery Hub` | In transito | Arrivato all’hub incaricato della consegna. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | nessuna variazione | Consegna prevista per quella settimana; giorno e fascia arrivano con l’appuntamento. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | nessuna variazione | Consegna spostata a un’altra settimana. |
| `Delivery Appointment Canceled` | nessuna variazione | Appuntamento annullato; di solito ne segue uno nuovo, altrimenti chiedi al negozio o a MWD. |
| `Arrived at Delivery Location` | In consegna | La squadra di consegna è arrivata al tuo indirizzo. |
| `Delivery Completed, No Exceptions` | Consegnato | Consegnato senza eccezioni (controlla comunque ogni pezzo). |

La pagina MWD scrive talvolta `Metro-<Warehouse>-Hub` lasciando il nome vuoto; Peek lo semplifica in `Metro-Hub`. Non esiste una riga precedente di partenza in consegna: il primo evento del giorno di consegna è `Arrived at Delivery Location`, con gli addetti già davanti a casa.

## Appuntamento, livello di servizio e mancata consegna

**Appuntamento e mappa in tempo reale.** Nelle pagine [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) e [Services](https://www.gomwd.com/services), MWD spiega che prenoti la fascia oraria in autonomia, ricevi promemoria automatici e nel giorno di consegna un link live con la posizione della squadra e l’orario stimato di arrivo. La pagina di tracciamento mostra anche fascia concordata e prova di consegna.

**Cosa fa la squadra.** Dipende dal servizio scelto dal negozio (indicato sotto *Service Type* sulla pagina dell’ordine):

| Livello di servizio | Cosa include |
| --- | --- |
| Full White Glove | Posizionamento nella stanza scelta, montaggio leggero e ritiro di tutti gli imballaggi. |
| White Glove (No Assembly) | Stesso servizio per mobili premontati e tappeti, senza montaggio. |
| Room of Choice | Trasporto all’interno nella stanza indicata, senza montaggio. |
| Threshold Delivery | Deposito nel primo spazio coperto dell’abitazione (ingresso o garage), senza disimballaggio né montaggio. |
| Doorstep Delivery | Deposito fuori dalla porta senza firma, per colli più leggeri. |
| Curbside Delivery | Deposito a bordo strada o nel vialetto; il resto è a carico del destinatario. |

Se montaggio o ritiro degli imballaggi non risultano inclusi, verifica con il rivenditore che ha venduto il servizio.

**Se non sei in casa.** Le pagine informative di MWD non spiegano come riprogrammare una consegna mancata: avvisa quindi MWD o il negozio prima del giorno fissato. Le [condizioni di trasporto](https://sso.gomwd.com/Account/TermsandConditions) prevedono che, in caso di rifiuto o impossibilità di consegna, MWD risponda come depositario: dopo il tentativo di avviso telefonico o elettronico possono maturare spese di giacenza dal giorno lavorativo successivo; senza istruzioni entro 48 ore segue un secondo avviso finale e, dopo altri 10 giorni, il diritto di vendere la merce all’asta. Vedi anche [consegne mancate](guide:missed-delivery).

## Ordine MWD danneggiato, in ritardo o smarrito

**Danni.** Controlla ogni pezzo prima che la squadra vada via. Le [condizioni di trasporto](https://sso.gomwd.com/Account/TermsandConditions) sul portale clienti stabiliscono **72 ore** dalla consegna per inviare un reclamo scritto per perdita o danni e consentono a MWD di riparare o sostituire l’articolo. In assenza di accordi diversi o dell’opzione *Enhanced Valuation*, la responsabilità massima predefinita è limitata a 500 USD per spedizione e i danni occulti a merce preimballata sono esclusi se l’imballo non mostra segni visibili di urti. Poiché il cliente di MWD è il negozio, avvisalo subito. Vedi [pacco danneggiato](guide:damaged-parcel).

**Ritardo.** Finché la cronologia resta su `Pickup scheduled for`, MWD non ha ancora ritirato il mobile. Secondo la [guida FTC sulla Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule), un ordine conta come spedito solo quando passa al corriere: se il venditore non riesce a spedire nei tempi dichiarati — o entro **30 giorni** in mancanza di una data — deve chiedere il tuo consenso al ritardo e offrire la cancellazione con rimborso completo e rapido. Se nulla cambia per giorni dopo il ritiro, leggi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Mancata consegna o smarrimento.** Le stesse condizioni concedono **30 giorni**, trascorso un tempo ragionevole per la consegna, per presentare un reclamo scritto di mancata consegna (e 6 mesi per agire dopo un rigetto scritto). Contatta il negozio e MWD e consulta [consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Contatti Metropolitan Warehouse & Delivery

Come indicato sulla [pagina contatti di MWD](https://www.gomwd.com/contact-us):

- **Linea di assistenza:** (800) 300-7417 — team attivo dal lunedì al venerdì (orari precisi non pubblicati).
- **E-mail generale:** `comments@gomwd.com`.
- **Sedi:** quartier generale a Perth Amboy, New Jersey (indicato su [About Us](https://www.gomwd.com/about-metropolitan) e [Services](https://www.gomwd.com/services)), e *Corporate Office* elencato nella pagina contatti al 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

La sezione *Info About My Order* è destinata ai clienti aziendali (i rivenditori che spediscono con MWD): rivolgiti al negozio per coinvolgere il team *Customer Success*.

## Tracciare un ordine MWD con Peek

[Peek](/) non può riconoscere MWD dal solo numero, perché dodici cifre corrispondono a molti corrieri ([perché i formati si sovrappongono](guide:tracking-number-formats)). Incolla comunque il codice: Peek segue oltre 3.500 corrieri. **Negli Stati Uniti**, interroga MWD tra i primi corrieri a 12 cifre — insieme a [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) e [Purolator](carrier:purolator) (se inizia per 0–6 con cifra di controllo valida) — e mostra «Metropolitan Warehouse & Delivery ha questo pacco.» quando MWD gestisce l’ordine. **Fuori dagli Stati Uniti**, Peek tratta MWD come ultima risorsa: chiede prima agli altri corrieri a 12 cifre e ai servizi di tracciamento, e interroga MWD solo se nessuno lo riconosce. Puoi sempre inserirlo sulla pagina MWD o scegliere Metropolitan Warehouse & Delivery tramite «Cambia» o «Cambia corriere» (anche cercando «MWD»). Gli spazi sono ammessi; incolla le 12 cifre, perché Peek non ha regole per i link di MWD.

Peek legge il tracker di MWD: stato, cronologia nel testo inglese di MWD, città e Stato di ogni voce sulla mappa, data e ora locali riportate da MWD (senza fuso orario) e peso convertito in chilogrammi. Esclude città e CAP di destinazione, riferimenti d’ordine e cliente, numero PRO di un corriere partner, numero di colli, volume e barra di avanzamento. Non mostra data stimata, prova di consegna o tracciamento live della squadra e non segue eventuali corrieri partner.

Peek interroga direttamente MWD fino a ogni 10 minuti mentre tieni aperto l’ordine o nel giorno di consegna con avvisi attivi, e ogni 2 minuti dalle 6 ora locale nel giorno in cui compare `Arrived at Delivery Location`; altrimenti ogni ora. Dopo 12 ore in «Etichetta creata» o «Preso in carico», o dopo due giorni senza nuovi eventi, controlla ogni ora anche a schermata aperta, e ogni 6 ore dopo sette giorni ancora in «Etichetta creata». Tramite servizi di tracciamento di supporto controlla ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti il giorno della consegna.

Dopo quattro giorni senza nuove scansioni mentre è «In transito», Peek segnala il ritardo (ad esempio «Nessun aggiornamento da lun 12 ott»), mostra «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Metropolitan Warehouse & Delivery.» e propone «Chiedi aiuto a Metropolitan Warehouse & Delivery». Oltre tre settimane senza novità mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Metropolitan Warehouse & Delivery.»

Puoi attivare notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna», che con MWD parte da `Arrived at Delivery Location`. Peek non può fissare o modificare appuntamenti, aprire reclami né contattare MWD o il negozio al posto tuo.

## Domande sul tracciamento Metropolitan Warehouse

### Che cos’è Metropolitan Warehouse & Delivery?

Un’azienda familiare statunitense di consegne di mobili e logistica 3PL fondata nel 1989 a Perth Amboy (New Jersey), con 47 hub nei 48 Stati contigui. Viene incaricata da negozi, marchi d’arredo e designer; i privati non la prenotano direttamente.

### Cosa significa «Delivery Scheduled for WEEK OF»?

MWD ha pianificato la consegna per la settimana di quella data. Giorno e fascia oraria precisi vengono definiti con l’appuntamento. `Delivery Rescheduled for WEEK OF` segnala lo spostamento a un’altra settimana.

### Metropolitan Warehouse monta i mobili?

Solo con *Full White Glove*, che comprende montaggio leggero e smaltimento degli imballaggi. Gli altri livelli — compreso *White Glove (No Assembly)* — non prevedono il montaggio. Verifica la voce *Service Type* sulla pagina dell’ordine.

### Come riprogrammo la consegna con Metropolitan Warehouse?

Le pagine di MWD illustrano la prenotazione iniziale in autonomia ma non la modifica successiva. Chiama il numero (800) 300-7417 dal lunedì al venerdì o chiedi al negozio.

### Quanto tempo ho per segnalare danni a MWD?

Le condizioni di trasporto sul portale clienti prevedono 72 ore dalla consegna per un reclamo scritto di perdita o danno. Poiché il cliente di MWD è il negozio, avvisalo subito.

:::sources
- [Metropolitan Warehouse & Delivery: Home](https://www.gomwd.com/) – fondata nel 1989, rete di 47 hub, linea di assistenza
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – gestione familiare, sede a Perth Amboy, 48 Stati contigui
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – tracciamento, fascia d’appuntamento e prova di consegna
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – campo unico a 12 cifre, messaggio d’errore, reCAPTCHA / captcha testuale
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – livelli di servizio, prenotazione autonoma, promemoria e tracking live
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, 5 hub 3PL completi, pagine di tracciamento personalizzate
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – supporto (800) 300-7417 lun–ven, comments@gomwd.com, Corporate Office West Sacramento, percorso clienti
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – reclami 72 ore (danni) e 30 giorni (mancata consegna), limite 500 USD, costi di giacenza
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – consegna al corriere, regola dei 30 giorni, consenso al ritardo e rimborso
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – formato a 12 cifre, messaggio numero sconosciuto, stati e dati letti da Peek
:::
