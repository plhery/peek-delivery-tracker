---
title: Jadlog Sendungsverfolgung: 14 Ziffern, Pickup & Predict
description: Jadlog-Paket in Brasilien verfolgen: 14-stellige Nummern, Status auf Portugiesisch und bei Geopost, Jadlog Pickup, 1-Stunden-Predict-SMS und Hilfe.
slug: jadlog-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Jadlog zählt zu Brasiliens größten Expresspaket- und E-Commerce-Logistikern und ist die brasilianische Tochter der europäischen Geopost-Gruppe (neben DPD, Chronopost, SEUR, BRT und Speedy). Landesweit betreibt das Unternehmen über 500 Franchises in rund 5.000 Gemeinden, mehr als 7.000 Fahrzeuge und über 4.000 Jadlog-Pickup-Partnergeschäfte. Du kannst eine Sendung im Feld `RASTREIE SUA ENCOMENDA` auf der [Jadlog-Startseite](https://www.jadlog.com.br/jadlog/home) verfolgen oder den 14-stelligen Code oben einfügen.

## Jadlog-Sendungsnummer: 14-stelliges Format und Fundort

Deinen Versandcode (*código de rastreio* oder *remessa*) findest du in der Versand-E-Mail des Onlineshops, in deiner Marktplatz-Bestellübersicht oder auf dem Versandetikett. Jadlog-Sendungsnummern bestehen aus 14 aufeinanderfolgenden Ziffern:

| Format (erfunden) | Was es ist |
| --- | --- |
| `48291736405821` | 14-stellige Jadlog-Paketnummer. Teilt sich das 14-stellige Format mit europäischen Geopost-/DPD-Diensten wie [DPD Deutschland](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) und [BRT](carrier:brt). |

Peek verfolgt Sendungen allein anhand der 14-stelligen Nummer und fragt nie nach deinem CPF. Hat dein Code 15 Ziffern und beginnt mit `8880`, gehört er zu [J&T Express Brazil](carrier:j-and-t-br); ein 15-stelliger `BR`-Code gehört zu [SPX Express Brazil](carrier:spx-br); ein 17-stelliger `AJ`-Code zu [Anjun Logistics](carrier:anjun); und ein 13-stelliger Code mit Endung `BR` zu [Correios](carrier:correios-br). Weitere Formate findest du in [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Jadlog-Sendungsstatus bedeuten

Da Jadlog zur Geopost-Gruppe gehört, erscheinen Statusmeldungen auf der Jadlog-Website auf Portugiesisch oder auf Englisch, wenn sie über die Geopost-Tracking-API gelesen werden.

| Jadlog / Geopost zeigt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | Der Verkäufer hat die Sendung elektronisch angelegt und die Abholung angefordert; Jadlog hat das physische Paket noch nicht gescannt. | „Etikett erstellt“ |
| `EMISSAO` | Jadlog hat das Frachtdokument erstellt und das Paket in sein Netz übernommen. | „Beim Paketdienst eingegangen“ |
| `Em transferência` | Das Paket bewegt sich zwischen Jadlog-Franchises, Sortierzentren oder Linienverkehren in Brasilien. | „Unterwegs“ |
| `Em rota de entrega` | Ein lokaler Fahrer hat das Paket geladen und ist auf dem Weg zu deiner Adresse. | „In Zustellung“ |
| `ENTREGUE` | Jadlog hat das Paket als zugestellt verbucht. | „Zugestellt“ |

Wenn ein Shop Versandetiketten gesammelt druckt, bevor der Jadlog-Transporter sie abholt, kann `Order created` oder `COLETA SOLICITADA` ein bis zwei Werktage lang der einzige Eintrag bleiben. Sobald `Em transferência` erscheint, können lange Straßenstrecken zwischen brasilianischen Bundesstaaten ebenfalls mehrtägige Pausen bis zum Scan der Zielfranchise verursachen.

## Jadlog-Dienste: Predict, Pickup-Punkte und Export

Auf [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) und den Produktseiten stellt Jadlog seine Hauptdienste vor:

- **Jadlog.com und Jadlog Package:** E-Commerce-Zustellung (Jadlog.com für Pakete bis 30 kg; Jadlog Package für B2C- und B2B-Straßensendungen).
- **Jadlog Rodo, RodoEconômico, Cargo und DOC:** schwerere Straßenfracht bis 50 kg (Rodo), Economy-Straßenversand, B2B-Luftfracht und Dokumentenversand.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** in Ballungsräumen und Gebieten mit hohem Aufkommen verfügbar; schickt automatisch eine SMS mit einem 1-Stunden-Zeitfenster für die Zustellung.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** über 4.000 Partnergeschäfte in Brasilien (über 90 % Bevölkerungsabdeckung) für Paketabholung, -abgabe und E-Commerce-Retouren (*Reversa*) mit SMS- und E-Mail-Benachrichtigungen.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** Export aus Brasilien in 154 Länder (300 Ziele) über das internationale Geopost-Netz.

**Was Jadlog nicht öffentlich festlegt.** Die Website nennt weder eine einheitliche Zahl an Zustellversuchen noch garantierte Laufzeiten oder eine feste Abholfrist an Pickup-Punkten für alle Onlineshops. Prüfe die SMS oder E-Mail zu deinem Pickup-Paket – oder die Bestellseite des Shops – für genaue Abholfristen und die Regeln für eine erneute Zustellung.

## Jadlog-Paket steckt fest, verspätet sich oder kam nicht an

Bei Onlinebestellungen ist der Verkäufer Vertragspartner von Jadlog und zuständig für Erstattungen, Adresskorrekturen und Ersatzlieferungen.

**Hängt bei `Order created` oder `COLETA SOLICITADA`.** Der Verkäufer hat die Sendungsdaten angelegt, Jadlog hat das Paket aber noch nicht abgeholt oder ins Netz eingebucht. Frag im Shop nach, ob das Paket das Lager bereits verlassen hat.

**Stillstand unterwegs (`Em transferência`).** Lange Straßentransporte zwischen Bundesstaaten, Wochenenden und volle regionale Hubs sorgen regelmäßig für Pausen zwischen den Franchise-Scans. Siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).

**Als `ENTREGUE` markiert, aber nicht da.** Frag zuerst Angehörige, Nachbarn und die *portaria* (Empfang/Pförtner) deines Gebäudes und prüfe, ob beim Kauf ein Jadlog-Pickup-Punkt gewählt wurde. Hat niemand das Paket erhalten, melde dich sofort beim Shop, damit er eine Nachforschung bei Jadlog startet. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Beschädigt, verloren oder zurückgesendet.** Bewahre die Außenverpackung auf und fotografiere Schäden sowie das Versandetikett deutlich, bevor du den Verkäufer kontaktierst; siehe [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Jadlog kontaktieren und Verbraucherrechte

Die Seite [Atendimento von Jadlog](https://www.jadlog.com.br/jadlog/atendimento) veröffentlicht weder eine allgemeine Telefonnummer für Empfänger noch eine Support-E-Mail-Adresse. Deine praktischen Wege:

- **Zuerst den Onlineshop kontaktieren:** Händler verfügen über den Versandvertrag (`CT-e` / Rechnung), um Nachforschungen zu eröffnen, Adressen anzupassen oder Erstattungen freizugeben.
- **Lokale Franchise-Suche:** Die zuständige Niederlassung findest du über [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades), wenn du Infos zu einem örtlichen Depot brauchst.
- **Datenschutzanfragen (LGPD):** Jadlog bietet ein eigenes Betroffenenportal unter [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Verbraucherschlichtung:** Lässt sich ein Problem mit einem teilnehmenden Shop oder Paketdienst nicht direkt klären, können Verbraucher in Brasilien mit einem Silber- oder Gold-Konto bei gov.br kostenlos eine Beschwerde über [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) einreichen; teilnehmende Unternehmen verpflichten sich zu einer Antwort innerhalb von 10 Tagen.

> Sei vorsichtig bei Links per SMS, WhatsApp oder E-Mail, die eine kleine „Freigabe-“ oder „Neuzustellungsgebühr“ für ein brasilianisches Inlandspaket von Jadlog verlangen. Prüfe unerwartete Gebühren zuerst direkt beim Shop, bei dem du bestellt hast. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## Jadlog-Paket mit Peek verfolgen

Da 14-stellige Nummern weltweit von mehreren Geopost-/DPD-Diensten genutzt werden, richtet sich [Peek](/) nach deinem Standort, wen es zuerst fragt. In Brasilien fragt Peek unter den 14-stelligen Kandidaten zuerst bei Jadlog an (vor europäischen DPD-Einheiten, Chronopost, SEUR und BRT) und zeigt „Jadlog hat dieses Paket.“, sobald Jadlog es bestätigt. Außerhalb Brasiliens nutzt Peek Jadlog als Rückfalloption – es fragt erst die europäischen Geopost-Dienste und dann Jadlog, falls keiner die Nummer kennt. Ein eingefügter Link mit `jadlog.com.br/jadlog/captcha?cte=` wählt Jadlog weltweit direkt aus; sonst tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ und wähle Jadlog (die Suche nach „DPD Brazil“ findet ihn ebenfalls).

Peek fragt die öffentliche Tracking-API von Geopost direkt für Jadlog ab, ohne nach Postleitzahl oder CPF zu fragen. Angezeigt werden die aktuelle Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Zugestellt“), der Scan-Verlauf (auf Englisch aus der Geopost-API oder auf Portugiesisch vom lokalen Jadlog-Tracker) sowie der Zustellzeitpunkt. Voraussichtliche Lieferdaten, Gewicht, Adressen von Jadlog-Pickup-Punkten sowie Empfängernamen oder Unterschriften zeigt Peek nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem das Paket in Zustellung geht, alle 2 Minuten ab 6 Uhr Ortszeit, solange das Tracking von Jadlog antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet das Jadlog-Tracking nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Jadlog nach.“ Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Jadlog nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur das Tracking und kann weder eine neue Zustellung buchen noch eine Jadlog-Franchise für dich kontaktieren.

## Fragen zur Jadlog-Sendungsverfolgung

### Wie sieht eine Jadlog-Sendungsnummer aus?

Eine Jadlog-Paketnummer ist 14 Ziffern lang (zum Beispiel `48291736405821`, Ziffern erfunden). Du kannst sie mit diesem 14-stelligen Code in Peek oder auf der [Jadlog-Startseite](https://www.jadlog.com.br/jadlog/home) verfolgen.

### Was ist Jadlog Predict?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) ist ein automatischer SMS-Benachrichtigungsdienst in Metropolregionen und Gebieten mit hohem Paketaufkommen, der Empfängern das voraussichtliche 1-Stunden-Zeitfenster für die Zustellung mitteilt.

### Wie funktioniert Jadlog Pickup?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) umfasst mehr als 4.000 Partnergeschäfte in Brasilien, in denen Empfänger Pakete abholen oder Retouren abgeben können. Jadlog informiert per SMS und E-Mail über den Verlauf der Sendung.

### Warum ist Jadlog mit DPD und Geopost verbunden?

Jadlog ist Teil der europäischen Geopost-Gruppe neben DPD, Chronopost, SEUR, BRT und Speedy. Deshalb nutzt Jadlog 14-stellige Geopost-Paketnummern und ist an das internationale Geopost-Netz in 154 Ländern angebunden.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – Tracker `RASTREIE SUA ENCOMENDA` und Überblick über das brasilianische Netz
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – Zugehörigkeit zur Geopost-Gruppe, über 500 Franchises, über 4.000 Pickup-Partner, über 7.000 Fahrzeuge, 7.000 direkte und indirekte Mitarbeiter, 154 Länder
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (bis 30 kg), Package, Rodo (bis 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – über 4.000 Partnergeschäfte, >90 % Bevölkerungsabdeckung, SMS-/E-Mail-Benachrichtigungen und Reversa-Retouren
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – automatische SMS-Hinweise mit 1-Stunden-Zustellfenster
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – Export in 154 Länder / 300 Ziele über Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – Suche nach brasilianischen Franchises und Standorten
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – Umfang der öffentlichen Hilfeseite
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – Datenschutzportal für Betroffene
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – öffentliche Schlichtungsplattform (10-Tage-Antwortfrist, gov.br-Konto Silber oder Gold)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – 14-Ziffern-Erkennung und Brasilien-Priorität (Alias `DPD Brazil`), Linkregel `captcha?cte=`, öffentliche Tracking-API von Geopost, Statuszuordnung und Abfragerhythmus
:::
