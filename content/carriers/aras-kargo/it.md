---
title: Tracciamento Aras Kargo: numeri e stati in italiano
description: Traccia un pacco Aras Kargo: formato del numero a 13 cifre, significato in italiano di stati turchi come TESLİMAT ŞUBESİNDE e seguito con Peek.
slug: tracciamento-aras-kargo
published: 2026-11-22
updated: 2026-11-22
---

Aras Kargo (scritto anche Aras Cargo) è un corriere turco. Il suo tracker, come letto da Peek, scrive ogni passaggio in turco. Il negozio o il mittente ti fornisce il codice di tracciamento: incollalo nel riquadro in alto. Il sito di tracciamento di Aras Kargo è kargotakip.araskargo.com.tr. Le tabelle qui sotto spiegano gli stati turchi in italiano.

## Numero di tracciamento Aras Kargo: come è fatto

Il codice di Peek riconosce i numeri Aras Kargo come sequenze di 13 cifre. Quelli collegati automaticamente ad Aras iniziano con 60 o 65. Esempi inventati:

| Numero (inventato) | Cos’è |
| --- | --- |
| `6048291736542` | 13 cifre che iniziano con 60: il formato che Peek associa ad Aras Kargo. Anche altri corrieri, tra cui [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda e Korea Post, usano 13 cifre. |
| `6548291736542` | 13 cifre che iniziano con 65: lo stesso. |
| `1482917365482` | 13 cifre con altro prefisso: Peek non le collega d’ufficio ad Aras, ma Peek interroga il tracker di Aras per qualsiasi numero di 13 cifre. Se il negozio indica Aras Kargo, seleziona Aras Kargo manualmente (vedi sotto). |
| `604829173654` | Solo 12 cifre. Se scegli Aras Kargo per questo codice, Peek risponde che «non emette numeri come questo». Controlla che non manchi una cifra. |

Gli spazi non contano quando incolli il numero in Peek. Funziona anche un link al tracker di Aras con il codice (kargotakip.araskargo.com.tr/mainpage.aspx con `code=` e il numero).

**Dove trovarlo.** Nell’email di spedizione del negozio o nella pagina dell’ordine. Se non lo trovi, consulta [come trovare il numero di tracciamento](guide:find-tracking-number) e [formati dei numeri di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento Aras Kargo in italiano

Il tracker di Aras mostra lo stato attuale (`Son Durum`), una data di consegna prevista e l’elenco dei movimenti di veicoli (`Araç Hareketleri`). Ogni movimento indica data e ora (`İşlem Tarihi`), provincia (`İl`), unità (`Birim`, filiale o centro di smistamento) e descrizione (`İşlem Açıklaması`). Quando capita, elenca anche i giorni di mancata consegna e i reindirizzamenti verso un’altra filiale.

**Stato attuale.** Uno di questi in cima alla scheda:

| Aras scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | Presso la filiale di origine che ha preso in carico il pacco. | «Preso in carico» |
| `YOLDA` | In viaggio tra i centri di smistamento di Aras. | «In transito» |
| `TESLİMAT ŞUBESİNDE` | Presso la filiale di consegna che recapita il pacco al tuo indirizzo. Resta uguale anche dopo un tentativo fallito. | «In transito» |
| `TESLİM EDİLDİ` | Consegnato. Aras riporta giorno e ora (se l’orario manca, lo stato resta «Consegnato» senza evento di consegna). | «Consegnato» |

**Movimenti di veicoli.** Ogni scansione di carico, scarico o insaccamento:

| Aras scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Caricato su un veicolo in filiale. | «In transito» |
| `ŞUBEDE ARAÇ İNDİRME` | Scaricato da un veicolo in filiale. | «In transito» |
| `AKTARMADA HAT ARACI YÜKLEME` | Caricato su un mezzo di linea in un centro di smistamento. | «In transito» |
| `AKTARMADA HAT ARACI İNDİRME` | Scaricato da un mezzo di linea in un centro di smistamento. | «In transito» |
| `AKTARMADA SUBE ARACI YUKLEME` | Caricato su un veicolo di filiale in un centro di smistamento. | «In transito» |
| `AKTARMADA SUBE ARACI INDIRME` | Scaricato da un veicolo di filiale in un centro di smistamento. | «In transito» |
| `AKTARMADA TORBA DOLDURMA` | Inserito in un sacco con altri pacchi in un centro di smistamento. | «In transito» |

Il tracker scrive le due righe con `SUBE` senza caratteri turchi, esattamente come sopra.

**Consegna non effettuata** (`Teslim Edilememe Nedeni`, motivo). Una riga per ogni giorno interessato:

| Aras scrive | Cosa significa per te | Peek mostra |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | Il pacco non ha potuto essere consegnato quel giorno. | «Tentativo di consegna» |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Nessuna consegna a causa di una festività. | «Tentativo di consegna» |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | Il corriere è passato e ha lasciato un avviso. | «Tentativo di consegna» |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Il tuo indirizzo è fuori dalla zona di consegna della filiale. | «Tentativo di consegna» |

**Reindirizzamento.** `YÖNLENDİRİLDİ` indica che il pacco è stato inoltrato a un’altra filiale. Aras indica il motivo e Peek lo aggiunge tra parentesi dopo la parola. Peek mostra «In transito».

Questi ultimi due tipi di riga riportano solo la data, senza orario. Il tracker di Aras può mostrare anche una nota libera del corriere accanto a un mancato recapito: Peek la omette. Altri dettagli sulle fasi in [cosa significano gli stati di tracciamento](guide:tracking-statuses).

## Consegna Aras Kargo in Turchia

Un pacco parte dalla filiale Aras di accettazione (`ÇIKIŞ ŞUBESİNDE`), passa attraverso i centri di smistamento (le righe con `AKTARMADA`) fino alla filiale di consegna (`TESLİMAT ŞUBESİNDE`), che lo porta al tuo indirizzo.

**Giorno di consegna previsto.** Il tracker di Aras fornisce una data. Peek la mostra come consegna stimata finché il pacco è in movimento e il giorno non precede l’ultima scansione. La rimuove dopo un tentativo fallito o trascorso quel giorno.

**Fuori zona di consegna.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` significa che la filiale non serve il tuo indirizzo: chiedi al negozio come procedere.

**Cosa questa pagina non può dirti.** Peek non è riuscito a leggere le pagine del sito di Aras Kargo, quindi qui non trovi tempi di consegna, numero di tentativi, giorni di giacenza né regole di ritiro. Il tracker di Aras, per come lo legge Peek, non indica punti di ritiro, peso né nome del servizio. In caso di assenza, vedi [consegna mancata](guide:missed-delivery) e [pacco non ritirato](guide:uncollected-parcel).

## Tracciamento Aras Kargo fermo, pacco perso o restituito

**Nessun risultato.** Quando Aras non conosce il numero, il tracker risponde `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (nessun risultato trovato). Il negozio potrebbe non aver ancora affidato il pacco. Peek continua a chiedere: una spedizione salvata sotto Aras Kargo mostra «In attesa di Aras Kargo». Controlla le 13 cifre con il negozio; se non compare nulla dopo qualche giorno, chiedi se ha consegnato davvero il pacco ad Aras.

**Fermo su `TESLİMAT ŞUBESİNDE`.** La filiale di consegna ha il pacco e lo stato resta identico anche dopo un tentativo fallito. Se per una giornata compare un motivo (tabella sopra), il pacco non è stato consegnato quel giorno e la riga spiega perché. Altrimenti attende di uscire in consegna. Poiché Aras non ha una voce di uscita per la consegna, Peek mostra «In transito» fino alla consegna o a un tentativo fallito.

**Nessun aggiornamento per giorni.** Dopo quattro giorni senza nuove scansioni su un pacco in movimento, Peek mostra nella tua lista il giorno dell’ultima scansione («Nessun aggiornamento da …») e nella scheda «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Aras Kargo.», con il link «Chiedi aiuto a Aras Kargo» alla sua pagina contatti solo in quell’avviso. Nel fine settimana i pacchi restano spesso fermi. Vedi [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

**Segnato come consegnato ma non arrivato.** Chiedi a vicini e familiari. Il tracker di Aras indica, oscurato, chi ha ritirato il pacco (`Teslim Alan`): Peek non mostra questo nome, quindi controllalo sul tracker di Aras. Poi contatta il negozio e Aras. Vedi [consegnato ma non ricevuto](guide:delivered-not-received).

**Perso o restituito.** Peek non mappa ancora una dicitura specifica di reso su Aras. Poiché la spedizione è stata prenotata dal negozio, chiedi prima al negozio un rimborso o una nuova spedizione. Vedi [pacco smarrito](guide:lost-parcel) e [restituito al mittente](guide:returned-to-sender).

## Servizio clienti e contatti Aras Kargo

Peek non è riuscito a leggere il sito web di Aras Kargo, quindi questa pagina non elenca telefono né email: Peek riporta solo contatti letti direttamente sulle pagine ufficiali del corriere.

Per un ordine online chiedi prima al negozio che ha prenotato il trasporto. In Peek, il luogo di ogni movimento indica la filiale o il centro di smistamento, e il luogo di consegna indica la filiale che ha consegnato: fornisci quel nome quando chiedi assistenza.

## Tracciare un pacco Aras Kargo con Peek

[Peek](/) non può riconoscere Aras Kargo solo dal formato del codice, perché anche altri corrieri usano 13 cifre. Incollalo comunque: Peek segue oltre 4.500+ corrieri.

- **13 cifre che iniziano con 60 o 65.** Peek chiede contemporaneamente ad Aras Kargo e agli altri corrieri che può interrogare su quel numero (Ukrposhta per `6048291736542`, FedEx Poland per `6548291736542`). Mentre controlla vedi «Verifica dei servizi di tracciamento…». Se solo Aras Kargo conferma il pacco, Peek mostra «Aras Kargo ha questo pacco.» e lo associa ad Aras; se lo conoscono più corrieri, Peek ti chiede quale scegliere.
- **Altre 13 cifre o pacco non ancora trovato.** Cambia corriere e seleziona Aras Kargo (scrivi Aras), così Peek interroga direttamente il tracker di Aras. Anche su un pacco già salvato puoi cambiare corriere dalla sua scheda.
- **Link al tracker di Aras.** Peek estrae il numero e individua subito Aras Kargo («Corriere rilevato»).

Aggiunto senza corriere, un pacco mostra «Nessuna cronologia di tracciamento per ora» mentre Peek continua a interrogare corrieri e servizi di tracciamento. Se un servizio di tracciamento indica Aras Kargo come unico corriere con passaggi datati, Peek lo associa ad Aras.

**Cosa mostra Peek.** Lo stato attuale, ogni movimento con orario e luogo (filiale o centro di smistamento e provincia), i giorni di consegna mancata, i reindirizzamenti, la data di consegna prevista, l’orario e la filiale di consegna, nonché il nome oscurato del destinatario mostrato nel riepilogo, ma non quello di chi ha ricevuto il pacco (`Teslim Alan`). Esclude il nome in chiaro nascosto nella pagina, il mittente, il codice di consegna, la verifica d’identità, i dati di pagamento e fattura, volume e numero di colli, barra di avanzamento, note libere e scheda SMS. Peek legge gli orari di Aras sull’ora turca e li converte nel tuo fuso orario (la cronologia termina con «Orari nel tuo fuso orario:» seguito dal nome). Il link «Apri il sito Aras Kargo» apre il numero direttamente sul tracker di Aras.

Quando il tracker di Aras non è raggiungibile, Peek interroga servizi di tracciamento e mostra la cronologia che restituiscono, con le loro parole.

Mentre tieni il pacco aperto sullo schermo — oppure il giorno della consegna con notifiche attive — Peek controlla direttamente Aras Kargo fino a ogni 10 minuti (senza intervallo di 2 minuti, perché Aras non registra uno stato in consegna). Altrimenti, e dopo due giorni senza novità (12 ore finché resta su «Etichetta creata» o «Preso in carico»), controlla ogni ora. Per le notifiche puoi scegliere «Ogni scansione», «Passaggi importanti» o «Solo la consegna». Peek non può contattare Aras Kargo o il negozio al posto tuo.

## Domande sul tracciamento Aras Kargo

### Cosa significa TESLİMAT ŞUBESİNDE su Aras Kargo?

Significa «presso la filiale di consegna»: la filiale Aras che porta il pacco al tuo indirizzo lo ha in custodia. Lo stato resta uguale anche dopo un tentativo fallito: controlla se compare una riga con motivo, che Peek mostra come «Tentativo di consegna».

### Cosa significa YOLDA su Aras Kargo?

Significa «in viaggio»: il pacco si sposta tra i centri di smistamento di Aras verso la filiale di consegna. Peek mostra «In transito».

### Come è fatto un numero di tracciamento Aras Kargo?

È composto da 13 cifre nel codice di Peek. Quelli che iniziano con 60 o 65, come `6048291736542`, vengono associati ad Aras Kargo. Per altri numeri a 13 cifre, seleziona Aras Kargo manualmente.

### Perché Aras Kargo non trova il mio numero di tracciamento?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` significa che Aras non ha ancora risultati per quel numero. Il negozio potrebbe non aver consegnato il pacco. Controlla le 13 cifre, aspetta un giorno e poi chiedi al negozio.

### Come posso contattare Aras Kargo?

Chiedi prima al negozio che ha prenotato la consegna. Poiché Peek non ha potuto leggere il sito di Aras Kargo, questa pagina non riporta numeri di telefono né email.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – nomi, formato a 13 cifre e prefissi 60 e 65, tracker e link, sezioni e diciture turche con le relative fasi, avviso non trovato, giorno previsto di consegna, ora turca, campi letti ed esclusi da Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – altri corrieri con lo stesso formato, riconoscimento automatico e fallback sui servizi di tracciamento
:::
