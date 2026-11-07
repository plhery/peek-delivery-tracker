---
title: BRT tracking: dov'è il pacco e cosa fare se non arriva
description: Tracking BRT: dove trovi il BRTcode, cosa vogliono dire gli stati, giacenza, BRT-fermopoint e locker, numero verde e cosa fare se il pacco non arriva.
slug: brt-tracking
published: 2026-10-10
updated: 2026-11-07
---

BRT è un corriere espresso italiano del gruppo Geopost, come DPD. Per seguire il pacco apri il link nell'SMS o nell'e-mail di BRT, cerca il numero in «Dov'è la mia spedizione?» su [brt.it](https://www.brt.it/it/) oppure incollalo nel box qui sopra. Il numero, il BRTcode, te lo comunica BRT quando prende in carico la spedizione, se il mittente le ha dato il tuo cellulare o la tua e-mail.

## Numero di tracciamento BRT: dove si trova e com'è fatto

Il BRTcode è negli SMS e nelle e-mail di presa in carico, sulla lettera di vettura BRT e sul tagliando di lasciato avviso. Nessun messaggio? Forse il mittente non ha dato i tuoi contatti a BRT: chiedi il numero al negozio, o guarda [dove altro può trovarsi il codice](guide:find-tracking-number).

Le pagine di BRT indicano lunghezze diverse e il riquadro di brt.it accetta «12, 14, 15 o 19 cifre», quindi conta la forma (cifre inventate):

| Forma | Che cos'è |
| --- | --- |
| `012345678912` | 12 cifre: il numero di spedizione, sulla lettera di vettura. |
| `01234567891234` | 14 cifre: BRTcode o numero di spedizione, per la pagina dei codici di BRT. |
| `012345678912345` | 15 cifre: per Peek, un ID collo cliente, il codice che il mittente applica sui colli. |
| `EOB01234567890123456` | `EOB` e 17 cifre: anche questo un ID collo cliente, applicato dal mittente. |
| 19 cifre | Il BRTcode, per il modulo di ricerca di vas.brt.it. |

Non sono numeri di tracciamento il codice di sicurezza di 3 cifre, che serve a modificare la consegna, e il numero di giacenza del dettaglio spedizione (3 caratteri della filiale più 7 progressivi).

Nessun risultato? Il numero può essere sbagliato, oppure la spedizione non è ancora nel sistema di BRT. La [Ricerca Avanzata](https://vas.brt.it/vas/sped_numspe_par.htm) di vas.brt.it copre 12 mesi, o 2 con l'ID collo cliente.

Le stesse forme le usano altri corrieri: 14 cifre anche DPD in Svizzera, Germania e Regno Unito, 12 anche Mondial Relay, Colis Privé e altri. Controlla che il messaggio di spedizione nomini BRT, o leggi [come capire il corriere dal numero](guide:tracking-number-formats).

## Stati del tracciamento BRT: cosa significano

Il tracciamento su vas.brt.it si apre in italiano e si può passare a English, Français o Deutsch. Nessuna pagina di BRT elenca gli stati: qui sotto trovi le diciture della versione italiana e, accanto, quelle della versione inglese (per i locker e per le consegne all'estero, Peek conosce solo quelle inglesi); myBRT può usare parole diverse. Anche in inglese le colonne restano Data, Ora, Filiale ed Evento, e la filiale accanto a ogni stato è quella a cui chiedere informazioni. Le fasi che ogni pacco attraversa, con qualsiasi corriere, le trovi in [cosa vogliono dire gli stati della spedizione](guide:tracking-statuses). Nell'ultima colonna c'è lo stato che Peek mostra per il pacco.

| Stato BRT | In inglese | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- | --- |
| `DATI SPEDIZ. TRASMESSI A BRT` | `SHIPPING INFO SENT TO BRT` | BRT ha i dati della spedizione, ma non ancora il pacco. | «Etichetta creata» |
| `RITIRATA` | `COLLECTED` | BRT ha ritirato il pacco dal mittente. | «Preso in carico» |
| `PARTITA` | `DEPARTED` | È partito da una filiale o da un hub BRT. | «In transito» |
| `ARRIVATA IN FILIALE` | `ARRIVED AT DEPOT` | È arrivato alla filiale indicata nella colonna Filiale. | «In transito» |
| `IN CONSEGNA` | `FOR DELIVERY` | È con il corriere per la consegna. | «In consegna» |
| `Destin.Assente:LASCIATO AVVISO` | `NOTICE OF ATTEMPTED DELIVERY` | Il corriere non ha trovato nessuno e ha lasciato un avviso. | «Tentativo di consegna» |
| `DESTINATAR.SCONOSC./INCOMPLETO` | `UNKNOWN/INCOMPLETE CONSIGNEE` | Non consegnato: destinatario o indirizzo non trovati. | «Tentativo di consegna» |
| `MANIFESTAZIONE PUBBLICA` | `TURMOIL` | Una manifestazione lo blocca temporaneamente. | «Problema segnalato» |
| `CONSEGNATA` | `DELIVERED` | Consegnato. | «Consegnato» |
| `ARRIVATA AL BRT-fermopoint` | `ARRIVED AT BRT-fermopoint` | Ti aspetta in un BRT-fermopoint. | «Pronto per il ritiro» |
| `RITIRATA AL BRT-fermopoint` | `COLLECTED AT BRT-fermopoint` | Ritirato al BRT-fermopoint. | «Consegnato» |
| – | `ARRIVED AT BRT LOCKER` | Ti aspetta in un locker BRT. | «Pronto per il ritiro» |
| – | `COLLECTED AT BRT LOCKER` | Ritirato dal locker. | «Consegnato» |
| – | `To collect at the parcel shop` | All'estero: il corriere che lo consegna l'ha lasciato in un suo punto di ritiro. | «Pronto per il ritiro» |
| – | `ABSENT AFTER ATTEMPT.DELIVERY` | All'estero: il corriere che lo consegna non ha trovato nessuno; seguirà un nuovo passaggio o il reso al mittente. | «Tentativo di consegna» |
| `RESO MITTENTE` | `RETURNED TO SENDER` | Sta tornando al mittente. | «Restituito al mittente» |

`RITIRATA` da sola è il ritiro dal mittente, non il tuo; se il pacco è arrivato dall'estero con Chronopost o DPD, è il passaggio a BRT e Peek mostra «In transito». Destinatario sconosciuto e indirizzo incompleto (in inglese `UNKNOWN/INCOMPLETE CONSIGNEE`: il «consignee» sei tu) sono tra i motivi per cui BRT mette un pacco in giacenza, e per correggere l'indirizzo serve il via libera del mittente. Un `CONSEGNATA` dopo `RESO MITTENTE` è il pacco tornato al mittente, anche se Peek mostra «Consegnato». Se la spedizione va all'estero sulla rete DPD, un riquadro DPD nel dettaglio dà accesso a Track e Tracing.

## Pacco BRT non consegnato: giacenza, fermopoint e locker

Se non ci sei, BRT ti avvisa per e-mail, SMS o con un avviso cartaceo. Se il mittente le ha dato i tuoi contatti, ricevi anche le indicazioni della nuova consegna, che di solito viene riprogrammata per il giorno lavorativo successivo. Secondo la Carta dei servizi segue un secondo tentativo e, se va a vuoto anche quello, la filiale ti telefona.

Con il link del messaggio di BRT, oppure nell'app myBRT o su [mybrt.it](https://www.mybrt.it/it/mybrt/) con il BRTcode e il CAP e/o il PIN, puoi scegliere un altro giorno o un'altra fascia oraria, un BRT-fermopoint o il ritiro in filiale, sbloccare una giacenza o cambiare indirizzo, se il mittente lo autorizza. Alcune funzioni potrebbero mancare per la tua spedizione.

**In giacenza.** Succede se il destinatario è assente, sconosciuto o trasferito, se l'indirizzo è errato o incompleto, o se la merce è stata rifiutata. Sblocca la giacenza dal link nell'e-mail o nell'SMS di BRT, o dal tracciamento con il BRTcode. Intanto BRT chiede istruzioni al mittente (consegna, dirottamento o rientro): secondo la Carta, se non arrivano per iscritto, la merce torna indietro 10 giorni dopo la comunicazione di giacenza; le condizioni di contratto di BRT parlano invece dei giorni concordati con il mittente.

**In filiale (fermo deposito).** Puoi ritirare tu o un tuo delegato. Il delegato porta il suo documento d'identità, il tuo (anche in fotocopia) e la delega per privati della pagina [Download](https://www.brt.it/it/download/) di BRT; per chi ritira di persona BRT non indica quale documento serva. Se il mittente ha dato i tuoi contatti, BRT ti manda indirizzo e orari della filiale e l'importo dell'eventuale contrassegno.

**BRT-fermopoint.** Sono edicole, tabaccherie, cartolerie e altre attività convenzionate, alcune aperte anche nel weekend. Ti avvisano per e-mail e/o SMS e tengono il pacco 10 giorni. Il più vicino lo trovi con la [ricerca BRT-fermopoint](https://www.mybrt.it/it/mybrt/parcel-shops/search).

**Locker BRT.** Stanno in negozi, centri commerciali e distributori di carburante, e quelli all'aperto sono accessibili 24 ore su 24. Scansiona il QR Code ricevuto per e-mail o SMS, o digita il PIN Code di 8 cifre. Per uno schermo guasto o un cassetto vuoto c'è il modulo in fondo all'e-mail di ritiro.

Un pacco sopra i 20 kg non va né in un BRT-fermopoint né in un locker, e il locker prende un solo collo, con il lato più lungo sotto i 50 cm. Per le condizioni di contratto di BRT, in un punto di ritiro non va nemmeno un pacco in contrassegno.

## Pacco BRT in ritardo, smarrito o danneggiato

In Italia BRT consegna «generalmente in 24/48 ore», con 12/24 ore in più per Calabria, Sicilia, Sardegna e le località a oltre 800 km, e verso l'estero in 48/72 ore nella maggior parte dei Paesi europei. Sono tempi indicativi, e BRT non dice dopo quanto un pacco è smarrito. Il cliente di BRT è il negozio: chiedi prima a lui di verificare, poi alla filiale indicata accanto all'ultimo stato, oppure scegli «La consegna è in ritardo» nella pagina [Assistenza](https://services.brt.it/it/assistenza) di BRT. Prima di aprire il modulo, BRT controlla la spedizione: se sta ancora viaggiando, ti rimanda al tracking di myBRT; se il mittente non l'ha ancora affidata a BRT, al mittente. Leggi anche [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Danneggiato o smarrito.** Se hai pagato il trasporto al corriere o in filiale, usa il [modulo danni](https://services.brt.it/it/form-danni) di BRT, con le foto se è un danno; se no, avvisa il mittente. Se alla consegna il pacco è rovinato, scrivi sulla lettera di vettura riserve specifiche e dettagliate: per BRT quelle generiche non valgono. Un danno che non si vede subito va comunicato a BRT per iscritto entro 8 giorni di calendario dal ricevimento, per le spedizioni nazionali.

**Reclamo.** Scarica il modulo PDF dalla pagina [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/), compilalo in tutte le sue parti e allegalo al form services.brt.it/it/form-agcom: può farlo anche il destinatario. BRT risponde entro 45 giorni. Se la risposta manca o non ti soddisfa, puoi chiedere la conciliazione scrivendo a conciliazioni@brt.it: la gestisce la Casa del Consumatore, a cui devi iscriverti (50 € l'anno, 25 € per controversie fino a 50 €). Dopo la conciliazione puoi chiedere all'AGCOM di risolvere la controversia.

## BRT contatti: numero verde, e-mail e moduli

I canali di BRT per chi riceve un pacco, come risultavano il 5 novembre 2026:

- **Moduli** dalla pagina [Assistenza](https://services.brt.it/it/assistenza), che ha preso il posto di «Contattaci»: scegli il caso (ritardo, tentata consegna mentre eri in casa, ritiro al BRT-fermopoint o al locker, anche per un QR Code mai arrivato, consegnato ma non ricevuto, contenuto mancante, danno, pacco non tuo) e arrivi al modulo. Lì non c'è un numero di telefono.
- **Numero verde** 800 278 708, dal lunedì al venerdì dalle 8.30 alle 18.30, scritto nella Carta dei servizi e non sulle pagine del sito.
- **E-mail** assistenzaclienti@brt.it, sempre dalla Carta: BRT risponde solo se indichi nome, cognome, CAP di ritiro e di consegna e BRTcode.
- **Chat** sul sito brt.it.
- **Filiale:** quella indicata nel tracciamento, o la più vicina con la [ricerca filiale](https://services.brt.it/it/servizio-clienti/ricerca-filiale).

Il numero di Bologna (051) sui documenti BRT è la sede operativa, non il servizio clienti. Dal Ticino o dall'estero: BRT non indica canali dedicati, né dice se il numero verde funziona fuori dall'Italia.

> Il 16 aprile 2026 BRT ha segnalato SMS ed e-mail falsi a suo nome, per esempio su presunte «mancate consegne». Controlla il mittente e, nel dubbio, non cliccare sui link e non scaricare allegati.

## Seguire un pacco BRT con Peek

[Peek](/) riconosce i numeri BRT di 12, 14 e 15 cifre e i codici `EOB`. Per gli `EOB` e per la maggior parte dei numeri di 15 cifre chiede solo a BRT; i numeri di 12 e 14 cifre li usano anche altri corrieri, quindi prova BRT tra i più probabili; se ti chiede di scegliere, prendi il corriere nominato nel messaggio di spedizione. Funzionano anche i link di vas.brt.it con `brtCode=`, non i BRTcode di 19 cifre né i link di mybrt.it.

Se usi Peek in italiano, chiede a BRT la versione italiana e ti mostra le sue diciture, ma per una consegna (`CONSEGNATA` o `RITIRATA AL BRT-fermopoint`) scrive solo «Consegnata». Se trova una dicitura italiana che non conosce, come quelle dei locker, richiede tutto in inglese; se usi Peek in un'altra lingua, le diciture sono sempre quelle inglesi. Con queste, per le tre di consegna (`DELIVERED`, `COLLECTED AT BRT-fermopoint` e `COLLECTED AT BRT LOCKER`) Peek scrive solo «Delivered», tradotto nella lingua in cui lo usi («Consegnato» in italiano). Mostra anche il luogo di ogni scansione su una mappa e il peso, e può mandarti notifiche quando lo stato cambia. Lascia fuori mittente, destinatario, riferimento del mittente, numero di colli e volume; non mostra i dati del punto di ritiro. BRT non dà un giorno di consegna previsto: per un numero di 14 cifre Peek può mostrare la previsione del tracking di DPD, che riconosce anche i numeri BRT, o quella di Chronopost se il pacco è arrivato in Italia con Chronopost. La previsione scompare quando il giorno previsto è passato, o prima se BRT consegna il pacco o lo rimanda al mittente, lo lascia in un punto di ritiro, tenta invano la consegna o segnala un problema. BRT dà a ogni scansione data e ora senza fuso orario: Peek le legge sull'ora italiana, o sull'ora locale di un deposito all'estero, e mostra la cronologia di BRT nel suo ordine.

Peek controlla il pacco ogni 10 minuti, ogni 2 quando è in consegna (ogni 5 se deve aprire il tracking di DPD in un browser). I controlli più fitti valgono solo il giorno in cui il pacco va in consegna, dalle 6.00 ora locale (se la previsione di DPD o di Chronopost indica una fascia oraria, da mezz'ora prima che inizi), e finché risponde il tracking di BRT o di DPD; quando rispondono i servizi di tracciamento, ogni 15 minuti. Dopo due giorni senza novità, o dopo 12 ore se il pacco è ancora su «Etichetta creata» o «Preso in carico», Peek controlla solo una volta all'ora.

## Domande sul tracking BRT

### BRT consegna anche il sabato?

Può farlo, se il negozio ha scelto quel servizio: BRT elenca la consegna al sabato tra le opzioni per l'e-commerce, e nessuna sua pagina dice che sia la regola. Nei tempi di consegna del contratto, sabato e domenica non contano.

### Quanto tempo ho per ritirare un pacco al BRT-fermopoint?

10 giorni, secondo la pagina BRT-fermopoint. Per i locker e per il fermo deposito in filiale BRT non pubblica un tempo.

### Pacco BRT in giacenza: quanto tempo resta?

Secondo la Carta dei servizi, 10 giorni dalla comunicazione di giacenza: se intanto il mittente non dà istruzioni scritte, la merce torna indietro (le condizioni di contratto di BRT parlano invece dei giorni concordati con il mittente). Prima puoi sbloccarla dal link nell'e-mail o nell'SMS di BRT, o dal tracciamento con il BRTcode.

### Quanto costa la giacenza BRT?

BRT non pubblica una cifra. Il campo «Addebito spese» del dettaglio spedizione dice se le spese di giacenza vanno addebitate e a chi, mittente o destinatario, una volta arrivate le istruzioni del mittente.

### Pacco BRT risulta consegnato ma non ricevuto: cosa fare?

Chiedi prima a vicini e familiari, poi al negozio di controllare la lettera di vettura firmata, che il mittente vede con il suo codice cliente (P.O.D Image). Se non basta, scegli «La spedizione risulta consegnata ma non l'ho ricevuta» nella pagina Assistenza di BRT e compila il modulo; resta poi il reclamo descritto sopra. Vedi anche [pacco consegnato ma non ricevuto](guide:delivered-not-received).

:::sources
- [BRT: FAQ, Domande frequenti sui Servizi BRT](https://www.brt.it/it/servizio-clienti/faq/) – BRTcode, consegna mancata, giacenza, tempi, danni, P.O.D
- [BRT: Servizio Assistenza Clienti BRT](https://www.brt.it/it/servizio-clienti/codici-assistenza/) – 14 cifre, dove si trova
- [BRT: Home](https://www.brt.it/it/) – riquadro di ricerca, chat
- [BRT: Rintraccia spedizione](https://vas.brt.it/vas/sped_numspe_par.htm) – 12 e 19 cifre, 12 e 2 mesi, lingue
- [BRT: Aiuto, Dettaglio della spedizione](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=it) – filiale, giacenza, spese, DPD
- [BRT: Cos'è il codice di sicurezza?](https://www.brt.it/it/faq/cose-il-codice-di-sicurezza/) – 3 cifre
- [BRT: Privati](https://www.brt.it/it/privati/) – myBRT, ricerca fermopoint
- [BRT: BRT-fermopoint](https://www.brt.it/it/brt-fermopoint/) – negozi, weekend, 10 giorni
- [BRT: Locker](https://www.brt.it/it/network/locker/) – QR Code, PIN, limiti
- [BRT: Posso ritirare presso una filiale BRT?](https://www.brt.it/it/faq/posso-ritirare-presso-una-filiale-brt/) – delega, avviso
- [BRT: Download](https://www.brt.it/it/download/) – delega per privati
- [BRT: BRT per l'e-commerce](https://www.brt.it/it/brt-per-ecommerce/) – consegna al sabato
- [BRT: Assistenza](https://services.brt.it/it/assistenza) – segnalazioni, ritardo, consegnato ma non ricevuto, controlli prima del modulo
- [BRT: Carta dei servizi, PDF](https://www.brt.it/wp-content/uploads/sites/275/2026/03/Carta-dei-Servizi_marzo_2026.pdf) – tentativi, giacenza, riserve, contatti, 45 giorni
- [BRT: Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) – reclamo, conciliazione, AGCOM
- [BRT: Modulo di Reclamo](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – anche il destinatario
- [BRT: Modulo di Conciliazione con istruzioni](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – iscrizione
- [BRT: Condizioni generali e particolari](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – giorni convenuti, contrassegno, weekend
- [BRT: Alert Phishing, SMS ed e-mail](https://www.brt.it/it/news/2026/04/16/alert-phishing-sms-email/) – messaggi falsi
- [DPD: Home](https://www.dpd.com/en/) – BRT e DPD in Geopost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek, fasce orarie di DPD e di Chronopost
- [Peek: Routing (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – ogni quanto controlla Peek: 10 minuti, 2 o 5 in consegna, da mezz'ora prima della fascia oraria, 15 con i servizi di tracciamento, una volta all'ora se non ci sono novità
:::
