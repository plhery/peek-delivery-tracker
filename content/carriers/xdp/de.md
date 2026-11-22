---
title: XDP Sendungsverfolgung: Sendungsnummer, Status & Kontakt
description: XDP Paket im Vereinigten Königreich verfolgen: woher die Consignment-Nummer kommt, warum XDP die Postleitzahl verlangt, Status-Erklärung und Kontakt.
slug: xdp-sendungsverfolgung
published: 2026-11-22
updated: 2026-11-22
---

XDP (XDP Express) ist ein britisches Paket- und Frachtnetzwerk, das große und schwere Artikel für Händler ausliefert — Möbel, Matratzen, Garten- und Fitnessgeräte, oft mit ein oder zwei Fahrern. Du verfolgst ein Paket auf [der Tracking-Seite von XDP](https://xdp.co.uk/track/) oder in der XDP-App mit der Consignment-Nummer und der Liefer-Postleitzahl, oder fügst die Nummer oben ins Feld ein. Die Nummer kommt vom Shop, der den Artikel verschickt, nicht von XDP.

## XDP Sendungsnummer: die Consignment-Nummer

XDP nennt sie Consignment-Nummer. Die Tracking-Seite sagt deutlich: Du bekommst sie von dem Unternehmen, das deinen Artikel per XDP verschickt — schau also in die Versand-E-Mail des Shops oder auf deine Bestellseite. Eigene SMS und E-Mails von XDP folgen erst später, wenn das Paket unterwegs zu dir ist.

XDP veröffentlicht kein Format; die Nummern sehen so aus:

| Form (erfunden) | Was es ist |
| --- | --- |
| `ZDBKLM482917` | `ZDB`, drei Buchstaben und sechs Ziffern: eine XDP-Consignment-Nummer. |
| `0ZDBKLM482917` | Dieselbe Nummer mit führender `0`, die weggelassen werden kann. |

Der XDP-Tracker fragt zusätzlich nach der Liefer- oder Abhol-Postleitzahl. Eine falsche Postleitzahl und eine falsch getippte Nummer liefern dieselbe Meldung (`Consignment not found for consignment number`), prüfe also beides.

Kein anderer britischer Paketdienst nutzt das `ZDB`-Format. Eine [DPD UK](carrier:dpd-uk)-Nummer hat 14 Ziffern und ein Frachtbrief von APC Overnight 22 Ziffern — nennt deine E-Mail einen davon, verfolge sie dort. Mehr unter [Sendungsnummer-Formate](guide:tracking-number-formats).

## XDP Tracking-Status erklärt

Tracker, App und FAQ von XDP gibt es nur auf Englisch. Routennummern und Uhrzeiten wechseln je Paket.

| XDP-Status | Was das für dich bedeutet |
| --- | --- |
| `Order received` | Der Shop hat XDP die Auftragsdaten geschickt, aber XDP hat den Artikel noch nicht. |
| `At Collection Depot` | XDP hat deinen Artikel und befördert ihn zu deinem Zustelldepot. |
| `At Delivery Depot` | Im zuständigen Zustelldepot eingetroffen. |
| `Allocated to Delivery Vehicle - Route 03` | Einer Zustelltour zugeteilt. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | Soll heute innerhalb dieses Zweistundenfensters ankommen. |
| `Next stop: On time` | Du bist der nächste Halt des Fahrers. XDP zeigt `On time`, `Late by …` oder `Early by …` bezogen aufs Zeitfenster; Peek stellt `Next stop:` voran. |
| `Delivered` | Zugestellt. |
| `Carded` | Der Fahrer war da, aber niemand konnte annehmen. |
| `Consignment Split` | Im Depot liegt erst ein Teil deiner Shop-Bestellung. |
| `Check Address` | Der Fahrer findet die Adresse nicht und braucht genauere Angaben. |
| `Service Fail` | Ein Umstand außerhalb von XDPs Einfluss (Panne, Stau oder Unwetter) hat die Lieferung verzögert. |
| `Return` | Zurück an den Absender: Kontaktiere den Shop. |

Die XDP-App formuliert manches anders (`Delivery scheduled`). Nach `Consignment Split` folgen die übrigen Teile laut XDP in den nächsten Tagen. `RETURN LABEL CREATED` startet den Rückweg zum Absender, und `Damaged` deutet auf einen beschädigten Artikel hin (siehe unten).

## XDP Zustellung: Zeitfenster, verpasste Zustellungen und Abholung

XDP sendet am Liefertag ein Zweistunden-Zeitfenster, sobald der Fahrer den Wagen beladen hat, in der Regel bis 12:00 Uhr britischer Zeit. In Spitzenzeiten plant ein Depot teils 24 Stunden oder mehr im Voraus. Danach folgt eine „You Are Next“-Meldung per SMS, E-Mail und App. Bei Zweimann-Lieferungen (2Man) nennt XDP zunächst ein Datum und am Tag selbst das Zweistundenfenster. Bei den Services 1+ und 2Man trägt das Team den Artikel in einen Raum deiner Wahl, nachdem du eine vorab von XDP gesendete Verzichtserklärung unterschrieben hast.

In der XDP-App kannst du:

- über **Change Delivery Date** die Lieferung verschieben oder teils einen Termin wählen;
- Lieferhinweise hinterlegen, etwa Abgabe beim Nachbarn oder in Vorbau, Schuppen, Garage oder hinter einem Tor;
- Hinweise für den Fahrer eintragen und den Zustellnachweis einsehen.

XDP warnt, dass du mit der Bitte um „Left Safe“ ab dem Moment der Zustellung die volle Verantwortung für die Sendung übernimmst.

**Verpasste Zustellungen.** `Carded` heißt, der Fahrer traf niemanden an. Laut XDP folgen drei erneute Zustellversuche, bevor der Artikel zurück an den Absender geht. Wurde keine Karte hinterlassen, fährt der Fahrer am selben Tag nicht noch einmal an: Vereinbare einen neuen Termin über Change Delivery Date.

**Keine Depotabholung.** Laut XDP-App ist keine Abholung in Depots oder Hubs möglich. Die FAQ verweisen bei Abholwunsch an den Shop.

## XDP Paket nicht geliefert, verspätet oder beschädigt

XDP arbeitet für den Absender, nicht für dich. Bei jedem Lieferproblem rät XDP, die Sendung zuerst in der App oder auf der Website zu prüfen und danach deinen Händler (den Shop) zu kontaktieren. Der Shop hält den Vertrag und stellt eventuelle Reklamationen.

**Verspätet oder ohne Update.** XDP garantiert keine Zustellung am nächsten Tag, schon gar nicht in Spitzenzeiten wie Feiertagen, Black Friday oder Cyber Monday. Im Stau steckende Fahrer aktualisieren ein verändertes Zeitfenster nicht immer. Siehe [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Beschädigt.** XDP bittet dich, die Lieferung anzunehmen statt abzulehnen und den Absender sofort zu informieren. Vermerke den Schaden bei der Unterschrift auf dem Zustellnachweis: XDPs Leitfaden für Absender besagt, dass eine Reklamation abgelehnt wird, wenn bei der Unterschrift kein Mangel vermerkt ist — Zusätze wie „unchecked“ oder „unseen“ zählen nicht.

**Verloren.** Die Reklamationsfristen des Absenders bei XDP sind knapp: 33 Tage ab Versand bei Verlust oder Teilverlust und 7 Tage ab Zustellung bei Schäden, Wochenenden eingeschlossen. Melde es dem Shop rasch. Steht im Tracking `Delivered` und nichts ist da, siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received).

**Zurückgesendet.** Nach `Return` sollst du laut XDP deinen Händler kontaktieren.

## XDP Kontakt und Kundenservice

XDPs Kontaktwege für Empfänger:

- **Formular Delivery Query** auf [XDPs Kontaktseite](https://xdp.co.uk/contact/). Das dortige Formular „New Business Enquiry“ ist nur für Vertriebsanfragen.
- **E-Mail:** trackingupdates@xdp.co.uk.
- **Live-Chat** auf `xdp.co.uk` und in der XDP-App; Öffnungszeiten veröffentlicht XDP nicht.
- **Kontaktformular in der App** (fragt nach Name, Postleitzahl, E-Mail und optional Consignment-Nummer).

Auf XDPs Website steht keine Telefonnummer. Die App erwähnt kostenpflichtige Anrufe für 7 Pence pro Minute ohne eine Nummer zu nennen; Rufnummern auf Drittseiten stammen nicht von XDP.

## XDP-Pakete mit Peek verfolgen

[Peek](/) erkennt eine `ZDB`-Nummer (mit oder ohne führende `0`) sofort als XDP. Links von `xdp.co.uk` (`/track/` gefolgt von der Nummer oder `track.php?c=`) funktionieren ebenfalls, eine im Link enthaltene Postleitzahl wird jedoch nicht übernommen.

Peek zeigt für XDP ein optionales Feld „Postleitzahl der Lieferadresse“ an. Ohne Postleitzahl wird XDP gar nicht gefragt und das Paket nur über [universelle Tracking-Dienste](guide:universal-tracker) verfolgt. Mit Postleitzahl liest Peek XDPs Tracking-Seite direkt aus; die Postleitzahl geht an XDP und bei Bedarf an Tracking-Dienste. XDP antwortet auf eine falsche Postleitzahl wie auf eine unbekannte Nummer: Prüfe beides.

XDPs Zeiten enthalten keine Zeitzone: Peek liest sie in britischer Zeit und zeigt sie in deiner Zeitzone („Uhrzeiten in deiner Zeitzone:“). Peek listet XDPs eigenen Verlauf (ohne E-Mail-/SMS-Zeilen und ohne Duplikate mehrerer Packstücke) und kann das Gewicht zeigen, lässt aber Depot-, Fahrer- und Kontocodes, Absender, Stückzahl und Kundenreferenzen weg. Einen voraussichtlichen Liefertag, Scan-Orte oder ein separates Zeitfenster-Feld gibt es nicht (außer im Text einzelner Scans wie `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`).

Mit Postleitzahl prüft Peek — solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen — direkt bei XDP bis zu alle 10 Minuten, und ab 6:00 Uhr britischer Zeit am Tag, an dem es in Zustellung geht, bis zu alle 2 Minuten, solange XDPs Tracker antwortet; sonst, und nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“), stündlich. Ohne Postleitzahl, nur über universelle Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange das Paket in Peek geöffnet ist oder Benachrichtigungen an sind. In beiden Fällen prüft Peek nach sieben Tagen ohne Neuigkeiten bei „Etikett erstellt“ alle 6 Stunden. Bleibt ein Paket auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“ vier Tage ohne neuen Scan, zeigt Peek das Datum des letzten Scans („Keine Aktualisierung seit …“) und „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei XDP nach.“ samt dem Link „Hol dir Hilfe bei XDP“ zur Kontaktseite. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Fragen zur XDP Sendungsverfolgung

### Warum funktioniert mein XDP-Tracking nicht?

Meist wegen der Postleitzahl: XDPs Tracker verlangt Consignment-Nummer und Liefer- oder Abhol-Postleitzahl, und eine falsche Postleitzahl zeigt „Consignment not found“. Prüfe beides in der E-Mail des Shops.

### Wie lange dauert die Lieferung mit XDP?

XDP nennt Absendern 1 bis 3 Tage für 1Man und 3 bis 5 Tage für 1+ und 2Man, bei Inseln abhängig vom Seetransport. Am Liefertag gibt es ein Zweistundenfenster.

### Kann ich mein Paket in einem XDP-Depot abholen?

Nein. Laut XDP-App gibt es keine Abholung an Depots oder Hubs, und die FAQ verweisen an den Shop. Du kannst die Lieferung umplanen oder um Abgabe beim Nachbarn bitten.

### Ist XDP ein seriöser Lieferdienst?

Ja. X D P Limited ist seit 1995 beim britischen Handelsregister Companies House eingetragen, hat seinen Sitz in Curdworth bei Sutton Coldfield und liefert auf dem britischen Festland, in Nordirland, auf den Kanalinseln und auf der Isle of Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – Consignment-Nummer vom Absender, Postleitzahl, Hinweise des Trackers
- [XDP: FAQs](https://xdp.co.uk/faqs/) – Statusbedeutungen, Händler kontaktieren, Depotabholung, Umplanung in der App
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – Formular Delivery Query, E-Mails
- [XDP: About Us](https://xdp.co.uk/about-us/) – gegründet 1995, Abdeckung, Zweistundenfenster, Zustellnachweis
- [XDP: Home](https://xdp.co.uk/) – Sperrgut und Großfracht, Services, App
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 bis 3 Tage, Benachrichtigungen, Proxy-Telefon
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 bis 5 Tage, Wunschraum, You Are Next, Live-Chat und E-Mail
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – vorab gebuchter Termin, Zweistundenfenster
- [XDP: App (web version)](https://app.xdp.co.uk/) – Lieferfenster, drei Versuche, Change Delivery Date, Left Safe, keine Depotabholung, Schäden, Rücksendungen, Anrufe, Kontaktformular
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – keine Next-Day-Garantie, Spitzenzeiten, Reklamationsfristen
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 und 7 Tage, Schadensvermerk auf dem Zustellnachweis
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – Postleitzahl und Nicht-gefunden-Meldungen
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – Handelsregistereintrag, Sitz
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – die XDP-App
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek ausliest
:::
