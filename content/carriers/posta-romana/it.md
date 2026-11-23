---
title: Tracking Poșta Română: codici RO, stati e dogana
description: Traccia una spedizione Poșta Română: formato RO, stati in rumeno spiegati, pacchi dall'estero, dazi e tasse doganali, reclami e contatti.
slug: tracking-posta-romana
published: 2026-11-23
updated: 2026-11-23
---

Poșta Română (Compania Națională Poșta Română, CNPR) fornisce il servizio postale universale in Romania e consegna in ogni città e villaggio del paese. Traccia una spedizione sulla sua pagina [Track & Trace](https://www.posta-romana.ro/en/track-trace.html) o incolla il codice nel riquadro in alto. Ogni operatore postale fornisce informazioni solo per la propria parte di viaggio, quindi il tracker di Poșta Română copre solo la Romania: un pacco da un negozio estero compare prima sul sito delle poste mittenti e su quello di Poșta Română appena entra in Romania. Non trovi il codice? Vedi [come trovare il codice di tracciamento](guide:find-tracking-number).

## Codice di tracciamento Poșta Română: come si presenta

Il codice di Poșta Română ha 13 caratteri: due lettere, nove cifre e due lettere, senza spazi. I suoi codici terminano con `RO`. È il [formato postale internazionale](guide:tracking-number-formats), quindi il codice di un altro servizio postale ha la stessa struttura con il proprio codice paese alla fine. L'ultima delle nove cifre è una cifra di controllo. Con cifre inventate:

| Formato (inventato) | Che cos'è |
| --- | --- |
| `CP482917367RO` | Una spedizione numerata da Poșta Română, in Romania o verso l'estero. Le prime due lettere cambiano in base al servizio; ciò che conta è `RO` alla fine. |
| `RR739402812CN` | Termina con altre lettere, qui `CN`: l'ha numerata il servizio postale di un altro paese, qui [China Post](carrier:china-post). Un pacco in arrivo in Romania dall'estero ha di solito un codice simile. |

Poșta Română traccia raccomandate e assicurate, pacchi, pacchi in contrassegno, EMS e stampe raccomandate. La posta ordinaria non è tracciabile.

## Stati di tracciamento Poșta Română spiegati

Peek legge ogni passaggio in rumeno, di solito senza accenti, con l'ufficio o il centro che ha scansionato la spedizione. Le righe riconosciute da Peek, grosso modo in ordine:

| Messaggio di Poșta Română | Che cosa significa per te |
| --- | --- |
| `Primit de la expeditor` | Ricevuto dal mittente: Poșta Română ha la spedizione. |
| `In prelucrare` | In lavorazione nel luogo indicato: ufficio postale, centro regionale o ufficio di scambio internazionale di Bucarest. |
| `In transport` | Ha lasciato il luogo indicato ed è in viaggio. |
| `Sosit in Romania` | Arrivato in Romania dall'estero, all'ufficio di scambio internazionale. |
| `Expediat din Romania` | Spedito all'estero. Il tracciamento di Poșta Română termina qui. |
| `Predat la factor` | Consegnato al postino per il giro: in consegna. |
| `Programat pentru predare incepand cu aceasta data` | Pronto per la consegna a partire da quella data nell'ufficio postale indicato. Ritiralo lì. |
| `Predat la destinatar` | Consegnato al destinatario: consegnato. |
| `Returnat catre expeditor` | Sta tornando al mittente. |

Peek classifica queste righe nei propri stati, come «In transito» o «Pronto per il ritiro», e riconosce anche le versioni accentate. Una consegna dopo `Returnat catre expeditor` significa che il pacco è rientrato al mittente, quindi Peek mostra «Restituito al mittente». Ogni altra riga viene interpretata secondo le regole generali di Peek oppure mostrata così com'è, senza stato, se non è nota. Approfondisci in [che cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Ricevere un pacco Poșta Română in Romania

**Tempi di consegna.** Le condizioni generali di Poșta Română fissano uno standard Z+5 per la consegna in Romania di un pacco fino a 20 kg proveniente dall'estero: cinque giorni dall'ingresso nella rete di Poșta Română. Per un pacco proveniente da fuori UE si aggiunge il tempo dello sdoganamento. Vedi [tempi di consegna](guide:delivery-times).

**Ritiro.** Quando il pacco ti attende in un ufficio postale, il tracciamento mostra `Programat pentru predare incepand cu aceasta data` con il nome dell'ufficio. Poșta Română lo custodisce per almeno 5 giorni di calendario dalla notifica. Se si trova in un ufficio postale con assistenza doganale, contatta quell'ufficio prima di recarti sul posto. Vedi [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

**Dogana sui pacchi da fuori UE.** La Romania fa parte dell'UE, quindi un pacco proveniente da Cina, Regno Unito o Stati Uniti passa la dogana e ogni invio con merci richiede una dichiarazione doganale elettronica, regali inclusi. Poșta Română dichiara per te gli acquisti online fino a 150 € come rappresentante e riscuote l'IVA ordinaria del 21%, salvo pagamento già avvenuto tramite IOSS e confermato dai dati trasmessi col pacco. Dal 1° luglio 2026 i beni acquistati online fuori UE in ordini fino a 150 € prevedono un dazio doganale UE di 3 € per articolo, come riportato anche dalla pagina doganale di Poșta Română. Dal 1° novembre 2026 si aggiunge una tariffa di gestione dell'Unione di 2 € per articolo, indipendentemente dal valore. Un articolo raggruppa i beni di un pacco con stessa classificazione tariffaria, descrizione e, se richiesta, origine.

Poșta Română applica un diritto di presentazione in dogana per questi pacchi di basso valore, inclusi regali e pacchi IOSS. Se la dogana seleziona il pacco per un controllo del rischio, ricevi un avviso che richiede documenti e si applica invece una commissione di intermediazione doganale più alta per pacco. Per le formalità doganali serve un codice EORI, anche da privato per un regalo soggetto a sdoganamento. Le imposte di importazione si pagano in contanti, oppure con carta negli uffici postali con assistenza doganale dotati di POS. Altro in [pacchi fermi in dogana](guide:customs).

**Ritardi e resi in dogana.** Poșta Română lavora questi pacchi a Bucarest usando i dati elettronici trasmessi in anticipo dalle poste mittenti. Dati mancanti o incompleti causano ritardi; in loro assenza o senza moduli doganali CN22/CN23 corretti, il pacco torna al mittente. Hai ordinato su [AliExpress](guide:aliexpress-orders), [Temu](guide:temu-orders) o [Shein](guide:shein-orders)? Vedi [tracking dalla Cina](guide:tracking-from-china).

## Spedizioni Poșta Română verso l'estero

Il tracker di Poșta Română segue una spedizione fino all'uscita dalla Romania e non mostra nulla mentre attraversa altri paesi. Dopo `Expediat din Romania`, usa il tracker delle poste del paese di destinazione con lo stesso codice `RO`, come [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [Poste Italiane](carrier:poste-italiane) o [Correos](carrier:correos-spain).

## Tracking Poșta Română fermo, pacco smarrito o restituito

**Nessun nuovo passaggio.** Il tracker copre solo la Romania: un invio in uscita si ferma a `Expediat din Romania`, uno in ingresso non mostra nulla finché non arriva in Romania. Un pacco extra-UE può anche attendere in dogana. Vedi [perché il tracking smette di aggiornarsi](guide:tracking-not-updating).

**Smarrito, in ritardo o danneggiato.** Mittente o destinatario possono presentare reclamo, nei modi indicati sotto, entro 6 mesi dalla spedizione; per gli invii internazionali i 6 mesi decorrono dal giorno successivo alla spedizione e per gli invii Express internazionali come EMS il limite è da 1 a 4 mesi. Tieni pronti il codice e la data di spedizione. Poșta Română risponde entro 3 mesi, indennizzo compreso. Puoi anche chiedere l'indennizzo per una raccomandata smarrita o danneggiata in qualsiasi ufficio postale con la ricevuta di spedizione; la posta non registrata non è coperta. Per gli invii internazionali l'indennizzo arriva fino a 30 DSP per documenti raccomandati e fino a 40 DSP per pacco più 4,5 DSP per kg per i pacchi standard; un invio assicurato è coperto fino al valore dichiarato. Acquisto online? Avvisa anche il negozio: vedi [pacchi smarriti](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Consegnato ma non ricevuto.** Presenta reclamo come sopra: il modulo internazionale elenca tra i motivi la mancata consegna e il contenuto mancante. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Restituito.** `Returnat catre expeditor` indica che l'invio torna al mittente. Peek lo mostra come «Restituito al mittente»: vedi [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Poșta Română

Canali di Poșta Română:

- **Telefono:** 021 9393, dal lunedì al venerdì, dalle 8:00 alle 20:00 ora rumena. Chiamata a tariffa ordinaria.
- **Email:** suportclienti@ro.post.
- **Reclami:** online con il modulo interno o il [modulo di reclamo internazionale](https://www.posta-romana.ro/en/reclamatii-externe.html), allo sportello di un ufficio postale oppure per posta con ricevuta di ritorno. Poșta Română non accetta reclami per telefono né tramite il modulo di supporto generico.

Poșta Română non chiede mai dati bancari, numeri di carta o PIN, né pagamenti online per spese di consegna. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Poșta Română con Peek

Poiché tutte le poste usano lo stesso formato a 13 caratteri, [Peek](/) non identifica Poșta Română dal solo codice `RO`. Incollalo comunque: Peek segue oltre 4.500 corrieri. Mostra «Verifica dei servizi di tracciamento…» mentre interroga i corrieri compatibili, Poșta Română per prima, e indica «Poșta Română ha questo pacco.» se lo conosce. In caso contrario, Peek lo archivia come operatore postale sconosciuto e interroga i servizi di tracciamento e anche le poste del paese di destinazione quando uno di essi lo nomina; nei primi 30 giorni interroga di nuovo Poșta Română e sposta lì il pacco non appena mostra movimenti. Puoi anche selezionare Poșta Română con «Cambia». Con una cifra di controllo errata Peek avvisa «La cifra di controllo di questo numero non torna.»: ricontrolla il codice o scegli «Traccia così com’è». Funziona anche un link al tracker di Poșta Română: Peek ne ricava il codice. Sotto Poșta Română il pacco mostra «In attesa di Poșta Română» fino alla prima scansione.

Peek legge il tracciamento ufficiale di Poșta Română. Mostra ogni passaggio nel testo rumeno originale con il luogo indicato e l'ufficio postale dove il pacco attende come «Punto di ritiro», o «Ritirato presso» dopo il ritiro. Legge ogni scansione sull'ora rumena, poiché il tracker riporta solo scansioni in Romania. Omette origine, data di partenza e orari dell'ufficio. Una riga `Programat pentru predare incepand cu aceasta data` datata in anticipo, come per il giorno dopo, compare in Peek quando arriva quel momento. Poiché il tracker non fornisce data prevista, peso né nomi, Peek non ne mostra alcuno. «Apri il sito Poșta Română» nella pagina del pacco apre il codice sul suo tracker. Se scegli Poșta Română per un codice non compatibile, Peek mostra «non emette numeri come questo».

Quando tracci un codice `RO` da fuori Romania, Peek interroga con lo stesso codice anche le poste del tuo paese: USPS negli Stati Uniti, Royal Mail nel Regno Unito ([Parcelforce](carrier:parcelforce) per un codice che inizia con E), An Post in Irlanda, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) in Germania, [La Poste](carrier:la-poste) in Francia, Israel Post in Israele, le poste di ciascun altro paese UE (Spring GDS nei Paesi Bassi), della Svizzera, della Norvegia e della Moldavia, e altre ancora, dal Giappone al Brasile. Passa a quelle poste non appena mostrano una scansione almeno recente quanto l'ultima di Poșta Română, mantenendo la cronologia di Poșta Română. Dalla Romania Peek non interroga altre poste perché Poșta Română non indica la destinazione; da un paese di cui Peek non conosce le poste, come la Turchia o gli Emirati Arabi Uniti, non ne interroga nessuna. Al contrario, quando un altro servizio postale indica la Romania come destinazione — o non ne indica alcuna e tu tracci dalla Romania — Peek interroga Poșta Română con quel codice.

Finché hai il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek interroga direttamente Poșta Română fino a ogni 10 minuti, e ogni 2 minuti dalle 6:00 ora romena nel giorno in cui affida il pacco al postino (`Predat la factor`), finché il suo tracker risponde; altrimenti ogni ora. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità in «Etichetta creata», ogni 6 ore. Dopo quattro giorni senza scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Poșta Română.» e il link «Chiedi aiuto a Poșta Română». Avvisi: «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Poșta Română, le tue poste né il negozio al posto tuo.

## Domande sul tracking Poșta Română

### Come traccio un pacco Poșta Română?

Inserisci il codice di 13 caratteri nella pagina Track & Trace di Poșta Română o incollalo in Peek. Per un pacco dall'estero usa il codice fornito dal mittente o dal negozio: Poșta Română lo mostra appena entra in Romania.

### Perché il mio tracking Poșta Română non si aggiorna?

Se l'ultimo passaggio è `Expediat din Romania`, la spedizione ha lasciato la Romania e il tracker di Poșta Română segue gli invii solo dentro il paese: controllalo sulle poste del paese di destinazione con lo stesso codice. Un pacco extra-UE può anche attendere in dogana.

### Per quanto tempo Poșta Română custodisce un pacco all'ufficio postale?

Almeno 5 giorni di calendario dal giorno della notifica. Ritiralo all'ufficio indicato nel tracciamento.

### Devo pagare la dogana su un pacco proveniente da fuori UE?

Per gli acquisti online, sì. Gli ordini fino a 150 € prevedono un dazio di 3 € per articolo, e ogni ordine online la tariffa di gestione dell'Unione di 2 € per articolo, qualunque sia il valore. Poșta Română riscuote inoltre il 21% di IVA salvo pagamento tramite IOSS e applica un diritto di presentazione in dogana.

### Qual è il numero del servizio clienti Poșta Română?

021 9393, dal lunedì al venerdì dalle 8:00 alle 20:00 ora rumena, oppure email a suportclienti@ro.post. Poșta Română non accetta reclami per telefono.

:::sources
- [Poșta Română: Track & Trace](https://www.posta-romana.ro/en/track-trace.html) – codice a 13 caratteri, tracking solo in Romania, tracker delle poste di destinazione, moduli reclamo, contatti, avviso truffe
- [Poșta Română](https://www.posta-romana.ro/) – avviso contro le truffe
- [Poșta Română: Ajutor (copia archiviata)](https://web.archive.org/web/20260713182410/https://www.posta-romana.ro/ajutor.html) – servizi tracciabili, competenza di ogni operatore, indennizzo in qualsiasi ufficio postale, dichiarazioni doganali, IVA al 21%, diritti di presentazione e di intermediazione, ufficio di scambio, resi senza dati, pagamento, dichiarazione autonoma
- [Poșta Română: Modificări vamale începând cu 01.07.2026](https://www.posta-romana.ro/a2050/servicii-destinatari/informatii-utile/intrebari-frecvente/modificari-vamale-incepand-cu-01072026.html) – dazio di 3 € per articolo dal 1° luglio 2026, EORI per regali
- [Poșta Română: Termeni și condiții](https://www.posta-romana.ro/a119/termeni-de-utilizare/termeni-si-conditii.html) – servizio universale, consegna in tutta la Romania, standard Z+5, giacenza di 5 giorni, responsabilità, regole sui reclami
- [Poșta Română: International complaint](https://www.posta-romana.ro/en/reclamatii-externe.html) – chi può reclamare, motivi di reclamo
- [Poșta Română: Contact](https://www.posta-romana.ro/contact.html) – telefono, email, orari, nessun reclamo per telefono o dal modulo di assistenza
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 €, tariffa di gestione di 2 €
- [Commissione europea: guida sul dazio di 3 € e la tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – per articolo, definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – testi delle scansioni, ciò che Peek legge, quali poste interroga in seguito
:::
