---
title: Tracciamento J&T Express Australia: codici JTAU e stati
description: Traccia un pacco J&T Express Australia: formato JTAU a 12 cifre, stati in inglese, foto di consegna, assistenza 13 18 18 e tracciamento Peek.
slug: tracciamento-jt-express-australia
published: 2026-11-21
updated: 2026-11-21
---

J&T Express Australia (*J&T Express Australia Pty Ltd*, con sede legale a Villawood nel Nuovo Galles del Sud) è il ramo australiano del gruppo J&T Express, attivo nel Sud-Est asiatico e nella Cina continentale. Consegna pacchi in Australia per piattaforme e-commerce, marchi e negozi, compresi gli ordini dall'estero dopo lo sdoganamento. I suoi codici iniziano per `JTAU`: incolla il tuo qui sopra oppure cercalo sul [tracker ufficiale di J&T Australia](https://www.jtexpress.au/tracking). Il codice ti viene inviato dal negozio nell'email di spedizione o nella pagina dell'ordine.

## Numero di tracciamento J&T Express Australia: com'è fatto

Una lettera di vettura (*waybill*) australiana di J&T è composta da `JTAU` seguito da 12 cifre (16 caratteri in tutto), l'unico formato conosciuto da Peek (esempi inventati):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `JTAU482910573649` | `JTAU` e 12 cifre: un pacco J&T Express Australia, rilevato all'istante da Peek. |
| `JTAU48291057364` | Manca una cifra. Se nessun corriere risponde, `peektracker.com` avvisa: «Inizia come un numero di tracciamento di J&T Express Australia, ma manca un carattere: forse c’è un errore di battitura. Confrontalo con il messaggio di spedizione.» |
| `JTDE482910573649` | `JTDE` e 12 cifre: [J&T Express Germania](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` e 12 cifre: [J&T Express Middle East](carrier:j-and-t-middle-east) (Arabia Saudita, EAU e Kuwait). `JTUK` (`JTUK482910573649`) appartiene a J&T Express UK. |
| `JT4829105736491` | `JT` e 13 cifre: reti J&T nel Sud-Est asiatico e [J&T Express Cina](carrier:j-and-t-cn). |

Spazi, trattini e minuscole non cambiano nulla in Peek (`jtau 4829 1057 3649` viene letto allo stesso modo); il catalogo riconosce il corriere anche come *J&T Express (AU)*, *J&T Express AU*, *J&T Australia* e *JT Express Australia*.

**Dove trovarlo.** J&T riceve nome, indirizzo e recapiti dal mittente o dal marketplace: controlla l'email di spedizione o la pagina dell'ordine ([come trovare il numero di tracciamento](guide:find-tracking-number)). Su `jtexpress.au/tracking` puoi cercare fino a 10 codici separati da spazi o virgole; per un codice sconosciuto compare `No tracking records yet`.

## Stati del tracciamento J&T Express Australia spiegati

Il tracker di J&T mostra per ogni scansione un orario e una frase senza città né deposito. Peek richiede le frasi in inglese (alcune prime fasi compaiono sul tracker anche in cinese: `包裹已从清关行取件。`, `快件已揽收成功，运输准备中。`, `快件到达转运中心，做发往下一站的运输准备`, `快件从转运中心发往下一站`):

| Stato J&T | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Spedizione dall'estero: la dogana è conclusa e J&T ha ritirato il pacco dallo spedizioniere doganale (prima scansione J&T di un import, non un blocco doganale). | «Preso in carico» |
| `The parcel has been successfully picked up and is being prepared for transportation.` | J&T ha preso in carico il pacco e lo prepara al trasporto. | «Preso in carico» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Arrivato in un centro di distribuzione J&T in vista della tappa successiva. | «In transito» |
| `The parcel is dispatched from the transit center to the next destination` | Partito da un centro di transito verso la tappa seguente. | «In transito» |
| `Your parcel is out for delivery.` | In uscita per la consegna al tuo indirizzo. | «In consegna» |
| `The building was closed when we arrived. We'll try again.` | Edificio chiuso all'arrivo del corriere; J&T riproverà. | «Tentativo di consegna» |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | Consegna fallita per indirizzo errato o incompleto; J&T chiede di aggiornarlo. | «Tentativo di consegna» |
| `Your parcel has been delivered.` | Consegnato. | «Consegnato» |

Per le spedizioni dall'estero vedi anche [pacchi fermi in dogana](guide:customs). Eventuali altre frasi vengono classificate in base alle parole (come «In transito», a meno che il pacco non sia già in consegna o oltre).

Sul tracker di J&T alcune frasi terminano con il numero dell'assistenza clienti: Peek rimuove tale coda, il testo tra parentesi quadre (`[…]`, `【…】`), i numeri di telefono e i nomi di autista o mittente. Quando la scansione di consegna riporta chi ha firmato o ritirato il collo, Peek mantiene quel nome per chi ha aggiunto il pacco e lo nasconde sui link condivisi. Non essendoci località nelle scansioni, né J&T né Peek possono dirti in quale Stato o filiale si trovi il pacco.

## Consegna J&T Express Australia e prova di consegna

J&T seleziona aziende partner in tutta l'Australia per ritiri e ultimo miglio, senza passaggio a un altro corriere con secondo codice: il numero `JTAU` vale fino alla tua porta.

**Pacchi da linee internazionali.** Alcuni spedizionieri internazionali affidano i lotti a J&T al porto d'arrivo in Australia: [Yanwen](carrier:yanwen), ad esempio, riporta poi nel proprio tracciamento le frasi di J&T (dove Peek mostra la presa in carico J&T come «In transito», dato che Yanwen seguiva già il collo).

**Edificio chiuso o problema di indirizzo.** Dopo `The building was closed when we arrived. We'll try again.` J&T effettua un nuovo passaggio; dopo `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` verifica subito interno e CAP con il negozio ([cambiare indirizzo di consegna](guide:change-delivery-address), [mancata consegna](guide:missed-delivery)). Il sito di J&T non specifica il numero di tentativi né i tempi di giacenza, né risultano punti di ritiro o locker tra le righe note.

**Prova di consegna.** L'informativa privacy di J&T spiega cosa registra l'autista nell'app:
- foto del pacco nel punto in cui è stato lasciato, data e ora, geolocalizzazione del dispositivo, firma ove richiesta e note di consegna;
- indicazione agli autisti di fotografare il collo evitando per quanto possibile persone, targhe e interni domestici;
- invio al destinatario via SMS o notifica elettronica di un link alla prova di consegna (apribile da chiunque abbia il link, privo del nome e recapito dell'autista), fornito anche a mittente o marketplace;
- se non desideri foto alla consegna, puoi inviare istruzioni a `dataprotection@jtexpress.au`.

## Pacco J&T fermo, non trovato o segnato consegnato ma assente

Avendo il negozio scelto J&T, per gli acquisti online contatta anzitutto il venditore.

**Nessuna nuova scansione da quattro giorni.** Un pacco «Preso in carico» o «In transito» fermo da quattro giorni riceve in Peek l'avviso «Nessun aggiornamento da quattro giorni» e il link «Chiedi aiuto a J&T Express Australia» ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).

**Nessun tracciamento per ora.** Per un codice che J&T non conosce ancora, Peek mostra «In attesa di J&T Express Australia». Se dopo due giorni nessun corriere risponde, compare «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» con controlli giornalieri e, passata una settimana, settimanali.

**Consegnato ma non c'è, smarrito o danneggiato.** Apri il link della foto nell'SMS di J&T, controlla ingresso, familiari e vicini, poi avvisa il negozio e J&T (che usa le registrazioni di consegna per verificare smarrimenti, danni o recapiti errati, pur non pubblicando una procedura di rimborso diretta per i destinatari): chiedi rimborso o sostituzione al venditore o marketplace ([consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel)).

## Assistenza clienti e contatti J&T Express Australia

La [pagina contatti di J&T Australia](https://www.jtexpress.au/contact) riporta:

- **Telefono:** `13 18 18`, orario `08:00-20:00` (senza indicazione dei giorni).
- **Email assistenza:** `service@jtexpress.au` (`business@jtexpress.au` e `partner@jtexpress.au` sono dedicati ad aziende e partner di consegna, mentre `dataprotection@jtexpress.au` è il contatto privacy presso la sede `10/2 Christina Road, Villawood NSW 2163`).

L'informativa privacy precisa che i fornitori di call center e assistenza clienti si trovano in Malesia e nelle Filippine.

Un SMS di J&T con il link alla foto di consegna può essere autentico; se però un messaggio chiede pagamenti o dati personali, controlla il dominio `www.jtexpress.au` (`jtexpress.au`) e leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare i pacchi J&T Express Australia con Peek

[Peek](/) individua subito J&T Express Australia da un codice `JTAU` («Corriere rilevato») o da un link `www.jtexpress.au/tracking` con `?q=` e un codice `JTAU` (con un altro numero dentro `?q=`, Peek lo legge come se fosse incollato da solo; scegliendo manualmente J&T Australia per un altro formato, avvisa che «non emette numeri come questo»). Per un codice `JT` a 13 cifre Peek interroga J&T Express e i servizi di tracciamento.

Peek interroga il tracker ufficiale in `UTC` (`GMT+0000`) e mostra l'istante esatto di ogni scansione nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»), fino alle 100 scansioni più recenti, senza mappa, data stimata, peso, recapiti d'ufficio né foto di consegna (visibile dal link nell'SMS). Se il tracker J&T non è raggiungibile o non conosce ancora il numero, Peek interroga servizi di tracciamento di supporto.

Peek controlla fino a ogni 10 minuti a pacco aperto o nel giorno di consegna con notifiche attive, e fino a ogni 2 minuti dalle `06:00` ora locale nel giorno in cui J&T scansiona il pacco «In consegna» finché il tracker J&T risponde (tramite servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti nel giorno di consegna a pacco aperto o con avvisi attivi); altrimenti, e dopo 12 ore su «Preso in carico» o due giorni senza scansioni, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può contattare J&T o il negozio né modificare l'indirizzo.

## Domande frequenti sul tracciamento J&T Express Australia

### J&T Express Australia è affidabile?

Sì. L'*Australian Business Register* registra *J&T EXPRESS AUSTRALIA PTY LTD* come società privata australiana con ABN attivo dal 4 giugno 2026 (`18 698 743 609`, ACN `698 743 609`, sede principale `NSW 2163`, sede legale `10/2 Christina Road, Villawood NSW 2163`), parte del gruppo J&T Express (`01519.HK`) fondato in Indonesia nel 2015.

### Cosa significa `picked up from customs clearance` nel tracciamento J&T?

Lo sdoganamento è completato: J&T ha ritirato il pacco importato dallo spedizioniere doganale per consegnarlo in Australia. Peek mostra «Preso in carico», non un fermo in dogana.

### Perché il mio codice J&T non viene trovato?

Verifica che inizi per `JTAU` seguito da 12 cifre (non `JTUK` o `JTDE`). Se è esatto, J&T potrebbe non aver ancora ricevuto il collo (`No tracking records yet`, «In attesa di J&T Express Australia» in Peek); se dopo due giorni resta sconosciuto, chiedi al negozio.

### Come contatto J&T Express Australia?

Chiama il `13 18 18` (`08:00-20:00`) oppure scrivi a `service@jtexpress.au`, avvisando anche il negozio da cui hai acquistato.

### J&T Express Australia scatta una foto alla consegna?

Sì: secondo l'informativa privacy, gli autisti fotografano il pacco nel punto in cui lo lasciano e ricevi un link alla prova di consegna via SMS o notifica elettronica.

:::sources
- [J&T Express Australia: Track & Trace](https://www.jtexpress.au/tracking) – tracker ufficiale e messaggio `No tracking records yet`
- [J&T Express Australia: Contact Us](https://www.jtexpress.au/contact) – telefono 13 18 18 (08:00-20:00), service@jtexpress.au, business@jtexpress.au, partner@jtexpress.au e ABN 18 698 743 609
- [J&T Express Australia: Delivery Partners](https://www.jtexpress.au/career) – partner di ritiro e consegna di ultimo miglio in Australia
- [J&T Express Australia: About Us](https://www.jtexpress.au/about) – gruppo J&T Express (01519.HK), fondazione in Indonesia nel 2015, Sud-Est asiatico e Cina continentale
- [J&T Express Australia: Privacy Notice](https://www.jtexpress.au/privacy-policy) – sede legale a 10/2 Christina Road, Villawood NSW 2163, foto di consegna e link SMS, dataprotection@jtexpress.au, verifiche e supporto in Malesia e Filippine
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – ABN 18 698 743 609 attivo dal 4 giugno 2026, società privata australiana, NSW 2163
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – formato JTAU, frasi in inglese e cinese, orari UTC, pulizia del testo, firmatario e pagina di aiuto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati delle altre reti J&T, consegna in lotti da Yanwen e servizi di tracciamento di riserva
:::
