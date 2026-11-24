---
title: Tracciamento Maersk eCommerce: codici 1M, stati e aiuto
description: Traccia un pacco Maersk eCommerce (B2C Europe / InterPost): codice 1M a 19 caratteri, codici 70000–77200, passaggi locali e supporto Maersk.
slug: tracciamento-maersk-ecommerce
published: 2026-11-24
updated: 2026-11-24
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, che integra le ex piattaforme pacchi *B2C Europe* e *InterPost*) è la rete di consegna pacchi B2C nazionale e internazionale di A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Movimentando oltre 80 milioni di pacchi all’anno con più di 75 corrieri integrati in Nord America ed Europa, Maersk eCommerce smista i pacchi dei negozi online nei propri impianti, sdogana le spedizioni B2C sulle rotte internazionali e affida la consegna finale a corrieri locali e poste come [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) e [DPD Austria](carrier:dpd-at). Puoi incollare il codice pacco `1M` da 19 caratteri nel riquadro qui sopra per seguire sia le scansioni dei centri Maersk sia le tappe inoltrate dal corriere locale.

## Codici di tracciamento Maersk eCommerce (formato `1M`)

Maersk eCommerce emette numeri pacco alfanumerici di 19 caratteri che iniziano con `1M`, seguiti da un codice commerciante/cliente di 7 caratteri (con almeno una lettera) e 10 cifre:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `1MAB4829C7360514928` | Codice pacco Maersk eCommerce da 19 caratteri (`1M` + codice cliente di 7 caratteri + 10 cifre). |
| `1MKX7391E4829173605` | Altro codice pacco `1M` da 19 caratteri usato sulle rotte nordamericane o europee. |

Controlla l’e-mail di spedizione del venditore o il link di tracciamento (`maersk.com/tracking/parcel/...`) per il numero `1M`. Il tracciamento Maersk può indicare il corriere dell’ultimo miglio e il codice che quel corriere ha assegnato al pacco. Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Maersk eCommerce

Le scansioni proprie di Maersk hanno codici da `70000` a `72004`, quelle del corriere dell’ultimo miglio da `77100` in su:

| Codice e testo Maersk | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: il manifesto del venditore elenca il pacco prima che Maersk lo riceva. `70001`: etichetta propria di Maersk, creata quando il pacco raggiunge la rete, spesso pochi minuti prima di `70002`. | «Etichetta creata» |
| `70002` `Parcel Received by Maersk` | Pacco fisico ricevuto e scansionato in un centro di ingresso Maersk eCommerce. | «Preso in carico» |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Smistato in un hub di consolidamento Maersk e spedito verso la regione o il gateway di destinazione. | «In transito» |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Eventi di transito nazionale inoltrati dal corriere locale dell’ultimo miglio (come OnTrac, Amazon Logistics o DPD). | «In transito» |
| `77203` `Out for Delivery` | Caricato sul veicolo dell’autista locale per la consegna di oggi. | «In consegna» |
| `77200` `Parcel Successfully Delivered` | Consegnato all’indirizzo del destinatario dal partner dell’ultimo miglio. | «Consegnato» |

Una scansione priva di questi codici mostra «Problema segnalato» quando Maersk la contrassegna come eccezione, altrimenti prende la fase del passaggio sotto cui compare sul tracker Maersk.

## Copertura, resi e dogana internazionale

Maersk E-Commerce offre copertura di consegna completa in 32 paesi tra Nord America ed Europa, con punti di immissione in origine in Cina e nel Sud-est asiatico ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Impianti e partner:** in Europa 8 centri di smistamento e cross-docking, hub di ingresso a Londra, Taulov e Amsterdam e oltre 40 partner dell’ultimo miglio e poste con consegna a domicilio e punti di ritiro. In Nord America centri di smistamento e gateway a JFK, LAX e ORD; Standard richiede 2–7 giorni negli USA e 1–10 in Canada, Expedited 2–5 e 1–5.
- **Sdoganamento internazionale:** Maersk sdogana pacchi B2C verso Regno Unito, UE e Stati Uniti. L’eventuale pagamento di dazi e IVA all’arrivo dipende dal fatto che il venditore li abbia addebitati al checkout. Vedi [pacchi fermi in dogana](guide:customs).
- **Resi e-commerce:** resi nazionali e internazionali tramite consegna in punto o ritiro a domicilio per i venditori aderenti, senza carta ed esenti da dazi su alcune rotte.

## Pacco Maersk eCommerce in ritardo, silenzioso o smarrito

Poiché Maersk eCommerce è un consolidatore B2B incaricato dai marchi e-commerce, **contatta sempre prima il negozio online** per modifiche di indirizzo, articoli mancanti, sostituzioni o rimborsi.

- **Pausa durante il trasporto di linea o il passaggio al partner:** il tracciamento si ferma spesso alcuni giorni tra `72004` (`Departed from Maersk Facility`) e la prima scansione `77100+` del corriere locale mentre camion o voli trasferiscono i pacchi al corriere locale. Vedi [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **Usa il codice del corriere finale:** appena Maersk indica corriere e numero, subentrano il suo tracciamento e la sua assistenza. Peek mostra «Consegna con» per [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) e [DPD Austria](carrier:dpd-at).
- **Segnato consegnato o danneggiato:** controlla ingressi, locali pacchi e vicini, verifica lo storico del partner locale e avvisa subito il negoziante. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Supporto Maersk eCommerce e portali ufficiali

La pagina supporto di Maersk offre live chat e gestione ticket, soprattutto per i clienti aziendali; i venditori prenotano tramite l’accesso business MyParcel di Maersk.

- **Panoramica Maersk E-Commerce e contatti business:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Supporto generale Maersk e FAQ:** [maersk.com/support](https://www.maersk.com/support) e [maersk.com](https://www.maersk.com/).
- **Assistenza per destinatari:** contatta il rivenditore online presso cui hai acquistato (oppure il corriere locale dell’ultimo miglio una volta scansionato il pacco).

> Fai attenzione ai falsi SMS che invitano a cliccare su un link sconosciuto e pagare una piccola somma per sbloccare un «pacco Maersk». Verifica sempre qualsiasi avviso dalla pagina ordine del negozio o dal sito ufficiale del corriere locale. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Maersk eCommerce con Peek

[Peek](/) verifica automaticamente Maersk eCommerce come unico candidato quando inserisci un codice pacco `1M` da 19 caratteri (`1MAB4829C7360514928` o `1MKX7391E4829173605`, inventati) e mostra «Maersk eCommerce ha questo pacco.» appena l’API pacchi di Maersk lo conferma; Peek riconosce direttamente anche i link incollati `maersk.com/tracking/parcel/`. Se devi selezionarlo manualmente, tocca «Cambia» in fase di aggiunta oppure «Cambia corriere» nella schermata del pacco (anche cercare «B2C Europe», «InterPost», «Maersk Parcel» o «Maersk E-Delivery» lo trova).

Peek legge il tracker pacchi di Maersk e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Problema segnalato», «Consegnato»), il testo delle scansioni di Maersk e del corriere locale, città e regione, giorno stimato di consegna quando fornito da Maersk, paese di destinazione e ora di consegna. Codici numerici e nome del servizio restano esclusi da tutte le app Peek. Maersk indica il fuso UTC per le proprie scansioni e per la maggior parte dei partner; una scansione partner senza fuso (come DPD) viene datata nel fuso del proprio luogo (Vienna per una scansione a Graz) o in quello della scansione vicina, e Peek le mostra tutte sull’orologio del tuo dispositivo. Peek mantiene una sola copia se una scansione partner compare due volte. Quando Maersk indica [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) o [DPD Austria](carrier:dpd-at) con un numero nel formato di quel corriere, Peek verifica che abbia il pacco: la pagina mostra quindi «Consegna con» seguito dal nome del corriere ed elenca entrambi i numeri. Peek non legge mai numero ordine del cliente, località/CAP di destinazione né link del partner.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Maersk mostra `77203` (`Out for Delivery`), ogni 2 minuti dalle 6:00 sull’orologio dell’ultima scansione finché il tracciamento Maersk risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento Maersk non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Maersk eCommerce.» Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Maersk eCommerce.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare pacchi, dirottare spedizioni né contattare Maersk eCommerce o i suoi partner dell’ultimo miglio al posto tuo.

## Domande sul tracciamento Maersk eCommerce

### Che cos’è Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce è la rete di consolidamento e consegna pacchi B2C di A.P. Moller–Maersk in Nord America ed Europa (che integra le ex reti B2C Europe e InterPost) e movimenta oltre 80 milioni di pacchi l’anno attraverso oltre 75 partner dell’ultimo miglio.

### Come si presenta un numero di tracciamento Maersk eCommerce?

I numeri pacco Maersk eCommerce hanno 19 caratteri: `1M`, seguito da un codice cliente alfanumerico di 7 caratteri (con almeno una lettera) e 10 cifre — ad esempio `1MAB4829C7360514928` (inventato).

### Chi consegna il mio pacco Maersk eCommerce fino a casa?

Maersk consolida e trasporta il pacco tra hub, poi affida la consegna finale a un partner regionale nel tuo paese, come [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Austria](carrier:dpd-at) o poste locali.

### Perché il mio pacco Maersk eCommerce resta per giorni su `70000 Shipment Manifest Received`?

`70000` significa che il manifesto del venditore indica il pacco prima che Maersk lo riceva, quindi nulla si muove finché il venditore non lo consegna: chiedi al venditore se resta fermo lì. Maersk aggiunge la propria etichetta `70001` all’ingresso nella rete, spesso minuti prima di `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: servizi consegna pacchi E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ pacchi annui, 75+ corrieri integrati, reti nazionali, internazionali e resi in Nord America ed Europa
- [Maersk: sito ufficiale](https://www.maersk.com/) – portale logistico globale e accesso al tracciamento pacchi
- [Maersk: supporto e FAQ](https://www.maersk.com/support) – portale ufficiale di assistenza Maersk
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – formato `1M` a 19 caratteri, regola link `maersk.com/tracking/parcel/`, codici 70000–77200, data stimata e paese, e regole passaggio Amazon/OnTrac/DPD-AT
:::
