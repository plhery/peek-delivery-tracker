---
title: Tracciamento Stone3PL: codici, stati e contatti
description: Traccia un pacco Stone3PL dalla Cina: com’è fatto il codice S, cosa significa ogni riga di tracciamento, chi consegna nel tuo paese e come contattare.
slug: tracciamento-stone3pl
published: 2026-11-22
updated: 2026-11-22
---

Stone3PL è una società logistica con sede a Shenzhen, in Cina, parte dello Stone Group. Spedisce merci per negozi online e aziende dalla Cina in tutto il mondo insieme ad altri operatori logistici. Se un negozio ha spedito il tuo ordine con Stone3PL, controlla il codice sulla [pagina di tracciamento di Stone3PL](https://stone3pl.com/?route=services/track) (in inglese) o incollalo nella casella qui sopra. Il codice arriva dal venditore con i dettagli di spedizione dell’ordine.

## Numero di tracciamento Stone3PL: com’è fatto

Stone3PL non pubblica un formato ufficiale. Peek collega un solo formato a Stone3PL: la lettera `S` seguita da 18 cifre. Entrambi gli esempi qui sotto sono inventati:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `S482917365428193642` | `S` e 18 cifre. Peek interroga Stone3PL per i codici di questa forma. La forma da sola non prova che il pacco sia presso Stone3PL. |
| `S48291736542819364` | Solo 17 cifre: non è la forma che Peek associa a Stone3PL. Controlla se manca una cifra, oppure incollalo comunque. |

In Peek minuscole, spazi e trattini non contano. Il tracker di Stone3PL accetta fino a 40 codici alla volta, uno per riga; mantiene solo lettere, cifre e trattini e trasforma le minuscole in maiuscole.

**Dove trovarlo.** Nell’e-mail di spedizione del venditore o nella pagina dell’ordine. Se non c’è, leggi [come trovare il numero di tracciamento](guide:find-tracking-number).

**Altri codici.** La pagina di tracciamento di Stone3PL elenca le aziende che segue, come DHL Express, UPS, USPS, China Post e China EMS (ePacket). Il risultato può inoltre indicare il corriere che consegna nel tuo paese insieme al suo codice, tracciabile direttamente sul sito di quel corriere.

## Stati di tracciamento Stone3PL spiegati

Le righe di tracciamento di Stone3PL sono in inglese, con data e ora ma senza luogo. La pagina dei risultati mostra prima le righe del paese di destinazione e poi quelle dell’inizio del viaggio. Queste sono le righe riconosciute da Peek:

| Stone3PL scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Order Submited.` | Il venditore ha inviato l’ordine a Stone3PL. Stone3PL potrebbe non avere ancora il pacco. La grafia è quella di Stone3PL. | «Etichetta creata» |
| `Shipment information received` | Stone3PL ha ricevuto i dati del pacco. | «Etichetta creata» |
| `Processing information input` | Stone3PL sta registrando i dati del pacco. | «Etichetta creata», o «In transito» dopo `Pickup Scan` |
| `Pickup Scan` | Il pacco è stato ritirato e scansionato. Ora è in viaggio. | «Preso in carico» |
| `facility - Outbound` | Il pacco ha lasciato un magazzino o centro di smistamento. | «In transito» |
| `Arrived at domestic terminal station` | Arrivato a un terminal. La riga non indica in quale paese. | «In transito» |
| `Port of departure` | Si trova al porto o aeroporto di partenza, prima di partire. | «In transito» |
| `International shipment release - Export` | Sdoganato all’esportazione, prosegue il viaggio. | «In transito» |
| `Port of departure - Departure` | Ha lasciato il porto o aeroporto di partenza. | «In transito» |
| `Port of destination - Arrival` | Arrivato in un porto o aeroporto del paese di destinazione. | «In transito» |
| `International shipment release - Import` | Sdoganato all’importazione, prosegue il tragitto. | «In transito» |
| `Pick up by local carrier at destination port` | Il corriere che consegna nel tuo paese lo ha ritirato al porto. Non devi andare a ritirare nulla. | «In transito» |
| `Batch delivery to carrier` | Stone3PL lo ha consegnato, in lotto con altri pacchi, al corriere finale. | «In transito» |

Le righe del paese di destinazione possono usare parole diverse. Peek riconosce termini comuni come consegna o dogana. Una riga che Peek non riconosce resta nella cronologia e lo stato rimane sull’ultimo passaggio compreso.

**Stato complessivo.** Il tracker di Stone3PL assegna anche uno stato generale a ogni pacco. Quando indica pronto per il ritiro, consegnato o un problema, Peek mostra «Pronto per il ritiro», «Consegnato» o «Problema segnalato», anche prima che una singola riga lo riporti.

**Orari.** Stone3PL fornisce orari privi di fuso orario. Peek data le righe dell’inizio del viaggio sull’ora cinese (UTC+8), quelle del paese di destinazione e `International shipment release - Import` sull’ora di quel paese (su uno solo dei suoi fusi se ne ha diversi), e mostra gli orari nel fuso del tuo dispositivo («Orari nel tuo fuso orario:»).

## Chi consegna i pacchi Stone3PL

Stone3PL dichiara di operare in collaborazione con partner logistici globali affermati e dispone di magazzini in NORAM, APAC ed EMEA (Nord America, Asia-Pacifico ed Europa, Medio Oriente e Africa).

Le sue pagine non indicano quale azienda consegni in ciascun paese: cerca il corriere finale e il suo numero nella pagina dei risultati di Stone3PL. Tentativi di consegna, avvisi e punti di ritiro seguono le regole di quel corriere. Vedi [consegne mancate](guide:missed-delivery).

**Dazi e dogana.** Stone3PL propone ai venditori spedizioni DDP (Delivered Duty Paid) con sdoganamento DDP. Solo il venditore può confermarti se l’ordine è stato spedito così.

**Nell’UE (in Irlanda, ad esempio).** Gli acquisti online provenienti da fuori UE prevedono un dazio doganale di 3 € per articolo sui pacchi fino a 150 €, dal 1° luglio 2026. Dal 1° novembre 2026 è dovuta inoltre una tariffa unionale di gestione di 2 € per articolo, indipendentemente dal valore. Un articolo è l’insieme di merci con stessa classificazione tariffaria, descrizione e, ove richiesta, origine. Le pagine di Stone3PL non dicono come vengano pagati questi importi. Nel Regno Unito, negli Stati Uniti, in Canada o in Australia ciò che è dovuto dipende dalle norme locali: vedi [pacchi fermi in dogana](guide:customs).

## Pacco Stone3PL che non si aggiorna, smarrito o restituito

Stone3PL lavora per le aziende che spediscono con lui e non offre pagine di assistenza per i destinatari: in caso di ritardo, smarrimento, danno o reso, contatta prima il venditore.

**Ancora nulla.** Peek continua a controllare. Mostra «In attesa di Stone3PL» se hai scelto Stone3PL, e «Nessuna cronologia di tracciamento per ora» se non era indicato alcun corriere all’aggiunta. Dopo due giorni senza novità, Peek segnala un pacco aggiunto senza corriere con «Ancora in attesa del primo aggiornamento del corriere»: verifica il numero con il venditore. Se hai scelto Stone3PL e dopo due giorni non conosce ancora il numero senza altre fonti che rispondano, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e da quel momento controlla una volta al giorno, e una volta a settimana dopo sette giorni.

**Solo `Order Submited.` o `Shipment information received`.** Stone3PL ha i dati dell’ordine, ma forse non ancora il pacco. Se non cambia per diversi giorni, chiedi al venditore se lo ha consegnato.

**Nessun aggiornamento da giorni.** Quando un pacco su «Etichetta creata», «Preso in carico» o «In transito» resta quattro giorni senza nuove righe, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e il messaggio «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Stone3PL.», insieme al link «Chiedi aiuto a Stone3PL» verso la pagina contatti. Vedi [perché il tracciamento si ferma](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**«Problema segnalato».** Stone3PL ha indicato un’anomalia. Leggi la riga più recente e chiedi al venditore cosa succede ora.

**Consegnato, ma non c’è.** Controlla intorno a casa e chiedi ai vicini, poi avvisa il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito, danneggiato o restituito.** Il sito di Stone3PL non dà informazioni ai destinatari su reclami o resi; la home page offre ai venditori un servizio di reso e rispedizione. Chiedi al venditore un rimborso o un nuovo pacco. Vedi [pacchi smarriti](guide:lost-parcel), [pacchi danneggiati](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

**Un messaggio che chiede un pagamento.** Prima di pagare qualsiasi importo richiesto via SMS o e-mail, leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Contatti Stone3PL

Dalla [pagina contatti di Stone3PL](https://stone3pl.com/index.php?route=services/contact_us), per la sede centrale di Shenzhen («STONE3PL Marketing Dpt»):

- **E-mail:** support@stone3pl.com. La stessa e-mail figura nell’informativa sulla privacy di Stone3PL.
- **Telefono:** 86-0755-81466633, numero a Shenzhen, Cina. Stone3PL non indica orari di apertura né lingue parlate.
- **Indirizzo:** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China.

Per il tuo ordine chiedi prima al venditore: è lui ad aver affidato la spedizione a Stone3PL, non tu.

## Tracciare un pacco Stone3PL con Peek

[Peek](/) segue i pacchi Stone3PL, ma non può riconoscere un codice Stone3PL solo dalla forma. Quando incolli un numero fatto da `S` e 18 cifre, Peek mostra «Verifica dei servizi di tracciamento…» mentre interroga Stone3PL. Se Stone3PL ha il pacco, compare «Stone3PL ha questo pacco.» e Peek lo segue lì. Un link al tracker di Stone3PL con un codice dopo `nums=` identifica subito Stone3PL («Corriere rilevato»).

Per qualsiasi altro codice, incollalo lo stesso: Peek segue oltre 4.500 corrieri. Quando una forma corrisponde debolmente ad alcuni corrieri, Peek mostra «Verifica dei servizi di tracciamento…» mentre li interroga; un numero che non corrisponde a nessuna forma viene cercato dopo l’aggiunta. Se Peek non nomina il corriere, tocca «Cambia» e scegli Stone3PL sotto «Tutti i corrieri», oppure usa «Cambia corriere» su un pacco già aggiunto.

Peek legge lo stato complessivo, ogni riga con il suo orario, i paesi di partenza e destinazione, il servizio di spedizione, nonché il corriere finale e il relativo codice quando forniti. Rimuove i numeri di telefono. Stone3PL non fornisce data prevista di consegna, peso, luoghi delle scansioni né prova di consegna.

Quando Stone3PL nomina il corriere finale e ne fornisce il codice, Peek interroga quel corriere. Con un codice postale (due lettere, nove cifre, due lettere) senza corriere nominato, Peek interroga le poste del paese di destinazione, come [USPS](carrier:usps) negli Stati Uniti, [Royal Mail](carrier:royal-mail) nel Regno Unito, An Post in Irlanda, [Canada Post](carrier:canada-post) o [Australia Post](carrier:australia-post). Con un altro codice interroga l’unico corriere a cui quella forma appartiene, se esiste. Non appena quel corriere mostra una scansione almeno recente quanto l’ultima di Stone3PL, Peek segue il pacco lì conservando la cronologia di Stone3PL. Il corriere nominato da Stone3PL appare sul pacco già prima («Consegna con USPS», per esempio); finché quel corriere ha solo l’annuncio senza scansioni proprie, il pacco mostra «Annunciato a …, non ancora ricevuto». Senza tale corriere, Peek resta sul tracciamento di Stone3PL.

Mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek interroga direttamente Stone3PL fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora del paese di destinazione nel giorno in cui il pacco passa in consegna, finché il tracker di Stone3PL risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Quando subentra un altro corriere, Peek segue il ritmo del corriere che effettua la consegna. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Stone3PL né il venditore per te.

## Domande sul tracciamento Stone3PL

### Stone3PL è affidabile?

Stone3PL dichiara di far parte dello Stone Group, con sede a Shenzhen, oltre 10 anni di esperienza e più di 8.000 clienti a contratto. Pubblica indirizzo, telefono ed e-mail. Lavora per i venditori, quindi le domande sull’ordine vanno al negozio.

### Dove posso tracciare un pacco Stone3PL?

Sulla [pagina di tracciamento di Stone3PL](https://stone3pl.com/?route=services/track) o in Peek. Se il risultato di Stone3PL nomina il corriere che consegna nel tuo paese, puoi tracciare quel codice anche sul sito del corriere locale.

### Cosa significa «Pick up by local carrier at destination port»?

Che il corriere incaricato della consegna nel tuo paese ha ritirato il pacco al porto o aeroporto di arrivo. Non significa che debba ritirarlo tu.

### Quanto tempo impiega Stone3PL a consegnare?

Stone3PL non pubblica tempi di consegna. Affidati alla stima del venditore e chiedi a lui se il pacco è in ritardo.

### Come posso contattare Stone3PL?

Via e-mail a support@stone3pl.com o per telefono allo 86-0755-81466633 (numero a Shenzhen, Cina). Per il tuo ordine chiedi prima al venditore.

:::sources
- [Stone3PL: Home](https://stone3pl.com/) – logistica e-commerce dalla Cina al mondo per venditori, sdoganamento DDP e Delivered Duty Paid, servizio di reso e rispedizione, magazzini in NORAM, APAC ed EMEA
- [Stone3PL: Logistics tracking](https://stone3pl.com/?route=services/track) – tracker in inglese, fino a 40 codici uno per riga, lettere, cifre e trattini in maiuscolo, aziende tracciate
- [Stone3PL: About us](https://stone3pl.com/index.php?route=services/about_us) – Stone Group, sede a Shenzhen, oltre 10 anni di esperienza, oltre 8.000 clienti a contratto, partner logistici globali
- [Stone3PL: Contact us](https://stone3pl.com/index.php?route=services/contact_us) – sede a Shenzhen, e-mail, telefono, indirizzo
- [Stone3PL: Privacy Policy](https://stone3pl.com/index.php?route=services/privacy) – e-mail di supporto
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per articolo fino a 150 € dal 1° luglio 2026, tariffa unionale di gestione di 2 € dal 1° novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per articolo a prescindere dal valore, definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato codice, diciture e fasi, stato complessivo, ora cinese, corriere finale e passaggio
:::
