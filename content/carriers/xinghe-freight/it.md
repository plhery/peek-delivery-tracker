---
title: Xinghe Freight tracking: numeri GZXH, stati e consegna
description: Traccia un pacco Xinghe Freight (星和货运) dalla Cina: codice GZXH…YQ, piattaforma GZYL e tappe scritte in anticipo (Delivered), dogana in Italia e chi contattare.
slug: xinghe-freight-tracking
published: 2026-11-18
updated: 2026-11-27
---

Per tracciare un pacco Xinghe Freight (星和货运, 广州星和货运代理有限公司, spedizioniere di Canton che dice di essere stato fondato nel 2014), incolla il codice da 16 caratteri `GZXH` + 10 cifre + `YQ` nel riquadro qui sopra oppure cercalo sul sito cinese xinghe-express.cn. Il tracciamento di Xinghe usa la piattaforma di [GZYL](carrier:gzyl), dove l'intera tabella di marcia — fino a `Delivered` — può essere scritta in anticipo prima che qualcuno scansioni davvero il pacco: se un sito mostra `Delivered` ma non hai ricevuto nulla, chiedi subito al venditore il nome e il numero del corriere locale.

## Numero di tracciamento Xinghe Freight: com'è fatto

I numeri che [Peek](/) riconosce come spedizioni di Xinghe Freight sono formati dalle lettere `GZXH`, dieci cifre e `YQ`, per un totale di 16 caratteri. Ecco alcuni esempi con cifre inventate:

| Numero | Che cos'è |
| --- | --- |
| `GZXH4829105736YQ` | `GZXH`, dieci cifre e `YQ`: il formato di Xinghe Freight. Peek riconosce subito il corriere. |
| `GZXH482910573YQ` | Solo nove cifre: non rispetta il formato di Xinghe Freight. Controlla che non manchi una cifra. |
| `YL4829105736YQ` | `YL`, dieci cifre e `YQ`: il formato delle lettere di vettura di [GZYL](carrier:gzyl). Usa lo stesso sistema di ricerca, ma è un altro spedizioniere. |
| `JXCAA4829105736YQ` | Termina anch'esso con `YQ`, come molti numeri di spedizionieri cinesi, ma inizia con `JXC`: è un codice di Juxi Express. |

Quando incolli il codice in Peek, spazi, trattini e lettere minuscole non fanno differenza.

**Dove trovarlo.** Il venditore prenota la spedizione con Xinghe e inserisce il codice nell'e-mail di conferma spedizione o nei dettagli dell'ordine. Se non riesci a recuperarlo, consulta la guida su [dove trovare il codice di tracciamento](guide:find-tracking-number).

## Stati del tracking Xinghe Freight: cosa significano

Poiché il tracciamento di Xinghe gira sul sistema di GZYL, le tappe compaiono con le diciture inglesi di GZYL, errori di battitura compresi. Peek le interpreta in base all'elenco degli stati di GZYL. Nell'ordine in cui una spedizione solitamente le attraversa:

| Il sistema mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order information received` | Il venditore ha inviato i dati elettronici dell'ordine. Nessuno ha ancora il pacco in mano. | «Etichetta creata» |
| `Arrive at the cross border receiving point` | Lo spedizioniere ha ricevuto il pacco: è la prima scansione fisica nei suoi magazzini. | «Preso in carico» |
| `Goods leave the operation center` | Il pacco ha lasciato il centro operativo. | «In transito» |
| `Arrive at the international airport` | All'aeroporto di partenza, in attesa del volo. | «In transito» |
| `Departing from the airport` | L'aereo è decollato. | «In transito» |
| `Item arrives at destination airport` | Atterrato nel Paese di destinazione. | «In transito» |
| `local carrier Agent Import customs clearance` | Un agente nel tuo Paese sta curando lo sdoganamento all'importazione. | «In dogana» |
| `The import customs keep it for inspection` | La dogana del Paese di destinazione trattiene il pacco per un controllo. | «In dogana» |
| `Customs clearance completed` | Sdoganamento concluso; la spedizione riprende il viaggio. | «In transito» |
| `Internationaliy Package received at processing center` | Un centro di smistamento nel tuo Paese ha preso in carico il pacco sdoganato (`Internationaliy` è un refuso della piattaforma). Non è un nuovo ritiro dal mittente. | «In transito» |
| `Leave the email processing center` | Il pacco ha lasciato quel centro di lavorazione. | «In transito» |
| `Arrival sorting, distribution center` | Arrivo a un centro di distribuzione nel tuo Paese, seguito dal nome della località. | «In transito» |
| `Leaving the sorting center` | Partenza da un centro di smistamento, con il nome della località. | «In transito» |
| `Package is on the way` | In movimento verso la tua zona, con il nome della città. Non indica che il corriere sia già uscito in consegna oggi. | «In transito» |
| `Delivered` | Consegna registrata con la località. Può trattarsi di una tappa pianificata in anticipo e non di una vera consegna (vedi sotto). | «Consegnato», con l'avviso sul calendario fisso, salvo quando Peek la tiene da parte (vedi sotto) |

**Transito da Hong Kong.** Su alcuni pacchi compare la riga `香港处理中心已收寄【Hong Kong】`, cioè l'accettazione presso il centro di lavorazione di Hong Kong, trasmessa in cinese con la città tra parentesi. Peek mostra la riga così com'è scritta, sotto la fase «Preso in carico».

**Una riga in cinese.** Quando un ordine non ha ancora alcuna scansione, la pagina del sistema può mostrare `货物电子信息已经收到`, che significa «informazioni elettroniche sulla merce ricevute». È una riga generata automaticamente dalla piattaforma quando il venditore registra l'ordine, prima di qualsiasi scansione fisica. Peek la riporta in cinese sotto «Etichetta creata».

**Orari e fuso orario.** Peek legge ogni orario del sistema sul fuso di Pechino (`UTC+8`), comprese le tappe nel tuo Paese, e lo converte nel fuso orario del tuo dispositivo. Durante l'ora solare invernale in Europa (dall'ultima domenica di ottobre all'ultima domenica di marzo), una tappa delle `20:00` a Pechino corrisponde alle `13:00` a Roma o Zurigo; durante l'ora legale estiva (dall'ultima domenica di marzo all'ultima domenica di ottobre) corrisponde alle `14:00`.

**Tappe scritte in anticipo.** Su questa piattaforma gli spedizionieri possono caricare fin dall'inizio l'intera tabella di marcia del pacco, dalla presa in carico fino all'ultimo passaggio nel Paese di destinazione, e il sistema pubblica ogni voce man mano che arriva l'orario previsto. Una voce simile è un programma, non una scansione reale. Sul sistema di GZYL il piano si chiude poi con un `Delivered` aggiunto in blocco la sera del giorno indicato: porta la data del giorno successivo all'ultima tappa pianificata, durante l'orario d'ufficio di Pechino, qualunque sia il Paese di destinazione. Peek tiene queste tappe da parte (vedi sotto), ma i siti di tracciamento universali che copiano la scheda possono mostrarle comunque: un `Delivered` letto lì non dimostra che il pacco sia stato davvero recapitato.

## Chi consegna i pacchi Xinghe Freight in Italia e in Svizzera

Peek non può sapere quale corriere effettui la consegna finale in Italia: né le tappe del sistema né il sito di Xinghe indicano il nome di un vettore locale. L'unico operatore di recapito a cui Peek può passare direttamente una spedizione Xinghe è [La Posta Svizzera](carrier:swiss-post), per i pacchi diretti in Svizzera e nel Liechtenstein, quando il record di Xinghe la nomina insieme al relativo codice.

Il sito di Xinghe non contiene alcuna informazione destinata a chi acquista online: non indica tempi di consegna, numero di tentativi di recapito, avvisi di giacenza o punti di ritiro. Queste regole dipendono interamente dal corriere che effettua l'ultimo tratto nel tuo Paese: chiedi al venditore quale azienda consegna il tuo ordine e qual è il suo codice di tracciamento locale.

**Dazi, IVA e oneri doganali (UE, Italia e Svizzera).** Nell'**Unione europea** (compresa l'**Italia**), dal 1° luglio 2026 le merci acquistate a distanza da Paesi extra-UE in spedizioni di valore intrinseco fino a 150 € scontano un dazio doganale fisso di 3 € per categoria di articolo (voce tariffaria/TARIC), e dal 1° novembre 2026 si applica inoltre la commissione di gestione dell'Unione (*Union Handling Fee*) di 2 € per categoria di articolo su tutte le vendite a distanza extra-UE di qualsiasi valore, oltre all'IVA all'importazione quando non è già stata riscossa al momento dell'acquisto. Per categoria di articolo si intendono le merci dello stesso invio che condividono classificazione tariffaria, descrizione e, dove richiesta, origine, indipendentemente dal numero di pezzi. In **Italia**, Livigno è fuori sia dal territorio doganale UE sia dall'area IVA UE, mentre Campione d'Italia (con le acque nazionali del Lago di Lugano) fa parte del territorio doganale UE ma resta fuori dall'area IVA UE. Quando in **Italia** lo sdoganamento di un invio postale UPU extra-UE è curato da [Poste Italiane](carrier:poste-italiane), Poste Italiane riscuote per conto delle autorità doganali il dazio, l'IVA eventualmente non assolta all'acquisto e la tassa di gestione UE, oltre ai propri Diritti Postali; nella sua guida antifrode per l'Italia, Poste Italiane raccomanda di non fidarsi di chi chiede di condividere dati e codici personali, i dati delle carte di pagamento o i codici di sicurezza. In **Svizzera**, fuori dall'UE, l'Ufficio federale della dogana e della sicurezza dei confini (UDSC/BAZG) non riscuote i tributi doganali fino a 5 franchi né l'IVA all'importazione fino a 5 franchi (calcolati separatamente), esenta i regali tra privati fino a 100 CHF (esclusi alcol e tabacco) e lascia al trasportatore la riscossione delle proprie spese di sdoganamento. Maggiori dettagli in [pacco fermo in dogana](guide:customs) e nella guida agli [SMS truffa sui pacchi](guide:delivery-scam-texts).

## Pacco Xinghe Freight in ritardo o tracking bloccato

**Fermo in dogana.** Se ti viene richiesto il pagamento di oneri d'importazione, verifica la richiesta seguendo i canali ufficiali del corriere incaricato nel tuo Paese e consulta [pacco fermo in dogana](guide:customs). Se lo stato doganale non cambia per diversi giorni, avvisa il venditore.

**Il sito del corriere o un altro servizio mostra più tappe di Peek.** Le righe aggiuntive, compreso un eventuale `Delivered`, possono far parte di una tabella di marcia scritta in anticipo che Peek tiene da parte perché non confermata. Fai riferimento alla finestra di consegna promessa dal negozio al momento dell'ordine e contatta il venditore se tale scadenza è passata. Approfondisci in [tracking fermo che non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**Risulta `Delivered`, ma non è arrivato nulla.** La voce può appartenere a un calendario precompilato. A meno che la consegna non sia passata a La Posta Svizzera, Peek mostra sotto «Consegnato» l'avviso: «Xinghe Freight aggiorna secondo un calendario fisso, senza firma. Se non è arrivato nulla, contatta il venditore.» Segnala subito al negozio che il pacco non ti è stato recapitato e chiedi il nome del corriere locale e il suo numero di spedizione. Leggi anche [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Pacco smarrito o rispedito al mittente.** È il venditore ad aver stipulato il trasporto con Xinghe: rivolgiti quindi al venditore o alla piattaforma di e-commerce per ottenere il rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti Xinghe Freight

Secondo quanto riportato sul sito xinghe-express.cn, disponibile solo in cinese:

- **Telefono**: `19924483103`, indicato come linea attiva 24 ore su 24, tutti i giorni (`+86 199 2448 3103` dall'estero).
- **E-mail**: `3975605292@qq.com`, destinata a richieste commerciali e preventivi di trasporto.
- **Sede**: Canton (Guangzhou), provincia del Guangdong, senza via né numero civico pubblicati; la pagina contatti invita a prendere appuntamento prima di recarsi in sede.

Il sito non offre assistenza per i destinatari privati all'estero e il modulo di contatto apre semplicemente un messaggio verso quell'indirizzo e-mail. Il cliente di Xinghe è il venditore: per qualsiasi problema con il tuo ordine scrivi prima al negozio e, se non ricevi risposta, apri una segnalazione sul marketplace.

## Tracciare un pacco Xinghe Freight con Peek

[Peek](/) riconosce immediatamente Xinghe Freight da un codice `GZXH…YQ`. Se il venditore ti ha dato un numero con un formato diverso, incollalo comunque: Peek segue oltre 4.500 corrieri. Per assegnare manualmente una spedizione a Xinghe, premi per cambiare corriere durante l'aggiunta e cerca Xinghe Freight (puoi digitare anche `星和货运`), oppure usa «Cambia corriere» su un pacco già salvato.

Peek interroga il sistema di GZYL proprio come fa il riquadro di ricerca sul sito di Xinghe, mostrando lo stato attuale, ogni tappa con la relativa località e il Paese di destinazione quando il record lo indica. Gli orari vengono letti sull'ora di Pechino e convertiti nel fuso del tuo dispositivo (con la nota «Orari nel tuo fuso orario: …» su `peektracker.com` e nelle app per iPhone e Android). Peek scarta nomi, indirizzo, codici postali, peso, dettagli di pagamento, firma e prova di consegna eventualmente presenti nel record, e non mostra una data di consegna prevista perché il sistema non ne fornisce alcuna.

Quando il sistema di GZYL non conosce il tuo numero, Peek interroga come riserva i servizi di tracciamento internazionali e ne riporta i passaggi, che per alcuni codici Xinghe possono essere gli unici disponibili. Finché né il sistema diretto né i servizi di tracciamento hanno una tappa, il pacco mostra «In attesa di Xinghe Freight» e, due giorni dopo, «Controlla il numero di tracciamento».

Peek tiene da parte qualsiasi tappa scritta più di 12 ore prima dell'orario che porta, ogni tappa scritta nello stesso secondo di una scritta più di mezz'ora prima del suo orario (tranne la registrazione dell'ordine), così come ogni tappa datata nel futuro rispetto al momento del controllo (sull'ora di Pechino). Su una scheda che contiene tappe scritte in anticipo, tiene da parte anche `Delivered`, perfino quando risulta registrato nello stesso giorno. Peek determina lo stato solo in base alle tappe che conserva, ignorando lo stato riepilogativo del record, e scarta anche le copie che i servizi di tracciamento fanno delle tappe trattenute come scritte in anticipo (`Delivered` compreso), anche quando conserva altri passaggi. Se però il sistema diretto non ha alcun record per quel numero o non è raggiungibile, nella copia di un servizio di tracciamento Peek non può distinguere una tappa pianificata da una scansione reale. Quando trattiene tappe scritte in anticipo, la cronologia del tracciamento si apre, fino alla consegna o al reso, con una riga: «Altri siti possono mostrare più passaggi, ma sono solo previsioni.» Con «Mostrali», subito dopo, vedi quelle tappe in grigio, dalla più recente alla più vecchia, ognuna con il giorno che le dà il sistema («Previsto per lun 12 ott»). Una tappa trattenuta non conta mai come scansione: non modifica lo stato e non invia notifiche.

Eccetto [La Posta Svizzera](carrier:swiss-post) in Svizzera e nel Liechtenstein, Peek non trasferisce la spedizione al corriere finale: se il venditore ti comunica il numero del vettore locale, aggiungilo in Peek come spedizione separata. Mentre tieni il pacco aperto sullo schermo, oppure nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti quando risponde il sistema diretto; negli altri momenti, e quando il pacco resta fermo, controlla ogni ora (tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo una giornata senza nuove scansioni e, nel giorno di uscita in consegna, fino a ogni 15 minuti mentre tieni il pacco aperto o hai le notifiche attive). Puoi scegliere le notifiche per «Ogni scansione», «Passaggi importanti» (che per queste tappe copre i due stati doganali e `Delivered`, non i voli né i passaggi di smistamento) o «Solo la consegna». Peek non può contattare Xinghe Freight, GZYL o il venditore al posto tuo.

## Domande frequenti sul tracking Xinghe Freight

### Che cos'è Xinghe Freight (星和货运) e com'è fatto il codice GZXH?

È 广州星和货运代理有限公司 (Guangzhou Xinghe Freight Forwarding), uno spedizioniere di Canton (Guangzhou) che sul proprio sito dichiara di essere stato fondato nel 2014 e di occuparsi di trasporto marittimo, aereo e terrestre e di pratiche doganali. I codici riconosciuti da Peek hanno 16 caratteri: `GZXH`, dieci cifre e `YQ`.

### Perché il mio pacco Xinghe Freight mostra il tracking di GZYL?

Perché il riquadro di ricerca di xinghe-express.cn invia il numero alla piattaforma di [GZYL](carrier:gzyl), restituendo le tappe con le diciture inglesi di GZYL (come `Internationaliy Package received at processing center`). Peek mantiene la spedizione sotto Xinghe Freight e legge direttamente quel sistema.

### Perché risulta `Delivered` su altri siti ma il pacco Xinghe non è arrivato?

Sulla piattaforma GZYL gli spedizionieri possono caricare in anticipo un calendario fisso di tappe fino a `Delivered`, pubblicato a orari prestabiliti senza firma reale. Peek tiene da parte le tappe previste sotto «Mostrali» e avvisa sotto «Consegnato» se il record usa un calendario fisso: segnala subito al venditore il mancato arrivo e chiedi il codice del corriere locale.

### Chi consegna i pacchi Xinghe Freight in Italia e ci sono costi doganali?

Né il tracking né xinghe-express.cn nominano il corriere italiano (solo in Svizzera e Liechtenstein Peek passa a La Posta Svizzera quando indicata nel record): chiedilo al venditore. Per ordini extra-UE verso l'Italia si applicano il dazio fisso UE di 3 € per categoria fino a 150 € (dal 1° luglio 2026), la commissione di gestione UE di 2 € per categoria (dal 1° novembre 2026) e l'eventuale IVA.

### Perché Peek non mostra ancora tappe per il mio codice GZXH e chi contatto?

Il sistema potrebbe non avere ancora ricevuto l'ordine («In attesa di Xinghe Freight») oppure contenere solo tappe scritte in anticipo tenute da parte sotto «Mostrali». Contatta prima il venditore o il marketplace: xinghe-express.cn, solo in cinese, indica un telefono attivo 24 ore su 24 (`19924483103`) e un'e-mail per richieste commerciali e preventivi (`3975605292@qq.com`), ma nessuna assistenza per i destinatari.

:::sources
- [Xinghe Freight: 广州星和货运代理有限公司 (home page, in cinese)](http://xinghe-express.cn/) – ragione sociale, servizi di spedizione internazionale, riquadro di ricerca che interroga il sistema di GZYL, telefono ed e-mail
- [Xinghe Freight: 关于我们 (chi siamo, in cinese)](http://xinghe-express.cn/about.html) – fondazione nel 2014, trasporto marittimo, aereo e terrestre, agenzia marittima e dichiarazioni doganali
- [Xinghe Freight: 产品服务 (servizi, in cinese)](http://xinghe-express.cn/services.html) – catalogo dei servizi logistici senza indicazioni sui pacchi e-commerce destinati ai consumatori
- [Xinghe Freight: 联系我们 (contatti, in cinese)](http://xinghe-express.cn/contact.html) – linea telefonica attiva 24 ore su 24, e-mail per richieste commerciali e preventivi, sede indicata solo come Guangzhou con invito a contattare prima della visita e modulo che apre un'e-mail
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio doganale di 3 € per categoria di articolo sui pacchi fino a 150 € dal 1° luglio 2026 e commissione di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione della commissione di 2 € per categoria di merce qualunque sia il valore e definizione di categoria di articolo
- [Commissione europea: Territorial Scope (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA dell'UE; Campione d'Italia dentro il territorio doganale UE ma fuori dall'area IVA
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – sdoganamento degli invii postali Extra-UE in Italia, riscossione di dazio, IVA, tassa di gestione UE e Diritti Postali
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – invito a non condividere dati e codici personali, dati delle carte di pagamento o codici di sicurezza
- [UDSC / BAZG: Costi d'imposizione e tracciamento dei pacchi](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – spese di sdoganamento riscosse dal trasportatore in Svizzera
- [UDSC / BAZG: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – franchigia di riscossione svizzera fino a 5 CHF per dazi e IVA e regali tra privati esenti fino a 100 CHF
- [Commissione europea: Summertime (in inglese)](https://transport.ec.europa.eu/transport-themes/summertime_en) – ora legale europea dall'ultima domenica di marzo all'ultima domenica di ottobre
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomi e alias, formato GZXH…YQ, lettura sul sistema di GZYL, diciture e fasi delle tappe, accettazione a Hong Kong, riga in cinese della piattaforma, fuso di Pechino, tabelle di marcia scritte in anticipo, servizi di tracciamento come riserva, risposta per numero sconosciuto, passaggio a La Posta Svizzera e cosa Peek mostra, tiene da parte e scarta
:::
