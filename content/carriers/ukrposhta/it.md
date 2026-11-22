---
title: Tracciamento Ukrposhta: numeri, stati e ritiro in ufficio
description: Segui un pacco Ukrposhta (Poste ucraine): codici a 13 cifre e UA, significato degli stati inglesi, ritiro in ufficio, spedizioni estere e Peek.
slug: tracciamento-ukrposhta
published: 2026-11-22
updated: 2026-11-22
---

Ukrposhta (Укрпошта) è il servizio postale nazionale dell’Ucraina. Puoi seguire un pacco su track.ukrposhta.ua, nell’app Ukrposhta oppure incollando il codice nel riquadro qui sopra. Il numero proviene dal mittente: 13 cifre dentro l’Ucraina, oppure due lettere, nove cifre e due lettere per spedizioni da o verso l’estero.

## Numero di tracciamento Ukrposhta

I codici Ukrposhta hanno due formati, e il numero nazionale a 13 cifre termina con una cifra di controllo (modulo 11).

| Formato (inventato) | Che cos’è |
| --- | --- |
| `0548291736543` | Una spedizione nazionale in Ucraina: 13 cifre. |
| `RR482917367UA` | Una spedizione internazionale partita dall’Ucraina: due lettere, nove cifre, `UA`. |
| `RR482917367US` | Un invio diretto in Ucraina mantiene il codice delle poste estere e la sigla del paese d’origine. |

Nessun codice? Chiedilo al mittente oppure vedi [dove trovare il numero di tracciamento](guide:find-tracking-number).

[Da non confondere](guide:tracking-number-formats): in Ucraina i codici Nova Poshta hanno 14 cifre e molti numeri Meest terminano con `G`.

## Cosa significano gli stati di tracciamento Ukrposhta

Peek richiede a Ukrposhta i suoi testi inglesi. I più comuni, in ordine approssimativo:

| Stato Ukrposhta | Cosa significa per te |
| --- | --- |
| `Created online. Waiting acceptance` | Il mittente ha [creato l’etichetta online](guide:tracking-statuses); Ukrposhta non ha ancora l’invio. |
| `Registered` | Accettato da Ukrposhta. |
| `Departed from the Branch` | Partito dall’ufficio postale di accettazione. |
| `Arrived to the Logistics Center` | In smistamento lungo il percorso in Ucraina. |
| `Arrival at inward office of exchange` | Una spedizione dall’estero è arrivata in Ucraina. |
| `Held at inward office of exchange` | Trattenuto all’arrivo per la dogana. |
| `Item presented to customs` | Presentato alla dogana per il controllo. |
| `Released from customs for further delivery` | Svincolato dalla dogana. |
| `Departure from inward office of exchange` | Uscito dalla dogana e diretto verso la tua zona. |
| `Item out for physical delivery` | In consegna. |
| `Arrived to the Branch` | Arrivato nel tuo ufficio postale, pronto per il ritiro. |
| `Final delivery` | Consegnato: rimesso al destinatario. |
| `Returned to Sender (Due to Recipient's Refusal)` | Rifiutato dal destinatario e rispedito indietro. |
| `Return: Delivered to Sender` | Tornato al mittente: restituito, non consegnato a te. |

I passaggi di rientro iniziano con `Return:`. Non appena una riga segnala il ritorno, Peek mostra una consegna successiva come «Restituito al mittente» anziché «Consegnato».

## Ritiro in ufficio postale e consegna Ukrposhta

Gli stati indicano se il pacco viene consegnato a domicilio (`Item out for physical delivery`) oppure attende in ufficio (`Arrived to the Branch`, mostrato come «Pronto per il ritiro» in Peek). L’app Ukrposhta mostra gli uffici su una mappa con CAP, indirizzi e orari. Chiedi al tuo ufficio quanto tempo tiene in giacenza una spedizione e quale documento portare.

## Pacco Ukrposhta in ritardo, smarrito o danneggiato

Se non cambia nulla per giorni, vedi [tracciamento che non si aggiorna](guide:tracking-not-updating); per un fermo doganale, vedi [pacchi fermi in dogana](guide:customs).

**Smarrito o in ritardo da o verso l’estero.** Secondo le regole dell’Unione Postale Universale (UPU), ogni posta nazionale deve accettare un reclamo su pacchi o invii raccomandati o assicurati, dal mittente o dal destinatario, entro sei mesi dal giorno successivo alla spedizione. Le indagini sono di regola gratuite, ma l’Ucraina si riserva il diritto di applicare una tariffa. Il risarcimento spetta al mittente, oppure a te se il mittente vi rinuncia per iscritto. Per acquisti online mai arrivati, contatta prima il venditore.

**Danneggiato all’arrivo.** Segnala il danno prima di ritirare il pacco. Secondo le stesse regole UPU, le poste restano responsabili dei danni rilevati prima o alla consegna, e la posta che consegna redige un verbale da controfirmare rilasciandotene copia.

## Assistenza clienti Ukrposhta

Per problemi con l’app, la scheda Google Play di Ukrposhta indica mobile-app@ukrposhta.ua. Per una spedizione internazionale smarrita o in ritardo, apri un reclamo come sopra: in base alle regole UPU, Ukrposhta deve accettarlo sia da te sia dal mittente.

## Seguire Ukrposhta con Peek

[Peek](/) traccia oltre 4.500 corrieri. Per 13 cifre con cifra di controllo valida Ukrposhta, Peek interroga Ukrposhta — insieme a [FAN Courier](carrier:fan-courier) o iMile quando il numero rientra anche nel loro formato — e indica Ukrposhta con «ha questo pacco» quando la conosce solo lei; se lo conosce anche un altro corriere, scegli tu. Un codice di 13 cifre con cifra di controllo errata non viene proposto a Ukrposhta: sceglila con «Cambia». Un codice S10 che inizia con `C`, `E`, `L` o `R` e termina in `UA` con cifra di controllo valida viene riconosciuto subito come Ukrposhta; con altra lettera valida, Peek interroga Ukrposhta e [Chronopost](carrier:chronopost), continuando a controllare dopo l’aggiunta se nessuno lo conosce ancora. Un link track.ukrposhta.ua con codice (`barcode=`) assegna sempre Ukrposhta.

Un numero postale di un altro paese non viene proposto d’ufficio a Ukrposhta. Quando la spedizione è diretta in Ucraina, Peek può seguirla fino a Ukrposhta, oppure puoi selezionare «Ukrposhta» con «Cambia».

Peek interroga il servizio dietro l’app Ukrposhta; se non risponde o non conosce un invio internazionale, apre la pagina di tracciamento di Ukrposhta in un browser sul proprio server. Mostra ogni tappa su mappa con luogo e paese, i passaggi doganali e l’orario di consegna. Ukrposhta trasmette gli orari senza fuso: le scansioni in Ucraina sono lette sull’ora di Kyiv e mostrate nel tuo fuso orario (in fondo alla cronologia compare «Orari nel tuo fuso orario:» con il suo nome); il fuso di una tappa all’estero o senza paese (come una tratta aerea) Peek lo ricava dal luogo o dalle tappe vicine, quindi quell’ora può risultare sfasata. Peek non mostra data prevista di arrivo e omette data pianificata e CAP; l’unico punto di ritiro nominato è la filiale su `Arrived to the Branch`. Gli stati mantengono le diciture inglesi di Ukrposhta, compreso `Final delivery`.

Mentre tieni il pacco aperto sullo schermo — oppure il giorno della consegna con notifiche attive — Peek controlla fino a ogni 10 minuti, e una volta in consegna, dalle 6:00 nell’ora dell’ultima scansione (ora di Kyiv in Ucraina), fino a ogni 2 minuti (o ogni 5 se l’ultimo controllo ha usato il browser), finché il servizio o il sito Ukrposhta rispondono. Altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche, scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni, salvo in dogana o con un problema segnalato, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Ukrposhta.», con il link «Chiedi aiuto a Ukrposhta» al suo centro contatti solo in quell’avviso.

## Domande sul tracciamento Ukrposhta

### Come traccio un pacco Ukrposhta nel Regno Unito?

Quando aggiungi un invio Ukrposhta nel Regno Unito, Peek interroga con lo stesso codice anche [Royal Mail](carrier:royal-mail), oppure Parcelforce per un codice che inizia con `E` (EMS). Appena quelle poste registrano una scansione recente almeno quanto l’ultima di Ukrposhta, Peek prosegue lì conservando le tappe di Ukrposhta.

### Perché il mio numero di tracciamento Ukrposhta non funziona?

Controllalo carattere per carattere con quello del mittente. I codici a 13 cifre terminano con una cifra di controllo e Peek non propone Ukrposhta se è errata. Se il codice è giusto, Ukrposhta potrebbe non averlo ancora registrato: sotto Ukrposhta mostra «In attesa di Ukrposhta» mentre Peek continua a verificare. Se due giorni dopo non lo conosce ancora e nessun altro ha risposto, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.», passando poi a controlli giornalieri e, dopo una settimana, settimanali. Se nulla cambia dopo vari giorni, chiedi al mittente.

### Come traccio un pacco spedito dall’estero verso l’Ucraina?

Incolla il codice fornito dal mittente. Peek segue la spedizione presso le poste d’origine; quando indicano l’Ucraina come destinazione (o la aggiungi dall’Ucraina), Peek interroga anche Ukrposhta e passa lì appena mostra una scansione almeno altrettanto recente, mantenendo i passaggi precedenti. Per seguirlo con Ukrposhta fin dall’inizio, scegli «Ukrposhta» con «Cambia». Un pacco DHL dalla Germania il cui codice inizia con `C` viene seguito invece con Meest.

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formati e cifre di controllo, link track.ukrposhta.ua, servizio app e fallback browser, testi inglesi, resi, ora di Kyiv e righe estere, campi esclusi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – corrieri interrogati per formato, passaggi a Ukrposhta (e a Meest per i `C` DHL) e verso Royal Mail o Parcelforce, formati Nova Poshta e Meest
- [Unione Postale Universale: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – reclami entro sei mesi, riserva dell’Ucraina su tariffe, indennizzo al mittente (o rinuncia scritta) e verbale danni alla consegna
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – tracciamento in app, mappa uffici con CAP, indirizzi e orari, e-mail di supporto dell’app
:::
