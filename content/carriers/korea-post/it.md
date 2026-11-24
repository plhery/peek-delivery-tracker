---
title: Tracciamento Korea Post: codici EMS KR, 13 cifre e aiuto
description: Traccia un pacco Korea Post (ePOST): codici S10 in KR, numeri nazionali a 13 cifre, stati EMS e coreani, passaggio in dogana, tempi e assistenza.
slug: tracciamento-korea-post
published: 2026-11-24
updated: 2026-11-24
---

Korea Post (*우정사업본부*, portale online *ePOST* / *인터넷우체국*) è l’operatore postale nazionale della Corea del Sud, sotto il Ministero della Scienza e dell’ICT. Gestisce la consegna nazionale di pacchi e raccomandate in tutta la Corea del Sud insieme ai servizi internazionali **EMS** (Express Mail Service verso oltre 100 destinazioni, più oltre 70 paesi tramite EMS Premium), **K-Packet** e raccomandate o pacchi aerei internazionali. Puoi controllare le spedizioni internazionali sul [tracker EMS in inglese di Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), verificare i pacchi nazionali su [ePOST Domestic Tracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) oppure incollare il codice di tracciamento nel campo qui sopra.

## Codici di tracciamento Korea Post: formato S10 in KR e 13 cifre nazionali

Controlla la ricevuta dell’ufficio postale, la pagina dell’ordine sul negozio coreano o l’e-mail di conferma spedizione. Korea Post utilizza due formati principali a seconda che l’invio viaggi all’estero o all’interno della Corea del Sud:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `EM482917367KR` | Spedizione internazionale **EMS** di merci (formato UPU S10 che termina con `KR`; i documenti iniziano di solito con `EE`). |
| `RR482917367KR` | Raccomandata internazionale o spedizione K-Packet (formato UPU S10: 2 lettere + 9 cifre con cifra di controllo + `KR`). |
| `6482917364058` | Codice nazionale sudcoreano a 13 cifre per raccomandata o pacco (inizia con le cifre da `1` a `6`). Condivide la struttura numerica a 13 cifre con [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) e altri vettori. |

Nella [panoramica EMS di Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), il prefisso `EE` indica documenti EMS (che non richiedono ispezione doganale per merci), mentre il prefisso `EM` indica spedizioni EMS di merci. Per altri codici postali internazionali, vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di Korea Post

Gli invii internazionali `KR` mostrano righe UPU/EMS in inglese che conservano l’ora locale comunicata dall’operatore postale di ciascun paese, mentre i pacchi nazionali a 13 cifre mostrano etichette in coreano sull’ora standard coreana (`Asia/Seoul`, UTC+9 tutto l’anno):

| Korea Post indica (internazionale / nazionale) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `운송장출력` *(nazionale)* | Lettera di vettura stampata prima dell’accettazione in ufficio postale. | «Etichetta creata» |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Accettato allo sportello di un ufficio postale coreano o ritirato da un corriere Korea Post. | «Preso in carico» |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Lavorato nell’ufficio di scambio internazionale in Corea (come Incheon), spedito in aereo, consegnato alle poste del paese di destinazione o trasferito fra centri di smistamento nazionali. | «In transito» |
| `Ready for customs clearance` | Presentato in dogana nel paese di destinazione (o controllo doganale in esportazione/importazione). | «In dogana» |
| `배달준비` *(nazionale)* | Preparato per la consegna finale dal portalettere dell’ufficio postale locale in Corea. | «In consegna» |
| `Unsuccessful delivery` | Il tentativo di consegna a destinazione non è andato a buon fine. | «Tentativo di consegna» |
| `Delivery complete` / `배달완료` | Consegnato; il tracker di Korea Post può indicare chi ha ritirato il pacco. | «Consegnato» |

## Tempi di consegna EMS Korea Post, dogana e passaggio alle poste locali

Secondo la [panoramica del servizio EMS di Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), le [condizioni di spedizione per paese](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) e il [calcolatore di tempi e tariffe](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **Tempi di transito EMS:** per i documenti (codici `EE`) spediti a Seul entro mezzogiorno, Korea Post indica il giorno successivo oppure 3–4 giorni verso città con voli diretti, come Tokyo, Hong Kong e Singapore, e 4–6 giorni altrove; la panoramica indica 2–3 e 3–5 giorni per i documenti spediti a Seul al mattino. Aggiungi un giorno per invii spediti dopo mezzogiorno o fuori Seul, oppure diretti fuori dalla città dell’ufficio di scambio di destinazione; da un ufficio postale di montagna o insulare si aggiungono i giorni necessari per raggiungere l’International Mail Logistics Center. Le merci (codici `EM`) attendono inoltre il controllo doganale a destinazione. Il giorno di spedizione e i festivi non contano.
- **Dichiarazioni doganali:** documenti e stampe usano la dichiarazione CN22, mentre merci e articoli soggetti a dazio richiedono la dichiarazione CN23 (oltre alla fattura commerciale e agli eventuali permessi per spedizioni commerciali). Gli oggetti vietati o soggetti a restrizioni sono elencati nella [guida agli articoli vietati e limitati di ePOST](https://ems.epost.go.kr/front.Introduction04.postal).
- **Passaggio alle poste di destinazione:** quando l’invio `KR` arriva nel tuo paese (`Delivered to Destination Post` / `Arrival at inward office of exchange`), il servizio postale nazionale (per esempio [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) o [Australia Post](carrier:australia-post)) gestisce la riscossione di dazi e oneri, i tentativi di consegna e la giacenza secondo le proprie regole. Controlla lo stesso codice `...KR` anche sul sito delle poste locali per opzioni di consegna più complete. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Korea Post in ritardo, fermo in transito o smarrito

- **Silenzio dopo `Departure from the Airport`:** i dispacci internazionali EMS e K-Packet restano spesso senza scansioni da diversi giorni a oltre una settimana tra la partenza dalla Corea del Sud e l’ingresso nell’ufficio di scambio del paese di destinazione (`Arrival at inward office of exchange`). Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Fermo su `Ready for customs clearance`:** verifica lo stesso codice `...KR` sul sito dell’operatore postale del tuo paese per controllare se IVA all’importazione, dazi o spese di gestione da saldare bloccano lo svincolo.
- **Invii persi, danneggiati o mancanti:** per gli acquisti online contatta prima il venditore o il marketplace coreano; ePOST accetta una richiesta di ricerca (*행방조사청구*) online o per iscritto e pubblica le proprie regole di indennizzo (*손해배상규정*). Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Korea Post

Contatti ufficiali pubblicati sul [Centro clienti ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), sul [portale principale ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) e nella [panoramica del servizio EMS](https://ems.epost.go.kr/front.Introduction01.postal):

- **Centro clienti Korea Post (in Corea del Sud):** `1588-1300` (dal lunedì al venerdì `09:00–18:00` KST, chiuso sabato e festivi; a carico di chi chiama).
- **Dall’estero:** `+82-42-609-4295`, negli stessi orari.
- **E-mail:** `help@posa.or.kr`.
- **FAQ e richieste online:** [Centro clienti ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) e l’opzione *e-mail EMS Inquiry* su [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Indirizzo nel footer di ePOST:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Corea del Sud.

> Ignora gli SMS falsi che sostengono che il tuo pacco Korea Post non può essere consegnato senza pagare una piccola somma tramite un link non ufficiale. Eventuali oneri reali su un pacco in arrivo con codice `KR` vengono riscossi dal servizio postale o dal portale doganale del tuo paese, mai da link via SMS casuali. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Korea Post con Peek

[Peek](/) riconosce automaticamente con elevata sicurezza qualsiasi codice UPU S10 che termina con `KR` (`EM482917367KR` o `RR482917367KR`, cifre inventate) come Korea Post, e riconosce anche i link incollati di `trace.epost.go.kr` con `POST_CODE=` e di `service.epost.go.kr` con `sid1=`. Un codice nazionale a 13 cifre (`6482917364058`) coincide con più corrieri: Peek elenca Korea Post tra le opzioni e ti chiede di scegliere. Puoi selezionarlo anche in seguito con «Cambia corriere» nella schermata del pacco (cercando «South Korea Post» o «ePOST» lo trovi ugualmente), oppure incollare direttamente il link ePOST.

Peek legge il tracker EMS in inglese di Korea Post per i codici `KR` e il tracker nazionale coreano di ePOST per i codici a 13 cifre. Mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «In consegna» sui pacchi nazionali, «Tentativo di consegna», «Consegnato»), il testo della scansione con ufficio postale o aeroporto e l’orario di consegna sui pacchi nazionali. Le scansioni nazionali sono datate sull’ora coreana e quelle internazionali sul fuso orario del rispettivo luogo (quello di New York per una scansione a New York, per esempio); Peek le converte sull’orologio del tuo dispositivo. Il nome del destinatario riportato da Korea Post compare sotto «Destinatario» nella tua pagina del pacco, mai su un link condiviso; il nome del firmatario resta fuori da tutte le app di Peek, e Peek omette il nome del servizio, il nome del mittente e i numeri di telefono degli uffici. Il tracker di Korea Post non fornisce data stimata di consegna, peso né punto di ritiro.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui un pacco nazionale mostra `배달준비`, ogni 2 minuti dalle 6:00 ora coreana, finché risponde il tracker di Korea Post. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di Korea Post non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Korea Post.», con il link «Chiedi aiuto a Korea Post» verso il Centro clienti ePOST. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Korea Post.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può sdoganare pacchi, deviare spedizioni né aprire una pratica EMS presso Korea Post al posto tuo.

## Domande frequenti sul tracciamento Korea Post

### Cosa significano `EE...KR`, `EM...KR` e `RR...KR`?

Sono codici postali internazionali UPU S10 di 13 caratteri spediti dalla Corea del Sud: `EE` identifica i documenti EMS, `EM` i pacchi EMS di merci e `RR` (o altri prefissi `R`/`C`/`L`) le raccomandate, gli invii K-Packet e i pacchi internazionali.

### Perché il mio pacco EMS Korea Post si è fermato dopo aver lasciato l’aeroporto?

Una volta comparso `Departure from the Airport` in Corea del Sud, il tracciamento rimane fermo finché l’operatore postale del paese di destinazione non registra il dispaccio in arrivo al proprio ufficio di scambio (`Arrival at inward office of exchange`). Controlla lo stesso numero `...KR` anche sul sito delle poste del tuo paese.

### Korea Post consegna direttamente all’estero?

Korea Post gestisce ritiro, smistamento in esportazione e spedizione aerea dalla Corea del Sud, poi affida il pacco (`Delivered to Destination Post`) al servizio postale nazionale del paese di destinazione per lo sdoganamento all’importazione e la consegna finale.

### Come contatto il servizio clienti di Korea Post?

Chiama `1588-1300` dalla Corea del Sud (lun–ven `09:00–18:00` KST, chiuso sabato e festivi), scrivi a `help@posa.or.kr` oppure usa il [Centro clienti ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: tracciamento EMS in inglese](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – portale internazionale EMS e poste partner di destinazione
- [Korea Post (ePOST): tracciamento nazionale](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – tracciamento nazionale a 13 cifre per pacchi e raccomandate
- [Korea Post (ePOST): panoramica del servizio EMS](https://ems.epost.go.kr/front.Introduction01.postal) – prefissi `EE` e `EM`, tempi EMS, regole doganali CN22/CN23, oltre 100 destinazioni EMS / oltre 70 EMS Premium e `1588-1300`
- [Korea Post (ePOST): condizioni di spedizione per paese](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – limiti EMS e note doganali per paese
- [Korea Post (ePOST): calcolatore tempi e tariffe](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – calcolatore per EMS, EMS Premium e pacchi
- [Korea Post (ePOST): articoli vietati e limitati](https://ems.epost.go.kr/front.Introduction04.postal) – restrizioni alle spedizioni internazionali
- [Korea Post (ePOST): Centro clienti](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, orari feriali `09:00–18:00` KST e indirizzo a Naju (URL di aiuto collegato da Peek)
- [Korea Post (ePOST): portale principale](https://www.epost.go.kr/main.retrieveMainPage.comm) – panoramica di pacchi nazionali, raccomandate, EMS e K-Packet, e `+82-42-609-4295` dall’estero
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – rilevamento `KR` S10 e 13 cifre, regole per link `trace.epost.go.kr` / `service.epost.go.kr`, mappe di stato EMS inglese e nazionale coreano, fusi orari, campi esclusi e URL di aiuto
:::
