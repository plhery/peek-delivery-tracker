---
title: Tracciamento HayPost: codici AM, stati e contatti
description: Traccia un pacco HayPost Armenia: codici S10 AM, stati IPS internazionali e nazionali, servizi, contatti a Erevan e tracciamento su Peek.
slug: tracciamento-haypost
published: 2026-11-24
updated: 2026-11-24
---

HayPost (*«Haypost» Closed Joint-Stock Company*, in armeno *Հայփոստ*) è l’operatore postale nazionale della Repubblica di Armenia. Fondato nel 1994 e riorganizzato nel 1998 come società per azioni chiusa statale (CJSC), gestisce circa 777 uffici postali in tutta l’Armenia, 23 hub regionali e un centro di scambio postale internazionale, operando nell’ambito dell’Unione Postale Universale (UPU) e di PostEurop. Puoi verificare un invio sulla [pagina Track & Trace di HayPost](https://www.haypost.am/en/track-trace), oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento HayPost: formato S10 e dove trovarli

Il codice di tracciamento è stampato sulla ricevuta postale o inviato nell’e-mail di spedizione del negozio online. HayPost traccia i codici a barre standard UPU S10 di 13 caratteri con cifra di controllo:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `RR482917367AM` | Raccomandata internazionale o piccolo pacco spedito dall’Armenia (`R` + seconda lettera + 9 cifre + `AM`). |
| `CP482917367AM` | Pacco postale spedito dall’Armenia (`CP` + 9 cifre + `AM`). |

Il tracciamento di HayPost legge anche i codici UPU S10 in arrivo dall’estero (che terminano con il codice ISO di due lettere del paese mittente, come `US`, `DE` o `CN`) una volta che la posta di partenza trasmette i dati elettronici o l’invio entra in Armenia. La posta ordinaria non tracciata non ha codice S10 e non può essere seguita online. Consulta [formati dei numeri di tracciamento](guide:tracking-number-formats) se la spedizione utilizza un corriere privato.

## Cosa significano gli stati di tracciamento HayPost

HayPost restituisce righe in inglese dell’Unione Postale Universale (IPS) con il segmento del percorso: `(Otb)` in uscita dal paese mittente, `(Inb)` in ingresso nel paese di destinazione oppure `(EDI-received)` per i dati elettronici trasmessi dalla posta di partenza.

| HayPost mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Accettato dal mittente presso un ufficio postale. | «Preso in carico» |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Insaccato per la spedizione internazionale, segnalato all’estero via dati elettronici o giunto all’ufficio di scambio internazionale. | «In transito» |
| `Send item to customs (Otb)` | Sdoganamento export presso l’ufficio di scambio in uscita prima di lasciare il paese d’origine. | «In transito» |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Fermo o sottoposto a verifica presso la dogana d’importazione di destinazione. | «In dogana» |
| `Return item from customs (Inb)` | Sdoganato e riconsegnato alla rete postale (non è un reso al mittente). | «In transito» |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | In transito nei centri di smistamento nazionali fino all’arrivo all’ufficio postale locale incaricato della consegna. | «In transito» |
| `Send item out for physical delivery (Inb)` | In consegna oggi con il portalettere. | «In consegna» |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Consegna non riuscita; HayPost aggiunge spesso il motivo e l’azione successiva (es. `Addressee not available; Scheduled for further action/processing today`). | «Tentativo di consegna» |
| `Receive item at collection point for pick-up (Inb)` | Pronto per il ritiro presso l’ufficio postale. | «Pronto per il ritiro» |
| `Deliver item (Inb)` | Consegnato e registrato come tale. | «Consegnato» |

Fai attenzione a due diciture facili da fraintendere. Prima di tutto, `Return item from customs (Inb)` è una buona notizia: la dogana ha completato il controllo e ha restituito il pacco alle poste per proseguire il viaggio. Inoltre, `Item was delivered to the postal office (Inb)` (spesso inoltrato da poste estere) significa che l’ufficio postale locale ha preso in custodia il pacco, non che è stato consegnato a te.

## Servizi nazionali, internazionali ed express di HayPost

Attraverso la sua rete di circa 777 uffici postali ([Chi siamo — HayPost](https://www.haypost.am/en/about-us)), HayPost offre:

- **Servizi postali nazionali ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** cartoline, lettere, piccoli pacchi fino a 2 kg, pacchi fino a 20 kg, **Yerevan Express Service (YES)** per consegne tracciate ufficio-ufficio o a domicilio a Erevan (ordini 09:00–16:00 lavorati in giornata; dopo le 16:00 il giorno lavorativo seguente; ordini del sabato il lunedì; linea prenotazioni `+374 10 514 513`) e **HayPost Sprint** per pacchetti e-commerce nazionali tracciati fino a 2 kg.
- **Servizi postali internazionali ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** lettere internazionali, piccoli pacchi fino a 2 kg (lunghezza + larghezza + spessore ≤ 0,9 m, lato massimo ≤ 0,6 m), pacchi fino a 20 kg (ogni lato ≤ 1,05 m), sacchi M fino a 30 kg e posta celere EMS.
- **HayPost Express verso gli Stati Uniti ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** spedizione aerea da qualsiasi ufficio postale armeno verso Los Angeles (uffici di ritiro a Glendale, Van Nuys e Sylmar), fino a 30 kg, con tempo medio di 7–10 giorni lavorativi.

**FAQ di HayPost.** Le [FAQ di HayPost](https://www.haypost.am/en/faq) raccolgono domande su tempi indicativi nazionali, oneri doganali, rimborsi e giacenza degli invii EMS; per i dati applicabili al tuo pacco chiedi all’ufficio postale o a HayPost.

## Pacchi HayPost in ritardo, in dogana o mancanti

Per acquisti online spediti da o verso l’Armenia tramite servizio postale, rivolgiti prima al venditore per rimborsi o rispedizioni.

**Pause tra paesi.** Dopo `Send item abroad (EDI-received)`, la posta internazionale attende il volo, lo smistamento in ingresso e la dogana di importazione nell’ufficio di scambio del paese di destinazione, con possibili pause di diversi giorni. Consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Sdoganamento.** I pacchi in ingresso in Armenia e quelli in uscita con finale `AM` diretti in aree come UE o Stati Uniti passano la dogana di importazione (`Send item to customs (Inb)`). Tieni pronti fattura d’acquisto e numero di tracciamento se la dogana richiede prova del valore. Consulta [pacchi fermi in dogana](guide:customs).

**Consegnato, danneggiato o restituito.** Se risulta `Deliver item (Inb)` ma non hai il pacco, verifica con i familiari e presso l’ufficio di zona (tramite l’[elenco uffici HayPost](https://www.haypost.am/en/our-network)), poi contatta venditore e assistenza HayPost. Consulta [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti HayPost

Recapiti pubblicati su [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (e [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Telefono assistenza clienti:** `+374 10 514-514`.
- **Vendite e servizi aziendali:** `+374 10 544-444`.
- **Linea ordini YES Yerevan Express:** `+374 10 514 513`.
- **E-mail:** `info@haypost.am`.
- **Sede legale:** 65/5 Arshakunyats Ave., Erevan 0061, Repubblica di Armenia.
- **Modulo di contatto:** su [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), con chat dal vivo su [haypost.am](https://www.haypost.am/en).

> Diffida di SMS, messaggi WhatsApp o e-mail che chiedono di aprire un link esterno e inserire i dati della carta per pagare una piccola tariffa di «riconsegna» o «conferma indirizzo» per un pacco HayPost. Verifica il codice S10 direttamente su [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) o chiama `+374 10 514-514`. Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco HayPost con Peek

Per i codici UPU S10 terminanti in `AM` (come `RR482917367AM` o `CP482917367AM`, cifre inventate), [Peek](/) segue automaticamente HayPost come posta d’origine preferita e può seguire anche codici S10 diretti in Armenia una volta scelto HayPost. Poiché HayPost non ha regole dedicate per link di tracciamento in Peek, incolla direttamente il codice S10 di 13 caratteri; per scegliere HayPost manualmente, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella scheda del pacco e scegli HayPost (anche cercando «Armenia Post» o «Հայփոստ»).

Peek legge direttamente l’API JSON pubblica in inglese di HayPost e mostra la fase («Preso in carico», «In transito», «In dogana», «In consegna», «Tentativo di consegna», «Pronto per il ritiro», «Consegnato»), il testo IPS (compresi motivi di mancata consegna e passaggi successivi come `Addressee not available`), ufficio/paese (senza codici postali numerici), paese di destinazione e ora di consegna. Le scansioni armene e le righe `Send item abroad (EDI-received)` usano l’ora di Erevan e quelle di una posta estera il fuso orario di quel luogo (ad esempio ora francese per Parigi); Peek le mostra sull’orologio del tuo dispositivo. Quando HayPost pubblica in anticipo una riga pianificata `Send item abroad (EDI-received)` con data futura (spesso programmata con ore d’anticipo), Peek la nasconde finché non arriva l’orario effettivo. L’API HayPost non restituisce data prevista, peso, nome servizio, dati mittente/destinatario né foto di prova di consegna.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui HayPost mostra `Send item out for physical delivery (Inb)`, ogni 2 minuti dalle 6:00 secondo l’orario dell’ultima scansione HayPost, finché il tracker di HayPost risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di HayPost non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti il giorno della consegna mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a HayPost.», con il link «Chiedi aiuto a HayPost» alla pagina contatti di HayPost. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a HayPost.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti e non può gestire sdoganamenti né aprire segnalazioni presso HayPost al posto tuo.

## Domande sul tracciamento HayPost

### Come si presenta un numero di tracciamento HayPost?

HayPost utilizza codici postali standard UPU S10 di 13 caratteri: due lettere, nove cifre e `AM` alla fine per spedizioni partite dall’Armenia (ad esempio `RR482917367AM` o `CP482917367AM`, cifre inventate). La posta internazionale in arrivo mantiene le due lettere del paese mittente.

### Perché il mio pacco HayPost indica `Return item from customs (Inb)`?

Quello stato IPS significa che la dogana ha sdoganato l’invio e lo ha riconsegnato al servizio postale per proseguire il trasporto — non significa che il pacco torni al mittente.

### HayPost può tracciare pacchi spediti in Armenia dall’estero?

Sì. Qualsiasi codice internazionale tracciato UPU S10 diretto in Armenia può essere controllato su [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) o in Peek non appena arrivano i dati elettronici di spedizione o HayPost lo scansiona in Armenia.

### Come posso contattare l’assistenza clienti HayPost?

Chiama `+374 10 514-514` (o `+374 10 544-444` per le aziende), scrivi a `info@haypost.am` oppure usa il modulo di contatto su [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) o la chat dal vivo sul sito HayPost.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – portale dell’operatore postale nazionale
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – tracciamento online degli invii
- [HayPost: About Us](https://www.haypost.am/en/about-us) – fondato nel 1994, CJSC dal 1998, ~777 uffici postali, 23 hub regionali, membro UPU e PostEurop
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – piccoli pacchi 2 kg, pacchi 20 kg, YES Yerevan Express Service (`+374 10 514 513`) e HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – limiti piccoli pacchi internazionali (2 kg), pacchi (20 kg), sacchi M (30 kg) ed EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – consegna aerea a Los Angeles (30 kg, 7–10 giorni lavorativi)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – elenco degli uffici postali armeni
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Erevan
- [HayPost: Contact Us (armeno)](https://www.haypost.am/am/contact-us) – URL di aiuto ufficiale collegato da Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – indice argomenti FAQ
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – rilevamento S10 `AM`, API JSON `api.haypost.am`, mappatura stati IPS, ora di Erevan, filtro EDI futuri e URL di aiuto
:::
