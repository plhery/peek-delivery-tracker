---
title: Tracciamento Memphis Courier: codici M-MIA, dogana e aiuto
description: Traccia le importazioni Memphis Courier dagli USA al Brasile: codici M-MIA, stati aeroporto GRU e ANVISA, regole CPF e contatti ufficiali.
slug: tracciamento-memphis-courier
published: 2026-11-24
updated: 2026-11-24
---

Memphis Courier è una società logistica 100 % brasiliana con oltre 25 anni di esperienza nella gestione di spedizioni espresse e formali di import ed export. Dal proprio terminal corriere presso l’Aeroporto Internazionale di Guarulhos (GRU) e dalla sede di San Paolo offre trasporto aereo, sdoganamento presso la Receita Federal, sdoganamento sanitario presso ANVISA (incluse le importazioni di cannabis medica ai sensi delle norme RDC 660 e RDC 327 per medicinali prodotti negli Stati Uniti, in Europa, in Colombia e in Uruguay) e consegna in tutto il Brasile.

Il tracciamento accompagna il pacco fino all’aeroporto di Guarulhos, attraverso il controllo Receita Federal e la verifica ANVISA, fino alla consegna.

## Formato del codice di tracciamento Memphis Courier

I numeri di tracciamento Memphis Courier seguono un formato alfanumerico distintivo di 11 caratteri:

- **`M` + 7 cifre + `MIA`** (11 caratteri totali, come `M4829173MIA`) — la forma che Peek riconosce come Memphis Courier.

Peek accetta codici Memphis Courier da 6 a 30 lettere e cifre, rimuove gli spazi e converte le lettere in maiuscolo. Il portale di tracciamento Memphis Courier (`clients.memphiscourier.com.br`) permette la ricerca senza accesso cliente. Con la forma `M` + 7 cifre + `MIA`, Peek interroga solo Memphis Courier e mostra «Memphis Courier ha questo pacco.» non appena risponde. Per altri formati comuni sulle importazioni verso il Brasile, consulta la [guida ai formati dei codici di tracciamento](guide:tracking-number-formats).

## Stati di tracciamento Memphis Courier

Il servizio di tracciamento Memphis Courier restituisce codici di scansione a 3 lettere con descrizioni in portoghese e una colonna località (stato o paese). La barra dei passaggi del sito riporta Criado (`FLT`), dogana (`BRP`), Liberado ed Entregue; le scansioni che raggiungono gli ultimi due passaggi (`CLA` e `DLV`) sono note solo tramite queste etichette.

| Codice | Testo Memphis Courier | Cosa significa |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | L’ordine di spedizione è stato registrato nel sistema Memphis Courier prima della partenza aerea. |
| `CHK` | Entrada registrada no Centro de Distribuição | Ingresso registrato in un centro di distribuzione Memphis Courier. |
| `ENO` | Encomenda em trânsito | In transito nella rete Memphis Courier. |
| `TRA` | Encomenda em transferência | In trasferimento tra sedi operative. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Partito dall’hub di origine su un volo diretto all’Aeroporto Internazionale di Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Atterrato all’aeroporto di Guarulhos a San Paolo. |
| `AAZ` | Pedido recebido pela GRU Airport | Preso in carico dal terminal cargo dell’aeroporto GRU prima dell’ingresso formale in dogana. |
| `BRP` | Processo de desembaraço iniciado | È iniziato lo sdoganamento brasiliano. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | In ispezione presso la Receita Federal (dogana brasiliana). |
| `ANV` | Em inspeção pela ANVISA | In ispezione normativa presso ANVISA, l’agenzia sanitaria brasiliana, durante lo sdoganamento. |
| `CLA` | Liberado (barra passaggi) | Dogana ed eventuali agenzie sanitarie hanno svincolato la spedizione per la consegna nazionale. |
| `DLV` | Entregue (barra passaggi) | Consegnato all’indirizzo del destinatario in Brasile. |

## Dogana, verifica CPF e sdoganamento ANVISA

Ogni importazione personale espressa in Brasile richiede il CPF (*Cadastro de Pessoas Físicas*) del destinatario per lo sdoganamento presso la Receita Federal. Come indica Memphis Courier nella propria [pagina sui servizi aggiuntivi](https://memphiscourier.com.br/servicos-adicionais/), molti negozi online esteri non includono un campo CPF al checkout; in tal caso l’assistenza clienti Memphis Courier contatta il destinatario per convalidare il CPF ed evitare blocchi in dogana.

Per la cannabis medica e altri medicinali che Memphis Courier importa da Stati Uniti, Europa, Colombia o Uruguay, il pacco può passare sia dai controlli Receita Federal (`HOC`) sia dalle verifiche ANVISA (`ANV`) a Guarulhos prima che compaia `CLA` (svincolato). Se la spedizione rimane su `BRP`, `HOC` o `ANV` per diversi giorni lavorativi senza variazioni, controlla email e WhatsApp: Memphis Courier informa i clienti tramite email, WhatsApp, chat o telefono e potrebbe averti chiesto una conferma del CPF o un documento. Per saperne di più sugli sdoganamenti internazionali, vedi la [guida alla dogana](guide:customs) e [quando il tracciamento smette di aggiornarsi](guide:tracking-not-updating).

Una volta svincolato dalla dogana (`CLA`), la distribuzione nazionale copre tutti gli stati brasiliani. Se un altro corriere consegna un ordine separato in Brasile, confronta il comportamento di tracciamento su [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) o [J&T Express Brazil](carrier:j-and-t-br).

## Tracciare Memphis Courier con Peek

Incolla un codice `M...MIA` in Peek e Peek lo abbina automaticamente a Memphis Courier appena il vettore risponde. Se devi scegliere o cambiare corriere manualmente, usa **Cambia** quando aggiungi il pacco oppure **Cambia corriere** dalla schermata di tracciamento.

Peek interroga direttamente il servizio pubblico di tracciamento Memphis Courier tramite l’[adattatore open source Memphis Courier di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Legge codice, dicitura portoghese, località e peso totale in chilogrammi quando disponibile. Memphis Courier fornisce ogni orario come ora locale e specifica il fuso UTC solo quando l’orario termina con esso. Peek mantiene quel fuso; altrimenti data la scansione nel fuso del luogo (ora di New York per `Estados Unidos`, ora di San Paolo per `SP` o `Brasil`), una scansione senza luogo nel fuso adiacente oppure su ora di San Paolo, mostrando tutto sull’orologio del tuo dispositivo. I dati visibili sul portale clienti come note interne, numero di colli, CAP, destinazione e documenti doganali allegati restano sul sito di Memphis Courier.

Peek raggruppa i 12 codici Memphis Courier in fasi chiare: **Etichetta creata** per `FLT`, **In transito** da `CHK` fino ad `AAZ` e nuovamente dopo lo svincolo doganale `CLA` (i codici di Memphis Courier passano direttamente dalla creazione ordine al transito nel centro di distribuzione senza un codice separato **Preso in carico**), **In dogana** per `BRP`, `HOC` e `ANV`, e **Consegnato** non appena appare `DLV`.

Mentre tieni il pacco aperto in Peek, o il giorno della consegna con le notifiche attive, Peek controlla fino a ogni 10 minuti finché il tracciamento Memphis Courier risponde. Altrimenti Peek controlla ogni ora. Poiché il tracciamento Memphis Courier non mostra una fase di uscita in consegna, Peek non passa mai ai controlli ogni 2 minuti del giorno di consegna. Dopo 12 ore con solo «Etichetta creata» o «Preso in carico», o dopo due giorni senza novità, controlla ogni ora comunque tu lo segua, e ogni 6 ore dopo sette giorni su «Etichetta creata». Quando il tracciamento Memphis Courier non risponde e Peek segue il pacco solo tramite servizi di tracciamento, controlla ogni ora, ogni 3 ore dopo una giornata ferma e fino a ogni 15 minuti il giorno della consegna mentre hai il pacco aperto o le notifiche attive.

Quando la scansione più recente risale a quattro giorni prima e il pacco è ancora su «Etichetta creata», «Preso in carico» o «In transito», Peek lo segnala con il giorno di quella scansione, ad esempio «Nessun aggiornamento da lun 12 ott». La pagina del pacco mostra quindi «Spesso un pacco resta in silenzio durante un lungo volo, in dogana o nel fine settimana. Peek continua a chiedere a Memphis Courier.», con un link «Chiedi aiuto a Memphis Courier» alla pagina Fale Conosco di Memphis Courier. Dopo tre settimane senza nuove scansioni mostra «Niente di nuovo da più di tre settimane. Se il pacco non è ancora arrivato, contatta il venditore. Peek continua a chiedere a Memphis Courier.»

Scegli **Ogni scansione**, **Passaggi importanti** o **Solo la consegna** nelle preferenze avvisi di Peek per stabilire la frequenza delle notifiche.

## Contattare Memphis Courier

Memphis Courier pubblica i canali di assistenza ufficiali sul proprio sito e specifica nell’avviso ufficiale che non considera i siti di reclamo di terze parti come Reclame Aqui un canale ufficiale di supporto o protocollo.

- **Pagina contatti ufficiale:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (modulo di contatto)
- **Telefono / assistenza clienti:** +55 11 94264-7550 (indicato sia per il Terminal Courier GRU sia per l’ufficio di San Paolo)
- **Email:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` e `info@memphiscourier.com.br`
- **Terminal Courier Guarulhos:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, San Paolo, Brasile
- **Ufficio di San Paolo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, San Paolo, Brasile ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Tieni a portata di mano il codice `M...MIA` e il CPF del destinatario quando contatti l’assistenza, così il team potrà recuperare subito la pratica doganale.

## Domande frequenti

### Cosa significa `ANV` («Em inspeção pela ANVISA») su Memphis Courier?

`ANV` indica che l’agenzia regolatoria sanitaria brasiliana (ANVISA) sta ispezionando la spedizione nello sdoganamento import. Memphis Courier sdogana importazioni di cannabis medica (RDC 660 / RDC 327) con ANVISA; il pacco resta in dogana fino a `CLA` (svincolato).

### Perché Memphis Courier mi ha contattato per il mio CPF?

La Receita Federal richiede il CPF del destinatario per ogni importazione personale espressa in Brasile. Quando un negozio estero non raccoglie il CPF al checkout, il servizio clienti Memphis Courier ti contatta per convalidarlo prima di procedere allo sdoganamento.

### Perché non ci sono scansioni tra `DEP` e `ARR`?

`DEP` registra la partenza dall’origine verso l’aeroporto di Guarulhos e `ARR` l’arrivo lì. L’elenco codici di Memphis Courier non prevede una scansione per il volo stesso, quindi l’evento successivo è `ARR` oppure `AAZ` al terminal cargo.

### Memphis Courier risponde alle segnalazioni su Reclame Aqui?

No. L’avviso ufficiale del sito Memphis Courier afferma che reclami, domande e chiarimenti vengono gestiti esclusivamente tramite i canali ufficiali pubblicati su [memphiscourier.com.br](https://memphiscourier.com.br/) (telefono, email e modulo di contatto) per tutelare tracciabilità e riservatezza dei dati.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [adattatore Memphis Courier di universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
