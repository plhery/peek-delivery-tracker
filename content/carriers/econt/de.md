---
title: Econt Sendungsverfolgung: Nummer, Status und Büros
description: Econt-Paket in Bulgarien verfolgen: 13-stellige Frachtbriefnummer finden, englische und bulgarische Statusmeldungen, Büros, Econtomat und Kontakt.
slug: econt-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

Econt Express (*Econt Express AD*) ist ein 1993 gegründeter bulgarischer Paketdienst, der an die Haustür, an Econt-Büros und an Econtomat-Paketautomaten zustellt. Verfolge deine Sendung unter `Track a shipment` auf [econt.com](https://www.econt.com/en/services/track-shipment), über den Link aus Econts E-Mail oder SMS oder direkt durch Einfügen der Sendungsnummer oben.

## Econt-Sendungsnummer: Format und Fundort

Bei Econt heißt sie Sendungsnummer (*shipment number*) oder Frachtbriefnummer (*waybill number*). E-Mail-Benachrichtigungen (kostenlos) und SMS-Benachrichtigungen (kostenpflichtige Zusatzleistung für `0,11 €` inkl. MwSt., die der Absender buchen muss und daher nicht bei jedem Paket aktiviert ist) enthalten einen Tracking-Link sowie Angaben zu Ort, Lieferzeit und einem etwaigen Zahlbetrag. Keine Nachricht erhalten? Sieh in der Versandmail des Shops nach oder lies, [wo du die Sendungsnummer sonst findest](guide:find-tracking-number).

Econt veröffentlicht keine formale Aufbauregel, doch die Beispiel-Frachtbriefnummern im englischen XML-Integrationshandbuch haben **13 Ziffern**, und Econts Tracking-Dienst liest sie als positive 13-stellige Ganzzahl ohne führende `0`, etwa wie die frei erfundene Nummer `1048291057364`.

Dreizehn Ziffern nutzen auch andere Paketdienste – darunter DPD France, Ukrposhta in der Ukraine, FAN Courier in Rumänien oder Delhivery in Indien –, prüfe also in der Versandnachricht, welcher Dienst genannt wird. Wählst du Econt manuell für eine Nummer mit führender `0` oder einer anderen Länge, meldet Peek, dass Econt „vergibt keine Nummern wie diese“.

## Was die Econt-Tracking-Status bedeuten

`econt.com` startet auf Bulgarisch (`Проследи пратка`) und lässt sich auf Englisch (`Track a shipment`) umstellen. Das sind Econts englische Scanbezeichnungen in Peek, in typischer Reihenfolge:

| Econt-Status | Bedeutung für dein Paket | Peek zeigt |
| --- | --- | --- |
| `Awaiting delivery to Econt` | Der Absender hat [das Etikett erstellt](guide:tracking-statuses), das Paket aber noch nicht an Econt übergeben. | „Etikett erstellt“ |
| `Submitted to courier` | Ganz am Anfang: Ein Kurier hat das Paket beim Absender abgeholt („Beim Paketdienst eingegangen“). Nach einem Büro- oder Linienscan: Das Paket ist auf Zustelltour („In Zustellung“). | „Beim Paketdienst eingegangen“ / „In Zustellung“ |
| `Arrival at an office` | Ankunft im genannten Econt-Büro oder Logistikzentrum (auch deinem Abholbüro). | „Unterwegs“ (oder „Abholbereit“) |
| `Received by line-haul` | Unterwegs auf einer Fernlinie zwischen Zentren; dahinter steht die Strecke von Ort zu Ort. | „Unterwegs“ |
| `Change delivery terms` | Lieferanweisung geändert (z. B. Abholbüro oder Tag); der Betreff folgt nach `Change delivery terms:`, das Paket hat sich nicht bewegt. | *(Hinweis ohne Stufe)* |
| `Shipment forwarding` | Weiterleitung an einen anderen Ort oder eine neue Adresse. | „Unterwegs“ |
| `Failed delivery` | Zustellversuch fehlgeschlagen (Grund in Klammern); Rücknahme in das genannte Econt-Büro. | „Zustellversuch“ |
| `Delivered` | Zugestellt oder abgeholt (mit dem Namen des Empfängers hinter `Delivered:`, sofern Econt ihn angibt). | „Zugestellt“ |
| `Delivery return` | Rücksendung an den Absender. | „Zurück an Absender“ |
| `Shipment destruction` | Vernichtung der Sendung vorgesehen; wende dich sofort an den Händler oder an Econt. | „Problem gemeldet“ |
| `Information` | Reine Infonotiz ohne neuen Transportschritt. | *(Hinweis ohne Stufe)* |
| `Return receipt: Delivered` | Betrifft den unterschriebenen Rückschein auf dem Weg zurück zum Absender, nicht dein Paket. | *(Rückschein, ohne Stufe)* |

Achte besonders auf `Submitted to courier`: Vor jedem Büro-, Linien-, Weiterleitungs- oder Zustellversuchsscan steht derselbe Wortlaut für die Abholung beim Absender, danach für die letzte Meile zu dir.

Zusätzlich liefert Econt einen **bulgarischen Gesamtstatus**, nach dem Peek die aktuelle Stufe setzt, sobald er eindeutig ist: `Очаква предаване към Еконт` und `Подготвена в eEcont` („Etikett erstellt“), `Приета в Еконт` und `Приета в офис` („Beim Paketdienst eingegangen“), `Пътува по линия` („Unterwegs“), `В офис` und `Пристигнала в офис` („Abholbereit“, sobald Econt das lagernde Büro nennt), `Доставена` („Zugestellt“), `Върната` und `Върната и доставена към подател` („Zurück an Absender“) sowie `Връща се към подател`, `Анулирана след изпращане` und `Анулирана преди изпращане` („Problem gemeldet“). Steht dort `В куриер` (beim Kurier), entscheidet der jüngste Scan zwischen Abholung und Zustellung.

## Zustellung, Econt-Büros und Econtomat-Paketautomaten

**An der Haustür.** In Orten mit eigenem Econt-Büro stellt Econt Standardpakete (bis `50 kg` und `90 x 90 x 90 cm`, Dokumente bis `0,5 kg` A4, unpalettierte Sendungen von `50` bis `1.000 kg`) am nächsten Werktag zu – innerorts bis `18:00` Uhr –, in Orten ohne Econt-Büro binnen **3 Werktagen** nach Tourenplan. Möglich sind kostenlose Lieferzeitfenster (`Delivery Time Slots`), feste Uhrzeiten (`1,88 €` bis `6,50 €`) sowie Samstagszustellung (`1,88 €`), jeweils inkl. MwSt.

**Im Econt-Büro.** Eine für den nächsten Werktag aufgegebene Bürosendung liegt frühmorgens mit Öffnung des Büros bereit. Ab dem **8. Lagertag** (nach dem 7. Tag) berechnet Econt **`3,78 €` Lagergebühr pro Tag und Sendung** (inkl. MwSt.).

**Am Econtomat (rund um die Uhr geöffnet).** Die Automaten fassen Pakete bis **`61 x 44 x 37 cm`** und **`30,5 kg`** (drei Fachhöhen: `8 cm`, `18 cm`, `37 cm`); die Laufzeit ab Econtomat beträgt **1–2 Werktage** je nach Einlieferungszeit. Zum Abholen bezahlst du offene Beträge vorab in der Econt-App per Bankkarte oder digitaler Geldbörse `ecoints`, tippst am Automaten auf `Receive a shipment`, scannst den QR-Code oder gibst `PIN 1` und `PIN 2` aus App oder SMS ein, entnimmst das Paket und schließt das Fach.

**Lieferung ändern und Ware prüfen.** Empfänger, Ort (andere Adresse, anderes Büro, Abgabe bei Freund oder Nachbar, Weiterleitung in eine andere Stadt) und Lieferzeit lassen sich jederzeit telefonisch, über den Link in der E-Mail-Benachrichtigung oder im `e-Econt`-Profil anpassen ([Lieferadresse ändern](guide:change-delivery-address)). Zudem enthält Econts Preisliste drei kostenlose Prüfoptionen, die der Händler freischalten kann: `Review` (Ansicht), `Review and Test` (Ansicht und Test) sowie `Review, Test and Select` (Ansicht, Test und Auswahl).

## Econt-Paket verspätet, beschädigt oder verschwunden

Rechne in Bulgarien mit dem nächsten Werktag in Orten mit Econt-Büro, bis zu 3 Werktagen ohne Büro und 1–2 Werktagen ab Econtomat. Tut sich tagelang nichts, hilft unser Ratgeber, wenn [die Sendungsverfolgung stillsteht](guide:tracking-not-updating); frag danach beim Shop oder bei Econt nach.

Bei Beschädigung oder Verlust ([beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel)) reichst du über `I want to file a claim` im Econt-Kundenzentrum eine Reklamation ein und informierst den Verkäufer.

## Econt-Kundenservice und Kontakt

Auf der [Kundenzentrums-Seite](https://www.econt.com/en/client-center) nennt Econt folgende Wege:

- **Aus Bulgarien und Griechenland:** `0 7001 7300` (zum Preis eines Ortsgesprächs oder gemäß deinem Mobilfunktarif).
- **Aus allen anderen Ländern:** `+359 88 565 43 21` (gemäß deinem Telefontarif).
- **Online:** virtueller Assistent `Ask Ena`, Büro- und Öffnungszeitensuche (`I want to find offices and working time`) sowie Schadensmeldung (`I want to file a claim`).

Verlangt eine verdächtige SMS Zahlungen außerhalb der offiziellen Econt-App oder `econt.com`, gleiche sie mit unserem Leitfaden zu [Paket-Phishing-SMS](guide:delivery-scam-texts) ab.

## Econt-Pakete mit Peek verfolgen

Weil 13 Ziffern zu mehreren Diensten passen, fragt [Peek](/) Econt bei einer reinen Nummer nur dann zuerst, wenn du sie auf der Website aus Bulgarien oder in der App mit Geräteregion Bulgarien eingibst. Außerhalb Bulgariens fragt Peek Econt zuletzt, wenn weder die anderen Dienste noch die Tracking-Dienste die Nummer kennen. Du kannst Econt auch über „Ändern“ auswählen – oder einen `econt.com`-Link mit `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` oder `/tracking/?num=` einfügen, der Econt sofort benennt (solange Econt die Sendung noch nicht kennt oder alte Verläufe gelöscht sind, zeigt Peek „Warten auf Econt“ und bittet dich nach zwei Tagen, die Nummer zu prüfen oder einen anderen Paketdienst zu wählen).

Peek liest Econts Tracking-Dienst ohne Konto oder Postleitzahl, zeigt den Status, verortet Econt-Büros auf der Karte, nennt bei „Abholbereit“ das lagernde Büro und behält bis zu die 100 neuesten Scans. Für die Person, die das Paket hinzugefügt hat, bleiben Empfängername und Unterzeichner nach `Delivered:` sichtbar (auf geteilten Links ausgeblendet), während Paketanzahl, Kuriername, Absender und Auftragsnummern entfallen; Lieferprognose, Gewicht oder Zustellfoto liefert Econt nicht.

Uhrzeiten werden als Ortszeit in Sofia (`Europe/Sofia`, `UTC+2` im Winter, `UTC+3` im Sommer) gelesen und in deine Gerätezeitzone umgerechnet („Uhrzeiten in deiner Zeitzone: …“). Bei geöffnetem Paket oder am Liefertag mit aktiven Mitteilungen prüft Peek bis zu alle 10 Minuten, und ab `06:00` Uhr Sofioter Zeit am Tag des Ausgangsscans „In Zustellung“ bis zu alle 2 Minuten, solange Econts Tracker antwortet (über Tracking-Dienste: stündlich, alle 3 Stunden nach einem ruhigen Tag und am Liefertag bis zu alle 15 Minuten bei geöffnetem Paket oder aktiven Mitteilungen); sonst sowie nach 12 Stunden auf „Etikett erstellt“ / „Beim Paketdienst eingegangen“ oder zwei ruhigen Tagen stündlich („Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“). Lieferanweisungen ändern oder Reklamationen einreichen kann Peek nicht.

## Häufige Fragen zur Econt-Sendungsverfolgung

### Kann ich Econt ohne Bulgarischkenntnisse verfolgen?

Ja: Peek zeigt alle Econt-Scans mit den englischen Bezeichnungen aus der Tabelle oben und übersetzt den Paketstatus auf Deutsch. Auch auf `econt.com` gibt es eine englische Ansicht unter `Track a shipment`.

### Wie lange dauert der Versand mit Econt in Bulgarien?

Laut Econt am nächsten Werktag in Orten mit Econt-Büro (an Adressen innerorts bis `18:00` Uhr, im Büro ab Öffnung am Morgen), innerhalb von 3 Werktagen in Orten ohne Büro nach Fahrplan und 1–2 Werktage ab einem Econtomat.

### Wie verfolge ich ein Econt-Paket außerhalb Bulgariens anhand der 13-stelligen Nummer?

Füge die 13 Ziffern oben ein und wähle Econt über „Ändern“ aus (oder füge den vollständigen Link `econt.com/en/services/track-shipment/…` ein), da Peek eine bloße 13-stellige Zahl außerhalb Bulgariens erst zuletzt bei Econt prüft.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – englische Sendungsverfolgung auf econt.com
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – bulgarische Tracking-Seite
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, gegründet 1993 in Bulgarien
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bulgarien und Griechenland), +359 88 565 43 21, Ask Ena, Bürosuche und Reklamationen
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – Maße bis 50 kg und 90 x 90 x 90 cm, Laufzeiten, Zeitfenster, Samstag, 3,78 €/Tag Lagergebühr nach dem 7. Tag, SMS für 0,11 € und kostenlose Optionen Review / Test / Select
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – 24/7-Paketautomaten bis 61 x 44 x 37 cm und 30,5 kg, Vorauszahlung, QR-Code sowie PIN 1 und PIN 2
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – E-Mail- (kostenlos) und SMS-Benachrichtigung mit Tracking-Link
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – Formular für Nachbarabgabe, Büroauswahl, Weiterleitung und Terminänderung
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – 13-stellige Beispiel-Frachtbriefnummern
- [Universal Parcel Scraper: Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – 13-stellige Ganzzahlprüfung, Bulgarien-Erkennung, englische Scans und bulgarische Zusammenfassungen, Zeitzone Europe/Sofia, Unterzeichner und verworfene Felder
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – weitere 13-stellige Dienste und Tracking-Dienste als Rückfallweg
:::
