---
title: Tracciamento nox NachtExpress: stati, deposito e contatti
description: Traccia una spedizione nox NachtExpress: formato del codice a barre, significato degli stati tedeschi, consegna notturna al punto concordato e contatti.
slug: tracciamento-nox
published: 2026-11-23
updated: 2026-11-23
---

nox (nox NachtExpress) è un corriere espresso notturno tedesco, nox Germany GmbH, con sede a Langenfeld. Trasporta ricambi e altre merci urgenti per le aziende. Ritira nel pomeriggio e consegna durante la notte, di regola entro le 8:00 (ora tedesca), in un luogo concordato con il destinatario: un box, un garage, un’officina o il bagagliaio di un furgone di assistenza. Traccia una spedizione con il codice a barre sul [tracker di nox](https://trackandtrace.app.nox.express/scannungen) oppure incolla il numero nella casella qui sopra. Il tracker è in tedesco: le tabelle qui sotto lo spiegano.

## Codice a barre nox: formato e dove trovarlo

nox chiama «barcode» il suo numero di tracciamento. Ogni collo deve avere un’etichetta nox, creata dal mittente alla prenotazione: quindi il mittente conosce il codice a barre. Nessun numero? Chiedi al mittente o leggi [come trovare il numero di tracciamento](guide:find-tracking-number). I formati nel codice di Peek:

| Codice a barre (inventato) | Cos’è |
| --- | --- |
| `220004829173640715482917` | 24 cifre che iniziano con 22000: codice mandante, 000, codice cliente del mittente, data di stampa dell’etichetta e seriale. Il catalogo di Peek conosce questa forma. Anche [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) e [Poste austriache](carrier:austrian-post) usano 24 cifre. |
| `SWR482917364058291736405` | SWR e 21 cifre: un vecchio codice a barre nox. |
| `RA1482917364058482917364` | RA, poi 1 o 2 (tipo di ritiro), 12 lettere o cifre per i clienti e un seriale di 9 cifre: codice a barre di reso. |
| `C48291736405829173640582917364` | C e 29 cifre: codice a barre del mittente, a cui il tracker di nox risponde comunque. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Link a una spedizione sul tracker di nox. Incolla l’intero link in Peek. |

Altri codici a barre nox hanno 24 cifre con un diverso codice mandante a due cifre prima di 000. In Peek gli spazi non contano e le minuscole vanno bene.

## Stati di tracciamento nox spiegati

Il tracker di nox mostra ogni scansione con `Status`, `Scanort` (luogo), `Bemerkung` (nota), `Datum` (data) e `Uhrzeit` (ora). Nella terminologia nox, Collo indica un pacco della spedizione e Niederlassung una filiale nox.

| nox mostra | Cosa significa per te | Stato in Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | Il mittente ha preparato il collo per il ritiro da parte di nox. nox non ce l’ha ancora. | «Etichetta creata» |
| `Collo in der nox Niederlassung eingetroffen` | Il collo è arrivato in una filiale nox. | «In transito» |
| `Collo von der nox Niederlassung weiterverladen` | La filiale lo ha caricato verso un’altra filiale. | «In transito» |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | È fermo in filiale e non è stato consegnato nella fascia prevista. Partirà in una notte successiva. | «In transito» |
| `Collo in nox Zustellfahrzeug geladen` | È caricato sul veicolo di consegna. | «In consegna» |
| `Collo von nox zugestellt` | Consegnato: di solito lasciato nel punto di deposito concordato, senza firma. | «Consegnato» |

Se il tracker risponde `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, nox non riconosce quel codice a barre: verificalo con il mittente. Per altri dettagli o per aprire un reclamo, il tracker chiede di confermare il CAP del destinatario tramite `PLZ validieren/Reklamation erfassen`.

## Come consegna nox: di notte, nel punto di deposito

**Quando.** nox ritira di solito dal mittente tra le 14:00 e le 18:00 (ora tedesca). Secondo le condizioni nox, NachtExpress consegna il giorno successivo, dal martedì al sabato inclusi i festivi, di norma entro le 8:00 (ora tedesca). Il mittente può prenotare la consegna entro le 7:00 o le 6:00 (ora tedesca). nox vende anche consegne nei weekend e festivi come servizio separato (WochenendService). Per colli oltre 100 kg, o più lunghi di 3,20 m, più larghi di 1,20 m o più alti di 1,50 m, l’orario può variare. Le spedizioni verso isole del Mare del Nord e del Mar Baltico non hanno garanzia di orario.

**Dove.** Poiché nox consegna mentre molti clienti dormono ancora, lascia la spedizione in un punto di deposito concordato in anticipo, di solito senza firma. Esempi forniti da nox:

- un box dedicato presso la sede aziendale;
- officina, autolavaggio, sala pausa o garage;
- il bagagliaio del veicolo di un tecnico dell’assistenza;
- il portiere, che riceve il collo di persona dall’autista;
- una delle stazioni di servizio nox accessibili 24 ore su 24, come una stazione di rifornimento sempre aperta.

**Impostare il punto di deposito.** Registralo o aggiornalo in qualsiasi momento, con foto e descrizione, nello strumento online [ready2nox](https://ready2nox.de/). Per le condizioni nox è l’unico modo per fissarlo, e resta valido finché non lo modifichi lì. Una volta lasciato il collo in quel punto, risulta consegnato.

**Senza punto di deposito.** Secondo le condizioni nox, nox può lasciarlo al tuo indirizzo. Se il rischio di smarrimento è chiaramente troppo alto, può non consegnarlo e rispedirlo al mittente.

**Ritiro in filiale.** Come servizio extra puoi ritirare le spedizioni nella filiale nox, dove vengono custodite per te.

**Prova.** Quando non c’è nessuno, nox fotografa il collo e il punto in cui è stato lasciato, conservando le foto come prova. nox può condividere una foto caso per caso con chi dimostra un interesse legittimo, ma non esiste un diritto legale a riceverla.

**Fuori dalla Germania.** nox opera in altri 20 paesi europei tramite consociate e partner. Spedire all’estero richiede un accordo preventivo del mittente con nox e le regole sui reclami possono variare per paese.

## Spedizione nox in ritardo, danneggiata o smarrita

Per una sostituzione il tuo riferimento è l’azienda che ha spedito la merce.

**Fermo.** Se `Collo durch Versender bereitgestellt zur Abholung` resta l’unica scansione, nox non ha ancora ritirato il collo: chiedi al mittente. Se è fermo in filiale, partirà una notte successiva (vedi tabella sopra). Per un ritardo usa il tracker nox: verifica il CAP e scegli `Laufzeitverzug` (ritardo) o `Sonstige Anliegen` (altro). Vedi anche [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato ma non c’è.** Controlla prima il punto di deposito concordato, poi gli altri punti usati dalla tua azienda. Chiedi al portiere o al tecnico il cui veicolo può averlo ricevuto. Avvisa poi subito nox prima della scadenza sotto. Maggiori dettagli in [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Per NachtExpress, nox deve ricevere ogni reclamo e segnalazione di danno entro le 12:00 (ora tedesca) del giorno di consegna, per iscritto e con descrizione. Se quel giorno è sabato, domenica o festivo, la scadenza è alle 12:00 (ora tedesca) del giorno lavorativo successivo. Una riserva generica non basta, e scaduto il termine le condizioni nox escludono ogni responsabilità. Conserva la merce nell’imballaggio originale affinché nox possa ispezionarla e ritirarla, altrimenti non risponde. Segnalalo sul tracker dopo la verifica del CAP (`Reklamation einer Beschädigung`), tramite il modulo contatti o nel portale clienti. Vedi [pacchi danneggiati](guide:damaged-parcel).

**Dogana.** Le pagine nox menzionano la dogana solo come servizio offerto ai clienti per importazioni ed esportazioni. Chiedi al mittente e leggi [pacchi fermi in dogana](guide:customs).

## Contatti e assistenza clienti nox

Peek non può contattare nox né il mittente al posto tuo. Recapiti sui siti nox:

- **Assistenza clienti:** +49 (0) 4224 920 000, dal lunedì al venerdì dalle 7:00 alle 17:00 (ora tedesca), oppure customerservice@nox-nachtexpress.de, come indicato sul tracker di nox.
- **Modulo di contatto:** [modulo di contatto nox](https://www.nox.eu/de/de/kontakt/kontaktformular/), per domande e reclami.
- **Reclami:** sul tracker dopo la verifica del CAP. I moduli (ritardo, danno, altro) chiedono se sei il destinatario (Empfänger), il mittente (Versender) o un terzo committente.
- **Azienda:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Germania; welcome@nox.eu.

Per seguire tutte le proprie spedizioni un’azienda ha bisogno di un account sul portale clienti nox: richiedilo tramite il modulo di contatto.

## Tracciare una spedizione nox con Peek

Peek non riconosce nox dal solo numero: i codici a barre `22000` hanno la stessa lunghezza di Ciblex, InPost, bpost e Poste austriache, e Peek non associa gli altri codici a nox dalla forma. Incollalo ugualmente: Peek segue oltre 4.500 corrieri. Aggiungendo un codice `22000`, Peek interroga i corrieri con quella forma — prima quelli del tuo paese («Verifica dei servizi di tracciamento…») — e mostra «nox ha questo pacco.» quando il tracker nox lo conosce. Se più corrieri lo conoscono, scegli tu.

Se nessuno risponde ancora, la spedizione mostra «Nessuna cronologia di tracciamento per ora» mentre Peek continua a controllare, oppure «In attesa di nox» una volta selezionato nox. Per selezionarlo manualmente premi «Cambia» e scegli nox: lo trovi sotto «Compatibili con questo numero» per un codice `22000`; per un codice SWR, RA o C cercalo (lo trovano anche NOX Nachtexpress, Innight Express e TNT Innight). Su un pacco già aggiunto usa «Cambia corriere». Se scegli nox per un numero di forma diversa, Peek indica che «non emette numeri come questo» e non interroga nox: dopo l’aggiunta, se nessun servizio di tracciamento ha la spedizione, mostra «Controlla il numero di tracciamento». Un link dal tracker nox su `trackandtrace.app.nox.express` o `tcs.app.nox.express` assegna subito la spedizione a nox.

Peek legge lo stato, il testo tedesco e l’orario di ogni scansione (ora tedesca) e l’ora di consegna. Omette luogo e nota della scansione e non chiede il CAP, quindi non mostra ciò che il tracker rivela dopo la verifica del CAP. Peek non mostra giorno stimato di consegna, peso, punto di deposito, foto di consegna né nome del firmatario.

Giorno e notte, mentre tieni la spedizione aperta in Peek o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e dalle 6:00 (ora tedesca) del giorno in cui nox registra la spedizione in consegna fino a ogni 2 minuti finché il tracker proprio di nox risponde. Dopo due giorni senza novità (12 ore finché la spedizione resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora; e se resta su «Etichetta creata» per sette giorni, ogni 6 ore.

Se una spedizione ancora su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza una nuova scansione, Peek la segnala con «Nessun aggiornamento da quattro giorni» (o «Nessun aggiornamento da …»), spiega: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a nox.» e propone «Chiedi aiuto a nox». Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può impostare il punto di deposito né aprire un reclamo al posto tuo.

## Domande sul tracciamento nox

### Cos’è nox NachtExpress?

Il servizio espresso notturno di nox Germany GmbH, con sede a Langenfeld. La merce ritirata entro il primo pomeriggio raggiunge il destinatario prima dell’inizio del lavoro il giorno lavorativo successivo. I settori principali sono automotive, veicoli commerciali, macchine edili e agricoltura, oltre a soluzioni dedicate alla sanità.

### Quando consegna nox?

Durante la notte, di regola entro le 8:00 (ora tedesca), dal martedì al sabato inclusi i festivi. Il mittente può prenotare la consegna entro le 7:00 o le 6:00 (ora tedesca).

### Dove lascia nox la mia spedizione?

Nel punto di deposito concordato per il tuo indirizzo, impostato nello strumento ready2nox di nox. Senza un punto concordato, nox può lasciarla al tuo indirizzo.

### nox richiede una firma?

Di solito no: nox consegna senza ricevuta, spesso quando non c’è nessuno, e poi fotografa il collo nel punto in cui è stato lasciato. Con un portiere, l’autista lo consegna di persona.

### Come segnalo una consegna nox danneggiata?

Per una consegna NachtExpress, entro le 12:00 (ora tedesca) del giorno di consegna, per iscritto, tramite il tracker o il modulo di contatto nox. Conserva merce e imballaggio originale. Se la consegna cade nel weekend o in un festivo, hai tempo fino alle 12:00 (ora tedesca) del giorno lavorativo successivo.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – modulo barcode che apre il tracker nox, portale clienti per tutte le spedizioni
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – colonne del tracker, codice sconosciuto, verifica CAP, moduli reclamo, telefono, orari ed e-mail assistenza, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – ritiro 14:00–18:00, consegna entro le 8:00, punti di deposito, colli grandi, altri 20 paesi, reclami via modulo contatti, scadenza danni
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – entro 8:00, 7:00 o 6:00, consegna senza ricevuta, esempi di punti di deposito, ritiro in filiale, servizio doganale
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – esempi di punti di deposito, ready2nox con foto e descrizioni
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – consegna prima dell’orario di lavoro, clienti addormentati, settori chiave
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – etichette nox, giorni di consegna, punto di deposito in ready2nox, consegna senza punto concordato, foto, isole, spedizioni estere, termini reclamo, regole per paese
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – condizioni generali vigenti
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – modulo di contatto
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, telefono, e-mail
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – formati barcode, link tracker, diciture e fasi di scansione, ora tedesca, cosa legge Peek
:::
