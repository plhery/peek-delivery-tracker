---
title: Tracciamento Royal Mail: numero, stati, dogana e Poste
description: Traccia un pacco Royal Mail in Italia o Svizzera: formati GB, stati in inglese, consegna con Poste Italiane o Posta Svizzera, IVA e dazi doganali.
slug: tracciamento-royal-mail
published: 2026-11-17
updated: 2026-11-17
---

Royal Mail è l'operatore postale nazionale del Regno Unito e il suo servizio *Track your item* (su `royalmail.com`) mostra anche le spedizioni di Parcelforce Worldwide. Per seguire un pacco, inserisci il codice su `royalmail.com` oppure incollalo nel riquadro qui sopra. Il numero ti viene fornito dal mittente: cercalo nell'e-mail di conferma d'ordine, nella notifica di spedizione o nella ricevuta del negozio britannico.

## Numero di tracciamento Royal Mail: formati e dove trovarlo

I codici tracciati da Royal Mail seguono tre formati principali. Se non trovi nessun riferimento nei messaggi del venditore, chiediglielo direttamente; vedi anche [come trovare il numero di tracciamento](guide:find-tracking-number).

| Formato (esempio inventato) | Che cos'è |
| --- | --- |
| `AB482910576GB` | Il codice postale internazionale standard (norma UPU S10): 2 lettere, 9 cifre e `GB`. La nona cifra è una cifra di controllo. |
| `0A1234567000000000ABC` | Codice di 21 caratteri ricavato dal codice a barre 2D sulle etichette nazionali britanniche. |
| `4829105738ABCDEF` | Codice di 16 caratteri composto da cifre e lettere da `A` a `F`. |

Codici simili da non confondere:

- Un prefisso che inizia con `E` (`EA`, `EB`…), `CP` o `GI` seguito da 9 cifre e `GB` appartiene a Parcelforce Worldwide (il tracciamento di Royal Mail accetta anche i codici Parcelforce di 14 caratteri che iniziano con `PB` seguiti da due lettere e dieci cifre).
- Lo stesso formato S10 che termina con la sigla di un altro Paese (`RR482910576IT` o `RR482910576CH`) proviene dalle poste di partenza: secondo lo standard UPU S10 mantiene quell'identificativo unico fino alla consegna, e Royal Mail lo prende in carico all'arrivo nel Regno Unito (tranne gli invii rapidi EMS che iniziano per `E`, affidati a Parcelforce).
- Una sequenza di 16 cifre senza lettere non è un codice Royal Mail: davanti a quella lunghezza, Peek interroga contemporaneamente più corrieri, tra cui DHL eCommerce e TNT.

## Cosa significano gli stati di Royal Mail in italiano

Royal Mail pubblica gli aggiornamenti solo in inglese. Ecco le diciture più frequenti su una spedizione in arrivo dal Regno Unito o diretta lì, nell'ordine tipico del viaggio:

| Royal Mail mostra | Cosa significa per te |
| --- | --- |
| `Sender preparing item` / `Sender has despatched item` | Il mittente ha creato l'etichetta o annunciato la spedizione, ma Royal Mail non ha ancora preso in carico il pacco. |
| `We're expecting it` | Spedizione preannunciata elettronicamente, non ancora consegnata a Royal Mail. |
| `Item Received` | Royal Mail ha ricevuto il pacco. La stessa voce riappare nei centri di smistamento britannici e poi all'arrivo nel centro di scambio internazionale del Paese di destinazione. |
| `In transit` | Il pacco viaggia tra due hub della rete. |
| `Item Leaving the UK` | La spedizione ha lasciato il Regno Unito diretta verso il Paese di destinazione. |
| `Your item is currently with Customs in the destination country.` | Il pacco è in fase di controllo e sdoganamento all'importazione nel tuo Paese. |
| `Released from Customs` | Lo sdoganamento è concluso e il pacco passa alla rete di recapito locale. |
| `Customs charges raised` | Ci sono IVA, dazi o oneri di sdoganamento da saldare prima della consegna (vedi sotto). |
| `Item has left the overseas International Processing Centre` | Il pacco ha lasciato il centro di scambio internazionale nel Paese di arrivo. |
| `Despatched to delivery office` / `Item received at Sorting Office` | In viaggio verso il centro di recapito competente per il tuo indirizzo. |
| `Ready for Delivery` | Arrivato al centro locale e pronto per uscire in consegna. |
| `Out for delivery` / `Due to be delivered today` | In consegna oggi con il portalettere o il corriere. |
| `Delivery attempted` | Tentativo di consegna non riuscito; controlla la cassetta delle lettere, gli SMS o le e-mail. |
| `Due for Redelivery the Next Working Day` | Nel Regno Unito, Royal Mail ritenterà la consegna il giorno lavorativo successivo. |
| `Available for Redelivery or Collection` | In giacenza per il ritiro o in attesa di una nuova consegna programmata. |
| `Delivered` | Consegnato (nel Regno Unito, `Delivered to Neighbour/Reception by` indica la consegna a un vicino o in portineria). |
| `Returned to sender` | Il pacco sta tornando al mittente. |

Quando il riepilogo di Royal Mail segna `Pending` (spesso accanto ai passaggi doganali), Peek mostra la fase dell'ultima scansione specifica registrata da Royal Mail, come «In dogana».

## Chi consegna il tuo pacco Royal Mail in Italia e in Svizzera

Le spedizioni partite dal Regno Unito con un codice postale in `…GB` viaggiano nella rete postale internazionale e mantengono lo stesso numero presso l'operatore nazionale di destinazione:

- **In Italia:** [Poste Italiane](carrier:poste-italiane) prende in carico l'invio e ne mostra gli aggiornamenti in *Cerca Spedizioni* su `poste.it` e nell'App Poste Italiane dal momento dell'accettazione in Italia. Se non sei in casa, ricevi un avviso di giacenza: l'invio resta in giacenza per il periodo indicato sull'avviso, dopodiché torna al mittente per compiuta giacenza. Per delegare il ritiro in Ufficio Postale basta compilare lo spazio sull'avviso di giacenza: il delegato deve presentare il proprio documento d'identità e una copia del tuo.
- **In Svizzera:** [Posta Svizzera](carrier:swiss-post) cura lo sdoganamento postale e il recapito. In caso di assenza ricevi un invito di ritiro con **7 giorni** di giacenza standard in filiale (**15 giorni** per gli invii dall'estero come questo), prorogabili gratuitamente online; tramite «I miei invii» o la Post-App puoi anche conferire gratuitamente una procura singola o chiedere gratuitamente un secondo recapito nei giorni feriali allo stesso indirizzo.

## Dogana, IVA e dazi su un pacco dal Regno Unito

Dopo la Brexit, la Gran Bretagna non fa più parte dell'Unione europea (solo l'Irlanda del Nord resta soggetta alle regole unionali sulle merci).

### In Italia (Unione europea)

Ogni acquisto spedito dalla Gran Bretagna verso l'Italia è soggetto a sdoganamento e al pagamento dell'IVA italiana (aliquota ordinaria del **22%**) sin dal primo euro, se non è già stata riscossa dal venditore tramite il regime IOSS:

- **Dazio doganale fisso di 3 € per tipologia di prodotto (fino a 150 €):** dal 1° luglio 2026 è stata abolita la franchigia dai dazi sotto i 150 € e, fino al 1° luglio 2028, tutte le vendite a distanza fino a 150 € pagano un dazio forfettario di **3 € per tipologia di prodotto** (per voce doganale/codice TARIC e origine), qualunque sia il regime IVA utilizzato (sia con IOSS sia senza IOSS). Due magliette identiche contano come una sola tipologia di prodotto (3 €), mentre una maglietta e un paio di pantaloni contano come due tipologie di prodotto (6 €). Una merce di origine preferenziale in base a un accordo commerciale dell'UE (come l'accordo UE–Regno Unito) può ottenere il dazio preferenziale solo con dichiarazione ordinaria `H1` quando non è stato usato l'IOSS; tutte le vendite dichiarate tramite IOSS pagano i 3 €. Oltre 150 € si applicano le aliquote della tariffa doganale comune.
- **Union Handling Fee di 2 € per tipologia di prodotto:** dal 1° novembre 2026 si aggiunge una commissione doganale europea fissa e non rimborsabile di **2 € per tipologia di prodotto** su tutte le vendite a distanza importate da Paesi extra-UE, di qualsiasi valore (anche sopra i 150 € e anche con origine preferenziale). Come precisa Poste Italiane, l'IVA all'importazione si calcola sulla somma di valore della merce, spese di trasporto, eventuale dazio e questa commissione europea di 2 €.
- **Diritti Postali e modalità di pagamento:** quando gli oneri non sono stati interamente assolti alla fonte (PDDP), [Poste Italiane](carrier:poste-italiane) applica i propri *Diritti Postali* di sdoganamento: per spedizioni fino a 150 €, **1,00 €** pagando online (o in PDDP), oppure **3,00 €** (valore fino a 22 €) e **7,00 €** (da 22,01 € a 150 €) pagando alla consegna; sopra i 150 €, **15,00 €** in PDDP, **18,00 €** online o **21,00 €** alla consegna (21,00 € anche quando documenti mancanti richiedono la custodia temporanea). Puoi pagare in anticipo nella sezione *Gestione delle spese di importazione* su `poste.it` o nell'App Poste Italiane (l'importo versato a titolo di deposito cauzionale viene rimborsato entro 80 giorni in caso di mancata consegna), oppure in contanti o con carta abilitata alla consegna fino a **999,00 €** al corriere (**258,23 €** al portalettere per la corrispondenza; oltre tali soglie si paga in Ufficio Postale). I regali occasionali tra privati senza carattere commerciale fino a **45,00 €** sono esenti da dazio, IVA, commissione UE di 2 € e Diritti Postali. Vedi anche [pacco fermo in dogana](guide:customs).

### In Svizzera (fuori dall'Unione europea)

La Svizzera non applica né il dazio europeo di 3 € né la commissione europea di 2 €. Come spiega l'Ufficio federale della dogana e della sicurezza dei confini (UDSC), la dicitura `Your item is being held in Customs` di solito significa che [Posta Svizzera](carrier:swiss-post) sta eseguendo lo sdoganamento postale:

- **IVA e dazi svizzeri:** l'IVA all'importazione è dell'**8,1%** (**2,6%** per libri, generi alimentari, riviste o medicinali), calcolata sul valore della merce spese di spedizione incluse, ma gli importi d'imposta fino a **5 CHF** non vengono riscossi (pari a un valore complessivo fino a 62 CHF all'8,1% o 193 CHF al 2,6%). Anche i dazi sul peso lordo inferiori a 5 CHF non vengono riscossi. I regali tra privati sono esenti fino a **100 CHF** (esclusi alcol e tabacchi).
- **Tariffa di Posta Svizzera:** se sono dovuti tributi su una spedizione proveniente da un Paese non UE come il Regno Unito, la Posta addebita un prezzo base di **16,00 CHF** (IVA esclusa) più un supplemento del **3%** del valore della merce; pagando online tramite «I miei invii» o la Post-App si ottiene uno sconto di **1,50 CHF** sul prezzo base.

## Se vivi nel Regno Unito: cartoncino rosso, consegne e dogana britannica

Nel Regno Unito Royal Mail consegna dal lunedì al sabato, di norma entro le 16:30, e anche la domenica in alcune zone, ma non nei giorni festivi (*bank holidays*). Le lettere *2nd Class* stanno passando gradualmente alla consegna a giorni lavorativi alterni entro dicembre 2026, mentre i pacchi (incluso *Tracked 24*) restano consegnati dal lunedì al sabato. Dal 1° aprile 2026 gli obiettivi fissati da Ofcom prevedono il 90% della posta *1st Class* consegnata il giorno lavorativo successivo e il 95% della *2nd Class* entro tre giorni (obiettivi mancati tra aprile e giugno 2026 secondo Citizens Advice).

In caso di assenza a un indirizzo britannico, il portalettere può lasciare il pacco nel tuo luogo sicuro (*safe place*) o a un vicino (le preferenze salvate nell'app durano un anno ed è disponibile un adesivo per escludere i vicini), oppure lascia il cartoncino rosso (*red card*) con indirizzo e orari del *Customer Service Point* per [ritirare il pacco dopo una mancata consegna](guide:missed-delivery) o prenotare una nuova consegna su `royalmail.com`. Se sono dovuti tributi britannici all'importazione, Royal Mail invia una fattura e trattiene il pacco per circa **3 settimane** prima di restituirlo al mittente (regali esenti da IVA fino a 39 £; acquisti fino a 135 £ di solito già tassati alla cassa; rimborsi tramite modulo BOR 286; in Irlanda del Nord i beni extra-UE/extra-UK considerati «a rischio» di entrare nell'UE pagano i dazi applicabili più 2 € per tipologia di prodotto in sterline).

## Pacco Royal Mail bloccato, in ritardo o smarrito

- **Nessun aggiornamento iniziale:** prima della prima scansione fisica Royal Mail può non riconoscere il codice, e `Sender preparing item` indica che non ha ancora il pacco. Se nulla si muove dopo qualche giorno, chiedi conferma al venditore. Leggi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).
- **Fermo su `Item Leaving the UK`:** alcuni giorni di silenzio tra l'uscita dal Regno Unito e l'arrivo al centro di scambio internazionale in Italia o Svizzera sono normali. Prova lo stesso codice `…GB` sul sito di [Poste Italiane](carrier:poste-italiane) o di [Posta Svizzera](carrier:swiss-post) per verificare se c'è uno sdoganamento da pagare.
- **In ritardo o smarrito:** per un acquisto online rivolgiti sempre prima al venditore (nell'UE ha per legge **30 giorni** per consegnare, salvo diverso accordo). Presso Royal Mail possono chiedere un indennizzo sia il mittente sia il destinatario di un invio spedito dal Regno Unito presentando la ricevuta di spedizione (o l'involucro timbrato), tranne per *Tracked 24* e *Tracked 48*, dove può reclamare solo il mittente. Non è previsto rimborso per ritardo su *Tracked 24*, *Tracked 48* né sulle spedizioni internazionali. Per lo smarrimento verso l'Europa occorre attendere **20 giorni lavorativi** dalla data prevista di consegna (25 verso il resto del mondo) e presentare reclamo entro **6 mesi** dalla spedizione (nel Regno Unito: attesa di 11 giorni lavorativi dalla spedizione per *1st Class*, 14 per *2nd Class* e 5 dalla data prevista per *Special Delivery*, più 1 giorno tra il primo lunedì di dicembre e il primo lavorativo di gennaio, entro 80 giorni). Massimali base senza copertura aggiuntiva: 20 £ (*1st/2nd Class*), 75 £ (*Tracked 24/48*), 50 £ (internazionale tracciato o con firma) e 750 £ (*Special Delivery Guaranteed by 1pm*). Royal Mail risponde entro 30 giorni (90 per l'estero). Approfondisci in [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).
- **Segnato come consegnato ma non ricevuto:** chiedi a familiari, vicini o portiere, controlla cassetta e luogo sicuro e avvisa subito il venditore. Vedi [pacco segnato consegnato ma non ricevuto](guide:delivered-not-received).

## Contattare Royal Mail e le poste locali

- **Royal Mail (Regno Unito):** `03457 740 740` (tariffa nazionale britannica, spesso costosa dall'estero: conviene passare dal venditore), dal lunedì al venerdì 8:00–18:30, sabato 8:00–15:00 e domenica 9:00–14:00 (ora britannica). Textphone: `03456 000 606`; Relay UK: `18001` poi `0345 266 0075`. Nel Regno Unito i reclami irrisolti possono passare entro 30 giorni al *Postal Review Panel* e poi all'organismo indipendente POSTRS (`020 7520 3766`, `postrs@cedr.com`).
- **Poste Italiane:** Servizio Clienti allo **803.160** (gratuito da rete fissa) e allo **06.4526.3160** (da rete fissa e mobile secondo il proprio piano tariffario), attivi dal lunedì al sabato dalle 8:00 alle 20:00, esclusi i festivi.
- **Posta Svizzera:** gestione invii e sdoganamento nel portale «I miei invii» su `post.ch` o nella Post-App.

> Hai ricevuto un SMS o un'e-mail che chiede pochi euro per sbloccare una spedizione? Non aprire il link. Poste Italiane ricorda nelle sue guide antifrode di non comunicare mai codici personali, dati delle carte o codici di sicurezza ricevuti via SMS: controlla gli oneri doganali solo entrando direttamente su `poste.it` o nell'App Poste Italiane. Nel Regno Unito gli SMS sospetti si inoltrano al **7726** e le e-mail a `report@phishing.gov.uk`. Scopri di più in [come riconoscere gli SMS truffa sui pacchi](guide:delivery-scam-texts).

## Tracciare un pacco Royal Mail con Peek

[Peek](/) riconosce subito un codice valido come `AB482910576GB` e mostra «Corriere rilevato». Se la cifra di controllo non torna, Peek segnala «La cifra di controllo di questo numero non torna.»: controlla il codice nell'e-mail del venditore, accetta la correzione proposta quando una lettera può essere stata scambiata per una cifra (`O` e `0`), oppure premi «Traccia così com’è» e seleziona Royal Mail tramite «Cambia». Con i riferimenti domestici britannici di 21 e 16 caratteri, Peek interroga prima i servizi di tracciamento e apre per ultimo il sito di Royal Mail nel browser, quindi la risposta può richiedere qualche istante; se non compare nulla o un raro codice di 21 caratteri viene attribuito ad Australia Post, scegli Royal Mail con «Cambia». Un link `royalmail.com` con `trackNumber=` identifica subito Royal Mail in qualsiasi formato, mentre un link `#/tracking-results/` funziona solo con i codici S10 che terminano in `GB`.

Peek consulta il tracciamento di Royal Mail in un browser sul proprio server. Poiché Royal Mail limita fortemente le richieste automatiche, Peek controlla un pacco Royal Mail in autonomia circa una volta all'ora, sia nei giorni normali sia il giorno della consegna, anche quando è indicata una fascia oraria. Vedi lo stato attuale, ogni scansione sulla mappa con la relativa città (senza codici postali), il giorno previsto di consegna (con l'eventuale fascia oraria), l'ora di consegna e il Paese di destinazione. Gli orari britannici vengono convertiti con la nota «Orari nel tuo fuso orario: …». Le righe di consegna mostrano «Consegnato» senza il nome del firmatario, l'indirizzo né la foto, mentre le altre scansioni mantengono il testo originale in inglese. Puoi impostare le notifiche su «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

Per le spedizioni in `…GB` dirette fuori dal Regno Unito, non appena Royal Mail indica il Paese di destinazione (o in base al Paese scelto su Peek), Peek interroga anche le poste nazionali di oltre 60 Paesi — tra cui [Poste Italiane](carrier:poste-italiane) e [Posta Svizzera](carrier:swiss-post) — e passa a «Consegna con …» appena pubblicano nuove scansioni. Anche Poste Italiane viene controllata da Peek circa una volta all'ora, mentre con Posta Svizzera Peek passa alla cadenza più ravvicinata (fino a ogni 10 minuti, o fino a ogni 2 minuti durante il recapito). Il pagamento degli oneri doganali, le deleghe e i reclami si gestiscono direttamente sui canali ufficiali di Royal Mail, Poste Italiane o Posta Svizzera.

## Domande frequenti sul tracciamento Royal Mail

### Chi consegna un pacco Royal Mail in Italia o in Svizzera?

Con lo stesso codice terminante in `…GB`, la consegna passa a [Poste Italiane](carrier:poste-italiane) in Italia e a [Posta Svizzera](carrier:swiss-post) in Svizzera. Non appena l'operatore locale pubblica le proprie scansioni, Peek passa automaticamente a mostrarle.

### Pagherò dazi e IVA su un pacco spedito dal Regno Unito?

Sì, salvo esenzioni come i regali tra privati (fino a 45 € in Italia, 100 CHF in Svizzera). In Italia gli acquisti dalla Gran Bretagna pagano l'IVA (22% ordinario, se non già versata con IOSS), il dazio fisso di 3 € per tipologia di prodotto fino a 150 € (da luglio 2026), la commissione UE di 2 € per tipologia di prodotto (da novembre 2026) e gli eventuali Diritti Postali di Poste Italiane (da 1 € online fino a 150 €). In Svizzera IVA (8,1% o 2,6%) e dazi non vengono riscossi sotto i 5 CHF d'imposta; altrimenti si aggiungono le spese di sdoganamento della Posta.

### Ogni quanto si aggiorna il tracciamento di Royal Mail?

Gli stati avanzano solo a ogni nuova scansione fisica del pacco, e tra `Item Leaving the UK` e l'arrivo in Italia o Svizzera possono passare diversi giorni senza novità. Peek interroga Royal Mail (e anche Poste Italiane dopo la presa in carico in Italia) circa una volta all'ora, mentre per Posta Svizzera controlla fino a ogni 10 minuti (fino a ogni 2 minuti in consegna).

### Cosa significano `Customs charges raised` e `Your item is currently with Customs`?

`Your item is currently with Customs in the destination country.` indica che il pacco è in fase di verifica doganale nel Paese di arrivo. `Customs charges raised` segnala che ci sono oneri da saldare per sbloccare o ricevere il pacco: in Italia puoi pagarli nella sezione *Gestione delle spese di importazione* su `poste.it`, nell'App Poste Italiane o alla consegna; in Svizzera tramite «I miei invii» o la Post-App.

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – giorni e orari di consegna nel Regno Unito, lettere 2nd Class a giorni alterni, Customer Service Points e recapiti telefonici
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – luogo sicuro, vicino, adesivo, preferenze valide un anno, cartoncino rosso e nuova consegna
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – chi può reclamare, prova di spedizione, tempi di attesa e scadenze nel Regno Unito e verso l'Europa, massimali e tempi di risposta
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – reclamo al venditore, Postal Review Panel entro 30 giorni e mediatore POSTRS
- [Citizens Advice: Response to Royal Mail's Quality of Service results, August 2026](https://www.citizensadvice.org.uk/about-us/media-centre/press-releases/citizens-advice-responds-to-royal-mails-latest-quality-of-service-results-august-2026/) – obiettivi Ofcom dal 1° aprile 2026, risultati aprile–giugno 2026 e passaggio del 2nd Class entro dicembre 2026
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – fattura Royal Mail, giacenza di circa 3 settimane, soglie di 39 £ e 135 £, modulo BOR 286 e commissione di 2 € per tipologia di prodotto in Irlanda del Nord
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – segnalazione SMS al 7726 ed e-mail a report@phishing.gov.uk
- [UPU: standard S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – codice a 13 caratteri, cifra di controllo e mantenimento dell'identificativo unico fino alla consegna
- [Commissione europea: Guidance on the temporary EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – dazio di 3 € per tipologia di prodotto qualunque sia il regime IVA, dichiarazione H1 per origine preferenziale senza IOSS e commissione non rimborsabile di 2 € per tipologia di prodotto
- [Commissione europea: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_it) – riforma doganale UE e abolizione della franchigia sotto 150 € dal 1° luglio 2026
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – termine legale di consegna di 30 giorni e calcolo dei 3 € per tipologia di prodotto
- [Poste Italiane: Ricevere spedizioni da estero](https://www.poste.it/ricevere-spedizioni-da-estero) – Cerca Spedizioni, IVA 22%, dazio di 3 € e Union Handling Fee di 2 € per tipologia di prodotto, Diritti Postali da 1 € a 21 €, pagamento online o alla consegna fino a 999 €/258,23 € e franchigia regali fino a 45 €
- [Poste Italiane: FAQ Spedizioni e consegne](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – numeri 803.160 e 06.4526.3160 con orari, tempi di giacenza e delega al ritiro
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – tutela dai tentativi di phishing e protezione di codici e carte
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – significato dello stato doganale, IVA svizzera all'8,1% e 2,6% ed esenzione fino a 5 CHF d'imposta o dazio
- [Posta Svizzera: Sdoganamento all'importazione](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) – regali esenti fino a 100 CHF, prezzo base non UE di 16 CHF, sconto online di 1,50 CHF e supplemento del 3%
- [Posta Svizzera: Invito di ritiro](https://www.post.ch/it/ricezione/invito-di-ritiro) – termini di 7 o 15 giorni, proroga gratuita, procura e secondo recapito
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati riconosciuti, stati Royal Mail, controllo orario e passaggio a Poste Italiane e Posta Svizzera su Peek
:::
