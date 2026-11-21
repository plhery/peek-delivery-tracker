---
title: Morning Global Sendungsverfolgung: BR…MG-Nummern & Status
description: Verfolge ein Paket von Morning Global aus Hongkong oder Festlandchina: BR…MG-Format, englische Status, Zustellung vor Ort, EU-Zoll und Kontakt.
slug: morning-global-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Morning Global (MG), gegründet 1987, ist die internationale Sparte der Hongkonger Kuriergruppe Morning Express Group. Das Unternehmen verschickt Auslandspakete für Online-Shops und Marktplätze, unterhält Büros in Hongkong und in ganz Festlandchina und übergibt Sendungen für die letzte Meile meist an einen Partner in deinem Land. Verfolge die vom Shop mitgeteilte Nummer auf Morning Globals englischer [Trace-&-Track-Seite](https://www.morninglobal.com/trace-track/) oder füge sie oben in das Suchfeld ein.

## Sendungsnummer von Morning Global: Aufbau und Beispiele

Morning Global veröffentlicht kein Nummernschema. Peeks Katalog kennt ein Muster für diesen Anbieter (erfundene Ziffern):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `BR482910573MG` | `BR`, neun Ziffern und `MG`: eine Nummer von Morning Global. Da dieses Muster auch zu internationalen Postnummern passt, fragt Peek erst bei Morning Global an, bevor es den Anbieter nennt (erfüllt die neunte Ziffer zudem die postalische Prüfziffer wie bei `BR482910576MG`, fragt Peek Morning Global und [Chronopost](carrier:chronopost) ab). |
| `BR48291057MG` | Nur acht Ziffern: kein Format von Morning Global; Peek ordnet die Nummer dort nicht zu. Prüfe, ob eine Ziffer fehlt. |

Kleinschreibung, Leerzeichen und Bindestriche spielen in Peek keine Rolle. Morning Globals Tracker nimmt bis zu 20 durch Kommas getrennte Nummern gleichzeitig an.

**Wo du sie findest.** In der Versand-E-Mail des Händlers oder in deiner Bestellübersicht ([Sendungsnummer finden](guide:find-tracking-number) und [Pakete aus China verfolgen](guide:tracking-from-china)).

**Abweichende Formate.** Wählst du Morning Global manuell aus, fragt Peek dessen Tracker für jede 8- bis 40-stellige Folge ab, die Buchstaben und Ziffern mischt (sonst meldet Peek, dass der Dienst „vergibt keine Nummern wie diese“).

**Zwei Versandarten.** Morning Global bietet *MG International Express (MGIE)* als selbst betriebenen Tür-zu-Tür-Express sowie das günstigere *MG International Postal (MGIP)* über weltweite Post-Hubs an. Zusammen decken MGIE und MGIP über 220 Ziele für Pakete bis 30 kg ab.

## Tracking-Status von Morning Global erklärt

Morning Globals Tracker listet den Verlauf unter `Shipment Journey` mit Status, oft einer Zusatzbeschreibung, Datum, Uhrzeit und Ort – häufig ein Ländercode samt UTC-Offset wie `CA (UTC-4)`. In der üblichen Reihenfolge:

| Morning Global meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order Created` | Der Shop hat die Sendung angemeldet; Morning Global hat das Paket noch nicht. | „Etikett erstellt“ |
| `Collection` | Morning Global hat das Paket beim Händler abgeholt. | „Beim Paketdienst eingegangen“ |
| `Arrive Hub` | In einem Umschlagzentrum von Morning Global eingetroffen. | „Unterwegs“ |
| `Depart Origin` | Hat das Abgangszentrum verlassen. | „Unterwegs“ |
| `Depart Hub` | Hat ein Umschlagzentrum verlassen (Formulierung aus Morning Globals früherem Tracker). | „Unterwegs“ |
| `Ready for Departure` | Versandbereit in Richtung Zielland. | „Unterwegs“ |
| `Flight Departure to Destination` | Der Flug in dein Land ist gestartet. | „Unterwegs“ |
| `Arrive Destination` | Im Zielland eingetroffen; Verzollung und Übergabe an den Zustellpartner folgen noch. | „Unterwegs“ |
| `Customs Clearance Begins` | Die Sendung liegt beim Zoll deines Landes. | „Beim Zoll“ |
| `Customs Cleared` | Der Zoll hat das Paket freigegeben. | „Unterwegs“ |
| `Handed Over for Delivery` | An den Zustellpartner in deinem Land übergeben; noch nicht auf dem Zustellfahrzeug. | „Unterwegs“ |
| `Out for Delivery` | Heute auf der Zustelltour. | „In Zustellung“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |

Enthält die Beschreibung mehr als der reine Status, zeigt Peek `Status: Beschreibung` und entfernt dabei Telefonnummern. Andere Texte stuft Peek nach seinen allgemeinen Regeln ein oder zeigt sie ohne eigene Statusänderung.

**Nicht gefunden.** Kennt Morning Globals Tracker eine Nummer nicht, lautet die Antwort `No shipment record was found for this tracking number.`

## Wer stellt Pakete von Morning Global zu?

Mit `Handed Over for Delivery` übergibt Morning Global die Sendung an den Partner, der in deinem Land zustellt. Weder die Website noch der Tracker nennen gegenüber Peek diesen Partner oder dessen eigene Sendungsnummer – für eine Umverfügung frag den Shop oder den Support von Morning Global nach der Partnernummer.

**Eigene Standorte.** Zu seinen eigenbetriebenen Standorten zählt Morning Global Großbritannien und die USA (Regionalzentralen in London für UK und Europa, Los Angeles für Amerika sowie Guangzhou in Festlandchina, dazu eine Niederlassung in Amsterdam); Kanada wird neben den USA als Kernmarkt genannt, Australien als Partnernetzwerk. Eigene Regeln zu Zustellversuchen oder Paketshops veröffentlicht Morning Global nicht – es gelten die des Endzustellers ([Zustellung verpasst](guide:missed-delivery), [Nicht abgeholte Pakete](guide:uncollected-parcel)).

**Zoll in Deutschland, Österreich und Luxemburg.** In der **Europäischen Union** fällt für online außerhalb der EU gekaufte Waren in Sendungen bis 150 € seit 1. Juli 2026 ein vorläufiger Zoll von 3 € je Warenposition an (Waren gleicher Zolltarifnummer, Beschreibung und – sofern erforderlich – gleichen Ursprungs; über 150 € gilt der reguläre Zollsatz) sowie seit 1. November 2026 unabhängig vom Wert eine EU-Bearbeitungsgebühr von 2 € je Warenposition, zuzüglich Einfuhrumsatzsteuer in **Deutschland** (ausgenommen die Insel Helgoland und das Gebiet von Büsingen, die außerhalb des EU-Zoll- und Mehrwertsteuergebiets liegen), **Österreich** und **Luxemburg**, sofern die Mehrwertsteuer nicht bereits beim Kauf über IOSS erhoben wurde. Am 22. Oktober 2026 teilte Morning Global seinen Versandkunden mit, dass seit 1. November 2026 für B2C-Sendungen in die EU eine `EUR 2 Customs Handling Fee` je Zollanmeldungsposition berechnet wird (sowohl bei IOSS als auch bei DAP, auch über 150 €), zuzüglich etwaiger Mehrwertsteuer auf diese Gebühr je nach Zielland; ob Händler sie weiterberechnen, lässt die Mitteilung offen ([Paket hängt beim Zoll](guide:customs)).

**Zollunterlagen und Verspätungshinweise.** Fordert der Zoll Ausweiskopie oder Rechnung an, stellt Morning Global das Formular [Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) bereit: Nutze es nur direkt auf `morninglobal.com` ([Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts)). Verzögerungen meldet die Seite *News* (etwa am 30. Oktober 2026 rund 2 bis 3 Werktage Zusatzlaufzeit für MGIP in Australien wegen Arbeitskampfmaßnahmen).

## Morning-Global-Paket aktualisiert sich nicht, fehlt oder geht zurück

Vertragspartner von Morning Global sind die einliefernden Shops – wende dich bei Verspätung, Verlust, Schaden oder Rücksendung zuerst an den Händler oder Marktplatz.

**Noch nicht gefunden.** Steht der Paketdienst noch nicht fest, passt die Nummer aber zum `BR…MG`-Muster (ohne gültige Post-Prüfziffer), zeigt Peek „Wahrscheinlich Morning Global“ und „Warten auf den ersten Scan von Morning Global“; erfüllt sie auch die Post-Prüfziffer (`BR482910576MG`), fragt Peek Morning Global und [Chronopost](carrier:chronopost) an und übernimmt den Dienst, der die Sendung kennt. Unter Morning Global gespeicherte Pakete zeigen „Warten auf Morning Global“. Tut sich nach einer Woche nichts, prüfe die Nummer beim Shop.

**Steht bei `Order Created` oder pausiert unterwegs.** `Order Created` heißt, dass der Händler das Paket noch nicht übergeben hat. Bleibt ein Paket unterwegs vier Tage ohne neue Zeile, zeigt Peek „Seit vier Tagen keine Aktualisierung“; nach `Handed Over for Delivery` erfrage die Nummer des Zustellpartners ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).

**Verloren, beschädigt, verweigert oder nicht erhalten.** Laut Bedingungen hat der Händler ab Übernahme 30 Tage zur Schadensmeldung und 90 Tage zum Einreichen der Belege; bei Verschulden von Morning Global ist der Ersatz auf den deklarierten Wert und höchstens 50 US-$ je Paket begrenzt. Scheitert die Zustellung wegen falscher oder unvollständiger Adresse, Nichtantreffens oder Annahmeverweigerung, gilt der Auftrag gegenüber dem Händler als erfüllt ([Paket verloren](guide:lost-parcel), [Beschädigtes Paket](guide:damaged-parcel), [Zurück an den Absender](guide:returned-to-sender)). Steht die Sendung auf `Delivered`, ist aber unauffindbar, prüfe `Signed By` in Morning Globals Tracker (von Peek ausgeblendet), frag Nachbarn und kontaktiere den Shop ([Zugestellt, aber nicht erhalten](guide:delivered-not-received)).

## Kundenservice und Kontakt von Morning Global

Für Sendungsanfragen auf Morning Globals [Kontaktseite](https://www.morninglobal.com/contact/) (Sendungsnummer bereithalten; Öffnungszeiten nennt Morning Global nicht):

- **Großbritannien** (Regionalzentrale London): `cs.mgeu@morninglobal.com`, `+44 20 7019 9035`.
- **USA** (Regionalzentrale Los Angeles): `cs.mgus@morninglobal.com`, `+1 888 981 8688`.
- **Hongkong und Macau** (Weltzentrale Hongkong): `cs.mghk@morninglobal.com`, `+852 3669 6360`.
- **Festlandchina** (Regionalzentrale Guangzhou): `cs.mgchina@morninglobal.com`, `+86 4008 268 216`.
- **Weitere Regionalteams**: Italien (`cs.mgit@morninglobal.com`), Indien, die Philippinen und Singapur stehen mit eigenen Adressen auf derselben Seite.
- **Weltweites Team**: `cs.mgworldwide@morninglobal.com`, per WhatsApp unter `+1 (479) 777 3307` (oder QR-Code) sowie per Kontaktformular (Name, E-Mail, Firma, Nachricht).

**Vorsicht vor Phishing-SMS.** Die offizielle Domain lautet `www.morninglobal.com` (`morninglobal.com`). Lies vor dem Öffnen von Links aus Nachrichten [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Morning-Global-Pakete mit Peek verfolgen

Allein aus einer `BR…MG`-Nummer schließt [Peek](/) noch nicht fest auf Morning Global, verfolgt aber über 3.500 Paketdienste: Beim Einfügen erscheint „Tracking-Dienste werden geprüft…“ und – sobald Morning Globals Tracker die Nummer kennt – „Morning Global hat dieses Paket.“. Ein `www.morninglobal.com`-Link mit `tracking_number=` oder `ydh=` legt das Paket sofort unter Morning Global an („Erkannter Paketdienst“). Andernfalls tippe auf „Ändern“ und wähle Morning Global unter „Passt zu dieser Nummer“ oder „Alle Paketdienste“ (bzw. „Paketdienst ändern“ bei gespeicherten Paketen).

Peek fragt Morning Globals Tracker ohne Postleitzahl ab, zeigt Status, jeden Schritt mit Ort, Zielland und Zustellzeit, liest Zeiten anhand des angegebenen UTC-Offsets neben dem Land (ohne Offset über die Uhr des Orts, eines Nachbarschritts oder Hongkongs) und rechnet alles in deine Gerätezeitzone um („Uhrzeiten in deiner Zeitzone: …“). Herkunftsort, `Estimated Delivery`, Gewicht, Stückzahl, `Reference` und `Signed By` lässt Peek weg (daher kein voraussichtliches Lieferdatum) und bleibt bis zum Schluss bei Morning Globals Tracker, ohne deine Landespost abzufragen.

Peek prüft höchstens einmal pro Stunde (auch bei „In Zustellung“) und wartet nach einer fehlgeschlagenen Abfrage 2 Stunden (ebenso bei manueller Aktualisierung). Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ (`Customs Clearance Begins`, `Out for Delivery` und `Delivered`) oder „Nur die Zustellung“. Morning Global oder den Shop kontaktieren kann Peek nicht.

## Häufige Fragen zur Sendungsverfolgung von Morning Global

### Ist Morning Global seriös?

Ja. Morning Global wurde 1987 gegründet, ist die Auslandssparte der Hongkonger Morning Express Group mit Hauptsitz in Hongkong, betreibt laut eigenen Angaben Standorte in 14 Ländern und versendet für Marken, Händler und Marktplätze.

### Wie verfolge ich ein Paket von Morning Global?

Füge die Sendungsnummer des Shops oben in das Feld ein oder gib sie auf `morninglobal.com/trace-track/` ein (bis zu 20 Nummern, getrennt durch Kommas).

### Was bedeutet `Handed Over for Delivery` bei Morning Global?

Morning Global hat das Paket nach dem Zoll an den Zustellpartner in deinem Land übergeben. Auf dem Zustellfahrzeug ist es noch nicht – als Nächstes folgt `Out for Delivery`.

### Wer liefert Morning-Global-Pakete aus?

Ein Partnerdienst in deinem Land, den Morning Globals Website und Tracker nicht benennen. Seine eigene Sendungsnummer erfragst du beim Händler oder beim Support von Morning Global.

### Wie erreiche ich Morning Global?

Per E-Mail mit deiner Sendungsnummer an `cs.mgeu@morninglobal.com` (Großbritannien), `cs.mgus@morninglobal.com` (USA) oder `cs.mgworldwide@morninglobal.com` (weltweit, auch WhatsApp `+1 (479) 777 3307`). Zur Bestellung selbst wende dich zuerst an den Shop.

:::sources
- [Morning Global: Trace & Track](https://www.morninglobal.com/trace-track/) – Suche bis zu 20 Nummern, Shipment Journey, Estimated Delivery, Reference, Signed By und Nicht-gefunden-Text
- [Morning Global: Home](https://www.morninglobal.com/) – Gründung 1987, eigene Präsenz in 14 Ländern und Meldungen
- [Morning Global: About Us](https://www.morninglobal.com/about-us/) – internationale Sparte der Morning Express Group in Hongkong
- [Morning Global: Services](https://www.morninglobal.com/services/) – MGIE und MGIP, über 220 Ziele und Gewicht bis 30 kg
- [Morning Global: Global Network](https://www.morninglobal.com/global-network/) – eigene Standorte (UK, USA), Kanada und Partnernetzwerk (Australien)
- [Morning Global: Contact](https://www.morninglobal.com/contact/) – regionale Postfächer und Rufnummern, weltweites WhatsApp, Formular sowie Zentralen in Hongkong, Guangzhou (Festlandchina), London und Los Angeles
- [Morning Global: Upload Clearance Documents](https://www.morninglobal.com/upload-clearance-documents/) – Formular zum Hochladen von Zolldokumenten
- [Morning Global: Terms and Conditions (Privacy Policy)](https://www.morninglobal.com/privacy-policy/) – fehlgeschlagene Zustellungen, 30-/90-Tage-Fristen und 50-US-$-Haftungsgrenze
- [Morning Global: EUR 2 Customs Handling Fee for EU-bound B2C Shipments](https://www.morninglobal.com/morning-global-service-update-introduction-of-eur-2-customs-handling-fee-for-eu-bound-b2c-shipments-from-1-november-2026/) – 2 € je Zollanmeldungsposition seit 1. November 2026 und Mehrwertsteuer auf die Gebühr
- [Morning Global: Extended Transit Times in Australia](https://www.morninglobal.com/morning-global-service-impact-notice-extended-transit-times-in-australia-due-to-industrial-action/) – rund 2 bis 3 Werktage Verzögerung für MGIP in Australien
- [Morning Global: News](https://www.morninglobal.com/news/) – Servicemitteilungen
- [Europäische Kommission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll bis 150 € seit Juli 2026 und 2-€-Bearbeitungsgebühr seit November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Positionsbegriff, 2 € unabhängig vom Wert und IOSS-Mehrwertsteuerregelung
- [Europäische Kommission: Territorialer Geltungsbereich von MwSt. und Zoll](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Helgoland und Büsingen außerhalb des EU-Zoll- und Mehrwertsteuergebiets
- [Universal Parcel Scraper: Morning Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/morning-global) – BR…MG-Muster, Links, Statuszuordnung, Zeitzonen, Stundentakt und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – S10-Abfrage mit Chronopost und kein Wechsel zur Landespost
:::
