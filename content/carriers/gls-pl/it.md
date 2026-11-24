---
title: Tracciamento GLS Poland: stati, GLS Points, locker e aiuto
description: Traccia un pacco GLS Poland: codici a 11 o 12 cifre e Track-ID, CAP facoltativo, stati, GLS Points e locker ORLEN, FlexDeliveryService e contatti.
slug: tracciamento-gls-poland
published: 2026-11-24
updated: 2026-11-24
---

GLS Poland (*General Logistics Systems Poland Sp. z o.o.*, con sede a Głuchowo vicino a Poznań e hub centrale di smistamento a Stryków vicino a Łódź) gestisce circa 50 filiali e oltre 26.000 punti di ritiro fuori casa in Polonia — tra cui più di 18.000 GLS Points (compresi tutti gli 11.000 negozi Żabka) e circa 8.000 locker condivisi 24/7 [ORLEN Paczka](carrier:orlen-paczka). Traccia la spedizione sulla [pagina di tracciamento di GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) con numero pacco o Track-ID, oppure incolla il codice nel riquadro qui sopra.

## Numero di tracciamento GLS Poland e Track-ID: formati

Trovi il numero del pacco nell’e-mail di spedizione del negozio online, negli avvisi via e-mail/SMS di GLS o sull’avviso cartaceo di mancata consegna (*awizo*). Il campo di ricerca GLS accetta anche il Track-ID riportato nelle notifiche GLS.

| Formato (inventato) | Che cos’è |
| --- | --- |
| `48291736405` | Numero pacco GLS a 11 cifre. Usato anche da [GLS Germania](carrier:gls-de), [GLS Francia](carrier:gls-fr), [GLS Svizzera](carrier:gls-ch), [GLS Austria](carrier:gls-at) e [PPL](carrier:ppl). |
| `482917364052` | Numero pacco a 12 cifre terminante con cifra di controllo. Usato anche da [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) e altri. |

Sulla [pagina di tracciamento di GLS Poland](https://gls-group.com/PL/en/parcel-tracking/), inserire il codice postale del destinatario sblocca la cronologia completa delle scansioni e le opzioni di consegna. Per altri formati consulta [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento GLS Poland

GLS Poland mostra descrizioni in polacco sul proprio sito polacco e descrizioni in inglese del gruppo tramite l’API internazionale GLS letta da Peek.

| GLS Poland / GLS Group mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | Il mittente ha creato l’etichetta elettronicamente; GLS non ha ancora ricevuto il pacco fisico. | «Etichetta creata» |
| `Paczka została przekazana GLS` | GLS ha ritirato o accettato il pacco nella propria rete. | «Preso in carico» |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | In transito tra gli hub di smistamento GLS e le filiali polacche verso la filiale di consegna. | «In transito» |
| `In delivery`, `Paczka w doręczeniu` | L’autista ha caricato il pacco per consegnarlo oggi. | «In consegna» |
| Arrivo in un GLS Point o locker, che GLS segna come consegnato | In attesa di ritiro presso un GLS Point o locker 24/7. | «Pronto per il ritiro» |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Consegnato a te, a una persona autorizzata o a un vicino, oppure ritirato al GLS Point. | «Consegnato» |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | Il destinatario ha rifiutato il pacco. | «Tentativo di consegna» |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Rifiutato e trattenuto presso la filiale di consegna. | «Problema segnalato» |

Il giorno della consegna, [Live Tracking di GLS Poland](https://gls-group.com/PL/en/live-tracking/) invia via e-mail un link con l’orario previsto entro 15 minuti e mostra il corriere sulla mappa quando restano 20 fermate o meno. La barra di avanzamento GLS segna come consegnato un pacco depositato in un GLS Point o locker mentre ti aspetta; Peek mostra quel passaggio come «Pronto per il ritiro» e come «Consegnato» dopo il ritiro.

## Tempi di consegna, GLS Points, locker ORLEN e FlexDeliveryService

**Giorni e velocità di consegna.** GLS Poland consegna dal lunedì al venerdì (consegna di sabato solo se ordinata espressamente dal mittente). I pacchi nazionali standard arrivano di solito il giorno lavorativo successivo (non garantito; i mittenti possono scegliere Express 10:00 o 12:00). Dalla Polonia GLS indica 2–7 giorni lavorativi verso altri paesi UE a seconda della destinazione ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Peso, dimensioni e CashService (contrassegno).** I pacchi nazionali a domicilio possono pesare fino a 31,5 kg (40 kg per spedizioni internazionali a domicilio; 31,5 kg in GLS Points selezionati; 20 kg negli altri GLS Points, locker e myGLS), entro 200 × 80 × 60 cm e lunghezza più circonferenza fino a 300 cm. Il contrassegno (`CashService`, fino a 6.000 PLN al giorno) può essere pagato al corriere in contanti, con carta o via BLIK.

**Mancata consegna e secondo tentativo.** Secondo la [pagina ricezione di GLS Poland](https://gls-group.com/PL/en/receiving/), se non sei in casa il corriere può lasciare il pacco a un vicino (con il tuo consenso) o dirottare un pacco idoneo senza contrassegno a un GLS Point vicino. In caso contrario il pacco rientra in filiale per un secondo tentativo il giorno lavorativo seguente; se resta non consegnato, giace in deposito prima di tornare al mittente (5 giorni lavorativi per ritiro in deposito FlexDeliveryService, oppure reso dopo 6 giorni lavorativi secondo la guida alla ricezione).

**Giacenza nei GLS Points.** In base ai [termini di GLS Poland](https://gls-group.com/PL/pl/regulamin/) per GLS Points e locker (in vigore dal 9 novembre 2026) e alle [FAQ](https://gls-group.com/PL/en/faq/), i pacchi restano in un GLS Point per **7 giorni solari** prima del rientro al mittente (le pagine [ricezione](https://gls-group.com/PL/en/receiving/) e GLS Points parlano ancora di sette giorni lavorativi). Ritiralo con il PIN inviato via SMS o e-mail; GLS può controllare il documento.

**Locker 24/7 ORLEN Paczka.** I pacchi indirizzati ai [locker condivisi ORLEN Paczka](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) vengono custoditi per **72 ore** e poi tornano automaticamente al mittente. Apri lo sportello con PIN via SMS/e-mail, codice QR o sblocco remoto nell’app myGLS — non serve documento, quindi chiunque abbia il codice può ritirare. Assistenza tecnica 24/7 per locker: `801 167 536` o `502 167 536`.

**Reindirizzamento FlexDeliveryService.** Tramite il link nell’e-mail di notifica GLS (senza account), [parcel redirection](https://gls-group.com/PL/en/parcel-redirection/) ti consente di scegliere un GLS Point, un locker ORLEN, un nuovo indirizzo, una nuova data, ritiro in deposito, autorizzazione al deposito o rifiuto del pacco.

## Dogana, danni, reclami e pacchi GLS Poland mancanti

Per gli acquisti online il venditore detiene il contratto di spedizione con GLS Poland e gestisce rimborsi o sostituzioni.

**Pacchi da fuori UE.** Le spedizioni interne all’UE non fanno dogana. Per gli ordini online provenienti da fuori UE, secondo le [regole doganali della Commissione Europea](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), si applicano un dazio fisso di 3 € per categoria di articolo fino a 150 € (in vigore dal 1° luglio 2026) e una commissione UE di gestione di 2 € (in vigore dal 1° novembre 2026), oltre all’IVA polacca d’importazione. Consulta [pacchi fermi in dogana](guide:customs).

**Danni visibili e occulti.** Puoi rifiutare un pacco visibilmente danneggiato alla consegna così che il corriere lo registri e lo rimandi al mittente. Secondo le [Condizioni di Servizio di GLS Poland](https://gls-group.com/PL/pl/regulamin/), eventuali danni occulti scoperti dopo l’accettazione vanno segnalati entro **7 giorni** (per iscritto alla sede GLS o tramite il [modulo reclami consumatori](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Le finestre generali di reclamo per consumatori sono 30 giorni dall’accettazione da parte di GLS (45 giorni per smarrimento, entro 12 mesi dalla spedizione), con risoluzione da parte di GLS entro 30 giorni. Consulta [pacco danneggiato](guide:damaged-parcel) e [pacco smarrito](guide:lost-parcel).

**Tracciamento fermo o consegnato ma assente.** Se il tracciamento resta fermo per giorni, consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating). Se il pacco risulta consegnato ma non lo hai, verifica se attende in un GLS Point, chiedi ai vicini e avvisa il negozio; consulta [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti GLS Poland e avviso anti-truffa

Contatta prima il negozio online per eventuali rimborsi. Per contattare direttamente GLS Poland (dettagli su [GLS Contact](https://gls-group.com/PL/en/gls-contact/) e [Corporate Information](https://gls-group.com/PL/en/corporate-information/)):

- **Hotline clienti:** `+48 46 814 82 20` (lunedì–venerdì 07:00–19:00, sabato 07:00–15:00).
- **E-mail:** `gls@gls-poland.com` (sede aziendale `office@gls-poland.com`).
- **Moduli online:** [modulo contatti GLS Poland](https://gls-group.com/PL/en/gls-contact/) e [modulo reclami consumatori](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Hotline tecnica locker 24/7:** `801 167 536` / `502 167 536`.
- **Sede legale:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Polonia.

> **Avviso truffe sui [consigli di sicurezza GLS Poland](https://gls-group.com/PL/en/security-advices/):** GLS non invia mai link via e-mail per pagare merce con carta o bonifico (CashService/ExWorksService si paga direttamente al corriere in contanti, carta o BLIK alla consegna) e non invia messaggi operativi da webmail gratuite come Gmail o Outlook. Inoltra messaggi sospetti a `security@gls-poland.com` (o `dataprotection@gls-poland.com`) e segnala i link di phishing polacchi a CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` o SMS `8080`). Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco GLS Poland con Peek

Incolla il codice in [Peek](/). I numeri a 11 e 12 cifre sono condivisi tra più corrieri, perciò Peek interroga i più probabili basandosi sul tuo paese (la regione del telefono o, sul web, da dove ti colleghi). In Polonia interroga prima GLS Poland, davanti a [GLS Francia](carrier:gls-fr), [GLS Germania](carrier:gls-de), [GLS Svizzera](carrier:gls-ch) e altri come [PPL](carrier:ppl) per 11 cifre o [BRT](carrier:brt) e [Colis Privé](carrier:colis-prive) per 12, e di solito mostra «GLS Poland ha questo pacco.». Altrove GLS Poland viene interrogata solo se gli altri corrieri probabili non trovano il codice. Un link incollato `gls-group.com` o `gls-group.eu` con `/PL/` nel percorso e il numero in `match=`, `parcelNumber=` o `matchParcelNumber=` viene tracciato direttamente come GLS Poland. Altrimenti tocca «Cambia» mentre aggiungi il pacco o «Cambia corriere» su uno già aggiunto e scegli GLS Poland.

Il campo «Codice postale di consegna» riporta «Facoltativo», ma GLS mostra le scansioni solo con esso, come spiega la riga sotto il campo: «GLS Poland mostra le scansioni solo con il codice postale di consegna.». Senza di esso Peek riceve lo stato GLS senza scansioni: intanto interroga altri servizi di tracciamento e la pagina del pacco propone «Aggiungi codice postale». Con un account Peek prova una volta il codice postale che hai inserito per l’ultimo pacco GLS Poland o, in mancanza, quello del tuo ultimo pacco. Se GLS non trova il pacco con quel codice postale, Peek ti avvisa e offre «Modifica codice postale». Finché GLS attende il codice postale o non trova il pacco con il tuo e nulla risponde per un giorno, Peek controlla una volta al giorno; aggiungerlo o modificarlo avvia subito un controllo. Il codice postale viene inviato a GLS e, se necessario, ai servizi di tracciamento, e non compare mai sui link condivisi.

Con il codice postale vedi lo stato, il luogo di ogni scansione sulla mappa, il giorno previsto, il peso, l’ora di consegna e, in un GLS Point, nome e indirizzo del punto. Il tuo nome, indirizzo, contatti, firma e riferimento del mittente vengono eliminati. Peek legge il servizio inglese di GLS anziché la pagina polacca: lì `W doręczeniu` diventa `In delivery` e `Paczka została doręczona` diventa `The parcel has been delivered.`. L’arrivo in un GLS Point o locker appare come «Pronto per il ritiro». Gli orari sono letti in ora polacca.

Peek controlla GLS circa una volta l’ora, giorno di consegna incluso, e aggiornare a mano non controlla prima (4 ore dopo un controllo fallito); ogni 6 ore dopo sette giorni senza novità su «Etichetta creata». Solo tramite servizi di tracciamento controlla ogni ora, e ogni 3 ore dopo una giornata senza novità.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a GLS Poland.», con il link «Chiedi aiuto a GLS Poland» alla pagina contatti di GLS Poland. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a GLS Poland.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti e non può reindirizzare pacchi né presentare reclami a GLS Poland al posto tuo.

## Domande sul tracciamento GLS Poland

### Per quanto tempo GLS Poland tiene un pacco in un GLS Point o locker ORLEN?

Secondo le attuali [Condizioni di Servizio](https://gls-group.com/PL/pl/regulamin/) e le [FAQ](https://gls-group.com/PL/en/faq/) di GLS Poland, i GLS Points custodiscono i pacchi 7 giorni solari (le pagine ricezione e GLS Points dicono ancora sette giorni lavorativi), mentre i locker 24/7 ORLEN Paczka li tengono 72 ore prima del reso automatico al mittente.

### Perché GLS Poland chiede il codice postale per il tracciamento completo?

GLS tutela i dettagli della spedizione restituendo cronologia completa delle scansioni, opzioni di consegna e dettagli del ParcelShop solo quando il numero del pacco combacia con il codice postale del destinatario. Inserisci il codice postale di consegna in Peek per avere il feed diretto GLS.

### Posso pagare un pacco GLS Poland CashService con carta o BLIK?

Sì. GLS Poland indica che le consegne CashService (fino a 6.000 PLN al giorno) si possono pagare al corriere alla consegna in contanti, carta di pagamento o BLIK — mai attraverso un link ricevuto in un’e-mail indesiderata.

### Come reindirizzo un pacco GLS Poland a un negozio Żabka o a un locker?

Apri il link FlexDeliveryService nell’e-mail di notifica GLS (senza account) oppure usa [parcel redirection](https://gls-group.com/PL/en/parcel-redirection/) per spostare il pacco verso un GLS Point (inclusi i negozi Żabka), un locker ORLEN Paczka, un altro indirizzo o una data successiva.

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – numero pacco, Track-ID e ricerca con codice postale
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – hub centrale Stryków, filiali e panoramica della rete
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – ragione sociale, sede di Głuchowo e `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – consegne feriali, consegna ai vicini, secondo tentativo, giacenza in deposito e CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – opzioni FlexDeliveryService senza account
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – oltre 18.000 GLS Points (tra cui 11.000 negozi Żabka), oltre 26.000 punti fuori casa, limiti di peso/misure e tempi UE
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ~8.000 locker condivisi 24/7 ORLEN Paczka, ritiro entro 72 ore, PIN/QR/app myGLS, limite 20 kg e hotline locker
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – mappa e orario previsto entro 15 minuti
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – 7 giorni solari nei GLS Point, CashService contanti/carta/BLIK, risposte sulle mancate consegne
- [GLS Poland: Condizioni di Servizio (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – condizioni, quelle per GLS Points e locker in vigore dal 9 novembre 2026: ritiro in 7 giorni solari, denuncia danni occulti entro 7 giorni, finestre reclamo 30/45 giorni e risposta entro 30 giorni
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – hotline `+48 46 814 82 20` (lun–ven 07:00–19:00, sab 07:00–15:00), `gls@gls-poland.com` e modulo contatti
- [GLS Poland: Reclami Consumatori](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – invio reclamo consumatori online
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – avvisi phishing, `security@gls-poland.com` e segnalazioni CERT NASK
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – domini ufficiali del gruppo e regole CashService
- [Commissione Europea: Riforma Doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio da 3 € fino a 150 € (dal 1° luglio 2026) e commissione UE da 2 € (dal 1° novembre 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – rilevamento 11/12 cifre, priorità Polonia, codice postale facoltativo, mappatura stati, cadenza oraria/4 ore e URL di aiuto
:::
