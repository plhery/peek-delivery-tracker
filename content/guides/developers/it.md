---
title: Tracciamento pacchi open source: codice e motore di Peek
description: Peek è open source con licenza AGPL-3.0. Ospitalo in proprio, avvialo in locale o traccia i pacchi dal tuo codice con Universal Parcel Scraper: CLI, Node o HTTP.
slug: tracciamento-pacchi-open-source
picture: Pip, il pacco con la faccia, appollaiato su un portatile aperto il cui terminale stampa le scansioni di un pacco riga per riga.
published: 2026-11-19
updated: 2026-11-22
---

[Peek](/) è un tracker di pacchi gratuito e open source: la web app, l'API e le app per iPhone e Android sono su GitHub con licenza GNU AGPL 3.0, e a interrogare i corrieri è un motore separato, Universal Parcel Scraper, libero per usi non commerciali. Peek non ha un'API pubblica con chiavi: per tracciare i pacchi dal tuo codice usa lo scraper, come comando, come libreria Node o come server HTTP, senza account e senza chiave API. Puoi anche ospitare Peek in proprio, o avviarlo sul tuo computer con pacchi inventati.

Per provare lo scraper dal terminale:

:::steps
- Installalo | Ti serve un Node.js recente (il README indica la versione minima). Esegui `npm install -g universal-parcel-scraper`, oppure scrivi `npx universal-parcel-scraper` ovunque qui sotto compaia `parcel-scraper`.
- Trova il corriere | `parcel-scraper detect 1Z999AA10123456784` dice a quale corriere appartiene un numero, offline.
- Scarica la cronologia | `parcel-scraper track` seguito dal tuo numero interroga il corriere e stampa la cronologia del pacco in JSON. Se il numero corrisponde a più corrieri, aggiungi per esempio `--carrier dhl`. Alcuni corrieri vogliono anche `--postcode`, `--phone-digits` o `--tracking-url`.
:::

## Peek è open source? Con quale licenza?

Sì. Il codice di Peek è pubblico su [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker), con licenza GNU Affero General Public License 3.0 (AGPL-3.0): la web app e l'API (Next.js, React, TypeScript), l'app nativa SwiftUI per iPhone, iPad e Mac, e l'app Android. Una copia o una versione modificata mantiene il credito «Based on Peek by Paul-Louis Hery» con il suo link. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), che Peek installa da npm, ha dalla versione 0.1294.0 la licenza PolyForm Noncommercial 1.0.0, che consente solo usi non commerciali; le versioni precedenti restano con licenza Apache 2.0. La sua immagine browser TRAWL, opzionale, ha licenza AGPL-3.0.

Peek è un progetto non commerciale e senza pubblicità; la guida al [tracking universale](guide:universal-tracker) lo confronta con gli altri tracker.

Bug e idee vanno nelle [issue di GitHub](https://github.com/plhery/peek-delivery-tracker/issues) di Peek, che sono pubbliche: non metterci numeri di tracciamento veri, codici postali né codici di accesso. I problemi di sicurezza passano dalla [segnalazione privata di vulnerabilità](https://github.com/plhery/peek-delivery-tracker/security/advisories/new) di GitHub. [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) elenca i controlli da eseguire prima di una pull request.

## Peek ha un'API che posso usare?

Non pubblica: Peek non rilascia chiavi API. La sua API è quella dell'app stessa, quella che chiamano il sito e le app per iPhone e Android, con la sessione dell'utente che ha fatto l'accesso. È descritta in un [file OpenAPI](https://peektracker.com/api/openapi.json), conservato nel repository come `contracts/openapi.json`; peektracker.com/api di per sé risponde 404.

Il catalogo dei corrieri, `GET /api/carriers`, non richiede l'accesso. Nemmeno seguire un pacco senza account, ma lì una nuova ricerca deve prima superare un controllo del browser o dell'app. I token personali per gli script sono disattivati, a meno che il server non li attivi, e in «Impostazioni» non c'è nessuna schermata che ne crei uno.

Dall'esterno, l'unico modo per entrare è l'accesso di un assistente. Claude, ChatGPT, Gemini o un altro assistente accede al tuo account con OAuth standard e usa il server MCP di Peek, `https://peektracker.com/mcp`, nei limiti di ciò che hai consentito. Lo stesso token può leggere i tuoi pacchi in JSON su `GET /api/parcels`, e l'integrazione di Peek per Home Assistant accede allo stesso modo. Vedi [la guida agli assistenti](guide:ai-assistants), [la pagina di Peek per gli assistenti](https://peektracker.com/it/assistants) e [la guida a Home Assistant](guide:home-assistant).

## Come traccio i pacchi dal mio codice senza una chiave API?

Con Universal Parcel Scraper, il motore che interroga i corrieri per Peek. Trova il corriere, legge il sito del corriere stesso, ripiega sui tracker universali solo quando deve, e restituisce lo stesso JSON per ogni corriere, con ogni scansione assegnata a una fase di consegna comune.

Il suo README conta oltre 4.500 corrieri, tramite più di 800 adattatori dedicati e fallback universali. Nel suo benchmark su 100 corrieri scelti per chi compra in Europa, gli adattatori restituiscono una cronologia per 92 corrieri, 95 con tutti i fallback, contro i 73 del miglior aggregatore singolo.

Oltre alla riga di comando, funziona in tre modi:

| Dove | Come |
| --- | --- |
| Node | Importa `createTracker` da `universal-parcel-scraper/node`; `tracker.track({ number })` restituisce il corriere, chi ha risposto, le scansioni, la fase attuale e ciò che mostra il corriere, come la consegna prevista |
| Un browser | Solo il riconoscimento del corriere, con `parseTrackingInput`: nessun import Node, nessuna richiesta |
| HTTP | `parcel-scraper serve --port 8080`, oppure l'immagine Docker `ghcr.io/plhery/universal-parcel-scraper`, con tag solo per le versioni rilasciate; poi una POST su `/v1/track`. Il server tiene le risposte in cache in memoria e limita le richieste di chi lo chiama; non ha database e non fa mai polling |

Prima di costruirci sopra:

- **Risponde a una ricerca alla volta.** Salvare i pacchi e ricontrollarli tocca a te: è la parte che aggiunge Peek.
- **Alcuni siti di corrieri rispondono solo a un browser.** Per quelli gli serve un Chromium che pilota tramite pacchetti opzionali, o il suo servizio browser TRAWL. L'immagine Docker include Chromium.
- **I fallback vanno attivati.** Solo UPU è attivo di default; ParcelsApp, Ship24, 17TRACK, Postal Ninja e Track718 aspettano che li attivi tu, e ognuno di quelli che attivi riceve il numero di tracciamento.
- **Una ricerca invia il numero al corriere**, con il codice postale, il link o le cifre del numero di telefono quando il corriere li chiede. Nessuna telemetria.

## Posso ospitare Peek in proprio?

Sì. Un Peek self-hosted è un unico container sempre in esecuzione, con la web app, l'API e il worker che continua a controllare i corrieri, accanto a Supabase per l'accesso e Postgres, dietro HTTPS. Ti servono:

- **uno stack Supabase** (Auth, PostgREST e Postgres) con backup e ripristino testati;
- **SMTP**, se l'accesso via e-mail o l'e-mail di consegna è attivo;
- **un host che sappia fare la build del Dockerfile e tenere in esecuzione il container.** L'hosting serverless non va bene: il worker deve restare in vita.

Le notifiche web push, quelle per iPhone (un team Apple e una chiave APNs) e quelle per Android (Firebase) sono opzionali. Anche gli assistenti: senza `ASSISTANT_ACCOUNTS`, `/mcp` e `/oauth` non ci sono. Questa variabile elenca gli account che possono collegarsi, o `*` per tutti, e richiede `CANONICAL_ORIGIN`. Quando è impostata, `PERSONAL_TOKENS=on` attiva i token personali per i tuoi script, creati tramite l'API. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) ha i passaggi, dal database e dall'accesso fino agli aggiornamenti, ai backup e alla migrazione su un altro host.

### Provalo prima sul tuo computer

Non servono account, database né file `.env`:

:::steps
- Prendi il codice | Clona `https://github.com/plhery/peek-delivery-tracker.git`. Ti servono nvm e la versione di Node.js indicata nel README.
- Installa | Nella sua cartella, esegui `nvm use`, poi `npm ci`.
- Avvia | Esegui `npm run dev` e apri localhost:3000: Peek risponde nel browser con pacchi inventati. localhost:3000/demo elenca consegne di esempio che vanno avanti quando ricarichi la pagina.
:::

Per account e tracciamento veri, copia `.env.example` in `.env.local`, inserisci un progetto Supabase e imposta `NEXT_PUBLIC_USE_API=true`; [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) ha i passaggi per l'accesso. L'app per iPhone si compila in Xcode per un simulatore di iPhone e parte con pacchi di esempio, senza account, server né team Apple.

## Manca un corriere: come lo segnalo o lo aggiungo?

Segnalalo allo scraper, dove vivono i corrieri. Nella sua [pagina per aprire una nuova issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), a cui il README di Peek rimanda come «Tell the scraper», scegli «Add a carrier» (nome, paese d'origine e sito di tracciamento pubblico del corriere) o «A carrier stopped working». Nuovi corrieri e correzioni arrivano allo scraper come pull request: ogni corriere ha la sua cartella con fixture sintetiche e test offline, e `npm run carrier:new` ne crea lo scheletro.

Peek adotta da solo le nuove versioni dello scraper, quindi per un corriere non serve nessuna pull request a Peek. E l'app per iPhone aggiorna la lista dei corrieri dal server, quindi non serve nemmeno una nuova versione dell'app.

Non hai un account GitHub? Dillo a Peek dalla pagina del pacco, sul web, su iPhone o su Android, con o senza account Peek:

:::steps
- Rispondi alla domanda | Quando Peek non trova un corriere per un pacco, la sua pagina chiede «Il sito del corriere lo mostra?». Scegli «Sì».
- Indica il corriere | In «Chi lo trasporta?», scrivilo sotto «Corriere o negozio», oppure incolla il suo link di tracciamento sotto «La sua pagina di tracciamento, se hai il link». Basta uno dei due.
- Invia | Scegli «Invia». La risposta parte con il numero del pacco e niente su di te.
:::

Una persona legge queste risposte, ma nessuno può risponderti: per avere una risposta scrivi a hello@peektracker.com, l'indirizzo indicato nella [pagina di aiuto di Peek](https://peektracker.com/support.html). Come Peek riconosce un corriere da quello che incolli è spiegato in [Tracciare un pacco senza sapere il corriere](guide:track-without-carrier).

:::sources
- [Peek su GitHub: README (in inglese)](https://github.com/plhery/peek-delivery-tracker) – cosa contiene il repository, i corrieri nello scraper, avviarlo in locale, ospitarlo in proprio, la build per iPhone
- [Peek su GitHub: LICENSE (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – GNU Affero General Public License 3.0
- [Universal Parcel Scraper su GitHub (in inglese)](https://github.com/plhery/universal-parcel-scraper) – comandi, opzioni, Node, browser, HTTP e Docker, Chromium nell'immagine, fallback, privacy, numero di corrieri, benchmark, licenze, aggiungere un corriere
- [Universal Parcel Scraper: nuova issue (in inglese)](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – i moduli «Add a carrier» e «A carrier stopped working»
- [Universal Parcel Scraper su npm (in inglese)](https://www.npmjs.com/package/universal-parcel-scraper) – il pacchetto e la versione di Node.js richiesta
- [Peek: file OpenAPI](https://peektracker.com/api/openapi.json) – l'API dell'app stessa, le rotte che non richiedono l'accesso e i loro controlli, leggere i pacchi con il token di un assistente
- [Peek su GitHub: architettura (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – l'accesso degli assistenti e di Home Assistant, GET /api/parcels, token personali disattivati
- [Peek su GitHub: impostazioni degli assistenti](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – token personali disattivati se PERSONAL_TOKENS non è attivo, ASSISTANT_ACCOUNTS
- [Peek: Collegare Peek a Claude, ChatGPT o Gemini](https://peektracker.com/it/assistants) – la via d'accesso per gli assistenti
- [Peek su GitHub: deployment (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – cosa serve per il self-hosting, le parti opzionali, assistenti e token personali sulla tua istanza
- [Peek su GitHub: come contribuire (in inglese)](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – controlli prima di una pull request, i corrieri vanno nello scraper, nuove versioni adottate da sole
- [Peek su GitHub: feedback sul pacco](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – «Chi lo trasporta?» quando non si trova un corriere, basta uno dei due campi per inviare
- [Peek: aiuto (in inglese)](https://peektracker.com/support.html) – segnalare un corriere senza GitHub, issue pubbliche, segnalazioni di sicurezza, contatti
- [Peek: condizioni d'uso (in inglese)](https://peektracker.com/terms.html) – un progetto gratuito e non commerciale, senza pubblicità
:::
