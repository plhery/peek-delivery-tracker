---
title: Tracciamento GEODIS Polonia: numero PL, stati e consegna
description: Traccia una spedizione GEODIS Polonia (ex Pekaes) con codice PL e 16 cifre: significato degli stati, consegna, ritiro al terminal, reclami e contatti.
slug: geodis-polonia-tracciamento
published: 2026-11-18
updated: 2026-11-18
---

GEODIS Poland è la rete polacca di trasporto pallet e pacchi del gruppo francese GEODIS, controllato dalle ferrovie pubbliche francesi SNCF. GEODIS ha acquisito PEKAES nel 2021 e oggi la rete è gestita da GEODIS Road Network sp. z o.o., con sede a Błonie in Polonia. Come procedere dipende da cosa ti ha comunicato il mittente:

- **Un codice composto da `PL` e 16 cifre, oppure un link a Strefa Klienta:** è una spedizione GEODIS Poland (pacco o pallet). Incolla il numero o il link nel riquadro qui sopra, oppure cercalo direttamente sul portale di tracciamento di GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Un altro numero GEODIS:** Peek legge anche il tracciamento francese di [GEODIS](carrier:geodis), i cui codici iniziano con `1G` (vedi sotto). La ricerca su `geodis.com` individua le spedizioni aeree, marittime e dell'ultimo miglio, mentre per il trasporto su strada rimanda a Strefa Klienta.
- **Nessun numero ancora:** lo fornisce il negozio online o il mittente, di solito nell'e-mail di conferma d'ordine o nella notifica di spedizione.

## Numero di tracciamento GEODIS Polonia: formato e dove trovarlo

Le soluzioni GEODIS sono destinate esclusivamente ai professionisti e alle aziende: il numero di spedizione si trova quindi presso l'impresa o l'e-commerce che ti invia la merce. Se non compare nelle tue e-mail, chiedilo al venditore o consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

| Numero (inventato) | Che cos'è |
| --- | --- |
| `PL4829105736491827` | `PL` seguito da 16 cifre: un numero di spedizione GEODIS Poland, chiamato `Shipment No.` su Strefa Klienta. |
| `1G4829105736` | `1G` seguito da 10 lettere maiuscole o cifre: un codice di [GEODIS](carrier:geodis) in Francia, tracciabile nello spazio destinatari francese e non su Strefa Klienta. |

Nella versione inglese di Strefa Klienta inserisci il codice nel campo `Enter Shipment No.` e premi `Search` (nella versione polacca: `Wpisz numer przesyłki` e `Szukaj`). Se il portale risponde `Shipment with such number does not exist`, non conosce quel numero: contatta il mittente o il negozio online, come raccomanda GEODIS quando un codice non funziona. Se compare il messaggio in polacco `Proszę wprowadzić właściwy numer przesyłki.`, il modulo chiede un numero di spedizione valido: controlla che non ci siano refusi.

## Cosa significano gli stati di tracciamento di GEODIS Polonia

Strefa Klienta è disponibile in polacco e in inglese. Quando trova la spedizione, la pagina inglese mostra prima un riepilogo (`Shipment No.`, `Status`, `Delivery Country`, `Delivery Location` con CAP e località) e il link `Details` apre la cronologia delle scansioni con data, ora e località del terminal. Ecco le diciture della pagina inglese nell'ordine tipico del trasporto:

| Stato GEODIS | Cosa significa per te |
| --- | --- |
| `Received from the sender` | GEODIS ha ritirato la spedizione presso il mittente (`Collected (SMS)` conferma il ritiro via SMS; `Failed collection (SMS)` segnala un ritiro non riuscito). |
| `Inbound Scan From Driver to Terminal` | Un autista ha portato la spedizione in un terminal: quello di partenza dopo il ritiro oppure, se preceduto da `Outbound for Delivery`, quello di consegna a fine giro. Se prima non compare né `Consignee Absent` né `Failed delivery (SMS)`, in quel giro non è stato effettuato il tentativo e la spedizione attende il successivo. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Ha lasciato il terminal di partenza su un mezzo di linea diretto al terminal che serve il tuo indirizzo. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | È arrivata al terminal incaricato della consegna finale. |
| `Awaiting Delivery` | È in attesa nel terminal di essere inserita in un giro di distribuzione. |
| `Outbound for Delivery` | È a bordo del mezzo in uscita per la consegna. |
| `Consignee Absent` | L'autista non ti ha trovato all'indirizzo. |
| `Failed delivery (SMS)` | Tentativo di consegna fallito, confermato via SMS. |
| `Awaiting designation of new delivery date` | GEODIS attende la definizione di una nuova data di consegna. |
| `Awaiting Decision` | GEODIS attende istruzioni dal committente o dal mittente. |
| `Delivery Refusal` | Il destinatario ha rifiutato la consegna. |
| `Request for Return` | GEODIS ha ricevuto disposizione di reso: la merce sta rientrando al mittente, ma non è ancora stata riconsegnata. |
| `Delivered` | Consegnato (`Delivered (SMS)` indica la stessa cosa, confermata via SMS). |
| `Claim Received` | È stato registrato un reclamo: è una nota amministrativa e non sposta la spedizione. |

Alcune voci segnalano un fermo o un imprevisto: `Stopped at Hub` e `Stopped at Collection Terminal` (spedizione trattenuta in un hub di smistamento o nel terminal di partenza), `Not Dispatched` (ha perso la linea o il giro previsto ma resta nella rete) e `Failure` (guasto o imprevisto). Una riga che inizia con `Pseudoscan` (`Pseudoscan – delete all previous 100, 400, 500 scans`) è una registrazione contabile che annulla le precedenti scansioni di consegna, reso e reclamo. Maggiori dettagli generali in [stati di tracciamento](guide:tracking-statuses).

## Consegna GEODIS in Polonia: indirizzo privato, preavviso e ritiro al terminal

Le Condizioni generali di prestazione dei servizi (*Ogólny Regulamin Świadczenia Usług*) di GEODIS Road Network, in vigore dal 4 novembre 2024, regolano il rapporto con il committente (*Zleceniodawca*, l'azienda che ordina il trasporto, di norma il negozio) per i servizi nazionali in **Polonia** e per il trasporto stradale internazionale:

- **Destinatario assente.** Nei servizi nazionali in **Polonia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`, `PTL DOM`), in caso di assenza l'incaricato lascia un avviso del tentativo; dopo un secondo tentativo fallito per cause imputabili al destinatario, la spedizione torna al mittente a spese del committente (così come in caso di rifiuto o indirizzo errato). Nei servizi stradali internazionali (`PAC INT`, `LTL INT`, `PTL INT`), se il destinatario è assente, committente o mittente hanno fino a 2 giorni lavorativi dal tentativo fallito per fornire istruzioni prima che GEODIS restituisca la spedizione a carico del committente. Vedi [consegna mancata](guide:missed-delivery) e [rispedito al mittente](guide:returned-to-sender).
- **Pallet a un indirizzo privato in Polonia (`DDOP`).** Nel groupage nazionale in **Polonia** (`LTL DOM` e `LTL DOM NST`), la consegna a indirizzi privati avviene solo nei giorni lavorativi, dal lunedì al venerdì tra le `8:00` e le `16:00`. Il committente deve indicare il tuo numero di cellulare e contrassegnare la spedizione con `DDOP`: GEODIS invia aggiornamenti sullo stato e concorda la consegna in anticipo per telefono, SMS o e-mail, telefonando al più tardi nel giorno stesso della possibile consegna. Se entro 2 giorni lavorativi non riesce a contattarti per fissare l'appuntamento, avvisa il committente e restituisce la merce al mittente.
- **Scarico e servizi aggiuntivi nazionali in Polonia.** Con il servizio `DDOP` in **Polonia**, l'autista scarica il pallet a livello strada presso il luogo di consegna, oppure nel punto pavimentato e sicuro più vicino se le condizioni stradali non consentono una sosta sicura davanti all'indirizzo. La consegna al piano (*Usługa Wniesienia*: nazionale su `LTL DOM` / `LTL DOM NST`, fino a 300 kg lordi, massimo 30 kg per collo e 30 colli, entro 150 m su percorso pavimentato e fino al secondo livello in assenza di ascensore utilizzabile, con possibile estensione dei tempi fino a 3 giorni lavorativi), il trasporto con transpallet (*Usługa Wwiezienia*, fino a 1.200 kg lordi e 300 kg per pallet entro 150 m fino alla prima barriera architettonica), il giorno fisso (*Fix Day*) e la consegna entro le `10:00` (`D10`) o le `12:00` (`D12`), entrambe solo su specifici CAP polacchi, sono servizi nazionali separati che il committente deve richiedere.
- **Ritiro al terminal in Polonia.** Quando il committente dispone il ritiro diretto al terminal GEODIS da parte di un destinatario privato, la spedizione resta in giacenza per 48 ore (calcolate in giorni lavorativi) dalla disposizione, con ritiro dal lunedì al venerdì dalle `11:00` alle `16:00`; trascorso il termine, rientra al mittente.
- **Contrassegno in Polonia (*Usługa pobrania*).** Previsto solo sui servizi nazionali in **Polonia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`), consente di pagare prima del ritiro fino a `10.000 PLN` (o l'equivalente in altra valuta) per spedizione in contanti oppure tramite carta di pagamento, BLIK o altro metodo elettronico accettato da GEODIS.
- **Firma e controllo.** Confermi la ricezione firmando sul dispositivo mobile dell'autista o sui documenti di trasporto. Il regolamento GEODIS stabilisce che il destinatario non può verificare il contenuto della spedizione prima di confermarne il ricevimento: controlla quindi con cura l'imballaggio esterno e annota eventuali riserve sui documenti di trasporto al momento della firma.

## Spedizione GEODIS Polonia in ritardo, danneggiata o non consegnata

**Tracciamento fermo.** Se per giorni non compaiono nuove scansioni, consulta [tracciamento che non si aggiorna](guide:tracking-not-updating) e contatta il negozio.

**Ritardo secondo il diritto polacco dei consumatori.** In **Polonia**, l'autorità garante dei consumatori (UOKiK, richiamando gli artt. 546 e 546¹ del Codice civile polacco) ricorda che nelle vendite ai consumatori regolate dalla legge polacca il venditore deve consegnare il bene tempestivamente e comunque entro 30 giorni dalla conclusione del contratto, salvo diverso accordo. Superato tale termine, l'acquirente può assegnare un termine supplementare al venditore e, se la consegna ancora non avviene, recedere dal contratto.

**Merce danneggiata o mancante.** In presenza di danni esterni visibili o ammanchi parziali alla consegna, redigi subito un verbale di danno (*protokół szkodowy*) insieme all'autista sul modello in vigore presso GEODIS, con firma leggibile di entrambi. Le riserve per danni o ammanchi non visibili alla consegna vanno presentate a GEODIS entro 7 giorni dal ricevimento; in ogni caso spetta a chi reclama dimostrare che il danno o la perdita parziale è avvenuto prima della consegna. Conserva la merce a disposizione di GEODIS fino alla chiusura della pratica e scatta fotografie con data e ora. Il modulo di reclamo GEODIS precisa che la sottoscrizione del verbale di danno costituisce presupposto istruttorio ma non equivale alla presentazione del reclamo. Vedi anche [pacco danneggiato](guide:damaged-parcel).

**Chi presenta il reclamo.** In base al regolamento di GEODIS Road Network, i reclami vanno inviati per iscritto all'ufficio reclami (*Dział Reklamacji*) presso la sede centrale di Błonie dal committente o da un soggetto munito di delega scritta o atto di cessione dei diritti del committente, tempestivamente ed entro 14 giorni dal ricevimento (danno o ammanco) oppure entro 21 giorni dalla data prevista di consegna (ritardo o perdita totale). Per gli acquisti dei consumatori soggetti al diritto polacco, l'UOKiK (art. 548 § 3 del Codice civile polacco) chiarisce che il venditore risponde del rischio di perdita o danneggiamento fortuito fino alla consegna all'acquirente quando il metodo di spedizione rientrava tra quelli proposti dal venditore, anche se il danno è stato causato dal corriere: come acquirente reclami quindi presso il venditore, che agisce poi verso il vettore.

## Contatti e assistenza clienti GEODIS Polonia

- **Servizio clienti in Polonia (*Dział Obsługi Klienta*):** `+48 22 460 26 26`, indicato sulla pagina polacca di Strefa Klienta (senza orari pubblicati).
- **Reclami per posta:** `Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie`, Polonia. I moduli di reclamo (`GRN Formularz zgloszenia reklamacji`) e di verbale di danno (`GRN Protokol szkody`) sono scaricabili da Strefa Klienta.
- **Sedi in Polonia:** la [pagina di contatto polacca di GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) elenca 42 sedi in Polonia complete di indirizzo, comprese le filiali di GEODIS Road Network.
- **Modulo online su `geodis.com`:** riservato esclusivamente ai clienti aziendali; richiede ragione sociale (`Firma *`) e indirizzo e-mail aziendale (`Adres e-mail służbowy *`).

## Tracciare una spedizione GEODIS Polonia con Peek

La combinazione di `PL` e 16 cifre non è esclusiva di GEODIS (`PL` indica la Polonia), quindi Peek non può attribuirla a GEODIS Poland basandosi solo sulla forma del numero. Incollala comunque: Peek segue oltre 3.500 corrieri, interroga GEODIS Poland e mostra «GEODIS Poland ha questo pacco.» non appena il portale lo riconosce. Se GEODIS non lo elenca ancora, il pacco riporta «Probabilmente GEODIS Poland» e «In attesa della prima scansione di GEODIS Poland» mentre Peek continua a verificare; se dopo una settimana non risulta ancora, Peek suggerisce di controllare il codice con il mittente o di scegliere un altro corriere. Per impostare manualmente il corriere, tocca «Cambia» sulla riga del corriere e scegli «GEODIS Poland» sotto «Compatibili con questo numero», oppure cercalo (anche `Pekaes` e `GEODIS Road Network` lo trovano; «GEODIS» da solo indica GEODIS Francia). Su un pacco già aggiunto usa «Cambia corriere». Un link a Strefa Klienta contenente `ShNumber=` viene invece associato subito a GEODIS Poland.

Peek legge Strefa Klienta in inglese: il codice `100` (`Delivered`) compare tradotto («Consegnato»), mentre le altre descrizioni di scansione restano nella formulazione inglese di GEODIS (incluso `Delivered (SMS)`), insieme alla località del terminal, all'orario di consegna e al paese di destinazione. Le scansioni nei terminal polacchi vengono lette sul fuso di Varsavia (`Europe/Warsaw`); i terminal all'estero mantengono il proprio orologio locale. Peek converte gli orari noti nel fuso del tuo dispositivo; su `peektracker.com` e nell'app per iPhone la cronologia si chiude con «Orari nel tuo fuso orario:» seguito dal nome del fuso. Quando una riga `Pseudoscan` annulla scansioni precedenti, anche Peek le rimuove, mentre `Claim Received` lascia invariata la fase della spedizione. Il portale GEODIS non fornisce data di consegna stimata, peso, nome del servizio, nome del destinatario né prova di consegna; Peek omette inoltre CAP e località di consegna, numero di colli, codici interni dei terminal e note in cui può comparire il nome del firmatario.

Mentre tieni il pacco aperto sullo schermo, o nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui GEODIS scansiona la spedizione in consegna (`Outbound for Delivery`) — finché risponde il portale di GEODIS; negli altri momenti, così come dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Tramite servizi di tracciamento esterni, Peek verifica ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti nel giorno di consegna mentre tieni aperto il pacco o hai le notifiche attive. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può fissare appuntamenti, prenotare un ritiro al terminal né inviare reclami: chiama GEODIS o rivolgiti al venditore.

## Domande frequenti sul tracciamento GEODIS Polonia

### GEODIS Poland è la stessa azienda di Pekaes?

Sì. GEODIS ha acquisito PEKAES nel 2021 e oggi la rete polacca è gestita da GEODIS Road Network. Il portale di tracciamento risiede ancora all'indirizzo `strefaklienta.pekaes.geodis.pl`.

### GEODIS consegna il sabato in Polonia?

Le Condizioni generali di GEODIS Road Network non prevedono consegne il sabato: i pallet destinati a indirizzi privati in Polonia (`DDOP`) vengono consegnati nei giorni lavorativi dal lunedì al venerdì dalle `8:00` alle `16:00`, e nei pacchi nazionali (`PAC DOM`) sabati, domeniche e festività sono esclusi dal calcolo dei tempi di resa.

### L'autista GEODIS porta l'ordine al piano in Polonia?

Solo per le spedizioni nazionali di groupage in Polonia (`LTL DOM` o `LTL DOM NST`) per cui il committente abbia ordinato il servizio supplementare di consegna al piano (*Usługa Wniesienia*): fino a 300 kg lordi (massimo 30 kg per collo), entro 150 m dal punto di scarico e fino al secondo livello se manca un ascensore utilizzabile. In assenza del servizio, lo scarico avviene a bordo strada.

### Quanto impiega GEODIS a consegnare in Polonia e in Europa?

Nella pagina dedicata a pallet e pacchi, GEODIS indica tempi standard di 24–48 ore in Polonia (3–5 giorni in Europa) e consegna espressa il giorno successivo entro mezzogiorno in Polonia (1 o 2 giorni in Europa). Nelle Condizioni generali, GEODIS consegna di norma i pacchi nazionali `PAC DOM` fino a 30 kg entro 48 ore dal ritiro (esclusi fine settimana e festivi) e il groupage `LTL` entro 2 giorni lavorativi.

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking (in inglese)](https://strefaklienta.pekaes.geodis.pl/en/) – campo `Enter Shipment No.`, pulsante `Search` e moduli di reclamo e verbale di danno
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek (in polacco)](https://strefaklienta.pekaes.geodis.pl/pl/) – numero del servizio clienti `+48 22 460 26 26` e Condizioni generali
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, dal 4 novembre 2024 (in polacco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – tentativi nazionali e internazionali, opzione `DDOP`, scarico, consegna al piano, ritiro al terminal, contrassegno, firma, tempi di resa e reclami
- [GEODIS Road Network: Formularz zgłoszenia reklamacji (in polacco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – indirizzo postale reclami a Błonie e valore del verbale di danno
- [GEODIS: Śledź swoją przesyłkę (in polacco)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – dove trovare il codice, spedizioni aeree/marittime/ultimo miglio su `geodis.com`, rimando per il trasporto stradale e codice non funzionante
- [GEODIS: Twój obszar roboczy (in polacco)](https://geodis.com/pl-pl/twoj-obszar-roboczy) – accesso a Strefa Klienta come piattaforma di tracciamento
- [GEODIS: Transport palet i paczek (in polacco)](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – servizi riservati ai professionisti e tempi standard ed espressi in Polonia e in Europa
- [GEODIS: Skontaktuj się z nami (in polacco)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – modulo B2B ed elenco delle 42 sedi in Polonia
- [GEODIS: Historia (in polacco)](https://geodis.com/pl-pl/historia) – acquisizione di PEKAES nel 2021 e appartenenza a SNCF
- [UOKiK: Odpowiedzialność za przesyłkę (in polacco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – responsabilità del venditore fino alla consegna nel diritto polacco dei consumatori
- [UOKiK: Wydanie towaru (in polacco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – termine massimo di 30 giorni, termine supplementare e recesso dal contratto in Polonia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `PL` a 16 cifre, link Strefa Klienta, diciture delle scansioni, codici `1G` di GEODIS Francia e campi letti da Peek
:::
