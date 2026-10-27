---
title: Tracking PostNL: codici, stati e consegna in Italia
description: Tracking PostNL in Italia: com'è fatto un codice 3S o NL, cosa dicono gli stati su postnl.post, chi ti consegna il pacco in Italia e a chi chiedere se si blocca.
slug: postnl-tracking
published: 2026-10-22
updated: 2026-10-27
---

PostNL è la posta olandese. Porta all'estero i pacchi dei negozi olandesi e, con Spring GDS, la sua divisione internazionale, quelli di venditori online di tutto il mondo. Lo segui su [postnl.post](https://postnl.post/), il sito internazionale di PostNL, in inglese, o nel box qui sopra. In Italia lo consegna di solito Poste Italiane o, per i pacchi Parcel EU con codice 3S, un partner di PostNL. Il codice è nell'e-mail di spedizione del negozio.

## Codice di tracking PostNL: com'è fatto

PostNL usa due tipi di codice a barre, secondo la sua documentazione per i negozi. Con cifre inventate:

| Codice | Cos'è |
| --- | --- |
| `3SABCD0000000` | 3S, il codice cliente del mittente in lettere, poi cifre: 13 caratteri. È Parcel EU, il servizio pacchi di PostNL dai Paesi Bassi verso gli altri Paesi dell'UE, Italia compresa. |
| `3SABCD000000000` | Lo stesso tipo di codice a barre di PostNL, nella forma da 15 caratteri. |
| `LA000000005NL` | Due lettere, nove cifre e NL: il codice postale internazionale di PostNL. L: un pacchetto o una lettera con tracking. Anche `LW`. |
| `RI000000005NL` | R: una raccomandata. Anche `RF`. |
| `UL000000005NL` | U: un pacchetto non tracciato. Non contare su scansioni in Italia. |

L'ultima delle nove cifre di un codice NL è una cifra di controllo: se ne sbagli una, di solito il codice risulta non valido.

postnl.post accetta fino a 150 codici alla volta. Risponde ai codici NL e ai 3S da 15 caratteri. Un 3S da 13 caratteri invece lo rifiuta con "The shipment barcode was incorrect.". In quel caso chiedi al negozio il codice del partner: quando crea l'etichetta Parcel EU, PostNL gli dà anche il codice a barre del partner in Italia che consegnerà il pacco, e con quel codice puoi seguirlo dal partner. Finché il pacco è nei Paesi Bassi, però, quel codice può ancora cambiare.

## Stati PostNL: cosa vogliono dire

postnl.post mostra una barra di avanzamento, `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered`, e sotto le scansioni, in inglese. Quasi tutte indicano il Paese in cui sono avvenute. Le righe più comuni, più o meno nell'ordine:

| postnl.post scrive | Cosa vuol dire per te |
| --- | --- |
| `Pre-advised`, `Preparing` | Il mittente ha prenotato la spedizione e ha mandato i dati a PostNL. Il pacco potrebbe non essere ancora partito. |
| `Consignment received at the PostNL Acceptance Centre` | PostNL ha preso in carico la spedizione del mittente. |
| `The item is received by PostNL` | PostNL ha scansionato il tuo pacco. Può arrivare giorni dopo la riga precedente. |
| `Departed`, `Transit` | In viaggio tra un Paese e l'altro. |
| `Arrived` | Arrivato in un Paese del percorso, di solito l'Italia. |
| `The item is at the local sorting centre` | In un centro di smistamento del Paese indicato accanto. |
| `The item is at the local delivery office` | Nell'ufficio che lo consegnerà. |
| `The item is out for delivery` | In consegna. |
| `Driver is en route to the pickup location` | In viaggio verso un punto di ritiro, non verso casa tua. |
| `Pick-up point` | Ti aspetta in un punto di ritiro. |
| `Unsuccesfull`, `Undelivered` | Tentativo di consegna non riuscito. L'errore di ortografia è di PostNL. |
| `Undeliverable item, has been returned to shipper` | Torna al mittente. |
| `Delivered` | Consegnato. |

`The shipment barcode was not found.` vuol dire che postnl.post non ha ancora niente con quel codice, o che c'è un carattere sbagliato.

## Chi consegna i pacchi PostNL in Italia

PostNL o Spring GDS portano il pacco fino in Italia. Chi lo consegna dipende dal codice:

- **Codice NL**: è un codice postale internazionale, nel formato S10 che le poste usano tra loro. In Italia di solito lo consegna [Poste Italiane](carrier:poste-italiane): su poste.it inserisci il codice nel Cerca Spedizioni. Poste mostra un pacco dal momento in cui lo prende in carico in Italia; per la corrispondenza con merce, anche la tratta estera, ma solo se il Paese d'origine l'ha resa disponibile.
- **Codice 3S (Parcel EU)**: lo consegna un partner di PostNL in Italia. PostNL non dice chi è: lo chiama solo il suo partner all'estero. Il codice del partner ce l'ha il negozio.
- **Pacchi da negozi fuori dai Paesi Bassi (Spring GDS)**: Spring GDS gestisce la tratta internazionale e un partner locale la consegna finale. Una volta entrato nel tuo Paese, scrive, il pacco passa spesso al servizio postale locale. Il nome del corriere compare nella cronologia del tracking.

Tentativi di consegna, avviso e giacenza seguono le regole di chi consegna: per Poste li trovi nella pagina [Poste Italiane](carrier:poste-italiane), e in generale in [pacco non ritirato](guide:uncollected-parcel). Un pacchetto non tracciato (`UL`) può non comparire affatto sul Cerca Spedizioni.

## Pacco PostNL fermo, perso o rispedito

**Ancora nessuna scansione.** Con `Pre-advised` o `Preparing` il venditore ha creato l'etichetta ma forse non ha ancora consegnato il pacco. Spring GDS dice di aspettare 24-48 ore dal momento in cui ricevi il codice.

**Fermo in viaggio.** Dopo `Departed` o `Transit` un pacco in aereo o in nave non ha scansioni fino all'arrivo. Poi prova lo stesso codice NL su poste.it. Altri consigli in [tracking fermo](guide:tracking-not-updating).

**Consegnato ma non c'è.** Spring GDS consiglia di chiedere ai vicini, di guardare nei posti dove il corriere lascia i pacchi e alla portineria. Scrive anche che a volte un pacco risulta consegnato fino a 24 ore prima che arrivi davvero. Poi contatta il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o danneggiato.** Avvisa subito il venditore e fotografa l'imballo rovinato: le foto servono al venditore per aprire una pratica con Spring GDS. Vedi [pacco smarrito](guide:lost-parcel).

**Rispedito al mittente.** Chiedi al venditore di rispedirlo o di rimborsarti. Spring GDS cita come motivi frequenti l'indirizzo sbagliato e il pacco non ritirato in tempo, e dice che un reso avviato non si può fermare. Vedi [rispedito al mittente](guide:returned-to-sender).

## Contatti PostNL

Come letto il 22 ottobre 2026:

- **postnl.post**: per domande su una consegna fuori dai Paesi Bassi rimanda al negozio ("Please contact your webshop"). Per le consegne nei Paesi Bassi indica il servizio clienti di PostNL.
- **Spring GDS**: non può parlare delle spedizioni con i consumatori, per la privacy e per i contratti con i venditori. Il venditore è il titolare del contratto e apre lui le verifiche.
- **Chi consegna in Italia**: per un tentativo di consegna mancato o una nuova data contatta direttamente lui; Spring GDS non può organizzare nuove consegne locali. I contatti di Poste sono nella pagina [Poste Italiane](carrier:poste-italiane).

Per tutto il resto, la via più rapida è il negozio o il marketplace dove hai comprato.

## Traccia un pacco PostNL con Peek

[Peek](/) riconosce subito i codici che finiscono con NL, se la cifra di controllo è giusta, e i codici 3S con un codice cliente di quattro lettere. Un 3S con tre lettere va a DHL eCommerce Netherlands, anche se le linee guida di PostNL prevedono codici di tre lettere pure per i suoi pacchi. Con meno lettere Peek non capisce il corriere dalla forma: incollalo lo stesso, Peek segue più di 3.500 corrieri. Mentre controlla vedi «verifica presso…»; se sceglie il corriere sbagliato, scegli PostNL con «Cambia». I link di postnl.post (`/track?barcodes=`) e di mailingtechnology.com, il tracking di Spring GDS (`/tracking?tn=`), vengono letti come PostNL.

Peek legge postnl.post. Mostra lo stato, la cronologia con il Paese di ogni scansione, l'ora di consegna e il Paese di destinazione, con gli orari letti sull'ora locale di ogni Paese e mostrati nel fuso orario del tuo dispositivo. PostNL non dà una data di consegna prevista né il mittente. Con un codice NL, quando PostNL indica l'Italia come destinazione, Peek chiede anche a Poste Italiane con lo stesso codice e passa a Poste appena mostra scansioni più recenti. Se PostNL non indica una destinazione, Peek usa il tuo Paese: nell'app per iPhone la regione impostata, nel browser il Paese da cui hai aggiunto il pacco. postnl.post rifiuta i 3S da 13 caratteri: per quelli Peek cerca il pacco nei [servizi di tracking universali](guide:universal-tracker).

Peek controlla PostNL ogni 30 minuti (anche quando il pacco è in consegna) e di notte ogni una o due ore, e può avvisarti a ogni scansione, solo per i passaggi importanti o solo il giorno di consegna. Non può contattare PostNL, Poste o il negozio al posto tuo.

## Domande sul tracking PostNL

### Dove si trova il mio pacco PostNL?

L'ultima scansione dice a che punto è e in quale Paese. Se ha un codice NL e risulta arrivato in Italia, prova lo stesso codice su poste.it: lì trovi le scansioni italiane.

### Cosa vuol dire "The shipment barcode was incorrect"?

Che postnl.post non accetta quel codice. Di solito è un 3S da 13 caratteri, cioè un pacco Parcel EU: chiedi al negozio il codice del partner che lo consegna in Italia e seguilo da lui.

### PostNL consegna con Poste Italiane?

Con un codice NL, di solito sì. Spring GDS, la divisione internazionale di PostNL, scrive che nel tuo Paese il pacco passa spesso al servizio postale locale, e su poste.it il codice NL funziona una volta che Poste ha preso in carico il pacco. I pacchi Parcel EU con codice 3S li consegna invece un partner di PostNL, che PostNL non nomina.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 codici, barra di avanzamento, scansioni, risposte "not found" e "incorrect", rimando al negozio
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – codici 3S da 13 o 15 caratteri, codice cliente in lettere, codici S10 con NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU con 3S da 13 caratteri e Italia tra le destinazioni, codice del partner estero, LA/LW, RI/RF, UL
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – divisione internazionale del gruppo PostNL
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – partner locale, servizio postale locale, prima scansione, consegnato ma non ricevuto, danni, resi, nuove consegne, nessun contatto con i consumatori
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – Cerca Spedizioni, tracking dalla presa in carico in Italia, tratta estera
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – formato del codice usato tra le poste, cifra di controllo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek
:::
