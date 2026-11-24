---
title: Tracciamento SkyPostal: codici SKAMZUS e SP, stati e aiuto
description: Traccia un pacco SkyPostal dagli USA all’America Latina: codici SKAMZUS e SP...BR, hub di Miami, dogana (CEN, HTI, DEL) ed email per paese.
slug: tracciamento-skypostal
published: 2026-11-24
updated: 2026-11-24
---

**SkyPostal** è una rete di consegna pacchi nazionale negli Stati Uniti e transfrontaliera verso l’America Latina con sede a Miami, fondata da Albert P. e A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Operando dal proprio principale hub statunitense di esportazione a Miami, Florida, SkyPostal movimenta pacchi e-commerce per grandi rivenditori e marketplace — tra cui Amazon, Temu ed eBay — verso Brasile, Messico, Colombia, Perù, Ecuador, Cile e Costa Rica, gestendo lo sdoganamento export e import e affidando la consegna finale a domicilio a partner regionali latinoamericani. Puoi controllare una spedizione su [tracking.skypostal.com](https://tracking.skypostal.com/) oppure incollare il codice o il link SkyPostal nel riquadro qui sopra.

## Codici di tracciamento SkyPostal (formati `SKAMZUS` e `SP...BR`)

SkyPostal utilizza diversi formati transfrontalieri a seconda del venditore di origine e del paese di destinazione, più comunemente codici `SKAMZUS` da 17 caratteri (molto usati per le esportazioni Amazon USA verso l’America Latina) e codici `SP...BR` da 15 caratteri per il Brasile:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `SKAMZUS0482917365` | Codice SkyPostal da 17 caratteri per esportazioni Amazon USA (`SKAMZUS` + 10 cifre). |
| `SP04829173605BR` | Codice SkyPostal transfrontaliero da 15 caratteri per il Brasile (`SP` + 11 cifre + `BR`). |

Se selezionato manualmente, il tracker di SkyPostal ricerca anche altre lettere di vettura commerciali e riferimenti partner alfanumerici da 8 a 40 caratteri contenenti almeno una cifra. Vedi la guida ai [formati dei codici di tracciamento](guide:tracking-number-formats).

## Significato degli stati di tracciamento SkyPostal

SkyPostal associa un codice evento di 3 lettere a ogni passaggio — dalla pre-dichiarazione elettronica e dall’ingresso all’hub di Miami ai voli, alla dogana di destinazione e alle scansioni inoltrate dal corriere locale:

| Codice e dicitura SkyPostal | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Dati ordine, validazione codice doganale HS e codice fiscale dell’acquirente ricevuti prima dell’arrivo all’hub SkyPostal. | «Etichetta creata» |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Pacco fisico ricevuto presso il centro di esportazione SkyPostal a Miami. | «Preso in carico» |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Lavorato a Miami, partito in aereo e trasferito dalla compagnia aerea all’operatore di terra nel paese di destinazione (non consegna a domicilio). | «In transito» |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Entrato in sdoganamento a destinazione o in attesa di rilascio nel magazzino doganale dell’aeroporto. | «In dogana» |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Sdoganato, affidato al corriere locale latinoamericano e in transito negli hub locali. | «In transito» |
| `OFD` (`OUT FOR DELIVERY`) | In consegna oggi con il corriere partner a destinazione. | «In consegna» |
| `PIK` (`AVAILABLE FOR PICKUP`) | Pronto per il ritiro da parte del destinatario presso un punto locale o ufficio postale. | «Pronto per il ritiro» |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Tentativo di consegna fallito perché nessuno era disponibile al ritiro. | «Tentativo di consegna» |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Fermo per problema con il codice fiscale, indirizzo errato o incompleto, etichetta respinta o consegna rifiutata. | «Problema segnalato» |
| `DEL` (`DELIVERED`) | Consegnato al destinatario dal partner locale. | «Consegnato» |

Nota che `DTP` (*Delivered from airline to operator*) indica semplicemente che la compagnia aerea ha consegnato il sacco aereo all’operatore di terra in aeroporto — Peek mantiene `DTP` su «In transito» anziché segnarlo consegnato. Gli eventi interni di fatturazione (`CUT`, `PAY`, `FSC`) non cambiano fase.

## Dogana in America Latina, codici fiscali (`HTI`) e consegne locali

Le spedizioni transfrontaliere dagli Stati Uniti all’America Latina con SkyPostal richiedono la stretta conformità doganale e fiscale:

- **Codice fiscale dell’acquirente (`TIC` / `HTI`):** le scansioni SkyPostal registrano l’acquisizione del codice fiscale (`TAX ID COLLECTED`) e l’eventuale blocco per irregolarità (`HOLD FOR PROBLEMATIC TAX ID`). Se compare `HTI`, rivolgiti al venditore o scrivi all’email di assistenza SkyPostal del tuo paese indicando il numero completo. Vedi la guida ai [pacchi fermi in dogana](guide:customs).
- **Consegna locale dell’ultimo miglio:** appena appaiono `CCH` (*Customs cleared*) e `TTC` (*Tendered to local carrier*), un corriere locale effettua la consegna e il tracker SkyPostal mostra le sue scansioni con la città del partner.

## Pacco SkyPostal in ritardo, fermo o smarrito

- **Pause silenziose tra Miami e la dogana di destinazione:** il tracciamento si ferma spesso tra `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) e `CCH` (`CUSTOMS CLEARED`) durante il controllo doganale delle spedizioni consolidate. Vedi la guida sul [tracciamento fermo](guide:tracking-not-updating).
- **Errori di indirizzo (`ICA`) o consegne rifiutate (`DRF`):** contatta per primo il tuo marketplace (Amazon, Temu, eBay, ecc.) per nuova spedizione o rimborso e scrivi all’indirizzo email SkyPostal del tuo paese con il codice completo se il pacco è ancora fermo in sede locale. Vedi anche [consegnato ma non ricevuto](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti SkyPostal ed email per paese

Su [SkyPostal Customer Service](https://www.skypostal.com/customer-service), SkyPostal chiede ai destinatari finali di scrivere **via email** includendo sempre il numero di tracciamento completo e i dettagli della richiesta (tempo di risposta pubblicato: **3–5 giorni lavorativi**):

- **Brasile:** `Brazilcs@skypostal.com`
- **Messico:** `Mexicocs@skypostal.com`
- **Colombia:** `Colombiacs@skypostal.com`
- **Perù:** `Perucs@skypostal.com`
- **Ecuador:** `Ecuadorcs@skypostal.com`
- **Tutti gli altri paesi:** `customerservice@skypostal.com`
- **Sede negli Stati Uniti:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Stati Uniti ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Diffida di falsi messaggi WhatsApp o SMS che richiedono un pagamento fiscale urgente su un sito sconosciuto per sbloccare un’importazione in America Latina. Verifica ogni eventuale tassa doganale reale nella pagina del tuo ordine o sul portale ufficiale della dogana o delle poste nazionali. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco SkyPostal con Peek

[Peek](/) rileva SkyPostal automaticamente come unico candidato quando inserisci un codice `SKAMZUS` (`SKAMZUS0482917365`, inventato) o `SP...BR` (`SP04829173605BR`, inventato) e mostra «SkyPostal ha questo pacco.» non appena il tracker SkyPostal restituisce lo storico; Peek riconosce anche direttamente i link incollati `tracking.skypostal.com/?trck_number=` e `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Per altri codici commerciali, tocca «Cambia» mentre aggiungi il pacco oppure «Cambia corriere» nella sua schermata e scegli **SkyPostal** (anche cercando «Sky Postal»).

Peek legge il tracker SkyPostal e mostra la fase («Etichetta creata», «Preso in carico», «In transito», «In dogana», «In consegna», «Pronto per il ritiro», «Tentativo di consegna», «Problema segnalato», «Consegnato»), il testo inglese della scansione e la città del partner oppure il codice aeroportuale SkyPostal. I codici a 3 lettere determinano la fase e nessuna app Peek li visualizza. SkyPostal indica ogni scansione senza scarto da UTC: Peek la data sul fuso del luogo (ora di New York per `MIA`, di San Paolo per `GRU`, di Bogotà o Lima per le relative scansioni), una scansione senza luogo sul fuso di quella vicina, e mostra tutti gli orari con l’ora del tuo dispositivo. Le scansioni di un partner possono arrivare due volte da flussi distanti ore, quindi una fase precedente comparsa dopo una consegna non la annulla. Peek non salva prova di consegna, commenti né il codice del partner, e nessuna app Peek mostra chi ha firmato. Il tracker SkyPostal non fornisce data stimata di consegna, peso né orario di consegna separato.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui SkyPostal mostra `OFD` (`OUT FOR DELIVERY`), ogni 2 minuti dalle 6:00 sull’orologio della scansione più recente finché il tracker SkyPostal risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di SkyPostal non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a SkyPostal.», con il link «Chiedi aiuto a SkyPostal» alla pagina di assistenza clienti. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a SkyPostal.»

Puoi scegliere gli avvisi «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo gli aggiornamenti di tracciamento e non può inviare codici fiscali, sdoganare in America Latina o contattare SkyPostal per te.

## Domande sul tracciamento SkyPostal

### Che aspetto ha un codice di tracciamento SkyPostal?

I codici SkyPostal più comuni sono `SKAMZUS` da 17 caratteri (`SKAMZUS0482917365`, inventato) per esportazioni Amazon USA e `SP...BR` da 15 caratteri (`SP04829173605BR`, inventato) per il Brasile, ma SkyPostal accetta anche altri riferimenti commerciali da 8 a 40 caratteri.

### Cosa significa `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) su SkyPostal?

Non significa che il pacco è stato consegnato a casa tua. `DTP` indica che la compagnia aerea ha consegnato la spedizione consolidata all’operatore di terra dell’aeroporto di destinazione prima dello sdoganamento (`CEN`) e della consegna al corriere locale (`TTC`).

### Cosa significa `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

La dogana di destinazione ha rilevato un codice fiscale mancante o non corrispondente del destinatario (come il CPF in Brasile o l’RFC in Messico). Contatta il venditore e scrivi all’email di supporto SkyPostal del tuo paese su [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Come contatto l’assistenza clienti SkyPostal?

Scrivi all’indirizzo email del paese di destinazione (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` o `customerservice@skypostal.com`) indicando il numero di tracciamento; SkyPostal dichiara risposte in 3–5 giorni lavorativi.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – spedizioni nazionali USA e transfrontaliere in America Latina, sede Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – storia aziendale e rete privata di consegna in America Latina
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – email di supporto per paese (Brasile, Messico, Colombia, Perù, Ecuador, generale), nota partner Amazon/Temu/eBay e risposta in 3–5 giorni lavorativi (link di aiuto in Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – tracker pubblico singolo pacco
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – regole rilevamento `SKAMZUS` e `SP...BR`, regole link `tracking.skypostal.com`, mappa codici a 3 lettere (`DRC`–`DEL`, `DTP`, `HTI`) e fusi orari delle scansioni
:::
