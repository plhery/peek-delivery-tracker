---
title: Tracciamento LinHan International: codici LHGJ e stati
description: Traccia un pacco LinHan International da Shenzhen: formato LHGJ, significato di ogni scansione e perché gli orari usano l’ora di Pechino.
slug: tracciamento-linhan
published: 2026-11-22
updated: 2026-11-22
---

LinHan International (深圳市林瀚国际物流有限公司, Shenzhen LinHan International Logistics Co., Ltd.) è uno spedizioniere di Shenzhen, in Cina, che spedisce per via aerea pacchi di venditori online fuori dalla Cina affidandoli poi a un agente nel paese dell’acquirente per dogana e consegna. I suoi codici iniziano con `LHGJ`: inserisci il tuo sul portale LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), oppure nel riquadro sopra.

## Numero di tracciamento LinHan

LinHan non pubblica un formato ufficiale. Le sue lettere di vettura sono composte da `LHGJ`, otto cifre e `CD` (con cifre inventate):

| Numero | Che cos’è |
| --- | --- |
| `LHGJ48291037CD` | `LHGJ`, otto cifre e `CD`: un codice LinHan International. |
| `LHGJ4829103CD` | Solo sette cifre: non è un formato LinHan. Controlla se manca una cifra. |

Minuscole e spazi non contano quando incolli il numero su Peek.

**Dove trovarlo.** LinHan opera per i venditori: la home page offre loro tariffe in tempo reale e prenotazione sulle rotte, mentre ai destinatari offre solo il tracciamento. Il numero arriva dal negozio in cui hai ordinato; vedi [come trovare un numero di tracciamento](guide:find-tracking-number).

**Sul tracciamento LinHan.** Accetta fino a 20 codici per ricerca, separati da virgole o a capo, e mostra solo i pacchi degli ultimi due mesi.

## Stati di tracciamento LinHan spiegati

LinHan non pubblica un elenco di stati. Il portale è in cinese, ma le scansioni sono in inglese, dalla più recente alla più vecchia. In alto una riga riassuntiva mostra il paese di destinazione e uno stato in cinese:

| LinHan indica | Cosa significa per te |
| --- | --- |
| `运输途中` | «In transito»: il pacco è in viaggio. |
| `成功签收` | «Firmato»: consegnato. |
| `查询不到` | «Non trovato»: il sistema non ha alcun pacco con questo numero e non seguono scansioni. |

Le scansioni dei passaggi di LinHan in Cina, nell’ordine abituale:

| LinHan indica | Cosa significa per te |
| --- | --- |
| `Order information received` | Il venditore ha inviato a LinHan i dati dell’ordine. LinHan non ha ancora il pacco. |
| `Arrive at the cross border receiving point` | LinHan ha ricevuto il pacco al punto di ricezione transfrontaliero in Cina. `Arrive at the receiving point` significa lo stesso. |
| `Goods leave the operation center` | Ha lasciato il centro operativo di LinHan. |
| `Arrival at domestic internationality airport` | All’aeroporto di partenza in Cina. |
| `Departure from the airport of origin domestic` | Il volo è partito dalla Cina. |

Seguono i passaggi nel tuo paese, dove subentrano l’agente di LinHan e la rete che consegna:

| LinHan indica | Cosa significa per te |
| --- | --- |
| `Item arrives at destination airport` | Atterrato nel tuo paese. `Arrive at the destination international airport` significa lo stesso. |
| `local carrier Agent Import customs clearance` | L’agente di LinHan sta sdoganando il pacco all’importazione; non è ancora svincolato. `Import customs clearance`, dopo la città portuale, indica lo stesso passaggio. |
| `transfer to local acting company International self-operation team` | Passato all’agente nel tuo paese. |
| `Internationaliy Package received at processing center` | Grafia originale di LinHan. Prima scansione della rete di consegna in un centro di smistamento. Non è un nuovo inizio: LinHan ha preso il pacco in Cina. |
| `Leave the processing center and transport to … processing center` | In viaggio tra due centri di lavorazione, con la città successiva al posto di «…». `Leave the … processing center` indica quello lasciato. |
| `Arrive at the sorting and distribution center` | In un centro di smistamento, la cui città segue «Arrive». `Leaving the sorting center` ne indica l’uscita. |
| `Arrive at the last mile sorting and distribution center` | Al centro di smistamento della rete di consegna finale, dopo città, stato e codice postale. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Scansione della rete che consegna, con il nome sostituito da «logistics carrier». |
| `Accepted at logistics carrier Regional Destination Facility` | Scansione di quella rete vicino a te, dopo città, stato e codice postale. «Accepted» non è una nuova partenza: il pacco è sempre in transito. |
| `package is on the way` | In viaggio dal centro di smistamento locale, dopo la città. Non indica una consegna prevista per oggi. |
| `Delivered` | Consegnato. |

Molte righe nel tuo paese iniziano con la città (negli USA città, stato e codice ZIP), e la riga di consegna riporta anche il tuo codice postale: città, codice postale, Delivered. La stessa riga può comparire due volte.

**Orari sull’ora di Pechino.** Il tracciamento LinHan scrive ogni orario sull’ora di Pechino (UTC+8), anche per le scansioni nel tuo paese. Poiché a Pechino la data cambia alle 16:00 UTC, una scansione tra quell’ora e la tua mezzanotte porta la data del giorno dopo: in Italia o Germania dalle 17:00 in inverno e 18:00 in estate, a New York dalle 11:00 in inverno e mezzogiorno in estate.

## Chi consegna i pacchi LinHan

La home page di LinHan elenca la filiera: ritiro, stoccaggio e smistamento, sdoganamento in Cina, trasporto aereo, dichiarazione doganale all’estero, magazzini esteri e consegna (揽货, 仓储分拣, 国内通关, 空运, 海外报关, 海外仓, 海外派送). Non nomina rotte né destinazioni; i pacchi osservati sul portale erano diretti negli Stati Uniti e in Germania.

Nel tuo paese subentra un agente, chiamato sul portale «local carrier Agent» e «local acting company», mentre la rete di consegna è chiamata «logistics carrier», senza nome né secondo numero di tracciamento. Lo storico prosegue però fino alla consegna, quindi il portale LinHan resta il punto di riferimento.

LinHan non pubblica tempi di consegna né regole per tentativi, punti di ritiro o giacenza. Se un avviso o messaggio del corriere locale ne indica il nome, valgono le sue regole: vedi [consegna mancata](guide:missed-delivery).

## Pacco LinHan fermo o non arrivato

**Solo `Order information received`.** Il venditore ha prenotato la spedizione, ma il pacco non è ancora arrivato a LinHan. Se per giorni non segue nulla, chiedi al venditore se ha spedito.

**Nulla dopo il volo.** Dopo `Departure from the airport of origin domestic` la riga successiva compare all’atterraggio o in dogana nel tuo paese. Altro in [tracciamento dalla Cina](guide:tracking-from-china) e [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**In dogana.** Il portale LinHan non indica se devi pagare dazi né nomina il corriere finale, quindi un SMS che chiede un pagamento è difficile da verificare: leggi [truffe via SMS sulle consegne](guide:delivery-scam-texts) prima di pagare e [pacchi fermi in dogana](guide:customs).

**`查询不到`.** Il sistema non riconosce il numero: verificalo con il venditore. Poiché mostra solo spedizioni degli ultimi due mesi, non trova neppure pacchi più vecchi.

**Consegnato ma non arrivato.** Se l’ultima riga dice `Delivered`, leggi [consegnato ma non ricevuto](guide:delivered-not-received) e avvisa il venditore. LinHan non mostra foto di consegna.

**Smarrito, danneggiato o restituito.** LinHan non pubblica procedure di reclamo, rimborso o reso. Ha prenotato il venditore, quindi chiedi al venditore o al marketplace un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel).

## Contatti LinHan International

La home page di LinHan, solo in cinese, termina con un blocco 联系我们 (Contattaci): un indirizzo e-mail e un indirizzo di servizio a Pinghu, distretto di Longgang, Shenzhen. Non fornisce telefono, chat, modulo né assistenza per i destinatari: per qualsiasi problema sull’ordine rivolgiti al venditore.

## Tracciare un pacco LinHan con Peek

[Peek](/) riconosce un codice LinHan dalla forma (`LHGJ`, otto cifre e `CD`), che nessun altro corriere del catalogo usa, e mostra subito «Corriere rilevato». Anche un link al tracciamento LinHan con `nos=` e il numero può essere incollato.

Peek legge il portale LinHan e mostra lo stato, ogni scansione con la città quando indicata e il paese di destinazione. Sposta la città fuori dal testo nel campo località ed esclude i codici postali, così una riga `Delivered` appare come «Consegnato» nella sua città. Le righe duplicate compaiono una volta sola e le altre scansioni mantengono l’inglese di LinHan, `Internationaliy` compreso. Se la riga di consegna di LinHan nomina chi ha firmato (`Delivered, signed by …`), il nome resta in quella riga in inglese e non compare altrove; un link condiviso mostra la riga senza il nome. Peek legge ogni orario sull’ora di Pechino (UTC+8), come scritto dal portale, e lo converte nel tuo fuso orario («Orari nel tuo fuso orario:»).

Alcune righe contano diversamente da come sembrano: `Order information received` è «Etichetta creata»; `Internationaliy Package received at processing center` e `Accepted at logistics carrier Regional Destination Facility` restano «In transito», non una nuova accettazione; le due righe doganali sono «In dogana»; `package is on the way` resta «In transito», non indicando una consegna in giornata.

LinHan non fornisce data prevista di consegna, peso, nome del servizio, corriere finale né un secondo codice: Peek quindi non li mostra e segue il numero `LHGJ` fino alla fine senza cambiare corriere.

Peek controlla fino a ogni 10 minuti mentre il pacco è aperto in Peek, o nel giorno di consegna con le notifiche attive, quando il portale LinHan risponde. Dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora, e dopo sette giorni senza novità su «Etichetta creata», ogni 6 ore. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Dopo quattro giorni senza scansioni su un invio in «Etichetta creata», «Preso in carico» o «In transito», Peek mostra «Nessun aggiornamento da …» e ricorda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a LinHan International.» Peek non può contattare LinHan né il venditore per te.

## Domande sul tracciamento LinHan

### Che cos’è LinHan International?

Shenzhen LinHan International Logistics (深圳市林瀚国际物流有限公司), uno spedizioniere su linhan56.com che spedisce in aereo i pacchi dei venditori fuori dalla Cina verso un agente che si occupa di dogana e consegna.

### Dove posso tracciare un codice LHGJ?

Sul tracciamento LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), oppure su Peek. La home page di LinHan rimanda anche a 17TRACK.

### Chi consegna i pacchi LinHan?

Un agente nel tuo paese, chiamato dal tracciamento «local carrier» o «local acting company» senza indicarne il nome. Le sue scansioni compaiono sul tracciamento LinHan fino alla consegna.

### Perché gli orari di LinHan sembrano avanti di un giorno?

Perché il portale LinHan registra ogni orario sull’ora di Pechino (UTC+8), anche per le scansioni nel tuo paese. Una scansione dopo le 16:00 UTC, quando a Pechino è già il giorno dopo, riporta quindi la data successiva. Peek mostra gli orari nel fuso orario del tuo dispositivo.

### Chi contatto per un pacco LinHan smarrito?

Il venditore da cui hai ordinato, che ha prenotato il trasporto con LinHan. La home page di LinHan riporta solo un’e-mail e un indirizzo a Shenzhen, nessun canale per i destinatari.

:::sources
- [LinHan International: 深圳市林瀚国际物流有限公司 (home)](https://www.linhan56.com/) – nome azienda, trasporto aereo transfrontaliero per venditori, filiera dal ritiro alla consegna estera, link a tracciamento e 17TRACK, blocco contatti
- [LinHan International: 快件跟踪 (tracciamento)](https://www.linhan56.com/index/tracknumber/track) – portale in cinese, pacchi degli ultimi due mesi, fino a 20 codici per ricerca
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato LHGJ, stati riepilogativi e testi delle scansioni, agente di destinazione e rete anonima, orologi su Pechino, città e codici postali, destinazioni osservate, cosa Peek mostra ed esclude
:::
