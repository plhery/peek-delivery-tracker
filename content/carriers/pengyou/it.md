---
title: Tracciamento Pengyou: numeri PY, stati e chi sentire
description: Tracciamento Pengyou (捧友 / Pony Supply Chain): codice ZXA…PY, significato degli stati, tappe scritte in anticipo, chi consegna in Italia e chi contattare.
slug: tracciamento-pengyou
published: 2026-11-18
updated: 2026-11-27
---

Pengyou (捧友, 深圳市捧友供应链有限公司, nel logo inglese Shenzhen Pony Supply Chain Co., Ltd) è uno spedizioniere di Shenzhen che trasporta pacchi per via aerea verso Europa e Stati Uniti per venditori online ed esportatori. Per tracciare una spedizione, incolla il codice nel campo qui sopra (Peek riconosce subito i codici `ZXA`, nove cifre e `PY`) o inseriscilo, anche come riferimento del negozio, nel riquadro `运单查询` su szpy56.com; tieni presente che la sua piattaforma può scrivere in anticipo tutte le tappe fino alla consegna.

## Numero di tracciamento Pengyou: com'è fatto

Pengyou non pubblica alcun formato ufficiale e nessuna pagina pubblica mostra per intero un codice Pengyou. La struttura che [Peek](/) associa a Pengyou è composta da tre lettere, nove cifre e `PY` finale (14 caratteri). Con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `ZXA246813576PY` | `ZXA`, nove cifre e `PY`: una serie a cui risponde il tracker di Pengyou e che Peek riconosce subito. |
| `ZZZ999999980PY` | Tre lettere diverse da `ZXA`, nove cifre e `PY`: lo schema associato a Pengyou (corrispondenza debole finché il tracker non risponde). |
| `ZZZ24681357PY` | Solo otto cifre: non rispetta quello schema. Controlla se manca una cifra. |
| `RR246813576PY` | Due lettere, nove cifre e `PY`: un numero postale internazionale, non di Pengyou. |

Minuscole e spazi non fanno differenza quando incolli il codice in Peek.

**Dove trovarlo.** Il sito di Pengyou si definisce uno specialista nelle spedizioni per negozi web indipendenti che vendono in Europa e negli Stati Uniti (`欧美独立站运输专家`). A parte il riquadro di ricerca nella home page, nulla sul sito è pensato per chi riceve il pacco. Se non trovi il codice nella conferma d'ordine, consulta [come trovare il numero di tracciamento](guide:find-tracking-number).

**Riferimento del negozio.** Il tracker di Pengyou individua un ordine anche cercando il riferimento interno assegnato dal venditore.

## Stati di tracciamento Pengyou spiegati

Pengyou non pubblica alcun elenco dei propri stati. Ecco cosa può comparire sul suo tracker:

| Cosa vedi | Cosa significa per te |
| --- | --- |
| `Shenzhen Processing Center has received it` | Il centro di smistamento di Pengyou a Shenzhen ha ricevuto il pacco. È la prima scansione nelle mani di Pengyou. |
| Una città tra parentesi quadre dopo una riga, ad esempio `[Shenzhen City]` | Il luogo in cui è avvenuta quella scansione. |
| Tappe distanziate da intervalli perfettamente identici | Una cronologia scritta in anticipo al momento della creazione dell'ordine, non scansioni reali del tuo pacco (vedi sotto). |
| Il tuo codice sotto `参考号` (riferimento) e tutti gli altri campi vuoti | Il tracker di Pengyou non conosce questo numero. Verificalo con il venditore. |

**Tappe scritte in anticipo.** Sulla piattaforma di tracciamento usata da Pengyou, l'intera cronologia di un ordine può essere compilata al momento stesso in cui l'ordine viene creato: ogni passaggio fino alla consegna viene datato in avanti a intervalli fissi e mostrato man mano che la data trascorre. Sia il tracker di Pengyou sia i servizi di tracciamento che ne copiano i dati possono quindi presentare quelle righe come se fossero scansioni effettive. Si tratta invece di una previsione: indicano dove era pianificato che il pacco si trovasse in una certa data, non dove si trovi davvero. Non basarti su quelle righe per stimare il giorno di consegna e non considerare un `Delivered` generato così come prova di avvenuta consegna.

**Orari sull'orologio di Pechino.** Gli orari delle scansioni non riportano alcun fuso orario: seguono l'ora di Pechino (`UTC+8`). Durante l'ora solare europea (dall'ultima domenica di ottobre all'ultima domenica di marzo), le `20:00` di Pechino corrispondono alle `13:00` in Italia e nel Canton Ticino; durante l'ora legale (dall'ultima domenica di marzo all'ultima domenica di ottobre), corrispondono alle `14:00`.

## Chi consegna i pacchi Pengyou

Il sito di Pengyou dichiara di gestire principalmente linee verso l'Europa e gli Stati Uniti con «doppio sdoganamento e dazi inclusi» (`双清包税`), ossia sdoganamento in partenza e in arrivo con oneri compresi nella tariffa pagata dal mittente. La sede principale si trova presso l'aeroporto di Bao'an a Shenzhen, con filiali a Bantian, Guangzhou e Hong Kong.

La pagina iniziale elenca per nome quattro linee principali: `欧洲DHL`, `欧洲DHL专线` e `欧洲DPD专线` (`欧洲` significa Europa) oltre a `美国UPSP专线` (`美国` indica gli Stati Uniti). La pagina interna di ciascuna linea contiene solo il titolo. Nella sezione `合作伙伴` (partner) compaiono i loghi di DHL, UPS, FedEx, TNT, USPS, DPD e del servizio di tracciamento 17TRACK. Lo schema generale del servizio termina con la consegna a destinazione (`配送目的地`) e la firma del cliente, ma il sito non indica mai quale corriere consegni uno specifico pacco in Italia, né pubblica tempi di percorrenza, regole sui tentativi di recapito, punti di ritiro o giorni di giacenza.

**Dogana, territori e avvisi antifrode.** Se ti vengono richiesti oneri d'importazione per un pacco spedito con dazi inclusi, chiedi chiarimenti al venditore prima di pagare. Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati a distanza da fuori del territorio doganale dell'UE in spedizioni di valore intrinseco fino a 150 € sono soggetti a un dazio doganale di 3 € per categoria di articolo; dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione di 2 € per categoria di articolo qualunque sia il valore della spedizione (una categoria raggruppa le merci di una spedizione aventi la stessa classificazione tariffaria, la stessa descrizione e, ove debba essere dichiarata, la stessa origine, indipendentemente dal numero di pezzi). In **Italia**, Livigno resta fuori sia dal territorio doganale sia dal territorio IVA dell'UE, mentre Campione d'Italia e le acque italiane del lago di Lugano rientrano nel territorio doganale dell'UE ma sono fuori dal territorio IVA dell'UE; per i pacchi provenienti da Paesi extra UE gestiti da Poste Italiane in **Italia**, gli importi dovuti (IVA, dazio, commissione di gestione dell'Unione e Diritti Postali) si pagano alla consegna, in contanti o tramite POS al corriere fino a 999 €, oppure all'Ufficio Postale al momento del ritiro, o ancora online prima della consegna sull'App Poste Italiane e su poste.it, anche dal link che Poste Italiane invia per notifica (in App, per e-mail, in Bacheca poste.it o per SMS) se il mittente ha fornito i tuoi contatti; Poste Italiane ricorda nelle sue pagine antifrode che né Poste Italiane né le Forze dell'Ordine richiedono mai, tramite e-mail, SMS, lettere, telefonate o social network, dati personali, credenziali di accesso ai servizi bancari, codici delle carte di pagamento e codici dispositivi o di autorizzazione. In **Svizzera**, l'Ufficio federale della dogana e della sicurezza dei confini (UDSC) non riscuote dazi né IVA quando il rispettivo importo ammonta fino a 5 franchi svizzeri, fermi restando i costi di sdoganamento applicati dal trasportatore. Approfondimenti in [pacchi fermi in dogana](guide:customs) e [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco Pengyou che non arriva o tracciamento fermo

**Tappe troppo regolari.** Appartengono al calendario scritto in anticipo all'apertura dell'ordine, non a scansioni fisiche. Se per diversi giorni non segue alcuna scansione reale, chiedi al venditore se il pacco sia stato effettivamente affidato allo spedizioniere.

**Fermo dopo Shenzhen.** Se la cronologia si interrompe a `Shenzhen Processing Center has received it` o poco dopo, chiedi al venditore se un altro corriere effettua la consegna nel tuo Paese e, in tal caso, fatti dare il relativo numero di tracciamento per aggiungerlo a Peek come spedizione separata. Vedi anche [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Smarrito, danneggiato o restituito.** Pengyou non pubblica alcuna procedura di reclamo né condizioni per i destinatari: le pagine sul profilo aziendale e sui contatti sono vuote. Poiché la spedizione è stata prenotata dal venditore presso Pengyou, rivolgiti al negozio o al marketplace per chiedere un rimborso o un nuovo invio. Consulta [pacchi smarriti](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender) e [consegnato ma non ricevuto](guide:delivered-not-received).

## Contatti e assistenza clienti Pengyou

Come riportato su szpy56.com, esclusivamente in cinese:

- **Telefono**: `181 2470 1707` e `188 7908 6605`, due numeri di cellulare cinesi (`+86` dall'estero), indicati come linea di consulenza attiva 24 ore su 24 (`全天24小时咨询热线`). Non sono specificate le lingue parlate.
- **QQ**: `2853292077`, l'account ufficiale di assistenza (`官方服务QQ`), aperto dal collegamento del servizio clienti online (`在线客服咨询`).
- **WeChat**: un codice QR per il canale ufficiale (`官方微信二维码`).

Pengyou non indica alcun indirizzo e-mail né alcun indirizzo stradale. Per qualsiasi problema relativo al tuo ordine, il referente è il venditore.

## Tracciare un pacco Pengyou con Peek

[Peek](/) assegna subito Pengyou ai codici che iniziano con `ZXA`, una serie a cui risponde direttamente il tracker di Pengyou. Con altre lettere iniziali non può avere la certezza che si tratti di Pengyou solo dalla forma: tre lettere, nove cifre e `PY` costituiscono soltanto una corrispondenza debole. Incollalo comunque: Peek segue oltre 4.500 corrieri. Durante l'aggiunta, Peek interroga il tracker di Pengyou («Verifica dei servizi di tracciamento…») e mostra «Pengyou ha questo pacco.» quando il sistema conosce il numero. Altrimenti tocca «Cambia» e scegli Pengyou (lo trovi anche digitando `捧友`), oppure usa «Cambia corriere» su un pacco già salvato. Fai lo stesso con il riferimento interno del venditore: Peek mostrerà così anche il numero di tracciamento proprio dell'ordine. Non esiste un link diretto di tracciamento Pengyou da incollare, perché la ricerca avviene tramite modulo.

Peek legge il tracker di Pengyou e mostra lo stato, ogni scansione con la relativa località e il Paese di destinazione quando compare nella scheda di Pengyou. Le scansioni vengono mostrate così come le scrive Pengyou, città tra parentesi quadre compresa, tranne quando una riga `Delivered` è seguita dal nome di una città: in quel caso Peek mostra «Consegnato», usa la città come luogo e rimuove il codice postale. `Shenzhen Processing Center has received it` viene classificato come «Preso in carico». Gli orari sono letti sull'ora di Pechino e convertiti nel fuso orario del tuo dispositivo («Orari nel tuo fuso orario: …» su peektracker.com e nelle app per iPhone e Android). Peek omette nomi, indirizzo, codici postali, peso, importi e firma eventualmente presenti nel record di Pengyou, e non mostra una data di consegna prevista perché Pengyou non ne fornisce alcuna.

Peek trattiene ogni tappa scritta più di dodici ore prima dell'orario che riporta, o più di sei ore prima nello stesso secondo di una tappa del genere, e qualsiasi tappa datata oltre il momento del controllo sull'ora di Pechino. Su un ordine con tappe scritte in anticipo trattiene anche `Delivered`, persino se registrato nello stesso giorno, perché la piattaforma di Pengyou aggiunge quella chiusura in un lotto serale. Quando l'interfaccia dati del tracker non restituisce tappe per un numero, Peek legge in alternativa la pagina web di tracciamento di Pengyou; poiché quella pagina non mostra quando è stata scritta ciascuna riga, lì Peek trattiene ogni `Delivered`. Scarta inoltre le copie che i servizi di tracciamento fanno di tali tappe e calcola lo stato in base alle scansioni mantenute, non allo stato riepilogativo del record. Un ordine le cui tappe sono tutte scritte in anticipo non ha ancora cronologia in Peek e mostra «In attesa di Pengyou» senza chiederti di controllare il numero: Peek considera le tappe scritte in anticipo come un ordine preso in carico da Pengyou ancora privo di scansioni e continua a interrogare il suo tracker. Fino alla consegna o al reso, Peek apre la cronologia del tracciamento con «Altri siti possono mostrare più passaggi, ma sono solo previsioni.» e, sotto «Mostrali», elenca in grigio le tappe trattenute, ognuna con «Previsto per» e il suo giorno. Una tappa trattenuta non conta mai come scansione: non modifica lo stato e non invia avvisi.

Peek controlla gli aggiornamenti fino a ogni 10 minuti mentre tieni il pacco aperto o nel giorno della consegna con le notifiche attive, e ogni ora negli altri casi così come dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»); tramite i servizi di tracciamento controlla al massimo ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno della consegna mentre hai il pacco aperto o le notifiche attive. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Pengyou né il venditore al posto tuo.

## Domande frequenti sul tracciamento Pengyou

### Che cos'è Pengyou (捧友 / Shenzhen Pony Supply Chain)?

È 深圳市捧友供应链有限公司, Shenzhen Pony Supply Chain Co., Ltd nel proprio logo inglese, uno spedizioniere di Shenzhen le cui linee trasportano pacchi per via aerea in Europa e negli Stati Uniti per esportatori, spedizionieri e venditori online. Il suo sito è szpy56.com.

### Perché il tracker di Pengyou mostra tappe o Delivered che Peek non riporta?

Perché la piattaforma usata da Pengyou può scrivere in anticipo l'intera cronologia di un pacco, fino alla consegna inclusa. Quelle righe sono una previsione e non scansioni reali, quindi Peek le tiene fuori dalle scansioni, compreso `Delivered`, e le elenca solo in grigio sotto «Mostrali».

### Come tracciare un pacco Pengyou con codice ZXA…PY?

Nel riquadro `运单查询` (ricerca lettera di vettura) sulla home page di szpy56.com, che accetta fino a 10 numeri (uno per riga), oppure incollando il numero in Peek e scegliendo Pengyou se non viene riconosciuto subito.

### Quanto impiega Pengyou a consegnare e chi consegna in Italia?

Pengyou non pubblica tempi di consegna né indica quale corriere locale consegni il pacco in Italia (le pagine delle singole linee su szpy56.com contengono solo il titolo). Fai riferimento alla stima del negozio e chiedi al venditore il numero del corriere locale.

### Chi contattare se il pacco Pengyou è fermo o non arriva?

Il venditore da cui hai acquistato l'ordine, che ha prenotato la spedizione con Pengyou. Pengyou elenca solo due cellulari cinesi (`181 2470 1707` e `188 7908 6605`), QQ (`2853292077`) e WeChat in cinese, senza assistenza dedicata ai destinatari.

:::sources
- [Pengyou: 深圳市捧友供应链有限公司 (home page, in cinese)](http://szpy56.com/) – ragione sociale e nome inglese nel logo, linee per Europa e USA con doppio sdoganamento e dazi inclusi, sede e filiali, clientela, linee principali, riquadro `运单查询` fino a 10 numeri, loghi dei partner, schema del servizio, numeri mobili 24h, QQ e WeChat
- [Pengyou: 商业快递 (corriere commerciale, in cinese)](http://szpy56.com/sykd/) – linee Europa DHL ed Europa DPD, le cui pagine contengono solo il titolo
- [Pengyou: 国际专线 (linee internazionali, in cinese)](http://szpy56.com/gjzx/) – linea USA e linea Europa DHL, le cui pagine contengono solo il titolo
- [Pengyou: 公司简介 (profilo aziendale, in cinese)](http://szpy56.com/gywm/gsjj/) – pagina priva di contenuto
- [Pengyou: 联系我们 (contatti, in cinese)](http://szpy56.com/lxwm/) – pagina priva di contenuto
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per categoria di articolo fino a 150 € dal 1° luglio 2026 e commissione di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione dei 2 € per categoria di articolo a prescindere dal valore e definizione di categoria di articolo
- [Commissione europea: Territorial Scope (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA dell'UE; Campione d'Italia e acque italiane del lago di Lugano nel territorio doganale dell'UE e fuori dal territorio IVA dell'UE
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – pagamento in Italia degli importi dovuti alla consegna, all'Ufficio Postale al ritiro oppure online prima della consegna
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – avviso in Italia sul fatto che Poste Italiane non richiede dati personali, credenziali o codici delle carte tramite e-mail, SMS, lettere, telefonate o social network
- [UDSC (Svizzera): Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – mancata riscossione in Svizzera di dazi e IVA fino a 5 franchi svizzeri
- [UDSC (Svizzera): Costi d'imposizione e tracciamento dei pacchi](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – spese di sdoganamento applicate dal trasportatore in Svizzera
- [Commissione europea: Summertime (in inglese)](https://transport.ec.europa.eu/transport-themes/summertime_en) – ora legale europea dall'ultima domenica di marzo all'ultima domenica di ottobre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – schemi `ZXA…PY` e `…PY`, dicitura delle scansioni e città tra parentesi quadre, risposta per numero sconosciuto, ricerca per riferimento, cronologie scritte in anticipo e tappe trattenute da Peek (`Delivered` incluso), pagina web di riserva, ora di Pechino, dati mostrati e omessi
:::
