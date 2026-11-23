---
title: Tracking Foxpost: stati, locker e contatti
description: Traccia un pacco Foxpost in Ungheria: numero CLFOX, significato degli stati ungheresi, ritiro ai locker, giacenza e contatti.
slug: tracking-foxpost
published: 2026-11-23
updated: 2026-11-23
---

Foxpost è un servizio pacchi ungherese che consegna ai propri locker e a domicilio in tutta l'Ungheria; da gennaio 2026 ha assorbito anche Packeta Hungary. Traccia una spedizione su [foxpost.hu](https://foxpost.hu/csomagkovetes/) oppure incolla il codice nel box qui sopra. Il codice si trova alla fine dell'e-mail inviata da Foxpost alla creazione del pacco e inizia di solito con `CLFOX`.

## Numero di tracking Foxpost

Il campo di ricerca di Foxpost accetta tre tipi di codice (cifre inventate):

| Numero | A cosa corrisponde |
| --- | --- |
| `CLFOX482917365048291` | `CLFOX` e cifre: pacco diretto a un locker o punto di ritiro Foxpost. Foxpost lo chiama anche codice a barre del pacco. |
| `Z4829173650` | `Z` e cifre: pacco Packeta. Se Foxpost non riconosce un codice Z, il portale rimanda al tracking Packeta. |
| `6663948271` | Cifre che iniziano con `66639`: pacco con consegna a domicilio. |

L'e-mail di Foxpost a te e al mittente termina con l'ID del pacco. Con un account Foxpost compare anche in «Bejövő csomagjaim» (pacchi in arrivo). Quando arriva al locker, il codice di apertura giunge via Viber (altrimenti SMS) ed e-mail.

Non trovi nulla? Chiedi al negozio e leggi [dove trovare il numero di tracking](guide:find-tracking-number). Se il codice inizia con `Z`, è un numero [Packeta](carrier:packeta).

## Significato degli stati di tracking Foxpost

Il tracking Foxpost mostra ogni tappa con un titolo breve, data, ora e una frase. Il sito è in ungherese, e il suo menu offre anche l'inglese. Le frasi ungheresi nell'ordine abituale:

| Stato Foxpost | Cosa vuol dire per te |
| --- | --- |
| `Csomagod létrejött a rendszerünkben, a feladó még nem adta át azt a FOXPOST részére.` | [L'etichetta esiste](guide:tracking-statuses); il mittente non ha ancora consegnato il pacco. |
| `Csomagod fel lett adva, a csomagautomatában el lett helyezve. A neked címzett (feladott/visszaküldött) csomagot hamarosan kézbesítjük.` | Il mittente lo ha inserito in un locker; non è ancora nel tuo. |
| `Csomagodat futárunk kivette a csomagautomatából.` | Un corriere lo ha prelevato dal locker di partenza. |
| `Csomagod beérkezett a FOXPOST raktárába. A feladott/visszaküldött csomag hamarosan kézbesítésre kerül.` | Arrivato al magazzino Foxpost. |
| `Csomagodat az automata telítettsége / műszaki hibája miatt átírányítottuk egy másik csomagautomatába` | Spostato in un altro locker perché il tuo è pieno o guasto; segue il nome. |
| `Csomagod megérkezett az általad választott (vissza)kézbesítési automatába, most már átveheted!` | Pronto per il ritiro: usa il codice ricevuto nel messaggio. |
| `Csomagod (vissza)kézbesítve lett, a címzett/feladó átvette.` | Ritirato: consegnato. |
| `Csomagod nem került átvételre a tárolási idő alatt, ezért a küldeményt visszaküldjük a feladónak.` | Non ritirato entro i termini; torna al mittente. |
| `Csomagot a címzett nem vette át, a csomag vissza lett küldve a feladónak.` | Non ritirato e rispedito al mittente. |

Leggi la frase, non solo il titolo: «Automatában» (nel locker) copre sia il deposito del mittente sia il pacco pronto per te. Le formule «(vissza)» e «címzett/feladó» servono anche per i resi, quindi «ritirato dal destinatario/mittente» indica che l'hai preso tu.

## Locker Foxpost, ritiro e mancate consegne

**Ritiro.** Su un locker A-BOX inserisci sullo schermo il codice del messaggio. Su uno Z-BOX (a tastierino) digita il codice di apertura a 6 cifre. Chiunque abbia il codice può aprire lo sportello. In un punto presidiato Z-Pont comunica la password; chiunque invii può ritirarlo senza documento salvo richiesta del negozio.

**Tempi di giacenza.** In un locker il pacco resta 5 giorni di calendario, inclusi fine settimana e festivi; la scadenza esatta al minuto è nel messaggio e non è prorogabile. In caso di guasto del locker si proroga in automatico. Presso uno Z-Pont le condizioni prevedono 7 giorni, o 5 dal 1° ottobre al 20 gennaio (la pagina ritiro indica 7 tutto l'anno, quindi segui la data nel messaggio), e l'assistenza può prorogare al telefono. Scaduto il termine torna al mittente; per acquisti in negozio contatta il venditore.

**Contrassegno.** Gli A-BOX accettano solo carta al terminale. Gli Z-BOX si pagano tramite il link nelle e-mail Foxpost o nell'app Foxpost, e il codice di apertura arriva dopo il pagamento. Uno Z-Pont accetta contanti o carta.

**Locker pieno o guasto.** Foxpost può spostare il pacco in un locker vicino o uno Z-Pont; puoi accettare o rifiutare nell'avviso. Se il codice non apre lo sportello, ripeti l'operazione e poi chiama l'assistenza.

**Pacchi DPD.** Da ottobre 2026 puoi scegliere al checkout un locker Foxpost per pacchi gestiti da DPD. Foxpost avvisa quando DPD lo consegna ed entra nel magazzino Foxpost. Traccialo con il numero DPD a 14 cifre su DPD o con il codice `CLFOX` su Foxpost. Resta 5 giorni di calendario e poi torna a DPD.

**Consegna a domicilio.** Un corriere partner effettua due tentativi in giorni lavorativi consecutivi, annunciati via SMS ed e-mail. Il link nell'e-mail cambia giorno o indirizzo ma consuma un tentativo. Un tentativo fallito genera e-mail e SMS, non un avviso cartaceo. Se rimane non ritirato torna al mittente.

## Pacco Foxpost in ritardo, danneggiato o smarrito

**Nessun aggiornamento.** Foxpost non garantisce tempi fissi: da locker a locker può bastare un giorno lavorativo, a domicilio di solito due o tre. Quando uno stato resta fermo per 3 giorni, invia una richiesta di ricerca («Csomag utáni érdeklődés») dai moduli Foxpost: la verifica parte solo allora e si conclude entro 30 giorni. Vedi anche [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Danneggiato.** Segnala subito eventuali danni visibili alla consegna. Altrimenti invia segnalazione scritta entro 3 giorni lavorativi con foto di imballo, interno e prodotto tramite il modulo reclami.

**Smarrito.** Per un pacco nazionale non consegnato entro 15 giorni dalla spedizione deve reclamare il mittente. Reclama il mittente a meno che il pacco sia stato consegnato a te, ti abbia ceduto il reclamo per iscritto o tu presenti la conferma d'ordine con il prezzo pagato per intero. I reclami per perdita o danno vanno inviati per iscritto entro 6 mesi dal 15° giorno dalla spedizione. Se la risposta non ti soddisfa hai 30 giorni per rivolgerti alla NMHH (autorità ungherese per le comunicazioni) o, come consumatore, a un organismo di conciliazione.

## Assistenza clienti e contatti Foxpost

Sulle pagine contatti di Foxpost:

- **Telefono:** 06-1-999-0-369, lunedì, martedì, mercoledì e venerdì 8:00–18:00, giovedì 8:00–20:00 (ora ungherese), a tariffa nazionale standard.
- **Di persona:** 1097 Budapest, Könyves Kálmán krt. 12-14, scala verde, 2° piano, solo giovedì 8:00–20:00 (ora ungherese).
- **Per iscritto:** i [moduli di contatto](https://foxpost.hu/kapcsolatfelveteli-urlapok?pageType=c2c), inclusa la ricerca pacco («Csomag utáni érdeklődés»), e il [modulo di reclamo](https://foxpost.hu/reklamacio?pageType=c2c). Foxpost non pubblica un indirizzo e-mail clienti. Per un pacco Packeta usa il supporto Packeta.

> Foxpost scrive solo da indirizzi foxpost.hu. Qualsiasi link che non termini con foxpost.hu è una truffa; Foxpost non chiede mai credenziali bancarie e non telefona per chiederti di «confermare la consegna»: riattacca.

## Tracciare pacchi Foxpost con Peek

[Peek](/) identifica subito Foxpost per un numero `CLFOX`. Per un numero di consegna a domicilio che inizia con `66639` (10–20 cifre), Peek interroga prima Foxpost insieme ad altri corrieri di pari lunghezza e mostra Foxpost appena lo riconosce. Finché Foxpost non lo conosce, Peek mostra «Probabilmente Foxpost» e continua a controllare. Puoi anche scegliere Foxpost con «Cambia» oppure incollare un link foxpost.hu con `code=` (da /csomagkovetes/ o /en/parcel-tracking), che individua Foxpost qualunque sia il codice. Un numero `Z` passa a Packeta.

Peek riporta la cronologia di Foxpost leggendo gli orari in ora di Budapest. Le righe della cronologia restano nell'ungherese di Foxpost in qualunque lingua usi Peek; lo stato del pacco appare nella tua lingua, per esempio «Pronto per il ritiro». Può mostrare il mittente (di solito il negozio) e il locker verso cui il pacco è diretto o in cui attende, e avvisarti a ogni cambio di stato. Mancano data prevista e peso, e restano esclusi numero d'ordine e dettagli di pagamento.

## Domande sul tracking Foxpost

### Foxpost e Packeta sono la stessa azienda?

In Ungheria ora sono un'unica società: Packeta Hungary si è fusa in FoxPost Kft. il 1° gennaio 2026 e il sito si presenta come «FOXPOST – Packeta Group».

### Foxpost opera in Polonia?

No. Foxpost consegna solo in Ungheria, e le FAQ precisano che inviare un pacco Foxpost all'estero verso un punto Packeta non è ancora possibile.

### Posso pagare in contanti alla consegna a un locker Foxpost?

Non in contanti. Un A-BOX accetta carta al terminale e uno Z-BOX il pagamento dal link nell'e-mail Foxpost; solo uno Z-Pont o il corriere a domicilio accettano contanti.

### Quanto impiega Foxpost a consegnare?

Foxpost indica che da locker a locker può bastare un giorno lavorativo dal ritiro e a domicilio di solito due o tre, senza garantire né l'uno né l'altro.

:::sources
- [Foxpost: Csomagkövetés](https://foxpost.hu/csomagkovetes/) – formati, numeri Z inviati a Packeta, stati, campi del portale
- [Foxpost: Track your parcel](https://foxpost.hu/en/parcel-tracking) – il tracking in inglese, dal menu
- [Foxpost: Home](https://foxpost.hu/) – locker e domicilio, formati, Packeta Group
- [Foxpost: Gyakran ismételt kérdések](https://foxpost.hu/ugyfelszolgalat) – dov'è il numero, app, fusione, nessun invio estero, tempi, contrassegno, locker pieni, pacchi DPD
- [Foxpost: Csomagátvétel](https://foxpost.hu/csomagatvetel) – codici, giacenza 5 giorni, Z-Pont, contrassegno, tentativi a domicilio
- [Foxpost: ÁSZF](https://www.foxpost.hu/uploads/documents/hu/foxpost_aszf.pdf) – tempi di giacenza, locker sostitutivi, reclami, scadenze, ricorsi, NMHH
- [Foxpost: Kapcsolat](https://foxpost.hu/kapcsolat) – telefono, orari, ufficio
- [Foxpost: Contact](https://foxpost.hu/en/contact) – telefono, orari e ufficio in inglese
- [Foxpost: Kárigény](https://foxpost.hu/reklamacio?pageType=c2c) – modulo reclami, Foxpost o Packeta
- [Foxpost: Kapcsolatfelvételi űrlapok](https://foxpost.hu/kapcsolatfelveteli-urlapok?pageType=c2c) – moduli di contatto, ricerca dopo 3 giorni senza variazioni
- [Foxpost: WebAPI integráció](https://foxpost.hu/uzleti-partnereknek/integracios-segedlet/webapi-integracio) – titoli e frasi di stato
- [Foxpost: Adathalász csalások és visszaélések formái](https://foxpost.hu/adathalaszat) – avviso anti-phishing
- [Foxpost: Minden, ami fontos az átirányításról](https://foxpost.hu/blog/maganszemely/minden-ami-fontos-az-atiranyitasrol) – avviso di reindirizzamento e scelta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti da Peek
:::
