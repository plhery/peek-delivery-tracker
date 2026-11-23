---
title: Tracking GLS US (GSO): numeri, stati e consegne
description: Traccia un pacco GLS US (anche GSO): codice SH, significato di ogni scansione, giorni di consegna, firme, assenze e contatti.
slug: tracking-gls-us
published: 2026-11-23
updated: 2026-11-23
---

GLS US (General Logistics Systems US, Inc.) è la società pacchi di GLS Group negli Stati Uniti, attiva nell'Ovest statunitense. Il catalogo di Peek la conosce anche come GLS USA, GLS United States, General Logistics Systems US, GSO e Golden State Overnight, la società acquisita da GLS nel 2016. Per tracciare un pacco GLS US, incolla il numero nel box qui sopra oppure inseriscilo sulla [pagina di tracking statunitense di GLS](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Numero di tracking GLS US: com'è fatto

Il catalogo di Peek conosce un formato per GLS US (cifre inventate):

| Cosa incolli (inventato) | Cosa fa Peek |
| --- | --- |
| `SH605182704936815247` | SH più 18 cifre, 20 caratteri in totale. Nel catalogo solo GLS US usa questa forma, ma la corrispondenza è debole, quindi Peek interroga GLS US prima di indicarlo. |
| `sh 6051 8270 4936 8152 47` | Lo stesso numero. Minuscole, spazi e trattini non contano. |
| Un numero GLS US di altro formato | Peek non lo riconosce in automatico. Scegli GLS US manualmente (vedi «Tracciare un pacco GLS US con Peek» sotto). |
| Un link di tracciamento da gls-group.com con `/US/`, oppure da gls-us.com o gso.com | Peek estrae il codice dal link e imposta subito GLS US («Corriere rilevato»). |

**Dove trovarlo.** Nell'e-mail di spedizione o nella pagina d'ordine del negozio, oppure nelle e-mail o negli SMS di GLS se il negozio ti ha registrato. Ogni collo ha la propria etichetta, quindi più colli significano più codici. La ricerca senza codice sul sito GLS richiede numero di conto e riferimento del mittente: chiedi al negozio. Vedi [dove trovare il numero di tracking](guide:find-tracking-number). Un numero d'ordine non funziona.

**Diverso dal GLS europeo.** GLS US fa parte di GLS Group, ma Peek segue i suoi pacchi sul portale proprio di GLS US. Un link gls-group.com con `/EU/` o `/DE/` porta Peek su [GLS Germany](carrier:gls-de), non su GLS US. Se l'e-mail cita una società GLS europea o un altro corriere, traccialo lì. [Formati dei numeri di tracciamento](guide:tracking-number-formats) mostra altri formati.

## Significato degli stati di tracking GLS US

Ogni scansione su GLS US riporta data e ora, e quasi tutte indicano il luogo: città, Stato e Paese. Le scansioni riconosciute da Peek sono in inglese e in maiuscolo, nell'ordine tipico:

| GLS US mostra | Cosa vuol dire per te | Peek mostra |
| --- | --- | --- |
| `LABEL CREATED` | Il mittente ha creato l'etichetta. GLS US potrebbe non avere ancora il pacco. Non indica un luogo. | «Etichetta creata» |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US ha scansionato il pacco all'arrivo. La data finale (mese prima del giorno) è il giorno programmato per la consegna. | «In transito» |
| `SCANNED AT DELIVERY DEPOT` | Scansionato presso il deposito di consegna. | «In transito» |
| `ON ROUTE FOR DELIVERY` | Oggi in giro di consegna. | «In consegna» |
| `SHIPMENT DELIVERED` | Consegnato. | «Consegnato» |

**Stato complessivo.** Sopra le scansioni il portale sintetizza il pacco in poche parole: `label created`, `in transit`, `out for delivery`, `delivered` o `returned`. Quando questo riepilogo è più avanti delle scansioni, Peek lo segue: `delivered` mostra «Consegnato» anche senza l'ultima scansione dedicata, e `returned` mostra «Restituito al mittente».

**Altre righe.** Il codice di Peek non conosce diciture specifiche GLS US per mancata consegna, problema, fermo o punto ritiro. Eventuali testi non mappati restano nella dicitura di GLS US e vengono ordinati con le regole generali di Peek. Altro in [significato degli stati di tracciamento](guide:tracking-statuses).

Se nulla segue `LABEL CREATED` per almeno un giorno, Peek avvisa: «Il mittente non l’ha ancora affidato a GLS US. Il tracciamento partirà da quel momento.» Chiedi al negozio.

**E-mail ed SMS di GLS.** Se il negozio ti registra alla creazione dell'etichetta, GLS scrive all'ingresso in deposito (con data prevista), in caso di ritardo o mancato tentativo, all'uscita in consegna (con fascia stimata) e a consegna avvenuta.

## Tempi di consegna, firme e assenze con GLS US

**Indirizzo.** GLS non consegna a caselle postali (*P.O. boxes*) e smista per codice ZIP, quindi un CAP errato può causare ritardi. Reindirizza un pacco solo su autorizzazione del mittente: chiedi al negozio. Vedi [cambiare indirizzo di consegna](guide:change-delivery-address).

**Giorni.** Dal lunedì al venerdì; sabato solo in alcune aree su richiesta del mittente; mai domenica. GLS resta chiusa in sei festività statunitensi (Capodanno, Memorial Day, Independence Day, Labor Day, Giorno del Ringraziamento e Natale). Un pacco previsto in un giorno festivo arriva il giorno lavorativo successivo.

**Orari.** Dipendono dal servizio scelto dal mittente. Priority consegna entro le 10:30 del giorno lavorativo successivo nella maggior parte delle zone, Ground entro le 17:00 (18:00 in aree rurali). Su tratte più lunghe Priority arriva entro le 10:30 del giorno previsto e Ground entro fine giornata. Le consegne del sabato avvengono in genere entro le 10:30.

**Firma.** La sceglie il mittente. Di norma l'autista raccoglie una firma presso le aziende. Nelle case indipendenti il pacco viene lasciato se nessuno può firmare e il punto è sicuro e asciutto; nei condomini davanti alla porta o presso l'ufficio amministrazione/affitti se l'autista non accede alla porta. Con *Signature Required* non viene lasciato senza firma; con *Adult Signature Required* (richiesta per alcol e tabacco) chi firma deve avere almeno 21 anni.

**Mancata consegna.** Hai perso un tentativo o trovato un avviso sulla porta? Riprogramma dal link nelle e-mail o negli SMS GLS, oppure chiama la linea destinatari sotto. Le pagine GLS non indicano numero di tentativi o tempi di giacenza e presentano i GLS Point solo come punti di consegna per mittenti. Vedi [consegne mancate](guide:missed-delivery) e [pacchi non ritirati](guide:uncollected-parcel).

## Pacco GLS US non trovato, fermo, smarrito o restituito

**Non trovato.** Il portale GLS US segnala quando non conosce un codice. Peek mostra «In attesa della prima scansione di GLS US» per un codice SH rilevato o «In attesa di GLS US» se lo hai scelto tu, continuando a controllare. Un'etichetta nuova richiede spesso tempo. Se dopo due giorni dall'aggiunta di un codice SH non compare ancora nulla, Peek segnala: «Ancora in attesa del primo aggiornamento del corriere». Se hai scelto GLS US e dopo due giorni continua a non riconoscerlo (senza altre fonti), Peek mostra: «Dopo due giorni il corriere non riconosce ancora questo numero. Controllalo, scegli un altro corriere o elimina il pacco.», controllando poi una volta al giorno e, dopo una settimana, una volta a settimana. Verifica le cifre sull'e-mail del negozio e chiedi se il pacco è partito.

**Nessuna novità per giorni.** Dopo quattro giorni senza nuove scansioni, un pacco su «Etichetta creata», «Preso in carico» o «In transito» riceve l'avviso «Nessun aggiornamento da quattro giorni». Peek ricorda che i pacchi restano spesso fermi nel fine settimana e continua a interrogare GLS US. Vedi [perché il tracking non si aggiorna](guide:tracking-not-updating).

**Consegnato ma non c'è nulla.** `SHIPMENT DELIVERED` senza pacco davanti alla porta? Controlla sul portale GLS chi ha firmato, verifica ingresso, sala posta, ufficio amministrazione e vicini, poi chiama la linea destinatari GLS e avvisa il negozio. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o danneggiato.** Avvisa prima il negozio che ha prenotato la consegna e può aprire pratica o rispedire l'ordine. Solo il mittente può reclamare, entro 90 giorni dalla spedizione; GLS punta a chiudere le pratiche entro 45 giorni. Per danni, GLS chiede al mittente foto di oggetto, imballo interno e scatola: scattale prima di buttare qualsiasi cosa. Vedi [pacchi smarriti](guide:lost-parcel) e [pacchi danneggiati](guide:damaged-parcel).

**Restituito.** «Restituito al mittente» significa che il pacco è tornato al negozio. Chiedi un rimborso o una nuova spedizione. Vedi [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti GLS US

Sulla [pagina di assistenza clienti GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Telefono:** 1-800-322-5555, opzione 2 (supporto destinatari: tracking, problemi e assistenza consegna).
- **Orari:** dal lunedì al venerdì dalle 7:00 alle 19:00 e sabato dalle 7:00 alle 16:00, ora del Pacifico.
- **Live chat**, offerta nelle FAQ GLS. GLS non fornisce un indirizzo e-mail per i destinatari.

> **Truffe.** GLS non chiede mai via e-mail pagamenti di merci con carta, pagamento online o bonifico, sebbene per pacchi dall'estero GLS o un agente possano richiedere dazi e imposte. I siti autentici sono gls-group.com e quelli collegati. Segnala i falsi a security@gls-us.com e leggi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco GLS US con Peek

**Un numero SH.** Incollalo nel box sopra. Mentre Peek interroga GLS US, la riga mostra «Verifica dei servizi di tracciamento…». Quando GLS US lo riconosce, indica GLS US con «ha questo pacco» e lo archivia lì. Se non lo conosce ancora, compare «non ancora trovato · Peek continua a controllare». Aggiungilo comunque: mostrerà «Probabilmente GLS US» fino alla prima scansione.

**Un altro numero.** Incollalo comunque: Peek traccia oltre 4.500 corrieri. Poi scegli GLS US manualmente:

1. Premi «Cambia» accanto al corriere e cerca GLS US (lo trovi anche con «GSO», «Golden State Overnight», «General Logistics Systems US» o «United States»).
2. Seleziona GLS US: la riga mostrerà «Scelto da te». Avvia il tracciamento.

Su un pacco già aggiunto usa «Cambia corriere». Peek interroga GLS US solo con codici di 5–20 lettere e cifre.

**Cosa vedi.** Peek interroga il portale GLS US con il solo numero: nessun account e nessuno ZIP code. Mostra lo stato, ogni scansione con città e Stato su mappa e, se fornito da GLS US, il giorno previsto fino a consegna o reso. Omette nome e indirizzo del destinatario, firmatario, firma, prova di consegna e riferimento del mittente. Il codice di Peek non passa pacchi GLS US ad altri corrieri. Se GLS US registra più spedizioni sotto un codice, Peek segue quella con la scansione più recente. «Apri il sito GLS US», nella scheda del pacco, apre il numero sul portale americano GLS.

**Orari.** GLS US riporta orari senza fuso orario. Peek usa l'ora della città della scansione o, se ignota, quella dello Stato (principale se lo Stato ne ha più d'uno). Senza città né Stato usa il fuso della scansione precedente più vicina o della successiva; in mancanza di entrambi, l'ora del Pacifico. Peek mostra tutti gli orari nel fuso del tuo dispositivo.

**Quando GLS US non risponde.** Se il portale non è raggiungibile, Peek interroga [servizi di tracciamento](guide:universal-tracker) e mostra la loro cronologia nelle loro parole.

**Frequenza.** Finché tieni aperto il pacco, o nel giorno di consegna con notifiche attive, e GLS US risponde, Peek controlla fino a ogni 10 minuti, o ogni 2 minuti dalle 6:00 ora locale il giorno in cui entra in giro di consegna; altrimenti ogni ora. Tramite servizi di tracciamento, ogni ora e fino a ogni 15 minuti quel giorno con scheda aperta o notifiche attive. Dopo due giorni senza novità (12 ore su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Le notifiche coprono «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

Peek non può contattare GLS US o il negozio, modificare la consegna né presentare reclami.

## Domande sul tracking GLS US

### GSO è la stessa cosa di GLS US?

GLS ha acquisito Golden State Overnight (GSO) nel 2016 e gso.com reindirizza oggi al sito GLS US. Fondata nel 1995, GSO consegnava in California, Arizona, Nevada e New Mexico. Peek li tratta come un unico corriere: elenca GSO, Golden State Overnight, GLS USA, GLS United States e General Logistics Systems US come nomi alternativi di GLS US e riconosce i link di gso.com e gls-us.com come GLS US.

### Com'è fatto un numero di tracking GLS US?

Il formato noto a Peek è SH seguito da 18 cifre, come `SH605182704936815247` (inventato). Le pagine GLS non fissano un formato e potrebbero essercene altri: incollali comunque e scegli GLS US con «Cambia».

### Cosa significa l'arrival scan di GLS US?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` significa che GLS US ha scansionato il pacco all'arrivo e fissato la consegna per la data finale, mese prima del giorno. Peek mostra «In transito».

### Perché Peek dice «In attesa della prima scansione di GLS US»?

Il codice ha il formato GLS US e Peek ha interrogato GLS US, ma non risulta ancora. È normale per un'etichetta appena creata e Peek continua a chiedere. Se dopo una settimana resta assente, Peek avvisa e suggerisce di verificare con il mittente o scegliere un altro corriere.

### GLS US è uguale a GLS in Europa?

Appartiene allo stesso gruppo, GLS Group, ma Peek tratta GLS US come corriere autonomo interrogando il portale GLS US. Le società GLS europee come [GLS Germany](carrier:gls-de) hanno i propri portali: segui il pacco con la filiale GLS indicata dal negozio.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – tracker USA, ricerca senza numero, firmatario
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – filiale USA di GLS Group, Ovest USA, storia di GSO
- [GSO: gso.com](https://www.gso.com/) – reindirizza al sito GLS US
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – tempi di consegna, sabato in alcune aree
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – giorni di consegna, festività
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – niente domenica, festività, firme, codici ZIP, un'etichetta per collo, reclami entro 90 giorni
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – caselle postali, reindirizzamento, riprogrammazione, avvisi porta, notifiche, live chat, reclami
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – reclamo dal mittente, 45 giorni, foto
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – linea destinatari, orari
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – solo drop-off
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – richieste di pagamento, dazi e tasse, siti autentici, segnalazioni
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – nomi, formato SH e corrispondenza debole, link, scansioni e stati, risposta non trovato, giorno previsto, più spedizioni per numero, fusi, dati omessi
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reti europee GLS e link, servizi di tracciamento in riserva, orari per città
:::
