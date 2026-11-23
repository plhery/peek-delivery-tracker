---
title: Tracciamento DAI Post: numeri, stati e aiuto consegna
description: Traccia un pacco DAI Post (Parcel Right) in Australia, Nuova Zelanda o Singapore: numeri, significato degli stati, consegna senza firma, ritiro e contatti.
slug: tracciamento-dai-post
published: 2026-11-23
updated: 2026-11-23
---

DAI Post è una società australiana di spedizioni per negozi online, nata a Melbourne nel 2000. Porta pacchi da Stati Uniti, Regno Unito, Europa e Asia agli acquirenti in Australia, Nuova Zelanda e Singapore. La consegna dell’ultimo miglio gestita direttamente si chiama Parcel Right. Traccia un pacco sulla [pagina di tracciamento DAI Post](https://daipost.com/track) oppure incolla il numero nella casella qui sopra. Il numero è nella conferma d’ordine del negozio; se non lo trovi, chiedi al negozio.

## Numero di tracciamento DAI Post: come si presenta

DAI Post traccia i numeri stampati dai negozi sui pacchi, che possono avere forme diverse. Il suo tracker accetta fino a 10 numeri alla volta. Le forme note al codice di Peek, con cifre inventate:

| Numero (inventato) | Cos’è |
| --- | --- |
| `NZC482910375` | `NZC` e nove cifre: nel codice di Peek, il numero DAI Post per i pacchi diretti in Nuova Zelanda. Peek riconosce subito DAI Post. |
| `00393580944829103754` | 20 cifre che iniziano con `0039358094`, l’ultima cifra è di controllo: un codice a barre SSCC stampato sull’etichetta, talvolta come `(00) 3 9358094 482910375 4`. Anche altri corrieri usano 20 cifre, quindi Peek non può esserne certo solo dalla forma. |
| `KPR48291037561` | Un riferimento proprio del negozio, lettere poi cifre. Peek non lo riconosce dalla forma. |

Spazi, parentesi e minuscolo non importano. Se una cifra in un codice di 20 cifre è errata, la cifra di controllo fallisce e Peek non lo associa a DAI Post: controlla il numero. Altro in [come trovare un numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati del tracciamento DAI Post spiegati

Il tracciamento DAI Post mostra una frase colloquiale per ciascuna tappa. Alcuni pacchi mostrano una versione più corta dello stesso passaggio, come `Checked in at origin hub`. Peek legge il codice dell’evento, quindi le due versioni hanno lo stesso significato. Le righe note a Peek, in ordine:

| DAI Post dice | Cosa significa | Peek mostra |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` o `Customer label information received` | Il negozio ha inviato i dati; DAI Post non ha ancora il pacco. | «Etichetta creata» |
| `Your parcel’s checked in and being prepared for its journey.` o `Checked in at origin hub` | L’hub di origine di DAI Post ha preso in carico il pacco. | «Preso in carico» |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` o `Parcel processed at origin facility` | Smistato, pronto a partire. | «In transito» |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` o `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | Il volo parte in ritardo o c’è un ritardo lungo il percorso. | «In transito» |
| `Your parcel is in the air and is heading your way.` o `Parcel departed origin country` | In volo dal Paese di origine. | «In transito» |
| `Parcel arrived in trans-shipment country.`, poi `Your parcel’s left the transfer country and is back on the move.` | Scalo in un Paese di transito. | «In transito» |
| `Touchdown! Your parcel has landed.` o `Parcel arrived in destination country` | Arrivato nel tuo Paese. | «In transito» |
| `We’ve collected your parcel from the airline and it’s being processed.` o `Collected from the airline for processing` | DAI Post lo ha ritirato dalla compagnia aerea. | «In transito» |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` o `Parcel processed by clearance facility` | La dogana lo sta controllando. | «In dogana» |
| `Customs clearance delay` | La dogana lo trattiene ancora. | «In dogana» |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` o `Customs cleared` | Sdoganato. | «In transito» |
| `We’ve got your parcel and it’s been sorted for delivery.` o `Parcel received and sorted` | Smistato per la consegna. | «In transito» |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | In viaggio da uno Stato all’altro. | «In transito» |
| `Your parcel’s arrived at our local facility, it's on the way.` | In un deposito vicino a te. | «In transito» |
| `Your parcel’s on its way to you, almost there!` | Diretto al deposito di consegna, non ancora sul furgone di consegna. | «In transito» |
| `It’s happening, your parcel will be delivered today. Get ready!` o `Parcel out for final mile carrier delivery` | In consegna oggi. | «In consegna» |
| `Parcel unsafe to leave at address` | L’autista non ha potuto lasciarlo (vedi sotto). | «Tentativo di consegna» |
| `Parcel available for collection` | In attesa in un punto di ritiro. | «Pronto per il ritiro» |
| `Delivered! Your parcel has arrived. Enjoy!` | Consegnato. | «Consegnato» |
| `Delivered, left where you can find it.` o `Parcel delivered - authority to leave` | Lasciato al tuo indirizzo senza firma. | «Consegnato» |

Per un numero sconosciuto, il tracker DAI Post risponde `Sorry, we couldn't find your parcel.` Altro in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

**Orari.** DAI Post registra ogni ora sull’orologio locale del luogo della scansione, senza fuso orario. Peek ricava il fuso dal luogo e mostra gli orari nel fuso del tuo dispositivo; l’ora della prima riga può discostarsi.

## Consegna DAI Post: consegna senza firma, ritiro e resi

DAI Post afferma che la sua rete copre il 100% della popolazione australiana. L’orario dipende dal giro dell’autista, che varia con i pacchi della giornata e il traffico.

- **Consegna senza firma (*Authority to leave*).** Non serve firma. Se nessuno risponde e l’autista ritiene il posto sicuro, lascia il pacco.
- **Non sicuro da lasciare.** Se il pacco sarebbe troppo esposto, va al punto ritiro più vicino della tua zona e DAI Post ti invia le istruzioni. Sul sito DAI Post, la riga `Parcel available for collection` apre una mappa del punto; Peek mostra solo la città.
- **7 giorni per il ritiro.** Un pacco non ritirato entro 7 giorni rientra al deposito DAI Post e poi torna al negozio. Lo stesso avviene con dati di consegna incompleti.
- **Messaggi.** Ricevi notifiche per i passaggi principali solo se il negozio ha fornito a DAI Post il tuo indirizzo e-mail e numero di cellulare.

Le condizioni di DAI Post aggiungono che, se sei assente, il pacco può essere lasciato in luogo sicuro o portato a un *Collection Point*, risultando così consegnato. Le sue pagine non indicano chi consegna in Nuova Zelanda o a Singapore. Vedi [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

## Dogana su un pacco DAI Post

**Fermo in dogana (*Customs held*).** La pagina di aiuto di DAI Post spiega che *Customs held* significa che ABF o DAFF — le agenzie australiane per frontiere e biosicurezza — hanno probabilmente bisogno di ulteriori informazioni prima del rilascio. Chiedi al servizio clienti di DAI Post cosa serve: il modulo richieste ha la voce «My parcel is Customs held.»

**Costi.** Le condizioni di DAI Post attribuiscono al mittente la responsabilità delle spese di spedizione, inclusi dazi e tasse. Se rifiuti di pagare un importo alla consegna, il mittente rimane debitore e DAI Post può trattenere il pacco fino al pagamento o restituirlo al mittente. Eventuali importi a tuo carico dipendono dalle norme del tuo Paese: vedi [pacchi fermi in dogana](guide:customs). Un messaggio su una tassa ti sembra sospetto? Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciamento DAI Post fermo, pacco smarrito o restituito

**Nessuna nuova riga da giorni.** DAI Post scansiona solo nei punti chiave e ricorda che il pacco continua a spostarsi tra una scansione e l’altra. Su tratte interstatali o regionali può sembrare fermo per giorni. Se il pacco resta quattro giorni senza una nuova riga su «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …» con il giorno di quella riga e un link «Chiedi aiuto a DAI Post». Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Ancora «Etichetta creata».** DAI Post non ha ancora ricevuto il pacco. Se resta così per giorni, chiedi al negozio se l’ha effettivamente spedito.

**Consegnato, ma non c’è.** Con la consegna senza firma, controlla prima intorno a casa. DAI Post può inviarti via e-mail la prova di consegna *Authority to leave*: richiedila dal modulo e poi contatta il negozio. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** Per un pacco mai arrivato usa il modulo di DAI Post con il motivo «I did not receive my parcel.» In ogni caso la spedizione è stata prenotata dal negozio: chiedi rimborso o nuovo invio. Vedi [pacchi smarriti](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Servizio clienti e contatti DAI Post

Canali di contatto di DAI Post:

- **Telefono:** Australia +61 3 8586 1200, Nuova Zelanda +64 800 451 718, Singapore +65 800 321 1587. Non sono indicati orari di apertura.
- **E-mail:** consumercare@daipost.com.
- **Online:** il [modulo richieste](https://daipost.com/contact). Chiede nome, telefono, e-mail, numero di tracciamento e motivo della richiesta.

## Tracciare un pacco DAI Post con Peek

[Peek](/) segue un pacco DAI Post con il solo numero: senza account né CAP.

- **Un numero `NZC`.** Peek indica subito DAI Post («Corriere rilevato»). Se due giorni dopo la sua prima risposta DAI Post non conosce ancora il numero, Peek ti chiede di controllarlo, scegliere un altro corriere o eliminare il pacco.
- **Un numero di 20 cifre che inizia con `0039358094`.** Incollalo comunque: Peek segue oltre 4.500 corrieri. Mentre verifica vedi «Verifica dei servizi di tracciamento…», e DAI Post è tra i primi interrogati. Se DAI Post ce l’ha, Peek lo indica; se non ancora, il pacco mostra «In attesa della prima scansione di DAI Post» e la scheda dice «Probabilmente DAI Post», e dopo una settimana Peek suggerisce di verificare il numero con il mittente. Per selezionare DAI Post manualmente tocca «Cambia»: lo trovi in «Compatibili con questo numero».
- **Qualsiasi altro numero.** Incollalo comunque, tocca «Cambia» e scegli DAI Post (cerca DAI Post o Parcel Right). Su un pacco già aggiunto usa «Cambia corriere».
- **Un link.** Incolla un link di tracciamento da daipost.com, parcelright.com.au o parcelrig.ht: Peek estrae il numero e imposta subito DAI Post.

Peek mostra stato, ogni riga nelle parole di DAI Post, città e Paese di ciascuna scansione, data di consegna prevista quando DAI Post la indica e orario di consegna. Non mostra punto di ritiro, nome servizio, peso né prova di consegna. «Apri il sito DAI Post» apre il numero sul tracciamento ufficiale. Nel codice di Peek, alcuni pacchi [Asendia](carrier:asendia), e alcuni pacchi [ePost Global](carrier:epost-global) diretti in Nuova Zelanda, arrivano a DAI Post: il loro tracciamento include le sue scansioni.

Finché il pacco è aperto in Peek, o nel giorno della consegna con le notifiche attive, Peek lo controlla fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 ora locale nel giorno in cui DAI Post registra il pacco in consegna. Dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e ogni 6 ore dopo sette giorni senza novità su «Etichetta creata». Le notifiche possono seguire «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare DAI Post né il negozio per te.

## Domande sul tracciamento DAI Post

### Parcel Right è la stessa azienda di DAI Post?

Parcel Right è la consegna dell’ultimo miglio gestita da DAI Post. Su Peek puoi cercare entrambi i nomi.

### Cosa significa «Parcel unsafe to leave at address»?

L’autista non ha trovato nessuno e il luogo non era sicuro. Il pacco va al punto di ritiro più vicino: ritiralo entro 7 giorni, altrimenti torna al negozio.

### Quanto tempo impiega DAI Post a consegnare?

Le condizioni di DAI Post precisano che i tempi standard sono stime, non garanzie. Il tracciamento può mostrare una data di consegna prevista, mostrata anche da Peek.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, pacchi da USA, Regno Unito, Europa e Asia verso Australia, Nuova Zelanda e Singapore, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – telefoni, copertura, tempi di consegna, nessun aggiornamento recente, notifiche, consegna senza firma, ritiro, resi, dogana, prova di consegna
- [DAI Post: Delivery tracking](https://daipost.com/track) – il tracker, fino a 10 numeri
- [DAI Post: Contact](https://daipost.com/contact) – modulo richieste e motivi, telefoni, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – luogo sicuro o Collection Point, tempi stimati, spese
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – nomi, formati numerici, link, diciture e fasi, orari, consegna prevista
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rilevamento, copie delle scansioni DAI Post in Asendia ed ePost Global
:::
