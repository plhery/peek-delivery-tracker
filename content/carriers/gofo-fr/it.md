---
title: Tracciamento GOFO France: codici GFFR, stati e assistenza
description: Segui il tuo pacco GOFO in Francia: formato dei codici GFFR e CIFR, significato degli stati in francese e a chi rivolgerti in caso di ritardo o smarrimento.
slug: gofo-france-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

GOFO France è la rete francese di GOFO, un corriere attivo con reti proprie in diversi Paesi, e consegna pacchi all’interno della Francia. Nell’elenco dei corrieri di Peek la trovi anche come CIRRO Parcel France. I suoi codici di tracciamento iniziano con `GFFR` o `CIFR`: puoi seguire la spedizione sulla pagina francese di GOFO su gofo.com oppure incollare il codice nel riquadro qui sopra. Non hai ancora il codice? Leggi [come trovare un pacco senza numero di tracciamento](guide:find-tracking-number).

## Codice di tracciamento GOFO France: com’è fatto

Ecco i formati che Peek assegna direttamente a GOFO France, in maiuscolo o in minuscolo:

| Formato | Esempio inventato |
| --- | --- |
| `GFFR` e 13 cifre | `GFFR4829105736492` |
| `GFFR` e 14 cifre | `GFFR48291057364928` |
| `CIFR` e 13 cifre | `CIFR4829105736492` |
| `CIFR` e 14 cifre | `CIFR48291057364928` |

Anche un riferimento del mittente può fungere da codice di tracciamento: `PK` seguito da 20 cifre, a volte scritto con i trattini come `PK-4829105736492817364-5` (inventato). Il tracciamento di GOFO France individua il pacco anche tramite questo riferimento, che GOFO registra accanto al proprio numero di lettera di vettura `GFFR` o `CIFR`.

Le altre reti nazionali di GOFO seguono lo stesso schema, con la sigla del Paese dopo `GF` o `CI`: i codici `GFIT` e `CIIT` appartengono a [GOFO Italy](carrier:gofo-it), `GFNL` e `CINL` a GOFO Netherlands, mentre `GFES` e `CIES` sono di GOFO Spain. Un codice `GFUS` con 14 cifre, `GF` con 13 cifre o `CR` con 12 cifre appartiene a [GOFO Express](carrier:gofo), una rete distinta negli Stati Uniti, mentre `GFCA` con 14 cifre è di GOFO Canada. Nessuno di questi è un codice di GOFO France. Per distinguere i vari formati, consulta [a quale corriere appartiene questo codice di tracciamento?](guide:tracking-number-formats).

## Stati di tracciamento GOFO: cosa significano le diciture in francese

Peek legge il tracciamento di GOFO in francese, quindi le scansioni compaiono nel testo originale francese scritto da GOFO, qualunque sia la tua lingua. Le righe più frequenti, nell’ordine in cui solitamente si presentano:

| GOFO scrive | Cosa significa per te |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | Il negozio sta preparando il pacco; GOFO non lo ha ancora ricevuto. |
| `Expédition en transit vers GOFO` | La spedizione è in viaggio verso GOFO, che non l’ha ancora presa in carico. |
| `Arrivé au site de prise en charge` | È arrivato al centro di accettazione di GOFO: è la prima scansione di GOFO. |
| `Votre colis a été pris en charge au centre de tri` | È stato scansionato all’ingresso in un centro di smistamento di GOFO. |
| `Colis en cours d'acheminement` | È in transito da una sede GOFO all’altra. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | Si trova nell’agenzia regionale GOFO della tua zona, incaricata della consegna. |
| `Le chauffeur est en route pour la livraison` | È sul furgone con l’autista per la consegna. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Consegna non riuscita: non c’era nessuno in casa. |
| `Livré` | Consegnato. |

Le prime due righe traggono spesso in inganno: finché non compaiono `Arrivé au site de prise en charge` o la scansione del centro di smistamento, GOFO non ha ancora il pacco in mano e Peek mostra «Etichetta creata». La consegna appare nella cronologia semplicemente come `Livré`, in qualsiasi lingua, con lo stato «Consegnato»: eventuali dettagli aggiuntivi inseriti da GOFO, inclusi i dati del destinatario, vengono lasciati fuori da Peek.

Il sistema di GOFO raggruppa inoltre le spedizioni in macrocategorie. `Alerte` indica un imprevisto rilevato da GOFO e su Peek appare come «Problema segnalato»; da sola non significa che ci sia stato un tentativo di consegna. `Retourné` indica un pacco in rientro al mittente, mostrato su Peek come «Restituito al mittente».

## Pacco GOFO in ritardo, non consegnato, perso o danneggiato

**Il tracciamento non avanza.** Peek continua a interrogare GOFO. Se passano quattro giorni senza nuove scansioni prima che il pacco esca in consegna, la scheda del pacco su Peek lo segnala («Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a GOFO France.») e rimanda alla pagina di assistenza con «Chiedi aiuto a GOFO France». Leggi anche [il tracciamento non si aggiorna](guide:tracking-not-updating).

**Tentativo di consegna fallito.** Dopo `Échec de la livraison, motif : Le destinataire n'est pas chez lui`, Peek mostra «Tentativo di consegna»; se dopo `motif :` GOFO indica una causale diversa, lo stato diventa «Problema segnalato». I passaggi successivi dipendono da GOFO; vedi cosa controllare in [consegna mancata](guide:missed-delivery).

**In ritardo.** Secondo le norme UE a tutela dei consumatori, il venditore deve consegnare l’ordine entro 30 giorni, salvo diverso accordo. Se non rispetta il termine, contattalo concedendo un ulteriore termine ragionevole; se non consegna nemmeno entro quella scadenza, hai diritto a risolvere il contratto e ottenere il rimborso.

**Segnato `Livré`, ma non l’hai ricevuto.** È il venditore a rispondere della consegna: spetta a lui contattare il corriere e dimostrare che il pacco ti è stato recapitato; se non è in grado di farlo, deve inviarti un articolo identico o rimborsarti. Per i controlli immediati da fare vicino a casa, leggi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Danneggiato.** Il venditore è responsabile di qualsiasi danno dal momento della spedizione fino a quando ricevi la merce, compresi i danni avvenuti durante il trasporto. Quando possibile, verifica le condizioni del pacco in presenza dello spedizioniere. Altri dettagli in [pacco danneggiato](guide:damaged-parcel).

**Dazi sulle importazioni nell’UE.** Secondo la Commissione europea, per gli acquisti online da venditori extra-UE dal 1° luglio 2026 sugli ordini fino a 150 € si applica un dazio doganale di 3 € per articolo, a cui dal 1° novembre 2026 si aggiunge una commissione di gestione dell’Unione di 2 € per articolo (dovuta anche sulle vendite a distanza oltre 150 €). Un articolo raggruppa i prodotti con lo stesso codice merceologico e lo stesso Paese di origine: per due magliette si pagano 3 € di dazio e 2 € di commissione; per una maglietta e un paio di pantaloni, il doppio. Ti viene chiesto un pagamento per lo sdoganamento? Consulta [pacco fermo in dogana](guide:customs).

## Assistenza clienti GOFO France

La pagina di supporto di GOFO France si trova sul suo sito francese gofo.com, a cui Peek rimanda tramite «Chiedi aiuto a GOFO France» quando trascorrono quattro giorni senza nuove scansioni prima della messa in consegna. Se il tuo ordine è in ritardo, smarrito o danneggiato, rivolgiti prima al negozio da cui hai acquistato, che è responsabile della consegna nei tuoi confronti. Tieni a portata di mano il codice `GFFR` o `CIFR` e il numero d’ordine.

## Seguire un pacco GOFO France con Peek

[Peek](/) riconosce automaticamente i codici `GFFR` e `CIFR`. Puoi incollare anche un link della pagina di tracciamento francese di GOFO (su gofo.com o gofoexpress.fr, con `id=` seguito dal codice). Funziona anche un riferimento `PK`: Peek lo chiede a GOFO France e le assegna il pacco non appena GOFO riconosce il riferimento; nell’attesa vedi «In attesa della prima scansione di GOFO France».

Peek controlla GOFO France fino a ogni 10 minuti mentre qualcuno attende il pacco e ogni ora negli altri casi, così come dopo due giorni senza scansioni o dopo 12 ore su «Etichetta creata» o «Preso in carico». Il giorno di consegna controlla fino a ogni 2 minuti dalle 6:00 ora locale quando il pacco è in consegna e risponde direttamente il tracciamento di GOFO; quando rispondono solo i servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti il giorno di consegna. Mostra lo stato, la cronologia con il luogo indicato da GOFO per ogni scansione, l’ora di consegna e questi luoghi su una mappa, con notifiche su «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Se GOFO riporta solo il codice di un deposito o il Paese, la scansione compare senza luogo. Peek converte gli orari locali di GOFO nel tuo fuso orario (sotto la cronologia appare «Orari nel tuo fuso orario: …»). Finché GOFO non elenca alcuna scansione, su peektracker.com compare «In attesa di GOFO France» e Peek continua a controllare. Se dopo due giorni GOFO continua a non riconoscere il codice e nessun’altra fonte risponde, Peek mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.» e passa a un controllo al giorno, poi uno a settimana trascorsa una settimana dall’aggiunta. Se GOFO ha già registrato la spedizione ma non l’ha ancora scansionata, mantiene «In attesa di GOFO France» senza chiederti di verificare il codice. Peek mostra la data di consegna prevista da GOFO finché il pacco è in viaggio, prima che vada in consegna, venga consegnato o restituito, arrivi a un punto di ritiro o registri un tentativo fallito o un problema; non mostra il peso e tralascia le foto di consegna e gli avvisi di GOFO. Non può contattare GOFO né il negozio al posto tuo.

## Domande frequenti sul tracciamento GOFO France

### Cosa significa «Expédition en transit vers GOFO»?

Significa che il pacco è in viaggio verso GOFO, che non lo ha ancora ricevuto. Subito dopo arriva la prima scansione effettuata da GOFO stessa: `Arrivé au site de prise en charge` (arrivo al centro di accettazione) oppure `Votre colis a été pris en charge au centre de tri` (presa in carico al centro di smistamento). Fino a quel momento Peek mostra «Etichetta creata».

### GOFO France è un’azienda reale?

Sì. GOFO FRANCE è una società francese (*SAS, société par actions simplifiée*) iscritta nel repertorio Sirene dell’INSEE con il numero Siren 978 559 839. È attiva dal 26 maggio 2023 e ha sede legale a Marly-la-Ville (Val-d’Oise).

### GOFO e CIRRO Parcel sono la stessa cosa?

In Francia, ai fini del tracciamento, sì: nell’elenco dei corrieri di Peek, CIRRO Parcel France è un altro nome di GOFO France. Se invece un tracciamento riporta soltanto «Cirro», può riferirsi alla rete francese, italiana o olandese di GOFO oppure alla linea internazionale della stessa Cirro, che affida i pacchi alle poste nazionali con codici postali locali.

### Cosa faccio se il mio pacco GOFO è andato perso?

Contatta il venditore. In base alle norme dell’UE, il negozio risponde della consegna finché il pacco non arriva nelle tue mani: se non può dimostrare l’avvenuto recapito, deve inviarti un articolo identico o rimborsarti. Approfondisci in [pacco smarrito](guide:lost-parcel).

:::sources
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, SAS, attiva dal 26 maggio 2023, sede a 95670 Marly-la-Ville
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – consegna entro 30 giorni, termine supplementare, responsabilità fino al ricevimento, dazio di 3 € per articolo fino a 150 € e definizione di articolo
- [Your Europe: FAQ – Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_it.htm) – prova di consegna a carico del venditore, danni durante il trasporto e controllo in presenza dello spedizioniere
- [Commissione europea: EU Customs Reform (EN)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 sugli invii fino a 150 € e commissione di gestione dal 1° novembre 2026
- [Commissione europea: Guidance on the 3 EUR customs duty and Union handling fee (PDF, EN)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – commissione fissa di 2 € per articolo nelle vendite a distanza, anche sopra 150 €
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati dei codici, riferimenti PK, link riconosciuti, stati e categorie in francese, CIRRO Parcel France, altre reti GOFO e cosa mostra Peek
:::
