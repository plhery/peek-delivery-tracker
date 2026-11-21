---
title: OnTrac Sendungsverfolgung: Nummern, Status und Hilfe
description: OnTrac- oder LaserShip-Paket in den USA verfolgen: Nummernformate C, D, LX und 1LS, Bedeutung aller Status, Fehlversuche, Verzögerungen und Wink-Chat.
slug: ontrac-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

OnTrac ist ein US-amerikanischer Zustelldienst für die letzte Meile, der Online-Bestellungen für Händler und Marken in `35` US-Bundesstaaten sowie Washington, D.C. (`70 %` der US-Bevölkerung) ausliefert und seit der Fusion 2021 auch LaserShip umfasst. Verfolge dein Paket auf [ontrac.com/tracking](https://www.ontrac.com/tracking/) mit der Sendungsnummer oder füge sie direkt oben in das Suchfeld ein.

## OnTrac-Sendungsnummer: Aufbau und Fundort

Die Nummer steht auf deiner Bestellseite oder in der Versandmail des Händlers (eine reine Bestellnummer funktioniert nicht). Laut OnTrac-FAQ beginnen Sendungsnummern mit `C`, `D`, `1LS`, `LS`, `LX` oder `BN` ([Sendungsnummer finden](guide:find-tracking-number)). Diese Formate erkennt Peek (mit erfundenen Ziffern):

| Format (erfunden) | Was das ist |
| --- | --- |
| `C14829105736497` | `C1` plus 13 Ziffern mit Prüfziffer am Ende (15 Zeichen): sofort als OnTrac erkannt. |
| `D10482910573645` | `D10` plus 12 Ziffern mit Prüfziffer (15 Zeichen): sofort als OnTrac erkannt (`C24829105736495` und andere gültige `C`/`D`-Nummern mit 14 Ziffern fragt Peek bei OnTrac an). |
| `LX48291057` | LaserShip-Format: `L`, gefolgt von `A`, `E`, `H`, `I`, `N` oder `X` und 8 Ziffern (10 Zeichen). |
| `1LS482910573649` | LaserShip-Format: `1LS` plus 12 bis 14 Buchstaben oder Ziffern. |
| `AD482910573` | Absenderreferenz (`AD` + 9 Ziffern), die OnTrac nach Auswahl von OnTrac in die eigentliche OnTrac-Nummer auflöst. |

Die frühere Website `lasership.com` leitet auf `ontrac.com` weiter, sodass LaserShip-Sendungen dort ebenfalls verfolgt werden. Nummern mit `LS` oder `BN` (oder anderen Mustern) prüfst du direkt auf `ontrac.com/tracking/`, da Peek bei manueller Wahl sonst meldet, dass OnTrac „vergibt keine Nummern wie diese“. Ganz frische Nummern zeigen anfangs oft noch nichts: OnTrac bittet um **1 bis 3 Tage** Geduld nach der Versandmail, weil manche Händler Daten erst nach bis zu 24 Stunden übermitteln.

## Was die OnTrac-Tracking-Status bedeuten

OnTracs Website ist ausschließlich auf Englisch verfügbar. Diese Ereignisse und Verzögerungsmeldungen liest Peek aus:

| OnTrac-Status | Bedeutung für dein Paket | Peek zeigt |
| --- | --- | --- |
| `On its way to OnTrac` | Etikettendaten übermittelt, Paket aber noch nicht bei OnTrac (`LABEL CREATED, TRACKING DETAILS WILL BE AVAILABLE ONCE THE PACKAGE ARRIVES AT THE ONTRAC FACILITY` oder `Waiting for package from shipper.`). | „Etikett erstellt“ |
| `Origin Scan` | OnTrac hat das Paket übernommen; unterwegs Richtung Zieldepot (Zwischenstationen heißen `Arrived at Facility`). | „Unterwegs“ |
| `Arrived at Final Servicing Facility` | Im lokalen Zustellstützpunkt eingetroffen und in Vorbereitung. | „Unterwegs“ |
| `Loaded onto vehicle` | In ein Fahrzeug verladen. | „Unterwegs“ |
| `Out for Delivery` | Heute mit dem Fahrer auf Zustelltour. | „In Zustellung“ |
| `Package Delivered` | Zugestellt. | „Zugestellt“ |
| `Delivery Pending` | Zustellversuch ohne Ablage (z. B. Sicherheitsbedenken oder fehlende Unterschrift); OnTrac versucht es erneut. | „Zustellversuch“ |
| `Business closed. Please provide hours` | Geschäftsadresse geschlossen; bis zu 3 Versuche, OnTrac bittet um Öffnungszeiten. | „Zustellversuch“ |
| `Incomplete address. Please contact us` | Unvollständige Adresse bei der Zustellung; Details nachreichen, sonst droht Rücksendung. | „Zustellversuch“ |
| `Details needed. Please contact us` | Paket im Depot zurückgehalten, bis du Adress- oder Zugangshinweise gibst. | „Problem gemeldet“ |
| `Order has been transferred to another carrier` | An einen nicht genannten Drittzusteller übergeben (obwohl die Überschrift auf `ontrac.com` *Delivered* lautet). | „Unterwegs“ |
| `Delivery Delay`, `Delayed. Delivery date updated`, `Delayed by road closure`, `Delayed. Could not reach delivery area` | Verzögerung im Lauf, Straßensperrung oder Gebiet nicht erreichbar; Lieferdatum aktualisiert. | „Unterwegs“ |
| `Weather delay. Delivery date updated`, `Weather delay. See service alerts` | Wetterbedingte Verzögerung im OnTrac-Netz. | „Unterwegs“ |
| `Misrouted. Delivery date updated` | Fehlleitung erkannt, Lieferdatum neu berechnet. | „Unterwegs“ |

Allgemeine Hintergründe findest du unter [Tracking-Status verstehen](guide:tracking-statuses).

## Lieferzeiten, Wochenendzustellung und verpasste Pakete

**Zustellfenster.** OnTrac liefert an Wohnadressen in der Regel zwischen **`08:00` und `21:00` Uhr** Ortszeit am geplanten Tag – in ausgewählten Gebieten auch **samstags und sonntags** zur selben Zeit (Standardgrenzen laut Zusatzbedingungen: abrechenbares Gewicht bis `50 lbs`, Länge `48 in`, zweitlängste Seite `30 in`, Volumen `8.640 Kubikzoll`).

**Verpasste Zustellung.** OnTrac unternimmt bis zu **3 Zustellversuche** (der nächste folgt binnen **1 bis 2 Werktagen**, nie noch am selben Tag) und schickt unzustellbare Sendungen danach zurück oder verfährt nach seinen Regeln für unzustellbare Sendungen ([Zustellung verpasst](guide:missed-delivery)).

**Zustellhinweise und Adressänderung.** Warst du trotz Fehlversuch zu Hause, prüfe die Lieferadresse im Shop-Konto und übermittle Zugangscodes oder Hinweise mit deiner Sendungsnummer über den Chat-Assistenten **Wink** (ist das Paket schon auf Tour, greifen Hinweise meist erst beim nächsten Versuch). Eine **Adressänderung** muss zwingend der Händler freigeben ([Lieferadresse ändern](guide:change-delivery-address)).

**Depotabholung und SMS-Alarme.** Eigene Paketshops oder Packstationen nennt OnTracs Website nicht; eine Abholung im lokalen Depot ist nur nach vorheriger Anfrage möglich (Sendungsnummer und Lichtbildausweis mitbringen). Über `Get Text Alerts` (`NotifyMe`) auf `ontrac.com/tracking/` erhältst du SMS bei Zustellstart, Fehlversuch und Ablieferung (Abmeldung per `STOP` an `(469) 529-1133`); dort kann auch ein Zustellfoto (`VPOD`) angezeigt werden.

## OnTrac-Paket verspätet, ohne Update oder unauffindbar

- **Kein neues Update:** Ändert sich der Status **3 Werktage** lang nicht, verweist OnTrac zuerst an den Händler und danach an den Chat-Assistenten **Wink** ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).
- **Als zugestellt markiert, aber nicht da:** Prüfe Adresse und Zustellfoto auf `ontrac.com/tracking/` (oder frage bei Wohnanlagen-Paketfächern die Hausverwaltung) und wähle unter dem Ergebnis im Formular `Have a question?` den Punkt `Tracking shows delivered; no package` oder starte über **Wink** eine Nachforschung. OnTrac sendet per E-Mail eine Fallnummer und prüft den Fall binnen **48 Stunden**; hörst du nach **2 Werktagen** nichts, kontaktiere den Verkäufer ([Zugestellt, aber nicht erhalten](guide:delivered-not-received)).
- **Beschädigt oder verloren:** Formelle Schadens- und Verlustansprüche muss laut OnTrac-Bedingungen der Versender einreichen (`cargoclaims@ontrac.com`) – wende dich für Ersatz oder Erstattung an den Shop ([Beschädigtes Paket](guide:damaged-parcel), [Paket verloren](guide:lost-parcel)).

## OnTrac-Kundenservice: Chat, SMS und Zeiten

Auf den Seiten [Support](https://www.ontrac.com/support/) und [FAQ](https://www.ontrac.com/faq/) nennt OnTrac weder eine Sprach-Hotline noch eine Kunden-E-Mail, sondern drei Wege für Empfänger:

- **Chat mit Wink:** rotes Chat-Symbol unten rechts auf `ontrac.com` (gültige Sendungsnummer erforderlich).
- **SMS-Service:** `start` per SMS an **`703-348-8441`** senden (reine Textleitung zum Verfolgen oder Falleröffnen, reguläre SMS-Tarife, `STOP` zum Beenden).
- **Anfrageformular:** `Have a question?` direkt unter dem Tracking-Ergebnis auf `ontrac.com/tracking/`.
- **Servicezeiten:** Montag–Freitag `08:00–21:00` Uhr US-Ostküstenzeit (*Eastern Time*), Samstag–Sonntag `08:00–20:00` Uhr *Eastern Time*; geschlossen am 1. und 2. Weihnachtsfeiertag (`December 26`) sowie an Neujahr.

Wie du gefälschte Benachrichtigungen erkennst, zeigt [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## OnTrac-Pakete mit Peek verfolgen

[Peek](/) erkennt OnTrac sofort („Erkannter Paketdienst“) bei `C1`- und `D10`-Nummern mit gültiger Prüfziffer, bei LaserShip-Codes mit `LX` und `1LS` sowie bei `ontrac.com`-Links mit `number=`. Bei anderen gültigen 14-ziffrigen `C`- oder `D`-Nummern zeigt Peek „Tracking-Dienste werden geprüft…“ und nach Antwort „OnTrac hat dieses Paket.“. Für eine `AD`-Absenderreferenz wählst du OnTrac über „Ändern“ aus, woraufhin OnTrac das Paket findet und Peek zusätzlich OnTracs eigene Sendungsnummer zeigt („OnTrac führt die Sendung unter“) (vor dem ersten Scan steht dort „Warten auf OnTrac“).

Peek liest den JSON-Dienst hinter `ontrac.com` aus und zeigt Status, jeden Scan mit Ort und US-Bundesstaat auf der Karte (bis zu 100 Einträge), das voraussichtliche Lieferdatum, Lieferzeitpunkt, Gewicht und Maße – ohne Empfängerdaten, Unterschrift oder Zustellfoto. Enthält ein Scanzeitpunkt einen UTC-Offset, rechnet Peek ihn in deine Gerätezeitzone um („Uhrzeiten in deiner Zeitzone: …“); ohne Offset liest Peek die Uhrzeit auf der Uhr des US-Bundesstaats des Scans und rechnet sie ebenso um. `Order has been transferred to another carrier` bleibt in Peek „Unterwegs“ statt zugestellt; mangels Angabe des Folgezustellers kann Peek ihm nicht folgen.

Bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen prüft Peek bis zu alle 10 Minuten, und ab `06:00` Uhr Ortszeit am Tag des Scans `Out for Delivery` bis zu alle 2 Minuten, solange OnTracs Dienst antwortet; sonst sowie nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich („Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“). OnTrac oder den Shop kontaktieren kann Peek nicht.

## Häufige Fragen zur OnTrac-Sendungsverfolgung

### Womit beginnen OnTrac-Sendungsnummern?

Laut OnTrac mit `C`, `D`, `1LS`, `LS`, `LX` oder `BN`; die von Peek erkannten `C`- und `D`-Nummern sind 15 Zeichen lang (Buchstabe plus 14 Ziffern).

### Warum aktualisiert sich mein OnTrac-Tracking nicht?

Plane nach der Versandmail zunächst 1 bis 3 Tage ein, bis erste Updates erscheinen. Bewegt sich ein aktives Paket 3 Werktage lang nicht, wende dich an den Händler und bei Bedarf an Wink.

### Wann stellt OnTrac zu?

Zwischen `08:00` und `21:00` Uhr deiner Ortszeit am geplanten Liefertag – in Teilen des Gebiets auch samstags und sonntags.

### Gibt es bei OnTrac Live-Tracking?

OnTrac zeigt jeden Scan mit Datum, Uhrzeit und Stadt (plus Zustellfoto auf der Website) und verschickt über `NotifyMe` SMS bei Zustellbeginn, Versuch und Lieferung.

### Ist OnTrac seriös?

Ja: OnTrac startete 1991 als *California Overnight*, fusionierte 2021 mit LaserShip (gegründet 1986), betreibt über `102` Standorte sowie `18` Sortierzentren und ist bevorzugter Versandpartner für eigenversendende Amazon-Händler (*MFN*).

:::sources
- [OnTrac: FAQ](https://www.ontrac.com/faq/) – Präfixe, Zustellung 08:00–21:00 Uhr und Wochenende, 1–2 Werktage bis zum Neuversuch, Wink, SMS-Nummer 703-348-8441, 102+ Standorte und 18 Sortierzentren
- [OnTrac: Support](https://www.ontrac.com/support/) – 1–3 Tage Vorlauf, 48-Stunden-Nachforschung, NotifyMe sowie Zeiten und Feiertage (inkl. 26. Dezember)
- [OnTrac: Tracking](https://www.ontrac.com/tracking/) – Suchmaske, Zustellfoto-Link und Formular Have a question?
- [OnTrac: Tracking-Skript](https://www.ontrac.com/wp-content/themes/ontrac/assets/js/ontrac-package-status.js) – Statusüberschriften, Verlaufsspalten und Anfragegründe
- [OnTrac: Statuscodes](https://www.ontrac.com/wp-content/themes/ontrac/assets/data/statusCodes.json) – Bedeutung von Versuchen, Rückhalten, Übergaben und Verzögerungen
- [OnTrac: Additional Terms & Conditions](https://www.ontrac.com/terms-conditions-additional/) – 3 Versuche, Maße bis 50 lbs / 48 in und Schadensmeldung durch den Versender
- [OnTrac: About OnTrac](https://www.ontrac.com/about/) – Gründung von LaserShip 1986 und OnTrac 1991, Fusion 2021 und Küste-zu-Küste-Netz
- [OnTrac: Residential E-Commerce Delivery](https://www.ontrac.com/residential-e-commerce-delivery/) – 35 US-Staaten und D.C., 70 % Bevölkerungsabdeckung und Foto-Nachweis VPOD
- [OnTrac: Amazon MFN Sellers](https://www.ontrac.com/amazon-mfn-sellers/) – bevorzugter Paketdienst für Amazon-MFN-Verkäufer
- [OnTrac: NotifyMe Terms of Use](https://www.ontrac.com/notifyme-terms-of-use/) – SMS-Benachrichtigungen und STOP an (469) 529-1133
- [Universal Parcel Scraper: OnTrac](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ontrac) – Muster C, D, LX, 1LS und AD-Referenz, 18 Statustexte, Zeitzonen, Gewicht/Maße und NFRP-Übergabe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Paketdiensterkennung und Tracking-Dienste
:::
