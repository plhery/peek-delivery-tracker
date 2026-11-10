---
title: TopYou tracking: codice TYZR, stati e consegna in Italia
description: Tracking TopYou (通邮) per un pacco dalla Cina: com'è fatto un codice TYZR…YQ, cosa vogliono dire le scansioni in inglese e chi prende il pacco in Italia.
slug: topyou-tracking
published: 2026-11-03
updated: 2026-11-03
---

TopYou (通邮, Tongyou, anche 通邮集团 o SZTY56) è 深圳市通邮物流科技（集团）股份有限公司, uno spedizioniere internazionale di Shenzhen, in Cina, che gestisce linee verso l'estero. Se un negozio online cinese ha spedito il tuo ordine con TopYou, il codice che ti ha dato inizia con TYZ e finisce con YQ: incollalo nel box qui sopra. Il tracking di TopYou segue il pacco dal suo magazzino in Cina fino in Italia, volo e dogana compresi. Poi il pacco passa al corriere che lo consegna.

## Codice di tracking TopYou: com'è fatto

Un codice TopYou è fatto di TYZR, una lettera, dieci cifre e YQ: 17 caratteri in tutto. In alcuni, al posto della R c'è un'altra lettera. Con cifre inventate:

| Codice | Cos'è |
| --- | --- |
| `TYZRA0000000017YQ` | TYZR, una lettera, dieci cifre e YQ: un codice TopYou. |
| `TYZRA000000017YQ` | Solo nove cifre: non è un codice TopYou. Controlla che non manchi una cifra. |
| `JXCAB0000000017YQ` | Finisce anche lui con YQ, come i codici di altri spedizionieri cinesi, ma inizia con JXC: è un codice Juxi Express. |

Spazi e minuscole non contano quando incolli il codice in Peek. Sul sito di tracking di TopYou, invece, scrivilo in maiuscolo. Se non trovi il codice nei dettagli dell'ordine, vedi [dove trovare il codice di tracking](guide:find-tracking-number).

**Un secondo codice.** La scheda di TopYou può contenere anche il codice con cui il corriere che consegna segue il pacco, e il sito di tracking di TopYou lo mostra.

**Incolla il codice, non un link.** Peek non legge i link al sito di TopYou: copia solo il codice.

## Stati TopYou: cosa vogliono dire

TopYou scrive le scansioni in inglese, e Peek le mostra così come sono. Nell'ordine in cui il pacco le incontra:

| TopYou scrive | Cosa vuol dire per te | Stato in Peek |
| --- | --- | --- |
| `Arrived at the first mile sorting center` | Il magazzino di TopYou in Cina ha il pacco: è la prima scansione. | «Preso in carico» |
| `Departed from the first mile sorting center` | Il pacco ha lasciato quel magazzino. | «In transito» |
| `Arrived at airport of the original country` | È all'aeroporto di partenza, in Cina, prima del volo. | «In transito» |
| `Departed from Airport to destination country` | È partito il volo verso il Paese di destinazione. | «In transito» |
| `Arrived at destination airport` | È atterrato nel Paese di destinazione. | «In transito» |
| `Departed from customs` | La dogana del Paese di destinazione l'ha svincolato. Sul sito di TopYou la riga finisce con un `of` senza Paese, che Peek toglie. | «In transito» |

**Codici più recenti.** Su questi TopYou descrive alcuni passaggi con altre parole:

| TopYou scrive | Cosa vuol dire per te | Stato in Peek |
| --- | --- | --- |
| `Carrier received the shipment` | TopYou ha il pacco in Cina: è la prima scansione. | «Preso in carico» |
| `Shipment arrived at a carrier facility` | Il pacco lascia il primo magazzino di TopYou e prosegue: non è una seconda presa in carico. | «In transito» |
| `Shipment started the customs clearance process at origin` | Sdoganamento all'esportazione in Cina, un passaggio di routine prima del volo: la dogana non trattiene il pacco, e non è ancora quella del Paese di destinazione. | «In transito» |
| `Shipment arrived in the destination country` | È arrivato nel Paese di destinazione. | «In transito» |
| `Shipment started the customs clearance process at destination` | È iniziato lo sdoganamento all'importazione nel Paese di destinazione. | «In dogana» |
| `Shipment completed the customs clearance process at destination` | Sdoganato: ora va al corriere che lo consegna. Non è più fermo in dogana. | «In transito» |
| `Shipment is out for delivery` | Non è ancora in consegna, anche se la frase lo dice: in Italia e in Francia segna il passaggio a GOFO e arriva con la prima scansione di GOFO in un centro di smistamento, qualche giorno prima che il corriere di GOFO parta per il giro di consegna. | «In transito» |

**Linee consegnate da una posta.** Sulle linee che consegna la posta del Paese di destinazione, TopYou usa altre parole ancora, fino alla dogana di quel Paese:

| TopYou scrive | Cosa vuol dire per te | Stato in Peek |
| --- | --- | --- |
| `Accepted by carrier` | TopYou ha il pacco in Cina: è la prima scansione. | «Preso in carico» |
| `Inbound in sorting center` | Registrato nel primo magazzino di TopYou, pochi minuti dopo. Non si muove ancora. | «Preso in carico» |
| `Outbound in sorting center` | Ha lasciato quel magazzino. TopYou aggiunge `, Lastmile Number:` e il codice con cui la posta lo consegna, e Peek mostra la riga con quel codice. | «In transito» |
| `Received by line-haul`, `Hand over to airline` | Affidato al trasporto, poi passato alla compagnia aerea. | «In transito» |
| `Airline arrived at transit country or district.`, `Airline departed from transit or district country.` | Uno scalo lungo la strada verso il Paese di destinazione. | «In transito» |
| `Airline arrived at destination country`, `Arrive at destination country` | È arrivato nel Paese di destinazione. | «In transito» |
| `Export clearance success` | La dogana cinese l'ha lasciato partire: non è fermo. | «In transito» |
| `Import clearance success` | La dogana del Paese di destinazione l'ha sdoganato per la posta. La posta può ancora mostrare scansioni doganali sue. | «In transito» |

**Dopo il passaggio.** Il sito di TopYou può continuare con le scansioni del corriere che consegna, nelle sue parole. In Italia, per esempio, quelle di GOFO: `Arrived at sorting center`, `The package left sorting center` e `Package departed from sorting center` (il pacco è in un centro di smistamento di GOFO o lo lascia: «In transito»), `Arrived at GOFO Regional Destination Facility` (il pacco è arrivato alla struttura regionale di GOFO: «In transito») e `The driver is out for delivery` (il corriere di GOFO è partito per il giro di consegna: «In consegna»).

## Chi consegna i pacchi TopYou in Italia

Dopo la dogana, TopYou passa il pacco al corriere che lo consegna. In Italia può essere [GOFO](carrier:gofo-it): sul sito di TopYou il passaggio compare come `Shipment is out for delivery`, e poi arrivano le scansioni di GOFO. Per il resto, la scheda di TopYou può dare il codice del corriere, ma non il suo nome. Su una linea consegnata da una posta, il codice della posta chiude anche la riga `Outbound in sorting center`, dopo `Lastmile Number:`. Neanche il nome della linea TopYou su cui viaggia il pacco ti aiuta: una linea può portare il nome di un altro corriere, e un pacco consegnato dalla posta americana viaggiava su una linea chiamata "DHL Paket". Se non è chiaro, chiedi al negozio chi consegna il tuo ordine e con quale codice.

Se il secondo codice inizia con GFIT o CIIT, Peek lo chiede a GOFO Italy. Se è un codice postale internazionale valido (due lettere, nove cifre con una cifra di controllo corretta, due lettere) e il pacco va in Italia, Peek lo chiede a [Poste Italiane](carrier:poste-italiane), anche se non è detto che consegni Poste.

**Se consegna Poste.** Cerca il secondo codice nel Cerca Spedizioni di poste.it o nell'App Poste Italiane. Per i pacchi dall'estero, Poste dice che il tracciamento parte dalla presa in carico in Italia: prima, poste.it non mostra niente. Se la consegna a casa non riesce, dal Cerca Spedizioni, con «Prenota», puoi fissare un appuntamento all'ufficio postale per ritirarlo, entro i termini di giacenza. Vedi [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

**Dogana e spese.** Dal 1° luglio 2026, sugli acquisti online da fuori UE in ordini fino a 150 € c'è un dazio di 3 € per articolo, oltre all'IVA. Dal 1° novembre 2026 si aggiunge la tassa di gestione dell'Unione, 2 € per articolo, qualunque sia il valore. Un articolo è un gruppo di prodotti con lo stesso codice merci e lo stesso Paese d'origine. Your Europe dice che il dazio lo riscuote il venditore o il vettore, e che può essere già compreso in quello che paghi online. Poste dice di occuparsi dello sdoganamento solo per le spedizioni che rientrano negli accordi e nella Convenzione dell'Unione Postale Universale: negli altri casi non sdogana e non chiede Diritti Postali. Se ti chiedono di pagare alla consegna, vedi [pacco fermo in dogana](guide:customs).

## Pacco TopYou fermo o non arrivato: cosa fare

**Fermo su `Shipment started the customs clearance process at destination`.** Il pacco è alla dogana del Paese di destinazione. Se ti chiedono spese di importazione, vedi [pacco fermo in dogana](guide:customs); se resta lì per giorni, avvisa il negozio. Approfondisci in [tracking che non si aggiorna](guide:tracking-not-updating).

**Niente dopo la dogana.** Dopo `Departed from customs`, `Shipment completed the customs clearance process at destination` o `Import clearance success` il pacco dovrebbe averlo il corriere che consegna. Cerca il suo codice sul sito di TopYou, oppure chiedi al negozio quale corriere consegna e con quale codice. Dopo `Shipment is out for delivery` il pacco è al primo centro di smistamento di GOFO: il giro di consegna arriva qualche giorno dopo. Vedi anche [tracking di un pacco dalla Cina](guide:tracking-from-china).

**Codice non trovato o ancora nessuna scansione.** Il sito di TopYou risponde 查询不到 (non trovato) per un codice che non conosce, e può rispondere 暂无轨迹信息 (ancora nessuna informazione di tracking) per un codice che conosce ma non ha ancora scansionato. In tutti e due i casi Peek mostra «In attesa del corriere» e continua a controllare. Confronta il codice con il messaggio del negozio, carattere per carattere; se dopo qualche giorno non arriva niente, chiedi al negozio se il pacco è partito davvero.

**Consegnato ma non ce l'hai.** Il pacco l'ha consegnato il corriere in Italia: parti dal suo tracking, poi scrivi al negozio. Vedi [pacco consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o rispedito.** Il negozio ha prenotato la spedizione con TopYou, quindi è il negozio, o il marketplace, a cui chiedere un rimborso o un nuovo invio. Vedi [pacco smarrito](guide:lost-parcel) e [pacco rispedito al mittente](guide:returned-to-sender).

## TopYou contatti: numero di telefono e assistenza

Al 4 novembre 2026 il sito di TopYou, [szty56.com](https://www.szty56.com/), indica un numero di telefono a Shenzhen, +86 755 8181 9898, ma nessun orario e nessun servizio per i destinatari. Per il tuo ordine scrivi al negozio, poi al marketplace se il negozio non risponde. Se il pacco ce l'ha Poste Italiane, per consegna e giacenza chiama il servizio clienti di Poste: 803 160, gratuito da rete fissa, o 06 4526 3160 da fisso e cellulare, al costo del tuo operatore, dal lunedì al sabato, festivi esclusi, dalle 8:00 alle 20:00.

## Tracking TopYou con Peek

[Peek](/) riconosce un codice TopYou dalla forma, TYZR, una lettera, dieci cifre e YQ, che nessun altro corriere del suo catalogo usa. Se al posto della R c'è un'altra lettera, la forma è solo un indizio: Peek mostra «Verifica dei servizi di tracciamento…» e chiede a TopYou se conosce il codice. Peek cerca su TopYou solo i codici di queste due forme. Se hai soltanto il codice del corriere che consegna, incolla quello: Peek segue più di 3.500 corrieri. Se Peek sceglie il corriere sbagliato, scegli quello giusto con «Cambia».

Peek legge il sito di tracking di TopYou e mostra lo stato, ogni scansione nelle parole di TopYou o del corriere di cui riporta le scansioni, il Paese di destinazione e il peso registrato da TopYou. Sulle scansioni di TopYou un luogo che è solo un Paese viene tolto: così la scansione all'aeroporto in Cina non sembra fatta in Italia. Quando la scheda di TopYou segna il pacco come consegnato o rispedito, Peek mostra «Consegnato» o «Restituito al mittente», anche se l'ultima scansione non lo dice. Le scansioni di TopYou sono in ora di Pechino. Quelle di GOFO che TopYou riporta portano il fuso orario dell'Italia o della Francia, ma l'ora è in realtà quella UTC: Peek le legge come UTC. Peek mostra tutte le ore nel fuso orario del tuo dispositivo. Non c'è una data di consegna prevista: TopYou non la dà.

Fino al passaggio, Peek mostra solo il codice TopYou. Quando la scheda di TopYou contiene il codice del corriere che consegna, Peek lo prova: con la posta del Paese di destinazione se è un codice postale internazionale valido, cioè Poste Italiane per l'Italia; altrimenti con l'unico corriere a cui quella forma di codice appartiene di sicuro, purché Peek legga il suo tracking, per esempio GOFO Italy per un codice GFIT o CIIT. Appena quel corriere mostra una scansione recente almeno quanto l'ultima di TopYou, Peek segue il pacco lì, mostra tutti e due i codici e tiene le scansioni di TopYou nella cronologia. Un codice che non appartiene di sicuro a un solo corriere, come 20 cifre da sole, non lo prova: Peek resta sul tracking di TopYou, che può mostrare anche le scansioni del corriere.

Peek controlla ogni 10 minuti. Se TopYou non aggiunge scansioni nuove, passa a un controllo all'ora: dopo 12 ore se il pacco è ancora «Etichetta creata» o «Preso in carico», dopo due giorni negli altri casi. Lo stesso succede, o i controlli si diradano ancora, se nessuna notifica può raggiungerti e non guardi il pacco da un'ora. Quando il pacco è «In consegna», Peek controlla ogni 2 minuti (ogni 5 quando il controllo deve aprire un browser): solo il giorno stesso, dalle 6.00 ora locale e finché risponde il tracking di TopYou. Quando rispondono i servizi di tracciamento, ogni 15 minuti. Se la consegna passa a un altro corriere, Peek segue il ritmo di quello che consegna il pacco: ogni ora con Royal Mail, ogni 30 minuti con Poste Italiane, PostNL e USPS, anche in consegna. Puoi ricevere notifiche per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». «Passaggi importanti» ti avvisa per la dogana del Paese di destinazione, come `Shipment started the customs clearance process at destination`, per `The driver is out for delivery` di GOFO e per la consegna, ma non per lo sdoganamento all'esportazione in Cina, per il volo, per lo svincolo dalla dogana o per `Shipment is out for delivery`. Peek non può contattare TopYou, il corriere o il negozio al posto tuo.

## Domande sul tracking TopYou

### Cos'è TopYou (通邮)?

È 深圳市通邮物流科技（集团）股份有限公司, detto anche 通邮集团, Tongyou o SZTY56, uno spedizioniere di Shenzhen, in Cina, con linee internazionali. I suoi codici sono TYZR, una lettera, dieci cifre e YQ, alcuni con un'altra lettera al posto della R.

### Perché TopYou mostra l'Italia quando il pacco è ancora in Cina?

Perché il sito di TopYou scrive il Paese di destinazione accanto alle sue scansioni, anche a quella all'aeroporto di partenza; Peek toglie quel luogo. Conta la frase, non il luogo: `Arrived at airport of the original country` vuol dire che il pacco aspetta ancora il volo.

### Chi consegna i pacchi TopYou in Italia?

Può essere GOFO: sul sito di TopYou il passaggio compare come `Shipment is out for delivery`. Per il resto, la scheda di TopYou può dare il codice del corriere ma non il nome: chiedi al negozio. Con un codice GFIT o CIIT Peek chiede a GOFO Italy, con un codice postale internazionale valido a Poste Italiane.

### Chi contatto se il pacco TopYou non arriva?

Il negozio dove hai ordinato: è lui che ha prenotato la spedizione con TopYou. Se il pacco ce l'ha già Poste Italiane, per la consegna chiama Poste.

:::sources
- [Poste Italiane: FAQ Spedizioni e consegne](https://www.poste.it/assistenza/faq-spedizioni-e-consegne/) – tracking delle spedizioni dall'estero, Prenota, numeri e orari dell'assistenza
- [Poste Italiane: Ricevere spedizioni dall'estero](https://www.poste.it/ricevere-spedizioni-da-estero) – Dazio, Tassa di Gestione dell'Unione, sdoganamento solo per i flussi UPU, Diritti Postali
- [Your Europe: Spedizione e consegna](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_it.htm) – dazio di 3 € per articolo sugli ordini fino a 150 €, riscosso dal venditore o dal vettore, cos'è un articolo
- [Commissione europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tassa di gestione di 2 € per articolo dal 1° novembre 2026
- [TopYou: 通邮集团 (home page, in cinese)](https://www.szty56.com/) – telefono
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – TopYou: società e altri nomi, forma dei codici, maiuscole, nessun link letto, scansioni e loro fasi, passaggio a GOFO in Italia e in Francia e scansioni di GOFO riportate, Paese accanto alle scansioni di TopYou, ora di Pechino e ore di GOFO, secondo codice, codice Lastmile su una linea della posta e nome della linea, codici sconosciuti o senza scansioni; cosa mostra Peek e il passaggio a un altro corriere
:::
