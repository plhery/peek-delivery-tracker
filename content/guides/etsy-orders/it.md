---
title: Tracciamento Etsy: dov'è il tuo ordine e chi lo consegna
description: Perché il tuo ordine Etsy risulta ancora «Non spedito», quale corriere lo consegna, com'è fatto il codice di tracciamento e quando aprire una disputa.
slug: tracciamento-ordine-etsy
picture: Un pacco legato con lo spago e affrancato, un furgone postale e Pip che corre di fretta.
published: 2026-10-17
updated: 2026-10-17
---

Etsy non spedisce nulla: ogni negozio crea o imballa i propri ordini, li affida al corriere che preferisce e aggiunge un codice di tracciamento quando il servizio ne ha uno. La pagina Acquisti ti dice se l'ordine è partito e quando dovrebbe arrivare; il codice di tracciamento ti dice dov'è adesso. In ritardo o sparito? Chiedi prima al venditore, poi a Etsy.

## Perché il mio ordine Etsy risulta ancora «Non spedito»?

Perché il venditore non l'ha ancora creato, imballato o segnato come spedito. Etsy calcola la data di consegna prevista così: «Tempo di lavorazione + Tempo di transito del corriere = Data di consegna prevista». Il tempo di lavorazione è il tempo che serve al venditore per creare l'articolo e prepararlo per la spedizione. L'esempio di Etsy: 3-5 giorni di lavorazione e 2-4 giorni di transito danno una consegna tra 5 e 9 giorni. Sulla ricevuta trovi anche la data di spedizione a cui il venditore si è impegnato.

In **Acquisti e recensioni** (app: **Tu** → **Acquisti**) ogni ordine mostra uno di questi stati:

| Stato | Cosa vuol dire |
| --- | --- |
| `Non spedito` | Il venditore non ha completato l'ordine, o non l'ha aggiornato su Etsy |
| `Spedito` | Il venditore ha completato l'ordine su Etsy |
| `Pre-transito` | C'è un codice di tracciamento, ma il corriere non ha ancora scansionato il pacco |
| `In transito` | Il corriere ce l'ha ed è in viaggio verso di te |
| `Consegnato` | È arrivato, e puoi lasciare una recensione |

`Spedito` non vuol dire sempre che un corriere ha il pacco. Il venditore può impostare una data di spedizione fino a 3 giorni dopo, ed Etsy ti avvisa in quel giorno. Solo con le etichette Asendia, Evri, FedEx, USPS (escluse lettere e buste) o UPS acquistate su Etsy l'email aspetta la prima scansione del corriere. Se la data prevista è passata e l'ordine risulta ancora `Non spedito`, secondo Etsy il venditore potrebbe aver dimenticato di segnarlo come completato, o non averlo ancora spedito: chiedi con **Assistenza per l'ordine**.

Dove trovi il codice su Etsy e nell'email di spedizione: [trovare il codice di tracciamento](guide:find-tracking-number).

## Quale corriere consegna un ordine Etsy?

Quello scelto dal venditore. Etsy vende etichette di spedizione solo ai venditori di Stati Uniti, Canada e Regno Unito; gli altri comprano l'affrancatura da soli, alla posta o da un corriere. Per le spedizioni all'estero, Etsy indica i corrieri usati di solito in ogni Paese:

| Venditore in | Etichette vendute su Etsy | Corrieri indicati da Etsy per l'estero |
| --- | --- | --- |
| Stati Uniti | USPS, [FedEx](carrier:fedex), [UPS](carrier:ups) (solo negli Stati Uniti), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Regno Unito | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Canada | Canada Post, Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| Italia | Nessuna | DHL, FedEx, [Poste Italiane](carrier:poste-italiane), UPS |
| Cina, Hong Kong | Nessuna | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), e SF Express dalla Cina |

Se il venditore ha usato un corriere supportato direttamente da Etsy, accanto all'ordine c'è il pulsante **Traccia spedizione**. Altrimenti il codice si trova solo in **Visualizza ricevuta**, e lo segui sul sito del corriere.

Dal 1° luglio 2026 Etsy non vende temporaneamente etichette USPS e Canada Post per l'Italia, a causa, dice Etsy, di un nuovo onere di gestione dell'UE che riguarda alcuni corrieri. I venditori negli Stati Uniti usano allora Global Postal Shipping o comprano l'etichetta altrove. I pacchi dalla Cina seguono una strada tutta loro: vedi [tracciare un pacco dalla Cina](guide:tracking-from-china).

## Com'è fatto un codice di tracciamento Etsy?

È il codice del corriere, non uno di Etsy. Le poste usano il formato postale internazionale: 13 caratteri che restano uguali da un Paese all'altro.

:::anatomy RR 00000000 5 US
- RR | Due lettere per il tipo di invio e il servizio
- 00000000 | Numero di serie, otto cifre
- 5 | Cifra di controllo, calcolata dal numero di serie
- US | Paese della posta che l'ha assegnato: gli Stati Uniti
:::

Le forme che Peek conosce per i corrieri qui sopra, con cifre inventate:

| Il codice è fatto così | Di solito |
| --- | --- |
| Due lettere, nove cifre, poi `IT`, `US` o `GB` | Poste Italiane, USPS o Royal Mail |
| `RA` + 11 cifre | Poste Italiane |
| 22 cifre che iniziano da `91` a `95`, come `9400111111111111111114` | USPS |
| `1Z` + 16 lettere e cifre | UPS |
| 12 o 15 cifre | FedEx |

Non sai quale hai? Scrivilo qui:

:::checker
:::

Tutti gli altri formati, corriere per corriere: [formati dei codici di tracciamento](guide:tracking-number-formats).

## Perché il tracciamento Etsy si ferma o cambia?

Di solito per uno di questi motivi:

- **Ha passato un confine.** Etsy avverte che «molte spedizioni internazionali non mostrano alcun tracciamento, una volta che l'ordine lascia il Paese di provenienza». Il tracciamento dovrebbe riprendere quando il pacco arriva in Italia.
- **È passato a Poste Italiane.** Con un codice postale, la posta del venditore manda il pacco all'estero e Poste Italiane lo consegna. L'Unione postale universale ammette «un solo identificativo S10» per invio, quindi di solito lo stesso codice funziona anche sul sito di Poste Italiane.
- **Due etichette, un solo viaggio.** Con Global Postal Shipping di Etsy, un venditore statunitense spedisce il pacco con un'etichetta USPS ad Asendia, che applica l'etichetta internazionale. Etsy unisce le due parti in un'unica cronologia in Acquisti e recensioni, e nelle sue email scrive «Tracked on Etsy».
- **Il venditore ha aggiunto o corretto il codice in ritardo.** Il venditore può modificare il codice per 3 giorni dopo aver completato l'ordine; poi ti manda quello giusto con un messaggio. Può anche aggiungere un pacco in seguito, e il nuovo codice compare accanto al primo.
- **Più ordini, una sola scatola.** Se il venditore unisce più ordini tuoi in un pacco, hanno tutti lo stesso codice.

:::journey
- shop | Artigiano | Lo crea e lo imballa: fino ad allora `Non spedito`
- label | Etichetta | `Pre-transito`: un codice, nessuna scansione
- truck | Posta di partenza | Prime scansioni nel Paese del venditore
- plane | Volo | Qui il tracciamento spesso tace
- customs | Dogana | Controlli, a volte con dazi
- handover | Poste Italiane | Lo consegna, di solito con lo stesso codice
- home | Tu | `Consegnato`
:::

Quanto può durare un silenzio: [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

### Perché non c'è nessun codice di tracciamento?

Non tutti i servizi ne hanno uno. Etsy lo dice chiaramente: «Non tutti i metodi di spedizione includono l'opzione di tracciamento.» Per lettere e buste dagli Stati Uniti le etichette di Etsy aggiungono il tracciamento tramite Pitney Bowes, ma una busta troppo rigida può non mostrarlo. Etsy chiede il tracciamento ai venditori statunitensi, ma ogni venditore può segnare un ordine come senza tracciamento, indicando un motivo. Nessun codice? Scrivi al venditore con **Assistenza per l'ordine**: sa quale servizio ha usato e quando ha spedito.

## Pagherò la dogana su un ordine Etsy dall'estero?

Etsy dice che di solito dazi, imposte e spese di sdoganamento sono a carico di chi compra, e quasi sempre si pagano all'arrivo del pacco. Per l'Italia valgono le regole dell'UE, ed Etsy ne incassa una parte già al pagamento:

| Ordine | Cosa si applica |
| --- | --- |
| Da un Paese dell'UE | L'IVA è inclusa nel prezzo, non paghi altro |
| Da fuori UE, sotto i 150 € | Etsy addebita l'IVA al pagamento. Si aggiunge un onere doganale di 3 € per articolo, al pagamento o alla consegna |
| Da fuori UE, sopra i 150 € | IVA e dazi possono esserti addebitati alla consegna, a meno che il venditore non spedisca con dazi già pagati |

Un pacco in ritardo o mai arrivato perché hai rifiutato o non hai pagato gli oneri dovuti alla consegna non è coperto dalla Protezione degli acquisti di Etsy. Di più su oneri e tempi: [pacco fermo in dogana](guide:customs).

## Cosa fare se il tuo ordine Etsy è in ritardo o non arriva?

Procedi in quest'ordine:

:::steps
- Controlla le date | La data di consegna prevista è sull'ordine. Etsy la ricalcola se il venditore spedisce prima.
- Traccialo dal corriere | Con Traccia spedizione, o con il codice di Visualizza ricevuta sul sito del corriere.
- Chiedi al venditore | Acquisti e recensioni, poi Assistenza per l'ordine. Il venditore può controllare lo stato, cambiare l'indirizzo o rimborsarti.
- Apri una disputa | 48 ore dopo la tua richiesta, una volta passata la data prevista. Per un ordine mai arrivato: almeno 7 giorni dopo la data prevista massima.
- Rispetta la scadenza | Hai tempo fino a 30 giorni dopo la data di consegna prevista.
:::

Con la Protezione degli acquisti di Etsy, un ordine idoneo viene rimborsato per intero se l'articolo non arriva, arriva danneggiato, non è conforme alla descrizione, o arriva oltre 7 giorni dopo la data di consegna prevista massima indicata all'acquisto. La tua richiesta in **Assistenza per l'ordine** mostra da quando puoi scegliere **Apri una disputa** e fino a quando. Etsy può emettere rimborsi solo entro 180 giorni dall'acquisto.

Alcuni casi sono esclusi: ritardi dovuti a uno sciopero del corriere, a eventi meteo gravi, a guerre o disordini civili, oneri rifiutati alla porta e ordini già contestati con la tua banca. Hai comprato come ospite? Prima associa l'ordine a un account Etsy. Se hai pagato con PayPal fuori da Etsy Payments, apri invece una contestazione con PayPal entro 180 giorni dall'acquisto.

### Risulta «Consegnato», ma non è arrivato

Etsy consiglia prima questi controlli: chiedi a chi vive o lavora con te, aspetta un giorno se non è urgente, contatta l'ufficio postale o il centro di spedizione della tua zona, e chiedi ai vicini. Ancora niente? Manda al venditore una richiesta con **Assistenza per l'ordine**: di solito è lui ad aprire il reclamo con il corriere per conto tuo. Poi apri una disputa se il pacco non salta fuori. Di più in [consegnato ma non ricevuto](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

## Posso tracciare un ordine Etsy fuori da Etsy?

Sì, con il codice del corriere. Incollalo in [Peek](/): riconosce i codici delle poste e dei corrieri qui sopra. Con un codice postale internazionale non serve cercarne un altro: una volta che il pacco è in Italia, di solito lo stesso codice funziona anche sul sito di Poste Italiane. I passaggi di Etsy restano su Etsy: Peek non vede un ordine ancora `Non spedito` e non può aprire una disputa al posto tuo.

:::sources
- [Etsy: Qual è lo stato del mio ordine?](https://help.etsy.com/hc/it/articles/115015521948-Qual-%C3%A8-lo-stato-del-mio-ordine) – stati, Traccia spedizione, Visualizza ricevuta, tracciamento all'estero, nessun tracciamento, consigli se non arriva
- [Etsy: Che cos'è la data di consegna prevista?](https://help.etsy.com/hc/it/articles/360020601674-Che-cos-%C3%A8-la-data-di-consegna-prevista) – lavorazione più transito, esempio, 7 giorni per la mancata consegna
- [Etsy: Come stabilire i tempi di lavorazione, i profili di lavorazione e le date di spedizione](https://help.etsy.com/hc/it/articles/115015588087-Come-stabilire-i-tempi-di-lavorazione-i-profili-di-lavorazione-e-le-date-di-spedizione) – tempo di lavorazione, data di spedizione sulla ricevuta
- [Etsy: Come aggiungere il tracciamento e completare un ordine](https://help.etsy.com/hc/it/articles/115015774228-Come-aggiungere-il-tracciamento-e-completare-un-ordine) – data di spedizione fino a 3 giorni dopo, email alla prima scansione, tracciamento obbligatorio negli Stati Uniti, 3 giorni per modificare, pacchi aggiunti, ordini uniti
- [Etsy: Come acquistare le etichette di spedizione su Etsy](https://help.etsy.com/hc/it/articles/360001967188-Come-acquistare-le-etichette-di-spedizione-su-Etsy) – corrieri, venditori in Canada, Regno Unito e Stati Uniti, destinazioni UE sospese
- [Etsy: FedEx Shipping Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – venditori statunitensi
- [Etsy: UPS Shipping Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – venditori statunitensi, solo negli Stati Uniti
- [Etsy: Royal Mail Postage Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – venditori nel Regno Unito
- [Etsy: Evri Postage Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – venditori nel Regno Unito
- [Etsy: Chit Chats Shipping Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – venditori in Canada
- [Etsy: Global Postal Shipping Labels on Etsy (in inglese)](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, due etichette, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters (in inglese)](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – tracciamento tramite Pitney Bowes, buste rigide
- [Etsy: Informazioni per la gestione delle spedizioni internazionali](https://help.etsy.com/hc/it/articles/360001987487-Informazioni-per-la-gestione-delle-spedizioni-internazionali) – corrieri usati di solito in ogni Paese
- [Etsy: Dovrò pagare imposte, oneri o dazi sul mio ordine?](https://help.etsy.com/hc/it/articles/115015691007-Dovr%C3%B2-pagare-imposte-oneri-o-dazi-sul-mio-ordine) – regole UE, 150 €, 3 € per articolo
- [Etsy: Programma di Protezione degli acquisti di Etsy](https://help.etsy.com/hc/it/articles/7471925990807-Programma-di-Protezione-degli-acquisti-di-Etsy) – cosa è coperto, oneri doganali, scioperi e meteo, ospiti
- [Etsy: Come ottenere assistenza per un ordine](https://help.etsy.com/hc/it/articles/4402660818583-Come-ottenere-assistenza-per-un-ordine) – Assistenza per l'ordine, cosa può fare il venditore
- [Etsy: Come aprire una disputa](https://help.etsy.com/hc/it/articles/5745586898199-Come-aprire-una-disputa) – 48 ore, 30 giorni, 180 giorni, PayPal, contestazioni bancarie
- [Etsy: What if My Buyer's Package Gets Lost? (in inglese)](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – reclamo aperto dal venditore
- [UPU: standard S10 (in inglese)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – il formato da 13 caratteri, un solo codice per invio
- [Universal Parcel Scraper: dati dei corrieri (in inglese)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – forme dei codici, cosa controlla Peek
:::
