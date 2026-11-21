---
title: Tracciamento DPD UK: numero a 14 cifre, stati e contatti
description: Traccia un pacco DPD UK: com'è fatto il numero a 14 cifre, significato degli stati in inglese, opzioni Predict e Pickup nel Regno Unito, dogana e contatti.
slug: tracciamento-dpd-uk
published: 2026-11-21
updated: 2026-11-21
---

DPD UK (`DPDgroup UK Ltd`) è il corriere britannico del gruppo Geopost, controllato dal gruppo francese [La Poste](carrier:la-poste) (*La Poste Groupe*). Nel **Regno Unito** consegna gli ordini di molti negozi online e spedisce pacchi anche verso l'Europa e il resto del mondo. Puoi seguire una spedizione su [Follow My Parcel di DPD UK](https://track.dpd.co.uk/) inserendo il numero di riferimento e il codice postale di consegna, oppure incollare direttamente il numero del pacco a 14 cifre nel riquadro qui sopra. Il codice ti viene inviato dal negozio, tramite l'SMS o l'email di DPD UK, oppure compare accanto al codice a barre sul tagliando di mancata consegna (*sorry we missed you*).

## Dove si trova il numero di tracciamento DPD UK e che formato ha?

Il venditore che ha spedito la merce possiede il numero del pacco; in alternativa puoi aprire il link contenuto nell'SMS o nell'email di DPD UK. Nel **Regno Unito**, se l'autista ha lasciato un avviso *sorry we missed you*, il numero è stampato vicino al codice a barre, e la pagina `track.dpd.co.uk` accetta anche il numero dell'avviso (*Calling card number*) abbinato al codice postale. Non lo trovi? Leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

Le pagine informative di DPD UK non pubblicano una regola sul formato dei codici. I numeri di pacco visibili sul tracciamento di DPD UK sono composti da 14 cifre, molto spesso con prefisso `1550` o `1597`, a cui la pagina può aggiungere in coda un quindicesimo carattere di controllo (cifra o lettera):

| Numero (inventato) | Cosa indica |
| --- | --- |
| `15504829105710` | Numero di pacco DPD UK: 14 cifre che iniziano con `1550` (o `1597`). |
| `15504829105710S` | Lo stesso codice con il carattere di controllo DPD aggiunto alla fine. |
| `18429105736482` | Altre 14 cifre: formato condiviso tra DPD UK, altre filiali DPD e altri corrieri. |

Anche [BRT](carrier:brt), [SEUR](carrier:seur), [Chronopost](carrier:chronopost), [DPD Germania](carrier:dpd-de), DPD Svizzera e DHL eCommerce UK usano codici a 14 cifre, quindi la sola lunghezza numerica non basta sempre a stabilire chi trasporta il pacco. Nel Regno Unito gli SMS autentici mostrano come mittente `DPD UK` (oppure `DPD Local` o `DPDgroup UK`), mentre il nome del negozio compare nel testo del messaggio. DPD Local è il marchio gemello di DPD UK (l'app DPD mostra le spedizioni di entrambi). In **Irlanda** le consegne sono gestite da DPD Ireland con una numerazione propria.

## Significato degli stati di tracciamento DPD UK

Il portale di DPD UK mostra gli aggiornamenti solo in inglese. Ecco le diciture principali nell'ordine consueto:

| DPD UK mostra | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | Il negozio ha prenotato la spedizione, ma DPD UK non ha ancora ricevuto il pacco. | «Etichetta creata» |
| `We've received your order details, and we're expecting your parcel shortly` | L'etichetta è registrata e DPD UK attende che il mittente consegni il pacco in un DPD Pickup Shop. | «Etichetta creata» |
| `Your parcel has been dropped off at a DPD Pickup Shop` | Il mittente ha affidato il pacco a un punto DPD Pickup Shop. | «Preso in carico» |
| `We have your parcel and it's on its way to our depot` | DPD UK ha preso in carico il pacco e lo sta portando al deposito. | «Preso in carico» (ritiro diretto) oppure «In transito» (dopo consegna in Pickup Shop) |
| `We have your parcel and it's on its way to you` | Il pacco viaggia nella rete DPD UK verso la tua zona. | «In transito» |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | Arrivato in un deposito DPD UK per lo smistamento. | «In transito» |
| `Your parcel is at our depot ready for delivery` | Al deposito, pronto per il giro di consegna; in seguito la stessa riga torna a leggersi `Your parcel is at our depot`. | «In transito» |
| `A notification has been sent to advise you when the delivery will take place` | DPD UK ti ha inviato l'SMS o l'email con il giorno o la fascia di consegna. | Resta valido lo stato precedente |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Hai scelto un nuovo giorno di consegna (a volte con un orario); data e ora variano. | Resta valido lo stato precedente |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | In consegna oggi, con la fascia di un'ora quando comunicata (gli orari variano). | «In consegna» |
| `As requested, your parcel will now be left in your selected safe place` | Tu (o le preferenze salvate nell'app DPD) avete indicato un luogo sicuro dove lasciare il pacco. | Resta valido lo stato precedente |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | Consegna non riuscita; nuovo passaggio nel giorno indicato. Peek rimuove il nome dell'autista presente nella frase di DPD UK. | «Tentativo di consegna» |
| `Your parcel has been delivered` | Consegnato (l'intestazione sul sito DPD UK aggiunge ora e data). | «Consegnato» |
| `Your parcel is waiting for you at home (left in safe place)` | Consegnato e lasciato nel luogo sicuro scelto. | «Consegnato» |
| `Your delivery has been cancelled` | Il mittente ha annullato la spedizione; DPD UK invita a contattare il negozio indicato. | «Problema segnalato» |
| `We have your parcel and it's on its way back to the sender` | Reso in viaggio verso il mittente (salvo prima del deposito su un'etichetta consegnata in un Pickup Shop, diretta al titolare dell'account che l'ha creata: «In transito»). | «Problema segnalato» |

`We have X-rayed your parcel as part of our normal security procedures` è un controllo radiogeno di routine che non richiede azioni («In transito» su Peek), mentre `There has been a delay with your delivery` è un avviso di ritardo che mantiene la fase precedente. Su un pacco spedito da DPD UK fuori dal **Regno Unito**, `Your parcel has left our sortation facility and is on it's onwards journey` (con la grafia `it's` di DPD UK) segnala la partenza dal centro britannico, seguita dagli stati in inglese del partner estero: `The parcel is in transit to its final destination` («In transito»), `The parcel is undergoing customs clearance` e `Duties and Taxes paid` («In dogana»), `The parcel has now cleared customs` e `The parcel has arrived at the delivery depot` («In transito»), `The parcel is on the vehicle for delivery` («In consegna») e `The parcel has been delivered` («Consegnato»). Approfondisci in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Opzioni Predict, negozi Pickup nel Regno Unito e dogana per Italia e Svizzera

Le regole su Predict, consegna al vicino e punti Pickup valgono per i recapiti effettuati nel **Regno Unito**:

**Fascia Predict.** Nel Regno Unito, DPD UK invia via SMS ed email una finestra di consegna di un'ora il giorno stesso del recapito, non appena l'autista ha caricato e scansionato i colli sul furgone — di solito entro mezzogiorno (*usually by 12 noon*). Una mappa in tempo reale restringe poi l'arrivo a uno slot di 15 minuti, e SMS ed email indicano il nome dell'autista e la tua posizione nella coda di consegna. Non è possibile cambiare l'orario della fascia in sé.

**Modificare la consegna.** La sera prima e il giorno della consegna, nel Regno Unito puoi usare il link di SMS/email o l'app DPD per scegliere un'altra data, un vicino di fiducia, un luogo sicuro (*safe place*) o un DPD Pickup Shop, oppure acquistare un upgrade per la consegna entro le 10:30, entro le 12:00, al pomeriggio, di sabato o di domenica. Se il mittente ha richiesto la firma, alcune scelte non sono attive. Per spedire a [un indirizzo diverso](guide:change-delivery-address) devi contattare il venditore.

**Assenza al passaggio.** Nel Regno Unito DPD UK prova automaticamente a consegnare a un vicino: le condizioni generali di trasporto definiscono *Neighbour* chi abita o lavora entro 50 metri a piedi dall'indirizzo di consegna (*within 50 metres walking distance of the Delivery Address*). L'autista non può ripassare in giornata. Inserendo numero di riferimento e codice postale su `track.dpd.co.uk` vedi dove è stato lasciato il biglietto *sorry we missed you* e puoi fissare una nuova data, un luogo sicuro, un vicino o il ritiro in un negozio Pickup; DPD UK non dichiara un numero fisso di tentativi. Se risulta consegnato ma non trovi nulla, controlla prima il luogo sicuro e i vicini vicini, poi consulta [consegnato ma non ricevuto](guide:delivered-not-received) e [mancata consegna](guide:missed-delivery).

**DPD Pickup Shop e locker nel Regno Unito.** DPD UK conta oltre 6.000 punti Pickup nel Regno Unito (tra cui filiali Sainsbury's, Post Office, Co-op, Morrisons, Argos e Matalan). Un pacco deviato lì è di norma pronto il giorno successivo. Per il ritiro servono due documenti (*2 pieces of ID*): un documento d'identità con foto e una prova di indirizzo come una bolletta, oltre al *DPD Pickup Pass* mostrato sul tracciamento. Le pagine di assistenza DPD UK non pubblicano un numero fisso di giorni di giacenza nei Pickup Shop; l'app DPD consente anche il recapito in armadietto (*Locker*).

**Oneri d'importazione nel Regno Unito.** I pacchi in arrivo in Gran Bretagna dall'estero possono essere soggetti a IVA britannica (salvo regali fino a 39 £) e dazi, addebitati dal corriere (pagabili online su `track.dpd.co.uk` alla voce *import charges*); secondo GOV.UK, il corriere trattiene normalmente il pacco per circa 3 settimane (*about 3 weeks*) prima di restituirlo al mittente se non pagato.

**Pacchi spediti dal Regno Unito verso l'Italia, l'UE o la Svizzera.** Poiché la Gran Bretagna non fa parte dell'UE, le spedizioni da lì dirette in **Italia** o in **Svizzera** passano in dogana all'arrivo; l'Irlanda del Nord invece è trattata come territorio dell'UE per la dogana e per l'IVA sulle merci, secondo la Commissione europea. Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € scontano un dazio doganale UE di 3 € per articolo (*per item*), e dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo a prescindere dal valore (un articolo raggruppa le merci dello stesso pacco aventi medesima classificazione tariffaria, descrizione e, ove richiesta, origine; oltre 150 € si applicano i dazi ordinari), oltre all'IVA all'importazione (secondo l'ambito territoriale pubblicato dalla Commissione europea, **Livigno** è fuori sia dal territorio doganale sia dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** rientrano nel territorio doganale UE ma non nell'area IVA UE). In **Svizzera**, secondo l'Ufficio federale della dogana e della sicurezza dei confini (UDSC), l'IVA sull'importazione è dell'8,1 % (2,6 % aliquota ridotta, ad esempio per libri e generi alimentari), ma per economia amministrativa «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi», al netto di eventuali spese di sdoganamento del vettore. Dettagli in [pacco fermo in dogana](guide:customs).

## Pacco DPD UK in ritardo, smarrito, danneggiato o SMS truffa

**Ritardi.** Le condizioni generali di DPD UK precisano che i tempi di consegna comunicati sono solo stime (*estimates only*). Per le consegne nel **Regno Unito**, la pagina [Service Disruption di DPD UK](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) elenca i codici postali britannici interessati da disservizi. Se non compaiono scansioni per giorni, leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating) e contatta il negozio.

**Pacco perso o danneggiato.** Il contratto di trasporto con DPD UK fa capo al mittente (*Customer*), che deve presentare il reclamo economico. In base alle condizioni di DPD UK, il mittente deve segnalare smarrimento o danni entro 14 giorni dalla spedizione (*within 14 days of the date of despatch*), inviare le prove richieste entro 28 giorni e presentare il reclamo definitivo entro un anno dalla data di spedizione (*within one year from the date of despatch*). Conserva imballaggio e foto e scrivi al venditore: vedi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

**Restituzione al mittente.** Secondo la *Return, Disposal and Destruction of Goods Policy* di DPD UK, se una spedizione non può essere consegnata e resta nei magazzini DPD UK, il corriere si riserva il diritto di rispedirla al mittente dopo almeno 5 giorni di calendario (*after a minimum of 5 calendar days*); la merce non restituibile può essere smaltita o distrutta dopo 4 settimane (12 settimane per gli effetti personali). Vedi [pacco restituito al mittente](guide:returned-to-sender).

> DPD UK dichiara di non chiedere mai il pagamento di una tariffa di riconsegna (*redelivery fee*) tramite link in email, SMS o iMessage. Nel Regno Unito gli avvisi automatici arrivano via SMS (mai tramite iMessage) con mittente `DPD UK`, `DPD Local` o `DPDgroup UK` — mai da un indirizzo email o da un numero mobile qualunque —, le email autentiche terminano con `@dpd.co.uk`, `@dpdlocal.co.uk`, `@dpdgroup.co.uk` o `@dpd.uk` e i link ufficiali usano esclusivamente i suoi siti: `www.dpd.co.uk`, `www.dpdlocal.co.uk` o `www.dpdgroup.co.uk`, e `track.dpd.co.uk` per seguire un pacco. Nel **Regno Unito** puoi inoltrare gli SMS sospetti al `7726` e segnalare le email di phishing a `report@phishing.gov.uk`; leggi anche [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Assistenza clienti e contatti DPD UK

Recapiti ufficiali di DPD UK nel **Regno Unito**:

- **Telefono:** `0121 275 0500` (numero britannico), dal lunedì al venerdì 8:00–18:30, sabato 8:00–16:00, domenica 9:00–15:00 (ora del Regno Unito), esclusi i giorni festivi britannici (*bank holidays*). La pagina contatti stima 5–10 minuti di attesa (la *Complaints Charter* indica l'obiettivo di risposta entro 2 minuti e 30 secondi). Tieni pronti numero del pacco e codice postale di consegna.
- **Chat online:** sulla [pagina Contact Us di DPD UK](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) inserendo numero di riferimento e codice postale di consegna; la pagina indica disponibilità `24/7`, mentre la *Complaints Charter* di DPD UK riporta gli stessi orari del telefono (lun.–ven. 8:00–18:30, sab. 8:00–16:00, dom. 9:00–15:00).
- **App DPD:** pulsanti di chat e chiamata sotto la mappa di tracciamento; DPD UK segnala che chiamando dall'app si passa in cima alla coda.
- **Posta e tempi di reclamo:** `Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY`, Regno Unito. Nella *Complaints Charter* DPD UK punta a rispondere entro 5 giorni lavorativi (fino a 28 giorni per i reclami più complessi).

## Tracciare i pacchi DPD UK con Peek

[Peek](/) riconosce un codice DPD a 14 cifre (con o senza il 15° carattere di controllo), ma poiché il formato è condiviso interroga i corrieri compatibili per verificare chi ha in carico il pacco: **prima DPD UK** se il numero inizia con `1550` o `1597`, altrimenti prima i corrieri a 14 cifre attivi nel tuo Paese. Quando DPD UK conferma la spedizione, compare «ha questo pacco» e Peek la segue lì. Se leggi «non ancora trovato · Peek continua a controllare» o un altro corriere, premi «Cambia» (o «Cambia corriere») e scegli **DPD UK**. Incollare un link `track.dpd.co.uk` o `tracking.dpd.co.uk` con `reference=` o `parcelNumber=`, oppure `track.dpdlocal.co.uk` con `reference=`, imposta subito DPD UK. Se un codice salvato sotto DPD UK resta sconosciuto a DPD UK e ai servizi di tracciamento, Peek mostra all'inizio «In attesa di DPD UK» e, dopo due giorni, «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.»

Le scansioni della rete DPD UK non riportano fuso orario: Peek le legge sull'ora britannica (`Europe/London`) e le converte nel fuso del tuo dispositivo. Su peektracker.com e nelle app iPhone e Android, in fondo alla cronologia compare «Orari nel tuo fuso orario: …». Le descrizioni restano nell'inglese originale di DPD UK, omettendo il nome dell'autista nei tentativi falliti e il nome del ricevente o firmatario dopo `delivered to …` o `signed for by …`.

Peek non mostra città o deposito per le singole scansioni di DPD UK, e non legge la data stimata né la finestra Predict di un'ora; scarta codici interni, riferimenti commerciali, dati del destinatario e foto di consegna. Peek mostra l'account che ha prenotato la spedizione come «Mittente: …», **tranne** quando la cronologia mostra che il pacco è stato consegnato in un DPD Pickup Shop: su quelle etichette (come i resi negozio) il titolare dell'account non è chi spedisce e Peek nasconde il campo. Sulle spedizioni internazionali Peek resta collegato alla cronologia di DPD UK senza cambio automatico di corriere: se il venditore ti fornisce un codice locale distinto, aggiungilo come pacco separato su Peek.

Finché tieni aperta la pagina del pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla gli aggiornamenti fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 (ora britannica) nel giorno in cui DPD UK segna il pacco «In consegna», finché risponde il tracker diretto di DPD UK (tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo una giornata senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive). Negli altri casi, così come dopo due giorni senza aggiornamenti (o 12 ore su «Etichetta creata» o «Preso in carico»), Peek verifica ogni ora. Puoi scegliere le notifiche per «Ogni scansione», «Passaggi importanti» oppure «Solo la consegna» / «Solo giorno di consegna». Per cambiare le opzioni di recapito nel Regno Unito usa il sito o l'app di DPD UK: Peek non può modificare la consegna né contattare DPD UK al posto tuo.

## Domande frequenti sul tracciamento DPD UK

### DPD UK fa parte di Royal Mail?

No. DPD UK è gestita da `DPDgroup UK Ltd` e fa parte di Geopost, la divisione internazionale pacchi del gruppo francese [La Poste](carrier:la-poste) (*La Poste Groupe*).

### Quanto impiega DPD UK a consegnare nel Regno Unito?

Dipende dal servizio scelto dal negozio: nel Regno Unito `DPD NEXT DAY` prevede la consegna entro fine giornata lavorativa successiva (con opzioni entro le 10:30, entro le 12:00, sabato e domenica). Le Isole del Canale, l'Isola di Man e le Isole Scilly richiedono due giorni come standard, mentre l'Irlanda del Nord è servita il giorno dopo per via aerea o in due giorni via terra e mare.

### Come vedo dove si trova l'autista DPD UK?

Per le consegne nel Regno Unito con Predict, l'SMS o l'email inviati la mattina del recapito (di solito entro mezzogiorno) indicano il nome dell'autista e la tua posizione nella coda, e una mappa in tempo reale restringe poi la finestra a 15 minuti.

### Per quanti giorni DPD UK tiene un pacco in un Pickup Shop?

Le pagine di assistenza di DPD UK non indicano un periodo fisso uguale per tutti i Pickup Shop britannici; controlla l'avviso di ritiro o il *DPD Pickup Pass* su `track.dpd.co.uk`. In generale, secondo la propria politica sui resi, DPD UK può rispedire al mittente i colli non consegnati custoditi nei suoi locali dopo almeno 5 giorni di calendario.

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – numero e avviso, fascia entro mezzogiorno, vicino, oltre 6.000 Pickup Shop e documenti
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – telefono 0121 275 0500, orari, attesa, chat e chiamata dall'app
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – fascia di un'ora, mappa a 15 min, autista e opzioni
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – ricerca con riferimento o tagliando e codice postale, Pickup Pass e oneri doganali
- [DPD UK: Cyber Security](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – mittenti SMS, nessuna tariffa via link, domini, 7726 e report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – deviazione verso DPD Pickup e resi
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – preferenze e armadietto (Locker)
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – spedizioni DPD UK e DPD Local nell'app
- [DPD UK: Standard Terms & Conditions of Carriage (PDF)](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – vicino entro 50 m, tempi indicativi e reclami (14 gg, 28 gg, un anno)
- [DPD UK: Complaints Charter (PDF)](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – orari, indirizzo di Smethwick e risposta in 5–28 giorni
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – reso dopo 5 giorni di calendario e custodia 4 o 12 settimane
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day, 10:30, 12:00, weekend, isole e Irlanda del Nord
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – codici postali britannici interessati
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – oneri d'importazione e giacenza di circa 3 settimane
- [Geopost: About us](https://www.geopost.com/en/about-us/) – La Poste Groupe
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € e commissione di 2 €
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcolo per articolo
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia, Lago di Lugano e Irlanda del Nord
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera e soglia di 5 franchi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numeri, riconoscimento, stati, fuso orario e mittente
:::
