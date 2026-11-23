---
title: Tracking Pitney Bowes: codici UPAA, UPBE e PBX spiegati
description: Traccia un pacco Pitney Bowes (come un ordine eBay dall'estero): formati UPAA, UPBE e PBX, significato degli stati e chi effettua la consegna.
slug: tracking-pitney-bowes
published: 2026-11-23
updated: 2026-11-23
---

Pitney Bowes spedisce pacchi verso altri paesi: nel codice di Peek i suoi numeri appartengono a pacchi eBay provenienti da Stati Uniti e Regno Unito e ad altre spedizioni internazionali di Pitney Bowes. Nelle cronologie presenti nel codice di Peek il pacco passa attraverso un centro di spedizione Pitney Bowes e poi viaggia verso il paese dell'acquirente, dove consegna un partner come le poste nazionali. Il codice inizia con UPAA, UPBE o PBX ed è fornito dal venditore o dal marketplace: incollalo nel riquadro in alto. Il tracker di Pitney Bowes si trova su trackpb.shipment.co.

## Codice di tracciamento Pitney Bowes: come si presenta

Peek conosce tre formati per Pitney Bowes, lunghi ciascuno da 14 a 20 caratteri. Con cifre inventate:

| Codice (inventato) | Che cos'è |
| --- | --- |
| `UPAAK482917364058291` | UPAA, una lettera e 15 cifre: nel codice di Peek, un pacco eBay dagli Stati Uniti o dal Regno Unito. |
| `UPBE48291736A4F02819` | UPBE e 16 caratteri, cifre o lettere da A a F: un pacco eBay dal Regno Unito. |
| `PBXHK482917365` | PBX, due lettere e 9 cifre: un altro pacco Pitney Bowes spedito all'estero. |
| `PBXHK48291736A4F0281` | PBX, due lettere e 15 caratteri, cifre o lettere da A a F: lo stesso in versione estesa. |
| `UPAA482917364058291` | UPAA senza la lettera: non corrisponde al formato Pitney Bowes e Peek non riconosce alcun corriere. Se scegli comunque Pitney Bowes, Peek indica «non emette numeri come questo». Controlla che non manchi un carattere. |

Spazi, trattini e minuscole non contano in Peek. Il catalogo riconosce Pitney Bowes anche come Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program e PBI.

**Dove trovarlo.** Nell'email di spedizione del venditore o nella pagina del tuo ordine. Per eBay consulta [ordini eBay](guide:ebay-orders); altrimenti [come trovare il codice di tracciamento](guide:find-tracking-number).

## Stati di tracciamento Pitney Bowes spiegati

Il tracker di Pitney Bowes elenca ogni riga con data, ora e, quasi sempre, una località, dalla più recente. Le righe non hanno un codice: Peek legge il loro testo. Nell'ordine abituale:

**Al centro di spedizione.** Righe proprie di Pitney Bowes:

| Messaggio di Pitney Bowes | Che cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | Il venditore ha inviato a Pitney Bowes i dati del pacco. Pitney Bowes non lo ha ancora ricevuto. | «Etichetta creata» |
| `Package not received by Hub or First Mile Carrier yet` | L'etichetta esiste, ma né il centro di spedizione né il corriere iniziale l'ha ancora scansionato. | «Etichetta creata» |
| `Processing at Shipping Center` | Il centro di spedizione Pitney Bowes ha il pacco e lo sta lavorando. | «In transito» |
| `First Dimension Event`, `Second Dimension Event` | Il centro sta misurando il pacco. | «In transito» |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | Il centro applica documenti di esportazione ed etichette. Non è ancora la dogana vera e propria. | «In transito» |
| `Container Assignment`, `Container Closed` | Inserito in un contenitore per il trasporto, poi il contenitore viene chiuso. | «In transito» |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | Ha lasciato il centro verso il paese di destinazione. | «In transito» |
| `International Carrier` | Affidato al vettore che lo trasporta all'estero. | «In transito» |
| `In Transit` | In viaggio. Può comparire senza località. | «In transito» |

**Proseguimento, dogana e passaggio alle poste.** Nelle cronologie presenti nel codice di Peek, i pacchi in partenza dal centro britannico di Pitney Bowes proseguono con Interpost, un partner che presenta la dichiarazione di importazione e li affida alle poste del paese di destinazione. Le righe di questa tratta, le prime due senza località durante la lavorazione nel centro UK:

| Messaggio di Pitney Bowes | Che cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Item processed at Interpost` | Il centro britannico lavora il pacco per Interpost. | «In transito» |
| `Interpost handed item over to postal company` | Passaggio da Interpost alle poste del paese di destinazione che consegneranno il pacco. Compare nello stesso istante della riga sopra, mentre il pacco può trovarsi ancora nel centro. | «In transito» |
| `Customs Clearance` | Dichiarazione di importazione depositata nel paese di destinazione. Pratica documentale: può arrivare poche ore dopo la partenza dal Regno Unito, prima che il pacco sia arrivato. | «In dogana» |
| `B2C ClearanceStatus4` | Sdoganato all'importazione. Peek lo scrive `Customs import cleared`, come il tracker di Interpost definisce quel codice. | «In transito» |
| `B2C ClearanceStatus` seguito da un altro numero | Passaggio doganale di cui il codice di Peek non conosce il significato. Peek lo scrive `Customs clearance update`. | «In dogana» |
| `Cleared customs` | Superata la dogana. | «In transito» |
| `Package is created in the system` | Il corriere successivo apre la propria registrazione per il pacco. Non indica alcun movimento. | nessun cambio |

**Le righe delle poste.** Dopo il passaggio, il tracker riporta le scansioni delle poste incaricate della consegna:

| Messaggio di Pitney Bowes | Che cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Item in transit by postal company` | Le poste stanno trasportando il pacco verso di te. | «In transito» |
| `Parcel is out for delivery` | In consegna oggi. | «In consegna» |
| `Item is waiting for addressee at local post office` | Ti aspetta all'ufficio postale locale: vai a ritirarlo. | «Pronto per il ritiro» |
| `Delivered by Consignee Pickup` | Tu, il destinatario, lo hai ritirato. | «Consegnato» |
| `Item has been delivered` | Consegnato. | «Consegnato» |

Anche `Departed carrier facility` e `Processed by Carrier` appaiono come «In transito». Le altre righe seguono le regole generali di Peek; una riga sconosciuta appare com'è scritta e conta come «In transito», a meno che il pacco non sia già in consegna o oltre, nel qual caso lo stato resta invariato.

**Stato complessivo.** Il tracker indica anche uno stato generale. Quando indica consegnato, Peek mostra «Consegnato» anche se la riga più recente non lo dice. Gli altri stati generali valgono solo finché nessuna riga ne ha uno. Lo stato di problema — comparso anche su un pacco semplicemente ritirato dal destinatario — non sovrascrive mai una riga.

## Chi consegna i pacchi Pitney Bowes

Peek non ha potuto leggere il sito di Pitney Bowes, quindi questa pagina non elenca partner né paesi. Il codice di Peek mostra Interpost prendere in carico i pacchi dal centro britannico e affidarli alle poste del paese di destinazione, e una cronologia mostra [Poste Italiane](carrier:poste-italiane) alla consegna in Italia. Il tracker di Pitney Bowes indica il corriere che subentra e può collegare una riga alla pagina di tracciamento di quel corriere con il suo codice.

**Tentativi di consegna e ritiro.** Tentativi, avvisi di giacenza e tempi di custodia seguono le regole delle poste che consegnano: rivolgiti a loro o leggi la loro pagina su Peek se disponibile. Vedi anche [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

**Dogana.** Se dovrai pagare qualcosa all'arrivo dipende dal tuo paese e da quanto addebitato dal venditore o dal marketplace al checkout: chiedi prima a loro. Peek non ha potuto verificare come Pitney Bowes gestisce gli oneri di importazione. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Pitney Bowes fermo, smarrito o restituito

**Ancora nulla.** Per un codice sconosciuto o una cronologia scaduta, il tracker di Pitney Bowes dà la stessa risposta di quando non riesce a leggere un pacco. Peek non può distinguerlo da un disservizio: interroga i servizi di tracciamento e, se nessuno ha il pacco, mostra «In attesa di Pitney Bowes» e dal secondo controllo «Non è ancora stato possibile raggiungere Pitney Bowes. Peek continua a provare.», continuando a verificare. Ricontrolla il codice nella tabella sopra. Se dopo qualche giorno non compare nulla, chiedi al venditore se il pacco è stato davvero spedito.

**Fermo su `Package not received by Hub or First Mile Carrier yet`.** Il venditore ha creato l'etichetta, ma né il centro di spedizione Pitney Bowes né il corriere iniziale ha scansionato il pacco. Chiedi al venditore quando lo ha spedito.

**Fermo su `Customs Clearance`.** La dichiarazione è depositata e il pacco potrebbe non essere ancora arrivato nel paese. Attendi la riga di sdoganamento, mostrata in Peek come `Customs import cleared`. Se resta fermo a lungo, avvisa il venditore.

**Nessuna novità una volta affidato alle poste.** Dopo le prime righe delle poste, il loro sito può essere più aggiornato di quanto riporta Pitney Bowes e Peek non passa automaticamente alle poste. Apri la pagina delle poste dalla riga sul tracker Pitney Bowes oppure aggiungi il codice postale in Peek come pacco separato.

**Nessun aggiornamento da giorni.** Dopo quattro giorni senza scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra un avviso («Nessun aggiornamento da quattro giorni» o «Nessun aggiornamento da …») con la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Pitney Bowes.» e il link «Chiedi aiuto a Pitney Bowes». Vedi [perché il tracking smette di aggiornarsi](guide:tracking-not-updating).

**Consegnato ma non ricevuto.** Controlla dove può essere stato lasciato e chiedi ai vicini, poi alle poste che hanno consegnato e al venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** Chiedi rimborso o rispedizione al venditore o al marketplace: sono loro ad aver prenotato la spedizione. Vedi [pacchi smarriti](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Pitney Bowes

Peek non ha potuto leggere il sito web di Pitney Bowes, quindi questa pagina non elenca numeri di telefono né indirizzi email: Peek riporta solo contatti letti sulle pagine del corriere stesso.

Pitney Bowes spedisce il pacco per il venditore o il marketplace, quindi rivolgiti prima a loro — per un ordine eBay, tramite eBay. Una volta che le poste locali hanno il pacco, chiedi a loro per la consegna.

## Tracciare un pacco Pitney Bowes con Peek

[Peek](/) riconosce subito Pitney Bowes da un codice UPAA, UPBE o PBX («Corriere rilevato») e interroga il suo tracker con quel solo codice: nessun codice postale o account. Funziona anche un link di tracciamento da trackpb.shipment.co o parceltracking.pb.com. Se hai solo il codice della prima tratta o quello delle poste, incollalo comunque: Peek segue oltre 4.500 corrieri. Scegliere Pitney Bowes con «Cambia» per un altro formato non aiuta: Peek indica «non emette numeri come questo».

Peek mostra lo stato, ogni riga con la località e il peso nelle tue unità (il tracker fornisce libbre e once). Omette link e codice del corriere successivo. Poiché il tracker non fornisce data di consegna prevista né prova di consegna, Peek non mostra nessuna delle due. «Apri il sito Pitney Bowes» nella pagina del pacco apre il codice sul tracker di Pitney Bowes.

Peek non passa il pacco alle poste: le scansioni postali arrivano tramite il tracker di Pitney Bowes. Quando quel tracker non è raggiungibile o non riesce a leggere il pacco, Peek interroga i servizi di tracciamento e mostra la loro cronologia con le loro parole.

Il tracker stampa ogni orario sull'orologio di chi ha scansionato il pacco, senza fuso orario. Peek legge ciascuna riga sull'orologio del suo luogo o della tratta in corso. Senza località, `In Transit` e `Item processed at Interpost` usano UTC, `Interpost handed item over to postal company` UTC+2 e `Customs Clearance` l'ora del paese citato. Ogni orario appare nel fuso orario del tuo dispositivo.

Finché hai il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek interroga direttamente Pitney Bowes fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 sull'orologio dell'ultima scansione nel giorno in cui il pacco va in consegna, finché il tracker di Pitney Bowes risponde. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità in «Etichetta creata», ogni 6 ore. Può avvisarti su «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Pitney Bowes, il venditore né le poste al posto tuo.

## Domande sul tracking Pitney Bowes

### Che cos'è un codice di tracciamento UPAA?

Un codice Pitney Bowes: UPAA, una lettera e 15 cifre. Il codice di Peek legge i numeri UPAA come pacchi eBay da Stati Uniti e Regno Unito, gli UPBE come pacchi eBay dal Regno Unito e i PBX come altri pacchi Pitney Bowes diretti all'estero. Il catalogo elenca anche eBay Global Shipping Program come altro nome di Pitney Bowes.

### Che cosa significa «Interpost handed item over to postal company»?

È il passaggio del tuo pacco da Interpost — che ritira i pacchi dal centro britannico di Pitney Bowes — alle poste del paese di destinazione incaricate della consegna. Pitney Bowes lo scrive mentre il centro britannico lavora il pacco, quindi può comparire prima della partenza. Le scansioni postali seguono poi sul tracker di Pitney Bowes.

### Perché il mio tracking Pitney Bowes è fermo su «Customs Clearance»?

È la dichiarazione di importazione, che può essere depositata poche ore dopo la partenza dal Regno Unito, prima dell'arrivo. Attendi la riga di sdoganamento, `Customs import cleared` in Peek. Se non cambia a lungo, chiedi al venditore.

### Come contatto Pitney Bowes per il mio pacco?

Rivolgiti prima al venditore o al marketplace: sono loro ad aver prenotato la spedizione. Poiché Peek non ha potuto leggere il sito di Pitney Bowes, questa pagina non indica numero di telefono né email.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – nomi, i tre formati di codice e che cosa coprono, link di tracciamento, il tracker, diciture e stati, righe di Interpost e codice di sdoganamento, righe riportate dalle poste, Poste Italiane su un pacco verso l'Italia, stato generale, orologi, peso, che cosa omette Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nessun passaggio automatico alle poste, servizi di tracciamento come riserva
:::
