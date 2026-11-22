---
title: Tracking Delivengo: segui un pacco La Poste dalla Francia
description: Segui una spedizione Delivengo partita dalla Francia: codice in FR, stati di La Poste, consegna con Poste Italiane o Posta Svizzera e contatti.
slug: delivengo-tracking
published: 2026-11-21
updated: 2026-11-21
---

Delivengo è il servizio export di [La Poste](carrier:la-poste) francese dedicato ai piccoli pacchi internazionali. Se un negozio francese ha spedito il tuo ordine con Delivengo, il pacco lascia la Francia con La Poste e la posta del tuo Paese cura il recapito finale — in Italia [Poste Italiane](carrier:poste-italiane), nel Canton Ticino e nel resto della Confederazione la [Posta Svizzera](carrier:swiss-post). Puoi seguirlo sul tracking di La Poste su `laposte.fr` con il codice fornito dal venditore oppure incollandolo nel campo qui sopra.

## Il tuo codice di tracking Delivengo: formato ed esempi

I codici Delivengo hanno il formato postale internazionale di 13 caratteri: due lettere, nove cifre (l'ultima è una cifra di controllo) e `FR`.

**Dove trovarlo.** Te lo invia il negozio, di solito nell'e-mail di spedizione. Il portale `mydelivengo.laposte.fr` (MyDelivengo) è l'area riservata alle aziende mittenti, non una pagina pubblica in cui cercare il pacco. Se non trovi il riferimento, leggi [dove trovare il codice di tracking](guide:find-tracking-number).

**Codici simili** (tutti con cifre inventate):

| Esempio (inventato) | A quale operatore appartiene |
| --- | --- |
| `LD482910576FR` | [La Poste](carrier:la-poste): Delivengo, Colissimo e altri invii postali francesi condividono questa struttura. |
| `RR482910576IT` | [Poste Italiane](carrier:poste-italiane) (finale `IT`), non Delivengo. |
| `LL482910576CH` | [Posta Svizzera](carrier:swiss-post) (finale `CH`), non Delivengo. |

Le ultime due lettere indicano la posta emittente. In un codice che termina in `…FR` nulla distingue dall'esterno Delivengo da un altro invio La Poste, ma il sistema di tracciamento è lo stesso. Poiché è presente una cifra di controllo, basta un numero digitato male per renderlo non valido e Peek non lo riconosce più: copialo invece di riscriverlo.

## Stati Delivengo: cosa dicono le scansioni di La Poste

Il tracking di La Poste risponde ai codici Delivengo come a qualsiasi altra spedizione postale francese. Peek associa ogni scansione a una fase chiara:

| Fase in Peek | Cosa significa la scansione di La Poste |
| --- | --- |
| «Etichetta creata» | Spedizione annunciata: il negozio ha preparato l'etichetta, ma La Poste non ha ancora preso il pacco. |
| «Preso in carico» | La Poste ha accettato e preso in carico il pacco. |
| «In transito» | Il pacco viaggia in Francia, è pronto a lasciare il Paese o è arrivato nel Paese di destinazione. |
| «In dogana» | In controllo doganale (per destinazioni o territori extra doganali); una volta sdoganato torna «In transito». |
| «In consegna» | Presso il centro di recapito, in preparazione per l'uscita di consegna. |
| «Tentativo di consegna» / «Pronto per il ritiro» | Consegna non riuscita a domicilio oppure pacco in attesa presso un ufficio postale o punto di ritiro. |
| «Consegnato» | Consegnato. Se non lo trovi, vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received). |
| «Problema segnalato» | Anomalia (danneggiamento, rifiuto, indirizzo errato o giacenza scaduta) oppure reso in viaggio verso il mittente. |
| «Restituito al mittente» | Il pacco è tornato al negozio. |

Dopo l'uscita dalla Francia, la posta del Paese di arrivo gestisce l'ultimo miglio e la cronologia su `laposte.fr` può restare ferma alla partenza o all'arrivo alla frontiera.

## Consegna, assenza e dogana in Italia e in Svizzera

**Chi consegna e cosa succede se non ci sei.** L'ultimo tratto spetta a [Poste Italiane](carrier:poste-italiane) in Italia e alla [Posta Svizzera](carrier:swiss-post) in Svizzera, non a La Poste: avvisi di giacenza, riconsegne e tempi di ritiro allo sportello seguono le regole della tua posta nazionale. Vedi [consegna mancata](guide:missed-delivery).

**Dogana.** Tra la Francia metropolitana e l'Italia non si pagano dazi grazie all'unione doganale UE. Fanno eccezione i territori fuori dal territorio doganale o IVA dell'UE: Livigno (fuori da dogana e IVA UE), Campione d'Italia e le acque italiane del Lago di Lugano (fuori dall'area IVA UE), nonché i dipartimenti e le collettività francesi d'oltremare (Guadalupa, Martinica, Guyana francese, Riunione, Mayotte e Saint-Martin sono fuori dall'area IVA UE). Verso la **Svizzera**, fuori dall'UE, l'UDSC (BAZG) applica l'IVA all'importazione dell'8,1 % (2,6 % ridotta per libri, generi alimentari o medicinali), ma non riscuote importi di dazio o IVA fino a 5 CHF; dettagli in [pacco fermo in dogana](guide:customs).

## Pacco Delivengo in ritardo, bloccato o smarrito

Rivolgiti per prima cosa al negozio online, titolare del contratto Delivengo.

**Nessun aggiornamento da giorni.** Dopo l'esportazione dalla Francia può capitare che `laposte.fr` non mostri tutti i passaggi locali: controlla lo stesso codice `…FR` su [Poste Italiane](carrier:poste-italiane) o sulla [Posta Svizzera](carrier:swiss-post) e consulta [tracking che non si aggiorna](guide:tracking-not-updating).

**Smarrito, danneggiato o restituito.** Chiedi al venditore una nuova spedizione o il rimborso. Nell'UE, ricorda Your Europe, il venditore è responsabile di eventuali danni alla merce dalla spedizione fino alla consegna nelle tue mani; se non consegna entro 30 giorni (o entro la data pattuita), concedigli un termine supplementare ragionevole dopodiché puoi risolvere il contratto ed essere rimborsato. Leggi anche [pacco smarrito](guide:lost-parcel) e [pacco restituito al mittente](guide:returned-to-sender).

## Contatti Delivengo

- **Prima il negozio**: ha spedito il pacco dal proprio account MyDelivengo.
- **La Poste, per un reclamo**: secondo l'Arcep (il regolatore postale francese), un reclamo a La Poste può essere presentato al numero `36 31` (lunedì–venerdì 8:30–19:00, sabato 8:30–13:00) oppure al `+33 810 821 821` (lunedì–venerdì 8:00–19:00 ora francese), sul sito web, per posta o in un punto postale; l'Arcep precisa che i diritti di mittente e destinatario non coincidono del tutto e non indica se questi canali trattino specificamente Delivengo.
- **La tua posta locale** quando il pacco è arrivato nel tuo Paese, con lo stesso numero `…FR`.

## Seguire un pacco Delivengo con Peek

[Peek](/) non ha un canale separato per Delivengo: segue questi invii tramite il tracking di [La Poste](carrier:la-poste), esattamente come un Colissimo. Incollando un codice `…FR` valido, Peek propone subito «La Poste / Colissimo»; puoi selezionare «Delivengo» con «Cambia» o «Cambia corriere» e il risultato è identico. Funzionano anche i link di `laposte.fr` (compresi quelli provenienti da `mydelivengo.laposte.fr`).

Peek mostra stato, cronologia con il Paese di ogni scansione e data di consegna prevista quando La Poste la fornisce, tralasciando nome e indirizzo del destinatario. Le descrizioni restano nel francese di La Poste e gli orari sono convertiti nel tuo fuso («Orari nel tuo fuso orario: …»). Quando La Poste indica il Paese di destinazione o il partner estero, Peek interroga anche la posta supportata sul posto — tra cui [Poste Italiane](carrier:poste-italiane), [Posta Svizzera](carrier:swiss-post), [USPS](carrier:usps) o Canada Post — passando a seguirla appena mostra progressi più recenti e conservando la cronologia francese.

Mentre hai il pacco aperto sullo schermo, o nel giorno della consegna con notifiche attive, Peek controlla il tracking diretto fino a ogni 10 minuti, e fino a ogni 2 minuti quando il pacco entra «In consegna» (quel giorno dalle 6:00 ora locale finché risponde direttamente La Poste); negli altri momenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Tramite i servizi di tracciamento controlla fino a ogni 15 minuti in quel giorno «In consegna» alle stesse condizioni, altrimenti ogni ora, o ogni 3 ore dopo un giorno senza aggiornamenti. Una volta passato alla posta di destinazione, Peek adotta la cadenza di quell'operatore. Puoi ricevere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti sul tracking Delivengo

### Delivengo e Colissimo sono la stessa cosa?

No, anche se fanno entrambi parte di [La Poste](carrier:la-poste) e usano lo stesso tracking: Delivengo è il servizio export per piccoli invii commerciali verso l'estero.

### Perché il tracking Delivengo si ferma dopo l'uscita dalla Francia?

Perché fuori dalla Francia la consegna passa alla posta locale e la cronologia di La Poste può restare silenziosa. Cerca lo stesso codice `…FR` su [Poste Italiane](carrier:poste-italiane) o sulla [Posta Svizzera](carrier:swiss-post) — Peek lo fa già per te quando La Poste comunica il Paese di arrivo.

### A chi chiedo il rimborso se un pacco Delivengo non arriva?

Al negozio da cui hai acquistato, titolare dell'account MyDelivengo e, nell'UE, responsabile della consegna fino al ricevimento della merce.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – servizio export di La Poste, ultimo miglio alla posta locale, area MyDelivengo e tracking condiviso
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – formato S10 con cifra di controllo, codici evento e passaggio alla posta estera
- [Unione europea: L'unione doganale dell'UE in azione](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_it) – assenza di dazi doganali negli scambi tra Paesi UE
- [Commissione europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – regime di Livigno, Campione d'Italia e dipartimenti francesi d'oltremare
- [UDSC (BAZG): Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e franchigia di 5 CHF
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – responsabilità del venditore fino alla consegna e termine di 30 giorni
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – reclami a La Poste (36 31, +33 810 821 821, orari e diritti)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dati letti e mostrati da Peek
:::
