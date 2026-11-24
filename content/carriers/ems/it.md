---
title: Tracciamento EMS: codici S10, stati e aiuto postale
description: Traccia un pacco internazionale EMS (Express Mail Service): codici S10 da 13 caratteri con E, stati dogana e consegna UPU, ritardi e contatti postali.
slug: tracciamento-ems
published: 2026-11-24
updated: 2026-11-24
---

**EMS** (*Express Mail Service*) è il servizio postale espresso internazionale per documenti e merci. L’**Unione Postale Universale (UPU)** ha istituito la **Cooperativa EMS** ([ems.post](https://www.ems.post/en)) per svilupparlo; nel giugno 2026 la Cooperativa contava 179 membri. Anziché operare propri aerei o furgoni, EMS collega gli operatori postali nazionali designati di tutto il mondo — come [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) e [Australia Post](carrier:australia-post) — così che documenti e merci espressi viaggino con un unico identificativo standard di 13 caratteri. Puoi controllare una spedizione sul tracciamento globale EMS su [items.ems.post](https://items.ems.post/) oppure incollare il codice EMS nel riquadro qui sopra.

## Codici di tracciamento EMS (formato UPU S10 a 13 caratteri)

Ogni spedizione EMS riporta un codice a barre UPU S10 di 13 caratteri che inizia con la lettera **`E`** (per Express), seguita da una seconda lettera di servizio, 8 cifre seriali, 1 cifra di controllo S10 e il codice ISO a 2 lettere del paese di origine:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `EA482917367JP` | Spedizione EMS a 13 caratteri inviata dal Giappone (prefisso `EA` + 9 cifre con cifra di controllo S10 valida + `JP`). |
| `EE739184627CN` | Spedizione EMS a 13 caratteri inviata dalla Cina (prefisso `EE` + 9 cifre con cifra di controllo S10 valida + `CN`). |

Le ultime due lettere identificano sempre l’**operatore postale di origine**, mai il paese di destinazione. Un pacco inviato via EMS da Tokyo a Parigi termina in `JP` per l’intero tragitto, anche quando [La Poste](carrier:la-poste) subentra per la consegna finale in Francia. I codici di spedizione postale che non iniziano per `E` (come le raccomandate `RR...` o i pacchi ordinari `CP...`) vengono rifiutati dal tracciamento EMS perché non indicano un invio EMS. Vedi i [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento EMS

La tabella pubblica di tracciamento della Cooperativa EMS su [items.ems.post](https://items.ems.post/) traduce gli eventi EDI degli operatori postali aderenti in descrizioni standard in inglese:

| EMS mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Posted` | Accettato allo sportello o ritirato dall’operatore postale di origine. | «Preso in carico» |
| `Arrived at export office`, `Departed from export office` | Lavorato presso l’ufficio di scambio internazionale del paese di origine e spedito all’estero. | «In transito» |
| `Presented to export customs and security`, `Held for export customs inspection` | Sottoposto a controlli doganali export o di sicurezza aerea nel paese di origine. | «In dogana» |
| `Released from export customs and security` | Controlli di esportazione superati e pronto per la partenza internazionale. | «In transito» |
| `Export cancelled` | La spedizione in esportazione è stata annullata o restituita prima di lasciare il paese di origine; contatta il mittente. | «Problema segnalato» |
| `Arrived at destination import office`, `Departed from destination import office` | Arrivato al centro di scambio internazionale del paese di destinazione o ripartito verso lo smistamento nazionale. | «In transito» |
| `Held at destination import office`, `Presented to import customs` | Trattenuto all’ufficio di scambio di frontiera o presentato alla dogana di importazione nel paese di destinazione. | «In dogana» |
| `Released from import customs` | Sdoganato all’importazione e affidato alla rete postale nazionale. | «In transito» |
| `Arrived at sorting center`, `Arrived at post office` | In movimento negli hub nazionali delle poste di destinazione oppure giunto all’ufficio postale locale di consegna. | «In transito» |
| `Out for delivery` | In consegna con il portalettere locale o il corriere EMS per la consegna finale. | «In consegna» |
| `Delivery attempted` | Consegna tentata senza esito; controlla un avviso di mancata consegna o una comunicazione dell’ufficio postale locale. | «Tentativo di consegna» |
| `Returned to sender` | In rientro verso il paese di origine dopo consegna fallita, rifiuto o respingimento doganale. | «Restituito al mittente» |
| `Delivered` | Consegnato al destinatario o presso un punto di ritiro autorizzato dall’operatore di destinazione. | «Consegnato» |

## Passaggi internazionali e dogana con EMS

Poiché EMS è una rete cooperativa di poste nazionali elencate nella [directory degli operatori EMS](https://www.ems.post/en/global-network/ems-operators), due diversi enti postali gestiscono ogni invio transfrontaliero:

- **Poste di origine e poste di destinazione:** l’operatore postale di origine gestisce accettazione, sdoganamento export e spedizione internazionale; una volta apparso `Arrived at destination import office`, il tuo operatore postale nazionale (o il suo ramo espresso, come Parcelforce nel Regno Unito) gestisce sdoganamento import, tentativi di consegna, riconsegna e periodi di giacenza in ufficio postale.
- **Controlla sia EMS sia le tue poste locali:** il flusso globale EMS inoltra tappe EDI standardizzate, mentre la pagina di tracciamento delle poste di destinazione aggiunge spesso dettagli nazionali più ricchi, come link per pagare i dazi, indirizzo dell’ufficio di ritiro, fasce orarie o riprogrammazione della consegna.
- **Dazi doganali e IVA di importazione:** i pacchi EMS internazionali sono soggetti alle norme doganali di destinazione, all’IVA di importazione, alle spese postali di sdoganamento e ai dazi (inclusi gli oneri UE per importazioni extra-UE). Se il tracciamento si ferma su `Presented to import customs` o `Held at destination import office`, attendi una lettera, un’e-mail o un avviso ufficiale sul portale delle tue poste locali. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco EMS in ritardo, fermo o smarrito

- **Pausa tra export e import:** le spedizioni aeree internazionali restano spesso diversi giorni senza scansioni tra `Departed from export office` e `Arrived at destination import office`, soprattutto nei periodi di punta o in attesa della presa in carico doganale. Vedi [perché il tracciamento smette di aggiornarsi](guide:tracking-not-updating).
- **Ti aiutano le tue poste locali, non l’UPU:** come indicato su [EMS Tracking](https://www.ems.post/en/global-network/tracking), l’Unità EMS e l’UPU non forniscono tracciamento telefonico e non intervengono nelle controversie tra operatori postali e clienti. Per un invio EMS mancante, la [pagina contatti EMS](https://www.ems.post/en/about-us/contacts-0) indirizza al servizio clienti o al call center EMS locale, che risponde alle richieste generali EMS, aiuta a trovare il codice e collabora nella ricerca del pacco. Vedi [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti EMS ed elenco ufficiale degli operatori

Poiché il servizio EMS di ogni paese è gestito dal rispettivo operatore postale nazionale designato, [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) pubblica una directory ricercabile dalla A alla Z delle pagine di assistenza clienti e dei call center EMS nazionali filtrabili per area (Africa, Paesi Arabi, Asia Pacifico, Caraibi, Europa, America Latina e Nord America):

- **Trova telefono e assistenza del tuo operatore EMS locale:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) e [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Tracciamento pubblico globale:** [items.ems.post](https://items.ems.post/) (istruzioni su [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Nessuna assistenza telefonica centrale UPU:** né l’Unità EMS né l’Unione Postale Universale gestiscono per telefono le richieste su singoli pacchi.

> Fai attenzione ai falsi SMS che sostengono che un «pacco EMS» è bloccato finché non paghi una piccola somma tramite un link sconosciuto. Verifica sempre eventuali oneri doganali direttamente sul sito ufficiale del tuo operatore postale nazionale con il tuo codice EMS a 13 caratteri. Vedi [truffe via SMS sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco EMS con Peek

Quando inserisci un codice S10 di 13 caratteri che inizia con `E` (`EA482917367JP` o `EE739184627CN`, inventati) in [Peek](/), Peek lo indirizza normalmente per primo all’operatore postale di origine indicato dalle ultime due lettere (come [Japan Post](carrier:japan-post) o [China Post](carrier:china-post)), perché EMS non ha una regola autonoma di rilevamento automatico. Per interrogare invece il tracciamento globale della Cooperativa **EMS**, tocca «Cambia» in fase di aggiunta oppure «Cambia corriere» nella schermata del pacco e seleziona **EMS** (anche cercare «Express Mail Service» o «EMS Post» lo trova). Peek riconosce direttamente come EMS anche i link incollati `items.ems.post/api/publicTracking/track?itemId=`.

Peek interroga direttamente `items.ems.post` dopo aver verificato che il codice sia un S10 con prefisso `E` e cifra di controllo valida, e mostra la fase («Preso in carico», «In transito», «In dogana», «In consegna», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), il testo inglese EMS e l’ufficio. EMS fornisce gli orari delle scansioni senza fuso orario: Peek data ogni scansione nel fuso orario del relativo ufficio (Tokyo per `TOKYO INTERNATIONAL`, Parigi per `ROISSY CDG`), e una scansione senza località nel fuso della scansione vicina, mostrandole tutte con l’ora del tuo dispositivo. La tabella EMS non include data di consegna stimata, peso, nome del firmatario né orario di consegna separato.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui EMS mostra `Out for delivery`, ogni 2 minuti dalle 6:00 secondo l’ora locale del luogo dell’ultima scansione, finché il tracciamento EMS risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento EMS non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a EMS.», con un link «Chiedi aiuto a EMS» alla directory dei contatti EMS. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a EMS.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può sdoganare pacchi, pagare oneri di importazione o contattare le tue poste locali al posto tuo.

## Domande sul tracciamento EMS

### Come si presenta un codice di tracciamento EMS valido?

Un codice di tracciamento EMS è un codice UPU S10 di 13 caratteri che inizia con `E`, una seconda lettera, 9 cifre (con cifra di controllo finale) e il codice ISO a 2 lettere del paese di invio — ad esempio `EA482917367JP` o `EE739184627CN` (inventati).

### Chi consegna il mio pacco EMS nel mio paese?

I pacchi EMS vengono consegnati dall’operatore postale nazionale designato del tuo paese (o dalla sua divisione espressa), come [USPS](carrier:usps) negli Stati Uniti, [Royal Mail](carrier:royal-mail) / Parcelforce nel Regno Unito, [La Poste](carrier:la-poste) in Francia, [Poste Italiane](carrier:poste-italiane) in Italia o [Australia Post](carrier:australia-post) in Australia.

### Perché il mio pacco EMS ha smesso di aggiornarsi dopo `Departed from export office`?

Dopo `Departed from export office`, il pacco è in transito aereo internazionale oppure in coda presso l’ufficio di scambio postale internazionale del paese di destinazione. Il tracciamento riprende non appena le poste di destinazione scansionano `Arrived at destination import office`.

### Chi contatto se un pacco EMS è fermo o smarrito?

L’UPU e l’Unità EMS non forniscono tracciamento telefonico e non intervengono nelle controversie tra poste e clienti. Usa [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) per trovare il servizio clienti o il call center EMS locale, che può aiutarti a localizzare la spedizione.

:::sources
- [Cooperativa EMS: sito ufficiale](https://www.ems.post/en) – panoramica della Cooperativa Express Mail Service dell’UPU e rete postale globale
- [Cooperativa EMS: guida al tracciamento](https://www.ems.post/en/global-network/tracking) – formato S10 a 13 caratteri, non intervento UPU/Unità EMS e assenza di tracciamento telefonico
- [Cooperativa EMS: contatti globali degli operatori](https://www.ems.post/en/about-us/contacts-0) – elenco A–Z dei servizi clienti EMS nazionali per area (URL di aiuto collegato da Peek)
- [Cooperativa EMS: operatori A–Z](https://www.ems.post/en/global-network/ems-operators) – rete degli operatori postali nazionali designati
- [Portale pubblico di tracciamento EMS](https://items.ems.post/) – ricerca pubblica mondiale delle spedizioni EMS
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – validazione della cifra di controllo S10 con prefisso `E`, regola link `items.ems.post`, mappatura dei 18 stati della Cooperativa EMS e datazione di ogni scansione nel fuso del proprio ufficio
:::
