---
title: Tracciamento Dachser in Spagna e Portogallo: link e stati
description: Traccia una consegna Dachser in Spagna o Portogallo: perché conta il link completo, significato degli stati, appuntamenti e contatti.
slug: tracciamento-dachser
published: 2026-11-22
updated: 2026-11-22
---

Dachser è un gruppo logistico tedesco le cui società spagnola e portoghese trasportano merci e pallet soprattutto tra aziende, e consegnano ordini voluminosi a domicilio per i negozi che prenotano il servizio targo on-site. Per tracciare una consegna in Spagna o Portogallo, apri il link di tracciamento presente nell'e-mail o nel messaggio ricevuto sulla consegna: porta alla pagina Customer Iberia di Dachser. Incollato per intero nel campo qui sopra funziona allo stesso modo.

## Codice e link di tracciamento Dachser

Dachser non pubblica un formato fisso per i propri codici. La pagina Customer Iberia mostra un numero di spedizione («N.º Expedición») e un «N.º Único» («Unique Number» in inglese), cioè il numero che appare nel link di tracciamento. Le pagine di Dachser indicano che si può creare un link di tracciamento per ogni spedizione e condividerlo con il cliente, ma non dicono come ti arriva. Nessun messaggio? Chiedilo al negozio e vedi [come trovare il codice di tracciamento](guide:find-tracking-number).

Quello che conta è il link completo. Ha questo aspetto (numero e chiave inventati):

`https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle?numeroUnico=48291057362&hash=K7M2P9Q4R8`

Oltre al numero, include una chiave di accesso: `hash=`, oppure `clave=` insieme a un parametro `fecha=` di otto cifre. Senza la chiave la pagina non mostra nulla. Copia l'intero link esattamente com'è.

Inserito da solo, un codice Dachser assomiglia a quelli di molti altri corrieri (cifre inventate):

| Codice | Come lo interpreta un tracker |
| --- | --- |
| `4829105736` | 10 cifre: [DHL](carrier:dhl-express), [Mondial Relay](carrier:mondial-relay) e altri. |
| `48291057362` | 11 cifre: GLS e altri. |
| `482910573621` | 12 cifre: [FedEx](carrier:fedex), [MRW](carrier:mrw), BRT e altri. |
| `4829105736214` | 13 cifre: DPD France, Delhivery e altri. |
| `48291057362148` | 14 cifre: [SEUR](carrier:seur), DPD e altri. |

Se il tuo messaggio cita Dachser, fidati di quello piuttosto che della lunghezza del numero.

## Significato degli stati di tracciamento Dachser

La pagina Customer Iberia ha etichette in spagnolo, portoghese, inglese e francese, ma il testo di ciascun evento proviene dal sistema di Dachser per quella spedizione. La tabella della cronologia si chiama «Delivery Exceptions Historical» in inglese («Historial de Incidencias» in spagnolo), eppure elenca tutti gli eventi, non solo i problemi.

Il pulsante «Track & Trace» su dachser.es e dachser.pt apre un altro strumento, lo shipmentpointer del gruppo. La guida in italiano di Dachser spiega i suoi stati con queste frasi; la pagina del tuo link può formulare gli eventi diversamente:

| Testo di Dachser | Cosa significa per te |
| --- | --- |
| `La spedizione non ha ancora uno stato.` | [Etichetta creata](guide:tracking-statuses): Dachser ha i dati, non ancora uno stato della merce. |
| `La spedizione è nel deposito merci.` | È in un deposito Dachser per lo smistamento e il trasbordo. |
| `La spedizione è nel percorso principale.` | È sulla tratta a lunga distanza tra depositi. |
| `La spedizione è stata consegnata al traffico locale.` | È in consegna sul veicolo locale di distribuzione. |
| `La spedizione non è ancora nella fase di consegna o non può essere consegnata.` | È trattenuta oppure una consegna è fallita. |
| `Spedizione consegnata.` | Consegnata. |
| `La spedizione è stata "completata" (per es. gli storni).` | Annullata. |

Quelli che confondono di più:

- «Nel deposito merci» indica un magazzino Dachser, non un'altra azienda.
- «Percorso principale» è la tratta a lunga percorrenza tra il deposito di partenza e quello di consegna.
- «Consegnata al traffico locale» significa che è uscita per la consegna locale, non che è già stata consegnata a te.
- «Non è ancora nella fase di consegna o non può essere consegnata» copre vari casi. La guida di Dachser riporta tre esempi: il destinatario desidera ricevere un avviso prima della consegna, vi è stata una mancata accettazione o l'indirizzo del destinatario era errato. Controlla che il negozio abbia telefono e indirizzo corretti.
- «"Completata" (per es. gli storni)» indica un annullamento, non una consegna riuscita.

## Consegna Dachser: appuntamenti e assenze

**Appuntamenti.** Le condizioni spagnole di Dachser stabiliscono che le consegne targo on-site non hanno un termine fisso e vengono di norma concordate per telefono con il destinatario: aspettati una chiamata. Dachser descrive targo on-site come consegna su appuntamento, a bordo strada, al mattino o al pomeriggio. Con targo on-site plus la merce viene portata fino al punto d'uso e in alcuni paesi è possibile la consegna serale o di sabato. Con targo on-site fix concordi una data di consegna con il negozio già al momento dell'ordine.

**Scegliere una data online.** Alcune pagine di spedizione possono offrire il pulsante «Arrange delivery date.» («Concertar Fecha» in spagnolo), con data e fascia oraria da selezionare e confermare. Se non lo vedi, attendi la chiamata di Dachser o rivolgiti al negozio.

**Giorni e tempi.** Dachser non ha obbligo di consegnare la domenica o nei giorni festivi. La consegna di sabato è possibile solo con i servizi targospeed 10 e targofix 10, previo accordo con la filiale Dachser. I tempi di consegna sono solo indicativi: decorrono dal giorno lavorativo successivo al ritiro, esclusi fine settimana e festivi.

**Nessun punto di ritiro né locker.** Le pagine spagnole e portoghesi di Dachser non menzionano punti di ritiro né armadietti per i destinatari, né avvisi di passaggio, numero di tentativi o tempi di giacenza.

**Consegna mancata.** Secondo le condizioni spagnole e portoghesi di Dachser, una consegna non riuscita per assenza, indirizzo errato o incompleto o rifiuto della merce ricade sotto la responsabilità del mittente, che ne sostiene i costi. Una seconda consegna è un servizio extra a pagamento per il mittente. Se hai perso la consegna, avvisa subito il negozio.

## Consegna Dachser in ritardo, danneggiata o smarrita

Hai acquistato dal negozio, ed è il negozio o il mittente ad avere il contratto con Dachser: rivolgiti prima a loro per ritardi, smarrimenti e rimborsi.

**Nessun aggiornamento.** Controlla di non avere una chiamata persa da Dachser, poi leggi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Danni.** Le condizioni di Dachser richiedono che perdita o danneggiamento siano descritti per iscritto al momento della consegna: controlla quindi la merce prima che l'autista riparta e annota ogni danno sui documenti di consegna. Questi termini valgono nel contratto tra Dachser e il suo cliente, sia in Spagna sia in Portogallo:

- **Danni non visibili:** entro 7 giorni di calendario dalla consegna nel trasporto nazionale; in quello internazionale, 7 giorni escluse domeniche e festività.
- **Ritardo:** riserve scritte entro 21 giorni dal giorno successivo alla consegna (trasporto nazionale).

Conserva la merce nell'imballaggio originale a disposizione di Dachser e avvisa il negozio il giorno stesso.

**Consegnata ma non trovata.** Chiedi al negozio di verificare la prova di consegna e vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

## Numero di telefono e assistenza Dachser

Dachser non elenca una linea clienti dedicata ai destinatari. Sulle sue pagine ufficiali figurano questi recapiti:

- **Spagna:** DACHSER Spain, S.A., Regional Head Office Madrid, Coslada: +34 916 60 65 78, dachser-spain.iberia@dachser.com. Non sono indicati orari né costi di chiamata.
- **Portogallo:** DACHSER Portugal, Vila do Conde: +351 221 210 500 (chiamata verso rete fissa nazionale), stessa e-mail. Nessun orario indicato.
- **Moduli e sedi:** dachser.es e dachser.pt hanno ciascuno un modulo di contatto, e dachser.es offre un cercatore di filiali.
- **Reclami in Portogallo:** dachser.pt rimanda al registro ufficiale dei reclami (*Livro de Reclamações*).
- **Germania:** DACHSER SE, Kempten: +49 831 5916 0, info@dachser.com.

## Tracciare una consegna Dachser con Peek

[Peek](/) segue oltre 4.500 corrieri, ma non può riconoscere un codice Dachser dalle sole cifre né consultarlo senza chiave: incolla quindi il link completo del tuo messaggio, che Peek riconosce come Dachser e conserva. Se hai digitato solo il numero, premi «Cambia» (o «Cambia corriere»), seleziona Dachser e incolla il link nel campo «Link di tracciamento completo». Un link con altro indirizzo, senza chiave, con un parametro sconosciuto o di un altro numero viene rifiutato con «Usa il link di tracciamento completo dell’e-mail di spedizione.».

Peek legge esclusivamente la pagina Customer Iberia e copre quindi le consegne Dachser in Spagna e Portogallo. Mostra lo stato, la cronologia e il giorno di consegna previsto finché il pacco non è in consegna. Ogni passaggio compare con le parole proprie di Peek — come «Dati della spedizione ricevuti», «Spedizione presa in carico», «Spedizione arrivata in una sede Dachser», «In consegna», «Appuntamento di consegna aggiornato» o «Consegnato» — senza salvare il testo originale di Dachser. Gli orari vengono letti nel fuso orario fornito da Dachser o, in mancanza, come ora di Madrid, e mostrati nel fuso orario del tuo dispositivo. Dachser non indica località, quindi sulla mappa non c'è quasi nulla da mostrare. Mittente, recapiti di contatto e firma vengono scartati (e Peek non visualizza dati personali del destinatario). Se la spedizione resta ferma, Peek mostra il link «Chiedi aiuto a Dachser».

Finché il tracciamento di Dachser risponde, Peek controlla fino a ogni 10 minuti con il pacco aperto sullo schermo o con le notifiche attive se la consegna è prevista oggi. Dalle 6:00 sull'orologio del corriere nel giorno in cui la spedizione va in consegna, i controlli possono arrivare ogni 2 minuti con la spedizione aperta sullo schermo o con gli avvisi attivi (se c'è una fascia oraria, i controlli ravvicinati iniziano al più presto mezz'ora prima dell'apertura). Fuori schermo senza notifiche, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla una volta all'ora. Quando risponde solo un servizio di tracciamento, Peek controlla ogni ora (ogni 3 ore quando l'ultima scansione ha più di un giorno), oppure fino a ogni 15 minuti nel giorno in cui esce in consegna con il pacco aperto o con le notifiche attive. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Una spedizione sconosciuta o un link non valido può risultare non trovato o come controllo fallito.

## Domande sul tracciamento Dachser

### Dove ha sede Dachser?

A Kempten, in Germania: DACHSER SE è un gruppo familiare fondato da Thomas Dachser nel 1930. La sede regionale di DACHSER Spain è a Coslada (Madrid) e DACHSER Portugal si trova a Vila do Conde.

### Dachser consegna ai privati a domicilio?

Sì, quando il negozio prenota il servizio: targo on-site consegna ai privati su appuntamento, a bordo strada, al mattino o al pomeriggio. La consegna fino al punto d'uso è targo on-site plus, scelta dal venditore.

### Posso cambiare la data di consegna Dachser?

A volte. Se la pagina Customer Iberia mostra il pulsante per fissare la data («Arrange delivery date.» / «Concertar Fecha»), scegli lì giorno e fascia oraria; altrimenti rispondi alla chiamata di Dachser o contatta il negozio.

:::sources
- [DACHSER Spain: Condiciones generales European Logistics](https://www.dachser.es/downloads/Spain/Spain_Terms_Conditions.zip) – appuntamento telefonico, domeniche, sabati, tempi indicativi, costi mancata consegna, extra, reclami
- [DACHSER Portugal: Condições gerais de transporte](https://www.dachser.pt/downloads/Portugal/Portugal_Terms_Conditions.zip) – tempi indicativi, costi mancata consegna, extra, reclami
- [DACHSER Spain: targo on-site fix permite fijar una fecha de entrega al realizar un pedido](https://www.dachser.es/es/mediaroom/DACHSER-amplia-su-cartera-de-servicios-para-entregas-B2C-24216) – targo on-site, bordo strada, plus, fix
- [DACHSER Portugal: DACHSER disponibiliza serviço entargo em Portugal e Espanha](https://www.dachser.pt/pt/mediaroom/DACHSER-disponibiliza-servico-entargo-em-Portugal-e-Espanha-16917) – consegna su appuntamento mattino e pomeriggio
- [DACHSER Spain: eLogistics](https://www.dachser.es/es/elogistics-116) – link di tracciamento condiviso con i clienti
- [DACHSER Spain: Información legal](https://www.dachser.es/es/informacion-legal-273) – società, Coslada, telefono, e-mail, DACHSER SE
- [DACHSER Portugal: Aviso legal](https://www.dachser.pt/pt/aviso-legal-273) – società, Vila do Conde, telefono, e-mail
- [DACHSER Spain: Contacto](https://www.dachser.es/es/contacto-275) – modulo di contatto
- [DACHSER Portugal: Contacto](https://www.dachser.pt/pt/contacto-275) – modulo di contatto
- [DACHSER Spain: Home](https://www.dachser.es/es/) – Track & Trace apre shipmentpointer
- [DACHSER Portugal: Home](https://www.dachser.pt/pt/) – Track & Trace, Livro de Reclamações
- [Dachser Customer Iberia: pagina pubblica della spedizione](https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle) – la pagina aperta dal link
- [Dachser Customer Iberia: testi inglesi della pagina](https://customeriberia.dachser.com/customerarea/assets/i18n/common/en.json) – chiavi di ricerca, tabella cronologia, Arrange delivery date
- [Dachser Customer Iberia: testi spagnoli della pagina](https://customeriberia.dachser.com/customerarea/assets/i18n/common/es.json) – N.º Único, Historial de Incidencias
- [Dachser: guida shipmentpointer (italiano)](https://elogistics.dachser.com/help/it/shp.html) – testi degli stati in italiano ed esempi
- [Dachser: Company](https://www.dachser.com/en/company-10) – Kempten, 1930, gruppo familiare
- [Universal Parcel Scraper: Dachser](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dachser) – link completo richiesto, campi letti e scartati, ora di Madrid, link di aiuto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek
:::
