---
title: 4PX traccia pacco: numero, stati e chi consegna
description: Traccia un pacco 4PX dalla Cina: com'è fatto il numero, cosa vogliono dire gli stati in inglese, chi consegna in Italia e a chi chiedere se si ferma.
slug: 4px-traccia-pacco
published: 2026-10-11
updated: 2026-10-26
---

4PX (递四方速递, 4PX Express Co., Ltd.) è un'azienda di logistica di Shenzhen, in Cina, che spedisce all'estero i pacchi dei venditori online e li affida a un corriere del tuo Paese per l'ultimo tratto. Puoi tracciare il numero sul sito di 4PX, [track.4px.com](https://track.4px.com/), oppure nel box qui sopra. Il numero te lo dà il negozio o il marketplace dove hai comprato, nei dettagli di spedizione dell'ordine.

## Codice spedizione 4PX: com'è fatto

4PX non pubblica numeri di esempio; il suo sito accetta da 5 a 50 lettere e cifre, e fino a 100 numeri insieme, uno per riga. Le forme più comuni, con cifre inventate:

| Numero | Di chi è |
| --- | --- |
| `4PX1234567890123CN` | 4PX, 13 cifre e CN: il numero di 4PX. |
| `LP1234567890123CN` | LP, 13 cifre e CN: probabilmente 4PX. |
| `LP12345678901234` | LP e 14 cifre, senza CN: è il riferimento logistico di AliExpress, da tracciare come [Cainiao](carrier:cainiao). |
| `RR000000005CN` | Un numero postale che finisce con la sigla di un Paese: qui China Post. |
| `YT1234567890123456` | Non è 4PX ma [YunExpress](carrier:yunexpress). |

Peek accetta anche minuscole e spazi. La pagina dei risultati di 4PX può mostrare due numeri: il «4PX Order No.» e un «Tracking No.», che è il numero del corriere che consegna nel tuo Paese. A volte il negozio ti dà quel numero locale al posto di quello di 4PX; il sito di 4PX risponde anche per alcuni di questi.

## 4PX tracking: cosa significano gli stati

Il sito di tracciamento di 4PX esiste in cinese, inglese, tedesco, giapponese, francese, spagnolo e portoghese, non in italiano: con un browser in italiano si apre in inglese. Le righe di scansione sono quasi sempre in inglese, e il sito può tradurle solo in inglese o in cinese semplificato, oppure mostrare il testo originale. Ecco quelle che vedi più spesso, nell'ordine in cui le incontra un pacco:

| 4PX scrive | Cosa vuol dire per te |
| --- | --- |
| `Parcel information received` | Il venditore ha creato la spedizione, ma 4PX non ha ancora il pacco. |
| `4px received shipment.` | Il pacco è arrivato al magazzino di 4PX. |
| `Shipment arrived at facility and measured.` | Misurato in un centro di 4PX. |
| `Hand over to airline.` | Consegnato alla compagnia aerea per il volo. |
| `Arrival to the destination airport` | È atterrato nel Paese di destinazione. |
| `Customs clearance agent received the shipment in destnation country.` | È in dogana nel tuo Paese (il refuso è di 4PX). |
| `Released from customs: customs cleared.` | Sdoganato: riparte. |
| `In transit, it's progressing through post network` | Ce l'ha il corriere locale. |
| `Out for delivery` | In consegna: sta arrivando da te. |
| `Delivery attempt unsuccessful` | Il corriere non è riuscito a consegnarlo. |
| `Delivered, in/at mailbox` | Consegnato nella tua cassetta della posta. |

`Shipping information received`, `Waybill generated`, `Shipping label created` e `Advance notice - package information received.` vogliono dire la stessa cosa della prima riga. `Shipping label created, usps awaiting item` riguarda i pacchi per gli Stati Uniti: l'etichetta USPS esiste, ma USPS non ha ancora scansionato il pacco.

Oltre alle scansioni, 4PX mette ogni pacco sotto un'etichetta riassuntiva, sempre in inglese per chi legge in italiano. `receive forecast` è il preavviso: 4PX ha i dati della spedizione ma non ancora il pacco. `In Transit` copre tutto, dal magazzino all'ultimo tratto. `Arrived for Pickup` vuol dire che il pacco ti aspetta in un punto di consegna. `Exception` raccoglie destinatario irraggiungibile, indirizzo sbagliato, rifiuto, danni, smarrimento, pacco non ritirato in tempo, ritardi, un primo tentativo fallito o problemi in dogana. `Suspected return` vuol dire che il corriere potrebbe rimandarlo indietro. `Trajectory stalled` arriva dopo 15 giorni senza aggiornamenti, e `No Data Found` significa che 4PX non trova niente con quel numero: forse è scritto male, o non ci sono ancora aggiornamenti.

## 4PX chi consegna in Italia: consegna e ritiro

4PX non consegna a casa tua da sola. Il suo sito dice che il pacco «is being delivered to the delivery company of the destination country», cioè passa al corriere del Paese di destinazione. Nessuna pagina di 4PX dice quale corriere consegna in Italia o in Svizzera. Lo scopri sulla pagina dei risultati di 4PX: dopo il passaggio, il riquadro «Terminal Service Provider» mostra i contatti e il sito ufficiale di quel corriere, e il «Tracking No.» è il numero da usare da lui.

Tentativi, avvisi, nuove consegne e giorni di giacenza sono regole del corriere locale, non di 4PX, che non ne pubblica. La nota di `Arrived for Pickup` ti chiede di ritirare il pacco «as soon as possible», per evitare che il corriere lo rimandi indietro. Dopo `Delivery attempt unsuccessful`, usa il «Tracking No.» e i contatti di quel riquadro per chiedere una nuova consegna o dove ritirarlo.

## Tracking 4PX fermo, pacco perso o tornato indietro

**Ancora nessun tracking.** Il sito di 4PX scrive: «There is currently no trace of your package. Please wait patiently or contact the merchant to inquire about the delivery status.» In pratica: aspetta, oppure chiedi al venditore. Anche `No Data Found` può voler dire un numero sbagliato.

**Fermo da giorni.** Dopo 15 giorni senza scansioni 4PX mette il pacco sotto `Trajectory stalled` e dice che potrebbe essere perso o in ritardo, oppure che un corriere non ha registrato le scansioni. Prima controlla il tracking del corriere locale con il «Tracking No.». Più dettagli in [perché il tracking non si aggiorna](guide:tracking-not-updating); per dogana e tempi dei pacchi dalla Cina, vedi [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Consegnato ma non c'è.** La nota di `Successful delivery` ti chiede di contattare il servizio clienti per una conferma. Comincia dal corriere locale, che ha fatto la consegna: vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o tornato indietro.** 4PX non pubblica una procedura di reclamo né scadenze per chi riceve, e i suoi siti presentano resi e reclami come servizi per i venditori. Il contratto con 4PX ce l'ha il negozio o il marketplace: chiedi a loro un rimborso o un nuovo invio, e al corriere locale tutto ciò che riguarda la consegna.

## 4PX contatti: servizio clienti e numero di telefono

All'11 ottobre 2026 il sito di tracciamento di 4PX indica un solo contatto per tutti, in Help, Contact Us:

- **Telefono**: 0755-23508000, un numero di Shenzhen, in Cina (dall'Italia o dalla Svizzera +86 755 2350 8000). Non sono indicati orari né lingue.
- **E-mail**: track@4px.com.
- **Indirizzo**: 4PX Express Co., Ltd., No.12 Building, FuYong e-cool, BaoAn District, ShenZhen, GuangDong, China.

4PX non indica un numero o un ufficio per chi riceve in Italia o in Ticino. Per l'ordine è più rapido il negozio; per la consegna, il corriere locale del riquadro «Terminal Service Provider».

## Tracciare un pacco 4PX con Peek

[Peek](/) riconosce subito un numero `4PX…CN`. Per un numero LP che finisce con CN, mostra «verifica presso 4PX…» e poi «4PX ha questo pacco.» se 4PX lo conosce; altrimenti scegli tu il corriere con «Cambia». Un link di track.4px.com con `#/result/0/` e il numero viene letto come 4PX qualunque numero contenga, quindi anche con un «Tracking No.» locale a cui 4PX risponde. Gli ordini con più colli passano ai servizi di tracking universali.

Legge 4PX in inglese e mostra lo stato, la cronologia con il luogo di ogni scansione su una mappa, l'ora della consegna, il Paese di destinazione e il corriere locale con il suo numero. Quando quel corriere è tra quelli di Peek (USPS, per esempio), può seguire il pacco da lui appena mostra passi più recenti. `Out for delivery` e `Delivery attempt unsuccessful` compaiono con le parole di Peek, in italiano; le altre righe come le scrive 4PX. 4PX non dà una data di consegna prevista, né i dati del punto di ritiro, né il peso.

Gli orari delle scansioni mantengono i fusi orari di 4PX; una scansione senza fuso orario viene letta sull'ora del suo luogo. Il controllo avviene ogni 10 minuti di giorno, ogni 2 quando il pacco è in consegna e ogni ora di notte, e può mandarti notifiche: per ogni scansione, solo per i passaggi importanti o «Solo giorno di consegna». Non può contattare 4PX o il negozio al posto tuo.

## Domande sul tracking 4PX

### 4PX che corriere è?

È un'azienda cinese di logistica internazionale, fondata nel 2004 secondo 4PX stessa, con sede a Shenzhen. Porta il pacco dal venditore fino al tuo Paese e poi lo affida a un corriere locale per la consegna.

### Poste Italiane consegna i pacchi 4PX?

4PX non lo dice: nessuna sua pagina nomina il corriere che consegna in Italia. Lo vedi sulla pagina dei risultati di 4PX, nel riquadro «Terminal Service Provider», insieme al «Tracking No.» da usare con quel corriere.

### Il tracking 4PX è falso?

No: track.4px.com è il sito di tracciamento di 4PX, un'azienda reale che con Cainiao ha aperto anche un polo logistico a Dongguan. Se mostra `No Data Found`, 4PX dice che il numero può essere sbagliato o non ancora aggiornato: ricontrollalo, poi chiedi al negozio.

### Quali sono i tempi di consegna di 4PX?

4PX non pubblica tempi di consegna per chi riceve, quindi conta la stima che il negozio o il marketplace mostra per il tuo ordine. Se non si muove nulla per 15 giorni, 4PX considera il tracking fermo.

:::sources
- [4PX: Global Order Tracking](https://track.4px.com/) – regole sul numero, etichette riassuntive, lingue e traduzione, riquadro Terminal Service Provider
- [4PX: testi del sito di tracciamento](https://track.4px.com/js/index.6aecf0a2c58f0d7c56a3.js) – note delle etichette, passaggio al corriere locale, messaggio senza tracking, inglese per le altre lingue
- [4PX: Contact Us](https://track.4px.com/#/help/contact) – telefono, e-mail, indirizzo
- [4PX: Privacy Policy](https://track.4px.com/#/help/privacy) – 4PX Group, reclami
- [4PX: For Perfect X-border](https://www.4px.com/view/index.html) – azienda e servizi per i venditori
- [4PX: 退换货 (resi)](https://www.4px.com/view/return.html) – resi come servizio per i venditori
- [4PX: 递四方成都至伦敦全货机航线成功首航](https://www.4px.com/view/newsCenterContentfive.html) – fondata nel 2004
- [4PX: 菜鸟&递四方国际智慧物流园盛大开园](https://www.4px.com/view/newsCenterContentsix.html) – polo logistico di Cainiao e 4PX
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek
:::
