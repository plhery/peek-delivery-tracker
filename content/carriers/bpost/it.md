---
title: Tracciamento bpost: codice a barre, stati e punti di ritiro
description: Traccia un pacco bpost dal o verso il Belgio: formati del codice a barre, stati in inglese su Track & Trace, tempi dei locker bbox e contatti.
slug: bpost-tracciamento
published: 2026-11-17
updated: 2026-11-17
---

bpost è l'operatore postale del Belgio e il marchio del gruppo bnode (ex bpostgroup da dicembre 2025) per le consegne dell'ultimo miglio in Belgio e nei Paesi Bassi. Segui il tuo pacco sul [Track & Trace di bpost](https://track.bpost.cloud/), nell'app My bpost oppure incollando il codice qui sopra. Trovi il codice a barre nell'e-mail di spedizione del negozio, nel messaggio di bpost, sull'etichetta o sull'avviso nella cassetta postale.

## Numero di tracciamento bpost: dove trovare il codice a barre

bpost chiama il numero di tracciamento codice a barre (*barcode*). È sull'etichetta di spedizione, sulla ricevuta postale, sull'avviso di mancata consegna e nell'e-mail di conferma. I negozi lo inviano di solito via e-mail, spesso come link al Track & Trace. Senza codice a barre bpost non può trovare l'invio: chiedilo al venditore. Altri consigli in [come trovare il numero di tracciamento](guide:find-tracking-number).

Formati più comuni indicati da bpost (esempi inventati):

| Formato | Esempio | Cosa identifica |
| --- | --- | --- |
| 24 cifre che iniziano con `3232` o `3299` | `323200000000000000000001` | Codice a barre per pacco bpost |
| 18 cifre che iniziano con `3232` | `323212345000000001` | Codice a barre per pacco bpost |
| 23 o 27 caratteri con `JJBE` + lettera | `JJBEA0000000000000000000001` | Codice nazionale bpost (pacco o raccomandata) |
| 2 lettere (esclusa `U`), 9 cifre e `BE` | `CE482910576BE` | Pacco postale internazionale spedito dal Belgio |
| `R` + lettera, 9 cifre e `BE` | `RR482910576BE` | Raccomandata internazionale dal Belgio |
| 30 cifre che iniziano con `010` | `010000000000000000000000000001` | Raccomandata nazionale in Belgio |

Un codice che inizia con `U` (`UA…BE`) serve solo per la dogana: non ha Track & Trace, viaggia come posta ordinaria e arriva entro 5 giorni lavorativi dallo sdoganamento.

**Codici simili.** Un codice postale che termina con le lettere di un altro paese appartiene alle poste di quel paese — ad esempio `…IT` è di [Poste Italiane](carrier:poste-italiane). Se digiti la lettera `O` al posto dello zero tra le nove cifre di un codice `…BE`, Peek la legge come `0`; una cifra errata che invalida il controllo S10 impedisce invece il riconoscimento finché non la correggi. Alcuni codici a 24 cifre appartengono a [InPost](carrier:inpost), Ciblex o Austrian Post, e quelli a 18 cifre con `323200000` coincidono con [Ecoscooting](carrier:ecoscooting).

## Stati del Track & Trace di bpost spiegati

Il Track & Trace di bpost è solo in inglese, francese, olandese e tedesco, e per i pacchi standard richiede il CAP del destinatario. Peek interroga direttamente bpost senza CAP e mostra gli stati originali in inglese:

| bpost scrive | Cosa significa per te |
| --- | --- |
| `Confirmation of preparation of the shipment received` | Il mittente ha annunciato la spedizione, ma bpost non ha ancora il pacco. |
| `Item accepted after deposit by sender` | Consegnato dal mittente in un punto bpost e preso in carico nella rete. |
| `Item is announced / Landmark Global (bpost) received the information` | Pacco internazionale preannunciato a Landmark Global, il marchio transfrontaliero del gruppo bnode. |
| `Landmark Global (bpost) has received the item` | Landmark Global ha ricevuto il pacco. |
| `Arrival at facilities` / `Arrival at international depot` | Arrivo in un centro di origine all'estero o al centro internazionale di bpost. |
| `Your item has been sorted` | Smistato e diretto alla tappa successiva. |
| `Awaiting presentation to Customs` / `No extra costs` | In attesa di controllo doganale, oppure svincolato senza costi. |
| `Departure to country of destination` | Partito dal Belgio verso il paese di destinazione. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | Le poste locali (in Italia, [Poste Italiane](carrier:poste-italiane)) hanno il pacco e lo consegneranno. |
| `The postman's delivery round for your item has started` | Oggi è nel giro di consegna del portalettere. |
| `Delivery interrupted due to unforeseen circumstances` | Il giro si è fermato dal lato di bpost prima del tuo indirizzo; seguirà una nuova data (`New delivery scheduled`). |
| `Item on it's way to bbox locker` | Diretto a un locker bbox, non ancora disponibile per il ritiro. |
| `Item available at Pick-up point` / `Item available at the bbox locker` | Pronto per il ritiro in un Punto di ritiro o in un locker bbox. |
| `Item delivered` / `Item delivered in mailbox` / `Item delivered at safeplace` | Consegnato a mano o ritirato al punto, in cassetta postale o nel luogo sicuro. |
| `Your parcel was delivered at one of your neighbours. You can find all delivery details in your mailbox.` | Consegnato a un vicino; l'avviso nella cassetta postale indica quale. |

Se compare `Item arrived at the distribution center - Item can't be assigned to a delivery round - missing information`, mancano dati sull'indirizzo; `Returned to Landmark Global (bpost), Incorrect / illegible / incomplete address` indica invece il reso dalle poste estere per indirizzo errato o incompleto. In Belgio l'obiettivo abituale dopo la presa in carico è il giorno lavorativo successivo (il 95% entro la fascia indicata); se il negozio ha un accordo weekend, bpost consegna anche il sabato (8:00–17:00 al punto o locker) e la domenica (9:00–19:00 a domicilio).

## Consegna in Belgio, Punti di ritiro e locker bbox

**Assenza a casa.** Senza preferenze impostate, il portalettere prova con un vicino o un luogo sicuro. Altrimenti ritiri il pacco nel Punto di ritiro indicato dal giorno lavorativo successivo (dalle 11:00 se arriva dall'estero). bpost avvisa sempre via e-mail e lascia carta solo se non può contattarti in digitale; controlla il punto su Track & Trace prima di uscire. Non c'è secondo passaggio in giornata, salvo dopo una mancata consegna di sabato (nuovo tentativo il giorno lavorativo successivo).

**Giacenza e delega.** Hai 14 giorni di calendario in un Punto di ritiro e 5 giorni di calendario in un locker bbox senza proroga (al 6° giorno torna al mittente), e un pacco già arrivato in un punto non si può trasferire altrove. Chiunque abbia il codice a barre ritira un pacco ordinario; una raccomandata (`010…` o `R…`) richiede il destinatario con documento e codice a barre, oppure procura firmata sull'avviso.

**Locker bbox e preferenze.** Gli oltre 2.500 locker bbox sono aperti 24/7 senza account: con schermo scansioni il QR dell'e-mail o dell'app My bpost (con codice a 6 cifre negli invii diretti); senza schermo si aprono solo dall'app via Bluetooth e posizione. I colli troppo grandi vanno a un altro Punto di ritiro, mentre quelli piccoli entrano prima nella cassetta postale. Da MyPreferences o dall'app cambi vicino o luogo sicuro fino a 5 minuti prima (esclusi contrassegni, codici sicuri, raccomandate `R` e, per il luogo sicuro, invii con garanzia o assicurazione), punto o locker fino alla mezzanotte precedente, o rinvii una volta di 1 giorno lavorativo prima del giro mattutino.

## Pacco bloccato, in dogana o non ricevuto

**Tracciamento fermo e reclami.** Attendi alcuni giorni lavorativi in Belgio, 5 giorni lavorativi da o verso paesi confinanti, o 10 giorni lavorativi da o verso l'Italia, il Regno Unito e altri paesi; poi chiedi al mittente di contattare bpost, poiché solo il mittente può aprire un'indagine formale (vedi [tracciamento che non si aggiorna](guide:tracking-not-updating)). Negli acquisti da professionisti nell'UE la consegna è dovuta entro 30 giorni salvo diverso accordo ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm)).

**Danneggiato, non ricevuto o reso.** Puoi rifiutare subito un pacco danneggiato al Punto di ritiro; dopo il ritiro invia foto al venditore entro 7 giorni (e anche a bpost per pacchi dall'estero in Belgio; vedi [pacco danneggiato](guide:damaged-parcel)). Se risulta consegnato ma manca, controlla vicini, cassetta postale, foto del luogo sicuro nell'app e il [modulo bpost per spedizioni non ricevute](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1), oltre a [pacco consegnato ma non ricevuto](guide:delivered-not-received). Se torna indietro per indirizzo errato, rifiuto o fine giacenza, chiedi rimborso o rispedizione al negozio ([pacco restituito al mittente](guide:returned-to-sender)).

**Dogana in Belgio.** Paghi gli oneri nell'app My bpost o su Track & Trace entro 14 giorni (un'ispezione fisica richiede da 3 a 7 giorni). Sugli ordini extra-UE sotto i 150 € (spedizione esclusa), bpost applica **3 € di dazio per tipo di prodotto** (dal 1° luglio 2026) più **2 € di commissione europea per tipo di prodotto** (dal 1° novembre 2026) — due magliette identiche pagano 3 € + 2 € = 5 €, una maglietta e un costume pagano 6 € + 4 € = 10 € — oltre a IVA (di norma 21%) e **21,50 €** di spese bpost se il negozio non ha riscosso IVA e dazi all'acquisto. Sopra i 150 € paghi dazi tariffari, IVA, **40 €** di spese bpost e i **2 €** per tipo di prodotto. I regali tra privati fino a 45 € sono esenti (esclusi alcolici, profumi e tabacco). In caso di doppia IVA per mancato invio IOSS, puoi rifiutare nell'app o pagare e chiedere il rimborso al venditore. Vedi [acquisti extra-UE su bpost](https://www.bpost.be/en/shopping-outside-eu) e la guida alla [dogana](guide:customs).

> Per spedizioni nell'UE, bpost non chiede mai pagamenti in transito, dati personali via e-mail o SMS né minaccia ufficiali giudiziari. Quando un pacco da fuori UE ha oneri d'importazione da pagare, invece, bpost invia davvero una richiesta di pagamento via e-mail, SMS, notifica nell'app My bpost o lettera: pagala solo nell'app My bpost, su Track & Trace o in un ufficio postale. Inoltra messaggi sospetti ad `abuse@bpost.be` e leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Assistenza clienti bpost e mediazione postale

- **Telefono:** `02 278 51 26` sulle pagine in inglese/olandese (`+32 2 278 51 26` dall'estero) e `02 278 51 27` in francese/tedesco; lun.–ven. 08:00–12:30 e 13:00–18:00, sab. 09:00–13:00 (tariffa zonale).
- **Chat e sportelli:** chat su pagine come Track & Trace (senza orari pubblicati) e sportelli Post Office, Post Point o Parcel Point.
- **Posta (senza francobollo in Belgio):** `bpost, Box 5000, 1000 Brussels`.

bpost non pubblica e-mail di assistenza. Dopo un reclamo a bpost rimasto irrisolto, mittenti e destinatari possono rivolgersi gratuitamente al [Mediatore del settore postale belga](https://www.ombudsmanposte.be/en/) (`Boulevard Roi Albert II 8 box 4, 1000 Brussels`, `+32 2 221 02 21`).

## Seguire un pacco bpost con Peek

[Peek](/) riconosce subito bpost («Corriere rilevato») con un codice valido di due lettere (esclusa `U`), nove cifre e `BE`, e con `JJBE` + lettera + 18 o 22 cifre. Con 24 cifre che iniziano per `3232` o `3299`, interroga bpost, [InPost](carrier:inpost), Austrian Post e Ciblex («Verifica dei servizi di tracciamento…») e sceglie bpost quando risponde («bpost ha questo pacco.»). Con 18 cifre che iniziano per `3232`, interroga bpost e anche [Ecoscooting](carrier:ecoscooting) se dalla 6ª alla 9ª cifra ci sono zeri (`323200000…`), chiedendo «Scegli» solo se entrambi lo conoscono. Le raccomandate belghe a 30 cifre (`010…`) si seguono impostando bpost con «Cambia»; i codici `U` vengono provati solo su [Chronopost](carrier:chronopost). Accetta anche link `bpost.be` o Track & Trace con `itemCodes=`. Sui codici S10 tra Belgio e Italia, Peek collega automaticamente bpost e [Poste Italiane](carrier:poste-italiane) (controllata ogni 60 minuti) mantenendo la cronologia.

Peek interroga bpost senza CAP e mostra le scansioni in inglese sotto «Cosa dice bpost» (con fasi come «In transito», «In dogana», «In consegna», «Pronto per il ritiro», «Problema segnalato» o «Restituito al mittente»), località, peso, dimensioni, nome e indirizzo del punto o locker (anche dopo il ritiro) e orari convertiti («Orari nel tuo fuso orario: …»). Controlla fino a ogni 10 minuti, fino a ogni 2 minuti dalle 06:00 locali «In consegna» diretto con bpost (15 minuti tramite servizi), e ogni ora dopo due giorni senza novità o 12 ore su «Etichetta creata» / «Preso in carico». Se dopo due giorni su peektracker.com («In attesa di bpost») resta sconosciuto, mostra «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.»; dopo quattro giorni fermo prima della consegna appare «Chiedi aiuto a bpost». Non mostra data stimata ed esclude mittente, destinatario, indirizzi personali, contatti, preferenze e prova di consegna, con avvisi su «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti sul tracciamento bpost

### Quanto tempo resta un pacco bpost in un Punto di ritiro o locker?

14 giorni di calendario in un Punto di ritiro e 5 giorni di calendario in un armadietto automatico bbox, senza proroghe. Al 6° giorno nel locker, o dopo 14 giorni al punto, torna al mittente.

### bpost consegna il sabato e la domenica?

Sì, quando il negozio online ha un accordo weekend con bpost: sabato dalle 8:00 alle 17:00 al Punto di ritiro o locker, domenica dalle 9:00 alle 19:00 a domicilio. Lettere e raccomandate viaggiano solo nei giorni feriali.

### Chi può ritirare una raccomandata bpost?

Solo il destinatario con documento d'identità e codice a barre, oppure un delegato con procura firmata sull'avviso di mancata consegna. Un pacco ordinario può essere ritirato da chiunque mostri il codice a barre.

### Perché non riesco a tracciare il mio codice a barre bpost?

Di solito il venditore non ha ancora consegnato il pacco a bpost («In attesa della prima scansione di bpost» sulla maggior parte dei formati condivisi). I codici con `U` non hanno Track & Trace, e un codice `…BE` trascritto male non supera il controllo S10.

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – formati e codici U
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – canali e poste estere
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – avvio del tracciamento
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – chiedere il codice al negozio
- [bpost: When will my parcel be delivered?](https://www.bpost.be/en/faq/when-will-my-parcel-be-delivered) – giorno lavorativo successivo e fascia del 95%
- [bpost: Track & Trace](https://track.bpost.cloud/) – portale ufficiale
- [bpost: Track & Trace (en.json)](https://track.bpost.cloud/assets/i18n/en.json) – lingue e testi in inglese
- [bpost: Not home, where and when can I pick it up?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – giorno lavorativo successivo ed e-mail
- [bpost: Missed parcel, can the postman pass again today?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – nessun secondo passaggio in giornata
- [bpost: Second pass after Saturday notice](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – secondo tentativo dopo il sabato
- [bpost: Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 e 5 giorni, raccomandate e divieto di trasferimento
- [bpost: Remove parcel from locker](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – QR, 6 cifre, Bluetooth e 6° giorno
- [bpost: Bbox](https://www.bpost.be/en/bbox) – +2.500 locker 24/7
- [bpost: Can I change the time of delivery?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – rinvio di 1 giorno lavorativo
- [bpost: Delivery preferences](https://www.bpost.be/en/mybpostapp-pref-parcel) – scadenze ed eccezioni in MyPreferences
- [bpost: Receive a parcel from abroad](https://www.bpost.be/en/receive-parcel/abroad) – 3–7 giorni in dogana, IOSS e ritiro alle 11:00
- [bpost: Parcel stopped moving](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 e 10 giorni lavorativi
- [bpost: File a complaint](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – reclamo del mittente e 7 giorni per danni
- [bpost: Delivered, haven't received anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – verifiche e foto del luogo sicuro
- [bpost: Formulaire colis pas reçu](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) – modulo online
- [bpost: Colis renvoyé à l'expéditeur](https://www.bpost.be/fr/faq/mon-colis-aurait-du-etre-livre-domicile-mais-ete-renvoye-lexpediteur-pourquoi-et-comment-puis-je) – motivi di reso
- [bpost: Customs](https://www.bpost.be/en/receive-parcel/customs) – 14 giorni per pagare e regali fino a 45 €
- [bpost: Shopping outside the EU](https://www.bpost.be/en/shopping-outside-eu) – 3 € e 2 € per tipo di prodotto, IVA, 21,50 € e 40 €
- [bpost: Saturday and Sunday delivery](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – orari del fine settimana
- [bpost: Contact customer services](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 02 278 51 26, orari, chat e Box 5000
- [bpost: Contacter le service clientèle](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27
- [Ombudsman for the Postal Sector](https://www.ombudsmanposte.be/en/) – mediazione gratuita
- [Ombudsman: Qui peut introduire une plainte ?](https://www.ombudsmanposte.be/fr/a-propos-de-nous/notre-fonctionnement/qui-peut-introduire-une-plainte/) – mittenti e destinatari
- [bpost: Phishing](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – avvisi UE e abuse@bpost.be
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – termine di 30 giorni nell'UE
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – gruppo bnode e marchi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – regole di tracciamento in Peek
:::
