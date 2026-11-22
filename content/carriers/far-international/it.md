---
title: Tracciamento FAR International: numeri, stati e contatti
description: Segui un pacco FAR International (泛远国际) dalla Cina: formato FAR…YQ, significato degli stati, chi consegna, dogana UE e contatti ufficiali.
slug: tracciamento-far-international
published: 2026-11-22
updated: 2026-11-22
---

FAR International (泛远国际, FAR International Logistics) è una società di logistica transfrontaliera con sede a Hangzhou, in Cina, fondata nel 2004. Negozi online e marketplace la usano per spedire all’estero: FAR ritira e controlla i pacchi in Cina, mentre i suoi partner gestiscono il trasporto internazionale, lo sdoganamento e la consegna tramite un corriere locale nel tuo paese. Puoi seguire il codice sulla [pagina Track and Trace di FAR](https://www.far800.com/en/logistics), in inglese, oppure incollandolo nel riquadro qui sopra. Il numero arriva dal negozio nei dettagli di spedizione dell’ordine.

## Numero di tracciamento FAR International: com’è fatto

FAR non pubblica un formato ufficiale. Lo schema riconosciuto da Peek come proprio di FAR (cifre inventate):

| Numero | Che cos’è |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, due lettere, 10 cifre e `YQ`: un codice FAR International. |
| `FARAA482917365YQ` | Solo 9 cifre: non corrisponde al formato FAR. Peek non assegna FAR International; controlla se manca una cifra. |

In Peek lettere minuscole, spazi e trattini non contano. Il tracker di FAR vuole il codice completo e accetta fino a 50 numeri per volta, uno per riga.

**Dove trovarlo.** Nell’e-mail di spedizione o nella pagina dell’ordine. Altrimenti vedi [dove trovare il numero di tracciamento](guide:find-tracking-number), oppure le guide per [ordini Temu](guide:temu-orders) e [ordini SHEIN](guide:shein-orders).

**Un secondo codice.** La pagina dei risultati di FAR può mostrare anche un numero di ultimo miglio (尾程单号), assegnato dal corriere che consegna nel tuo paese e tracciabile sul suo sito.

## Stati di tracciamento FAR International spiegati

Le righe di magazzino e volo di FAR riportano spesso il cinese seguito dall’inglese, come `仓库收到包裹 Arrived at Warehouse`: Peek mantiene la parte inglese. Le righe successive possono essere solo in inglese o solo in cinese. Ogni passaggio ha ora e luogo, e il tracker di FAR include una colonna per il fuso orario. Gli stati più comuni in ordine:

| Testo FAR | Cosa significa per te | Cosa mostra Peek |
| --- | --- | --- |
| `Arrived at Warehouse` | Il magazzino FAR in Cina ha ricevuto il pacco. | «Preso in carico» |
| `Inspection by Warehouse` | Il magazzino controlla la merce (FAR apre i pacchi per ispezione al ritiro). | «Preso in carico» |
| `Await Release from Sender` | Il magazzino trattiene il pacco finché il venditore non conferma. | «Preso in carico» |
| `Departed Warehouse`, `Pickup Scan` | Uscito dal magazzino FAR e ritirato per la tratta successiva. | «In transito» |
| `In Transit to Export Port` | In viaggio verso l’aeroporto o il porto in Cina. | «In transito» |
| `Export Custom Clearance Processing and Flight Booking` | Sdoganamento export in Cina e prenotazione volo: passaggio di routine prima della partenza, non la dogana del tuo paese. | «In transito» |
| `Scheduled to Take off`, `Export Scan` | In attesa del decollo e scansionato per l’esportazione. | «In transito» |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | Arrivato all’hub di partenza e poi decollato. | «In transito» |
| `Arrived at Facility`, `Departed from Facility` | Passato da un centro di smistamento e ripartito. | «In transito» |
| `Last Mile Label Create` | Creata l’etichetta del corriere locale (spesso compare dopo l’hub di partenza: non è un passo indietro). | lo stato precedente |
| `At destination country` | Arrivato nel tuo paese; seguono dogana e smistamento. | «In transito» |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Preso in carico da un agente doganale; sdoganamento import avviato. | «In dogana» |
| `Customs Released`, `Import Scan`, `进口清关完成` | Svincolato dalla dogana di importazione. | «In transito» |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | FAR lo ha affidato al corriere che consegna nel tuo paese. | «In transito» |
| `包裹开始派送` | Quel corriere ha preso in carico il pacco; seguiranno le sue scansioni (non significa ancora consegna immediata). | «In transito» |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | In movimento tra centri di smistamento. | «In transito» |
| `Out For Delivery` | In consegna sul mezzo del corriere. | «In consegna» |
| `DELIVERED` | Consegnato. | «Consegnato» |

Dopo il passaggio, la cronologia di FAR può includere diciture proprie del corriere locale, come quelle di FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` come «In consegna» e `Package delivered` come «Consegnato»). Per altre diciture, Peek riconosce parole chiave comuni come delivered o customs; una riga non riconosciuta resta in cronologia mantenendo lo stato precedente.

Le FAQ di FAR avvisano che `RETURN` indica un’anomalia nella consegna. Se una riga mostra soltanto `RETURN`, Peek mantiene lo stato precedente: leggi il testo e contatta il venditore.

## Chi consegna i pacchi FAR International

FAR si definisce un operatore «light asset»: le sue sedi in Cina ritirano, ispezionano, imballano, etichettano e smistano i pacchi, mentre i suoi fornitori (operatori aeroportuali e portuali, compagnie aeree e marittime, agenti doganali e corrieri terzi) curano dogana, lunga tratta e consegna finale. Le pagine di FAR non indicano quale corriere consegni nei singoli paesi: controlla il codice di ultimo miglio nella pagina dei risultati di FAR.

FAR non pubblica regole su tentativi di consegna, avvisi o punti di ritiro: valgono quelle del corriere locale. Segui il codice di ultimo miglio sul suo sito e leggi [consegna mancata](guide:missed-delivery).

**Dogana nell’UE.** Gli acquisti online da fuori UE sono soggetti dal 1° luglio 2026 a un dazio di 3 € per articolo sui pacchi fino a 150 €, e dal 1° novembre 2026 a un contributo UE di gestione di 2 € per articolo a prescindere dal valore. Un articolo corrisponde a merci con la stessa classificazione tariffaria, descrizione e, se richiesta, origine. Le pagine di FAR non precisano come vengano pagati questi importi. Fuori dall’UE dipendono dal tuo paese: vedi [pacco fermo in dogana](guide:customs).

**Quando la dogana richiede dati.** Le FAQ di FAR segnalano che un problema di sdoganamento a destinazione richiede la collaborazione del destinatario con le autorità locali. Se lo sdoganamento fallisce oltre la scadenza, il pacco viene abbandonato o restituito secondo le norme locali, con costi a carico del mittente.

## Pacco FAR International fermo, smarrito o restituito

FAR lavora per negozi e marketplace, che nelle FAQ vengono indirizzati al proprio account manager. In caso di ritardo, smarrimento, danno o reso, contatta quindi prima il venditore.

**Ancora nessun dato.** Se il tracker di FAR non ha ancora nulla, Peek mostra «In attesa di FAR International» e continua a controllare. Se dopo alcuni giorni non cambia nulla, verifica il codice con il venditore.

**Nessun aggiornamento da giorni.** Le FAQ di FAR segnalano che gli aggiornamenti sui voli possono arrivare in ritardo. Dopo quattro giorni senza novità lungo il percorso, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a FAR International.». Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**Consegnato ma non ricevuto.** Le FAQ di FAR consigliano di controllare intorno a casa (angoli, ingresso, casella postale, armadietto o altri posti possibili) e di chiedere a parenti e vicini. Se continua a mancare, avvisa subito il venditore: l’indagine di reclamo va aperta entro **14 giorni** dalla data di firma tramite il venditore presso FAR. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Conserva l’imballo esterno e il prodotto danneggiato come richiesto dalle FAQ di FAR e contatta il venditore. Vedi [pacco danneggiato](guide:damaged-parcel).

**Restituito o smarrito.** Chiedi al negozio nuova spedizione o rimborso. Vedi [restituito al mittente](guide:returned-to-sender) e [pacco smarrito](guide:lost-parcel).

## Assistenza clienti e contatti FAR International

Recapiti presenti sul [sito inglese di FAR](https://www.far800.com/en):

- **Hotline globale:** 400-128-5656 (numero cinese; la pagina dei servizi menziona una hotline 24/7 per i clienti, senza indicare se funzioni dall’estero né le lingue parlate).
- **E-mail:** csc@far800.com.
- **Indirizzo:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, Cina, CAP 310022.

Per il tuo ordine rivolgiti prima al venditore, che ha prenotato FAR.

## Seguire un pacco FAR International con Peek

[Peek](/) riconosce subito FAR International da qualsiasi codice `FAR…YQ` («Corriere rilevato»). Funziona anche incollare un link dei risultati far800.com con `key=` e un codice FAR. Incolla comunque anche un eventuale altro codice, come quello di ultimo miglio: Peek traccia oltre 4.500 corrieri e assegna il corriere quando il formato è univoco. Se il formato combacia con più corrieri, Peek mostra «Verifica dei servizi di tracciamento…»; se non corrisponde a nessuno, lo cerca dopo l’aggiunta. Se occorre, tocca «Cambia» e sceglilo sotto «Tutti i corrieri», oppure usa «Cambia corriere» su un pacco già salvato. FAR International risponde solo ai numeri `FAR…YQ`.

Peek legge direttamente il tracciamento FAR: stato, ogni riga con luogo e ora, orario di consegna e codice di ultimo miglio. Quando FAR indica un fuso orario, Peek legge l’orario in quel fuso e lo mostra nel tuo (in fondo alla cronologia compare «Orari nel tuo fuso orario:» con il suo nome). FAR non fornisce data di consegna prevista né peso.

Quando FAR restituisce un codice di ultimo miglio, Peek interroga il corriere corrispondente: le poste del paese da cui cerchi se è un codice postale (due lettere, nove cifre, due lettere), come [Correos](carrier:correos-spain) in Spagna, oppure l’unico corriere indicato dal formato. Non appena quel corriere registra una scansione almeno recente quanto l’ultima di FAR, Peek prosegue lì mantenendo la cronologia precedente e mostra chi consegna (ad esempio «Consegna con Correos»). In assenza di tale corriere, resta sul tracciamento FAR.

Mentre tieni aperta la spedizione in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente FAR fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui va in consegna; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi impostare gli avvisi su «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare FAR o il venditore al posto tuo.

## Domande sul tracciamento FAR International

### FAR International è affidabile?

Sì. FAR International Logistics è stata fondata a Hangzhou nel 2004. La capogruppo FAR International Holdings Group è nata nel novembre 2022 e, secondo le notizie di FAR, si è quotata alla Borsa di Hong Kong nel dicembre 2023. FAR indica di essere diventata fornitore di UPS Cina nel 2005, di aver avviato un progetto logistico transfrontaliero con Temu nel 2023 e collaborazioni strategiche con TikTok e SHEIN nel 2024.

### Quanto tempo impiega FAR International per consegnare?

Ai propri clienti FAR indica tempi di 3–5 giorni lavorativi per Express, 6–9 per Standard e 10–30 per Economy, variabili in base al CAP di destinazione. Fai riferimento alla stima fornita dal negozio.

### Cosa significa «Await Release from Sender»?

Il magazzino FAR in Cina ha il pacco e lo trattiene in attesa della conferma del venditore. Se non si sblocca, chiedi al negozio.

### Come contatto FAR International?

Tramite la hotline globale 400-128-5656 o via e-mail a csc@far800.com. Per il tuo ordine chiedi prima al venditore.

:::sources
- [FAR International: Home](https://www.far800.com/en) – azienda, 泛远国际, 2004 e 2022, quotazione a Hong Kong (22 dicembre 2023), hotline globale, e-mail, indirizzo
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – numero completo, fino a 50 numeri uno per riga
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, UPS Cina 2005, Temu 2023, TikTok e SHEIN 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – modello light asset, fornitori per dogana e ultimo miglio, controllo al ritiro, tempi Express/Standard/Economy, hotline 24/7
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – ritardi voli, RETURN, consegnato non ricevuto (14 giorni), pacchi danneggiati, dogana a destinazione
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – ragione sociale, hotline, e-mail, indirizzo
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e contributo di gestione di 2 € dal 1° novembre 2026
- [Commissione europea: guida sul dazio di 3 € e contributo di gestione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articolo a prescindere dal valore e definizione di articolo
- [Universal Parcel Scraper: adattatore FAR International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – formato FAR…YQ, link, testi, numero di ultimo miglio, fusi orari e passaggio di consegna
:::
