---
title: Tracciamento Mayi Yinfu: codici MY, voli e consegna DHL
description: Traccia un pacco Mayi Yinfu (蚂蚁银服) dalla Cina continentale: numeri MY…US e MYYF…CN, stati dei voli, tappe scritte in anticipo e passaggio a DHL in Europa.
slug: tracciamento-mayi-yinfu
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) è il nome breve di `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), uno spedizioniere del Guangdong nella Cina continentale che invia piccoli pacchi e spedizioni espresse all'estero. I suoi codici di tracciamento sono formati da `MY`, dieci cifre e `US` (oppure `MYYF`, otto cifre e `CN`): incolla il tuo nel riquadro qui sopra o inseriscilo nella casella `快递查询` su `mayiyinfu.com.cn`. Mayi Yinfu trasporta i colli per via aerea fino alla tua regione e li affida poi al corriere locale — verso l'Europa, Deutsche Post [DHL](carrier:dhl).

## Numero di tracciamento Mayi Yinfu: com'è fatto

`MY` deriva da `蚂蚁` (*mǎyǐ*, formica). Ecco i formati riconosciuti da Peek, con cifre inventate:

| Numero (inventato) | Cos'è |
| --- | --- |
| `MY4829105736US` | `MY`, 10 cifre e `US` (14 caratteri): codice esclusivo di Mayi Yinfu, riconosciuto subito da Peek. |
| `MYYF48291057CN` | `MYYF` (iniziali di `蚂蚁银服`), 8 cifre e `CN` (14 caratteri): secondo formato gestito dal tracker di Mayi Yinfu. |
| `MY482910573US` | Solo 9 cifre invece di 10: Peek segnala: «Inizia come un numero di tracciamento di Mayi Yinfu, ma manca un carattere: forse c’è un errore di battitura. Confrontalo con il messaggio di spedizione.» |
| `RR482910576CN` | Due lettere, 9 cifre e `CN`: una spedizione postale [China Post](carrier:china-post), non Mayi Yinfu. |

Spazi e minuscole non contano in Peek. Poiché Mayi Yinfu lavora per aziende mittenti, il codice ti viene fornito dal negozio online ([come trovare il numero di tracciamento](guide:find-tracking-number)); il tracker di Mayi Yinfu accetta anche il riferimento d'ordine del venditore digitato esattamente uguale (trattini inclusi).

## Significato degli stati di tracciamento Mayi Yinfu

Mayi Yinfu non pubblica un elenco ufficiale degli stati. Le sue righe sono in cinese (spesso seguite da una traduzione inglese), mentre dopo l'affidamento il tracker copia in inglese le scansioni del corriere finale:

| Stato Mayi Yinfu | Cosa significa per il tuo pacco | Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` | Dati elettronici ricevuti sulla piattaforma Hualei (`华磊`, usata anche da BYHD): ordine registrato, nessuna scansione fisica. | «Etichetta creata» |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Copiato da DHL: i dati dell'etichetta sono stati trasmessi elettronicamente a DHL, senza il pacco. | «Etichetta creata» |
| `已入库,到达收货点` | Registrato nel magazzino di accettazione: Mayi Yinfu ha il tuo pacco. | «Preso in carico» |
| `进仓时间：…；Warehouse entry time:…`, `已出库,货物离开操作中心`, `国内清关完成` | Ora di ingresso in magazzino, uscita dal centro operativo e sdoganamento d'esportazione completato nella Cina continentale. | «In transito» |
| `货物已交机场` | Consegnato all'aeroporto di partenza (**non** a casa tua, malgrado l'inglese *delivered to the airport*), seguito da `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` o dal giorno del volo previsto. | «In transito» |
| `航班延误` | Volo in ritardo, con il giorno stimato del volo successivo. | «In transito» |
| `航班已起飞`, `航班落地`, `头程起飞`, `头程落地`, `二程起飞` | Decollo dall'origine e atterraggio (`航班已起飞`, `航班落地`), partenza e atterraggio della prima tratta (`头程起飞`, `头程落地`) e decollo della coincidenza (`二程起飞`, tradotto per errore *Flight 202* sul tracker). | «In transito» |
| `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Atterrato nella regione di destinazione; apertura dei bancali e sdoganamento d'importazione in corso. | «In dogana» |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Dogana completata e passaggio al corriere incaricato della consegna, il cui numero compare ora nella scheda. | «In transito» |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Copiati da DHL: lavorazione nei centri pacchi DHL in Germania. | «In transito» |
| `The shipment has arrived in the destination country/destination area.` | Arrivo nel tuo Paese (talvolta seguito tra parentesi dal link al corriere locale). | «In transito» |
| `The shipment has been loaded onto the delivery vehicle` | Caricato sul furgone per la consegna finale. | «In consegna» |
| `The shipment has been successfully delivered` | Consegnato. | «Consegnato» |

**Righe generate da script (`CO_C_CR`).** Presi da soli, gli stati `国内清关中` (dogana export), `已排航班` (volo prenotato), `起运地航班起飞` (volo partito), `已到达目的国` (arrivato nel Paese di destinazione) e `清关完成` (sdoganato) provengono da uno script automatico che può segnare il pacco all'estero prima ancora che arrivi in aeroporto: Peek non li conta come scansioni ([cosa significano gli stati di tracciamento](guide:tracking-statuses)).

## Chi consegna i pacchi Mayi Yinfu a destinazione?

Il sito di Mayi Yinfu non elenca partner di recapito, ma dopo `已转运transferred` il record mostra il numero del vettore successivo. **Verso l'Europa**, è un numero Deutsche Post [DHL](carrier:dhl), seguito dal tracker DHL fino alla consegna nel tuo Paese (la riga di arrivo DHL può contenere un link all'operatore nazionale che effettua il recapito finale). **Altrove**, riconosci il corriere tramite i [formati dei numeri di tracciamento](guide:tracking-number-formats) oppure chiedilo al venditore. Tentativi di consegna, punti di ritiro e giacenza seguono le regole del corriere finale; per eventuali oneri doganali leggi [pacchi fermi in dogana](guide:customs), e per riconoscere link sospetti consulta [SMS truffa sulle consegne](guide:delivery-scam-texts).

## Pacco Mayi Yinfu bloccato, non trovato o in ritardo

- **Fermo sulle prime righe:** `货物电子信息已经收到` (anche con la riga elettronica DHL) significa che il venditore ha creato l'ordine ma non ha ancora consegnato il collo al magazzino. Dopo alcuni giorni senza ingresso in magazzino, senti il negozio.
- **Fermo dopo `已转运transferred`:** il tratto gestito da Mayi Yinfu è concluso; segui il numero del corriere finale ([tracciare un pacco dalla Cina](guide:tracking-from-china), [perché il tracciamento non si aggiorna](guide:tracking-not-updating)).
- **Numero sconosciuto:** per un codice inesistente, il tracker di Mayi Yinfu restituisce comunque una scheda col codice sotto `参考号` (riferimento) e tutti gli altri campi vuoti. Peek la interpreta come numero non trovato: mostra «In attesa di Mayi Yinfu» e, se dopo due giorni resta sconosciuto, ti chiede di verificarlo; ricontrolla quindi ogni carattere col messaggio del venditore.
- **Smarrito, danneggiato o restituito:** Mayi Yinfu non pubblica procedure di reclamo; chiedi rimborso o rispedizione al venditore o al marketplace ([pacco smarrito](guide:lost-parcel), [restituito al mittente](guide:returned-to-sender), [consegnato ma non ricevuto](guide:delivered-not-received)).

## Contatti e assistenza Mayi Yinfu

Il sito `mayiyinfu.com.cn`, interamente in cinese, riporta un solo numero di cellulare della Cina continentale, **`+86 13705996996`** (`13705996996`), nell'intestazione come linea nazionale (`全国服务热线`) e nel riquadro `在线咨询`. La pagina contatti (`contact.html`), il profilo aziendale, le note legali e l'informativa privacy sono vuoti (nessun indirizzo, e-mail o modulo). Rivolgiti sempre prima al negozio da cui hai acquistato.

## Tracciare un pacco Mayi Yinfu con Peek

Incolla un codice `MY…US` o un link `mayiyinfu.com.cn/trackIndex.htm` con `documentCode=` in [Peek](/): viene assegnato subito a Mayi Yinfu («Corriere rilevato»). Con un codice `MYYF…CN`, Peek mostra «Verifica dei servizi di tracciamento…» e «Mayi Yinfu ha questo pacco.» non appena il tracker risponde. Per un riferimento del venditore senza trattini, tocca «Cambia» e scegli Mayi Yinfu (trovabile anche cercando `蚂蚁银服` o `MYYF`; poiché Peek rimuove i trattini, cerca i riferimenti coi trattini direttamente su `mayiyinfu.com.cn`).

**Scansioni, fusi orari e tappe scritte in anticipo.** Quando una riga di volo include un giorno futuro previsto, Peek mostra solo il passaggio in inglese (come `The first leg has departed`) senza la data non ancora raggiunta, ed esclude nomi, indirizzi, CAP, peso, importi e foto (non c'è data di consegna stimata; la sigla isolata `SZ`, che nello standard ISO è l'Eswatini, viene rimossa dal luogo). Le scansioni collocate nella Cina continentale (`CN`) — e le righe inglesi copiate da DHL con codice Paese una volta collegata DHL — sono lette sull'ora di Pechino (`UTC+8`) e convertite nel tuo fuso («Orari nel tuo fuso orario: …»); le altre mantengono l'ora locale. Le righe scritte oltre 12 ore prima dell'orario indicato, o oltre 6 ore prima nello stesso secondo di una riga simile, sono trattate come previsioni, come le righe `CO_C_CR`: non contano come scansioni e non cambiano lo stato. Sul web la cronologia si apre allora con «Altri siti possono mostrare più passaggi, ma sono solo previsioni.», con quei passaggi in grigio dietro «Mostrali». Una riga datata dopo il controllo di Peek compare solo passata quell'ora.

**Passaggio automatico a DHL e frequenza.** Se il numero di trasferimento appartiene univocamente a [DHL](carrier:dhl), Peek mostra «Consegna con DHL» con quel secondo numero (o «Annunciato a DHL, non ancora ricevuto» finché DHL ha solo il preavviso elettronico) e passa al tracciamento diretto di DHL appena compare una scansione DHL almeno pari all'ultima di Mayi Yinfu; ogni altro numero di consegna va aggiunto a parte come nuovo pacco. A pacco aperto o nel giorno di consegna con notifiche attive, Peek controlla fino a ogni 10 minuti, e ogni ora negli altri casi o dopo 12 ore su «Etichetta creata» / «Preso in carico» o due giorni senza novità (se dopo due giorni ci sono solo le righe iniziali, interroga anche servizi di tracciamento). Avvisi: «Ogni scansione», «Passaggi importanti» o «Solo la consegna».

## Domande frequenti sul tracciamento Mayi Yinfu

### Perché il mio numero Mayi Yinfu finisce con `US`?

Tutti i numeri `MY` di Mayi Yinfu terminano in `US` (e i numeri `MYYF` in `CN`), qualunque sia il Paese di destinazione: non significa che il pacco sia diretto negli Stati Uniti né che viaggi con USPS.

### `货物已交机场` (*delivered to the airport*) significa che il pacco è stato consegnato?

No: indica soltanto che Mayi Yinfu ha consegnato la spedizione all'aeroporto di partenza per l'imbarco sul volo. Il recapito al tuo indirizzo avverrà più avanti tramite il corriere locale.

### Quanto impiega Mayi Yinfu a consegnare?

Le pagine prodotto di Mayi Yinfu contengono solo testo segnaposto senza tempi di resa: fai riferimento alla stima indicata dal negozio al momento dell'ordine.

### Chi contatto se un pacco Mayi Yinfu non arriva?

Il venditore o il marketplace presso cui hai effettuato l'ordine: è lui ad aver prenotato Mayi Yinfu, il cui sito riporta solo un cellulare nella Cina continentale.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (home)](http://www.mayiyinfu.com.cn/Default.aspx) – ragione sociale, ricerca 快递查询, area clienti e linea mobile +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu: 联系我们 (contatti)](http://www.mayiyinfu.com.cn/contact.html) – pagina contatti priva di indirizzo, e-mail o modulo oltre al cellulare
- [Mayi Yinfu: 国际小包 (piccoli pacchi internazionali)](http://www.mayiyinfu.com.cn/products/category-1.html) – testo segnaposto senza tempistiche né partner pubblicati
- [Universal Parcel Scraper: Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – piattaforma Hualei, formati MY…US e MYYF…CN, stati di volo e righe CO_C_CR, fuso di Pechino, filtro SZ e passaggio a Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – riconoscimento China Post e DHL, tappe scritte in anticipo e servizi di tracciamento
:::
