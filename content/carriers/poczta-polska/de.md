---
title: Poczta Polska Sendungsverfolgung: Nummern, Status, Abholung
description: Sendung von Poczta Polska oder Pocztex verfolgen: Nummernformate, Statusmeldungen, Benachrichtigungen, Abholpunkte, Zoll und Kontakt.
slug: poczta-polska-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Poczta Polska ist das staatliche Postunternehmen Polens, und Pocztex ist dessen Kurierdienst. Du kannst Pakete und Einschreiben auf der eMonitoring-Seite von Poczta Polska verfolgen, die auch eine englische Version bietet, oder die Nummer oben ins Feld einfügen. Die Nummer steht auf dem Einlieferungsbeleg des Absenders – der Shop hat sie also –, und die SMS sowie Benachrichtigungen von Poczta Polska wiederholen sie.

## Sendungsnummer von Poczta Polska: wo du sie findest

Gib die Nummer ohne Leerzeichen oder Klammern ein. Das Tracking deckt alle Sendungen mit Einlieferungsnachweis ab, ausgenommen Wertbriefe; Einschreiben zeigen lediglich Einlieferungs- und Zustelldatum.

| Format (erfunden) | Was es ist |
| --- | --- |
| `PX4829105734` | `PX` und 10 Ziffern. |
| `0045900773482910573` | 19 Ziffern. |
| `00459007734829105736` | 20 Ziffern mit `5900773` nach den ersten drei (oder 18 Ziffern ohne die beiden führenden Nullen `00`). |
| `RR482910576PL` | Internationales Postformat: zwei Buchstaben, neun Ziffern, `PL`. |

Wo sie zu finden ist:

- **Einlieferungsbeleg des Absenders**: Frage den Shop, falls deine Bestellmail keine Nummer enthält.
- **Abholbenachrichtigung** für einen Pocztex-Abholpunkt oder -Automaten, mit Nummer, Ort, Abholfrist und Einmalcode.
- **SMS, E-Mail oder App-Meldung nach verpasster Zustellung**, mit Nummer, Frist und Adresse der Postfiliale.

Antwortet eMonitoring „The given package number is incorrect“, prüfe jedes Zeichen und welchen Paketdienst der Shop genannt hat. Gar keine Nummer? Siehe [wo du deine Sendungsnummer findest](guide:find-tracking-number).

## Was die Statusmeldungen von Poczta Polska bedeuten

eMonitoring öffnet auf Polnisch; die englische Version zeigt diese Meldungen samt Datum, Uhrzeit und Postamt für jeden Schritt:

| Status bei Poczta Polska | Was das für dich bedeutet |
| --- | --- |
| `Electronic item data received` | Der Absender hat die Sendung angemeldet. Poczta Polska hat sie noch nicht. |
| `Posting/collection`, `Item arrived at the shipping point` | In einer Filiale oder einem Punkt aufgegeben oder beim Absender abgeholt. |
| `In transport` | Unterwegs zwischen Sortierzentren. |
| `Departure from Poland` | Hat Polen Richtung Ausland verlassen. |
| `Arrival at inward office of exchange (Poland)` | Eine Sendung aus dem Ausland hat das polnische Austauschamt erreicht. |
| `Customs service` | Zollbearbeitung. |
| `Item returned from import customs` | Vom Einfuhrzoll freigegeben. |
| `In delivery` | In Zustellung – oder ein fehlgeschlagener Versuch (siehe unten). |
| `Unsuccessful (physical) delivery` | Niemand war da. Eine Benachrichtigung wurde hinterlassen, und die Sendung geht in die Filiale oder erneut in Zustellung. |
| `Attempted delivery - redirected` | Zustellversuch, danach wird die Sendung weitergeleitet. |
| `Delivery at sender's request - delivery attempt` | Versuch einer vom Absender angeforderten Zustellung. |
| `Unsuccessful (physical) delivery - lost` | Während der Zustellung verloren gegangen. |
| `Ready for pick-up at the Post Office`, `Added to postal notice book - Item can be collected`, `Ready for pick up at the point` | Wartet in der im Scan genannten Filiale oder Abholstelle auf dich. |
| `Collected at the post office`, `Collected at the delivery point` | In der Filiale oder am Abholpunkt abgeholt. |
| `Final delivery`, `Delivered to the PO box`, `Delivery at sender's request - delivered` | Zugestellt. |
| `Returned`, `Returned to sender`, `Not collected at the delivery point` | Geht zurück an den Absender. |

`Posting/collection` ist der Schritt des Absenders, nicht deine Abholung. Poczta Polska benennt außerdem ein Ereignis für fehlgeschlagene Zustellung ebenfalls `In delivery`: Peek unterscheidet beide anhand des Ereigniscodes und zeigt dafür „Zustellversuch“ statt „In Zustellung“. Eine Zeile `Customs service` kann auch nach der Zustellung auftauchen: Sie verbucht an der Tür kassierte Abgaben. Ohne „(Poland)“ bedeutet `Arrival at inward office of exchange`, dass eine Sendung aus Polen das Zielland erreicht hat. Auf der polnischen Seite steht `Awizowana` für eine verpasste Zustellung und `Oczekuje na odbiór` für abholbereit. Laut Poczta Polska können sich die Ereignisnamen ändern.

## Verpasste Zustellung bei Poczta Polska: Awizo, Punkte und Automaten

**Pocztex-Kurierpakete.** Der Kurier übergibt das Paket an dich, einen Erwachsenen in deinem Haushalt oder eine bevollmächtigte Person. Bist du nicht da, informiert dich Poczta Polska per SMS, E-Mail oder App, sofern der Absender deine Kontaktdaten angegeben hat; sonst liegt eine Karte im Briefkasten, mit einer zweiten Nachricht zwei Tage später. Das Paket wartet ab dem Tag nach der Benachrichtigung 7 Tage in der Postfiliale und geht danach zurück an den Absender. Du kannst den Kurier auch anrufen, um es direkt an deiner Adresse abstellen zu lassen (nicht bei Nachnahme, Wertangabe, Empfängerzahlung oder „do rąk własnych“), auf eigenes Risiko.

**Abholpunkte und Automaten.** An einem vom Absender gewählten Pocztex-Punkt (`PUNKT`) liegt das Paket 7 Tage in einer Postfiliale oder 3 Tage in einem Partnershop wie Żabka, ABC oder Lewiatan. Automaten (`AUTOMAT`) bei Postfilialen, Tankstellen und Märkten wie Biedronka bewahren es 72 Stunden auf, mit Erinnerungen nach 24 und 48 Stunden. Geöffnet werden sie mit Telefonnummer und Code oder der App Pocztex Mobile. Die Frist lässt sich nicht verlängern und das Paket nicht umleiten, doch jeder mit dem Code kann es abholen.

**Einschreiben und Postpakete.** Eine Benachrichtigung (*awizo*) landet im Briefkasten oder kommt auf Wunsch elektronisch. Nach 7 Tagen folgt eine zweite Benachrichtigung; nach 14 Tagen ab der ersten geht die Sendung zurück. Gegen Gebühr kannst du in der Filiale oder auf poczta-polska.pl bis zu 14 zusätzliche Tage Lagerung beantragen.

## Paket von Poczta Polska verspätet, verloren, beschädigt oder beim Zoll

Das Tracking von Poczta Polska dient nur zur Information und begründet keine Ansprüche; Hinweise zu stehenden Sendungsverläufen veröffentlicht das Unternehmen nicht: Frage beim Absender nach und lies [wenn sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Wer reklamiert.** Der Absender – oder du, sobald die Sendung zugestellt wurde oder der Absender dir den Anspruch abgetreten hat: online auf poczta-polska.pl (eReklamacje), schriftlich oder persönlich in jeder Postfiliale.

**Fristen** für Inlandssendungen laut Bedingungen von Poczta Polska:

- Innerhalb von 12 Monaten nach Einlieferung.
- Sichtbare Schäden: bei Zustellung im Protokoll. Den Inhalt eines äußerlich beschädigten Pakets darfst du prüfen, außer an Abholpunkten und Automaten.
- Verdeckte Schäden oder fehlender Inhalt: innerhalb von 7 Tagen nach Erhalt.
- Verlust: Ein Pocztex-Paket gilt 30 Tage nach Einlieferung als verloren; der Verlust eines Postpakets kann 14 Tage nach Einlieferung gemeldet werden, ohne Sonn- und Feiertage.
- Poczta Polska antwortet innerhalb von 30 Tagen; Einspruch ist binnen 14 Tagen möglich.

Auslandssendungen: innerhalb von sechs Monaten ab dem Tag nach der Einlieferung.

**Zoll.** Seit dem 1. Juli 2026 fällt für Sendungen von außerhalb der EU bis 150 € ein EU-Zoll von 3 € je Warenart an, den Poczta Polska einzieht; Geschenke zwischen Privatpersonen bis 45 € sind befreit. Seit dem 1. November 2026 kommt eine EU-Bearbeitungsgebühr von 2 € je Warenposition für Online-Bestellungen von außerhalb der EU hinzu: Poczta Polska zieht sie beim Empfänger ein und führt sie an die polnische Steuerverwaltung (KAS) ab. Hinzu können noch nicht bezahlte Mehrwertsteuer sowie die Zollanmeldegebühr von Poczta Polska (20 PLN, beziehungsweise 25 PLN bei eigener Zollabwicklung) kommen, zahlbar bei Zustellung oder vor Abholung. Du kannst die Annahme verweigern. Mehr unter [Pakete beim Zoll](guide:customs).

## Kontaktnummer und Kundenservice von Poczta Polska

Die offiziellen englischen und polnischen Seiten von Poczta Polska und Pocztex nennen folgende Kontakte:

- **Hotline:** +48 438 420 600, Montag bis Freitag 8:00–20:00 Uhr, zum Tarif deines Anbieters, sowie ein Online-Formular auf der [Kontaktseite](https://www.poczta-polska.pl/en/kontakt/).
- **Pocztex:** 804 104 104 aus dem polnischen Festnetz, +48 43 842 0 842 mobil und aus dem Ausland, Montag bis Freitag 8:00–20:00 Uhr und Samstag 8:00–16:00 Uhr.
- **Fragen zum Tracking:** per E-Mail an kontakt@poczta-polska.pl.
- **Probleme am Abholpunkt** (Paket fehlt, Punkt geschlossen): Contact Center 801 333 444 (Festnetz) oder +48 438 420 600.

> Poczta Polska warnt vor gefälschten Pocztex-SMS über fehlgeschlagene Zustellungen mit Links zu nachgeahmten Seiten. Pocztex sendet niemals Zahlungslinks und fragt per SMS nie nach Kartendaten. Leite verdächtige SMS an 8080 weiter.

## Sendungen von Poczta Polska mit Peek verfolgen

[Peek](/) verfolgt mehr als 4500 Paketdienste. Es erkennt `PX`-Nummern mit gültiger Prüfziffer sofort, ebenso 20-stellige Nummern mit `5900773` nach den ersten drei Ziffern und gültiger Prüfziffer sowie 18-stellige Nummern ohne die führenden Nullen `00` (die Peek selbst ergänzt). Bei 19-stelligen Nummern mit `00` am Anfang fragt es allein Poczta Polska und hängt die Prüfziffer wie die offizielle Website an. Eine gültige internationale Nummer mit `PL` am Ende, die mit R, E, L oder C (außer CV) beginnt – etwa `RR`, `EE` oder `CP` –, geht direkt an Poczta Polska; andere gültige `PL`-Nummern, wie `VV`, erscheinen als „Postanbieter unbekannt“, während Peek Poczta Polska und [Chronopost](carrier:chronopost) fragt, wobei Poczta Polska gewinnt, wenn es die Sendung kennt. Auch eMonitoring-Links mit `numer=` funktionieren; eine Postleitzahl ist nicht nötig.

Peek zeigt die englischen Meldungen im Original, außer `Returned to sender`, das als „Zurück an Absender“ erscheint (oder die polnischen, wenn du Peek auf Polnisch nutzt), jede Postfiliale auf der Karte, das Gewicht, die Versandart, das Zielland und die abholbereite Filiale. Ein voraussichtliches Lieferdatum gibt es nicht. Filialadressen, Öffnungszeiten, Zahlungsbelege und Zusatzleistungen werden nicht übernommen. Aus dem Ausland weitergeleitete Scans enthalten keine Zeitzone; Peek liest sie nach der Uhrzeit des jeweiligen Landes. Paletten, Sammelsendungen und unregistrierte Post lassen sich nicht verfolgen. Bleibt eine registrierte, angenommene oder transportierte Sendung vier Tage ohne Scan, zeigt Peek „Keine Aktualisierung seit Mo 12. Okt“ (den Tag des letzten Scans) sowie „Hol dir Hilfe bei Poczta Polska“.

Solange du das Paket auf dem Bildschirm geöffnet hast, oder am Liefertag mit aktivierten Benachrichtigungen, prüft Peek bis zu alle 10 Minuten – und ab 6:00 Uhr Ortszeit am Tag, an dem Poczta Polska die Sendung in Zustellung scannt, bis zu alle 2 Minuten –, solange der eigene Tracker von Poczta Polska antwortet. Ansonsten sowie nach zwei Tagen ohne Neuigkeiten (nach 12 Stunden, solange das Paket noch auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht) prüft Peek stündlich. Allein über Tracking-Dienste prüft Peek stündlich, nach einem Tag ohne Neuigkeiten alle 3 Stunden und an dem Tag, an dem die Sendung in Zustellung ist, bis zu alle 15 Minuten, solange du sie geöffnet hast oder Benachrichtigungen aktiviert sind. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ einstellen. Ein aus dem Ausland nach Polen verschicktes Paket kann zu Poczta Polska weiterverfolgt werden.

## Fragen zur Sendungsverfolgung von Poczta Polska

### Was ist Pocztex?

Pocztex ist der Kurierdienst von Poczta Polska mit eigener Website pocztex.pl und der App Pocztex Mobile. Seine Pakete nutzen dasselbe eMonitoring-Tracking.

### Kann ich Poczta Polska auf Englisch verfolgen?

Ja. eMonitoring startet auf Polnisch, bietet aber eine englische Oberfläche.

### Wie lange dauert eine Lieferung mit Pocztex?

Poczta Polska garantiert bei Pocztex Kurier die Zustellung bis zum fünften Werktag nach Einlieferung, bei 2XL- und nicht standardmäßigen Paketen bis zum siebten. Werktage sind Montag bis Freitag ohne Feiertage.

### Kann ich ein Paket von Poczta Polska umleiten?

Nicht als Empfänger. Laut Bedingungen von Poczta Polska darf nur der Absender Adresse oder Empfänger ändern, und Sendungen an einen Pocztex-Punkt oder -Automaten lassen sich nicht an einen anderen verlegen.

:::sources
- [Poczta Polska: Track your shipment](https://www.poczta-polska.pl/en/sledzenie-przesylek/) – Nummernformat, Umfang, Abholpunkte, ausländische Postdienste
- [Poczta Polska: Śledź przesyłkę](https://www.poczta-polska.pl/sledzenie-przesylek/) – dasselbe auf Polnisch
- [Poczta Polska: eMonitoring (Englisch)](https://emonitoring.poczta-polska.pl/?lang=en) – Beispiele, Fehlermeldung, nur informativ, kontakt@
- [Poczta Polska: eMonitoring](https://emonitoring.poczta-polska.pl/) – polnische Version, Statusmeldungen
- [Pocztex: Śledzenie przesyłek](https://www.pocztex.pl/sledzenie-przesylek/) – Pocztex-Tracking
- [Poczta Polska: Regulamin usługi Pocztex](https://www.poczta-polska.pl/wp-content/uploads/2026/08/Regulamin-swiadczenia-uslugi-Pocztex-w-obrocie-krajowym-dla-klienta-detalicznego-obowiazuje-od-24.08.2026-r.pdf) – Zustellung, Benachrichtigungen, Punkte, Automaten, Fristen, Reklamationen, Garantie
- [Poczta Polska: Regulamin usług powszechnych](https://www.poczta-polska.pl/wp-content/uploads/2026/07/Regulamin-swiadczenia-uslug-powszechnych-tekst-jednolity.pdf) – Awizo, Lagerung, Verlust, internationale Reklamationen
- [Poczta Polska: Lista zdarzeń / Events list](https://www.poczta-polska.pl/wp-content/uploads/2025/02/PPSA_events_lists.pdf) – Ereignisnamen und Änderungsmöglichkeit
- [Poczta Polska: Pocztex PUNKT and AUTOMAT FAQ](https://odbiorwpunkcie.poczta-polska.pl/en/faq/) – Partnershops, Automaten, keine Umleitung, Contact Center
- [Pocztex: FAQ](https://www.pocztex.pl/en/faq-en/) – Benachrichtigungen, Abholpunkte, Schadensprotokoll
- [Poczta Polska: eReklamacje, paczka pocztowa](https://ereklamacje.poczta-polska.pl/sub/reklamacje-krajowe/paczka-pocztowa) – wer reklamiert, Fristen, 30 Tage
- [Poczta Polska: Contact](https://www.poczta-polska.pl/en/kontakt/) – Hotline, Zeiten, Formular
- [Poczta Polska: Kontakt](https://www.poczta-polska.pl/kontakt/) – polnische Version, Reklamationsformular
- [Pocztex: Kontakt](https://www.pocztex.pl/kontakt/) – Pocztex-Rufnummern und Zeiten
- [Poczta Polska: Beware of fake "Pocztex" messages](https://www.poczta-polska.pl/en/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – gefälschte SMS, 8080
- [Poczta Polska: Uwaga na fałszywe wiadomości „Pocztex”](https://www.poczta-polska.pl/news/uwaga-na-falszywe-wiadomosci-pocztex-grozna-kampania-phishingowa/) – polnische Version, Contact Center
- [Poczta Polska: Changes in fees for shipments from outside the EU](https://www.poczta-polska.pl/en/news/zmiany-w-oplatach-dla-przesylek-spoza-unii-europejskiej-od-1-lipca-2026-r/) – 3-€-Zoll, Geschenke bis 45 €, Annahmeverweigerung
- [Poczta Polska: Nowe przepisy Unii Europejskiej. Tak działa nowa opłata za przesyłki spoza UE](https://media.poczta-polska.pl/releases/nowe-przepisy-unii-europejskiej-tak-dziala-nowa-oplata-za-przesylki-spoza-ue) – 2-€-EU-Bearbeitungsgebühr je Warenposition zu Lasten des Empfängers, Abführung an KAS
- [Poczta Polska: Cennik opłat dodatkowych](https://www.poczta-polska.pl/wp-content/uploads/2025/04/cennik-oplat-dodatkowych.pdf) – Zollanmeldegebühren
- [Poczta Polska: Poczta Polska SA Group](https://www.poczta-polska.pl/en/grupa-pp/poczta-polska/) – Staatsunternehmen
- [Pocztex Mobile im App Store](https://apps.apple.com/pl/app/pocztex-mobile/id1661761150) – die App
- [Universal Parcel Scraper: Poczta Polska](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/poczta-polska) – Erkennung, 18-/19-/20-stellige Nummern, gelesene und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest
:::
