---
title: Tracciamento Tianhui Hengyuan (THHY): codici AT e scansioni
description: Traccia un pacco Tianhui Hengyuan (天汇恒源, THHY) dalla Cina: com’è fatto il codice AT…TH, quali righe sono scansioni reali e chi contattare.
slug: tracciamento-tianhui-hengyuan
published: 2026-11-22
updated: 2026-11-22
---

Tianhui Hengyuan (天汇恒源, scritto anche THHY o TH) è uno spedizioniere cinese. Se un negozio in Cina ha spedito il tuo ordine con questo operatore, il codice è composto da `AT`, dieci cifre e `TH`: incollalo nella casella qui sopra. Peek non conosce alcun sito web ufficiale dell’azienda, e il suo tracker gira su un semplice indirizzo server numerico. Leggi quel tracciamento con attenzione: dopo le prime righe proprie di Tianhui Hengyuan, quelle successive sono generate da uno script e non indicano dove si trovi il tuo pacco.

## Numero di tracciamento Tianhui Hengyuan: com’è fatto

Le lettere di vettura di Tianhui Hengyuan hanno `AT`, dieci cifre e `TH`, per 14 caratteri totali. Derivano da una regola di numerazione di Hualei (华磊), la piattaforma per spedizionieri su cui gira il tracker. Con cifre inventate:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `AT4829173654TH` | `AT`, dieci cifre e `TH`: il formato delle lettere di vettura di Tianhui Hengyuan. Peek lo verifica sul tracker di Tianhui Hengyuan (sotto). |
| `AT482917367TH` | Solo nove cifre. Due lettere, nove cifre e `TH` sono il formato di un codice postale internazionale di Thailand Post, non di Tianhui Hengyuan. |
| `AT48291736542TH` | Undici cifre: nessun corriere del catalogo Peek usa questo formato. Controlla che non ci sia una cifra di troppo. |

Spazi e lettere minuscole non contano quando incolli il codice in Peek.

**Dove trovarlo.** Il venditore prenota la spedizione presso Tianhui Hengyuan e ti comunica il codice nei dettagli dell’ordine. Se non lo trovi, leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Riferimento del negozio.** Il tracker di Tianhui Hengyuan individua un ordine anche tramite il riferimento del venditore, scritto esattamente come l’ha indicato il negozio, trattini compresi.

**Nessun link da condividere.** Il tracker risponde su un indirizzo IP numerico in semplice HTTP, non su un dominio web. Peek non pubblica il link: un collegamento HTTP in chiaro trasmetterebbe il tuo codice senza cifratura.

## Stati di tracciamento Tianhui Hengyuan spiegati

Le righe proprie del tracker sono poche, e due sono solo in cinese. Nell’ordine in cui compaiono:

| Il tracker Tianhui Hengyuan scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` | «The electronic cargo information has been received». La piattaforma inserisce questa riga automaticamente per un ordine senza scansioni: il venditore ha creato l’ordine, ma Tianhui Hengyuan non ha ancora scansionato il pacco. | «Etichetta creata» |
| `Arrived at the receiving point` | Tianhui Hengyuan ha il pacco: è la prima vera scansione nelle sue mani. | «Preso in carico» |
| `货物离开操作中心` | «Goods leave the operation center»: il pacco ha lasciato il centro operativo di Tianhui Hengyuan. | «In transito» |

**Righe scritte da uno script.** Dopo questi passaggi, il tracker prosegue con righe simili alle scansioni di China Post e righe in inglese sull’ultimo tratto. Provengono dal prodotto di tracciamento personalizzato della piattaforma Hualei (轨迹定制, «customised track»), che le genera secondo uno script. Non sono scansioni reali del tuo pacco. Tra queste:

| Il tracker Tianhui Hengyuan scrive | Traduzione |
| --- | --- |
| `已收取邮件` | Invio ritirato |
| `邮件已完成分拣` | Smistamento completato |
| `送交出口` | Presentato alla dogana di esportazione |
| `飞机起飞` | Volo partito |
| `到达寄达地` | Arrivato nel paese di destinazione |
| `送交境外进口海关` | Presentato alla dogana di importazione estera |

Le righe in inglese includono `Transporting to the next facility`, `Packages ready for delivery on the next working day` e `Packages in transit for delivery`. Puoi riconoscere quelle cinesi generate da script dagli orari: cadono tutte nello stesso secondo del minuto, distanziate da multipli di sei minuti. Non prendere quindi `送交境外进口海关` come prova che il pacco sia alla dogana del tuo paese, né `Packages in transit for delivery` come prova che sia in consegna. Anche i servizi di tracciamento che copiano i dati di Tianhui Hengyuan possono mostrare queste stesse righe.

**Orari.** Ogni orario sul tracker di Tianhui Hengyuan è sull’ora di Pechino, comprese le righe estere, senza fuso orario indicato. Le 16:00 lì corrispondono alla mattina a Londra e a notte fonda a New York.

## Chi consegna i pacchi Tianhui Hengyuan

Peek non sa chi consegni i pacchi Tianhui Hengyuan. L’unico corriere a cui il suo codice può passare la consegna è [Swiss Post](carrier:swiss-post), e solo quando il record di Tianhui Hengyuan nomina Swiss Post e contiene un codice Swiss Post (dettagli sotto). I record di Tianhui Hengyuan però non riportano alcun codice postale, quindi le righe generate da script non possono essere verificate su alcun sito postale. Un codice Swiss Post ha due lettere, nove cifre e `CH`, oppure 18 cifre che iniziano con `98` o `99`.

Peek non ha trovato tempi di consegna di Tianhui Hengyuan né regole su tentativi di consegna, punti di ritiro o giacenza: dipendono dal corriere che effettua la consegna. Chiedi al venditore quale corriere consegna e il relativo numero. In Svizzera, la pagina Swiss Post spiega avvisi e ritiri. Se ti vengono chiesti oneri di importazione, leggi [pacchi fermi in dogana](guide:customs).

## Pacco Tianhui Hengyuan che non arriva o tracciamento fermo

**Solo `货物电子信息已经收到`.** Il venditore ha creato l’ordine, ma Tianhui Hengyuan non ha ancora scansionato il pacco. Se per alcuni giorni non compare altro, chiedi al venditore se lo ha davvero spedito.

**Fermo dopo `货物离开操作中心`.** È l’ultima riga propria di Tianhui Hengyuan. Ciò che il tracker mostra in seguito arriva dallo script, che Peek scarta, quindi il silenzio in Peek non significa che il pacco sia fermo. Chiedi al venditore chi consegna e il suo codice, e aggiungilo in Peek come pacco separato. Approfondisci in [perché il tracciamento si ferma](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Il tracker mostra il pacco più avanti rispetto a Peek.** Peek trattiene quelle righe perché generate da script (sopra) o scritte in anticipo rispetto all’orario riportato (sotto). Affidati alla data stimata dal negozio e contatta il venditore una volta superata.

**Nessun risultato.** Per un codice sconosciuto, il tracker di Tianhui Hengyuan mostra un risultato con il tuo numero sotto `参考号` (riferimento) e tutti gli altri campi vuoti. Peek legge questa risposta come una spedizione registrata senza scansioni: continua a controllare al ritmo indicato sotto per le righe scritte in anticipo e mostra «In attesa di Tianhui Hengyuan», senza chiederti di verificare il codice. Controlla quindi tu stesso carattere per carattere con il messaggio del venditore.

**Consegnato, ma non è arrivato nulla.** Una scansione di consegna arriva dal corriere finale, non dalle righe di Tianhui Hengyuan: chiedi prima a quel corriere, poi avvisa il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** È il venditore ad aver affidato il pacco a Tianhui Hengyuan, quindi chiedi al venditore o al marketplace un rimborso o una nuova spedizione. Vedi [pacchi smarriti](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti Tianhui Hengyuan

Peek non ha trovato recapiti di Tianhui Hengyuan: non conosce alcun sito web dell’azienda, né telefono o e-mail per i destinatari. Il venditore ha prenotato la spedizione ed è il referente a cui rivolgerti, oppure il marketplace se il venditore non risponde.

## Tracciare un pacco Tianhui Hengyuan con Peek

[Peek](/) non può identificare con certezza un codice Tianhui Hengyuan solo dalla forma: `AT`, dieci cifre e `TH` sono solo un indizio debole. Incollalo comunque: Peek segue oltre 4.500 corrieri. Mentre lo aggiungi, Peek interroga il tracker di Tianhui Hengyuan («Verifica dei servizi di tracciamento…») e mostra «Tianhui Hengyuan ha questo pacco.» se il tracker riconosce il codice. Altrimenti tocca «Cambia» e scegli Tianhui Hengyuan (anche cercando `天汇恒源` o `THHY`), oppure usa «Cambia corriere» su un pacco già aggiunto. Fai lo stesso per un riferimento del venditore: Peek mostra poi il codice dell’ordine con l’etichetta «Tianhui Hengyuan lo traccia con il numero». Peek rimuove però i trattini, quindi un riferimento con trattini non viene trovato tramite Peek: chiedi al venditore il codice `AT…TH`.

Peek legge direttamente il tracker di Tianhui Hengyuan e mostra lo stato, ogni riga con il luogo e il paese di destinazione quando è presente nel record. Le due righe cinesi compaiono nell’inglese usato dagli altri spedizionieri della stessa piattaforma: `Goods leave the operation center` e `The electronic cargo information has been received`. Gli orari sono letti sull’ora di Pechino e convertiti nel fuso del tuo dispositivo («Orari nel tuo fuso orario:»). Peek esclude nomi, indirizzo, codici postali, peso, tariffe, firma e prova di consegna eventualmente contenuti nel record e non mostra date di consegna previste perché Tianhui Hengyuan non ne indica.

Dal tracker di Tianhui Hengyuan, Peek trattiene le righe generate da script, qualsiasi riga scritta oltre dodici ore prima dell’orario che porta (o, nello stesso secondo di una tale riga, oltre sei ore prima del proprio) e qualsiasi riga datata nel futuro rispetto all’ora di Pechino. Si basa sulle righe mantenute, non sullo stato riepilogativo del record. Un ordine con tutte le righe trattenute non ha ancora cronologia in Peek e mostra «In attesa di Tianhui Hengyuan» senza chiedere di controllare il numero: tratta le righe anticipate come un ordine registrato senza scansione e continua a interrogare il tracker ogni 6 ore dopo le prime ore sull’app iPhone o Android o nel tuo account. Per una ricerca senza account, Peek controlla nelle prime 6 ore, poi quando apri il link (al massimo ogni 6 ore e una volta al giorno dopo due giorni) e, con notifiche attive, una volta al giorno fino a 30 giorni. Quando trattiene passaggi scritti in anticipo, Peek avvisa con «Il sito di Tianhui Hengyuan mostra in anticipo tutto il percorso, consegna compresa. Quei passaggi non sono scansioni reali.» — e per una consegna anticipata con «Il sito di Tianhui Hengyuan mostra una consegna scritta in anticipo, non una scansione reale. Se non è arrivato nulla, contatta il venditore.». Tali righe non cambiano lo stato e non inviano notifiche. Peek mette da parte anche le copie di queste righe provenienti dai servizi di tracciamento; se però il tracker non è raggiungibile, Peek ricorre ai servizi di tracciamento, le cui copie possono includere le righe da script.

**Swiss Post.** Peek passa il pacco solo se il record di Tianhui Hengyuan nomina Swiss Post come canale, contiene un codice Swiss Post e non indica una destinazione fuori da Svizzera e Liechtenstein. Il pacco mostra allora «Consegna con Swiss Post» e quel codice (oppure «Annunciato a Swiss Post, non ancora ricevuto» finché Swiss Post ha solo l’annuncio senza scansioni proprie), e non appena Swiss Post registra una scansione almeno recente quanto l’ultima di Tianhui Hengyuan, Peek segue il pacco lì mantenendo in cronologia le righe di Tianhui Hengyuan. Altrimenti resta sul tracker di Tianhui Hengyuan: se il venditore ti fornisce il codice del corriere finale, aggiungilo come pacco separato.

Mentre tieni il pacco aperto in Peek, controlla direttamente fino a ogni 10 minuti, poi ogni ora quando Tianhui Hengyuan tace: dopo 12 ore senza novità oltre la prima riga o scansione, oppure due giorni senza novità in seguito. Poiché le righe proprie di Tianhui Hengyuan sono solo fasi iniziali, soltanto «Ogni scansione» le notifica; «Passaggi importanti» e «Solo la consegna» attendono fasi successive. Peek non può contattare Tianhui Hengyuan né il venditore per te.

## Domande sul tracciamento Tianhui Hengyuan

### Che cos’è Tianhui Hengyuan (THHY)?

Uno spedizioniere cinese, 天汇恒源, scritto anche THHY o TH, il cui tracker gira sulla piattaforma Hualei. Il catalogo Peek riporta due ragioni sociali: 深圳市天汇恒源供应链有限公司 e 广州市天慧恒源供应链有限公司, società di supply chain (供应链) di Shenzhen e Guangzhou. Le lettere di vettura sono `AT`, dieci cifre e `TH`.

### Un codice AT…TH è di Thailand Post?

Non con dieci cifre. `AT`, dieci cifre e `TH` è il formato di Tianhui Hengyuan. I codici internazionali di Thailand Post hanno nove cifre: due lettere, nove cifre e `TH`.

### Perché il tracker di Tianhui Hengyuan mostra più passaggi di Peek?

Perché dopo le proprie prime righe mostra passaggi scritti secondo uno script da un prodotto di tracciamento personalizzato. Sembrano scansioni di China Post ma non sono scansioni reali del tuo pacco, perciò Peek li esclude.

### Chi consegna i pacchi Tianhui Hengyuan?

Peek non lo sa, e i record di Tianhui Hengyuan non indicano codici postali: chiedi al venditore quale corriere consegna e il suo codice di tracciamento. L’unico corriere a cui Peek può passare un pacco Tianhui Hengyuan è Swiss Post, quando il record lo nomina con un codice Swiss Post.

### Chi contatto per un pacco Tianhui Hengyuan mancante?

Il venditore da cui hai ordinato: è lui che ha affidato il pacco a Tianhui Hengyuan. Peek non conosce alcun sito web né recapiti di contatto per Tianhui Hengyuan.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomi e ragioni sociali, piattaforma Hualei, tracker su indirizzo IP in HTTP senza link pubblicato, formato AT…TH e corrispondenza debole, ricerca per numero o riferimento con trattini, righe proprie dello spedizioniere e della piattaforma in inglese, righe da script del tracciamento personalizzato e come riconoscerle, ora di Pechino, risposta per numero sconosciuto, assenza di codice postale nel record, regola di passaggio a Swiss Post e formati Swiss Post, cosa Peek mostra, trattiene, esclude e affida
:::
