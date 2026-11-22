---
title: Tracciamento Australia Post: codici, stati e locker
description: Traccia un pacco Australia Post: dove trovare l'article ID, cosa significano gli stati in inglese, ritiro in ufficio postale o Parcel Locker, dogana e contatti.
slug: tracciamento-australia-post
published: 2026-11-21
updated: 2026-11-21
---

Australia Post è il servizio postale nazionale dell'**Australia**. Puoi seguire una spedizione senza registrarti su [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), nell'app AusPost o incollando il codice nel riquadro qui sopra. Il numero viene fornito dal mittente: si trova nella conferma d'ordine del negozio, in un'e-mail di Australia Post o sulla ricevuta dell'ufficio postale australiano.

## Codice di tracciamento Australia Post: dove trovarlo e formato

Australia Post lo chiama numero di tracciamento o identificativo dell'articolo (*tracking number, or article ID*). Di solito è in uno di questi punti:

- **Acquisto online:** nella conferma d'ordine o fattura del venditore; se il mittente ha comunicato il tuo indirizzo e-mail ad Australia Post, quest'ultima ti invia direttamente il numero e gli aggiornamenti.
- **Spedizione da un ufficio postale in Australia:** spesso sullo scontrino di accettazione.
- **Busta prepagata (*prepaid satchel*):** sul codice a barre e sul tagliando adesivo staccabile.

Australia Post non può cercare un pacco per nome o indirizzo: se non hai il codice, chiedilo al mittente. I pacchi abbinati all'e-mail o al cellulare del tuo profilo MyPost compaiono da soli nell'account e nell'app. Su `auspost.com.au/mypost/track` puoi inserire fino a 10 codici contemporaneamente.

Australia Post non pubblica uno schema ufficiale dei propri codici; ecco i formati riconosciuti da Peek (con caratteri inventati):

| Formato (esempio) | Che cos'è |
| --- | --- |
| `33X482910573000482913` | Identificativo articolo da 21 caratteri: 3 lettere o cifre, 9 cifre, `000` e 6 cifre. |
| `2933X482910573000482914` | Lo stesso identificativo preceduto da 2 cifre (23 caratteri). |
| `94004829105736000482916` | 23 cifre che iniziano con `9400` e hanno `000` prima delle ultime 6 cifre: sempre Australia Post. |
| `29ABC4829105` | Numero di spedizione (*consignment number*) da 12 caratteri. |
| `ABC4829105` | Numero di spedizione da 10 caratteri. |
| `ABCZ48291057` | Numero StarTrack da 12 caratteri: 3 lettere o cifre, `Z` e 8 cifre. |
| `0048291057364918273643` | Codice a barre prepagato da 22 cifre che inizia da `00` a `03` (con cifra di controllo GS1). |
| `R482910573649182736491827` | `R` seguita da 24 cifre (25 caratteri). |
| `EE482910576AU` | Spedizione internazionale EMS, pacco o lettera tracciata: `C`, `E` o `L`, una lettera, 9 cifre e `AU`. |
| `RR482910576AU` | Altro codice postale internazionale che termina in `AU` (ad esempio raccomandata). |

Codici simili da distinguere: 2 lettere e 10 cifre (`AB4829105736`) appartengono ad Aramex Australia o Aramex New Zealand, mentre 22 cifre che iniziano con `9400` (`9400482910573649182736`) sono di [USPS](carrier:usps). Se il codice non viene riconosciuto, potrebbe contenere un errore o non essere ancora stato scansionato: Australia Post suggerisce di riprovare dopo 24 ore. Vedi anche [formati dei numeri di tracciamento](guide:tracking-number-formats) e [come trovare il numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di tracciamento di Australia Post

Sopra l'elenco delle scansioni, Australia Post mostra una fase sintetica in inglese:

| Stato di Australia Post | Cosa significa per la spedizione | Peek mostra |
| --- | --- | --- |
| `Label created by sender` | Il mittente ha creato l'etichetta; Australia Post non ha ancora il pacco. | «Etichetta creata» |
| `Pending` | Non ancora scansionato nella rete di Australia Post; di norma si aggiorna entro 24–48 ore. | «In attesa di Australia Post» (prima della prima scansione) |
| `We've got it` | Australia Post ha preso in carico il pacco. | «Preso in carico» |
| `It's on its way` | Spedito, lavorato e in movimento tra i centri di smistamento. | «In transito» |
| `International arrival - awaiting clearance` | Pacco arrivato dall'estero: in attesa dello sdoganamento australiano. | «In dogana» |
| `Possible delay` | Maltempo, imprevisti, dati incompleti o dogana rallentano il viaggio, ma il pacco prosegue. | In base al codice della scansione, ad esempio «In transito» |
| `It's coming today` / `Onboard for delivery` | A bordo del mezzo per la consegna in giornata. | «In consegna» |
| `Attempted delivery` | Nessuno era presente o l'autista non ha potuto accedere all'indirizzo. | «Tentativo di consegna» |
| `Awaiting collection` | In attesa di ritiro nell'ufficio postale o nel *Parcel Locker* indicato dalla scansione. | «Pronto per il ritiro» |
| `Delivered` / `Delivered - Left in a safe place` | Consegnato all'indirizzo o lasciato in un punto sicuro (*Safe Drop*). | «Consegnato» |
| `Returning to sender` | Non ritirato entro i termini, destinatario sconosciuto, rifiutato o danneggiato: torna al mittente. | In base al codice della scansione |

Se `Pending` o `Ready for processing` resta fermo per più di una settimana, chiedi al mittente se ha effettivamente spedito il pacco. In Australia le spedizioni tra Stati passano per grandi hub come Redbank (QLD), Chullora (NSW) o Sunshine West (VIC), quindi una scansione lontana da partenza e arrivo è normale. Su un pacco proveniente dall'estero verso l'Australia, `It's on its way` segnala che è atterrato in Australia e rimane attivo finché non sale sul furgone di consegna.

In Australia i portalettere consegnano di solito dalle 7:00 alle 17:00 dal lunedì al venerdì; un pacco non consegnato entro le 17:00 viene in genere messo in priorità il giorno lavorativo successivo. Con `It's coming today`, in alcune zone arriva anche un SMS con una fascia di 2 ore.

`Contact sender` indica che il pacco è tornato indietro per dati errati o incompleti oppure dopo 10 giorni lavorativi senza ritiro; `Returned` significa che è rientrato al mittente, da contattare per organizzare una nuova spedizione.

## Mancata consegna, ufficio postale e Parcel Locker in Australia

Le regole seguenti valgono per il recapito in **Australia**:

Se non c'è nessuno in casa e non è possibile lasciarlo in un luogo sicuro (*Safe Drop*), il pacco viene portato in un ufficio postale vicino oppure, se sul pacco figura il tuo numero di cellulare, in un armadietto *Parcel Locker* (riceverai un codice di accesso). Gli iscritti a MyPost ricevono un avviso via e-mail, app o SMS; gli altri possono ancora trovare il cartoncino *Sorry We Missed You*. Leggi anche [mancata consegna](guide:missed-delivery).

Un ufficio postale australiano tiene il pacco in giacenza per **10 giorni lavorativi** prima di rispedirlo al mittente. Non è prevista una nuova consegna a domicilio dall'ufficio postale; trasferirlo a un altro ufficio costa **7,55 AU$** da pagare al ritiro (il modulo di trasferimento riporta ancora 7,20 AU$), e se non sai perché sia finito in quell'ufficio puoi inviare una segnalazione di recapito chiedendo l'esenzione dalla tariffa. Porta un documento con foto, oppure due documenti senza foto di cui uno con il tuo indirizzo. Un delegato può ritirare con l'autorizzazione dall'app, da un modulo o dal cartoncino, più il proprio documento.

I *Parcel Locker* sono gratuiti e, nella maggior parte delle postazioni, aperti 24/7. Il ritiro va effettuato entro **2 giorni lavorativi** (48 ore esclusi fine settimana e festivi) con il codice ricevuto o il QR dell'app; scaduto il termine (dopo un promemoria via e-mail 24 ore prima), il pacco passa in un ufficio postale vicino per 10 giorni lavorativi.

Con un account gratuito MyPost puoi reindirizzare un pacco associato a un altro indirizzo finché non è sul mezzo di consegna (calcola un giorno lavorativo in più) e impostare *Safe Drop* fino a un'ora prima.

## Tracciamento fermo, pacco in ritardo o segnato consegnato ma assente

L'assenza di scansioni per qualche giorno non significa che il pacco sia bloccato: Australia Post precisa che continua a viaggiare e che la maggior parte dei pacchi in ritardo arriva entro **5 giorni lavorativi** dalla data prevista. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

Australia Post invita chi riceve a sentire prima il mittente per verificare codice e indirizzo e fargli aprire un reclamo. Se non basta, compila il modulo online per invii in ritardo o smarriti (serve un account gratuito MyPost) trascorsi **10 giorni lavorativi** dalla data attesa. In Australia i pacchi provenienti dall'estero possono restare fino a **15 giorni** nella dogana australiana — di più se sono dovuti dazi — e dogana o quarantena scrivono al destinatario in caso di fermo prolungato; un reclamo per ritardi internazionali va aperto presso le poste del Paese di partenza tramite il mittente. Approfondisci in [pacco fermo in dogana](guide:customs).

**Segnato consegnato ma non trovato.** Controlla i punti riparati intorno all'abitazione, chiedi a familiari o portineria, verifica la foto di consegna nell'app o nel modulo online e invia la segnalazione; se la foto mostra il pacco consegnato al tuo indirizzo ed è sparito, sporgi denuncia alla polizia. Per un pacco danneggiato in Australia, portalo con l'imballaggio in un ufficio postale abilitato alle perizie danni. La maggior parte dei servizi include fino a **100 AU$** per smarrimento o danneggiamento (di solito corrisposti al mittente per i pacchi nazionali smarriti e al destinatario per quelli danneggiati), entro **6 mesi** dalla spedizione; per gli acquisti online contatta prima il venditore. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Contatti Australia Post e assistenza

Recapiti pubblicati da Australia Post:

- **Telefono:** `13 76 78` (`13POST`) dall'Australia o `+61 3 8340 7239` dall'estero, dalle 8:00 alle 18:00 dal lunedì al venerdì esclusi i giorni festivi nazionali australiani.
- **Online:** moduli per pacchi in ritardo/smarriti e problemi di consegna, oltre a chatbot e operatori in chat su [auspost.com.au/help](https://auspost.com.au/help).
- **Di persona in Australia:** oltre 4.100 uffici postali possono assistere nella presentazione di un reclamo.

StarTrack, il ramo corrieri e merci di Australia Post, ha un numero dedicato (`13 23 45`) e propri moduli, ma le sue spedizioni si tracciano sulla stessa rete e compaiono anche nell'app AusPost.

> Australia Post ricorda di non chiamare, mandare SMS né inviare e-mail chiedendo dati personali o bancari non autorizzati né pagamenti inattesi (come presunte spese di riconsegna o verifiche del CAP), nemmeno quando il mittente visualizzato è *AusPost*. Inoltra i messaggi sospetti a `scams@auspost.com.au` e consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare i pacchi Australia Post con Peek

[Peek](/) riconosce subito Australia Post per gli identificativi articolo da 21 e 23 caratteri e per i numeri internazionali EMS, pacco o posta tracciata che finiscono in `AU` (`C…AU`, `E…AU`, `L…AU`) con cifra di controllo valida, mostrando «Corriere rilevato» con «Cambia». I numeri di spedizione (inclusi quelli StarTrack), i codici prepagati da 22 cifre e i codici `R` da 25 caratteri non bastano da soli: durante l'aggiunta Peek interroga i corrieri compatibili («Verifica dei servizi di tracciamento…») e mostra «Australia Post ha questo pacco.» appena il sistema risponde, altrimenti ti lascia scegliere Australia Post con «Cambia» sotto «Compatibili con questo numero». Gli altri numeri postali che terminano in `AU` vengono aperti come invio postale mentre Peek interroga Australia Post. I link `auspost.com.au` con `id=` (incluso `#/search?id=`) o `/mypost/track/details/` assegnano subito Australia Post qualunque sia il numero.

Se il gateway di Australia Post rifiuta la richiesta diretta di Peek, un browser sul server di Peek apre `auspost.com.au`; poiché i servizi di tracciamento esterni conservano pochissimi eventi di Australia Post, la cronologia può restare scarna se anche il browser viene respinto.

Peek mostra lo stato, ogni scansione con la località sulla mappa, l'ora di consegna, il Paese di destinazione e il nome dell'ufficio postale o del *Parcel Locker* dove il pacco attende il ritiro, escludendo destinatario, via e foto di consegna (la consegna appare solo come `Delivered`). Le scansioni interne di Australia Post portano il fuso australiano, convertite nel fuso del tuo dispositivo («Orari nel tuo fuso orario: …» in fondo alla cronologia su `peektracker.com` e nelle app per iPhone e Android). Finché il pacco è in viaggio, Peek mostra la data o l'intervallo di consegna previsto da Australia Post finché non è passato — e, nel giorno di consegna, la fascia oraria finché non è trascorsa — nascondendo la stima dopo un tentativo di consegna fallito o un problema, e una volta che il pacco è consegnato, restituito o pronto per il ritiro. Quando Australia Post non fornisce una data (ad esempio per invii imbucati in cassetta stradale o indirizzati a mano), il suo strumento web può comunque stimarne una dal CAP. Peek non può reindirizzare un pacco né cambiare locker.

Finché il codice non è ancora noto, Peek mostra «In attesa di Australia Post»; se dopo due giorni né Australia Post né altre fonti rispondono, compare «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.», e dopo quattro giorni senza nuove scansioni durante il trasporto Peek propone «Chiedi aiuto a Australia Post».

Mentre hai il pacco aperto sullo schermo, o nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti — e fino a ogni 2 minuti (ogni 5 minuti quando deve passare dal browser) nel giorno in cui Australia Post scansiona il pacco in consegna, dalle 6:00 ora locale o mezz'ora prima dell'apertura della fascia oraria indicata, finché risponde il tracciamento di Australia Post (tramite i soli servizi di tracciamento esterni: ogni ora, ogni 3 ore dopo una giornata senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive; quando subentra una posta estera per il recapito finale, Peek segue il ritmo di quest'ultima) —; altrimenti, e dopo due giorni senza aggiornamenti (o 12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Puoi scegliere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Un pacco spedito dall'estero verso l'Australia con numero postale passa ad Australia Post non appena questa registra avanzamenti.

## Domande frequenti sul tracciamento Australia Post

### Cosa significa Pending sul tracciamento Australia Post?

Il pacco non è ancora stato scansionato nella rete di Australia Post: potrebbe non essere ancora stato spedito oppure essere stato imbucato in una cassetta postale stradale in Australia. Di solito si aggiorna entro 24–48 ore. Anche un vecchio numero senza attività da oltre 3 mesi mostra `Pending`.

### Quanto tempo resta un pacco in un Parcel Locker di Australia Post?

**2 giorni lavorativi** (48 ore esclusi fine settimana e giorni festivi). Poi il pacco viene spostato nell'ufficio postale vicino abilitato al ritiro, che lo conserva per **10 giorni lavorativi** prima di restituirlo al mittente.

### Tutti i pacchi Australia Post hanno un codice di tracciamento?

No: *International Post Registered* e *International Economy* (via aerea e via mare) non includono il tracciamento come standard. Per i pacchi spediti dall'estero verso l'Australia, il tracciamento dipende dal servizio scelto presso le poste o il corriere di partenza.

:::sources
- [Australia Post: Tracciamento pacchi](https://auspost.com.au/mypost/track) – ricerca fino a 10 codici di tracciamento contemporaneamente
- [Australia Post: Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – dove trovare il codice, nessuna ricerca per nome e nuovo tentativo dopo 24 ore
- [Australia Post: Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – article ID, centri di smistamento ed eventi recenti
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 ore e oltre 3 mesi), tentativo di consegna, cartoncino Sorry We Missed You, It's on its way dall'estero e SMS con fascia di 2 ore
- [Australia Post: Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – tracciamento senza account e hub di Redbank, Chullora e Sunshine West
- [Australia Post: Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending e Ready for processing oltre una settimana, 5 giorni lavorativi
- [Australia Post: Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – significato di Possible delay
- [Australia Post: Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned e Contact sender dopo 10 giorni lavorativi
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – consegna in ufficio postale o locker, documenti validi, delega e trasferimento a 7,55 AU$
- [Australia Post: Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – giacenza di 10 giorni lavorativi, nessuna riconsegna dall'ufficio postale ed esenzione tramite segnalazione
- [Australia Post: Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – modulo di trasferimento che indica ancora 7,20 AU$
- [Australia Post: 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – apertura 24/7 nella maggior parte delle sedi, 48 ore esclusi weekend e festivi, codice e promemoria 24 ore prima
- [Australia Post: Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – reindirizzamento con MyPost prima della consegna e giorno lavorativo aggiuntivo
- [Australia Post: Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – richiesta fino a 1 ora prima e consegne dalle 7:00 alle 17:00 nei giorni feriali
- [Australia Post: AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – avvisi digitali, cartoncini e finestra di 2 ore
- [Australia Post: No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – assenza di scansioni, 5 giorni lavorativi, reclamo dopo 10 giorni lavorativi e orari telefonici
- [Australia Post: Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – modulo di reclamo e account MyPost
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – fino a 15 giorni nella dogana australiana, lettere dalle autorità e reclamo presso la posta mittente
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – tariffe senza tracciamento standard (International Post Registered, International Economy) e tracciamento in entrata
- [Australia Post: Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – verifiche, foto di consegna e denuncia alla polizia
- [Australia Post: Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – verifica danni in ufficio postale
- [Australia Post: Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – fino a 100 AU$ di rimborso, aventi diritto, termine di 6 mesi e contatto col venditore
- [Australia Post: Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – numeri 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 e supporto negli uffici postali
- [Australia Post: Help & support](https://auspost.com.au/help) – moduli online e chat
- [Australia Post: Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – data o intervallo previsto, casi senza data e stima tramite CAP
- [Australia Post: StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – spedizioni StarTrack nello stesso strumento e nell'app AusPost
- [Australia Post: Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – falsi SMS AusPost e inoltro a scams@auspost.com.au
- [Australia Post: About us](https://auspost.com.au/about-us) – oltre 4.100 uffici postali in Australia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati e link riconosciuti, gateway e lettura browser, data e fascia previste, fusi orari e privacy
:::
