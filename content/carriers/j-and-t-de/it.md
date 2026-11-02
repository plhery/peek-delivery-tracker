---
title: Tracking J&T Express in Germania: numero JTDE e stati
description: Tracking J&T in Germania: com'è fatto un numero JTDE, cosa vuol dire ogni riga del tracking e cosa fare se il pacco è in ritardo, smarrito o non arriva.
slug: jt-express-germania-tracking
published: 2026-11-11
updated: 2026-11-11
---

J&T Express Germany è la rete tedesca di J&T Express: consegna pacchi in Germania. Se un negozio ti ha spedito l'ordine con J&T, il numero di tracking inizia con `JTDE`: incollalo nel box qui sopra, oppure cercalo sul tracking di J&T, all'indirizzo www.jtexpress.de/tracking. Il numero ce l'ha il negozio che ha spedito l'ordine: lo trovi nell'e-mail di spedizione o nella pagina dell'ordine. Questa pagina non si basa sul sito di J&T Express Germany, ma su quello che il codice di Peek sa del tracking di J&T e sulla legge tedesca.

## Numero di tracking J&T Express Germany: com'è fatto

Il numero di spedizione di J&T Express Germany è `JTDE` seguito da 12 cifre: 16 caratteri in tutto. Peek non ne conosce altre forme. Con cifre inventate:

| Numero (inventato) | Cos'è |
| --- | --- |
| `JTDE000000000000` | `JTDE` e 12 cifre: un pacco J&T Express Germany. Peek riconosce subito J&T Express Germany. |
| `JTDE00000000000` | Una cifra in meno: non ha la forma di un numero J&T Express Germany, e Peek non riconosce nessun corriere. Controlla di non aver saltato una cifra. |

**Dove lo trovi.** Nell'e-mail di spedizione del negozio, nella pagina dell'ordine sul sito del negozio o del marketplace, o in qualsiasi messaggio sulla consegna. Se non lo trovi, leggi [dove trovare il codice di tracking](guide:find-tracking-number), oppure chiedilo al negozio.

**Numeri che si somigliano.** Le reti J&T degli altri Paesi hanno tracking e numeri propri: `JTUK` e 12 cifre è J&T Express UK, `JTAU` e 12 cifre J&T Express Australia, `JTE` e 12 cifre J&T Express Middle East, mentre `JT` con 13 cifre lo usa J&T nel Sud-est asiatico e in Cina (J&T Express China). Solo `JTDE` è della rete tedesca. Se Peek continua a mostrare «In attesa del corriere», confronta il numero con l'e-mail del negozio, o chiedine conferma al negozio.

## Stati della spedizione J&T Express Germany: cosa vogliono dire

Nel tracking di J&T ogni scansione ha solo data, ora e una frase: nessun luogo. Peek chiede le frasi in inglese e non le traduce, nemmeno se usi Peek in italiano. Queste sono le frasi che vedi, nell'ordine in cui compaiono lungo il viaggio del pacco:

| J&T scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T ha ritirato il pacco dal mittente. | «Preso in carico» |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Il pacco è arrivato in un centro di smistamento J&T. | «In transito» |
| `The shipment is dispatched from the transit center to the next destination` | Ha lasciato quel centro, diretto alla tappa successiva. | «In transito» |
| `Your package has been loaded and is on its way to you` | È sul furgone: in consegna. | «In consegna» |
| `Delivery failed because the address could not be found` | Il corriere non ha trovato il tuo indirizzo. | «Tentativo di consegna» |
| `Delivery failed, customer refused to accept` | Il destinatario ha rifiutato il pacco. | «Tentativo di consegna» |
| `Your package has been successfully delivered.` | Consegnato. J&T aggiunge il tipo di consegna (vedi sotto). | «Consegnato» |
| `Returns have begun` | Dopo una consegna non riuscita, il pacco sta tornando al mittente. | «Restituito al mittente» |
| `The shipment has been returned` | Il pacco è tornato al mittente. | «Restituito al mittente» |

Una frase che non è in tabella viene letta con le regole che Peek usa per tutti i corrieri. Se Peek non la riconosce nemmeno così, la mostra come l'ha scritta J&T e il pacco risulta «In transito», a meno che non sia già in consegna o in una fase successiva: in quel caso lo stato resta quello di prima.

La riga `Your package has been loaded and is on its way to you` può comparire più volte: J&T la scrive a ogni giro di consegna. La riga di avvenuta consegna prosegue con il tipo di consegna tra parentesi 【】 e si chiude con una frase sul servizio clienti. Peek toglie quella frase e tiene il tipo, senza parentesi, solo se è uno di questi sei:

- `Left with neighbor`: lasciato a un vicino;
- `Front Door Porch`: davanti alla porta di casa;
- `Front Desk Reception`: alla reception;
- `Garden Yard`: in giardino o in cortile;
- `Mailbox`: nella cassetta delle lettere;
- `Garage`: in garage.

Un pacco lasciato a un vicino, quindi, in Peek si legge `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Quale vicino?** Peek mostra solo che il pacco è stato lasciato a un vicino: niente nome e niente indirizzo. Toglie anche qualsiasi nome o numero di telefono che compaia in una riga di J&T.

## Consegna J&T in Germania: cosa ti dice il tracking

Nelle cronologie che il codice di Peek conosce, il corriere di J&T carica il pacco sul furgone per un giro di consegna e poi lo consegna, oppure scrive perché non ci è riuscito. Il codice di Peek non mostra nessun passaggio a un altro corriere: il tracking di J&T segue il pacco fino alla tua porta.

**In consegna.** `Your package has been loaded and is on its way to you` vuol dire che il pacco è partito per il giro di consegna. In quelle cronologie la riga compare la mattina, qualche ora prima della consegna. Il tracking di J&T non dà né una fascia oraria né un giorno di consegna previsto, quindi nemmeno Peek può mostrarli.

**Rifiutato o indirizzo non trovato.** Dopo `Delivery failed, customer refused to accept`, nelle cronologie che il codice di Peek conosce il corriere ha ricaricato il pacco per un altro giro la mattina dopo. Dopo `Delivery failed because the address could not be found`, controlla l'indirizzo dell'ordine. Se manca qualcosa, come il numero dell'appartamento o il tuo nome sul campanello, avvisa subito il negozio: ha spedito il pacco all'indirizzo che gli hai dato tu. Dopo una consegna non riuscita può comparire anche `Returns have begun`. Vedi [come cambiare l'indirizzo di consegna](guide:change-delivery-address) e [consegna mancata](guide:missed-delivery).

**Tentativi, dove lasciare il pacco, ritiro.** Il sito di J&T non si può usare come fonte per questa pagina, quindi qui non trovi quante volte J&T riprova, se puoi scegliere dove farti lasciare il pacco o per quanto tempo J&T tiene in giacenza un pacco che non è riuscito a consegnare. Tra le righe di J&T Express Germany che il codice di Peek conosce, nessuna manda il pacco in un punto di ritiro o in un locker. Chiedi al negozio, o guarda i messaggi che hai ricevuto sulla consegna.

## Pacco J&T in ritardo, sparito o rispedito

Il corriere l'ha scelto il negozio, quindi per un ordine online il primo a cui rivolgerti è il negozio. Per la legge tedesca, se compri da un'azienda come consumatore, il rischio che il pacco si perda o si danneggi durante il trasporto è a carico del venditore, a meno che non sia stato tu a incaricare il corriere senza che il venditore te lo avesse indicato prima (§ 475 BGB).

**Pacco fermo.** Se un pacco resta su «Preso in carico» o «In transito» per quattro giorni senza righe nuove, Peek lo segnala («Nessun aggiornamento da quattro giorni») e ti mostra il link «Chiedi aiuto a J&T Express Germany». Se poi non si muove ancora niente, chiedi al negozio. Vedi anche [tracking che non si aggiorna](guide:tracking-not-updating).

**Ancora nessun tracking.** Un numero che il tracking di J&T non conosce ancora appare in Peek come «In attesa del corriere». Spesso i negozi creano il numero prima di affidare il pacco al corriere. Se resta così per qualche giorno, verifica il numero con il negozio e chiedi se il pacco è partito davvero.

**Consegnato ma non ricevuto.** Leggi in Peek la riga di avvenuta consegna: se nomina un posto, come `Mailbox` o `Garage`, guarda lì; se dice `Left with neighbor`, chiedi ai vicini. Ancora niente? Avvisa il negozio, con il numero di tracking, e chiedi un rimborso o un nuovo invio. Vedi anche [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso, danneggiato o rispedito.** Chiedi al negozio un rimborso o una sostituzione, con il numero di tracking. Questa pagina non riporta regole di J&T sui reclami; vedi [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## J&T Express Germany: servizio clienti e contatti

Al 2 novembre 2026 questa pagina non riporta numeri di telefono, e-mail o chat di J&T Express Germany: Peek indica solo contatti letti sulle pagine del corriere stesso, e il sito di J&T non si può usare come fonte per questa pagina.

Parti dal negozio: è lui che ha spedito il pacco con J&T. Per un ordine su un marketplace, usa l'assistenza nella pagina dell'ordine. Il tracking di J&T è all'indirizzo www.jtexpress.de/tracking. Su un pacco che si è fermato, il link «Chiedi aiuto a J&T Express Germany» di Peek apre la pagina contatti di J&T.

## Seguire un pacco J&T Express Germany con Peek

[Peek](/) riconosce subito J&T Express Germany da un numero `JTDE` («Corriere rilevato»), scritto con o senza spazi, in maiuscolo o in minuscolo, e interroga il tracking di J&T con il solo numero: niente CAP e niente account. Funziona anche un link www.jtexpress.de/tracking con `?q=` e il numero; se dentro c'è un numero di un altro tipo, Peek lo legge come se avessi incollato solo quello. Se il tuo numero non è un `JTDE`, incollalo lo stesso: Peek segue più di 3.500 corrieri. Scegliere J&T Express Germany con «Cambia» per un numero di un'altra forma non serve: Peek ti avvisa che J&T Express Germany «non emette numeri come questo».

Peek mostra lo stato, ogni scansione con l'ora esatta e il momento della consegna. J&T indica il momento preciso di ogni scansione, quindi Peek lo mostra nel fuso orario del tuo dispositivo. Le scansioni restano nell'inglese di J&T. J&T non indica né il luogo né il giorno di consegna previsto né il peso, quindi Peek non mostra la mappa. Su una cronologia molto lunga tiene le 100 scansioni più recenti. «Apri il sito J&T Express Germany», nella pagina del pacco, apre il tuo numero sul tracking di J&T.

Finché J&T non scansiona il pacco, Peek mostra «In attesa del corriere» e continua a chiedere. Se il tracking di J&T non risponde o non conosce il numero, Peek chiede ai servizi di tracking e mostra la loro cronologia, con le loro parole.

Peek controlla ogni 10 minuti, e ogni 2 minuti quando il pacco è in consegna. Se per due giorni non arriva nessuna scansione nuova (12 ore se resta su «Preso in carico»), controlla una volta all'ora. Puoi ricevere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare J&T o il negozio al posto tuo, cambiare l'indirizzo o prenotare una nuova consegna.

## Domande sul tracking J&T Express Germany

### J&T Express Germany è un corriere vero?

Sì, è un vero corriere: la rete tedesca di J&T Express, che il catalogo di Peek elenca anche come J&T Express Germany GmbH. Il suo tracking risponde per i numeri `JTDE`, e ogni frase della tabella degli stati qui sopra è stata letta su pacchi veri di J&T Express Germany. Il sito di J&T non si può usare come fonte per questa pagina, quindi qui non trovi dati sull'azienda.

### Quale vicino ha il mio pacco J&T?

Peek non può dirtelo. Quando la riga di avvenuta consegna dice `Left with neighbor`, Peek mostra proprio questo, senza nome né indirizzo. Chiedi ai vicini; se nessuno ce l'ha, avvisa il negozio.

### J&T Express ha punti di ritiro in Germania?

Nessuno che il codice di Peek conosca. Sulla riga di avvenuta consegna Peek mostra solo questi tipi di consegna: a un vicino, davanti alla porta, alla reception, in giardino, nella cassetta delle lettere o in garage. Nessuna riga di J&T Express Germany che Peek conosce manda un pacco in un punto di ritiro o in un locker.

### Perché il tracking J&T non trova il mio numero?

Controlla che inizi con `JTDE` e che dopo ci siano 12 cifre: i numeri delle altre reti J&T, come `JTUK` nel Regno Unito, non sono tedeschi. Se il numero è giusto, forse J&T non ha ancora il pacco. Peek mostra «In attesa del corriere» fino alla prima scansione; se dura più di qualche giorno, chiedi al negozio.

### Come contatto J&T Express in Germania?

Prima chiedi al negozio: è lui che ha spedito il pacco con J&T. Questa pagina non riporta numeri di telefono o e-mail, ma su un pacco che si è fermato il link «Chiedi aiuto a J&T Express Germany» di Peek apre la pagina contatti di J&T.

:::sources
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nomi, forma JTDE, link di tracking, frasi del tracking lette su pacchi veri e loro fasi, giri di consegna, tipi di consegna, cosa viene tolto, pagina di assistenza
- [Universal Parcel Scraper: interrogazione comune delle reti J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – frasi in inglese, orari esatti delle scansioni, niente luogo, giorno previsto o peso, nomi e numeri di telefono tolti, 100 scansioni più recenti
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – forme dei numeri delle altre reti J&T, nessun passaggio a un altro corriere, servizi di tracking come ripiego
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – rischio del trasporto a carico del venditore quando un'azienda vende a un consumatore
:::
