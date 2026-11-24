---
title: Post Haste Sendungsverfolgung: NZ-Tickets, Status & Hilfe
description: Verfolge ein Post-Haste-Couriers-Paket in Neuseeland: Ticket-, Label- und Frachtbrief-Formate, Freightways-Status, Neuzustellung und Depots.
slug: post-haste-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Post Haste** (*Post Haste Couriers*) ist ein neuseeländisches Kuriernetzwerk, das 1977 Teil der **Freightways Group** wurde und in Partnerschaft mit Castle Parcels arbeitet ([posthaste.co.nz](https://posthaste.co.nz/)). Mit über 20 regionalen Niederlassungen und Delivery-Link-Partnern auf der Nord- und Südinsel bedient Post Haste Geschäfts- und Privatadressen mit Lokal-, Regional-Overnight-, landesweiten Overnight- und Zweitages-Economy-Sendungen sowie ländlichen Zustellzonen. Du kannst ein Paket unter [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) prüfen oder deinen Post-Haste-Tracking-Link beziehungsweise deinen Sendungscode oben einfügen.

## Post Haste Sendungsnummern (Tickets, Etiketten & Links)

Post Haste verwendet verschiedene Freightways-Etikettenformate — je nachdem, ob der Absender ein vorbezahltes Kurier-Ticket bzw. eine Versandtasche, ein 8-stelliges Abrechnungsetikett, einen 20-stelligen SSCC-Barcode oder ein selbst gedrucktes Etikett (z. B. Despatch IT) genutzt hat:

| Format (erfunden) | Was es ist |
| --- | --- |
| `PH48291736` | Kurier-Ticket oder Express-Pack-Versandtasche: Produktcode aus 2–4 Buchstaben (z. B. `PE` oder `PH`) + 5–8-stellige Ticketnummer (ohne Leerzeichen eingeben). |
| `73918462` | 8-stellige Post-Haste-Abrechnungsnummer (*charge label*). |
| `KLM4829173` | Vom Kunden gedruckte Frachtbriefnummer (Buchstaben + 6–8 Ziffern); manche selbst gedruckten Etiketten benötigen zusätzlich die Kundennummer des Absenders, die im Link deiner Versand-E-Mail steckt (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

Hat dir der Händler einen Link von `receiver.posthaste.co.nz` oder `posthaste.co.nz` per E-Mail oder SMS geschickt, **füge diesen vollständigen Link direkt in Peek ein** — so übernimmt Peek automatisch sowohl die Frachtbriefnummer als auch bei Bedarf den Kontocode des Absenders. Siehe auch den Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der Post Haste Statusmeldungen

Post Haste nutzt das gemeinsame Tracking-System von Freightways (das auch für Castle Parcels, New Zealand Couriers und Now Couriers läuft) und meldet Scan-Typen und Untertypen zusammen mit Vorort- oder Regionsangaben:

| Post Haste Code oder Scan | Was er für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Label created` | Elektronisches Etikett im Versandsystem des Absenders angelegt, noch vor der Abholung. | „Etikett erstellt“ |
| `P` (`Picked Up`) | Von einem Post-Haste-Kurier beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `STAT/RZDEL` | Hinweis auf ländliche oder nicht-städtische Zustellzone; die Übergabe an einen ländlichen Partner dauert meist 2–3 Werktage länger. | „Unterwegs“ |
| `STAT/REDEL` | Nach einer früheren verpassten Zustellung neu angesetzt und wieder ausgeliefert. | „Unterwegs“ |
| `D` (`Delivery Handed Over` / `Delivered by courier or branch` an ländlichen Partner) | An einen ländlichen Zustellpartner übergeben; noch auf dem Weg zu dir und nicht vor deiner Tür. | „Unterwegs“ |
| `ONBD` | Heute im Fahrzeug des örtlichen Kuriers zur Auslieferung. | „In Zustellung“ |
| `NOND` / `NOND/*` | Nichtzustellung (z. B. niemand angetroffen und Karte hinterlassen, geschlossene Räume oder Zugangsproblem). | „Zustellversuch“ |
| `D`, `3P/D` | An der Empfängeradresse zugestellt (einschließlich `3P/D` durch einen ländlichen Zustellpartner). | „Zugestellt“ |

`STAT/HLDFR8` bedeutet, dass das Paket nach einer nicht erfolgten Zustellung im Frachtbüro der Niederlassung liegt. Der Hinweis sagt nicht aus, ob es auf Abholung oder auf eine erneute Zustellung wartet: Vereinbare eine Neuzustellung oder frage in der Niederlassung nach.

## Laufzeiten, ländliche Zonen und Neuzustellung in Neuseeland

Laut [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) und [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Veröffentlichte Laufzeiten:** **Lokal** für innerstädtische Geschäftssendungen; **Regional Overnight** (meist bis zur Mittagszeit am nächsten Werktag); **Landesweit Overnight** innerhalb und zwischen Nord- und Südinsel (meist bis Mittag am nächsten Werktag für Geschäftsadressen, bis Tagesende für Privatadressen); **Zweitages-Economy** zwischen den Inseln; sowie **Samstagszustellung** in großen Ballungsräumen.
- **Nicht-städtische und ländliche Adressen:** Post Haste erreicht die meisten ländlichen Adressen Neuseelands über ländliche Zustellpartner (`STAT/RZDEL`), was zwei bis drei Werktage zusätzlich zu den städtischen Zielen bedeutet.
- **Abwesenheit & Neuzustellung:** Grundsätzlich stellt Post Haste Pakete **nicht** an unbesetzten Adressen ab, sofern keine Abstellgenehmigung (*authority to leave*) vereinbart wurde. Hast du eine Zustellung verpasst, buche online eine Neuzustellung unter [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) mit der 16-stelligen Zustellreferenz oder wende dich ohne Referenz an deine örtliche Niederlassung. Für die Abholung in der Niederlassung benötigst du einen Ausweis.

## Post-Haste-Paket verzögert, gelagert oder verschwunden

- **Übergabe aufs Land wirkt anderswo wie „zugestellt“:** Meldet ein Scan `Delivered by courier or branch` oder `Delivery Handed Over`, wurde das Paket meist an einen ländlichen Subunternehmer (`3P`) übergeben statt an deiner Tür abgegeben — rechne 2–3 Werktage für die ländliche Runde (`3P/D`) ein. Peek belässt ländliche Übergaben auf „Unterwegs“, statt sie verfrüht als zugestellt zu werten.
- **Keine Scans nach Etikett oder Stillstand im Depot:** Wird ein selbst gedruckter Frachtbrief allein über die Nummer nicht gefunden, füge den vollständigen Link `receiver.posthaste.co.nz` aus deiner Versand-E-Mail ein, damit Peek den Kontocode des Absenders mitgibt. Hängt ein Paket in einer Niederlassung fest, kontaktiere dein örtliches Post-Haste-Depot oder den Verkäufer.
- **Verlust- oder Schadensmeldungen:** Lies die Richtlinien von Post Haste auf [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) und wende dich zuerst an den Händler, der den Versand gebucht hat. Siehe auch [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel), [zurück an den Absender](guide:returned-to-sender) und [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).

## Kundenservice und Niederlassungen von Post Haste

Offizielle Kontakte und Filialverzeichnisse auf [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) und [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Verzeichnis lokaler Niederlassungen (Nord- und Südinsel):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) nennt Telefonnummern und Adressen der Niederlassungen auf der Nordinsel (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) und der Südinsel (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), einschließlich gemeinsamer Filialen mit Castle Parcels.
- **Online-Neuzustellung:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Hilfebereich & Richtlinien für Empfänger:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Vorsicht vor gefälschten SMS, die für ein neuseeländisches Kurierpaket eine kleine Kartenzahlung zur Terminverschiebung verlangen. Nutze ausschließlich [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) oder frage direkt bei deiner Post-Haste-Niederlassung nach. Mehr dazu unter [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Post Haste mit Peek verfolgen

Am einfachsten fügst du eine Post-Haste-Sendung in [Peek](/) hinzu, indem du den vollständigen Tracking-Link aus deiner Versand-E-Mail einfügst (`receiver.posthaste.co.nz/?consignmentid=CU..…` oder `posthaste.co.nz/track?query=`), den Peek sofort als Post Haste erkennt und nutzt, um bei Bedarf das Absenderkonto auszulesen. Gibst du eine bloße Ticket-, Abrechnungs- oder Frachtbriefnummer ein (`PH48291736`, `73918462` oder `KLM4829173`, erfunden), erkennt Peek Post Haste nicht allein an der Nummer (bei einem Ticket wie `PH48291736` fragt es TNT Italien und bei einem Frachtbrief wie `KLM4829173` Australia Post). Tippe daher beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“ und wähle **Post Haste** (auch „Post Haste Couriers“ oder „Posthaste“ findet den Dienst). Peek bietet anschließend ein optionales Feld „Vollständiger Tracking-Link“ für den Link aus deiner Versand-E-Mail an, der das für manche Pakete erforderliche Absenderkonto enthält.

Peek ruft die Freightways-Abfrage unter dem Namen Post Haste ab und zeigt die Stufe („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Zustellversuch“, „Zugestellt“), den Scan-Wortlaut, Vorort und Region, den voraussichtlichen Zustelltag (wenn Post Haste einen nennt) sowie den Zustellzeitpunkt an. Scan-Typen und Untertypen legen die Stufe fest und bleiben in allen Peek-Apps verborgen. Zeitstempel kommen als UTC-Zeitpunkte und erscheinen in der Uhrzeit deines Geräts. Stammt der neueste Scan von einem anderen Freightways-Netzwerk wie Castle Parcels, zeigt die Paketseite „Zustellung durch“ samt dem Namen dieses Netzwerks. Unterschrift und Namen des Unterzeichners, Zustellfotos, den Abstellgenehmigungs-Hinweis, Postleitzahlen und den Fahrer speichert Peek nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Post Haste `ONBD` meldet, ab 6 Uhr neuseeländischer Zeit alle 2 Minuten, solange der Post-Haste-Tracker antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Post Haste nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Post Haste nach.“ samt dem Link „Hol dir Hilfe bei Post Haste“ zur Empfängerhilfe von Post Haste. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Post Haste nach.“

Als Benachrichtigungen stehen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ zur Wahl. Peek überwacht nur den Sendungsverlauf und kann weder eine Neuzustellung buchen noch eine Abstellgenehmigung erteilen oder deine örtliche Post-Haste-Niederlassung kontaktieren.

## Fragen zur Sendungsverfolgung bei Post Haste

### Welche Sendungsnummern funktionieren bei Post Haste?

Post Haste verfolgt Kurier-Tickets und Express Packs (2–4 Buchstaben + 5–8 Ziffern, z. B. `PH48291736`, erfunden), 8-stellige Abrechnungsetiketten (`73918462`, erfunden), 20-stellige SSCC-Barcodes sowie vom Kunden gedruckte Frachtbriefnummern (`KLM4829173`, erfunden) — oder füge direkt den vollständigen Link `receiver.posthaste.co.nz` aus deiner Versand-E-Mail ein.

### Warum wird meine vom Absender gedruckte Post-Haste-Frachtbriefnummer nicht gefunden?

Manche vom Kunden gedruckten Etiketten (wie Despatch-IT-Sendungen) sind an die Kundennummer des Absenders gebunden. Füge den vollständigen Link `receiver.posthaste.co.nz` aus deiner Versandbestätigung ein, damit der Kontocode bei der Abfrage mitgesendet wird.

### Was mache ich nach einer verpassten Post-Haste-Zustellung?

Post Haste verlangt normalerweise eine persönliche Entgegennahme, sofern keine Abstellgenehmigung vorliegt. Nutze das Online-Formular unter [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) mit deiner 16-stelligen Zustellreferenz oder rufe deine örtliche Niederlassung über [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) an.

### Warum zeigt Post Haste `Delivery Handed Over` oder `STAT/RZDEL` an?

`STAT/RZDEL` kennzeichnet eine ländliche oder nicht-städtische Adresse, und ein Übergabe-Scan bedeutet, dass das Paket an einen ländlichen Zustellpartner weitergereicht wurde — das verlängert die Laufzeit meist um 2–3 Werktage bis zur endgültigen Zustellung (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – neuseeländisches Kuriernetzwerk (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – Übernahme durch Freightways (1977) und Partnerschaft mit Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – Lokal-, Regional-Overnight-, landesweite Overnight-, Zweitages-Economy-, Samstags- und Landzustellung
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – kundengedruckte Etiketten (mit Firmenkonto), Kurier-Tickets/Express Packs, Barcodes und Vertragssäcke
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – Online-Neuzustellung und Ausweispflicht bei Abholung in der Niederlassung
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – Filialverzeichnis für Nord- und Südinsel
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – Richtlinie bei Abwesenheit, Paketbenachrichtigungen und Verlust-/Schadensregeln (von Peek verlinkte Hilfeseite)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – Freightways-Abfrage für Tickets/Etiketten/SSCC/Frachtbriefe, `receiver.posthaste.co.nz`-Kontolink-Regeln, Code-Zuordnung (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) und ETA-Auswertung
:::
