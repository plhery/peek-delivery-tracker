---
title: Tracking WanbExpress: codici WNB…YQ, stati e consegna
description: Traccia un pacco WanbExpress (Wanb Express) dalla Cina: formato del codice WNB…YQ, significato degli stati in inglese, corriere finale e cosa fare se si ferma.
slug: tracking-wanbexpress
published: 2026-11-21
updated: 2026-11-21
---

WanbExpress (noto anche come Wanb Express, WanBang Express o WANB) è un corriere cinese che spedisce pacchi all'estero, dove un corriere locale effettua la consegna. Se un negozio ha spedito il tuo ordine con WanbExpress, il codice inizia con `WNB` e termina con `YQ`: incollalo nel campo qui sopra. Il tracciamento di WanbExpress, tracking.wanbexpress.com, può anche indicare il corriere che consegna nel tuo Paese e il relativo codice.

## Codice di tracking WanbExpress: com'è fatto

I codici di WanbExpress sono composti da `WNB`, due lettere, dieci cifre e `YQ`, per un totale di 17 caratteri. Esempi inventati:

| Codice (inventato) | Cos'è |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, due lettere, dieci cifre e `YQ`: un codice WanbExpress. |
| `WNBKG482910573YQ` | Solo nove cifre: non corrisponde al formato WanbExpress. Controlla se manca una cifra. |
| `JSEKG4829105736YQ` | Finisce anch'esso con `YQ`, ma inizia con `JSE`: è un codice [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Inizia con `JXC`: è un codice [Juxi Express](carrier:juxi-express). |

Diversi spedizionieri cinesi chiudono i loro codici con `YQ`, ciascuno con un prefisso diverso: `WNB` distingue WanbExpress. In Peek spazi, trattini e minuscole non fanno differenza.

**Dove trovarlo.** Il venditore lo inserisce nell'e-mail di spedizione o nella pagina dell'ordine. Se non lo trovi, leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Un link di tracciamento.** Un link al sito di WanbExpress che termina con `?trackingNumbers=` seguito da un codice apre quella spedizione sul loro portale. Il codice `WNB` è la parte dopo il segno di uguale: copialo da lì in Peek.

**Un secondo codice.** Il portale di WanbExpress può mostrare anche il numero usato dal corriere che consegna il pacco. Quel numero appartiene all'altro corriere: cercalo sul suo sito o aggiungilo in Peek come pacco separato.

## Stati del tracking WanbExpress spiegati

Le scansioni di WanbExpress sono in inglese, ciascuna con l'orario e talvolta il luogo. Ecco le diciture presenti nel codice di Peek:

| Cosa dice WanbExpress | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | Il negozio ha prenotato la spedizione e inviato i dati. WanbExpress non ha ancora il pacco. | «Etichetta creata» |
| `Parcel received at Shenzhen Sorting Centre.` | Il centro di smistamento di Shenzhen, in Cina, ha ricevuto il pacco: è la prima scansione fisica. | «Preso in carico» |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Smistato e poi partito da quel centro. | «In transito» |
| `A-Scan: shipment in international transit` | Scansione di accettazione riportata tra la lavorazione al centro di smistamento e la partenza. | «In transito» |
| `Customs declaration completed` | È stata completata una dichiarazione doganale. La dicitura non indica di quale Paese e non è lo sdoganamento all'importazione, che compare come `Custom clearance completed`. | «In transito» |
| `Customs inspection has not yet released` | Ispezione doganale all'esportazione in origine, tra la dichiarazione e il volo. | «In transito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Partito da un impianto di transito. | «In transito» |
| `Parcel is leaving Airport, Parcel Leaving Port` | In partenza sul volo verso il Paese di destinazione. | «In transito» |
| `Parcel arrives at Airport, Parcel Arrival` | Atterrato nel tuo Paese o nelle vicinanze. Seguono le operazioni doganali. | «In transito» |
| `Custom clearance completed` | La dogana d'importazione ha sdoganato il pacco. | «In transito» |
| `Parcel received by in JFK01.` | Registrato da WanbExpress come presa in carico per l'ultimo tratto, senza indicare chi lo ha ricevuto. | «In transito» |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Avviso di un partner riportato senza codice WanbExpress: il pacco non gli è ancora stato consegnato. | «Etichetta creata», oppure uno stato successivo se una scansione ha già fatto avanzare il pacco |
| `In transit along Poste Italiane’s logistic network` | In viaggio nella rete del corriere finale, qui [Poste Italiane](carrier:poste-italiane). | «In transito» |
| `Parcel is out for delivery.` | In consegna con il corriere. | «In consegna» |
| `Consignee absent, Consignee could not be found.` | Il corriere partner non ha trovato nessuno. | «Tentativo di consegna» |
| `Placed in Parcelshop/Parcel locker` | Dopo il tentativo mancato, il partner ha lasciato il pacco in un punto di ritiro o in un locker. | «Pronto per il ritiro» |
| `Parcel has been delivered.(Front door)` | Consegnato. `(Front door)` indica dove è stato lasciato. | «Consegnato» |

**Le scansioni del corriere finale.** Dopo il volo, il tracciamento di WanbExpress inoltra le scansioni del corriere locale con le sue parole, di solito senza un codice WanbExpress. Su un pacco diretto in Germania, la cronologia mostra la consegna con [iMile](carrier:imile), con il nome del centro o della stazione tra parentesi `【】`:

| Cosa dice iMile | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Received.` | Il centro iMile nel Paese di destinazione ha ricevuto il pacco. | «In transito» |
| `Arrived.` | Arrivato alla sede di consegna che serve la tua zona. | «In transito» |
| `Dispatch task assigned to DA.` | Un autista (DA, delivery associate) è stato assegnato al giro, anche ore prima della partenza. | «In transito» |
| `Shipment out for delivery.` | In consegna. | «In consegna» |
| `<Route not completed>`, `Back To DS.` | Il giro si è concluso senza consegna e il pacco è rientrato in sede (DS). | «Tentativo di consegna» (da solo dopo il giro; dopo un tentativo già segnalato `Back To DS.` resta un avviso, e dopo «In transito» resta «In transito») |
| `Scheduled to <2026-11-02> .` | Giorno fissato per il tentativo successivo dopo il rientro in sede. | Ancora «Tentativo di consegna» |

Anche gli spostamenti di altri partner arrivano così. Sui pacchi verso gli Stati Uniti, `Parcel received by Transit Facility.` è la presa in carico presso un centro di transito del partner locale, `In Processing at Transit Facility.` lo smistamento, `In Transit to Next Facility.` il trasferimento verso la sede locale e `Parcel received by local Facility.` l'arrivo prima del giro di consegna. `It is currently in transit to the next facility` e `The package is being transferred to the next facility` indicano trasferimenti tra sedi dopo il volo, e `Shipment arrived to sorting centre / Shipment in the sorting centre` l'ingresso nel centro di smistamento del partner. Peek li mostra tutti e sette come «In transito». Su un pacco verso il Regno Unito, le righe di [InPost](carrier:inpost) possono comparire anche prima della scansione nel centro di smistamento WanbExpress: `Your parcel is on its way to InPost. Please check back for updates` («Etichetta creata») e `We have your parcel. Please check back for updates` («Preso in carico»). `We'll apply your My Account preferences to this parcel delivery` è solo una nota di InPost sulle preferenze dell'account, non una scansione: Peek la tiene in cronologia senza cambiare fase. Lo stesso vale per `Consignee Contacted`, una telefonata dell'autista registrata nello stesso minuto di un tentativo fallito.

**Orari.** Ogni scansione include il proprio scarto rispetto a UTC. Peek legge quell'istante e mostra tutti gli orari nel fuso orario del tuo dispositivo, dal più recente al più vecchio. Per i silenzi e i passaggi tipici delle spedizioni dalla Cina, leggi [tracciare un pacco dalla Cina](guide:tracking-from-china).

## Chi consegna i pacchi WanbExpress

Le scansioni di WanbExpress seguono il pacco fuori dalla Cina, attraverso la dogana e fino al corriere locale che lo consegna. Il portale di WanbExpress può indicare quel corriere, il suo codice e una foto di consegna.

**Cosa mostra il codice di Peek.** Su un pacco verso la Germania, la cronologia mostra iMile in consegna, e una scansione cita la rete di Poste Italiane. Su un pacco per il Regno Unito compaiono le righe di InPost. Il codice contempla anche rietichettature su numeri Cainiao Express in Francia (`DOFR…HD` o `CNFR…HD`) e righe inoltrate da J&T Express Australia.

**Tentativi di consegna e ritiro.** Valgono le regole del corriere che consegna: quanti tentativi fa, se lascia un avviso, dove custodisce il pacco e per quanto tempo. Peek non ha trovato regole proprie di WanbExpress su questi aspetti. Appena conosci corriere locale e codice, controlla il suo tracciamento e accordati con lui per la riconsegna. Vedi [consegna mancata](guide:missed-delivery).

## Pacco WanbExpress bloccato o che non arriva

**Codice non trovato.** Se il tracciamento di WanbExpress non conosce il codice, Peek mostra «In attesa di WanbExpress» e continua a verificare. Se due giorni dopo WanbExpress non lo riconosce ancora e nessun'altra fonte ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» Controlla il codice carattere per carattere rispetto al messaggio del venditore: `WNB`, due lettere, dieci cifre, `YQ`. Se dopo qualche giorno non compare nulla, chiedi al venditore se il pacco è stato davvero spedito.

**Fermo su `Shipping Order Created.` o `Parcel Data Received`.** Il negozio ha prenotato la spedizione, ma WanbExpress non ha ancora scansionato il pacco. Chiedi al venditore quando lo ha consegnato al corriere.

**Fermo dopo `Parcel arrives at Airport, Parcel Arrival`.** È probabile che sia in attesa dello sdoganamento all'importazione. Per le spedizioni dirette nell'Unione europea, gli acquisti online provenienti da fuori UE prevedono dal 1° luglio 2026 un dazio doganale temporaneo di 3 € per articolo (categoria di prodotto) sui pacchi fino a 150 € e, dal 1° novembre 2026, una tariffa di gestione dell'Unione di 2 € per articolo, qualunque sia il valore. Se ti vengono chiesti oneri d'importazione, leggi [pacchi fermi in dogana](guide:customs); se la sosta si prolunga, avvisa il venditore. Altro in [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Nulla dopo `Custom clearance completed`.** Il corriere locale ha preso in carico il pacco o sta per farlo, e il suo tracciamento può essere più aggiornato di quello di WanbExpress. Cerca nome e codice su tracking.wanbexpress.com oppure chiedili al venditore.

**Tentativo di consegna.** Dopo una riga come `<Route not completed>`, decide il corriere finale quando riprovare o dove lasciare il pacco. Rivolgiti a quel corriere, non a WanbExpress.

**Segnato come consegnato, ma non hai ricevuto nulla.** Controlla se tracking.wanbexpress.com mostra una foto di consegna, poi contatta il corriere locale e il venditore. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** Il contratto con WanbExpress è del venditore: rivolgiti al venditore o al marketplace per rimborso o nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti WanbExpress

Peek non ha potuto consultare il sito web di WanbExpress, quindi questa pagina non riporta recapiti di WanbExpress. Per il tuo ordine contatta il venditore che ha prenotato la spedizione, o il marketplace se il venditore non risponde. Per la consegna vera e propria rivolgiti al corriere locale.

## Tracciare un pacco WanbExpress con Peek

[Peek](/) riconosce un codice WanbExpress dalla sua forma (`WNB`, due lettere, dieci cifre e `YQ`, non usata da altri corrieri nel catalogo) e interroga il tracciamento di WanbExpress con il solo codice, senza account, CAP o telefono.

Poiché il portale di WanbExpress richiede una verifica prima di rispondere, Peek lo interroga tramite browser. Se il portale non risponde, Peek si rivolge ai servizi di tracciamento e mostra la cronologia che possiedono, nelle loro parole; tale cronologia può essere indietro rispetto a WanbExpress.

Dal tracciamento di WanbExpress, Peek mostra lo stato, le scansioni con il luogo, e i Paesi di origine e destinazione riportati nel record. Quando il record indica il pacco in transito, in consegna o consegnato, Peek segue quell'indicazione anche se l'ultima scansione dice meno. Se il tracciamento di WanbExpress fornisce il numero del corriere finale e il pannello dell'ultimo miglio individua un corriere in catalogo il cui formato corrisponde a quel numero, Peek può mostrare «Consegna con …» (o «Annunciato a …, non ancora ricevuto») e passare a seguirlo lì. Peek non mostra una data di consegna prevista e lascia fuori le foto di consegna e l'indirizzo del destinatario: per vederli usa tracking.wanbexpress.com, o aggiungi separatamente in Peek il codice del corriere finale. Peek traccia oltre 4.500 corrieri.

Finché tieni il pacco aperto sullo schermo o nel giorno della consegna con le notifiche attive, e mentre risponde il tracciamento di WanbExpress, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 nell'orario del corriere il giorno in cui entra in consegna (ogni 5 minuti quando la verifica apre un browser); altrimenti ogni ora. Tramite i servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno della consegna finché tieni il pacco aperto o hai le notifiche attive. Dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». «Passaggi importanti» (consegna, ritiro, dogana, tentativi falliti e resi) copre la messa in consegna, il tentativo fallito e la consegna, ma non le righe doganali di WanbExpress, che Peek classifica come «In transito». Peek non può contattare WanbExpress o il venditore al posto tuo.

## Domande sul tracking WanbExpress

### Che cos'è WanbExpress?

Un corriere cinese, noto anche come Wanb Express, WanBang Express o WANB. Nel codice di Peek, le sue scansioni partono da un centro di smistamento a Shenzhen, seguono volo e dogana e si concludono con le scansioni del corriere che consegna nel Paese di destinazione.

### Dove posso tracciare un pacco WanbExpress?

Nel campo qui sopra oppure sul tracciamento di WanbExpress su tracking.wanbexpress.com. Quel sito esegue un controllo antibot e può anche indicare il corriere finale. Inserisci l'intero codice `WNB…YQ`.

### Cosa significa «Customs declaration completed» sul tracking WanbExpress?

Che è stata completata una dichiarazione doganale per il tuo pacco. La dicitura non specifica di quale Paese e non indica lo sdoganamento all'importazione, che appare come `Custom clearance completed`. Peek mostra entrambi come «In transito».

### Chi consegna i pacchi WanbExpress nel mio Paese?

Un corriere locale, che il sito di WanbExpress può indicare insieme al proprio codice. Il codice di Peek mostra iMile su un pacco verso la Germania, le righe di InPost verso il Regno Unito e una scansione che cita la rete di Poste Italiane, oltre a passaggi verso J&T Express Australia o codici `DOFR…HD`/`CNFR…HD` in Francia; per gli altri Paesi controlla tracking.wanbexpress.com o chiedi al venditore.

### Chi devo contattare se il pacco WanbExpress non arriva?

Il venditore da cui hai acquistato: è lui ad aver affidato il pacco a WanbExpress. Peek non ha potuto verificare il sito di WanbExpress per eventuali recapiti.

:::sources
- [Universal Parcel Scraper: WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – nomi, formato `WNB…YQ`, controllo tramite browser, diciture delle scansioni e fasi (inclusi `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, righe di iMile, InPost, Poste Italiane e partner USA), orari con scarto UTC, riconoscimento del corriere finale (`TrackLastMileCarrier`), foto e indirizzo esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri formati che terminano in `YQ` (`JS Express`, `Juxi Express`), servizi di tracciamento di riserva e passaggio al corriere locale
- [Commissione europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio doganale temporaneo di 3 € per articolo fino a 150 € dal 1° luglio 2026, tariffa di gestione dell'Unione di 2 € dal 1° novembre 2026
- [Commissione europea: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tariffa di gestione di 2 € per articolo, qualunque sia il valore
:::
