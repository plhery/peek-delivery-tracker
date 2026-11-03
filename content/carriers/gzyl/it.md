---
title: GZYL tracking: numero YL…YQ, stati e pacco non arrivato
description: Tracking GZYL (广州云链) per i pacchi dalla Cina: com'è fatto il numero YL…YQ, cosa vogliono dire gli stati e perché il pacco può risultare consegnato senza esserlo.
slug: gzyl-tracking
published: 2026-11-03
updated: 2026-11-03
---

GZYL (广州云链, Guangzhou Yunlian) è 广州云链货运代理有限公司, uno spedizioniere di Guangzhou (Canton), in Cina. Se un negozio cinese ha spedito il tuo ordine con GZYL, il numero di tracking è composto da YL, dieci cifre e YQ: incollalo nel box qui sopra. Peek non conosce alcun sito web di GZYL e il suo tracking si trova su un semplice indirizzo IP, senza nome di dominio. Prendi con cautela quello che mostra: GZYL scrive in anticipo buona parte del viaggio di un pacco, quindi un passaggio, persino `Delivered`, non è sempre una vera scansione.

## Numero di tracking GZYL: com'è fatto

I numeri di spedizione di GZYL sono composti da YL, dieci cifre e YQ: 14 caratteri in tutto. Esempi con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `YL0000000000YQ` | YL, dieci cifre e YQ: la forma dei numeri di GZYL. Peek lo verifica sul tracking di GZYL. |
| `YL000000000YQ` | Solo nove cifre: non è la forma di GZYL. Controlla che non ne manchi una. |
| `GZXH0000000000YQ` | GZXH, dieci cifre e YQ: Xinghe Freight (星和货运), un altro spedizioniere. Il suo tracking passa da quello di GZYL e i passaggi hanno gli stessi testi. |
| `JXCAA0000000000YQ` | Anche questo finisce con YQ, come i numeri di molti spedizionieri cinesi, ma inizia con JXC: è un numero di Juxi Express, non di GZYL. |

In Peek spazi e minuscole non contano: incolla il numero così come l'hai ricevuto.

**Dove trovarlo.** È il venditore a prenotare la spedizione con GZYL e a passarti il numero. Se non lo trovi, leggi [dove si trova il codice tracking](guide:find-tracking-number).

## Stati del tracking GZYL: cosa significano

GZYL scrive i suoi passaggi in inglese, errori di battitura compresi. Nell'ordine in cui compaiono:

| GZYL scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `Order information received` | Il venditore ha mandato a GZYL i dati dell'ordine. GZYL non ha ancora il pacco. | «Etichetta creata» |
| `Arrive at the cross border receiving point` | GZYL ha ricevuto il pacco: è la sua prima scansione. | «Preso in carico» |
| `Goods leave the operation center` | Il pacco ha lasciato il centro operativo di GZYL. | «In transito» |
| `Arrive at the international airport` | È in aeroporto, in attesa del volo. | «In transito» |
| `Departing from the airport` | Il volo è partito. | «In transito» |
| `Item arrives at destination airport` | È atterrato nel Paese di destinazione. | «In transito» |
| `local carrier Agent Import customs clearance` | Un agente doganale nel tuo Paese sta sdoganando il pacco all'importazione. | «In dogana» |
| `The import customs keep it for inspection` | La dogana del tuo Paese sta controllando il pacco. | «In dogana» |
| `Customs clearance completed` | Sdoganato, il pacco riparte. | «In transito» |
| `Internationaliy Package received at processing center` | Un centro di smistamento del tuo Paese ha ricevuto il pacco sdoganato. Non è una nuova presa in carico: GZYL l'aveva già da tempo. | «In transito» |
| `Leave the email processing center` | Il pacco ha lasciato quel centro di smistamento. | «In transito» |
| `Arrival sorting, distribution center` | In un centro di distribuzione del tuo Paese; GZYL scrive la città dopo il testo. | «In transito» |
| `Leaving the sorting center` | Il pacco lascia un centro di smistamento; GZYL ne scrive la città. | «In transito» |
| `Package is on the way` | Prosegue nel tuo Paese; GZYL aggiunge una città. Non vuol dire che sia già in consegna. | «In transito» |
| `Delivered` | La consegna, con la città. Può essere un programma e non una scansione: vedi sotto. | «Consegnato», con una nota sul calendario fisso, se Peek non lo tiene da parte (vedi sotto) |

Quando GZYL scrive una città dopo uno degli ultimi quattro testi della tabella, Peek la mostra come luogo del passaggio, senza CAP. Nella cronologia di Peek i passaggi restano come li scrive GZYL, tranne `Delivered`, che Peek mostra in italiano: «Consegnato».

**Passando dagli Stati Uniti.** Un pacco può mostrare anche `Los Angeles Processing Center has received it`, con una città tra parentesi quadre dopo il testo: un centro di smistamento negli Stati Uniti l'ha ricevuto durante il viaggio verso la destinazione. Peek mostra «In transito».

**Una riga in cinese.** Per un ordine ancora senza scansioni, la piattaforma su cui gira il tracking di GZYL scrive da sola `货物电子信息已经收到`, cioè: i dati elettronici della merce sono stati ricevuti. Il venditore ha creato l'ordine, ma nessuno ha ancora scansionato il pacco. Peek mostra la riga così com'è, in cinese, con lo stato «Etichetta creata».

**Passando da China Post.** Un pacco può mostrare anche `广州处理中心已收寄`, con la città tra parentesi dopo il testo: è l'accettazione di China Post nel suo centro di smistamento di Guangzhou, che GZYL riporta in cinese. Peek mostra la riga in cinese, con lo stato «Preso in carico».

**Orari.** Sul tracking di GZYL tutti gli orari sono nell'ora di Pechino, anche quelli dei passaggi in Italia, e il fuso orario non è indicato. Le 20:00 sul tracking di GZYL sono le 13:00 in Italia con l'ora solare e le 14:00 con l'ora legale.

**Passaggi scritti in anticipo.** GZYL può scrivere in anticipo l'intera cronologia di un pacco, dal ritiro fino all'ultimo passaggio nel tuo Paese, molto prima degli orari che quei passaggi riportano, e il tracking mostra ognuno solo quando arriva l'ora indicata. Un passaggio scritto così è un programma, non una scansione del tuo pacco. Lo fa anche Meiteshun, che usa la stessa piattaforma di tracking. Il programma di GZYL si chiude poi con un `Delivered` che la piattaforma aggiunge in blocco la sera stessa del giorno indicato: porta la data del giorno dopo l'ultimo passaggio del programma e un orario d'ufficio di Pechino, qualunque sia il Paese di destinazione. Anche i servizi di tracciamento che copiano i dati di GZYL possono mostrare questi passaggi: un `Delivered`, sul tracking di GZYL come su un servizio di tracciamento, non prova quindi che il pacco sia arrivato.

## Chi consegna i pacchi GZYL in Italia

Peek non sa chi consegna i pacchi GZYL in Italia: i passaggi di GZYL nel Paese di destinazione non nominano alcun corriere. L'unico corriere a cui Peek può passare il tracciamento di un pacco GZYL è la [Posta Svizzera](carrier:swiss-post) (Swiss Post), in Svizzera e nel Liechtenstein.

Peek non ha trovato tempi di consegna di GZYL, né regole su tentativi di consegna, punti di ritiro o giacenza: dipendono dal corriere che consegna. Chiedi al venditore quale corriere consegna il tuo ordine e con quale numero di tracking.

## Pacco GZYL fermo o che non arriva

**Solo `Order information received` o `货物电子信息已经收到`.** Il venditore ha creato l'ordine, ma GZYL non ha ancora scansionato il pacco. Se dopo un giorno non è arrivato altro, Peek scrive sotto lo stato: «Il mittente ha creato l’etichetta, ma non ha ancora affidato il pacco a GZYL. Il tracciamento partirà da quel momento.» Se dopo qualche giorno non succede niente, chiedi al venditore se l'ha spedito davvero.

**Fermo in dogana.** Se ti chiedono di pagare spese d'importazione, leggi [pacco fermo in dogana](guide:customs). Se resta lì per giorni, avvisa il venditore.

**Il tracking di GZYL o un servizio di tracciamento mostra più di Peek.** Peek tiene da parte i passaggi scritti in anticipo rispetto all'ora che riportano, il `Delivered` che chiude il programma di GZYL e i passaggi datati dopo il suo ultimo controllo (vedi sotto), quindi quelli in più possono essere solo il programma di GZYL, non vere scansioni. Regolati sulla data di consegna stimata dal negozio e, quando è passata, scrivi al venditore. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**`Delivered`, ma non è arrivato niente.** Il passaggio può far parte del programma di GZYL, senza essere una scansione. A meno che il pacco non lo consegni la Posta Svizzera, Peek lo scrive sotto «Consegnato»: «Il tracciamento di GZYL segue un calendario fisso e non mostra nessuna firma. Se non è arrivato nulla, contatta il venditore.» Avvisa il venditore che il pacco non è arrivato e chiedigli quale corriere lo consegna e con che numero. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Nessun risultato.** Per un numero che non conosce, il tracking di GZYL mostra un solo risultato: il tuo numero sotto 参考号 (riferimento) e tutti gli altri campi vuoti. Se hai aggiunto un numero YL…YQ senza scegliere il corriere, Peek mostra allora «Probabilmente GZYL» al posto del nome del corriere, con lo stato «In attesa della prima scansione di GZYL», e continua a controllare. Se dopo una settimana GZYL non lo conosce ancora, Peek scrive «Dopo una settimana GZYL non riconosce ancora il numero. Verificalo con il mittente o scegli un altro corriere.» e mostra il pulsante «Scegli un altro corriere». Intanto confronta il numero con il messaggio del venditore, carattere per carattere.

**Perso o rispedito al mittente.** La spedizione con GZYL l'ha prenotata il venditore: chiedi a lui, o al marketplace, un rimborso o un nuovo invio. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## GZYL contatti: telefono e assistenza clienti

Al 30 ottobre 2026 Peek non ha trovato recapiti di GZYL: nessun sito web oltre al tracking, né un telefono o un'e-mail per chi riceve un pacco. La spedizione l'ha prenotata il venditore: è a lui che devi chiedere del tuo ordine e, se non risponde, al marketplace.

## Tracciare un pacco GZYL con Peek

Dalla sola forma, [Peek](/) non può essere sicuro che un numero sia di GZYL: YL, dieci cifre e YQ è solo un indizio. Incollalo lo stesso: Peek segue più di 3.500 corrieri. Mentre lo aggiungi, Peek interroga il tracking di GZYL («Verifica dei servizi di tracciamento…») e, se GZYL conosce il numero, mostra «GZYL ha questo pacco.» Altrimenti premi «Cambia» e scegli GZYL sotto «Compatibili con questo numero» (lo trovi anche scrivendo 广州云链), oppure usa «Cambia corriere» su un pacco già aggiunto. Funziona anche il riferimento che ti ha dato il venditore, ma solo se scegli tu GZYL: premi «Cambia» (o «Cambia corriere») e cerca GZYL. Peek mostra allora il numero con cui GZYL registra la spedizione, alla voce «GZYL lo traccia con il numero». Peek però toglie i trattini, quindi un riferimento che li contiene non viene trovato: in quel caso chiedi al venditore il numero YL…YQ.

Peek legge il tracking di GZYL e mostra lo stato, ogni passaggio con il suo luogo e, quando i dati di GZYL lo indicano, il Paese di destinazione. Legge gli orari come ora di Pechino e li mostra nel fuso orario del tuo dispositivo. Non riporta nomi, indirizzo, CAP, peso, dati di pagamento, firma né prova di consegna, che i dati di GZYL possono contenere, e non mostra una data di consegna prevista: GZYL non la indica.

Peek tiene da parte ogni passaggio scritto più di dodici ore prima dell'ora che riporta, e ogni passaggio datato dopo il momento in cui controlla, secondo l'ora di Pechino. In un ordine con passaggi scritti in anticipo tiene da parte anche `Delivered`, persino se è stato scritto il giorno stesso che riporta. Quando l'interfaccia dati del tracking non restituisce passaggi per un numero, Peek legge invece la pagina di tracking di GZYL; quella pagina non mostra quando è stato scritto un passaggio, quindi lì Peek tiene da parte ogni `Delivered`. Si basa sui passaggi che tiene, non sullo stato riassuntivo dei dati di GZYL. Se tutti i passaggi di un ordine sono tenuti da parte, in Peek l'ordine non ha ancora una cronologia. E se un servizio di tracciamento copia i passaggi che Peek ha tenuto da parte come programma di GZYL, `Delivered` compreso, Peek scarta anche quelle copie, persino quando tiene altri passaggi dello stesso ordine. Quando però il tracking di GZYL non risponde, Peek chiede ai servizi di tracciamento, e le loro copie possono contenere passaggi che GZYL ha scritto in anticipo.

A parte il passaggio alla Posta Svizzera, Peek resta con GZYL e non passa a un altro corriere: se il venditore ti dà il numero del corriere che consegna, aggiungilo come pacco separato. Peek controlla ogni 10 minuti, e ogni 2 minuti quando il pacco è «In consegna»; nessun passaggio di GZYL che Peek conosce, però, porta a questo stato. Dopo due giorni senza novità, o dopo 12 ore se il pacco è ancora su «Etichetta creata» o «Preso in carico», Peek controlla solo una volta all'ora. Per le notifiche scegli tra «Ogni scansione», «Passaggi importanti» e «Solo la consegna»: «Passaggi importanti» comprende i due passaggi doganali di GZYL e `Delivered`, non i voli né lo smistamento. Peek non può contattare GZYL o il venditore al posto tuo.

## Domande sul tracking GZYL

### Che cos'è GZYL (广州云链)?

È 广州云链货运代理有限公司 (Guangzhou Yunlian), uno spedizioniere di Guangzhou, in Cina. I suoi numeri di spedizione sono composti da YL, dieci cifre e YQ.

### Tutti i numeri YL…YQ sono di GZYL?

Non è detto. YL, dieci cifre e YQ è la forma di GZYL e nessun altro corriere del catalogo di Peek la usa, ma Peek chiede comunque al tracking di GZYL prima di attribuirgli il numero. Con nove o undici cifre il numero non ha la forma di GZYL.

### Perché GZYL dice che il pacco è stato consegnato se non è arrivato?

Perché quel `Delivered` può far parte del programma di GZYL. GZYL scrive in anticipo buona parte della cronologia di un pacco e la chiude con un `Delivered`: questi passaggi sono un programma, non scansioni. Quando legge il tracking di GZYL, Peek tiene da parte questi passaggi, `Delivered` compreso, e se mostra comunque «Consegnato», sotto aggiunge che il tracciamento di GZYL segue un calendario fisso. Avvisa il venditore che il pacco non è arrivato e chiedigli quale corriere lo consegna.

### Chi consegna i pacchi GZYL in Italia?

Peek non lo sa: i passaggi di GZYL nel Paese di destinazione non nominano alcun corriere. Chiedi al venditore quale corriere consegna il tuo ordine e con quale numero di tracking, poi aggiungi quel numero a Peek come pacco separato. Solo in Svizzera e nel Liechtenstein Peek può passare da sé il tracciamento di un pacco GZYL alla Posta Svizzera.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome dell'azienda e altri nomi, tracking su un semplice indirizzo IP, forma YL…YQ e corrispondenza debole, Xinghe Freight sul tracking di GZYL, ricerca per numero o per riferimento con i suoi trattini, testi dei passaggi e loro fasi, la riga in cinese della piattaforma, città dopo i testi e CAP tolti, ora di Pechino, cronologia scritta in anticipo e la consegna che la chiude, risposta a un numero sconosciuto, Posta Svizzera come unico passaggio ad altro corriere, cosa Peek mostra, tiene da parte e lascia fuori
:::
