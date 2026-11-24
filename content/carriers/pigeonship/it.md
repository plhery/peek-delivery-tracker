---
title: Tracciamento PigeonShip: numeri, stati e consegna
description: Traccia consegne in giornata e ultimo miglio PigeonShip in USA e Canada: codici a 18 caratteri, stato in tempo reale, orari previsti e supporto.
slug: tracciamento-pigeonship
published: 2026-11-24
updated: 2026-11-24
---

PigeonShip è un corriere dell’ultimo miglio attivo negli Stati Uniti e in Canada, con uffici a Provo (Utah) e Oakville (Ontario). Rivenditori, e-commerce e attività locali utilizzano PigeonShip per consegne in giornata, il giorno successivo e regionali tramite servizi quali **Pigeon Flex** (consegna nazionale in 2–5 giorni), **Pigeon Express** (consegna nazionale in 2 giorni), **Pigeon Connect** (in giornata su richiesta) e **Pigeon Flash** (in giornata programmata).

PigeonShip si avvale di autisti indipendenti e gestisce programmi di consegna dedicati per le aziende. I destinatari ricevono un link di tracciamento su `admin.pigeonship.com` che mostra lo stato dell’ordine e l’orario limite, poi una pagina di completamento una volta avvenuta la consegna.

## Formato del codice di tracciamento PigeonShip

Peek accetta codici PigeonShip da 8 a 30 lettere e cifre, rimuove gli spazi e converte le lettere in maiuscolo. Peek riconosce un formato a 18 caratteri che alterna cifre e lettere maiuscole:

- **18 caratteri alfanumerici** con lo schema `cifra-lettera-cifra-lettera-3 cifre-lettera-3 cifre-lettera-2 cifre-lettera-cifra-2 lettere` (ad esempio `7P4K829H631N58R2TM`).

I link di tracciamento PigeonShip hanno la forma `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Quando incolli quel link in Peek, Peek estrae il codice del pacco e lo invia direttamente a PigeonShip. Con un codice isolato a 18 caratteri di questo tipo, Peek interroga solo PigeonShip e mostra «PigeonShip ha questo pacco.» non appena risponde. Per un confronto con altri formati statunitensi e canadesi, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats).

## Come funziona il tracciamento in tempo reale PigeonShip

La pagina di tracciamento destinata ai clienti PigeonShip è una vista riepilogativa anziché una cronologia di scansioni. Finché l’ordine è attivo, mostra «In transit» e l’autista su una mappa con un orario limite (`dueDate`) se impostato; dietro la pagina l’ordine ha un codice e un’etichetta di stato come `16` e `Assigned`. La pagina mostra «Delivered» soltanto per il codice `64`. A consegna completata, PigeonShip sostituisce la pagina allo stesso URL con una schermata di conferma `Your delivery has been completed successfully.` e un link alle foto della consegna.

| Stato / pagina PigeonShip | Cosa significa |
| --- | --- |
| Stato `16` — `Assigned` | L’ordine è assegnato al giro di un autista; la pagina mostra «In transit». |
| Scadenza / `dueDate` | L’orario obiettivo di consegna mostrato sugli ordini attivi prima del deposito. |
| Stato `64` / `Delivered` o `Your delivery has been completed successfully.` | L’autista ha completato la consegna; aprendo il link di PigeonShip puoi vedere la foto di conferma. |
| `Page not found` (404) | Il codice non è attivo nel sistema live di PigeonShip o contiene un errore di battitura. |

Poiché PigeonShip non pubblica un registro cronologico di scansioni sulla pagina destinatario, non vedrai passaggi intermedi in magazzini o centri di smistamento come con [UPS](carrier:ups), [USPS](carrier:usps), [Canada Post](carrier:canada-post) o [Intelcom](carrier:intelcom). La pagina aggiorna invece lo stato dal vivo man mano che la consegna procede e passa alla schermata finale al deposito.

## Consegne in giornata e cosa controllare in caso di ritardo

PigeonShip offre consegne in giornata e il giorno successivo in tutti gli Stati Uniti e in Canada. Le recensioni sul sito menzionano un SMS prima dell’arrivo dell’autista e una foto dopo il deposito.

Se l’ordine rimane su `Assigned` oltre l’orario previsto o il link mostra `Page not found`:

- **Controlla eventuali errori:** verifica il codice completo o l’intero link `admin.pigeonship.com` dall’email o SMS del venditore; il sito PigeonShip risponde `Page not found` per numeri privi di pagina attiva.
- **Contatta PigeonShip:** usa il [modulo di contatto](https://www.pigeonship.com/contact-us) se l’ordine resta su «In transit» oltre l’orario limite.
- **Verifica le istruzioni di consegna:** le recensioni sul sito PigeonShip menzionano autisti che seguono le note sull’etichetta e scattano una foto al deposito; apri il link originale su `admin.pigeonship.com` per vedere le foto dopo il completamento. Per altri controlli, leggi [quando il tracciamento smette di aggiornarsi](guide:tracking-not-updating) e la [guida alla consegna mancata](guide:missed-delivery).

## Tracciare PigeonShip con Peek

Incolla il codice PigeonShip oppure il link completo `admin.pigeonship.com/shipping/tracking/package/...` in Peek. Se Peek non preseleziona PigeonShip per un formato atipico, scegli **Cambia** quando aggiungi il pacco o **Cambia corriere** nella schermata del pacco.

Peek legge direttamente la pagina di tracciamento di PigeonShip resa lato server tramite l’[adattatore open source PigeonShip di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Legge il codice e l’etichetta di stato dell’ordine, se è consegnato e l’orario limite (`dueDate`) quando presente, che Peek mostra come consegna prevista. Peek omette il nome del destinatario, le coordinate dell’autista sulla mappa, la barra di avanzamento e le foto della consegna — apri il link di PigeonShip per vedere la foto del deposito.

Poiché PigeonShip mostra solo lo stato attuale, Peek aggiunge una riga datata quando rileva ciascun cambio di fase: un ordine attivo `Assigned` compare come **In transito** con l’orario previsto, e una consegna conclusa come **Consegnato**.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché la pagina di tracciamento PigeonShip risponde. Altrimenti Peek controlla ogni ora. Poiché la pagina di PigeonShip non mostra una fase di uscita in consegna, Peek non passa mai ai controlli ogni 2 minuti del giorno di consegna. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando la pagina PigeonShip non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a PigeonShip.», con un link «Chiedi aiuto a PigeonShip» alla pagina contatti di PigeonShip. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a PigeonShip.»

Nelle impostazioni notifiche di Peek, scegli **Ogni scansione**, **Passaggi importanti** o **Solo la consegna** per decidere quali aggiornamenti ti avvisano.

## Contattare PigeonShip

- **Modulo di contatto ufficiale:** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (seleziona United States, Canada o Other e descrivi la tua richiesta di consegna)
- **Sito ufficiale:** [pigeonship.com](https://www.pigeonship.com/)
- **Ufficio USA:** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Ufficio Canada:** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Domande frequenti

### Perché il tracciamento PigeonShip non mostra un elenco di scansioni passate?

La pagina live per destinatari di PigeonShip è solo riepilogativa: mostra lo stato corrente dell’ordine («In transit» o «Delivered») e l’orario limite anziché una cronologia di scansioni tra magazzini.

### Dove posso vedere la foto di consegna di PigeonShip?

Peek non archivia né mostra foto di consegna di PigeonShip. Quando il pacco risulta consegnato, apri il link originale `admin.pigeonship.com/shipping/tracking/package/...` ricevuto via email o SMS per vedere la pagina finale e le foto di PigeonShip.

### Cosa significa lo stato `Assigned` su PigeonShip?

`Assigned` (codice stato `16`) è lo stato dell’ordine dietro la pagina PigeonShip mentre è sul giro di un autista; la pagina mostra «In transit». Peek lo visualizza come **In transito**.

### PigeonShip consegna sia negli Stati Uniti sia in Canada?

Sì. PigeonShip gestisce servizi di consegna in giornata, il giorno successivo e nazionali dell’ultimo miglio negli Stati Uniti e in Canada, con uffici a Provo (Utah) e Oakville (Ontario).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [adattatore PigeonShip di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
