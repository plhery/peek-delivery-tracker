---
title: Tracking J&T Express in Germania: numero JTDE e stati
description: Tracking J&T in Germania: com'è fatto un numero JTDE, cosa vuol dire ogni riga del tracking e cosa fare se il pacco è in ritardo, smarrito o non arriva.
slug: jt-express-germania-tracking
published: 2026-11-03
updated: 2026-11-06
---

J&T Express Germany (J&T Express Germany GmbH, Francoforte sul Meno) è la rete tedesca di J&T Express: consegna pacchi in Germania. Se un negozio ti ha spedito l'ordine con J&T, il numero di tracking inizia con `JTDE`: incollalo nel box qui sopra, oppure cercalo sul [tracking di J&T su jtexpress.de](https://www.jtexpress.de/tracking). Il numero ce l'ha il negozio che ha spedito l'ordine: lo trovi nell'e-mail di spedizione o nella pagina dell'ordine.

## Numero di tracking J&T Express Germany: com'è fatto

Secondo le FAQ di J&T, il numero di spedizione inizia con JTDE. Peek ne conosce una sola forma: `JTDE` seguito da 12 cifre, 16 caratteri in tutto. Con cifre inventate:

| Numero (inventato) | Cos'è |
| --- | --- |
| `JTDE000000000000` | `JTDE` e 12 cifre: un pacco J&T Express Germany. Peek riconosce subito J&T Express Germany. |
| `JTDE00000000000` | Una cifra in meno: non ha la forma di un numero J&T Express Germany, e Peek non riconosce nessun corriere. Controlla di non aver saltato una cifra. |

**Dove lo trovi.** Secondo J&T te lo dà il mittente, e lo trovi nella pagina dell'ordine del negozio online, nell'e-mail di conferma dell'ordine, nell'e-mail o nell'SMS di spedizione, nelle e-mail che annunciano o confermano la consegna e sull'etichetta del pacco. Se non lo trovi, J&T ti rimanda al mittente o al servizio clienti della piattaforma su cui hai comprato. Vedi anche [dove trovare il codice di tracking](guide:find-tracking-number).

Il tracking di J&T accetta fino a 10 numeri per ricerca, separati da spazi o virgole.

**Numeri che si somigliano.** Le reti J&T degli altri Paesi hanno tracking e numeri propri: `JTUK` e 12 cifre è J&T Express UK, `JTAU` e 12 cifre J&T Express Australia, `JTE` e 12 cifre J&T Express Middle East, mentre `JT` con 13 cifre lo usa J&T nel Sud-est asiatico e in Cina (J&T Express China). Solo `JTDE` è della rete tedesca. Se il tracking di J&T risponde `Keine Abfrageergebnisse` (`No tracking records yet` in inglese) o Peek continua a mostrare «In attesa del corriere», confronta il numero con l'e-mail del negozio, o chiedine conferma al negozio.

## Stati della spedizione J&T Express Germany: cosa vogliono dire

Nel tracking di J&T ogni scansione ha solo data, ora e una frase: nessun luogo. Il sito si apre in tedesco e non ha una versione italiana; se dal menu della lingua passi all'inglese o al cinese, ricarica le scansioni in quella lingua. Peek chiede le frasi in inglese e non le traduce, nemmeno se usi Peek in italiano. Queste sono le frasi che vedi, nell'ordine in cui compaiono lungo il viaggio del pacco:

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

**Quale vicino?** Sul tracking di J&T, sotto quella scansione compare `Zustelldetails anzeigen` (`Click to view delivery details` sul sito in inglese). Cliccaci e inserisci il CAP dell'indirizzo di consegna: vedrai il nome del vicino e il suo numero civico. Dopo cinque CAP sbagliati devi aspettare prima di riprovare. Peek interroga il tracking con il solo numero e si limita a mostrare che il pacco è stato lasciato a un vicino: niente nome e niente indirizzo. Toglie anche qualsiasi nome o numero di telefono che compaia in una riga di J&T.

## Consegna J&T in Germania: cosa ti dice il tracking

Nelle cronologie che il codice di Peek conosce, il corriere di J&T carica il pacco sul furgone per un giro di consegna e poi lo consegna, oppure scrive perché non ci è riuscito. Il codice di Peek non mostra nessun passaggio a un altro corriere: il tracking di J&T segue il pacco fino alla tua porta.

**In consegna.** `Your package has been loaded and is on its way to you` vuol dire che il pacco è partito per il giro di consegna. In quelle cronologie la riga compare la mattina, qualche ora prima della consegna. Il tracking di J&T non dà né una fascia oraria né un giorno di consegna previsto, quindi nemmeno Peek può mostrarli.

**Preferenze di consegna.** L'e-mail che ti avvisa della consegna contiene un link alla [pagina delle preferenze di consegna](https://www.jtexpress.de/delivery-preference) di J&T. Conferma il CAP e scegli tra i modi che J&T ti propone: con firma, a te, a un vicino (ne indichi il nome e se abita accanto o sul tuo piano) o alla reception; senza firma, davanti alla porta, in giardino o in cortile, nella cassetta delle lettere o in garage. Secondo J&T il corriere segue la tua scelta. Se scegli un posto senza firma, la pagina avverte che il rischio di perdita passa a te appena il pacco viene lasciato lì. Se lo stato della spedizione non permette più di scegliere, la pagina te lo dice.

**Foto di consegna.** Secondo l'informativa privacy di J&T sulla prova di consegna fotografica, il corriere fotografa il pacco quando te lo consegna o lo lascia nel posto concordato, inquadrando solo il pacco e la parte del posto che serve; chi finisce nella foto per caso viene reso irriconoscibile in automatico. Di regola J&T cancella la foto sei mesi dopo la consegna, ma può tenerla finché non si chiude un reclamo aperto in quel periodo, e può mostrarla al mittente per risolverlo.

**Rifiutato o indirizzo non trovato.** Dopo `Delivery failed, customer refused to accept`, nelle cronologie che il codice di Peek conosce il corriere ha ricaricato il pacco per un altro giro la mattina dopo. Dopo `Delivery failed because the address could not be found`, controlla l'indirizzo dell'ordine. Se manca qualcosa, come il numero dell'appartamento o il tuo nome sul campanello, avvisa subito il negozio: ha spedito il pacco all'indirizzo che gli hai dato tu. Anche le FAQ di J&T dicono di contattare subito il mittente o la piattaforma: dopo la spedizione, se l'indirizzo si possa ancora cambiare dipende da dove si trova il pacco e dalle regole del mittente. Dopo una consegna non riuscita può comparire anche `Returns have begun`. Vedi [come cambiare l'indirizzo di consegna](guide:change-delivery-address) e [consegna mancata](guide:missed-delivery).

**Tentativi e ritiro.** Il sito di J&T non dice quante volte J&T riprova, per quanto tiene un pacco in giacenza né a che ora consegna, e non elenca punti di ritiro, locker o depositi dove andare a prendere un pacco. Tra le righe di J&T Express Germany che il codice di Peek conosce, nessuna manda il pacco in un punto di ritiro o in un locker. Chiedi al negozio, o guarda i messaggi che hai ricevuto sulla consegna.

## Pacco J&T in ritardo, sparito o rispedito

Il corriere l'ha scelto il negozio, quindi per un ordine online il primo a cui rivolgerti è il negozio. Per la legge tedesca, se compri da un'azienda come consumatore, il rischio che il pacco si perda o si danneggi durante il trasporto è a carico del venditore, a meno che non sia stato tu a incaricare il corriere senza che il venditore te lo avesse indicato prima (§ 475 BGB).

**Pacco fermo.** J&T non pubblica tempi di consegna per i destinatari. Se un pacco resta su «Preso in carico» o «In transito» per quattro giorni senza righe nuove, Peek lo segnala («Nessun aggiornamento da quattro giorni») e ti mostra il link «Chiedi aiuto a J&T Express Germany». Se poi non si muove ancora niente, chiedi al negozio, o chiama l'assistenza telefonica di J&T con il numero di tracking. Vedi anche [tracking che non si aggiorna](guide:tracking-not-updating).

**Ancora nessun tracking.** Un numero che il tracking di J&T non conosce ancora appare in Peek come «In attesa del corriere». Spesso i negozi creano il numero prima di affidare il pacco al corriere. Se resta così per qualche giorno, verifica il numero con il negozio e chiedi se il pacco è partito davvero.

**Consegnato ma non ricevuto.** Leggi in Peek la riga di avvenuta consegna: se nomina un posto, come `Mailbox` o `Garage`, guarda lì; se dice `Left with neighbor`, chiedi ai vicini, o cerca il vicino sul tracking di J&T (vedi sopra). Le FAQ di J&T chiedono anche di verificare se il pacco l'hanno preso familiari, vicini o il personale dello stabile. Ancora niente? Segnalalo all'assistenza telefonica di J&T, e chiedi al negozio un rimborso o un nuovo invio, con il numero di tracking. Vedi anche [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso, danneggiato o rispedito.** Chiedi al negozio un rimborso o una sostituzione, con il numero di tracking. Le FAQ di J&T non indicano moduli di reclamo, termini o indennizzi per i destinatari: i termini li fissa la legge tedesca. Secondo il § 438 del Codice di commercio tedesco (HGB), un danno visibile va segnalato al più tardi alla consegna (basta dirlo al corriere), uno nascosto entro sette giorni dalla consegna, per iscritto (va bene anche un'e-mail a J&T). Se con J&T non trovi un accordo su un pacco perso o danneggiato, puoi rivolgerti all'organo di conciliazione postale della Bundesnetzagentur (Schlichtungsstelle Post), che è gratuito e accetta le domande dei destinatari come quelle dei mittenti. I pacchi dei grandi negozi online, però, viaggiano di solito con condizioni speciali che escludono la conciliazione: in quel caso puoi comunque rivolgerti al servizio consumatori della Bundesnetzagentur per la posta (Verbraucherservice Post). Vedi [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## J&T Express Germany: servizio clienti e contatti

I canali di J&T Express Germany, come li riportava la sua [pagina contatti](https://www.jtexpress.de/contact) il 6 novembre 2026:

- **Telefono:** +49 6931090650, dal lunedì al venerdì dalle 8:00 alle 20:00, chiuso nei giorni festivi.
- **E-mail:** de-jtservice@jtexpress.de.
- **Chat:** «Chat starten» («Start Chat» sul sito in inglese), nelle pagine contatti e FAQ.
- **Foto di consegna e dati personali:** de-dpo@jtexpress.de (dall'informativa sulla foto di consegna).
- **Ufficio:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. Le note legali danno come indirizzo Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

J&T non dice in quali lingue risponde il servizio clienti; il sito è in tedesco, inglese e cinese. Per un ordine online, comunque, parti dal negozio: è lui che ha spedito il pacco con J&T. Per un ordine su un marketplace, usa l'assistenza nella pagina dell'ordine. Su un pacco che si è fermato, il link «Chiedi aiuto a J&T Express Germany» di Peek apre la pagina contatti di J&T.

## Seguire un pacco J&T Express Germany con Peek

[Peek](/) riconosce subito J&T Express Germany da un numero `JTDE` («Corriere rilevato»), scritto con o senza spazi, in maiuscolo o in minuscolo, e interroga il tracking di J&T con il solo numero: niente CAP e niente account. Funziona anche un link www.jtexpress.de/tracking con `?q=` e il numero; se dentro c'è un numero di un altro tipo, Peek lo legge come se avessi incollato solo quello. Se il tuo numero non è un `JTDE`, incollalo lo stesso: Peek segue più di 3.500 corrieri. Scegliere J&T Express Germany con «Cambia» per un numero di un'altra forma non serve: Peek ti avvisa che J&T Express Germany «non emette numeri come questo».

Peek mostra lo stato, ogni scansione con l'ora esatta e il momento della consegna. J&T indica il momento preciso di ogni scansione, quindi Peek lo mostra nel fuso orario del tuo dispositivo. Le scansioni restano nell'inglese di J&T, anche se su jtexpress.de le leggi in tedesco. J&T non indica né il luogo né il giorno di consegna previsto né il peso, quindi Peek non mostra la mappa. Su una cronologia molto lunga tiene le 100 scansioni più recenti. «Apri il sito J&T Express Germany», nella pagina del pacco, apre il tuo numero sul tracking di J&T.

Finché J&T non scansiona il pacco, Peek mostra «In attesa del corriere» e continua a chiedere. Se il tracking di J&T non risponde o non conosce il numero, Peek chiede ai servizi di tracking e mostra la loro cronologia, con le loro parole.

Peek controlla ogni 10 minuti, e ogni 2 minuti quando il pacco è in consegna. Se per due giorni non arriva nessuna scansione nuova (12 ore se resta su «Preso in carico»), controlla una volta all'ora. Puoi ricevere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare J&T o il negozio al posto tuo, cambiare l'indirizzo o prenotare una nuova consegna.

## Domande sul tracking J&T Express Germany

### J&T Express Germany è un corriere vero?

Sì. J&T Express Germany GmbH, con sede a Francoforte sul Meno, è la società tedesca di J&T Express, un gruppo di corrieri espresso fondato in Indonesia nel 2015 e quotato alla Borsa di Hong Kong. Dal 1° settembre 2026 è iscritta nel registro dei fornitori di servizi postali della Bundesnetzagentur, l'autorità tedesca che regola le poste. J&T cerca partner in franchising in tutta la Germania per le consegne e i ritiri dell'ultimo miglio. Ogni frase della tabella degli stati qui sopra è stata letta su pacchi veri di J&T Express Germany.

### Quale vicino ha il mio pacco J&T?

Te lo dice il tracking di J&T: sotto la scansione di consegna clicca `Zustelldetails anzeigen` (`Click to view delivery details` in inglese) e inserisci il CAP dell'indirizzo di consegna: vedrai il nome del vicino e il suo numero civico. Peek mostra solo `Left with neighbor`, senza nome né indirizzo.

### J&T Express ha punti di ritiro in Germania?

Il sito tedesco di J&T non ne elenca, e nessuna riga di J&T Express Germany che Peek conosce manda un pacco in un punto di ritiro o in un locker. Dalla pagina delle preferenze di consegna puoi chiedere la consegna a te, a un vicino, alla reception o in un posto al tuo indirizzo.

### Perché il tracking J&T non trova il mio numero?

Controlla che inizi con `JTDE` e che dopo ci siano 12 cifre: i numeri delle altre reti J&T, come `JTUK` nel Regno Unito, non sono tedeschi. Se il numero è giusto, forse J&T non ha ancora il pacco: il suo tracking risponde `Keine Abfrageergebnisse` (`No tracking records yet`), e Peek mostra «In attesa del corriere» fino alla prima scansione. Se dura più di qualche giorno, chiedi al negozio.

### Come contatto J&T Express in Germania?

Chiama il +49 6931090650 (dal lunedì al venerdì, dalle 8:00 alle 20:00), usa la chat su jtexpress.de o scrivi a de-jtservice@jtexpress.de. Per un ordine online, però, chiedi prima al negozio: è lui che ha spedito il pacco con J&T.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – tracking, fino a 10 numeri, lingue del sito, scansioni ricaricate, risposta senza risultati, dati del vicino dopo la verifica del CAP, cinque tentativi
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – numeri JTDE e dove trovarli, consegnato ma non ricevuto, indirizzo sbagliato, chat; niente tempi di consegna, tentativi o regole sui reclami
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link nell'e-mail di avviso, verifica del CAP, modi di consegna con e senza firma, rischio nel posto scelto
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – telefono, orari, chat, e-mail, indirizzo dell'ufficio
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Francoforte sul Meno
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – foto di consegna, sei mesi, reclami, contatto per la privacy
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – partner in franchising per consegne e ritiri dell'ultimo miglio
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fondata nel 2015 in Indonesia, quotata a Hong Kong
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nomi, forma JTDE, link di tracking, frasi del tracking lette su pacchi veri e loro fasi, giri di consegna, tipi di consegna, cosa viene tolto, pagina di assistenza
- [Universal Parcel Scraper: interrogazione comune delle reti J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – frasi in inglese, orari esatti delle scansioni, niente luogo, giorno previsto o peso, nomi e numeri di telefono tolti, 100 scansioni più recenti
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – forme dei numeri delle altre reti J&T, nessun passaggio a un altro corriere, servizi di tracking come ripiego
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – rischio del trasporto a carico del venditore quando un'azienda vende a un consumatore
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – danno segnalato alla consegna, danno nascosto entro sette giorni, per iscritto
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registro dei fornitori di servizi postali: J&T Express Germany GmbH, iscritta il 1° settembre 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – domanda anche dei destinatari, gratuita, condizioni speciali per i pacchi dei negozi online, Verbraucherservice Post
:::
