---
title: Tracciamento JD Logistics: codici JD, stati e contatti
description: Traccia un pacco JD Logistics (京东物流): formati dei codici JD e VG, significato degli stati in inglese e cinese, magazzini in Europa, dogana e assistenza.
slug: tracciamento-jd-logistics
published: 2026-11-18
updated: 2026-11-21
---

JD Logistics (京东物流, Jingdong Logistics) è la società logistica del gruppo JD.com, quotata alla Borsa di Hong Kong (`HKEX: 2618`). All’estero opera con i marchi JoyLogistics e JoyExpress: spedisce la merce stoccata dai venditori nei suoi magazzini in **Francia**, **Germania**, **Paesi Bassi**, **Polonia**, **Regno Unito**, **Stati Uniti** o **Australia** e trasporta pacchi e-commerce dalla **Cina**. Puoi seguire una spedizione in inglese sul [tracker internazionale di JD Logistics](https://www.jingdonglogistics.com/Tracking) oppure incollare il codice nel riquadro qui sopra. Il codice ti viene fornito dal negozio che ha spedito l’ordine.

## Codice di tracciamento JD Logistics: come riconoscerlo

Ecco i formati che Peek riconosce come JD Logistics, più uno simile che appartiene invece a un altro corriere (con cifre di esempio):

| Codice | Che cos’è |
| --- | --- |
| `JD4829105736491` | `JD` e 13 cifre: lettera di vettura JD Logistics. |
| `JDA482910573649` | `JD`, una lettera e 12 cifre: sempre JD Logistics. |
| `JDAP48291057364` | `JD`, due lettere e 11 cifre: sempre JD Logistics. |
| `VG48291057364` | `VG` e 11 cifre: usato anche da JD Logistics, ma coincide con un formato di [La Poste](carrier:la-poste), perciò Peek non assegna subito JD (vedi più sotto). |
| `JD482910573649182736` | `JD` e 18 cifre: non è JD Logistics. Peek interroga [DHL Paket](carrier:dhl), che utilizza questo formato. |

Minuscole, spazi e trattini non fanno differenza in Peek. Il tracker internazionale di JD accetta fino a 100 codici per ricerca, separati da spazi: premi Invio o clicca su «Track».

**Dove trovarlo.** Il venditore che si è affidato a JD lo indica nell’e-mail di conferma spedizione o nella pagina dell’ordine. Se non lo trovi, consulta [come trovare il codice di tracciamento](guide:find-tracking-number).

**Pacco in transito dentro la Cina.** Una spedizione che viaggia all’interno della **Cina** (ad esempio verso il magazzino di uno spedizioniere) si controlla sul sito cinese [jdl.com](https://www.jdl.com/orderSearch): inserisci il numero di lettera di vettura o del collo sotto `运单查询` e premi `立即查询` (puoi inserire più codici separati da virgole, spazi o a capo).

## Stati di tracciamento JD Logistics spiegati

Il tracciamento internazionale di JD mostra ogni passaggio in inglese con la città in cui è avvenuto. Queste sono le diciture originali di JD lette da Peek, nell’ordine consueto di un ordine partito da un magazzino estero di JD:

| Cosa scrive JD | Stato in Peek | Cosa significa per te |
| --- | --- | --- |
| `Picking Completed.` | «Preso in carico» | L’ordine è stato prelevato in un magazzino JD in cui il venditore conservava già la merce. |
| `Weighed at Warehouse.` | «Preso in carico» | Pesato nello stesso magazzino prima della partenza. |
| `Departed from Warehouse.` | «In transito» | Ha lasciato il magazzino di JD Logistics. |
| `Handed over to Carrier` | «In transito» | JD ha affidato il pacco al corriere incaricato della consegna finale. |
| `Shipping Label Created` | nessun arretramento | L’etichetta del corriere locale trasmessa da JD; un pacco già in transito mantiene il proprio stato. |
| `Arrived at GOFO Regional Hub` | «In transito» | Negli **Stati Uniti**: [GOFO Express](carrier:gofo), il corriere mostrato nella cronologia JD per il recapito locale, lo ha scansionato nel proprio hub regionale. |

**Un pacco spedito dalla Cina** segue invece questa sequenza: prima `Shipment Picked up` (ritirato presso il venditore), poi `Arrived at Warehouse`, `Weighed at Warehouse` e `Consolidation Completed` nel magazzino cinese di JD in attesa della partenza — tutti classificati «Preso in carico» (`Consolidation Completed` indica che il pacco è stato raggruppato con altri per il volo, non consegnato). Seguono, tutti come «In transito»: `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (sdoganamento all’esportazione concluso), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (refuso di JD: lo spedizioniere doganale ha ritirato il collo sdoganato, non il destinatario) e `Handover to local carrier`. Negli **Stati Uniti** proseguono le scansioni di GOFO sotto i codici di JD: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility`, `Loaded on Vehicle` (caricato in un hub GOFO) e `Arrived at GOFO Delivery Station` («In transito»), quindi `Out for Delivery` («In consegna»).

**Il codice del corriere locale.** Sui pacchi partiti dalla Cina, la riga `Departed from Warehouse.` sul tracker di JD termina talvolta con `Tracking no` seguito dal codice assegnato dal corriere finale (negli **Stati Uniti**, un codice `GFUS` da 14 cifre come `GFUS48291057364918` di GOFO Express). Peek rimuove quel numero dal testo della riga ma interroga direttamente quel corriere quando il formato lo identifica: non appena il corriere locale mostra una scansione almeno recente quanto l’ultima di JD, Peek prosegue il tracciamento lì conservando la cronologia di JD e mostra «Consegna con GOFO Express». Se Peek non può associare il numero a un corriere noto, cercalo sul sito del corriere locale.

Peek legge gli altri eventi di JD in base al loro codice interno: ordine creato («Etichetta creata»); sdoganamento avviato («In dogana»); sdoganamento completato, scarico o carico in centro di smistamento, scarico a destinazione o inoltro verso un centro estero («In transito»); corriere JD in uscita («In consegna»); consegna firmata («Consegnato»). Quando la riga di uscita in consegna di JD riporta nome e telefono del corriere tra parentesi `【…】`, Peek li omette.

**Righe del tracker cinese (`jdl.com`).** Per i pacchi interni alla **Cina**, le voci sono in cinese, tra cui `京东快件已取件` (ritirato dal mittente), `已到达【…】` (arrivato al centro di smistamento o alla stazione di recapito indicata tra parentesi), `正在派送中` (in consegna), `已与收件人约定新的派送时间` (nuovo orario di consegna concordato con il destinatario) e `已送达` (consegnato).

## Consegna JD Logistics in Europa e nel mondo

**Pacchi dalla Cina.** Nella pagina «Cross-Border Parcels», JD presenta il servizio per i venditori online che spediscono dalla **Cina** verso **Stati Uniti**, **Regno Unito**, **Australia**, **Canada**, **Messico**, **Russia** e altri paesi in attivazione, con opzioni standard, express o economica. JD trasporta le merci per via aerea e affida l’ultimo miglio a partner locali con tempi medi dichiarati di 3–6 giorni lavorativi per il recapito locale, senza elencare i nomi dei partner (negli **Stati Uniti** la cronologia JD mostra [GOFO Express](carrier:gofo)). In **Europa**, JD.com ha inoltre lanciato nell’aprile 2025 il proprio marchio di consegna espressa JoyExpress.

**Pacchi da un magazzino europeo o estero di JD.** JD Logistics gestisce anche magazzini fuori dalla Cina: in **Francia** vicino a Parigi (`FR-JD-PAR1` a Dugny, con distribuzione in Francia e nell’UE), in **Germania** vicino a Francoforte (`DE-JD-FRA1` a Buseck) e Dortmund (`DE-JD-DTM1` a Unna), nei **Paesi Bassi** a Venray (`NL-JD-VEN1`) e Tilburg (`NL-JD-TIL1`), in **Polonia** vicino a Varsavia (`PL-JD-WAW1` a Urzut e `PL-JD-WAW2` a Brwinów), nel **Regno Unito** (`GB-JD-BHM1` a Lutterworth), negli **Stati Uniti** (New Jersey, area di Los Angeles e Georgia) e in **Australia** (Sydney). Quando il venditore spedisce da queste scorte locali, la cronologia si apre con `Picking Completed.` e `Weighed at Warehouse.` anziché `Shipment Picked up`.

Le pagine di JD non indicano quanti tentativi effettua il corriere locale né dove resta in giacenza un pacco non consegnato: valgono le regole del corriere del tuo paese. Vedi [consegna mancata](guide:missed-delivery).

## Dogana, oneri d’importazione e falsi SMS

Le condizioni di servizio dell’espresso internazionale di JD Logistics dalla **Cina continentale** ([jdl.com/serviceDetails](https://www.jdl.com/serviceDetails), scheda `国际`) precisano che durante lo sdoganamento transfrontaliero possono maturare dazi e imposte, a carico del mittente o del destinatario ed esclusi dalla tariffa di trasporto di JD. Chiedi al negozio da dove è partito il tuo ordine e se IVA e dazi erano già compresi al momento del pagamento. Approfondisci in [pacchi fermi in dogana](guide:customs) e [riconoscere gli SMS truffa sulle consegne](guide:delivery-scam-texts).

- **Unione europea.** Dal 1° luglio 2026, nell’**Unione europea** le merci acquistate a distanza fuori dal territorio doganale UE in spedizioni di valore intrinseco fino a 150 € scontano un dazio doganale di 3 € per categoria di articoli; dal 1° novembre 2026 si aggiunge una commissione di gestione dell’Unione di 2 € per categoria di articoli, indipendentemente dal valore della spedizione (una categoria raggruppa le merci con la stessa classificazione tariffaria, descrizione e, ove richiesta, origine, a prescindere dal numero di pezzi).
- **Italia.** In **Italia**, Livigno resta fuori sia dal territorio doganale sia dal territorio IVA dell’UE, mentre Campione d’Italia e le acque italiane del lago di Lugano rientrano nel territorio doganale dell’UE ma sono fuori dal territorio IVA dell’UE. Per le spedizioni da Paesi extra UE gestite da [Poste Italiane](https://www.poste.it/ricevere-spedizioni-da-estero) in **Italia**, gli importi dovuti (IVA, dazio, commissione di gestione dell’Unione e Diritti Postali) si pagano alla consegna, in contanti o tramite POS al corriere fino a 999 €, oppure all’Ufficio Postale al momento del ritiro, o ancora online prima della consegna sull’App Poste Italiane e su `poste.it`, anche dal link che Poste Italiane invia per notifica (in App, per e-mail, in Bacheca `poste.it` o per SMS) se il mittente ha fornito i tuoi recapiti; nella sua [guida antitruffa](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe), Poste Italiane ricorda che né Poste Italiane né le Forze dell’Ordine chiedono mai, tramite e-mail, SMS, lettere, telefonate o social network, dati personali, credenziali di accesso ai servizi bancari, codici delle carte di pagamento o codici dispositivi o di autorizzazione.
- **Svizzera.** In **Svizzera**, l’Ufficio federale della dogana e della sicurezza dei confini ([UDSC](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi)) non riscuote dazi né IVA sull’importazione fino a 5 franchi svizzeri ciascuno (pari a una base imponibile di 62 CHF con aliquota IVA dell’8,1 % o di 193 CHF al tasso ridotto del 2,6 %), al netto delle spese di sdoganamento applicate separatamente dalla posta o dal corriere.

## Pacco JD Logistics fermo, in ritardo o restituito

**Ancora nessun risultato.** Se il tracker di JD non trova ancora nulla sotto il codice, verificalo con il venditore. Peek mostra «In attesa di JD Logistics» e continua a controllare; se dopo due giorni il numero resta sconosciuto, compare «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.».

**Nessuna novità da giorni.** Quando una spedizione in viaggio non riceve scansioni da quattro giorni, Peek segnala il giorno dell'ultima scansione («Nessun aggiornamento da lun 12 ott»), ricorda che le pause sono frequenti sui voli intercontinentali, in dogana o nel fine settimana (dopo tre settimane senza scansioni consiglia invece di contattare il venditore) e aggiunge il link «Chiedi aiuto a JD Logistics» verso la pagina contatti di JD (oppure verso il corriere locale quando un partner come GOFO è già subentrato). Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e [tracciare un pacco dalla Cina](guide:tracking-from-china).

**In ritardo o smarrito.** Il contratto con JD fa capo al venditore: contatta prima il negozio. Per l’espresso internazionale dalla **Cina continentale**, le regole di JD specificano che il tempo stimato di consegna indicato sulla lettera di vettura ha solo valore indicativo: JD non rimborsa il trasporto né riconosce indennizzi in caso di ritardo. Vedi [pacco smarrito](guide:lost-parcel).

**Restituito.** Per l’espresso internazionale dalla **Cina continentale**, il regolamento di JD non prevede servizi di logistica inversa una volta che la merce ha lasciato la Cina continentale. Per effettuare un reso, chiedi istruzioni al negozio. Vedi [restituito al mittente](guide:returned-to-sender).

**Risulta consegnato ma non c’è.** Chiedi al venditore, verifica con vicini o portineria e consulta [consegnato ma non ricevuto](guide:delivered-not-received).

## Assistenza clienti e contatti JD Logistics

La [pagina internazionale Contact Us di JD Logistics](https://www.jingdonglogistics.com/contact) elenca i contatti dell’assistenza per paese (chiuso nei fine settimana e nei festivi salvo diversa indicazione, senza fuso orario specificato):

- **Germania, Paesi Bassi e Regno Unito:** `org.cs.eu1@jd.com`, dal lunedì al venerdì dalle 09:00 alle 18:00.
- **Francia:** `org.cs.eu1@jd.com`, dal lunedì al venerdì dalle 09:00 alle 17:00.
- **Polonia:** `org.cs.intl1@jd.com`, dal lunedì al venerdì dalle 09:00 alle 17:00.
- **Stati Uniti:** `org.cs.us1@jd.com`, dal lunedì al venerdì dalle 08:00 alle 17:00.
- **Australia:** `+61 (02)87239755` e `org.cs.intl1@jd.com`, dal lunedì al venerdì dalle 09:00 alle 18:00 (chiuso domenica e festivi).

JD non elenca un recapito specifico per l’Italia, la Svizzera o il Canada. In **Cina** il numero centrale di JD Logistics è `950616` (`0086 950616` dall’estero nella pagina International Express), e la [pagina Contact Us di jdl.com](https://www.jdl.com/en/contactUs) offre anche il pulsante «Online Consultation». Per questioni sull’ordine o sui rimborsi, rivolgiti al venditore.

## Tracciare un pacco JD Logistics con Peek

[Peek](/) riconosce all’istante JD Logistics dai formati `JD…` della tabella qui sopra («Corriere rilevato»). Un codice `VG` da 11 cifre combacia anche con La Poste: Peek interroga prima La Poste e altri servizi di tracciamento («Verifica dei servizi di tracciamento…») e, se non lo trovano, puoi toccare «Cambia» e scegliere JD Logistics. Un link di tracciamento `jingdonglogistics.com` con `waybillCodes=` assegna il pacco a JD Logistics qualunque sia il numero; da un link `jdl.com`, Peek ricava il codice quando rispetta uno dei formati `JD…`. Se il tuo numero JD ha un’altra forma, incollalo comunque: Peek segue oltre 3.500 corrieri e puoi toccare «Cambia» per selezionare JD Logistics sotto «Tutti i corrieri» (cercando anche `Jingdong` o `京东物流`), oppure usare «Cambia corriere» su un pacco già salvato.

Peek legge il tracciamento internazionale di JD: lo stato, ogni evento con le parole di JD insieme a città e paese in inglese (anche quando JD scrive il paese in cinese) e l’ora di consegna. Gli orari sono letti sull’orologio del luogo della scansione e mostrati nel fuso orario del tuo dispositivo («Orari nel tuo fuso orario: …» su peektracker.com e nelle app per iPhone e Android). Peek non mostra una data di consegna stimata per i pacchi JD e tralascia i dati del destinatario, le eventuali foto di consegna presenti in JD, il nome e il telefono del corriere e il codice del partner locale accodato alla riga di partenza.

Per le serie numeriche riservate al **traffico interno cinese** (`JD` e 13 cifre che iniziano con `0`; `JDJ`, `JDK`, `JDV` o `JDX` e 12 cifre; `JDVA`, `JDVB`, `JDVC` o `JDAZ` e 11 cifre), Peek apre il tracker cinese su `jdl.com`. Con `JDW` e 12 cifre o `JDAP` e 11 interroga solo il tracker internazionale; per gli altri codici `JD` prova prima l’internazionale e poi il cinese se il primo è vuoto. Le righe cinesi compaiono in cinese senza nome e telefono del corriere né nome del firmatario. Poiché `jdl.com` richiede spesso una verifica che Peek non può superare, dopo un rifiuto Peek sospende l’apertura di `jdl.com` per quel numero per 6 ore o più, fino a un giorno: se una spedizione interna alla Cina non mostra nulla in Peek, controllala direttamente su `jdl.com`.

Finché tieni aperto il pacco, o nel giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e dalle 6:00 locali del giorno in cui JD scansiona il pacco in consegna, fino a ogni 2 minuti mentre risponde il tracker internazionale di JD (ogni 5 minuti quando apre il tracker cinese su `jdl.com`); tramite i soli servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno di consegna mentre il pacco è aperto o le notifiche sono attive. Negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando un corriere locale subentra per la consegna, Peek segue la cadenza di quell’operatore (negli **Stati Uniti**, GOFO Express: fino a ogni 30 minuti, o ogni ora dopo un controllo non riuscito, anche nel giorno di consegna). Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare JD Logistics né il negozio al posto tuo.

## Domande frequenti sul tracciamento JD Logistics

### JD Logistics è affidabile?

Sì. JD.com ha avviato la costruzione della propria rete logistica nel 2007, ha costituito JD Logistics come gruppo autonomo nell’aprile 2017 e l’ha quotata alla Borsa di Hong Kong nel maggio 2021 (codice `2618`). La pagina aziendale dichiara oltre 1.600 magazzini gestiti direttamente e 2.000 magazzini cloud gestiti da terzi in 26 mercati. A livello globale, JD Logistics gestisce oltre 200 magazzini doganali, di distribuzione diretta internazionale ed esteri.

### Come faccio a tracciare un pacco JD Logistics?

Su `jingdonglogistics.com/Tracking` (fino a 100 codici separati da spazi), su `jdl.com/orderSearch` per le spedizioni interne alla Cina oppure incollando il numero in cima a questa pagina.

### Chi consegna i pacchi JD Logistics in Italia e in Europa?

JD non pubblica l’elenco dei corrieri locali per i pacchi spediti dalla Cina, ma in Europa opera dall’aprile 2025 con il marchio espresso JoyExpress e dispone di magazzini vicino a Parigi (Dugny, con consegne in tutta l’UE), Francoforte, Dortmund, Venray, Tilburg, Varsavia e in Inghilterra. Se la riga `Departed from Warehouse.` riporta dopo `Tracking no` il codice di un corriere locale riconosciuto da Peek, Peek segue automaticamente quel codice non appena registra scansioni.

### Perché il tracciamento JD Logistics non si aggiorna?

Il venditore potrebbe non aver ancora consegnato il pacco a JD, il collo può trovarsi in volo o in dogana oppure il corriere finale non ha ancora trasmesso scansioni. Dopo quattro giorni senza aggiornamenti, contatta il venditore o scrivi al supporto europeo di JD (`org.cs.eu1@jd.com`). Per un pacco in Cina, controlla `jdl.com`.

### Come contattare JD Logistics?

Tramite gli indirizzi pubblicati su `jingdonglogistics.com/contact` (`org.cs.eu1@jd.com` per l’Europa occidentale, senza casella dedicata elencata per l’Italia). In Cina risponde il numero `950616` (`0086 950616` dall’estero). Per l’ordine o un rimborso rivolgiti prima al negozio.

:::sources
- [JD Logistics: Track your shipment (in inglese)](https://www.jingdonglogistics.com/Tracking) – tracker internazionale in inglese, fino a 100 codici separati da spazi, Invio o pulsante Track
- [JD Logistics: About JINGDONG Logistics (in inglese)](https://www.jingdonglogistics.com/about) – ramo logistico di JD.com (HKEX: 2618), marchi JoyLogistics e JoyExpress, oltre 1.600 magazzini propri, 2.000 magazzini cloud e oltre 200 magazzini internazionali in 26 mercati
- [JD Logistics: Contact Us (in inglese)](https://www.jingdonglogistics.com/contact) – caselle e-mail dell’assistenza clienti, numero australiano, orari e chiusure per paese e linea 950616
- [JD Logistics: Cross-Border Parcels (in inglese)](https://www.jingdonglogistics.com/ser/CrossExport) – paesi serviti, opzioni standard/express/economica, trasporto aereo e consegna locale in media in 3–6 giorni lavorativi
- [JD Logistics: JINGDONG Logistics (France) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseFR) – magazzino FR-JD-PAR1 a Dugny e distribuzione in Francia e nell’UE
- [JD Logistics: JINGDONG Logistics (Germany) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseDE) – magazzini DE-JD-FRA1 a Buseck e DE-JD-DTM1 a Unna
- [JD Logistics: JINGDONG Logistics (The Netherlands) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseNL) – magazzini NL-JD-VEN1 a Venray e NL-JD-TIL1 a Tilburg
- [JD Logistics: JINGDONG Logistics (Poland) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehousePL) – magazzini PL-JD-WAW1 a Urzut e PL-JD-WAW2 a Brwinów vicino a Varsavia
- [JD Logistics: JINGDONG Logistics (United Kingdom) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – magazzino GB-JD-BHM1 a Lutterworth (Inghilterra)
- [JD Logistics: JINGDONG Logistics (USA) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – magazzini negli Stati Uniti (New Jersey, area di Los Angeles e Georgia)
- [JD Logistics: JINGDONG Logistics (Australia) (in inglese)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – magazzini a Sydney
- [JDL: 运单查询 (ricerca spedizioni, in cinese)](https://www.jdl.com/orderSearch) – tracker cinese per numero di lettera di vettura o pacco, ricerca multipla
- [JDL: 服务细则 (regole di servizio, in cinese)](https://www.jdl.com/serviceDetails) – scheda internazionale: dazi e tasse esclusi dal nolo, tempi stimati solo indicativi senza rimborsi o indennizzi e assenza di resi fuori dalla Cina continentale
- [JDL: International Express (in inglese)](https://www.jdl.com/en/crossBorder/internationalC2C) – espresso dalla Cina continentale verso l’estero e numero 0086 950616
- [JDL: Contact Us (in inglese)](https://www.jdl.com/en/contactUs) – hotline 950616 e pulsante Online Consultation
- [JDL: Company Profile (in inglese)](https://www.jdl.com/en/profile) – fondazione nell’aprile 2017, quotazione a Hong Kong nel maggio 2021 e lancio di JoyExpress in Europa nell’aprile 2025
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per categoria di articoli fino a 150 € dal 1° luglio 2026 e commissione di gestione UE di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the EUR 3 customs duty and the Union handling fee (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – commissione di 2 € a prescindere dal valore e definizione di categoria di articoli
- [Commissione europea: ambito territoriale doganale e IVA dell’UE](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – regime di Livigno, Campione d’Italia e acque italiane del lago di Lugano
- [Poste Italiane: ricevere spedizioni dall’estero](https://www.poste.it/ricevere-spedizioni-da-estero) – canali di pagamento degli oneri doganali in Italia
- [Poste Italiane: come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – avvertenze contro richieste fraudolente di dati e codici di pagamento
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – franchigia di riscossione fino a 5 CHF in Svizzera e spese separate dei trasportatori
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati `JD` e `VG`, testi e codici di JD, passaggio a GOFO Express, interrogazione di `jdl.com` e visualizzazione in Peek
:::
