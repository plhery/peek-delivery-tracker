---
title: Parcelforce Worldwide Sendungsverfolgung: Status & Zoll
description: Parcelforce Worldwide Paket verfolgen: wo die Nummer steht, was jeder Status bedeutet, Lieferoptionen, Zollrechnungen und Kontakt zu Royal Mail.
slug: parcelforce-worldwide-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

Parcelforce Worldwide gehört zur [Royal Mail](carrier:royal-mail) Group: Ein Parcelforce-Paket verfolgst du auf der Seite „Track your item“ von Royal Mail oder direkt oben im Feld. Die Sendungsnummer erhältst du vom Shop oder Absender, meistens in der Bestellbestätigung per E-Mail.

## Parcelforce Sendungsnummer: wo sie steht

Schau in die Bestellbestätigung des Shops oder frage dort nach. Nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number). Drei mögliche Formen:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `EA482917367GB` | Zwei Buchstaben (`E` und ein Buchstabe, oder `CP` bzw. `GI`), neun Ziffern und `GB`, mit Prüfziffer vor `GB`. |
| `PBTR4829173654` | 14-stellige Paketnummer: `PB`, die Sendungsnummer und die Position des Pakets darin. |
| `TR4829173` | Eine bloße Consignment-Nummer: Royal Mails Tracker verlangt stattdessen die eigene Nummer jedes Pakets. |

Das 13-stellige Format gehört auch zu Royal Mail (`RR482917367GB` ist Royal Mail, nicht Parcelforce), und manche express48-Pakete tragen eine Royal-Mail-Nummer. Da beide auf derselben Seite verfolgt werden, macht das für dich kaum einen Unterschied.

## Parcelforce Tracking-Status erklärt

Royal Mails Tracker zeigt diese Zeilen auf Englisch an. In typischer Reihenfolge:

| Tracking-Text | Was das für dich bedeutet |
| --- | --- |
| `Sender has despatched item` | Der Absender hat das Paket angemeldet. Parcelforce hat es noch nicht. |
| `Item Collected` | Beim Absender abgeholt. |
| `Accepted at Parcelshop` | Vom Absender in einer Postfiliale oder einem Paketshop abgegeben. |
| `Item received` | Im Netzwerk eingetroffen. |
| `Customs charges raised` | Für einen Import von außerhalb des Vereinigten Königreichs sind vor der Zustellung Mehrwertsteuer, Zoll oder Gebühren fällig. |
| `Despatched to delivery office` | Unterwegs zu deinem lokalen Depot oder Zustellamt. |
| `Received at Delivery Depot` | Im lokalen Depot eingetroffen. Noch nicht abholbereit. |
| `Out for delivery` | Heute beim Fahrer auf Tour. |
| `Delivery attempted` | Der Fahrer konnte nicht zustellen. |
| `Delivered by` | Zugestellt. Die Zeile kann das zustellende Amt nennen. |
| `Delivered to Neighbour/Reception by` | Bei einem Nachbarn oder am Empfang abgegeben, mit Namensnennung in der Zeile. |
| `Returned to sender` | Auf dem Rückweg zum Absender. |

Bei Paketen ins Ausland geht der Verlauf mit Zeilen wie `Item Leaving the UK` und `Due to be delivered today` des dortigen Zustellpartners weiter.

## Parcelforce Lieferoptionen und verpasste Zustellungen

**Vor der Zustellung.** In der Royal-Mail-App oder über Royal Mail Customer Services kannst du auch für ein Parcelforce-Paket einen anderen Liefertag wählen, die Abgabe beim Nachbarn anfordern oder den Fahrer bitten, lauter zu klopfen bzw. dir mehr Zeit bis zur Tür zu lassen. Auch ein sicherer Ablageort wird über Royal Mail festgelegt.

**Zustellung verpasst.** Das Tracking zeigt `Delivery attempted` oder `Delivered to Neighbour/Reception by` mit dem Namen der Person, die es angenommen hat. Bleibt unklar, wo das Paket ist, ruf Royal Mail Customer Services (unten) an.

## Parcelforce Sendungsverfolgung aktualisiert sich nicht, verspätet oder verloren

**Noch keine Scans.** Der Absender hat die Nummer möglicherweise vor der Übergabe erzeugt: `Sender has despatched item` bedeutet, dass Parcelforce das Paket noch nicht hat. Prüfe die Nummer und versuche es später erneut. Mehr unter [warum die Sendungsverfolgung stoppt](guide:tracking-not-updating).

**Verspätet, verloren oder beschädigt.** Hast du online gekauft, wende dich zuerst an den Verkäufer: Laut Citizens Advice erreichst du dort meist das bessere Ergebnis und kannst Erstattung oder Neulieferung verlangen. Für eine Entschädigung prüfe das Reklamationsverfahren auf der Parcelforce-Website.

**Zollgebühren.** Parcelforce bringt Pakete von außerhalb des Vereinigten Königreichs durch den Zoll und kontaktiert dich mit einer Rechnung über den genauen Betrag, falls Mehrwertsteuer, Zoll oder andere Gebühren anfallen ([mehr zum Zoll](guide:customs)). Das Paket wird normalerweise etwa 3 Wochen gelagert; wird bis dahin nicht gezahlt, geht es zurück an den Absender. Fragen zu den Waren gehen ebenfalls an Parcelforce.

> Unsicher, ob eine E-Mail oder SMS mit Zahlungsaufforderung echt ist? Klicke keine Links an und gib keine Bankdaten preis. Leite Phishing-E-Mails kostenlos an report@phishing.gov.uk und verdächtige SMS (im Vereinigten Königreich) an 7726 weiter.

## Parcelforce Telefonnummer und Kundenservice

Citizens Advice verweist Parcelforce-Empfänger für Lieferwünsche und sichere Ablageorte an Royal Mail:

- **Royal Mail Customer Services:** 03457 740 740, Montag bis Freitag 08:00–18:30 Uhr, Samstag 08:00–15:00 Uhr und Sonntag 09:00–14:00 Uhr britischer Zeit. **Relay UK:** 18001, dann 0345 266 0075.
- **Royal-Mail-App:** Liefertag, Nachbar, sicherer Ablageort und Zustellhinweise auch für Parcelforce-Pakete.

Für Entschädigungsanträge oder Beschwerden nutze die eigene Website von Parcelforce.

## Parcelforce-Pakete mit Peek verfolgen

[Peek](/) erkennt Parcelforce sofort bei Nummern der Form `EA482917367GB` mit gültiger Prüfziffer sowie bei 14-stelligen `PB`-Nummern. Eine reine Consignment-Nummer wird nicht verfolgt. Ein Parcelforce-Tracking-Link mit `trackNumber=` funktioniert ebenso wie ein Royal-Mail-Link, der auf `#/tracking-results/` plus Parcelforce-Nummer endet.

Peek ruft Parcelforce auf der Royal-Mail-Website in einem echten Browser auf seinem Server auf; eine Abfrage dauert deshalb länger und kann fehlschlagen, wenn die Website automatisierte Aufrufe blockiert. Peek fragt dann universelle Tracking-Dienste an, die für Parcelforce jedoch wenig vorhalten.

Peek zeigt Status, jeden Scan mit Ort (ohne Postleitzahlen), voraussichtlichen Liefertag, Zustellzeit und Zielland. Die Status behalten den englischen Wortlaut von Royal Mail, außer einigen, die Peek übersetzt, etwa `Out for delivery` („In Zustellung“), `Delivered` („Zugestellt“) und `Returned to sender` („Zurück an Absender“). Ein Auslandsscan ohne Zeitzone wird nicht datiert; datierte Scans zeigt Peek in deiner Zeitzone („Uhrzeiten in deiner Zeitzone:“). Bei einem Export mit einer Nummer vom Typ `EA482917367GB` kann Peek auch die Landespost des Ziellands fragen, das Parcelforce angibt (gibt es keines an, die des Landes, in dem Peek dich verortet, außer dem Vereinigten Königreich), und zu ihr wechseln, sobald sie einen Scan zeigt, der nicht älter ist als der neueste von Parcelforce.

Solange du das Paket in Peek geöffnet hast — oder am Zustelltag mit aktivierten Benachrichtigungen — prüft Peek bis zu alle 10 Minuten, und ab 6:00 Uhr britischer Zeit (bzw. ab 30 Minuten vor Beginn eines Zeitfensters, wenn Parcelforce eines nennt) am Tag, an dem Parcelforce das Paket in Zustellung scannt, bis zu alle 5 Minuten, solange Parcelforces eigener Tracker antwortet; sonst, und nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“), stündlich; nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Bleibt ein Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) und „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Parcelforce Worldwide nach.“ samt dem Link „Hol dir Hilfe bei Parcelforce Worldwide“ zur Kontaktseite von Parcelforce. Jeder Scan erscheint auf einer Karte, und du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ aktivieren.

## Fragen zur Parcelforce Sendungsverfolgung

### Gehört Parcelforce zu Royal Mail?

Ja. GOV.UK ordnet Parcelforce der Royal Mail Group zu, und seine Pakete werden über die Seite „Track your item“ von Royal Mail verfolgt.

### Warum wird meine Parcelforce-Nummer nicht erkannt?

Das Paket ist womöglich noch nicht bei Parcelforce eingetroffen, oder die Nummer enthält einen Tippfehler. Eine Consignment-Nummer allein wird nicht verfolgt: Bitte den Absender um die 14-stellige `PB`-Nummer jedes Pakets und versuche es später erneut.

### Wie lange lagert Parcelforce ein Paket mit Zollgebühren?

Etwa 3 Wochen. Wird die Rechnung bis dahin nicht bezahlt, geht das Paket zurück an den Absender.

### Kann ich für eine Parcelforce-Lieferung einen anderen Tag wählen?

Ja, in der Royal-Mail-App oder über Royal Mail Customer Services. Dort kannst du auch die Zustellung an einen Nachbarn festlegen.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce Teil der Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – Royal-Mail-App und Customer Services für Parcelforce, anderer Tag, Nachbar, sicherer Ort, Klopfen, Telefon und Zeiten
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – Relay-UK-Nummer
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – Verkäufer zuerst, Ansprüche auf der Website des Paketdienstes
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce verzollt Waren, Fragen zu den Waren
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – Rechnung, rund 3 Wochen, Rücksendung an den Absender
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – Prüfziffer vor dem Ländercode
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek ausliest, Status und Nummernformen
:::
