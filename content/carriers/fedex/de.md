---
title: FedEx Tracking und Sendungsverfolgung: Status und Hilfe
description: FedEx-Paket verfolgen: wie die 12-stellige Sendungsnummer aussieht, was „Delivery exception“ und andere Scans bedeuten, Zollgebühren und deine Rechte bei Verlust.
slug: fedex-sendungsverfolgung
published: 2026-10-13
updated: 2026-10-28
---

Füg eine FedEx-Sendungsnummer oder einen FedEx-Tracking-Link oben ins Feld ein, um dein Paket zu verfolgen. FedEx' eigene Sendungsverfolgung findest du auf fedex.com und in der FedEx Mobile App.

## FedEx Trackingnummer: wo du sie findest

Schau in die Versandbestätigung des Shops oder auf die Bestellseite. Ein Tracking-Link von fedex.com enthält die Nummer ebenfalls, hinter `trknbr=` oder `tracknumbers=`. Nichts gefunden? Mehr in [Sendungsnummer finden](guide:find-tracking-number).

| Form (Ziffern erfunden) | Was es ist |
| --- | --- |
| `123456789012` | 12 Ziffern: eine FedEx-Sendungsnummer. Die letzte Ziffer ist eine Prüfziffer, berechnet aus den 11 davor. |
| `123456789012343` | 15 Ziffern: eine Form, die Peek unter anderem FedEx zuordnet. |
| `61290000000000000014` | 20 Ziffern, beginnt mit 6129 oder 7489: FedEx Ground Economy (früher SmartPost) in den USA. |

Zwölf Ziffern sind keine Besonderheit von FedEx: Je nach Prüfziffer passt dieselbe Form zu [GLS](carrier:gls-de), [GLS Schweiz](carrier:gls-ch) oder [DHL](carrier:dhl), dazu zu DPD France, Mondial Relay, BRT und mehreren asiatischen Paketdiensten. Eine Nummer, die mit `1Z` beginnt, ist von [UPS](carrier:ups), eine 10-stellige Frachtbriefnummer kann von [DHL Express](carrier:dhl-express) sein. Kennt fedex.com deine Nummer nicht, schau, welchen Paketdienst die Mail des Shops nennt.

## FedEx Sendungsverfolgung: was die Status bedeuten

Peek liest FedEx' Scans auf Englisch, so wie FedEx sie schreibt, und zeigt darüber den Stand auf Deutsch. Hier die wichtigsten, ungefähr in der Reihenfolge, in der ein Paket sie durchläuft:

| FedEx schreibt | Peek zeigt | Was es für dich heißt |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Etikett erstellt | Der Versender hat FedEx die Sendungsdaten geschickt. |
| `Picked up`, `Tendered at FedEx location` | Beim Paketdienst eingegangen | FedEx hat das Paket: Ein Kurier hat es abgeholt, oder der Versender hat es an einem FedEx-Standort abgegeben. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | Unterwegs | Es ist in FedEx' Netz unterwegs. |
| `Clearance in progress`, `Clearance delay` | Beim Zoll | Es wird verzollt (siehe unten). |
| `International shipment release - Import` | Unterwegs | Der Zoll hat es ins Zielland freigegeben. |
| `On FedEx vehicle for delivery` | In Zustellung | Es ist im Fahrzeug zur Zustellung. |
| `Delivery exception`, `Customer not available or business closed` | Zustellversuch | Die Zustellung hat nicht geklappt. |
| `Ready for pickup` | Abholbereit | Es wartet an einem Abholort auf dich. |
| `Delivered` | Zugestellt | Es ist zugestellt. |
| `Shipment exception` | Problem gemeldet | FedEx meldet ein Problem mit der Sendung. |
| `Returning to shipper` | Zurück an Absender | Es geht zurück an den Versender. |

Markiert FedEx ein Paket als verspätet, zeigt Peek „Problem gemeldet“, außer solange der letzte Scan `Package received after FedEx cutoff` lautet: Dann kam das Paket nach Annahmeschluss bei FedEx an und geht am nächsten Werktag weiter.

## FedEx Zustellung verpasst und Abholort

Klappt die Zustellung nicht, kann ein Scan den Grund nennen: `Customer not available or business closed`, wenn niemand da war, oder `Local delivery restriction - Delivery not attempted`, wenn eine örtliche Einschränkung den Zustellversuch verhindert hat.

Bittest du FedEx, das Paket an einem Abholort zu hinterlegen, kann der Scan unterwegs `Delivery option requested` lauten, nach der Ankunft `Ready for pickup`. Peek zeigt dann Name und Adresse des Abholorts, sofern FedEx sie mitliefert: Wurde die Hinterlegung erst nach dem Versand gewünscht, zeigt FedEx' Seite Besuchern ohne Anmeldung nur den Ort. Die letzte Etappe nennt FedEx' Seite eventuell `Heading to pickup location`; Peek zählt sie als „In Zustellung“. Solche Änderungen vereinbarst du mit FedEx, nicht in Peek. Mehr in [Paket verpasst](guide:missed-delivery) und [Paket nicht abgeholt](guide:uncollected-parcel).

## FedEx Paket beim Zoll

Kommt ein Paket aus einem Nicht-EU-Staat, können Einfuhrabgaben fällig werden. Solange es beim Zoll ist, zeigt die Sendungsverfolgung `Clearance in progress` oder `Clearance delay`, nach der Freigabe `International shipment release - Import`.

In Deutschland übernimmt laut Zoll in den meisten Fällen der Post- oder Kurierdienst die Zollanmeldung und legt die Einfuhrabgaben aus. Du zahlst sie in der Regel bei der Zustellung, dazu meist eine Servicepauschale, deren Höhe in den AGB des Dienstes steht. Seit dem 1. Juli 2026 fällt für Sendungen aus Nicht-EU-Staaten bis 150 € ein pauschaler Zoll von 3 € je Warenkategorie an, dazu wie bisher die Einfuhrumsatzsteuer von 19 bzw. 7 %. Dieser Pauschalzoll gilt laut dem EU-Portal „Ihr Europa“ in der ganzen EU, also auch in Österreich. Mehr in [Paket beim Zoll](guide:customs).

## FedEx Paket kommt nicht an, ist verspätet oder beschädigt

Steht die Sendungsverfolgung still, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

Hast du bei einem Händler bestellt, ist er dein Ansprechpartner, auch wenn FedEx das Paket bringt. Laut „Ihr Europa“ haftet er für Schäden an der Ware vom Versand bis zur Übergabe an dich. Ist nichts anderes vereinbart, muss er innerhalb von 30 Tagen liefern. Kommt nichts, setz ihm eine angemessene Nachfrist; hält er auch die nicht ein, kannst du vom Vertrag zurücktreten und bekommst dein Geld zurück. Das gilt in der ganzen EU.

In Deutschland gilt laut Verbraucherzentrale außerdem: Du musst nichts bezahlen, solange du die Ware nicht erhalten hast. Stellt der Paketdienst das Paket einfach vor die Tür und es wird gestohlen, oder gibt er es einem Hausbewohner, den du nicht bevollmächtigt hast, trägt der Verkäufer den Verlust.

**Zugestellt, aber nicht da?** FedEx' Zustellscan nennt, wer unterschrieben hat; Peek speichert das nicht. Schau auf fedex.com nach und wende dich dann an den Händler. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

## FedEx-Paket mit Peek verfolgen

Bei 12- und 15-stelligen Nummern mit gültiger Prüfziffer denkt [Peek](/) zuerst an FedEx, ist sich aber nicht sicher: Andere Paketdienste teilen diese Formen, und bei FedEx fragt Peek erst zuletzt nach, im Browser. Oft nennt deshalb ein Tracking-Dienst den Paketdienst, oder es dauert einen Moment. Fällt eine Nummer durch FedEx' Prüfung, wird FedEx nicht angeboten: Dann wählst du den Paketdienst mit „Paketdienst ändern“. Ground-Economy-Nummern mit 20 Ziffern erkennt Peek sofort als FedEx, bei gescannten Etikett-Barcodes liest es die Nummer darin. Links von fedex.com mit `trknbr=` oder `tracknumbers=` kannst du ebenfalls einfügen. 13-stellige FedEx-Nummern für Inlandspakete in Polen verfolgt Peek als FedEx Poland.

Peeks Server öffnet dafür die Tracking-Seite von fedex.com in einem Browser. Das klappt nicht immer: Blockt FedEx den Abruf oder verlangt FedEx eine Bestätigung durch den Empfänger, springen Tracking-Dienste ein.

Antwortet FedEx, siehst du den Status, jeden Scan mit Ort und genauer Uhrzeit auf einer Karte, den erwarteten Liefertag, die Zustellzeit und, solange das Paket wartet, Name und Adresse des FedEx-Standorts, an dem es liegt, sofern FedEx sie angibt. Peek schaut tagsüber alle 10 Minuten nach, alle 5 Minuten, sobald das Paket in Zustellung ist, nachts alle ein bis zwei Stunden, und schickt dir auf Wunsch Mitteilungen bei jedem Scan, nur bei wichtigen Schritten oder nur am Liefertag. Nicht zu sehen sind dein Name und deine Adresse, der Versender, die Unterschrift, Gewicht und Abmessungen. Die Scans erscheinen in FedEx' englischer Fassung. Nur den Zustellscan, in dem FedEx den Namen der unterschreibenden Person nennt, sowie `Ready for pickup` und `Shipment exception` zeigt Peek in eigenen Worten auf Deutsch, etwa „Zugestellt“ oder „Abholbereit“.

Bestellst du in den USA und übergibt FedEx ein Ground-Economy-Paket an die US-Post, geht die Verfolgung dort weiter („Zustellung durch USPS“). Die meisten dieser Pakete stellt FedEx aber selbst zu.

## Fragen zur FedEx Sendungsverfolgung

### Was bedeutet „Delivery exception“ bei FedEx?

Peek ordnet diesen FedEx-Scan den gescheiterten Zustellversuchen zu und zeigt dafür „Zustellversuch“. Warum die Zustellung nicht geklappt hat, kann ein Scan wie `Customer not available or business closed` (niemand angetroffen oder Geschäft geschlossen) verraten.

### Warum steht bei FedEx nur „Shipment information sent to FedEx“?

Dieser erste Scan heißt nur, dass der Versender FedEx die Sendungsdaten geschickt hat. Weiter geht es, sobald FedEx das Paket hat: `Picked up` oder `Tendered at FedEx location`.

### Ist eine SMS von FedEx echt?

Nicht unbedingt. Die Verbraucherzentralen warnen vor SMS angeblicher Paketdienste, in denen du auf einen Link tippen sollst: Die Folge können schädliche Apps, Massen-SMS oder Abofallen sein. Tipp den Link nicht an, antworte nicht und lösch die SMS. Erwartest du ein Paket, gib die Sendungsnummer selbst auf fedex.com oder in Peek ein. Zollgebühren zahlst du in der Regel bei der Zustellung, nicht vorab über eine Internetseite. Mehr in [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

:::sources
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Kurierdienst meldet an und legt aus, Zahlung bei Zustellung, Servicepauschale, 3 € seit 1. Juli 2026, 19 bzw. 7 %
- [Ihr Europa: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Nachfrist, Rücktritt, Haftung des Händlers, 3 € je Position in der EU
- [Verbraucherzentrale: Was tun, wenn meine Online-Bestellung nach dem Versand nicht ankommt?](https://www.verbraucherzentrale.de/wissen/digitale-welt/onlinehandel/was-tun-wenn-meine-onlinebestellung-nach-dem-versand-nicht-ankommt-28083) – nichts zahlen ohne Ware, Fehler des Zustellers
- [Verbraucherzentrale: Paketdienst-SMS: Vorsicht, Abzocke!](https://www.verbraucherzentrale.de/wissen/digitale-welt/mobilfunk-und-festnetz/paketdienstsms-vorsicht-abzocke-58988) – Fake-SMS, Zollgebühren bei Zustellung, Nummer selbst eingeben
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – Nummernformen, Prüfziffern, FedEx' Scans, was Peek bei FedEx liest
:::
