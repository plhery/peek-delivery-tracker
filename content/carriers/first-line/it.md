---
title: Tracciamento FIRST LINE: codici 1ST, stati e consegna
description: Traccia un pacco FIRST LINE (壹号专线) dalla Cina: formato del codice 1ST, significato degli stati, chi consegna negli USA e a chi rivolgersi.
slug: tracciamento-first-line
published: 2026-11-22
updated: 2026-11-22
---

FIRST LINE (壹号专线, Shenzhen First Line Supply Chain Co., Ltd.) è uno spedizioniere di Shenzhen che trasporta pacchi dalla Cina per venditori online, soprattutto verso gli Stati Uniti, affidandoli poi a un corriere locale per la consegna. Fa parte del gruppo 1ST (一代国际集团), i cui spedizionieri emettono lettere di vettura che iniziano con `1ST`. Traccia il pacco nel riquadro in home page su firstline56.com, che apre la pagina 追踪单号 (traccia un numero), oppure nel campo qui sopra. Il codice arriva dal negozio nei dettagli di spedizione dell'ordine.

## Numero di tracciamento FIRST LINE: com'è fatto

FIRST LINE non pubblica un formato ufficiale. Le sue lettere di vettura, come quelle del resto del gruppo 1ST, sono `1ST` seguito da 11 cifre (esempi inventati):

| Numero | Che cos'è |
| --- | --- |
| `1ST48291736548` | `1ST` e 11 cifre: una lettera di vettura del gruppo 1ST, tracciata sul sito di FIRST LINE. |
| `9400 1847 2936 5482 9173 64` | 22 cifre che iniziano con `94`: un'etichetta [USPS](carrier:usps) per la consegna negli Stati Uniti. |

**Stesso prefisso, altri spedizionieri.** Le quattro società del gruppo — 1st Agent International (一代国际), FIRST LINE, Ferry-Boat (摆渡一下) e Universal Best Union (万国优联) — stampano tutte codici `1ST`. Un numero `1ST` sconosciuto al tracker di FIRST LINE può appartenere a una delle altre tre.

**Dove trovarlo.** Nei dettagli di spedizione del negozio, che possono indicare USPS, China Post o FIRST LINE: FIRST LINE consente ai venditori di dichiarare qualunque di essi sul marketplace. Il gruppo indica ai venditori che il codice di ultimo miglio (ad esempio USPS) arriva 6–9 giorni dopo l'ingresso del pacco in magazzino, quindi il negozio potrebbe fornirti direttamente quello. Approfondisci in [come trovare un numero di tracciamento](guide:find-tracking-number).

## Stati di tracciamento FIRST LINE spiegati

Il tracker di FIRST LINE è in cinese, ma le scansioni sono in inglese. Le diciture proprie di FIRST LINE nell'ordine abituale:

| FIRST LINE scrive | Cosa significa per te |
| --- | --- |
| `Forecast has been received` | Il venditore ha inviato i dati dell'ordine. FIRST LINE non ha ancora il pacco. `Parcel information received` significa lo stesso. |
| `Order received` | Il corriere finale, come UniUni, ha i dati dell'ordine ma non ancora il pacco. |
| `Barcode scanning` | Prima scansione nel magazzino di FIRST LINE a Shenzhen. |
| `Shipment arrived` | Anche questa è la prima scansione a Shenzhen, non un arrivo nel tuo paese. |
| `Packaged` | Imballato per il viaggio all'estero. |
| `Leaving Scan` | Ha lasciato il magazzino. |
| `Export customs declaration completed` | Dichiarazione doganale di esportazione dalla Cina continentale completata a Shenzhen prima del volo. Il pacco non è fermo in dogana. |
| `Depart from facility to service provider` | In transito da FIRST LINE all'operatore successivo. |
| `Hand over to airline` | Consegnato alla compagnia aerea all'aeroporto di Hong Kong dopo la dichiarazione di esportazione. |
| `Next stop destination airport` | In volo verso l'aeroporto di destinazione. La località mostra ancora l'aeroporto di Hong Kong. |
| `Arrived at destination airport` | Il volo è atterrato. Segue lo sdoganamento all'importazione. |
| `The package is being submitted for customs clearance` | Sdoganamento all'importazione dopo il volo. Peek mostra «In dogana». |
| `The package has been cleared through customs` | Sdoganato dalla dogana. Peek torna a mostrare «In transito». |
| `Customs broker picked it up` | Lo spedizioniere doganale lo ha ritirato dopo il rilascio per affidarlo alla consegna. Non è più fermo in dogana. |
| `The package has been transferred to the service provider` | Affidato al corriere che effettua la consegna dopo lo spedizioniere doganale. |

Negli Stati Uniti seguono le scansioni del corriere finale con le sue diciture fino alla consegna: USPS, [GOFO](carrier:gofo) o UniUni nelle cronologie osservate. Il giro di consegna di GOFO appare come `Out for Delivery` (in Peek «In consegna»); la consegna recita `Delivered,` seguita dal punto di rilascio (come `Door/Yard`) e `For delivery issues & tracking support, contact GOFO at` con un recapito, mostrato da Peek come «Consegnato». Il tracker di FIRST LINE non nomina mai il corriere finale, quindi le diciture forniscono l'indizio.

Ogni orario segue l'ora locale del luogo di scansione (Shenzhen o Hong Kong, poi Stati Uniti) senza fuso orario, quindi una scansione statunitense può riportare un'ora precedente all'ultima in Cina. Un numero sconosciuto restituisce `未找到此单号信息` («nessuna informazione trovata per questo numero»).

## Chi consegna i pacchi FIRST LINE

FIRST LINE trasporta il pacco fuori dalla Cina, ne cura lo sdoganamento all'arrivo e lo affida a un corriere locale:

- **Stati Uniti, via aerea:** consegna USPS o un corriere commerciale locale; la pagina di FIRST LINE cita USPS e DHL eCommerce. FIRST LINE indica 7–16 giorni porta a porta.
- **Stati Uniti, via mare:** sulle navi veloci Matson, poi [FedEx](carrier:fedex) o USPS, in 19–31 giorni porta a porta.
- **Canada:** via aerea a Vancouver o Toronto, poi consegna con Canada Post, che secondo FIRST LINE richiede 2–7 giorni dallo sdoganamento.
- **Europa, via aerea:** volato a Praga e sdoganato in Cechia, poi consegnato da [UPS](carrier:ups) a privati, aziende e magazzini Amazon in oltre 20 paesi, fra cui Germania, Francia, Italia, Spagna e Regno Unito. Ogni collo è fatturato per almeno 12 kg e le imposte sono incluse in quanto paga il venditore.

Le linee USA servono solo i 48 stati contigui, esclusi Alaska, Hawaii, Porto Rico, Isole Vergini USA, Guam, caselle postali, indirizzi militari e Amazon. FIRST LINE vende le linee USA e Canada rese sdoganate (DDP): gli oneri d'importazione sono pagati dal venditore. Maggiori dettagli in [pacchi fermi in dogana](guide:customs).

FIRST LINE non pubblica regole su tentativi di consegna, avvisi o punti di ritiro: valgono quelle del corriere finale. Il tracker non mostra il codice dell'ultimo miglio, quindi per riprogrammare la consegna chiedi al venditore il numero del corriere locale.

## Pacco FIRST LINE in ritardo o fermo

**Solo `Forecast has been received`.** FIRST LINE non ha ancora scansionato il pacco. Il gruppo dice ai venditori che i pacchi affidati prima delle 17:00 compaiono online in giornata. Se non segue nulla per giorni, chiedi al venditore se ha spedito.

**Nessun aggiornamento dopo l'esportazione.** Seguono il volo e lo sdoganamento, e la cronologia può restare ferma per giorni. Se supera nettamente i tempi indicati, contatta il venditore. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato ma non c'è.** Se il corriere finale indica una consegna che non hai ricevuto, le condizioni di FIRST LINE per Stati Uniti e Canada prevedono assistenza nella verifica ma nessun indennizzo. Parti dal corriere locale e dal venditore: vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o danneggiato.** I reclami spettano al venditore (per Stati Uniti e Canada entro 60 giorni dalla spedizione), e FIRST LINE non rimborsa danni in transito né ritardi. Chiedi rimborso o sostituzione al venditore o marketplace. In caso di danni, le FAQ del gruppo chiedono al destinatario di segnalarli prima localmente conservando riferimento della pratica e foto dell'imballo. Vedi [pacco smarrito](guide:lost-parcel).

**Restituito.** Sulle linee statunitensi un pacco non consegnabile non torna in Cina: finisce nel magazzino estero di FIRST LINE e, se il venditore non paga una nuova consegna entro 14 giorni, viene distrutto. Se compare un reso, contatta subito il venditore. Approfondisci in [restituito al mittente](guide:returned-to-sender).

## Contatti e servizio clienti FIRST LINE

Sul sito cinese, la pagina contatti di FIRST LINE elenca un centro assistenza clienti (客维中心) e la sede: 3rd floor, Building 36, Zone 5, Cuigang Industrial Park, Huaide, Fuyong, Bao'an District, Shenzhen, Cina. Non indica orari né lingue. Il sito del gruppo 1ST dispone di un modulo reclami (投诉建议) con campi per telefono, e-mail e messaggio.

Questi canali servono i venditori, clienti di FIRST LINE; non viene offerto supporto pubblico ai destinatari. Rivolgiti al venditore per l'ordine e al corriere locale per la consegna.

## Tracciare un pacco FIRST LINE con Peek

[Peek](/) riconosce subito come FIRST LINE un codice `1ST` con 11 cifre, in maiuscolo o minuscolo, con o senza spazi. Peek non legge link FIRST LINE: incolla direttamente il numero.

Peek legge il tracker di FIRST LINE e mostra stato, cronologia (fino a 100 scansioni), località di ogni scansione senza CAP e orario letto sull'ora locale del luogo e mostrato nel fuso del tuo dispositivo; su peektracker.com e nelle app per iPhone e Android la cronologia termina con «Orari nel tuo fuso orario:» e il suo nome. Le righe restano nel testo di FIRST LINE, incluse le scansioni inoltrate di USPS, GOFO o UniUni, tranne un `Delivered` esatto che appare nelle parole di Peek. Il tracker non fornisce paese di destinazione, corriere finale, numero locale né prova di consegna, quindi Peek non li mostra. L'unica stima deriva da una scansione USPS inoltrata «Out for Delivery, Expected Delivery …»: finché resta l'ultima, Peek mostra la fascia oraria di quel giorno se indicata. Non passa automaticamente a USPS, GOFO o UniUni: se il venditore ti dà il codice locale, aggiungilo come spedizione separata (Peek traccia oltre 4.500 corrieri).

Finché tieni il pacco aperto sullo schermo, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 ora locale nel giorno di uscita in consegna, finché risponde il tracker di FIRST LINE (tramite i soli servizi di tracciamento esterni: ogni ora, ogni 3 ore dopo una giornata senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive). Altrimenti, e dopo due giorni senza novità (12 ore finché mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare FIRST LINE né il venditore al posto tuo.

## Domande sul tracciamento FIRST LINE

### Che cos'è un numero di tracciamento 1ST?

È un numero di lettera di vettura degli spedizionieri del gruppo 1ST, tra cui FIRST LINE: `1ST` seguito da 11 cifre. Si traccia su firstline56.com o in Peek.

### Chi consegna i pacchi FIRST LINE negli Stati Uniti?

Un corriere statunitense: le pagine di FIRST LINE citano USPS, DHL eCommerce e FedEx, e le cronologie osservate sono state consegnate da USPS, GOFO o UniUni. Il tracker di FIRST LINE inoltra le loro scansioni senza nominarli.

### Perché il tracker di FIRST LINE non trova il mio codice 1ST?

Potrebbe essere digitato male, non ancora registrato oppure appartenere a un altro spedizioniere del gruppo 1ST. Controllalo e chiedi al venditore quale società gestisce la spedizione.

### Quanto impiega FIRST LINE a consegnare negli Stati Uniti?

FIRST LINE indica ai venditori 7–16 giorni porta a porta via aerea e 19–31 giorni via mare. Sono cifre commerciali, non una data per il tuo ordine.

:::sources
- [FIRST LINE: 壹号专线 (home)](http://www.firstline56.com/) – riquadro tracking, sito cinese, indirizzo a piè di pagina
- [FIRST LINE: 追踪单号 (tracking)](http://www.firstline56.com/track.php) – pagina tracking aperta dalla home
- [FIRST LINE: 公司简介 (chi siamo)](http://www.firstline56.com/aboutus.php) – ragione sociale, spedizioniere a Shenzhen, linee USA e Canada
- [FIRST LINE: 联系我们 (contatti)](http://www.firstline56.com/contact.php) – centro assistenza, indirizzo ufficio
- [FIRST LINE: 美国小包 (linea pacchi USA)](http://www.firstline56.com/business.php?id=4) – rotta, corrieri locali, nomi per marketplace, tempi e copertura, DDP, resi, reclami
- [FIRST LINE: 美国海运小包 (linea mare USA)](http://www.firstline56.com/business.php?id=5) – Matson, FedEx e USPS, 19–31 giorni
- [FIRST LINE: 加拿大小包 (linea Canada)](http://www.firstline56.com/business.php?id=6) – Vancouver o Toronto, Canada Post, tempi, DDP
- [FIRST LINE: 欧洲空派 (linea aerea Europa)](http://www.firstline56.com/yhzx.php?id=4) – Praga, UPS, paesi, minimo 12 kg, imposte incluse
- [1ST: Business](https://www.1st56.com/group/groupbusiness/en/) – le quattro società del gruppo, nome inglese di FIRST LINE
- [一代国际集团: 关于我们 (chi siamo)](https://www.1st56.com/group/aboutus/) – società del gruppo, modulo reclami
- [一代国际集团: 常见问题 (FAQ)](https://www.1st56.com/group/commonproblems/) – USA mercato principale, scansioni in giornata, codice locale dopo 6–9 giorni, reclami, verbali danni, copertura USA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/first-line) – formato, lettere di vettura condivise, stati, corrieri osservati e dati letti da Peek
:::
