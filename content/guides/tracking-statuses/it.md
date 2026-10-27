---
title: Stati della spedizione e del tracking: cosa significano
description: Cosa vuol dire ogni stato del tracking, da «etichetta creata» a «consegnato», anche per i pacchi dall'estero («consegnato al corriere locale») e quando tocca a te.
slug: stati-spedizione-cosa-significano
picture: Un percorso tortuoso con tappe spuntate (etichetta, deposito, aereo), un furgone alla tappa attuale, una casa ancora davanti e Pip che corre.
published: 2026-10-09
updated: 2026-10-27
---

Uno stato della spedizione è l'ultima scansione del corriere riassunta in poche parole, e quasi sempre non ti chiede niente. «Etichetta creata» vuol dire che il corriere il pacco non ce l'ha ancora, «in transito» che è da qualche parte tra due scansioni, «in consegna» che è sul furgone di oggi. Solo pochi stati ti chiedono di fare qualcosa: una mancata consegna, un pacco in giacenza da ritirare, qualcosa da pagare in dogana e un «consegnato» quando alla porta non c'è niente.

## Quali sono le fasi di una spedizione?

Ogni pacco passa dalle stesse tappe; se arriva dall'estero, se ne aggiungono tre.

:::journey
- label | Etichetta creata | Il corriere ha i dati, non il pacco.
- warehouse | In transito | Ritirato, poi da un hub all'altro, con lunghi silenzi.
- plane | Partito dal Paese | Solo dall'estero. In volo niente scansioni.
- customs | Dogana | Solo dall'estero. A volte c'è da pagare.
- handover | Corriere locale | Solo dall'estero. Le sue scansioni a volte si vedono solo sul suo sito.
- truck | In consegna | Sul furgone di oggi.
- locker | Pronto per il ritiro | In un ufficio postale, un negozio o un locker.
- home | Consegnato | Dato in mano, o lasciato dove per il corriere vale come consegna.
:::

## Cosa vogliono dire gli stati di Poste, UPS e La Posta?

Stesse tappe, parole diverse:

| Fase | Poste Italiane | UPS | La Posta (Svizzera) |
| --- | --- | --- | --- |
| Etichetta creata | `Tracciatura non disponibile` (codice appena creato) | `Shipper created a label, UPS has not received the package yet.` | `Notifica dell'invio da parte dello speditore (inoltro dei dati)` |
| Preso in carico | `Presa in carico` | `Drop-Off`, `Pickup Scan` | `Momento dell'impostazione del proprio invio` |
| In transito | `In transito` | `Arrived at Facility`, `Departed from Facility` | `L'invio è stato spartito e inoltrato` |
| In consegna | `In consegna` | `Loaded on Delivery Vehicle`, `Out For Delivery Today` | `L'invio è stato spartito per il recapito` (smistato verso la tua località) |
| Pronto per il ritiro | Può restare `In consegna` | `Delivered to UPS Access Point™` | `Arrivo al punto di ritiro / ufficio di recapito` |
| Consegnato | `la spedizione è stata consegnata` | `DELIVERED` | `Recapitato da` |

Le scansioni di UPS sono in inglese, così come le legge Peek. In Svizzera, nel tracking inglese di [Planzer](carrier:planzer) e [Quickpac](carrier:quickpac), `Shipped` vuol dire già consegnato, non spedito.

## «Etichetta creata»: il corriere ha già il pacco?

No: il venditore ha stampato un'etichetta e mandato i dati al corriere, niente di più. Con [FedEx](carrier:fedex) questa fase, in inglese, si chiama `Shipment information sent to FedEx`. La prima scansione di [UPS](carrier:ups) dice che il mittente ha creato l'etichetta e che UPS non ha ancora il pacco. Per la [Posta svizzera](carrier:swiss-post), con `Notifica dell'invio da parte dello speditore (inoltro dei dati)` il mittente ha generato il numero d'invio, ma di norma il pacco non è ancora alla Posta. Diciture come «spedizione generata, in attesa di ritiro» vogliono dire la stessa cosa.

Su [Poste](carrier:poste-italiane) un codice appena creato dà `Tracciatura non disponibile`: Poste consiglia di riprovare dopo qualche ora. Se l'etichetta resta l'unica riga per giorni, scrivi al venditore, non al corriere: il pacco ce l'ha ancora lui.

## Cosa significa «in transito»?

Il corriere ha il tuo pacco, da qualche parte nella sua rete. Poste avverte che, per fare prima, il pacco può passare da tappe intermedie che sembrano fuori strada rispetto a partenza e arrivo.

Tra un hub e l'altro, il silenzio è normale: in Germania, DHL avverte che uno stato di smistamento può restare uguale per ore o giorni mentre il pacco continua a viaggiare.

### Quanto resta in transito un pacco?

In Italia, di solito poco. L'obiettivo di Poste per il Poste Delivery Standard è la consegna entro 5 giorni lavorativi (sabato escluso) nel 90% dei casi; [BRT (ex Bartolini)](carrier:brt) dichiara 1 o 2 giorni lavorativi per il 96% delle spedizioni. In Svizzera un PostPac Priority arriva di norma il giorno feriale successivo, un PostPac Economy entro due giorni lavorativi. Tra Paesi diversi ci vuole di più: per le spedizioni dall'Italia all'estero BRT dichiara da 2 a 10 giorni lavorativi nel 96% dei casi. Fermo da più tempo del previsto? Leggi [perché il tracking non si aggiorna](guide:tracking-not-updating).

## Pacchi dall'estero: partenza, dogana, corriere locale

- **Partito dal Paese.** Il pacco viaggia in aereo, in nave o in treno, e nel frattempo non lo scansiona nessuno. La Posta mostra `Arrivo alla frontiera del paese di destinazione`, e avverte che prima del recapito il pacco può dover passare dalla dogana. Può comparire anche `Termine di recapito sconosciuto`: per un invio che arriva dall'estero o ci va, la data non si può prevedere. Se invece spedisci tu con Poste Delivery International Standard, `In transito presso il Centro di lavorazione Internazionale` (il pacco ha lasciato l'Italia) è l'ultima traccia che vedrai.
- **Dogana.** Su Poste compare `In attesa di sdoganamento`: il pacco è sotto controllo doganale e potrebbero chiederti altri documenti. La Posta scrive `L'invio è stato consegnato alla dogana`, poi `Procedura di sdoganamento postale in corso`, infine `Autorizzato dalla dogana svizzera`, quando passa al recapito nazionale. DHL parla di alcuni giorni lavorativi, la Posta di tre o quattro giorni lavorativi se i dati sono completi e corretti; nessuno dei due dà un massimo. Muoviti solo se ti chiedono un documento o un pagamento; leggi [pacco fermo in dogana](guide:customs).
- **Corriere locale.** Diciture come «consegnato al corriere locale» o «in transito verso l'operatore dell'ultimo miglio» vogliono dire che l'ultimo tratto lo fa un'altra azienda. DHL ti rimanda al tracking del corriere del Paese di destinazione. Hai ordinato su AliExpress, Temu o Shein? Leggi [come tracciare un pacco dalla Cina](guide:tracking-from-china). Le righe in inglese di [Cainiao](carrier:cainiao) e [YunExpress](carrier:yunexpress) sono spiegate nelle pagine dedicate.

## «In consegna» vuol dire che arriva oggi?

Di solito sì. Su Poste, `In consegna` vuol dire che il pacco è stato affidato a chi lo consegna. Ma anche con questo stato, per Poste la data prevista è solo indicativa.

Due diciture ingannano. Su Poste, `In consegna` può restare anche dopo che il pacco è stato consegnato, o quando ti aspetta già in un ufficio postale o in un Punto Poste; se la consegna non riesce, torna `In transito`. E `L'invio è stato spartito per il recapito` della Posta vuol dire che un centro l'ha smistato verso la tua località: non è detto che sia già sul furgone. Quanto a «fuori per la consegna», che si legge su alcuni tracking tradotti dall'inglese, è semplicemente «in consegna».

[Peek](/) ricontrolla un pacco in consegna fino a ogni 2 minuti, e il resto del percorso fino a ogni 10 minuti. Puoi impostare le notifiche su «Solo giorno di consegna».

## Tentativo di consegna fallito o pacco in giacenza: cosa fare?

Questo stato invece ti chiede di muoverti: [non c'era nessuno a ricevere il pacco](guide:missed-delivery), o il corriere non è riuscito a entrare. «In giacenza» vuol dire che ora il pacco aspetta in filiale o all'ufficio postale, [finché lo ritiri o qualcuno decide cosa farne](guide:uncollected-parcel).

:::steps
- Leggi l'avviso | Cartaceo, SMS o e-mail: dice dov'è finito il pacco e ti dà un codice da usare.
- Controlla la scadenza | Da 7 a 15 giorni, contati in giorni di calendario o lavorativi: dipende dal corriere.
- Riprogramma o sposta | Sul sito o nell'app del corriere: un altro giorno, un vicino, un punto di ritiro.
- Ritira con un documento | Porta un documento d'identità e l'avviso. Per chi ritira al posto tuo di solito serve una delega.
:::

| Corriere | Dopo una mancata consegna | Quanto lo tengono |
| --- | --- | --- |
| Poste Delivery Standard | Due tentativi a domicilio, poi l'avviso di giacenza | 10 giorni lavorativi, sabato compreso; poi torna al mittente, salvo che abbia chiesto l'abbandono |
| Poste Delivery Express | Avviso di mancata consegna | 10 giorni lavorativi, sabato compreso; torna al mittente solo se l'ha chiesto, altrimenti è abbandonato |
| BRT | Avviso via e-mail, SMS o carta; secondo tentativo, poi la filiale ti telefona | In giacenza in filiale: torna indietro se il mittente non dà istruzioni scritte entro 10 giorni |
| [GLS](carrier:gls-it) | «Avviso di passaggio»; primo e secondo tentativo | In giacenza alla sede GLS: 10 giorni lavorativi (8 per i pacchi dall'estero) secondo la Carta dei Servizi, poi senza istruzioni torna al mittente |
| UPS | La scansione dice che non c'eri o che l'attività era chiusa, e UPS riprova; dopo l'ultimo tentativo tiene il pacco, lo porta in un Access Point o lo rimanda al mittente | – |
| Mondial Relay (Italia) | Il pacco è già indirizzato a un Point Relais o a un locker | 7 giorni, senza proroga; poi torna al mittente |
| La Posta (Svizzera) | Invito di ritiro | 7 giorni, 15 per i pacchi dall'estero; proroga gratuita, anche senza account |

Con BRT, il BRTcode lo trovi negli SMS, nelle e-mail o sul tagliando del corriere: con quello cambi data e fascia oraria, scegli un BRT-fermopoint o sblocchi la giacenza. Con la Posta, dopo il login a «I miei invii», puoi chiedere un secondo recapito (gratis se il pacco viene depositato o dato a un vicino, CHF 3.30 al piano, CHF 7.00 il sabato) o un inoltro (CHF 8.00 dal lunedì al venerdì). Hai perso l'avviso di Poste? Chiama il Servizio Clienti e il portalettere te ne porta un duplicato.

> Un SMS che parla di un pacco trattenuto e ti chiede di pagare da un link è il [classico messaggio truffa](guide:delivery-scam-texts). La Posta svizzera cita proprio «Pacco trattenuto presso il terminale» tra le frasi tipiche dei messaggi falsi, di solito seguita da una richiesta di pagamento. Poste Italiane ricorda che non ti chiederà mai, né via SMS né via e-mail, i dati della carta, i codici OTP o le credenziali. Controlla il pacco solo sul sito o nell'app del corriere, scrivendo tu l'indirizzo.

## «Disponibile per il ritiro»: dove si trova il pacco?

In un ufficio postale, in un punto di ritiro o in un locker, fino alla scadenza della tabella qui sopra. UPS lo segnala con `Delivered to UPS Access Point™`; per la Posta il pacco è disponibile da quando compare `Arrivo al punto di ritiro / ufficio di recapito`.

Su Poste, invece, lo stato può restare `In consegna` anche se il pacco ti aspetta già in un ufficio postale, in un Punto Poste o in un Punto Poste Da Te. Se è all'ufficio postale, nel Cerca spedizioni compare il pulsante «Prenota»: scegli giorno e ora per ritirarlo, entro la giacenza.

Prima di uscire, controlla che sia davvero pronto: su Poste, l'avviso riporta la data da cui puoi ritirarlo.

## «Consegnato» vuol dire che ce l'hai tu?

Non sempre. Vuol dire che il corriere l'ha scansionato come consegnato o lasciato:

- **A qualcun altro.** Per le condizioni generali di Poste possono ricevere un invio al tuo domicilio anche i familiari, i conviventi, i collaboratori familiari e il portiere, se c'è. Con BRT puoi indicare tu un vicino a cui lasciarlo, purché abiti allo stesso indirizzo.
- **In un posto sicuro.** La pagina di UPS può indicare dove ha lasciato il pacco (per esempio `Left at: Front Door`).
- **Nella cassetta o allo sportello.** La Posta distingue `Recapitato da` (a domicilio), `Recapitato nello scomparto di deposito / cassetta delle lettere` (la cassetta del latte) e `Recapitato allo sportello` (una filiale, una filiale in partenariato o un punto PickPost).

Non c'è niente? Cerca nel tracking una foto, un nome o un luogo, poi leggi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

## «Eccezione» o «ritardo»: il pacco è perso?

No. `Exception` è lo stato generico di UPS e può voler dire anche solo un ritardo: conta la scansione sotto.

La Posta ha `Ritardo` ed `Errore di avviamento`: per esempio, un numero postale di avviamento (il CAP svizzero) sbagliato manda il pacco all'ufficio sbagliato, e può esserci un ritardo, ma non è detto.

Guarda meglio quando c'entra l'indirizzo o un pagamento. BRT mette il pacco in giacenza se il destinatario è assente, sconosciuto o trasferito, se l'indirizzo è sbagliato o incompleto, o se la merce è stata rifiutata; puoi sbloccarla tu dal link dell'SMS o dell'e-mail, o con il BRTcode. La Posta segnala `Tentativo di recapito, indirizzo non corretto`.

## Perché il pacco è stato restituito al mittente?

I motivi dei corrieri: non ritirato in tempo, indirizzo sbagliato o incompleto, destinatario irreperibile, rifiuto, richiesta del mittente. Ognuno lo scrive a modo suo:

- **Poste** `Resa al mittente`: dal tracking segui il viaggio di ritorno con «Segui la spedizione di ritorno al mittente». Se poi non si riesce a restituirlo nemmeno al mittente, il pacco viene distrutto o dato in beneficenza.
- **La Posta** `Rinvio`, con il motivo: `Non ritirato`, `Respinto`, trasloco con l'ordine di rispedizione scaduto (di norma dopo un anno), destinatario irreperibile all'indirizzo indicato.
- **BRT:** un pacco in giacenza torna indietro dopo 10 giorni dall'avviso di giacenza mandato al mittente, se lui non dà istruzioni scritte.
- **Mondial Relay:** un pacco non ritirato entro 7 giorni torna in automatico al mittente.

A quel punto decide il venditore: chiedigli di [rispedirtelo o di rimborsarti](guide:returned-to-sender).

:::sources
- [Poste Italiane: Cerca spedizioni, legenda degli stati](https://www.poste.it/cerca/index.html) – Presa in carico, In transito, In consegna, sdoganamento, Centro di lavorazione Internazionale, Resa al mittente, Tracciatura non disponibile, data prevista indicativa
- [Poste Italiane: FAQ Spedizioni e Consegne](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – giacenza di Poste Delivery Express e Standard, duplicato dell'avviso, «Prenota»
- [Poste Italiane: Carta del Servizio Postale Universale](https://www.media.poste.it/c919f7cf-8236-428e-82fc-f8b2be8428b8/web/carta-servizi-universali) – Poste Delivery Standard: due tentativi, giacenza, 90% in 5 giorni lavorativi
- [Poste Italiane: Condizioni generali del servizio postale](https://www.media.poste.it/404d312b-44e4-4a27-bace-a1741986ebc2/web/condizioni-generali-espletamento-servizio-postale-universale) – chi può ricevere al tuo domicilio (art. 27)
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – dati della carta, OTP e credenziali
- [BRT: Domande frequenti](https://www.brt.it/it/servizio-clienti/faq/) – BRTcode, nuova consegna, sblocco della giacenza
- [BRT: Ho la spedizione in giacenza, cosa significa?](https://www.brt.it/it/faq/ho-la-spedizione-in-giacenza-cosa-significa/) – motivi della giacenza
- [BRT: Carta dei Servizi](https://www.brt.it/wp-content/uploads/sites/275/2026/03/Carta-dei-Servizi_marzo_2026.pdf) – avviso, secondo tentativo, telefonata, consegna al vicino, 10 giorni, 96%
- [GLS: La consegna e le possibili opzioni](https://gls-group.com/IT/it/spedire-ricevere/opzioni-consegna/) – avviso di passaggio, due tentativi, giacenza in sede
- [GLS Italia: Carta dei Servizi Postali](https://gls-group.com/IT/media/downloads/CARTA_DEI_SERVIZI_POSTALI_AGGIORNATAallegati_09.06.26_def.pdf) – 10 giorni lavorativi di giacenza, 8 per i pacchi dall'estero
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – scansioni UPS che legge Peek: etichetta, presa in carico, transito, consegna, mancata consegna, Access Point, «Left at», «Exception»
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – scansioni FedEx che legge Peek: dati della spedizione inviati a FedEx
- [Mondial Relay Belgio: Quel est le délai de garde d'un colis en Locker ou Point Relais ? (in francese)](https://www.mondialrelay.be/fr-be/faq/recevoir-un-colis/quel-est-le-d%C3%A9lai-de-garde-dun-colis-en-locker-ou-point-relais/) – Italia 7 giorni, nessuna proroga, ritorno al mittente
- [La Posta: Eventi «Monitorare gli invii»](https://www.post.ch/it/ricezione/monitorare-gli-invii/eventi-monitorare-gli-invii) – stati, dogana, recapito, errore di avviamento, rinvio
- [La Posta: FAQ su importazione, dogana e IVA](https://www.post.ch/it/soluzioni-commerciali/esportazione-importazione-e-sdoganamento/importazione/faq-su-importazione-dogana-e-iva) – sdoganamento in tre o quattro giorni lavorativi
- [La Posta: Invito di ritiro](https://www.post.ch/it/ricezione/invito-di-ritiro) – 7 e 15 giorni, proroga, secondo recapito, inoltro, prezzi
- [La Posta: PostPac Priority](https://www.post.ch/it/spedire-pacchi/pacchi-svizzera/postpac-priority) – giorno feriale successivo
- [La Posta: PostPac Economy](https://www.post.ch/it/spedire-pacchi/pacchi-svizzera/postpac-economy) – due giorni lavorativi
- [La Posta: Attuali tentativi di frode](https://site.post.ch/it/chi-siamo/sicurezza/phishing-e-frodi/tentativi-di-frode-attuali) – «Pacco trattenuto presso il terminale», verifica su posta.ch o nella Post-App
- [Planzer: Tracking](https://tracking.app.planzer.ch/) – `Shipped` nel tracking inglese di Planzer e Quickpac
- [DHL: What does my shipment status mean?](https://www.dhl.de/en/privatkunden/hilfe-kundenservice/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – stati fermi per ore o giorni
- [DHL: International shipment status](https://www.dhl.de/en/privatkunden/hilfe-kundenservice/themen/international/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – dogana in alcuni giorni lavorativi, tracking del corriere di destinazione
- [Peek: home page](https://peektracker.com/it) – «Controllato fino a ogni 10 min» e «Fino a ogni 2 min nell'ultimo miglio»
:::
