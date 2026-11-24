---
title: Tracciamento A Tu Hora Express: codici, CAP e aiuto
description: Traccia un pacco A Tu Hora Express in Spagna: codici da 10, 16 e 24 cifre, perché serve il CAP a 5 cifre, stati spagnoli, incidenti e contatti.
slug: tracciamento-a-tu-hora-express
published: 2026-11-24
updated: 2026-11-24
---

A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) è un corriere spagnolo dell’ultimo miglio con sede a San Fernando de Henares (Madrid) e filiali in tutta la Spagna continentale, tra cui Madrid, Barcellona, Valencia, Siviglia, Malaga, Alicante, Murcia, Almería, Cadice, Castellón, Cordova, Girona, Granada, Huelva, Jaén, Lleida e Tarragona. Consegna ordini e-commerce e B2B con servizi quali *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) e *B2B Premium*. Puoi cercare un pacco sul [portale di tracciamento di A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) oppure incollare il codice di tracciamento nel campo qui sopra.

## Codici di tracciamento A Tu Hora Express e codice postale di consegna

Cerca il numero di servizio o di tracciamento nell’e-mail di spedizione del negozio o nella pagina dell’ordine. Le spedizioni A Tu Hora Express usano tre lunghezze di sole cifre:

| Formato (inventato) | Che cos’è |
| --- | --- |
| `0000004829173640` | Numero di servizio A Tu Hora Express a 16 cifre (`000000` + 10 cifre). Richiede il tuo CAP spagnolo di consegna a 5 cifre sul tracker. |
| `4829173640` | Codice breve a 10 cifre (completato con sei zeri iniziali per formare il numero di servizio a 16 cifre). Richiede anch’esso il CAP di consegna a 5 cifre. |
| `000000482917364000128013` | Codice a barre completo a 24 cifre: il numero di servizio a 16 cifre + `001` + il CAP spagnolo di consegna a 5 cifre (qui `28013`). Condivide la lunghezza a 24 cifre con [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) ed [Express One](carrier:express-one). |

**Perché il codice postale è importante.** Su [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), i codici da 10 e 16 cifre mostrano i dettagli del pacco solo se abbinati al **CAP spagnolo di consegna a 5 cifre**. Il codice a barre a 24 cifre include già quel CAP nelle ultime cinque cifre, quindi funziona senza doverlo inserire a parte. Per altri formati spagnoli e internazionali, vedi [formati dei codici di tracciamento](guide:tracking-number-formats).

## Cosa significano gli stati di tracciamento di A Tu Hora Express

A Tu Hora Express riporta ogni scansione in spagnolo, con un codice stato (`cod`) e l’orario sull’ora di Madrid:

| A Tu Hora Express indica (codice) | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `Documentado` (`0`) | Il mittente ha registrato elettronicamente la spedizione prima della consegna in filiale. | «Etichetta creata» |
| `En tránsito` (`1`) | In transito tra filiali nella rete spagnola di A Tu Hora Express. | «In transito» |
| `En agencia distribución` (`11`) | Arrivato alla filiale locale spagnola competente per il tuo codice postale. | «In transito» |
| `En reparto` (`2`) / `En realización` | Affidato al corriere locale per la consegna odierna. | «In consegna» |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | La consegna non è andata a buon fine (destinatario assente, indirizzo incompleto, rifiuto o nuova uscita in coda). | «Tentativo di consegna» |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Incidente operativo come un collo mancante in una spedizione multicollo o una mancata consegna della filiale. | «Problema segnalato» |
| `Pendiente de realizar` (`5`) | Abbinato a un incidente con lo stesso orario per indicare che una nuova consegna è in attesa; la riga dell’incidente definisce la fase. | *(mantiene la fase dell’incidente)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Consegnato da A Tu Hora Express. | «Consegnato» |
| `Devuelto` | In restituzione al mittente. | «Restituito al mittente» |

## Servizi di consegna e informazioni pubblicate sul sito ufficiale

Su [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) e [atuhoraexpress.com](https://atuhoraexpress.com/), A Tu Hora Express elenca cinque servizi principali — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* e *B2B Premium* — sulla propria rete spagnola di filiali, con tracciamento tramite [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) e [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

Il sito di A Tu Hora Express non pubblica il numero di tentativi di consegna, i limiti di peso o dimensioni né i tempi di giacenza. Se l’ordine proviene da fuori dell’Unione europea, si applicano 3 € di dazio doganale per articolo quando il valore è pari o inferiore a 150 € e il contributo di gestione dell’Unione di 2 € per articolo, salvo siano già stati addebitati dal negozio al checkout; vedi [pacchi fermi in dogana](guide:customs).

## Pacco A Tu Hora Express in ritardo, bloccato o smarrito

Poiché è il negozio online a detenere il contratto di trasporto con A Tu Hora Express, contatta prima il rivenditore se un pacco resta bloccato più volte in `Incidencia`, segnala un problema di indirizzo (`Dirección incorrecta o incompleta`) o supera il giorno di consegna previsto.

- **Incidenti ripetuti o `Pendiente nuevo reparto`:** conferma via, piano/interno e numero di telefono con il venditore e scrivi a `info@atuhoraexpress.com` indicando il codice a 16 o 24 cifre e il CAP di consegna.
- **Segnato come `Realizado con éxito` ma non ricevuto:** controlla prima con familiari, vicini e portineria (*portería*), poi contatta subito il negozio e A Tu Hora Express.
- Per i passaggi successivi, vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating), [consegnato ma non ricevuto](guide:delivered-not-received), [pacco danneggiato](guide:damaged-parcel), [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Assistenza clienti e contatti A Tu Hora Express

Contatti ufficiali pubblicati su [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) e [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Telefono:** `911 631 356` (Spagna).
- **E-mail clienti:** `info@atuhoraexpress.com`.
- **Modulo feedback:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **E-mail protezione dati:** `protecciondedatos@atuhoraexpress.com`.
- **Sede di Madrid:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Spagna (sede legale di *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Fai attenzione a SMS o messaggi WhatsApp che chiedono un piccolo pagamento con carta per riprogrammare una consegna A Tu Hora Express. Verifica il pacco direttamente su [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) o scrivendo a `info@atuhoraexpress.com`. Vedi [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Tracciare un pacco A Tu Hora Express con Peek

[Peek](/) riconosce automaticamente con elevata sicurezza qualsiasi codice a 16 cifre che inizia con sei zeri (`0000004829173640`, cifre inventate) come A Tu Hora Express. Un codice a barre a 24 cifre (`000000482917364000128013`) coincide con più corrieri: Peek interroga A Tu Hora Express (con il CAP presente nel codice a barre), [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) e [Ciblex](carrier:ciblex) per sapere se lo conoscono, e [Express One](carrier:express-one) solo se nessuno lo riconosce, mostrando «A Tu Hora Express ha questo pacco.» quando A Tu Hora Express risponde. Per un codice a 10 cifre (`4829173640`), tocca «Cambia» mentre aggiungi o «Cambia corriere» nella schermata del pacco e scegli A Tu Hora Express (cercare «A Tu Hora» lo trova ugualmente).

Per i codici a 10 e 16 cifre, Peek offre il campo facoltativo «Codice postale di consegna» con la nota «A Tu Hora Express mostra il pacco solo con il codice postale di consegna.». Finché non lo inserisci, la schermata del pacco propone «Aggiungi codice postale» e Peek segue il pacco tramite servizi di tracciamento, controllando una volta al giorno dopo un giorno senza risposta. Inserendo il codice postale Peek verifica subito. Un codice a barre a 24 cifre include il CAP nelle ultime cinque cifre e quindi non ne ha bisogno.

Quando A Tu Hora Express risponde, Peek mostra la fase («Etichetta creata», «In transito», «In consegna», «Tentativo di consegna», «Problema segnalato», «Restituito al mittente», «Consegnato»), il testo spagnolo della scansione con la località, il giorno di consegna previsto finché A Tu Hora Express lo mostra su una spedizione aperta, e il peso. I codici stato definiscono la fase; Peek non li visualizza. Le scansioni sono datate sull’ora di Madrid e mostrate sull’orologio del tuo dispositivo. Peek omette coordinate GPS accanto alle scansioni, barra di avanzamento, data di partenza, riferimento cliente, nomi di mittente e destinatario, indirizzo di destinazione, numero di colli, volume, note, prova di consegna e opzioni di consegna; il tracker di A Tu Hora Express non indica punto di ritiro né nome del servizio.

Mentre hai il pacco aperto in Peek, oppure il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti; il giorno in cui A Tu Hora Express mostra `En reparto` o `En realización`, ogni 2 minuti dalle 6:00 ora di Madrid, finché il tracker di A Tu Hora Express risponde. Altrimenti Peek controlla ogni ora. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», oppure dopo due giorni senza novità, controlla ogni ora comunque lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracker di A Tu Hora Express non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando l’ultima scansione risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con la data di quella scansione, come «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra allora «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a A Tu Hora Express.» Dopo tre settimane senza nuovi aggiornamenti mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a A Tu Hora Express.»

Puoi scegliere gli avvisi per «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek si limita a monitorare gli aggiornamenti di tracciamento e non può riprogrammare consegne, modificare il tuo indirizzo né contattare A Tu Hora Express al posto tuo.

## Domande frequenti sul tracciamento A Tu Hora Express

### Perché A Tu Hora Express chiede il mio codice postale di consegna?

A Tu Hora Express mostra un codice da 10 o 16 cifre solo insieme al relativo CAP spagnolo di consegna a 5 cifre, su [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) così come in Peek, dove il campo è facoltativo ma senza il quale A Tu Hora Express non risponde. Se il negozio ti ha dato un codice a 24 cifre, le ultime cinque cifre contengono già il CAP.

### Cosa significa `Realizado con éxito` nel tracciamento A Tu Hora Express?

`Realizado con éxito` (codice stato `4`) indica che A Tu Hora Express ha registrato la spedizione come consegnata. Se non ce l’hai, controlla con familiari, vicini e portineria, poi contatta il negozio e `info@atuhoraexpress.com`.

### Cosa significa `Incidencia - Pendiente nuevo reparto`?

Significa che un tentativo di consegna non è riuscito e il pacco è in coda per un nuovo giro di consegne (spesso affiancato da `Pendiente de realizar` con lo stesso orario). Se si ripete, verifica indirizzo e telefono con il venditore.

### Come contatto A Tu Hora Express in Spagna?

Chiama il `911 631 356`, scrivi a `info@atuhoraexpress.com` oppure compila il modulo [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) con il tuo codice di tracciamento e il CAP di consegna a 5 cifre.

:::sources
- [A Tu Hora Express: Home (spagnolo)](https://atuhoraexpress.com/) – corriere spagnolo dell’ultimo miglio, filiali, sede di Madrid, `911 631 356` e `info@atuhoraexpress.com`
- [A Tu Hora Express: Home (inglese)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium ed elenco filiali
- [A Tu Hora Express: pagina di tracciamento](https://atuhoraexpress.com/tracking/) – punto di accesso al tracciamento sul sito
- [A Tu Hora Express: portale MyDelivery](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – portale diretto di ricerca spedizioni
- [A Tu Hora Express: note legali](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, sede legale ad Alovera (Guadalajara) e `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: modulo feedback](https://atuhoraexpress.com/ayudanos-a-mejorar/) – modulo feedback clienti
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – rilevamento 10/16/24 cifre, requisito facoltativo del CAP a 5 cifre, codici stato spagnoli (`0`, `1`, `11`, `2`, `3`, `4`, `5`), ora locale `Europe/Madrid`, data di consegna prevista, peso e campi esclusi
:::
