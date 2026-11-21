---
title: Tracciamento GlobalPost: numeri GP, stati e consegna
description: Traccia un pacco GlobalPost: formato dei codici GP e SDC, significato degli stati in inglese, servizi, dogana in Italia e Svizzera e uso di Peek.
slug: tracciamento-globalpost
published: 2026-11-21
updated: 2026-11-21
---

GlobalPost, parte del gruppo Auctane, è un servizio di spedizione internazionale acquistato dai venditori online tramite piattaforme come ShipStation e Stamps.com (oltre 220 Paesi raggiunti e più di 60 milioni di spedizioni consegnate secondo il sito di GlobalPost). Il pacco raggiunge prima uno dei centri di spedizione GlobalPost, dove riceve l'etichetta internazionale e la documentazione doganale, poi partner postali e commerciali lo trasportano all'estero fino alle poste o al corriere che consegna nel Paese d'arrivo. Il venditore ti comunica il numero, che di solito inizia con `GP`: incollalo nel riquadro qui sopra. Il tracker ufficiale è su `goglobalpost.com/tracking`; nel catalogo di Peek trovi GlobalPost anche cercando `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` o `GlobalPost UK`.

## Numero di tracciamento GlobalPost: formato ed esempi

Peek riconosce i codici attuali di GlobalPost dalla forma: `GP`, quattro lettere per il servizio, dieci cifre, poi `US` o `GB` per il Paese di partenza. Con lettere e cifre inventate:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `GPECON4829105736US` | `GP`, quattro lettere, dieci cifre e `US`: un pacco GlobalPost spedito dagli Stati Uniti. |
| `GPSTND4829105736GB` | Lo stesso formato che termina in `GB`: spedito dal Regno Unito. |
| `SDC482910573649182` | `SDC` e 15 cifre: una vecchia etichetta GlobalPost. Dal solo numero Peek non assegna un corriere: scegli GlobalPost con «Cambia». |
| `GPECON482910573US` | Solo nove cifre: Peek non riconosce alcun corriere. Controlla se manca una cifra. |
| `LZ482910576US` | Un codice postale statunitense, non di GlobalPost: Peek indica [USPS](carrier:usps). Se selezioni GlobalPost a mano per questo numero, Peek avvisa che «non emette numeri come questo». |

Spazi, trattini e lettere minuscole non contano quando incolli il numero in Peek.

**Dove trovarlo.** Nell'email di conferma spedizione del venditore o nella pagina dell'ordine. Se non lo trovi, leggi [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

**Un secondo codice.** Sotto `Final Mile Tracking By:`, il tracker di GlobalPost può riportare il codice assegnato dal corriere finale e apre la spedizione anche cercando quel numero (mentre Peek interroga GlobalPost solo con codici `GP` o `SDC`).

## Stati del tracciamento GlobalPost spiegati

Il tracker di GlobalPost elenca le scansioni dalla più recente alla più vecchia sotto intestazioni giornaliere, con ora, dicitura in inglese e quasi sempre località, riscrivendo con parole proprie gli aggiornamenti dei partner. Nell'ordine consueto:

| GlobalPost scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Label Created` | Il venditore ha creato l'etichetta; nulla si è ancora mosso. | «Etichetta creata» |
| `Data Received` | GlobalPost ha ricevuto i dati dal venditore, ma non ancora il pacco. | «Etichetta creata» |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Un partner ha scansionato il collo: USPS verso GlobalPost o le poste nel Paese di destinazione. | «In transito» |
| `Arrived at GlobalPost Facility` | Un centro di spedizione GlobalPost ha preso in carico il pacco (possono precederlo scansioni USPS in ingresso). | «Preso in carico» |
| `Departed GlobalPost Facility` | Il pacco ha lasciato quel centro GlobalPost. | «In transito» |
| `Departed from Origin Country` | È partito dagli Stati Uniti o dal Regno Unito. | «In transito» |
| `In Transit`, `In-Transit` | In viaggio. | «In transito» |
| `Arrived in Destination Country` | È arrivato nel Paese di destinazione. | «In transito» |
| `In Customs` | In controllo doganale d'importazione. | «In dogana» |
| `Cleared Customs` | Svincolato dalla dogana; riprende il viaggio. | «In transito» |
| `Out for Delivery` | In uscita per la consegna odierna. | «In consegna» |
| `Attempted Delivery` | Tentativo di recapito non riuscito. | «Tentativo di consegna» |
| `Exception, No Action Required` | Imprevisto sulla consegna nel Paese d'arrivo che non richiede azioni da parte tua. | «Problema segnalato» |
| `Delayed, No Action Required` | Ritardo segnalato senza azioni richieste dal destinatario. | «Problema segnalato» |
| `Delivered` | Consegnato (oppure restituito al mittente: vedi sotto). | «Consegnato» |

Una riga può chiudersi con la nota di GlobalPost secondo cui gli aggiornamenti richiedono tempo: Peek valuta lo stato che la precede mantenendo l'intero testo, e una registrazione elencata dopo la partenza non fa arretrare lo stato. Le altre voci passano per le regole condivise di Peek.

**Resi.** Il tracker di GlobalPost chiude anche un reso al mittente con `Delivered`, indicando nell'intestazione la città del destinatario mentre la scansione riporta la città del mittente. Quando il luogo della scansione coincide con la città del mittente in un Paese e una città diversi da quelli del destinatario, Peek mostra «Restituito al mittente».

## Chi consegna i pacchi GlobalPost in Italia e in Svizzera

GlobalPost affida i pacchi *Economy* e *Standard* a un operatore postale (come USPS dagli Stati Uniti) per la tratta internazionale. Le sue pagine non elencano i corrieri per singolo Paese, ma il tracker può indicarne nome e codice.

**Ciò che mostra il tracciamento dipende dal servizio** scelto dal venditore:

| Servizio | Tracciamento | Dazi e imposte | Stima indicata al venditore |
| --- | --- | --- | --- |
| `Economy` | fino all'ingresso nel Paese e allo sdoganamento | paghi tu (`DDU`) | `10 – 14 business days` |
| `Standard` | fino a domicilio | paghi tu (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | fino a domicilio | paga il venditore (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | fino a domicilio | inclusi nel prezzo del venditore | `6 – 10 business days` |

Dagli Stati Uniti, *GlobalPost Plus – Duties & Taxes Included* è disponibile verso l'**Italia** (oltre a Canada e altri Paesi europei), mentre *Flat Rate (formerly Go)* copre Canada, Australia, Nuova Zelanda, Hong Kong e Messico. Tentativi di recapito e giacenza seguono le regole dell'operatore finale: consulta [Poste Italiane](carrier:poste-italiane), [Posta Svizzera](carrier:swiss-post), [mancata consegna](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

**Dogana in Italia, nell'UE e in Svizzera.** Una spedizione proveniente dagli Stati Uniti o dalla Gran Bretagna passa in dogana all'ingresso nell'UE o in Svizzera (l'**Irlanda del Nord**, invece, è trattata come territorio dell'UE ai fini doganali e dell'IVA sui beni secondo la Commissione europea). Con *Economy* e *Standard* (*Delivered Duties Unpaid*), dazi e tasse sono a carico dell'acquirente. Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore intrinseco fino a 150 € pagano un dazio doganale UE di 3 € per articolo (*per item*), e dal 1° novembre 2026 si aggiunge una commissione di gestione dell'Unione (*Union handling fee*) di 2 € per articolo indipendentemente dal valore (un articolo riunisce le merci dello stesso pacco con uguale classificazione tariffaria, descrizione e, se richiesta, origine; oltre 150 € valgono i dazi ordinari), più l'IVA all'importazione in **Italia**, se non è già stata pagata all'acquisto tramite IOSS (secondo l'ambito territoriale della Commissione europea, **Livigno** è fuori sia dal territorio doganale sia dall'area IVA UE, mentre **Campione d'Italia** e le acque italiane del **Lago di Lugano** fanno parte del territorio doganale UE ma non dell'area IVA UE). In **Svizzera**, secondo l'UDSC, l'IVA sull'importazione è dell'8,1 % (2,6 % aliquota ridotta), ma «i tributi doganali fino a 5 franchi non vengono riscossi» e «Gli importi d'imposta fino a 5 franchi non vengono riscossi», escluse eventuali spese del corriere. Le pagine di GlobalPost non specificano come venga addebitata la commissione di 2 €: chiedi al venditore se era compresa nel prezzo e leggi [pacco fermo in dogana](guide:customs).

## Pacco GlobalPost fermo, smarrito o restituito

**«In attesa di GlobalPost».** Finché il tracker reindirizza segnalando di non trovare dettagli per il numero inserito (`status=not_found`), Peek mostra «In attesa di GlobalPost» e continua a controllare. Se dopo due giorni GlobalPost non riconosce ancora il codice e nessun servizio di tracciamento lo trova, Peek mostra: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» Con il solo `Label Created` per più giorni, chiedi al venditore se ha spedito il collo.

**Nessun aggiornamento per giorni.** GlobalPost avvisa che sulle spedizioni internazionali gli aggiornamenti possono richiedere tempo (meteo, festività, scioperi, dogana) e che con *Economy* il tracking termina dopo la dogana nel Paese d'arrivo. Dopo quattro giorni senza nuove scansioni, Peek mostra il giorno dell'ultima scansione («Nessun aggiornamento da lun 12 ott») ricordando le pause tipiche su voli lunghi, in dogana o nel fine settimana; dopo tre settimane senza novità consiglia invece di contattare il venditore. Non c'è un link d'assistenza GlobalPost; appena Peek segue il pacco presso il corriere finale e ne conosce la pagina d'aiuto, mostra quella. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Non arrivato dopo 30 giorni.** Se il pacco non è arrivato entro 30 giorni dalla spedizione, GlobalPost invita a contattare il venditore: il venditore può aprire un reclamo presso GlobalPost entro `90 days` dalla data di spedizione, con tempi di gestione fino a `3 weeks`. Leggi [pacco smarrito](guide:lost-parcel).

**Segnato consegnato ma assente, o non recapitabile / reso.** Se risulta consegnato ma non c'è, chiedi prima al vettore indicato nel tracking e poi al venditore ([consegnato ma non ricevuto](guide:delivered-not-received)). Per indirizzo errato o incompleto, dazi o tasse non saldati, avvisi doganali ignorati o articoli soggetti a restrizioni, le poste di destinazione gestiscono il collo (di solito lo rispediscono al mittente o lo tengono in giacenza) senza fornire a GlobalPost una data o un nuovo codice di ritorno; i termini di GlobalPost escludono i pacchi non recapitabili (*undeliverable*) dai rimborsi assicurativi, quindi rivolgiti al negozio ([pacco restituito al mittente](guide:returned-to-sender)).

## Assistenza clienti e contatti GlobalPost

Nella pagina *Contact Support*, GlobalPost mette a disposizione questi recapiti:

- **Email**: `info@goglobalpost.com`.
- **Telefono**: `(888) 899-1255`, dal lunedì al venerdì dalle `6am` alle `6pm` ora del Pacifico (*Pacific Time*).
- **Modulo online**: su `www.goglobalpost.com/contact-support/`, seleziona `Recipient` e inserisci il numero di tracciamento (GlobalPost precisa che l'assistenza visualizza le stesse informazioni mostrate dalla pagina di tracking).

Per un pacco non arrivato, GlobalPost rimanda anzitutto al venditore che ha prenotato la spedizione. Precisa inoltre che un pacco spedito dalla Cina con [China Post](carrier:china-post) non è una spedizione GlobalPost: chiedi al mittente.

**Attenzione agli SMS truffa.** Il dominio ufficiale di GlobalPost è `www.goglobalpost.com` (`goglobalpost.com`). Prima di aprire link sospetti ricevuti via SMS o email, consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco GlobalPost con Peek

[Peek](/) riconosce all'istante un codice `GP` («Corriere rilevato») e interroga il tracker di GlobalPost senza account né CAP (il sito mantiene in cache per qualche minuto le schede trovate). Funzionano anche i link `goglobalpost.com/track-detail/?t=` o `/track/?t=`; per un vecchio codice `SDC`, imposta GlobalPost con «Cambia». Se hai solo il numero del corriere finale, incollalo comunque: Peek segue oltre 4.500 corrieri.

Peek mostra lo stato, ogni scansione con la relativa località (tagliando i codici postali), il Paese di destinazione e il corriere finale con il suo numero quando GlobalPost li riporta. Esclude la barra d'avanzamento, le città del mittente e del destinatario nell'intestazione (lette solo per riconoscere un reso) e i CAP; GlobalPost non fornisce data di consegna stimata, peso o prova di consegna. «Apri il sito GlobalPost» apre il tuo codice su `goglobalpost.com`.

Gli orari di GlobalPost sono privi di fuso: Peek legge ogni scansione sull'orologio del suo luogo, o della tratta in cui si trovava il pacco, e la mostra nel fuso del tuo dispositivo; su `peektracker.com` come nelle app iPhone e Android, la cronologia si chiude con «Orari nel tuo fuso orario: …».

Quando GlobalPost nomina un corriere finale riconosciuto da Peek con quel nome, la scheda mostra «Consegna con …» ed elenca il suo numero dopo il codice `GP`, oppure «Annunciato a …, non ancora ricevuto» finché quel vettore ha solo il preavviso senza scansioni proprie. Con un secondo numero ma nessun corriere noto per nome, per un codice postale valido Peek interroga le poste del Paese di destinazione ([Poste Italiane](carrier:poste-italiane) in Italia, [Posta Svizzera](carrier:swiss-post) in Svizzera) o l'unico corriere individuato dal formato del codice. Non appena compare una scansione almeno recente quanto l'ultima di GlobalPost, Peek passa a seguirlo lì conservando la cronologia di GlobalPost.

Finché tieni aperto il pacco o, nel giorno di consegna, hai le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti (ogni 5 minuti quando deve aprire il tracker di GlobalPost nel browser) dalle 6:00 locali nel giorno in cui passa «In consegna» finché risponde il tracker di GlobalPost (dopo il passaggio al corriere finale, Peek ne segue la cadenza; tramite i soli servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno fermo e fino a ogni 15 minuti il giorno di consegna a pacco aperto o con notifiche attive) —; negli altri casi, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi scegliere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare GlobalPost né il venditore per te.

## Domande frequenti sul tracciamento GlobalPost

### Perché il tracciamento GlobalPost si è fermato dopo la dogana?

Con *GlobalPost Economy* il tracciamento si interrompe una volta che il pacco raggiunge il Paese di destinazione e supera la dogana, e GlobalPost segnala che alcune spedizioni hanno tracciamento limitato a destinazione. Il pacco è comunque in viaggio; se sotto `Final Mile Tracking By:` compare il codice del corriere locale, Peek interroga quel vettore e puoi farlo anche tu.

### Chi consegna i pacchi GlobalPost?

Le poste o un corriere del Paese di arrivo, che il tracker di GlobalPost può indicare insieme al relativo numero di spedizione. Quando è presente un codice postale internazionale senza nome del partner riconosciuto, Peek interroga l'operatore postale di destinazione (Poste Italiane in Italia, Posta Svizzera in Svizzera).

### Quanto tempo impiega GlobalPost a consegnare?

Le FAQ indicano `5-6 days` per i Paesi vicini e fino a `20 days` per quelli più lontani; ai venditori GlobalPost stima `10 – 14 business days` per *Economy*, `6 – 10 business days` per *Standard* e *Flat Rate* e `5 – 8 business days` per *Plus* dal momento in cui il suo centro lavora il pacco.

### GlobalPost è affidabile?

Sì. GlobalPost fa parte di Auctane, è venduto tramite piattaforme come ShipStation e Stamps.com, spedisce in oltre 220 Paesi e dichiara più di 60 milioni di spedizioni consegnate.

### Come posso contattare GlobalPost?

Scrivi a `info@goglobalpost.com`, chiama `(888) 899-1255` nei giorni feriali dalle `6am` alle `6pm` ora del Pacifico oppure usa il modulo su `goglobalpost.com/contact-support/` scegliendo `Recipient`. Se il pacco non è arrivato 30 giorni dopo la spedizione, GlobalPost chiede di rivolgersi prima al venditore.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, funzionamento, Paesi di Plus – Duties & Taxes Included e Flat Rate, tracking Economy fino alla dogana, tempi, resi e reclami entro 90 giorni / 3 settimane
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – confronto tra Economy, Standard, Plus e Flat Rate: tracking, oneri DDU/DDP e giorni lavorativi
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – email, telefono, orari Pacific Time, opzione Recipient nel modulo e nota su China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – oltre 220 Paesi e più di 60 milioni di spedizioni consegnate
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – tracker ufficiale e avviso di numero non trovato
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – centri di spedizione, tempi come stima ed esclusione dei pacchi non recapitabili
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: Orientamenti sul dazio di 3 € e sulla commissione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – importo fisso di 2 € a prescindere dal valore, definizione di articolo ed esenzione IVA tramite IOSS
- [Commissione europea: Ambito territoriale IVA e dogane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Livigno, Campione d'Italia, Lago di Lugano e Irlanda del Nord
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e non riscossione fino a 5 franchi
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formati GP e SDC, stati, riconoscimento dei resi, orologi, cache e corriere finale
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato USPS e passaggio al corriere finale o alle poste nazionali
:::
