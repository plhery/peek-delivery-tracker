---
title: Tracciamento ECMS Express: codici da 16 caratteri e dogana
description: Traccia un pacco ECMS Express (易客满): come sono fatti i codici da 16 caratteri, cosa significa ogni scansione, dogana in Italia e Svizzera e uso di Peek.
slug: tracciamento-ecms-express
published: 2026-11-21
updated: 2026-11-21
---

ECMS Express (`易客满`, noto anche come `ECMS Global` o `ECMSGlobal`, ragione sociale cinese `天津易客满国际物流有限公司`) è un corriere espresso internazionale fondato nel 2013 che spedisce ordini e-commerce in oltre 200 Paesi e regioni. Se un negozio online ha affidato il tuo ordine a ECMS, il codice ha 16 caratteri e inizia con `EC`, `ES`, `AP` o `AX`: incollalo nel riquadro qui sopra. ECMS cura il trasporto aereo internazionale e lo sdoganamento, poi passa il pacco a un corriere locale per il recapito finale. Nel catalogo di Peek lo trovi anche cercando `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` o `易客满国际快递`.

## Numero di tracciamento ECMS Express: formato ed esempi

I numeri ECMS contano sempre 16 caratteri: `EC`, `ES`, `AP` o `AX`, seguiti da tre a cinque lettere e da cifre per la parte restante. Con caratteri inventati:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `ECLAX48291057364` | `EC`, tre lettere e undici cifre: un codice ECMS Express. |
| `ECSZXA4829105736` | `EC`, quattro lettere e dieci cifre: sempre ECMS. |
| `ECSZXAB482910573` | `EC`, cinque lettere e nove cifre: sempre ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Gli stessi schemi da 16 caratteri che iniziano per `ES`, `AP` o `AX`. |
| `ECLAX4829105736` | Solo 15 caratteri: non rispetta il formato ECMS e Peek non riconosce alcun corriere (se selezioni ECMS Express a mano, Peek segnala che «non emette numeri come questo»). Controlla se manca una cifra. |

Nessun altro corriere nel catalogo di Peek adotta questi schemi. Spazi e minuscole non contano quando incolli il codice in Peek.

**Dove trovarlo.** ECMS stampa il numero della lettera di vettura (*waybill*) sull'etichetta del pacco, e il negozio lo comunica nell'email di spedizione o nella pagina dell'ordine. Se non lo trovi, vedi [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

**Un link di tracciamento.** Un link al tracker `ecmsglobal.com` che termina con `?number=` compila il numero e apre la verifica con immagine (*Security verification*). Altri link ECMS passano il codice dopo `orderNumber=`, `trackingno=` (come su `consignee.ecmsglobal.com`) oppure `order_number=`. Incolla uno qualsiasi di questi link in Peek: estrarrà automaticamente il numero ECMS.

## Stati del tracciamento ECMS Express spiegati

Su `ecmsglobal.com` il tracker chiede prima di superare una verifica visiva (*Security verification*: *Enter the characters shown below to continue tracking.*), poi riassume la spedizione con una di otto etichette (da *Shipment Information Received* a *Delivered* o *Exception*) ed elenca le scansioni dalla più recente alla più vecchia con data, ora e luogo. Peek mostra ogni scansione con le parole registrate nella scheda di ECMS. Nell'ordine del percorso:

| Scheda ECMS | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | Il negozio ha trasmesso i dati del pacco a ECMS, che non ha ancora ricevuto fisicamente il collo. | «Etichetta creata» |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS ha ricevuto i documenti per la dogana o ha verificato il documento d'identità del destinatario; non è ancora una scansione fisica. | «Etichetta creata» |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Prima scansione reale nel magazzino d'origine di ECMS. | «Preso in carico» |
| `Parcel has arrived at export distribution warehouse` | Ingresso nel magazzino di esportazione di ECMS alla partenza. | «In transito» |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Il pacco ha lasciato il magazzino di partenza. | «In transito» |
| `Flight departure`, `Flight arrival` | Decollo e atterraggio del volo cargo. | «In transito» |
| `Arriving notification received from terminal` | Il terminal merci dell'aeroporto di arrivo conferma la ricezione del carico del volo. | «In transito» |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Magazzino di transito ECMS presso l'aeroporto di arrivo, prima e dopo lo sdoganamento d'importazione. | «In transito» |
| `Shipment started customs clearance process.` | La dogana del Paese di destinazione sta controllando la spedizione. | «In dogana» |
| `Shipment completed customs clearance process.` | Lo sdoganamento d'importazione è concluso; il pacco prosegue. | «In transito» |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Arrivato al centro nel Paese di destinazione, pesato e in attesa del corriere locale. | «In transito» |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS sta affidando il pacco al corriere locale per il recapito finale; da qui può comparire il suo codice. | «In transito» |
| `Return to origin` | Anomalia registrata al centro di destinazione, non sempre un vero reso: in uno storico conservato dal codice di Peek, il corriere locale ha preso in carico il pacco il giorno seguente portando a termine la consegna. | «Problema segnalato» |
| `Local carrier has accepted package for final delivery` | Il corriere locale ha preso in carico il pacco. | «In transito» |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Avanzamento nella rete del corriere locale ritrasmesso da ECMS (la seconda voce può ripetersi nei giorni successivi). | «In transito» |
| `Shipment is out for delivery.` | In uscita per la consegna. | «In consegna» |
| `Available for pickup` | Il corriere locale tiene il pacco pronto per il ritiro. | «Pronto per il ritiro» |
| `Shipment delivered to customer` | Consegnato. | «Consegnato» |

**Voci in cinese.** Alcune schede riportano questi passaggi in cinese; Peek mostra la frase cinese e le assegna lo stesso stato della gemella inglese: `发货预报已收到，正在等待货物到达我们的始发站仓库` («Etichetta creata»), `干线航班起飞` e `干线航班已到达` («In transito»), `货物已进入清关流程` («In dogana»), `货物已清关放行`, `货物已到达目的站仓库` e `货物出库前往配送中心安排最终派送` («In transito»).

**Orari.** ECMS associa alle proprie scansioni l'orologio e lo scostamento UTC della stazione che le registra, e Peek li legge così. Le scansioni ritrasmesse del corriere locale portano scostamento zero: Peek le legge in UTC, a meno che una scansione di quel flusso cada oltre dieci minuti dopo il momento in cui ECMS l'ha registrata — in tal caso Peek tratta gli orari di quel flusso come ora locale datata in base alla località. Una riga di volo (`Flight departure`, `Flight arrival`) timbrata esattamente a mezzanotte riporta soltanto il giorno del volo e compare in Peek senza orario. Vedi anche [tracciare un pacco dalla Cina](guide:tracking-from-china).

## Chi consegna i pacchi ECMS Express in Italia e in Svizzera

Per l'ultimo miglio (*Final Mile Delivery*), ECMS dichiara nella pagina *Parcel Delivery Service* di collaborare con corrieri locali affidabili (*trusted local carriers*) in oltre 200 Paesi e regioni, senza pubblicare un elenco fisso per singolo Paese europeo. Sul tracker di `ecmsglobal.com`, il riquadro *Last Mile Delivery* può mostrare il nome del corriere sotto `LM Provider`, il suo codice sotto `LM Tracking No.` e un pulsante `Track with …` (negli storici statunitensi letti dal codice di Peek figura ad esempio SpeedX).

**Quanto impiega.** Secondo le FAQ di ECMS, i tempi dipendono da origine, destinazione e servizio. Dagli Stati Uniti (*U.S. Origin* su *Parcel Delivery Service*), ECMS indica `4–8 business days` verso l'**Unione europea** e `4–8 business days` verso la **Svizzera** (oltre a `3–6 business days` per il Regno Unito e `3–11 business days` per il Canada). Tentativi di consegna e ritiro in punto vendita seguono le regole del corriere locale: vedi [mancata consegna](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

**Dogana in Italia, nell'UE e in Svizzera.** Le FAQ di ECMS spiegano che sulle spedizioni `DDU/DAP` il destinatario può dover pagare dazi e imposte prima della consegna, mentre sulle spedizioni `DDP` li salda di norma il mittente in anticipo (alcuni Paesi richiedono anche copia del documento d'identità per sdoganare). Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € pagano un dazio doganale UE di 3 € per articolo (*per item*), e dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo indipendentemente dal valore (un articolo riunisce le merci dello stesso pacco con uguale classificazione tariffaria, descrizione e, se richiesta, origine; oltre 150 € valgono i dazi ordinari), più l'IVA all'importazione in **Italia**, se non è già stata pagata all'acquisto tramite IOSS (secondo l'ambito territoriale della Commissione europea, **Livigno** è fuori sia dal territorio doganale sia dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** fanno parte del territorio doganale UE ma non dell'area IVA UE). In **Svizzera**, secondo l'UDSC, l'IVA sull'importazione è dell'8,1 % (2,6 % aliquota ridotta), ma «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi», escluse eventuali spese del corriere. Leggi [pacco fermo in dogana](guide:customs).

## Pacco ECMS Express fermo, non trovato o restituito

**«We could not find a shipment for this tracking number.»** Il tracker di ECMS non conosce ancora il numero (errore di battitura o spedizione non ancora trasmessa dal negozio): Peek mostra «In attesa di ECMS Express» e continua a verificare. Se dopo due giorni ECMS non riconosce ancora il codice e nessun servizio di tracciamento lo trova, Peek mostra: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.»

**Fermo su `Virtual Manifest has been received`.** ECMS ha ricevuto il preavviso elettronico, non il pacco fisico. Chiedi al negozio quando lo ha affidato al corriere.

**Nessun passo dopo `Shipment is tendering to third party carrier for final delivery.`** Il corriere locale ha preso o sta per prendere il pacco e il suo tracciamento diretto può essere più aggiornato. Controlla `LM Provider` e `LM Tracking No.` nel riquadro *Last Mile Delivery* e, se Peek non segue già la spedizione lì, aggiungi quel codice separatamente in Peek. Dopo quattro giorni senza scansioni nuove, Peek avvisa con «Nessun aggiornamento da quattro giorni» e mostra «Chiedi aiuto a ECMS Express» (o il link del corriere locale quando Peek segue già il pacco presso di lui e ne conosce la pagina di supporto). Approfondisci in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato consegnato ma non arrivato, smarrito, danneggiato o reso.** Chiedi subito al corriere locale dove ha lasciato il collo e avvisa il venditore: le FAQ di ECMS indirizzano gli acquirenti al negoziante per sostituzioni o rimborsi, perché solo il mittente può presentare reclamo scritto a ECMS entro 30 giorni dall'accettazione della spedizione (un solo reclamo per invio), ed ECMS non può annullare né modificare gli ordini. Tra le cause di reso indicate da ECMS rientrano articoli proibiti o soggetti a restrizioni, dati di spedizione incompleti o errati, problemi doganali, dazi e tasse non pagati o mancato completamento delle pratiche doganali da parte del destinatario; una singola riga `Return to origin`, però, non basta da sola a confermare un rientro definitivo. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Contatti e assistenza clienti ECMS Express

Secondo la pagina *Contact Us* di `www.ecmsglobal.com` (senza fuso orario indicato per gli orari; Italia e Svizzera non hanno un numero telefonico dedicato in elenco):

- **Modulo online**: su `www.ecmsglobal.com/en-us/contact-us.html`, scegli `Shipment Support` sotto *Inquiry Type* e inserisci il codice in *Tracking Number (Optional)*.
- **Europa (Regno Unito)**: `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, dal lunedì al venerdì `08:30`–`17:00`.
- **Cina continentale**: `(+86) 400 086 1756`, `service@ecmsglobal.com`, dal lunedì al venerdì `08:30`–`17:30`.
- **Stati Uniti**: `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, dal lunedì al venerdì `09:00`–`18:00`.

**Attenzione agli SMS truffa.** I domini ufficiali di ECMS per il tracciamento sono `www.ecmsglobal.com` (`ecmsglobal.com`) e il vecchio portale `consignee.ecmsglobal.com`. Peek scarta eventuali link di pagamento presenti nella scheda; se ricevi un SMS che chiede oneri doganali o di riconsegna, consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco ECMS Express con Peek

[Peek](/) riconosce all'istante un codice ECMS da 16 caratteri («Corriere rilevato») e interroga il tracker di ECMS senza account, CAP né numero di telefono.

La verifica con immagine di ECMS è una piccola operazione aritmetica a una cifra: Peek la legge da sé e, se fallisce, riprova una volta con una nuova immagine. Se ECMS continua a non rispondere, Peek interroga i servizi di tracciamento e mostra la cronologia che possiedono, con le loro diciture.

Peek mostra lo stato, ogni scansione con città, regione e Paese e il Paese di destinazione, dalla più recente alla più vecchia, nel fuso orario del tuo dispositivo; su `peektracker.com` e nelle app iPhone e Android, la cronologia termina con «Orari nel tuo fuso orario: …». Esclude il riferimento del venditore, la località sulle righe di manifesto elettronico ed eventuali link di pagamento, e non mostra peso né data di consegna prevista. Il pulsante «Apri il sito ECMS Express» apre il tuo numero su `www.ecmsglobal.com`.

Quando la scheda ECMS nomina un corriere locale noto a Peek, compare «Consegna con …» (con il relativo numero quando le righe del partner ne riportano uno univoco), oppure «Annunciato a …, non ancora ricevuto» finché quel vettore elenca il pacco senza scansioni proprie. Se Peek legge direttamente il tracker di quel corriere senza dati aggiuntivi, lo interroga a sua volta e passa a seguirlo non appena mostra una scansione almeno recente quanto l'ultima di ECMS, mantenendo le scansioni di ECMS; altrimenti aggiungi il `LM Tracking No.` come spedizione a sé: Peek segue oltre 3.500 corrieri.

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 locali nel giorno in cui passa «In consegna» finché risponde il tracker di ECMS (dopo il passaggio al corriere locale, Peek ne segue il ritmo; tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive) —; negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» (ingresso in dogana d'importazione, `Return to origin`, uscita in consegna, `Available for pickup` e consegna, esclusi voli e passaggi di magazzino) o «Solo la consegna». Peek non può contattare ECMS né il venditore per te.

## Domande frequenti sul tracciamento ECMS Express

### Che cos'è ECMS Express (易客满)?

Una società internazionale di spedizioni espresse e logistica transfrontaliera fondata nel 2013 (`天津易客满国际物流有限公司`), con filiali proprie nella Cina continentale, a Hong Kong, Singapore, Stati Uniti, Regno Unito, Europa, Corea del Sud e Giappone.

### Dove posso tracciare un pacco ECMS Express?

Nel riquadro in cima a questa pagina (senza dover inserire il codice d'immagine) oppure sulla pagina *Track Your Shipment* di `www.ecmsglobal.com` superando la verifica visiva.

### Chi consegna i pacchi ECMS Express in Italia e in Svizzera?

Un corriere locale partner di ECMS, che il tracker di ECMS può indicare nel riquadro *Last Mile Delivery* alla voce `LM Provider` insieme al codice `LM Tracking No.`. Se non è ancora visibile, chiedilo al venditore.

### Cosa significa «Shipment is tendering to third party carrier for final delivery»?

Che ECMS sta consegnando il tuo pacco al corriere locale incaricato del recapito finale, il cui numero può comparire sotto `LM Tracking No.`. Peek mostra «In transito».

### Chi contatto se un pacco ECMS Express non arriva?

Prima il venditore da cui hai acquistato: le FAQ di ECMS rimandano al negozio per rimborsi o sostituzioni, e solo il mittente può presentare reclamo scritto a ECMS entro 30 giorni dall'accettazione. Per il recapito finale chiedi al corriere locale; puoi scrivere a ECMS tramite il modulo *Shipment Support* su `ecmsglobal.com`.

:::sources
- [ECMS Express: home page](https://www.ecmsglobal.com/en-us/home.html) – corriere espresso internazionale, spedizioni e logistica per e-commerce
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – fondazione nel 2013
- [易客满: 关于我们 (chi siamo)](https://www.ecmsglobal.com/zh-cn/about.html) – fondazione nel 2013, filiali proprie e ragione sociale 天津易客满国际物流有限公司
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – corrieri locali per l'ultimo miglio, 200+ Paesi e tempi dagli Stati Uniti verso UE, Svizzera, Regno Unito e Canada
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – verifica con immagine, otto etichette di riepilogo, riquadro Last Mile Delivery (`LM Provider`, `LM Tracking No.`), messaggio di spedizione non trovata e `?number=`
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – lettera di vettura sull'etichetta, DDU/DAP e DDP, motivi di reso, impossibilità di annullare e reclami del mittente entro 30 giorni
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – modulo Shipment Support, telefoni regionali, email e orari
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcolo per articolo ed esenzione IVA tramite IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia e Lago di Lugano
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e franchigia fino a 5 franchi
- [Universal Parcel Scraper: ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – alias, formato a 16 caratteri, link, controllo aritmetico e nuovo tentativo, stati inglesi e cinesi, fusi orari e campi esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – passaggio al corriere locale e ricorso ai servizi di tracciamento
:::
