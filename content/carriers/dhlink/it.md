---
title: Tracking DHLink: codice DHgate, stati e chi consegna
description: Traccia un pacco DHLink da un ordine DHgate: quali codici accetta la ricerca, cosa significano gli stati in inglese, chi consegna nel tuo Paese e chi sentire.
slug: dhlink-tracking
published: 2026-11-16
updated: 2026-11-20
---

DHLink (驼飞侠) è la piattaforma di logistica transfrontaliera di DHgate (敦煌网). Nata nel 2014, spedisce gli ordini dei venditori DHgate e di altri clienti dalla Cina o dai suoi magazzini all'estero e li affida a un corriere del Paese di destinazione per l'ultimo miglio. Cerca il tuo codice sulla [pagina di tracciamento di DHLink](https://www.dhlink.com/trackQuery?lang=en) o incollalo nel riquadro qui sopra. Trovi il numero nei dettagli dell'ordine su DHgate; se manca, chiedilo al venditore.

## Codice di tracking DHLink: com'è fatto

DHLink non pubblica un formato fisso per i propri codici. La pagina di ricerca accetta fino a 40 numeri alla volta e riconosce quelli che chiama *Global Tracking No.*, *Logistics Delivery No.* e *DHLink pickup No.* Ecco le forme viste sui pacchi DHLink, con cifre inventate:

| Codice | Cos'è |
| --- | --- |
| `FC9999999999980` | `FC` e 13 cifre: il numero interno di DHLink («DHLink No.»). Su alcune linee fa anche da codice di tracciamento. |
| `XZG999999980WL` | `XZG`, 9 cifre e `WL`: codice di un partner di linea che DHLink riconosce come numero di tracking. |
| `YLUS9999999980` | `YLUS` e 10 cifre: una linea verso gli Stati Uniti consegnata da GOFO. |
| `QH999999999980FS` | `QH`, 12 cifre e `FS`: un'altra linea verso gli Stati Uniti consegnata da GOFO. |
| `EYT9999999980HZ` | `EYT`, 10 cifre e `HZ`: una linea verso gli Stati Uniti consegnata da SwiftX. |
| `CYS99999999980` | `CYS` e 11 cifre: una linea verso gli Stati Uniti consegnata da UniUni. |
| `JXCYI9999999990YQ` | `JXC`, `YI`, `ZO`, `US` o `ZI`, 10 cifre e `YQ`: un formato di lettera di vettura di Juxi Express a cui risponde anche il tracking di DHLink su alcuni pacchi diretti negli Stati Uniti. Per questa forma Peek interroga prima DHLink; gli altri codici `JXC` vanno a Juxi Express. |
| `RR482910576CN` | Due lettere, 9 cifre e `CN`: un codice di [China Post](carrier:china-post), usato quando la spedizione viaggia per posta. |
| `GFUS99999999999980` | Non è di DHLink: è un codice GOFO Express per l'ultimo miglio di alcune linee statunitensi. |

In un risultato DHLink elenca il «DHLink No.», il «Tracking No.» e, quando consegna un altro corriere, il «Last Leg No.» di quest'ultimo. Se il codice di tracciamento è diverso dal numero DHLink, cerca con il codice di tracciamento: interrogato solo con il numero `FC`, un pacco che ha un altro codice di tracking può non comparire. Senza `lang=en` nell'indirizzo, la pagina si apre in cinese.

## Stati del tracking DHLink: cosa vogliono dire

La pagina raggruppa le spedizioni sotto `In transit`, `Delivered` o `Not found`, mostra i codici dei Paesi di partenza e di arrivo insieme all'orario di ogni scansione e aggiunge `Signed for` a consegna avvenuta. Poiché le righe arrivano da più flussi, sul sito di DHLink lo stesso evento compare spesso più volte. Ecco le diciture inglesi più comuni, nell'ordine del viaggio:

| DHLink scrive | Cosa significa per te |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | Su una linea verso l'Europa sud-orientale: Pactic, la piattaforma in Ungheria da cui passa quella rotta, ha ricevuto i dati del pacco. Prima della scansione nel magazzino DHLink, Peek mostra «Etichetta creata»; se la riga ricompare dopo il volo, il pacco resta «In transito». |
| `Arrive at the collection warehouse` | Il pacco è arrivato al magazzino di raccolta di DHLink in Cina. |
| `The collection warehouse has completed sorting` | Smistamento completato in quel magazzino. |
| `Package received & scanned at warehouse` | Registrato e scansionato nel magazzino DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Preso in carico al punto di raccolta, in attesa del trasporto. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Ha lasciato il magazzino. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | Su alcune linee: insaccamento nel magazzino cinese per il volo, ricezione della lettera di trasporto aereo master (*master air waybill*) e uscita dal magazzino («In transito» su Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | Svincolato dalla dogana cinese di esportazione e affidato alla compagnia aerea. |
| `Flight departure`, `The flight has taken off` | L'aereo è decollato dalla Cina. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | Lo stesso passaggio: il volo è partito dalla Cina («In transito» su Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | Il volo è atterrato e all'aeroporto di arrivo stanno smontando i pallet aerei («In transito» su Peek). |
| `Shipment has arriveved at destination Country at …` | Arrivato nel Paese di destinazione (il refuso *arriveved* è di DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Dopo il volo, passaggio per l'hub europeo di un partner (indicato da un codice al posto dei puntini, come `HU` in Ungheria per Pactic) e trasporto sulla tratta successiva («In transito» su Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Sdoganamento all'importazione concluso; il corriere locale deve ora ritirare il pacco. |
| `UPS/USPS/Fedex` | Sta per avvenire il passaggio al corriere statunitense. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Affidato a [UPS](carrier:ups), USPS o [FedEx](carrier:fedex) negli Stati Uniti. Non è ancora stato consegnato a te. |
| `Package picked up by last-mile carrier` | Il corriere incaricato dell'ultimo miglio nel tuo Paese ha preso in carico il pacco. |
| `Scheduled for Delivery` | In consegna. |
| `Shipment has delivered at …` | Consegnato. |

Sulle linee per gli Stati Uniti si aggiungono scansioni come `Arrived at Origin Hub`, `Departed Destination Hub` o `Shipping Order Created.` («Etichetta creata» su Peek, circa un'ora prima dell'ingresso in magazzino). Una sede del corriere finale può scrivere `Parcel received by ABC01.` (con una propria sigla al posto di `ABC01`) e pochi minuti dopo `Parcel departed from Facility.`, entrambi «In transito». Seguono poi le righe di chi consegna, come `Arrived at GOFO Delivery Station` per GOFO, `No Access to Delivery Location` per USPS (tentativo fallito) oppure, per UniUni, `Parcel received at UniUni sorting hub.`, `Parcel processed.` e `Parcel scanned and in transit.` Sulle linee [China Post](carrier:china-post) compaiono anche scansioni solo in cinese: `中国邮政 已收取邮件` (accettato da China Post), `送交出口海关` (presentato alla dogana di esportazione) e `已妥投` dopo il codice del Paese (consegnato). Per un numero sconosciuto, la pagina risponde `There is no track for this waybill number`.

## Chi consegna i pacchi DHLink

Il sito di DHLink è rivolto ai venditori e non elenca i corrieri partner Paese per Paese: a dirti chi consegna è il risultato del tracking. In Svizzera il «Last Leg No.» può essere un codice di [DPD Svizzera](carrier:dpd-ch) a 14 cifre che inizia per `060` o `061`. Sulle linee statunitensi una scansione preannuncia a volte «UPS/USPS/Fedex» senza specificare quale prima che inizino gli aggiornamenti di quel corriere, mentre altre linee passano a GOFO Express (il cui tracking accetta anche il codice `FC` a 13 cifre per i pacchi DHgate che consegna) o a SwiftX. I pacchi spediti con [China Post](carrier:china-post) mantengono invece il codice che finisce in `CN` fino a casa: vedi sulla pagina China Post quale operatore postale li recapita in Italia.

Una volta individuati il corriere locale e il suo numero, il suo tracking mostra i dettagli su messa in consegna, avviso di giacenza e punti di ritiro: leggi anche [consegna mancata](guide:missed-delivery).

**Dogana e oneri in Italia e nell'UE.** Nell'Unione europea dal 1° luglio 2026 è stata abolita la franchigia dai dazi fino a 150 €: sulle vendite a distanza di beni importati da Paesi extra-UE fino a 150 € si applica un dazio doganale forfettario temporaneo di 3 € per voce merceologica (sottovoce tariffaria, non per singolo pezzo), oltre all'IVA. Dal 1° novembre 2026 si aggiunge inoltre una tassa di gestione dell'Unione di 2 € per voce, calcolata allo stesso modo su qualunque valore e dovuta al momento dell'accettazione della dichiarazione doganale. Maggiori dettagli nella [guida alla dogana](guide:customs).

## Pacco DHLink fermo, non trovato o segnato come consegnato

**Codice non trovato.** Controlla il numero nel tuo ordine DHgate e prova con il codice di tracciamento (*Tracking No.*) invece che con il numero `FC` di DHLink. Un codice del corriere finale non dà risultati sul sito di DHLink: cercalo direttamente presso quel corriere. Se hai scelto DHLink con «Cambia» per un codice che DHLink non conosce, Peek mostra «In attesa di DHLink» e continua a controllare. Se dopo due giorni DHLink non conosce ancora il numero e nessun'altra fonte risponde per quel codice, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e da quel momento lo controlla una volta al giorno, e una volta alla settimana quando è passata una settimana da quando l'hai aggiunto.

**Fermo dopo il volo o la dogana.** Guarda se nel risultato è comparso un «Last Leg No.» e cercalo sul sito del corriere locale, che spesso ha scansioni più aggiornate di quelle di DHLink. Approfondisci in [tracking che non si aggiorna](guide:tracking-not-updating) e [tracking di un pacco dalla Cina](guide:tracking-from-china).

**Consegnato ma non ricevuto, in ritardo o smarrito.** Le regole di rimborso e indennizzo di DHLink valgono per i venditori: è il negozio a presentare reclamo a DHLink, mentre tu ti rivolgi al venditore o a DHgate. Sono esclusi i disguidi causati dall'acquirente, come una consegna in ritardo perché il pacco non è stato ritirato o firmato, o uno sdoganamento fallito perché il destinatario non ha collaborato in tempo. Rispondi subito alle richieste della dogana o del corriere e avvisa presto il venditore: può chiedere l'indennizzo solo entro 90 giorni di calendario dalla creazione dell'ordine logistico. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Contatti e assistenza clienti DHLink

Consultata il 16 novembre 2026, la pagina dei contatti di DHLink riporta un solo indirizzo e-mail per l'assistenza clienti, `dhlinkservice@dhlink.com`, un accesso per le richieste sulla piattaforma e l'assistente virtuale «AI 小飞». La pagina è in cinese e non indica alcun numero di telefono. Per problemi con l'ordine contatta prima il venditore tramite DHgate; per la consegna finale nel tuo Paese rivolgiti al corriere incaricato del recapito.

## Tracciare un pacco DHLink con Peek

[Peek](/) non può identificare con certezza DHLink solo dalla forma del codice: le serie `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI`, `JXCZO`, `JXCUS` e `JXCZI` sono corrispondenze deboli. Incollalo comunque: Peek segue più di 4.000 corrieri, mostra «Verifica dei servizi di tracciamento…» e, appena DHLink riconosce il pacco, indica «DHLink ha questo pacco.»; altrimenti scegli DHLink con «Cambia». Un codice `JXCYI`, `JXCZO`, `JXCUS` o `JXCZI` può appartenere anche a Juxi Express: Peek interroga per primo DHLink e, finché nessuno risponde, mostra «Probabilmente DHLink»; se il venditore ha spedito con Juxi, passa a Juxi Express con «Cambia». Un codice China Post (due lettere, 9 cifre e `CN`) va a [China Post](carrier:china-post).

Peek legge direttamente il tracking di DHLink, mostra lo stato, il Paese di destinazione e la località di ogni passaggio, ed elenca ciascuna scansione una sola volta (così come la scrive DHLink, per lo più in inglese), eliminando i doppioni tra flussi oltre a codici postali e riferimenti d'ordine. Le scansioni in Cina sono lette sull'ora di Pechino, quelle di USPS sull'ora dello Stato americano interessato e quelle di Pactic, sulla rotta via Ungheria, sull'ora di Budapest, e vengono mostrate nel fuso orario del tuo dispositivo (su peektracker.com e nelle app per iPhone e Android lo ricorda una riga sotto la cronologia: «Orari nel tuo fuso orario: …»); le altre mantengono l'orario fornito da DHLink. DHLink non comunica peso, nome del servizio né orario di consegna; l'unica stima mostrata da Peek viene da un'eventuale scansione USPS inoltrata del tipo `Out for Delivery, Expected Delivery …` finché resta la più recente e indica una fascia oraria per la giornata stessa. Quando il «Last Leg No.» è un codice GOFO (`GFUS` e 14 cifre), SwiftX (`SWX` e 18 cifre) o [DPD Svizzera](carrier:dpd-ch) (14 cifre che iniziano per `060` o `061`), Peek mostra quel corriere e il suo numero, lo interroga e passa a seguirlo appena riporta avanzamenti più recenti; gli altri codici di ultimo miglio restano sulla cronologia di DHLink.

Finché risponde direttamente il tracking di DHLink, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti il giorno in cui il pacco esce in consegna (dalle 6:00 ora locale oppure, se è indicata una fascia oraria, da mezz'ora prima dell'inizio); tramite i servizi di tracciamento controlla ogni ora, e fino a ogni 15 minuti il giorno della consegna. Una volta passato il pacco su GOFO, il controllo avviene al massimo ogni 30 minuti, anche in consegna. Dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»), Peek passa a un controllo ogni ora. Puoi attivare gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare DHLink, DHgate o il venditore al posto tuo.

## Domande frequenti sul tracking DHLink

### Che cos'è DHLink?

È la piattaforma di logistica transfrontaliera di DHgate, gestita a Pechino da 驼飞侠科技（北京）有限公司. Fondata nel 2014, spedisce ordini dalla Cina e da magazzini all'estero tramite linee proprie e corrieri partner.

### Chi consegna il mio pacco DHLink?

Un corriere del tuo Paese. In Svizzera l'ultimo miglio può essere affidato a DPD Svizzera; sulle linee per gli Stati Uniti a UPS, USPS, FedEx, GOFO, SwiftX o UniUni; e sulle spedizioni postali in `CN` all'operatore postale nazionale che riceve la posta da China Post. Controlla la voce «Last Leg No.» nel risultato di DHLink.

### Cosa vuol dire «The goods have been delivered to UPS/USPS/Fedex»?

Significa che DHLink ha consegnato il pacco al corriere statunitense incaricato della tratta successiva. Non è ancora stato recapitato al tuo indirizzo: seguiranno le scansioni di quel corriere.

:::sources
- [DHLink: Chi siamo (关于我们)](https://www.dhlink.com/about/introduce) – fondata nel 2014, piattaforma logistica transfrontaliera di DHgate, magazzini all'estero e consegna nel Paese di destinazione
- [DHLink: Home page (首页)](https://www.dhlink.com/) – spedizione diretta dalla Cina e magazzini all'estero
- [DHLink: Tracciamento (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – ricerca fino a 40 numeri, tipi di codice accettati, campi DHLink No., Tracking No., Last Leg No., Signed for, messaggio per numero sconosciuto e lingua predefinita
- [DHLink: Contatti (联系我们)](https://www.dhlink.com/about/connect) – e-mail dhlinkservice@dhlink.com, richiesta sulla piattaforma, assistente AI 小飞, nessun numero di telefono
- [DHLink: Contratto di servizio (服务协议)](https://www.dhlink.com/agreementPage/service) – gestore 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: Regole di indennizzo DHLink (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – indennizzo riservato ai venditori, esclusioni per ritardi o mancato sdoganamento imputabili all'acquirente, termine di 90 giorni di calendario dall'ordine logistico
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – abolizione della franchigia fino a 150 € e dazio temporaneo di 3 € per voce dal 1° luglio 2026, tassa di gestione UE di 2 € dal 1° novembre 2026
- [Commissione europea: Customs Guidance on EUR 3 customs duty, PDF (in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tassa di 2 € contata per voce merceologica come il dazio di 3 €, per qualsiasi valore, dovuta all'accettazione della dichiarazione doganale
- [Universal Parcel Scraper: DHLink (in inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – formati dei codici, testi delle scansioni (Pactic, UniUni, China Post, USPS), fusi orari, passaggio a GOFO, SwiftX e DPD Svizzera e funzionamento su Peek
- [Universal Parcel Scraper: GOFO Express (in inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – supporto dei codici FC di DHLink nel tracking GOFO e controllo ogni 30 minuti
:::
