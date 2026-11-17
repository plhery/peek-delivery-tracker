---
title: Tracciamento DPD Germania: numero, stati e consegna
description: Segui un pacco DPD Germania verso l’Italia, la Svizzera o in Germania: numero a 14 cifre, significato degli stati inglesi, Predict, punti di ritiro e contatti.
slug: tracciamento-dpd-germania
published: 2026-11-17
updated: 2026-11-17
---

DPD in Germania è gestita da DPD Deutschland GmbH (Aschaffenburg), società del gruppo Geopost con 10.000 Paketshop in territorio tedesco. Se attendi un ordine spedito dalla Germania verso l’Italia, la Svizzera italiana o un indirizzo in Germania, puoi tracciarlo con il numero a 14 cifre sul sito DPD o nel campo qui sopra.

Finché viaggia in Germania è seguito da DPD Deutschland.

## Numero di pacco DPD Germania: formato e dove trovarlo

Secondo DPD Deutschland il numero si trova nell’e-mail di spedizione del negozio, nell’avviso e-mail di DPD o — secondo la FAQ inglese — nell’SMS di DPD (gli SMS autentici di DPD Germania arrivano solo dal `+49 177 1787807`). Sull’avviso cartaceo in Germania (*Paketinfokarte*), il numero sotto il codice QR è il numero di pacco.

| Esempio (inventato) | Che cos’è |
| --- | --- |
| `01482910573649` | Numero di pacco a 14 cifre usato dal tracciamento DPD. |
| `014829105736492` | Stesso codice seguito dal 15° carattere di controllo (cifra o lettera maiuscola). |

DPD non illustra il 15° carattere: secondo il codice di Peek è un carattere di controllo stampato su etichette e avvisi dopo le 14 cifre. Per tracciare bastano le 14 cifre. Un riferimento d’ordine non consente la ricerca: DPD richiede sempre il numero di pacco (*Paketnummer*). Vedi anche [come trovare il numero di tracciamento](guide:find-tracking-number).

Codici a 14 cifre sono usati tra gli altri anche da [BRT](carrier:brt), [DPD Svizzera](carrier:dpd-ch), [Chronopost](carrier:chronopost), [SEUR](carrier:seur), DPD France, DPD UK e Hermes: controlla quale corriere indica l’e-mail del venditore.

## Stati del tracciamento DPD Germania

Peek interroga DPD Germania in inglese e mostra le scansioni così come le restituisce DPD, nell’ordine tipico del percorso:

| Messaggio di DPD | Cosa significa |
| --- | --- |
| `Order information has been transmitted to DPD.` | Preavviso: dati inviati dal mittente, pacco non ancora consegnato a DPD (`Auftragsdaten übermittelt` in tedesco). |
| `Parcelinfo handed to DPD` | Dati ricevuti senza il collo; Peek mostra «Etichetta creata» (solo tramite servizi di tracciamento esterni). |
| `Parcel handed to DPD` | Pacco preso in carico da DPD. |
| `Parcel handed to Pickup parcelshop by consignor.` | Affidato dal mittente (*consignor*) a un Pickup Paketshop. |
| `In transit.`, `Your parcel is on its way` | In transito nella rete DPD. |
| `At parcel delivery centre.` | In un centro di distribuzione o deposito DPD (*Paketzustellzentrum*). |
| `Out for delivery.` | Sul furgone per la consegna odierna. |
| `Unsuccessful delivery attempt` | Tentativo non riuscito; seguirà nuova uscita o punto Pickup. |
| `Back at parcel delivery centre after an unsuccessful delivery attempt.` | Rientrato in deposito dopo il tentativo fallito. |
| `Delivered by driver to DPD Pickup parcelshop/ station.` | Depositato in negozio Pickup o Pickup Station («Pronto per il ritiro»). |
| `Picked up from Pickup parcelshop by consignee.` | Ritirato al punto Pickup dal destinatario (*consignee*, tu): consegnato. |
| `Delivered.`, `Parcel has been left in: mail box` | Consegnato; la seconda riga indica dove è stato lasciato (cassetta postale). |
| `At parcel delivery centre. (Return to sender)` | In rientro al mittente. |

Occhio a quattro diciture: `Pickup ordered for: …` riguarda il ritiro presso il mittente, e `Goods issue at sender.` l’uscita dal magazzino del venditore prima della presa in carico. `We're sorry but your parcel couldn't be delivered as arranged.` contiene *delivered* ma segnala una mancata consegna. `Delivered. (Return to sender)` conferma il reso riconsegnato al mittente.

Sul sito tedesco, proseguire senza CAP mostra solo uno stato generico; se `Im Paketzustellzentrum` resta invariato per più giorni lavorativi, chiedi al mittente una ricerca ufficiale (*Nachforschung*).

## Consegna, fascia Predict e punti di ritiro

**In Germania.** DPD indica di norma 1–2 giorni lavorativi dalla presa in carico (senza termine garantito nelle condizioni DPD CLASSIC). Se il venditore ha comunicato e-mail o cellulare, Predict invia il giorno della consegna una finestra di 2 ore, ridotta fino a 30 minuti nel live tracking (escluse le isole). Dall’app DPD, myDPD o dal tracciamento con CAP puoi deviare a un vicino immediato, a un Paketshop, in luogo sicuro (*Abstell-Okay*, con foto obbligatoria e passaggio a tuo carico del rischio di perdita o danno) o a un altro giorno; conta l’ultima modifica prima della messa in consegna. Il secondo tentativo avviene in giornata o il giorno lavorativo seguente, ma le condizioni generali permettono già dopo il primo tentativo la consegna a un vicino entro 50 m o al punto Pickup più vicino (salvo opposizione). Per il ritiro in Germania basta un documento ufficiale con foto (o [delega firmata](https://www.dpd.com/de/de/support/vollmacht/) e documento del delegato): la FAQ indica 7 giorni di calendario nei Paketshop e 3 nelle Paketstation (7 giorni lavorativi nelle condizioni generali dopo mancata consegna). Consegna di sabato solo con l’espresso DPD 12 Uhr (isole escluse), e nessun ritiro privato in deposito.

**Verso l’Italia e la Svizzera:**

- **Italia ([BRT](carrier:brt)):** se il collo passa a BRT, con il BRTcode o il numero di spedizione lo segui anche su brt.it; la rete [BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) conta oltre 11.600 punti di ritiro in Italia con 10 giorni di custodia.
- **Svizzera ([DPD Svizzera](carrier:dpd-ch)):** ritiro presso un [DPD Pickup Location](https://www.dpd.com/ch/it/dpd-pickup-location/) con documento ufficiale con foto (o procura e documento di chi ritira).

**Resi.** Con etichetta di reso del negozio, consegna gratuita in Germania in Paketshop, Pickup Station o all’autista DPD (anche con QR mobile in app DPD o myDPD); vedi [tracciamento dei resi](guide:returns-tracking).

## Dogana: UE, Svizzera e Paesi extra-UE

Tra Germania (esclusi Helgoland e Büsingen) e Italia (esclusi Livigno e Campione d’Italia) non ci sono dazi né dogana nell’UE. Si passa invece in dogana per:

- **Svizzera:** secondo l’UFDSC possono applicarsi IVA sull’importazione, eventuali dazi e spese di sdoganamento del corriere; per spese e stato del pacco l’UFDSC rimanda al trasportatore. Le e-mail ufficiali di fatturazione e importazione di DPD Svizzera provengono da `no-replyinvoicing@dpd.ch`, `import@dpd.ch`, `export@dpd.ch` o `faktura@dpd.ch`.
- **Importazioni extra-UE:** DPD Deutschland ricorda che dazi, IVA all’importazione e commissione amministrativa restano a carico del destinatario se non assolti dal mittente. Nell’UE dal 1° luglio 2026 il dazio temporaneo di 3 € per articolo ha sostituito la franchigia sotto 150 €, affiancato dal 1° novembre 2026 da 2 € di commissione di gestione dell’Unione per articolo distinto, riaddebitabili da venditore o corriere. Vedi [pacco fermo in dogana](guide:customs).

## Pacco bloccato, segnato consegnato o danneggiato

Il contratto lega DPD al mittente: secondo DPD Deutschland toccano al venditore rimborsi, sostituzioni e ricerche ufficiali, e secondo Your Europe nell’UE il venditore risponde del bene fino alla consegna.

**Segnato consegnato ma assente.** Controlla nel tracciamento vicino o punto Pickup, poi ingresso, garage, cassetta, bidoni e porta sul retro. Avvisa il negozio e, se serve, usa il modulo DPD; vedi [consegnato ma non ricevuto](guide:delivered-not-received) e [tracciamento fermo](guide:tracking-not-updating).

**Danni e diritto tedesco.** Fotografa scatola e merce e avvisa subito il venditore. Nel diritto tedesco: copertura automatica fino a 520 € per collo (a favore del mittente secondo le condizioni generali, entro il prezzo d’acquisto provato), diritti propri del destinatario (`§ 421 HGB`), e termini di reclamo al vettore (`§ 438 HGB`): alla consegna per danni visibili, 7 giorni in forma testuale per danni occulti, 21 giorni per ritardo. Per liti non risolte su perdita o danno l’*Impressum* indica la conciliazione della Bundesnetzagentur (*Schlichtungsstelle Post*).

## Contatti DPD Germania e assistenza locale

- **DPD Germania:** `06021-150415` (`+49 6021 150 415` dall’estero), `info@dpd.de`, menu «Altro / Chat / Aiuto / Contatto» nell’app DPD o [modulo DPD Germania](https://www.dpd.com/de/de/support/kontakt/) (`06021 8430` nell’Impressum è il centralino della sede).
- **Italia ([BRT](carrier:brt)):** assistenza su [services.brt.it](https://services.brt.it/en/support) e [FAQ BRT](https://www.brt.it/en/customer-service/faq/).
- **Svizzera ([DPD Svizzera](carrier:dpd-ch)):** `058 311 13 13` (lun.–ven. 8:00–18:00, max. 0,08 CHF/min) o [supporto DPD Svizzera](https://www.dpd.com/ch/it/centro-supporto/la-richiesta/).

> DPD Deutschland avvisa che i suoi SMS partono solo dal `+49 177 1787807` e le e-mail da `@dpd.de` o domini ufficiali DPD (mai con allegati `.doc`). In caso di richieste di pagamento dubbie verifica il codice su dpd.com/de/de/ o scrivi a `info@dpd.de`.

## Seguire un pacco DPD Germania con Peek

[Peek](/) accetta le 14 cifre con o senza controllo e i link `tracking.dpd.de`, `my.dpd.de` e `dpd.com/de`. Il prefisso `01` (salvo `0100`) mette DPD Germany in prima ondata; in Italia altri prefissi interrogano prima [BRT](carrier:brt), Hermes, DPD Belgium, [DPD Svizzera](carrier:dpd-ch) e DPD Paesi Bassi e subito dopo DPD Germany, mentre `10` e `1550` puntano di base a DPD France o DPD UK. In automatico DPD Germany risponde («DPD Germany ha questo pacco.») solo finché DPD colloca il pacco in Germania; altrimenti tocca «Cambia» (o «Cambia corriere») e scegli DPD Germany sotto «Compatibili con questo numero». Una volta scelto, quando il pacco passa in un altro Paese, Peek lo mantiene sotto DPD Germany leggendo il tracciamento di gruppo di DPD e, se possibile, quello della società DPD del Paese.

Il campo «Codice postale di consegna» è facoltativo e accetta solo CAP tedeschi di 5 cifre (lascialo vuoto fuori dalla Germania). Senza CAP Peek legge l’app tedesca DPD: scansioni con luogo su mappa, peso, dimensioni, data o fascia stimata e, in giacenza Pickup, nome e indirizzo del punto. Con un CAP tedesco valido di 5 cifre legge il tracciamento verificato: scansioni verificate, nome del mittente e fascia oraria (senza dimensioni né indirizzo Pickup). Se DPD rifiuta il CAP, Peek mostra «DPD Germany non ha accettato il codice postale …, quindi mostra meno dettagli.». Prova di consegna scartata, nome del ricevente non mappato sostituito con `Delivery update` e orari tedeschi convertiti («Orari nel tuo fuso orario: …»).

Peek aggiorna lo stato fino a ogni 10 minuti mentre hai il pacco aperto sullo schermo, o nel giorno della consegna con le notifiche attive, e ogni ora negli altri casi (anche dopo due giorni senza novità o dopo 12 ore su «Etichetta creata» o «Preso in carico»). In «In consegna» controlla fino a ogni 2 minuti dalle 6:00 locali — o da 30 minuti prima della fascia DPD — finché DPD risponde direttamente. Tramite i soli servizi di tracciamento esterni controlla ogni ora, ogni 3 ore dopo un giorno senza scansioni e fino a ogni 15 minuti nel giorno della consegna mentre hai il pacco aperto o le notifiche attive («Ogni scansione», «Passaggi importanti» o «Solo la consegna»).

## Domande frequenti sul tracciamento DPD Germania

### Perché il numero DPD Germania non viene riconosciuto?

La pagina di errore myDPD indica tre motivi: errore di digitazione (`O` invece di `0` secondo la FAQ), codice archiviato oltre 180 giorni, o pacco non ancora consegnato a DPD.

### Quanto tempo impiega DPD Germania per consegnare?

In Germania di norma 1–2 giorni lavorativi dall’affidamento (non garantiti nel servizio standard), con finestra Predict di 2 ore il giorno della consegna se il venditore ha trasmesso i tuoi recapiti.

### DPD Germania consegna anche il sabato?

In Germania solo come supplemento abbinato all’espresso DPD 12 Uhr (isole escluse, affidamento il venerdì).

### Posso ritirare il pacco direttamente nel deposito DPD in Germania?

No: il ritiro privato nei depositi non è consentito. Devia invece il collo a un punto cercandolo nel [trova Paketshop di DPD](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/).

:::sources
- [DPD: Startseite](https://www.dpd.com/de/de/) – 10.000 Paketshop
- [DPD: Paketnummer finden](https://www.dpd.com/de/de/faq/wo-finde-ich-meine-paketnummer/) – e-mail, avviso
- [DPD: Parcel number](https://www.dpd.com/de/en/faq/where-do-i-find-my-parcel-number/) – SMS
- [DPD: Nummer unter QR-Code](https://www.dpd.com/de/de/faq/was-bedeutet-die-nummer-unter-dem-qr-code/) – Paketinfokarte
- [DPD: Paketnummer nicht erkannt](https://www.dpd.com/de/de/faq/meine-paketnummer-wird-nicht-erkannt-was-kann-ich-tun/) – 0 e O
- [DPD: Referenznummer](https://www.dpd.com/de/de/faq/was-ist-eine-referenznummer/) – riferimento
- [myDPD: Postleitzahl](https://my.dpd.de/myparcels/dataprotection-tracking.aspx) – 180 giorni
- [DPD: Sendungsverfolgung](https://www.dpd.com/de/de/support/empfaenger/sendungsverfolgung/) – 14 cifre, tempi
- [DPD: PLZ beim Tracking](https://www.dpd.com/de/de/faq/warum-muss-ich-beim-tracking-meine-plz-eingeben/) – senza CAP
- [DPD: Auftragsdaten übermittelt](https://www.dpd.com/de/de/faq/was-heisst-auftragsdaten-uebermittelt/) – preavviso
- [DPD: Predict](https://www.dpd.com/de/de/faq/was-ist-predict/) – finestra
- [DPD: Paket empfangen](https://www.dpd.com/de/de/empfangen/) – isole, deviazioni
- [DPD: Mehrmals umleiten](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-mehrmals-umleiten/) – ultima scelta
- [DPD: Abstellbedingungen](https://www.dpd.com/wp-content/uploads/sites/59/2026/09/20260923_Abstellbedingungen_DE_final.pdf) – foto, rischio
- [DPD: Zweiter Zustellversuch](https://www.dpd.com/de/de/faq/das-paket-konnte-nicht-zugestellt-werden-gibt-es-einen-zweiten-zustellversuch/) – 2° tentativo
- [DPD: Paket abholen](https://www.dpd.com/de/de/support/empfaenger/paket-abholen/) – 7 e 3 giorni
- [DPD: Ohne Paketinfokarte](https://www.dpd.com/de/de/faq/kann-ich-mein-paket-ohne-paketinfokarte-abholen/) – documento
- [DPD: Vollmacht](https://www.dpd.com/de/de/support/vollmacht/) – delega
- [DPD: Parcel return](https://www.dpd.com/de/en/support/recipients/parcel-return/) – resi
- [DPD: AGB DPD CLASSIC](https://www.dpd.com/wp-content/uploads/sites/59/2026/07/AGB-DPD-CLASSIC_06-2026_DE_END.pdf) – 50 m, 7 gg lav.
- [DPD: Zusatzleistungen](https://www.dpd.com/wp-content/uploads/sites/59/2025/01/Beforderungsbedingungen-Zusatzleistungen-01-2025_DE_END.pdf) – sabato
- [DPD: Delivery center](https://www.dpd.com/de/en/faq/can-i-pick-up-my-parcel-myself-at-the-dpd-delivery-center/) – deposito
- [DPD: Paketshop finden](https://www.dpd.com/de/de/pickup-paketshops/paketshop-finden/) – orari
- [DPD: DPD oder Versender](https://www.dpd.com/de/de/faq/wann-wende-ich-mich-an-dpd-und-wann-an-den-versender/) – venditore
- [DPD: Zugestellt, nicht erhalten](https://www.dpd.com/de/de/faq/mein-paket-wurde-laut-tracking-zugestellt-aber-ich-habe-es-nicht-erhalten-was-kann-ich-tun/) – verifiche
- [DPD: Delivered, not here](https://www.dpd.com/de/en/faq/my-parcel-was-delivered-but-it-is-not-here-where-is-it/) – +49 6021 150 415
- [DPD: Paket beschädigt](https://www.dpd.com/de/de/faq/mein-paket-ist-beschaedigt-angekommen-was-kann-ich-tun/) – danni
- [DPD: Versicherung](https://www.dpd.com/de/de/faq/wie-hoch-ist-mein-paket-versichert/) – 520 €
- [HGB § 421](https://www.gesetze-im-internet.de/hgb/__421.html) – destinatario
- [HGB § 438](https://www.gesetze-im-internet.de/hgb/__438.html) – termini
- [DPD: Impressum](https://www.dpd.com/de/de/impressum/) – sede, BNetzA
- [DPD: Zustellung und Zoll](https://www.dpd.com/de/de/support/empfaenger/zustellung-und-zoll/) – dogana
- [DPD: Kontakt](https://www.dpd.com/de/de/support/kontakt/) – telefono, e-mail
- [DPD: App-Support](https://www.dpd.com/de/de/faq/wie-kontaktiere-ich-den-support-ueber-die-app/) – app
- [DPD: Sicherheit](https://www.dpd.com/de/de/sicherheit/) – SMS, .doc
- [DPD: Phishing-Warnung](https://www.dpd.com/de/de/news/warnung-vor-phishing-mails-und-sms-im-namen-von-dpd/) – @dpd.de
- [Your Europe: Consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – regole UE
- [BRT: BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) – 11.600 punti, 10 gg
- [BRT: FAQ](https://www.brt.it/en/customer-service/faq/) – brt.it, BRTcode
- [BRT: Support](https://services.brt.it/en/support) – assistenza
- [DPD Svizzera: Pickup](https://www.dpd.com/ch/it/dpd-pickup-location/) – ritiro
- [DPD Svizzera: Contatti](https://www.dpd.com/ch/it/centro-supporto/la-richiesta/) – 058 311 13 13
- [DPD Svizzera: Sicurezza](https://www.dpd.com/ch/it/sicurezza-al-dpd/) – @dpd.ch
- [UFDSC: Ricevere pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – import CH
- [UFDSC: Costi e tracciamento](https://www.bazg.admin.ch/it/costi-imposizione-e-tracciamento-pacchi) – corriere
- [UE: Territorial Scope](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – territori esclusi
- [UE: Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € e 2 €
- [Revenue: Handling Fee](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – 2 € per articolo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – tracciamento Peek
:::
