---
title: Tracciamento Lietuvos paštas (Poste Lituane) e LP Express
description: Traccia un pacco Lietuvos paštas o LP Express: formato del numero, stati in italiano, locker Unisend, spese doganali, reclami e contatti.
slug: tracciamento-lietuvos-pastas
published: 2026-11-22
updated: 2026-11-22
---

Lietuvos paštas sono le poste nazionali della Lituania, e LP Express è il loro marchio per locker, corrieri e spedizioni self-service. Puoi tracciare un pacco su [lpexpress.lt/track](https://lpexpress.lt/track), che dispone di interfaccia in inglese, oppure incollando il numero nel riquadro in alto. Trovi il numero nell’SMS o nell’email di Lietuvos paštas, oppure sulla ricevuta se hai spedito tu.

## Numero di tracciamento Lietuvos paštas

Un numero delle poste lituane ha 13 caratteri nel [formato postale internazionale](guide:tracking-number-formats): due lettere, nove cifre e `LT`. Le regole di Lietuvos paštas indicano due prefissi LP Express, `CC` e `CH` (LP Express usa anche `HC`).

| Formato (inventato) | Cos’è |
| --- | --- |
| `RR482917367LT` | Invio postale spedito in Lituania. |
| `CC482917367LT` | Pacco LP Express diretto a un locker. |
| `CH482917367LT` | Pacco LP Express consegnato da corriere a un indirizzo in Lituania o all’estero. |

Quando un pacco arriva al locker, Lietuvos paštas invia un SMS e/o un’email con il codice, l’indirizzo del locker e un PIN.

I messaggi autentici provengono dal mittente `LPASTAS` o `LP_EXPRESS`, mai da un numero di telefono.

Altri codici paese appartengono ad altre poste: `…LV` è Latvijas Pasts, `…EE` è Omniva. Una spedizione dall’estero mantiene il codice delle poste d’origine. Venipak, un altro corriere lituano, usa un formato diverso (`V48291E7365428`). L’hai perso? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati di Lietuvos paštas

lpexpress.lt/track mostra ogni passaggio in inglese, lituano, lettone o estone; post.lt, il sito principale, è solo in lituano. Ogni passaggio ha un titolo breve e una riga descrittiva. Ecco le righe inglesi in ordine:

| Stato Lietuvos paštas | Cosa significa per te |
| --- | --- |
| `The label for the parcel has been created` | Il mittente ha creato l’etichetta. Lietuvos paštas non ha ancora il pacco. |
| `Parcel received` | Lietuvos paštas lo ha accettato dal mittente. |
| `The parcel is at the distribution center` | Presso un centro di smistamento; la riga indica la città. |
| `The parcel is transported to another distribution center` | In spostamento tra centri di smistamento. |
| `The parcel has been sent abroad` | Ha lasciato la Lituania. |
| `The parcel was received at the distribution center of the recipient's (or transit) country` | È arrivato a un centro di smistamento all’estero. |
| `The parcel has been forwarded for delivery` | In consegna: diretto al tuo indirizzo o al locker. |
| `The parcel was delivered to the UNISEND parcel locker` | Pronto per il ritiro nel locker indicato. |
| `The parcel has been delivered to the recipient` | Consegnato o ritirato dal locker. |
| `Sent to receiver` | È un titolo, non un singolo passaggio: in viaggio oppure fermo nel locker. |

Due punti generano spesso confusione. Il titolo `Sent to receiver` resta dal primo smistamento fino al ritiro, compreso tutto il tempo di attesa nel locker: solo la riga dell’evento avvisa che è pronto. Inoltre la dicitura del locker indica UNISEND anche per i locker in Lituania con marchio LP EXPRESS: Unisend è il nome usato in Lettonia ed Estonia.

Se compare `Your parcel could not be found`, controlla il codice; `Without tracking` indica un invio non tracciato. In Lituania solo le spedizioni registrate mostrano il percorso. Altri dettagli in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Locker LP Express, uffici postali e mancate consegne

**Locker.** Sono circa 1.500 i locker LP EXPRESS e Unisend in Lituania, Lettonia ed Estonia, aperti 24 ore su 24. Allo schermo seleziona «Atsiimti siuntą» (ritira pacco) e digita il PIN ricevuto via SMS o email. Hai 72 ore dall’arrivo. Trascorso quel tempo il corriere ritira il pacco e ricevi un SMS con link: entro 4 giorni puoi rinviarlo gratis una volta allo stesso locker o a un altro, altrimenti torna al mittente. Un pacco troppo grande per lo scomparto attende 15 giorni in un punto di servizio Lietuvos paštas.

**Reindirizzamento.** Per spostare un pacco ancora in viaggio verso un altro locker, inserisci il codice nella pagina di tracciamento e scegli una nuova postazione sotto «To parcel locker» (c’è anche «To home address»). Se è troppo tardi compare `Redirection is not possible` o un messaggio di termine scaduto.

**Corriere a domicilio.** Il corriere non è tenuto ad attendere più di 10 minuti; firmi sul suo dispositivo. Se non trova nessuno, ricevi avviso via SMS o email e il pacco resta gratis al centro logistico per 7 giorni di calendario dal primo giorno di uscita in consegna.

**Spedizioni in ufficio postale.** Gli invii registrati vengono portati una volta all’indirizzo; se sei assente arriva un avviso in cassetta, email o SMS da presentare con un documento. Secondo le regole TAI-16 restano 30 giorni dal giorno successivo all’avviso; le FAQ di post.lt indicano un mese solare, salvo diverso contratto del mittente.

**Da fuori UE.** Lietuvos paštas richiede il pagamento degli oneri di importazione nell’area self-service LP EXPRESS entro 7 giorni di calendario dall’avviso di pagamento, altrimenti il pacco torna al mittente. Pagato l’importo, prosegue verso ufficio o locker senza altri costi. Per la dichiarazione doganale Lietuvos paštas applica 6,99 € fino a 150 € e 30 € oltre tale soglia o per dichiarazione standard, più il 21% di IVA all’importazione su beni e spedizione. Se dichiari tu o il negozio ha riscosso l’IVA all’acquisto (IOSS), la tariffa è 2 €. Dal 1° luglio 2026 gli acquisti online extra-UE scontano un dazio UE temporaneo di 3 € per articolo su pacchi fino a 150 €; dal 1° novembre 2026 si aggiunge una commissione di gestione dell’Unione di 2 € per articolo a prescindere dal valore. Per articolo si intendono le merci del pacco con uguale classificazione tariffaria, descrizione e, se richiesta, origine. Vedi [pacchi fermi in dogana](guide:customs).

## Pacco Lietuvos paštas in ritardo, smarrito o restituito

Lietuvos paštas chiede di segnalare spedizioni registrate o assicurate smarrite o in ritardo al 1842. Il reclamo spetta al mittente entro 6 mesi dalla spedizione, quindi per un acquisto online scrivi prima al negozio. Dopo la firma per ricevuta non viene corrisposto alcun risarcimento.

Per un pacco spedito dall’estero, Lietuvos paštas rimanda al mittente. Se noti danni alla consegna del corriere, falli annotare sul dispositivo o sul documento accompagnatorio mentre il corriere è presente; diversamente Lietuvos paštas non risponde.

**Spedito dalla Lituania all’estero.** Dopo `The parcel has been sent abroad` la consegna passa a un operatore nel paese di destinazione, e nei reclami Lietuvos paštas si attiene a quanto confermato da quell’operatore. Anche Peek interroga le poste di destinazione (sotto). Il mittente ha comunque 6 mesi dalla spedizione per reclamare e Lietuvos paštas liquida i rimborsi internazionali entro 90 giorni di calendario dal reclamo.

Se il tracciamento è fermo da giorni, vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

## Contatti e assistenza clienti Lietuvos paštas

Canali ufficiali pubblicati su post.lt (le pagine contatti sono in lituano):

- **Telefono dalla Lituania:** 1842 o +370 700 554 00, alla tariffa del proprio operatore più scatto alla risposta.
- **Telefono dall’estero:** +370 5 233 3060.
- **Orari:** giorni feriali 8:00–19:00, sabato 9:00–16:00, ora lituana.
- **Email:** info@post.lt.
- **Modulo web:** «Rašykite mums» (scrivici) su post.lt, con schede per reclami e ricerche internazionali; presente anche una chat.

> Lietuvos paštas mette in guardia da SMS falsi su indirizzi errati, pacchi bloccati e resi. I messaggi autentici arrivano da `LPASTAS` o `LP_EXPRESS`, negli SMS non chiedono mai dati bancari o personali e non contengono link di pagamento diretto. Segnala i falsi a prevencija@post.lt; se hai inserito la carta, chiama subito la banca.

## Tracciare Lietuvos paštas con Peek

[Peek](/) riconosce subito un codice che termina in `LT` con cifra di controllo valida se inizia con `C`, `E` o `HC` e indica Lietuvos paštas come «Corriere rilevato». Tuttavia molti codici `CC`, `CH` e `HC` non rispettano la cifra di controllo S10: in tal caso Peek mostra «La cifra di controllo di questo numero non torna.» e «Traccia così com’è» interroga solo Lietuvos paštas. Per qualsiasi altro codice postale in `LT` con cifra valida, Peek interroga insieme Lietuvos paštas e [Chronopost](carrier:chronopost), mostrando «Lietuvos paštas ha questo pacco.» quando lo riconosce. Funzionano anche i link di tracciamento di lpexpress.lt e post.lt.

Un pacco dall’estero con formato postale internazionale viene seguito prima sulle poste mittenti. Quando quelle poste indicano la Lituania come destinazione — o nessun paese e tu controlli dalla Lituania — Peek interroga anche Lietuvos paštas e passa a seguirlo lì non appena registra nuovi passaggi, conservando i precedenti. Altrimenti scegli «Lietuvos paštas» manualmente. Un codice [DHL Paket](carrier:dhl) che inizia con `C` e termina in `DE` non viene mai passato a Lietuvos paštas.

Lo stesso vale per un pacco spedito dalla Lituania con cifra di controllo valida: Peek interroga anche le poste di destinazione quando Lietuvos paštas mostra un centro di smistamento nel paese destinatario o di transito (oppure prima le poste del paese da cui cerchi, se diverso dalla Lituania). Per alcuni paesi di destinazione, come Russia, Kazakistan o Cina, Peek non interroga le poste locali.

Peek legge il servizio di lpexpress.lt/track in inglese. Mentre tieni il pacco aperto sullo schermo — oppure il giorno della consegna con notifiche attive — Peek controlla fino a ogni 10 minuti, e dalle 6:00 ora lituana fino a ogni 2 minuti il giorno in cui Lietuvos paštas lo mette in consegna in Lituania, finché il suo tracker risponde. Altrimenti, e dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando subentra un altro corriere, Peek segue il ritmo di quel corriere. Per le notifiche scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in transito, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Lietuvos paštas.», con il link «Chiedi aiuto a Lietuvos paštas» alla pagina contatti solo in quell’avviso.

Ogni passaggio appare sulla mappa con città, centro di smistamento o paese, fasi doganali, orario di consegna e nome e località del locker o dell’ufficio postale (senza via né CAP). Gli orari lituani sono letti sull’ora di Vilnius e mostrati nel tuo fuso orario (la cronologia termina con «Orari nel tuo fuso orario:» seguito dal nome); i passaggi all’estero sono letti sull’ora del rispettivo paese (su uno dei fusi nei paesi con più fusi orari) e convertiti nel tuo fuso. Non ci sono data stimata né peso. Gli stati conservano il testo inglese di Lietuvos paštas; un reso mostra «Problema segnalato» e, una volta rientrato, «Restituito al mittente».

## Domande sul tracciamento Lietuvos paštas

### Cos’è LP Express?

È il nome con cui Lietuvos paštas gestisce in Lituania locker, corrieri e spedizioni self-service. In Lettonia ed Estonia lo stesso servizio si chiama Unisend.

### Esiste un tracciamento Lietuvos paštas in inglese?

Sì, su lpexpress.lt/track cambiando lingua in inglese. Il sito post.lt è disponibile solo in lituano.

### Posso tracciare un pacco internazionale con le Poste Lituane?

Sì, se spedito con tracciamento: dalla Lituania corrisponde a un servizio «Su sekimu» (con tracciamento). Per pacchi spediti dall’estero chiedi al mittente.

### Quanto tempo impiega un pacco LP Express?

Lietuvos paštas indica che un pacco verso locker in Lituania arriva solitamente il giorno lavorativo successivo o di sabato, se consegnato prima del ritiro. Per i pacchi postali nazionali, post.lt indica un obiettivo di qualità del 97% consegnato entro 4 giorni lavorativi; per le lettere prioritarie verso altri paesi UE, l’85% entro 3 giorni lavorativi e il 97% entro 5.

:::sources
- [Lietuvos paštas: Siuntų sekimas](https://www.post.lt/lt/siuntu-sekimas) – formato codice, ricevuta, visibilità percorso, 1842, reclami, spedizioni all’estero, 6 mesi, 90 giorni, obiettivi di qualità
- [LP Express: Parcel tracking](https://lpexpress.lt/track) – pagina tracciamento inglese, lingue, titoli, messaggi e reindirizzamento
- [Lietuvos paštas: Dažniausiai užduodami klausimai](https://www.post.lt/duk) – FAQ locker, ritiro in ufficio, giacenza
- [Lietuvos paštas: Siuntos](https://www.post.lt/lt/duk/siuntos) – tempi di giacenza, regole di ritiro
- [Lietuvos paštas: LP EXPRESS paštomatai](https://www.post.lt/lt/duk/lp-express-pastomatai) – 72 ore, reindirizzamento 4 giorni, 15 giorni se troppo grande, pagamento anticipato importazione
- [Lietuvos paštas: Siuntų siuntimas paštomatu](https://www.post.lt/lt/lp-express-pastomatai) – marchi LP EXPRESS e Unisend, circa 1.500 locker, avviso PIN, giorno lavorativo successivo
- [Lietuvos paštas: Pašto paslaugų teikimo nesutartiniams naudotojams taisyklės TAI-16](https://www.post.lt/sites/default/files/Dokumentai/PA%C5%A0TO%20PASLAUG%C5%B2%20TEIKIMO%20TAISYKL%C4%96S.pdf) – `CC` e `CH`, regole corriere, custodia 7 giorni, 30 giorni in ufficio, documento, danni, pagamento in 7 giorni
- [Lietuvos paštas: Siuntų deklaravimas](https://www.post.lt/lt/siuntu-deklaravimas) – spese di dichiarazione, IVA, 7 giorni per pagare
- [Lietuvos paštas: Kontaktai](https://www.post.lt/lt/kontaktai) – telefoni, orari, costi, email, chat
- [Lietuvos paštas: Rašykite mums](https://www.post.lt/rasykite-mums) – modulo e schede reclami e ricerche
- [Lietuvos paštas: Būkite atsargūs, Lietuvos paštu apsimetantys sukčiai](https://www.post.lt/lt/apie-mus/naujienos/bukite-atsargus-lietuvos-pastu-apsimetantys-sukciai-gviesiasi-pinigu-ir-duomenu) – avviso SMS truffa, mittenti, prevencija@post.lt
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – commissione di gestione di 2 € dal 1° novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 3 € e 2 € per articolo, definizione di articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – cosa legge e mostra Peek
:::
