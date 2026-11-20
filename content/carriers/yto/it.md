---
title: Tracciamento YTO Express: numero YT, stati e consegna
description: Traccia un pacco YTO Express (圆通): formato del numero YT, significato delle scansioni cinesi, consegna in Cina, tempi verso l'Italia e contatti.
slug: tracciamento-yto-express
published: 2026-11-18
updated: 2026-11-20
---

YTO Express (圆通速递, Yuantong) è un corriere espresso cinese fondato a Shanghai nel 2000, con oltre 5.000 filiali in tutta la Cina. Un numero YTO appartiene il più delle volte a una spedizione interna in Cina: per esempio un ordine su Taobao, Pinduoduo o Douyin — piattaforme servite da YTO — diretto al magazzino di uno spedizioniere o a un indirizzo locale. Puoi seguirlo sul [tracker di YTO](https://www.yto.net.cn/ytoExpress/waybill/search), disponibile solo in cinese, oppure nel riquadro qui sopra. Le spedizioni internazionali di YTO viaggiano invece con YTO International e il relativo tracker su [ytoglobal.com](https://www.ytoglobal.com/service).

## Numero di tracciamento YTO Express: come si presenta

YTO lo chiama numero di lettera di vettura (`运单号`). Con cifre inventate:

| Numero | Che cos'è |
| --- | --- |
| `YT2468135790246` | `YT` e 13 cifre: una lettera di vettura YTO Express. |
| `D24681357902` | `D` e 11 cifre: un formato YTO, ma anche i numeri di Colis Privé possono avere questa struttura. |
| `G24681357902` | `G` e 11 cifre: un altro formato YTO che può sovrapporsi a Colis Privé. |
| `YT2612468135790246` | `YT` e 16 cifre: non è YTO, ma [YunExpress](carrier:yunexpress). |

Minuscolo e spazi non contano in [Peek](/). Il tracker ufficiale di YTO accetta fino a 10 numeri alla volta, separati da virgole, barre o spazi.

**Dove trovarlo.** Il venditore lo indica nei dettagli di spedizione dell'ordine ed è stampato anche sull'etichetta del pacco. Se non lo trovi, consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

**Ricerca sul sito di YTO.** Anche partendo dalla versione inglese del sito di YTO, la pagina di ricerca resta in cinese: inserisci il codice nel campo di testo e premi `查询` (cerca). Secondo l'informativa sulla privacy di YTO, una lettera di vettura resta consultabile per un periodo compreso tra tre e sei mesi a seconda del canale d'ordine; trascorso questo termine, YTO la rimuove dalla ricerca.

## Significato degli stati di tracciamento YTO Express

Ogni scansione nei dati di YTO riporta una breve dicitura in cinese, un orario sull'ora cinese (`UTC+8`) e la filiale o stazione che l'ha registrata. Ecco come Peek mostra le scansioni principali:

| Scansione YTO | In Peek | Cosa è successo |
| --- | --- | --- |
| `揽收扫描` | `Picked up` | Ritirato presso il mittente. |
| `业务员收件` | `Picked up` | Preso in consegna di persona da un corriere YTO dal mittente. |
| `装件入车扫描` | `Loaded for transport` | Caricato su un veicolo per la tratta successiva. |
| `下车扫描` | `Unloaded at a sorting centre` | Scaricato in un centro di smistamento o in una filiale. |
| `快件城市信息` | «In transito» | Nota di passaggio attraverso una città lungo il percorso. |
| `冬运会安检` | `Security check` | Controllo di sicurezza nella rete YTO legato a un evento sportivo invernale; il pacco prosegue poi il viaggio. |
| `派件扫描` | «In consegna» | Affidato al corriere per la consegna. |
| `入柜\|入库` | `In a parcel locker or pickup station` | Depositato in un armadietto automatico o registrato in un punto di ritiro. |
| `小件员取出` | `Taken out of the locker by the courier` | Il corriere ha ripreso il pacco dall'armadietto o dalla stazione. |
| `PDA正常签收扫描` | «Consegnato» | Consegnato contro firma (`签收`), registrato sul terminale portatile del corriere. |
| `退回件扫描` | `Return to the sender started` | È iniziato il rientro al mittente. |

Le tre voci tra virgolette compaiono in italiano in Peek; le altre righe della tabella restano in inglese, mentre eventuali scansioni non elencate restano nel cinese originale di YTO. Lo stato generale del pacco è sempre in italiano, ad esempio «Preso in carico» o «Pronto per il ritiro». Con `入柜|入库` lo stato passa a «Pronto per il ritiro»; se il corriere estrae di nuovo il pacco (`小件员取出`), torna a «In transito».

Se l'indirizzo di destinazione è il magazzino in Cina di uno spedizioniere internazionale, `PDA正常签收扫描` indica che quel magazzino ha firmato la ricezione. Da lì in avanti subentra il codice di tracciamento proprio dello spedizioniere.

## Consegna YTO in Cina: corriere, armadietti e stazioni

In **Cina**, un corriere YTO porta il pacco all'indirizzo (`派件扫描`) oppure il pacco viene lasciato in un armadietto o in una stazione di ritiro (`入柜|入库`). YTO indica `95161` come «Courier Outbound Hotline», il numero da cui chiamano i suoi corrieri.

Le norme sul mercato dei corrieri espressi in vigore in **Cina** dal 1° marzo 2024 stabiliscono che:

- YTO deve informarti che puoi controllare il pacco alla consegna confrontandolo con la lettera di vettura; se l'imballaggio è visibilmente danneggiato o il contenuto è fragile, puoi ispezionare l'interno o rifiutare la consegna.
- Nessun operatore espresso può confermare la ricezione al posto tuo né depositare il pacco in un armadietto intelligente o in una stazione di servizio senza il tuo consenso.

Per far restituire un pacco o cambiarne l'indirizzo prima della consegna (`退回/更址`), YTO rimanda tra l'altro alla propria app e al miniprogramma. L'informativa sulla privacy precisa che per modificare indirizzo o recapito telefonico occorre rivolgersi al servizio clienti.

## YTO International: spedizioni fuori dalla Cina

YTO International (圆通国际) risale al 2006 e dichiara oltre 50 uffici in 18 paesi e regioni. Il suo tracker su [ytoglobal.com/service](https://www.ytoglobal.com/service) è disponibile in inglese («Express Freight Tracking») e in cinese (`国际快递查询`) e accetta fino a 10 numeri separati da virgole o punti e virgola.

Le pagine di YTO non specificano quale corriere effettui il recapito finale nel tuo paese, parlando soltanto di collaborazioni con primari operatori logistici globali («top-notch global logistics service providers»). Per International Standard Express, il servizio destinato ai privati, YTO indica dal proprio magazzino internazionale tempi puramente indicativi («for reference only») di 7–12 giorni lavorativi verso Italia, Svizzera, San Marino, Francia, Germania, Spagna, Portogallo, Polonia, Regno Unito e Canada (12–17 verso Bulgaria e Romania), 9–12 verso gli Stati Uniti e 8–13 verso l'Australia.

In caso di assenza del destinatario, le FAQ di YTO International invitano a seguire l'avviso di riconsegna («Redelivery Notification») per fissare gratuitamente un secondo tentativo; se anche questo non va a buon fine, YTO chiede istruzioni al mittente. Per concordare in anticipo un orario di consegna, le FAQ rimandano al numero `400 60 95554`. Sulle spedizioni aeree, dogana e sicurezza aeroportuale ispezionano i colli e la dogana può sequestrare o distruggere un invio che contenga articoli vietati.

**Dogana, territori e avvisi antifrode.** Dazi e imposte dipendono dal paese di destinazione: vedi [pacchi fermi in dogana](guide:customs). Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati a distanza da fuori del territorio doganale dell'UE in spedizioni di valore intrinseco fino a 150 € sono soggetti a un dazio doganale di 3 € per categoria di articolo; dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione di 2 € per categoria di articolo qualunque sia il valore della spedizione (una categoria raggruppa le merci di una spedizione aventi la stessa classificazione tariffaria, la stessa descrizione e, ove debba essere dichiarata, la stessa origine, indipendentemente dal numero di pezzi). In **Italia**, Livigno resta fuori sia dal territorio doganale sia dal territorio IVA dell'UE, mentre Campione d'Italia e le acque italiane del lago di Lugano rientrano nel territorio doganale dell'UE ma sono fuori dal territorio IVA dell'UE; per i pacchi provenienti da Paesi extra UE gestiti da [Poste Italiane](carrier:poste-italiane) in **Italia**, gli importi dovuti (IVA, dazio, commissione di gestione dell'Unione e Diritti Postali) si pagano alla consegna, in contanti o tramite POS al corriere fino a 999 €, oppure all'Ufficio Postale al momento del ritiro, o ancora online prima della consegna sull'App Poste Italiane e su poste.it, anche dal link che Poste Italiane invia per notifica (in App, per e-mail, in Bacheca poste.it o per SMS) se il mittente ha fornito i tuoi contatti; Poste Italiane ricorda nelle sue pagine antifrode che né Poste Italiane né le Forze dell'Ordine richiedono mai, tramite e-mail, SMS, lettere, telefonate o social network, dati personali, credenziali di accesso ai servizi bancari, codici delle carte di pagamento e codici dispositivi o di autorizzazione. In **Svizzera**, l'Ufficio federale della dogana e della sicurezza dei confini (UDSC) non riscuote dazi né IVA quando il rispettivo importo ammonta fino a 5 franchi svizzeri, fermi restando i costi di sdoganamento applicati dal trasportatore. Approfondimenti anche in [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco YTO fermo, smarrito o rispedito al mittente

**Nessuna scansione.** Una lettera di vettura appena creata non mostra eventi finché YTO non scansiona il pacco per la prima volta. Se resta vuota, verifica il codice con il venditore; su una spedizione vecchia, i dati potrebbero invece essere già stati cancellati dopo i tre–sei mesi previsti.

**Tracciamento bloccato.** In **Cina**, il contratto di servizio espresso nazionale di YTO con i mittenti considera smarrito un pacco rimasto senza aggiornamenti per 4 giorni dopo il primo inserimento oppure non ancora firmato 7 giorni dopo la data prevista. Contatta il venditore che ha prenotato la spedizione affinché apra una verifica con YTO. Altri dettagli in [tracciamento fermo](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Smarrito o danneggiato.** Per un acquisto online è il venditore ad aver pagato il trasporto: rivolgiti prima a lui. In base allo stesso contratto in **Cina**, un pacco non assicurato (`保价`) viene rimborsato fino a 300 yuan (fino a 100 yuan per i documenti), mentre un pacco con valore dichiarato viene risarcito per la perdita effettiva entro la somma assicurata. I reclami vanno presentati per telefono alla filiale YTO di partenza o al servizio clienti YTO; sul sito di YTO esistono inoltre, dietro accesso riservato, pagine per reclami (`投诉反馈`) e indennizzi su spedizioni assicurate (`保价理赔`). Se YTO non risponde o l'esito non è soddisfacente, le norme cinesi consentono di presentare reclamo all'autorità postale State Post Bureau (`消费者申诉` sul relativo sito). Vedi anche [pacco smarrito](guide:lost-parcel).

**In reso.** Dopo `退回件扫描` il pacco sta tornando indietro e le successive scansioni di consegna riguardano la restituzione al mittente. Contatta il venditore; vedi [restituito al mittente](guide:returned-to-sender).

**Risulta consegnato ma non c'è.** In **Cina**, il contratto di YTO consente la firma da parte di conviventi o di un delegato, oppure il deposito in un armadietto o in una stazione vicino all'indirizzo: controlla questi punti e la portineria dello stabile. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Numero di telefono e assistenza clienti YTO Express

Sulle proprie pagine YTO indica questi recapiti:

- **`95554`**: «Service Hotline» (`服务热线`) per YTO Express in Cina.
- **`400 60 95554`**: «Service Hotline» di YTO International per le spedizioni internazionali.
- **`95161`**: «Courier Outbound Hotline», il numero utilizzato dai corrieri YTO per chiamare i destinatari.
- **`021-69777888`**: riportato accanto agli altri numeri senza ulteriore etichetta.

YTO non pubblica orari di servizio né lingue parlate su queste linee. Sulla home page cinese è presente il pulsante dell'assistenza online (`在线客服`), mentre YTO International mette a disposizione un modulo di contatto. Per domande sull'ordine o rimborsi, rivolgiti al venditore.

## Tracciare un pacco YTO Express con Peek

[Peek](/) riconosce subito YTO Express da `YT` seguito da 13 cifre. Un codice `D` o `G` con 11 cifre è invece compatibile anche con Colis Privé, quindi Peek non può attribuirlo con certezza dalla sola forma. Incollalo comunque: Peek segue oltre 3.500 corrieri e interroga i servizi di tracciamento («Verifica dei servizi di tracciamento…»). Se YTO Express non viene selezionato automaticamente, tocca «Cambia» e sceglilo sotto «Compatibili con questo numero», oppure usa «Cambia corriere» su un pacco già salvato. L'integrazione YTO di Peek legge anche lettere di vettura YTO con altri formati selezionando YTO Express da «Tutti i corrieri». Anche un link `yto.net.cn` contenente `mailNo=` o `waybillNo=` con il numero assegna direttamente il pacco a YTO.

Peek legge il tracciamento nazionale cinese di YTO, non quello di YTO International: mostra lo stato, le scansioni restituite da YTO con la città della filiale o stazione (oppure il nome cinese se non riesce a ricondurla a una città) e l'orario di consegna. Gli orari vengono letti sull'ora della Cina (`UTC+8`: le `20:00` a Pechino corrispondono in Italia e in Svizzera alle `13:00` CET in inverno e alle `14:00` CEST con l'ora legale) e convertiti nel fuso orario del tuo dispositivo; su peektracker.com e nelle app per iPhone e Android la cronologia termina con «Orari nel tuo fuso orario:» seguito dal nome del fuso. Le scansioni duplicate compaiono una sola volta. Subito dopo `退回件扫描` lo stato diventa «Problema segnalato», e Peek riporta le tappe del rientro in inglese come `Out for delivery back to the sender` e `In a parcel locker or station on its way back`, quindi la firma del mittente come `Returned to the sender` con stato «Restituito al mittente».

YTO non fornisce una data di consegna prevista. Peek omette nomi e numeri di telefono dei corrieri, dati del destinatario e indirizzi di ritiro, visibili solo sul tracker di YTO. Poiché YTO risponde per qualsiasi numero formalmente valido, l'assenza di scansioni non dimostra un errore di battitura: Peek mostra «In attesa di YTO Express» e continua a controllare. Se dopo due giorni un pacco registrato sotto YTO non è ancora riconosciuto da YTO e nessun'altra fonte ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a verifiche giornaliere, poi settimanali una volta trascorsa una settimana dall'aggiunta.

Peek controlla gli aggiornamenti fino a ogni 10 minuti mentre hai il pacco aperto o nel giorno di consegna con le notifiche attive, e fino a ogni 2 minuti dalle 6:00 (ora cinese) nel giorno in cui YTO scansiona il pacco in consegna (`派件扫描`) finché risponde il tracker di YTO; tramite i servizi di tracciamento controlla al massimo ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno di consegna mentre hai il pacco aperto o le notifiche attive. Negli altri casi e dopo due giorni senza nuove scansioni (12 ore finché mostra «Preso in carico») controlla ogni ora. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare YTO né il venditore per conto tuo.

## Domande frequenti sul tracciamento YTO Express

### YTO Express è affidabile?

Sì. YTO Express è stata fondata a Shanghai il 28 maggio 2000. Il gruppo conta due società quotate in borsa — YTO Express (codice 600233) e YTO International (06123.HK) — e dichiara 550.000 dipendenti a fine 2024.

### Cosa significa 签收 nel tracciamento YTO?

Consegnato contro firma: il corriere ha consegnato il pacco e lo ha registrato sul proprio terminale (`PDA正常签收扫描`), mostrato da Peek come «Consegnato». Può aver firmato tu, una persona autorizzata al tuo indirizzo o il magazzino di uno spedizioniere. Se nessuno ha il pacco, vedi [consegnato ma non ricevuto](guide:delivered-not-received).

### Perché il mio numero YTO non mostra aggiornamenti?

Perché YTO non ha ancora scansionato il pacco, il numero contiene un refuso oppure sono trascorsi più dei tre–sei mesi durante i quali YTO mantiene consultabili le lettere di vettura secondo la sua informativa sulla privacy. Controlla il codice con il venditore o incollalo in Peek, che continuerà a interrogarlo.

### YTO Express consegna fuori dalla Cina?

Sì, tramite YTO International e il suo tracker su ytoglobal.com. Le pagine di YTO non indicano quale corriere effettui il recapito finale nel paese di arrivo e riportano tempi solo indicativi: 7–12 giorni lavorativi verso l'Italia, la Svizzera, San Marino e gran parte dell'Europa occidentale e centrale.

### Qual è il numero di telefono di YTO Express?

`95554` all'interno della Cina e `400 60 95554` per YTO International, entrambi senza orari pubblicati. Le chiamate provenienti dal `95161` arrivano dai corrieri YTO.

:::sources
- [YTO Express: About YTO (in inglese)](https://www.yto.net.cn/en/aboutYto/about) – fondazione il 28 maggio 2000 a Shanghai, società quotate, oltre 5.000 filiali e 550.000 dipendenti
- [YTO Express: 我要查件 (ricerca lettere di vettura, in cinese)](https://www.yto.net.cn/ytoExpress/waybill/search) – tracker solo in cinese, fino a 10 numeri, pagine reclami e indennizzi
- [YTO Express: Domestic Express Services (in inglese)](https://www.yto.net.cn/en/domesticService) – piattaforme servite, reso o cambio indirizzo prima della consegna, linee 95554 e 95161
- [YTO Express: Home page (in cinese)](https://www.yto.net.cn/) – assistenza clienti online `在线客服`
- [YTO Express: 国内快递 (servizi nazionali, in cinese)](https://www.yto.net.cn/domesticService) – `退回/更址`, `服务热线`, `快递员外呼热线`
- [YTO Express: 国内快递服务协议 (contratto di servizio espresso nazionale, in cinese)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=DESA) – soglie di smarrimento in Cina, indennizzo con e senza valore dichiarato, reclami e chi può firmare
- [YTO Express: 圆通速递隐私政策 (informativa sulla privacy, in cinese)](https://mec.yto.net.cn/h5/#/subPages/agreement/index?code=PCPrivacyAgreement) – consultazione per tre–sei mesi e modifica dell'indirizzo tramite il servizio clienti
- [YTO Express: 寄快递 (spedire un pacco, in cinese)](https://www.yto.net.cn/ytoExpress/subCreatePages/create) – rimborso fino a 300 yuan senza valore dichiarato
- [YTO Express: International Business Service (in inglese)](https://www.yto.net.cn/en/global/globalProduct) – linea YTO International 4006095554 e 021-69777888
- [YTO International: Company Introduction (in inglese)](https://www.ytoglobal.com/about-us) – attività dal 2006, oltre 50 uffici in 18 paesi e regioni e partner globali
- [YTO International: Express Freight Tracking](https://www.ytoglobal.com/service) – tracker in inglese e cinese fino a 10 numeri
- [YTO International: International Standard Express (in inglese)](https://www.ytoglobal.com/product/express-intl) – servizio per privati e tempi indicativi verso Italia, Svizzera, San Marino e altri paesi
- [YTO International: FAQ (in inglese)](https://www.ytoglobal.com/rich/question) – linea 400 60 95554, secondo tentativo gratuito, appuntamento e ispezione doganale
- [YTO International: Contact Us (in inglese)](https://www.ytoglobal.com/about-us/contact) – modulo di contatto
- [Ministero dei Trasporti della Cina: 快递市场管理办法 (misure sul mercato dei corrieri espressi, in cinese)](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – in vigore in Cina dal 1° marzo 2024, verifica alla consegna, consenso per armadietti o stazioni e reclamo alle autorità postali
- [State Post Bureau della Cina: 国家邮政局主要职责 (funzioni principali, in cinese)](https://www.spb.gov.cn/gjyzj/c100001/c100006/201306/2722edab189242549b320d06e0dbd382.shtml) – autorità di regolazione postale e dei corrieri espressi
- [State Post Bureau della Cina: home page (in cinese)](https://www.spb.gov.cn/) – sportello reclami dei consumatori (`消费者申诉`)
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per categoria di articolo fino a 150 € dal 1° luglio 2026 e commissione di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione dei 2 € qualunque sia il valore e definizione di categoria di articolo
- [Commissione europea: Territorial Scope (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA dell'UE; Campione d'Italia e acque italiane del lago di Lugano nel territorio doganale dell'UE e fuori dal territorio IVA dell'UE
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – pagamento in Italia degli importi dovuti alla consegna, all'Ufficio Postale al ritiro oppure online prima della consegna
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – avviso in Italia sul fatto che Poste Italiane e le Forze dell'Ordine non richiedono dati personali, credenziali o codici delle carte tramite e-mail, SMS, lettere, telefonate o social network
- [UDSC (Svizzera): Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – mancata riscossione in Svizzera di dazi e IVA fino a 5 franchi svizzeri
- [UDSC (Svizzera): Costi d'imposizione e tracciamento dei pacchi](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – costi di sdoganamento applicati dal trasportatore in Svizzera
- [Commissione europea: Summertime (in inglese)](https://transport.ec.europa.eu/transport-themes/summertime_en) – ora legale europea dall'ultima domenica di marzo all'ultima domenica di ottobre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati riconosciuti, link di `yto.net.cn`, traduzione delle scansioni di YTO, orario cinese, viaggio di reso e dati mostrati o omessi da Peek
:::
