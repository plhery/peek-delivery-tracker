---
title: Delhivery Sendungsverfolgung: AWB, Status und Support
description: Delhivery-Paket in Indien oder international verfolgen: 13- und 14-stellige AWB-Nummern, Status wie RTO, Fehlversuche, Zoll, Reklamationen und Support.
slug: delhivery-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Das 2011 gegründete Unternehmen Delhivery (*Delhivery Limited*) bezeichnet sich als Indiens größten voll integrierten Logistikanbieter: Es stellt Online-Bestellungen für Marktplätze und Shops in über `18.850` PIN-Codes zu und versendet aus Indien in mehr als `220` Länder und Gebiete. Verfolge dein Paket auf [Delhiverys Tracking-Seite](https://www.delhivery.com/tracking) per AWB-Nummer, Bestellnummer (`Order Id`), `LRN` oder Mobilnummer – oder füge die AWB direkt oben in das Suchfeld ein.

## Delhivery-Sendungsnummer (AWB): Aufbau und Fundort

Delhivery nennt die Sendungsnummer **AWB** (*airway bill number*, Frachtbriefnummer). Peek kennt zwei AWB-Formate von Delhivery (mit frei erfundenen Ziffern):

| Nummer (erfunden) | Was das ist |
| --- | --- |
| `1482910573642` | **13 Ziffern**: eine Delhivery-AWB. Dreizehn Ziffern nutzen auch andere Dienste wie [Econt](carrier:econt), DPD France, FAN Courier oder Korea Post. |
| `14829105736492` | **14 Ziffern**: ebenfalls eine Delhivery-AWB – ebenso genutzt von [DPD](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) oder Xpressbees. |

Allein die Ziffernlänge weist also noch nicht eindeutig auf Delhivery hin: Prüfe in der Versand-SMS oder E-Mail des Händlers, welcher Paketdienst genannt wird. Auf `delhivery.com/tracking` klappt die Suche auch per `Order Id`, `LRN` (B2B-Fracht) oder per Login mit deiner Mobilnummer und Einmalcode (`OTP`); Peek benötigt die **AWB-Nummer** ([Sendungsnummer finden](guide:find-tracking-number)).

## Delhivery-Tracking-Status erklärt

Delhiverys Sendungsverfolgung zeigt eine Fortschrittsleiste sowie die bisherigen Scans samt Stadt. Diese Statusmeldungen und Codes liest Peek bei Delhivery aus:

| Delhivery-Status | Bedeutung für dein Paket | Peek zeigt |
| --- | --- | --- |
| `WAITING_PICKUP` | Sendung angemeldet, aber noch nicht abgeholt (ohne Scan als `Waiting pickup` angezeigt). | „Etikett erstellt“ |
| `Picked Up` | Vom Absender abgeholt (auch als Fracht-Meilenstein ohne Einzelscans genutzt). | „Beim Paketdienst eingegangen“ |
| `PROD_REPLACED` | Bei einer Ersatzlieferung mit Rückholung hat der Fahrer den auszutauschenden Artikel mitgenommen. | „Beim Paketdienst eingegangen“ |
| `In Transit`, `IN_TRANSIT`, `On the Way` | Unterwegs im Delhivery-Netz (`IN_TRANSIT` als Statuscode, `On the Way` als Fracht-Meilenstein). | „Unterwegs“ |
| `Dispatched`, `Out for Delivery` | Auf dem Weg zur Zustellung (`Dispatched` im Paketscan, `Out for Delivery` in der Fortschrittsleiste). | „In Zustellung“ |
| `Delivered` | Zugestellt (auf einem Rücksendeweg: zurück beim Verkäufer eingetroffen). | „Zugestellt“ (bzw. „Zurück an Absender“) |
| `Undelivered` | Nicht zugestellt und auf dem Rückweg zum Händler; bei Frachtsendungen benennt Delhivery den Schritt `On the Way` unter Beibehaltung seines Datums so um – das Datum ist also kein Zustellversuch. | „Problem gemeldet“ |
| `REACHED_SEL_CITY` | Eine Rücksendung hat die Stadt des Verkäufers erreicht. | „Unterwegs“ |
| `RTO`, `DELIVERED_SELLER` | *Return to Origin*: Das unzustellbare Paket ist wieder beim Verkäufer angekommen. | „Zurück an Absender“ |
| `DTO` | Eine von dir aufgegebene Kundenretoure hat den Händler erreicht. | „Zurück an Absender“ |
| `LOST` | Delhivery hat die Sendung als verloren markiert. | „Problem gemeldet“ |
| `CANCELLED`, `Canceled` | Sendung oder Rückholauftrag wurde storniert. | „Problem gemeldet“ |

Mehr zu den allgemeinen Versandphasen liest du unter [Tracking-Status verstehen](guide:tracking-statuses).

## Zustellung in Indien: Anrufe, Fehlversuche und Adresse

**Netzwerk.** Delhivery betreibt `50` automatisierte Sortierzentren, `123` Gateways und über `4.500` direkte Zustellzentren in allen indischen Bundesstaaten und deckt rund um die Uhr `99,5 %` aller PIN-Codes ab, einschließlich Nordostindien sowie Jammu und Kaschmir.

**Anrufe und WhatsApp.** Laut Nutzungsbedingungen dürfen Delhivery und seine Zustellpartner dich per Telefon, SMS oder WhatsApp kontaktieren (Gespräche können aufgezeichnet werden), um die Übergabe abzustimmen. Für Online-Shops bietet Delhivery `Smart NDR` mit KI-Anrufen und WhatsApp-Bestätigungen für neue Zustellversuche an – fragt zur Adressbestätigung aber **niemals** nach einem `OTP` oder Zugangsdaten.

**Verpasste Zustellung und Direct-Regeln.** Beim Privatkundenservice *Delhivery Direct* (bis `50 kg` Abrechnungsgewicht, im Express-Modus maximal `10 kg` und `50 cm` Kantenlänge, keine Postfächer) bricht Delhivery die Zustellung nach **3 erfolglosen Versuchen** ohne Kostenerstattung ab (Rücksendung auf Kosten des Absenders möglich). Nachbarschaftsabgabe, Briefkasten-/Paketfachzustellung oder Abholung an einem genehmigten Ort sind mit Zustimmung von Delhivery möglich, ein Wechsel des PIN-Codes kann jedoch abgelehnt werden ([Zustellung verpasst](guide:missed-delivery)). Bei Shop-Bestellungen gelten die Vereinbarungen des Händlers.

## Internationale Sendungen aus Indien und Zoll

**Laufzeiten und Partner.** Mit **DLV Saver** schätzt Delhivery `5–7` Tage nach Großbritannien und `8–12` Tage in die USA (verfügbar für USA, UK, Kanada und Australien); **DLV Premium** bedient über `220` Länder und Gebiete in `3–6` Tagen (*Express*) bzw. `6–10` Tagen (*Deferred Express*) ab `50 g`. Als strategische Auslandspartner nennt Delhivery unter anderem [FedEx](carrier:fedex) und Aramex; teilt der Shop eine Anschlussnummer für die letzte Meile mit, füge auch diese in Peek ein.

**Zoll in Deutschland, Österreich, Luxemburg und der Schweiz.** In der **Europäischen Union** fällt für online außerhalb der EU gekaufte Waren in Sendungen bis `150 €` seit 1. Juli 2026 ein vorläufiger Zoll von `3 €` je Warenposition an (gleiche Zolltarifnummer, Beschreibung und ggf. gleicher Ursprung; über `150 €` regulärer Zollsatz) sowie seit 1. November 2026 unabhängig vom Wert eine EU-Bearbeitungsgebühr von `2 €` je Warenposition, zuzüglich Einfuhrumsatzsteuer in **Deutschland** (ausgenommen die Insel Helgoland und das Gebiet von Büsingen, die außerhalb des EU-Zoll- und Mehrwertsteuergebiets liegen), **Österreich** und **Luxemburg**, sofern die Mehrwertsteuer nicht bereits beim Kauf über IOSS erhoben wurde. In der **Schweiz** (Nicht-EU) erhebt das BAZG `8,1 %` Einfuhrsteuer (`2,6 %` ermäßigter Satz), wobei Zollabgaben und Steuerbeträge bis `5 Schweizerfranken` jeweils nicht erhoben werden ([Paket hängt beim Zoll](guide:customs)).

## Tracking steht still, Paket verloren, beschädigt oder zurückgeschickt

Für Erstattung oder Ersatzlieferung bei Online-Käufen ist immer der Shop dein erster Ansprechpartner.

- **Kein Fortschritt:** Steht das Paket auf „Etikett erstellt“ (`WAITING_PICKUP`), hat der Händler es noch nicht übergeben. Stockt es unterwegs, melde dich mit deiner Mobilnummer auf `delhivery.com/support` oder in der App an und eröffne ein Ticket ([Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating)).
- **Verlust, Schaden oder Falschzustellung:** Bei *Delhivery Direct* müssen Ansprüche binnen **3 Tagen** per App-Ticket gemeldet werden (bei beschädigt gelieferten Delhivery-Versandkartons noch am selben Kalendertag), bei Transportschäden mit **3 Fotos** von Außenverpackung und Inhalt samt Beschreibung (Auspackvideo empfohlen). Die Bearbeitung dauert **30 bis 45 Werktage**; ohne kostenpflichtigen *Delhivery Protect*-Schutz (Pflicht ab `10.000 ₹` bis zur Direct-Obergrenze von `49.999 ₹`; im E-Commerce bis `30.000 ₹` pro AWB) haftet Delhivery höchstens bis **`2.000 ₹`** je nach Warenkategorie (`500 ₹` Bücher/Dokumente/Sonstiges, `700 ₹` Kleidung, `800 ₹` Haushalt, `1.000 ₹` Elektronik oder Mischpakete, `2.000 ₹` Sportartikel, `400 ₹` Verbrauchsgüter), niemals für Verspätungen ([Paket verloren](guide:lost-parcel), [Beschädigtes Paket](guide:damaged-parcel), [Zugestellt, aber nicht erhalten](guide:delivered-not-received)).
- **Rücksendung (`RTO`, `DELIVERED_SELLER`):** Die Sendung ging zurück an den Verkäufer – bitte den Shop um Rückerstattung oder Neuversand ([Zurück an den Absender](guide:returned-to-sender)).

## Delhivery-Kundenservice und Betrugswarnungen

- **Support nur über Website oder App:** Laut Delhivery ist der Kundendienst **ausschließlich** über die [Support-Seite](https://www.delhivery.com/support) (Login per Telefonnummer und `OTP`, dann `Raise a Query`; Themen u. a. `Report Issues`, `Grievance Officer`, `Report on-field staff issues`, `Returns`) oder die *Delhivery Direct*-App erreichbar.
- **Keine öffentliche Support-Rufnummer:** Delhivery warnt ausdrücklich, dass im Internet kursierende Kundenservice-Nummern betrügerisch sein können. Offizielle E-Mails stammen nur von `@delhivery.com`, offizielle Social-Media-Hilfe nur von den verifizierten Konten auf Facebook, X und Instagram.
- **Phishing-Warnung:** Gefälschte SMS behaupten fehlgeschlagene Zustellversuche und führen auf Tippfehler-Domains (wie `delhiveryu.top` oder `delhiveryma.top`); Delhivery verlangt niemals `PIN` oder `OTP` für Zahlungen und weder `OTP` noch Zugangsdaten für Adressbestätigungen. In Indien: Bei finanziellem Schaden Notruf `1930` wählen oder auf `cybercrime.gov.in` melden (Spam-Anrufe/SMS binnen 3 Tagen auf `sancharsaathi.gov.in`); siehe auch [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## Delhivery-Pakete mit Peek verfolgen

Füge die 13- oder 14-stellige AWB in [Peek](/) ein: Wegen der geteilten Nummernform zeigt Peek „Tracking-Dienste werden geprüft…“ und fragt Delhivery automatisch an, wenn du aus Indien zugreifst und die Nummer mit `1`–`9` beginnt sowie auf `0`–`6` endet („Delhivery hat dieses Paket.“). Außerhalb Indiens – oder bei Nummern mit `0` am Anfang bzw. `7`, `8` oder `9` am Ende – wähle Delhivery über „Ändern“. Bei einer anderen Länge meldet Peek nach manueller Auswahl, dass Delhivery „vergibt keine Nummern wie diese“.

Vor dem ersten Scan erscheint „Warten auf Delhivery“. Peek liest den öffentlichen Tracking-Feed ohne Konto aus: alle erreichten Scans mit Stadt (ohne künftige Schritte der Fortschrittsleiste und ohne Empfängerdaten), ergänzt bei Fracht-Meilensteinen ohne Jahreszahl das Jahr anhand des Wochentags, liest Zeiten als indische Zeit (`Asia/Kolkata`, `UTC+5:30`) und zeigt sie in deiner Gerätezeitzone („Uhrzeiten in deiner Zeitzone: …“), ohne Lieferdatumsschätzung. Eine an den Händler zurückgelieferte Sendung steht auf „Zurück an Absender“ statt „Zugestellt“.

Bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen prüft Peek bis zu alle 10 Minuten, und ab `06:00` Uhr indischer Zeit am Tag des Ausgangsscans „In Zustellung“ bis zu alle 2 Minuten, solange Delhiverys Tracker antwortet; sonst sowie nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich („Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“). Delhivery oder den Händler kontaktieren kann Peek nicht.

## Häufige Fragen zur Delhivery-Sendungsverfolgung

### Wie verfolge ich ein Delhivery-Paket?

Gib die 13- oder 14-stellige AWB oben in Peek ein (außerhalb Indiens über „Ändern“ Delhivery wählen) oder suche auf `delhivery.com/tracking` per AWB, `Order Id`, `LRN` oder Mobilnummer mit `OTP`.

### Was ist die AWB-Nummer bei Delhivery?

Die *airway bill number* – Delhiverys 13- oder 14-stellige Sendungsnummer aus der Versand-SMS oder E-Mail des Shops.

### Was bedeutet `RTO` im Delhivery-Tracking?

*Return to Origin*: Das Paket ging zurück und wurde wieder beim Verkäufer abgeliefert. Bitte den Shop um Rückerstattung oder erneuten Versand.

### Wie lautet die Telefonnummer des Delhivery-Kundenservice?

Delhivery nennt keine telefonische Kundenhotline: Der Support ist ausschließlich nach Login mit deiner Telefonnummer auf `delhivery.com/support` oder in der Delhivery-App erreichbar; anderweitig im Netz gefundene Nummern können betrügerisch sein.

:::sources
- [Delhivery: Track your order](https://www.delhivery.com/tracking) – Suche per Mobilnummer, AWB, Order Id oder LRN sowie Betrugshinweise
- [Delhivery: Support](https://www.delhivery.com/support) – OTP-Login, Ticket-Eröffnung und Themen inklusive Grievance Officer
- [Delhivery: About Us](https://www.delhivery.com/about-us) – Gründung 2011, über 18.850 PIN-Codes, 50 Sortierzentren und über 4.500 Zustellzentren 24/7
- [Delhivery: Startseite](https://www.delhivery.com/) – über 220 Länder und Gebiete sowie strategische Partner FedEx und Aramex
- [Delhivery: Express Parcel](https://www.delhivery.com/services/express-parcel) – 99,5 % PIN-Code-Abdeckung, Smart NDR per WhatsApp/KI-Anruf und E-Commerce-Schutz
- [Delhivery: International](https://www.delhivery.com/services/international) – DLV Saver und DLV Premium ab 50 g samt Laufzeiten
- [Delhivery: Personal Courier](https://www.delhivery.com/solutions/personal-courier) – Privatversand Delhivery Direct
- [Delhivery: Terms and Conditions](https://www.delhivery.com/terms-and-conditions) – Anrufe, SMS und WhatsApp zur Lieferabstimmung
- [Delhivery: TnC (Direct National Courier)](https://www.delhivery.com/tnc-direct) – Gewichtsgrenzen, 3 Zustellversuche, 3-Tage-Reklamationsfrist mit 3 Fotos, 30–45 Werktage Bearbeitung und Haftungsgrenzen bis 2.000 ₹ / Delhivery Protect
- [Delhivery: Fraud Disclaimer](https://www.delhivery.com/fraud-disclaimer) – offizielle @delhivery.com-Kanäle, Phishing-SMS, kein Zahlungs-OTP, Notruf 1930 und cybercrime.gov.in
- [Europäische Kommission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3-€-Zoll bis 150 € seit Juli 2026 und 2-€-Bearbeitungsgebühr seit November 2026
- [Europäische Kommission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Positionsbegriff, 2 € unabhängig vom Wert und IOSS-Mehrwertsteuerregelung
- [Europäische Kommission: Territorialer Geltungsbereich von MwSt. und Zoll](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Helgoland und Büsingen außerhalb des EU-Zoll- und Mehrwertsteuergebiets
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Schweizerfranken
- [Universal Parcel Scraper: Delhivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delhivery) – 13- und 14-stellige AWBs, 17 Status und Codes, Fracht-Meilensteine, Retourenlogik und Zeitzone Asia/Kolkata
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – andere 13- und 14-stellige Paketdienste
:::
