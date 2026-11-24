---
title: GOFO Spain Sendungsverfolgung: GFES & CIES & Kontakt
description: GOFO-Spain-Paket verfolgen: GFES- und CIES-Nummern, spanische Status, 3 Versuche, 2 Wochen Lagerung, POD-Fotos und Kundenservice.
slug: gofo-spain-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

GOFO Spain ist der spanische Ableger von GOFO, einem im August 2023 in den USA gegründeten Letzte-Meile-Logistiker, der mit Zustellpartnern in den USA, Kanada, Frankreich, den Niederlanden, Italien und Spanien zusammenarbeitet. In Spanien stellt GOFO für E-Commerce-Marken, Händler und Logistiker zu; nach drei erfolglosen Zustellversuchen geht ein Paket zurück ins Verteilzentrum und wird dort zwei Wochen lang gelagert. Prüfe eine Sendung auf der [Tracking-Seite von GOFO Spain](https://www.gofo.com/es/tracking-results/) oder füge deine Nummer oben ein.

## GOFO-Spain-Sendungsnummern: GFES- und CIES-Formate

Deine Sendungsnummer steht in der Versand-E-Mail des Onlineshops oder auf deiner Marktplatz-Bestellseite. Nummern von GOFO Spain beginnen mit `GFES` oder `CIES`, gefolgt von 13 oder 14 Ziffern:

| Format (erfunden) | Was es ist |
| --- | --- |
| `GFES26482917364052` | Standard-18-Zeichen-Code von GOFO Spain (`GFES` + 14 Ziffern mit Prüfziffer; auch 17-stellig als `GFES` + 13 Ziffern). |
| `CIES4829173640582` | Alternativer 17- oder 18-stelliger Code von GOFO Spain (`CIES` + 13 oder 14 Ziffern). |

Weitere europäische Paketnummern findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die GOFO-Spain-Sendungsstatus bedeuten

Der Feed von GOFO Spain kombiniert Zahlencodes mit spanischen Texten in spanischer Ortszeit (`Europe/Madrid`):

| GOFO Spain zeigt (Code) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Pedido recibido` (`100`) | Der Verkäufer hat den Auftrag elektronisch gemeldet; GOFO wartet auf das physische Paket. | „Etikett erstellt“ |
| `Llegada al centro de clasificación` (`202`), `Salida del centro de clasificación` (`200`), `Llegada a las instalaciones de destino de GOFO` (`201`), `En preparación para reparto` (`203`) | Im GOFO-Sortierzentrum gescannt, an die Zieleinrichtung gesandt und für die lokale Zustellung vorbereitet. | „Unterwegs“ |
| `El paquete está en reparto` (`208`) | Mit einem GOFO-Zusteller in Spanien auf Tour. | „In Zustellung“ |
| `Entrega fallida` (`206`) | Zustellversuch fehlgeschlagen (z. B. Empfänger abwesend oder Adressproblem). | „Zustellversuch“ |
| `Devuelto al centro de clasificación` (`204`) | Nach erfolglosem Versuch zurück ins GOFO-Sortierzentrum gebracht – **noch nicht** an den Absender zurückgesandt. | „Problem gemeldet“ |
| `Devuelto` (`257` / `264`) | Paket wird an den Absender zurückgeschickt. | „Zurück an Absender“ |
| `Entregado por En persona` / `Entregado por En el buzón` (`205`) | Persönlich übergeben oder im Briefkasten abgelegt. | „Zugestellt“ |

**Hinweis zu `Devuelto al centro de clasificación` (`204`).** Das Wort *„Devuelto“* wirkt leicht alarmierend, aber Code `204` bedeutet lediglich, dass der Fahrer das Paket nach einem erfolglosen Versuch zurück ins lokale GOFO-Sortierzentrum gebracht hat – erst `Devuelto` (`257`/`264`) bezeichnet die echte Rücksendung an den Absender.

## Zustellversuche, 2 Wochen Lagerung und Zoll bei GOFO Spain

Laut [gofo.com/es/en/](https://www.gofo.com/es/en/) und den [Kundenservice-FAQ von GOFO Spain](https://www.gofo.com/es/en/contact-us/):

- **Drei Versuche und zwei Wochen Lagerung:** GOFO Spain gibt an, dass Pakete nach **drei fehlgeschlagenen Zustellversuchen** ins Verteilzentrum zurückkehren und dort **zwei Wochen sicher gelagert** werden. Melde dich vor Ablauf dieser zwei Wochen, um die Zustellung oder Adresse zu klären.
- **Zustellfotos (POD):** Bei zugestellten Sendungen zeigt das [Hauptportal von GOFO Spain](https://www.gofo.com/es/) POD-Angaben, meist samt Foto des Ablageorts und Zustellbeschreibung.
- **Nicht-EU-Bestellungen und Zoll:** Viele GOFO-Spain-Sendungen kommen aus Shops außerhalb der EU. Gemäß der [EU-Zollreform der Europäischen Kommission](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) fallen bei Bestellungen bis 150 € pauschal 3 € Zoll pro Artikel und bei jeder Nicht-EU-Bestellung 2 € EU-Bearbeitungsgebühr pro Artikel an, sofern nicht bereits beim Kauf erhoben. Siehe [Paket beim Zoll](guide:customs).

## GOFO-Spain-Paket fehlt, beschädigt oder als zugestellt gemeldet

Die [Kundenservice-FAQ von GOFO Spain](https://www.gofo.com/es/en/contact-us/) nennen klare Schritte je nach Fall:

- **Als `Entregado` („Zugestellt“) markiert, aber nicht erhalten:** Prüfe zuerst das Zustellfoto auf [gofo.com/es/](https://www.gofo.com/es/). Schau in Briefkasten, gemeinschaftliche Paketbereiche, Eingang und sichere Ablageorte und frag Nachbarn, Haushaltsmitglieder oder Hausverwaltung. Fehlt das Paket weiter, schreib rasch an `cs@es.gofo.com` – ein Foto deines Hauseingangs oder der Außenansicht beschleunigt die Prüfung (GOFO bearbeitet Meldungen aber auch ohne Foto).
- **Fehlender Inhalt bei unversehrter Verpackung oder bestätigter Verlust:** GOFO weist Empfänger an, **direkt den Absender zu kontaktieren**, um fehlende Artikel oder Verlustmeldungen abzuwickeln.
- Allgemeine Hilfen: [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating), [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakte von GOFO Spain

Offizielle Kontakte auf [GOFO Spain Customer Support](https://www.gofo.com/es/en/contact-us/) und [Contacto](https://www.gofo.com/es/contact-us/):

- **Kundenservice-E-Mail:** `cs@es.gofo.com`.
- **Empfänger-Hotline:** als *Coming soon* (*Próximamente*) angekündigt mit Zeiten `09:00 – 18:00`, Montag bis Freitag außer Feiertagen. Bis zur Freischaltung ist E-Mail der offizielle Empfängerkanal.

> Ignoriere unverlangte SMS oder WhatsApp-Nachrichten mit Online-Kartenzahlung zur „Neuzustellung“ oder „Adressbestätigung“ eines GOFO-Pakets. Prüfe deinen `GFES`- oder `CIES`-Code direkt auf [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) oder schreib an `cs@es.gofo.com`. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## GOFO-Spain-Paket mit Peek verfolgen

[Peek](/) erkennt `GFES` + 13 oder 14 Ziffern (`GFES26482917364052`, erfunden – mit gültiger GOFO-Europe-Prüfziffer) und `CIES` + 13 oder 14 Ziffern (`CIES4829173640582`) automatisch mit hoher Sicherheit als GOFO Spain sowie eingefügte Links `gofo.com/es/...?id=`. Musst du GOFO Spain manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ (die Suche nach „GOFO ES“ findet ihn ebenfalls).

Peek liest den Tracker von GOFO Spain direkt aus und zeigt die Phase („Etikett erstellt“, „Unterwegs“, „In Zustellung“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), die spanischen Scan-Texte samt Ort, die Zustellzeit und den voraussichtlichen Zustelltag, wenn GOFO einen angibt. Die internen Codes bestimmen die Phase; Peek zeigt weder sie noch den Dienstnamen an. Scans werden nach der jeweiligen Ortszeit in Spanien datiert und in deiner Gerätezeit angezeigt. Zustelldetails, POD-Fotos (dafür [gofo.com/es/](https://www.gofo.com/es/) öffnen), persönliche Referenzen, Empfängerprüfungen, unbenannte Gewichte und Portalhinweise lässt Peek weg.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem GOFO Spain `El paquete está en reparto` meldet, alle 2 Minuten ab 6 Uhr nach der Uhrzeit des neuesten Scans von GOFO Spain, solange der Tracker von GOFO Spain antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von GOFO Spain nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei GOFO Spain nach.“, mit dem Link „Hol dir Hilfe bei GOFO Spain“ zur Kontaktseite von GOFO Spain. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei GOFO Spain nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsstatus und kann weder POD-Fotos in der App öffnen noch Zustellungen verschieben oder Reklamationen bei GOFO Spain einreichen.

## Fragen zur GOFO-Spain-Sendungsverfolgung

### Wie viele Zustellversuche unternimmt GOFO Spain?

GOFO Spain macht **drei Zustellversuche**. Nach drei erfolglosen Versuchen kehrt das Paket ins Verteilzentrum zurück und wird dort **zwei Wochen** sicher gelagert.

### Was bedeutet `Devuelto al centro de clasificación`?

Code `204` (`Devuelto al centro de clasificación`) heißt, dass der Zusteller das Paket nach einem erfolglosen Versuch zurück ins GOFO-Sortierzentrum gebracht hat – es wurde **noch nicht** an den Händler zurückgesandt (das wäre `Devuelto`, Codes `257`/`264`).

### Wo finde ich mein Zustellfoto (POD) von GOFO Spain?

Gib deine `GFES`- oder `CIES`-Nummer auf [gofo.com/es/](https://www.gofo.com/es/) oder [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) ein, um das POD-Foto und den Ablagehinweis direkt einzusehen.

### Wie erreiche ich den Kundenservice von GOFO Spain?

Schreib an `cs@es.gofo.com`; die Telefonnummer für Empfänger wird noch als kommend geführt. Bei bestätigtem Verlust oder fehlendem Inhalt in unversehrter Verpackung wende dich direkt an den Onlinehändler.

:::sources
- [GOFO Spain: English Home](https://www.gofo.com/es/en/) – US-gegründeter Letzte-Meile-Dienstleister, drei erfolglose Versuche und zwei Wochen Lagerung im Verteilzentrum
- [GOFO Spain: Spanish Home](https://www.gofo.com/es/) – spanisches Tracking-Portal und POD-Zugang
- [GOFO Spain: Who We Are](https://www.gofo.com/es/en/who-we-are) – gegründet im August 2023
- [GOFO Spain: Ship With Us](https://www.gofo.com/es/ship-with-us) – Zustellpartner in USA, Kanada, Frankreich, Niederlande, Italien und Spanien
- [GOFO Spain: Tracking Results](https://www.gofo.com/es/tracking-results/) – Mehrfach-Sendungsverfolgung
- [GOFO Spain: Customer Support (Englisch)](https://www.gofo.com/es/en/contact-us/) – `cs@es.gofo.com`, angekündigte Empfänger-Hotline (Mo–Fr `09:00–18:00`), POD-/Verlustschritte und Absender-Reklamationsregel
- [GOFO Spain: Contacto (Spanisch)](https://www.gofo.com/es/contact-us/) – spanische Kontaktseite (Hilfe-URL in Peek)
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll pro Artikel bis 150 € seit 1. Juli 2026, 2-€-EU-Bearbeitungsgebühr seit 1. November 2026
- [Universal Parcel Scraper: gofo-es](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-es) – `GFES`-/`CIES`-Erkennung mit `gofo-europe`-Prüfziffer, Linkregeln `gofo.com/es`, numerische Codes (`100`, `200`–`208`, `257`/`264`), Scan-Zeitzonen, verworfene POD-/Detailfelder und Hilfe-URL
:::
