---
title: Nova Global Sendungsverfolgung: NPG- & NGUS-Codes
description: Nova-Global-Paket (Nova Poshta Global) verfolgen: NPG- und NGUS-Formate, Statuscodes, Zollabfertigung, lokale Übergabe und Kundenservice.
slug: nova-global-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Nova Global (rechtlich *LLC „Nova Poshta Global“* / *LLC NP GLOBAL*, Teil der Nova-Poshta-Gruppe) ist ein internationaler Express- und E-Commerce-Logistiker mit Büros in Kyjiw und New Jersey, der in über 160 Ländern und Gebieten tätig ist und weltweit 506.690 Abholpunkte anbindet. Über die Sparten *Global E-Commerce*, *Global Forwarding* (virtuelle Lieferadressen in 16 Ländern, darunter USA, Großbritannien und Italien), *Global Sellers* und *Global Freight* befördert das Unternehmen internationale Pakete von und nach Ukraine, USA, Kanada, EU und Asien. Verfolge eine Sendung auf der [Tracking-Seite von Nova Global](https://nova.global/track/) oder füge deine Nummer oben ein.

## Nova-Global-Sendungsnummern: NPG- und NGUS-Formate

Deine internationale Frachtbriefnummer steht in der Versand-E-Mail des Händlers, in deinem Forwarding-Konto oder in der Nova-Post-App. Frachtbriefe von Nova Global treten in drei Hauptformen auf:

| Format (erfunden) | Was es ist |
| --- | --- |
| `NP482917364058NPG` | Standard-Frachtbrief von Nova Global: 2 Buchstaben (wie `NP`, `WB`, `OG` oder `YW`) + 9 bis 16 Ziffern + `NPG`. |
| `ABCDF4829173640NPG` | Händler-Frachtbrief: 5 Buchstaben + 10 Ziffern + `NPG`. |
| `TE4829173640582917NGUS` | Nova-Global-Frachtbrief für ein Paket aus China: `TE` + 16 Ziffern + `NGUS`. |

Im Zielland übergibt Nova Global das Paket teils an einen lokalen Dienst, etwa Nova Poshta in der Ukraine, und nennt dessen eigene Nummer neben dem `NPG`- oder `NGUS`-Frachtbrief. Weitere grenzüberschreitende Formate findest du in [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was Nova-Global-Sendungsstatus und -Codes bedeuten

Der Tracker von Nova Global nutzt einheitliche Zahlencodes samt englischen oder ukrainischen Meldungen, vorangestellt das Land des jeweiligen Scans:

| Nova-Global-Code & Meldung | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `1-0` (`Order is processed for delivery to the country of destination`) | Absender oder Forwarding-Konto hat den internationalen Auftrag vor Eingang im Hub angelegt. | „Etikett erstellt“ |
| `1-2` (`Processing an unregistered shipment`), `14` (`Accepted for delivery`) | Im Ursprungshub oder Lager von Nova Global angenommen und gescannt. | „Beim Paketdienst eingegangen“ |
| `247`, `76`, `78`, `23`–`26`, `54`, `55`, `30`, `301` | Für den Lufttransport bearbeitet, zwischen Flughäfen befördert, durch Transithubs (z. B. Warschau oder Wien) geleitet und ans Zielterminal geschickt. | „Unterwegs“ |
| `20` (`Delivered to the customs terminal`), `400`, `402` (`Parcel require customs clearance`), `407` / `407-1` (`documents submitted`), `410` (`Accompanying documents received`) | In der Zollprüfung oder wartend auf Zolldokumente und Abgabenfestsetzung. | „Beim Zoll“ |
| `401` (`Customs declaration finished`), `22` (`Left the customs terminal`) | Verzollt und wieder im Zustellnetz unterwegs. | „Unterwegs“ |
| `37` (`On courier delivery`) | Mit dem lokalen Kurier im Zielland zur Haustürzustellung unterwegs. | „In Zustellung“ |
| `303` (`Delivered to the locker`), `38` (`At the post office`) | Abholbereit in einer Paketstation (*поштомат*) oder Nova-Poshta-Filiale. | „Abholbereit“ |
| `553` (`Shipment lost`) | Störung/Verlust vermerkt; kontaktiere umgehend Kundenservice und Verkäufer. | „Problem gemeldet“ |
| `3-1` (`Delivered`) | Vom Empfänger abgeholt oder zugestellt. | „Zugestellt“ |

## Grenzüberschreitender Versand, Forwarding und Zoll

Auf [nova.global](https://nova.global/) stellt Nova Global vier Sparten vor: *Global E-Commerce* (internationale Einzelhandelszustellung in NALA, EMEA und APAC), *Global Forwarding* (virtuelle Einkaufsadressen in 16 Ländern mit Weiterleitung zu dir), *Global Sellers* (Marktplatz- und Social-Commerce-Exporte über Etsy, Amazon, eBay, Vinted, StockX, Depop und TikTok Shop in über 160 Länder) sowie *Global Freight* (Luft-, Straßen- und Seefracht).

**Zollabfertigung und Unterlagen.** Sendungen in die Ukraine, EU, USA oder nach Kanada durchlaufen den Zoll (`20`, `400`, `402`). Die [Datenschutzerklärung von Nova Global](https://nova.global/public-contract/) weist darauf hin, dass eingescannte Dokumente zur Sendung – etwa Wertnachweise – sowie weitere vom Zoll des Absender- oder Ziellands verlangte Unterlagen angefordert werden können. Allgemeine Zollregeln (einschließlich EU-Einfuhrumsatzsteuer, 3-€-Zoll bis 150 € und 2-€-EU-Gebühr auf Nicht-EU-Bestellungen) findest du in [Paket beim Zoll](guide:customs).

**Lokale Zustellregeln.** Übergibt Nova Global an Nova Poshta oder einen anderen lokalen Paketdienst, gelten für Zustellversuche, Lagerfristen und Öffnungszeiten dessen Regeln.

## Nova-Global-Paket beim Zoll, ohne Update oder vermisst

- **Stillstand zwischen Transitländern:** Luftfracht und grenzüberschreitende Straßentransporte (z. B. über Polen in die Ukraine) pausieren oft mehrere Tage zwischen `78` (Abflug) oder `25` (Transitland verlassen) und `20` (Zollterminal). Siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Nummer des lokalen Partners nutzen:** Sobald Nova Global nach dem Zoll einen lokalen Dienst mit eigener Nummer nennt, prüfe dort direkt Zustellfenster oder Abholstellen.
- **Verloren (`553`), beschädigt oder zugestellt, aber nicht da:** Kontaktiere zuerst den Onlineshop (oder Forwarding-Dienst) für eine Reklamation und melde dich mit deinem `NPG`-/`NGUS`-Frachtbrief samt Handelsrechnung beim Nova-Global-Support. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakte von Nova Global

Offizielle Angaben auf [nova.global/contact/](https://www.nova.global/contact/), [nova.global/track/](https://nova.global/track/) und [nova.global/public-contract/](https://nova.global/public-contract/):

- **E-Mail:** `info@nova.global`.
- **Hilfe auf der Tracking-Seite:** Funktionen *Call us*, *Send e-mail* und *Chat with us* auf [nova.global/track/](https://nova.global/track/) sowie Tracking und Support in der **Nova-Post**-App.
- **Unternehmen:** LLC „Nova Poshta Global“ (*LLC NP GLOBAL*, Kennnummer `38130410`, Steuernummer `381304126502`), 103 Stolychne Hwy, Kyjiw, 03026, Ukraine. Nova Global unterhält außerdem ein Büro in New Jersey.

> Ignoriere unverlangte SMS oder Messenger-Links mit dringender Kartenzahlung zum „Freischalten“ eines Nova-Global-Pakets. Prüfe echte Zollhinweise in der offiziellen Nova-Post-App oder über [nova.global/track/](https://nova.global/track/) und `info@nova.global`. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## Nova-Global-Paket mit Peek verfolgen

[Peek](/) erkennt `NPG`-Frachtbriefe (`NP482917364058NPG` oder `ABCDF4829173640NPG`, Ziffern erfunden) und `NGUS`-Frachtbriefe (`TE4829173640582917NGUS`) automatisch mit hoher Sicherheit als Nova Global sowie eingefügte Links `nova.global/track/?Tracking_ID=` oder `novaposhtaglobal.ua/track/?Tracking_ID=`. Musst du Nova Global manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ (auch die Suche nach „Nova Poshta Global“, „NP Global“ oder „Нова Пошта Глобал“ findet ihn).

Peek liest den Nova-Global-Tracker direkt auf Englisch aus und zeigt die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Abholbereit“, „Problem gemeldet“, „Zugestellt“), den Text jedes Scans samt Land, das Zielland und die Zustellzeit, wenn Nova Global eine angibt. Nova-Global-Codes wie `402` oder `553` bestimmen die Phase; Peek zeigt sie nicht an. Scans in der Ukraine tragen Kyjiwer Zeit, Scans im Ausland deren jeweilige Ortszeit; Peek rechnet sie auf dein Gerät um. Nennt Nova Global den lokalen Paketdienst samt Nummer und kennt Peek diesen Dienst, prüft Peek, ob er das Paket hat: Die Seite zeigt dann „Zustellung durch“ mit dem Namen des Dienstes und listet beide Nummern. Ursprungsland und sonstige Nummern des Pakets lässt Peek weg. Voraussichtliches Lieferdatum, Gewicht, Dienstname, Abholadresse und Zustellfoto liefert der Tracker nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem Nova Global `37` (`On courier delivery`) meldet, alle 2 Minuten ab 6 Uhr nach der Ortszeit des neuesten Scans, solange der Tracker von Nova Global antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Nova-Global-Tracker nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Nova Global nach.“, mit dem Link „Hol dir Hilfe bei Nova Global“ zur Kontaktseite von Nova Global. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Nova Global nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsstatus und kann weder Zolldokumente einreichen noch Nova Global für dich kontaktieren.

## Fragen zur Nova-Global-Sendungsverfolgung

### Was bedeuten `NPG` und `NGUS` am Ende einer Sendungsnummer?

`NPG` steht für einen internationalen Frachtbrief von Nova Global (*Nova Poshta Global*, etwa `NP482917364058NPG` oder `ABCDF4829173640NPG`, Ziffern erfunden), während `TE...NGUS` (`TE4829173640582917NGUS`) ein Nova-Global-Frachtbrief für eine Sendung aus China ist.

### Was bedeutet `Parcel require customs clearance` (`402`)?

Code `402` bedeutet, dass das Paket verzollt werden muss. Nova Global kann eingescannte Unterlagen zur Sendung – etwa Wertnachweise – anfordern, wenn der Zoll sie verlangt; nach der Freigabe erscheint `401` (`Customs declaration finished`).

### Wo hole ich ein Nova-Global-Paket in der Ukraine ab?

Zeigt das Tracking `303` (`Delivered to the locker`) oder `38` (`At the post office`), liegt dein Paket in der gewählten Nova-Poshta-Paketstation (*поштомат*) oder Filiale zur Abholung bereit.

### Wie erreiche ich den Kundenservice von Nova Global?

Schreib an `info@nova.global`, besuche [nova.global/contact/](https://www.nova.global/contact/) oder nutze Anruf-, E-Mail- und Chat-Optionen auf [nova.global/track/](https://nova.global/track/).

:::sources
- [Nova Global: Offizielle Website](https://nova.global/) – 160+ Länder, Büros in Kyjiw und New Jersey, Global E-Commerce, Global Forwarding (16 Länder mit virtuellen Adressen), Global Sellers und Global Freight
- [Nova Global: Sendungsverfolgung](https://nova.global/track/) – öffentliches Tracking-Portal, 506.690 Abholpunkte und Kundenservice-Kanäle
- [Nova Global: Kontakt](https://www.nova.global/contact/) – `info@nova.global`, LLC NP GLOBAL (Steuernummer `381304126502`, 103 Stolychne Hwy, Kyjiw) (Hilfe-URL in Peek)
- [Nova Global: Öffentlicher Vertrag & Datenschutz](https://nova.global/public-contract/) – *LLC „Nova Poshta Global“* (ID `38130410`), Postanschrift in Kyjiw und Zoll-/Datenschutzhinweise
- [Universal Parcel Scraper: nova-global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nova-global) – `NPG`-/`NGUS`-Erkennung, Linkregeln `nova.global`/`novaposhtaglobal.ua`, Statuscodes (`1-0` bis `553`), Kyjiwer Zeit in der Ukraine und Ortszeit im Ausland, Auslesung lokaler Partner und Hilfe-URL
:::
