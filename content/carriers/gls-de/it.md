---
title: Tracciamento GLS Germania: Track-ID, stati e contatti
description: Traccia un pacco GLS Germania (GLS Germany): numero di spedizione o Track-ID, CAP richiesto, stati in inglese, ParcelShop, dogana, reclami e contatti.
slug: tracciamento-gls-germania
published: 2026-11-18
updated: 2026-11-20
---

GLS Germany (*General Logistics Systems Germany GmbH & Co. OHG*) consegna dal lunedì al venerdì e conta in Germania, secondo i dati del gruppo, 9.000 ParcelShop e 200 locker Parcelstation. Che tu aspetti un pacco a un indirizzo tedesco o un ordine spedito dalla Germania verso l'Italia o la Svizzera, puoi seguirlo sulla [pagina europea di tracciamento GLS](https://gls-group.com/EU/en/parcel-tracking) con il numero di collo e il codice postale di consegna, oppure incollare il numero nel riquadro qui sopra. Il codice si trova nell'e-mail di spedizione del negozio, mentre l'avviso di passaggio lasciato da GLS in Germania riporta un Track-ID.

## Numero di pacco GLS e Track-ID: dove trovarli

Controlla per prima cosa l'e-mail di conferma della spedizione inviata dal venditore. Il tracciamento parte non appena il mittente trasmette i dati elettronici a GLS, e una spedizione transfrontaliera affidata a GLS in Germania si segue sulla stessa pagina europea del gruppo (e, secondo il paese di arrivo, anche sulla [ricerca spedizioni di GLS Italy](https://gls-group.com/IT/it/servizi-online/ricerca-spedizioni/) di [GLS Italy](carrier:gls-it), presso [GLS Svizzera](carrier:gls-ch) o presso [La Posta Svizzera](carrier:swiss-post)). L'avviso cartaceo o via e-mail lasciato da GLS in Germania dopo un tentativo a vuoto indica un Track-ID di sei lettere che funziona esattamente come il numero di collo nel cercatore.

Ecco i formati che Peek riconosce, con esempi inventati:

| Numero (inventato) | Che cos'è |
| --- | --- |
| `48291057364` | 11 cifre. Stesso formato presso [GLS Svizzera](carrier:gls-ch), GLS Francia, GLS Austria, GLS Polonia, PostLogistics e altri. |
| `482910573604` | 12 cifre, di cui l'ultima è una cifra di controllo. Stesso formato presso [BRT](carrier:brt), [Mondial Relay](carrier:mondial-relay), Colis Privé, [MRW](carrier:mrw) e altri. |
| `AB482910` | 8 lettere e cifre: anche un Track-ID può avere questa forma. Condiviso con [GLS Svizzera](carrier:gls-ch), GLS Francia, GLS Paesi Bassi e Loggi. |
| `KGLSDE` | Track-ID di sei lettere sull'avviso di passaggio in Germania (condiviso con [GLS Svizzera](carrier:gls-ch)). Se non viene trovato, usa il numero di collo completo. |

Numero rifiutato? Se l'e-mail del negozio nomina un altro corriere, il codice appartiene alla sua rete: consulta [i formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento GLS

La pagina internazionale del gruppo GLS mostra i passaggi in inglese nell'ordine consueto del percorso:

| Stato mostrato da GLS | Cosa significa per te |
| --- | --- |
| `The parcel data was entered into the GLS IT system…`, `The parcel has not been handed over to GLS.` | GLS ha ricevuto i dati elettronici dal mittente, ma non ha ancora fisicamente il pacco. |
| `The parcel was handed over to GLS.` | GLS ha preso in carico il pacco. |
| `The parcel has not been released by customs.`, `Customs Consignment via Customs Portal` | Una spedizione proveniente da fuori UE è ferma in dogana; Peek mostra «In dogana». |
| `The parcel was released by customs.` | La dogana ha svincolato il pacco, che riprende il viaggio; Peek mostra «In transito». |
| `The parcel is in delivery.`, `The parcel is expected to be delivered during the day.` | Caricato sul furgone GLS, con consegna prevista in giornata. |
| `The parcel is ready for collection at the ParcelShop.` | Pronto per il ritiro in un ParcelShop o in un locker Parcelstation. |
| `The parcel has been delivered.` | Consegnato a te o a una persona che lo ha ritirato per te, come un vicino. |

Tra queste tappe compaiono le scansioni di ingresso o uscita nei centri di smistamento e nei depositi GLS. Se l'autista non ha trovato nessuno o non ha potuto consegnare, Peek mostra «Tentativo di consegna». In Germania GLS effettua un secondo tentativo oppure deposita il pacco in un ParcelShop, deposito o Parcelstation nelle vicinanze, indicandone l'indirizzo sull'avviso o per e-mail. Se non viene ritirato entro la scadenza o la consegna è impossibile, il pacco torna al venditore e Peek mostra «Restituito al mittente». Attenzione: GLS registra l'arrivo al ParcelShop come pacco consegnato anche mentre ti sta ancora aspettando lì. Finché `The parcel has not been handed over to GLS.` resta l'ultima riga, il negozio non ha ancora affidato il pacco: contatta il venditore.

## Consegne GLS in Germania, ParcelShop e locker

**Giorni e tempi.** In Germania GLS consegna solo dal lunedì al venerdì, mai di sabato, con tempi stimati non garantiti (solo il servizio *Guaranteed24Service* prevede la consegna il giorno lavorativo successivo nella Germania continentale, escluse le isole). Secondo il gruppo GLS, un pacco nazionale arriva di solito in meno di 24 ore e uno spedito in Europa tra 24 e 96 ore.

**Cassetta delle lettere.** In Germania un pacco piccolo fino a 2 kg, formato massimo DIN C4 e spessore fino a 5 cm può essere inserito direttamente nella cassetta postale.

**In caso di assenza.** In Germania l'autista può consegnare il collo a una persona presente in casa o in azienda oppure a un vicino dello stesso stabile o dell'edificio più vicino, salvo diversa indicazione tua o del mittente; l'avviso cartaceo o l'e-mail riporta il nome del vicino. Altrimenti GLS porta il pacco al ParcelShop, deposito o Parcelstation più vicino. GLS effettua al massimo due tentativi di consegna.

**Modificare la consegna.** Quando GLS propone le opzioni di riconsegna in Germania, puoi scegliere un altro giorno, un altro indirizzo, un ParcelShop o il ritiro in deposito; l'informativa per i consumatori di GLS Germany (gennaio 2018) precisa che queste opzioni sono gratuite per il destinatario. Per i pacchi spediti dall'estero con *FlexDeliveryService*, GLS avvisa per e-mail, comunica data o fascia stimata quando disponibile e consente di modificare la consegna; se al momento dell'acquisto su un sito estero hai scelto direttamente un punto di ritiro (*ShopDeliveryService*), il collo va subito al ParcelShop senza passaggio a domicilio. Un'autorizzazione di deposito in Germania consente inoltre di lasciare il pacco nel punto concordato, con conferma su cartolina o per e-mail.

**ParcelShop e Parcelstation in Germania.** Secondo le condizioni generali per clienti aziendali di GLS Germany, un pacco lasciato in ParcelShop, deposito o Parcelstation dopo un'assenza resta in giacenza almeno sette giorni lavorativi prima del reso al mittente. I locker automatici *Parcelstation* in Germania si aprono con il tuo dispositivo mobile, e puoi opporti al deposito in locker — per una singola spedizione o in modo permanente — seguendo le istruzioni riportate sull'avviso di passaggio.

## Pacco GLS in ritardo, in dogana o danneggiato

Il contratto di trasporto lega GLS al mittente: per ottenere un rimborso o una nuova spedizione rivolgiti sempre al negozio.

**Tracciamento fermo.** Scrivi al venditore e consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato consegnato ma introvabile.** Controlla nel tracciamento se ti aspetta in un ParcelShop, poi verifica cassetta postale ed e-mail per vedere se è indicato un vicino o un luogo di deposito. Altri passi in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Dogana in Italia e in Svizzera.** Tra la Germania continentale e l'Italia non si applicano dazi doganali perché la merce circola nel territorio doganale e IVA dell'Unione europea (in Germania Helgoland e Büsingen e in Italia Livigno restano fuori dal territorio doganale e IVA dell'UE, mentre Campione d'Italia fa parte del territorio doganale dell'UE ma è fuori dall'area IVA). Passano invece in dogana le spedizioni provenienti da fuori UE — oppure dirette in Svizzera, dove l'UDSC precisa che eventuali domande sui costi di sdoganamento vanno rivolte al trasportatore: GLS segnala che dazi, IVA all'importazione e di norma una spesa di sdoganamento vanno saldati prima della consegna secondo le condizioni scelte dal venditore. Per gli acquisti online provenienti da fuori UE e diretti nell'Unione europea, dal 1° luglio 2026 si applica un dazio di 3 € per categoria di articolo sulle spedizioni fino a 150 €, cui si somma dal 1° novembre 2026 la commissione di gestione dell'Unione di 2 € per categoria di articolo qualunque sia il valore, dovuta dal dichiarante doganale. Approfondisci in [pacco fermo in dogana](guide:customs).

**Pacco danneggiato (diritto tedesco).** Per le spedizioni soggette al diritto tedesco (Codice di commercio tedesco, § 438 HGB), i danni visibili vanno segnalati alla consegna (basta comunicarlo all'autista), i danni occulti entro 7 giorni e il ritardo entro 21 giorni in forma testuale (e-mail o lettera); fa fede l'invio entro il termine. Sia il destinatario sia il mittente possono segnalare perdita o avaria. Negli acquisti da professionista, il venditore sopporta il rischio del trasporto salvo che tu abbia incaricato autonomamente un vettore non proposto dal negozio (Codice civile tedesco, § 475 BGB): avvisa quindi per primo il venditore.

**Conciliazione in Germania.** Una controversia per perdita o danneggiamento non risolta con GLS Germany può essere sottoposta gratuitamente alla *Schlichtungsstelle Post* della Bundesnetzagentur, da mittenti o destinatari, per importi compresi tra 10 € e 2.500 €; GLS è tenuta a partecipare quando la domanda parte da un consumatore.

**Diritto di recesso (*Widerruf*).** Non ritirare il pacco non equivale a recedere dall'acquisto. Nei contratti a distanza o fuori dai locali commerciali regolati dal diritto tedesco hai di norma 14 giorni dal ricevimento dei beni per dichiarare chiaramente il recesso al venditore senza dover fornire motivazioni (Codice civile tedesco, §§ 355 e 356 BGB).

## Assistenza clienti GLS Germania e contatti

Per un ordine online il primo interlocutore resta il negozio. I recapiti nazionali di GLS Germany sono pubblicati sul sito tedesco `gls-pakete.de`. Per domande sullo stato di una spedizione, il gruppo GLS invita a rivolgersi al servizio clienti GLS del proprio paese (il cui numero figura sul sito GLS nazionale, ad esempio presso [GLS Italy](carrier:gls-it) o [GLS Svizzera](carrier:gls-ch)); anche il [modulo di contatto del gruppo GLS](https://gls-group.com/GROUP/en/contact-form/) accetta richieste dei destinatari. Se hai spedito tu stesso un pacco tramite GLS-Pakete.de o l'app tedesca, le condizioni dell'agosto 2023 indicano `versand-service@gls-germany.com` e il numero `+49 6677 646907040`.

> **Avvisi di sicurezza e pagamenti GLS.** Il gruppo GLS mette in guardia da e-mail, SMS e siti falsi a suo nome. Il sito principale è `gls-group.com`, e solo quel dominio e i siti nazionali collegati da esso — come `gls-pakete.de` — sono autentici. GLS non chiede mai per e-mail ai destinatari di pagare la merce con carta, bonifico o pagamento online. Per un pacco proveniente da fuori UE, invece, GLS o un agente autorizzato può chiedere per e-mail, SMS o lettera il pagamento di dazi e imposte. Le spedizioni in contrassegno (*GLS Cash Service*) vengono consegnate solo dietro pagamento in contanti all'autista, e GLS sconsiglia di pagare un contrassegno inatteso, per te o per un vicino. Inoltra le e-mail sospette come allegato a `security@gls-group.com`.

## Tracciare un pacco GLS Germania con Peek

[Peek](/) interroga i corrieri compatibili con i formati qui sopra. Il servizio del gruppo GLS risponde sia per i pacchi tedeschi sia per quelli svizzeri: dall'Italia Peek seleziona GLS Germany («GLS Germany ha questo pacco.»), oppure GLS Francia quando la spedizione è seguita senza CAP sulla rete francese. In Svizzera o nel Liechtenstein (in base alla regione del telefono o alla connessione web) Peek seleziona invece [GLS Svizzera](carrier:gls-ch) con un campo CAP a 4 cifre: per un pacco diretto in Germania premi «Cambia» e scegli GLS Germany. Incollare un link `gls-pakete.de` o un link `gls-group` con `/DE/`, `/EU/` o `/GROUP/` seleziona direttamente GLS Germany (mentre un link `gls-group` con `/IT/` seleziona [GLS Italy](carrier:gls-it)).

Poiché GLS richiede il codice postale del destinatario per mostrare i dettagli delle scansioni, il campo «Codice postale di consegna» è obbligatorio, e se GLS rifiuta il CAP inserito («GLS Germany non ha trovato questo pacco con il codice postale …») puoi correggerlo con «Modifica codice postale». È accettato il formato postale di qualsiasi paese; viene inviato a GLS e, quando necessario, ai servizi di tracciamento.

Vedrai così lo stato, i passaggi in inglese così come GLS li scrive, città e paese di ogni scansione sulla mappa, il giorno previsto, il peso, l'ora di consegna e, in caso di deposito in ParcelShop, nome e indirizzo sotto «Pronto per il ritiro». Nome, indirizzo, recapiti, firma e riferimento del mittente vengono scartati. Gli orari sono letti sull'ora tedesca e convertiti nel fuso del tuo dispositivo; su `peektracker.com` e nelle app per iPhone e Android una riga sotto la cronologia lo indica («Orari nel tuo fuso orario: …»). Peek controlla GLS circa una volta all'ora, giorno di consegna incluso (ogni 4 ore dopo un controllo fallito), e aggiornare manualmente non anticipa la verifica. Solo i pacchi affidati da GLS alla [Posta Svizzera](carrier:swiss-post) passano automaticamente a un secondo corriere; quando un altro corriere prende in carico la consegna, Peek segue il ritmo di chi consegna il pacco. Notifiche a scelta: «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti sul tracciamento GLS Germania

### Dove si trova il mio pacco GLS?

Il tracciamento GLS lo mostra appena inserisci il numero di collo e il codice postale di consegna: `The parcel is in delivery.` significa che oggi è sul furgone per la consegna, e `The parcel is ready for collection at the ParcelShop.` che ti aspetta al punto di ritiro.

### Che cos'è il Track-ID di GLS?

È il codice di sei lettere riportato sull'avviso di passaggio cartaceo o elettronico lasciato da GLS in Germania dopo un tentativo di consegna. Funziona come il numero di pacco nella ricerca spedizioni.

### Quanto impiega GLS a consegnare dalla Germania?

GLS indica tempi stimati non garantiti (salvo *Guaranteed24Service* entro il giorno lavorativo successivo nella Germania continentale, isole escluse). Secondo il gruppo GLS, un pacco nazionale arriva di solito in meno di 24 ore e uno in Europa tra 24 e 96 ore.

### Per quanto tempo GLS tiene un pacco al ParcelShop?

In Germania, dopo una mancata consegna a domicilio, il ParcelShop, il deposito o la Parcelstation conserva il pacco almeno sette giorni lavorativi prima di restituirlo al mittente.

### GLS Germany è affidabile?

Sì: GLS Germany è *General Logistics Systems Germany GmbH & Co. OHG*, con sede a Neuenstein in Assia. Circolano però messaggi falsi a suo nome: verifica sempre che i link portino a `gls-group.com` o ai siti nazionali ufficiali collegati.

:::sources
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – pagina europea di tracciamento del gruppo GLS, CAP e Track-ID sull'avviso
- [GLS Italy: ricerca spedizioni](https://gls-group.com/IT/it/servizi-online/ricerca-spedizioni/) – pagina italiana di tracciamento del gruppo GLS
- [GLS: About us](https://gls-group.com/GROUP/en/about-us/) – 9.000 ParcelShop e 200 locker in Germania
- [GLS: Parcel, Logistics & Express](https://gls-group.com/GROUP/en/our-business/parcels/) – tempi nazionali (< 24 h) ed europei (24–96 h), dazi, imposte e spese di sdoganamento prima della consegna
- [GLS: B2C Services](https://gls-group.com/GROUP/en/our-business/cross-border-services/b2c/) – FlexDeliveryService e ShopDeliveryService per spedizioni internazionali
- [GLS: Contact](https://gls-group.com/GROUP/en/contact-form/) – modulo di contatto del gruppo e rimando all'assistenza nazionale
- [GLS Germany: General Terms and Conditions for business customers (PDF)](https://gls-group.com/DE/media/downloads/AGB_GLS_Germany_2025-01_EN_2024-10-25_FINAL-WEB.pdf) – nessuna consegna il sabato, piccoli pacchi in cassetta, vicini, due tentativi, sette giorni lavorativi in ParcelShop, Parcelstation e Guaranteed24Service
- [GLS Germany: Terms for GLS-Pakete.de and the app (PDF)](https://gls-group.com/DE/media/downloads/AGB_GLS-paketeApp_EN_2023-08-21.pdf) – ragione sociale a Neuenstein, avviso di passaggio, autorizzazione di deposito, Schlichtungsstelle Post e contatti mittenti
- [GLS Germany: Consumer information on delivery options (PDF)](https://gls-group.com/DE/media/downloads/180130_Verbraucherinformation_Umverfuegung_DE_EN.pdf) – gratuità delle opzioni di consegna per i destinatari
- [GLS: Security advice](https://gls-group.com/GROUP/en/security-advice/) – siti ufficiali, truffe, dazi extra-UE, GLS Cash Service e segnalazioni a `security@gls-group.com`
- [UDSC: costi d'imposizione e tracciamento dei pacchi](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – spese di sdoganamento riscosse dal trasportatore in Svizzera
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – conciliazione postale gratuita tra 10 € e 2.500 €
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – termini per segnalare danni (consegna / 7 giorni) e ritardo (21 giorni)
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – passaggio del rischio nella vendita ai consumatori
- [Gesetze im Internet: § 355 BGB](https://www.gesetze-im-internet.de/bgb/__355.html) – diritto di recesso entro 14 giorni senza obbligo di motivazione
- [Gesetze im Internet: § 356 BGB](https://www.gesetze-im-internet.de/bgb/__356.html) – decorrenza del recesso dal ricevimento dei beni
- [Commissione europea: ambito territoriale doganale e IVA](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – posizione di Helgoland, Büsingen, Livigno e Campione d'Italia
- [Commissione europea: riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € fino a 150 € dal 1° luglio 2026 e commissione di 2 € dal 1° novembre 2026
- [Commissione europea: linee guida sul dazio di 3 € e sulla commissione di gestione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – commissione di 2 € per categoria di articolo qualunque sia il valore, dovuta dal dichiarante
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento dei formati GLS, CAP obbligatorio, passaggio a Swiss Post e controllo orario
:::
