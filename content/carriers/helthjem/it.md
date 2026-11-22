---
title: Tracciamento Helthjem: stati, ritiro e contatti
description: Traccia un pacco Helthjem in Norvegia: dove trovare il codice, stati norvegesi tradotti, consegne notturne, punti di ritiro e contatti.
slug: tracciamento-helthjem
published: 2026-11-22
updated: 2026-11-22
---

Helthjem è un servizio norvegese di consegna pacchi per negozi online: lascia i pacchi sullo zerbino o nella cassetta postale, anche nei giri notturni, oppure li invia a un punto di ritiro. Incolla il numero nel campo sopra per tracciare il tuo; anche il sito `helthjem.no` dispone di una pagina di tracciamento. Poiché Helthjem usa il norvegese, gli stati sono tradotti qui sotto.

## Dove trovare il numero di tracciamento Helthjem

Il negozio mittente fornisce il codice, di solito nell’e-mail di spedizione o nella pagina dell’ordine. Nulla? Chiedi al negozio e consulta [dove trovare il numero di tracciamento](guide:find-tracking-number).

I numeri che Peek collega a Helthjem hanno questa forma (cifre inventate), accanto a quelli simili di Bring:

| Numero | Di chi è |
| --- | --- |
| `370724764829173648` | Helthjem: 18 cifre che iniziano con `37072476`. |
| `00370724764829173648` | Lo stesso con `00` davanti, come sotto un codice a barre: 20 cifre. |
| `370748291736482919` | Bring: anche i suoi numeri a 18 cifre iniziano con `370` o `373`. |
| `RR482917367NO` | Bring: codici postali che terminano in `NO`. |

L’ultima cifra è una cifra di controllo. La ricerca di Helthjem accetta anche il numero di spedizione a 17 cifre, con o senza `401` davanti.

I codici Helthjem [somigliano molto alla serie](guide:tracking-number-formats) `370` di Bring; Peek li distingue dal prefisso `37072476` e dalla cifra di controllo. Se Helthjem non conosce un numero o non lo conserva più, la ricerca non trova nulla. Verifica le cifre, prova Bring o chiedi al negozio quale corriere ha prenotato.

## Significato degli stati di tracciamento Helthjem

La pagina Helthjem mostra stato, scansioni con località, giorno previsto, negozio mittente e, quando previsto, punto di ritiro con indirizzo e foto del deposito. Helthjem classifica pacco e scansioni in dodici stati espressi in norvegese bokmål. Nell’ordine abituale:

| Stato | Cosa significa per te |
| --- | --- |
| `Nettbutikken har meldt at en pakke er på vei!` | Il negozio ha [annunciato un pacco](guide:tracking-statuses). Helthjem non lo ha ancora ricevuto. |
| `Vi har mottatt pakken din, og gjør den klar for veien videre.` | Ricevuto al terminal Helthjem indicato, in preparazione per la tratta successiva. |
| `Pakken er under transport.` | In transito: scansione di un terminal o centro di smistamento. |
| `Planlagt levering natt til 8. oktober!` | Programmato sul giro notturno verso la data indicata. Nessun fattorino lo ha ancora: parte quella notte. |
| `Budet fikk ikke med seg pakken din ut i natt.` | Il corriere notturno non ha potuto portare il pacco. Attende un giro successivo. |
| `Pakken er levert på dørmatten din!` | Consegnato sullo zerbino. |
| `Pakken er levert i postkassen din!` | Consegnato nella cassetta postale. |
| `På vei til hentested`, `Leveres til hentested` | Indirizzato a un punto di ritiro anziché a casa. |
| `Klar til henting` | Pronto per il ritiro nel punto indicato sulla pagina Helthjem. |
| `Pakken er hentet` | Ritirato presso il punto di ritiro. |
| `Pakke ikke hentet` | Non ritirato in tempo: torna al negozio. |
| `Returneres` | In rientro verso il negozio, o già rientrato. |
| `Pakken er kansellert` | Spedizione annullata. |

**Il luogo sembra sbagliato.** Su `Vi har mottatt pakken din…` e `Pakken er under transport.`, il luogo è il terminal o il centro di smistamento Helthjem, non il tuo indirizzo. Nel 2023 Helthjem gestiva un proprio terminal pacchi a Vestby, vicino Oslo, e il registro delle imprese elenca ancora una sede lì: una scansione a Vestby indica dove è stato smistato, non la destinazione.

## Consegna Helthjem: giri notturni, zerbino e punti di ritiro

**Chi consegna.** Nel 2023 Helthjem vendeva consegne ai negozi affidando i pacchi ai distributori di giornali della rete Helthjem, attivi sei giorni su sette. I pacchi più ingombranti non lasciabili alla porta viaggiavano con PostNord.

**Giri notturni.** Il giorno prima della consegna a domicilio, Helthjem inserisce il pacco in un giro notturno segnalando `Planlagt levering natt til 8. oktober!`: uscirà la notte che porta all’8 ottobre. In quel momento non è ancora con un fattorino.

**Dove viene lasciato.** Sullo zerbino (`Pakken er levert på dørmatten din!`) o nella cassetta postale (`Pakken er levert i postkassen din!`). La pagina Helthjem può mostrare una foto del deposito; Peek non la mostra, quindi verifica lì.

**Punti di ritiro (*hentested*).** Nel 2023 Helthjem consegnava presso negozi per colli grandi, per chi preferiva il ritiro e nelle aree non servite dai distributori. Un pacco deviato lì mostra `På vei til hentested` e riceve una nuova etichetta con un nuovo numero; il tuo numero originario continua a trovarlo. Quando è pronto passa a `Klar til henting` con nome e indirizzo sulla pagina Helthjem. Se non viene ritirato in tempo segue `Pakke ikke hentet` e rientra al negozio.

**Pacchi PostNord.** Quando Helthjem segnala un pacco come prodotto PostNord, la pagina di tracciamento ti rimanda al tracciamento PostNord con il numero di spedizione.

**Pacchi dalla Cina tramite 4PX.** La voce `Parcel arrival notified` nella cronologia 4PX è l’avviso di Helthjem che il negozio ha annunciato il pacco, riportato in inglese dopo il passaggio da 4PX.

## Pacco Helthjem in ritardo, smarrito o danneggiato

**Nessun aggiornamento.** Dopo la prenotazione di un giro notturno o `Budet fikk ikke med seg pakken din ut i natt.`, il pacco attende una corsa notturna. Se resta fermo per giorni, chiedi al negozio e vedi [perché il tracciamento si ferma](guide:tracking-not-updating).

**Consegnato ma non arrivato.** L’ultima scansione indica dove è stato lasciato: sullo zerbino o in cassetta. Controlla eventuale foto sulla pagina Helthjem, verifica entrambi i punti e chiedi a chi vive con te. Ancora assente? Contatta il negozio e leggi [consegnato ma non ricevuto](guide:delivered-not-received).

**Perso, danneggiato o in ritardo.** Il cliente di Helthjem è il negozio: parti da lì. Il Consiglio norvegese dei consumatori (*Forbrukerrådet*) consiglia di rivolgersi prima al venditore se un ordine online non arriva. Con un negozio norvegese puoi poi chiedere mediazione all’Autorità per i consumatori (*Forbrukertilsynet*). Se hai pagato con carta di credito hai diritto per legge a chiedere il rimborso alla banca quando non ricevi ciò che hai ordinato. Vedi [pacco smarrito](guide:lost-parcel).

**Restituito o annullato.** Dopo `Pakke ikke hentet`, `Returneres` o `Pakken er kansellert`, chiedi rimborso o nuova consegna al negozio.

## Assistenza clienti e contatti Helthjem

Il sito `helthjem.no` offre una pagina di aiuto e contatto. Se passano quattro giorni senza scansioni prima della consegna, la nota di Peek collega direttamente quella pagina con «Chiedi aiuto a Helthjem». Per l’ordine, un rimborso o un reclamo rivolgiti al negozio che ha prenotato Helthjem.

> Nkom, l’autorità norvegese per le comunicazioni, invita a diffidare di ogni link negli SMS anche quando aspetti un pacco: portano spesso a pagine false che chiedono dati della carta, e un link che si spaccia per un’azienda norvegese senza dominio `.no` richiede massima cautela. La polizia consiglia di cercare il sito da sé anziché cliccare il link. Altro in [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco Helthjem con Peek

[Peek](/) segue oltre 4.500 corrieri e riconosce subito Helthjem da 18 cifre che iniziano con `37072476`, o le stesse precedute da `00`, se la cifra di controllo torna («Corriere rilevato»). Altri numeri con `370` o `373` possono essere di Bring: Peek interroga Bring e gli altri corrieri compatibili. I codici di spedizione Helthjem da 17 cifre e altri codici a barre da 20 cifre richiedono «Cambia» o un link su `helthjem.no/sporing/`.

Peek mostra stato, luogo di ogni scansione (comprese scansioni di smistamento che la pagina Helthjem nasconde), ora di consegna, negozio mittente e nome del punto di ritiro mentre attende o una volta ritirato. Se Helthjem rietichetta il pacco per un punto di ritiro, Peek mostra anche il nuovo numero sotto «Helthjem lo traccia con il numero». Gli stati restano nel norvegese di Helthjem e gli orari sono convertiti nel tuo fuso («Orari nel tuo fuso orario:» seguito dal nome). Quando Helthjem programma il giro notturno, Peek mostra la data in `Planlagt levering natt til …` come giorno previsto. Omette invece la data generica della pagina Helthjem, l’indirizzo del punto di ritiro, la foto e i codici postali.

Quando Helthjem segnala un pacco affidato a PostNord, la scheda mostra «Consegna con PostNord» e Peek può seguirlo su PostNord col relativo codice. Finché PostNord ha solo l’annuncio senza scansioni proprie, appare «Annunciato a PostNord, non ancora ricevuto».

Mentre hai il pacco aperto in Peek — o il giorno della consegna con le notifiche attive — Peek interroga direttamente Helthjem fino a ogni 10 minuti, e fino a ogni 2 minuti dalle 6:00 ora di Oslo nel giorno in cui Helthjem (o PostNord dopo il passaggio) segna «In consegna», finché il rispettivo tracker risponde; altrimenti, e dopo due giorni senza novità (12 ore se resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche scegli «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni in viaggio, fuori da dogana o problemi segnalati, Peek mostra la data dell’ultima scansione («Nessun aggiornamento da …») e «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Helthjem.», con il link «Chiedi aiuto a Helthjem». Peek non può contattare Helthjem per te.

## Domande frequenti su Helthjem

### Helthjem è affidabile?

Sì. Helthjem Netthandel AS è registrata a Oslo come azienda postale e corriere; all’assemblea generale 2025 il gruppo editoriale norvegese Polaris Media indicava una quota del 34%. Nel 2023 i suoi pacchi viaggiavano coi distributori di giornali che nel 2015 avevano adottato il nome Helthjem. Diffida comunque degli SMS a suo nome con link che chiedono dati della carta.

### A che ora consegna Helthjem?

Le consegne a domicilio avvengono spesso su giro notturno. Quando Helthjem ne prenota uno, il tracciamento mostra il giorno prima `Planlagt levering natt til …` con la data che chiude quella notte; Peek mostra quella data come giorno previsto.

### Cosa significa «Budet fikk ikke med seg pakken din ut i natt»?

Il corriere notturno non è riuscito a portare via il tuo pacco. Resta con Helthjem in attesa di un giro notturno successivo.

### Perché Helthjem non trova il mio numero di tracciamento?

Helthjem non conosce il numero o non lo conserva più, oppure appartiene a un altro corriere: anche i codici Bring iniziano con `370` o `373`. Controlla le cifre, prova Bring o chiedi al negozio.

### Come contatto Helthjem?

Tramite la pagina di aiuto e contatti su `helthjem.no`. Per rimborsi e reclami rivolgiti al negozio che ha spedito il pacco.

:::sources
- [Brønnøysund Register Centre: Helthjem Netthandel AS](https://virksomhet.brreg.no/nb/oppslag/enheter/914919010) – azienda a Oslo, servizi postali e corriere
- [Brønnøysund Register Centre: Helthjem Netthandel AS avd Vestby](https://virksomhet.brreg.no/nb/oppslag/underenheter/925669946) – sede di Vestby
- [Konkurransetilsynet: Schibsted Delivery's notice of its acquisition of Amedia Distribusjon (23 October 2023)](https://konkurransetilsynet.no/wp-content/uploads/2023/10/2023_0474-1-OFF-Schibsted-Delivery-AS-Amedia-Distribusjon-AS-1.pdf) – rete Helthjem dal 2015, distribuzione sei giorni a settimana, consegne per negozi, PostNord per pacchi grandi, punti ritiro, terminal di Vestby
- [Polaris Media: Annual general meeting 2025](https://www.polarismedia.no/wp-content/uploads/2025/05/Presentasjon-ordin%C3%A6r-generalforsamling-2025-1.pdf) – quota del 34% in Helthjem Netthandel
- [Forbrukerrådet: Netthandel](https://www.forbrukerradet.no/forside/okonomi-og-betaling/netthandel-misfornoyd/) – contattare prima il venditore, mediazione, storno carta di credito
- [Nkom: Slik kan du avsløre SMS-svindel](https://nkom.no/aktuelt/slik-kan-du-avslore-sms-svindel) – link negli SMS pacchi, pagine false carte, domini .no
- [Politiet: Beskytt deg mot svindel og ID-tyveri](https://www.politiet.no/rad/beskytt-deg-mot-svindel-og-id-tyveri) – cercare il sito autonomamente
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formati codici, pagine tracking e aiuto Helthjem, dodici stati e testi, giri notturni, punti ritiro e nuove etichette, PostNord, 4PX, campi letti da Peek e frequenza controlli
:::
