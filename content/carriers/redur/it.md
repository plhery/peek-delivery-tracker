---
title: Tracciamento Redur: codici 14 cifre, Spagna e aiuto
description: Traccia un pacco Redur in Spagna, Portogallo o Europa: codici a 14 cifre, scansioni filiale e consegna, ritiro in agenzia, rotte Eurodis e supporto.
slug: tracciamento-redur
published: 2026-11-24
updated: 2026-11-24
---

**Redur** (*Redur S.A.*) è un operatore spagnolo di trasporto espresso e logistica con oltre 50 anni di storia e sede ad Algete, Madrid ([redur.es](https://redur.es/)). Con una rete interamente gestita in proprio di oltre 61 filiali (*delegaciones*) e 3.000 addetti tra Spagna peninsulare, isole spagnole e Portogallo, Redur è anche socio fondatore di **Eurodis**, la rete europea di trasporto combinato pacchi e pallet. Oltre alle spedizioni nazionali urgenti B2B/B2C e alle partenze stradali internazionali giornaliere in Europa, Redur gestisce distribuzione farmaceutica certificata GDP (*Farma 15-25*) e logistica integrata. Puoi tracciare una spedizione su [redur.es/track-trace/](https://redur.es/track-trace/) oppure incollare il codice Redur a 14 cifre o il link di tracciamento nel riquadro qui sopra.

## Codici di tracciamento Redur (formato a 14 cifre)

I numeri di tracciamento Redur (*N° Tracking*) sono composti da 14 cifre senza lettere né spazi:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `58492716380419` | Numero di tracciamento Redur standard a 14 cifre (*NTRACKING*). |
| `61738492051628` | Altro codice di tracciamento Redur a 14 cifre per tratte Spagna, Portogallo o Eurodis. |

Su [redur.es/track-trace/](https://redur.es/track-trace/) il modulo cerca anche per numero di spedizione e riferimenti del mittente o di un partner, mentre il tracciamento automatico in Peek utilizza il numero di tracciamento a 14 cifre (`NTRACKING`) o un link diretto `redur.es/track-trace/?...&valor=`. Peek non riconosce Redur dal solo numero: un numero isolato di 14 cifre viene inviato a DPD, [SEUR](carrier:seur) e altri corrieri (SEUR per primo in Spagna), quindi incolla il link Redur o seleziona Redur. Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Redur

La pagina di tracciamento Redur mostra una barra di avanzamento in 7 passaggi insieme alle righe di scansione di filiale (in spagnolo su `redur.es/track-trace/`, o in inglese sulla pagina inglese):

| Redur mostra (ES / EN) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Documentazione elettronica di spedizione inviata dal mittente prima dell’ingresso fisico in filiale (datata solo al giorno). | «Etichetta creata» |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Prima scansione fisica di ingresso nella filiale di origine Redur. | «Preso in carico» |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | In movimento tra filiali di origine, transito e destinazione in Spagna o Portogallo. | «In transito» |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Caricato su un camion diretto verso un altro paese, dove consegna un partner. | «In transito» |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | In attesa di concordare una data di consegna con il destinatario, oppure appuntamento di consegna confermato. | «In transito» |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | In consegna oggi con il veicolo locale Redur. | «In consegna» |
| `RECOGERÁN EN AGENCIA` | In giacenza presso la filiale Redur di destinazione pronto per il ritiro. | «Pronto per il ritiro» |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Tentativo di consegna fallito per assenza del destinatario, indirizzo errato o locali chiusi. | «Tentativo di consegna» |
| `DEVOLUCIÓN A ORIGEN` | In rientro verso il mittente dopo consegna mancata o rifiuto. | «Restituito al mittente» |
| `ENTREGA` / `DELIVERY` | Consegnato al destinatario (o confermato tramite l’ultimo passaggio attivo sulla barra di avanzamento Redur). | «Consegnato» |

## Servizi in Spagna, Portogallo ed Europa con Eurodis

Secondo [Redur](https://redur.es/) e [Servizi Redur](https://redur.es/servicios/):

- **Nazionale Iberia (*Servicio Doméstico*):** distribuzione urgente di pacchi, pallet e carico combinato in Spagna peninsulare, isole e Portogallo attraverso oltre 61 filiali proprie, con notifiche email e SMS *Redur Connected* e lo strumento di gestione spedizioni *Maia*.
- **Internazionale Europa (*Servicio Internacional*):** partenze giornaliere in Europa per spedizioni B2B e B2C tramite la rete partner **Eurodis**. Una volta apparso `CARGA CAMION T. INTERNACIONAL`, la consegna finale nel paese di destinazione è eseguita da un corriere partner locale Eurodis.
- **Pharma a temperatura controllata (*Farma 15-25*):** distribuzione conforme GDP a 15–25 °C per spedizioni sanitarie e farmaceutiche.

## Consegna mancata, appuntamenti in attesa e pacchi smarriti

- **`PENDIENTE DE FECHA CONCERTADA` (in attesa di data concordata):** Redur attende di concordare con te un giorno di consegna prima di far partire il pacco; `FECHA DE ENTREGA CONCERTADA` o `BOOKING AGREED DATE` indica che il giorno è stato fissato. Controlla telefono ed email oppure chiama Redur con il numero a 14 cifre.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` o `RECOGERÁN EN AGENCIA`:** contatta subito il venditore o l’assistenza clienti Redur con il codice di tracciamento a 14 cifre per confermare l’indirizzo, fissare un nuovo tentativo o verificare gli orari di ritiro in filiale.
- **Spedizioni in ritardo, danneggiate o smarrite:** avvisa prima il negozio online o il mittente (detiene il contratto con Redur e può aprire un reclamo formale) e conferma con Redur in quale filiale si trova il pacco. Vedi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender) e [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).

## Assistenza clienti Redur e sede di Madrid

Recapiti ufficiali pubblicati su [Redur Contacto](https://redur.es/contacto/) e [Servizi Redur](https://redur.es/servicios/):

- **Telefono centrale (Spagna):** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **Email:** `info@redur.es`.
- **Modulo di contatto ed elenco filiali:** [redur.es/contacto/](https://redur.es/contacto/).
- **Sede (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Spagna.

> Fai attenzione ai falsi SMS che chiedono una piccola tariffa online per riprogrammare una consegna in Spagna o Portogallo. Verifica sempre prima gli avvisi di consegna su [redur.es/track-trace/](https://redur.es/track-trace/) o presso il venditore. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Redur con Peek

Incolla un link diretto `redur.es/track-trace/?...&valor=` in [Peek](/) e Peek riconosce Redur automaticamente. Poiché i numeri isolati di 14 cifre (`58492716380419` o `61738492051628`, inventati) sono condivisi da molti corrieri europei e Redur non ha un prefisso esclusivo, inserisci il numero a 14 cifre e tocca «Cambia» in fase di aggiunta o «Cambia corriere» nella schermata del pacco per selezionare **Redur** (anche cercare «Redur Spain», «Redur Portugal» o «Redur S.A.» lo trova); Peek lo verifica poi direttamente sul tracker Redur e mostra «Redur ha questo pacco.» una volta confermato.

Peek legge il tracker Redur e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Restituito al mittente», «Consegnato»), il testo Redur e la filiale. Quando l’ultimo passaggio della barra di avanzamento Redur è acceso, Peek mostra il pacco come consegnato. Redur fornisce orari di scansione senza fuso: Peek data ogni scansione nel fuso orario della relativa filiale (Madrid per `ALGETE`, Lisbona per `LISBOA`, Canarie per `LAS PALMAS`) e una scansione di una filiale non localizzabile nel fuso della scansione vicina, mostrando tutto sull’orologio del tuo dispositivo; `DOCUMENTACION` conserva solo la data. Peek non mantiene il conteggio colli. Il tracker Redur non fornisce data stimata di consegna, peso, nome servizio, ora di consegna separata né codice partner.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Redur mostra `SALIDA A REPARTO` o `OUT FOR DELIVERY`, ogni 2 minuti dalle 6:00 sull’orologio della filiale dell’ultima scansione finché il tracciamento Redur risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento Redur non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Redur.», con un link «Chiedi aiuto a Redur» alla pagina contatti di Redur. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Redur.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può fissare un appuntamento di consegna, modificare un indirizzo né contattare Redur al posto tuo.

## Domande sul tracciamento Redur

### Come si presenta un numero di tracciamento Redur?

I numeri di tracciamento Redur sono composti da 14 cifre senza lettere né spazi (ad esempio `58492716380419`, inventato).

### Cosa significa `PENDIENTE DE FECHA CONCERTADA` su Redur?

Significa che Redur è in attesa di concordare con te un giorno di consegna prima di mettere il pacco in uscita.

### Cosa significa `RECOGERÁN EN AGENCIA`?

Significa che il pacco è custodito presso la filiale locale Redur (*delegación*), pronto per essere ritirato da te.

### Come contatto l’assistenza clienti Redur?

Chiama il centralino Redur in Spagna al `(+34) 916 20 46 10`, scrivi a `info@redur.es` oppure usa la pagina contatti su [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: sito ufficiale](https://redur.es/) – 50+ anni, 61+ filiali Spagna e Portogallo, 3.000 addetti, socio fondatore Eurodis, *Maia* e *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – tracciamento pubblico spedizioni
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) e *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` e sede di Algete (Madrid) (URL di aiuto collegato da Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – formato 14 cifre, regola link `redur.es/track-trace/`, mappatura testi spagnolo/inglese, gestione barra di avanzamento consegnato e datazione scansioni nel fuso di ciascuna filiale
:::
