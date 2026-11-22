---
title: Tracciamento Parcelforce Worldwide: stati, dogana e aiuto
description: Traccia un pacco Parcelforce Worldwide: dove trovare il codice, cosa significano gli stati, opzioni di consegna, oneri doganali e contatti Royal Mail.
slug: tracciamento-parcelforce-worldwide
published: 2026-11-22
updated: 2026-11-22
---

Parcelforce Worldwide fa parte del gruppo [Royal Mail](carrier:royal-mail): puoi tracciare un pacco Parcelforce sulla pagina Track your item di Royal Mail oppure incollando il codice nella casella qui sopra. Il codice arriva dal negozio o dal mittente, di solito nell’e-mail di conferma d’ordine.

## Numero di tracciamento Parcelforce: dove trovarlo

Cercalo nell’e-mail di conferma del negozio o chiedilo al venditore. Smarrito? Leggi [come trovare il numero di tracciamento](guide:find-tracking-number). Ecco tre formati possibili:

| Codice (inventato) | Che cos’è |
| --- | --- |
| `EA482917367GB` | Due lettere (`E` e una lettera, oppure `CP` o `GI`), nove cifre e `GB`, con cifra di controllo prima di `GB`. |
| `PBTR4829173654` | Numero di pacco da 14 caratteri: `PB`, il numero di spedizione e la posizione del collo al suo interno. |
| `TR4829173` | Un numero di spedizione da solo: il tracker Royal Mail richiede invece il codice specifico di ogni collo. |

Il formato a 13 caratteri appartiene anche a Royal Mail (`RR482917367GB` è Royal Mail, non Parcelforce), e alcuni pacchi express48 portano un numero Royal Mail. Poiché entrambi si tracciano sulla stessa pagina, cambia poco per te.

## Stati di tracciamento Parcelforce spiegati

Il tracker Royal Mail mostra queste righe in inglese. Nell’ordine consueto:

| Il tracciamento dice | Cosa significa per te |
| --- | --- |
| `Sender has despatched item` | Il mittente ha prenotato il pacco. Parcelforce non lo ha ancora ricevuto. |
| `Item Collected` | Ritirato presso il mittente. |
| `Accepted at Parcelshop` | Il mittente lo ha consegnato in un ufficio postale o parcel shop. |
| `Item received` | È entrato nella rete. |
| `Customs charges raised` | Un pacco importato da fuori Regno Unito deve pagare IVA, dazi o commissioni prima della consegna. |
| `Despatched to delivery office` | In viaggio verso il deposito o ufficio di consegna locale. |
| `Received at Delivery Depot` | Al deposito locale. Non è ancora pronto per il ritiro. |
| `Out for delivery` | Con l’autista oggi. |
| `Delivery attempted` | L’autista non è riuscito a consegnare. |
| `Delivered by` | Consegnato. La riga può indicare l’ufficio che l’ha consegnato. |
| `Delivered to Neighbour/Reception by` | Lasciato a un vicino o alla reception, indicato nella riga. |
| `Returned to sender` | In ritorno al mittente. |

Sui pacchi inviati all’estero la cronologia continua con righe come `Item Leaving the UK` e `Due to be delivered today` del partner locale.

## Opzioni di consegna Parcelforce e consegne mancate

**Prima della consegna.** Nell’app Royal Mail o tramite Royal Mail Customer Services puoi scegliere un altro giorno di consegna anche per un pacco Parcelforce, chiedere la consegna a un vicino oppure chiedere all’autista di bussare più forte o darti più tempo per aprire. Anche il luogo sicuro si imposta con Royal Mail per i pacchi Parcelforce.

**Consegna mancata.** Il tracciamento mostra `Delivery attempted` oppure `Delivered to Neighbour/Reception by` con il nome di chi l’ha preso in consegna. Se non riesci ancora a capire dov’è il pacco, chiama Royal Mail Customer Services (sotto).

## Tracciamento Parcelforce fermo, pacco in ritardo o smarrito

**Nessuna scansione ancora.** Il mittente potrebbe aver creato il numero prima di consegnare il pacco: `Sender has despatched item` significa che Parcelforce non lo ha ancora. Controlla il numero e riprova più tardi. Altro in [perché il tracciamento si ferma](guide:tracking-not-updating).

**In ritardo, smarrito o danneggiato.** Se hai comprato online, contatta prima il venditore: Citizens Advice indica che di solito si ottiene un esito migliore e puoi chiedere un rimborso o una nuova spedizione. Per il risarcimento verifica la procedura di reclamo sul sito Parcelforce.

**Oneri doganali.** Parcelforce fa passare in dogana i pacchi da fuori Regno Unito e ti contatta se sono dovuti IVA, dazi o altri oneri, con un avviso che indica esattamente cosa pagare ([approfondisci la dogana](guide:customs)). In genere trattiene il pacco circa 3 settimane; se non pagato entro allora, torna al mittente. Anche le domande sulla merce vanno a Parcelforce.

> Non sei sicuro che un’e-mail o un SMS che chiede un pagamento sia autentico? Non cliccare i link e non fornire dati bancari. Inoltra gratuitamente le e-mail truffa a report@phishing.gov.uk e gli SMS sospetti al 7726 (Regno Unito).

## Numero di telefono e assistenza clienti Parcelforce

Citizens Advice indirizza i clienti Parcelforce a Royal Mail per esigenze di consegna e luoghi sicuri:

- **Royal Mail Customer Services:** 03457 740 740, lunedì–venerdì 8:00–18:30, sabato 8:00–15:00 e domenica 9:00–14:00 (ora del Regno Unito). **Relay UK:** 18001 poi 0345 266 0075.
- **App Royal Mail:** data di consegna, vicino, luogo sicuro ed esigenze di consegna anche per i pacchi Parcelforce.

Per un reclamo o una richiesta di rimborso usa il sito web di Parcelforce.

## Tracciare pacchi Parcelforce con Peek

[Peek](/) identifica subito Parcelforce per i codici tipo `EA482917367GB` con cifra di controllo valida e per i codici `PB` da 14 caratteri. Un numero di spedizione da solo non viene tracciato. Un link Parcelforce con `trackNumber=` funziona, così come un link Royal Mail che termina con `#/tracking-results/` e un codice Parcelforce.

Peek legge Parcelforce sul sito Royal Mail aprendolo in un browser reale sul server, quindi ogni controllo impiega più tempo e può fallire se il sito blocca gli accessi automatici. Peek prova allora i servizi di tracciamento universali, che però hanno pochi dati su Parcelforce.

Peek mostra stato, ogni scansione con il luogo (senza codici postali), giorno di consegna previsto, ora di consegna e paese di destinazione. Gli stati mantengono l’inglese di Royal Mail, tranne alcuni che Peek traduce, come `Out for delivery` («In consegna»), `Delivered` («Consegnato») e `Returned to sender` («Restituito al mittente»). Una scansione estera priva di fuso orario non viene datata; quelle con fuso appaiono nel tuo fuso orario («Orari nel tuo fuso orario:»). Per una spedizione estera con codice `EA482917367GB`, Peek può anche interrogare le poste nazionali del paese di destinazione indicato da Parcelforce (se non ne indica uno, quelle del paese in cui ti colloca, tranne il Regno Unito) e passare al loro tracciamento non appena mostrano una scansione non più vecchia dell’ultima di Parcelforce.

Mentre tieni il pacco aperto in Peek — oppure il giorno della consegna con le notifiche attive — Peek controlla fino a ogni 10 minuti, e fino a ogni 5 minuti dalle 6:00 ora del Regno Unito (oppure da mezz’ora prima dell’apertura della fascia oraria se Parcelforce ne indica una) nel giorno in cui Parcelforce lo scansiona in consegna, finché il tracker di Parcelforce risponde; altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), ogni ora; dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Dopo quattro giorni senza nuove scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Parcelforce Worldwide.», con il link «Chiedi aiuto a Parcelforce Worldwide» verso la pagina contatti Parcelforce. Mostra le scansioni su una mappa e invia avvisi a scelta («Ogni scansione», «Passaggi importanti» o «Solo la consegna»).

## Domande sul tracciamento Parcelforce

### Parcelforce fa parte di Royal Mail?

Sì. GOV.UK include Parcelforce nel Royal Mail Group e i suoi pacchi si tracciano sulla pagina Track your item di Royal Mail.

### Perché il mio codice Parcelforce non è riconosciuto?

Il pacco potrebbe non essere ancora arrivato a Parcelforce oppure il numero è digitato male. Un numero di spedizione da solo non è tracciato: chiedi al mittente il codice `PB` di 14 caratteri di ciascun collo e riprova più tardi.

### Per quanto tempo Parcelforce trattiene un pacco con oneri doganali?

Circa 3 settimane. Se la fattura non viene saldata entro quel termine, il pacco torna al mittente.

### Posso scegliere un altro giorno per una consegna Parcelforce?

Sì, nell’app Royal Mail o tramite Royal Mail Customer Services. Puoi anche chiedere la consegna a un vicino.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce parte di Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – app Royal Mail e Customer Services per Parcelforce, altro giorno, vicino, luogo sicuro, bussare più forte, telefono e orari
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – numero Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – prima il venditore, reclami sul sito del corriere
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce sdogana la merce, domande sui beni
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – avviso di pagamento, circa 3 settimane, reso al mittente
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – cifra di controllo prima del codice paese
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek, stati e formati di codici
:::
