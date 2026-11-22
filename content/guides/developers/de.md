---
title: Open-Source-Sendungsverfolgung: Peeks Code und Engine
description: Peek ist Open Source unter AGPL-3.0. Hoste es selbst, starte es lokal oder verfolge Pakete aus deinem Code mit Universal Parcel Scraper: CLI, Node oder HTTP.
slug: open-source-sendungsverfolgung
picture: Pip, das Paket mit Gesicht, sitzt auf einem aufgeklappten Laptop, dessen Terminal die Scans eines Pakets Zeile für Zeile ausgibt.
published: 2026-11-19
updated: 2026-11-22
---

[Peek](/) ist eine kostenlose Open-Source-Sendungsverfolgung: Web-App, API sowie iPhone- und Android-App liegen unter der GNU AGPL 3.0 auf GitHub, und die Abfrage der Paketdienste übernimmt eine eigenständige Engine, Universal Parcel Scraper, frei für nicht kommerzielle Zwecke. Eine öffentliche API mit Keys hat Peek nicht. Um Pakete aus deinem eigenen Code zu verfolgen, nimm deshalb den Scraper, als Befehl, als Node-Bibliothek oder als HTTP-Server, ohne Konto und ohne API-Key. Du kannst Peek auch selbst hosten oder es mit erfundenen Paketen auf deinem Rechner starten.

So probierst du den Scraper im Terminal aus:

:::steps
- Installiere ihn | Du brauchst ein aktuelles Node.js (die Mindestversion steht im README). Führe `npm install -g universal-parcel-scraper` aus, oder schreib `npx universal-parcel-scraper` überall dort, wo unten `parcel-scraper` steht.
- Bestimme den Paketdienst | `parcel-scraper detect 1Z999AA10123456784` sagt dir offline, zu welchem Paketdienst eine Nummer gehört.
- Hol den Verlauf | `parcel-scraper track` mit deiner Nummer dahinter fragt beim Paketdienst nach und gibt den Verlauf des Pakets als JSON aus. Passt die Nummer zu mehreren Paketdiensten, häng zum Beispiel `--carrier dhl` an. Manche Paketdienste wollen außerdem `--postcode`, `--phone-digits` oder `--tracking-url`.
:::

## Ist Peek Open Source, und unter welcher Lizenz?

Ja. Peeks Code ist öffentlich auf [github.com/plhery/peek-delivery-tracker](https://github.com/plhery/peek-delivery-tracker) und steht unter der GNU Affero General Public License 3.0 (AGPL-3.0): die Web-App und die API (Next.js, React, TypeScript), die native SwiftUI-App für iPhone, iPad und Mac und die Android-App. Eine Kopie oder veränderte Version behält den Hinweis „Based on Peek by Paul-Louis Hery“ mit seinem Link. [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper), den Peek über npm installiert, steht ab Version 0.1294.0 unter der PolyForm Noncommercial License 1.0.0, die nur nicht kommerzielle Zwecke erlaubt; ältere Versionen bleiben unter Apache 2.0. Sein optionales TRAWL-Browser-Image steht unter AGPL-3.0.

Peek ist ein nicht kommerzielles Projekt ohne Werbung; [Paketverfolgung aller Anbieter](guide:universal-tracker) vergleicht es mit anderen Trackern.

Fehler und Ideen gehören in Peeks [GitHub-Issues](https://github.com/plhery/peek-delivery-tracker/issues), und die sind öffentlich: Lass echte Sendungsnummern, Postleitzahlen und Anmeldecodes weg. Sicherheitsprobleme meldest du über GitHubs [private Meldung von Sicherheitslücken](https://github.com/plhery/peek-delivery-tracker/security/advisories/new). In [CONTRIBUTING.md](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) stehen die Prüfungen, die vor einem Pull Request laufen sollen.

## Hat Peek eine API, die ich nutzen kann?

Keine öffentliche: Peek vergibt keine API-Keys. Seine API ist die der App selbst, die die Website und die iPhone- und Android-Apps aufrufen, angemeldet mit der Sitzung des Nutzers. Beschrieben ist sie in einer [OpenAPI-Datei](https://peektracker.com/api/openapi.json), die im Repository als `contracts/openapi.json` liegt; peektracker.com/api selbst antwortet mit 404.

Der Katalog der Paketdienste, `GET /api/carriers`, braucht keine Anmeldung. Das Verfolgen eines Pakets ohne Konto auch nicht, aber eine neue Abfrage muss dort zuerst eine Prüfung im Browser oder in der App bestehen. Persönliche Tokens für Skripte sind aus, solange der Server sie nicht einschaltet, und in den „Einstellungen“ gibt es keinen Bildschirm, der eins erstellt.

Von außen führt nur ein Weg hinein: die Anmeldung eines Assistenten. Claude, ChatGPT, Gemini oder ein anderer Assistent meldet sich mit Standard-OAuth bei deinem Konto an und nutzt Peeks MCP-Server, `https://peektracker.com/mcp`, im Rahmen dessen, was du erlaubt hast. Mit demselben Token lassen sich deine Pakete als JSON unter `GET /api/parcels` lesen, und Peeks Integration für Home Assistant meldet sich auf dieselbe Weise an. Siehe [den Ratgeber zu Assistenten](guide:ai-assistants), [Peeks Seite für Assistenten](https://peektracker.com/de/assistants) und [den Ratgeber zu Home Assistant](guide:home-assistant).

## Wie verfolge ich Pakete aus meinem eigenen Code ohne API-Key?

Mit Universal Parcel Scraper, der Engine, die für Peek die Paketdienste abfragt. Er findet den Paketdienst, liest dessen eigene Website, weicht nur wo nötig auf universelle Tracker aus und liefert für jeden Paketdienst dasselbe JSON, in dem jeder Scan einem gemeinsamen Zustellschritt zugeordnet ist.

Sein README zählt mehr als 4.500 Paketdienste, angebunden über mehr als 800 eigene Adapter und universelle Fallbacks. In seinem Benchmark mit 100 Paketdiensten, ausgewählt für Kunden in Europa, liefern die Adapter für 92 einen Verlauf, mit allen Fallbacks für 95, gegenüber 73 beim besten einzelnen Aggregator.

Neben der Kommandozeile läuft er auf drei Arten:

| Wo | Wie |
| --- | --- |
| Node | Importiere `createTracker` aus `universal-parcel-scraper/node`; `tracker.track({ number })` liefert den Paketdienst, wer geantwortet hat, die Scans, den aktuellen Zustellschritt und was der Paketdienst anzeigt, etwa die erwartete Zustellung |
| Ein Browser | Nur die Erkennung des Paketdiensts, mit `parseTrackingInput`: keine Node-Importe, keine Anfragen |
| HTTP | `parcel-scraper serve --port 8080` oder das Docker-Image `ghcr.io/plhery/universal-parcel-scraper`, das nur mit Release-Versionen getaggt wird; dann per POST an `/v1/track`. Der Server hält Antworten im Arbeitsspeicher vor und begrenzt die Anfragen pro Aufrufer; er hat keine Datenbank und fragt nie von sich aus nach |

Bevor du darauf aufbaust:

- **Er beantwortet jeweils eine Abfrage.** Pakete speichern und erneut abfragen musst du selbst: Das ist der Teil, den Peek hinzufügt.
- **Manche Websites von Paketdiensten antworten nur einem Browser.** Für sie braucht er ein Chromium, das er über optionale Zusatzpakete steuert, oder seinen TRAWL-Browserdienst. Das Docker-Image bringt Chromium mit.
- **Fallbacks musst du selbst einschalten.** Nur UPU ist standardmäßig an; ParcelsApp, Ship24, 17TRACK, Postal Ninja und Track718 warten, bis du sie aktivierst, und jeder, den du aktivierst, bekommt die Sendungsnummer.
- **Eine Abfrage schickt die Nummer an den Paketdienst**, mit Postleitzahl, Link oder Ziffern der Telefonnummer, wenn der Paketdienst danach fragt. Telemetrie gibt es keine.

## Kann ich Peek selbst hosten?

Ja. Ein selbst gehostetes Peek ist ein einziger, dauerhaft laufender Container mit der Web-App, der API und dem Worker, der immer wieder bei den Paketdiensten nachfragt, daneben Supabase für die Anmeldung und Postgres, hinter HTTPS. Du brauchst:

- **einen Supabase-Stack** (Auth, PostgREST und Postgres) mit getestetem Backup und Restore;
- **SMTP**, wenn die Anmeldung per E-Mail oder die Zustell-E-Mail eingeschaltet ist;
- **einen Host, der das Dockerfile bauen und den Container am Laufen halten kann.** Serverless-Hosting funktioniert nicht: Der Worker muss am Leben bleiben.

Web-Push, Mitteilungen auf dem iPhone (ein Apple-Team und ein APNs-Key) und auf Android (Firebase) sind optional. Assistenten auch: Ohne `ASSISTANT_ACCOUNTS` gibt es `/mcp` und `/oauth` nicht. Die Einstellung listet die Konten, die sich verbinden dürfen, oder `*` für alle, und braucht `CANONICAL_ORIGIN`. Ist sie gesetzt, schaltet `PERSONAL_TOKENS=on` persönliche Tokens für deine Skripte ein, die du über die API erstellst. [docs/DEPLOYMENT.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) enthält die Schritte, von der Datenbank und der Anmeldung bis zu Upgrades, Backups und dem Umzug auf einen anderen Host.

### Erst auf deinem Rechner ausprobieren

Kein Konto, keine Datenbank und keine `.env`-Datei nötig:

:::steps
- Hol den Code | Klone `https://github.com/plhery/peek-delivery-tracker.git`. Du brauchst nvm und die Node.js-Version, die im README steht.
- Installiere | Führe in seinem Ordner `nvm use` aus, dann `npm ci`.
- Starte | Führe `npm run dev` aus und öffne localhost:3000: Peek antwortet im Browser mit erfundenen Paketen. Unter localhost:3000/demo steht eine Liste von Beispiellieferungen, die bei jedem Neuladen ein Stück weiterkommen.
:::

Für echte Konten und echte Sendungsverfolgung kopierst du `.env.example` nach `.env.local`, trägst ein Supabase-Projekt ein und setzt `NEXT_PUBLIC_USE_API=true`; in [docs/AUTHENTICATION.md](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/AUTHENTICATION.md) stehen die Schritte für die Anmeldung. Die iPhone-App lässt sich in Xcode für einen iPhone-Simulator bauen und startet mit Beispielpaketen, ohne Konto, Server oder Apple-Team.

## Ein Paketdienst fehlt: Wie melde oder ergänze ich ihn?

Melde ihn beim Scraper, denn dort leben die Paketdienste. Auf seiner [Seite für ein neues Issue](https://github.com/plhery/universal-parcel-scraper/issues/new/choose), die Peeks README als „Tell the scraper“ verlinkt, wählst du „Add a carrier“ (Name, Heimatland und öffentliche Tracking-Website des Paketdiensts) oder „A carrier stopped working“. Neue Paketdienste und Korrekturen gehen als Pull Requests an den Scraper: Jeder Paketdienst hat einen eigenen Ordner mit synthetischen Fixtures und Offline-Tests, und `npm run carrier:new` legt das Grundgerüst für einen neuen an.

Peek übernimmt neue Releases des Scrapers von selbst, ein Paketdienst braucht also keinen Pull Request an Peek. Und die iPhone-App holt sich ihre Liste der Paketdienste neu vom Server, also braucht es auch kein neues App-Release.

Kein GitHub-Konto? Sag es Peek auf der Seite des Pakets, im Web, auf dem iPhone oder auf Android, mit oder ohne Peek-Konto:

:::steps
- Beantworte die Frage | Findet Peek für ein Paket keinen Paketdienst, fragt seine Seite „Zeigt die Website des Paketdienstes das Paket?“ Wähle „Ja“.
- Nenne den Paketdienst | Gib ihn unter „Wer liefert es?“ bei „Paketdienst oder Shop“ an, oder füge seinen Tracking-Link bei „Seine Tracking-Seite, falls du den Link hast“ ein. Eins von beiden reicht.
- Schick es ab | Wähle „Senden“. Mitgeschickt wird die Nummer des Pakets, nichts über dich.
:::

Ein Mensch liest diese Antworten, aber niemand kann dir zurückschreiben: Wenn du eine Antwort möchtest, schreib an hello@peektracker.com, die Adresse auf [Peeks Hilfeseite](https://peektracker.com/support.html). Wie Peek aus dem, was du einfügst, den Paketdienst bestimmt, steht in [Paket verfolgen, ohne den Paketdienst zu kennen](guide:track-without-carrier).

:::sources
- [Peek auf GitHub: README (auf Englisch)](https://github.com/plhery/peek-delivery-tracker) – was das Repository enthält, Paketdienste im Scraper, lokal starten, selbst hosten, der Build der iPhone-App
- [Peek auf GitHub: LICENSE (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/LICENSE) – GNU Affero General Public License 3.0
- [Universal Parcel Scraper auf GitHub (auf Englisch)](https://github.com/plhery/universal-parcel-scraper) – Befehle, Optionen, Node, Browser, HTTP und Docker, Chromium im Image, Fallbacks, Datenschutz, Zahl der Paketdienste, Benchmark, Lizenzen, einen Paketdienst hinzufügen
- [Universal Parcel Scraper: neues Issue (auf Englisch)](https://github.com/plhery/universal-parcel-scraper/issues/new/choose) – die Formulare „Add a carrier“ und „A carrier stopped working“
- [Universal Parcel Scraper auf npm (auf Englisch)](https://www.npmjs.com/package/universal-parcel-scraper) – das Paket und die Node.js-Version, die es braucht
- [Peek: OpenAPI-Datei](https://peektracker.com/api/openapi.json) – die eigene API der App, die Routen ohne Anmeldung und ihre Prüfungen, Pakete lesen mit dem Token eines Assistenten
- [Peek auf GitHub: Architektur (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – die Anmeldung der Assistenten und von Home Assistant, GET /api/parcels, persönliche Tokens aus
- [Peek auf GitHub: Einstellungen der Assistenten](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/settings.ts) – persönliche Tokens aus, solange PERSONAL_TOKENS nicht an ist, ASSISTANT_ACCOUNTS
- [Peek: Peek mit Claude, ChatGPT oder Gemini verbinden](https://peektracker.com/de/assistants) – der Weg hinein für Assistenten
- [Peek auf GitHub: Deployment (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/DEPLOYMENT.md) – was Self-Hosting braucht, optionale Teile, Assistenten und persönliche Tokens auf deiner eigenen Instanz
- [Peek auf GitHub: Mitwirken (auf Englisch)](https://github.com/plhery/peek-delivery-tracker/blob/main/CONTRIBUTING.md) – Prüfungen vor einem Pull Request, Paketdienste gehören in den Scraper, Releases werden von selbst übernommen
- [Peek auf GitHub: Rückmeldung zum Paket](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/parcel/Feedback.tsx) – „Wer liefert es?“, wenn kein Paketdienst gefunden wird, eins der beiden Felder reicht zum Senden
- [Peek: Hilfe (auf Englisch)](https://peektracker.com/support.html) – einen Paketdienst ohne GitHub melden, öffentliche Issues, Sicherheitsmeldungen, Kontakt
- [Peek: Nutzungsbedingungen (auf Englisch)](https://peektracker.com/terms.html) – ein kostenloses, nicht kommerzielles Projekt ohne Werbung
:::
