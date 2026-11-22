---
title: Quantium Solutions Sendungsverfolgung: Nummern & Hilfe
description: Quantium Solutions Paket verfolgen: welche Nummern der Tracker annimmt, was jeder Status bedeutet, wer zustellt, Zollregeln und Kontakt zum Support.
slug: quantium-solutions-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Quantium Solutions ist ein E-Commerce-Logistikunternehmen mit Standorten in 10 Ländern im Raum Asien-Pazifik. Es transportiert Online-Bestellungen in die Region, innerhalb der Region und aus ihr hinaus in alle Welt. Du verfolgst ein Paket über die Seite [Track Your Shipments](https://track.quantiumworldwide.com/) von Quantium oder fügst die Nummer oben ins Feld ein. Die Nummer teilt dir der Shop in der Versand-E-Mail oder auf deiner Bestellseite mit.

## Quantium Solutions Sendungsnummer: wie sie aussieht

Der Quantium-Tracker fragt nach einer „Tracking/Reference number“ und nimmt mehrere auf einmal an, eine pro Zeile. Der Code von Peek kennt zwei Formen von Quantium-Nummern, und keine gehört Quantium allein. Mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `4829173654` | Zehn Ziffern: in Peeks Code die Quantium-Nummer für eine Sendung in Malaysia. Zehn Ziffern passen auch zu anderen Diensten, darunter DHL — Peek kann Quantium daher nicht allein daran erkennen. |
| `UQ482917367TH` | `UQ`, neun Ziffern und `TH`: in Peeks Code die Quantium-Nummer für eine Sendung in Thailand. Es ist das internationale Postformat, daher nennt Peek Thailand Post. |
| `RA482917367SG` | Zwei Buchstaben, neun Ziffern und `SG`: eine Nummer von Singapore Post. Peeks Code vermerkt, dass Quantium manche Pakete unter SingPost-Nummern befördert. Peek nennt Singapore Post. |

Peeks Quantium-Abfrage akzeptiert nur diese beiden Formen (zehn Ziffern oder Postformat S10). Leerzeichen und Kleinbuchstaben spielen keine Rolle. Für Referenzen in einem anderen Format nutze direkt den Quantium-Tracker. Keine Nummer gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number) und [Sendungsnummer-Formate](guide:tracking-number-formats).

## Quantium Solutions Tracking-Status erklärt

Jede Zeile im Quantium-Tracker besteht aus einem Meilenstein und oft einer Bemerkung, also der Bezeichnung des Partners, der das Paket gerade bearbeitet. Peek schreibt den Meilenstein und nach einem Doppelpunkt die Bemerkung, wenn sie mehr aussagt. Die Zeilen, die Peek kennt, in typischer Reihenfolge:

| Quantium schreibt | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipment created` | Der Shop hat die Sendung angemeldet. Quantium hat das Paket noch nicht. | „Etikett erstellt“ |
| `Shipment received` | Quantium hat das Paket erhalten. | „Beim Paketdienst eingegangen“ |
| `Shipment under processing at origin hub` | Wird in einem Drehkreuz im Absenderland sortiert. | „Unterwegs“ |
| `Shipment despatched to transshipment hub` | Unterwegs zu einem Umschlagzentrum für die nächste Etappe. | „Unterwegs“ |
| `DEPART ORIGIN` | Hat den Ausgangsort verlassen. | „Unterwegs“ |
| `Shipment received at transshipment hub: Arrive Hub` | Im Umschlagzentrum eingetroffen. | „Unterwegs“ |
| `Shipment despatched to overseas: Depart Hub` | Hat das Umschlagzentrum Richtung Zielland verlassen. | „Unterwegs“ |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | Bereit zum Abflug, dann auf dem Flug in dein Land. | „Unterwegs“ |
| `Shipment arrived at destination port: Arrive Destination` | In deinem Land angekommen. | „Unterwegs“ |
| `IN TRANSIT: Customs Clearance Begins` | Der Zoll deines Landes hat die Abfertigung begonnen. | „Beim Zoll“ |
| `Shipment released from customs/ government check point: Customs Cleared` | Vom Zoll freigegeben. | „Unterwegs“ |
| `Shipment received at delivery branch: Handed Over for Delivery` | In der lokalen Zustellniederlassung eingetroffen. | „Unterwegs“ |
| `IN TRANSIT` | In Bewegung. Sortierscans des Partners in deinem Land erscheinen so. | „Unterwegs“ |
| `Shipment out for delivery` | In Zustellung. | „In Zustellung“ |
| `Shipment delivered` | Zugestellt. Nennt die Bemerkung, wer unterschrieben oder angenommen hat, bleibt der Name in der Zeile, wie Quantium sie schreibt. | „Zugestellt“ |

Eine Zeile, die Peek nicht kennt, erscheint im Wortlaut von Quantium, und Peek leitet nach Möglichkeit eine Stufe aus den Wörtern ab. Bei einer unbekannten Nummer meldet der Quantium-Tracker „No record!“. Mehr unter [was Tracking-Status bedeuten](guide:tracking-statuses).

**Uhrzeiten.** Der Quantium-Tracker nennt Datum und Uhrzeit ohne Zeitzone, jeweils nach der Uhr des Betriebs, der gescannt hat: erst das Absenderland, dann der Partner in deinem Land. Zwei Zeilen aus verschiedenen Ländern lassen sich daher nicht Stunde um Stunde direkt vergleichen.

## Wer Quantium Solutions Pakete zustellt

Quantium bietet Shops vier grenzüberschreitende Dienste an:

| Dienst | Tracking | Zustellweg | Laufzeit | Zölle und Steuern |
| --- | --- | --- | --- | --- |
| Q-Simple | keines | postalisch | 4 bis 17 Tage | beim Empfänger (DDU) |
| Q-Smart | verfolgbar | postalisch | 4 bis 10 Tage | beim Empfänger (DDU) |
| Q-Speed | verfolgbar, prioritär | postalisch | 3 bis 7 Tage | beim Empfänger (DDU) |
| Q-Sure | verfolgbar | gewerblich | 2 bis 5 Tage | beim Absender oder Empfänger (DDP oder DDU) |

Welchen Dienst dein Paket nutzt, zeigt Peek nicht an: Frag beim Shop nach.

**In Asien.** Quantiums eigene Last-Mile-Dienste bieten Haustürzustellung, Abholung in Postfilialen, Packstationen und an über 3.000 Abholstellen in ganz Asien sowie Bar- oder elektronische Zahlung bei Zustellung. Die Regular-Zustellung dauert 2 bis 4 Tage.

**In Australien.** Die australische Gesellschaft Quantium Solutions (Australia) Pty Ltd verschickt Pakete innerhalb Australiens per Kurier — darunter CouriersPlease — sowie per [Australia Post](carrier:australia-post) eParcel und Express eParcel, wie aus der Treibstoffzuschlag-Seite hervorgeht. Quantium führt CouriersPlease zudem als offiziellen Partner auf. Der zustellende Dienst legt fest, wie oft zugestellt wird und wo verpasste Pakete lagern.

War niemand zu Hause, siehe [verpasste Zustellung](guide:missed-delivery) und [nicht abgeholte Pakete](guide:uncollected-parcel).

## Zoll und Abgaben bei einem Quantium-Paket

Bei Q-Simple, Q-Smart und Q-Speed trägt laut Quantium der Empfänger Zölle und Steuern: Delivered Duty Unpaid (DDU). Q-Sure kann DDP (vom Absender bezahlt) oder DDU laufen. Frag den Shop, was für deine Bestellung gilt. Ob und wie viel du zahlst, hängt von den Regeln deines Landes ab: siehe [Paket beim Zoll](guide:customs).

**In die EU.** Online-Bestellungen von außerhalb der EU unterliegen seit dem 1. Juli 2026 einem vorläufigen Zoll von 3 € pro Artikel in Paketen bis 150 € Warenwert sowie seit dem 1. November 2026 einer EU-Bearbeitungsgebühr von 2 € pro Artikel unabhängig vom Wert. Ein Artikel bezeichnet Waren eines Pakets mit gleicher Zolltarifnummer, Beschreibung und, sofern erforderlich, Herkunft. Quantium weist versendende Unternehmen darauf hin, dass die 2-€-Gebühr pro Zollanmeldungsposition sowohl unter IOSS als auch unter DAP und unabhängig vom Sendungswert anfällt, zuzüglich Mehrwertsteuer, wo das Zielland diese erhebt. Frag beim Shop, ob dein Preis sie bereits einschloss.

## Quantium Solutions Sendungsverfolgung aktualisiert sich nicht, verloren oder zurück

**Nur `Shipment created`.** Der Shop hat die Sendung angelegt, Quantium hat das Paket aber noch nicht gescannt. Bleibt es tagelang dabei, frag den Shop, ob er es wirklich abgeschickt hat.

**Stillstand unterwegs.** Nach vier Tagen ohne neue Zeile auf einer Sendung auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) sowie „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Quantium Solutions nach.“ samt dem Link „Hol dir Hilfe bei Quantium Solutions“ zum Support-Formular. Quantium veröffentlicht Störungsmeldungen auf seiner News-Seite (wie die Meldung vom 7. Oktober 2026 über starken Dunst in Malaysia und Singapur). Der Tracker kann unter „Tracking No.“ zudem die Nummer des Zustellpartners nennen: Verfolge sie auch dort. Siehe [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating).

**Zugestellt, aber nicht da.** Prüfe das Umfeld deiner Adresse und frag Nachbarn, dann kontaktiere den Shop und Quantium. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Verloren oder beschädigt.** Quantiums Website nennt keinen Reklamationsweg für Empfänger. Der Shop ist Kunde von Quantium — bitte ihn um Erstattung oder Ersatz. Siehe [Paket verloren](guide:lost-parcel) und [beschädigte Pakete](guide:damaged-parcel).

**Zurückgesendet.** Quantium bietet Shops den Rückversand an den Absender an. Siehe [zurück an Absender](guide:returned-to-sender).

## Quantium Solutions Kundenservice und Kontakt

Kontaktwege laut Quantiums Seiten:

- **Online:** das [Kunden-Support-Formular](https://www.quantiumsolutions.com/contact-support/) für Sendungsverfolgung, allgemeine Hilfe und Rückmeldungen. Es fragt nach Name, E-Mail, Absenderland und Sendungsnummer; das Feld „Customer ID“ trägt den Hinweis „Corporate Customer only“. Laut Quantium antwortet ein lokaler Mitarbeiter am nächsten Werktag.
- **Malaysia:** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Thailand:** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Weitere Länder:** Die Seite [Contact Us](https://www.quantiumsolutions.com/contact-us/) listet Support-Postfächer für Hongkong, Indonesien, Taiwan und Vietnam. Für Singapur nennt sie nur eine Vertriebsadresse, für Australien keinen Kontakt. Öffnungszeiten sind nicht angegeben.

Bei einer Online-Bestellung wende dich zuerst an den Shop: Er hat die Lieferung gebucht und ist Kunde von Quantium.

## Ein Quantium Solutions Paket mit Peek verfolgen

[Peek](/) erkennt eine Quantium-Nummer nicht allein an ihrer Form. Füge sie trotzdem ein: Peek verfolgt über 4500 Paketdienste. Bei zehn Ziffern fragt Peek die passenden Paketdienste und danach Tracking-Dienste an („Tracking-Dienste werden geprüft…“). Bei einer Nummer im Postformat mit `SG` oder `TH` am Ende und gültiger Prüfziffer nennt es Singapore Post oder Thailand Post („Erkannter Paketdienst“). Um Quantiums eigene Zeilen zu sehen, tippe auf „Ändern“ und wähle Quantium Solutions; bei einem schon hinzugefügten Paket nutze „Paketdienst ändern“. Wählst du Quantium für eine andere Nummernform, meldet Peek „vergibt keine Nummern wie diese“.

Ein Link zum Quantium-Tracker mit `trackno=` funktioniert ebenfalls: Peek übernimmt die Nummer und nennt Quantium Solutions sofort.

Peek zeigt Quantiums Status, jede Zeile im Wortlaut von Quantium (Tabelle oben), das Zielland sowie Empfängername (unter „Empfänger“). Kundenreferenz, Partner-Sendungsnummer, Absenderangaben und Abgangsort bleiben außen vor. Da Quantium keine Orte pro Scan, keinen voraussichtlichen Liefertermin, kein Gewicht und keinen Zustellnachweis liefert, zeigt Peek davon nichts an. „Website von Quantium Solutions öffnen“ öffnet deine Nummer im Quantium-Tracker.

Weil Quantiums Zeiten keine Zeitzone tragen, liest Peek sie bei Sendungen nach Singapur, Malaysia, Thailand oder Australien auf der Uhr des Ziellands (für Australien Sydney-Zeit); bei anderen Zielländern in Singapur-Zeit bis zur Ankunftszeile (`Shipment arrived at destination port: Arrive Destination`), danach in der Zeit des Ziellands (eine Zeitzone pro Land, für die USA New Yorker Zeit). Peek zeigt jede Uhrzeit in deiner Geräte-Zeitzone („Uhrzeiten in deiner Zeitzone:“), sodass ein Scan aus einem anderen Land einige Stunden abweichen kann.

Hat die Nummer das Postformat mit gültiger Prüfziffer und nennt Quantium das Zielland, fragt Peek auch die Post dieses Landes unter derselben Nummer an, etwa [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) oder NZ Post. Sobald diese Post neue Scans zeigt, folgt Peek dem Paket dort und behält Quantiums Verlauf bei. Das gilt nicht für alle Länder (etwa nicht für Singapur, Malaysia, Thailand, Hongkong oder Vietnam) und nie für zehnstellige Nummern.

Solange du das Paket in Peek geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek direkt bei Quantium bis zu alle 10 Minuten, und ab 6:00 Uhr Singapur-Zeit am Tag, an dem Quantium das Paket in Zustellung scannt, bis zu alle 2 Minuten, solange Quantiums eigener Tracker antwortet; sonst, sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden, solange das Paket auf „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ steht), prüft Peek stündlich; nach sieben Tagen auf „Etikett erstellt“ ohne Neuigkeiten nur noch alle 6 Stunden. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Quantium oder den Shop kontaktieren kann Peek nicht für dich.

## Fragen zur Quantium Solutions Sendungsverfolgung

### Ist Quantium Solutions dasselbe wie Quantium Worldwide?

Ja: Tracker und Support-E-Mails laufen über `quantiumworldwide.com`, und Peek kennt das Unternehmen auch als Quantium oder Quantium Solutions International.

### Warum zeigt Peek für meine Quantium-Nummer Singapore Post oder Thailand Post an?

Deine Nummer nutzt das internationale Postformat, das am Ende die zuständige Post benennt: `SG` für Singapore Post, `TH` für Thailand Post. Für Quantiums eigene Zeilen tippe auf „Ändern“ und wähle Quantium Solutions.

### Was bedeutet „Customs Clearance Begins“?

Der Zoll in deinem Land hat die Abfertigung gestartet, und Peek zeigt „Beim Zoll“. Nach der Freigabe lautet die Zeile `Shipment released from customs/ government check point: Customs Cleared`. Bei einem DDU-Dienst trägst du eventuelle Zölle und Steuern: siehe [Paket beim Zoll](guide:customs).

### Wie lange dauert die Lieferung mit Quantium Solutions?

Quantium gibt 2 bis 5 Tage für Q-Sure und bis zu 17 Tage für Q-Simple an (Tabelle oben). Innerhalb Asiens dauert Regular 2 bis 4 Tage.

### Kann ich ein Q-Simple-Paket verfolgen?

Unterwegs nicht. Quantium verkauft Q-Simple als günstigen Dienst ohne Sendungsverfolgung — verlasse dich daher nicht auf Scans und frag den Shop, wann er verschickt hat.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – Tracker, Spalte „Tracking No.“, „No record!“
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – Präsenz in 10 Ländern im Raum Asien-Pazifik
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – Tätigkeit von Quantium, CouriersPlease als Partner
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – die vier Dienste, Rücksendung an den Absender
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – Zustelloptionen in Asien
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – Verzögerungen durch Dunst
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – 2 € Gebühr pro Anmeldungsposition
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – Formularfelder, Antwortzeit
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – Telefonnummern und E-Mail-Adressen
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll, 2 € Bearbeitungsgebühr
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Definition eines Artikels
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – Nummernformen, Links, Wortlaute, Uhren, gelesene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Erkennung, Zeitzone für Quantium, Übergabe an die Zielpost
:::
