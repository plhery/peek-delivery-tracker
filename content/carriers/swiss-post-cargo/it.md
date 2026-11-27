---
title: Swiss Post Cargo tracking: stati, consegna e contatti
description: Swiss Post Cargo tracking (ex PostLogistics): come seguire il codice a barre o riferimento, significato di Loaded for Delivery, consegna collettame e contatti.
slug: swiss-post-cargo-tracking
published: 2026-10-12
updated: 2026-11-27
---

Swiss Post Cargo (dal 1° marzo 2025 il marchio della logistica merci della Posta Svizzera, che comprende PostLogistics) trasporta pallet e collettame su camion per le aziende. Segui la spedizione su Track & Trace (`tt.swisspost-cargo.com`) con il codice a barre da 20 cifre (`00…`) o il riferimento del mittente, oppure incollalo nel box qui sopra. Per un normale pacco postale usa invece la [pagina della Posta Svizzera](carrier:swiss-post).

## Swiss Post Cargo tracking number: che aspetto ha

Il tracking di Swiss Post Cargo accetta il codice a barre di un singolo collo oppure un riferimento del mittente. Un riferimento mostra tutti i codici a barre della sua spedizione. Le forme che Peek collega a Swiss Post Cargo, con cifre inventate:

| Numero | Cos'è |
| --- | --- |
| `00482917365428193648` | 20 cifre che iniziano con `00`: il codice a barre di un pallet o di un collo (SSCC). L'ultima cifra è di controllo. |
| `PL-48291736` | `PL-` e 8 cifre: un riferimento cliente. |

Guarda nelle e-mail del negozio, poi chiedi al negozio stesso; altri posti dove cercare sono in [dove si trova il codice tracking](guide:find-tracking-number). Se il tracking non trova nulla, ricontrolla le cifre e poi chiedi al mittente.

Numeri che non si tracciano come Swiss Post Cargo:

- **Pacchi della Posta Svizzera:** 18 cifre che iniziano con `99` (stampate `99.00.482917.36542819`), oppure due lettere, nove cifre e `CH` (`RR482917367CH`).
- **Riferimenti PostLogistics** come `48291736-542`: PostLogistics oggi opera come Swiss Post Cargo, e il suo vecchio tracking, tracking.postlogistics.ch, non si apre più. Peek traccia questi riferimenti come PostLogistics.

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

**Quando.** Secondo l'opuscolo della Posta sul trasporto di collettame, la merce arriva di norma il giorno lavorativo successivo. In Svizzera e nel Liechtenstein, i luoghi raggiungibili da un mezzo pesante la ricevono entro le 17.00 (ora svizzera) del giorno lavorativo successivo. Le valli alpine e le zone di montagna difficili da raggiungere (Vallese, Ticino e Grigioni) entro le 17.00 del secondo giorno lavorativo.

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

Per lettere e pacchi della Posta (non Cargo) la linea della Posta Svizzera è il +41 848 888 888 (CHF 0.08 al minuto da rete fissa svizzera), dal lunedì al venerdì 07.30–18.00 e il sabato 08.00–12.00 (ora svizzera), con una chat su post.ch.

## Tracciare una spedizione Swiss Post Cargo con Peek

Un codice a barre di 20 cifre che inizia con `00`, con una cifra di controllo valida, appartiene a chi spedisce, non a un corriere: [Peek](/) mostra «Verifica dei servizi di tracciamento…» e indica Swiss Post Cargo solo se Swiss Post Cargo conosce il numero. Un riferimento `PL-48291736` viene chiesto a Swiss Post Cargo e a TNT Italy, i cui numeri possono avere la stessa forma. Per un altro riferimento da 6 a 40 lettere e cifre, scegli Swiss Post Cargo con «Cambia», oppure incolla un link Track & Trace di tt.swisspost-cargo.com o apv.swisspost-cargo.com (`/public/trackandtrace/` seguito dal numero). Un numero di pacco della Posta in un link così viene tracciato come Posta Svizzera.

Peek mostra lo stato e la cronologia con il comune di ogni scansione su una mappa, con gli orari letti sull'ora di Zurigo e mostrati nel fuso orario del tuo dispositivo, e l'ora di consegna. Le diciture restano in inglese; `Loaded for Delivery` vale come in consegna. Le righe della firma e della foto confluiscono nella scansione di consegna, senza l'immagine. Peek lascia fuori il tuo nome e indirizzo e non ha data di consegna prevista, peso o punto di ritiro.

Un riferimento condiviso da più spedizioni viene letto solo se una sola di queste ha scansioni degli ultimi 60 giorni; altrimenti Swiss Post Cargo non lo trova (Peek mostra «In attesa di Swiss Post Cargo» e, dopo due giorni, ti chiede di controllare il numero), quindi usa il codice a barre. Finché hai la spedizione aperta, o il giorno di consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, fino a ogni 2 quando la merce è in consegna (il giorno stesso, dalle 6.00 ora svizzera e finché risponde il tracking di Swiss Post Cargo), e può mandarti notifiche per ogni scansione, solo per i passaggi importanti o solo il giorno di consegna. Altrimenti, e dopo due giorni senza novità, o dopo 12 ore se la spedizione è ancora su «Etichetta creata» o «Preso in carico», Peek controlla solo una volta all'ora.

## Domande sul tracking Swiss Post Cargo

### Che cos'è Swiss Post Cargo, e che legame ha con PostLogistics?

Dal 1° marzo 2025 Swiss Post Cargo è il marchio della logistica merci della Posta Svizzera (tra cui Swiss Post Cargo CH AG, controllata al 100% dalla Posta). PostLogistics opera ora sotto questo nome: il vecchio tracking `tracking.postlogistics.ch` non si apre più e le spedizioni si seguono su `tt.swisspost-cargo.com` (Peek traccia comunque i riferimenti PostLogistics).

### Che cosa significano "Data entry POST" e "Loaded for Delivery"?

`Data entry POST` indica che il mittente ha trasmesso i dati ma la merce non è ancora stata scansionata nei centri Swiss Post Cargo; `Loaded for Delivery` significa che il collo è sul camion delle consegne.

### Come avviene la consegna di una spedizione Swiss Post Cargo?

Di norma il collettame arriva il giorno lavorativo successivo entro le 17.00 (secondo giorno lavorativo nelle valli alpine di Vallese, Ticino e Grigioni), al pianterreno, rampa o bordo strada in orario d'ufficio e contro firma. Preavviso, appuntamento, consegna al piano e secondo recapito valgono solo se prenotati dal mittente.

### Perché ho ricevuto un SMS da Swiss Post Cargo o dalla Posta?

Può essere un preavviso di consegna ordinato dal mittente, ma circolano anche SMS di phishing a nome della Posta su pacchi e spese: non cliccare link sospetti e controlla sempre il numero direttamente sul tracking.

### Come contattare Swiss Post Cargo o la Posta Svizzera?

Per spedizioni Cargo rivolgiti prima al negozio che ha prenotato il trasporto (l'opuscolo collettame indica solo il contatto aziendale di Dintikon, `sales.ls4@post.ch`). Per lettere e pacchi normali della Posta Svizzera chiama il +41 848 888 888 (lun–ven 07.30–18.00, sab 08.00–12.00) o usa la chat su post.ch.

:::sources
- [Posta Svizzera: opuscolo Trasporti, collettame](https://www.post.ch/it/-/media/post/gk/dokumente/stueckgut.pdf) – dai pacchi singoli agli interi carichi, ordini tramite Swiss Post Cargo Web da parte delle aziende, giorno lavorativo successivo, zone di montagna, pianterreno, scarico, firma, servizi aggiuntivi compreso il secondo recapito, stato e luogo degli invii, contatto di Dintikon ed e-mail
- [Posta Svizzera: Mercato logistico della Posta](https://site.post.ch/it/chi-siamo/azienda/strategia/attivita-aziendali/logistica) – Swiss Post Cargo è la logistica delle merci della Posta, soluzioni per le aziende, nazionali e internazionali
- [Posta Svizzera: Società del gruppo](https://site.post.ch/it/chi-siamo/azienda/governance/organizzazione/societa-del-gruppo) – Swiss Post Cargo CH AG, partecipazione della Posta al 100%
- [Posta Svizzera: Aiuto e contatto](https://www.post.ch/it/aiuto-e-contatto) – linea per lettere e pacchi, costo, orari, chat
- [Posta Svizzera: Tentativi di frode attuali](https://site.post.ch/it/chi-siamo/sicurezza/phishing-e-frodi/tentativi-di-frode-attuali) – messaggi falsi a nome della Posta
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – nome Swiss Post Cargo dal 1° marzo 2025, PostLogistics tra le prime società
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek: codici a barre e riferimenti, forme dei numeri, diciture inglesi delle scansioni, indirizzi Track & Trace, PostLogistics oggi Swiss Post Cargo
:::
