---
title: Swiss Post Cargo tracking: stati, consegna e contatti
description: Segui una spedizione Swiss Post Cargo: quale numero usare, cosa vuol dire Loaded for Delivery, come arriva la merce e chi chiamare.
slug: swiss-post-cargo-tracking
published: 2026-10-12
updated: 2026-10-27
---

Swiss Post Cargo è il marchio della Posta Svizzera per il trasporto merci: pallet e altra merce viaggiano su camion, dal singolo collo al carico completo, non come pacchi normali. Segui la spedizione sulla pagina Track & Trace (tt.swisspost-cargo.com) con il codice a barre o il riferimento che ti ha dato il mittente, oppure incollalo nel box qui sopra. Se hai un numero di pacco della Posta, usa invece la [pagina della Posta Svizzera](carrier:swiss-post).

## Swiss Post Cargo tracking number: che aspetto ha

Il tracking di Swiss Post Cargo accetta il codice a barre di un singolo collo oppure un riferimento del mittente. Un riferimento mostra tutti i codici a barre della sua spedizione. Le forme che Peek collega a Swiss Post Cargo, con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `00012345600000000005` | 20 cifre che iniziano con `00`: il codice a barre di un pallet o di un collo (SSCC). L'ultima cifra è di controllo. |
| `PL-12345678` | `PL-` e 8 cifre: un riferimento cliente. |

Guarda nelle e-mail del negozio, poi chiedi al negozio stesso; altri posti dove cercare sono in [dove si trova il codice tracking](guide:find-tracking-number). Se il tracking non trova nulla, ricontrolla le cifre e poi chiedi al mittente.

Numeri che non si tracciano come Swiss Post Cargo:

- **Pacchi della Posta Svizzera:** 18 cifre che iniziano con `99` (stampate `99.00.123456.12345678`), oppure due lettere, nove cifre e `CH` (`RR000000005CH`).
- **Riferimenti PostLogistics** come `12345678-123`: PostLogistics oggi opera come Swiss Post Cargo, e il suo vecchio tracking, tracking.postlogistics.ch, non si apre più. Peek traccia questi riferimenti come PostLogistics.

## Swiss Post Cargo live tracking: cosa vogliono dire gli stati

Swiss Post Cargo scrive le sue scansioni in italiano, tedesco, francese o inglese. In Peek compaiono in inglese. In inglese, una spedizione consegnata si legge così:

| Swiss Post Cargo scrive (in inglese) | Cosa vuol dire per te |
| --- | --- |
| `Data entry POST` | Sono arrivati i dati del mittente. La merce non è ancora da Swiss Post Cargo. |
| `Received warehouse POST` | La merce è arrivata in un centro o deposito Swiss Post Cargo, indicato accanto alla scansione. |
| `Loaded for Delivery` | È sul camion delle consegne: in consegna. |
| `IMAGE` | Una foto della consegna, elencata subito prima della scansione di consegna. |
| `DELIVERED SCANNED` | Consegnato, nel comune indicato. |
| `SIGNATURE` | La firma presa alla consegna, alla stessa ora della scansione di consegna. |

`Data entry POST` riguarda solo i dati, non la merce. `IMAGE` e `SIGNATURE` sono prove che fanno parte della consegna, non nuovi eventi.

## Consegna Swiss Post Cargo: quando, dove e se non ci sei

**Quando.** Secondo l'opuscolo della Posta sul trasporto di collettame, la merce arriva di norma il giorno lavorativo successivo. In Svizzera e nel Liechtenstein, i luoghi raggiungibili da un mezzo pesante la ricevono entro le 17.00 del giorno lavorativo successivo. Le valli alpine e le zone di montagna difficili da raggiungere (Vallese, Ticino e Grigioni) entro le 17.00 del secondo giorno lavorativo.

**Dove.** Di norma la merce arriva in orario d'ufficio alla prima porta al pianterreno, alla rampa o al bordo strada, senza consegna ai piani. L'autista si aspetta un accesso con il transpallet, e lo scarico tocca al destinatario: se l'autista scarica, lo fa per conto tuo. Ti chiede una firma per confermare che la consegna è corretta.

**Servizi che prenota il mittente.** Un preavviso per telefono, e-mail o SMS, una consegna su appuntamento, la consegna al piano e una seconda consegna sono servizi aggiuntivi: li hai solo se il mittente li ha ordinati.

**Consegna mancata e ritiro.** L'opuscolo della Posta non parla di punti di ritiro o locker, e la seconda consegna è uno dei servizi aggiuntivi qui sopra. Non dice quanto resta la merce in deposito né come spostare la consegna. Chiedi al negozio, che ha prenotato il trasporto.

## Spedizione Swiss Post Cargo in ritardo, persa o danneggiata

Il cliente di Swiss Post Cargo è il negozio: sono le aziende a ordinare i suoi servizi, quindi ritardi, danni o colli mancanti si segnalano prima di tutto al negozio. Un danno segnalalo subito al negozio, con delle foto.

**Nessun aggiornamento da giorni.** Se ci sono solo i dati del mittente (`Data entry POST`), la merce può essere ancora da lui: chiedi al negozio quando la affida a Swiss Post Cargo. I motivi più comuni sono in [tracking che non si aggiorna](guide:tracking-not-updating).

**Risulta consegnato, ma non c'è.** Cerca sul tracking le righe della firma e della foto (in inglese `SIGNATURE` e `IMAGE`), poi contatta il negozio. I passi successivi sono in [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Messaggi sospetti.** La pagina della Posta su phishing e frodi avverte che i messaggi falsi a nome della Posta arrivano per SMS, e-mail, WhatsApp, siti web, telefonate o social media. «Spesso riguardano pacchi, spese, tracciamento degli invii o dati personali.»

## Swiss Post Cargo telefono e contatti

Swiss Post Cargo lavora per il negozio, quindi parti da lì: è il negozio che ha prenotato il trasporto. L'opuscolo della Posta sul collettame, datato aprile 2022, dà un solo contatto, per i clienti aziendali e senza numero di telefono: Servizio clienti trasporto CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Per lettere e pacchi della Posta (non Cargo) la linea della Posta Svizzera è il +41 848 888 888 (CHF 0.08 al minuto da rete fissa svizzera), dal lunedì al venerdì 07.30–18.00 e il sabato 08.00–12.00, con una chat su post.ch.

## Tracciare una spedizione Swiss Post Cargo con Peek

Un codice a barre di 20 cifre che inizia con `00`, con una cifra di controllo valida, appartiene a chi spedisce, non a un corriere: [Peek](/) mostra «verifica presso Swiss Post Cargo, NZ Post…» e indica Swiss Post Cargo solo se Swiss Post Cargo conosce il numero. Un riferimento `PL-12345678` viene chiesto a Swiss Post Cargo e a TNT Italy, i cui numeri possono avere la stessa forma. Per un altro riferimento da 6 a 40 lettere e cifre, scegli Swiss Post Cargo con «Cambia», oppure incolla un link Track & Trace di tt.swisspost-cargo.com o apv.swisspost-cargo.com. Un numero di pacco della Posta in un link così viene tracciato come Posta Svizzera.

Peek mostra lo stato e la cronologia con il comune di ogni scansione su una mappa, con gli orari letti sull'ora di Zurigo e mostrati nel fuso orario del tuo dispositivo, e l'ora di consegna. Le diciture restano in inglese; `Loaded for Delivery` vale come in consegna. Le righe della firma e della foto confluiscono nella scansione di consegna, senza l'immagine. Peek lascia fuori il tuo nome e indirizzo e non ha data di consegna prevista, peso o punto di ritiro.

Un riferimento condiviso da più spedizioni viene letto solo se una di queste ha scansioni degli ultimi 60 giorni; altrimenti risulta non trovato, quindi usa il codice a barre. Peek controlla ogni 10 minuti di giorno, ogni 2 quando la merce è in consegna e di notte ogni una o due ore, e può mandarti notifiche per ogni scansione, solo per i passaggi importanti o solo il giorno di consegna.

## Domande sul tracking Swiss Post Cargo

### Che cos'è Swiss Post Cargo?

È il nome con cui opera dal 1° marzo 2025 la logistica merci della Posta Svizzera: logistica per le aziende, in Svizzera e all'estero, collettame compreso. PostLogistics è stata una delle prime società del gruppo a prendere il nome. Tra le società svizzere del gruppo c'è Swiss Post Cargo CH AG, controllata al 100% dalla Posta.

### PostLogistics e Swiss Post Cargo sono la stessa cosa?

Sì, PostLogistics ora opera come Swiss Post Cargo. Il suo vecchio tracking, tracking.postlogistics.ch, non si apre più; quello di Swiss Post Cargo è tt.swisspost-cargo.com. Peek traccia ancora i riferimenti PostLogistics come `12345678-123`.

### Swiss Post Cargo ha un indirizzo e-mail?

L'opuscolo della Posta sul collettame, datato aprile 2022, indica sales.ls4@post.ch, per i clienti aziendali. Se sei tu a ricevere, scrivi prima al negozio: è il negozio che ha prenotato il trasporto.

### Perché Swiss Post Cargo mi manda un SMS?

Può essere un preavviso prenotato dal mittente: l'opuscolo della Posta lo elenca tra i servizi aggiuntivi, per telefono, e-mail o SMS. Esistono però anche SMS falsi a nome della Posta, quindi controlla il numero tu stesso sul tracking.

### Si vede dove si trova il camion in tempo reale?

No, non c'è una mappa in diretta. Il tracking elenca ogni scansione con il suo comune, e secondo l'opuscolo della Posta stato e luogo dell'invio si possono conoscere in ogni momento.

:::sources
- [Posta Svizzera: opuscolo Trasporti, collettame](https://www.post.ch/it/-/media/post/gk/dokumente/stueckgut.pdf) – dai pacchi singoli agli interi carichi, ordini tramite Swiss Post Cargo Web da parte delle aziende, giorno lavorativo successivo, zone di montagna, pianterreno, scarico, firma, servizi aggiuntivi compreso il secondo recapito, stato e luogo degli invii, contatto di Dintikon ed e-mail
- [Posta Svizzera: Mercato logistico della Posta](https://site.post.ch/it/chi-siamo/azienda/strategia/attivita-aziendali/logistica) – Swiss Post Cargo è la logistica delle merci della Posta, soluzioni per le aziende, nazionali e internazionali
- [Posta Svizzera: Società del gruppo](https://site.post.ch/it/chi-siamo/azienda/governance/organizzazione/societa-del-gruppo) – Swiss Post Cargo CH AG, partecipazione della Posta al 100%
- [Posta Svizzera: Aiuto e contatto](https://www.post.ch/it/aiuto-e-contatto) – linea per lettere e pacchi, costo, orari, chat
- [Posta Svizzera: Tentativi di frode attuali](https://site.post.ch/it/chi-siamo/sicurezza/phishing-e-frodi/tentativi-di-frode-attuali) – messaggi falsi a nome della Posta
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – nome Swiss Post Cargo dal 1° marzo 2025, PostLogistics tra le prime società
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek: codici a barre e riferimenti, forme dei numeri, diciture inglesi delle scansioni, indirizzi Track & Trace, PostLogistics oggi Swiss Post Cargo
:::
