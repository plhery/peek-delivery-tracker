---
title: Tracciamento Poczta Polska: numeri, stati e ritiri
description: Traccia un pacco Poczta Polska o Pocztex: formati dei codici, significato degli stati, avvisi, punti di ritiro, dogana e contatti.
slug: tracciamento-poczta-polska
published: 2026-11-22
updated: 2026-11-22
---

Poczta Polska è l'operatore postale statale della Polonia, e Pocztex è il suo servizio di corriere. Puoi tracciare un pacco o una raccomandata sulla pagina eMonitoring di Poczta Polska, disponibile anche in inglese, oppure incollare il codice nel campo qui sopra. Il codice è quello sulla ricevuta di spedizione del mittente, quindi il negozio ce l'ha, e gli SMS e avvisi di Poczta Polska lo ripetono.

## Codice di tracciamento Poczta Polska: dove trovarlo

Inseriscilo senza spazi né parentesi. Il tracciamento copre tutti gli invii spediti con prova di accettazione, tranne le lettere con valore dichiarato; le raccomandate mostrano solo le date di spedizione e di consegna.

| Formato (inventato) | Che cos'è |
| --- | --- |
| `PX4829105734` | `PX` e 10 cifre. |
| `0045900773482910573` | 19 cifre. |
| `00459007734829105736` | 20 cifre con `5900773` dopo le prime tre (oppure 18 cifre senza i due zeri `00` iniziali). |
| `RR482910576PL` | Formato postale internazionale: due lettere, nove cifre e `PL`. |

Dove compare:

- **Ricevuta di spedizione del mittente**: chiedi al negozio se nell'e-mail dell'ordine manca il numero.
- **Messaggio di ritiro** per un punto o armadietto Pocztex, con numero, luogo, scadenza e codice monouso.
- **SMS, e-mail o messaggio in app dopo una consegna mancata**, con numero, scadenza e indirizzo dell'ufficio postale.

Se eMonitoring risponde «The given package number is incorrect», controlla ogni carattere e quale corriere ha indicato il negozio. Nessun numero? Vedi [come trovare il codice di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di tracciamento di Poczta Polska

eMonitoring si apre in polacco; la versione inglese mostra queste diciture con data, ora e ufficio postale per ogni passaggio:

| Stato Poczta Polska | Cosa significa per te |
| --- | --- |
| `Electronic item data received` | Il mittente ha registrato l'invio. Poczta Polska non lo ha ancora ricevuto. |
| `Posting/collection`, `Item arrived at the shipping point` | Spedito presso un ufficio o punto, oppure ritirato dal mittente. |
| `In transport` | In viaggio tra centri di smistamento. |
| `Departure from Poland` | Ha lasciato la Polonia verso un altro paese. |
| `Arrival at inward office of exchange (Poland)` | Un pacco dall'estero ha raggiunto l'ufficio di scambio polacco. |
| `Customs service` | Operazioni doganali. |
| `Item returned from import customs` | Sdoganato all'importazione. |
| `In delivery` | In consegna, oppure un tentativo fallito (vedi sotto). |
| `Unsuccessful (physical) delivery` | Non c'era nessuno. È stato lasciato un avviso e l'invio va in ufficio postale o riparte. |
| `Attempted delivery - redirected` | Tentativo seguito dall'inoltro dell'invio. |
| `Delivery at sender's request - delivery attempt` | Tentativo di consegna richiesto dal mittente. |
| `Unsuccessful (physical) delivery - lost` | Smarrito durante la consegna. |
| `Ready for pick-up at the Post Office`, `Added to postal notice book - Item can be collected`, `Ready for pick up at the point` | Ti aspetta nell'ufficio o nel punto indicato nella scansione. |
| `Collected at the post office`, `Collected at the delivery point` | Ritirato in ufficio o al punto. |
| `Final delivery`, `Delivered to the PO box`, `Delivery at sender's request - delivered` | Consegnato. |
| `Returned`, `Returned to sender`, `Not collected at the delivery point` | Ritorna al mittente. |

`Posting/collection` è il passaggio del mittente, non il tuo ritiro. Poczta Polska usa `In delivery` anche per un evento di consegna fallita: Peek distingue i due casi dal codice evento e mostra «Tentativo di consegna» invece di «In consegna». Una riga `Customs service` può comparire dopo la consegna per registrare oneri incassati alla consegna. Senza «(Poland)», `Arrival at inward office of exchange` indica che un pacco partito dalla Polonia ha raggiunto il paese di destinazione. Sulla pagina polacca una consegna mancata compare come `Awizowana` e un pacco in attesa come `Oczekuje na odbiór`. Poczta Polska segnala che i nomi degli eventi possono cambiare.

## Consegna mancata Poczta Polska: avvisi, punti di ritiro e locker

**Pacchi corriere Pocztex.** Il corriere consegna il pacco a te, a un adulto del nucleo familiare o a una persona delegata. Se sei assente, vieni avvisato via SMS, e-mail o app se il mittente ha fornito i tuoi recapiti, altrimenti con un avviso in cassetta, seguito da un secondo messaggio dopo due giorni. Il pacco attende 7 giorni in ufficio postale dal giorno successivo all'avviso, poi torna al mittente. Puoi anche chiamare il corriere per farlo lasciare presso l'indirizzo (non per contrassegno, valore dichiarato, spese a carico del destinatario o «do rąk własnych»), a tuo rischio.

**Punti di ritiro e locker.** Presso un punto Pocztex (`PUNKT`) scelto dal mittente, il pacco aspetta 7 giorni in un ufficio postale o 3 giorni in un negozio convenzionato come Żabka, ABC o Lewiatan. I locker (`AUTOMAT`), situati presso uffici postali, stazioni di servizio e negozi come Biedronka, lo custodiscono 72 ore con promemoria dopo 24 e 48 ore. Si aprono con il numero di telefono e il codice oppure con l'app Pocztex Mobile. La scadenza non è prorogabile e il pacco non può essere spostato in un altro punto, ma chiunque abbia il codice può ritirarlo.

**Raccomandate e pacchi postali.** Un avviso (*awizo*) viene lasciato nella cassetta delle lettere o inviato elettronicamente su richiesta. Dopo 7 giorni arriva un secondo avviso e l'invio viene rispedito indietro se non ritirato entro 14 giorni dal primo. A pagamento puoi chiedere in ufficio o su poczta-polska.pl fino a 14 giorni in più di giacenza.

## Pacco Poczta Polska in ritardo, smarrito, danneggiato o in dogana

Il tracciamento di Poczta Polska ha solo valore informativo e non costituisce base per un reclamo; inoltre non pubblica indicazioni per tracciamenti bloccati: chiedi al mittente e leggi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Chi presenta reclamo.** Il mittente, oppure tu una volta consegnato l'invio o se il mittente ti ha ceduto il diritto: online su poczta-polska.pl (eReklamacje), per iscritto o di persona in qualsiasi ufficio postale.

**Termini** per spedizioni nazionali secondo le condizioni di Poczta Polska:

- Entro 12 mesi dalla spedizione.
- Danno visibile: alla consegna, con verbale. Puoi verificare il contenuto di un pacco visibilmente danneggiato, tranne presso punti e locker.
- Danno nascosto o contenuto mancante: entro 7 giorni dal ricevimento.
- Smarrimento: un pacco Pocztex è considerato smarrito 30 giorni dopo la spedizione; lo smarrimento di un pacco postale può essere segnalato 14 giorni dopo la spedizione, escluse domeniche e festività.
- Poczta Polska risponde entro 30 giorni; puoi fare ricorso entro 14 giorni.

Spedizioni internazionali: entro sei mesi dal giorno successivo alla spedizione.

**Dogana.** Dal 1º luglio 2026 gli invii extra-UE di valore fino a 150 € pagano un dazio UE di 3 € per categoria di merce, riscosso da Poczta Polska; i regali tra privati fino a 45 € sono esenti. Dal 1º novembre 2026 si aggiunge una commissione UE di gestione di 2 € per articolo (più prodotti identici possono contare come uno) sugli ordini online spediti da fuori UE: Poczta Polska la addebita al destinatario e la versa all'amministrazione fiscale polacca (KAS). Possono aggiungersi l'IVA non pagata e la tariffa di dichiarazione doganale di Poczta Polska (20 PLN, oppure 25 PLN se gestisci la dogana in autonomia), pagabili alla consegna o prima del ritiro. Puoi rifiutare il pacco. Altro in [pacchi fermi in dogana](guide:customs).

## Numero di telefono e assistenza clienti Poczta Polska

Contatti indicati sulle pagine ufficiali in inglese e polacco di Poczta Polska e Pocztex:

- **Linea generale:** +48 438 420 600, dal lunedì al venerdì 8:00–20:00, alle tariffe del tuo operatore, più modulo online sulla [pagina contatti](https://www.poczta-polska.pl/en/kontakt/).
- **Pocztex:** 804 104 104 da rete fissa polacca e +48 43 842 0 842 da cellulari e dall'estero, dal lunedì al venerdì 8:00–20:00 e sabato 8:00–16:00.
- **Domande sul tracciamento:** via e-mail a kontakt@poczta-polska.pl.
- **Problemi al punto di ritiro** (pacco mancante, punto chiuso): Contact Center 801 333 444 da rete fissa o +48 438 420 600.

> Poczta Polska mette in guardia contro falsi SMS Pocztex su consegne fallite con link a siti imitati. Pocztex non invia mai link di pagamento né chiede dati della carta via SMS. Inoltra gli SMS sospetti al numero 8080.

## Tracciare spedizioni Poczta Polska con Peek

[Peek](/) segue oltre 4.500 corrieri. Riconosce subito i codici `PX` con cifra di controllo valida, quelli da 20 cifre con `5900773` dopo le prime tre e cifra di controllo valida, e quelli da 18 cifre senza i due zeri `00` iniziali (che Peek antepone in automatico). Per i numeri da 19 cifre che iniziano per `00` chiede solo a Poczta Polska, aggiungendo la cifra di controllo come il sito ufficiale. Un codice internazionale valido in `PL` che inizia con R, E, L o C (escluso CV), come `RR`, `EE` o `CP`, va subito a Poczta Polska; un altro codice valido in `PL`, come uno `VV`, appare come «Corriere postale sconosciuto» mentre Peek chiede a Poczta Polska e [Chronopost](carrier:chronopost), e Poczta Polska prevale quando lo riconosce. Anche i link eMonitoring con `numer=` funzionano, senza CAP.

Peek mostra i testi inglesi così come scritti, tranne `Returned to sender`, che appare come «Restituito al mittente» (oppure quelli polacchi se usi Peek in polacco), ogni ufficio postale sulla mappa, il peso, il nome del servizio, il paese di destinazione e l'ufficio che custodisce un pacco pronto per il ritiro. Non c'è una data prevista di consegna. Scarta indirizzi e orari degli uffici, documenti di pagamento e servizi aggiuntivi. Le scansioni inoltrate dall'estero non portano il fuso orario: Peek le legge sull'orologio del paese di provenienza. Pallet, spedizioni raggruppate e posta non registrata non sono tracciabili. Quando un pacco registrato, preso in carico o in transito resta quattro giorni senza scansioni, Peek indica «Nessun aggiornamento da lun 12 ott» (il giorno dell'ultima scansione) e mostra «Chiedi aiuto a Poczta Polska».

Finché tieni il pacco aperto sullo schermo, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui Poczta Polska lo scansiona in consegna — finché risponde il tracciamento di Poczta Polska. Altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Solo tramite i servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti nel giorno in cui è in consegna, finché tieni il pacco aperto o le notifiche attive. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Un pacco spedito dall'estero verso la Polonia può proseguire su Poczta Polska.

## Domande sul tracciamento Poczta Polska

### Che cos'è Pocztex?

Pocztex è il servizio di corriere di Poczta Polska, con sito dedicato pocztex.pl e l'app Pocztex Mobile. I suoi pacchi usano lo stesso tracciamento eMonitoring.

### Posso tracciare Poczta Polska in inglese?

Sì. eMonitoring si apre in polacco ma dispone di una versione in inglese.

### Quanto tempo impiega Pocztex a consegnare?

Poczta Polska garantisce la consegna di Pocztex Kurier entro il quinto giorno lavorativo dalla spedizione, o il settimo per pacchi 2XL e fuori formato. I giorni lavorativi sono dal lunedì al venerdì esclusi i festivi.

### Posso reindirizzare un pacco Poczta Polska?

Non come destinatario. Le condizioni di Poczta Polska riservano al mittente le modifiche di indirizzo o destinatario, e i pacchi indirizzati a un punto o locker Pocztex non possono essere trasferiti altrove.

:::sources
- [Poczta Polska: Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – formato dei codici, ambito, punti di ritiro, operatori esteri
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – stessi dettagli in polacco
- [Poczta Polska: eMonitoring (inglese)](https://emonitoring.poczta-polska.pl/?lang=en) – esempi, messaggio d'errore, solo informativo, kontakt@
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – versione polacca, diciture degli stati
- [Pocztex: Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – tracciamento Pocztex
- [Poczta Polska: Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – consegna, avvisi, punti, locker, termini, reclami, garanzia
- [Poczta Polska: Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – awizo, giacenza, smarrimento, reclami internazionali
- [Poczta Polska: Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – nomi degli eventi e possibili variazioni
- [Poczta Polska: Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – negozi convenzionati, locker, nessun reindirizzamento, Contact Center
- [Pocztex: FAQ](https://www.pocztex.pl/en/faq-en/) – avvisi, punti di ritiro, verbale di danno
- [Poczta Polska: eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – chi reclama, termini, 30 giorni
- [Poczta Polska: Contact](https://www.poczta-polska.pl/en/kontakt/) – linea generale, orari, modulo
- [Poczta Polska: Kontakt](https://www.poczta-polska.pl/kontakt/) – versione polacca, modulo reclami
- [Pocztex: Kontakt](https://www.pocztex.pl/kontakt/) – linee Pocztex e orari
- [Poczta Polska: Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – SMS falsi, 8080
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – versione polacca, Contact Center
- [Poczta Polska: Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – dazio 3 €, regali fino a 45 €, rifiuto
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – commissione UE di gestione di 2 € per voce a carico del destinatario, versata a KAS
- [Poczta Polska: Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – tariffe dichiarazione doganale
- [Poczta Polska: Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – società statale
- [Pocztex Mobile su App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – l'app
- [Universal Parcel Scraper: Poczta Polska](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poczta-polska) – rilevamento, numeri di 18/19/20 cifre, campi letti e scartati
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
