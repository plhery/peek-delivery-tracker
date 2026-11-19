---
title: Tracciare i pacchi in Home Assistant
description: L'integrazione Peek porta i pacchi in Home Assistant come sensori, calendario delle consegne e scheda per la dashboard. Requisiti, installazione e configurazione.
slug: tracciare-pacchi-home-assistant
picture: Un tablet su una parete della cucina mostra una dashboard di casa, con un elenco di pacchi, un piccolo calendario delle consegne e Pip, il pacco con la faccia, che sorride da una delle schede.
published: 2026-11-19
updated: 2026-11-19
---

Per tracciare i tuoi pacchi in Home Assistant, installa Peek per Home Assistant: l'integrazione porta il tuo elenco di pacchi di [Peek](/) in Home Assistant sotto forma di sensori, di un calendario delle consegne, di una scheda per la dashboard e di uno strumento per Assist. Ti servono un account Peek gratuito e Home Assistant 2026.11 o successivo, dove la aggiungi dal Marketplace tramite il suo link; con la versione 2026.10, il README dell'integrazione indica una strada tramite HACS.

## Cosa mostra Peek in Home Assistant?

I tuoi pacchi di Peek, nelle forme che usa Home Assistant:

| Cosa | Cosa fa |
| --- | --- |
| Sensori | Contano i pacchi in viaggio, quelli che arrivano oggi, quelli che hanno bisogno di te (in attesa in un punto di ritiro, una consegna mancata, la dogana) e le consegne di oggi, e indicano il prossimo giorno di consegna |
| Un calendario «Deliveries» | Eventi di un'intera giornata: ogni pacco in viaggio nel suo giorno previsto, ogni consegna nel giorno in cui è arrivata |
| Un evento «Parcel updates» | Si attiva quando un pacco viene aggiunto, cambia stato, parte per la consegna, ha bisogno di te, viene consegnato o cambia il suo giorno previsto |
| Tre azioni | Aggiungere pacchi da un testo, archiviare un pacco o leggere l'elenco |
| La scheda «Peek parcels» | I tuoi pacchi su una dashboard, come elenco o come schede con Pip |
| Uno strumento per Assist | Risponde a domande come «Cosa arriva oggi?» |

Home Assistant interroga Peek ogni 30 minuti, ogni 10 minuti in un giorno in cui un pacco è in consegna o previsto, e subito dopo aver aggiunto o archiviato un pacco. Per interrogarlo subito, chiama `homeassistant.update_entity` su una qualsiasi entità di Peek. «Oggi» è il giorno nel fuso orario di Home Assistant.

## Cosa mi serve?

- **Home Assistant 2026.11 o successivo**, come dice la pagina di Peek, e un accesso da amministratore: il Marketplace di Home Assistant è solo per gli amministratori. Con la versione 2026.10, il README dell'integrazione indica una strada tramite HACS.
- **Un account Peek.** La configurazione apre Peek per farti accedere, con un codice di sei cifre che Peek ti manda per e-mail, oppure con Google o Apple dove la schermata lo propone. Il tuo primo accesso crea l'account; un ospite non può collegare Home Assistant.
- **Nient'altro.** Peek è gratuito, e non c'è nessun client id, secret o Application Credentials da digitare. A ogni Home Assistant si può collegare un solo account Peek.

L'integrazione si collega come fanno gli assistenti, e i collegamenti di Peek per gli assistenti sono in beta. Se Peek dice «Peek non può ancora collegare assistenti a questo account.», l'integrazione non si può ancora configurare.

## Come installo l'integrazione Peek?

Dal Marketplace di Home Assistant, tramite il suo link, perché Peek non è ancora nel catalogo del Marketplace. Con la versione 2026.10, usa HACS o installala a mano.

### Dal Marketplace (Home Assistant 2026.11 e successivi)

:::steps
- Apri «Settings › Marketplace» | Seleziona «Add from link», inserisci `https://github.com/plhery/peek-ha` e seleziona «Add». La prima volta, il Marketplace ti chiede di collegare GitHub.
- Installa Peek | Apri Peek nell'elenco e seleziona «Install», poi «Set up».
- Accedi a Peek | Il browser apre Peek. Controlla cosa chiede Home Assistant (vedere i tuoi pacchi, aggiungerli e archiviarli), poi seleziona «Consenti».
- Torna a Home Assistant | Peek ti riporta indietro tramite My Home Assistant (my.home-assistant.io). La prima volta, ti chiede l'indirizzo del tuo Home Assistant.
:::

### Con HACS (Home Assistant 2026.10)

:::steps
- Aggiungi un repository personalizzato | In HACS, aggiungi `https://github.com/plhery/peek-ha` come repository personalizzato di tipo «Integration».
- Scarica Peek | Poi riavvia Home Assistant.
- Aggiungi l'integrazione | Vai in «Settings › Devices & services › Add integration», scegli Peek, accedi a Peek e seleziona «Consenti».
:::

Puoi anche installarla a mano: copia la cartella `custom_components/peek` nella cartella `custom_components` della tua configurazione, riavvia e aggiungi l'integrazione come sopra.

## Come aggiungo la scheda Peek parcels alla mia dashboard?

L'integrazione carica da sola la sua scheda su ogni dashboard: non c'è nessuna risorsa da aggiungere. Una pagina già aperta mentre configuri Peek non ha ancora la scheda: ricaricala prima, oppure chiudi l'app Home Assistant e riaprila.

:::steps
- Modifica una dashboard | Aprila e seleziona la matita. Su una dashboard che Home Assistant ha creato per te, prima ti chiede di prenderne il controllo.
- Seleziona «Add card» | Poi la tab «By card». La tab «By entity» cerca entità, non schede.
- Scegli «Peek parcels» | Cerca «Peek», scegli «Peek parcels» e salva.
:::

La scheda esiste in due stili: un elenco, quello predefinito, con una riga per ogni pacco che mostra il furgone del suo corriere, la sua fase e il suo giorno; oppure una scheda per ogni pacco nei colori del suo corriere, come i widget di Peek per iPhone e Android, con Pip, la città dell'ultima scansione, il nome del pacco, la sua fase e il suo giorno. Entrambi seguono le sezioni di Peek, a partire dai pacchi che hanno bisogno di te, e finiscono con «+N more» quando ci sono più pacchi che righe.

La scheda segue il tuo tema, chiaro o scuro, e la tua lingua, se è l'inglese o il francese. In YAML è `type: custom:peek-parcels-card`; il README dell'integrazione elenca le sue opzioni.

## Posso usarla nelle automazioni o chiedere ad Assist?

Sì. Il README ha quattro automazioni pronte:

- una notifica sul telefono quando un pacco parte per la consegna;
- aggiungere i codici tracking dalle tue e-mail di spedizione, lette con l'integrazione IMAP di Home Assistant;
- un messaggio al mattino con ciò che arriva oggi;
- archiviare ciò che è stato consegnato, ogni sera.

Quando aggiungi pacchi da un testo, Peek aggiunge ogni codice tracking che trova, al massimo 10 alla volta, e conserva solo i codici e il nome che indichi, quindi invia solo la parte di un'e-mail che riguarda la spedizione, quando puoi.

Una volta che una delle entità di Peek è esposta ad Assist («Settings › Voice assistants › Expose»), puoi chiedere «Cosa arriva oggi?» o dov'è il pacco di un certo negozio. Lo strumento Peek di Assist legge soltanto: non aggiunge né archivia mai pacchi. Ciò che restituisce (nomi, negozi, corrieri, fasi, giorni previsti e luogo dell'ultima scansione) va all'agente di conversazione che hai scelto, che può essere un servizio cloud.

## Cosa non può fare l'integrazione?

- **Eliminare o modificare pacchi.** Home Assistant chiede solo di vedere i tuoi pacchi, e di aggiungerli e archiviarli. Puoi ripristinare un pacco archiviato in Peek.
- **Collegare Claude, ChatGPT o Gemini.** Si collegano a Peek da soli: vedi la guida [Tracciare i pacchi con ChatGPT, Claude o Gemini](guide:ai-assistants).

## Cosa conserva Home Assistant, e come rimuovo l'integrazione?

Home Assistant parla solo con Peek, su peektracker.com, e conserva i token dell'accesso nella sua configurazione, come qualsiasi integrazione. La sua cronologia registra i conteggi dei sensori e gli eventi «Parcel updates», con nome, corriere, fase e ultimo luogo del pacco, ma non gli elenchi di pacchi dei sensori. La scheda gira nel tuo browser con i dati di Home Assistant stesso e non carica nulla da altrove.

Per rimuoverla, elimina Peek in «Settings › Devices & services»: Home Assistant restituisce il suo accesso a Peek, che dimentica il collegamento, e i tuoi pacchi restano in Peek. Se in quel momento Peek non è raggiungibile, o per staccare Home Assistant dal lato di Peek, seleziona «Scollega» accanto a Home Assistant sul sito di Peek (non nelle sue app), in «Impostazioni» → «Consegne» → «Assistenti». Se poi tieni l'integrazione, mostrerà «Authentication expired for Peek»; riconfigurarla avvia lo stesso accesso.

:::sources
- [Peek per Home Assistant: README (in inglese)](https://github.com/plhery/peek-ha) – cosa aggiunge, versioni, installazione, accesso, la scheda, automazioni, Assist, privacy, rimozione
- [Peek per Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – la versione più vecchia di Home Assistant su cui si installa, 2026.10
- [Home Assistant: Marketplace (in inglese)](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, Add from link, collegare GitHub, Install, solo per gli amministratori, sostituisce HACS dalla 2026.11
- [Peek: Collegare Peek a Claude, ChatGPT o Gemini](https://peektracker.com/it/assistants) – i passaggi per Home Assistant sulla pagina di Peek, 2026.11 o successivo
- [Peek su GitHub: la configurazione di ogni assistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – i nomi dei menu di Home Assistant
- [Peek su GitHub: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – versione, passaggi, account, Beta
- [Peek su GitHub: Impostazioni, Assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – assistenti collegati, Scollega
- [Peek: aiuto (in inglese)](https://peektracker.com/support.html) – accesso con un codice di sei cifre
- [Peek su GitHub: condizioni d'uso (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratuito, senza costi né abbonamento
:::
