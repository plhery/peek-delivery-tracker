---
title: Tracciamento Cyprus Post: numeri, stati e dogana
description: Traccia una spedizione Cyprus Post: codice a 13 caratteri, significato degli stati UPU in inglese, oneri UE per acquisti extra-UE e reclami.
slug: tracciamento-cyprus-post
published: 2026-11-22
updated: 2026-11-22
---

Cyprus Post (Κυπριακά Ταχυδρομεία), il Department of Postal Services, è il servizio postale nazionale della Repubblica di Cipro. Traccia una spedizione sul sito di Cyprus Post, cypruspost.post, oppure incolla il codice qui sopra. Nessun numero? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Numero di tracciamento Cyprus Post

Il tracker di Cyprus Post accetta i codici a 13 caratteri dello standard S10 dell'Unione Postale Universale, usati dalle poste per raccomandate, invii EMS, pacchi e lettere tracciate: [due lettere, nove cifre e due lettere](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Indicatore di servizio: la prima lettera indica il tipo di invio
- 482917367 | Numero di serie a otto cifre seguito da una cifra di controllo
- CY | Codice paese: CY per un numero emesso sotto l'autorità di Cipro
:::

| Prima lettera (esempi inventati) | Che cos'è |
| --- | --- |
| `RR482917367CY` | R: invio raccomandato |
| `CP482917367CY` | C: pacco postale |
| `EE482917367CY` | E: invio EMS |
| `LA482917367CY` | L: lettera tracciata |
| `UA482917367CY` | U: invio postale contenente merci, non tracciato per il cliente |

Un invio spedito dall'estero conserva il numero emesso dalla posta di origine con il rispettivo codice paese (`…GB`, `…FR`, `…GR`), e il tracker di Cyprus Post lo accetta con le scansioni della posta estera. `There is no information for this item` significa che il tracker non conosce il codice: verificalo con il mittente.

## Cosa significano gli stati di Cyprus Post

Nella versione inglese del tracker Cyprus Post letta da Peek, ogni scansione riporta ora, paese, località, evento, ufficio successivo e informazioni extra. Gli eventi seguono i [nomi postali internazionali (UPU)](guide:tracking-statuses): `(Otb)` indica una fase nel paese di spedizione, `(Inb)` una nel paese di destinazione. L'ufficio successivo indica dove è diretto l'invio, non dove si trova. Per un invio in arrivo:

| Stato Cyprus Post | Cosa significa per te |
| --- | --- |
| `Receive item from customer (Otb)` | L'invio è stato accettato, a Cipro o all'estero. |
| `Insert item into bag (Otb)` | Inserito nel sacco presso l'ufficio di scambio per partire all'estero. |
| `Receive item at office of exchange (Inb)` | È arrivato nel paese di destinazione. |
| `Send items to delivery point/ collection point` | In viaggio verso l'ufficio che lo consegnerà o lo custodirà per il ritiro. |
| `Receive item at delivery office (Inb)` | Arrivato all'ufficio incaricato della consegna. |
| `Hold item at point of delivery (Inb)` | Presso l'ufficio di consegna, in attesa del giro. |
| `Handover item to delivery agent (Inb)` | Affidato al postino. |
| `Item not delivered/ Pending delivery` | Non consegnato: la colonna Extra Information indica il motivo. |
| `Receive item at collection point for pick-up (Inb)` | Pronto per il ritiro presso l'ufficio postale. |
| `Deliver item (Inb)` | Consegnato, o ritirato all'ufficio postale. |

## Oneri doganali sui beni provenienti da fuori UE

Dal 1° luglio 2026 i beni acquistati online al di fuori dell'UE, compresi gli invii postali, non entrano più esenti da dazio quando la spedizione vale 150 € o meno: l'UE applica un dazio doganale temporaneo di 3 € per articolo. Per articolo si intendono tutte le merci della spedizione con la stessa classificazione tariffaria, descrizione e, se dichiarata, origine, indipendentemente dal numero di pezzi. Dal 1° novembre 2026 l'UE applica inoltre una tassa di gestione dell'Unione di 2 € per articolo sugli acquisti online da fuori UE, qualunque sia il valore, soggetta a IVA. Il pagamento spetta in primo luogo a chi dichiara la merce in dogana: piattaforma, venditore, vettore o rappresentante. A Cipro il Department of Customs and Excise riscuote dazi d'importazione e IVA sulle merci extra-UE. Vedi [pacchi fermi in dogana](guide:customs).

## Spedizione Cyprus Post in ritardo o non arrivata

Se il tracciamento è fermo, consulta [tracciamento che non si aggiorna](guide:tracking-not-updating). Se [risulta consegnato](guide:delivered-not-received) ma non lo hai, verifica il codice con il mittente. Per smarrimenti o danni vedi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

**Acquisto online in ritardo?** Il Consumer Protection Service di Cipro ricorda che un commerciante deve di norma consegnare entro 30 giorni salvo diversa data pattuita. In caso contrario informalo e concedi un termine aggiuntivo ragionevole; se continua a non consegnare puoi recedere dal contratto. La linea consumatori 1429 è gratuita.

**Un messaggio chiede di pagare per sbloccare un pacco?** Leggi [SMS truffa di consegna](guide:delivery-scam-texts) prima di aprire qualsiasi link.

## Tracciare spedizioni Cyprus Post con Peek

Per un numero postale che termina con `CY` e cifra di controllo valida, [Peek](/) interroga insieme Cyprus Post e [Chronopost](carrier:chronopost), mostrando «Cyprus Post ha questo pacco.» quando Cyprus Post lo conosce, oppure «Non ancora trovato presso… Peek continuerà a controllare dopo l’aggiunta.». Con una cifra di controllo errata Peek non interroga Cyprus Post neppure selezionandolo con «Cambia»: verifica il numero con il mittente. Un link di cypruspost.post funziona quando contiene il numero.

Per gli invii spediti dall'estero, Peek interroga prima la posta emittente quando supportata — come [ELTA](carrier:elta) per `…GR` — oppure i servizi di tracciamento. Anche Cyprus Post accetta questi numeri: seleziona «Cyprus Post» con «Cambia». Peek prova Cyprus Post automaticamente se la posta estera indica Cipro come destinazione o, in assenza di destinazione, quando lo aggiungi da Cipro, passando a Cyprus Post appena registra scansioni recenti almeno quanto quelle estere. Peek interroga Cyprus Post solo su codici a 13 caratteri. Non sei sicuro del corriere? Incolla comunque il numero: Peek segue oltre 4.500 corrieri.

Peek mostra gli stati Cyprus Post così come scritti, l'ufficio o centro e il paese di ogni scansione sulla mappa, l'orario di consegna e l'ufficio postale in cui l'invio attende o è stato ritirato. Le scansioni cipriote mantengono l'ora esatta e quelle estere vengono lette sull'orologio del rispettivo paese (nei paesi con più fusi Peek ricava il fuso dal luogo indicato, con possibili scarti), tutto mostrato nel tuo fuso orario («Orari nel tuo fuso orario:»). Non sono disponibili data prevista né peso, e Peek scarta Extra Information, ufficio successivo, codici postali e firmatario: per il motivo di una mancata consegna consulta cypruspost.post.

Peek interroga direttamente Cyprus Post fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 sull'orologio dell'ultima scansione (ora di Cipro a Cipro) del giorno in cui l'invio esce con il postino, mentre il pacco è aperto in Peek o nel giorno di consegna con le notifiche attive. Dopo due giorni senza novità (12 ore mentre mostra ancora «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un invio in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da…», ricorda che «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Cyprus Post.» e propone «Chiedi aiuto a Cyprus Post».

## Domande frequenti su Cyprus Post

### Cyprus Post è il Department of Postal Services?

Sì. L'Unione Postale Universale indica il Department of Postal Services (Cyprus Post) quale operatore designato di Cipro, servizio statale incaricato dei servizi postali, e il regolatore postale OCECPR indica Cyprus Post come fornitore del servizio postale universale.

### Cosa significa CY alla fine del codice?

Che il codice è stato emesso sotto l'autorità di Cipro. Nello standard S10 UPU le ultime due lettere identificano il paese sotto la cui autorità il codice è stato emesso e non provano quindi con certezza il luogo di spedizione. Peek interroga Cyprus Post per questi codici.

### Posso tracciare un invio con numero che inizia con U?

Non come invio tracciato. Lo standard S10 riserva i numeri U agli invii della posta-lettere contenenti merci che viaggiano senza tracciamento per il cliente: il codice serve alla visibilità nella filiera, come i dati elettronici anticipati. Peek interroga comunque Cyprus Post per un codice U terminante in `CY`.

### Come presentare reclamo contro Cyprus Post?

Scrivi prima a Cyprus Post. Se non risponde entro 15 giorni o la risposta non ti soddisfa, presenta reclamo al regolatore postale, l'Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). Puoi farlo online; il sito è in greco ma offre un modulo di reclamo in inglese.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 caratteri, lettere iniziali R, C, E, L e U, cifra di controllo, codice paese emittente
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, operatore designato
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, servizio statale per i servizi postali (in greco)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post fornitore del servizio postale universale (in greco)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – prima per iscritto all'operatore, 15 giorni, reclamo online, modulo in inglese (in greco)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – consegna entro 30 giorni, termine extra, risoluzione del contratto (in greco)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – linea consumatori gratuita 1429 (in greco)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € per articolo dal 1° luglio 2026, tassa di gestione UE di 2 € dal 1° novembre 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – definizione di articolo, invii postali, tassa su qualsiasi valore, soggetto obbligato, IVA sulla tassa
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – dazi d'importazione e IVA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek: tracker inglese Cyprus Post, colonne e nomi degli eventi
:::
