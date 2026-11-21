---
title: Tracciamento Huaya Express: codici HYYT…GZ e stati
description: Huaya Express (华亚运通) spedisce ordini online da Canton. Come leggere un codice HYYT…GZ, cosa indicano le scansioni e a chi chiedere il corriere locale.
slug: tracciamento-huaya-express
published: 2026-11-21
updated: 2026-11-21
---

Huaya Express (`华亚运通`) è il marchio nel logo di `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), uno spedizioniere (*freight forwarder*) di Canton (Guangzhou), nella Cina continentale, che spedisce pacchi all'estero per negozi online. Una lettera di vettura Huaya inizia con `HYYT` e finisce con `GZ`. Incollala nel riquadro qui sopra oppure segui il link `物流跟踪` (tracciamento logistico) nel menu in alto di `hyytgz.com` verso il tracker di Huaya. La cronologia di Huaya termina quando affida il collo a un corriere nel tuo Paese, o dopo alcune scansioni di quel corriere (negli Stati Uniti talvolta fino alla consegna), senza mai indicarne il nome — tranne su una linea via [DHL](carrier:dhl) per la Germania, dove copia anche le scansioni DHL, in una riga svedese che cita Early Bird e su una linea per il Messico, dove copia le scansioni di J&T Express.

## Numero di tracciamento Huaya Express: com'è fatto

Huaya non pubblica un formato dei codici. Lo schema riconosciuto da Peek è formato da `HYYT`, dodici cifre e `GZ` (esempi inventati):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `HYYT482910573617GZ` | `HYYT`, dodici cifre e `GZ`: la forma di una lettera di vettura Huaya Express. |
| `HYYT48291057317GZ` | Undici cifre: copiando il codice è saltata una cifra. Ricopialo dal messaggio del venditore. |
| `RR482910576CN` | Codice postale che termina in `CN`: si traccia con [China Post](carrier:china-post), non con Huaya. |

Peek legge il codice con o senza spazi, in maiuscolo o in minuscolo.

**Dove trovare il codice.** Huaya lavora per i venditori: la home page cita negozi Shopify, Vova, DHgate (`敦煌`) e venditori su Facebook. Il codice ti viene quindi inviato dal negozio da cui hai acquistato; se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number). Il tracker di Huaya accetta anche il riferimento d'ordine del negozio (`参考号`), digitato esattamente com'è scritto, trattini compresi.

**Un link di tracciamento con indirizzo numerico.** Il tracker proprio di Huaya non si trova su `hyytgz.com`, ma su un indirizzo numerico via semplice HTTP (`trackIndex.htm` con `documentCode=` e il tuo codice): puoi incollare direttamente quel link in Peek. Il link `物流跟踪` nel piè di pagina del sito apre invece il servizio di tracciamento 17TRACK.

## Stati del tracciamento Huaya Express spiegati

Il tracker di Huaya ha una versione cinese e una inglese. Le righe note a Peek sono per lo più in inglese, tranne la presa in carico in magazzino e alcune tappe di aeroporto, volo, dogana e consegna al partner che possono comparire in cinese, oltre a una riga in svedese per la Svezia. Nell'ordine consueto del viaggio:

| Huaya scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipping Label Created` | Esiste l'etichetta del corriere che consegnerà nel tuo Paese: solo dati dell'ordine, Huaya non ha ancora il pacco. | «Etichetta creata» |
| `Shipment not yet received or processed` | Riga del corriere finale trasmessa prima dell'arrivo al punto di raccolta di Huaya: anche qui solo dati d'ordine. | «Etichetta creata» |
| `Package received & scanned at warehouse`, `到达收货点` | Huaya ha preso in carico il collo nel suo magazzino di Canton, nella Cina continentale: prima scansione nelle sue mani (`到达收货点` significa arrivato al punto di ricezione). | «Preso in carico» |
| `Package departed from the warehouse` | Il pacco ha lasciato il magazzino di Canton. | «In transito» |
| `货物发往机场途中`, `货物到达机场` | In viaggio verso l'aeroporto e arrivo all'aeroporto nella Cina continentale, prima dello sdoganamento all'esportazione. | «In transito» |
| `Customs export clearance completed`, `国内清关完成` | Sdoganamento all'esportazione concluso nella Cina continentale; il controllo doganale d'importazione nel tuo Paese avverrà dopo l'atterraggio. | «In transito» |
| `安排航班中`, `Package handed over to airline` | Prenotazione del volo in corso e consegna del collo alla compagnia aerea. | «In transito» |
| `Shipment Announced` | Huaya ha preannunciato la spedizione al corriere del tuo Paese, che non l'ha ancora ricevuta. | «In transito» |
| `Package in transit to destination country`, `航班起飞，预计航班落地时间(…)` | In volo verso il Paese di destinazione (la dicitura cinese riporta tra parentesi il giorno previsto di atterraggio). | «In transito» |
| `Package arrived at destination country` | Arrivato nel Paese di destinazione. | «In transito» |
| `航班落地，清关中` | Volo atterrato, sdoganamento d'importazione in corso nel Paese d'arrivo. | «In dogana» |
| `Customs import clearance completed, awaiting pickup by local carrier`, `清关完成` | La dogana del tuo Paese ha svincolato il pacco; il corriere locale deve ancora ritirarlo. | «In transito» |
| `It has been handed over to the service provider.`, `已交仓`, `Preparing for delivery` | Affidato al corriere locale (o al suo magazzino) o in preparazione alla consegna. Huaya non indica né il corriere né il suo codice; oltre questo punto compaiono solo scansioni di un partner statunitense (`Arrived at Origin Hub` vicino a Chicago O'Hare, `Departed Origin Hub`, `Arrived at Destination Hub`, `Departed Destination Hub`, `Arrived At Warehouse`, poi `Out for Delivery` come «In consegna» e `Delivered` come «Consegnato») oppure, verso la Germania, le scansioni copiate da [DHL](carrier:dhl), oppure, verso il Messico, le scansioni copiate da J&T Express. Peek conosce anche altre righe di consegna in inglese di cui nessuna fonte indica il Paese (`Arrived at the regional hub`, `Departed from the regional hub`, `Arrived at the sorting center`, `Departed from the sorting center`, `Arrived in your region`, `Out for delivery with the courier` come «In consegna»). | «In transito» |
| `Din leverans ?r bokad med Early Bird.` | Svedese per «La tua consegna è prenotata con Early Bird» (il tracker scrive la `ä` di `är` come `?`): in Svezia consegna Early Bird. | «In transito» |

Gli orari di Huaya non indicano il fuso: seguono l'ora di Pechino (`UTC+8`), tranne le scansioni copiate da DHL e quelle statunitensi localizzate in una città USA, che mantengono l'ora locale del posto della scansione, e quelle di J&T Express copiate verso il Messico, all'ora di Città del Messico. Per un numero sconosciuto, la colonna `参考号` (riferimento) ripete il codice lasciando vuoto il resto della riga.

## Chi consegna i pacchi Huaya Express?

Escluse le scansioni [DHL](carrier:dhl) copiate sulla direttrice tedesca, la riga svedese di Early Bird e le scansioni di J&T Express copiate su una linea per il Messico, il tracciamento di Huaya non riporta né il corriere incaricato della consegna né un secondo codice. Nemmeno il sito di Huaya chiarisce il punto, perché le schede per i venditori si contraddicono: la pagina della linea Regno Unito (`ywfw.php?id=21`) chiude con testo statunitense su USPS e quella della linea Australia (`ywfw.php?id=27`) fa volare i pacchi negli USA per USPS. Consegna il corriere scelto tramite il venditore: chiedi al negozio il nome del corriere locale e il relativo numero di tracciamento. Per eventuali oneri doganali, consulta [pacchi fermi in dogana](guide:customs).

## Pacco Huaya Express bloccato o non ancora arrivato

**Fermo su `It has been handed over to the service provider.`.** Spesso il tracker di Huaya non aggiunge altro perché il pacco prosegue con il numero proprio del corriere locale. Chiedi al venditore quale corriere consegna nel tuo Paese e il suo codice per seguirlo lì ([tracciare pacchi dalla Cina](guide:tracking-from-china)).

**In attesa dopo lo svincolo doganale.** La dogana d'importazione ha terminato i controlli e il collo attende il ritiro del corriere locale. Se passano diversi giorni senza la presa in carico, avvisa il venditore ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).

**Nessun risultato.** Poiché per un codice sconosciuto Huaya restituisce una riga vuota con il riferimento ripetuto, Peek la interpreta come spedizione registrata senza ancora scansioni e mostra «In attesa di Huaya Express» senza segnalare errori di formato (per una ricerca senza account continua a controllare nelle prime 6 ore, poi quando apri il link e, fino a 30 giorni dalla ricerca, finché le notifiche restano attive). Ricontrolla tu stesso i caratteri rispetto al messaggio del negozio (`HYYT`, dodici cifre, `GZ`) e chiedi conferma al venditore.

**In ritardo, smarrito o restituito.** Le condizioni di rimborso sul sito di Huaya sono scritte per i negozi mittenti, non per chi riceve. Chiedi al venditore una nuova spedizione o un rimborso e, se non risponde, apri una contestazione sul marketplace ([pacco smarrito](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender), [consegnato ma non ricevuto](guide:delivered-not-received)).

## Contatti e assistenza clienti Huaya Express

Recapiti aziendali elencati solo in cinese su `hyytgz.com`:

- **Telefono**: `18039073131`, indicato come linea di servizio aziendale (`公司服务热线`). È un cellulare della Cina continentale (aggiungi `+86` dall'estero); non sono indicati orari né lingue.
- **Email**: `2065332407@qq.com`, casella QQ Mail etichettata come email aziendale (`公司邮箱`).
- **Modulo**: la pagina `投诉建议` (`message.php`, reclami e suggerimenti) richiede nome, cellulare, email e messaggio.
- **Sede**: distretto di Baiyun, Canton (`广州市白云区`), Cina continentale.

Le pagine `关于我们` (`about.php`) e `服务网点` (`outlets.php`) contengono solo il titolo e nessuna sezione è pensata per i destinatari.

**Attenzione agli SMS truffa.** Il sito aziendale di Huaya Express è `hyytgz.com`. Prima di aprire link ricevuti via SMS o email, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts). Per qualsiasi problema con l'ordine, contatta prima il negozio.

## Tracciare un pacco Huaya Express con Peek

Incolla un codice `HYYT…GZ` o un link del tracker con `documentCode=` e [Peek](/) lo assegna subito a Huaya Express («Corriere rilevato»), l'unico corriere del catalogo con quel formato. Per usare un riferimento d'ordine del negozio (tra oltre 3.500 corrieri supportati), inseriscilo e scegli Huaya Express toccando «Cambia» (lo trovi anche cercando `华亚运通` o `GZHY`, oppure con «Cambia corriere» su un pacco già salvato): Peek mostra allora anche il codice Huaya associato, ma rimuove i trattini — se il riferimento contiene trattini, cercalo sul tracker di Huaya.

**Scansioni, fuso orario e tappe scritte in anticipo.** Peek legge il tracker di Huaya, mostra ogni passaggio con luogo e Paese di destinazione, scarta i dati personali (nomi, indirizzi, CAP, firme, foto, peso) e non mostra una data di consegna prevista (Huaya non la fornisce). Converte gli orari da Pechino (`UTC+8`) — o dall'ora locale del luogo di scansione per le righe copiate da DHL e quelle in città statunitensi, e dall'ora di Città del Messico per quelle di J&T Express — al fuso del tuo dispositivo («Orari nel tuo fuso orario: …»). Se una riga porta un'ora futura a Pechino, Peek attende quell'ora. Trattiene inoltre le righe registrate più di 12 ore prima dell'orario indicato, o più di 6 ore prima nello stesso secondo di una tale riga (previsioni, non scansioni): le mostra in grigio, separate dalle scansioni, come tappe che nulla conferma, finché il pacco non viene consegnato o rispedito al mittente. Queste righe non contano mai come una scansione, non cambiano lo stato e non inviano notifiche.

**Nessun passaggio automatico e avvisi.** Non ricevendo da Huaya né corriere finale né secondo codice utilizzabile, Peek resta sul tracker di Huaya; aggiungi come secondo pacco il codice del corriere locale fornito dal venditore. Peek controlla fino a ogni 10 minuti a pacco aperto o nel giorno di consegna con notifiche attive e fino a ogni 2 minuti dalle 6:00 locali nel giorno «In consegna» (altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni fermi, ogni ora). Delle righe proprie di Huaya, solo `航班落地，清关中` vale come «In dogana» per «Passaggi importanti», mentre «Solo la consegna» scatta unicamente sulle consegne copiate da DHL, da J&T Express o dal partner USA: scegli «Ogni scansione» o «Passaggi importanti». Peek non può contattare Huaya o il venditore per te.

## Domande frequenti sul tracciamento Huaya Express

### Che cos'è Huaya Express (`华亚运通`)?

È il marchio di `华亚运通国际货运代理有限公司` (*Huaya Yuntong International Freight Forwarding*), uno spedizioniere di Canton (Cina continentale) per negozi online che dichiara di utilizzare soprattutto [China Post](carrier:china-post), linee proprie sdoganate tasse incluse verso USA ed Europa, linee per merci sensibili e i quattro grandi corrieri espressi. Le sue lettere di vettura hanno la forma `HYYT`, dodici cifre e `GZ`.

### Perché il tracciamento si ferma a `It has been handed over to the service provider.`?

Perché lì termina la tratta seguita da Huaya: un corriere del tuo Paese ha preso il collo con un codice proprio non indicato da Huaya. Il venditore può comunicarti nome del corriere e numero.

### Cosa sono le tappe in grigio in Peek?

Sono passaggi inseriti a sistema oltre 12 ore prima dell'orario che riportano (o oltre 6 ore prima nello stesso secondo): previsioni precompilate, non scansioni reali, anche quando parlano di consegna.

### Chi devo contattare se un pacco Huaya Express non arriva?

Il negozio da cui hai comprato: è il cliente contrattuale di Huaya e le regole di reclamo sono riservate ai mittenti. Huaya pubblica recapiti soltanto in cinese.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (home)](http://hyytgz.com/) – ragione sociale e logo, piattaforme servite, servizi principali, link 物流跟踪 (tracker proprio e 17TRACK), linea 18039073131, email 2065332407@qq.com e sede a Baiyun (Canton)
- [Huaya Express: 欧洲双清包税小包 (linee Europa e Regno Unito)](http://hyytgz.com/ywfw.php?id=21) – linee Regno Unito con testo USA che termina con USPS e condizioni per i mittenti
- [Huaya Express: 澳大利亚专线小包 (linea Australia)](http://hyytgz.com/ywfw.php?id=27) – linea Australia instradata negli USA per USPS e condizioni per i mittenti
- [Huaya Express: 投诉建议 (reclami e suggerimenti)](http://hyytgz.com/message.php) – campi del modulo (nome, cellulare, email, messaggio)
- [Huaya Express: 关于我们 (chi siamo)](http://hyytgz.com/about.php) – pagina priva di testo
- [Huaya Express: 服务网点 (filiali)](http://hyytgz.com/outlets.php) – pagina priva di testo
- [Universal Parcel Scraper: Huaya Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/huaya-express) – formato HYYT…GZ, alias, link trackIndex.htm, stati inglesi/cinesi/svedesi, scansioni DHL e USA, fusi e righe scritte in anticipo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento nel catalogo, ricerca per riferimento e visualizzazione in Peek
:::
