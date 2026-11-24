---
title: Tracciamento DobroPost: codici DBRPST, stati e hub Cina
description: Traccia un pacco DobroPost dalla Cina a Russia o Bielorussia: codici DBRPST, scansioni magazzino e dogana, ritiro PVZ, corrieri locali e aiuto.
slug: tracciamento-dobropost
published: 2026-11-24
updated: 2026-11-24
---

DobroPost (*ДоброПост*, gestito da *OOO «Dobropost Logistika»* a Mosca insieme a *OOO ICOM DP SOLUTIONS* in Armenia) è un servizio di spedizione pacchi dalla Cina verso Russia e Bielorussia usato da chi acquista su marketplace cinesi come Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish e JD. Gli acquirenti fanno inviare gli acquisti al magazzino DobroPost di Jixi (Heilongjiang), dove gli articoli vengono controllati, imballati e pesati; poi il pacco supera la dogana e raggiunge un punto di ritiro DobroPost (PVZ) oppure [CDEK](carrier:cdek), DPD o [Russian Post](carrier:russian-post). Il tracker DobroPost riporta anche le scansioni di Boxberry. Puoi verificare una spedizione su [tracking.dobropost.com](https://tracking.dobropost.com/) oppure incollare il codice `DBRPST` nel campo qui sopra.

## Codici di tracciamento DobroPost (formato `DBRPST`)

Un codice pacco DobroPost inizia con `DBRPST`, seguito da 10 o 11 cifre:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `DBRPST6482917304` | Codice pacco DobroPost da 16 caratteri (`DBRPST` + 10 cifre). |
| `DBRPST73918462051` | Codice pacco DobroPost da 17 caratteri (`DBRPST` + 11 cifre). |

I codici assegnati dai corrieri cinesi fino al magazzino DobroPost appartengono a quei corrieri, non a DobroPost: [tracking.dobropost.com](https://tracking.dobropost.com/) e Peek seguono il pacco tramite il numero `DBRPST`. Vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di DobroPost

DobroPost pubblica tappe in russo abbinate a codici interni — prima dal proprio magazzino in Cina e dalla dogana, poi inoltrate direttamente dal corriere locale ([CDEK](carrier:cdek), Boxberry o [Russian Post](carrier:russian-post)):

| Codice o dicitura DobroPost | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | Il pacco è registrato su DobroPost; pochi giorni dopo il magazzino in Cina vi imballa la tua merce. | «Etichetta creata» |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Imballato e pesato nel magazzino DobroPost in Cina, in attesa di pagamento della spedizione o inserito in un registro di partenza. | «Preso in carico» |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Partito dal magazzino DobroPost in Cina e uscito dalla Cina sulla tratta internazionale. | «In transito» |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | In sdoganamento export in Cina o import in Russia o Bielorussia. | «In dogana» |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Svincolato dalla dogana e diretto alla rete locale dell’ultimo miglio. | «In transito» |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | La dogana ha rifiutato lo svincolo; il tracker indica il motivo, ad esempio merce non a uso personale. Controlla il tuo account DobroPost. | «Problema segnalato» |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Scansioni nazionali inoltrate di CDEK, Boxberry o Russian Post verso la tua città. | «In transito» |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Arrivato al punto di ritiro o ufficio scelto e pronto per il ritiro. | «Pronto per il ritiro» |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Non ritirato o respinto sull’ultima tratta, in rientro presso DobroPost che può rispedirlo. | «Restituito al mittente» |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | La dogana ha negato lo svincolo e autorizzato il reso del pacco in Cina. | «Restituito al mittente» |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Ritirato al punto di consegna o consegnato al destinatario. | «Consegnato» |

## Tempi di consegna, tariffe e punti di ritiro DobroPost PVZ

Secondo [Tariffe DobroPost](https://dobropost.com/tariffs), i tempi decorrono dalla spedizione dal magazzino DobroPost in Cina fino all’arrivo a un punto PVZ o alla consegna a un corriere nazionale:

- **Tariffe Express** (peso volumetrico, senza articoli sensibili): in genere **9–13 giorni** a Mosca, **10–14 giorni** a San Pietroburgo, **9–12 giorni** a Novosibirsk, Krasnodar, Ekaterinburg, Kazan, Irkutsk e Ulan-Ude, e **11–15 giorni** a Minsk (Bielorussia).
- **Tariffe Economy** (peso reale, adatte a pacchi pesanti o ingombranti): in genere **19–25 giorni** a Mosca, **20–27 giorni** a San Pietroburgo, **15–23 giorni** a Irkutsk e **22–29 giorni** a Minsk.
- **Tariffe Estremo Oriente e Pacchi Pesanti:** **6–9 giorni** a Vladivostok, **8–12 giorni** a Blagoveščensk e **6–9 giorni** per pacchi singoli pesanti da 5 a 30 kg.
- **Punti di ritiro DobroPost PVZ:** elencati su [dobropost.com/pvz](https://dobropost.com/pvz) a Mosca, Odincovo, San Pietroburgo, Kazan, Krasnodar, Novosibirsk, Ekaterinburg, Irkutsk, Ulan-Ude, Blagoveščensk, Vladivostok, Ussurijsk e Minsk. Le tariffe di consegna PVZ sono `100 RUB` (fino a 5 kg), `200 RUB` (fino a 10 kg) e `300 RUB` (oltre 10 kg); le città senza PVZ DobroPost usano punti e corrieri partner. Un pacco resta gratuito per 7 giorni in un PVZ DobroPost, poi 50 ₽ al giorno, e viene smaltito dopo 30 giorni dall’arrivo.

## Dogana, pause di tracciamento e problemi col pacco

- **Pagamento richiesto prima della spedizione:** se il tracciamento si ferma su `AWAITING_PAYMENT`, il pacco resta nel magazzino in Cina finché non paghi la fattura nel tuo account cliente DobroPost.
- **Pause al confine e in dogana:** gli aggiornamenti si fermano spesso tra `FLEW_OUT_FROM_CHINA` e `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION` oppure durante il controllo doganale. Vedi [pacchi fermi in dogana](guide:customs) e [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Danni, articoli mancanti o resi:** le [condizioni DobroPost](https://dobropost.com/rules) richiedono di annotare eventuali danni sulla ricevuta alla consegna e mostrare contenuto e imballo originale per un reclamo. La [Knowledge Base](https://dobropost.com/baza_main) spiega cosa fare se manca un articolo. Un pacco non ritirato torna a DobroPost (`RETURNED`), che può rispedirlo contattando il supporto. Vedi [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e link ufficiali DobroPost

Il sito di DobroPost dispone del pulsante «Напишите в поддержку» (scrivi al supporto), di una community clienti e di un canale Telegram, e consente di inviare reclami o idee direttamente al CEO Sergey Serbin. La [Knowledge Base](https://dobropost.com/baza_main) spiega come aprire richieste al supporto, cambiare tariffa a un pacco già creato e completare la dogana.

- **Operatore russo registrato:** *OOO «Dobropost Logistika»* (*ООО «Добропост Логистика»*, INN `9722023780`), Mosca (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; il sito riporta anche `Michurinsky Prospekt, 54 bldg. 2`).
- **Entità armena:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, provincia di Lori, Armenia.
- **Condizioni e tariffe:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) e [dobropost.com/pvz](https://dobropost.com/pvz).

> Diffida di falsi messaggi Telegram o link SMS che chiedono tasse doganali extra fuori dal tuo account ufficiale DobroPost o dal portale ufficiale del broker doganale. Verifica sempre ogni pagamento prima nel tuo account DobroPost. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco DobroPost con Peek

[Peek](/) rileva DobroPost automaticamente come unico candidato quando inserisci un numero `DBRPST` (`DBRPST6482917304` o `DBRPST73918462051`, inventati) e lo indica subito con «Corriere rilevato»; Peek riconosce anche i link incollati `tracking.dobropost.com/?trackNumber=`. Se devi scegliere DobroPost manualmente, tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco (cercare «ДоброПост» o «Dobropost» lo trova ugualmente).

Peek legge direttamente il tracker di DobroPost e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «Pronto per il ritiro», «Problema segnalato», «Restituito al mittente», «Consegnato»), i testi russi di DobroPost e dei partner inoltrati (CDEK, Boxberry e Russian Post) senza numeri di telefono, e l’orario di consegna a consegna avvenuta. La località indicata tra parentesi da CDEK alla fine di una scansione vale come luogo della scansione. I codici stato restano fuori da tutte le app Peek. DobroPost fornisce ogni scansione con l’offset UTC di Mosca, comprese quelle del magazzino in Cina, e Peek le mostra sull’orologio del tuo dispositivo. Il tracker DobroPost non fornisce data stimata di consegna, peso, nome del firmatario né codice del corriere locale.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché il tracker di DobroPost risponde. Altrimenti Peek controlla ogni ora. Il tracker di DobroPost non mostra la fase in consegna, perciò Peek non passa mai ai controlli ogni 2 minuti del giorno di consegna. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di DobroPost non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a DobroPost.» Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a DobroPost.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può pagare fatture di spedizione, inviare dati del passaporto in dogana né contattare DobroPost o i suoi partner locali al posto tuo.

## Domande frequenti sul tracciamento DobroPost

### Com’è fatto un codice di tracciamento DobroPost?

I codici internazionali DobroPost iniziano con `DBRPST` seguito da 10 o 11 cifre (per esempio `DBRPST6482917304` o `DBRPST73918462051`, inventati).

### Quanto tempo impiega una consegna DobroPost dalla Cina?

Le stime pubblicate su [dobropost.com/tariffs](https://dobropost.com/tariffs) vanno da **6–9 giorni** a Vladivostok e **9–13 giorni** (Express) o **19–25 giorni** (Economy) a Mosca, fino a **11–15 giorni** (Express) o **22–29 giorni** (Economy) a Minsk, dalla partenza dal magazzino in Cina.

### Quali corrieri nazionali consegnano i pacchi DobroPost in Russia e Bielorussia?

DobroPost consegna ai propri punti PVZ in Russia e a Minsk e, secondo le tariffe, tramite [CDEK](carrier:cdek), DPD e [Russian Post](carrier:russian-post) (solo CDEK per Minsk). Il tracker inoltra le scansioni di CDEK, Boxberry e Russian Post.

### Perché il mio pacco DobroPost è fermo su `AWAITING_PAYMENT` o in dogana?

`AWAITING_PAYMENT` indica che il pacco è stato imballato e pesato nel magazzino DobroPost in Cina e attende il pagamento della spedizione nel tuo account. In caso di fermo doganale, la [Knowledge Base](https://dobropost.com/baza_main) spiega la procedura e il supporto può aggiornarti sullo stato.

:::sources
- [DobroPost: sito ufficiale](https://dobropost.com/) – spedizioni da marketplace Cina–Russia/Bielorussia, magazzino di Jixi, supporto e contatto CEO, società a Mosca e in Armenia (*OOO «Dobropost Logistika»*, INN 9722023780)
- [DobroPost: tracker pacchi](https://tracking.dobropost.com/) – pagina pubblica di ricerca `DBRPST`
- [DobroPost: tariffe e tempi](https://dobropost.com/tariffs) – tempi Express, Economy, Estremo Oriente e Pacchi Pesanti, regole peso e corrieri partner
- [DobroPost: punti di ritiro (PVZ)](https://dobropost.com/pvz) – sedi PVZ DobroPost in Russia e Minsk, costi ritiro e giacenza
- [DobroPost: Knowledge Base](https://dobropost.com/baza_main) – guide acquisto, richieste supporto, cambio tariffa, dogana e articoli mancanti
- [DobroPost: Condizioni generali di trasporto](https://dobropost.com/rules) – termini di trasporto e intermediazione doganale
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – formato `DBRPST`, regola link `tracking.dobropost.com`, mappa stati DobroPost/CDEK/Boxberry/Russian Post, località tra parentesi e orari con offset di Mosca
:::
