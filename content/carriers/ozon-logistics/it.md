---
title: Tracking Ozon: codici d'ordine e stati in italiano
description: Traccia un ordine Ozon (Ozon Rocket, Ozon Доставка): formato dei codici con trattini, significato degli stati russi in italiano e tracciamento in Peek.
slug: tracking-ozon
published: 2026-11-21
updated: 2026-11-21
---

Il servizio di consegna di Ozon trasporta gli ordini affidati dai venditori fino a casa tua o a un punto di ritiro. Il catalogo di Peek lo elenca in Russia, Bielorussia e Kazakistan, anche con i nomi Ozon Rocket, OZON Rocket Доставка, Ozon Доставка e Ozon Dostavka. Incolla il tuo codice nel campo qui sopra mantenendo i trattini. Il tracciamento di Ozon è tracking.ozon.ru e Peek lo legge per te come spiegato sotto.

## Codice di tracking Ozon: com'è fatto

Peek riconosce due formati per Ozon, entrambi scritti con i trattini. I trattini contano: senza, le cifre assomigliano ai numeri di altri corrieri. Esempi inventati:

| Codice (inventato) | Cos'è |
| --- | --- |
| `48291057-3628-1` | 8 cifre, 4 cifre e 1 cifra unite da trattini. Peek riconosce subito Ozon. |
| `4829105736-2841-2` | Lo stesso schema con 10 cifre all'inizio. Anche qui Peek riconosce Ozon. |
| `4829105736281` | Il primo numero senza trattini, o con spazi al loro posto. DPD France, Ukrposhta e altri corrieri usano codici a 13 cifre, quindi le cifre da sole non bastano a indicare Ozon. Se aggiunto da Russia, Bielorussia o Kazakistan, Peek interroga Ozon insieme ad altri corrieri; da altrove non lo interroga automaticamente. Reinserisci i trattini oppure scegli Ozon a mano (sotto). Vale lo stesso per 15 cifre, formato usato da FedEx e altri. |
| `PL-482910-57362` | `PL`, 6 cifre e poi altre lettere o cifre, con trattini. Peek riconosce Ozon. |
| `PL-482910-5736` | `PL` seguito da esattamente 10 cifre coincide anche con i numeri di [Amazon](carrier:amazon-logistics). Peek lo assegna ad Amazon e non permette di selezionare Ozon per questo formato. |

Le minuscole non contano: `pl-` equivale a `PL-`. Non trovi il codice? Leggi [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei codici di tracking](guide:tracking-number-formats).

## Stati del tracking Ozon in italiano

Il codice di Peek elenca i passaggi mostrati dal tracciamento di Ozon in russo. Peek legge quel tracciamento e riporta le stesse parole russe, tranne `Заказ в работе`, `Заказ на пути к границе страны назначения` e `Заказ прибывает на приграничный пункт`, che esclude (vedi «Tracciare un ordine Ozon con Peek»).

**Nel tuo Paese.** All'incirca nell'ordine in cui compaiono:

| Cosa dice Ozon | Cosa significa per te |
| --- | --- |
| `Передается в доставку` | Il venditore ha preparato l'ordine e lo sta affidando per la consegna. Ozon potrebbe non averlo ancora ricevuto. |
| `Заказ принят перевозчиком.` | Il corriere ha preso in carico l'ordine: è in viaggio. |
| `Заказ отправили на сортировочный терминал`, `Заказ отправлен на сортировочный терминал.` | Inviato a un centro di smistamento. |
| `Заказ покинул сортировочный терминал.` | Ha lasciato il centro di smistamento. |
| `Заказ ожидает отправки в город получателя.` | In attesa di spedizione verso la tua città. |
| `Заказ везут в город получателя` | In viaggio verso la tua città. |
| `Заказ везут` | In viaggio. |
| `Заказ передали в курьерскую доставку` | Affidato alla consegna tramite corriere, che ti chiama per concordare l'orario. |
| `Заказ в пункте выдачи` | È arrivato al punto di ritiro: puoi andare a ritirarlo. |
| `Заказ получен в пункте выдачи.` | Ritirato al punto di ritiro o all'ufficio postale: consegnato. |
| `Заказ успешно доставлен получателю.` | Consegnato. Compare quando l'ordine non è passato da un punto di ritiro o ufficio postale, ad esempio con consegna tramite corriere. |
| `Заказ отменён` | L'ordine è stato annullato. |
| `Заказ в работе` | Ordine in lavorazione: segnaposto del portale Ozon, non una tappa reale del viaggio. |

**Da un altro Paese.** Un ordine spedito dall'estero mostra anche le fasi doganali, all'incirca in questo ordine:

| Cosa dice Ozon | Cosa significa per te |
| --- | --- |
| `Заказ везут на таможню в стране отправления` | In viaggio verso la dogana del Paese di spedizione. |
| `Заказ привезли на таможню для экспортного таможенного оформления` | Arrivato in dogana di esportazione in quel Paese. |
| `Заказ покинул зону экспортного таможенного оформления` | Ha lasciato la zona di sdoganamento all'esportazione. |
| `Заказ на пути к границе страны назначения` | In viaggio verso il confine del tuo Paese. |
| `Заказ прибывает на приграничный пункт` | In arrivo al valico di frontiera. |
| `Заказ привезли в страну назначения` | Arrivato nel tuo Paese. |
| `Заказ передан на импортное таможенное оформление.` | Consegnato alla dogana di importazione del tuo Paese. |
| `Заказ проходит импортное таможенное оформление` | Sdoganamento all'importazione in corso. |
| `Заказ выпущен импортной таможней.` | Sdoganato dalla dogana di importazione: riparte il viaggio. |
| `Импортная таможня задержала заказ` | La dogana di importazione trattiene l'ordine per ulteriori controlli. |

## Come consegna Ozon: corriere o punto di ritiro

Le diciture indicano con quale modalità arriva l'ordine.

**A domicilio.** Dopo `Заказ передали в курьерскую доставку` (classificato «In transito» in Peek), un corriere porta l'ordine e ti chiama prima per concordare l'orario. L'ultima tappa è `Заказ успешно доставлен получателю.`

**Al punto di ritiro.** Attendi `Заказ в пункте выдачи` («Pronto per il ritiro») e vai a ritirarlo. Una volta ritirato compare `Заказ получен в пункте выдачи.` (stessa formula per il ritiro in ufficio postale).

Poiché Peek non ha potuto leggere il sito web di Ozon, questa pagina non riporta tempi di consegna, orari di apertura, periodi di giacenza o regole sui tentativi di consegna: chiedi a Ozon. Vedi anche [pacco non ritirato](guide:uncollected-parcel) e [consegna mancata](guide:missed-delivery).

**Dall'estero.** Le righe doganali sopra indicano dove si trova un ordine internazionale. Questa pagina non indica norme o tariffe doganali perché Peek non ha potuto verificarle su una fonte ufficiale. Vedi [pacchi fermi in dogana](guide:customs).

## Ordine Ozon fermo o che non arriva

**Nessuna tappa ancora, o solo `Передается в доставку`.** Il venditore potrebbe non aver ancora consegnato l'ordine, oppure il portale Ozon non ha ancora registrato il codice. Peek continua a verificare. Se hai inserito il codice senza trattini, scegli Ozon con «Cambia corriere».

**Peek è indietro rispetto a tracking.ozon.ru.** Quando il portale di Ozon non risponde a Peek, interviene un servizio di tracciamento che può essere in ritardo. Confronta con tracking.ozon.ru prima di preoccuparti.

**Nessuna nuova tappa da giorni.** Se l'ultimo aggiornamento risale a quattro o più giorni fa mentre Peek mostra «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella tappa («Nessun aggiornamento da lun 12 ott») e collega la guida di Ozon («Chiedi aiuto a Ozon»). Altro in [perché il tracking non si aggiorna](guide:tracking-not-updating).

**`Импортная таможня задержала заказ`.** La dogana sta controllando l'ordine. Attendi `Заказ выпущен импортной таможней.`; se resta fermo a lungo, chiedi a Ozon.

**`Заказ отменён`.** L'ordine è stato annullato e non arriverà. Chiedi un rimborso a Ozon o al negozio dove hai acquistato.

**Consegnato, ma non è arrivato nulla.** Verifica se qualcuno lo ha ritirato al posto tuo, poi contatta Ozon. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito.** Contatta Ozon o il negozio da cui hai ordinato. Vedi [pacco smarrito](guide:lost-parcel).

## Assistenza clienti e contatti Ozon

Poiché Peek non ha potuto leggere il sito web di Ozon, questa pagina non riporta numeri di telefono né indirizzi e-mail: Peek indica solo contatti letti sulle pagine ufficiali del corriere. Il tracciamento di Ozon è su tracking.ozon.ru.

Per un ordine acquistato su Ozon, contatta Ozon. Per un ordine fatto in un altro negozio, rivolgiti prima a quel negozio, che ha prenotato la consegna.

## Tracciare un ordine Ozon con Peek

[Peek](/) segue un codice Ozon con il solo numero: niente account, numero di telefono o altri codici.

- **Con i trattini.** Peek riconosce subito Ozon («Corriere rilevato») e archivia il pacco sotto Ozon.
- **Senza trattini.** Peek non riesce a identificare il corriere dalle sole cifre. Incollalo comunque: Peek traccia oltre 4.500 corrieri. Mentre interroga i corrieri compatibili e i servizi di tracciamento, mostra «Verifica dei servizi di tracciamento…». Ozon è tra i corrieri interrogati solo se aggiungi il codice da Russia, Bielorussia o Kazakistan. Se i servizi di tracciamento indicano Ozon con tappe datate, Peek lo assegna a Ozon da solo; altrimenti tocca «Cambia» e cerca Ozon (o «Cambia corriere» su un pacco già salvato).
- **Un link.** Incolla un link di tracking.ozon.ru con `track=` o di rocket.ozon.ru con `SearchId=`, e Peek seguirà quel codice con Ozon.

**Da dove arrivano le tappe.** Peek legge il tracciamento di Ozon e mostra ogni passaggio nel russo di Ozon (come nelle tabelle sopra), con l'orario e le date di consegna previste fornite da Ozon. Gli orari con fuso esplicito vengono letti come istanti esatti e quelli privi di fuso sull'ora di Mosca, quindi mostrati nel fuso orario del tuo dispositivo; su peektracker.com e nelle app iPhone e Android la cronologia si chiude con «Orari nel tuo fuso orario:» e il suo nome. Poiché Ozon non fornisce luoghi per le tappe, non compare alcuna mappa. Finché Ozon non conosce il numero, Peek mostra «In attesa di Ozon», o «Nessuna cronologia di tracciamento per ora» per un numero aggiunto senza corriere. Se il portale di Ozon non risponde, Peek interroga nell'ordine ParcelsApp, Ship24 e 17TRACK e mostra la cronologia del primo che ha il pacco, nelle sue parole (che può essere indietro rispetto a tracking.ozon.ru). Il controllo diretto di Ozon in Peek non accetta i numeri `PL-`: Peek mostra «Controlla il numero di tracciamento» finché un servizio di tracciamento trova il pacco.

**Il link sulla pagina del pacco.** «Apri il sito Ozon» apre il numero su tracking.ozon.ru. Se ha risposto un servizio di tracciamento, il link apre quel servizio, ad esempio «Apri il sito ParcelsApp».

**Un pacco di un altro corriere.** Peek salva il codice Ozon senza trattini. Finché il portale di Ozon non ha mostrato il pacco, un controllo senza esito interroga nei primi 30 giorni, prima dei servizi di tracciamento, alcuni corrieri con la stessa lunghezza (come Ukrposhta e Delhivery per 13 cifre, o Hermes Germany e BRT per 15). Se uno di essi mostra il pacco in movimento, Peek lo sposta lì e segnala «Cambio automatico da Ozon».

**Consegna con un altro corriere.** Se un servizio di tracciamento nomina un altro corriere con il relativo numero e Peek ne legge il tracciamento diretto, Peek interroga anche quello; appena registra una scansione recente almeno quanto quella del servizio, Peek prosegue lì.

**Controlli e notifiche.** Finché tieni il pacco aperto sullo schermo o nel giorno della consegna con le notifiche attive e il portale di Ozon risponde, Peek controlla fino a ogni 10 minuti; altrimenti ogni ora. Quando il pacco è seguito da un servizio di tracciamento, Peek controlla ogni ora, fino a ogni 15 minuti il giorno della consegna finché lo tieni aperto o hai le notifiche attive, e ogni 3 ore dopo un giorno senza novità. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Per un punto di ritiro scegli «Passaggi importanti»: «Solo la consegna» non avvisa quando il pacco è pronto per il ritiro. Peek non può contattare Ozon o il venditore al posto tuo.

## Domande sul tracking Ozon

### Com'è fatto un codice di tracking Ozon?

Peek associa a Ozon due formati, entrambi con trattini: tre gruppi di cifre (8 o 10 cifre, poi 4, poi 1, come `48291057-3628-1`) e codici che iniziano con `PL-`, come `PL-482910-57362`. Digita i trattini: senza, Peek non può riconoscerlo da solo come Ozon.

### Perché Peek non riconosce il mio codice Ozon?

Probabilmente lo hai inserito senza trattini o con spazi. Riscrivilo come `48291057-3628-1` con le tue cifre, oppure premi «Cambia» e seleziona Ozon.

### Cosa significa «Заказ в пункте выдачи»?

Il tuo ordine è al punto di ritiro e puoi andare a ritirarlo. Una volta ritirato, il tracciamento di Ozon mostra `Заказ получен в пункте выдачи.`

### Posso tracciare un ordine Ozon in italiano?

Sì. Peek mostra lo stato generale in italiano (ad esempio «In transito») e la cronologia nelle parole russe di Ozon; le tabelle sopra spiegano ogni passaggio.

:::sources
- [Universal Parcel Scraper: Ozon](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ozon-logistics) – nomi, Paesi serviti (`RU`, `BY`, `KZ`), formati con trattini, link tracking.ozon.ru e rocket.ozon.ru, pagina di aiuto `docs.ozon.ru/common/dostavka/`, campi letti e stati segnaposto esclusi (`FantomDelivery` e stati fantasma di frontiera), tappe russe e relative fasi, chiamata del corriere, ritiro in punto o ufficio postale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – corrieri che condividono 13 e 15 cifre, numeri Amazon (`PL-` + 10 cifre), ordine dei servizi di tracciamento, corrieri interrogati nei primi 30 giorni, passaggi indicati da un servizio di tracciamento
:::
