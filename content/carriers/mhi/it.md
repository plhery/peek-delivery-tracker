---
title: Tracciamento MHI: stati Trackmail e chi consegna
description: Hai ricevuto un codice MHI da un negozio online? Dove tracciarlo, cosa significano gli stati di Trackmail, quale corriere consegna da te e chi contattare.
slug: tracciamento-mhi
published: 2026-11-23
updated: 2026-11-23
---

MHI (Mail Handling International) è un’azienda di Bristol che spedisce all’estero gli ordini dei negozi online britannici. I negozi inviano i pacchi al suo centro di lavorazione di Bristol; MHI li esporta e affida ciascuno a un corriere o servizio postale del tuo paese, che lo consegna con un proprio codice. Traccia il numero MHI sul sito di tracciamento di MHI, [Trackmail](https://www.trackmail.co.uk/), oppure nella casella qui sopra. Il codice arriva dal negozio: MHI assegna ai propri clienti un codice per ogni pacco, quindi cercalo nell’e-mail di spedizione o nei dettagli dell’ordine.

## Numero di tracciamento MHI: com’è fatto

MHI non pubblica un formato per gli acquirenti. Ecco le forme che assumono i suoi numeri, con cifre inventate:

| Numero | Cos’è |
| --- | --- |
| `MHI482W736405829173` | MHI, tre cifre, poi W e 12 cifre: MHI. |
| `MHI482WBB736405829` | MHI, tre cifre, poi WBB e 9 cifre: MHI. |
| `MHI-482-P736405829` | MHI, tre cifre, poi P e 9 cifre: MHI. Le etichette lo stampano con i trattini. |
| `MHI482736405829173` | MHI, tre cifre e poi altre 12 cifre: MHI. |
| `RR482917367US` | Due lettere, 9 cifre, due lettere: un codice postale per lo stesso invio, che Trackmail mostra per alcuni pacchi non tracciati. |
| Qualsiasi altro formato | Il codice proprio del corriere che consegna, indicato su Trackmail dopo «Tracking number:» per un invio tracciato. |

Le tre cifre dopo MHI indicano l’account del negozio presso MHI. Digita un codice P su Trackmail con i trattini, come stampato: Trackmail lo trova solo così. Uno stesso pacco può quindi avere due codici, quello di MHI e quello del corriere. Maggiori dettagli in [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento MHI spiegati

Trackmail è in inglese. Sopra la cronologia indica il servizio scelto dal negozio: «The seller requested this item be shipped via the following service:», seguito da `TRACKED` o `UNTRACKED`. Sotto compaiono gli eventi, dal più recente, ciascuno con data, ora e testo, quasi sempre con categoria e luogo: prima la scansione di esportazione di MHI, poi quelle del corriere riformulate da MHI. Nell’ordine in cui il pacco le incontra:

| Trackmail dice | Cosa significa per te |
| --- | --- |
| `Your order has been processed at the UK export facility. It is now on route to the receiver.` | MHI ha lavorato il pacco nel Regno Unito e lo ha spedito all’estero. Su un servizio non tracciato è spesso l’unica riga. |
| `Shipment information sent to last mile service provider` | Il corriere nel tuo paese ha i dati del pacco, non ancora il pacco fisico. |
| `Notification sent to recipient` | Il corriere ti ha avvisato che il pacco è in viaggio. |
| `Handed Over to Delivery Partner` | Il corriere ha preso in carico il pacco. |
| `Arrived at sorting center` | Scansione di deposito nella rete del corriere. |
| `Dispatched from sorting center` | Ha lasciato quel deposito. |
| `Assigned to Transport` | È su un mezzo tra due depositi. |
| `Shipment at final delivery center` | È arrivato al deposito incaricato della consegna. |
| `Delivery scheduled in the final delivery depot` | È previsto sul giro di consegna della giornata. |
| `Delivery attempted` | Consegna fallita: nessuno ha ritirato il pacco. |
| `Arrived at Pick-up Point` | Ti aspetta in un punto di ritiro. |
| `Successfully delivered` | Consegnato o ritirato. |

Leggi il testo, non la categoria accanto. Trackmail mette `Shipment at final delivery center` e `Arrived at Pick-up Point` sotto «Out for delivery», anche se il primo precede il giro e il secondo indica che devi andare a ritirarlo. Inoltre classifica `Delivery attempted` sotto «Delivery», come una consegna riuscita. Giro e tentativo possono ripetersi per più giorni.

Gli orari su Trackmail non hanno fuso orario: la scansione di MHI è sull’ora britannica, quelle del corriere sulla sua ora locale.

Per un numero sconosciuto, Trackmail mostra un’unica riga che inizia con `No details found for Tracking Code, or Tracking Code not recognised.` e aggiunge che se hai ordinato da poco MHI potrebbe non avere ancora i dati.

## Consegna MHI: chi porta il tuo pacco

MHI non effettua consegne dirette all’estero. Dichiara di collaborare con oltre 100 corrieri per raggiungere 252 paesi e territori. Nella sede di Bristol viene scansionato il codice a barre di ogni pacco e il sistema di MHI seleziona il corriere per l’ultimo miglio in base al paese di destinazione. I negozi possono scegliere servizi tracciati, non tracciati o espressi: chi consegna dipende quindi da dove vivi e da cosa ha pagato il negozio.

**Spedizioni tracciate.** Trackmail può mostrare il codice del corriere che consegna dopo «Tracking number:», con il link «View carrier tracking» al suo sito. Per chiarimenti, Trackmail invita a usare quel link o a contattare il rivenditore.

**Spedizioni non tracciate.** Trackmail precisa che il tracciamento è limitato e spesso mostra solo la scansione di esportazione di MHI. Per alcuni invii fornisce un «local poster number»: comunicandolo al tuo servizio postale o ufficio postale, dice Trackmail, potrebbero darti altre notizie. Provalo anche sul sito delle tue poste: [USPS](carrier:usps), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) o An Post.

Avvisi, tentativi, nuove consegne e punti di ritiro seguono le regole del corriere finale: controlla il suo tracciamento da «View carrier tracking» e leggi [consegne mancate](guide:missed-delivery).

## Pacco MHI fermo, smarrito o restituito

**`No details found…`.** Verifica il codice e digita i numeri P con i trattini. Se l’ordine è recente, MHI potrebbe non avere ancora i dati: riprova più tardi.

**Solo la scansione di esportazione.** Su un servizio non tracciato Trackmail potrebbe fermarsi lì: usa il «local poster number» presso le tue poste se c’è, oppure chiedi al negozio. Su uno tracciato, il corriere potrebbe non averlo ancora scansionato; quando compare il suo numero, il suo sito può essere più aggiornato. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato ma non c’è.** La consegna è stata fatta dal corriere locale: controlla il suo tracciamento tramite «View carrier tracking», poi contatta il negozio. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o danneggiato.** Contatta il negozio. MHI lavora per il negozio, non pubblica procedure di reclamo per i destinatari e Trackmail stesso rimanda al rivenditore. Vedi [pacchi smarriti](guide:lost-parcel).

**Dogana.** MHI offre ai negozi vari regimi doganali, tra cui DDP (Delivered Duty Paid), quindi se resta qualcosa da pagare dipende dalla scelta del negozio. Cosa potresti pagare è spiegato in [pacchi fermi in dogana](guide:customs).

**Restituito.** Per quanto tempo un pacco non ritirato attenda prima di tornare indietro dipende dalle regole del corriere finale: verifica il suo tracciamento. Se rientra, chiedi al negozio rimborso o nuova spedizione. Vedi [restituito al mittente](guide:returned-to-sender).

## Numero di contatto e assistenza clienti MHI

Recapiti indicati da MHI:

- **Richieste sul tracciamento:** e-mail tracking@mhi.co, riportata sulla pagina eCommerce delivery e sulla pagina contatti di Nexae (la sua piattaforma di spedizione).
- **Telefono:** +44 (0) 117 977 6655, numero britannico accanto alla sede di Bristol, 82 Feeder Road, BS2 0TQ. MHI non indica orari e non dichiara di gestire richieste sui pacchi su questa linea.
- **Modulo Enquiries:** per richieste generali. MHI avvisa che le domande sul tracciamento inviate tramite questo modulo non vengono prese in carico né gestite.

Il consiglio di MHI agli acquirenti: controllare Trackmail o contattare il venditore o il marketplace. Il negozio è cliente di MHI e può sollecitarla; per una consegna mancata o un ritiro rivolgiti al corriere indicato su Trackmail.

## Tracciare un pacco MHI con Peek

[Peek](/) riconosce un codice MHI dalla forma in tutte e quattro le serie sopra, con o senza trattini. Le vecchie etichette con sole due cifre dopo MHI o otto cifre finali non vengono cercate: controllale su Trackmail. Puoi anche incollare un link Trackmail con il codice; Peek lo riconosce come MHI quando il numero segue il formato MHI.

Peek legge Trackmail e mostra stato e cronologia, con il luogo di ogni scansione su una mappa. Trackmail non fornisce data di consegna stimata, peso, prova di consegna né nome di chi ha firmato, quindi Peek non riceve nessuno di questi dati da MHI, ed esclude il tuo indirizzo e il codice postale presenti nel link del corriere.

Quando il link di Trackmail porta a un corriere che Peek conosce, la pagina del pacco indica «Consegna con» quel corriere e ne mostra il numero. Finché quel corriere ha solo il preavviso, senza proprie scansioni, compare invece «Annunciato a …, non ancora ricevuto». Se Peek legge direttamente il sito di quel corriere, lo interroga con quel codice e passa a esso appena mostra progressi più recenti. Un corriere sconosciuto a Peek non viene nominato: usa «View carrier tracking» su Trackmail. Per un invio non tracciato con «local poster number», Peek può provare quel numero con un servizio postale nazionale, di solito quello del paese da cui aggiungi il pacco, e passare a esso allo stesso modo. Non con tutte le poste però: un codice `RR…` che finisce in `AU` aggiunto dall’Australia non viene passato ad Australia Post, quindi controllalo direttamente sul sito di Australia Post.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e dalle 6:00 (ora locale) del giorno in cui il pacco va in consegna fino a ogni 2 minuti finché Trackmail (o il tracciamento diretto del corriere subentrato) risponde. Dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora; e se resta su «Etichetta creata» per sette giorni, ogni 6 ore.

Se un pacco ancora su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza una nuova scansione, Peek lo segnala con «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da …»), spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a MHI.» Peek non ha una pagina di aiuto per MHI: il link «Chiedi aiuto a …» compare solo quando Peek segue il corriere che consegna, e solo se questo ne ha una. Puoi attivare gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare MHI, il corriere né il negozio al posto tuo.

## Domande sul tracciamento MHI

### Dov’è il mio pacco MHI?

La riga più recente su Trackmail indica la fase, e le scansioni del corriere indicano una città. Prima di `Handed Over to Delivery Partner` è da MHI o in viaggio verso il tuo paese; dopo è in mano al corriere locale, e su un invio tracciato il suo numero appare su Trackmail dopo «Tracking number:».

### Cosa significa «processed at the UK export facility»?

MHI ha lavorato il pacco nel proprio centro di esportazione nel Regno Unito e lo ha spedito verso di te. È la scansione di MHI, e su un servizio non tracciato è spesso l’unica.

### trackmail.co.uk è affidabile?

Sì. Trackmail è il sito di tracciamento di MHI: il link «Track my Parcel» sul sito ufficiale di MHI porta lì. Companies House registra Mail Handling International Limited come società attiva, costituita nel 1996 all’indirizzo di Bristol indicato da MHI.

### MHI ha un numero di assistenza clienti?

Indica il +44 (0) 117 977 6655, ma per il tracciamento chiede di scrivere a tracking@mhi.co o di contattare il negozio. Per la consegna vera e propria il referente è il corriere indicato su Trackmail.

:::sources
- [MHI: Home](https://www.mhi.co/) – link Track my Parcel a Trackmail, richieste pacchi al venditore, modulo generale non per tracciamento, oltre 100 corrieri, 252 paesi e territori, tracciato, non tracciato ed espresso, DDP, indirizzo, telefono
- [MHI: eCommerce Delivery](https://www.mhi.co/ecommerce-delivery) – centro di Bristol, corriere scelto per paese, codici di tracciamento per negozi, export, tracking@mhi.co
- [Nexae: Contact Us](https://nexae.co.uk/ContactUsHomepage) – tracking@mhi.co, Nexae appartiene a MHI
- [MHI: Trackmail](https://www.trackmail.co.uk/) – sito di tracciamento di MHI, inglese, contatto tracciamento
- [Companies House: Mail Handling International Limited](https://find-and-update.company-information.service.gov.uk/company/03241562) – attiva, costituita il 23 agosto 1996, sede legale 82 Feeder Road, Bristol
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati dei codici, testi e categorie di Trackmail, riga di servizio, link corriere, codice postale locale, messaggio codice sconosciuto, fusi orari, cosa mostra Peek e quale corriere o posta interroga
:::
