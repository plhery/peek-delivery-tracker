---
title: Tracciare i pacchi con ChatGPT, Claude o Gemini
description: Collega Peek a ChatGPT, Claude o Gemini e chiedigli dove sono i tuoi pacchi, o fagli aggiungere i codici tracking delle tue e-mail. Chi può e come fare.
slug: tracciare-pacchi-chatgpt-claude-gemini
picture: Pip, il pacco con la faccia, si sporge da una finestra di chat su un portatile, dove a una domanda sui pacchi di questa settimana risponde una breve lista.
published: 2026-11-19
updated: 2026-11-21
---

Puoi tracciare i tuoi pacchi in Claude, ChatGPT o Gemini collegandoli a [Peek](/), un tracker di pacchi gratuito: l'assistente può poi dirti dove sono i tuoi pacchi e aggiungere i codici tracking che gli dai. Ti serve un account Peek, e ogni assistente decide chi può farlo: Claude con qualsiasi piano, ChatGPT con un piano Business, Enterprise o Edu, Gemini per i maggiorenni negli Stati Uniti con un account Google personale, e OpenClaw per chi lo fa girare. Peek indica la funzione come «Beta», quindi potrebbe ancora cambiare.

## ChatGPT, Claude o Gemini possono tracciare i miei pacchi?

Sì, una volta collegati a Peek. Ogni assistente fissa le sue condizioni:

| Assistente | Dove funziona | Chi può aggiungere Peek |
| --- | --- | --- |
| Claude | claude.ai e le app Claude per desktop, iPhone e Android | Qualsiasi piano. Con un piano Team o Enterprise, prima un proprietario aggiunge Peek |
| ChatGPT | chatgpt.com | Un piano Business, Enterprise o Edu. In uno spazio di lavoro, un amministratore potrebbe dover prima aggiungere o consentire Peek |
| Gemini | Si collega su gemini.google.com, poi funziona anche nell'app Gemini | Almeno 18 anni, negli Stati Uniti, con un account Google personale e «History» attivo, in inglese |
| OpenClaw | Il computer o il server su cui lo fai girare, nelle chat dei suoi agenti | Chi lo fa girare, da un terminale su quella macchina |
| Un altro assistente | Qualsiasi assistente che permetta di aggiungere un connettore personalizzato o un server MCP | Chi può aggiungerne uno lì |

Dal lato di Peek, qualsiasi account può collegarne uno (un ospite no), e Peek è gratuito.

Home Assistant si collega invece con l'integrazione di Peek: vedi la guida [Tracciare i pacchi in Home Assistant](guide:home-assistant).

## Come aggiungo Peek a Claude, ChatGPT o Gemini?

Aggiungi Peek nell'assistente, con l'indirizzo di Peek per gli assistenti: `https://peektracker.com/mcp`. I passaggi qui sotto sono [quelli di Peek](https://peektracker.com/it/assistants); se hai effettuato l'accesso sul sito di Peek (non nelle sue app), li trovi anche in «Impostazioni» → «Consegne» → «Assistenti», con il pulsante «Copia indirizzo».

### Claude

:::steps
- Apri «Peek Parcel Tracker» | Nella [directory dei connettori di Claude](https://claude.ai/directory/peek-parcel-tracker), oppure in Claude: apri «Customize › Connectors», seleziona «+» e cerca Peek.
- Seleziona «Connect» | Claude ti chiede poi di accedere a Peek.
- Accedi a Peek | Scegli cosa può fare Claude, poi seleziona «Consenti».
- Attiva Peek in una chat | Seleziona «+» in basso a sinistra, poi «Connectors», e attiva Peek.
:::

Con un piano Team o Enterprise, prima un proprietario aggiunge Peek: «Organization settings › Connectors», «Browse connectors», poi «Add to your team».

Se Claude non mostra «Peek Parcel Tracker», aggiungi Peek come connettore personalizzato su claude.ai o nell'app desktop (il piano Free ne consente uno):

:::steps
- Apri «Customize › Connectors» | Seleziona «+ Add», poi «Add custom connector».
- Chiamalo Peek | Incolla `https://peektracker.com/mcp` e seleziona «Continue».
- Mantieni le impostazioni di accesso che trova Claude | Seleziona «Continue», poi «Add».
:::

### ChatGPT

:::steps
- Vai su «chatgpt.com/plugins» | Seleziona «+», poi «Add custom MCP server».
- Chiamalo Peek | Incolla `https://peektracker.com/mcp` e scegli «OAuth» come autenticazione. Seleziona «I understand and want to continue», poi «Create as a plugin».
- Accedi a Peek | Scegli cosa può fare ChatGPT, poi seleziona «Consenti».
- Installalo | Apri Peek nei tuoi plugin e seleziona «+». Poi scrivi @ in una nuova chat e scegli Peek.
:::

### Gemini

Secondo la pagina di aiuto di Google, per collegare un'app personalizzata come Peek devi avere almeno 18 anni e trovarti negli Stati Uniti, usare un account Google personale, non di lavoro o della scuola, e avere attivo «History». La colleghi su gemini.google.com e poi funziona anche nell'app Gemini per smartphone, solo in inglese. Google non nomina nessun piano a pagamento. La pagina di Google e quella di Peek dicono entrambe di aggiungere l'indirizzo di Peek nelle «Settings» di Gemini, poi in «Connected Apps», sotto «Custom apps».

### OpenClaw

OpenClaw è un assistente che fai girare tu, sul tuo computer o server. Aggiungi Peek da un terminale su quella macchina, e poi i suoi agenti usano il tuo account Peek in ogni chat, con chiunque stiano parlando. I suoi comandi `openclaw mcp set` e `openclaw mcp login` non partono finché il suo Gateway è attivo, quindi prima fermi il Gateway:

:::steps
- Ferma il Gateway | Esegui `openclaw gateway stop`.
- Aggiungi Peek | Esegui `openclaw mcp set peek '{"url":"https://peektracker.com/mcp","transport":"streamable-http","auth":"oauth"}'`.
- Accedi a Peek | Esegui `openclaw mcp login peek` e apri il link che mostra. Peek lo chiama «OpenClaw MCP»: scegli cosa può fare, poi seleziona «Consenti».
- Riavvia il Gateway | Esegui `openclaw gateway start`, poi chiedi al tuo agente dei tuoi pacchi.
:::

Se il browser è su un altro dispositivo, la pagina che apre dopo l'accesso non si carica: completa con il codice nel suo indirizzo, come spiega OpenClaw nel terminale.

## Cosa posso chiedergli?

Chiedi con parole tue, per esempio:

- «Guarda tra le mie e-mail e aggiungi i codici tracking che trovi.» Funziona se l'assistente può leggere da sé la tua posta, per esempio con il suo connettore Gmail: Peek non legge mai la tua posta.
- «Cosa arriva questa settimana?»
- «Dov’è il mio pacco IKEA?»
- «Archivia tutto quello che è stato consegnato».
- «Qual è l'ultimo pacco che mi è stato consegnato?»

Può anche individuare i pacchi che hanno bisogno di te, come uno in attesa in un punto di ritiro, e quelli previsti per oggi, nel tuo fuso orario.

Per aggiungere pacchi, dagli un codice tracking o la parte dell'e-mail sulla spedizione: Peek segue ogni codice tracking che ci trova, fino a 10 alla volta, e conserva solo i codici e il nome indicato, mai il testo. Un numero d'ordine non è un codice tracking: [ecco come distinguerli](guide:find-tracking-number).

### Ordini Amazon

Le e-mail di Amazon di solito non contengono un codice tracking. Per questo l'assistente ha bisogno del browser in cui hai effettuato l'accesso ad Amazon, su un computer: Claude in Chrome o l'estensione per browser di ChatGPT. Dalla sua app sul telefono, o con un browser che fa girare sui propri server, non arriva ai tuoi ordini, e ha l'istruzione di dirtelo invece di provarci. Un assistente che può navigare sul sito Amazon dove hai effettuato l'accesso riceve l'istruzione di aprire «I miei ordini», poi «Track package», e di inviare il corriere e il «Tracking ID», mai il numero d'ordine. Lì può solo leggere: mai comprare, annullare, restituire o modificare nulla, né accedere al posto tuo. Altrimenti trova il numero da te: [la nostra guida Amazon](guide:amazon-orders) ti mostra dove. Un pacco che Amazon consegna da sé, senza codice tracking, si segue solo nel tuo account Amazon.

## L'assistente può eliminare o modificare i miei pacchi?

Non può mai eliminare un pacco: quello lo fai tu in Peek. Il resto lo decidi tu: quando lo colleghi, la schermata di Peek («Collegare Claude a Peek?», per esempio) mostra il tuo indirizzo e-mail e una casella per ogni permesso che l'assistente chiede:

| Permesso sulla schermata di Peek | Cosa può fare poi l'assistente |
| --- | --- |
| «Vedere i tuoi pacchi: dove si trova ciascuno, quando è previsto e la sua cronologia» | Elencare i tuoi pacchi, e aprirne uno con le sue scansioni |
| «Aggiungere pacchi dai numeri di tracciamento che gli dai e archiviarli» | Aggiungere pacchi, e archiviarne uno |
| «Cambiare il nome, il corriere o le notifiche dei tuoi pacchi e ripristinare quelli archiviati» | Rinominare un pacco, cambiarne il corriere, disattivare o riattivare le sue notifiche o le e-mail di Peek, e ripristinare un pacco archiviato |

Togli la spunta a ciò che non vuoi; «Consenti» resta grigio finché non è spuntata almeno una casella. Se più tardi chiedi qualcosa che non hai permesso, all'assistente viene detto di collegare di nuovo Peek con quel permesso.

## Cosa vede l'assistente, e come lo scollego?

Riceve ciò che hai permesso: nomi, mittenti, corrieri, codici tracking, stati, giorni previsti, punti di ritiro e scansioni dei tuoi pacchi. Mai il nome del destinatario, né il codice postale o le cifre del telefono che hai indicato: una scansione che nomina il destinatario, chi ha firmato, un codice o una porta gli arriva solo come fase, per esempio «Consegnato». Il luogo di una scansione resta come l'ha scritto il corriere, quindi può indicare la località di consegna e il suo codice postale. Poi si applicano le regole sulla privacy del suo fornitore. L'[informativa sulla privacy](https://peektracker.com/privacy.html) di Peek spiega cosa conserva su ogni assistente che colleghi.

Per scollegare un assistente, sul sito di Peek:

:::steps
- Apri «Impostazioni» | Dal pulsante rotondo in alto con la prima lettera del tuo indirizzo e-mail.
- Seleziona «Assistenti» | In «Consegne». Ogni assistente mostra cosa può fare e quando è stato usato l'ultima volta.
- Seleziona «Scollega» | Accanto all'assistente.
:::

Puoi anche rimuovere Peek nelle impostazioni dell'assistente. Un collegamento non usato per 60 giorni termina da solo, ed eliminare il tuo account Peek elimina anche gli assistenti collegati.

## Perché non riesco ad aggiungere Peek al mio assistente?

Di solito dipende dal piano, dal Paese o dall'account:

- **Il tuo piano o il tuo Paese.** Controlla la tabella qui sopra e, con un piano per team o per spazio di lavoro, chiedi al proprietario o all'amministratore.
- **Nessun account Peek.** Accedi quando l'assistente te lo chiede, con un codice di sei cifre che Peek ti manda per e-mail, oppure con Google o Apple se la schermata lo propone. Il primo accesso crea l'account.
- **Peek ti chiede di accedere di nuovo.** Per autorizzare un assistente serve un accesso recente, anche se hai già effettuato l'accesso: accedi, poi seleziona «Consenti».
- **«Questo link non può collegare un assistente».** Ricomincia il collegamento dal tuo assistente.

Altri casi sono in «Se qualcosa non va» sulla [pagina di Peek sugli assistenti](https://peektracker.com/it/assistants), e la [pagina di aiuto](https://peektracker.com/support.html) di Peek spiega come contattarlo.

:::sources
- [Peek: Collegare Peek a Claude, ChatGPT o Gemini](https://peektracker.com/it/assistants) – chi può, i passaggi per ogni assistente, cosa chiedergli, cosa condivide Peek, cosa fare se qualcosa non va
- [Peek su GitHub: la configurazione di ogni assistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – passaggi e nomi dei menu, accesso prima dell'ultimo passaggio
- [Peek su GitHub: testi in italiano](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/it.json) – piani e luoghi, permessi, Beta, cosa fare se qualcosa non va
- [Peek su GitHub: gli strumenti degli assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – cosa può elencare, aggiungere e modificare un assistente, niente eliminazione, Amazon
- [Peek su GitHub: la schermata di consenso](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AssistantConsentScreen.tsx) – caselle, Consenti, accesso recente
- [Peek su GitHub: Impostazioni, Assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – indirizzo, assistenti collegati, Scollega
- [Peek: informativa sulla privacy (in inglese)](https://peektracker.com/privacy.html) – cosa riceve un assistente, cosa conserva Peek, 60 giorni, eliminazione dell'account
- [Peek su GitHub: condizioni d'uso (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratuito, senza costi né abbonamento
- [Peek: aiuto (in inglese)](https://peektracker.com/support.html) – accesso con un codice di sei cifre
- [Assistenza Claude: Iniziare con i connettori personalizzati utilizzando MCP remoto](https://support.claude.com/it/articles/11175166-iniziare-con-i-connettori-personalizzati-utilizzando-mcp-remoto) – passaggi per il connettore personalizzato, uno solo con il piano Free
- [Guida di Gemini: Connect & manage custom apps for Gemini Apps (in inglese)](https://support.google.com/gemini/answer/17209137?hl=en&co=GENIE.Platform%3DDesktop) – chi può collegare un'app personalizzata, History attivo, inglese, collegamento sul web
- [OpenClaw: Transports and OAuth (in inglese)](https://docs.openclaw.ai/cli/mcp/transports) – salvare un server con OAuth, `openclaw mcp login`, completare con il codice, un accesso condiviso
- [OpenClaw: Connect MCP servers (in inglese)](https://docs.openclaw.ai/tools/mcp) – gli agenti usano gli strumenti di un server salvato
- [OpenClaw su GitHub: i comandi MCP e il Gateway (in inglese)](https://github.com/openclaw/openclaw/blob/v2026.11.5/src/cli/mcp-cli-state-owner.ts) – `mcp set` e `mcp login` rifiutati mentre il Gateway è attivo
:::
