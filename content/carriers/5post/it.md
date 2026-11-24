---
title: Tracciamento 5Post: codici, ritiro Pyaterochka e stati
description: Traccia un pacco 5Post (FivePost / X5) in Russia: codici P di Avito e numeri ordine, stati russi, ritiro da Pyaterochka e Perekrestok e aiuto.
slug: tracciamento-5post
published: 2026-11-24
updated: 2026-11-24
---

5Post (*5Пост* / *FivePost*, gestito da X5 Group) è una rete russa di pacchi e-commerce, spedizioni tra privati e logistica che consegna ordini online e pacchi Avito Delivery in oltre **25.700** locker (*постаматы*) e punti ritiro alle casse dei supermercati **Pyaterochka** («Пятёрочка») e **Perekrestok** («Перекрёсток») in **4.950** città e località della Russia, supportata da **51** centri di smistamento ([fivepost.ru](https://fivepost.ru/)). 5Post collabora con oltre 1.000 negozi online e marketplace — tra cui Avito Delivery e ordini internazionali AliExpress — ed effettua anche consegne a domicilio con corriere e trasporto merci LTL/FTL.

Puoi verificare un pacco su [fivepost.ru](https://fivepost.ru/) (sotto *Где мой заказ?*) oppure incollare il numero d’ordine o un link `fivepost.ru/tracking?id=...` nel campo qui sopra.

## Formati dei codici di tracciamento 5Post

5Post traccia le spedizioni tramite il numero d’ordine del negozio o marketplace — qualsiasi codice unico da 3 a 40 lettere e cifre che contenga almeno una cifra:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `P48291736051` | `P` + 11 cifre (12 caratteri), il formato dei codici Avito Delivery. Peek interroga [Colis Privé](carrier:colis-prive) e 5Post, in Russia prima 5Post. |
| `99994829173605` | 14 cifre, un numero d’ordine di un negozio online. Peek non propone mai 5Post per questo formato e interroga le società DPD, Chronopost, SEUR, BRT e altri corrieri: scegli 5Post manualmente o incolla il link 5Post. |

Poiché ogni negozio sceglie i propri numeri d’ordine, due ordini possono avere lo stesso codice. Il sito di 5Post chiede allora alcune cifre del telefono del destinatario e Peek non può distinguere i due ordini. Un link `fivepost.ru/tracking?id=...` assegna subito il pacco a 5Post. Per altri formati, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di 5Post

Il servizio di tracciamento di 5Post abbina codici tecnici a descrizioni in russo, indicando il magazzino regionale di smistamento (`РЦ ...`) nelle scansioni di lavorazione e partenza.

| Codice 5Post e testo russo | Significato |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Dati dell’ordine registrati; 5Post attende che il venditore o il mittente consegni il pacco. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | Il mittente ha consegnato o affidato il pacco a 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | In lavorazione presso un magazzino regionale di smistamento 5Post (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Partito dal magazzino di smistamento verso il polo successivo o il negozio di ritiro. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Arrivato alla cassa Pyaterochka / Perekrestok scelta o al locker 5Post — **pronto per il ritiro**, non ancora ritirato! |
| `DONE` — `Получен` | Ritirato dal destinatario (consegnato). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | In consegna a domicilio per gli ordini affidati al corriere. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | Tentativo di consegna con corriere non riuscito. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Ordine dall’estero (come AliExpress) in sdoganamento, in attesa dei tuoi dati per il rappresentante doganale o in attesa del pagamento dei dazi. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | La dogana ha svincolato il pacco per proseguire con 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Non ritirato entro il termine di giacenza o annullato dopo l’affidamento, in rientro verso il negozio o il mittente (`UNCLAIMED_READY_FOR_REPICKUP` indica la preparazione per un nuovo deposito al punto di ritiro). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Problema dell’ordine, annullamento, smarrimento o telefono / data di consegna / indirizzo mancante da correggere. |

Fai attenzione a una particolarità importante dei codici 5Post: `DELIVERED` (`Доставлен в пункт выдачи`) significa **pronto per il ritiro** in negozio o locker, mentre `DONE` (`Получен`) indica che l’hai effettivamente ritirato.

## Ritiro da Pyaterochka, Perekrestok e nei locker

Un ordine da ritirare ti aspetta in un locker 5Post (*постамат*) o alla cassa (*касса*) del supermercato Pyaterochka o Perekrestok scelto in fase d’ordine; le FAQ di 5Post riportano gli orari di apertura di locker e punti di ritiro:

- **Codice di ritiro:** quando l’ordine arriva al punto (`Доставлен в пункт выдачи`), 5Post invia il codice di ritiro via SMS. Digitalo sullo schermo del locker oppure comunicalo in cassa. Le FAQ di 5Post spiegano dove recuperare il codice e cosa fare se il personale non trova l’ordine.
- **Proroga della giacenza e cambio telefono:** come indicato nelle [FAQ di 5Post](https://fivepost.ru/faq/), i destinatari possono controllare i tempi di giacenza, prorogarli, pagare online sul sito o tramite app, oppure leggere come cambiare numero di telefono, sbloccare uno sportello o segnalare un vano vuoto o errato.
- **Ordini dall’estero (AliExpress):** gli ordini internazionali gestiti da 5Post includono la tratta aerea (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) e lo sdoganamento russo; se compare `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) o `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), il rappresentante doganale richiede i tuoi dati o mancano i pagamenti doganali; le FAQ di 5Post indicano quali ordini passano in dogana, perché servono i dati, come contattare il rappresentante e quando pagare. Vedi la [guida dogana](guide:customs) e [quando il tracciamento non si aggiorna](guide:tracking-not-updating).

Per i pacchi gestiti in Russia dalle poste nazionali o da un altro corriere, confronta con [Russian Post](carrier:russian-post) e [CDEK](carrier:cdek).

## Tracciare 5Post con Peek

Incolla il codice `P`, il numero d’ordine del negozio o il link `fivepost.ru/tracking?id=...` in [Peek](/). Per un codice `P`, Peek interroga Colis Privé e 5Post (in Russia prima 5Post) e mostra «5Post ha questo pacco.» non appena 5Post risponde. Per un numero d’ordine a 14 cifre non propone mai 5Post: tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella sua pagina e seleziona 5Post. Un link 5Post registra subito il pacco sotto 5Post.

Peek legge il tracciamento pubblico di 5Post tramite l’adattatore open source [universal-parcel-scraper 5Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post) e riprova dopo un errore di rete. Mostra il testo russo di ogni scansione, il centro di smistamento o hub citato nelle scansioni di magazzino (`РЦ ...`, `ХАБ ...`), il nome e l’indirizzo del punto di ritiro quando l’ordine è disponibile in negozio o locker e l’orario di ritiro. 5Post data ogni scansione con il relativo offset UTC e Peek la mostra con l’ora del tuo dispositivo. Termine di giacenza, dettagli di pagamento, orari del punto, nome del mittente e numero di colli restano sul sito e sull’app di 5Post.

Peek interpreta con precisione i codici 5Post così che `DELIVERED` non generi mai un falso avviso di consegna a casa: **Etichetta creata** (`INFORMATION_RECEIVED`), **Preso in carico** (`PROCESS_WITHDRAWN`), **In transito** (magazzino, partenza, tratte aeree e trasferimento post-dogana), **In dogana** (richiesta dati del rappresentante, sdoganamento e pagamento dazi), **In consegna** (`RECEIVED_BY_COURIER`), **Tentativo di consegna** (`RETURNING_BY_COURIER`), **Pronto per il ritiro** (`DELIVERED` — `Доставлен в пункт выдачи`), **Consegnato** (`DONE` — `Получен`), **Restituito al mittente** (`RETURNED_TO_...` e codici `UNCLAIMED_...`, tranne `UNCLAIMED_READY_FOR_REPICKUP` che resta In transito) e **Problema segnalato** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED` ed errori di registrazione).

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a 5Post.», con il link «Chiedi aiuto a 5Post» verso le FAQ di 5Post. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a 5Post.»

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui 5Post mostra `Передан курьеру`, ogni 2 minuti dalle 6:00 secondo l’orologio dell’ultima scansione, finché il tracker di 5Post risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di 5Post non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Contattare 5Post

5Post pubblica linee telefoniche ufficiali e moduli di supporto con o senza accesso su [fivepost.ru/contacts](https://fivepost.ru/contacts/) e [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Telefono Mosca e regione di Mosca:** `8-495-935-88-00` (secondo la tariffa del tuo operatore)
- **Regioni della Russia:** `8-800-100-05-05`
- **FAQ e modulo ticket online:** [FAQ 5Post](https://fivepost.ru/faq/) e [Contatti 5Post](https://fivepost.ru/contacts/) (accetta richieste dei destinatari con o senza login e allegati fino a 40 MB)
- **Indirizzo legale:** 109029, Mosca, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Domande frequenti

### Perché lo stato interno di 5Post mostra `DELIVERED` prima che io ritiri il pacco?

Nel sistema di 5Post, `DELIVERED` (`Доставлен в пункт выдачи`) indica che il pacco è arrivato alla cassa o al locker Pyaterochka/Perekrestok e ti sta aspettando. Peek lo mostra come **Pronto per il ritiro** e attende `DONE` (`Получен`) prima di indicare **Consegnato**.

### Dove ritiro un ordine 5Post?

Lo ritiri al locker 5Post (*постамат*) o alla cassa del supermercato Pyaterochka («Пятёрочка») o Perekrestok («Перекрёсток») scelto al momento dell’ordine, utilizzando il codice inviato da 5Post via SMS.

### Perché 5Post ha richiesto dati doganali sul mio ordine AliExpress?

Un ordine dall’estero viene sdoganato da un rappresentante doganale, che può richiedere i tuoi dati (`CUSTOMS_DATA_REQUIRED`). Le FAQ di 5Post spiegano quali ordini passano in dogana, perché servono i dati e come contattare il rappresentante. Dopo lo svincolo, 5Post mostra `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### Come contatto 5Post se lo sportello del locker non si apre o il pacco manca?

Chiama `8-495-935-88-00` (Mosca e regione) o `8-800-100-05-05` (regioni), oppure invia una segnalazione destinatario (con foto fino a 40 MB) tramite [fivepost.ru/contacts](https://fivepost.ru/contacts/) o il tuo account 5Post.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [FAQ 5Post](https://fivepost.ru/faq/)
- [Contatti 5Post](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
