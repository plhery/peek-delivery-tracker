---
title: Tracciamento ePost Global: numeri EPG, stati e consegna
description: Traccia un pacco ePost Global: formato dei codici EPG, significato degli stati in inglese, chi consegna in Italia e Svizzera e come seguirlo con Peek.
slug: tracciamento-epost-global
published: 2026-11-21
updated: 2026-11-21
---

ePost Global (`epostglobalshipping.com`) è un consolidatore internazionale di pacchi e corrispondenza con sede negli Stati Uniti (`11137 Warland Drive, Cypress, CA 90630, USA`). Nato nella divisione *Express Postal Options (XPO)* di R.R. Donnelley e attivo da oltre 25 anni nella logistica transfrontaliera, raccoglie gli ordini nei propri centri di smistamento statunitensi, gestisce il trasporto aereo e lo sdoganamento e affida il recapito finale a oltre 100 operatori postali e corrieri in più di 200 Paesi e territori (oltre alle spedizioni nazionali in USA e Canada tramite `eDGE`). I suoi codici iniziano con `EPG`: incolla il tuo nel riquadro qui sopra. I portali ufficiali di tracciamento sono `epgtrack.com` (*EPG Track*) e `ppxtrack.com` (*PPX Track*); nel catalogo di Peek puoi trovare ePost Global anche cercando `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` o `RRD International Logistics`.

## Numero di tracciamento ePost Global: formato ed esempi

Peek riconosce una sola forma per ePost Global: le lettere `EPG` seguite da 10 a 15 cifre (le etichette attuali ne usano 15). Con cifre inventate:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `EPG482910573649182` | `EPG` e 15 cifre: un codice ePost Global attuale. |
| `EPG4829105736` | `EPG` e 10 cifre: sempre ePost Global, la lunghezza minima accettata da Peek. |
| `EPG482910573` | Solo 9 cifre: non è il formato di ePost Global. Tre lettere e nove cifre corrispondono a Colis Privé, Purolator e TNT Australia, che Peek interroga di conseguenza. Se imposti ePost Global a mano, Peek avvisa che «non emette numeri come questo». Controlla se manca una cifra. |
| `EPG4829105736491827` | 16 cifre, una di troppo: Peek non riconosce alcun corriere. Verifica che non ci sia una cifra in più. |
| `LZ482910576US` | Due lettere, nove cifre e `US`: un codice postale statunitense. Peek assegna [USPS](carrier:usps) alla maggior parte di quelli che iniziano per `C`, `E`, `L` o `R` (secondo le FAQ di ePost Global, il tracciamento avviene sul portale PPX o su quello USPS a seconda del prodotto). Per vedere anche i passaggi interni di ePost Global su Peek, aggiungi il codice `EPG`. |

Spazi, trattini e lettere minuscole non fanno differenza quando incolli il numero in Peek. Su `epgtrack.com` puoi inserire fino a 20 codici alla volta, su `ppxtrack.com` fino a 10.

**Dove trovarlo.** Nell'email di conferma spedizione del negozio o nella pagina dell'ordine. Se non lo trovi, leggi [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati del tracciamento ePost Global spiegati

Il tracker di ePost Global elenca ogni riga in inglese con data, ora, dicitura e, quasi sempre, località. Ecco prima le voci proprie di ePost Global nell'ordine in cui compaiono:

| ePost Global scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Data Received` | Il negozio ha inviato i dati elettronici del pacco a ePost Global, che non ha ancora ricevuto fisicamente la merce. | «Etichetta creata» |
| `Shipment in Transit to the ePost Global Processing Center` | Aggiunto un minuto dopo `Data Received` su ogni spedizione: fa parte della creazione dell'etichetta, non indica che il pacco sia già in viaggio. | «Etichetta creata» |
| `Parcel Received and Processing` | Un centro di lavorazione statunitense di ePost Global ha scansionato il pacco (con città e Stato USA). ePost Global dichiara di lavorare e far ripartire i pacchi in 24 ore o meno. | «Preso in carico» |
| `Parcel Shipped` | Il pacco ha lasciato il centro di lavorazione negli Stati Uniti. | «In transito» |
| `In transit to … tracking #: …` | Passaggio al partner incaricato della tratta successiva un minuto dopo la partenza, con il nome del servizio e il codice del partner (a volte scritto `In transit via …` o `tracking#:`). | «In transito» |
| `In transit` | Lo stesso passaggio al partner, senza indicare servizio né secondo numero. | «In transito» |

**Le righe ritrasmesse dai partner.** Dopo l'affidamento, `epgtrack.com` riporta gli aggiornamenti del vettore partner. Quelli mappati direttamente dal codice di Peek:

| Dopo l'affidamento | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | Il partner statunitense OSM Worldwide ha ricevuto i dati del pacco. | nessun passo indietro |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Transito in un impianto OSM Worldwide e consegna al suo corriere partner. | «In transito» |
| `Electronic manifest received` | Manifesto elettronico registrato da ECMS Express dopo la presa in carico di ePost Global: semplice notifica senza luogo che mantiene lo stato precedente. | nessuna variazione |
| `Parcel has arrived at export distribution warehouse` | Arrivo nel magazzino di esportazione di ECMS Express nel Paese d'origine. | «In transito» |
| `Customer Information Manifested` | DAI Post, il partner per la Nuova Zelanda, ha registrato i dati. | nessun passo indietro |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | Hub USA di DAI Post, ritardo del volo (`Airline Delay` indica un volo in ritardo, non un'anomalia del pacco) e presa in carico dall'aereo a destinazione. | «In transito» |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | [PostNL](carrier:postnl), tramite Spring, ha preso in carico il pacco e lo instrada per via aerea verso il Paese di destinazione. | «In transito» |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Arrivo nel Paese di destinazione e lavorazione nei centri PostNL. | «In transito» |
| `The item is at customs`, `The item is being processed by customs` | Controllo doganale d'importazione su una spedizione gestita da PostNL. | «In dogana» |
| `Item released by customs` | Svincolo doganale completato presso PostNL; il pacco riparte. | «In transito» |
| `The item is out for delivery` | In consegna secondo la scansione PostNL. | «In consegna» |
| `The item will be scheduled for a new delivery attempt` | Tentativo non riuscito segnalato da PostNL; verrà fissata una nuova consegna. | «Tentativo di consegna» |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Avvisi di [La Poste](carrier:la-poste) per pacchi portati in Francia tramite PostNL, mentre sono ancora in viaggio. | «In transito» |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Rete europea di GLS: partenza dall'hub e arrivo al centro pacchi. | «In transito» |
| `The parcel is expected to be delivered during the day.` | Rete europea di GLS: consegna prevista in giornata. | «In consegna» |
| `The parcel has been delivered.` | Rete europea di GLS: consegnato. | «Consegnato» |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Partner britannico di [Royal Mail](carrier:royal-mail): verso il Regno Unito o in transito al deposito. | «In transito» |
| `Arrived in customs` | Lo stesso partner britannico: in dogana nel Regno Unito. | «In dogana» |
| `Ready for Collection` | Lo stesso partner britannico: pronto al punto di ritiro. | «Pronto per il ritiro» |
| `Duty &/or tax fees requested` | Il vettore finale richiede il pagamento di dazi o tasse prima del recapito. | «In dogana» |
| `Received payment` | Oneri pagati; la spedizione prosegue. | «In transito» |
| `The item is available at the pickuppoint` | Il pacco è disponibile presso un punto di ritiro del corriere locale. | «Pronto per il ritiro» |

Una presa in carico del partner dopo `Parcel Shipped` conta come «In transito», non come nuovo inizio. Peek legge le altre diciture dei partner con le sue regole condivise; una frase sconosciuta resta visibile così com'è senza far arretrare un pacco già in consegna o consegnato.

## Chi consegna i pacchi ePost Global in Italia e in Svizzera

ePost Global collabora con oltre 100 poste e corrieri di ultimo miglio su sei continenti. Negli storici presenti nel codice di Peek compaiono la rete europea di GLS, [PostNL](carrier:postnl) tramite Spring (con passaggio a [La Poste](carrier:la-poste) in Francia), OSM Worldwide, ECMS Express, DAI Post e [Royal Mail](carrier:royal-mail). Quando ePost Global non nomina un partner ma fornisce un codice postale internazionale valido (due lettere, nove cifre, due lettere), Peek interroga l'operatore postale di destinazione: [Poste Italiane](carrier:poste-italiane) in **Italia** e la [Posta Svizzera](carrier:swiss-post) in **Svizzera**.

**Tempi indicativi e copertura del tracking.** Dagli Stati Uniti, la pagina *Cross-Border Parcel Shipping* di ePost Global indica `1-5 days` porta a porta sulla maggior parte delle tratte per *Expedited Parcel with Delivery Confirmation* e `4 to 8 days` verso il Canada o `5 to 10 days` verso l'Europa occidentale per *Priority Parcel with Delivery Confirmation* (`4 to 8 days` verso Canada ed Europa occidentale per *Priority Packet Tracked*, con tracciamento porta a porta verso 48 Paesi principali). Cinque dei sette servizi pacchi includono il tracciamento porta a porta; i due servizi economici forniscono tracciamento interno dopo l'arrivo nel Paese di destinazione. Tentativi di recapito e giacenza seguono le regole del vettore finale: vedi [mancata consegna](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

**Dogana in Italia, nell'UE e in Svizzera.** Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € pagano un dazio doganale UE di 3 € per articolo (*per item*), e dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo indipendentemente dal valore (un articolo riunisce le merci dello stesso pacco con uguale classificazione tariffaria, descrizione e, se richiesta, origine; oltre 150 € valgono i dazi ordinari), più l'IVA all'importazione in **Italia**, se non è già stata pagata all'acquisto tramite IOSS (secondo l'ambito territoriale della Commissione europea, **Livigno** è fuori sia dal territorio doganale sia dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** fanno parte del territorio doganale UE ma non dell'area IVA UE). In **Svizzera**, secondo l'UDSC, l'IVA sull'importazione è dell'8,1 % (2,6 % aliquota ridotta), ma «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi», escluse eventuali spese del corriere. La nota doganale di ePost Global precisa che i 2 € si applicano sia agli invii `DDP` (oneri riscossi al checkout) sia ai `DDU` (a carico del destinatario alla consegna, con possibile fermo fino al saldo). Approfondisci in [pacco fermo in dogana](guide:customs).

## Pacco ePost Global fermo, smarrito o restituito

**«In attesa di ePost Global».** Quando `epgtrack.com` risponde `We haven't received any tracking updates`, Peek mostra «In attesa di ePost Global» e continua a controllare. Verifica che il codice sia composto da `EPG` e da 10 a 15 cifre; se dopo due giorni ePost Global non riconosce ancora il numero e nessun servizio di tracciamento lo trova, Peek mostra: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.»

**Nessuna novità dopo `In transit to …`.** Il pacco è passato al partner, il cui tracciamento diretto può essere più avanti. Se Peek non è ancora passato automaticamente a quel vettore, copia il codice dopo `tracking #:` e aggiungilo in Peek.

**Fermo da diversi giorni.** Dopo quattro giorni senza nuove scansioni, Peek segnala il giorno dell'ultima scansione («Nessun aggiornamento da lun 12 ott»), ricorda che i pacchi restano spesso silenziosi sui voli lunghi, in dogana o nel fine settimana (dopo tre settimane senza novità consiglia invece di contattare il venditore) e aggiunge il link «Chiedi aiuto a ePost Global» (oppure quello del partner non appena Peek segue la spedizione presso un corriere di cui conosce la pagina d'assistenza; OSM Worldwide e DAI Post ne sono privi nel catalogo, quindi resta il link di ePost Global). Eventuali sospensioni di linea sono pubblicate su `epostglobalshipping.com/service-updates`; vedi anche [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato consegnato ma non ricevuto, perso o reso.** Chiedi prima al corriere locale con il suo codice di consegna e poi contatta il negozio da cui hai acquistato, che è il committente della spedizione presso ePost Global. Leggi [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti ePost Global

Secondo `epostglobalshipping.com`:

- **Telefono (Stati Uniti)**: `+1 866-784-8444` (numero verde USA) e `+1 310-784-8485` (numero locale), senza orari né indirizzo email di supporto pubblicati sulla pagina.
- **Assistente online ePost Assist**: su `epostassist.epgtrack.com` (dai pulsanti *Track a package* o *Chat with our online support*), con le voci *Check package status*, *Create a support ticket* e *Get support ticket status*.
- **Modulo di supporto**: su `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*), con campo facoltativo per il numero di tracciamento.
- **Sede principale**: `11137 Warland Drive, Cypress, CA 90630, USA`.

**Attenzione agli SMS truffa.** I domini ufficiali usati da ePost Global per tracciamento e assistenza sono `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` ed `epostglobalshipping.com`. Su una spedizione `DDU` verso l'Italia o la Svizzera, un'eventuale richiesta di dazi o IVA arriva dalle poste o dal corriere che vi recapita: verifica sempre il mittente e consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco ePost Global con Peek

[Peek](/) riconosce subito un codice formato da `EPG` e da 10 a 15 cifre («Corriere rilevato») e interroga `epgtrack.com` senza account né CAP. Funziona anche un link di tracciamento `epgtrack.com` o `ppxtrack.com`: Peek ne ricava il numero (il primo se `?id=` ne elenca più d'uno). Se hai solo il codice del corriere finale, incollalo comunque: Peek segue oltre 4.000 corrieri.

Peek mostra lo stato, ogni scansione con il suo luogo, il Paese di destinazione e il partner con il relativo codice quando ePost Global li fornisce. Scarta la barra d'avanzamento, i CAP, i numeri civici o di deposito, il riferimento cliente e le lettere di vettura aerea (`MAWB`); `epgtrack.com` non fornisce data di consegna stimata, peso o prova di consegna, quindi Peek non ne mostra. Il pulsante «Apri il sito ePost Global» apre il tuo codice su `epgtrack.com`.

Il tracker stampa gli orari senza fuso orario. Peek legge `Parcel Received and Processing` e `Parcel Shipped` sull'orologio dello Stato USA del centro quando l'intero Stato ha un solo fuso, le voci PostNL prive di Paese sull'ora di PostNL, le righe della rete europea di GLS sull'ora dell'Europa centrale e quelle del partner britannico di Royal Mail sull'ora del Regno Unito; le altre righe vengono datate in base alla località o alla tratta del viaggio. Tutti gli orari compaiono nel fuso del tuo dispositivo e, su `peektracker.com` come nelle app iPhone e Android, la cronologia si chiude con «Orari nel tuo fuso orario: …».

Quando ePost Global nomina un partner noto a Peek, la scheda indica ad esempio «Consegna con PostNL» — oppure «Annunciato a PostNL, non ancora ricevuto» finché quel partner registra solo il preavviso senza scansioni proprie — e mostra il numero del partner accanto al codice `EPG`. Peek interroga allora anche quel corriere (con il suo numero o, se assente, con il codice `EPG`); senza partner nominato, per un numero postale valido interroga le poste del Paese di destinazione oppure l'unico corriere individuato dal formato del secondo numero (tra quelli il cui tracker diretto è letto da Peek senza dati aggiuntivi). Non appena quel vettore mostra una scansione almeno recente quanto l'ultima di ePost Global, Peek passa a seguirlo mantenendo lo storico di ePost Global (per la rete europea di GLS, poiché ePost Global non indica quale società GLS nazionale consegna, Peek resta sulle righe trasmesse da ePost Global).

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 locali nel giorno in cui passa «In consegna» finché risponde il tracker di ePost Global (quando subentra il corriere finale, Peek ne segue la cadenza; tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive) —; negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi attivare le notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare ePost Global né il negozio al posto tuo.

## Domande frequenti sul tracciamento ePost Global

### Che cos'è PPX Track?

È un altro portale di tracciamento di ePost Global (`ppxtrack.com`, fino a 10 codici per ricerca) e un alias di ePost Global nel catalogo di Peek. Peek legge i link `ppxtrack.com` esattamente come quelli di `epgtrack.com`.

### Cosa significa «In transit to» sul tracciamento ePost Global?

ePost Global ha affidato il pacco al partner che prosegue il trasporto. La riga riporta il servizio usato e, dopo `tracking #:`, il numero di tracciamento assegnato dal partner.

### Perché il tracciamento ePost Global resta fermo su «Data Received»?

Il negozio ha trasmesso i dati della spedizione a ePost Global, ma nessun centro di smistamento statunitense ha ancora scansionato fisicamente il collo. Chiedi al venditore quando ha consegnato il pacco al corriere.

### Chi consegna i pacchi ePost Global in Italia e in Svizzera?

Il partner indicato nella riga di affidamento: nei dati letti da Peek figurano tra gli altri la rete europea di GLS e PostNL tramite Spring. Per le spedizioni con codice postale internazionale senza partner nominato, Peek interroga Poste Italiane in Italia e la Posta Svizzera in Svizzera.

### Come posso contattare ePost Global?

Rivolgiti prima al negozio da cui hai comprato. Per contattare direttamente ePost Global puoi usare *ePost Assist* su `epostassist.epgtrack.com`, il modulo su `epostglobalshipping.com/contact-support` oppure i numeri statunitensi `+1 866-784-8444` e `+1 310-784-8485`.

:::sources
- [ePost Global: home page](https://epostglobalshipping.com/) – consolidatore internazionale, 20M+ spedizioni annue, 100+ partner, 200+ Paesi, sede a Cypress e numeri telefonici
- [ePost Global: About us](https://epostglobalshipping.com/about-us) – oltre 25 anni di attività, origini in R.R. Donnelley (XPO) e partner su sei continenti
- [ePost Global: Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – lavorazione entro 24 ore, tempi verso Canada ed Europa occidentale, tracking porta a porta o nel Paese di arrivo, DDP e DDU, portali PPX e USPS
- [ePost Global: eDGE](https://epostglobalshipping.com/edge) – rete domestica per Stati Uniti e Canada con oltre 100 corrieri
- [ePost Global: EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – commissione di 2 € per articolo su DDP e DDU, dazio di 3 € fino a 150 €, IOSS e fermo fino al pagamento in DDU
- [ePost Global: Contact support](https://epostglobalshipping.com/contact-support) – modulo assistenza, link a ePost Assist, telefoni e indirizzo
- [ePost Global: Contact us](https://epostglobalshipping.com/contact-us) – rimando al supporto online per pacchi dispersi
- [ePost Global: Service updates](https://epostglobalshipping.com/service-updates) – sospensioni e avvisi operativi per destinazione
- [EPG Track](https://epgtrack.com/) – tracker ufficiale fino a 20 numeri
- [PPX Track](https://ppxtrack.com/) – portale PPX Track fino a 10 numeri
- [ePost Assist](https://epostassist.epgtrack.com/) – stato spedizione e apertura o verifica di ticket di supporto
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – importo fisso di 2 € a prescindere dal valore, definizione di articolo ed esenzione IVA tramite IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia e Lago di Lugano
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e franchigia fino a 5 franchi
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – alias, formato EPG, link, stati propri e dei partner, fusi orari, campi esclusi e pagina di supporto
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati Colis Privé, Purolator e USPS e passaggio al partner o alle poste nazionali
:::
