---
title: Chronopost tracking: pacco dalla Francia, stati e ritiro
description: Segui un pacco Chronopost dalla Francia in Italia: com'è fatto il numero, cosa significano gli stati in francese, punti Pickup, problemi e contatti.
slug: chronopost-tracking
published: 2026-11-14
updated: 2026-11-14
---

Chronopost è il corriere espresso francese del gruppo La Poste e fa parte di Geopost, la divisione pacchi a cui appartengono anche DPD e [BRT](carrier:brt). Questa guida è pensata per chi aspetta in Italia o nella Svizzera italiana una spedizione partita dalla Francia con Chronopost — per esempio da un negozio online o con Shop2Shop — con una parte dedicata ai punti di ritiro in Francia. Segui il pacco su chronopost.fr o incolla il codice qui sopra. Anche laposte.fr risponde ai numeri Chronopost, ma può omettere scansioni e riferimenti presenti su Chronopost.

## Numero di tracciamento Chronopost: com'è fatto

Il codice Chronopost ha di solito 13 caratteri (due lettere, nove cifre e due lettere), oppure 14 o 15 caratteri. Se non lo trovi, chiedilo al venditore o leggi [dove trovare il numero di tracciamento](guide:find-tracking-number).

| Formato (inventato) | Che cos'è |
| --- | --- |
| `XY000000005FR` | Due lettere (`PZ`, `XR`, `XT`, `XU`, `XW` o `XY`), nove cifre e `FR`: il classico codice Chronopost. |
| `AB000000000JB` | La stessa forma a 13 caratteri che termina in `JB`, `JF` o `TS`. |
| `XN000000000000` | `XN` e 12 cifre: Chronopost segue anche questi codici. |
| `00000000000000U` | 14 cifre più un carattere di controllo (lettera o cifra): il formato DPD usato anche da Chronopost. |
| `20000000000000` | 14 cifre che non iniziano con `10`: può essere Chronopost, ma anche DPD o [BRT](carrier:brt). |

La struttura a 13 caratteri è il formato postale internazionale: un codice che finisce in `FR` con altri prefissi può essere un Colissimo di La Poste, mentre uno in `IT` o `CH` può appartenere a [Poste Italiane](carrier:poste-italiane) o a [La Posta Svizzera](carrier:swiss-post). Il formato a 15 caratteri è condiviso con [DPD Svizzera](carrier:dpd-ch), DPD Germania, DPD Portogallo e DPD Regno Unito. Se Chronopost non lo riconosce, controlla l'e-mail del negozio o [come riconoscere il corriere dal numero](guide:tracking-number-formats).

## Stati del tracciamento Chronopost: cosa significano

Chronopost scrive gli stati in francese; Peek li conserva nella cronologia e traduce `Livraison effectuée` in «Consegnato». Nell'ordine abituale della spedizione:

| Chronopost scrive | Cosa significa per te | In Peek |
| --- | --- | --- |
| `Colis en cours de préparation chez l'expéditeur` | Etichetta creata; il negozio non ha ancora affidato il pacco. | «Etichetta creata» |
| `Colis déposé par l'expéditeur` | Il mittente ha consegnato il pacco a Chronopost. | «Preso in carico» |
| `Tri effectué dans l'agence de départ` | Smistato nella filiale di partenza. | «In transito» |
| `Colis en cours d'acheminement` | In viaggio nella rete Chronopost. | «In transito» |
| `Colis entré dans le pays de destination` | È entrato nel Paese di destinazione. | «In transito» |
| `Tri effectué dans l'agence de distribution` | Smistato nella filiale di consegna. | «In transito» |
| `Livraison reportée de 24h` | Trattenuto un giorno in filiale senza tentare la consegna; riparte da solo. | «In transito» |
| `Livraison reportée` | Il partner di consegna ha rinviato il passaggio senza azioni richieste da parte tua. | «In transito» |
| `Colis en cours de livraison` | In consegna oggi. | «In consegna» |
| `Echec de livraison suite à l'absence du destinataire.` | Non c'era nessuno a casa. | «Tentativo di consegna» |
| `Colis non livré : client absent` | Il corriere incaricato non ti ha trovato a casa. | «Tentativo di consegna» |
| `Colis mis à disposition au point de retrait` | Ti aspetta in un punto di ritiro o locker. | «Pronto per il ritiro» |
| `Colis remis au destinataire au point de retrait` | Ritirato al punto di ritiro. | «Consegnato» |
| `Colis en attente d'informations complémentaires de votre part` | Serve un'informazione tua: usa i canali ufficiali, mai link da SMS dubbi. | «Problema segnalato» |
| `Colis en retour expéditeur` | Sta tornando al mittente. | «Problema segnalato» |
| `Livraison effectuée` | Consegnato. | «Consegnato» |

`Livraison reportée de 24h` (verifica indirizzo, errore di smistamento o chiusura) non significa pacco perso. `Destinataire informé par SMS ou mail` e `Instruction de livraison reçue` segnano solo un avviso o una tua istruzione. Un `Livraison effectuée` dopo `Colis en retour expéditeur` indica il rientro dal venditore («Restituito al mittente» su Peek). Approfondisci in [stati di una spedizione](guide:tracking-statuses).

## Pacco Chronopost verso l'Italia o la Svizzera

Secondo La Poste Groupe, Chronopost spedisce colli fino a 30 kg in 230 Paesi. In Italia fa parte di Geopost dal 2020 [BRT](carrier:brt), con oltre 550 locker e più di 11.600 punti BRT-fermopoint (dove i pacchi fino a 20 kg restano 10 giorni).

**Consegna locale.** A differenza di Germania e Portogallo, per Italia e Svizzera Peek non riceve da Chronopost un numero partner automatico e prosegue il monitoraggio su Chronopost. Se per la tratta italiana ricevi via SMS o e-mail un BRTcode (es. 14 cifre), puoi cercarlo su brt.it o incollarlo su Peek per seguire [BRT](carrier:brt).

**Dogana.** Sugli acquisti online provenienti da fuori UE, dal 1° luglio 2026 nell'UE si applica un dazio fisso di 3 € per articolo fino a 150 € e, dal 1° novembre 2026, una commissione doganale UE di 2 € fissi per articolo sulle vendite a distanza di qualsiasi valore, dovuta all'accettazione della dichiarazione. In Svizzera (fuori UE), l'UFDSC ricorda che gli invii dall'estero sono soggetti a dazi e IVA: sdogana il corriere, che riscuote le relative spese e risponde sui costi e sulla ricerca del pacco. `Problème douanier résolu, colis en cours d'acheminement` indica che l'intoppo doganale è risolto («In transito»). Vedi [pacco fermo in dogana](guide:customs).

## In Francia: punti Pickup, locker e assenza

In Francia Chronopost conta 19.555 punti tra negozi e locker Pickup, uffici postali e filiali. All'arrivo in un punto Pickup ricevi e-mail o SMS con orari e scadenza; secondo Pickup non supera mai:

- **8 giorni** in un negozio (*relais*) Pickup;
- **5 giorni** in un locker automatico Pickup.

Poi il pacco torna automaticamente al mittente ([pacco non ritirato](guide:uncollected-parcel)). Ritiri con il Pickup Pass (QR) o i codici ricevuti, oppure in negozio con un documento d'identità originale. Alcuni locker sono aperti 24/7 ([locker per pacchi](guide:parcel-lockers)). Un terzo può ritirare con il tuo Pickup Pass o i tuoi codici, o in negozio con il suo documento originale e il tuo; Pickup non accetta deleghe scritte.

## Pacco bloccato, danneggiato o non ricevuto

**Fermo o in ritardo.** Rivolgiti al negozio. Negli acquisti a distanza da un venditore francese, questi risponde da solo della consegna e, salvo diversa data concordata, deve consegnare entro 30 giorni dall'ordine; oltre puoi chiedergli per iscritto un termine supplementare ragionevole prima di risolvere il contratto. Vedi [tracciamento che non si aggiorna](guide:tracking-not-updating).

**Danneggiato o smarrito.** Reclama con il venditore. A domicilio annota riserve sulla bolla se il pacco è rovinato e avvisa subito il negozio; in un negozio Pickup fai registrare le riserve al commerciante prima di ritirarlo o rifiutarlo. Leggi [pacco smarrito](guide:lost-parcel) e [pacco danneggiato](guide:damaged-parcel).

**Consegnato ma assente.** Contatta il negozio: se non prova la consegna fisica a te, il rischio resta suo ([consegnato ma non ricevuto](guide:delivered-not-received)).

## Contatti Chronopost

Oltre alla pagina contatti su chronopost.fr, La Poste Groupe, Geopost e Pickup indicano (al 14 novembre 2026):

- **Léonard**, chatbot di Chronopost con passaggio a un operatore per i casi complessi.
- **Telefono:** `09 69 39 13 91` (numero francese, utile se mancano i codici di ritiro).
- **Shop2Shop** (es. acquisti su leboncoin): chronoshop2shop.fr.

Se un reclamo scritto, datato e firmato resta senza risposta per almeno due mesi, o la risposta non ti soddisfa, puoi rivolgerti entro un anno al *Médiateur du Groupe La Poste*.

> Pickup avverte di SMS ed e-mail truffa che chiedono oneri doganali o affrancatura, usano link non ufficiali o mettono fretta («ultima occasione»). Non cliccare sul link: verifica sul sito ufficiale. Leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Seguire un pacco Chronopost con Peek

[Peek](/) riconosce subito i codici di 13 caratteri che iniziano con `PZ`, `XR`, `XT`, `XU`, `XW` o `XY` con cifra di controllo valida e finiscono in `FR` o `IT`, o che terminano in `JB`, `JF` o `TS`. Con finale `CH` (possibile anche [La Posta Svizzera](carrier:swiss-post)) o `GB` verifica con Chronopost. Se la cifra di controllo non torna, o se il codice finisce in `RV`/`VF` o inizia con `XN`, interroga solo Chronopost; altri prefissi validi in `FR` vanno a La Poste.

Con 15 caratteri mostra «Verifica dei servizi di tracciamento…» e interroga Chronopost e le quattro reti DPD; con 14 cifre senza `10` iniziale interroga più corrieri, inclusi Chronopost, DPD e [BRT](carrier:brt). Quando Chronopost risponde vedi «ha questo pacco»; se rispondono più corrieri premi «Scegli», oppure usa «Cambia» sotto «Compatibili con questo numero» o «Tutti i corrieri». Accetta link chronopost.fr e chronoshop2shop.fr senza codice postale.

Peek mostra sulla mappa ogni filiale, il Paese di destinazione, l'ora di consegna, il numero partner per Germania o Portogallo e, durante l'attesa, nome e indirizzo del punto di ritiro (scartando il tuo indirizzo e i contatti del punto). Mostra una data prevista solo con riconsegna o fascia prenotata, o con `Livraison prévue lundi prochain` tra venerdì e domenica. Le scansioni con fuso appaiono nel tuo fuso orario («Orari nel tuo fuso orario: …» su peektracker.com); quelle senza fuso seguono l'ora locale.

Controlla ogni 10 minuti, e in consegna ogni 2 minuti dalle 6:00 (ora francese, italiana e svizzera) se risponde Chronopost — 15 minuti dopo un errore o tramite servizi di tracciamento. Resta a 10 minuti fino a 30 minuti prima di una fascia oraria o se è in consegna da un giorno precedente; dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Quando subentra un altro corriere, Peek segue il suo ritmo. Scegli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non invia reclami per te.

## Domande sul tracking Chronopost

### Chronopost fa parte di La Poste?

Sì. Appartiene a Geopost, la holding internazionale dei pacchi di La Poste Groupe.

### Chronopost, DPD e BRT sono la stessa azienda?

Operano con marchi propri, ma Chronopost, BRT e le reti DPD fanno tutti parte del gruppo Geopost; per questo alcuni codici Chronopost hanno 15 caratteri come quelli DPD.

### Quanto resta un pacco Chronopost in un punto Pickup?

In Francia fino a 8 giorni in un negozio Pickup e fino a 5 giorni in un locker Pickup; il messaggio di avviso riporta la scadenza prima del reso.

:::sources
- [La Poste Groupe: Chronopost (in inglese)](https://www.lapostegroupe.com/en/focus/la-poste-groupes-subsidiaries/chronopost) – fino a 30 kg in 230 Paesi e 19.555 punti in Francia
- [La Poste Groupe: 2025 Integrated Report, PDF (in inglese)](https://www.lapostegroupe.com/assets/media/le-groupe-laposte/ad4Q_p1ZCF7ETKqb_MEL_LAPO_RI_seul_2025_EN_14042026.pdf) – Chronopost in Geopost con DPD e BRT, chatbot Léonard
- [Geopost: Who we are (in inglese)](https://www.geopost.com/en/about-us/who-we-are/) – Geopost in La Poste Groupe con DPD, Chronopost, SEUR e BRT
- [Geopost: BRT (in inglese)](https://www.geopost.com/en/about-us/our-companies-and-partners/brt/) – BRT in Geopost dal 2020, oltre 550 locker e punti di ritiro
- [Geopost: leboncoin is teaming up with Chronopost (in inglese)](https://www.geopost.com/en/news/leboncoin-is-teaming-up-with-chronopost/) – Shop2Shop by Chronopost
- [BRT: FAQ, Domande frequenti sui Servizi BRT](https://www.brt.it/it/servizio-clienti/faq/) – BRTcode in SMS ed e-mail, ricerca su brt.it e limite di 20 kg
- [BRT: BRT-fermopoint](https://www.brt.it/it/brt-fermopoint/) – oltre 11.600 punti in Italia e 10 giorni di giacenza
- [Pickup: FAQ particulier (in francese)](https://www.pickup.fr/faq-particulier/) – 8 giorni nei relais e 5 nei locker, codici e ritiro di terzi senza delega scritta, 09 69 39 13 91, riserve e truffe
- [Service-Public: Achat à distance, livraison du bien (in francese)](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – responsabilità del venditore, 30 giorni, riserve e pacchi non ricevuti
- [Médiateur du groupe La Poste: Conditions de saisine (in francese)](https://mediateur.groupelaposte.com/pages/conditions-requises-a-la-saisine-du-mediateur-de-la-consommation-du-groupe-la-poste) – reclamo scritto preventivo, due mesi e un anno
- [Commissione europea: EU Customs Reform (in inglese)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € dal 1° luglio 2026 e commissione UE di 2 € dal 1° novembre 2026
- [Commissione europea: Customs Guidance on EUR 3 customs duty, PDF (in inglese)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – commissione fissa di 2 € per articolo sulle vendite a distanza, dovuta all'accettazione della dichiarazione
- [UFDSC: Frais de dédouanement et suivi des colis (in francese)](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – in Svizzera dazi, IVA e spese di sdoganamento del corriere
- [Universal Parcel Scraper: Chronopost (in inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chronopost) – numeri, stati, partner per Germania e Portogallo e cosa mostra Peek
- [Universal Parcel Scraper: La Poste (in inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – risposta La Poste ai numeri Chronopost
:::
