---
title: Tracciare un ordine Amazon: dov'è il numero di tracciamento
description: Dove Amazon mostra il corriere e il numero di tracciamento del tuo pacco, e come seguirlo in Peek, a mano o lasciando che lo facciano Claude o ChatGPT.
slug: tracciamento-ordine-amazon
picture: Un telefono mostra l'avanzamento di un ordine, due furgoni si passano un pacco e Pip guarda.
published: 2026-10-16
updated: 2026-11-21
---

Amazon mostra il corriere e il numero di tracciamento di ogni pacco in I miei ordini, poi Traccia il mio pacco. Copia il numero in [Peek](/) e segui il pacco insieme a tutti gli altri. Funziona allo stesso modo su ogni sito Amazon.

## Dove trovo il numero di tracciamento su Amazon?

:::steps
- Apri I miei ordini | Sul sito lo trovi in alto a destra. Nell'app Amazon, tocca l'icona della persona, poi I miei ordini.
- Seleziona Traccia il mio pacco | Il pulsante è accanto a ogni pacco in arrivo o appena consegnato. Se un ordine arriva in più pacchi, ognuno ha il suo.
- Copia il numero di tracciamento | In grassetto c'è il corriere, per esempio BRT, e subito sotto il suo numero di tracciamento.
- Incollalo in Peek | Peek riconosce il corriere e segue il pacco.
:::

:::picture amazon-orders/orders-it.webp 480x300
I miei ordini su Amazon: un ordine, con accanto il pulsante Traccia il mio pacco evidenziato. Un ordine di esempio, non uno vero.
:::

:::picture amazon-orders/track-it.webp 480x300
La pagina Traccia il mio pacco di Amazon: il corriere BRT e, subito sotto, il numero di tracciamento, entrambi evidenziati. Valori di esempio.
:::

Sugli altri siti Amazon, gli stessi pulsanti si chiamano così:

| Sito | I miei ordini | Traccia il mio pacco |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### Nessun numero di tracciamento?

- **Un ordine recente.** Di solito il numero compare quando il corriere ha il pacco.
- **Lo consegna Amazon.** L'ordine può non mostrare alcun numero, oppure uno di Amazon: due lettere per il Paese e 10 cifre, come `IT1234567890`. Incollalo comunque in Peek: Peek lo segue se il tracciamento pubblico di Amazon lo conosce, e ti dice se lo mostra solo il tuo account Amazon.
- **Il numero d'ordine,** tre gruppi di cifre come `123-1234567-1234567`, non è mai un numero di tracciamento.

## Claude o ChatGPT possono farlo al posto mio?

Sì, se l'assistente può usare il browser in cui hai effettuato l'accesso ad Amazon: Claude in Chrome con un piano Claude a pagamento, o l'estensione per browser di ChatGPT, che configuri dall'app ChatGPT per desktop. Serve quindi un computer: dalla sua app sul telefono, o con un browser che fa girare sui propri server, l'assistente non arriva ai tuoi ordini, e le e-mail di Amazon non riportano il numero di tracciamento.

### Senza il connettore Peek

Copia questo nell'assistente:

> Apri I miei ordini su Amazon in questo browser. Per ogni pacco in arrivo o appena consegnato, apri Traccia il mio pacco e annota il corriere e il numero di tracciamento, mai il numero d'ordine. Elencali qui, un pacco per riga, poi dimmi di incollare l'elenco su peektracker.com. Su Amazon limitati a leggere: non comprare, annullare, restituire o modificare nulla, e non accedere al posto mio.

Poi incolla tutto l'elenco in [Peek](/): Peek trova da solo ogni numero.

### Con Peek nell'assistente

Collega Peek una volta. In Claude, aggiungi «Peek Parcel Tracker» dalla [directory dei connettori di Claude](https://claude.ai/directory/peek-parcel-tracker). In ChatGPT, aggiungi Peek come plugin, con un piano Business, Enterprise o Edu. [Qui trovi tutti i passaggi](guide:ai-assistants).

Poi chiedi:

> Importa i miei pacchi Amazon in Peek.

Peek dice all'assistente dove Amazon mostra i numeri. L'assistente aggiunge ogni pacco, con il nome del suo articolo, e ti dice quali Amazon traccia solo nel tuo account.

In entrambi i casi l'assistente si limita a leggere Amazon. Se Amazon ti chiede di accedere, fallo tu in quella finestra, poi chiediglielo di nuovo.

Pacco in ritardo o mai arrivato? Leggi [risulta consegnato ma non è arrivato](guide:delivered-not-received) e [pacco smarrito](guide:lost-parcel).

:::sources
- [Aiuto Amazon: Traccia il tuo pacco](https://www.amazon.it/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – I miei ordini, Traccia il mio pacco, i numeri di tracciamento; la stessa pagina su amazon.com, .co.uk, .fr, .de, .es e .pl dà le loro etichette
- [Aiuto Amazon: Informazioni di contatto del corriere](https://www.amazon.it/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – i corrieri principali in Italia, tra cui BRT; il corriere e il codice della spedizione in I miei ordini
- [Aiuto Amazon: Informazioni di tracciabilità mancanti](https://www.amazon.it/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – nessun numero finché il pacco non arriva al corriere
- [Aiuto Amazon: Consegne effettuate tramite Amazon Logistics](https://www.amazon.it/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – i pacchi che consegna Amazon, tracciati in I miei ordini
- [Aiuto Amazon: Restituisci un regalo](https://www.amazon.it/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – il numero d'ordine a 17 cifre
- [About Amazon: How to track your packages (in inglese)](https://www.aboutamazon.com/news/retail/track-amazon-package) – gli ordini in alto a destra sul sito, l'icona della persona nell'app, Track package
- [Claude Help Center: Get started with Claude in Chrome (in inglese)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – piani Claude a pagamento, il pannello laterale, Claude in Chrome tra i connettori di una chat
- [ChatGPT: Browser extension (in inglese)](https://learn.chatgpt.com/docs/chrome-extension) – si configura dall'app ChatGPT per desktop; legge i siti dove hai effettuato l'accesso e usa anche i plugin
- [Peek: Collegare Peek a Claude, ChatGPT o Gemini](https://peektracker.com/it/assistants) – i passaggi di Peek per Claude e ChatGPT
- [Peek su GitHub: gli strumenti degli assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – cosa dice Peek a un assistente su Amazon: Your Orders, Track package, «Delivery By», «Tracking ID», solo lettura
- [Peek su GitHub: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – più numeri in un solo testo incollato
- [Universal Parcel Scraper: dati dei corrieri](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – le forme dei numeri di Amazon
:::
