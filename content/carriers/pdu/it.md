---
title: Tracciamento PDU: codici Parcel Delivery Union e aiuto
description: Traccia un pacco internazionale PDU (Parcel Delivery Union / 友递联): formati codice, stati di transito e consegna, dogana e contatti a Shanghai.
slug: tracciamento-pdu
published: 2026-11-24
updated: 2026-11-24
---

**PDU** (*Parcel Delivery Union*, gestito da *Shanghai Youdilian Technology Co., Ltd.*, in cinese *友递联科技*) è un operatore di logistica intelligente transfrontaliera con sede a Shanghai fondato nel 2022 ([pduni.com](https://www.pduni.com/)). Con filiali dirette e hub operativi locali in oltre 10 paesi tra Nord America, Europa, Africa e Asia, PDU offre a commercianti e-commerce e produttori sdoganamento end-to-end, ritiro, consegna finale all’estero e incasso in contrassegno (COD). Puoi controllare una spedizione sul portale inglese PDU su [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) o incollare il numero di tracciamento nel riquadro qui sopra.

## Codici di tracciamento PDU (suffisso `PD` e lettere di vettura personalizzate)

Molti pacchi internazionali PDU portano un codice a 12 caratteri composto da due lettere, 8 cifre e `PD`:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `MX48291736PD` | Lettera di vettura internazionale PDU da 12 caratteri (`MX` + 8 cifre + `PD`). |
| `CO73918462PD` | Altro formato di lettera di vettura `..PD` da 12 caratteri usato su rotte internazionali. |

La pagina di ricerca pubblica di PDU accetta anche altri riferimenti alfanumerici da 6 a 40 caratteri quando selezioni PDU manualmente. Lo stesso schema corrisponde ai codici di [Colis Privé](carrier:colis-prive): Peek interroga prima Colis Privé e poi PDU, oppure prima PDU in Cina. Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento PDU

PDU assegna a ogni spedizione e scansione un codice stato globale (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED`, ecc.) insieme a testi in inglese o nella lingua del paese di destinazione (spesso in spagnolo sulle tratte latinoamericane, spesso preceduti da un breve codice del corriere locale come `ENT,Entregado` che Peek ripulisce per la visualizzazione):

| Stato o testo PDU | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Ordine elettronico ricevuto da PDU prima della presa in carico fisica o prima che la rete locale abbia registrato il primo evento. | «Etichetta creata» |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Ritirato all’origine o in movimento nel transito internazionale e nei centri di distribuzione regionali. | «In transito» |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Ritirato dalla dogana, entrato nel centro di distribuzione nazionale (CEDI) o in transito nazionale. | «In transito» |
| `DELIVERY` (senza dicitura di uscita in giro) | Affidato alla rete del corriere di destinazione; Peek lo mantiene in transito fino all’avvio effettivo del giro di consegna. | «In transito» |
| `Salida a Ruta` | In giro oggi con il corriere locale per la consegna. | «In consegna» |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Eccezione di consegna segnalata o pacco in attesa di rientro al mittente; contatta venditore o corriere locale. | «Problema segnalato» |
| `RETURNED` (`Returned`) | Restituito verso il mittente o il magazzino di origine. | «Restituito al mittente» |
| `SIGNED` (`Delivered`), `Entregado` | Consegnato e firmato all’indirizzo di destinazione. | «Consegnato» |

Nota che il messaggio iniziale `Package not found in last mile or customs process.` di PDU indica semplicemente che il partner locale dell’ultimo miglio non ha ancora scansionato il pacco — **non** significa che la dogana lo stia bloccando.

## Sdoganamento, consegna locale all’estero e contrassegno

Secondo [pduni.com](https://www.pduni.com/) e il [Centro assistenza PDU](https://www.pduni.com/MV/en/customer-services/help-center):

- **Sdoganamento proprio e classificazione SA con IA:** PDU gestisce una propria rete di sdoganamento in oltre 10 paesi con abbinamento automatizzato dei codici SA e pagamento anticipato dei dazi; secondo le FAQ, PDU non addebita un costo separato per le dichiarazioni doganali di esportazione formali e i commercianti possono inviare online documenti integrativi se manca qualcosa.
- **Consegna locale dell’ultimo miglio:** dopo lo svincolo della dogana di destinazione (`Recogida en Aduanas` / `Ingreso a CEDI`), filiali locali o corrieri partner eseguono la consegna a domicilio e, se previsto, l’incasso in contrassegno (COD).
- **Imposte e dazi di importazione:** IVA di importazione o oneri doganali dipendono dalle soglie fiscali locali e dal fatto che il venditore abbia riscosso i dazi al checkout. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco PDU fermo, silenzioso o smarrito

Poiché i negozi online e i produttori detengono l’account di spedizione presso PDU, **contatta sempre prima il venditore o il marketplace** per correzioni di indirizzo, rimborsi o rispedizioni.

- **Pause tra la Cina e l’ingresso locale:** le scansioni si fermano spesso per diversi giorni tra `Shipment information sent to PDU.` (o `Package not found in last mile or customs process.`) e `Recogida en Aduanas` / `Ingreso a CEDI` mentre la spedizione aerea o marittima consolidata raggiunge il paese di destinazione. Vedi [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` o `TO_BE_RETURN`:** agisci subito — verifica indirizzo di consegna e numero di telefono con il venditore così che l’hub locale possa fissare un nuovo tentativo prima che il pacco passi a `RETURNED`.
- **Segnato consegnato o danneggiato:** controlla prima con familiari, portineria e vicini, poi apri una segnalazione al negozio prima della scadenza della protezione acquisti. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti PDU e contatti a Shanghai

Recapiti ufficiali pubblicati su [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Ragione sociale:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Telefono:** `021-50837888` (`+86 21 5083 7888`).
- **Email:** `service@pduni.com`.
- **Sede centrale:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Shanghai, Cina.
- **Tracciamento e supporto online:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) e [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> Il Centro assistenza PDU avverte esplicitamente di diffidare dalle truffe che imitano il logo o i contatti di PDU. Non pagare mai spese doganali o di consegna tramite link SMS non verificati — conferma sempre prima qualsiasi importo con il venditore o sul portale ufficiale del corriere. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco PDU con Peek

Quando inserisci un codice `..PD` da 12 caratteri (`MX48291736PD` o `CO73918462PD`, inventati), [Peek](/) interroga a turno Colis Privé e PDU (prima PDU in Cina) e mostra «PDU ha questo pacco.» non appena il tracker PDU lo riconosce. Se il tuo codice PDU usa un altro formato da 6 a 40 caratteri, tocca «Cambia» in fase di aggiunta oppure «Cambia corriere» nella schermata del pacco e seleziona **PDU** (anche cercare «Parcel Delivery Union» o «友递联» lo trova).

Peek legge il tracker PDU e mostra la fase («Etichetta creata», «In transito», «In consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), il testo della scansione privo del codice del corriere locale davanti (`Entregado`, non `ENT,Entregado`), la località e l’ora di consegna. I codici stato PDU impostano la fase e restano esclusi da tutte le app Peek. PDU indica ogni orario di scansione con il suo scarto da UTC, altrimenti con l’ora di Pechino, e Peek li mostra con l’ora del tuo dispositivo. Il tracker PDU non fornisce una data di consegna stimata.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui PDU mostra `Salida a Ruta`, ogni 2 minuti dalle 6:00 sull’orologio dell’ultima scansione finché il tracciamento PDU risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento PDU non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a PDU.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a PDU.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare pacchi, modificare il tuo indirizzo di consegna né contattare PDU al posto tuo.

## Domande sul tracciamento PDU

### Che cos’è PDU (*Parcel Delivery Union*)?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) è una società di logistica transfrontaliera di Shanghai fondata nel 2022 che offre sdoganamento internazionale, ritiro, consegna ultimo miglio e contrassegno in oltre 10 paesi.

### Come si presenta un numero di tracciamento PDU?

Molte lettere di vettura internazionali PDU hanno 12 caratteri: due lettere, 8 cifre e `PD` (ad esempio `MX48291736PD`, inventato), sebbene il portale PDU tracci anche altri codici alfanumerici da 6 a 40 caratteri.

### `Package not found in last mile or customs process.` significa che il mio pacco è bloccato in dogana?

No. Sul tracker PDU quel messaggio iniziale significa solo che i dati elettronici del pacco sono stati creati e non ha ancora ricevuto la prima scansione fisica nel sistema locale di consegna o doganale.

### Come contatto l’assistenza clienti PDU?

Contatta prima il venditore online per rimborsi o modifiche di consegna. I recapiti pubblicati da PDU a Shanghai su [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) sono `021-50837888` e `service@pduni.com`.

:::sources
- [PDU: sito ufficiale](https://www.pduni.com/) – fondata nel 2022 nel distretto di Putuo, Shanghai, rete diretta di sdoganamento e ultimo miglio in 10+ paesi, COD
- [PDU: portale di tracciamento spedizioni](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – ricerca pubblica in inglese, `service@pduni.com` e `021-50837888`
- [PDU: centro assistenza e FAQ](https://www.pduni.com/MV/en/customer-services/help-center) – FAQ dogana, avviso anti-impersonificazione e indirizzo sede Shanghai (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – regola rilevamento `^[A-Z]{2}\d{8}PD$`, API `traceByWeb`, mappatura stato/testo (`DELIVERY` vs. `Salida a Ruta`), pulizia prefissi e gestione orari UTC/Pechino
:::
