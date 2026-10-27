---
title: Come capire il corriere dal numero di tracking
description: Dal formato del codice si risale spesso al corriere: prefissi, lunghezze ed esempi per Poste, BRT, GLS, InPost, DHL, UPS, la Posta svizzera e i pacchi dalla Cina.
slug: corriere-dal-numero-di-tracking
picture: Tre codici di tracking divisi in parti colorate, una lente sul codice paese CH e Pip che osserva incuriosito.
published: 2026-10-09
updated: 2026-10-27
---

Spesso bastano le lettere e la lunghezza del numero di tracking per capire quale corriere ha il tuo pacco. `1Z` seguito da 16 lettere e cifre è UPS, 18 cifre che iniziano con `99` sono la [Posta svizzera](carrier:swiss-post), e un codice di 13 caratteri come `RR000000005CH` (due lettere, nove cifre, due lettere) di solito è di una posta nazionale, con il Paese che l'ha emesso nelle ultime due lettere. Il caso difficile sono i codici di sole cifre: 14 cifre, per esempio, possono essere di BRT come di DPD. In quei casi l'ultima parola spetta all'e-mail di spedizione, o a un tracker che prova tutti i corrieri possibili.

:::checker
:::

## Ho il codice tracking ma non so il corriere: come faccio?

Quattro controlli, in quest'ordine:

:::steps
- Guarda le lettere | Sono l'indizio più forte. `1Z` all'inizio è UPS; due lettere alla fine di solito indicano una posta nazionale.
- Conta i caratteri | La lunghezza da sola decide di rado, ma esclude qualche corriere. Non contare gli spazi: servono solo a rendere il codice più leggibile. [Poste Italiane](carrier:poste-italiane), per esempio, chiede di scriverlo «senza interruzioni e senza trattino».
- Controlla l'e-mail | L'e-mail o l'SMS di spedizione del negozio di solito indica il corriere o porta alla sua pagina di tracking. Vale più di qualsiasi ipotesi.
- Prova sui siti dei corrieri | Inserisci il codice sul sito di ogni corriere possibile. Quello che mostra delle scansioni ha il tuo pacco.
:::

Se non hai voglia di fare il detective, incolla il codice, un link del corriere o l'intera e-mail di spedizione in [Peek](/): trova il numero e riconosce il corriere tra più di 3.500. Come fa un tracker a riconoscerlo, e cosa non può vedere, lo spiega [la guida al tracking universale](guide:universal-tracker).

## Com'è fatto il codice di ogni corriere

Ecco cosa dicono i corrieri, e lo standard postale, sui codici che incontri più spesso in Italia e in Ticino:

| Corriere | Com'è fatto il codice |
| --- | --- |
| Poste Italiane | Poste lo chiama «codice spedizione», da scrivere senza spazi e senza trattino. Ha forme diverse: `RA` e 11 cifre, `1UW` o `3UW` e 10 lettere o cifre, `2IMA` e 10 cifre, `5P` e 11 lettere o cifre, cifre con una lettera in mezzo (codici in stile SDA), o il formato postale internazionale che finisce con `IT`. Con Poste Delivery Web è un codice di 13 caratteri, stampato in alto a destra sulla lettera di vettura |
| [BRT (ex Bartolini)](carrier:brt) | «BRTcode» di 14 o 19 cifre, oppure «numero di spedizione» di 12 o 14 cifre |
| [GLS Italia](carrier:gls-it) | spedizione nazionale: due lettere, o una lettera e una cifra, seguite da al massimo 9 cifre, come `AB123456789` o `A1 123456789`; spedizione internazionale: 11 cifre, una per collo. Il modulo di GLS prende anche l'ID Collo, senza gli zeri iniziali e senza «GLS» finale |
| [InPost](carrier:inpost) | il modulo «Trova il tuo pacco» accetta 8, 10, 11, 13, 15, 22, 24 o 26 cifre, oppure due formati con lettere: `LM` + 9 cifre + `IT`, o 4 lettere + 15 cifre |
| DHL | [DHL Express](carrier:dhl-express): 10 cifre, mai lettere. DHL in Germania: da 10 a 39 caratteri |
| [UPS](carrier:ups) | `1Z` + 16 lettere e cifre, 18 caratteri in tutto, come `1Z999AA10123456784`, un esempio con una cifra di controllo UPS valida |
| La Posta (Svizzera) | la Posta lo chiama «numero d'invio». Per gli invii nazionali è un codice a barre di 18 cifre che iniziano con `98` o `99`, con o senza punti o spazi; sui pacchi spediti dalle aziende è stampato con i punti come `99.34.123456.12345678` (il 99, il numero di licenza di affrancatura del mittente e un numero progressivo). Altrimenti è il formato postale che finisce con `CH` |
| [Quickpac](carrier:quickpac) | 18 cifre, dice Quickpac; nei dati dei corrieri che usa Peek iniziano con `44`. Il riquadro di tracking di Quickpac le mostra con i punti, come `44.12.345678.90123456` |
| [Planzer](carrier:planzer) | Planzer lo chiama numero di spedizione e non ne pubblica il formato. Nei dati dei corrieri che usa Peek: 20 cifre che iniziano con `91346097`, o 13 cifre che iniziano con `99990` per le spedizioni condivise |
| [GLS Svizzera](carrier:gls-ch) | numero del pacco o Track ID; né GLS né la Posta svizzera, suo partner, ne pubblicano il formato. Nei dati dei corrieri che usa Peek: 11 cifre, 12 cifre con l'ultima di controllo, 8 lettere e cifre mescolate, o sei lettere |
| [DPD Svizzera](carrier:dpd-ch) | «numero di pacco» di 14 cifre; i moduli di DPD per i mittenti chiedono che quelli svizzeri inizino con `060`. Nei dati dei corrieri che usa Peek, le 14 cifre possono essere seguite da un carattere di controllo, che le pagine svizzere di DPD non citano |
| Amazon | nei dati dei corrieri che usa Peek, `IT` seguito da 10 cifre o `TBA` seguito da 12; spiegati più sotto |
| [YunExpress](carrier:yunexpress) | `YT` + 16 cifre |
| [4PX](carrier:4px) | `4PX` + 13 cifre + `CN`; 4PX non pubblica numeri di esempio, e il suo sito accetta da 5 a 50 lettere e cifre. Anche `LP` + 13 cifre + `CN` è probabilmente 4PX |
| [Yanwen](carrier:yanwen) | Yanwen non pubblica numeri di esempio; i suoi pacchi hanno `BYS` + 9 cifre, oppure due lettere, 9 cifre e `YP` (spesso inizia con `UL` o `UK`). `YW`, tre lettere e 12 cifre è il suo servizio di consegna negli Stati Uniti |
| [Cainiao](carrier:cainiao) (AliExpress) | Cainiao non descrive i suoi formati e come esempio mostra un numero postale che finisce con `HK`. Nei dati dei corrieri che usa Peek, `LP` o `CNG` + 14 cifre sono probabilmente Cainiao; `DOFR` o `CNFR` + 13 cifre + `HD` sono Cainiao |
| [China Post](carrier:china-post), EMS | il formato postale che finisce con `CN`; i numeri EMS internazionali iniziano con `E` |

### Codice che inizia con YT, LP, 5P o 1Z: di chi è?

Sono i prefissi che si cercano di più:

- `1Z`: UPS, se seguono 16 lettere e cifre, l'ultima delle quali è una cifra di controllo.
- `YT`: YunExpress, se seguono 16 cifre; il suo sito di tracking chiede proprio un numero che inizia con YT. Ma nei dati dei corrieri che usa Peek `YT` + 13 cifre è YTO Express, e `YT` + nove cifre + `GB` è un codice postale della Royal Mail britannica.
- `LP`: negli stessi dati, `LP` seguito da 14 cifre è un possibile codice AliExpress/Cainiao, e `LP` + 13 cifre + `CN` un possibile codice 4PX. Cainiao non descrive il formato sul suo sito, quindi prendilo solo come un indizio.
- `5P`: negli stessi dati, `5P` seguito da 11 lettere e cifre è Poste Italiane. Il modulo di ricerca di Poste però non parla di questo formato.

## Quante cifre ha un codice di tracking?

Non c'è una lunghezza unica. Queste sono le lunghezze che i corrieri indicano sulle loro pagine, e le sovrapposizioni spiegano perché un codice di sole cifre può andare bene per più corrieri:

| Lunghezza | Chi la usa |
| --- | --- |
| 10 cifre | DHL Express; InPost |
| 11 cifre | GLS Italia, spedizioni internazionali; InPost |
| 12 cifre | BRT, numero di spedizione |
| 13 caratteri | Poste Delivery Web; il formato postale internazionale; InPost, con sole cifre |
| 14 cifre | BRT, BRTcode o numero di spedizione; DPD, secondo le sue pagine per Austria e Belgio (quella tedesca parla di 14 caratteri) |
| 18 cifre | La Posta svizzera, quando il codice inizia con `98` o `99` |
| 19 cifre | BRT, BRTcode |
| 8, 15, 22, 24 o 26 cifre | InPost |
| da 10 a 39 caratteri | DHL in Germania |

Se cerchi il «codice tracking BRT di 18 cifre», sappi che BRT non ne parla: il suo campo di ricerca chiede un BRTcode di 14 o 19 cifre o un numero di spedizione di 12 o 14, e se la lunghezza non va risponde «Deve essere 14 o 19 cifre» o «Deve essere 12 o 14 cifre».

## Tracking che inizia con IT: che corriere è?

Se dopo `IT` ci sono 10 cifre, di solito è Amazon. Negli stessi dati dei corrieri citati sopra, `IT` seguito da 10 cifre è [Amazon Logistics](carrier:amazon-logistics), il servizio di consegna di Amazon (o [Amazon Shipping](carrier:amazon-shipping), se Amazon ha consegnato per un altro negozio); lo stesso schema vale con altri codici paese (`DE`, `FR`, `ES`…) e con `TBA`, `TBC` o `TBM` seguiti da 12 cifre. Per consegnare, Amazon collabora con decine di fornitori di servizi di consegna in Italia, e il tuo ordine si segue nella sezione *I miei ordini* su Amazon.

Non confonderlo con un codice che *finisce* con `IT`: due lettere, nove cifre e `IT` è il formato postale con il codice dell'Italia, come l'esempio nel modulo di ricerca di Poste (`ZA123456789IT`).

## Il formato postale: due lettere, nove cifre, due lettere

Le poste nazionali condividono un formato di 13 caratteri, fissato dall'Unione postale universale (UPU) nel suo standard S10. Eccolo smontato:

:::anatomy RR 00000000 5 CH
- RR | Tipo di invio: R per una raccomandata
- 00000000 | Numero di serie, otto cifre
- 5 | Cifra di controllo, calcolata dal numero di serie
- CH | Paese che ha emesso il codice: la Svizzera
:::

Per gli invii che attraversano un confine, la prima lettera indica il tipo di invio. All'interno del proprio Paese, ogni posta può usare le lettere in modo diverso.

- `E`: EMS, il servizio espresso delle poste
- `C`: pacco
- `R`: raccomandata
- `L`: lettera tracciata
- `V`: lettera assicurata
- `U`: merce spedita come lettera, senza tracciamento per te

Le ultime due lettere non dicono sempre da dove parte il pacco. Per l'UPU il codice paese non è un indicatore affidabile dell'origine geografica: la Posta svizzera usa `CH` anche per gli invii che spedisce dai suoi uffici all'estero, e le poste portoghesi CTT danno codici che finiscono con `PT` a invii che i loro clienti con contratto spediscono dalla Spagna, dalla Cina e da altri Paesi.

La cifra di controllo serve a scovare gli errori di battitura. Per verificare un codice:

1. Moltiplica le otto cifre del numero di serie per 8, 6, 4, 2, 3, 5, 9 e 7, e somma i risultati.
2. Dividi per 11 e sottrai il resto da 11.
3. Se ottieni 10 la cifra è 0, se ottieni 11 è 5.

Per `RR000000005CH` le otto cifre del numero di serie sono tutte 0: la somma è 0, che diviso per 11 dà resto 0, e 11 − 0 = 11, quindi la cifra è 5. Se un codice postale copiato a mano non supera la prova, cerca una cifra sbagliata o due cifre invertite. Attenzione però agli esempi nei moduli dei corrieri: molti, come `ZA123456789IT` di Poste, sono solo segnaposto e la prova non la superano.

### Codice che inizia con UL o UU: di chi è?

Se ha 13 caratteri e finisce con due lettere, per esempio `CN`, è un codice postale della serie `U`: merce spedita come lettera. Le ultime due lettere dicono quale posta l'ha emesso. Un codice più lungo che inizia con `UU` non è in questo formato: cerca il corriere nell'e-mail di spedizione.

> Sulla posta dall'estero, un codice postale che inizia con `U` (da `UA` a `UZ`) non è fatto per essere seguito da te. L'UPU riserva quella serie alla merce spedita come lettera, senza un tracciamento rivolto al cliente, e le poste belghe bpost dicono chiaramente che un pacco così non si può seguire. Non contare sul fatto che prima o poi inizi ad aggiornarsi.

## Numeri che non sono il tuo codice di tracking

Con un pacco viaggiano anche altri numeri:

- **Non sono codici di tracking:** il numero d'ordine, che è del negozio (DHL ricorda che il numero di spedizione non è un numero d'ordine), e il codice prenotazione ritiro di Poste, come `CP123456789`, che segue il ritiro a domicilio, non la spedizione.
- **Funziona, ma accorciato:** l'ID collo GLS. GLS chiede di togliere gli zeri iniziali e la sigla finale «GLS»: resta un numero di cifre come `12345678`.

Gli avvisi lasciati dal corriere, il riferimento del mittente e cosa fare se hai solo il numero d'ordine: [dove trovare il codice di tracking](guide:find-tracking-number).

## Perché il codice di tracking può cambiare durante il viaggio

Un pacco che attraversa un confine passa spesso di mano, e ogni azienda può stampare la sua etichetta. L'UPU permette alla posta di destinazione di aggiungere un suo codice a barre accanto all'originale, purché non sia nel formato postale di 13 caratteri.

:::journey
- shop | Venditore | Stampa l'etichetta e il primo codice
- plane | Volo | Viaggia con quel primo codice
- customs | Dogana | Controllo nel tuo Paese
- handover | Corriere locale | Può aggiungere etichetta e codice suoi
- home | Casa tua | Le ultime scansioni possono stare sotto il nuovo codice
:::

- **Gli invii postali** sono il caso semplice: l'UPU ammette un solo codice S10 per invio, quindi di solito funziona sui siti di entrambe le poste.
- **Il numero aggiunto all'arrivo** può avere tutt'altra forma. Nell'esempio dello standard UPU, la posta di destinazione ha messo accanto al codice d'origine il numero `98.00.802077.23271453` e ha collegato i due nel suo sistema: 18 cifre con i punti, come i numeri della Posta svizzera. Se sul pacco compare un secondo numero così, prova anche quello.
- **Spedizioni economiche di AliExpress:** Cainiao dice che il tracking si ferma alla consegna al corriere locale, e che il venditore può dirti come contattarlo. Di più in [seguire un pacco dalla Cina](guide:tracking-from-china).

Un codice che non dà risultati non è per forza sbagliato. Le poste portoghesi CTT danno due motivi: il codice serve solo per la dogana, oppure l'invio lo consegna un'altra azienda, e in questi casi consigliano di sentire il mittente. Se un codice valido semplicemente non si muove ancora, leggi [perché il tracking non si aggiorna](guide:tracking-not-updating).

:::sources
- [UPU: standard S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – il formato postale, le lettere, la cifra di controllo, il codice paese, il numero aggiunto a destinazione
- [Poste Italiane: Cerca spedizioni, modulo di ricerca](https://www.poste.it/cerca/partials/forms-ricerche.html) – «senza interruzioni e senza trattino», l'esempio ZA123456789IT
- [Poste Italiane: FAQ Poste Delivery Web](https://www.poste.it/assistenza/faq-poste-delivery-web) – il codice spedizione di 13 caratteri, il codice prenotazione ritiro
- [BRT: Tracking](https://services.brt.it/it/tracking) – BRTcode di 14 o 19 cifre, numero di spedizione di 12 o 14, i messaggi d'errore
- [GLS Italia: Ricerca spedizione](https://gls-group.com/IT/it/servizi-online/ricerca-spedizioni/) – riferimento nazionale e internazionale, ID collo
- [InPost: Trova il tuo pacco](https://inpost.it/) – i formati accettati dal modulo di ricerca
- [La Posta: Monitorare gli invii](https://www.post.ch/it/ricezione/monitorare-gli-invii) – numero d'invio
- [La Posta: Barcode for Business Customers, gennaio 2026](https://handbuch.post.ch/-/media/post/gk/dokumente/1864-Anleitung-Barcodes-GK.pdf) – 18 cifre che iniziano con 99, stampate con i punti
- [Quickpac: FAQ](https://quickpac.ch/en/faq) – il numero di 18 cifre
- [Quickpac: Sendung verfolgen](https://quickpac.ch/de/tracking) – il numero con i punti nel riquadro di tracking
- [Planzer Pacchi: Contatti](https://www.planzer-paket.ch/it/contatti/) – numero di spedizione
- [GLS: Parcel Tracking](https://gls-group.com/EU/en/parcel-tracking) – numeri di pacco e Track ID
- [GLS: GLS partners, Switzerland](https://gls-group.com/GROUP/en/gls-partners/?partner=switzerland) – la Posta, partner di GLS in Svizzera
- [myDPD: Traduzioni italiane](https://www.dpdgroup.com/ch/mydpd/api/translations/DPD-CH/it) – il numero di pacco di 14 cifre
- [DPD Svizzera: Responsabilità per il mittente](https://www.dpd.com/ch/it/centro-supporto/tutto-sulla-responsabilita/mittente/) – i numeri svizzeri iniziano con 060
- [DHL Express Netherlands: DHL Express or DHL eCommerce number](https://www.dhlexpress.nl/en/consumer/faq/express-account-zendingsnummer/my-shipment-number-dhl-express-or-dhl-ecommerce) – 10 cifre
- [DHL: Sendungsverfolgung, Probleme und Lösungen](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/sendungsverfolgung/probleme-loesungen.html) – da 10 a 39 caratteri, non è un numero d'ordine
- [DPD Germany: Wo finde ich meine Paketnummer?](https://www.dpd.com/de/de/faq/wo-finde-ich-meine-paketnummer/) – 14 caratteri
- [DPD Austria: Wo finde ich die Paketnummer?](https://www.dpd.com/at/de/faq/wo-finde-ich-die-paketnummer-2/) – 14 cifre
- [DPD Belgium: Where do I find the parcel number?](https://www.dpd.com/be/en/faq/where-do-i-find-the-parcel-number-2/) – 14 cifre
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – i numeri 1Z e la loro cifra di controllo
- [YunExpress: YunTrack](https://www.yuntrack.com/) – numeri che iniziano con YT
- [4PX: Global Order Tracking](https://track.4px.com/) – da 5 a 50 lettere e cifre
- [Yanwen: YW Tracking (in inglese)](https://track.yw56.com.cn/en/index) – il tracking di Yanwen, senza numeri di esempio
- [Cainiao: Help centre](https://global.cainiao.com/helpDoc.htm) – niente tracking dopo la consegna al corriere locale con le spedizioni economiche, chiedere al venditore
- [Cainiao: Tracciamento espresso globale](https://global.cainiao.com/) – il numero postale di esempio
- [About Amazon Italia: L'impegno di Amazon verso i dipendenti e i fornitori di servizi](https://www.aboutamazon.it/notizie/lavorare-ad-amazon/il-nostro-impegno-nei-confronti-dei-dipendenti-e-degli-autisti-dei-fornitori-di-servizi-di-consegna) – decine di fornitori di servizi di consegna in Italia
- [Universal Parcel Scraper: carrier catalog](https://github.com/plhery/universal-parcel-scraper/blob/main/data/catalog.json) – i formati IT, TBA, LP, 5P e YT, e quelli di Poste Italiane (RA, 1UW, 3UW, 2IMA, 5P e i codici in stile SDA), 4PX, Yanwen (BYS, YP e YW), la Posta svizzera (98 e 99), Cainiao, Quickpac, Planzer, GLS Svizzera e DPD Svizzera, nei dati dei corrieri di Peek
- [bpost: Puis-je suivre mon colis en ligne ?](https://www.bpost.be/fr/faq/puis-je-suivre-mon-colis-en-ligne) – i codici che iniziano con U non si possono seguire
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – codici PT per invii dall'estero, codici che non danno risultati, contattare il mittente
:::
