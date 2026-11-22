---
title: Tracciamento CDEK (СДЭК): codici e stati in italiano
description: Segui un pacco CDEK (СДЭК): come è fatto il numero d’ordine, stati russi spiegati in italiano, consegna con corriere o punto di ritiro e uso di Peek.
slug: tracciamento-cdek
published: 2026-11-22
updated: 2026-11-22
---

CDEK (СДЭК, scritto anche SDEK) è un corriere espresso presente nel catalogo di Peek per Russia, Kazakistan, Bielorussia e Serbia. Consegna i pacchi a domicilio tramite corriere oppure in punti di ritiro e locker. Il venditore o il mittente ti fornisce il numero: incollalo nel riquadro qui sopra. Il tracker di CDEK si trova su my.cdek.ru. CDEK scrive i passaggi in russo e la tabella seguente li spiega in italiano.

## Numero di tracciamento CDEK: com’è fatto

CDEK chiama ordine (заказ) ogni spedizione, quindi il codice è un numero d’ordine CDEK (diverso da quello del negozio). Il tracker accetta codici da 5 a 40 lettere e cifre: i propri numeri da 10 o 11 cifre e altri codici associati, come un codice postale internazionale. Peek associa una sola forma a CDEK (cifre inventate):

| Numero (inventato) | Che cos’è |
| --- | --- |
| `10482917365` | 11 cifre che iniziano per `10`: l’unico formato collegato a CDEK da Peek, solo come corrispondenza debole. Anche [GLS Germania](carrier:gls-de), [GLS Svizzera](carrier:gls-ch), GLS Francia, GLS Austria, GLS Polonia, PostLogistics, Coordinadora e Mainfreight usano 11 cifre. |
| `48291736548` | 11 cifre che non iniziano per `10`: Peek non propone CDEK; verifica il codice o scegli CDEK manualmente (sotto). |
| `RR482917367CN` | Un codice postale estero, qui di [China Post](carrier:china-post). Peek lo segue con quelle poste; se CDEK lo ha collegato a un ordine, seleziona CDEK per vedere i suoi passaggi. |

Non trovi il tuo? Vedi [dove trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento CDEK in italiano

Ogni passaggio CDEK ha un codice (usato da Peek) e un testo in russo, che Peek mostra così com’è. CDEK raggruppa le scansioni sotto tappe generali come `В пути`: Peek mostra una tappa generale solo quando non contiene scansioni singole. In ordine approssimativo:

| Testo CDEK | Cosa significa per te | Cosa mostra Peek |
| --- | --- | --- |
| `Создан` | Creato: l’ordine esiste in CDEK, ma il pacco potrebbe non essere ancora stato consegnato. | «Etichetta creata» |
| `Назначен курьер` | Un corriere è diretto dal mittente per il ritiro. | «Etichetta creata» |
| `Курьер забрал заказ у отправителя` | Il corriere ha ritirato il pacco dal mittente. | «Preso in carico» |
| `Принят на доставку` | Accettato per la consegna: CDEK ha il pacco. | «Preso in carico» |
| `В пути` | In viaggio: fase generale tra la presa in carico e la consegna finale. | «In transito» |
| `Принят СДЭК на территории РФ` | Un pacco dall’estero è entrato nella rete CDEK in Russia. | «In transito» |
| `Отправлен в сортировочный центр` | Inviato a un centro di smistamento. | «In transito» |
| `Прибыл в сортировочный центр` | Arrivato a un centro di smistamento. | «In transito» |
| `Отправлено в г.` + città | Spedito verso quella città. | «In transito» |
| `Отправлен в пункт доставки` | Inviato al deposito da cui partono i corrieri. | «In transito» |
| `Прибыл в пункт доставки` | Arrivato al deposito di consegna. | «In transito» |
| `Отправлен в пункт выдачи` | Inviato al tuo punto di ritiro. | «In transito» |
| `Доставляет курьер` | In consegna con corriere (raggruppa i tentativi). | «In consegna» |
| `Выдан курьеру` | Affidato al corriere per la consegna; ricompare a ogni nuovo tentativo. | «In consegna» |
| `Курьер не смог доставить заказ` | Il corriere non è riuscito a consegnare. | «Tentativo di consegna» |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Pronto: è arrivato al punto di ritiro o al locker. Puoi ritirarlo. | «Pronto per il ritiro» |
| `Вручён` | Consegnato al destinatario. | «Consegnato» |
| `Не вручён` | Non consegnato: l’ordine si è chiuso senza consegna (non ritirato o rifiutato). | «Problema segnalato» |

CDEK può anche chiudere un ordine come annullato («Problema segnalato» in Peek) oppure parzialmente consegnato se hai accettato solo una parte («Consegnato» in Peek).

## Come consegna CDEK: corriere o punto di ritiro

Le tappe ti dicono con quale modalità arriva il pacco.

**A domicilio.** Dopo l’arrivo al deposito (`Прибыл в пункт доставки`), il pacco esce con un corriere (`Выдан курьеру`). Dopo un tentativo fallito, `Выдан курьеру` ricompare per il successivo.

**Al punto di ritiro o locker.** Dopo `Отправлен в пункт выдачи`, attendi `Поступил. Заберите заказ` per andare a ritirarlo.

La pagina d’ordine su my.cdek.ru riporta punto di ritiro, indirizzo, contatti, orari e scadenza di ritiro. Peek non mostra questi dettagli, quindi controllali lì. Poiché il sito principale cdek.ru blocca gli accessi automatici, questa pagina non indica tempi di consegna, giacenza o numero di tentativi. Vedi [pacchi non ritirati](guide:uncollected-parcel), [consegna mancata](guide:missed-delivery) e [armadietti per pacchi](guide:parcel-lockers).

**Dall’estero.** `Принят СДЭК на территории РФ` indica che CDEK ha preso in carico un pacco estero in Russia. Senza accesso alle pagine ufficiali di cdek.ru, questa pagina non elenca tariffe o regole doganali; vedi [pacchi fermi in dogana](guide:customs).

## Pacco CDEK che non arriva o tracciamento bloccato

**Solo `Создан` o nessun passaggio.** Il mittente potrebbe non aver ancora consegnato il pacco oppure c’è un errore nel numero. Peek continua a chiedere. Controlla le cifre con il messaggio del venditore e chiedi al negozio dopo qualche giorno.

**Giorni senza aggiornamenti.** Se l’ultimo passaggio risale ad almeno quattro giorni fa prima della consegna o del punto di ritiro, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a CDEK.», solo in quell’avviso con il link «Chiedi aiuto a CDEK» alle FAQ. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Tentativo non riuscito. Un nuovo tentativo apparirà come `Выдан курьеру`; se non arriva, contatta il negozio.

**`Не вручён`.** L’ordine si è chiuso senza consegna. Chiedi al negozio rimborso o nuova spedizione. Vedi [restituito al mittente](guide:returned-to-sender).

**Consegnato ma non ricevuto.** Controlla se qualcuno l’ha ritirato per te, poi scrivi al negozio. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito.** Il negozio ha prenotato CDEK: rivolgiti prima al negozio. Vedi [pacchi smarriti](guide:lost-parcel).

## Assistenza clienti e contatti CDEK

Poiché il sito principale cdek.ru blocca le consultazioni automatiche, questa pagina non indica telefono né e-mail: Peek pubblica solo contatti letti sulle pagine ufficiali del corriere. Il tracciamento e le pagine ordine sono su my.cdek.ru.

Per un acquisto online rivolgiti prima al negozio.

## Seguire un pacco CDEK con Peek

[Peek](/) traccia oltre 4.500 corrieri, ma non può riconoscere con certezza CDEK dalla sola forma del numero. Incollalo comunque:

- **11 cifre che iniziano con `10`.** Peek interroga cinque corrieri compatibili alla volta mostrando «Verifica dei servizi di tracciamento…». Da Russia, Kazakistan, Bielorussia o Serbia, CDEK viene interrogato per primo; altrove, fra i primi cinque o i cinque successivi. Se CDEK conosce il numero, Peek mostra «CDEK ha questo pacco.». Se lo conoscono più corrieri scegli tu; se nessuno ancora, vedi «non ancora trovato · Peek continua a controllare». Puoi anche toccare «Cambia» e scegliere CDEK in «Compatibili con questo numero».
- **Altro formato (anche 10 cifre).** Tocca «Cambia» e cerca CDEK (anche СДЭК o SDEK).
- **Un link.** Incolla un link d’ordine my.cdek.ru (`/lkfl/order/...`) per tracciarlo subito con CDEK.

Su un pacco già salvato usa «Cambia corriere». Senza corriere mostra «Nessuna cronologia di tracciamento per ora»; sotto CDEK mostra «In attesa di CDEK» finché CDEK non conosce il codice e, se dopo due giorni CDEK non lo conosce ancora, ti chiede di controllarlo, scegliere un altro corriere o eliminare il pacco.

**Cosa mostra Peek.** Peek legge il tracciamento pubblico di CDEK con il solo numero, senza account né verifica telefonica: stato, ogni passaggio in russo, città e orari esatti (in UTC convertiti nel tuo fuso orario, con «Orari nel tuo fuso orario:» in fondo alla cronologia). Le risposte anonime non espongono il codice di ritiro riservato agli utenti connessi, e Peek esclude indirizzo mittente, dettagli della sede di ritiro (indirizzo, telefono, orari, scadenza), pagamenti, peso, dimensioni e data di consegna prevista. «Apri il sito CDEK» apre la pagina dell’ordine su my.cdek.ru.

**Un altro numero per lo stesso ordine.** Se tracci un codice collegato da CDEK (come un numero postale estero), CDEK restituisce il proprio numero d’ordine, mostrato come «CDEK lo traccia con il numero».

**Quando CDEK non risponde.** I siti di CDEK possono bloccare le richieste automatiche. Peek interroga allora ParcelsApp, Ship24 e 17TRACK (le copie di ParcelsApp riassumono talvolta i tratti senza l’ora esatta) e torna su CDEK appena riprende a rispondere.

**Numero di un altro corriere.** Se un codice di 11 cifre salvato sotto CDEK non viene trovato, Peek interroga nei primi 30 giorni altri corrieri compatibili e lo sposta in automatico («Cambio automatico da CDEK») se uno di essi mostra movimenti.

**Pacchi affidati a CDEK.** Qwintry affida alcuni pacchi a CDEK: quando indica CDEK con il suo numero, il pacco mostra «Consegna con CDEK» (o «Annunciato a CDEK, non ancora ricevuto» prima della prima scansione CDEK) e passa a seguirlo lì non appena CDEK mostra un evento almeno altrettanto recente. DobroPost riporta le tappe CDEK nel proprio tracciamento (`DBRPST`). Per un codice postale estero seguito sotto CDEK, Peek può interrogare anche la posta del paese da cui cerchi, come Belpost dalla Bielorussia o Poste Italiane dall’Italia (non dalla Russia né dal Kazakistan).

**Controlli e notifiche.** Mentre tieni il pacco aperto sullo schermo — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente CDEK fino a ogni 10 minuti, o fino a ogni 2 minuti dalle 6:00, ora di Mosca, nel giorno in cui CDEK lo scansiona in consegna, finché il tracciamento di CDEK risponde; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Scegli «Ogni scansione», «Passaggi importanti» (consigliato per i punti di ritiro, perché «Solo la consegna» non avvisa quando è pronto per il ritiro) o «Solo la consegna». Peek non può contattare CDEK o il negozio per te.

## Domande sul tracciamento CDEK

### Che cos’è СДЭК in italiano o inglese?

CDEK (scritto anche SDEK o CDEK Express). Il suo tracciamento è su my.cdek.ru.

### Com’è fatto un numero di tracciamento CDEK?

Peek collega a CDEK 11 cifre che iniziano con `10`, come `10482917365`, ma altri corrieri usano 11 cifre (e CDEK emette anche codici a 10 cifre).

### Cosa significa «Поступил. Заберите заказ» su CDEK?

Il pacco è arrivato al punto di ritiro o al locker e puoi ritirarlo: Peek mostra «Pronto per il ritiro». Controlla indirizzo e scadenza su my.cdek.ru.

### Posso seguire un pacco CDEK in italiano?

Sì. Peek mostra lo stato generale in italiano (ad esempio «In transito») e mantiene i singoli passaggi nel russo originale di CDEK, tradotti nella tabella sopra.

### Perché Peek mi chiede di scegliere un corriere per il mio codice CDEK?

GLS, PostLogistics e altri corrieri usano anch’essi 11 cifre. Se più di uno conosce quel numero, seleziona CDEK dall’elenco.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – nomi, paesi, formato 11 cifre (`10`) e 10 cifre, link my.cdek.ru, codici, testi russi e stati, orari UTC, numero canonico e controllo anti-bot
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – tracciamento Qwintry con riferimento a CDEK e al suo codice
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – passaggi CDEK nel tracciamento DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri corrieri a 11 cifre, ordine delle verifiche e dei servizi terzi, copie ParcelsApp, passaggi di consegna e Belpost in Bielorussia
:::
