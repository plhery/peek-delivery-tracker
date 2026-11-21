---
title: Tracciamento Morning Global: codici BR…MG, stati e contatti
description: Traccia un pacco Morning Global da Hong Kong o dalla Cina continentale: formato BR…MG, stati in inglese, consegna locale, dogana UE e contatti.
slug: tracciamento-morning-global
published: 2026-11-21
updated: 2026-11-21
---

Morning Global (MG), fondata nel 1987, è la divisione internazionale di Morning Express Group, un gruppo di corrieri di Hong Kong. Spedisce pacchi all'estero per negozi online e marketplace, ha uffici a Hong Kong e in tutta la Cina continentale e di norma affida il recapito finale a un partner nel tuo Paese. Controlla il codice fornito dal negozio sulla [pagina Trace & Track di Morning Global](https://www.morninglobal.com/trace-track/), in inglese, oppure incollalo nel riquadro qui sopra.

## Numero di tracciamento Morning Global: com'è fatto

Morning Global non pubblica un formato dei propri codici. Il catalogo di Peek riconosce uno schema per questo corriere (cifre inventate):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `BR482910573MG` | `BR`, nove cifre e `MG`: un numero Morning Global. Poiché questo schema coincide anche con quello postale internazionale, Peek verifica prima con Morning Global (e se la nona cifra supera anche il controllo dei codici postali, come `BR482910576MG`, interroga sia Morning Global sia [Chronopost](carrier:chronopost)). |
| `BR48291057MG` | Solo otto cifre: non ha la lunghezza di Morning Global e Peek non lo associa al corriere. Controlla se è saltata una cifra. |

Minuscole, spazi e trattini non contano in Peek. Il tracker di Morning Global accetta fino a 20 codici contemporaneamente, separati da virgole.

**Dove trovarlo.** Nell'email di spedizione del negozio o nella pagina dell'ordine ([come trovare il numero di tracciamento](guide:find-tracking-number) e [tracciare pacchi dalla Cina](guide:tracking-from-china)).

**Altri formati.** Una volta selezionato manualmente Morning Global, Peek interroga il suo tracker per qualsiasi codice di 8–40 caratteri che mescoli lettere e cifre (per un altro codice segnala che «non emette numeri come questo»).

**Due servizi.** Morning Global offre *MG International Express (MGIE)*, servizio espresso porta a porta gestito direttamente, e il più economico *MG International Postal (MGIP)* tramite hub postali internazionali. Insieme coprono oltre 220 destinazioni per pacchi fino a 30 kg.

## Stati del tracciamento Morning Global spiegati

Il tracker di Morning Global elenca i passaggi sotto `Shipment Journey`, ciascuno con stato, spesso una descrizione, data, ora e luogo — spesso il codice Paese seguito dal fuso UTC dell'orologio locale, come `CA (UTC-4)`. Nell'ordine consueto:

| Morning Global scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order Created` | Il negozio ha registrato la spedizione; Morning Global non ha ancora il pacco. | «Etichetta creata» |
| `Collection` | Morning Global ha ritirato il collo presso il venditore. | «Preso in carico» |
| `Arrive Hub` | Arrivato in un hub di Morning Global. | «In transito» |
| `Depart Origin` | Ha lasciato l'hub di origine da cui parte il viaggio. | «In transito» |
| `Depart Hub` | Ha lasciato un hub (dicitura proveniente dal vecchio tracker di Morning Global). | «In transito» |
| `Ready for Departure` | Pronto a partire verso il tuo Paese. | «In transito» |
| `Flight Departure to Destination` | Il volo verso il tuo Paese è decollato. | «In transito» |
| `Arrive Destination` | Ha raggiunto il tuo Paese; seguono ancora dogana e affidamento al partner di consegna. | «In transito» |
| `Customs Clearance Begins` | Il pacco è in lavorazione presso la dogana del tuo Paese. | «In dogana» |
| `Customs Cleared` | La dogana ha svincolato la spedizione. | «In transito» |
| `Handed Over for Delivery` | Affidato al partner che consegna nel tuo Paese; non è ancora sul furgone di recapito. | «In transito» |
| `Out for Delivery` | In consegna oggi. | «In consegna» |
| `Delivered` | Consegnato. | «Consegnato» |

Quando la descrizione aggiunge dettagli allo stato, Peek mostra `stato: descrizione`, omettendo i numeri di telefono. Le diciture non presenti in elenco vengono lette dalle regole generali di Peek o mostrate senza cambio di fase.

**Non trovato.** Per un numero sconosciuto, il tracker di Morning Global risponde `No shipment record was found for this tracking number.`

## Chi consegna i pacchi Morning Global?

Con `Handed Over for Delivery`, Morning Global passa il pacco al partner incaricato della consegna nel tuo Paese. Il sito non elenca i partner di recapito e il tracker non comunica a Peek né il nome del corriere locale né il suo numero di tracciamento: per riprogrammare una consegna, chiedi quel numero al negozio o all'assistenza di Morning Global.

**Sedi proprie.** Morning Global indica Regno Unito e Stati Uniti tra le sedi gestite direttamente, con quartieri generali regionali a Londra (Regno Unito ed Europa), Los Angeles (Americhe) e Canton nella Cina continentale, oltre a una filiale ad Amsterdam e a un contatto dedicato per l'Italia; il Canada figura tra le sedi chiave accanto agli USA e l'Australia nella rete partner. Morning Global non pubblica regole proprie su tentativi di consegna o punti di ritiro: valgono quelle del corriere finale ([mancata consegna](guide:missed-delivery), [pacchi non ritirati](guide:uncollected-parcel)).

**Dogana in Italia.** Nell'**Unione europea**, gli acquisti online spediti da fuori UE fino a 150 € pagano dal 1° luglio 2026 un dazio doganale temporaneo di 3 € per articolo (beni con stessa voce tariffaria, descrizione e, ove richiesto, origine; oltre 150 € si applica il dazio ordinario) e, dal 1° novembre 2026, una commissione di gestione dell'Unione di 2 € per articolo qualunque sia il valore, oltre all'IVA d'importazione in **Italia** salvo che l'IVA sia già stata riscossa al pagamento tramite il regime IOSS (**Livigno** è fuori sia dal territorio doganale sia dal territorio IVA dell'UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** rientrano nel territorio doganale dell'UE ma sono fuori dal territorio IVA dell'UE). Il 22 ottobre 2026 Morning Global ha informato i venditori che dal 1° novembre 2026 viene addebitata una `EUR 2 Customs Handling Fee` per ciascuna riga di dichiarazione doganale sulle spedizioni B2C dirette nell'UE (sia IOSS sia DAP, anche oltre 150 €), più eventuale IVA sulla commissione in base al Paese di destinazione, senza precisare se i negozi la riaddebitino all'acquirente ([pacchi fermi in dogana](guide:customs)).

**Documenti doganali e ritardi.** Se la dogana richiede copia di un documento o della fattura, Morning Global mette a disposizione il modulo [Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/): usalo solo su `morninglobal.com` ([SMS truffa sulle consegne](guide:delivery-scam-texts)). Gli avvisi di ritardo escono nella pagina *News* (come la nota del 30 ottobre 2026 su 2–3 giorni lavorativi aggiuntivi per MGIP in Australia a causa di scioperi).

## Pacco Morning Global fermo, smarrito o restituito

I clienti di Morning Global sono i negozi che spediscono: per ritardi, smarrimenti, danni o resi contatta per primo il venditore o il marketplace.

**Non ancora trovato.** Se il corriere non è ancora confermato ma il codice ha la forma `BR…MG` (senza cifra di controllo postale), Peek indica «Probabilmente Morning Global» e «In attesa della prima scansione di Morning Global»; se supera anche il controllo postale (`BR482910576MG`), Peek interroga Morning Global e [Chronopost](carrier:chronopost) assegnando chi riconosce il collo. Salvato sotto Morning Global, mostra «In attesa di Morning Global». Se dopo una settimana non compare nulla, verifica il codice col negozio.

**Fermo su `Order Created` o senza aggiornamenti.** `Order Created` indica che il venditore non ha ancora affidato il collo. In viaggio, dopo quattro giorni senza nuove righe, Peek segnala il giorno dell'ultima riga («Nessun aggiornamento da lun 12 ott»); dopo `Handed Over for Delivery`, chiedi il codice del corriere locale ([perché il tracciamento non si aggiorna](guide:tracking-not-updating)).

**Smarrito, danneggiato, rifiutato o consegnato ma assente.** I termini di Morning Global concedono al negozio 30 giorni dalla presa in carico per aprire un reclamo e 90 giorni per inviare i documenti, con risarcimento limitato al valore dichiarato e comunque a massimo 50 US$ per collo in caso di colpa di Morning Global; una mancata consegna per indirizzo errato o incompleto, assenza o rifiuto conta come ordine completato verso il negozio ([pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [restituito al mittente](guide:returned-to-sender)). Se risulta `Delivered` ma non c'è nulla, controlla `Signed By` sul tracker di Morning Global (omesso da Peek), chiedi ai vicini e avvisa il venditore ([consegnato ma non ricevuto](guide:delivered-not-received)).

## Assistenza clienti e contatti Morning Global

Per informazioni sulle spedizioni nella [pagina Contact di Morning Global](https://www.morninglobal.com/contact/) (indica il numero di tracciamento; non sono pubblicati orari):

- **Italia**: `cs.mgit@morninglobal.com`.
- **Regno Unito** (sede regionale a Londra): `cs.mgeu@morninglobal.com`, `+44 20 7019 9035`.
- **Stati Uniti** (sede regionale a Los Angeles): `cs.mgus@morninglobal.com`, `+1 888 981 8688`.
- **Hong Kong e Macao** (sede mondiale a Hong Kong): `cs.mghk@morninglobal.com`, `+852 3669 6360`.
- **Cina continentale** (sede regionale a Canton): `cs.mgchina@morninglobal.com`, `+86 4008 268 216`.
- **Altri team e supporto mondiale**: India, Filippine e Singapore hanno caselle dedicate sulla stessa pagina; per il resto del mondo `cs.mgworldwide@morninglobal.com`, WhatsApp `+1 (479) 777 3307` (o QR code) e modulo online (nome, email, azienda, messaggio).

**Attenzione agli SMS truffa.** Il dominio ufficiale è `www.morninglobal.com` (`morninglobal.com`). Prima di seguire link ricevuti per messaggio, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Morning Global con Peek

[Peek](/) non assegna Morning Global dalla sola forma di un codice `BR…MG`, ma segue oltre 3.500 corrieri: incolla il numero, vedrai «Verifica dei servizi di tracciamento…» e «Morning Global ha questo pacco.» appena il tracker di Morning Global lo riconosce. Un link `www.morninglobal.com` con `tracking_number=` o `ydh=` registra subito la spedizione sotto Morning Global («Corriere rilevato»). Altrimenti tocca «Cambia» e scegli Morning Global sotto «Compatibili con questo numero» o «Tutti i corrieri» (oppure «Cambia corriere» su un pacco già salvato).

Peek legge il tracker di Morning Global senza CAP: mostra lo stato, ogni tappa col suo luogo, il Paese di destinazione e l'ora di consegna, legge l'orario in base al fuso UTC accanto al Paese (e senza fuso, sull'orologio del luogo, di una riga vicina o di Hong Kong) e converte tutto nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …»). Esclude origine, `Estimated Delivery`, peso, colli, `Reference` e `Signed By` (nessuna data di consegna prevista) e segue il pacco su Morning Global fino alla fine senza interrogare le poste del tuo Paese.

Peek controlla al massimo una volta all'ora (anche in «In consegna») e attende 2 ore dopo un controllo non riuscito (anche per l'aggiornamento manuale). Puoi attivare «Ogni scansione», «Passaggi importanti» (`Customs Clearance Begins`, `Out for Delivery` e `Delivered`) o «Solo la consegna». Peek non può contattare Morning Global o il negozio per te.

## Domande frequenti sul tracciamento Morning Global

### Morning Global è affidabile?

Sì. Fondata nel 1987, Morning Global è la divisione internazionale del gruppo di corrieri di Hong Kong Morning Express Group, con sede globale a Hong Kong e attività dirette in 14 Paesi al servizio di marchi, rivenditori e marketplace.

### Come traccio un pacco Morning Global?

Incolla il codice fornito dal negozio nel riquadro in alto oppure inseriscilo su `morninglobal.com/trace-track/` (fino a 20 numeri separati da virgole).

### Cosa significa `Handed Over for Delivery` su Morning Global?

Morning Global ha consegnato il collo, dopo la dogana, al partner incaricato del recapito nel tuo Paese. Non è ancora sul furgone di consegna: seguirà `Out for Delivery`.

### Chi consegna i pacchi Morning Global?

Un corriere partner nel tuo Paese, che il sito e il tracker di Morning Global non nominano. Chiedi il relativo codice di tracciamento al negozio o al supporto di Morning Global.

### Come contatto Morning Global?

Scrivi indicando il numero di tracciamento a `cs.mgit@morninglobal.com` in Italia, `cs.mgeu@morninglobal.com` per il Regno Unito o `cs.mgworldwide@morninglobal.com` nel resto del mondo (anche WhatsApp `+1 (479) 777 3307`). Per l'ordine in sé, chiedi prima al venditore.

:::sources
- [Morning Global: Trace & Track](https://www.morninglobal.com/trace-track/) – ricerca fino a 20 codici, Shipment Journey, Estimated Delivery, Reference, Signed By e messaggio di spedizione non trovata
- [Morning Global: Home](https://www.morninglobal.com/) – fondazione nel 1987, presenza diretta in 14 Paesi e avvisi
- [Morning Global: About Us](https://www.morninglobal.com/about-us/) – divisione internazionale di Morning Express Group a Hong Kong
- [Morning Global: Services](https://www.morninglobal.com/services/) – servizi MGIE e MGIP, oltre 220 destinazioni e limite di 30 kg
- [Morning Global: Global Network](https://www.morninglobal.com/global-network/) – sedi dirette (Regno Unito, USA), Canada e rete partner (Australia)
- [Morning Global: Contact](https://www.morninglobal.com/contact/) – casella italiana e recapiti regionali, WhatsApp mondiale, modulo e sedi di Hong Kong, Canton (Cina continentale), Londra e Los Angeles
- [Morning Global: Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) – modulo per l'invio dei documenti doganali
- [Morning Global: Terms and Conditions (Privacy Policy)](https://www.morninglobal.com/privacy-policy/) – mancate consegne, termini di reclamo di 30 / 90 giorni e massimale di 50 US$
- [Morning Global: EUR 2 Customs Handling Fee for EU-bound B2C Shipments](https://www.morninglobal.com/morning-global-service-update-introduction-of-eur-2-customs-handling-fee-for-eu-bound-b2c-shipments-from-1-november-2026/) – commissione di 2 € per riga doganale dal 1° novembre 2026 e IVA sulla commissione
- [Morning Global: Extended Transit Times in Australia](https://www.morninglobal.com/morning-global-service-impact-notice-extended-transit-times-in-australia-due-to-industrial-action/) – ritardo di circa 2–3 giorni lavorativi su MGIP in Australia
- [Morning Global: News](https://www.morninglobal.com/news/) – avvisi operativi
- [Commissione europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € fino a 150 € da luglio 2026 e commissione di 2 € da novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definizione di articolo, commissione di 2 € qualunque sia il valore ed esenzione IVA all'importazione tramite IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno fuori dal territorio doganale e IVA UE; Campione d'Italia e acque italiane del Lago di Lugano fuori dal territorio IVA UE
- [Universal Parcel Scraper: Morning Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/morning-global) – formato BR…MG, link, stati, fusi orari, frequenza oraria e campi omessi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – controllo S10 con Chronopost e nessun passaggio alle poste nazionali
:::
