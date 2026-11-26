---
title: Tracciamento Econt: numero di spedizione, stati e uffici
description: Traccia un pacco Econt in Bulgaria: dove trovare il codice a 13 cifre, significato degli stati inglesi e bulgari, uffici, Econtomat, reclami e contatti.
slug: tracciamento-econt
published: 2026-11-21
updated: 2026-11-21
---

Econt Express (*Econt Express AD*) è un corriere bulgaro fondato nel 1993 che consegna a domicilio, nei propri uffici Econt e negli armadietti automatici Econtomat. Puoi seguire una spedizione tramite `Track a shipment` su [econt.com](https://www.econt.com/en/services/track-shipment), con il link ricevuto via e-mail o SMS da Econt, oppure incollando il numero di spedizione nel riquadro qui sopra.

## Numero di tracciamento Econt: formato e dove trovarlo

Econt lo chiama numero di spedizione (*shipment number*) o numero di lettera di vettura (*waybill number*). Le notifiche via e-mail (gratuite) e via SMS (opzione a pagamento da `0,11 €` IVA inclusa attivata dal mittente, quindi non presente su ogni collo) contengono un link diretto al tracciamento insieme a luogo, tempistiche ed eventuale importo dovuto. Se non hai ricevuto messaggi da Econt, controlla l'e-mail di spedizione del negozio oppure leggi [dove altro trovare il numero di tracciamento](guide:find-tracking-number).

Econt non pubblica una regola formale sul formato, ma i numeri di lettera di vettura d'esempio nel suo manuale di integrazione XML hanno **13 cifre**, e il servizio di tracciamento Econt li legge come interi positivi a 13 cifre che non iniziano per `0`, come il codice inventato `1048291057364`.

Tredici cifre sono usate anche da altri corrieri — tra cui DPD France, Ukrposhta in Ucraina, FAN Courier in Romania o Delhivery in India — perciò verifica quale corriere è indicato dal venditore. Se scegli manualmente Econt per un codice che inizia con `0` o ha un'altra lunghezza, Peek segnala che «non emette numeri come questo».

## Cosa significano gli stati di tracciamento Econt

Il sito `econt.com` si apre in bulgaro (`Проследи пратка`) con selettore per l'inglese (`Track a shipment`). Ecco le diciture inglesi delle scansioni mostrate da Peek, nell'ordine tipico del trasporto:

| Stato Econt | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `Awaiting delivery to Econt` | Il mittente ha [creato l'etichetta](guide:tracking-statuses) ma non ha ancora affidato il collo a Econt. | «Etichetta creata» |
| `Submitted to courier` | All'inizio della cronologia: un corriere ha ritirato il pacco dal mittente («Preso in carico»). Dopo un passaggio in ufficio o su linea: è uscito per la consegna («In consegna»). | «Preso in carico» / «In consegna» |
| `Arrival at an office` | Arrivo nell'ufficio Econt o centro logistico indicato accanto (incluso l'ufficio di ritiro). | «In transito» (o «Pronto per il ritiro») |
| `Received by line-haul` | Viaggio su una tratta principale tra centri; il testo riporta la direttrice da città a città. | «In transito» |
| `Change delivery terms` | Modifica delle disposizioni di consegna (ad es. ufficio o data); l'oggetto segue `Change delivery terms:`, senza spostamento del pacco. | *(nota senza fase)* |
| `Shipment forwarding` | Rispedizione verso un'altra località o un nuovo indirizzo. | «In transito» |
| `Failed delivery` | Consegna non riuscita (motivo tra parentesi) e rientro nell'ufficio indicato. | «Tentativo di consegna» |
| `Delivered` | Consegnato o ritirato (seguito dal nome di chi ha ricevuto il pacco quando Econt lo registra). | «Consegnato» |
| `Delivery return` | Il pacco sta tornando al mittente. | «Restituito al mittente» |
| `Shipment destruction` | Spedizione avviata a distruzione; contatta subito il venditore o Econt. | «Problema segnalato» |
| `Information` | Nota informativa senza avanzamento di tappa. | *(nota senza fase)* |
| `Return receipt: Delivered` | Tracciamento della ricevuta di ritorno firmata diretta al mittente: riguarda la ricevuta, non il pacco. | *(ricevuta, senza fase)* |

Fai attenzione a `Submitted to courier`: prima di qualsiasi ufficio, tratta di linea, rispedizione o tentativo di consegna indica il ritiro presso il mittente; dopo, la consegna finale a destinazione.

Il tracker Econt fornisce anche uno **stato riepilogativo in bulgaro** con cui Peek fissa la fase attuale quando è univoca: `Очаква предаване към Еконт` e `Подготвена в eEcont` («Etichetta creata»), `Приета в Еконт` e `Приета в офис` («Preso in carico»), `Пътува по линия` («In transito»), `В офис` e `Пристигнала в офис` («Pronto per il ritiro» non appena Econt specifica l'ufficio che custodisce il pacco), `Доставена` («Consegnato»), `Върната` e `Върната и доставена към подател` («Restituito al mittente»), oltre a `Връща се към подател`, `Анулирана след изпращане` e `Анулирана преди изпращане` («Problema segnalato»). Quando il riepilogo è `В куриер` (in mano al corriere), decide l'ultima scansione.

## Consegna a domicilio, uffici Econt ed Econtomat

**A domicilio.** Nei centri abitati serviti da un ufficio Econt, i colli standard (fino a `50 kg` e `90 x 90 x 90 cm`, documenti A4 fino a `0,5 kg`, spedizioni non pallettizzate da `50` a `1.000 kg`) vengono consegnati il giorno lavorativo successivo — entro le `18:00` per spedizioni urbane — e entro **3 giorni lavorativi** secondo calendario nelle località senza ufficio. Il mittente può abbinare fasce orarie gratuite (`Delivery Time Slots`), orario fisso a pagamento (`1,88 €`–`6,50 €`) o consegna di sabato (`1,88 €`), IVA inclusa.

**In ufficio Econt.** Una spedizione per il giorno lavorativo successivo è pronta al mattino presto, all'apertura dell'ufficio. Dopo il **7° giorno** di giacenza Econt applica **`3,78 €` al giorno per spedizione** (IVA inclusa).

**Negli armadietti Econtomat (attivi 24/7).** Accettano pacchi fino a **`61 x 44 x 37 cm`** e **`30,5 kg`** (tre altezze di scomparto: `8 cm`, `18 cm` e `37 cm`), con consegna in **1–2 giorni lavorativi** in base all'orario di invio. Per il ritiro: salda in anticipo gli eventuali importi nell'app Econt con carta bancaria o portafoglio digitale `ecoints`, premi `Receive a shipment`, scansiona il codice QR o inserisci `PIN 1` e `PIN 2` ricevuti nell'app o via SMS, ritira il pacco e richiudi lo sportello.

**Modificare la consegna o controllare la merce.** Puoi cambiare in ogni momento destinatario, luogo (altro indirizzo, altro ufficio, consegna a un amico o vicino, inoltro in un'altra città) e orario per telefono, dal link nell'e-mail o dal profilo `e-Econt` ([cambiare indirizzo di consegna](guide:change-delivery-address)). Il listino prevede inoltre tre opzioni gratuite attivabili dal venditore: `Review` (ispezione), `Review and Test` (ispezione e prova) e `Review, Test and Select` (ispezione, prova e scelta).

## Pacco Econt in ritardo, danneggiato o smarrito

In Bulgaria Econt indica il giorno lavorativo successivo nelle località con ufficio Econt, fino a 3 giorni lavorativi dove non c'è ufficio e 1–2 giorni lavorativi da un Econtomat. Se il pacco è fermo da giorni, consulta la guida sul [tracciamento che non si aggiorna](guide:tracking-not-updating) e senti il negozio o Econt.

Per pacchi danneggiati, incompleti o persi ([pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel)), invia un reclamo tramite `I want to file a claim` nel centro clienti Econt e avvisa il venditore.

## Assistenza clienti e contatti Econt

Nella [pagina del centro clienti](https://www.econt.com/en/client-center) Econt riporta:

- **Da Bulgaria e Grecia:** `0 7001 7300` (al costo di una chiamata urbana o secondo il tuo piano tariffario).
- **Da tutti gli altri Paesi:** `+359 88 565 43 21` (secondo la tariffa del tuo operatore).
- **Online:** assistente virtuale `Ask Ena`, ricerca uffici e orari (`I want to find offices and working time`) e modulo reclami (`I want to file a claim`).

Se ricevi un SMS sospetto che chiede pagamenti fuori dall'app ufficiale o da `econt.com`, verificalo con la guida sugli [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Econt con Peek

Poiché 13 cifre sono condivise da più corrieri, [Peek](/) interroga Econt per primo, per il solo numero, soltanto quando lo inserisci sul sito dalla Bulgaria o nell'app su un telefono con regione Bulgaria. Dall'estero Peek interroga Econt per ultimo, quando né gli altri corrieri né i servizi di tracciamento conoscono il numero. Puoi anche scegliere Econt con «Cambia» — oppure incollare un link `econt.com` con `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` o `/tracking/?num=`, che seleziona subito Econt (finché Econt non ha ancora il pacco o se lo storico vecchio è stato rimosso, Peek mostra «In attesa di Econt» e, dopo due giorni, ti chiede di controllare il numero o di scegliere un altro corriere).

Peek interroga il servizio di tracciamento Econt senza account né CAP, mostra lo stato, colloca gli uffici sulla mappa, indica l'ufficio in cui il pacco ti aspetta quando è «Pronto per il ritiro» e conserva fino alle 100 scansioni più recenti. Per chi ha aggiunto la spedizione conserva il nome del destinatario e del firmatario dopo `Delivered:` (nascosti nei link condivisi), scartando numero di colli, nome del corriere, mittente e numeri d'ordine; Econt non fornisce data stimata, peso né foto di consegna.

Gli orari sono letti sul fuso di Sofia (`Europe/Sofia`, `UTC+2` d'inverno e `UTC+3` d'estate) e convertiti nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»). A pacco aperto o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle `06:00` ora di Sofia nel giorno in cui Econt scansiona il pacco «In consegna» finché il tracker Econt risponde (tramite servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti nel giorno di consegna a pacco aperto o con avvisi attivi); altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza scansioni, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può modificare le disposizioni presso Econt né aprire reclami per tuo conto.

## Domande frequenti sul tracciamento Econt

### Posso seguire una spedizione Econt senza conoscere il bulgaro?

Sì: Peek mostra le scansioni Econt con le diciture inglesi della tabella qui sopra e traduce lo stato generale in italiano. Anche `econt.com` ha una versione inglese sotto `Track a shipment`.

### Quanto impiega Econt a consegnare in Bulgaria?

Il giorno lavorativo successivo nei centri con ufficio Econt (entro le `18:00` a domicilio in città, dal mattino all'apertura in ufficio), entro 3 giorni lavorativi nelle località senza ufficio secondo il calendario di zona e 1–2 giorni lavorativi da un Econtomat.

### Come traccio un pacco Econt dall'estero con il numero a 13 cifre?

Incolla le 13 cifre nel campo qui sopra e seleziona Econt tramite «Cambia» (oppure incolla il link completo `econt.com/en/services/track-shipment/…`), perché fuori dalla Bulgaria Peek interroga Econt solo per ultimo per un numero a 13 cifre da solo.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – ricerca spedizioni in inglese su econt.com
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – pagina di tracciamento in bulgaro
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, fondata nel 1993 in Bulgaria
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bulgaria e Grecia), +359 88 565 43 21, Ask Ena, uffici e reclami
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – colli fino a 50 kg e 90 x 90 x 90 cm, tempi, fasce orarie, sabato, giacenza a 3,78 €/giorno dopo il 7° giorno, SMS a 0,11 € e opzioni gratuite Review / Test / Select
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – armadietti 24/7 fino a 61 x 44 x 37 cm e 30,5 kg, pagamento anticipato, codice QR e PIN 1 / PIN 2
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – notifiche e-mail (gratuite) o SMS con link di tracciamento
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – modulo per consegna al vicino, cambio ufficio, inoltro e orario
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – esempi di lettere di vettura a 13 cifre
- [Universal Parcel Scraper: Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – convalida intera a 13 cifre, riconoscimento in Bulgaria, stati inglesi e riepiloghi bulgari, fuso Europe/Sofia, firmatario e campi esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri corrieri a 13 cifre e servizi di tracciamento di riserva
:::
