---
title: Tracciamento AnserX: codici BTYCN...YQ, consegna e aiuto
description: Traccia un pacco internazionale AnserX (斑头雁) dalla Cina: codici BTYCN...YQ, eventi BTY, passaggio al corriere locale, dogana e assistenza.
slug: tracciamento-anserx
published: 2026-11-24
updated: 2026-11-24
---

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, in cinese *斑头雁国际物流*), fondata nel 2018 con sede a Shenzhen, è un operatore cinese di logistica e-commerce transfrontaliera che gestisce linee dedicate per piccoli pacchi dalla Cina verso Stati Uniti, Europa (tra cui Regno Unito, Francia e Germania), Giappone, Canada, Australia, Sud-Est asiatico, Messico, Nigeria e Arabia Saudita, oltre a trasporto aereo/marittimo FBA (0–30 kg, lato massimo 1,2 m) e magazzini all’estero. Puoi tracciare una spedizione sul [portale OMS di AnserX](https://oms.anserx.com/tracking) oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento AnserX: formato BTYCN...YQ

Controlla i dettagli dell’ordine sul marketplace online o l’e-mail di conferma spedizione. I codici AnserX sono lunghi 17 caratteri: `BTYCN` seguito da 10 cifre e chiuso da `YQ`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `BTYCN4829173640YQ` | Codice standard AnserX per pacchi internazionali: `BTYCN` + 10 cifre + `YQ`. |

Quando la spedizione arriva nel paese di destinazione e supera la dogana, AnserX la affida a un operatore postale o corriere locale (come [USPS](carrier:usps) negli Stati Uniti o un partner locale dell’ultimo miglio in Europa o in Messico) e abbina il codice locale di quel partner continuando a riportarne le scansioni sotto il tuo codice `BTYCN...YQ`. Per altri prefissi internazionali cinesi, vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati e i codici di tracciamento di AnserX

AnserX associa codici tappa `BTY_` a descrizioni in inglese e cinese lungo smistamento export, trasporto aereo, sdoganamento e consegna a destinazione:

| Codice e dicitura AnserX | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `BTY_ORDER_CREATED` (`Shipment information received`) | Il venditore ha trasmesso i dati elettronici dell’ordine ad AnserX prima che il pacco fisico arrivasse al centro. | «Etichetta creata» |
| `BTY_WAREHOUSE_INBOUND` (`Shipment inbound in sorting center`) | Ricevuto e registrato nel centro di smistamento AnserX in Cina. | «Preso in carico» |
| `BTY_WAREHOUSE_OUTBOUND`, `BTY_LINE_HANDOVER` (`Hand over to airline` / `Departed from origin port`), `BTY_LINE_ARRIVED` (`Arrived at destination port`) | Partito dal centro di smistamento in Cina, consegnato alla compagnia aerea e atterrato all’aeroporto di destinazione. | «In transito» |
| `BTY_EXCC_COMPLETED` (`Relased from export customs` / `目的地进口清关完成`) | Sdoganamento completato (nota: la dicitura inglese di AnserX dice «export», mentre il testo cinese conferma che lo sdoganamento in importazione a destinazione è concluso); il pacco prosegue nel transito locale. | «In transito» |
| `BTY_LASTMILE_TRANSIT` (`Delivery to local courier company` / `arrival at transportation partner facilities`) | Affidato al corriere locale del paese di destinazione e diretto alla filiale locale di consegna. | «In transito» |
| `BTY_LASTMILE_DELIVERING` (`Parcel currently being delivered` / `Out for Delivery`) | Oggi con il corriere locale per la consegna. | «In consegna» |
| `BTY_LASTMILE_DELIVERED` (`Parcel has been delivered` / `Delivered`) | Consegnato al destinatario dal partner di destinazione. | «Consegnato» |

## Come funziona la consegna transfrontaliera AnserX

Su [anserx.com](https://www.anserx.com/) e [Chi è AnserX](https://www.anserx.com/about.html), AnserX descrive la propria piattaforma intelligente di capacità e instradamento che collega ritiro nazionale in Cina, hub di esportazione, trasporto aereo, sdoganamento e corrieri dell’ultimo miglio a destinazione (compresi canali dedicati per piccoli pacchi che supportano prodotti elettronici con batteria).

**Consegna finale e dogana.** AnserX è un consolidatore aereo internazionale e non una flotta di consegna a domicilio fuori dalla Cina. Dopo lo sdoganamento a destinazione (`BTY_EXCC_COMPLETED`), il partner postale o espresso locale del tuo paese gestisce i tentativi di consegna, i depositi in luogo sicuro, la giacenza ai punti di ritiro e i resi. IVA all’importazione, dazi doganali o spese di gestione seguono le regole doganali locali salvo siano stati prepagati dal venditore al checkout; vedi [pacchi fermi in dogana](guide:customs).

## Pacco AnserX fermo, senza aggiornamenti o non consegnato

I clienti di AnserX sono i venditori che spediscono con l’azienda: contatta quindi prima il venditore per ritardi, indirizzi errati, rimborsi o reclami per smarrimento.

- **Pause durante il volo o la dogana:** il tracciamento resta spesso fermo per alcuni giorni tra `BTY_LINE_HANDOVER` (partito dal porto d’origine), `BTY_LINE_ARRIVED` e `BTY_EXCC_COMPLETED` mentre i voli atterrano e la dogana elabora i lotti consolidati. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Controlla il codice locale dopo il passaggio:** quando AnserX collega il codice di tracciamento del corriere di destinazione, verificarlo direttamente sul sito del corriere locale fornisce spesso maggiori dettagli su fascia di consegna o ritiro.
- **Consegnato, danneggiato o smarrito:** controlla prima con familiari, vicini, portineria e corriere locale, poi avvisa tempestivamente il venditore online. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti AnserX

Contatti ufficiali pubblicati su [Contatti AnserX](https://www.anserx.com/contact-us.html) e [anserx.com](https://www.anserx.com/):

- **Linee assistenza 24 ore (Cina):** `0755-2891 8474` e `0755-2868-5256` (assistenza online `09:00–24:00` ora cinese).
- **E-mail assistenza clienti:** `bantouyankf@anserx.com`.
- **Sede centrale:** Shenzhen AnserX International Logistics Technology Co., Ltd., Room 804, Block C, Building 3, Tian'an Cloud Park, Xuegang North Road, Bantian Street, Longgang District, Shenzhen, Cina.

> Diffida di SMS o messaggi WhatsApp non richiesti che chiedono un pagamento online con carta per «sdoganare» o «correggere l’indirizzo» di un pacco dalla Cina. Verifica sempre eventuali oneri legittimi sulla pagina dell’ordine o presso il tuo operatore postale locale. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco AnserX con Peek

[Peek](/) riconosce automaticamente `BTYCN` + 10 cifre + `YQ` (`BTYCN4829173640YQ`, cifre inventate) come AnserX con elevata sicurezza, e riconosce anche i link incollati di `oms.anserx.com/tracking?nos=`. Se dovessi selezionare AnserX manualmente, tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco (cercare «斑头雁» o «AnserX International Logistics» lo trova ugualmente).

Peek legge direttamente il tracker OMS di AnserX e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Consegnato»), il testo inglese della scansione (quello cinese quando manca l’inglese), la città o l’hub senza CAP e il paese di destinazione. I codici `BTY_` determinano la fase; nessuna app Peek li mostra. AnserX registra le proprie scansioni sull’ora di Pechino; le scansioni inoltrate dal corriere locale sono datate sul fuso orario del rispettivo luogo (Los Angeles per una scansione a Los Angeles, Città del Messico per Puebla), e Peek le mostra tutte con l’ora del tuo dispositivo. Quando AnserX fornisce il codice del corriere di consegna e Peek lo riconosce, Peek verifica che abbia il pacco: la scheda mostra quindi «Consegna con» seguito dal nome del corriere ed elenca entrambi i codici. Un codice a barre USPS perde il prefisso ZIP di instradamento. Peek omette codice account cliente e riferimento ordine; il tracker di AnserX non fornisce data stimata di consegna, peso, nome del servizio, orario di consegna separato né foto di prova di consegna.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui AnserX mostra `BTY_LASTMILE_DELIVERING`, ogni 2 minuti dalle 6:00 secondo l’ora locale dell’ultima scansione, finché il tracker di AnserX risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di AnserX non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a AnserX.», con il link «Chiedi aiuto a AnserX» verso la pagina contatti di AnserX. Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a AnserX.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può sdoganare pacchi né contattare AnserX o il tuo corriere locale al posto tuo.

## Domande frequenti sul tracciamento AnserX

### Che cos’è AnserX (*斑头雁*)?

AnserX (*Shenzhen AnserX International Logistics Technology Co., Ltd.*, fondata nel 2018 a Shenzhen) è un operatore cinese di logistica e-commerce transfrontaliera con linee aeree dedicate per piccoli pacchi, spedizioni FBA e magazzini all’estero verso Stati Uniti, Europa, Canada, Australia, Giappone, Messico e altri paesi.

### Cosa significa un codice di tracciamento `BTYCN...YQ`?

`BTYCN` + 10 cifre + `YQ` (per esempio `BTYCN4829173640YQ`, cifre inventate) è la lettera di vettura internazionale da 17 caratteri di AnserX.

### Perché AnserX indica `Relased from export customs` dopo l’arrivo nel mio paese?

È una particolarità di traduzione nel feed inglese di AnserX per il codice `BTY_EXCC_COMPLETED`: il testo cinese corrispondente (`目的地进口清关完成`) conferma che lo sdoganamento in importazione nel paese di destinazione è terminato e che il pacco passa al corriere locale.

### Come contatto il servizio clienti di AnserX?

Chiedi prima al negozio online o al marketplace per problemi sull’ordine e rimborsi. Per richieste al vettore, AnserX indica `bantouyankf@anserx.com` e le linee 24 ore di Shenzhen `0755-2891 8474` e `0755-2868-5256` su [anserx.com/contact-us.html](https://www.anserx.com/contact-us.html).

:::sources
- [AnserX: sito ufficiale](https://www.anserx.com/) – linee dedicate piccoli pacchi, limiti FBA 0–30 kg / 1,2 m, magazzini all’estero e sede di Shenzhen
- [AnserX: chi siamo](https://www.anserx.com/about.html) – fondata nel 2018, rotte globali (USA, Europa, Giappone, Sud-Est asiatico, Russia, Australia) e piattaforma di routing intelligente
- [AnserX: contatti](https://www.anserx.com/contact-us.html) – `bantouyankf@anserx.com`, `0755-2891 8474`, `0755-2868-5256`, servizio online `09:00–24:00` e indirizzo a Shenzhen (URL di aiuto collegato da Peek)
- [AnserX: portale di tracciamento OMS](https://oms.anserx.com/tracking) – ricerca pubblica spedizioni
- [Universal Parcel Scraper: anserx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anserx) – rilevamento `BTYCN...YQ`, regola link `oms.anserx.com/tracking?nos=`, codici `BTY_*`, ora di Pechino per le proprie scansioni e fuso locale per quelle inoltrate, estrazione `deliveryNo` locale e URL di aiuto
:::
