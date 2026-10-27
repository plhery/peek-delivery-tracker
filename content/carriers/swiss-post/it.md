---
title: Swiss Post tracking: Posta Svizzera, stati e ritiro
description: Segui un pacco della Posta Svizzera in Ticino: com'è fatto il numero, cosa vogliono dire gli stati, cosa fare con l'invito di ritiro e come contattare la Posta.
slug: swiss-post-tracking-posta-svizzera
published: 2026-10-11
updated: 2026-10-27
---

La Posta Svizzera (Swiss Post, Die Post, La Poste) consegna pacchi e raccomandate in tutta la Svizzera e nel Principato del Liechtenstein. Puoi seguire il pacco gratis su [post.ch](https://www.post.ch/it/ricezione/monitorare-gli-invii) con il numero d'invio, oppure incollare il numero nel box qui sopra. Il numero lo crea il mittente: cercalo prima nell'e-mail di spedizione del negozio.

## Numero di tracking della Posta Svizzera: com'è fatto

Il mittente genera il numero e trasmette i dati alla Posta, di solito prima di consegnarle il pacco. La Posta non pubblica un elenco dei formati; queste sono le due forme che Peek riconosce come sue, con cifre inventate:

| Numero (inventato) | Cos'è |
| --- | --- |
| `RR000000005CH` | Due lettere, nove cifre e `CH`: un numero postale internazionale emesso in Svizzera. L'ultima cifra prima di `CH` è di controllo. |
| `99.00.000000.00000000` | 18 cifre che iniziano con `98` o `99`, con o senza punti o spazi: il codice a barre degli invii nazionali della Posta. |

Nel campo di ricerca della Posta, «Numero d’invio o codice di ritiro», puoi inserire anche il codice di ritiro; Peek invece riconosce solo il numero d'invio.

Copia il numero invece di riscriverlo: con una cifra sbagliata, in meno o in più, sembra il numero di un altro corriere. 18 cifre che iniziano con `44` sono di [Quickpac](carrier:quickpac), non della Posta. Pallet e altra merce viaggiano con [Swiss Post Cargo](carrier:swiss-post-cargo), il marchio merci della Posta, che ha un suo tracking. Un pacco dall'estero tiene il suo numero straniero (che finisce con `DE`, `FR`, `IT` e così via) anche in Svizzera. Nessun numero? Leggi [dove si trova il codice tracking](guide:find-tracking-number).

## Posta Svizzera tracciamento: cosa vogliono dire gli stati

Su post.ch ogni scansione appare nella lingua in cui guardi il sito: in italiano se lo apri in italiano. Peek invece legge il tracking della Posta in inglese, quindi qui trovi entrambe le diciture, nell'ordine in cui le incontra di solito un pacco:

| La Posta scrive (italiano / inglese) | Cosa vuol dire per te |
| --- | --- |
| `Il suo invio sarà presto consegnato alla Posta` / `Your shipment will shortly be handed over to the Swiss Post` | Il mittente ha annunciato il pacco, ma la Posta non ce l'ha ancora. |
| `Data d’impostazione dell’invio` / `Time at which your consignment was mailed` | Quando il pacco è stato impostato e in quale ufficio postale. |
| `Processo di sdoganamento` / `Customs clearance process underway` | Un pacco dall'estero è alla dogana postale svizzera. |
| `Processo di sdoganamento terminato` / `Completion of customs clearance process` | Sdoganato, passa alla consegna in Svizzera. |
| `L'invio è stato spartito e inoltrato` / `Sorting - forwarding` | Smistato in un centro e inviato al successivo. |
| `Arrivo all’ufficio di recapito` / `Arrival at the delivery point` | È all'ufficio che consegna a casa tua. |
| `Caricamento nel veicolo di consegna` / `Loading into delivery vehicle` | È in consegna. |
| `Recapitato` / `Delivered` | Consegnato. |
| `Recapitato nella cassetta di deposito/Cassetta delle lettere` / `Delivered to the mailbox/letter box` | Lasciato nella bucalettere o nel suo vano pacchi. |
| `recapito fallito: destinatario assente` / `Delivery failed: Recipient absent` | Il postino non ti ha trovato. |
| `Avvisato per il ritiro (invito di ritiro)` / `Registered for collection` | Ti hanno lasciato un invito di ritiro: il pacco ti aspetta in filiale. |
| `Depositati nel distributore automatico di MyPost24` / `Deposited in the MyPost24 machine` | Ti aspetta in uno sportello automatico My Post 24. |

L'inglese `Registered for collection` sembra una formalità, ma vuol dire consegna mancata e invito di ritiro; l'italiano lo dice più chiaro. `Invio annunciato dal mittente straniero (dati trasmessi)` significa solo che un mittente all'estero ha mandato i dati: non è stato consegnato nulla. `Rinvio`, con un motivo come `Motivo: Non ritirato`, vuol dire che il pacco torna al mittente. Alcuni testi italiani della Posta hanno refusi, per esempio «presso lil punto PickPost», scritto proprio così.

## Invito di ritiro, consegna mancata e ritiro del pacco

Il giorno prima della consegna la Posta ti avvisa via SMS, e-mail o notifica push nella Post-App. Con «I miei invii» ricevi di solito una fascia oraria da 40 a 90 minuti.

**Non eri a casa?** Ricevi un invito di ritiro. Secondo la Posta:

- Puoi ritirare il pacco solo dal giorno dopo, nel luogo indicato sull'invito, mostrando l'invito o il codice QR nella Post-App.
- Di norma hai sette giorni per ritirare i pacchi, 15 per gli invii dall'estero. Prolungare il termine è gratuito e non serve un account.
- Con il login a «I miei invii» puoi far ritirare gratis il pacco a un'altra persona con un'autorizzazione singola, oppure chiedere un secondo recapito: deposito o consegna al vicino gratis, al sabato CHF 7. L'inoltro a un My Post 24, a una filiale o a un altro indirizzo in Svizzera o nel Liechtenstein costa CHF 8 dal lunedì al venerdì.

**Prima della consegna**, «I miei invii» ti permette di deviare il pacco gratis, scegliere gratis un altro giorno dal lunedì al venerdì (il sabato CHF 3.80) o indicare un posto sicuro dove depositarlo. Hai tempo fino a mezzanotte per cambiare ora e luogo; dopo, la Posta non garantisce la modifica.

**Punti di ritiro.** Oltre 2700 punti PickPost e oltre 320 sportelli My Post 24, aperti giorno e notte, entrambi gratuiti.

## Pacco della Posta in ritardo, perso o danneggiato

**Nessun aggiornamento.** Se il pacco è in dogana, la Posta avverte che il trattamento può durare diversi giorni. Negli altri casi leggi [tracking che non si aggiorna](guide:tracking-not-updating).

**Perso.** Il mittente o il destinatario possono avviare una ricerca quando manca l'evento di consegna e il tempo di consegna pubblicato è superato di oltre due giorni, oppure se il destinatario conferma di non aver ricevuto nulla. I termini della Posta: fino a un anno dall'impostazione in Svizzera, sei mesi per gli invii internazionali. Per un pacco dall'estero le sue pagine d'aiuto affidano la ricerca al mittente, di solito il negozio.

**Danneggiato.** Hai fino a otto giorni dal recapito per segnalarlo: online, nella Post-App, tra le funzioni per ricevere, oppure in filiale con il pacco, l'imballaggio e il materiale di riempimento.

**Costi d'importazione.** Per un pacco dall'estero paghi IVA e dazio, più la tassa di sdoganamento della Posta, pagabile online nella Post-App. Le tariffe cambiano: controlla la [pagina della Posta sullo sdoganamento](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) e la guida [pacco fermo in dogana](guide:customs).

## Posta Svizzera contatti e telefono

Come risultavano su post.ch l'11 ottobre 2026:

- **Telefono:** +41 848 888 888, CHF 0.08 al minuto da rete fissa svizzera, dal lunedì al venerdì dalle 7.30 alle 18.00, il sabato dalle 8.00 alle 12.00.
- **Chat:** un chatbot con specialisti alle spalle, dalla [pagina Aiuto e contatto](https://www.post.ch/it/aiuto-e-contatto).
- **WhatsApp:** indicato, insieme a telefono e chat, per modificare un ordine di recapito.
- **Ricerca:** moduli per avviare una ricerca nazionale o internazionale, collegati dalle [domande e risposte sulla ricezione](https://www.post.ch/it/aiuto-e-contatto/ricezione).
- **In filiale:** per gli ordini di recapito, con un documento d'identità valido.

> La Posta mette in guardia da messaggi falsi, per esempio un pacco «trattenuto» o piccole spese di porto o doganali da pagare tramite un link. Non chiede mai password, codici di sicurezza o dati completi della carta via SMS o e-mail. Controlla gli invii solo direttamente su swisspost.ch o nella Post-App ufficiale.

## Tracciare un pacco della Posta Svizzera con Peek

[Peek](/) riconosce subito entrambe le forme, anche con punti o spazi. Funziona anche un link di service.post.ch con `/entry/search/` seguito dal numero; gli altri link di post.ch solo se contengono uno di questi numeri. Non serve un NPA.

Legge i testi inglesi della Posta, in qualunque lingua usi Peek. Mostra lo stato, il luogo di ogni scansione su una mappa, il giorno di consegna previsto (con una fascia oraria quando la Posta la dà), peso, dimensioni, ora di consegna e paese di destinazione. Mentre il pacco aspetta, mostra nome e indirizzo della filiale o del My Post 24, che spariscono dopo il ritiro. Lascia fuori nome e indirizzo del destinatario, firma e istruzioni di recapito, e non legge il termine di ritiro.

Peek mostra `Delivered` con la sua etichetta «Consegnato» e segna `Registered for collection` e `Deposited in the MyPost24 machine` come «Pronto per il ritiro»; `Delivered to the mailbox/letter box` resta come lo scrive la Posta.

Controlla il pacco ogni 10 minuti di giorno, ogni 2 quando è in consegna e di notte ogni una o due ore, e può mandarti notifiche per tutti gli aggiornamenti, solo per quelli importanti o solo il giorno di consegna. I pacchi di [GLS Svizzera](carrier:gls-ch) e i numeri postali stranieri diretti in Svizzera possono essere seguiti fino alla Posta; un numero `CH` diretto all'estero, fino alla posta nazionale del paese di destinazione, se Peek la conosce (per esempio Italia, Francia o Germania), appena questa mostra progressi più recenti. Peek non può contattare la Posta al posto tuo.

## Domande sul tracking della Posta Svizzera

### La Posta Svizzera consegna il sabato?

Sì, se lo prenoti in «I miei invii»: CHF 3.80 scelto in anticipo, oppure CHF 7 come secondo recapito dopo una consegna mancata.

### Quanto resta in filiale un pacco della Posta Svizzera?

Di norma sette giorni, 15 per gli invii dall'estero. Puoi prolungare il termine gratis, anche senza account.

### Dove trovo un punto della Posta Svizzera vicino a me?

Su swisspost.ch/locations trovi l'indirizzo esatto del punto di ritiro indicato sul tuo invito. La Posta conta oltre 2700 punti PickPost e oltre 320 sportelli My Post 24, aperti giorno e notte.

### Come si fa un reclamo alla Posta Svizzera?

Per un pacco danneggiato hai otto giorni dal recapito, online, nella Post-App o in filiale. Per un pacco perso, mittente o destinatario avviano una ricerca con il modulo online o in filiale.

:::sources
- [La Posta: Monitorare gli invii](https://www.post.ch/it/ricezione/monitorare-gli-invii) – campo di ricerca, invii all'estero
- [La Posta: Eventi Monitorare gli invii](https://www.post.ch/it/ricezione/monitorare-gli-invii/eventi-monitorare-gli-invii) – significato degli stati
- [La Posta: Testi del tracking (it)](https://service.post.ch/ekp-web/core/rest/translations/it/shipment-text-messages) – diciture italiane
- [La Posta: Testi del tracking (en)](https://service.post.ch/ekp-web/core/rest/translations/en/shipment-text-messages) – diciture inglesi
- [La Posta: Invito di ritiro](https://www.post.ch/it/ricezione/invito-di-ritiro) – termini, autorizzazione, prezzi, «I miei invii»
- [La Posta: Domande e risposte sulla ricezione](https://www.post.ch/it/aiuto-e-contatto/ricezione) – ritiro dal giorno dopo, mezzanotte, WhatsApp
- [La Posta: Danneggiamento o smarrimento](https://www.post.ch/it/spedire-pacchi/danneggiamento-o-smarrimento) – ricerche, termini, danni
- [La Posta: Sdoganamento all'importazione](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) – costi d'importazione
- [La Posta: Aiuto e contatto](https://www.post.ch/it/aiuto-e-contatto) – telefono, orari, chat
- [La Posta: Riconoscere il phishing e le frodi](https://site.post.ch/it/chi-siamo/sicurezza/phishing-e-frodi) – messaggi falsi
- [Swiss Post: Track consignments](https://www.post.ch/en/receiving-mail/track-consignments) – notifiche, fascia oraria
- [Swiss Post: Manage and receive parcels](https://www.post.ch/en/receiving-mail/manage-consignment/manage-one-off) – deviazione, sabato, posto sicuro
- [Swiss Post: Questions and answers on receiving](https://www.post.ch/en/help-and-contact/receiving-mail) – avviso del giorno prima, moduli di ricerca
- [Swiss Post: Receipt locations](https://www.post.ch/en/receiving-mail/receipt-locations) – PickPost, My Post 24
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge Peek
:::
