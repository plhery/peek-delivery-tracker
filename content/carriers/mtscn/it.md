---
title: Tracking Meiteshun: numero AMTS…FS, stati e chi contattare
description: Tracciare un pacco Meiteshun Logistics (美特顺) da Shenzhen: com'è fatto il numero AMTS…FS, cosa significano i passaggi e perché la consegna è scritta prima.
slug: meiteshun-tracking
published: 2026-11-18
updated: 2026-11-21
---

Meiteshun Logistics (美特顺物流, 深圳市美特顺物流有限公司, indicata anche come MTSCN) è uno spedizioniere con sede a Shenzhen, in Cina. I suoi numeri di tracciamento iniziano con AMTS e finiscono con FS: se il negozio che ha spedito il tuo ordine te ne ha comunicato uno, incollalo nel box qui sopra. Leggi però il tracking di Meiteshun con cautela: il suo sistema registra l'intero percorso del pacco, consegna compresa, già al momento della creazione dell'ordine, quindi un `Delivered` lì non dimostra che il pacco sia davvero arrivato.

## Numero di tracking Meiteshun: com'è fatto

Le lettere di vettura di Meiteshun sono composte da AMTS, nove cifre e FS, per un totale di 15 caratteri. Con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `AMTS482910517FS` | AMTS, nove cifre e FS: una lettera di vettura di Meiteshun. |
| `AMTS48291051FS` | Solo otto cifre: non è il formato di Meiteshun. Controlla che non manchi una cifra. |
| `RR482910576CN` | Due lettere, nove cifre e CN: un numero di [China Post](carrier:china-post), non di Meiteshun. |

Minuscole e spazi non contano quando incolli il codice in Peek.

**Dove trovarlo.** È il venditore a prenotare la spedizione con Meiteshun e a passarti il numero. Se non lo trovi, leggi [dove si trova il codice tracking](guide:find-tracking-number).

**Un riferimento del negozio.** Il sito di tracking di Meiteshun trova un ordine anche tramite il riferimento interno del venditore, purché digitato esattamente come fornito, trattini compresi, mostrando poi il relativo numero AMTS.

**Un link con delle cifre al posto del dominio.** Il tracking di Meiteshun gira su un semplice indirizzo numerico di server in HTTP non cifrato, senza un nome di dominio. Un link diretto contiene quell'indirizzo numerico al posto del nome del sito, oltre a `trackIndex.htm`, `documentCode=` e il tuo numero o riferimento: puoi incollarlo in Peek così com'è.

## Stati del tracking Meiteshun: cosa significano

Ogni ordine Meiteshun segue lo stesso copione, e la maggior parte dei passaggi viene scritta in anticipo (vedi sotto). Ecco le diciture in inglese nell'ordine in cui scorrono, con lo stato mostrato da Peek finché ciascuna è la più recente:

| Meiteshun scrive | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `The package has been collected and is ready to be sent to the distribution center` | Il primo passaggio: la presa in carico di Meiteshun a Shenzhen. | «Preso in carico» |
| `item inbound in sorting center` | Ingresso in un centro di smistamento, con località `ShenZhen`. | «In transito» |
| `item outbound in sorting center` | Uscita da quel centro di smistamento. | «In transito» |
| `Arriving at the terminal` | In un terminal aeroportuale, con località `Hong Kong`. | «In transito» |
| `Leaving the terminal, the airline will take off` | Il volo decolla da Hong Kong. | «In transito» |
| `Arriving at the destination country` | Atterrato nel tuo Paese, indicato dal codice a due lettere (`IT` per l'Italia). | «In transito» |
| `Customs clearanceisin progress` | Dogana d'importazione nel tuo Paese. Gli spazi mancanti sono di Meiteshun. | «In dogana» |
| `Customs clearance completed` | Sdoganato e di nuovo in viaggio. | «In transito» |
| `Arriving at the mail processing center` | In un centro di lavorazione postale del tuo Paese. | «In transito» |
| `Arrive at the sorting and distribution center` | In un centro di smistamento e distribuzione vicino a te, con la tua città o regione. | «In transito» |
| `package is on the way` | Il passaggio prima della consegna. Non significa che il pacco sia uscito in consegna. | «In transito» |
| `Delivered` | L'ultimo passaggio del copione (vedi sotto). | «Consegnato», salvo che Peek tenga da parte il passaggio (sotto) |

Per un ordine ancora privo di passaggi, la piattaforma scrive da sola `货物电子信息已经收到`, cioè che i dati elettronici della merce sono stati ricevuti: il venditore ha creato l'ordine, ma nessuno ha ancora scansionato il pacco. Peek mostra la riga in cinese sotto «Etichetta creata».

Il luogo di ciascun passaggio compare all'inizio della riga prima di una virgola, mentre i passaggi nel tuo Paese aggiungono la tua città o regione e il CAP dopo il testo. Tutti gli orari seguono l'ora di Pechino (UTC+8), anche per i passaggi in Italia: le 21:00 sul tracking di Meiteshun corrispondono alle 14:00 in Italia e in Svizzera con l'ora solare e alle 15:00 con l'ora legale (dall'ultima domenica di marzo all'ultima domenica di ottobre).

**Passaggi scritti in anticipo.** Meiteshun appoggia il proprio tracking su Hualei (华磊), una piattaforma per spedizionieri usata anche da altri operatori cinesi come [GZYL](carrier:gzyl) e Pengyou. Su Meiteshun tutti i passaggi di un ordine vengono scritti nel momento stesso in cui l'ordine è creato, ciascuno già con l'orario previsto, dal ritiro fino a `Delivered`, e compaiono man mano che arriva quell'ora. Sono previsioni, non scansioni reali del pacco: dicono dove era previsto che si trovasse in una certa data. Anche i servizi di tracciamento che copiano i dati di Meiteshun mostrano gli stessi passaggi.

## Chi consegna i pacchi Meiteshun

I passaggi di Meiteshun nel tuo Paese non indicano mai quale corriere effettua la consegna finale. Il nome del servizio riportato nei dati è MTS全球小包 (piccolo pacco globale MTS). Sulla pagina di tracking di Meiteshun, un codice sotto 渠道转单号 (numero di trasferimento), oppure sotto 跟踪号码 (numero di tracciamento) quando è diverso dal tuo numero AMTS, può essere quello del corriere locale: aggiungilo a Peek come pacco separato.

Peek non ha trovato tempi di consegna dichiarati da Meiteshun, né regole su tentativi di consegna, punti di ritiro o giorni di giacenza. Chiedi al venditore quale corriere consegna il tuo ordine e con quale codice di tracciamento: fanno fede il tracking e le regole di quell'operatore. Se ti vengono chiesti oneri doganali — che nell'Unione europea comprendono un dazio di 3 € per categoria di merce sulle spedizioni provenienti da fuori del territorio doganale UE fino a 150 € dal 1° luglio 2026 e una tassa di gestione dell'Unione di 2 € per categoria di merce qualunque sia il valore dal 1° novembre 2026 (in Italia Livigno è fuori sia dal territorio doganale UE sia dall'area IVA, mentre Campione d'Italia rientra nel territorio doganale UE ma è fuori dall'area IVA) — consulta [pacco fermo in dogana](guide:customs).

## Pacco Meiteshun fermo o che non arriva

**`Delivered`, ma non è arrivato nulla.** Il passaggio può essere la previsione del copione e non una scansione. Avvisa il venditore che il pacco non è stato consegnato e chiedigli il numero di tracciamento del corriere incaricato della consegna finale. Più indicazioni in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Tutti i passaggi puntuali, ma nessun pacco.** Poiché le tappe sono state scritte alla creazione dell'ordine, mostrano il programma e non dove si trova davvero la spedizione. Regolati sulla stima di consegna indicata dal negozio e contatta il venditore una volta scaduta.

**Fermo a Shenzhen in Peek.** Se Peek mostra soltanto i primi passaggi a Shenzhen mentre un link del venditore ne elenca altri, le tappe che mancano tra le scansioni di Peek sono state scritte in anticipo oppure portano una data successiva al controllo (vedi sotto). Una tappa scritta in anticipo non conta mai come scansione: non modifica lo stato né la data prevista e non invia notifiche. Fino alla consegna o al reso, Peek apre la cronologia del tracciamento con «Altri siti possono mostrare più passaggi, ma sono solo previsioni.» e, sotto «Mostrali», elenca in grigio le tappe che ha tenuto da parte, ognuna con «Previsto per» e il suo giorno. Cerca sul sito di Meiteshun il corriere che consegna nel tuo Paese e il suo numero di tracciamento; se non c'è, chiedi al venditore. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Nessun risultato.** Per un numero che non conosce, il sistema di Meiteshun mostra il codice sotto 参考号 (riferimento) lasciando vuoti tutti gli altri campi. Peek interpreta questa risposta come una spedizione già registrata da Meiteshun ma ancora senza scansioni: continua a controllare con la cadenza indicata più sotto per le tappe scritte in anticipo e mostra «In attesa di Meiteshun Logistics», senza chiederti di ricontrollare il numero. Verificalo quindi tu stesso con il messaggio del venditore.

**Perso, danneggiato o restituito al mittente.** La spedizione con Meiteshun l'ha prenotata il venditore: chiedi al negozio o al marketplace un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## Meiteshun contatti: telefono e assistenza clienti

Per qualsiasi problema con il tuo ordine, rivolgiti prima al venditore, che ha affidato la spedizione a Meiteshun; se non risponde, contatta il marketplace. Se né il venditore né il marketplace risolvono, la scheda di 17TRACK dedicata a Meiteshun riporta un indirizzo e-mail (`szmeiteshun@163.com`), il sito web (`mtswl.com.cn`) e un ufficio nel distretto di Longgang a Shenzhen (Qianlong Logistics Park, Pinghu Street), ma nessun numero di telefono: scrivi indicando il tuo codice AMTS e il nome del negozio.

## Tracciare un pacco Meiteshun con Peek

[Peek](/) riconosce un codice Meiteshun dalla forma: AMTS, nove cifre e FS, non usata da nessun altro corriere del suo catalogo. Puoi incollare anche un link al tracking di Meiteshun contenente `documentCode=` seguito dal numero o dal riferimento del venditore. Un riferimento interno del negozio non ha invece una forma riconoscibile: incollalo comunque, dato che Peek segue più di 4.000 corrieri, poi premi «Cambia» e scegli Meiteshun Logistics (la trovi anche digitando 美特顺 o MTSCN), oppure usa «Cambia corriere» su un pacco già aggiunto. Peek mostra allora anche il codice AMTS sotto «Meiteshun Logistics lo traccia con il numero». Peek tuttavia rimuove i trattini, sia digitati sia presenti in un link: cerca un riferimento con i trattini direttamente sul sito di Meiteshun oppure chiedi il codice AMTS al venditore.

Peek legge il sistema di tracking di Meiteshun e mostra lo stato, ogni passaggio con il relativo luogo e il Paese di destinazione. I passaggi restano in inglese così come li scrive Meiteshun, tranne `Delivered` che nella cronologia compare come «Consegnato»: `Customs clearanceisin progress` conta come «In dogana», mentre `package is on the way` resta «In transito» e non «In consegna». Dai passaggi nel tuo Paese Peek conserva la tua città e toglie il CAP. Gli orari vengono letti sull'ora di Pechino e mostrati nel fuso orario del tuo dispositivo. Peek esclude nomi, indirizzo, peso, spese, firma e prova di consegna eventualmente presenti nei dati, e non mostra una data di consegna prevista perché Meiteshun non ne fornisce.

Peek tiene da parte ogni passaggio scritto più di dodici ore prima dell'orario che riporta e ogni passaggio con data successiva al momento della verifica, secondo l'ora di Pechino. Si basa sui passaggi conservati e non sullo stato riassuntivo della scheda. Quando l'intero copione di un ordine è stato scritto alla creazione, Peek mantiene solo i passaggi datati prima di quel momento o fino a sei ore dopo, lasciando quindi da parte il `Delivered` generato allora. Quando deve leggere la pagina `trackIndex.htm` perché l'interfaccia non restituisce tappe per il numero, tiene da parte ogni `Delivered` presente lì, dato che quella pagina non indica quando ciascuna riga è stata scritta. Un ordine le cui tappe siano tutte tenute da parte non ha ancora cronologia in Peek e mostra «In attesa di Meiteshun Logistics», senza avvisi sul numero: Peek considera le tappe scritte in anticipo prova che Meiteshun ha in carico l'ordine e continua a interrogare il suo sistema ogni 6 ore dopo le prime ore nell'app per iPhone o Android, o nel tuo account. Per una ricerca senza account interroga nelle prime 6 ore, poi quando apri il link (al massimo ogni 6 ore e, dopo i primi due giorni, al massimo una volta al giorno) e, finché le notifiche sono attive, una volta al giorno fino a 30 giorni dopo la ricerca. Che tenga da parte alcune tappe o tutte, Peek scarta anche le copie di quelle tappe provenienti dai servizi di tracciamento.

Peek segue la spedizione sul sistema di Meiteshun. L'unico passaggio automatico ad altro corriere riguarda la [Posta Svizzera](carrier:swiss-post), quando i dati indicano una linea e un numero della Posta Svizzera per un pacco diretto in Svizzera o nel Liechtenstein (mostrando «Consegna con Swiss Post», oppure «Annunciato a Swiss Post, non ancora ricevuto» finché non c'è una scansione propria). Per qualsiasi altro codice del corriere locale fornito dal venditore, aggiungilo come pacco a sé.

Peek controlla fino a ogni 10 minuti mentre tieni aperto il pacco o nel giorno della consegna con le notifiche attive, e fino a ogni 2 minuti nel giorno in cui un pacco entra in consegna (dalle 6:00 ora locale o da mezz'ora prima di una fascia oraria comunicata, finché risponde direttamente il sistema di Meiteshun o della Posta Svizzera; tramite i servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni, e fino a ogni 15 minuti nel giorno della consegna mentre hai il pacco aperto o le notifiche attive), benché nessun passaggio noto di Meiteshun attivi quello stato. Una volta passata la spedizione alla Posta Svizzera, Peek ne segue il ritmo. Dopo due giorni senza novità (o 12 ore su «Etichetta creata» o «Preso in carico») controlla ogni ora, condizione che un ordine scritto interamente alla creazione raggiunge presto. Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna» («Passaggi importanti» avvisa per `Customs clearanceisin progress` e `Delivered`), e Peek non può contattare Meiteshun né il venditore al posto tuo.

## Domande sul tracking Meiteshun

### Che cos'è Meiteshun Logistics (美特顺)?

È 深圳市美特顺物流有限公司, Shenzhen Meiteshun Logistics, uno spedizioniere di Shenzhen, in Cina. Le sue lettere di vettura hanno il formato AMTS, nove cifre e FS, e nei suoi dati il servizio è indicato come MTS全球小包.

### Un numero AMTS è un numero di China Post?

No. AMTS, nove cifre e FS è il formato esclusivo di Meiteshun. I codici internazionali di China Post hanno due lettere, nove cifre e CN.

### Perché Meiteshun dice che il pacco è consegnato se non l'ho ricevuto?

Con ogni probabilità perché il sistema di Meiteshun scrive tutte le tappe dell'ordine, consegna inclusa, già quando l'ordine viene creato. Un `Delivered` scritto in quel modo è una previsione, non una scansione. Avvisa il venditore che il pacco non è arrivato.

### Chi consegna i pacchi Meiteshun in Italia?

I passaggi di Meiteshun non lo dicono: nessuno nomina un corriere di consegna. La sua pagina di tracking può riportare il codice del corriere finale nella colonna 渠道转单号 (numero di trasferimento); se non c'è, chiedi al venditore quale azienda consegna il tuo ordine e con quale numero di tracciamento.

### Chi contatto per un pacco Meiteshun smarrito?

Il venditore da cui hai acquistato, che ha prenotato la spedizione con Meiteshun. Se né il venditore né il marketplace aiutano, la pagina di 17TRACK dedicata a Meiteshun indica l'indirizzo e-mail `szmeiteshun@163.com`.

:::sources
- [17TRACK: MTSCN tracking (in inglese)](https://www.17track.net/en/carriers/mtscn) – indirizzo e-mail, sito web e ufficio di Shenzhen (distretto di Longgang) indicati per Meiteshun, nessun numero di telefono
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € sulle spedizioni fino a 150 € dal 1° luglio 2026 e tassa di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tassa di 2 € per categoria di merce venduta a distanza qualunque sia il valore, calcolata per voce doganale come il dazio di 3 €
- [Commissione europea: Territorial Scope (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale UE e dall'area IVA; Campione d'Italia dentro il territorio doganale UE ma fuori dall'area IVA
- [Commissione europea: Summertime (in inglese)](https://transport.ec.europa.eu/transport-themes/summertime_en) – ora legale europea dall'ultima domenica di marzo all'ultima domenica di ottobre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ragione sociale, piattaforma Hualei, formato AMTS, sistema di tracking su indirizzo numerico e link riconosciuti, ricerca per riferimento con trattini, copione e diciture dei passaggi, località e ora di Pechino, tappe scritte in anticipo, risposta per un numero sconosciuto, riga in cinese della piattaforma, nome del servizio, assenza di corrieri di consegna nei passaggi, colonne del numero di trasferimento e di tracciamento, passaggio alla Posta Svizzera e cosa Peek mostra, tiene da parte e tralascia
:::
