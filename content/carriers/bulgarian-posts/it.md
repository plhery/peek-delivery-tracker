---
title: Tracciamento Bulgarian Posts: codici PS/BG, dogana e aiuto
description: Traccia un pacco Bulgarian Posts: codici PS e S10 BG, stati in inglese e bulgaro, giacenza 20 giorni, tariffe doganali UE, reclami e contatti.
slug: tracciamento-bulgarian-posts
published: 2026-11-24
updated: 2026-11-24
---

Bulgarian Posts (*Български пощи ЕАД*) è l’operatore postale universale nazionale della Bulgaria, responsabile di lettere nazionali, e-package e pacchi, nonché della posta internazionale dell’Unione Postale Universale (UPU) e delle consegne corriere EMS / Bulpost. Puoi controllare una spedizione sulla [pagina di tracciamento di Bulgarian Posts](https://www.bgpost.bg/en/postal-services/track-package), oppure incollare il codice nel campo qui sopra.

## Codici di tracciamento Bulgarian Posts: formati PS e S10

Cerca il codice a barre di 13 caratteri sulla ricevuta di spedizione, nell’e-mail del negozio online o sull’avviso cartaceo di consegna (*служебно известие*). Su [bgpost.bg/en/postal-services/track-package](https://www.bgpost.bg/en/postal-services/track-package), Bulgarian Posts indica due formati tracciati:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `PS48291736405` | Invio nazionale o e-package: `PS` seguito da 11 lettere e cifre (13 caratteri in totale). |
| `RR482917367BG` | Raccomandata o piccolo pacco internazionale (`R`, `C`, `E`, `L` o `V` + seconda lettera + 9 cifre + `BG`). |
| `CP482917367BG` | Pacco postale internazionale spedito dalla Bulgaria (`CP` + 9 cifre + `BG`). |

I pacchi in arrivo dall’estero mantengono il codice UPU S10 delle poste di origine (che termina con le due lettere di quel paese, come `DE`, `CN` o `US`). Bulgarian Posts precisa che gli invii internazionali in uscita si tracciano sul proprio sito fino all’uscita dalla Bulgaria e poi sul sito delle poste di destinazione, mentre quelli in arrivo compaiono su Bulgarian Posts una volta giunti in Bulgaria. Per altri corrieri in Bulgaria consulta [Sameday](carrier:sameday), [BOX NOW](carrier:box-now) o [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento Bulgarian Posts

Il tracciamento di Bulgarian Posts mostra eventi UPU IPS in inglese sulle tratte internazionali — contrassegnati con `(Otb)` in uscita o `(Inb)` in entrata — accanto a diciture bulgare per le scansioni nazionali (anche nella versione inglese del sito).

| Bulgarian Posts mostra | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Receive item from customer (Otb)`, `Получаване пратка от клиент (А10)`, primo `Приемане` | Accettato dal mittente presso un ufficio postale. | «Preso in carico» |
| `Insert item into bag (Otb) and Send item abroad`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Insaccato per l’esportazione, spedito all’estero o ricevuto presso l’ufficio di scambio internazionale bulgaro. | «In transito» |
| `Receive item at sorting centre (Inb)`, `Insert item into domestic bag`, `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Приемане` successivo | In transito nei centri di smistamento bulgari verso il tuo ufficio postale locale. | «In transito» |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Presentato alla dogana bulgara o in attesa di documenti/valutazione doganale (spesso accompagnato da una nota di attesa presentazione al funzionario doganale). | «In dogana» |
| `Return item from customs (Inb)` | Sdoganato e riconsegnato alla rete Bulgarian Posts (non è un reso al mittente). | «In transito» |
| `Handover item to delivery agent` | In consegna oggi con il portalettere a domicilio. | «In consegna» |
| `Unsuccessful item delivery attempt (Inb)`, `Първо посещение` | Primo tentativo non riuscito; lasciato avviso scritto e spedizione inviata all’ufficio postale. | «Tentativo di consegna» |
| `Receive item at collection point for pick-up (Inb)`, `За получаване в ПС`, `Второ посещение` | Pronto per il ritiro nel tuo ufficio postale (`Второ посещение` segna il secondo avviso scritto inviato dopo 10 giorni solari). | «Pronto per il ritiro» |
| `Deliver item (Inb)`, `Доставена`, `Доставена пратка` | Consegnato con firma. | «Consegnato» |

Nota che `Създаване` (la creazione della registrazione nazionale da parte di Bulgarian Posts subito dopo la scansione all’ufficio di scambio) è una voce amministrativa priva di spostamento fisico, perciò Peek mantiene la fase della scansione precedente. Allo stesso modo, `Второ посещение` non è un secondo tentativo a domicilio: registra il secondo avviso di giacenza mentre il pacco resta in attesa in ufficio postale.

## Ritiro in ufficio postale, giacenza e spese di custodia

Secondo le [Condizioni Generali di Bulgarian Posts (ОУ 2023)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) (punti 43, 45 e 46, collegate da [General Terms](https://www.bgpost.bg/en/about_us/conditions)):

- **Raccomandate e pacchi:** consegnati personalmente al destinatario o a persona delegata dietro firma e documento con foto. I pacchi postali (*пощенски колети*) vengono normalmente ritirati all’ufficio postale di destinazione dopo la consegna di un avviso scritto (*писмено служебно известие*), salvo pagamento separato della consegna a domicilio.
- **Giacenza standard — 20 giorni solari:** sia le raccomandate dopo un tentativo fallito sia i pacchi in ufficio attendono **20 giorni solari** dall’arrivo all’ufficio di consegna prima di tornare al mittente.
- **Secondo avviso a 10 giorni:** se non ritiri entro **10 giorni solari**, Bulgarian Posts invia un secondo avviso scritto (`Второ посещение`).
- **Tariffa di custodia dopo 9 giorni:** per pacchi postali non ritirati entro **9 giorni solari** dalla notifica, Bulgarian Posts addebita una tariffa di custodia (*магазинаж*) secondo il tariffario vigente.
- **Invii sdoganati — 45 giorni solari:** in base alle [regole doganali di Bulgarian Posts](https://www.bgpost.bg/en/customs), **tutti gli invii sottoposti a sdoganamento si ritirano esclusivamente all’ufficio postale designato** (mai consegnati a domicilio) e restano in giacenza **45 giorni solari** prima del rientro al mittente.

## Pacchi extra-UE, oneri doganali e dazio da 3 € / commissione UE da 2 €

Le spedizioni all’interno dell’UE non fanno dogana. Per acquisti online e regali che arrivano in Bulgaria da fuori UE, [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs) e la [Commissione Europea](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) illustrano gli oneri in vigore:

- **Dazio fisso e commissione di gestione UE:** un **dazio doganale fisso di 3 € per articolo** si applica agli acquisti online B2C fino a 150 € (in vigore dal 1° luglio 2026) e una **Union Handling Fee (UHF) di 2 € per singolo articolo** si applica agli acquisti online extra-UE qualunque sia il valore (in vigore dal 1° novembre 2026; inclusa nel valore doganale ai fini IVA; esente per regali privati C2C). Sia il dazio da 3 € (fino a 150 €) sia la commissione UHF da 2 € **non sono rimborsabili**, anche in caso di reso di merce difettosa.
- **Tariffe di rappresentanza doganale Bulgarian Posts (IVA inclusa, in vigore dal 2 novembre 2026):**
  - B2C fino a 150 € con IOSS (dichiarazione H7): **2,70 €** (più dazio se non riscosso dal negozio al checkout).
  - B2C fino a 150 € senza IOSS (Regime Speciale, H7): **3,80 €** (più IVA e dazio).
  - B2C da 150 € a 1.000 € (H6): **12,00 €** (più IVA e dazio).
  - B2C oltre 1.000 € (H1): **20,00 €** per la prima tipologia di articolo più **5,00 €** per ogni tipologia aggiuntiva.
  - Regali privati C2C: **2,70 €** fino a 45 € (senza IVA né dazio), **3,80 €** da 45 € a 1.000 €, oppure **20,00 €** (+ 5,00 € per tipologia aggiuntiva, codice EORI richiesto) oltre 1.000 €.
- **Documenti richiesti:** dichiarazione di consenso, dichiarazione di valore e, per gli acquisti, ordine/fattura corrispondente al numero di tracciamento con prova di pagamento (estratto conto bancario, carta o PayPal).

Consulta [pacchi fermi in dogana](guide:customs) per una guida generale sulla dogana UE.

## Pacchi mancanti, in ritardo o danneggiati e diritti dei consumatori

Per gli acquisti online il venditore è responsabile della consegna e dei rimborsi. Secondo la Commissione bulgara per la tutela dei consumatori ([regole KZP sugli acquisti online](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping)), gli ordini online vanno consegnati entro 30 giorni salvo diverso accordo e i consumatori hanno diritto di recesso entro 14 giorni dalla ricezione.

**Presentare reclamo a Bulgarian Posts.** Secondo [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) e i punti 56–59 delle Condizioni Generali, prima della consegna il reclamo spetta al **mittente** — il destinatario può presentarlo prima della ricezione solo con consenso scritto del mittente. Il termine è di **6 mesi** dalla spedizione (presso qualsiasi ufficio postale o online sul portale RQMS) con copia della ricevuta di spedizione. Bulgarian Posts risponde entro **1 mese** per gli invii nazionali e **3 mesi** per quelli internazionali, versando l’indennizzo approvato entro **1 mese** dalla comunicazione.

**Ricorso al regolatore.** Le controversie postali irrisolte possono essere portate davanti alla Commissione bulgara di regolazione delle comunicazioni ([reclami CRC / КРС](https://crc.bg/bg/rubriki/271/jalbi)), che risponde entro 30 giorni (fino a 2 mesi se servono ulteriori prove). Per approfondire blocchi o problemi consulta [perché il tracciamento non si aggiorna](guide:tracking-not-updating), [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Bulgarian Posts

Recapiti pubblicati su [bgpost.bg/en/contacts](https://www.bgpost.bg/en/contacts), [bgpost.bg/en/complaints](https://www.bgpost.bg/en/complaints) e [bgpost.bg/en/customs](https://www.bgpost.bg/en/customs):

- **Assistenza clienti generale:** `*7678` oppure `+359 2 962 50 50` (giorni lavorativi 08:00–17:30), e-mail `info@bgpost.bg`.
- **Corriere EMS / Bulpost:** `*6666` oppure `0700 19 666` (giorni lavorativi 07:00–19:00).
- **Servizio doganale (1900 Sofia BESC):** `customerservice@bgpost.bg` (lun–ven 08:00–16:00); invii EMS che iniziano per `E`: `emscustomerservice@bgpost.bg` (lun–ven 08:30–16:00); ufficio doganale Varna `psm.varna@bgpost.bg` / `052 604 143`.
- **Linee reclami (giorni lavorativi 09:00–17:30):** nazionale `+359 2 949 3326`, internazionale in arrivo `+359 2 949 3121`, internazionale in uscita `+359 2 949 3343`, supporto portale RQMS `02 949 31 90`.
- **Sede legale:** 1, Akademik Stefan Mladenov Str., Bl. 31, 1700 Sofia, Bulgaria.

> Diffida di SMS o e-mail indesiderati che chiedono di pagare con carta online per «programmare una consegna» Bulgarian Posts: gli invii sdoganati si pagano e si ritirano presso l’ufficio postale designato. Controlla il codice direttamente su [bgpost.bg](https://www.bgpost.bg/en/postal-services/track-package). Consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Traccia un pacco Bulgarian Posts con Peek

[Peek](/) identifica direttamente i codici `PS` di 13 caratteri (`PS48291736405`, cifre inventate) come Bulgarian Posts con alta affidabilità e riconosce i link incollati `bgpost.bg/postal-services/track-package?itemid=` (incluso `/en/`) e i vecchi link `IPSWeb_item_events.asp?itemid=`. Per i codici internazionali UPU S10 terminanti in `BG` (come `RR482917367BG` o `CP482917367BG`), Peek segue automaticamente Bulgarian Posts come poste di origine preferite; puoi selezionare Bulgarian Posts anche per codici S10 in arrivo dall’estero toccando «Cambia» in fase di aggiunta o «Cambia corriere» nella scheda del pacco (anche cercando «Български пощи», «Bulgaria Post» o «BG Post»). Nota che se un codice appartiene a Bulpost (`EMS/Bulpost`), Bulgarian Posts reindirizza alla pagina separata di Bulpost.

Peek interroga direttamente l’API pubblica di tracciamento di Bulgarian Posts e mostra la fase («Preso in carico», «In transito», «In dogana», «In consegna», «Tentativo di consegna», «Pronto per il ritiro», «Consegnato»), le righe IPS in inglese (oltre a quelle nazionali bulgare e alle note doganali), uffici e paesi (senza codici postali) e gli orari di consegna negli uffici bulgari. Le scansioni negli uffici bulgari sono datate sull’ora di Sofia e quelle di una posta estera sul fuso orario di quel luogo (ad esempio ora tedesca per Francoforte); Peek le mostra nell’orario del tuo dispositivo. Il tracker di Bulgarian Posts non fornisce data prevista di consegna, peso, nome del servizio, nome del mittente/destinatario né foto di prova di consegna.

Mentre tieni il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; nel giorno in cui Bulgarian Posts mostra `Handover item to delivery agent`, ogni 2 minuti dalle 6:00 ora bulgara, finché il tracker di Bulgarian Posts risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento di Bulgarian Posts non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata senza movimenti e fino a ogni 15 minuti nel giorno in cui il pacco è in consegna, mentre lo tieni aperto o hai le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora: «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Bulgarian Posts.», con il link «Chiedi aiuto a Bulgarian Posts» alla pagina contatti di Bulgarian Posts. Dopo tre settimane senza nuove scansioni mostra: «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Bulgarian Posts.»

Puoi scegliere le notifiche «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek monitora solo il tracciamento e non può inviare dichiarazioni doganali né presentare reclami a Bulgarian Posts al posto tuo.

## Domande sul tracciamento Bulgarian Posts

### Per quanto tempo Bulgarian Posts tiene un pacco in ufficio postale?

I pacchi standard e le raccomandate restano **20 giorni solari** dall’arrivo all’ufficio postale di consegna (con un secondo avviso dopo 10 giorni e spese di custodia sui pacchi dopo 9 giorni). Gli invii passati in dogana sono conservati **45 giorni solari** nell’ufficio postale designato.

### Cosa significa `Второ посещение` nel tracciamento Bulgarian Posts?

Indica che Bulgarian Posts ha emesso il secondo avviso scritto di ritiro dopo 10 giorni solari senza ritiro. Il pacco attende ancora in ufficio postale: Peek lo mantiene in «Pronto per il ritiro».

### Cosa significa `Return item from customs (Inb)`?

Significa che la dogana bulgara ha sdoganato l’invio e lo ha riconsegnato alla rete Bulgarian Posts per portarlo al tuo ufficio postale. Non significa che il pacco stia tornando al mittente.

### Quanto fa pagare Bulgarian Posts per lo sdoganamento?

Con il tariffario in vigore dal 2 novembre 2026, la tariffa di rappresentanza di Bulgarian Posts (IVA inclusa) è di 2,70 € per acquisti IOSS fino a 150 € (o regali C2C fino a 45 €), 3,80 € per acquisti senza IOSS fino a 150 € (o regali 45 €–1.000 €), 12,00 € per acquisti 150 €–1.000 € e 20,00 € (+ 5,00 € per ogni tipologia aggiuntiva) oltre 1.000 €, oltre al dazio UE applicabile (3 € fino a 150 €), alla commissione UE di 2 € e all’IVA.

:::sources
- [Bulgarian Posts: Home](https://www.bgpost.bg/en) – panoramica sull’operatore postale nazionale
- [Bulgarian Posts: Item Tracking](https://www.bgpost.bg/en/postal-services/track-package) – regole sui codici S10 e `PS` da 13 caratteri, ambito tracciamento in entrata/uscita
- [Bulgarian Posts: Customs](https://www.bgpost.bg/en/customs) – dazio da 3 €, commissione UHF da 2 €, tariffario rappresentanza doganale dal 2 nov 2026 (2,70 € / 3,80 € / 12 € / 20 €), ritiro esclusivo in ufficio e giacenza doganale di 45 giorni solari
- [Bulgarian Posts: Condizioni Generali (ОУ 2023 PDF)](https://www.bgpost.bg/cms/static/%D0%9E%D0%A3_2023.pdf) – punti 43, 45, 46 (documento/firma, giacenza 20 giorni solari, secondo avviso a 10 giorni, custodia dopo 9 giorni) e punti 56–59 (reclami)
- [Bulgarian Posts: General Terms Overview](https://www.bgpost.bg/en/about_us/conditions) – servizio postale universale e condizioni EMS/Bulpost
- [Bulgarian Posts: Claims and Inquiries](https://www.bgpost.bg/en/complaints) – regola reclamo del mittente, termine 6 mesi, risposta in 1/3 mesi, portale RQMS e numeri reclami
- [Bulgarian Posts: Contacts](https://www.bgpost.bg/en/contacts) – `*7678`, `+359 2 962 50 50`, `info@bgpost.bg`, EMS `*6666` / `0700 19 666`, sede a Sofia
- [Bulgarian Posts: FAQ](https://www.bgpost.bg/en/faq) – domande frequenti dei destinatari
- [Commissione Europea: Riforma Doganale UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio da 3 € e commissione UE da 2 €
- [KZP: Acquisti Online](https://kzp.bg/bg/potrebiteli/good-to-know/online-shopping) – consegna entro 30 giorni e recesso di 14 giorni
- [CRC (КРС): Reclami](https://crc.bg/bg/rubriki/271/jalbi) – reclami al regolatore postale e risposta entro 30 giorni
- [Universal Parcel Scraper: bulgarian-posts](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bulgarian-posts) – rilevamento `PS` e S10 `BG`, regole URL `bgpost.bg`, mappatura stati IPS inglese/bulgaro, reindirizzamento Bulpost, ora di Sofia e URL di aiuto
:::
