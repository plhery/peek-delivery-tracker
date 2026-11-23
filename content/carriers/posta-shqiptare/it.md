---
title: Tracking Posta Shqiptare: codici AL, stati e dogana
description: Traccia un pacco o EMS Posta Shqiptare (poste albanesi): formato AL, stati in albanese spiegati, dogana in Albania, tempi e contatti.
slug: tracking-posta-shqiptare
published: 2026-11-23
updated: 2026-11-23
---

Posta Shqiptare (Albanian Post) è il servizio postale nazionale dell'Albania, con 533 uffici postali nel paese. Gestisce corrispondenza e pacchi in Albania, dall'estero e verso altri paesi, EMS incluso. Traccia una spedizione nel riquadro «Gjurmo objektin» (traccia un oggetto) su [postashqiptare.al](https://www.postashqiptare.al/), nella sua app e-Posta oppure incollando il codice nel riquadro in alto. Per un acquisto online, chiedi il codice al venditore.

## Codice di tracciamento Posta Shqiptare: come si presenta

Posta Shqiptare traccia gli invii con un codice a barre di 13 caratteri: due lettere per il tipo di servizio, nove cifre e due lettere per il paese di spedizione. L'ultima delle nove cifre è una cifra di controllo. Con cifre inventate:

| Formato (inventato) | Che cos'è |
| --- | --- |
| `RR482917367AL` | Inizia con C, E, L o R e finisce in `AL`: una spedizione impostata in Albania. |
| `UA619402838AL` | Altre lettere iniziali, comunque terminante in `AL`: anch'essa numerata in Albania. |
| `RR739402812CN` | Termina con altre lettere, qui `CN` per la Cina: numerata dalle poste di un altro paese. Un pacco diretto in Albania ha di solito un codice simile e il tracker di Posta Shqiptare lo segue comunque. |

Il riquadro sul sito di Posta Shqiptare accetta solo questo formato; qualsiasi altro valore restituisce `Kodi eshte i gabuar!` (il codice è errato). Nessun codice? Posta Shqiptare consiglia di chiedere allo sportello dell'ufficio postale che distribuisce la tua posta, indicando nome e cognome. Vedi [come trovare il codice di tracciamento](guide:find-tracking-number).

## Stati di tracciamento Posta Shqiptare spiegati

Le righe di accettazione e lavorazione di Posta Shqiptare sono in albanese con l'inglese dopo la barra; l'invio all'estero e le righe riportate dalle poste del paese di destinazione sono solo in albanese. Le diciture riconosciute da Peek, grosso modo in ordine:

| Messaggio di Posta Shqiptare | Che cosa significa per te |
| --- | --- |
| `Pranim objekti nga klienti / Receive item from customer` | Accettato presso un ufficio Posta Shqiptare. |
| `Objekti u nxor nga thesi per perpunim / Receive item at location (Inb)` | Estratto dal sacco per la lavorazione in un ufficio o centro di smistamento in Albania, poco dopo l'accettazione e prima che lasci il paese, anche se la parte inglese reca `(Inb)`, in ingresso. |
| `Objekti u fut ne thes dhe u dergua jashte` | Inserito nel sacco e spedito all'estero dall'ufficio di scambio albanese: sta lasciando il paese. |
| `Objekti u pranua ne zyren e shkembimit` | Le poste del paese di destinazione lo hanno ricevuto al proprio ufficio di scambio: è arrivato in quel paese. |
| `Regjistrimi i informacionit doganor te objektit` | Informazioni doganali registrate: è in dogana lì o trattenuto dalla dogana. |
| `Objekti u dergua ne zyren destinacion` | Inoltrato dall'ufficio di scambio verso la consegna. |
| `Objekti u pranua ne zyren e shperndarjes` | Arrivato all'ufficio di distribuzione. |
| `Tentative e pasuksesshme ne shperndarjen e objektit` | Tentativo di consegna non riuscito. Può seguirne un altro. |
| `Objekti u dorezua` | Consegnato. |

Alcune spedizioni EMS verso l'estero viaggiano con un corriere e Posta Shqiptare ne riporta le righe in albanese:

| Riga del corriere | Che cosa significa per te |
| --- | --- |
| `Nisur nga origjina` | Ha lasciato l'Albania. |
| `Mbajtur ne dogane` | Trattenuto in dogana nel paese di destinazione. |
| `Mbërritur në qendrën e shpërndarjes` | Presso il centro di distribuzione del corriere. |
| `Marrë nga korrieri` | Preso in carico dal corriere. Non significa che sia già in consegna. |
| `Dorëzuar` | Consegnato. |

Peek classifica queste righe nei propri stati: «Preso in carico», «In transito», «In dogana», «Tentativo di consegna» e «Consegnato». Una riga non ancora riconosciuta compare come scritta da Posta Shqiptare. Approfondisci in [che cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Ricevere un pacco dall'estero in Albania

**Dogana.** Posta Shqiptare sdogana in anticipo le spedizioni dall'estero con merci di valore superiore a 22 €, o regali oltre 44 €, fino a 1.000 € per invio. L'invio va all'ufficio postale del centro della località di destinazione e ricevi un avviso scritto. Lì paghi il dazio doganale e una commissione per la dichiarazione doganale e ritiri subito l'oggetto. Due pagine di Posta Shqiptare divergono sull'aliquota: la pagina dogana indica il 22,4% del valore dichiarato per acquisti online sopra i 22 €, la FAQ dal 22,4% al 38% per invii dichiarati sopra i 22 €, conteggiando prezzo più trasporto come valore doganale. Devi recarti all'ufficio doganale della filiale Transit Centre solo se la dogana ferma l'oggetto per ispezione o trova merci non ammesse. Altro in [pacchi fermi in dogana](guide:customs).

**Tempi di consegna in Albania.** Standard di Posta Shqiptare per gli invii consegnati prima dell'ultima raccolta dell'ufficio: giorno seguente entro le 14:00 nella stessa città, giorno seguente (entro 24 ore) in ogni altra città e fino a 3 giorni lavorativi in media nei villaggi.

## Spedizioni Posta Shqiptare all'estero: servizi e consegna

**Servizi.** Un piccolo pacchetto (paketë e vogël) di merci arriva fino a 2 kg, un pacco postale (kolipostë) fino a 20 kg. EMS trasporta documenti e merci fino a 20 kg verso circa 200 destinazioni in 2–5 giorni secondo Posta Shqiptare.

**Tracked.** Dal 3 gennaio 2026 i piccoli pacchetti di merci fino a 2 kg viaggiano all'estero come Tracked, che ha sostituito la raccomandata per essi. Sono richiesti solo tre passaggi: uscita dall'ufficio di scambio albanese, arrivo all'ufficio di scambio di destinazione e arrivo all'ufficio di consegna, tentativo di consegna o consegna. Tracked non prevede firma alla consegna, solo la scansione di consegna, e nessun indennizzo per smarrimento o danni.

**Tempi di consegna.** Gli standard di Posta Shqiptare per pacchetti e pacchi sono in media 7–10 giorni lavorativi verso l'Europa e 10–14 verso Americhe, Asia e Africa.

**Chi consegna.** Dopo l'uscita dall'Albania subentrano le poste del paese di destinazione, e il tracker di Posta Shqiptare mostra le righe di quelle poste in albanese. Controlla anche il tracciamento delle tue poste con lo stesso codice `AL`: [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) o NZ Post. Tentativi di consegna e ritiro seguono le regole delle tue poste: vedi [consegna mancata](guide:missed-delivery).

**Dogana UE.** L'Albania è fuori dall'UE, quindi un pacco diretto in Italia, Irlanda, Grecia o un altro paese UE passa la dogana locale. Dal 1° luglio 2026 i beni acquistati online fuori UE in ordini fino a 150 € prevedono un dazio UE di 3 € per articolo. Dal 1° novembre 2026 si applica anche la tariffa di gestione dell'Unione di 2 € per articolo, indipendentemente dal valore. Un articolo raggruppa i beni del pacco con stessa classificazione tariffaria, descrizione e, dove richiesta, origine. Altrove valgono le norme del tuo paese.

## Tracking Posta Shqiptare fermo, in ritardo o smarrito

**Silenzio dopo l'uscita dall'Albania.** Un pacchetto Tracked richiede solo tre scansioni, quindi diversi giorni senza notizie sono normali. Una volta arrivato, controlla il sito delle tue poste con lo stesso codice: vedi [perché il tracking smette di aggiornarsi](guide:tracking-not-updating).

**In ritardo, senza aggiornamenti.** Il mittente si reca di persona all'ufficio postale dove ha spedito l'oggetto e compila un modulo di ricerca con i dati di entrambe le persone, il contenuto e un recapito telefonico. Posta Shqiptare chiede poi notizie alle poste di destinazione.

**Reclami.** Il mittente può reclamare per mancato arrivo fino a 3 mesi dalla spedizione in Albania, o 6 mesi per l'estero. Per l'estero è gratuito, tramite modulo all'ufficio di spedizione. Posta Shqiptare risponde entro 15 giorni, o 2 mesi per l'estero. Per un pacco diretto a te, chiedi al mittente di verificare con le proprie poste, o al negozio per un ordine online: vedi [pacchi smarriti](guide:lost-parcel).

**Segnato consegnato ma assente.** Un pacchetto Tracked viene consegnato senza firma: rivolgiti prima alle poste che lo hanno consegnato e vedi [consegnato ma non ricevuto](guide:delivered-not-received).

## Numero di telefono e assistenza Posta Shqiptare

Canali di Posta Shqiptare:

- **Telefono:** +355 682044727.
- **Email:** asistence@postashqiptare.al.
- **Sede centrale:** Drejtoria e përgjithshme Posta Shqiptare, Rruga «Reshit Çollaku» nr. 4, Tirana. Orari indicati sotto nella pagina contatti: da lunedì a giovedì 08:00–16:30, venerdì 08:00–14:00 (ora albanese).
- **Reclami per posta estera:** tramite modulo presso l'ufficio postale in cui l'invio è stato spedito.

Posta Shqiptare avverte contro messaggi relativi ai suoi servizi che non provengono da essa: non cliccare sui link e non fornire dati. Pubblica informazioni ufficiali solo su postashqiptare.al e sui suoi canali social. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Posta Shqiptare con Peek

[Peek](/) riconosce come Posta Shqiptare un codice con C, E, L o R, un'altra lettera, nove cifre e `AL` quando la cifra di controllo è valida, e mostra «Corriere rilevato»; «Cambia» permette di sceglierne un altro. Con cifra di controllo errata indica «La cifra di controllo di questo numero non torna.»: controlla il codice o scegli «Traccia così com’è». Funzionano anche i link al tracker e-Posta. Sotto Posta Shqiptare il pacco mostra «In attesa di Posta Shqiptare» fino alla prima scansione.

Un codice che termina in `AL` iniziando con un'altra lettera, come U o V, non viene assegnato a Posta Shqiptare dalla sola forma. Incollalo comunque: Peek segue oltre 4.500 corrieri. Durante la verifica mostra «Verifica dei servizi di tracciamento…», interroga prima Posta Shqiptare e mostra «Posta Shqiptare ha questo pacco.» se lo conosce.

Un pacco in arrivo in Albania con il codice di un altro servizio postale viene assegnato a quest'ultimo, e Peek non interroga Posta Shqiptare da solo. Per seguirlo sul tracker di Posta Shqiptare apri il pacco, scegli «Cambia corriere» e seleziona Posta Shqiptare: Peek mantiene la cronologia durante il controllo.

Peek mostra ogni riga nel testo di Posta Shqiptare e le scansioni estere con il nome del paese in inglese. Omette eventuali nomi di ufficio contenenti un codice postale e qualsiasi aggiunta dopo la consegna che possa nominare chi ha ritirato l'invio. Non mostra una data di consegna prevista, poiché Posta Shqiptare non ne fornisce. Legge le righe di Posta Shqiptare sull'ora albanese e ricava il fuso delle righe estere dal luogo indicato o dalle scansioni vicine.

Quando tracci da un altro paese, Peek interroga con lo stesso codice `AL` anche le poste del tuo paese: USPS, Royal Mail ([Parcelforce](carrier:parcelforce) se inizia con E), An Post, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) in Germania, [Poste Italiane](carrier:poste-italiane), [ELTA](carrier:elta) in Grecia e altri. Passa a quelle poste non appena mostrano una scansione almeno recente quanto l'ultima di Posta Shqiptare, conservando la cronologia di Posta Shqiptare. Da Kosovo, Montenegro o Turchia non interroga altre poste.

Finché hai il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek interroga direttamente Posta Shqiptare fino a ogni 10 minuti finché il suo tracker risponde; altrimenti ogni ora. Dopo due giorni senza novità (12 ore mentre il pacco mostra ancora «Etichetta creata» o «Preso in carico»), controlla ogni ora; dopo sette giorni senza novità in «Etichetta creata», ogni 6 ore. Dopo quattro giorni senza scansioni su un pacco in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …», la nota «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Posta Shqiptare.» e il link «Chiedi aiuto a Posta Shqiptare». Avvisi: «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Posta Shqiptare, le tue poste né il negozio al posto tuo.

## Domande sul tracking Posta Shqiptare

### Come traccio un pacco Posta Shqiptare?

Inserisci il codice a 13 caratteri nel riquadro «Gjurmo objektin» su postashqiptare.al o nell'app e-Posta, oppure incollalo in Peek. Un pacco diretto in Albania si traccia con il codice delle poste mittenti.

### Perché il mio tracking Posta Shqiptare non si aggiorna?

Un pacchetto Tracked ha solo tre scansioni obbligatorie e, una volta lasciata l'Albania, le righe successive provengono dalle poste del paese di destinazione: controlla il loro sito con lo stesso codice. Se non arriva nulla per molto tempo, il mittente può chiedere una ricerca dove lo ha spedito.

### Quanto tempo impiega Posta Shqiptare per consegnare?

Secondo i propri standard, in Albania il giorno seguente tra città e fino a 3 giorni lavorativi in media verso i villaggi. All'estero, pacchetti e pacchi impiegano in media 7–10 giorni lavorativi verso l'Europa e 10–14 verso Americhe, Asia e Africa. EMS richiede 2–5 giorni secondo l'operatore.

### Qual è il numero di telefono di Posta Shqiptare?

+355 682044727, oppure email a asistence@postashqiptare.al. La pagina contatti riporta gli orari 08:00–16:30 dal lunedì al giovedì e 08:00–14:00 il venerdì (ora albanese) sotto l'indirizzo della sede centrale.

:::sources
- [Posta Shqiptare: Home](https://www.postashqiptare.al/) – casella di tracking, formato del codice, uffici postali
- [Posta Shqiptare: Pyetje/Përgjigje (FAQ)](https://www.postashqiptare.al/c/71/pyetje-prgjigje) – posta dall'estero, codice a barre, ricerche, dogana
- [Posta Shqiptare: Çështje doganore (dogana)](https://www.postashqiptare.al/c/43/shtje-doganore) – sdoganamento anticipato, avviso, dazio
- [Posta Shqiptare: Tracked](https://www.postashqiptare.al/c/207/tracked) – servizio Tracked, scansioni, firma, indennizzo
- [Posta Shqiptare: EMS](https://www.postashqiptare.al/c/39/sherbim-postar-ekspres-i-jashtem-ems) – peso EMS, destinazioni, giorni
- [Posta Shqiptare: Shërbim Postar Universal](https://www.postashqiptare.al/c/37/shrbim-postar-universal) – piccoli pacchetti e pacchi
- [Posta Shqiptare: Universal service standards](https://www.postashqiptare.al/c/82/informacion-pr-klientin-pr-standardet-e-shrbimit-universal-postar) – tempi di consegna
- [Posta Shqiptare: Përse e-Posta?](https://www.postashqiptare.al/c/110/prse-e-posta) – app e-Posta
- [Posta Shqiptare: Ankimim (reclami)](https://www.postashqiptare.al/c/157/ankimim) – scadenze, risposte, modulo
- [Posta Shqiptare: Kontakto Postën Shqiptare](https://www.postashqiptare.al/c/70/kontakto-postn-shqiptare) – telefono, email, indirizzo, orari
- [Posta Shqiptare: !!KUJDES !! (avviso)](https://www.postashqiptare.al/d/355/kujdes-) – messaggi falsi
- [Commissione europea: riforma doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 €, tariffa di gestione di 2 €
- [Commissione europea: guida sul dazio di 3 € e la tariffa di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – per articolo, definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – testi delle scansioni, che cosa legge Peek, quali poste interroga in seguito
:::
