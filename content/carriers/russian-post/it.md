---
title: Tracciamento Russian Post (Почта России): numeri e stati
description: Traccia un pacco Russian Post (Почта России): codici RU e a 14 cifre, significato degli stati in russo e come lo segue Peek.
slug: tracciamento-russian-post
published: 2026-11-22
updated: 2026-11-22
---

Russian Post (Почта России, Pochta Rossii) è il servizio postale della Russia. Trasporta pacchi spediti dalla Russia e pacchi spediti verso la Russia. Il codice di tracciamento viene fornito dal mittente o dal negozio: incollalo nel campo qui sopra. Il sito ufficiale di Russian Post è pochta.ru, e Peek ne legge il tracciamento al posto tuo come spiegato qui sotto.

## Codice di tracciamento Russian Post: come è fatto

Con cifre inventate:

| Codice (inventato) | Che cos'è |
| --- | --- |
| `RR482910576RU` | Due lettere, nove cifre e `RU`: il formato postale internazionale che termina con il codice della Russia, con la nona cifra di controllo. Quando inizia con C, E, L o R, Peek riconosce subito Russian Post. |
| `VV482910576RU` | Un altro codice internazionale in `RU` che non inizia con C, E, L né R. Peek lo mostra come «Unknown postal carrier» (servizio postale sconosciuto): puoi scegliere Russian Post manualmente. |
| `80482910573625` | 14 cifre che iniziano con `80`, con l'ultima cifra di controllo. Peek lo collega a Russian Post solo come corrispondenza debole: anche DPD, SEUR, BRT, Delhivery e altri corrieri usano numeri di questo formato. |
| `80482910573620` | Lo stesso formato con una cifra di controllo errata. Peek non propone Russian Post, ma solo gli altri corrieri: se il tuo è un numero Russian Post, controlla eventuali refusi. |
| `RR482910576CN` | Un numero emesso da un altro servizio postale, qui [China Post](carrier:china-post). Peek lo segue presso quel servizio, anche quando viaggia verso la Russia. |

Spazi e minuscole non contano quando incolli un codice in Peek. Se non trovi il tuo, vedi [come trovare il codice di tracciamento](guide:find-tracking-number) e [formati dei codici di tracciamento](guide:tracking-number-formats).

## Stati del tracciamento Russian Post spiegati

Russian Post scrive i suoi passaggi in russo. Peek li legge, come nella tabella sotto, sul tracciamento di Qwintry, uno spedizioniere che affida parte dei suoi pacchi a Russian Post: vale per un pacco aggiunto con il codice Qwintry, oppure aggiunto con il codice RU e associato a Qwintry (vedi «Pacchi da Qwintry» più avanti). Lì ogni passaggio di Russian Post ha un'operazione, come `обработка` (lavorazione), seguita da una riga di dettaglio e dall'ufficio postale alla fine. Se manca il dettaglio, l'operazione funge da passaggio. Grossomodo nell'ordine in cui compaiono:

| Russian Post scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Партионный` | Russian Post ha preso in carico il pacco in un lotto con altri (operazione `приём`, accettazione). | «In transito» |
| `экспорт международной почты` | Esportazione di posta internazionale: il pacco lascia il paese in cui si trova. | «In transito» |
| `Прибыло на территорию РФ` | Il pacco è arrivato in Russia. | «In transito» |
| `импорт международной почты` | Importazione di posta internazionale: il pacco viene preso in carico in Russia. | «In transito» |
| `приём на таможню` | Affidato alla dogana. | «In dogana» |
| `Выпущено таможней` | Sdoganato: il pacco ha ripreso il viaggio. | «In transito» |
| `Покинуло место международного обмена` | Ha lasciato il centro che gestisce la posta dall'estero. | «In transito» |
| `Сортировка` | Smistamento. | «In transito» |
| `Прибыло в сортировочный центр` | Arrivato a un centro di smistamento. | «In transito» |
| `Покинуло сортировочный центр` | Partito da un centro di smistamento. | «In transito» |
| `Прибыло в место вручения` | Arrivato all'ufficio postale che lo consegnerà o dove potrai ritirarlo. | «In transito» |
| `Передано почтальону` | Affidato al postino per la consegna. | «In consegna» |
| `Временное отсутствие адресата` | Non consegnato: non c'eri. | «Tentativo di consegna» |
| `Адресат заберет отправление сам` | Non consegnato perché ritirerai il pacco di persona. | «Tentativo di consegna» |
| `Вручение адресату` | Consegnato a te, il destinatario. | «Consegnato» |

Per tutti gli altri pacchi Russian Post, Peek legge il tracciamento diretto di Russian Post, che risponde in inglese: vedi «Tracciare un pacco Russian Post con Peek» sotto.

## Come consegna Russian Post

I passaggi sopra descrivono il percorso in Russia: centri di smistamento e poi l'ufficio postale che consegna il pacco. Da lì può portarlo un postino (`Передано почтальону`). Se eri assente o devi ritirarlo tu, il passaggio lo dice. Sul tracciamento di Qwintry, Peek mostra la città dell'ufficio postale come luogo del passaggio. Se usi Peek in russo, o se Peek non riesce a localizzare l'ufficio, mostra il nome dell'ufficio esattamente come lo scrive Russian Post, così sai quale sede ha il tuo pacco.

Questa pagina non riporta tempi di consegna, periodi di giacenza né regole sui tentativi di consegna, perché Peek non ha potuto verificarli sul sito di Russian Post. Per un pacco in giacenza in ufficio postale, vedi [pacchi non ritirati](guide:uncollected-parcel) e [consegne mancate](guide:missed-delivery).

**Spedito dalla Russia verso un altro paese.** Peek non sa in anticipo quale azienda consegnerà un pacco Russian Post nel tuo paese. Per un codice RU, interroga anche le poste del paese che il tracciamento di Russian Post indica come destinazione e può proseguire lì il monitoraggio (vedi sotto).

**Spedito verso la Russia da un altro paese.** Peek segue il pacco presso il corriere che ha emesso il numero, tramite il suo tracciamento o i servizi di tracciamento. Se quel tracciamento indica Russian Post come azienda che consegna, Peek interroga anche Russian Post e sposta lì il pacco appena compare una scansione almeno recente quanto l'ultima nota. Il solo fatto che sia diretto in Russia non basta a spostarlo.

## Pacco Russian Post fermo o non arrivato

**Ancora nessun passaggio.** Il tracciamento di Russian Post o i servizi di tracciamento potrebbero non avere ancora il numero, oppure il mittente non ha ancora affidato il pacco. Peek continua a controllare. Se dopo alcuni giorni non compare nulla, verifica il codice con il mittente. Altri dettagli in [perché il tracciamento si ferma](guide:tracking-not-updating).

**Fermo in dogana.** Se Peek mostra «In dogana», il pacco è presso la dogana. Questa pagina non riporta regole o costi doganali, perché non è stato possibile verificarli su una pagina ufficiale. Vedi [pacchi fermi in dogana](guide:customs).

**Peek è indietro.** Quando il tracciamento di Russian Post non risponde a Peek, subentra un servizio di tracciamento che può essere in ritardo rispetto al sito ufficiale. Confronta con pochta.ru prima di preoccuparti.

**Consegnato, ma non hai ricevuto nulla.** Se Peek mostra «Consegnato» e non hai il pacco, chiedi al tuo ufficio postale e avvisa il mittente. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Smarrito o rispedito al mittente.** Rivolgiti prima al mittente o al negozio: sono loro ad aver prenotato la spedizione. Vedi [pacchi smarriti](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti Russian Post

Poiché Peek non ha potuto leggere il sito di Russian Post, questa pagina non riporta alcun numero di telefono né indirizzo e-mail: indichiamo solo contatti letti direttamente sulle pagine del corriere. Le pagine di assistenza di Russian Post si trovano su info.pochta.ru. Per un pacco che non si muove più, Peek collega la pagina di aiuto sul tracciamento di Russian Post come «Chiedi aiuto a Russian Post».

Per un ordine da un negozio, chiedi prima al negozio: ha prenotato lui la spedizione.

## Tracciare un pacco Russian Post con Peek

Incolla il codice in [Peek](/): Peek segue oltre 4.500 corrieri. In base al formato:

- **Codice internazionale in `RU` che inizia con C, E, L o R (cifra di controllo valida).** Peek riconosce subito Russian Post («Corriere rilevato»).
- **Altro codice internazionale in `RU` (altra lettera iniziale).** Peek lo mostra come «Unknown postal carrier». Premi «Cambia» (o in seguito «Cambia corriere») e scegli Russian Post: anche digitando Почта России lo trovi. Peek mostrerà quindi Russian Post.
- **14 cifre che iniziano con `80` (cifra di controllo valida).** Mentre lo aggiungi, Peek mostra «Verifica dei servizi di tracciamento…» e chiede al tracciamento di Russian Post, insieme a DPD e agli altri corrieri compatibili, se hanno il pacco. Se lo conosce solo Russian Post, Peek mostra «Russian Post ha questo pacco.» e lo associa lì. Altrimenti premi «Cambia»: Russian Post compare sotto «Compatibili con questo numero», con gli altri corrieri compatibili, prima quelli del tuo paese. Su un pacco già aggiunto usa «Cambia corriere».

Peek legge il tracciamento di Russian Post, che risponde in inglese. Mostra ogni passaggio nelle parole inglesi di Russian Post, come `Sorting`, `Arrived at the local distribution center` o `Forwarded to the postman` («In consegna»), con orario, città, paese e paese di destinazione. `Available for pickup at the Post Office` compare come «Pronto per il ritiro» e `Handed over for temporary storage` come «Problema segnalato». Dopo `Returned to the sender` (anch'esso «Problema segnalato»), il pacco torna indietro e la consegna al mittente diventa «Restituito al mittente». Russian Post non indica una data di consegna prevista. Finché il suo tracciamento non conosce il numero, Peek mostra «In attesa di Russian Post», oppure «In attesa della prima scansione di Russian Post» per un codice `80` aggiunto senza corriere. Quando il tracciamento di Russian Post non risponde a Peek, Peek interroga servizi di tracciamento — ParcelsApp, Ship24 e 17TRACK nell'ordine, e per un codice RU infine il tracciamento dell'Unione postale universale — mostrando la cronologia del primo che conosce il pacco, nelle sue parole, che può restare indietro rispetto a pochta.ru. Un servizio che non conosceva il numero viene ricontrollato il giorno successivo.

Per un codice `80` aggiunto senza corriere, o per un codice RU mostrato come «Unknown postal carrier», nei primi 30 giorni e prima dei servizi di tracciamento Peek interroga anche i corrieri che potrebbero avere il pacco (come Russian Post, DPD e altri per i numeri `80`). Se uno mostra movimenti, Peek sposta lì il pacco. Se hai aggiunto un codice `80` senza corriere e un servizio di tracciamento nomina solo Russian Post con passaggi datati, Peek lo assegna automaticamente a Russian Post. Per un codice RU mostrato come «Unknown postal carrier» questo non basta: serve che il tracciamento di Russian Post mostri il pacco oppure che tu scelga Russian Post manualmente.

Peek non ha un link diretto al tracciamento di Russian Post. Se ha risposto un servizio di tracciamento, il link sulla pagina del pacco apre quel servizio, ad esempio «Apri il sito ParcelsApp». Per pochta.ru, aprilo tu direttamente.

**Pacchi da Qwintry.** Se Qwintry ha spedito il tuo pacco verso la Russia, aggiungi piuttosto il codice Qwintry: `QR` e otto cifre. Peek riconosce subito Qwintry e ne legge il tracciamento, che continua a mostrare i passaggi di Russian Post dopo la consegna alle poste. Quando il tracciamento di Qwintry cita Russian Post, il pacco mostra «Consegna con Russian Post» con il codice RU delle poste e Peek interroga anche Russian Post: appena Russian Post mostra una scansione recente almeno quanto l'ultima di Qwintry, Peek prosegue lì il tracciamento conservando la cronologia di Qwintry. Finché Russian Post ha solo il preavviso senza scansioni proprie, il pacco mostra «Annunciato a Russian Post, non ancora ricevuto». Il tracciamento di Qwintry accetta anche il codice RU di un pacco che ha affidato alle poste: se hai solo quello e Russian Post non lo trova, premi «Cambia» e scegli Qwintry.

**Le poste del tuo paese.** Per un codice RU associato a Russian Post, il tracciamento di Russian Post indica il paese di destinazione e Peek interroga con lo stesso codice RU anche le poste di quel paese se ne legge direttamente il tracciamento, come per [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, Canada Post e Australia Post. Per un pacco mostrato come «Unknown postal carrier», basta che un servizio di tracciamento indichi le poste incaricate con il loro numero, oppure il paese di destinazione per un codice RU: Peek interroga allo stesso modo quel servizio postale e prosegue lì appena mostra una scansione almeno recente quanto l'ultima nota.

Finché tieni il pacco aperto sullo schermo, oppure nel giorno della consegna con le notifiche attive, Peek controlla il tracciamento di Russian Post fino a ogni 10 minuti — e fino a ogni 2 minuti dalle 6:00 ora locale nel giorno in cui passa in consegna — finché quel tracciamento risponde; tramite i servizi di tracciamento controlla ogni ora, ogni 3 ore dopo un giorno senza nuove scansioni e fino a ogni 15 minuti nel giorno in cui passa in consegna. Altrimenti, e dopo due giorni senza novità (12 ore finché il pacco resta su «Etichetta creata» o «Preso in carico»), controlla una volta all'ora. Può avvisarti per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Russian Post né il mittente al posto tuo.

## Domande sul tracciamento Russian Post

### Cosa significa Почта России?

Poste Russe, noto internazionalmente come Russian Post o Pochta Rossii. Il sito ufficiale è pochta.ru.

### Com'è fatto un codice di tracciamento Russian Post?

Un codice internazionale ha due lettere, nove cifre e `RU`, come `RR482910576RU`. Peek collega a Russian Post anche i numeri di 14 cifre che iniziano con `80` se la cifra di controllo è corretta.

### Perché i passaggi su Peek non corrispondono a pochta.ru?

Peek legge il tracciamento di Russian Post in inglese, quindi per la maggior parte dei passaggi mostra i testi inglesi di Russian Post invece di quelli russi. Quando quel tracciamento non risponde a Peek, subentra un servizio di tracciamento con parole proprie e possibile ritardo. Peek mostra i testi russi della tabella sopra per i pacchi seguiti tramite il tracciamento di Qwintry.

### Peek può tracciare un pacco spedito in Russia da un altro paese?

Sì. Incolla il codice fornito dal mittente: Peek lo segue con il corriere che lo ha emesso e lo sposta su Russian Post solo se quel tracciamento indica Russian Post come azienda che consegna.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – nomi, formati `80` e `RU` con cifre di controllo e corrispondenze, tracciamento letto da Peek e testi in inglese, luoghi, destinazione, fasi e pagina di aiuto
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – passaggi russi di Russian Post sul tracciamento di Qwintry e relative fasi, ufficio postale come luogo, codice `QR` e codici `RU` accettati
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – codici postali `RU`, corrieri interrogati per primi, ordine dei servizi di tracciamento, passaggi alle poste del tuo paese e a Russian Post
:::
