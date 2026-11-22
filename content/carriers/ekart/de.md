---
title: Ekart Sendungsverfolgung: Tracking-ID, Status & RTO erklärt
description: Verfolge ein Ekart-Paket in Indien: Aufbau der 14-stelligen Tracking-ID, Bedeutung von InscannedAtDH und RTO, Zustellversuche und Peek.
slug: ekart-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Ekart (auch *eKart Logistics* und *Ekart Express*), laut *About Us* 2009 gestartet und betrieben von der *Instakart Services Private Limited* mit Sitz in Bengaluru (Karnataka), ist einer der größten indischen E-Commerce-Paketdienste. Er holt Online-Bestellungen beim Händler ab, leitet sie über seine Sortierzentren weiter und stellt in über 15.000 PIN-Code-Gebieten in Indien zu. Kopiere die Tracking-ID aus der Versandnachricht des Shops oben in das Feld oder gib sie auf der [Track-Order-Seite von ekartlogistics.in](https://www.ekartlogistics.in/track-order) ein.

## Tracking-ID von Ekart: Aufbau und Beispiele

Peeks Erkennung kennt Ekarts E-Commerce-IDs als drei Buchstaben, gefolgt von `C`, `P` oder `R` und zehn Ziffern – insgesamt 14 Zeichen (erfundene Beispiele):

| Tracking-ID (erfunden) | Bedeutung |
| --- | --- |
| `FMPP4829105736` | Eine der 17 unten aufgeführten Buchstabengruppen von Ekart, danach `C`, `P` oder `R` und zehn Ziffern: Peek erkennt Ekart allein am Format. |
| `ABCP4829105736` | Andere drei Anfangsbuchstaben im selben Muster: Kann von Ekart stammen, wird aber erst nach Abfrage von Ekarts Tracker zugeordnet (siehe unten). |

Die 17 fest mit Ekart verknüpften Buchstabengruppen lauten `AMI`, `BAL`, `BGS`, `BSI`, `DEL`, `FLO`, `FMP`, `GLA`, `LUA`, `MYE`, `MYN`, `MYS`, `SDL`, `SHO`, `SPO`, `SRT` und `TEL`. Leerzeichen und Kleinschreibung spielen in Peek keine Rolle. Zwei Ausnahmen: Beginnt ein solcher Code mit `PB`, gehört das Muster zum britischen Dienst Parcelforce, und `HRBC` (`HRBC4829105736`) passt zu [Cainiao](carrier:cainiao) / AliExpress statt zu Ekart – stammt deine Sendung von einem indischen Shop, tippe auf „Ändern“ und wähle Ekart.

Verwende stets die Tracking-ID statt der Bestellnummer des Händlers ([Sendungsnummer finden](guide:find-tracking-number)); für B2B-/Stückgutsendungen mit LR-Nummer führt `ekartlogistics.in/track-order` einen separaten Link.

## Tracking-Status von Ekart erklärt

Ekarts Tracker nennt zu jedem Scan Datum, Uhrzeit und Stadt – teils als englischen Klartext (`Shipment Created`), teils als Systemcode samt Zentrumsnamen (`InscannedAtDH` plus Hub). In der üblichen Reihenfolge:

| Ekart meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Pickup Requested`, `Shipment Created` | Der Händler hat Ekart gebucht; Ekart hat das Paket noch nicht. | „Etikett erstellt“ |
| `OutForPickupEvent` | Eine Abholtour ist zum Händler unterwegs; das Paket ist noch beim Verkäufer. | „Etikett erstellt“ |
| `NotPickedEvent` | Die Abholung beim Händler schlug fehl; das Paket liegt weiter dort. | „Etikett erstellt“ |
| `Pickup From Seller`, `PickupReceived` | Ekart hat das Paket beim Verkäufer abgeholt. | „Beim Paketdienst eingegangen“ |
| `ShipmentGrouped`, `ShipmentInTransit`, `dispatched_to_tc`, `dispatched`, `Dispatched to` + Hub | Unterwegs zwischen den Umschlagzentren im Ekart-Netzwerk. | „Unterwegs“ |
| `Expected` | Das nächste Zentrum wurde über das kommende Paket vorinformiert. | „Unterwegs“ |
| `Received`, `Received at` + Hub | In einem Ekart-Zentrum eingetroffen. | „Unterwegs“ |
| `InscannedAtFacility` | Eingangsscan in einer Ekart-Niederlassung. | „Unterwegs“ |
| `InscannedAtDH`, `PrimaryDHInscan` | Im Zustellstützpunkt (*Delivery Hub*, DH) deiner Region angekommen; als Nächstes folgt die Zustelltour. | „Unterwegs“ |
| `Out For Delivery` | Heute auf der Zustelltour. | „In Zustellung“ |
| `Delivered` | Bei dir zugestellt (oder nach einer RTO-Zeile zurück beim Händler, siehe unten). | „Zugestellt“ |
| `undelivered_attempted`, `ShipmentUndeliveredAttempted`, `undelivered_update` | Zustellversuch ohne Erfolg (`undelivered_update` erscheint oft zeitgleich neben dem Hub). | „Zustellversuch“ |
| `undelivered_unattempted` | Die Tagestour endete ohne Zustellversuch. | „Zustellversuch“ |
| `Unsuccessful Unable to attempt delivery due to operational issues`, `Unsuccessful Undelivered. (Rescheduled to: …)` | Ausgeschriebene Fehlzustellung; das Datum hinter `Rescheduled to:` nennt den geplanten nächsten Versuch (ein `Unsuccessful` zur Abholung zählt nicht als Zustellversuch). | „Zustellversuch“ |
| `PickupCancel` | Die Abholung beim Verkäufer wurde storniert, bevor das Paket ins Netz gelangte. | „Problem gemeldet“ |
| `ShipmentRtoConfirmed` | Rücksendung an den Verkäufer (*Return to Origin*, RTO) bestätigt: Das Paket geht zurück. | „Problem gemeldet“ |
| `Marked RTO: ORDER_REJECT` | Nach erfolglosen Touren mit Grund hinter dem Doppelpunkt als Rückläufer zum Händler markiert. | „Problem gemeldet“ |
| `RtoExpected`, `RtoReceived`, `ReturnInscanned` | Auf dem Rückweg zum Verkäufer. | „Unterwegs“ |

**Was RTO bedeutet.** `ShipmentRtoConfirmed`, jede `Marked RTO`-Zeile sowie Codes mit `Rto` oder `Return` am Anfang leiten in Peek den Rückweg zum Händler ein: Ab dieser Zeile steht `InscannedAtDH` für das Zustellzentrum des Verkäufers, und ein späteres `Delivered` zeigt die Rückgabe an den Händler – Peek meldet dann „Zurück an Absender“ statt „Zugestellt“ ([Tracking-Status verstehen](guide:tracking-statuses)).

## Wie Ekart in Indien zustellt

Peeks Code kennt Ekart nur in Indien, und Peek übergibt Ekart-Pakete an keinen anderen Paketdienst. Auf `ekartlogistics.in` nennt die B2C-Express-Seite über 15.000 belieferte PIN-Codes (die FAQ spricht von 5.000 Abhol- und 15.000 Zustell-PIN-Codes, *About Us* von 14.000 PIN-Codes, über 80 Sortierzentren und 3.500 Zustell-Hubs) sowie Same-Day-/Next-Day-Optionen auf kurzen Strecken und 48 bis 96 Stunden auf längeren Distanzen je nach Postleitzahl.

Laut offizieller Ekart-FAQ unternimmt Ekart **fünf erneute Versuche** (*reattempts*) auf der ersten Meile (Abholung beim Händler) und **drei erneute Zustellversuche** auf der letzten Meile beim Empfänger; auf Kundenwunsch lässt sich die Zustellung neu terminieren, solange die Sendung noch nicht als `RTO` markiert ist ([Zustellung verpasst](guide:missed-delivery)).

## Ekart-Tracking hängt, zeigt RTO oder Paket fehlt

**Hängt bei `Shipment Created`, `Pickup Requested` oder `NotPickedEvent`.** Der Händler hat das Paket noch nicht übergeben (oder die Abholung schlug fehl); `PickupCancel` bedeutet Stornierung vor Übergabe. Frag beim Shop nach dem Versandtermin.

**Tagelang kein neuer Scan.** Bleibt ein angemeldetes, übernommenes oder reisendes Paket vier Tage ohne neuen Scan, zeigt Peek „Keine Aktualisierung seit …“ mit dem Tag dieses letzten Scans. Wende dich an den Shop ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).

**Fehlversuch oder RTO.** Prüfe nach `undelivered_attempted`, `undelivered_unattempted` oder `Unsuccessful` ein etwaiges Datum hinter `Rescheduled to:` und gleiche Adresse sowie Telefonnummer beim Händler ab. Bei `ShipmentRtoConfirmed` oder `Marked RTO: ORDER_REJECT` geht die Sendung zurück – bitte den Shop um Erstattung oder Neuversand ([Zurück an den Absender](guide:returned-to-sender)).

**Als zugestellt gemeldet, verloren oder beschädigt.** Frag Haushaltsmitglieder, Nachbarn und den Empfang bzw. Sicherheitsdienst des Gebäudes und melde dich dann beim Händler oder Marktplatz, der Ekart beauftragt hat ([Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Paket verloren](guide:lost-parcel), [Beschädigtes Paket](guide:damaged-parcel)).

## Kundenservice und Kontakt von Ekart

In der FAQ auf `ekartlogistics.in` verweist Ekart einliefernde Händler auf das Ticket-Portal (Antwort binnen 24 Stunden) und nennt unter *Corporate Information* die Adresse der *Instakart Services Private Limited* in Bengaluru (`Embassy Tech Village, Outer Ring Road, Devarabeesanahalli Village, Bengaluru KA 560103`), veröffentlicht dort aber weder Telefonnummer noch E-Mail-Adresse für Paketempfänger. Wende dich für Rückfragen, Ersatz oder Rückerstattung direkt an den Online-Shop oder Marktplatz.

**Vorsicht vor Betrugs-SMS.** Offizielle Domains von Ekart sind `www.ekartlogistics.in` (`ekartlogistics.in`) und `ekartlogistics.com`. Lies vor Zahlungen auf SMS-Links [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Ekart-Pakete mit Peek verfolgen

Füge die Tracking-ID in [Peek](/) ein: Mit einer der 17 Buchstabengruppen plus `C`, `P` oder `R` und zehn Ziffern erkennt Peek Ekart sofort („Erkannter Paketdienst“). Bei anderen Anfangsbuchstaben (`ABCP4829105736`) zeigt Peek „Tracking-Dienste werden geprüft…“ und fragt Ekarts Tracker ab – liegt ein Scan vor, erscheint „Ekart hat dieses Paket.“ (falls noch keiner vorliegt, wähle Ekart über „Ändern“ unter „Passt zu dieser Nummer“). Auch ein `ekartlogistics.com`-Link mit `shipmenttrack/` ordnet die Sendung sofort Ekart zu; ein `shiprocket.co`-Link wird hingegen bei Shiprocket verfolgt. Für alles außer vier Buchstaben und zehn Ziffern meldet Peek bei manueller Wahl, dass Ekart „vergibt keine Nummern wie diese“.

Unter Ekart zeigt ein Paket vor dem ersten Scan „Warten auf Ekart“ (ohne zugewiesenen Dienst „Noch kein Sendungsverlauf“). Zwei Tage nachdem Ekart erstmals meldete, die Nummer nicht zu kennen, bittet Peek dich, sie zu prüfen, einen anderen Paketdienst zu wählen oder das Paket zu löschen. Peek liest Ekarts Tracker ohne Konto oder Telefonnummer aus: jede Zeile im Originalwortlaut samt Stadt und exakter Uhrzeit in deiner Gerätezeitzone („Uhrzeiten in deiner Zeitzone: …“), ohne Dubletten, sowie Ekarts voraussichtliches Lieferdatum, solange das Paket regulär zu dir unterwegs ist (ausgeblendet nach Zustellung, Storno oder RTO).

Solange Ekarts Tracker antwortet, prüft Peek bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen bis zu alle 10 Minuten sowie ab 6 Uhr indischer Zeit am Tag mit Scan `Out For Delivery` bis zu alle 2 Minuten (über Tracking-Dienste an diesem Tag bis zu alle 15 Minuten bei geöffnetem Paket oder aktiven Mitteilungen); sonst und nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ bzw. zwei ruhigen Tagen stündlich. Benachrichtigungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. Ekart oder den Shop kontaktieren kann Peek nicht.

## Häufige Fragen zur Ekart-Sendungsverfolgung

### Wie verfolge ich mein Ekart-Paket?

Füge die 14-stellige Tracking-ID aus der Versandnachricht des Shops oben in Peek ein oder nutze das Feld *Ecommerce Parcel Tracking* auf `ekartlogistics.in/track-order`.

### Wie sieht eine Tracking-ID von Ekart aus?

Drei Buchstaben, danach `C`, `P` oder `R` und zehn Ziffern, zum Beispiel `FMPP4829105736` (erfunden).

### Was bedeutet `RTO` im Ekart-Tracking?

*Return to Origin*: Das Paket geht zurück an den Verkäufer. `ShipmentRtoConfirmed` oder `Marked RTO: ORDER_REJECT` startet die Rücksendung, und ein späteres `Delivered` bestätigt die Rückgabe beim Händler („Zurück an Absender“ in Peek).

### Was heißt `InscannedAtDH` bei Ekart?

Das Paket wurde im Zustellstützpunkt (*Delivery Hub*) eingescannt – auf dem Hinweg in der Basis für deine Zustellung, nach einem RTO im Stützpunkt des Verkäufers.

### Wie viele Zustellversuche unternimmt Ekart?

Laut offizieller Ekart-FAQ unternimmt Ekart drei erneute Zustellversuche (*reattempts*) beim Empfänger (und fünf erneute Abholversuche beim Händler). Für Hilfe zur Bestellung kontaktiere zuerst den Shop.

:::sources
- [Ekart Logistics: Startseite](https://www.ekartlogistics.in/) – indischer E-Commerce-Logistiker und Abdeckung von über 15.000 PIN-Codes
- [Ekart Logistics: Track Order](https://www.ekartlogistics.in/track-order) – E-Commerce-Sendungsverfolgung per Tracking-Nummer und separater B2B-/LR-Link
- [Ekart Logistics: FAQ](https://www.ekartlogistics.in/faq) – drei erneute Zustell-, fünf erneute Abholversuche, Umterminierung vor RTO, 5.000 / 15.000 PIN-Codes und Ticket-Support für Händler
- [Ekart Logistics: About Us](https://www.ekartlogistics.in/about-us) – Start 2009, 3.500 Zustell-Hubs, über 80 Sortierzentren und 14.000 PIN-Codes
- [Ekart Logistics: B2C Express](https://www.ekartlogistics.in/b2c-express) – Same-Day-/Next-Day-Optionen sowie 48–96 Stunden Laufzeit in Indien
- [Ekart Logistics: Corporate Information](https://www.ekartlogistics.in/corporate-info) – Instakart Services Private Limited mit Sitz in Bengaluru
- [Ekart Logistics: Terms & Conditions](https://www.ekartlogistics.in/terms-and-conditions) – Betrieb von ekartlogistics.com durch Instakart Services Private Limited
- [Universal Parcel Scraper: Ekart](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ekart) – 17 Buchstabengruppen, shipmenttrack-Links, 31 Statuscodes und Texte, RTO-Logik, Zeitstempel und Lieferprognose
- [Universal Parcel Scraper: Shiprocket](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/shiprocket) – Tracking-Links auf shiprocket.co
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Ausnahmen PB (Parcelforce) und HRBC (Cainiao / AliExpress) sowie keine Drittübergabe
:::
