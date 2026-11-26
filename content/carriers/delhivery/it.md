---
title: Tracciamento Delhivery: numeri AWB, stati e assistenza
description: Traccia un pacco Delhivery in India o verso l'estero: formato del codice AWB, significato di RTO e altri stati, mancate consegne, dogana e supporto ufficiale.
slug: tracciamento-delhivery
published: 2026-11-21
updated: 2026-11-21
---

Fondata nel 2011, Delhivery (*Delhivery Limited*) si presenta come il maggiore operatore logistico completamente integrato dell'India: consegna ordini online per marketplace e negozi in oltre `18.850` codici PIN e spedisce dall'India verso più di `220` Paesi e territori. Puoi seguire un collo sulla [pagina di tracciamento Delhivery](https://www.delhivery.com/tracking) con il numero AWB, l'ID ordine (`Order Id`), un `LRN` o il tuo numero di cellulare, oppure incollando l'AWB nel campo qui sopra.

## Numero di tracciamento Delhivery (AWB): com'è fatto

Delhivery chiama il suo codice di tracciamento **AWB** (*airway bill number*, lettera di vettura). Ecco i formati AWB riconosciuti da Peek, con cifre inventate:

| Numero (inventato) | Cos'è |
| --- | --- |
| `1482910573642` | **13 cifre**: un AWB Delhivery. Tredici cifre sono usate anche da altri corrieri come [Econt](carrier:econt), DPD France, FAN Courier o Korea Post. |
| `14829105736492` | **14 cifre**: anche questo è un AWB Delhivery, formato condiviso con [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) o Xpressbees. |

La sola lunghezza non basta quindi a identificare Delhivery: controlla quale corriere indica l'SMS o l'e-mail del negozio. Su `delhivery.com/tracking` puoi cercare anche per `Order Id`, `LRN` (spedizioni cargo B2B) o accedere col numero di cellulare e codice `OTP` per vedere tutte le spedizioni a te dirette; su Peek serve invece l'**AWB** a 13 o 14 cifre ([come trovare il numero di tracciamento](guide:find-tracking-number)).

## Significato degli stati di tracciamento Delhivery

Il tracciamento Delhivery affianca una barra di avanzamento alle singole scansioni con la relativa città. Queste sono le diciture e i codici letti da Peek:

| Stato Delhivery | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `WAITING_PICKUP` | Spedizione prenotata ma non ancora ritirata (mostrata come `Waiting pickup` finché non ci sono scansioni). | «Etichetta creata» |
| `Picked Up` | Delhivery ha ritirato il pacco dal mittente (usato anche come tappa cargo senza singole scansioni). | «Preso in carico» |
| `PROD_REPLACED` | In un ritiro inverso per sostituzione, il corriere ha preso in consegna il prodotto sostituito. | «Preso in carico» |
| `In Transit`, `IN_TRANSIT`, `On the Way` | In viaggio nella rete Delhivery (`IN_TRANSIT` come codice, `On the Way` come tappa cargo). | «In transito» |
| `Dispatched`, `Out for Delivery` | Uscito per la consegna (`Dispatched` nella scansione pacco, `Out for Delivery` sulla barra di avanzamento). | «In consegna» |
| `Delivered` | Consegnato (nei flussi di reso indica la riconsegna al venditore). | «Consegnato» (o «Restituito al mittente») |
| `Undelivered` | Non consegnato e in rientro al venditore; nelle spedizioni cargo Delhivery rinomina così la tappa `On the Way` mantenendone la data originale, che quindi non indica un tentativo di consegna. | «Problema segnalato» |
| `REACHED_SEL_CITY` | Un collo in reso ha raggiunto la città del venditore. | «In transito» |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin*: il pacco non consegnato è tornato al venditore. | «Restituito al mittente» |
| `DTO` | Un reso inviato dal cliente ha raggiunto il venditore. | «Restituito al mittente» |
| `LOST` | Delhivery ha contrassegnato la spedizione come smarrita. | «Problema segnalato» |
| `CANCELLED`, `Canceled` | Spedizione o ritiro di reso annullato. | «Problema segnalato» |

Per approfondire il significato delle varie fasi, vedi [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Consegna Delhivery in India: chiamate, assenze e indirizzo

**Rete.** Delhivery gestisce `50` centri di smistamento automatizzati, `123` gateway e oltre `4.500` centri di consegna diretta in ogni Stato indiano, coprendo il `99,5 %` dei codici PIN (inclusi l'India nord-orientale e Jammu e Kashmir) 24 ore su 24, 365 giorni l'anno.

**Chiamate e WhatsApp.** I termini prevedono che Delhivery o i suoi partner possano contattarti via telefono, SMS o WhatsApp (le chiamate possono essere registrate) per concordare il recapito. Per gli e-commerce offre la gestione `Smart NDR` con chiamate IA e conferme WhatsApp per nuovi tentativi, ma **non chiede mai** codici `OTP` o credenziali per confermare l'indirizzo.

**Mancata consegna e regole Direct.** Per le spedizioni tra privati *Delhivery Direct* (fino a `50 kg` di peso tassabile, `10 kg` e `50 cm` per lato in modalità express; niente caselle postali), la consegna viene annullata dopo **3 tentativi falliti** senza rimborso del trasporto (rientro possibile a spese del mittente). Con l'accordo di Delhivery puoi autorizzare consegna a un vicino, cassetta/armadietto o ritiro in sede approvata, ma un cambio di codice PIN può essere rifiutato ([mancata consegna](guide:missed-delivery)). Per gli acquisti online valgono gli accordi del venditore.

## Spedizioni internazionali dall'India e dogana

**Tempi e partner.** Con **DLV Saver** Delhivery stima `5–7` giorni verso il Regno Unito e `8–12` giorni verso gli USA (servizio attivo per USA, Regno Unito, Canada e Australia); **DLV Premium** copre oltre `220` Paesi e territori in `3–6` giorni (*Express*) o `6–10` giorni (*Deferred Express*) a partire da `50 g`. Tra i partner strategici esteri Delhivery cita [FedEx](carrier:fedex) e Aramex: se il negozio ti fornisce un secondo codice per la tratta finale, aggiungilo anche su Peek.

**Dogana in Italia e in Svizzera.** Nell'**Unione europea**, gli acquisti online spediti da fuori UE fino a `150 €` pagano dal 1° luglio 2026 un dazio doganale temporaneo di `3 €` per articolo (beni con stessa voce tariffaria, descrizione e, ove richiesto, origine; oltre `150 €` dazio ordinario) e, dal 1° novembre 2026, una commissione di gestione dell'Unione di `2 €` per articolo qualunque sia il valore, oltre all'IVA d'importazione in **Italia** salvo che l'IVA sia già stata riscossa al pagamento tramite il regime IOSS (**Livigno** è fuori sia dal territorio doganale sia dal territorio IVA dell'UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** rientrano nel territorio doganale dell'UE ma sono fuori dal territorio IVA dell'UE). In **Svizzera** (extra UE), l'UDSC riscuote l'IVA sull'importazione all'`8,1 %` (`2,6 %` aliquota ridotta), mentre tributi doganali e imposta fino a `5 franchi` non vengono riscossi ([pacchi fermi in dogana](guide:customs)).

## Tracciamento fermo, pacco smarrito, danneggiato o restituito

Per rimborsi o nuove spedizioni di un ordine online, il referente resta il negozio che ha incaricato Delhivery.

- **Nessun aggiornamento:** su «Etichetta creata» (`WAITING_PICKUP`) il venditore non ha ancora consegnato il pacco al corriere. Se si blocca durante il viaggio, accedi col tuo numero su `delhivery.com/support` o nell'app per inviare una segnalazione ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).
- **Smarrito, danneggiato o recapitato altrove:** su *Delhivery Direct* i reclami per perdita, danno o errata consegna vanno aperti tramite ticket nell'app entro **3 giorni** (nello stesso giorno solare se arriva danneggiata una scatola acquistata da Delhivery), allegando **3 foto** dell'imballo esterno e del contenuto in caso di danni (consigliato un video dell'apertura). I reclami vengono chiusi entro **30–45 giorni lavorativi** e, senza la copertura a pagamento *Delhivery Protect* (obbligatoria tra `10.000 ₹` e il limite Direct di `49.999 ₹`; fino a `30.000 ₹` per AWB nell'e-commerce), il risarcimento massimo è di **`2.000 ₹`** con tetti per categoria (`500 ₹` libri/documenti/altro, `700 ₹` abbigliamento, `800 ₹` casa, `1.000 ₹` elettronica o articoli multipli, `2.000 ₹` sport, `400 ₹` consumabili), senza indennizzi per ritardo ([pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received)).
- **Reso (`RTO`, `DELIVERED_SELLER`):** il pacco è tornato al venditore; chiedi il rimborso o una nuova spedizione ([restituito al mittente](guide:returned-to-sender)).

## Assistenza clienti Delhivery e avvisi antifrode

- **Supporto solo tramite sito o app:** Delhivery precisa che il servizio clienti è raggiungibile **esclusivamente** dalla sua [pagina di supporto](https://www.delhivery.com/support) (accesso con numero di telefono e `OTP`, poi `Raise a Query`; voci `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) o dall'app *Delhivery Direct*.
- **Nessun numero telefonico pubblico:** eventuali numeri di assistenza trovati altrove sul web possono essere fraudolenti. Le e-mail ufficiali provengono solo da `@delhivery.com` e sui social Delhivery risponde solo dai profili ufficiali Facebook, X e Instagram.
- **Attenzione agli SMS falsi:** diffida degli SMS su falsi tentativi falliti che rimandano a domini contraffatti (`delhiveryu.top`, `delhiveryma.top`); Delhivery non chiede mai `PIN` o `OTP` per pagamenti, né `OTP` o credenziali per confermare l'indirizzo. In India, in caso di frode finanziaria chiama il `1930` o segnala su `cybercrime.gov.in` (e segnala chiamate/SMS spam entro 3 giorni su `sancharsaathi.gov.in`); leggi anche [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Delhivery con Peek

Incolla l'AWB a 13 o 14 cifre in [Peek](/): poiché molti corrieri condividono queste lunghezze, Peek mostra «Verifica dei servizi di tracciamento…» e interroga automaticamente Delhivery quando ti colleghi dall'India con un numero che inizia per `1`–`9` e termina con `0`–`6` («Delhivery ha questo pacco.»). Da qualsiasi altro Paese, Peek interroga Delhivery per ultimo, quando gli altri corrieri e i servizi di tracciamento non hanno trovato il numero. Per codici che iniziano per `0` o finiscono con `7`, `8` o `9`, tocca «Cambia» e seleziona Delhivery. Per lunghezze diverse, la scelta manuale avvisa che «non emette numeri come questo».

Archiviato sotto Delhivery, prima della prima scansione compare «In attesa di Delhivery». Peek legge il tracciamento pubblico senza account: mostra le scansioni effettuate con la città (saltando le tappe future della barra e i dati del destinatario), ricava l'anno delle tappe cargo dal giorno della settimana, legge gli orari sul fuso indiano (`Asia/Kolkata`, `UTC+5:30`) e li converte nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»), senza data stimata. Un collo riconsegnato al venditore viene mostrato come «Restituito al mittente» e non «Consegnato».

A pacco aperto o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle `06:00` ora indiana nel giorno in cui Delhivery scansiona il pacco «In consegna» finché il tracker Delhivery risponde; altrimenti, e dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza novità, ogni ora («Ogni scansione», «Passaggi importanti» o «Solo la consegna»). Peek non può contattare Delhivery o il venditore per te.

## Domande frequenti sul tracciamento Delhivery

### Come traccio una spedizione Delhivery?

Incolla l'AWB a 13 o 14 cifre nel riquadro in alto su Peek, oppure cerca su `delhivery.com/tracking` per AWB, `Order Id`, `LRN` o numero di cellulare con `OTP`.

### Cos'è il numero AWB di Delhivery?

È l'*airway bill number*, il codice di tracciamento Delhivery a 13 o 14 cifre riportato nell'SMS o nell'e-mail di spedizione del negozio.

### Cosa significa `RTO` nel tracciamento Delhivery?

*Return to Origin*: la spedizione è tornata indietro ed è stata riconsegnata al venditore. Contatta il negozio per il rimborso o un nuovo invio.

### Qual è il numero del servizio clienti Delhivery?

Delhivery non pubblica un numero telefonico di assistenza: il supporto risponde solo effettuando l'accesso col proprio telefono su `delhivery.com/support` o nell'app Delhivery, e i numeri trovati altrove online possono essere truffaldini.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – ricerca tramite cellulare, AWB, Order Id o LRN e avvisi antifrode
- [Delhivery: Support](https://www.delhivery.com/support) – accesso con OTP, invio segnalazioni e sezioni incluso Grievance Officer
- [Delhivery: About Us](https://www.delhivery.com/about-us) – fondata nel 2011, oltre 18.850 codici PIN, 50 centri di smistamento e oltre 4.500 centri di consegna 24/7
- [Delhivery: Home](https://www.delhivery.com/) – oltre 220 Paesi e territori, partner strategici FedEx e Aramex
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – copertura del 99,5 % dei codici PIN, Smart NDR via WhatsApp/IA e copertura e-commerce
- [Delhivery: International](https://www.delhivery.com/services/international) – tempi DLV Saver e DLV Premium da 50 g e dazi e imposte
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – spedizioni tra privati Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – chiamate, SMS e comunicazioni WhatsApp per la consegna
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – limiti di peso, 3 tentativi, reclami entro 3 giorni con 3 foto, rimborsi in 30–45 giorni lavorativi e massimali fino a 2.000 ₹ / Delhivery Protect
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – canali ufficiali @delhivery.com, SMS truffa, nessuna richiesta OTP di pagamento, 1930 e cybercrime.gov.in
- [Commissione europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € fino a 150 € da luglio 2026 e commissione di 2 € da novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definizione di articolo, commissione di 2 € qualunque sia il valore e regime IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA UE; Campione d'Italia e acque italiane del Lago di Lugano fuori dal territorio IVA UE
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e franchigia fino a 5 franchi
- [Universal Parcel Scraper: Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – formati AWB a 13 e 14 cifre, 17 stati e codici, tappe cargo, resi e fuso Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri corrieri a 13 e 14 cifre
:::
