---
title: Tracciamento India Post e Speed Post: numeri, stati e dogana
description: Traccia una spedizione India Post o Speed Post: formato del codice in IN, significato degli stati, consegna con Poste Italiane o Posta Svizzera e dogana.
slug: tracciamento-india-post
published: 2026-11-18
updated: 2026-11-20
---

India Post è il Department of Posts, il servizio postale pubblico dell'**India**, che dichiara sul proprio sito 164.999 uffici postali: la rete postale più estesa al mondo, afferma. All'interno dell'India gestisce Speed Post, posta raccomandata e India Post Parcel; verso l'estero spedisce EMS (International Speed Post), piccoli pacchi tracciati (International Tracked Packet) e pacchi aerei. Puoi seguire una spedizione sul portale [indiapost.gov.in](https://www.indiapost.gov.in/), che richiede un CAPTCHA, oppure incollare il codice nel riquadro qui sopra. India Post lo chiama *article barcode number* (numero del codice a barre dell'invio); per un acquisto online, chiedilo al venditore.

## Numero di tracciamento India Post: formato ed esempi

Un codice India Post ha 13 caratteri nel [formato postale internazionale](guide:tracking-number-formats): due lettere, nove cifre e `IN` alla fine. L'ultima delle nove cifre è una cifra di controllo. Esempi con cifre inventate:

| Formato inventato | Cosa identifica |
| --- | --- |
| `EE482910576IN` | Un invio numerato da India Post, sia nazionale in India sia diretto all'estero. Le due lettere iniziali cambiano in base al servizio; a contare è `IN` in coda. |
| `EE482910576IT` | Termina con un'altra sigla nazionale, qui `IT`: il codice è stato emesso dalle poste di un altro Paese ([Poste Italiane](carrier:poste-italiane)). Un pacco spedito verso l'India dall'estero mantiene il numero della posta di partenza. |

La ricerca di India Post copre Speed Post, invii raccomandati e assicurati, Business Parcel ed Express Parcel, vaglia elettronici (*electronic money orders*) e International EMS. Non trovi il codice? Vedi [come trovare il numero di tracciamento](guide:find-tracking-number).

## Significato degli stati di tracciamento India Post

Le FAQ di India Post riassumono il percorso nelle fasi *booked*, *received*, *bagged*, *dispatched*, *out for delivery* e *delivered*. Ecco le diciture riconosciute da Peek nell'ordine abituale:

| India Post mostra | Cosa significa per il tuo invio |
| --- | --- |
| `Item Booked` | Il mittente ha consegnato l'invio allo sportello o lo ha prenotato online e India Post lo ha accettato. |
| `Bag Received for Forwarding` | Il dispaccio che contiene l'invio è arrivato in un ufficio per l'inoltro successivo. |
| `Assigned to load plan` | L'invio è inserito nel piano di carico del prossimo mezzo. |
| `Transport Leg Completed` | Una tratta del trasporto si è conclusa. |
| `Transfer to OOE (Otb)` | In trasferimento verso il centro scambi internazionali indiano (*Office of Exchange*) che spedisce all'estero. |
| `Aircraft Departure` | Il volo con la spedizione è decollato. A volte India Post usa la voce `UPLIFT`. |
| `Bag unloaded at destination Airport` | Il dispaccio è stato scaricato all'aeroporto del Paese di destinazione. |
| `Send item to domestic location (Inb)` | Il centro scambi del Paese di arrivo lo ha inoltrato nella rete postale interna. |
| `Item Delivered` | Consegnato. |

La sigla `(Otb)` indica una scansione della posta mittente in India, mentre `(Inb)` segnala un evento comunicato dalla posta del Paese di destinazione e ritrasmesso da India Post. Nei dati letti da Peek, India Post invia ormai spesso codici sintetici come `BAG_DISPATCH`: Peek espande quelli noti — come `Bag Dispatched` o `Item Presented To Customs` — e trasforma gli altri codici in parole leggibili. Per approfondire le fasi generali, leggi [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Ricevere una spedizione India Post in India

Le regole seguenti valgono per il recapito all'interno dell'**India**:

**Tempi di consegna.** Per Speed Post in India, gli standard pubblicati da India Post dall'accettazione alla consegna sono di 1-2 giorni in ambito locale, 1-3 giorni tra aree metropolitane, 1-4 giorni tra capitali di Stato o dentro lo stesso Stato e 4-5 giorni nel resto del Paese, più un giorno se recapita un ufficio succursale (*branch office*). Il servizio 24 Speed Post consegna il giorno successivo in sei metropoli indiane (New Delhi, Mumbai, Bengaluru, Chennai, Kolkata e Hyderabad), sette giorni su sette; in caso di ritardo il mittente può chiedere online il rimborso della tariffa entro 7 giorni.

**SMS e codice OTP.** Per un pacco Speed Post in India, il cellulare registrato riceve un SMS quando il plico raggiunge l'ufficio di recapito e un secondo alla consegna. Alcuni invii Speed Post vengono consegnati solo dietro codice monouso (OTP): è incluso di serie in 24 Speed Post ed è un supplemento a pagamento sugli altri Speed Post.

**Mancata consegna.** Il tracciamento di India Post segnala l'emissione di un avviso (*Delivery Intimation Notice*) quando la spedizione è disponibile per il recapito. Consulta anche [consegna mancata](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

**Modifica dell'indirizzo.** In India, chi ha prenotato la spedizione online con un account India Post può richiamarla o reindirizzarla finché non è stata consegnata né affidata al portalettere per il giro.

## Pacchi India Post verso l'estero: chi consegna e dogana

Verso l'estero India Post spedisce EMS (International Speed Post, fino a 35 kg), International Tracked Packet per chi vende online (fino a 5 kg) e International Air Parcel (fino a 20 kg), tutti tracciati. Dopo l'atterraggio, le scansioni contrassegnate `(Inb)` provengono dall'operatore postale locale: puoi seguirle con lo stesso codice in `IN` su [Poste Italiane](carrier:poste-italiane) in **Italia**, [La Posta Svizzera](carrier:swiss-post) in **Svizzera**, [USPS](carrier:usps) negli **Stati Uniti**, [Royal Mail](carrier:royal-mail) (Parcelforce per EMS) nel **Regno Unito**, Canada Post in **Canada** o Australia Post in **Australia**.

**Dogana nell'Unione europea (Italia).** Poiché l'India è fuori dall'Unione europea, ogni pacco passa dalla dogana all'arrivo. Nell'**Unione europea**, dal 1° luglio 2026 i beni acquistati online fuori dall'UE in spedizioni di valore fino a 150 € scontano un dazio doganale UE di 3 € per articolo (*per item*); dal 1° novembre 2026 si aggiunge anche una commissione di gestione dell'Unione di 2 € per articolo, indipendentemente dal valore, oltre all'IVA all'importazione nazionale e, sopra i 150 €, ai dazi tariffari ordinari. Costituiscono un singolo articolo le merci di uno stesso pacco che condividono classificazione tariffaria, descrizione e, ove richiesta, origine. India Post elenca il servizio Delivery Duty Paid (DDP) tra le opzioni per gli esportatori: chiedi al venditore se lo ha utilizzato.

**Dogana in Svizzera.** In **Svizzera**, secondo l'Ufficio federale della dogana e della sicurezza dei confini (UDSC), l'IVA sull'importazione è pari all'8,1 % della base di calcolo (o al 2,6 % ad aliquota ridotta, ad esempio per libri e generi alimentari). Per motivi economico-amministrativi, precisa l'UDSC, «i tributi doganali fino a 5 franchi non vengono riscossi» e «gli importi d'imposta fino a 5 franchi non vengono riscossi» (importo che corrisponde a una base di calcolo di 62 franchi con aliquota dell'8,1 % oppure di 193 franchi con aliquota del 2,6 %), ferme restando le eventuali spese di sdoganamento applicate dal trasportatore. Per i dettagli generali vedi [pacchi fermi in dogana](guide:customs).

**Sospensioni del servizio.** Quando India Post sospende o riattiva il servizio verso un Paese — come la sospensione per il Canada nell'ottobre 2025 o la ripresa per Iraq, Kuwait e Libano nell'agosto 2026 — pubblica un avviso nella pagina [News and Updates](https://www.indiapost.gov.in/media/newsandupdates).

**Spedizioni dirette in India.** Un pacco spedito dall'estero verso l'India viaggia con il codice della posta d'origine. Controllalo prima sul sito di quest'ultima: le FAQ di India Post avvertono che i dati trasmessi dalle amministrazioni postali estere possono arrivare in ritardo.

## Tracciamento India Post fermo, pacco smarrito o restituito

**Nessun aggiornamento da giorni.** India Post precisa che l'assenza di una scansione non significa che il plico non sia partito: la comparsa online può richiedere tempo, specie dagli uffici rurali in India o dalle poste estere. Quando una spedizione internazionale mostra `Aircraft Departure`, ha lasciato l'India: verifica lo stesso codice sul sito della posta del tuo Paese. Altri dettagli in [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Ritardo, smarrimento o danneggiamento (regole di India Post).** Presso India Post si può presentare reclamo una volta trascorso lo standard di resa ed entro 60 giorni dall'operazione, citando il codice dell'invio; il tempo standard di lavorazione indicato da India Post è di 90 giorni. Per Speed Post nazionale in India è previsto il rimborso delle spese di spedizione in caso di ritardo e il doppio della tariffa o 1.000 ₹ (l'importo minore) per smarrimento, manomissione o danno. Per le merci spedite all'estero con EMS la copertura arriva al valore dichiarato o a 130 DSP (diritti speciali di prelievo), il minore dei due, più l'affrancatura, mentre un ritardo superiore a 5 giorni oltre lo standard pubblicato dà diritto al 5 % della tariffa; un International Tracked Packet è coperto fino al valore o a 1.000 ₹, quale sia l'importo inferiore. Se hai acquistato online, contatta anche il venditore: leggi [pacchi smarriti](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Segnato consegnato ma non ricevuto.** Presenta reclamo attraverso i canali qui sotto oppure, in India, sul registro dei reclami presente in ogni ufficio postale: vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Reso al mittente.** Durante la prenotazione online su India Post, il mittente può indicare un indirizzo di reso diverso dal proprio. Peek mostra i resi sotto «Restituito al mittente»: consulta [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti India Post e reclami

Canali ufficiali di India Post:

- **Numero verde (dall'India):** `1800 266 6868`. Gli operatori rispondono dalle 8:00 alle 20:00 ora indiana (IST); il risponditore automatico (IVRS) è attivo 24/7, domeniche comprese, tranne nelle festività nazionali ufficiali (*gazetted holidays*) indiane.
- **Reclami:** online tramite la pagina [File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) di India Post (dove puoi anche seguire lo stato della pratica), presso un ufficio postale in India (rivolgendoti al *postmaster* o al *Superintendent of Post Offices*) oppure tramite call center. Non serve registrare un account.
- **Secondo livello in India:** se India Post non chiude il reclamo entro tempi ragionevoli, il Directorate of Public Grievances del governo indiano (`dpg.gov.in`).

Sulla home page India Post mette in guardia dai link falsi che ne imitano il nome: usa soltanto `www.indiapost.gov.in` e non comunicare mai codici OTP né dati bancari, di carta o UPI tramite link non autorizzati («do not share OTP, banking, card, UPI or other personal/financial information through unauthorized links»). Vedi anche [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco India Post con Peek

[Peek](/) riconosce due lettere, nove cifre e `IN` come India Post quando la cifra di controllo è valida, mostrando «Corriere rilevato»; «Cambia» permette di sceglierne un altro. Con una cifra di controllo errata (ad esempio `EE482910577IN`), avvisa «La cifra di controllo di questo numero non torna.»: controlla il numero fornito dal mittente oppure scegli «Traccia così com’è». Salvato sotto India Post, un invio mostra «In attesa di India Post» fino alla prima scansione.

Peek non interroga il sito di India Post direttamente: legge le scansioni tramite MySpeedPost, un servizio esterno al dominio di India Post che le recupera da India Post (oppure tramite track91).

Peek riporta ogni evento con la formulazione di India Post insieme all'ufficio, collocando l'ufficio sulla mappa quando la località è verificata. Un decollo compare come `Aircraft Departure`. Gli eventi vengono classificati nelle fasi di Peek: un tentativo non riuscito passa a «Tentativo di consegna», mentre rifiuto, danneggiamento, smarrimento o indirizzo insufficiente passano a «Problema segnalato». Tramite track91 le voci sono più sintetiche: `Dispatched` (spedito all'estero dal centro scambi) e `Aircraft arrival in Transit country` (scalo in un Paese di transito) appaiono come «In transito», e `Delivered` come «Consegnato». Peek scarta nome, indirizzo e firma del destinatario e non mostra una data di consegna stimata. Legge le scansioni indiane sull'ora dell'India (UTC+5:30), mostrando ogni orario nel fuso del tuo dispositivo; su peektracker.com e nell'app per iPhone una riga sotto la cronologia indica quel fuso («Orari nel tuo fuso orario: …»).

Quando India Post indica il Paese di destinazione — o non lo indica e cerchi il pacco da fuori dell'India — Peek interroga con lo stesso codice `IN` anche l'operatore postale che conosce lì, come [Poste Italiane](carrier:poste-italiane) in Italia, [La Posta Svizzera](carrier:swiss-post) in Svizzera, [DHL](carrier:dhl) in Germania, [La Poste](carrier:la-poste) in Francia, USPS, Royal Mail (o Parcelforce per EMS), Canada Post o Australia Post. Non appena quell'operatore restituisce scansioni aggiornate, Peek passa a seguirlo conservando la cronologia di India Post. Peek copre così gran parte d'Europa e diversi altri Paesi, tra cui NZ Post in Nuova Zelanda e Qatar Post in Qatar, ma non Emirati Arabi Uniti, Kuwait, Oman, Singapore, Malaysia o Sri Lanka. Non interroga invece India Post per i codici emessi da altre poste: selezionando manualmente India Post compare «non emette numeri come questo».

Mentre tieni aperta la scheda del pacco oppure, nel giorno della consegna, con le notifiche attive, Peek controlla gli aggiornamenti fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui il pacco passa «In consegna», finché risponde MySpeedPost o il tracker diretto della tua posta (dopo il passaggio, Peek segue il ritmo della posta di destinazione, come [Poste Italiane](carrier:poste-italiane); tramite altri servizi di tracciamento da soli, ogni ora, ogni 3 ore dopo un giorno senza novità e fino a ogni 15 minuti il giorno della consegna a pacco aperto o con notifiche attive). Negli altri casi, e dopo due giorni senza aggiornamenti (o 12 ore su «Etichetta creata» o «Preso in carico»), Peek controlla ogni ora. Notifiche disponibili: «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Se il pacco si blocca mentre è in mano a India Post, «Chiedi aiuto a India Post» apre la pagina Contact Us di India Post. Peek non può contattare per te India Post, la tua posta o il negozio.

## Domande frequenti sul tracciamento India Post

### Come faccio a tracciare una spedizione Speed Post di India Post?

Inserisci il codice di 13 caratteri che termina in `IN` su indiapost.gov.in oppure incollalo in Peek, che continua a seguirlo fino alla consegna anche dopo la presa in carico da parte della posta del tuo Paese.

### Perché il tracciamento India Post non si aggiorna?

Le scansioni possono impiegare tempo per comparire online, soprattutto dagli uffici rurali indiani o dalle poste estere. Se per un pacco diretto all'estero l'ultima voce è `Aircraft Departure`, la spedizione ha lasciato l'India: controlla lo stesso numero sul sito di Poste Italiane o della posta del tuo Paese.

### Quanto impiega Speed Post a consegnare?

In India lo standard di India Post è di 1-3 giorni tra metropoli e di 4-5 giorni per il resto del Paese, più un giorno per gli uffici succursali; 24 Speed Post consegna il giorno dopo in sei metropoli indiane. Per le spedizioni internazionali i tempi dipendono dal servizio scelto e dallo sdoganamento nel Paese di arrivo.

### Qual è il numero dell'assistenza clienti India Post?

Il numero verde `1800 266 6868` (dall'India): operatori dalle 8:00 alle 20:00 ora indiana e linea automatica attiva 24 ore su 24 esclusi i festivi ufficiali indiani. Dall'estero puoi inviare e seguire un reclamo online su indiapost.gov.in senza bisogno di registrarti.

:::sources
- [India Post: Home](https://www.indiapost.gov.in/) – ricerca spedizioni, numero verde e avviso antifrode
- [India Post: Contact Us](https://www.indiapost.gov.in/help&Support/contactus) – numero gratuito, orari e reclami presso l'ufficio postale
- [India Post: Help and FAQs](https://www.indiapost.gov.in/grievance-redressal/help-faq) – codice a barre, invii tracciabili, stati, ritardi, reclami, richiamo, reindirizzamento e indirizzo di reso
- [India Post: About Us](https://www.indiapost.gov.in/ministry/aboutus) – Department of Posts e 164.999 uffici postali
- [India Post: Speed Post](https://www.indiapost.gov.in/mail&Parcel/mailproducts/premiumservices/speedpost) – OTP, standard di resa, SMS per Speed Post Parcel e indennizzi
- [India Post: 24/48 Speed Post FAQs](https://www.indiapost.gov.in/speedpost/faqs) – consegna il giorno successivo in sei metropoli, OTP e rimborsi
- [India Post: India Post Parcel](https://www.indiapost.gov.in/mail&Parcel/mailproducts/domesticservices/indiapostparcel) – India Post Parcel
- [India Post: International Mail](https://www.indiapost.gov.in/mail&Parcel/mailproducts/mailservices/international) – servizi internazionali e opzione DDP per gli esportatori
- [India Post: International services](https://www.indiapost.gov.in/mail&Parcel/mailproducts/internationalservices) – EMS, International Tracked Packet, pacchi aerei e massimali di rimborso
- [India Post: News and Updates](https://www.indiapost.gov.in/media/newsandupdates) – avvisi di sospensione e riattivazione per Paese
- [India Post: File a Complaint](https://www.indiapost.gov.in/grievance-redressal/file-a-complaint) – reclami online ed escalation al DPG
- [India Post: Guidelines on Complaint Registration](https://www.indiapost.gov.in/grievance-redressal/guidelines) – termini e canali per sporgere reclamo
- [Commissione europea: Riforma doganale dell'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – dazio di 3 € e commissione di gestione di 2 €
- [Commissione europea: Orientamenti sul dazio doganale di 3 € e sulla commissione di gestione dell'Unione (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – applicazione per articolo e definizione di articolo
- [UDSC: Ricevere lettere e pacchi](https://www.bazg.admin.ch/it/ricevere-lettere-e-pacchi) – IVA svizzera 8,1 % / 2,6 % e mancata riscossione di tributi e imposte fino a 5 franchi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – diciture di scansione (incluso track91), fusi orari e passaggio agli operatori postali di destinazione
:::
