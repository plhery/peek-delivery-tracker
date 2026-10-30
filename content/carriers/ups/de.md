---
title: UPS Sendungsverfolgung: Nummer, Status und Zustellung
description: UPS-Paket verfolgen: wie die 1Z-Trackingnummer aussieht, was Scans wie „Exception“ bedeuten, Access Point, Zollgebühren und deine Rechte bei Verlust.
slug: ups-sendungsverfolgung
published: 2026-10-13
updated: 2026-10-27
---

UPS-Sendungsnummern (UPS steht für United Parcel Service) beginnen meist mit `1Z`. Füg die Nummer oder einen UPS-Tracking-Link oben ins Feld ein, um dein Paket zu verfolgen. UPS' eigene Sendungsverfolgung findest du auf ups.com.

## UPS Trackingnummer: wo sie steht und wie sie aussieht

Die Nummer bekommst du vom Shop, der das Paket verschickt, meist in der Versandbestätigung oder auf der Bestellseite. Ein Tracking-Link von ups.com enthält sie ebenfalls, hinter `tracknum=`. Nichts gefunden? Mehr in [Sendungsnummer finden](guide:find-tracking-number).

| Nummer (erfunden) | Was sie ist |
| --- | --- |
| `1Z1234567812345674` | UPS' Paketnummer: `1Z` und 16 Buchstaben und Ziffern, 18 Zeichen. Das letzte Zeichen ist eine Prüfziffer, berechnet aus den 15 Zeichen davor. |
| `H1234567890` | Ein Buchstabe (H, J, K oder V) und 10 Ziffern. Das kann eine UPS-Nummer sein, andere Paketdienste nutzen aber dieselbe Form. |

## UPS Status: was „Exception“, „Out for Delivery“ und Co. bedeuten

UPS' englische Tracking-Seite zeigt einen Fortschrittsbalken (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) und darunter die Scans. Peek liest diese Scans auf Englisch und zeigt darüber den Stand auf Deutsch. Hier die wichtigsten, ungefähr in der Reihenfolge, in der ein Paket sie durchläuft:

| UPS schreibt | Peek zeigt | Was es für dich heißt |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Etikett erstellt | Der Versender hat ein Etikett erstellt. UPS hat das Paket noch nicht. |
| `Drop-Off`, `Pickup Scan` | Beim Paketdienst eingegangen | UPS hat das Paket: Der Versender hat es in einem Access Point abgegeben, oder UPS hat es abgeholt. Das erste `Arrived at Facility` zählt auch dazu. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | Unterwegs | Es ist in UPS' Netz unterwegs. `Export Scan` und `Import Scan` markieren eine Grenze. |
| `Your package is prepared for clearance.` | Beim Zoll | Es wird verzollt. |
| `Duties or taxes are due on this package.` | Beim Zoll | Du musst Einfuhrabgaben zahlen, bevor UPS zustellt (siehe unten). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | In Zustellung | Ein Fahrer hat es dabei. |
| `The receiver was not available at the time of the final delivery attempt.` | Zustellversuch | Auch der letzte Zustellversuch ist gescheitert. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Abholbereit | Es liegt in einem UPS Access Point und wartet auf dich. |
| `DELIVERED` | Zugestellt | Zugestellt oder von dir im Access Point abgeholt. |
| `Return to Sender` | Zurück an Absender | Es geht zurück an den Versender. |

`Exception` ist UPS' allgemeiner Status und kann eine bloße Verspätung bedeuten. Entscheidend ist der Scan darunter: `We're sorry this package may experience a temporary delay.` heißt genau das, `The company or receiver name is incorrect. This will delay delivery.` dagegen, dass die Zustellung an deinen Angaben hängt (Peek zeigt dann „Problem gemeldet“). Peek richtet sich nach dem neuesten Scan, nicht nach dem allgemeinen Status.

## UPS Zustellung verpasst und Access Point

Trifft der Fahrer dich nicht an, steht im Scan, dass der Empfänger nicht da oder das Geschäft geschlossen war, und UPS versucht es erneut. Nach dem letzten Versuch zeigt ein weiterer Scan, wie es weitergeht: UPS behält das Paket, bringt es in einen Access Point oder schickt es an den Versender zurück. Mehr in [Paket verpasst](guide:missed-delivery).

UPS kann das Paket auch in einen UPS Access Point bringen. Dort lauten die Scans `Delivered to UPS Access Point™`, dann `UPS Access Point™ possession`, und Peek zeigt Name und Adresse des Access Points. Lass es nicht zu lange liegen: Geht ein nicht abgeholtes Paket zurück, lautet der Scan `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Schließt ein Access Point, holt UPS seine Pakete ab, und der Scan kündigt die Zustellung an einen anderen Access Point an. Mehr in [Paket nicht abgeholt](guide:uncollected-parcel).

## Zollgebühren bei UPS-Paketen

Kommt ein Paket aus einem Nicht-EU-Staat, können Einfuhrabgaben fällig werden, bevor UPS zustellt. Die Sendungsverfolgung zeigt dann `Duties or taxes are due on this package.` und `Your package is being held until duties and taxes are paid. Awaiting customer action.`

In Deutschland übernimmt laut Zoll in den meisten Fällen der Post- oder Kurierdienst die Zollanmeldung und legt die Einfuhrabgaben aus. Du zahlst sie in der Regel bei der Zustellung, dazu meist eine Servicepauschale, deren Höhe in den AGB des Dienstes steht. Seit dem 1. Juli 2026 fällt für Sendungen aus Nicht-EU-Staaten bis 150 € ein pauschaler Zoll von 3 € je Warenkategorie an, dazu wie bisher die Einfuhrumsatzsteuer von 19 bzw. 7 %. Dieser Pauschalzoll gilt laut dem EU-Portal „Ihr Europa“ in der ganzen EU, also auch in Österreich. Mehr in [Paket beim Zoll](guide:customs).

## UPS Paket kommt nicht an, ist verspätet oder beschädigt

Steht die Sendungsverfolgung still, lies [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

Hast du bei einem Händler bestellt, ist er dein Ansprechpartner, auch wenn UPS das Paket bringt. Laut „Ihr Europa“ haftet er für Schäden an der Ware vom Versand bis zur Übergabe an dich. Ist nichts anderes vereinbart, muss er innerhalb von 30 Tagen liefern. Kommt nichts, setz ihm eine angemessene Nachfrist; hält er auch die nicht ein, kannst du vom Vertrag zurücktreten und bekommst dein Geld zurück. Das gilt in der ganzen EU.

In Deutschland gilt laut Verbraucherzentrale außerdem: Du musst nichts bezahlen, solange du die Ware nicht erhalten hast. Stellt der Paketdienst das Paket einfach vor die Tür und es wird gestohlen, oder gibt er es einem Hausbewohner, den du nicht bevollmächtigt hast, trägt der Verkäufer den Verlust. Du kannst Absender und Lieferdienst eine Frist zur Zustellung setzen.

Ist ein verlorenes, gestohlenes oder beschädigtes Paket mit dem Paketdienst nicht zu klären, kannst du bei der Schlichtungsstelle Post der Bundesnetzagentur eine gebührenfreie Schlichtung beantragen. Voraussetzung: Du hast vorher vergeblich eine Einigung versucht, und es geht um 10 bis 2.500 €. Stellst du den Antrag als Verbraucher, muss das Postunternehmen teilnehmen. Im Anbieterverzeichnis Post der Bundesnetzagentur steht UPS als „UPS Management LLC“ mit Sitz in Neuss.

**Zugestellt, aber nicht da?** UPS' Seite kann zeigen, wo das Paket abgestellt wurde (etwa `Left at: Front Door`), und einen Zustellnachweis. Peek speichert beides nicht. Schau also auf ups.com nach und wende dich dann an den Händler. Mehr in [Paket zugestellt, aber nicht erhalten](guide:delivered-not-received) und [Paket verloren](guide:lost-parcel).

## UPS-Paket mit Peek verfolgen

[Peek](/) erkennt UPS sofort an einer `1Z`-Nummer, deren letzte Ziffer UPS' Prüfung besteht. Fällt sie durch, wird nur UPS angeboten, und UPS lehnt sie als vertippt ab. Bei UPS selbst fragt Peek nur `1Z`-Nummern ab. Andere Formen wie `H1234567890` aus der Tabelle oben gehen deshalb nie an UPS: Wählst du über „Paketdienst ändern“ UPS, liefern nur Tracking-Dienste den Verlauf. Links von ups.com mit einer Nummer kannst du ebenso einfügen wie jeden Link, in dem eine `1Z`-Nummer steckt.

Peeks Server öffnet dafür UPS' Tracking-Seite in einem Browser, tagsüber alle 10 Minuten, ab der Zustellung alle 5 Minuten und nachts alle ein bis zwei Stunden. Blockt UPS den Abruf oder ist nur der Status lesbar, liefern Tracking-Dienste den Verlauf. Du siehst den Status, jeden Scan mit Ort und genauer Uhrzeit auf einer Karte, den geplanten Zustelltag (nie ein Zeitfenster), die Zustellzeit, das Zielland und, solange das Paket wartet, Name und Adresse des Access Points. Mitteilungen bekommst du auf Wunsch bei jedem Scan, nur bei wichtigen Schritten oder nur am Liefertag. Nicht zu sehen sind, wer unterschrieben hat, deine Adresse, der Zustellnachweis, der Ablageort und das Zustellfoto. Gewicht und Absender gibt UPS nicht heraus.

Die Scans erscheinen in jeder Sprache in UPS' englischer Fassung: Zugestellt heißt dort „DELIVERED“. Übergibt UPS in den USA ein Ground-Saver-Paket an die US-Post und nennt deren Nummer, geht die Verfolgung dort weiter („Zustellung durch USPS“), sobald USPS das Paket bewegt.

## Fragen zur UPS Sendungsverfolgung

### Warum funktioniert die UPS Sendungsverfolgung nicht?

Prüf zuerst die Nummer: Das letzte Zeichen einer `1Z`-Nummer ist eine Prüfziffer, ein falsches Zeichen macht sie meist ungültig, und UPS lehnt sie ab. Steht nur `Shipper created a label, UPS has not received the package yet.`, hat UPS das Paket noch nicht: Weiter geht es erst, wenn der Versender es abgibt oder UPS es abholt (`Drop-Off`, `Pickup Scan`). Oder die Nummer gehört zu einem anderen Paketdienst: Schau, welchen die Versand-E-Mail nennt.

### Kann ich die UPS-Zustellung in Peek ändern?

Nein, Peek liest UPS' Sendungsverfolgung nur mit. Änderungen, die du bei UPS vornimmst, erscheinen in den Scans, etwa `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Ist eine SMS von UPS echt?

Nicht unbedingt. Die Verbraucherzentralen warnen vor SMS angeblicher Paketdienste, in denen du auf einen Link tippen sollst: Die Folge können schädliche Apps, Massen-SMS oder Abofallen sein. Tipp den Link nicht an, installier keine App, antworte nicht und lösch die SMS. Erwartest du ein Paket, gib die Sendungsnummer selbst auf ups.com oder in Peek ein. Zollgebühren zahlst du in der Regel bei der Zustellung, nicht vorab über eine Internetseite. Bietet ein Paketdienst die Zahlung vorab an, musst du dich dafür registriert haben, und seine Nachrichten nennen immer Sendungsnummer und Absender. Mehr in [Fake-Paket-SMS erkennen](guide:delivery-scam-texts).

:::sources
- [Zoll: Internetbestellungen](https://www.zoll.de/DE/Privatpersonen/Postsendungen-Internetbestellungen/Sendungen-aus-einem-Nicht-EU-Staat/Zoll-und-Steuern/Internetbestellungen/internetbestellungen_node.html) – Kurierdienst meldet an und legt aus, Zahlung bei Zustellung, Servicepauschale, 3 € seit 1. Juli 2026, 19 bzw. 7 %
- [Ihr Europa: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 30 Tage, Nachfrist, Rücktritt, Haftung des Händlers, 3 € je Position in der EU
- [Verbraucherzentrale: Was tun, wenn meine Online-Bestellung nach dem Versand nicht ankommt?](https://www.verbraucherzentrale.de/wissen/digitale-welt/onlinehandel/was-tun-wenn-meine-onlinebestellung-nach-dem-versand-nicht-ankommt-28083) – nichts zahlen ohne Ware, Fehler des Zustellers, Frist
- [Verbraucherzentrale: Paketdienst-SMS: Vorsicht, Abzocke!](https://www.verbraucherzentrale.de/wissen/digitale-welt/mobilfunk-und-festnetz/paketdienstsms-vorsicht-abzocke-58988) – Fake-SMS, Zollgebühren bei Zustellung, Nummer selbst eingeben
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – gebührenfrei, Voraussetzungen, 10 bis 2.500 €, Teilnahmepflicht
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – UPS (UPS Management LLC, Neuss) als Postdienstleister eingetragen
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – Nummernformen, Prüfziffer, UPS' Scans, was Peek bei UPS liest
:::
