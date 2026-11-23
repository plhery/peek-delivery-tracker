---
title: Tracciamento Liccardi Express Courier: LDV, stati e aiuto
description: Traccia una spedizione Liccardi Express Courier (F.lli Liccardi): codici 660 e LDV, stati di consegna, locker e PUDO, giacenza, reclami e numero verde.
slug: tracciamento-liccardi
published: 2026-11-23
updated: 2026-11-23
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) è un corriere espresso nazionale italiano con sede centrale e hub di smistamento a Casandrino, in provincia di Napoli ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Nata nella forma moderna nel 1992 dalla ditta di trasporti fondata trent'anni prima da Giovanni Liccardi, l'azienda è cresciuta dal trasporto di documenti bancari negli anni '90 alla logistica B2C ed e-commerce su tutto il territorio nazionale dal 2004, gestendo oltre 5,9 milioni di spedizioni all'anno attraverso più di 20 filiali e hub tra cui Napoli, Roma, Milano, Bologna, Torino, Firenze, Verona, Bari e Salerno. Puoi verificare la spedizione dal sito ufficiale o incollare il codice etichetta `660` o la lettera di vettura (LDV) nel riquadro qui sopra.

## Codici di tracciamento Liccardi Express Courier (`660` e LDV)

Liccardi identifica ogni spedizione con due riferimenti numerici — il codice etichetta collo a 13 cifre che inizia con `66` (di norma `660`) e il numero di spedizione o lettera di vettura (*LDV*) di 7 o 8 cifre:

| Formato (inventato) | Che cos'è |
| --- | --- |
| `6604829173605` | Codice etichetta collo Liccardi a 13 cifre (`660` + numero spedizione a 8 cifre + 2 cifre). |
| `48291736` | Numero di spedizione / lettera di vettura (*LDV*) Liccardi di 7 o 8 cifre. |

Trovi uno dei due codici nell'e-mail di spedizione del venditore o nell'avviso di consegna Liccardi: entrambi puntano alla stessa spedizione nel sistema del corriere. Consulta i [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di Liccardi Express Courier

Il sistema di tracciamento Liccardi pubblica codici esito numerici affiancati dalla descrizione della filiale in italiano. Nelle spedizioni multicollo (*colli*), ogni collo ha le proprie scansioni; Peek mantiene aperta la spedizione finché non vengono consegnati tutti i colli:

| Cosa dice Liccardi (codice) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | Il mittente ha trasmesso i dati della spedizione o il ritiro è stato assegnato a un autista prima della presa in carico fisica. | «Etichetta creata» |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Spedizione ritirata dal mittente e presa in carico nella rete Liccardi. | «Preso in carico» |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | In transito tra hub, arrivata alla filiale di destino, in prenotazione appuntamento o svincolata dalla giacenza. | «In transito» |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Caricata sul furgone di consegna locale e in consegna nella giornata. | «In consegna» |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Disponibile per il ritiro presso un punto PUDO, un locker o la filiale Liccardi (*fermo deposito*). | «Pronto per il ritiro» |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | Consegna non riuscita per assenza del destinatario, errore di indirizzo, contrassegno non pronto, chiusura o giro non completato. | «Tentativo di consegna» |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Spedizione in giacenza in attesa di istruzioni del mittente, danneggiata, respinta, disguidata, smarrita o da verificare con l'assistenza. | «Problema segnalato» |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | In rientro o già riconsegnata al mittente. | «Restituito al mittente» |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Consegnata al destinatario o ritirata dal locker. | «Consegnato» |

Tieni presente che la barra di avanzamento sul sito Liccardi considera consegnato il deposito nel locker (`11`), mentre Peek segnala correttamente PUDO, locker e *fermo deposito* come **«Pronto per il ritiro»** finché il destinatario non effettua il ritiro effettivo (`12`).

## Tempi di consegna, servizi e rete di filiali in Italia

Secondo le pagine ufficiali [Servizi](https://www.liccarditrasporti.com/servizi/), [Azienda](https://www.liccarditrasporti.com/azienda/) e [Filiali](https://www.liccarditrasporti.com/filiali/):

- **Tempi di consegna:** il ritiro presso il mittente avviene entro 48 ore; i colli vengono pesati, misurati e fotografati nell'impianto di smistamento e Liccardi garantisce la consegna nazionale entro **24/48 ore** (coprendo statisticamente l'intera gamma di spedizioni entro **72 ore**, salvo tempi aggiuntivi per *località disagiate* e picchi stagionali).
- **Servizi disponibili:** espresso nazionale B2C, consegne al piano, consegne tassative, consegne in giornata, contrassegno, fermo deposito, prova di consegna, ritiro indiretto, servizio Swap e assicurazione integrativa.
- **Rete operativa:** oltre 20 filiali e hub in Italia, tra cui sede e hub Napoli/Casandrino (`NA1`), Roma (`RM1`, `RM3`), Milano (`MI1`), Bologna (`BO1`), Torino (`TO2`), Firenze (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Modena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) e Reggio Calabria (`RC1`). Quando un altro corriere prende in carico il pacco, il tracciamento Liccardi può mostrare la lettera di vettura di quel corriere.

## Mancata consegna, giacenza e reclami

- **`IN GIACENZA` (`35`) o tentativo fallito (`28`, `40`, `45`):** dopo un esito negativo o un indirizzo errato, la spedizione entra in giacenza in attesa delle istruzioni di svincolo del **mittente**. Avvisa subito il venditore affinché invii lo svincolo, oppure usa la sezione [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) per aggiornare le informazioni di consegna, segnalare un errore di indirizzo o richiedere il ritiro in filiale.
- **Contestazione esito e moduli reclamo:** nella pagina destinatari puoi contestare un esito errato (ad esempio assenza segnalata mentre eri in casa, rifiuto mai espresso o pacco risultante consegnato ma non ricevuto) e scaricare i moduli ufficiali (*Modulo reclamo*, *Modulo conciliazione*, *Formulario CP*). Informa sempre anche il negozio da cui hai acquistato. Consulta [pacco consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel), [pacco restituito al mittente](guide:returned-to-sender) e [perché il tracciamento si blocca](guide:tracking-not-updating).

## Assistenza clienti e contatti Liccardi Express Courier

Recapiti ufficiali pubblicati su [Contatti](https://www.liccarditrasporti.com/contatti/) e [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Numero Verde (Italia):** `800 029 764` (`800029764`).
- **Telefono:** `+39 081 5053642` (fax `081 5053071`).
- **E-mail assistenza clienti:** `assistenzaclienti@liccarditrasporti.com` (informazioni generali: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Portale assistenza e reclami destinatari:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Sede centrale e hub:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (sede legale 123), 80025 Casandrino (NA).

> Diffida di SMS o e-mail sospetti che chiedono un piccolo pagamento online per sbloccare una consegna. Verifica sempre eventuali comunicazioni con il venditore o attraverso i canali ufficiali di Liccardi. Consulta [truffe via SMS di consegna](guide:delivery-scam-texts).

## Traccia un pacco Liccardi Express Courier con Peek

Quando inserisci un codice etichetta a 13 cifre che inizia con `660` (`6604829173605`, inventato), [Peek](/) chiede prima a Liccardi Express Courier, ovunque ti trovi tranne in Russia, dove chiede prima a Ozon, e mostra «Liccardi Express Courier ha questo pacco.» quando il tracciamento Liccardi lo conferma. Peek riconosce anche un link `my.liccarditrasporti.com/segui?ldv=` incollato come Liccardi. Una LDV di 7 o 8 cifre (`48291736`, inventata) da sola non indica nessun corriere: incolla il link, oppure tocca «Cambia» durante l'aggiunta o «Cambia corriere» nella pagina del pacco e scegli **Liccardi Express Courier** (puoi cercarlo anche come «Liccardi Trasporti», «Liccardi Express» o «F.lli Liccardi»).

Peek legge il tracciamento di Liccardi e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), la descrizione in italiano di Liccardi e la filiale. I codici esito (`codEsito`) determinano la fase; nessuna app Peek li mostra. Peek data le scansioni sull'ora italiana e le mostra con l'orologio del tuo dispositivo. Mostra anche la data prevista di consegna quando Liccardi la indica (senza la fascia oraria), l'orario di consegna a pacco consegnato e lo stato riepilogativo prima della prima scansione del collo. Quando Liccardi indica la lettera di vettura di un altro corriere e Peek riconosce quel corriere, Peek verifica che abbia il pacco: il pacco mostra allora «Consegna con» e il nome del corriere, ed elenca entrambi i numeri. Nelle spedizioni multicollo Peek unisce le scansioni ripetute della stessa filiale entro un minuto e mantiene aperta la spedizione finché ogni collo non risulta consegnato. Peek non mostra comune o CAP del destinatario, nomi degli autisti né prove di consegna firmate.

Finché hai il pacco aperto in Peek, o nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 ora italiana nel giorno in cui Liccardi indica `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) o `IN CONSEGNA` (`52`), finché il tracciamento di Liccardi risponde. Altrimenti controlla ogni ora. Dopo 12 ore solo su «Etichetta creata» o «Preso in carico», o due giorni senza novità, controlla ogni ora comunque tu segua il pacco, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di Liccardi non risponde e Peek segue il pacco solo tramite i servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna.

Quando la scansione più recente ha quattro giorni e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco dice allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Liccardi Express Courier.», con il link «Chiedi aiuto a Liccardi Express Courier» alla pagina Assistenza / Reclami Destinatari. Dopo tre settimane senza nuove scansioni dice «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Liccardi Express Courier.»

Puoi scegliere i profili di avviso tra «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può svincolare una giacenza, modificare l'indirizzo di consegna né contattare Liccardi Express Courier al posto tuo.

## Domande frequenti sul tracciamento Liccardi Express Courier

### Com'è fatto un numero di tracciamento Liccardi?

Liccardi usa un codice etichetta a 13 cifre che inizia con `66` (di norma `660`, ad esempio `6604829173605`, inventato) oppure il numero di spedizione / lettera di vettura (*LDV*) di 7 o 8 cifre (ad esempio `48291736`, inventato).

### Cosa significa `IN GIACENZA` su Liccardi Express Courier?

`IN GIACENZA` (codice `35`) indica che il pacco è fermo in filiale dopo un tentativo non riuscito o un problema di indirizzo e attende lo svincolo da parte del mittente. Contatta il venditore e invia eventuali dettagli utili tramite [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### In quanto tempo consegna Liccardi Express Courier?

Liccardi garantisce la consegna sul territorio nazionale entro **24/48 ore** dal ritiro (coprendo statisticamente l'intera rete entro **72 ore**), con tempi maggiori possibili per località disagiate e picchi stagionali.

### Come contattare l'assistenza Liccardi Express Courier?

Puoi chiamare il Numero Verde `800 029 764` o lo `+39 081 5053642`, scrivere a `assistenzaclienti@liccarditrasporti.com` oppure aprire una segnalazione su [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: Sito Ufficiale](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, oltre 20 filiali, oltre 5,9 milioni di spedizioni annue, sede di Casandrino (NA) e contatti
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – storia dal 1992 (origini anni '60), sviluppo B2C dal 2004 e associazione Unione Industriali
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – consegna 24/48 h (fino a 72 h), ritiro 48 h, contrassegno, fermo deposito e tracciatura fotografica
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – elenco completo delle oltre 20 filiali e hub italiani (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1, ecc.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – Numero Verde `800 029 764`, `+39 081 5053642`, e-mail, PEC e sede di Casandrino
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – guida reclami destinatari, contestazione esito e moduli conciliazione/CP (URL di aiuto aperto da Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – formati `660`/LDV, regola link `my.liccarditrasporti.com`, mappa `codEsito`, gestione multicollo, data prevista e LDV corriere partner
:::
