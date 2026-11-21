---
title: GlobalPost Sendungsverfolgung: GP-Nummern, Status & Zoll
description: Verfolge dein GlobalPost-Paket: Aufbau von GP- und SDC-Nummern, Bedeutung der englischen Statusmeldungen, Tarife, Zoll und Zustellung mit Peek.
slug: globalpost-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

GlobalPost gehört zu Auctane und ist ein internationaler Versandservice, den Online-Händler über Plattformen wie ShipStation und Stamps.com buchen (über 220 belieferte Länder und mehr als 60 Millionen zugestellte Sendungen laut Website). Das Paket geht zuerst an ein Versandzentrum von GlobalPost, wo das internationale Etikett und die Zollpapiere angebracht werden; anschließend übernehmen Post- und Logistikpartner den Weitertransport, bis die Post oder ein Paketdienst im Zielland zustellt. Der Verkäufer gibt dir die Sendungsnummer, die meist mit `GP` beginnt: Füge sie oben ins Suchfeld ein. Den eigenen Tracker findest du unter `goglobalpost.com/tracking`; in Peeks Katalog lässt sich GlobalPost auch unter `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` oder `GlobalPost UK` auswählen.

## GlobalPost-Sendungsnummer: Aufbau und Beispiele

Peek erkennt aktuelle GlobalPost-Nummern am Format: `GP`, vier Buchstaben für die Versandart, zehn Ziffern sowie `US` oder `GB` für das Abgangsland. Mit erfundenen Zeichen:

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `GPECON4829105736US` | `GP`, vier Buchstaben, zehn Ziffern und `US`: ein aus den Vereinigten Staaten verschicktes GlobalPost-Paket. |
| `GPSTND4829105736GB` | Dasselbe Muster mit `GB` am Ende: Versand aus dem Vereinigten Königreich. |
| `SDC482910573649182` | `SDC` und 15 Ziffern: ein älteres GlobalPost-Etikett. Allein am Format erkennt Peek keinen Paketdienst – wähle GlobalPost über „Ändern“. |
| `GPECON482910573US` | Nur neun Ziffern: Peek erkennt keinen Paketdienst. Prüfe, ob eine Ziffer fehlt. |
| `LZ482910576US` | Eine US-Postnummer, kein GlobalPost-Muster: Peek nennt [USPS](carrier:usps). Wählst du GlobalPost manuell dafür aus, meldet Peek, dass der Dienst „vergibt keine Nummern wie diese“. |

Leerzeichen, Bindestriche und Kleinschreibung ignoriert Peek.

**Wo du die Nummer findest.** In der Versandbestätigung des Händlers oder in deiner Bestellübersicht. Fehlt sie dort, helfen [Sendungsnummer finden](guide:find-tracking-number) und [Formate von Sendungsnummern](guide:tracking-number-formats).

**Zweite Sendungsnummer.** Unter `Final Mile Tracking By:` kann der GlobalPost-Tracker die eigene Sendungsnummer des Zustellunternehmens anzeigen und öffnet die Sendung auch direkt über diese Nummer (Peek fragt GlobalPost selbst nur mit `GP`- oder `SDC`-Nummern ab).

## GlobalPost-Tracking-Status erklärt

Der Tracker von GlobalPost listet die Scans absteigend unter Tagesüberschriften auf – jeweils mit Uhrzeit, englischem Wortlaut und meist einem Ort – und gibt Partnerscans in eigenen Worten wieder. Typischer Ablauf:

| GlobalPost meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Label Created` | Der Händler hat das Versandetikett erzeugt; das Paket ist noch nicht unterwegs. | „Etikett erstellt“ |
| `Data Received` | GlobalPost hat die elektronischen Sendungsdaten vom Händler erhalten, noch nicht das Paket. | „Etikett erstellt“ |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Ein Partner hat gescannt: etwa USPS auf dem Weg zu GlobalPost oder der Postdienst im Zielland. | „Unterwegs“ |
| `Arrived at GlobalPost Facility` | Das Paket ist in einem Versandzentrum von GlobalPost eingetroffen (USPS-Scans auf dem Hinweg können davor stehen). | „Beim Paketdienst eingegangen“ |
| `Departed GlobalPost Facility` | Es hat das GlobalPost-Zentrum verlassen. | „Unterwegs“ |
| `Departed from Origin Country` | Es hat die USA oder Großbritannien verlassen. | „Unterwegs“ |
| `In Transit`, `In-Transit` | Unterwegs. | „Unterwegs“ |
| `Arrived in Destination Country` | Im Zielland eingetroffen. | „Unterwegs“ |
| `In Customs` | Beim Einfuhrzoll im Zielland. | „Beim Zoll“ |
| `Cleared Customs` | Vom Zoll freigegeben; der Weitertransport läuft. | „Unterwegs“ |
| `Out for Delivery` | Heute auf der Zustelltour. | „In Zustellung“ |
| `Attempted Delivery` | Zustellversuch fehlgeschlagen; niemand konnte annehmen. | „Zustellversuch“ |
| `Exception, No Action Required` | Störung bei der Zustellung im Zielland, bei der du nichts unternehmen musst. | „Problem gemeldet“ |
| `Delayed, No Action Required` | Verzögerung ohne Handlungsbedarf auf deiner Seite. | „Problem gemeldet“ |
| `Delivered` | Zugestellt (oder zurück beim Absender – siehe unten). | „Zugestellt“ |

Eine Zeile kann mit dem Hinweis von GlobalPost enden, dass Tracking-Updates Zeit brauchen können: Peek wertet den Statuswortlaut davor aus und behält den ganzen Text; ein nachträglicher Registrierungseintrag stuft ein bereits bewegtes Paket nicht zurück. Andere Texte liest Peek nach seinen allgemeinen Wortlaut-Regeln.

**Rücksendungen.** GlobalPost beendet auch eine Rücksendung mit `Delivered` und zeigt im Seitenkopf den Ort des Empfängers, während der Scan selbst den Ort des Absenders trägt. Stimmt der Scan-Ort mit dem Absenderort in einem anderen Land und einer anderen Stadt als beim Empfänger überein, zeigt Peek „Zurück an Absender“.

## Wer liefert GlobalPost-Pakete in Deutschland, Österreich und der Schweiz?

GlobalPost übergibt *Economy*- und *Standard*-Sendungen für den Auslandsweg an Postpartner wie USPS. Eine feste Zustellerliste nach Ländern nennt die Website nicht, wohl aber der Tracker samt lokaler Sendungsnummer.

**Was das Tracking zeigt, hängt vom gebuchten Tarif ab:**

| Tarif | Tracking-Umfang | Zoll & Steuern | Richtlaufzeit laut GlobalPost |
| --- | --- | --- | --- |
| `Economy` | bis ins Zielland und durch den Zoll | du zahlst (`DDU`) | `10 – 14 business days` |
| `Standard` | bis an die Haustür | du zahlst (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | bis an die Haustür | der Absender zahlt (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | bis an die Haustür | im Händlerpreis enthalten | `6 – 10 business days` |

Ab den USA ist *GlobalPost Plus – Duties & Taxes Included* unter anderem nach **Deutschland** und **Österreich** verfügbar, während *Flat Rate (formerly Go)* Kanada, Australien, Neuseeland, Hongkong und Mexiko abdeckt. Zustellversuche und Abholfristen richten sich nach dem lokalen Zusteller: Siehe [DHL](carrier:dhl), [Österreichische Post](carrier:austrian-post), [Schweizerische Post](carrier:swiss-post) oder [Canada Post](carrier:canada-post) sowie [Zustellung verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

**Zoll in Deutschland, Österreich, der EU und der Schweiz.** Sendungen aus den USA oder Großbritannien durchlaufen bei der Einfuhr in die EU oder die Schweiz den Zoll (**Nordirland** gilt laut Europäischer Kommission dagegen für Zoll und Mehrwertsteuer auf Waren als EU-Gebiet). Bei *Economy* und *Standard* (*Delivered Duties Unpaid*) trägt der Käufer Zölle und Einfuhrabgaben. In der **Europäischen Union** (**Deutschland** und **Österreich**) fällt seit dem 1. Juli 2026 für online außerhalb der EU gekaufte Waren in Sendungen mit einem Sachwert bis 150 € ein EU-Zollsatz von 3 € pro Position (*per item*) an; seit dem 1. November 2026 kommt unabhängig vom Wert eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position hinzu (eine Position bündelt Waren desselben Pakets mit gleicher Zolltarifnummer, Beschreibung und – falls anzugeben – gleichem Ursprung; über 150 € gelten reguläre Zollsätze), zuzüglich Einfuhrumsatzsteuer in **Deutschland** und **Österreich**, sofern sie nicht schon beim Kauf über IOSS berechnet wurde. In der **Schweiz** erhebt das Bundesamt für Zoll und Grenzsicherheit (BAZG) 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz), wobei „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden – zuzüglich möglicher Verzollungsgebühren des Zustellers. Wie die 2-€-Gebühr weiterbelastet wird, nennen GlobalPosts Tarifseiten nicht: Frag beim Verkäufer nach und lies [Paket hängt beim Zoll](guide:customs).

## GlobalPost-Paket hängt fest, fehlt oder geht zurück

**„Warten auf GlobalPost“.** Findet der Tracker unter der Nummer noch keine Angaben (`status=not_found`), zeigt Peek „Warten auf GlobalPost“ und prüft weiter. Kennt GlobalPost die Nummer auch nach zwei Tagen noch nicht und liefert kein Tracking-Dienst ein Ergebnis, meldet Peek: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ Steht tagelang nur `Label Created`, frag beim Händler nach, ob das Paket übergeben wurde.

**Tagelang kein neuer Scan.** GlobalPost weist darauf hin, dass Auslands-Updates Zeit brauchen und Wetter, Feiertage, Streiks oder der Zoll verzögern können; bei *Economy* endet die Verfolgung nach der Verzollung im Zielland. Nach vier Tagen ohne neuen Scan zeigt Peek den Tag des letzten Scans („Keine Aktualisierung seit Mo 12. Okt“) samt dem Hinweis auf Flüge, Zoll und Wochenenden; nach über drei Wochen ohne Neues rät der Hinweis stattdessen, beim Verkäufer nachzufragen. Eine eigene Hilfeseite von GlobalPost verlinkt Peek nicht; sobald Peek das Paket beim lokalen Zusteller verfolgt und dessen Hilfeseite kennt, erscheint deren Link. Siehe [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating).

**Nach 30 Tagen noch nicht da.** Ist das Paket 30 Tage nach Versand nicht eingetroffen, bittet GlobalPost darum, zuerst den Verkäufer zu kontaktieren: Der Händler kann binnen `90 days` ab Versanddatum bei GlobalPost reklamieren, und die Bearbeitung dauert bis zu `3 weeks`. Siehe [Paket verloren](guide:lost-parcel).

**Als zugestellt gemeldet oder unzustellbar / zurückgeschickt.** Fehlt ein als zugestellt markiertes Paket, frag erst beim benannten Zusteller und dann beim Händler nach ([Zugestellt, aber nicht erhalten](guide:delivered-not-received)). Bei falscher oder unvollständiger Adresse, offenen Zollabgaben, verpassten Zollbenachrichtigungen oder verbotenen Artikeln übernimmt die Zielpost die Abwicklung – meist geht das Paket zurück oder lagert bei der Post, ohne dass GlobalPost ein Rücksendedatum oder eine neue Nummer erhält. Unzustellbare Pakete (*undeliverable*) schließen GlobalPosts Bedingungen von Ersatzansprüchen aus; kläre Erstattung oder Ersatz mit dem Shop ([Zurück an den Absender](guide:returned-to-sender)).

## GlobalPost Kundenservice und Kontakt

Auf seiner Seite *Contact Support* nennt GlobalPost folgende Kontaktwege:

- **E-Mail**: `info@goglobalpost.com`.
- **Telefon**: `(888) 899-1255`, Montag bis Freitag von `6am` bis `6pm` *Pacific Time*.
- **Kontaktformular**: unter `www.goglobalpost.com/contact-support/` die Rolle `Recipient` auswählen und die Sendungsnummer angeben (GlobalPost betont, dass der Support dieselben Trackingdaten sieht wie die öffentliche Tracking-Seite).

Bei ausbleibenden Paketen verweist GlobalPost zuerst an den Verkäufer. Außerdem stellt GlobalPost klar, dass ein aus China mit [China Post](carrier:china-post) verschicktes Paket keine GlobalPost-Sendung ist – wende dich dafür an den Absender.

**Vorsicht vor Phishing-SMS.** Die offizielle Website von GlobalPost lautet `www.goglobalpost.com` (`goglobalpost.com`). Prüfe verdächtige Nachrichten vor dem Antippen anhand von [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## GlobalPost-Pakete mit Peek verfolgen

[Peek](/) erkennt eine `GP`-Nummer sofort („Erkannter Paketdienst“) und fragt den GlobalPost-Tracker ohne Konto oder Postleitzahl ab (gefundene Seiten speichert die Website einige Minuten im Cache). Ein Link von `goglobalpost.com/track-detail/?t=` oder `/track/?t=` klappt ebenso; für eine ältere `SDC`-Nummer wählst du GlobalPost über „Ändern“. Hast du nur die Nummer des Endzustellers, füge sie trotzdem ein – Peek unterstützt über 4.500 Paketdienste.

Peek zeigt den Status, jeden Scan mit seinem Ort (ohne Postleitzahlen), das Zielland sowie den benannten Endzusteller samt Nummer. Fortschrittsbalken, Absender- und Empfängerort aus dem Seitenkopf (nur zur Rücksenderkennung ausgewertet) und Postleitzahlen lässt Peek weg; Lieferdatum, Gewicht oder Zustellnachweis liefert GlobalPost nicht. „Website von GlobalPost öffnen“ öffnet deine Nummer auf `goglobalpost.com`.

Die Zeitangaben von GlobalPost tragen keine Zeitzone: Peek liest jeden Scan auf der Uhr seines Orts oder des Abschnitts, auf dem das Paket gerade war, und zeigt ihn in der Zeitzone deines Geräts; auf `peektracker.com` sowie in der iPhone- und Android-App endet der Verlauf mit „Uhrzeiten in deiner Zeitzone: …“.

Nennt GlobalPost einen Zusteller, den Peek beim Namen kennt, steht auf der Karte „Zustellung durch …“ samt dessen Nummer hinter dem `GP`-Code – oder „Bei … angekündigt, noch nicht eingetroffen“, solange dort noch kein eigener Scan vorliegt. Liegt eine Zweitnummer ohne bekannten Partnernamen vor, fragt Peek bei einer gültigen Postnummer die Landespost des Ziellands ([DHL](carrier:dhl) in Deutschland, [Österreichische Post](carrier:austrian-post) in Österreich, [Schweizerische Post](carrier:swiss-post) in der Schweiz) oder den einzelnen Paketdienst ab, auf den das Nummernformat verweist. Sobald dort ein mindestens ebenso neuer Scan wie der letzte von GlobalPost erscheint, wechselt Peek dorthin und behält die Vorgeschichte von GlobalPost.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiv sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ bis zu alle 2 Minuten (alle 5 Minuten, wenn Peek den GlobalPost-Tracker im Browser öffnen muss), solange der eigene Tracker antwortet (nach Übergabe an den Endzusteller folgt Peek dessen Takt; über Tracking-Dienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und bis zu alle 15 Minuten am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen). Sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. GlobalPost oder den Verkäufer kontaktieren kann Peek nicht.

## Häufige Fragen zur GlobalPost-Sendungsverfolgung

### Warum stoppt mein GlobalPost-Tracking nach dem Zoll?

Beim Tarif *GlobalPost Economy* endet die Sendungsverfolgung, sobald das Paket das Zielland erreicht und den Zoll passiert hat; laut GlobalPost bieten manche Sendungen vor Ort nur eingeschränktes Tracking. Das Paket ist trotzdem weiter unterwegs – steht unter `Final Mile Tracking By:` eine lokale Nummer, fragt Peek diesen Dienst ab, und du kannst sie ebenfalls prüfen.

### Wer stellt GlobalPost-Pakete zu?

Eine Postgesellschaft oder ein Paketdienst im Zielland, den GlobalPosts Tracker samt eigener Sendungsnummer nennen kann. Ist nur eine internationale Postnummer ohne erkannten Partnernamen hinterlegt, fragt Peek die Post des Ziellands ab (DHL in Deutschland, Österreichische Post in Österreich, Schweizerische Post in der Schweiz).

### Wie lange dauert der Versand mit GlobalPost?

Die FAQ nennt `5-6 days` in nahe gelegene Länder und bis zu `20 days` bei weiter entfernten Zielen; Händlern nennt GlobalPost als Schätzwert ab Bearbeitung im Versandzentrum `10 – 14 business days` für *Economy*, `6 – 10 business days` für *Standard* und *Flat Rate* sowie `5 – 8 business days` für *Plus*.

### Ist GlobalPost seriös?

Ja. GlobalPost ist Teil von Auctane, wird über Versandplattformen wie ShipStation und Stamps.com vertrieben, liefert in über 220 Länder und gibt mehr als 60 Millionen zugestellte Sendungen an.

### Wie erreiche ich GlobalPost?

Per E-Mail an `info@goglobalpost.com`, telefonisch unter `(888) 899-1255` werktags von `6am` bis `6pm` *Pacific Time* oder über das Formular auf `goglobalpost.com/contact-support/` mit der Auswahl `Recipient`. Ist ein Paket 30 Tage nach Versand noch nicht da, bittet GlobalPost darum, zuerst den Verkäufer anzusprechen.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, Ablauf, Länderliste für Plus – Duties & Taxes Included und Flat Rate, Economy-Tracking bis nach dem Zoll, Laufzeiten, Rücksendungen und 90-Tage-/3-Wochen-Reklamation
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – Tarifvergleich Economy, Standard, Plus und Flat Rate: Tracking, DDU/DDP und Werktage
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – E-Mail, Telefon, Pacific-Time-Zeiten, Auswahl Recipient im Formular und Hinweis zu China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – über 220 Länder und mehr als 60 Millionen zugestellte Sendungen
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – offizieller Tracker und Nicht-gefunden-Hinweis
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – Versandzentren, Laufzeiten als Schätzung und Ausschluss unzustellbarer Pakete
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € unabhängig vom Wert, Positionsbegriff und IOSS-Einfuhrsteuerbefreiung
- [Europäische Kommission: Räumlicher Geltungsbereich für MwSt. und Zoll](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Nordirland
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – GP- und SDC-Nummern, Statuszeilen, Rücksenderkennung, Zeitzonen, Seiten-Cache und Endzusteller
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – USPS-Muster und Übergabe an den Zusteller oder die Landespost
:::
