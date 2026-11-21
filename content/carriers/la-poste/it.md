---
title: Tracciamento Colissimo e La Poste: stati, consegna e FAQ
description: Traccia un pacco Colissimo o La Poste dalla Francia: formati del codice, significato degli stati francesi, consegna in Italia e Svizzera, reclami e contatti.
slug: tracciamento-colissimo-la-poste
published: 2026-11-18
updated: 2026-11-20
---

Colissimo è il servizio pacchi di La Poste, l'operatore postale nazionale francese. Se in Italia o nella Svizzera italiana aspetti un ordine o una raccomandata spedita dalla Francia, incolla il numero di tracciamento – oppure un link di `laposte.fr` – nel riquadro qui sopra. Il tracciamento diretto di La Poste si consulta su `laposte.fr`, e il codice ti viene fornito dal mittente o nell'e-mail di spedizione del negozio.

## Numero di tracciamento Colissimo e La Poste: formati

Con esempi interamente inventati, ecco i codici principali emessi da La Poste:

| Formato (inventato) | Che cos'è |
| --- | --- |
| `6A48291057365` | 1 cifra, 1 lettera e 11 cifre: un pacco Colissimo oppure una lettera tracciata o raccomandata. L'ultima cifra è una cifra di controllo. |
| `LA482910576FR` | 2 lettere, 9 cifre e `FR`: un codice postale internazionale emesso in Francia per pacchi Colissimo e posta tracciata o raccomandata. La cifra prima di `FR` è di controllo. |
| `87048291057364R` | 15 caratteri che iniziano con `863`–`866`, `870`, `875`, `880` o `885`: un codice Smart Data usato da La Poste per lettere tracciate e raccomandate. L'ultimo carattere è di controllo, ma il codice viene riconosciuto anche nelle 14 cifre senza quel carattere finale. |

I numeri postali internazionali che iniziano con `XU`, `XR`, `XT`, `XW`, `XY` o `PZ` e finiscono con `FR` appartengono invece a [Chronopost](carrier:chronopost), il corriere espresso del gruppo. I codici di 12 caratteri puntano spesso a Colis Privé, mentre una serie di 14 cifre può appartenere anche a [DPD](carrier:dpd-de), [BRT](carrier:brt) o altri corrieri: controlla il nome indicato nell'e-mail del venditore o leggi la guida ai [formati dei numeri di tracciamento](guide:tracking-number-formats). Non trovi il codice? Vedi [dove trovare il numero di tracciamento](guide:find-tracking-number).

## Cosa significano gli stati in francese di Colissimo

Peek mostra le scansioni originali di La Poste in francese, parola per parola, e assegna alla spedizione una fase in italiano in base all'ultimo evento. Qui trovi le diciture più frequenti nell'ordine abituale del percorso (le voci di recapito riguardano la rete francese):

| La Poste scrive | Peek mostra | Cosa significa per te |
| --- | --- | --- |
| `Votre Colissimo va bientôt nous être confié ! Il est en cours de préparation chez votre expéditeur.` | Etichetta creata | Il mittente sta preparando la spedizione; La Poste non ha ancora il pacco. |
| `Votre colis a été déposé dans un point postal.` | Preso in carico | Il mittente ha consegnato il pacco in un ufficio o punto postale. |
| `Votre colis est en transit sur nos plateformes logistiques.` | In transito | In viaggio tra i centri logistici di smistamento. |
| `Les formalités import/export sont en cours sur votre colis.` | In dogana | Sono in corso le pratiche doganali di importazione o esportazione. |
| `Votre Colissimo est en cours de traitement sur le site de tri local.` | In transito | In lavorazione presso il centro di smistamento locale. |
| `Votre colis est sur son site de distribution. Nous le préparons pour le mettre en livraison.` | In consegna | È arrivato al centro di distribuzione e viene preparato per il giro di consegna della giornata. |
| `Votre colis n'a pas pu vous être remis. Il sera mis en livraison demain (hors dimanche et jours fériés).` | Tentativo di consegna | Consegna non riuscita; nuovo tentativo il giorno lavorativo successivo (esclusi domeniche e festivi). |
| `Nous sommes passés mais nous n'avons pu vous remettre votre colis. Il va être acheminé vers votre point de retrait.` | Tentativo di consegna | Assente al passaggio; il pacco viene indirizzato a un punto di ritiro. |
| `Votre colis est disponible dans votre point de retrait pendant un délai de 15 jours calendaires.` | Pronto per il ritiro | Ti aspetta al punto di ritiro entro la scadenza indicata nel messaggio (per esempio 15 giorni di calendario o `Le délai de retrait est de 5 jours.`). |
| `Votre colis est livré dans votre boite aux lettres.` | Consegnato | Consegnato nella cassetta delle lettere. |
| `Votre colis vous a attendu dans votre point de retrait jusqu'à la date limite. Nous sommes contraints de le renvoyer à l'expéditeur.` | Problema segnalato | Non ritirato entro il termine; rientra al mittente. |
| `Votre colis est livré à son expéditeur à la suite d'un retour.` | Restituito al mittente | Il reso è tornato al mittente. |

La riga che trae più spesso in inganno è `Votre colis est sur son site de distribution…`: sembra indicare un pacco fermo in deposito, ma segna lo smistamento mattutino per l'uscita del corriere in giornata. Approfondisci in [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

## Consegna in Italia e nella Svizzera italiana: chi porta il pacco e dogana

Quando la spedizione lascia la Francia, il tracciamento di La Poste può indicare il partner estero incaricato della consegna e il suo codice locale: Peek li mostra sulla scheda del pacco. Inoltre, per i codici postali internazionali che terminano in `FR` come `LA482910576FR`, Peek collega la spedizione all'operatore postale del Paese di destinazione – [Poste Italiane](carrier:poste-italiane) per l'Italia e [La Posta Svizzera](carrier:swiss-post) per la Svizzera – passando ai suoi aggiornamenti non appena compaiono nuove scansioni. Se ricevi un avviso o un'e-mail da [Poste Italiane](carrier:poste-italiane), [BRT](carrier:brt), [DPD](carrier:dpd-de) o [La Posta Svizzera](carrier:swiss-post) con un numero locale, segui l'ultimo tratto e i termini di giacenza sulle regole di quel corriere.

**Dogana.** Tra la Francia europea e l'Italia non ci sono dazi né formalità doganali. Fanno eccezione Livigno, fuori dal territorio doganale e IVA dell'UE, Campione d'Italia, fuori dal territorio IVA, e i pacchi dai dipartimenti francesi d'oltremare (Guadalupa, Guyana francese, Martinica, Riunione, Mayotte), anch'essi fuori dal territorio IVA: su questi può essere dovuta l'IVA all'importazione. Per gli acquisti online spediti invece da Paesi extra-UE nell'UE si applicano dal 1° luglio 2026 il dazio doganale forfettario di 3 € per categoria di articolo fino a 150 € e, dal 1° novembre 2026, la commissione di gestione dell'Unione di 2 € per categoria di articolo di qualsiasi valore. Nella **Svizzera italiana** (Canton Ticino e Grigioni italiano), fuori dall'UE, i pacchi provenienti dalla Francia passano sempre dallo sdoganamento svizzero: secondo l'Ufficio federale della dogana e della sicurezza dei confini (UDSC/BAZG) e [La Posta Svizzera](carrier:swiss-post), IVA e tributi non vengono riscossi se l'importo d'imposta non supera 5 CHF, e i regali tra privati sono esenti fino a 100 CHF (esclusi alcol e tabacco). Dettagli in [pacco fermo in dogana](guide:customs).

## Pacco bloccato, smarrito o danneggiato

**Nessun aggiornamento.** Verifica se il pacco è passato al corriere locale; se tutto tace da diversi giorni, avvisa il mittente e consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Smarrito o danneggiato.** Se hai comprato da un negozio online nell'UE, rivolgiti prima al venditore: secondo *Your Europe*, il commerciante è responsabile di eventuali danni fino alla consegna e deve recapitare l'ordine entro 30 giorni salvo patto diverso; dopo un termine supplementare ragionevole hai diritto alla risoluzione e al rimborso. Vedi anche [pacco smarrito](guide:lost-parcel), [pacco danneggiato](guide:damaged-parcel), [consegnato ma non ricevuto](guide:delivered-not-received) e [rispedito al mittente](guide:returned-to-sender).

**Reclami a La Poste (e in Italia).** Secondo l'autorità postale francese Arcep, il reclamo a La Poste è gratuito e può essere presentato sia dal mittente sia dal destinatario (pur con diritti diversi). Se la risposta non ti soddisfa, puoi chiedere un riesame al *service recours* di La Poste attraverso gli stessi canali; in caso di esito insoddisfacente o mancata risposta dopo due mesi, puoi rivolgerti gratuitamente al *Médiateur du Groupe La Poste* entro un anno dal primo reclamo scritto. Per disservizi imputabili all'operatore postale italiano nella tratta finale, dopo il reclamo e l'eventuale conciliazione è prevista anche la tutela presso l'AGCOM.

## Contatti La Poste e assistenza clienti

Secondo l'Arcep e l'annuario ufficiale francese *Service-Public.fr*, puoi contattare La Poste così:

- **Dall'Italia e dalla Svizzera:** `+33 810 821 821` (servizio clienti internazionale), dal lunedì al venerdì dalle 8:00 alle 19:00 (ora francese).
- **Dalla Francia:** `3631`, dal lunedì al venerdì 8:30–19:00 e il sabato 8:30–13:00 (servizio gratuito più il costo di una chiamata verso rete fissa).
- **Online e per posta:** modulo sul sito `laposte.fr` oppure posta ordinaria a `Service consommateurs, 99 999 LA POSTE`.

> **Attenzione a SMS ed e-mail truffa:** Il portale governativo francese *Cybermalveillance.gouv.fr* ricorda che La Poste, per i propri ritiri in Francia, **non chiede mai via SMS o e-mail di pagare per ritirare un pacco** (puoi segnalare il phishing a nome di La Poste a `phishing@laposte.fr` e, in Francia, gli SMS sospetti al `33700`). Tieni però conto del recapito nel tuo Paese: per una spedizione proveniente da fuori UE in **Italia**, [Poste Italiane](carrier:poste-italiane) riscuote gli oneri doganali alla consegna (in contanti o tramite POS dove previsto) oppure online tramite il servizio ufficiale *Gestione delle spese di importazione* su `poste.it`, mentre in **Svizzera** gli oneri d'importazione sono gestiti dal vettore che consegna come [La Posta Svizzera](carrier:swiss-post). Non seguire link sospetti: controlla sempre direttamente su `laposte.fr`, sul sito ufficiale del corriere locale o su Peek. Leggi anche [come riconoscere gli SMS truffa sui pacchi](guide:delivery-scam-texts).

## Tracciare un pacco Colissimo con Peek

[Peek](/) riconosce subito La Poste / Colissimo nei codici Colissimo di 13 caratteri con cifra di controllo valida, nei codici di 14 cifre che iniziano per `870` e nei numeri Smart Data di 15 caratteri o postali che terminano con `FR` (esclusi quelli di Chronopost) con carattere di controllo valido. Con altre varianti – come 2 lettere più 11 cifre, una chiave errata o 14 cifre condivise con [DPD](carrier:dpd-de) e altri – incolla comunque il codice: Peek segue oltre 4.000 corrieri e interroga quelli compatibili, oppure puoi scegliere La Poste toccando «Cambia corriere» (o «Cambia»). Se La Poste rifiuta il codice al primo controllo, Peek avvisa: «Questo corriere non emette numeri di tracciamento come questo. Controlla il numero o scegli un altro corriere.» Funzionano anche i link `laposte.fr` con `code=`, `idShip=` o `idship=`.

Le scansioni restano nel francese originale di La Poste; Peek colloca ogni tappa sulla mappa, indica la consegna prevista, il nome del negozio, l'ora di consegna e – finché il collo attende al punto di ritiro – nome e indirizzo della sede, scartando il tuo nome e il tuo indirizzo. Gli orari vengono convertiti dal fuso `Europe/Paris` a quello del tuo dispositivo, con la nota «Orari nel tuo fuso orario: …» su `peektracker.com` e nelle app per iPhone e Android.

Mentre tieni il pacco aperto sullo schermo, o nel giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui La Poste segna il pacco in consegna, finché risponde direttamente il tracciamento di La Poste (tramite servizi di tracciamento: ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti il giorno della consegna con pacco aperto o notifiche attive). Negli altri momenti, e dopo due giorni senza novità o 12 ore ferme su «Etichetta creata» o «Preso in carico», controlla ogni ora. Quando un altro corriere prende in carico il recapito, Peek mostra «Consegna con …» (o prima della sua prima scansione «Annunciato a …, non ancora ricevuto») e adotta il ritmo di chi consegna: ogni ora per Royal Mail e Poste Italiane, fino a ogni 30 minuti per PostNL e USPS, anche in consegna. Puoi ricevere avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può modificare la consegna né pagare dazi al posto tuo.

## Domande frequenti sul tracciamento Colissimo

### Chi consegna i pacchi Colissimo in Italia?

Quando il pacco esce dalla Francia, La Poste può indicare nel tracciamento il corriere partner e il relativo codice locale, che Peek riporta sulla scheda. Per i codici postali internazionali che finiscono con `FR` diretti in Italia, Peek interroga inoltre [Poste Italiane](carrier:poste-italiane). Anche l'e-mail o l'avviso di passaggio ricevuto in Italia ti conferma chi ha in carico l'ultimo miglio.

### Si paga la dogana su un pacco spedito dalla Francia?

Dalla Francia europea verso l'Italia no (fanno eccezione Livigno e Campione d'Italia, fuori dal territorio IVA dell'UE, e i pacchi dai dipartimenti francesi d'oltremare). Verso la Svizzera, invece, il pacco attraversa una frontiera doganale e sconta IVA e oneri se l'imposta supera 5 CHF (o oltre 100 CHF per i regali tra privati).

### La Poste consegna anche il sabato?

In Francia sì: La Poste Groupe recapita posta e pacchi sei giorni su sette (dal lunedì al sabato), e nel 2025 il 41 % dei pacchi Colissimo nazionali è arrivato il giorno successivo alla spedizione. In Italia e in Svizzera i giorni di consegna seguono invece il calendario del corriere incaricato della tratta finale.

:::sources
- [La Poste Groupe: La branche Services-Courrier-Colis](https://www.lapostegroupe.com/fr/services-courrier-colis) – servizio pacchi Colissimo, recapito 6 giorni su 7 in Francia e 41 % di consegne il giorno successivo nel 2025
- [Arcep: Comment résoudre un litige avec un opérateur postal ?](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – reclamo gratuito di mittente o destinatario, canali di contatto, service recours e Médiateur
- [Le Médiateur du Groupe La Poste: Foire aux questions](https://mediateur.groupelaposte.com/pages/foire-aux-questions) – procedura gratuita dopo due livelli di reclamo o due mesi senza risposta, termine di un anno
- [Service-Public.fr: Service consommateur de La Poste](https://lannuaire.service-public.gouv.fr/centres-contact/R11647) – numeri 3631 e +33 810 821 821, orari e indirizzo postale 99 999 LA POSTE
- [Cybermalveillance.gouv.fr: L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – La Poste non chiede mai pagamenti via SMS o e-mail per ritirare un pacco, segnalazioni a phishing@laposte.fr e 33700
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – consegna entro 30 giorni, termine supplementare, rimborso e responsabilità del venditore per i danni
- [Commissione europea: riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) e [Guidance sul dazio di 3 € e sulla commissione di 2 € (PDF, in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – dazio di 3 € per categoria fino a 150 € dal 1° luglio 2026 e commissione di gestione UE di 2 € per categoria dal 1° novembre 2026
- [Commissione europea: ambito territoriale IVA e doganale (in inglese)](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – esenzione intra-UE ed esclusione di Livigno e Campione d'Italia
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – pagamento degli oneri doganali su invii extra-UE alla consegna o tramite Gestione delle spese di importazione
- [Poste Italiane: Come difendersi dalle truffe](https://www.poste.it/sicurezza-online/guide-per-operare-in-sicurezza/come-difendersi-dalle-truffe) – riconoscimento di SMS ed e-mail di phishing
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – procedura di risoluzione delle controversie postali in Italia
- [UDSC/BAZG: Spese di imposizione e tracciabilità dei colli](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – competenza del trasportatore su spese di sdoganamento e tracciamento in Svizzera
- [UDSC/BAZG: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – franchigia d'imposta di 5 CHF e limite di 100 CHF per regali tra privati in Svizzera
- [La Posta Svizzera: Sdoganamento all'importazione](https://www.post.ch/it/ricezione/sdoganamento-all-importazione) – riscossione dei tributi all'importazione da parte della Posta Svizzera
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – formati e cifre di controllo, mappatura degli stati, privacy e frequenze di verifica su Peek
- [Universal Parcel Scraper: storico di tracciamento verificato](https://github.com/plhery/universal-parcel-scraper/blob/main/testing/auditedTrackingHistory.json) – diciture francesi di La Poste rilevate su spedizioni reali
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – passaggio dei codici postali FR verso Poste Italiane e La Posta Svizzera
:::
