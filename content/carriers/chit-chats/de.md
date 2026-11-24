---
title: Chit Chats Sendungsverfolgung: 10-stellige IDs & Übergabe
description: Verfolge ein Chit-Chats-Paket aus Kanada in die USA, nach Kanada oder weltweit: 10-stellige IDs, Übergabe an USPS/Canada Post, Verzögerung & Hilfe.
slug: chit-chats-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Chit Chats** (*Chit Chats Express*) ist ein kanadisches E-Commerce-Versandunternehmen, das 2001 von Derek Nolan gegründet wurde. Es betreibt 66 Standorte in ganz Kanada, nimmt Pakete auch per Einsendung (SmartDrop) und Abholung entgegen und hat über 50 Millionen Sendungen für mehr als 100.000 Versender verschickt ([chitchats.com](https://chitchats.com/)). Die Zustellung übernehmen Partnerdienste: Der Tracker nennt den Partner und dessen Sendungsnummer, etwa [USPS](carrier:usps) in den USA oder [Canada Post](carrier:canada-post) in Kanada. Du kannst eine Sendung auf [chitchats.com/tracking](https://chitchats.com/tracking) verfolgen oder deine 10-stellige Chit-Chats-Sendungs-ID oben einfügen.

## Chit-Chats-Sendungsnummern (10-stellige Sendungs-ID)

Jeder Chit-Chats-Auftrag erhält eine 10-stellige alphanumerische **Sendungs-ID** in Großbuchstaben (Buchstaben und Ziffern, üblicherweise beginnend mit einem Buchstaben und einer Ziffer ungleich Null):

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `K4N8V3T6R2` | Standardmäßige 10-stellige Chit-Chats-Sendungs-ID. |
| `P7M3X9D5Q8` | Alternative 10-stellige Chit-Chats-Sendungs-ID für US-, kanadischen oder internationalen Versand. |

Sobald Chit Chats das Paket an den Zustellpartner übergibt, zeigt die Tracking-Seite auch dessen eigene Sendungsnummer — etwa einen [USPS](carrier:usps)-Intelligent-Mail-Barcode (der ein führendes `420`-Routing-ZIP-Präfix enthalten kann, das Peek sauber entfernt) oder eine [Canada-Post](carrier:canada-post)-Sendungsnummer. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Chit-Chats-Sendungsstatus bedeuten

Der Tracker von Chit Chats zeigt zunächst eigene Scans und anschließend jene des Partnerdienstes ([USPS](carrier:usps), [Canada Post](carrier:canada-post) oder eines anderen):

| Chit Chats oder Partner meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | Der Händler hat Porto online gekauft, das Paket aber noch nicht in einer Chit-Chats-Filiale abgegeben. | „Etikett erstellt“ |
| `Received by Chit Chats` | Das physische Paket wurde in einer kanadischen Chit-Chats-Filiale oder Annahmestelle gescannt. | „Beim Paketdienst eingegangen“ |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | In einer Chit-Chats-Anlage sortiert; `In transit` ist die Übergabe an den Partnerdienst. | „Unterwegs“ |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | In das Sortiernetz des Partnerdienstes aufgenommen (USPS, Canada Post oder internationaler Partner). | „Unterwegs“ / „Beim Paketdienst eingegangen“ |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | Unterwegs zwischen den inländischen oder internationalen Sortierzentren des Partnerdienstes. | „Unterwegs“ |
| `Out for Delivery`, `Item out for delivery` | Heute mit dem lokalen Partnerdienst auf Zustelltour. | „In Zustellung“ |
| `Notice card left indicating where and when to pick up item` | Der Partnerdienst versuchte erfolglos zuzustellen und hinterließ eine Benachrichtigungskarte. | „Zustellversuch“ |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | Liegt in der Postfiliale zur Abholung bereit (selbst eine letzte Erinnerung bedeutet, dass das Paket noch abholbereit ist und noch nicht zurückging). | „Abholbereit“ |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Vom Partnerdienst an der Empfängeradresse zugestellt. | „Zugestellt“ |

## Grenzüberschreitende US-Einspeisung, Zoll und Partnerübergaben

- **Versand in die USA:** Chit Chats bietet US-Versand mit Verzollung, Delivered Duty Paid (DDP) und Hilfe bei CUSMA-Papieren, nennt 2–6 Werktage Laufzeit und veröffentlicht seine US-Zoll-Updates 2026 auf [chitchats.com](https://chitchats.com/). Siehe [Pakete beim Zoll](guide:customs).
- **Verfolgung beim Partnerdienst:** Nach der Übergabe empfiehlt die Kontaktseite von Chit Chats, die Sendungsnummer des Partners direkt auf dessen eigener Tracking-Seite zu prüfen.

## Chit-Chats-Paket verspätet, ohne Updates oder vermisst

- **Zuerst den Online-Händler kontaktieren:** Auf der [Kontaktseite](https://chitchats.com/contact) erklärt Chit Chats, dass sein Team dieselben Tracking-Daten sieht wie du und nur den Versender zu Kontofragen beraten kann; Empfänger sollen sich an den Verkäufer wenden.
- **Verspätetes Paket:** Ist das voraussichtliche Lieferdatum überschritten, rät die Tracking-Seite von Chit Chats zum Kontakt mit dem Verkäufer. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Zugestellt oder vermisst:** Prüfe Briefkasten, Hauseingang oder Postfiliale vor Ort (besonders bei einer Canada-Post-Benachrichtigungskarte) und kontaktiere dann rasch den Verkäufer. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## Chit Chats Kundenservice und kanadische Kontakte

Offizielle Support-Kanäle auf [Chit Chats Kontakt](https://chitchats.com/contact) und [Chit Chats Support](https://support.chitchats.com/en/support/home):

- **Gebührenfreie Rufnummer (Englisch & Französisch — Abrechnung & Filialfragen):** `1-844-842-8777` (zuzüglich lokaler Filialnummern zu den Öffnungszeiten an 66 Standorten in Kanada).
- **Hilfe- & Support-Portal:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) und [chitchats.com/contact](https://chitchats.com/contact).
- **Öffentlicher Tracker:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Vorsicht vor gefälschten SMS, die eine kleine Online-Zahlung zur Verzollung eines Kanada/USA-Pakets fordern. Prüfe echte Zoll- oder Portomeldungen immer zuerst über deinen Verkäufer oder das offizielle USPS-/Canada-Post-Tracking. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Chit-Chats-Paket mit Peek verfolgen

[Peek](/) prüft Chit Chats automatisch als einzigen Kandidaten, wenn du eine 10-stellige Chit-Chats-Sendungs-ID eingibst (`K4N8V3T6R2` oder `P7M3X9D5Q8`, erfunden), und zeigt „Chit Chats hat dieses Paket.“, sobald Chit Chats sie bestätigt; eingefügte Links im Format `chitchats.com/tracking/<10-Zeichen>` erkennt Peek ebenfalls direkt. Musst du Chit Chats einmal manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ (auch die Suche nach „ChitChats“ oder „Chit Chats Express“ findet den Dienst).

Peek liest den Chit-Chats-Tracker direkt aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Zugestellt“), den Wortlaut der Scans von Chit Chats und dem Partnerdienst sowie den Ort jedes Scans ohne Postleitzahl; Ortsangaben mit Hausnummer werden weggelassen. Angezeigt werden außerdem der voraussichtliche Zustelltag, das Gewicht in Kilogramm und die Maße. Der Empfängername erscheint auf deiner eigenen Paketseite unter „Empfänger“, und der Zielort kann deine eigene Kartenroute abschließen, solange das Paket unterwegs ist; geteilte Links zeigen beides nicht. Nennt Chit Chats einen Peek bekannten Partner wie USPS oder Canada Post samt dessen Sendungsnummer (bei USPS ohne ZIP-Routing-Präfix), prüft Peek, ob dieser das Paket führt: Die Sendung zeigt dann „Zustellung durch“ mit dem Namen des Dienstes und listet beide Nummern. Eigene Chit-Chats-Scans tragen eine genaue Zeitangabe. Partner-Scans erscheinen mit einer von Chit Chats als Pazifikzeit gekennzeichneten Uhrzeit, die jedoch um Stunden abweichen kann; Peek datiert sie daher in der Zeitzone ihres jeweiligen Ortes (New York für `NEW YORK, NY`, Toronto für `TORONTO, ON`), einen Scan ohne Ort in der Zone des Nachbarscans oder sonst nach Vancouver, und zeigt alle in der Uhrzeit deines Geräts. Peek lässt den Servicenamen, Statuscodes und den Absenderort weg.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem der Tracker `Out for Delivery` oder `Item out for delivery` zeigt, alle 2 Minuten ab 6 Uhr nach der Uhr des neuesten Scans, solange der Chit-Chats-Tracker antwortet. Sonst fragt Peek stündlich ab. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der Chit-Chats-Tracker nicht und Peek verfolgt das Paket allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Chit Chats nach.“, samt Link „Hol dir Hilfe bei Chit Chats“ zur Kontaktseite von Chit Chats. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Chit Chats nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ und „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder Ansprüche geltend machen noch Adressen ändern oder Chit Chats beziehungsweise Partnerdienste für dich kontaktieren.

## Häufige Fragen zur Chit-Chats-Sendungsverfolgung

### Wie sieht eine Chit-Chats-Sendungsnummer aus?

Chit-Chats-Sendungs-IDs bestehen aus 10 Großbuchstaben und Ziffern (etwa `K4N8V3T6R2` oder `P7M3X9D5Q8`, erfunden). Nach der Übergabe erhält das Paket zusätzlich einen Code des Partnerdienstes (wie USPS oder Canada Post).

### Wer liefert ein Chit-Chats-Paket an meine Haustür?

Die Zustellung übernimmt ein Partnerdienst, etwa [USPS](carrier:usps) in den USA oder [Canada Post](carrier:canada-post) in Kanada. Der Tracker von Chit Chats nennt den Partner und dessen Sendungsnummer.

### Warum pausiert mein Chit-Chats-Paket nach `Departed from Chit Chats facility`?

Der Chit-Chats-Tracker nennt keinen Grund. Sein Status `In transit` ist die Übergabe an den Partnerdienst, und dessen Scans folgen, sobald er das Paket gescannt hat. Ist die geschätzte Lieferzeit vorbei, wende dich an den Verkäufer.

### Wen kontaktiere ich, wenn mein Chit-Chats-Paket fehlt?

Wende dich an den Verkäufer, der das Paket verschickt hat. Laut [chitchats.com/contact](https://chitchats.com/contact) kann das Chit-Chats-Team nur den Versender zu Kontofragen beraten. Die gebührenfreie Nummer `1-844-842-8777` ist für Abrechnung, Filialfragen und französischsprachigen Support.

:::sources
- [Chit Chats: Offizielle Website](https://chitchats.com/) – kanadischer E-Commerce-Versandkonsolidierer für US-, Kanada- und Auslandspost
- [Chit Chats: Über uns](https://chitchats.com/about) – gegründet 2001 von Derek Nolan, 50 Mio.+ Pakete, 66 Standorte in Kanada und 100.000+ Versender
- [Chit Chats: Tracking-Portal](https://chitchats.com/tracking) – Trackingseite für 10-stellige Sendungs-IDs
- [Chit Chats: Kontakt](https://chitchats.com/contact) – gebührenfreie Nummer `1-844-842-8777`, Regelung zum Verkäuferkontakt und Filialverzeichnis (von Peek verlinkte Hilfeseite)
- [Chit Chats: Hilfe- & Support-Portal](https://support.chitchats.com/en/support/home) – offizielles Hilfecenter
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – 10-Zeichen-ID-Erkennung, Link-Regel `chitchats.com/tracking/`, eigene vs. Partner-Statustabelle, ETA-/Gewichts-/Maß-/Service-Extraktion und Bereinigung des USPS-420-Präfixes
:::
