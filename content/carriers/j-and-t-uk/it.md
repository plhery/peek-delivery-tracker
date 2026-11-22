---
title: Tracking J&T Express UK: codici JTUK e stati
description: Traccia un pacco J&T Express UK: formato del codice JTUK, significato degli stati di tracciamento, diritti nel Regno Unito e cosa fare in caso di ritardo.
slug: tracking-jt-express-uk
published: 2026-11-21
updated: 2026-11-21
---

J&T Express UK consegna pacchi nel Regno Unito. Se un negozio ha spedito il tuo ordine con J&T, il codice di tracciamento inizia con `JTUK`: incollalo nel campo qui sopra oppure cercalo sul sito di tracciamento di J&T su www.jtexpress.uk/tracking. Il numero si trova nell'e-mail di spedizione o nella pagina dell'ordine del negozio. Poiché Peek non ha potuto leggere il sito web di J&T Express UK, questa pagina si basa su ciò che il codice di Peek conosce del tracciamento J&T e su fonti ufficiali britanniche.

## Codice di tracking J&T Express UK: com'è fatto

La lettera di vettura di J&T Express UK è composta da `JTUK` seguito da 12 cifre, 16 caratteri in tutto. Peek non conosce altri formati per questo corriere. Esempi inventati:

| Codice (inventato) | Cos'è |
| --- | --- |
| `JTUK482910573628` | `JTUK` e 12 cifre: un pacco J&T Express UK. Peek riconosce subito J&T Express UK. |
| `JTUK48291057362` | Manca una cifra: non corrisponde al formato J&T Express UK e Peek non riconosce alcun corriere. Controlla se manca una cifra. |
| `JTDE482910573628` | `JTDE` e 12 cifre: [J&T Express Germany](carrier:j-and-t-de), non la rete britannica. |
| `JTAU482910573628` | `JTAU` e 12 cifre: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` e 12 cifre: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` e 13 cifre: reti J&T nel Sud-est asiatico e in Cina ([J&T Express China](carrier:j-and-t-cn)). |

Su peektracker.com, un numero `JTUK` con un carattere in meno o in più mostra un avviso come: «Inizia come un numero di tracciamento di J&T Express UK, ma manca un carattere: forse c’è un errore di battitura. Confrontalo con il messaggio di spedizione.» Spazi, trattini e minuscole non fanno differenza in Peek: `jtuk 4829 1057 3628` equivale alla prima riga. Il catalogo di Peek conosce il corriere anche come J&T Express (UK), J&T Express UK Ltd e J&T UK.

**Dove trovarlo.** Nell'e-mail di spedizione del negozio, nella pagina dell'ordine del negozio o marketplace, oppure nei messaggi relativi alla consegna. Se non lo trovi, leggi [come trovare il numero di tracciamento](guide:find-tracking-number) o chiedi al negozio.

## Stati del tracking J&T Express UK spiegati

Il tracciamento di J&T mostra ogni scansione come un orario e una frase, senza luogo. Ecco le frasi registrate nel codice di Peek su pacchi reali di J&T Express UK, nell'ordine in cui un pacco le incontra:

| Cosa dice J&T | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | J&T ha ritirato il pacco dal mittente e lo sta preparando per il trasporto. | «Preso in carico» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | Arrivato in un centro di smistamento J&T. | «In transito» |
| `The parcel is dispatched from the transit center to the next destination` | Partito dal centro verso la tappa successiva. | «In transito» |
| `The parcel has been collected and is on its way` | Il corriere ha preso in carico il pacco per il giro di consegna. Nonostante le parole, non è il ritiro iniziale: J&T lo registra come scansione di consegna. | «In consegna» |
| `The parcel has been successfully delivered to the recipient` | Consegnato. | «Consegnato» |
| `The delivery address is incomplete or incorrect.` | Tentativo fallito: il corriere non ha potuto consegnare all'indirizzo indicato. Nella cronologia presente nel codice di Peek compare poche ore dopo la partenza in consegna, lo stesso giorno. | «Tentativo di consegna» |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | Non c'era nessuno per ricevere il pacco, che è stato portato in un punto di ritiro per il cliente. | «Tentativo di consegna» |

Una frase assente da questa tabella segue le regole generali di Peek: se Peek non la riconosce, la mostra come scritta da J&T e la conta come «In transito», a meno che il pacco non sia già in consegna o oltre, nel qual caso lo stato resta invariato.

Se una frase termina con un numero di assistenza clienti o un invito a chiamare, Peek lo elimina. Rimuove anche il testo tra parentesi, i numeri di telefono e i nomi di chi ha firmato, ricevuto o ritirato il pacco, o di corrieri, autisti, destinatari o mittenti, conservando il resto.

## Consegna J&T Express UK: cosa mostra il tracciamento

Nelle cronologie nel codice di Peek, il corriere J&T esce in consegna e consegna il pacco, segnala un problema d'indirizzo o porta il pacco a un punto di ritiro se nessuno è disponibile. Il codice di Peek non mostra passaggi ad altri corrieri: il tracciamento di J&T segue il pacco fino a casa tua.

**In consegna.** `The parcel has been collected and is on its way` significa che il pacco è sul giro di oggi. Il tracciamento di J&T non fornisce fasce orarie né giorno previsto, quindi nemmeno Peek può mostrarli.

**Problema d'indirizzo.** Dopo `The delivery address is incomplete or incorrect.`, controlla l'indirizzo sull'ordine. Se manca un dato (come il numero d'interno o il codice postale), avvisa subito il negozio che ha prenotato la consegna. Vedi [come cambiare l'indirizzo di consegna](guide:change-delivery-address) e [consegna mancata](guide:missed-delivery).

**Tentativi, luoghi sicuri e punti di ritiro.** Poiché Peek non ha potuto leggere il sito web di J&T Express UK, questa pagina non indica quanti tentativi faccia J&T, se lasci pacchi a vicini o in luogo sicuro, né per quanto tempo custodisca un pacco non consegnato. Chiedi al negozio o controlla i tuoi messaggi di consegna.

**Ordini dall'estero verso il Regno Unito.** Secondo GOV.UK, per merci spedite dall'estero, l'azienda che consegna ti contatta se sono dovuti IVA (VAT), dazi o spese di consegna, inviandoti una fattura con l'importo esatto da pagare. Di norma trattiene il pacco per circa 3 settimane, poi lo restituisce al mittente in caso di mancato pagamento. Per beni del valore di 135 £ o meno acquistati di persona (esclusi prodotti soggetti ad accisa come alcol, tabacco o sigarette elettroniche), il venditore ha già incluso l'IVA nel prezzo. Oltre 135 £ paghi l'IVA alla società di consegna prima della consegna o al ritiro, più eventuali dazi doganali secondo la merce e l'origine. L'Irlanda del Nord segue regole proprie. Peek non ha potuto verificare come J&T addebiti gli oneri d'importazione. Altro in [pacchi fermi in dogana](guide:customs).

## Pacco J&T Express UK in ritardo, smarrito o restituito

È stato il negozio a scegliere J&T, quindi per un acquisto online il negozio è il primo riferimento. Ai sensi della sezione 29 del Consumer Rights Act 2015, i beni acquistati da un professionista restano a rischio del venditore finché non entrano in tuo possesso materiale o in quello di una persona da te indicata, salvo che tu abbia incaricato un corriere non offerto dal venditore.

**Nessun aggiornamento.** Un pacco su «Preso in carico» o «In transito» senza nuove righe per quattro giorni riceve una segnalazione in Peek con il giorno dell'ultima scansione («Nessun aggiornamento da …») e il link alla pagina di contatto di J&T Express UK («Chiedi aiuto a J&T Express UK»). Se non si muove nulla, chiedi al negozio. Vedi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Nessun tracciamento ancora.** Un numero che il tracciamento di J&T non conosce ancora appare come «In attesa di J&T Express UK». Un negozio può creare il codice prima di consegnare il pacco. Se due giorni dopo J&T non lo riconosce ancora e nessun'altra fonte ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e controlla poi una volta al giorno, e una volta alla settimana trascorsa una settimana dall'aggiunta. Verifica il codice con il negozio e chiedi se il pacco è stato davvero spedito.

**Consegnato, ma non è arrivato nulla.** Controlla attorno alla porta, in casa e dai vicini, poi avvisa il negozio: secondo quella legge il rischio resta suo fino alla consegna effettiva, quindi chiedi un rimborso o una nuova spedizione. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o danneggiato.** Chiedi rimborso o sostituzione al negozio indicando il codice di tracciamento. Peek non ha potuto leggere le regole di reclamo di J&T; vedi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

**Restituito.** Se il pacco rientra al negozio dopo una consegna fallita, spetta al negozio decidere su rimborso o nuova spedizione. Vedi [pacco restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti J&T Express UK

Poiché Peek non ha potuto leggere il sito web di J&T Express UK, questa pagina non riporta numero di telefono, e-mail o chat: Peek elenca solo contatti letti sulle pagine ufficiali del corriere. Companies House indica come sede legale `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ`: l'indirizzo legale presso un'altra società, non uno sportello pacchi.

Comincia dal negozio che ha prenotato la spedizione, oppure usa l'assistenza sulla pagina dell'ordine di un marketplace. Il tracciamento di J&T è su www.jtexpress.uk/tracking.

## Tracciare un pacco J&T Express UK con Peek

[Peek](/) riconosce subito J&T Express UK da un codice `JTUK` («Corriere rilevato») e interroga il tracciamento di J&T con il solo numero, senza CAP né account. Funziona anche un link www.jtexpress.uk/tracking con `?q=` e il numero; se contiene un altro tipo di codice, Peek lo legge come se lo avessi incollato da solo. Se il tuo codice non è `JTUK`, incollalo lo stesso: Peek traccia oltre 4.500 corrieri. Per un numero `JT` a 13 cifre, Peek mostra «Verifica dei servizi di tracciamento…» durante il controllo. Scegliere J&T Express UK con «Cambia» per un altro formato non serve: Peek avvisa che «non emette numeri come questo».

Peek mostra lo stato, ogni scansione con l'ora esatta e l'ora di consegna. J&T fornisce ogni scansione come istante esatto, quindi Peek mostra tutti gli orari nel fuso orario del tuo dispositivo, sia d'estate sia d'inverno; su peektracker.com e nelle app per iPhone e Android la cronologia si chiude con «Orari nel tuo fuso orario:» e il suo nome. Le scansioni restano nell'inglese di J&T. J&T non fornisce luogo, giorno previsto né peso, perciò Peek non mostra mappa. Il portale di J&T può mostrare la prova di consegna, che Peek lascia fuori. «Apri il sito J&T Express UK», nella pagina del pacco, apre il tuo codice sul tracciamento di J&T.

Finché J&T non scansiona il pacco, Peek continua a controllare con «In attesa di J&T Express UK» (o chiede di verificare il numero dopo due giorni). Quando il tracciamento di J&T non è raggiungibile o non conosce il numero, Peek interroga i servizi di tracciamento e mostra la loro cronologia nelle loro parole.

Finché tieni il pacco aperto sullo schermo o nel giorno della consegna con le notifiche attive, e mentre risponde il tracciamento di J&T, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui J&T scansiona il pacco in consegna; altrimenti ogni ora. Tramite i servizi di tracciamento controlla ogni ora (ogni 3 ore dopo un giorno senza novità), e fino a ogni 15 minuti il giorno della consegna finché lo tieni aperto o hai le notifiche attive. Dopo due giorni senza una nuova scansione (12 ore finché mostra «Preso in carico»), controlla ogni ora. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare J&T o il negozio al posto tuo, cambiare l'indirizzo né prenotare una nuova consegna.

## Domande sul tracking J&T Express UK

### J&T Express UK è affidabile?

Sì. Companies House elenca `J&T EXPRESS UK LTD` come società a responsabilità limitata attiva, costituita il 18 dicembre 2025, con attività dichiarate tra cui trasporto merci su strada, magazzinaggio e attività postali. I suoi codici iniziano con `JTUK` e Peek legge direttamente il suo tracciamento.

### Cosa significa «The parcel has been collected and is on its way» su J&T?

Il tuo pacco è in consegna. Il corriere lo ha preso in carico per il giro di consegna; J&T registra questa riga come scansione di consegna e Peek mostra «In consegna». Non significa che il pacco sia appena partito dal mittente.

### Perché J&T dice che il mio indirizzo di consegna è incompleto o errato?

Il corriere non ha potuto consegnare perché mancava qualche dettaglio o l'indirizzo non corrispondeva. Controlla l'indirizzo sull'ordine e contatta subito il negozio con indirizzo completo e numero di tracciamento.

### Perché il mio codice J&T non viene trovato?

Verifica che inizi con `JTUK` seguito da 12 cifre: i numeri di altre reti J&T, come `JTDE` in Germania, non appartengono alla rete britannica. Se il codice è giusto, J&T potrebbe non avere ancora il pacco e Peek mostra «In attesa di J&T Express UK». Se dopo due giorni non lo riconosce ancora e nessun'altra fonte ha risposto, Peek ti chiede di controllarlo, scegliere un altro corriere o eliminare il pacco. Se dopo alcuni giorni non compare nulla, chiedi al negozio.

### Come contatto J&T Express UK?

Chiedi prima al negozio che ha prenotato la consegna. Poiché Peek non ha potuto leggere il sito web di J&T Express UK, questa pagina non riporta telefono né e-mail.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – nomi, formato `JTUK`, link di tracciamento, pagina di aiuto `jtexpress.uk/contact`, frasi del tracciamento e fasi (incluso il deposito al punto di ritiro dopo assenza), orari esatti, assenza di luogo/giorno previsto/peso, prova di consegna omessa
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati delle altre reti J&T, rimozione di righe assistenza clienti, parentesi, nomi e numeri di telefono, nessun passaggio ad altro corriere, servizi di tracciamento di riserva
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – società attiva a responsabilità limitata, costituita il 18 dicembre 2025, oggetto sociale, sede legale
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – fattura dal corriere, giacenza di circa 3 settimane, IVA fino a 135 £ e oltre, dazi doganali, Irlanda del Nord
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – rischio a carico del venditore fino al possesso fisico da parte del consumatore
:::
