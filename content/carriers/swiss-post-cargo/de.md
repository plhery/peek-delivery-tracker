---
title: Swiss Post Cargo Tracking: Status, Zustellung, Kontakt
description: Swiss Post Cargo Sendung verfolgen: welche Nummer funktioniert, was „Received warehouse POST“ heißt, wie Stückgut zugestellt wird und wen du anrufst.
slug: swiss-post-cargo-sendungsverfolgung
published: 2026-10-12
updated: 2026-10-27
---

Swiss Post Cargo ist die Gütermarke der Schweizerischen Post: Paletten und andere Güter per Lastwagen, vom einzelnen Stück bis zur ganzen Wagenladung, keine gewöhnlichen Pakete. Du verfolgst die Sendung auf der Track-&-Trace-Seite (tt.swisspost-cargo.com) mit dem Barcode oder der Referenz, die dir der Absender gegeben hat, oder fügst sie oben ins Feld ein. Hast du eine Paketnummer der Post, gehört sie auf [die Seite der Post](carrier:swiss-post).

## Swiss Post Cargo Sendungsnummer: Barcode oder Referenz

Das Tracking von Swiss Post Cargo akzeptiert einen Barcode für ein einzelnes Stück oder eine Referenz des Absenders. Eine Referenz zeigt alle Barcodes ihrer Sendung. Diese Formen bringt Peek mit Swiss Post Cargo in Verbindung (Ziffern erfunden):

| Nummer | Was sie ist |
| --- | --- |
| `00012345600000000005` | 20 Ziffern, beginnt mit 00: der Barcode einer Palette oder eines Stücks (eine SSCC). Die letzte Ziffer ist eine Prüfziffer. |
| `PL-12345678` | PL- und 8 Ziffern: eine Kundenreferenz. |

Schau in die E-Mails des Shops und frag sonst dort nach. Weitere Stellen zeigt [Sendungsnummer finden](guide:find-tracking-number). Findet das Tracking nichts, prüf die Ziffern und frag dann den Absender.

Diese Nummern werden nicht als Swiss Post Cargo verfolgt:

- **Pakete der Post:** 18 Ziffern mit 99 am Anfang (gedruckt `99.00.123456.12345678`) oder zwei Buchstaben, neun Ziffern und CH (`RR000000005CH`).
- **PostLogistics-Referenzen** wie `12345678-123`: PostLogistics tritt heute als Swiss Post Cargo auf, das alte Tracking tracking.postlogistics.ch lässt sich nicht mehr öffnen. Peek verfolgt solche Referenzen als PostLogistics.

## Swiss Post Cargo Sendung verfolgen: was die Status bedeuten

Swiss Post Cargo schreibt seine Scans auf Deutsch, Französisch, Italienisch oder Englisch. In Peek erscheinen sie auf Englisch. Auf Englisch liest sich eine zugestellte Sendung so:

| Swiss Post Cargo (auf Englisch) | Was es für dich heißt |
| --- | --- |
| `Data entry POST` | Die Daten des Absenders sind da. Die Ware ist noch nicht bei Swiss Post Cargo. |
| `Received warehouse POST` | Die Ware ist in einem Hub oder Depot von Swiss Post Cargo angekommen, der Ort steht daneben. |
| `Loaded for Delivery` | Die Ware ist auf dem Lastwagen: in Zustellung. |
| `IMAGE` | Ein Zustellfoto, direkt vor dem Zustellscan aufgeführt. |
| `DELIVERED SCANNED` | Zugestellt, der Ort steht daneben. |
| `SIGNATURE` | Die Unterschrift bei der Zustellung, zur selben Zeit wie der Zustellscan. |

Bei `Data entry POST` geht es nur um Daten: Bewegt hat sich noch nichts. `IMAGE` und `SIGNATURE` sind Belege zur Zustellung, keine neuen Ereignisse.

## Swiss Post Cargo Zustellung: wann, wohin und wenn niemand da ist

**Wann.** Laut der Stückgut-Broschüre der Post wird Stückgut in der Regel am nächsten Arbeitstag zugestellt. In der Schweiz und in Liechtenstein bekommen Orte, die ein Lastwagen erreicht, die Ware am nächsten Arbeitstag bis 17 Uhr. Schwer erreichbare Alpentäler und Berggebiete (Wallis, Tessin, Graubünden) am zweiten Arbeitstag bis 17 Uhr.

**Wohin.** Standard ist die Zustellung während der Geschäftszeiten an die erste Tür im Erdgeschoss, an die Rampe oder an den Straßenrand, nicht in obere Stockwerke. Der Fahrer rechnet mit einem Zugang für den Hubwagen, und das Abladen ist Sache des Empfängers: Lädt der Fahrer ab, tut er das in deinem Auftrag. Er bittet dich, die korrekte Zustellung mit deiner Unterschrift zu bestätigen.

**Extras des Absenders.** Eine Avisierung per Telefon, E-Mail oder SMS, eine geplante Zustellung, die Lieferung ins Stockwerk und eine zweite Zustellung sind Zusatzleistungen. Du bekommst sie nur, wenn der Absender sie gebucht hat.

**Verpasste Zustellung und Abholung.** Abholstellen oder Paketautomaten nennt die Stückgut-Broschüre der Post nicht, und eine Zweitzustellung ist eines der Extras oben. Wie lange Ware im Depot wartet und wie du einen neuen Termin bekommst, steht dort nicht. Frag den Shop, der den Transport gebucht hat.

## Swiss Post Cargo Sendung verspätet, verloren oder beschädigt

Kunde von Swiss Post Cargo ist der Shop: Die Dienste bestellen Firmen. Verspätung, Schaden oder ein fehlendes Stück meldest du deshalb zuerst dem Shop. Fotografiere einen Schaden, bevor du ihn dem Shop meldest.

**Seit Tagen kein neuer Scan.** Stehen nur die Daten des Absenders da (`Data entry POST`), kann die Ware noch beim Absender sein: Frag den Shop, wann er sie übergibt. Die üblichen Gründe erklärt [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Schau im Tracking nach den Zeilen mit Unterschrift und Foto (auf Englisch `SIGNATURE` und `IMAGE`) und melde dich dann beim Shop. Wie es weitergeht, zeigt [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received).

> Auf der Phishing-Seite der Post steht, dass gefälschte Nachrichten im Namen der Post per SMS, E-Mail, WhatsApp, Website, Anruf oder über soziale Medien kommen. „Oft geht es um Pakete, Gebühren, Sendungsverfolgung oder persönliche Daten.“

## Swiss Post Cargo Kontakt und Telefonnummer

Swiss Post Cargo arbeitet für den Shop, also fang dort an: Er hat den Transport gebucht. Die Stückgut-Broschüre der Post vom April 2022 nennt einen einzigen Kontakt, für Geschäftskunden und ohne Telefonnummer: Kundendienst Transporte CH, Lagerstrasse 12, 5606 Dintikon, sales.ls4@post.ch.

Für Briefe und Pakete der Post (nicht Cargo) gilt +41 848 888 888 (CHF 0.08 pro Minute vom Schweizer Festnetz), Montag bis Freitag 7.30 bis 18 Uhr und Samstag 8 bis 12 Uhr, dazu ein Chat auf post.ch.

## Swiss Post Cargo mit Peek verfolgen

Ein 20-stelliger Barcode mit 00 am Anfang stammt vom Versender, nicht von einem bestimmten Dienst. Stimmt seine Prüfziffer, zeigt [Peek](/) deshalb „Peek fragt bei Swiss Post Cargo, NZ Post nach …“ und nennt Swiss Post Cargo nur, wenn Swiss Post Cargo die Nummer kennt. Eine Referenz wie `PL-12345678` geht an Swiss Post Cargo und an TNT Italy, deren Nummern dieselbe Form haben können. Für andere Referenzen mit 6 bis 40 Buchstaben und Ziffern wählst du Swiss Post Cargo mit „Ändern“ oder fügst einen Track-&-Trace-Link von tt.swisspost-cargo.com oder apv.swisspost-cargo.com ein. Eine Paketnummer der Post in so einem Link wird als Post-Sendung verfolgt.

Du siehst den Status und den Verlauf mit dem Ort jedes Scans auf einer Karte und den Zeitpunkt der Zustellung. Die Uhrzeiten liest Peek als Schweizer Zeit und zeigt sie in der Zeitzone deines Geräts. Die Scan-Texte bleiben Englisch, weil die Abfrage auf Englisch läuft; `Loaded for Delivery` gilt als in Zustellung. Unterschrift und Foto gehen im Zustellscan auf, ohne das Bild. Dein Name und deine Adresse fehlen, eine voraussichtliche Zustellung, ein Gewicht oder eine Abholstelle gibt es nicht.

Eine Referenz, die mehrere Sendungen teilen, wird nur gelesen, solange eine davon Scans aus den letzten 60 Tagen hat. Sonst gilt sie als nicht gefunden, nimm dann den Barcode. Peek schaut tagsüber alle 10 Minuten nach, alle 2 Minuten, sobald die Ware in Zustellung ist, nachts alle ein bis zwei Stunden, und schickt dir auf Wunsch Mitteilungen für jeden Scan, nur für wichtige Schritte oder nur am Liefertag.

## Fragen zum Swiss Post Cargo Tracking

### Was ist Swiss Post Cargo?

So heißt die Güterlogistik der Post seit dem 1. März 2025: Logistik für Firmen, in der Schweiz und international, Stückgut inklusive. Zu den Konzerngesellschaften der Post in der Schweiz gehört die Swiss Post Cargo CH AG, an der die Post zu 100 Prozent beteiligt ist. PostLogistics gehörte zu den ersten Gesellschaften der Gruppe, die den Namen übernommen haben.

### Ist PostLogistics jetzt Swiss Post Cargo?

Ja, PostLogistics tritt heute als Swiss Post Cargo auf. Das alte Tracking tracking.postlogistics.ch lässt sich nicht mehr öffnen, das Tracking von Swiss Post Cargo ist tt.swisspost-cargo.com. Referenzen wie `12345678-123` verfolgt Peek weiter als PostLogistics.

### Hat Swiss Post Cargo eine E-Mail-Adresse?

Die Stückgut-Broschüre der Post vom April 2022 nennt sales.ls4@post.ch, für Geschäftskunden. Als Empfänger schreibst du zuerst dem Shop: Er hat den Transport gebucht.

### Warum bekomme ich eine SMS von Swiss Post Cargo?

Oft, weil der Absender eine Avisierung gebucht hat: Die Stückgut-Broschüre der Post nennt Avis per Telefon, E-Mail oder SMS als Zusatzleistung. Es gibt auch gefälschte SMS im Namen der Post, prüf die Nummer deshalb selbst im Tracking.

### Kann ich Swiss Post Cargo live verfolgen?

Nicht auf einer Live-Karte. Das Tracking listet jeden Scan mit Ort, und laut Stückgut-Broschüre lassen sich Status und Standort der Sendung laufend online abfragen.

:::sources
- [Die Post: Stückgut-Broschüre](https://www.post.ch/de/-/media/post/gk/dokumente/stueckgut.pdf) – einzelne Stücke bis ganze Wagenladungen, Aufträge über Swiss Post Cargo Web von Geschäftskunden, nächster Arbeitstag, Berggebiete, Erdgeschoss, Abladen, Unterschrift, Zusatzleistungen samt Zweitzustellung, Status und Standort online, Kontakt in Dintikon und E-Mail
- [Die Post: Logistikmarkt der Post](https://site.post.ch/de/ueber-uns/unternehmen/strategie/geschaeftstaetigkeit/logistik) – Swiss Post Cargo ist die Güterlogistik der Post, Logistiklösungen für Unternehmen, national und international
- [Die Post: Konzerngesellschaften](https://site.post.ch/de/ueber-uns/unternehmen/governance/organisation/konzerngesellschaften) – Swiss Post Cargo CH AG, zu 100 % bei der Post
- [Die Post: Hilfe und Kontakt](https://www.post.ch/de/hilfe-und-kontakt) – Nummer für Briefe und Pakete, Preis, Zeiten, Chat
- [Die Post: Aktuelle Betrugsversuche im Namen der Post](https://site.post.ch/de/ueber-uns/sicherheit/phishing-und-betrug/aktuelle-betrugsversuche) – gefälschte Nachrichten
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – Name Swiss Post Cargo ab 1. März 2025, PostLogistics unter den ersten Gesellschaften
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek liest: Barcodes und Referenzen, Nummernformen, die englischen Scan-Texte, Track-&-Trace-Adressen, PostLogistics heute als Swiss Post Cargo
:::
