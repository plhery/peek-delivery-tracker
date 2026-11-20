---
title: Tracciare un ordine Amazon: dov'è il numero di tracciamento
description: Dove Amazon mostra il corriere e il numero di tracciamento del tuo pacco, sul sito e nell'app, e come seguirlo in Peek, a mano o con Claude o ChatGPT.
slug: tracciamento-ordine-amazon
picture: Un telefono mostra l'avanzamento di un ordine, due furgoni si passano un pacco e Pip guarda.
published: 2026-10-16
updated: 2026-11-20
---

Amazon mostra il corriere e il numero di tracciamento del tuo pacco in un solo posto: I miei ordini, poi Traccia il mio pacco. Copia il numero in [Peek](/) e segui il pacco insieme a tutti gli altri. Ci vuole un minuto, e funziona allo stesso modo su ogni sito Amazon. L'eccezione è un pacco che Amazon consegna da sé: di solito lo segui solo nel tuo account Amazon.

> Usi Claude o ChatGPT in Chrome? [Chiedigli di farlo al posto tuo](#claude-o-chatgpt-possono-aggiungere-i-pacchi-amazon-al-posto-mio).

## Dove trovo il numero di tracciamento su Amazon?

Nella pagina Traccia il mio pacco di ogni pacco. Ecco come arrivarci:

:::steps
- Apri I miei ordini | Sul sito lo trovi in alto a destra. Nell'app Amazon, tocca l'icona della persona in basso, poi I miei ordini.
- Trova l'ordine | Gli ordini più recenti sono in cima. Se un ordine arriva in più pacchi, ogni pacco ha il suo pulsante Traccia il mio pacco.
- Seleziona Traccia il mio pacco | Sul sito è accanto all'ordine. Nell'app, tocca prima l'articolo.
- Trova il corriere e il numero | La pagina indica il corriere, per esempio Poste Italiane, e il suo numero di tracciamento. Se non lo vedi, apri tutti gli aggiornamenti: lì Amazon elenca il numero.
- Incollalo in Peek | Peek riconosce il corriere dal numero e inizia a seguire il pacco.
:::

### Quando compare il numero?

Di solito dopo la spedizione. Un ordine appena fatto può non avere ancora un numero: il tracciamento può aggiornarsi in ritardo, o la prima parte del viaggio può essere affidata a un corriere intermedio che non rende visibile il tracciamento su Amazon. A volte il numero compare prima della prima scansione del corriere. Incollalo comunque: Peek mostra il primo aggiornamento appena il corriere ne ha uno. Per un articolo di un venditore terzo, il tracciamento compare solo se il venditore lo condivide con Amazon e hai scelto una spedizione tracciabile.

Le e-mail di Amazon di solito non contengono il numero di tracciamento. Il pulsante per tracciare il pacco in quelle e-mail porta alla stessa pagina.

### Funziona anche su amazon.de, .fr, .es e sugli altri siti Amazon?

Sì. Tutti i siti Amazon funzionano allo stesso modo. Cambiano solo le parole, con la lingua del sito:

| Sito | I miei ordini | Traccia il mio pacco |
| --- | --- | --- |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## Quale numero è quello di tracciamento?

Un ordine Amazon può mostrare tre tipi di numeri. Solo il numero di tracciamento del corriere permette sempre di seguire il pacco fuori da Amazon.

- **Il numero di tracciamento del corriere.** Viene dal corriere: Poste Italiane, BRT, GLS, SDA e così via. Amazon lo mostra accanto al nome del corriere. Peek lo segue come qualsiasi altro pacco.
- **Il numero d'ordine.** Tre gruppi di cifre uniti da trattini, come `123-1234567-1234567`. Lo trovi sull'ordine, sulla fattura e nelle e-mail di Amazon. Non è mai un numero di tracciamento: se lo incolli in Peek, Peek risponde «Sembra un numero d’ordine Amazon».
- **Un numero Amazon.** Amazon ne dà uno ai pacchi che consegna con la sua rete, e Peek lo verifica con Amazon: [vedi sotto](#peek-puo-tracciare-un-numero-amazon-tba). Eccone uno, con cifre inventate:

:::anatomy TBA 123456789012
- TBA | Il prefisso di Amazon. Anche `TBC` (Canada) e `TBM` (Messico), o `TBAU` con 9 cifre (Australia)
- 123456789012 | Dodici cifre
:::

In Europa un numero Amazon può anche essere formato da due lettere per il Paese, come `IT` o `FR`, e 10 cifre. Non sai quale hai? Scrivilo qui:

:::checker
:::

Un pacco può cambiare corriere durante il viaggio. Per le consegne nella Laguna di Venezia, per esempio, Amazon si appoggia anche a grandi corrieri nazionali come Poste Italiane: il pacco passa dal magazzino di un partner di trasporto della zona, e da lì un postino lo consegna. In quel caso Traccia il mio pacco indica quel corriere: copia il suo numero.

## Peek può tracciare un numero Amazon TBA?

A volte. Quando incolli un numero Amazon, Peek lo verifica prima con il tracciamento pubblico di Amazon:

- **Amazon lo conosce.** È un pacco che Amazon consegna per conto di un venditore, con [Amazon Shipping](carrier:amazon-shipping). Peek lo segue come qualsiasi altro.
- **Amazon non lo conosce.** Allora molto probabilmente è [Amazon Logistics](carrier:amazon-logistics) a consegnare un tuo ordine Amazon, e Amazon lo mostra solo nel tuo account. Peek non lo aggiunge: te lo dice e propone «Apri i miei ordini Amazon», che apre i tuoi ordini sul sito Amazon giusto. Peek non ti chiede mai la password di Amazon.
- **Amazon non è raggiungibile.** Peek te lo dice e propone «Riprova».

Quando Amazon consegna un pacco da sé, l'ordine può non mostrare nessun numero di tracciamento. Allora non c'è niente da copiare: quel pacco si segue solo dalla pagina Traccia il mio pacco di Amazon.

## Claude o ChatGPT possono aggiungere i pacchi Amazon al posto mio?

Sì, se l'assistente può usare il tuo browser. Questi due possono farlo:

- **Claude in Chrome,** l'estensione di Claude per Chrome, con i piani Claude a pagamento.
- **L'estensione di ChatGPT per Chrome,** che installi e usi dall'app ChatGPT per desktop.

Entrambe funzionano nella finestra di Chrome in cui hai già effettuato l'accesso ad Amazon. Chiediglielo, e l'assistente apre I miei ordini, poi una per una le pagine Traccia il mio pacco, e annota corriere e numero di tracciamento di ogni pacco.

Il seguito dipende da una cosa: Peek è collegato all'assistente?

- **Se Peek è collegato** e l'assistente può usarlo in quella chat, aggiunge da solo i pacchi al tuo account Peek, con il nome dell'articolo. In Claude, apri «Peek Parcel Tracker» nella [directory dei connettori di Claude](https://claude.ai/directory/peek-parcel-tracker), seleziona «Connect», accedi a Peek e seleziona «Consenti». In ChatGPT, Peek si aggiunge come plugin con un piano Business, Enterprise o Edu. Tutti i passaggi sono in [tracciare i pacchi con ChatGPT o Claude](guide:ai-assistants).
- **Se non lo è,** l'assistente elenca i numeri nella chat. Copia tutta la lista e incollala in [Peek](/): Peek trova ogni numero. Se hai effettuato l'accesso li segue tutti; altrimenti uno alla volta.

Copia questo nell'assistente:

> Apri I miei ordini su Amazon in questo browser. Per ogni pacco in arrivo o appena consegnato, apri Traccia il mio pacco e annota il corriere e il numero di tracciamento. Non usare mai il numero d'ordine (tre gruppi di cifre uniti da trattini). Se Peek è collegato, aggiungi ogni pacco a Peek con il nome del suo articolo. Altrimenti elenca qui i numeri, uno per riga. Dimmi quali pacchi non mostrano un numero di tracciamento. Su Amazon limitati a leggere: non comprare, annullare, restituire o modificare nulla, e non accedere al posto mio.

In tutti e due i casi l'assistente deve solo leggere Amazon. Non deve mai comprare, annullare o restituire nulla, né accedere al posto tuo. Se Amazon ti chiede di accedere, fallo tu in quella finestra, poi chiediglielo di nuovo. Un pacco senza numero di tracciamento, o che Peek rifiuta, si segue solo nel tuo account Amazon.

## Cosa fare se il pacco Amazon è in ritardo o risulta consegnato?

Chi ti aiuta dipende da chi l'ha spedito. Per sapere se hai ordinato da un venditore terzo, apri I miei ordini e guarda il nome del venditore. Amazon si occupa dei propri ordini e di quelli che spedisce per i venditori. Se un venditore terzo vende e spedisce da sé, è lui il primo a cui chiedere.

:::steps
- Controlla Traccia il mio pacco | Mostra l'ultimo aggiornamento e, quando consegna Amazon, dove si trova il pacco in tempo reale. Se è in ritardo, Amazon consiglia di aspettare 48 ore dalla data prevista.
- Guardati intorno | Controlla il posto indicato nelle tue istruzioni di consegna e la foto della consegna, se c'è. Chiedi in famiglia, ai vicini o in portineria.
- Chiedi al corriere | Se l'ha consegnato un altro corriere, la pagina Informazioni di contatto del corriere di Amazon dice come contattarlo.
- Contatta il venditore | Se l'ha venduto e spedito un venditore terzo, apri l'ordine in I miei ordini e scegli l'opzione giusta, come Problema con l'ordine: da lì puoi scrivergli.
- Chiedi ad Amazon | Per un ordine spedito da Amazon, contatta il Servizio Clienti entro 30 giorni dalla data di consegna prevista. Se il venditore non ti risponde entro 48 ore, puoi avere diritto alla Garanzia dalla A alla Z, che copre i problemi di consegna e le condizioni dell'articolo.
:::

Altro aiuto: [risulta consegnato ma non è arrivato](guide:delivered-not-received), [pacco smarrito](guide:lost-parcel) e [perché il tracciamento non si aggiorna](guide:tracking-not-updating).

:::sources
- [Aiuto Amazon: Traccia il tuo pacco](https://www.amazon.it/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – I miei ordini, Traccia il mio pacco, tutti gli aggiornamenti con i numeri di tracciamento, più pacchi in un ordine, il nome del venditore, tracciamento dei venditori terzi; la stessa pagina su amazon.com, .co.uk, .fr, .de, .es e .pl dà le loro etichette
- [Aiuto Amazon: Informazioni di contatto del corriere](https://www.amazon.it/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – i corrieri principali per le spedizioni in Italia, tra cui Poste Italiane, BRT, GLS e SDA; corriere e codice della spedizione in I miei ordini; contatti dei corrieri
- [Aiuto Amazon: Informazioni di tracciabilità mancanti](https://www.amazon.it/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – aggiornamento in ritardo, corriere intermedio, venditori terzi
- [Aiuto Amazon: Consegne effettuate tramite Amazon Logistics](https://www.amazon.it/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – partner di consegna locali e grandi corrieri nazionali, tracciamento in I miei ordini, istruzioni di consegna
- [Aiuto Amazon: Restituisci un regalo](https://www.amazon.it/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – il numero d'ordine a 17 cifre
- [Aiuto Amazon: Consegne in ritardo](https://www.amazon.it/gp/help/customer/display.html?nodeId=GL669SSDXFCJ2ZA6) – posizione del pacco in tempo reale quando consegna Amazon, 48 ore, Servizio Clienti entro 30 giorni, venditore terzo
- [Aiuto Amazon: Trova un pacco mancante che risulta essere stato consegnato](https://www.amazon.it/gp/help/customer/display.html?nodeId=GCU8BWGTQNJKQEBS) – istruzioni di consegna, foto della consegna, famiglia, vicini e portineria, il corriere
- [Aiuto Amazon: Contatta un venditore del Marketplace](https://www.amazon.it/gp/help/customer/display.html?nodeId=GLC8ZMBWMBTR6QZZ) – I miei ordini, Problema con l'ordine, 48 ore, la Garanzia dalla A alla Z copre consegna e condizioni dell'articolo
- [About Amazon: How to track your packages (in inglese)](https://www.aboutamazon.com/news/retail/track-amazon-package) – l'icona della persona nell'app, toccare l'articolo prima di tracciarlo
- [About Amazon Italia: Segui una consegna Amazon a Murano, la città costruita sull'acqua nella laguna di Venezia](https://www.aboutamazon.it/notizie/logistica/segui-una-consegna-amazon-a-murano-la-citta-costruita-sullacqua-nella-laguna-di-venezia) – grandi corrieri nazionali come Poste Italiane, il magazzino di un partner di trasporto, il postino
- [About Amazon Italia: Lettera agli azionisti dal CEO Andy Jassy - 2024](https://www.aboutamazon.it/notizie/company-news/lettera-agli-azionisti-dal-ceo-andy-jassy-2024) – Amazon Shipping per i venditori che gestiscono da sé le spedizioni
- [Claude Help Center: Get started with Claude in Chrome (in inglese)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – piani Claude a pagamento, solo Chrome, installare l'estensione
- [Claude Help Center: Use Claude in Chrome safely (in inglese)](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – resti tu responsabile degli acquisti; permessi per ogni sito
- [ChatGPT: Chrome extension (in inglese)](https://learn.chatgpt.com/docs/chrome-extension) – si configura dall'app ChatGPT per desktop; funziona sui siti dove hai effettuato l'accesso
- [Peek: Collegare Peek a Claude, ChatGPT o Gemini](https://peektracker.com/it/assistants) – i passaggi di Peek per Claude e ChatGPT
- [Peek su GitHub: gli strumenti degli assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – cosa dice Peek a un assistente su Amazon: I miei ordini, Traccia il mio pacco, nessun numero di tracciamento nelle e-mail di Amazon, solo lettura
- [Peek su GitHub: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – la nota sul numero d'ordine Amazon, «Apri i miei ordini Amazon», «Riprova», più numeri in un solo testo
- [Universal Parcel Scraper: dati dei corrieri](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – forme dei numeri Amazon e cosa controlla Peek
:::
