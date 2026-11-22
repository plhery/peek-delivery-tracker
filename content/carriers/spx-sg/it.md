---
title: Tracciamento SPX Express Singapore: Shopee e stati
description: Traccia un pacco SPX Express (Shopee Xpress) a Singapore: codici SPXSG e SG, significato degli stati, GST all'importazione e assistenza.
slug: tracciamento-spx-express-singapore
published: 2026-11-22
updated: 2026-11-22
---

SPX Express Singapore, chiamata anche Shopee Xpress o Shopee Express, consegna pacchi a Singapore. Trasporta ordini Shopee, compresi pacchi spediti da venditori in Cina che transitano dal centro di smistamento Shopee di Hong Kong. Il tracker di SPX si trova su spx.sg. Per seguire un pacco qui, incolla il codice nel campo qui sopra. Per un ordine Shopee, apri l'ordine in Shopee e copia il numero di tracciamento dalle informazioni di spedizione.

## Numero di tracciamento SPX Express Singapore: com'è fatto

Peek riconosce due formati per SPX Express Singapore (cifre inventate):

| Numero (inventato) | Che cos'è |
| --- | --- |
| `SPXSG48291736482K` | SPXSG, 11 cifre e una cifra o lettera finale (17 caratteri): il numero proprio di SPX. Peek identifica subito SPX Express Singapore. |
| `SG482917364829K` | SG, 12 cifre e una cifra o lettera finale (15 caratteri): il numero Shopee associato dal tracker SPX. Da solo questo formato non basta a Peek per indicare SPX (vedi sotto). |
| `SPXSG4829173648` | SPXSG e solo 10 cifre: Peek non lo riconosce. Verifica se manca una cifra. |
| `RA482917367SG` | Due lettere, nove cifre e SG finale: un codice Singapore Post, non SPX. |

Spazi e minuscole non contano quando incolli un numero in Peek.

**Il numero d'ordine non è un numero di tracciamento.** Se il codice incollato ha la forma di un ordine Shopee, Peek mostra «Sembra un numero d’ordine Shopee». Copia invece il codice di tracciamento dall'ordine. Non lo trovi? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Gli stati di tracciamento SPX Express Singapore spiegati

Il tracker SPX, letto da Peek, scrive ogni riga in inglese. Ecco le diciture riconosciute dal codice di Peek, grosso modo nell'ordine di un pacco dall'estero:

| SPX indica | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | Il venditore in Cina sta preparando il pacco. SPX non lo ha ancora. | «Etichetta creata» |
| `[China] Parcel has arrived at Hong Kong sorting centre` | È arrivato al centro di smistamento Shopee di Hong Kong. | «In transito» |
| `[China] Parcel has departed from Hong Kong sorting centre` | Ha lasciato quel centro di smistamento. | «In transito» |
| `[China] Parcel has been handed over to delivery service provider` | È stato affidato a un vettore aereo che lo inoltra. | «In transito» |
| `[China] Parcel has cleared export customs` | Ha superato la dogana di esportazione, non quella di Singapore. | «In transito» |
| `Parcel has departed from the origin port: Hongkong` | Ha lasciato Hong Kong. | «In transito» |
| `Parcel has arrived at the destination port Singapore` | È arrivato a Singapore: è un arrivo, non un fermo doganale. | «In transito» |
| `Parcel has cleared import customs` | La dogana di importazione di Singapore lo ha svincolato. | «In transito» |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | È nella rete di consegna di Singapore per ulteriore smistamento prima della consegna. | «In transito» |
| `[Singapore] Parcel has arrived at sorting facility` | È in un centro di smistamento a Singapore. | «In transito» |
| `[SPX Express] Parcel is out for delivery` | È in consegna. | «In consegna» |
| `Parcel has been delivered` | Consegnato. | «Consegnato» |

**Altri passaggi.** Il codice di Peek riconosce altri stati SPX: ritiro presso il venditore o deposito in punto di consegna («Preso in carico»), consegna fallita («Tentativo di consegna») e reso al venditore («Restituito al mittente», oppure «Problema segnalato» se il reso si blocca). Di più in [significato degli stati di tracciamento](guide:tracking-statuses).

## Consegna SPX Express a Singapore

Dopo `[SPX Express] Parcel is out for delivery`, la riga successiva dovrebbe essere `Parcel has been delivered`. Se SPX porta il pacco a un punto di ritiro self-service invece che a domicilio, Peek mostra «In consegna» mentre è diretto lì.

Peek non ha potuto leggere il sito web di SPX Express Singapore, per cui questa pagina non indica fasce di consegna, numero di tentativi o regole di ritiro. Chiedi al venditore, o a Shopee se hai ordinato lì. Vedi [consegne mancate](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

## Pacchi dall'estero: GST e dogana a Singapore

Regole di Singapore Customs per acquisti dall'estero via aerea o posta:

- **GST al checkout.** Se ciascun articolo costa 400 S$ o meno e il venditore o marketplace estero è registrato ai fini GST, l'imposta è già nel prezzo. Il venditore invia i dati del pagamento al corriere e la GST non viene riscossa di nuovo all'importazione. Il limite riguarda solo la merce, esclusi spedizione e assicurazione.
- **GST all'importazione.** Se il venditore non è registrato GST e il valore CIF totale (merce, assicurazione e trasporto) supera 400 S$, la GST viene riscossa all'ingresso a Singapore. Fino a 400 S$ non si applica GST all'importazione, tranne per alcolici e tabacco.
- **Addebitata due volte?** Se hai pagato la GST al checkout e ti viene chiesta ancora all'importazione, paga la GST d'importazione per ritirare la merce e chiedi poi il rimborso al venditore allegando la prova del pagamento.

**Fermo in dogana.** La Immigration & Checkpoints Authority (ICA) può trattenere pacchi contenenti merci soggette a dazio, controllate o vietate, oppure di valore CIF totale superiore a 400 S$ privi di fattura all'arrivo. L'ICA rilascia al corriere una ricevuta di deposito e il corriere dovrebbe indicarti cosa fare.

**Truffe.** Singapore Customs avverte che non chiede pagamenti GST né dati personali tramite SMS, e-mail o app di messaggistica. Vedi [SMS truffa di consegna](guide:delivery-scam-texts) e [pacchi fermi in dogana](guide:customs).

## Pacco SPX fermo, smarrito o restituito

**Codice non ancora trovato.** Quando SPX non conosce ancora un codice SPXSG, Peek mostra «In attesa di SPX Express Singapore» e continua a controllare. Il venditore potrebbe non aver ancora consegnato il pacco; controlla anche il codice.

**Bloccato su `[China]Sender is preparing to ship your parcel`.** Il venditore non lo ha ancora spedito: chiedigli quando partirà.

**Nessun aggiornamento per giorni.** Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Dopo quattro giorni senza scansioni in transito (fuori da dogana o problemi segnalati), Peek lo segnala con «Nessun aggiornamento da…» e continua a chiedere a SPX. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating) e [tracciare pacchi dalla Cina](guide:tracking-from-china).

**Consegnato ma non arrivato.** Controlla intorno alla porta e chiedi a familiari e vicini, poi contatta il venditore o Shopee. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o restituito.** La spedizione è stata prenotata dal venditore: rivolgiti al venditore o a Shopee per rimborso o sostituzione. Vedi [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Servizio clienti e contatti SPX Express Singapore

Poiché Peek non ha potuto leggere il sito web di SPX Express Singapore, questa pagina non elenca telefono o e-mail: Peek riporta solo recapiti letti sulle pagine del corriere.

Per un ordine Shopee, contatta l'assistenza Shopee. Per un altro negozio, rivolgiti prima al negozio che ha prenotato la spedizione. Singapore Customs non può aiutare perché non accede al tracciamento dei corrieri e rimanda al corriere.

## Tracciare un pacco SPX Express Singapore con Peek

[Peek](/) segue un pacco SPX con il solo numero: nessun account, telefono o codice postale richiesto.

- **Un numero SPXSG.** Peek seleziona subito SPX Express Singapore («Corriere rilevato») e interroga direttamente il tracker SPX. Con «Cambia» puoi scegliere un altro corriere.
- **Un link.** Incolla un link del tracker su spx.sg contenente un codice SPXSG (`/track?`, `/m/track?` o `/detail/`) e Peek ne ricava il numero.
- **Un numero SG.** Da solo questo formato non permette a Peek di essere certo del corriere. Incollalo comunque: Peek segue oltre 4.500 corrieri. Durante la ricerca compare «Verifica dei servizi di tracciamento…». Per scegliere SPX tu stesso, premi «Cambia»: SPX Express Singapore è sotto «Compatibili con questo numero». Meglio comunque usare il codice SPXSG se lo possiedi, perché il tracker SPX spesso trova i pacchi Shopee solo con quello.

Aggiunto senza corriere, il pacco mostra «Nessuna cronologia di tracciamento per ora» mentre Peek interroga i servizi di tracciamento. Se uno indica solo SPX Express Singapore con passaggi datati, Peek assegna il pacco a SPX.

**Cosa mostra Peek.** Stato, ogni riga nelle parole di SPX e il luogo delle scansioni in magazzini, porti e centri di smistamento. SPX fornisce il momento esatto di ogni scansione, che Peek mostra nel tuo fuso orario («Orari nel tuo fuso orario:»). Peek conserva anche alcuni passaggi nascosti sulla pagina SPX, come lo sdoganamento all'importazione e un reso in corso. Omette posizione del venditore, indirizzi degli uffici e nome di chi ha ritirato il pacco. SPX non comunica data prevista, peso o punto di ritiro, quindi Peek non ne mostra.

**Da Janio a SPX.** Quando il tracciamento di un pacco Janio indica SPX con un numero SPX Singapore, la scheda mostra «Consegna con SPX Express Singapore» e Peek interroga anche SPX. Finché SPX ha solo l'annuncio senza una propria scansione, mostra «Annunciato a SPX Express Singapore, non ancora ricevuto». Appena SPX registra una scansione recente almeno quanto l'ultima di Janio, Peek segue il pacco su SPX conservando la cronologia Janio.

**Controlli e notifiche.** Peek interroga direttamente SPX fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 (ora di Singapore) del giorno in cui SPX scansiona il pacco in consegna, mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Tutti gli aggiornamenti», «Solo importanti» o «Solo giorno di consegna». Dopo quattro giorni senza scansioni in transito, fuori da dogana o problemi segnalati, Peek mostra «Nessun aggiornamento da…», ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a SPX Express Singapore.» e propone «Chiedi aiuto a SPX Express Singapore». Peek non può contattare SPX, Shopee o il venditore al posto tuo.

## Domande frequenti su SPX Express Singapore

### SPX Express è la stessa cosa di Shopee Express?

Sì. Il catalogo di Peek elenca SPX Express Singapore anche come Shopee Xpress Singapore e Shopee Express Singapore, e trasporta ordini Shopee. SPX gestisce reti distinte in altri paesi, come [SPX Express Brasile](carrier:spx-br): un codice SPXSG riguarda Singapore.

### Qual è la differenza tra un codice SPXSG e un codice SG?

Il codice SPXSG è il numero proprio di SPX per il pacco, con cui Peek lo riconosce subito. Il codice SG è il numero Shopee abbinato. Poiché il tracker SPX spesso individua i pacchi Shopee solo tramite il codice SPXSG, conviene usare quello.

### Cosa significa «Parcel has been picked up by our local logistics partner»?

Il pacco dall'estero è arrivato a Singapore ed è stato affidato alla rete di consegna locale per lo smistamento. SPX indica che riceverai avviso quando sarà pronto per la consegna. Peek mostra «In transito».

### Perché il tracciamento SPX non si aggiorna?

Se l'ultima riga è `[China]Sender is preparing to ship your parcel`, il venditore non ha ancora spedito. Più avanti i pacchi restano spesso fermi durante voli, dogana o fine settimana. Dopo quattro giorni senza novità in transito Peek lo segnala; se resta immobile chiedi al venditore o a Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – soglia 400 S$, GST al checkout o all'importazione, doppio addebito, truffe
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – pacchi trattenuti da ICA, rivolgersi al corriere per il tracciamento
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – nomi, formati codice, link tracker, diciture e fasi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – codici condivisi SPX, passaggi nascosti conservati, formato Singapore Post, passaggio da Janio
:::
