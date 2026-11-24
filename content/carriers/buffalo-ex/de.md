---
title: BUFFALO Sendungsverfolgung: BUFZA-Codes, Zoll & Hilfe
description: Verfolge ein BUFFALO-Paket von China nach Südafrika: BUFZA...YQ-Codes, SARS-Zoll- und Mehrwertsteuer-Scans, Lieferzeiten und Support-Kontakte.
slug: buffalo-ex-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**BUFFALO** (*BUFFALO International Logistics*, oft auch *Buffaloex* oder *Buffalo Express* genannt) ist ein grenzüberschreitender E-Commerce-Logistikdienstleister zwischen China und Afrika, der seit April 2017 aktiv ist ([buffaloex.com](https://www.buffaloex.com/)). Der Hauptsitz befindet sich in Shanghai, die afrikanische Niederlassung in Johannesburg. Die integrierten Lager umfassen 35.000 Quadratmeter, darunter ein 6.500 m² großes Zolllager, und das Unternehmen betreibt 300 eigene Fahrzeuge. Nach eigenen Angaben ist BUFFALO direkt an die südafrikanischen Zoll- und Bankensysteme angebunden und Marktführer bei E-Commerce-Kleinpaketen von China nach Südafrika. Du kannst eine Sendung auf [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html) prüfen oder deine `BUFZA`-Sendungsnummer oben einfügen.

## BUFFALO-Sendungsnummern (Format `BUFZA...YQ`)

Grenzüberschreitende E-Commerce-Sendungen von China nach Südafrika über BUFFALO tragen einen 17-stelligen Barcode, der mit `BUFZA` beginnt und mit `YQ` endet:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `BUFZA6482917305YQ` | Standardmäßige 17-stellige BUFFALO-Paketnummer China–Südafrika (`BUFZA` + 10 Ziffern + `YQ`). |
| `BUFZA4SK73918BUYQ` | Alternativer 17-stelliger grenzüberschreitender Paketcode im Format `BUFZA...BU...YQ`. |

Die Kontaktseite von BUFFALO leitet Auftragsnummern mit dem Präfix `BUFEX-` an ZA-BUFFALO weiter. Hast du deine Sendungsnummer verloren, rät die BUFFALO-FAQ dazu, sie beim Lieferanten zu erfragen oder dich in dein BUFFALO-Konto einzuloggen. Siehe [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die BUFFALO-Sendungsstatus bedeuten

Laut [BUFFALO-Support](https://www.buffaloex.com/support.html) und dem Tracking-Feed von BUFFALO durchlaufen Sendungen das China-Lager, den Exportflug, die südafrikanische Zoll- und Mehrwertsteuerzahlung, die Depots in Johannesburg/Regionen und die lokale Zustellung:

| BUFFALO meldet | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Order Confirmed` | Auftrag erstellt, während das Paket noch beim Lieferanten oder auf dem Weg ins BUFFALO-Lager in China ist. | „Etikett erstellt“ |
| `Enter Warehouse` | Das Paket ist im BUFFALO-Lager in China eingetroffen. | „Beim Paketdienst eingegangen“ |
| `Out of Warehouse`, `Air Shipping`, `International Shipping` | Hat das China-Lager von BUFFALO verlassen und wurde per Luft- oder Seefracht nach Südafrika versandt. | „Unterwegs“ |
| `Clearance`, `At Destination`, `Arrival at South Africa customs`, `Customs VAT and duty can be paid now, please pay in time`, `Tax Paid`, `Clearance finished` | Ausfuhrabfertigung in China vor dem Flug oder südafrikanische Einfuhrverzollung; Einfuhr-MwSt. und Zoll müssen vor der Freigabe bezahlt werden. | „Beim Zoll“ |
| `Inbound`, `AIR DC Received the parcel`, `BUFFALO Airport Warehouse received`, `Transit the parcel from AIRDC to JHBDC`, `JHB DC Received the parcel`, `Dispatching`, `WAYBILL IMPORTED`, `Transit to thirdparty Express`, `RETURNS` | Verzollt und unterwegs zwischen Flughafenlager (`AIR DC`) und Johannesburg-Depot (`JHB DC`), an einen südafrikanischen Partnerkurier übergeben oder zwischen Touren zurück im Depot. | „Unterwegs“ |
| `Driver Pick the Order`, `SCAN VEHICLE DEPARTURE` | Heute mit einem Fahrer von BUFFALO oder einem Partnerkurier in Auslieferung. | „In Zustellung“ |
| `Returned to BUFFALO warehouse[...]` | Nach fehlgeschlagener Zustellung durch den lokalen Kurier wieder in dem in Klammern genannten BUFFALO-Depot eingegangen; folge der SMS/E-Mail von BUFFALO oder kontaktiere den Kundenservice. | „Problem gemeldet“ |
| `Client Received` | An deiner Adresse zugestellt oder am Schalter abgeholt. | „Zugestellt“ |

## Südafrikanische Zoll-/MwSt.-Zahlung, Lieferzeiten und Adressänderungen

Laut [BUFFALO-Support](https://www.buffaloex.com/support.html) und [Über BUFFALO](https://www.buffaloex.com/about.html):

- **Einfuhr-MwSt. und Zoll:** Zeigt das Tracking `Customs VAT and duty can be paid now, please pay in time`, müssen Einfuhr-MwSt. und Zoll bezahlt werden, bevor der südafrikanische Zoll das Paket freigibt. Die Mehrwertsteuer- und Zoll-Videos von BUFFALO erklären Online- oder Banküberweisung und den Upload des Zahlungsbelegs; über das Anfrageformular lassen sich Fragen zu Steuerzahlungen sowie zu Ausweis- oder Importeurcodes klären. Siehe [Pakete beim Zoll](guide:customs).
- **Lieferzeiten & Laufzeit:** BUFFALO stellt in ganz Südafrika **Montag bis Samstag von 08:00 bis 17:00 Uhr** zu (keine Zustellung an Sonn- und Feiertagen). Sobald das Paket im Tracking unterwegs ist, rechnet BUFFALO je nach Adresse mit **1–5 Tagen** bis zur Zustellung.
- **Änderung von Adresse, Telefon oder Zollwert:** Laut BUFFALO-FAQ können deklarierter Wert, Menge und HS-Code **nicht** mehr geändert werden, sobald das Paket das chinesische Transitlager erreicht hat, und Lieferadresse oder Telefonnummer **nicht** mehr, nachdem das Paket in Südafrika eingetroffen ist.

## BUFFALO-Paket verspätet, zurück im Depot oder vermisst

- **Tracking aktualisiert nicht:** Laut BUFFALO-FAQ befindet sich das Paket auf dem Weg und hat den nächsten Scanpunkt noch nicht erreicht; der Kundenservice kann Näheres nennen. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Abendlicher `RETURNS`-Scan:** In Scans lokaler Kuriere über Tracking-Dienste erscheint `RETURNS` mitunter am Abend nach `SCAN VEHICLE DEPARTURE`: Das Paket kam nach einer Tour zurück ins Depot und ging am nächsten Morgen erneut raus. `Returned to BUFFALO warehouse[...]` folgt hingegen auf eine fehlgeschlagene Zustellung und erfordert Rückmeldung.
- **Als `Client Received` markiert, aber nicht da:** BUFFALO empfiehlt, zuerst bei Familie, Nachbarn und Wohnanlagen-/Gebäudesicherheit nachzufragen und dann den Kundenservice Südafrika (`support@buffaloex.com`) zu kontaktieren. Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel) und [zurück an Absender](guide:returned-to-sender).

## BUFFALO Kundenservice und Kontakt

Offizielle Kontaktdaten auf [BUFFALO Kontakt](https://www.buffaloex.com/contact.html):

- **Kundenservice Südafrika:** `support@buffaloex.com`, Telefon `+27-10-510-9307`, WhatsApp `+27-67-758-6614`.
- **Kundenservice China:** `service@bufflogistics.com`, Hotline `+86-400-921-9066`.
- **Anfrageformular & Status-FAQ:** [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) und [buffaloex.com/support.html](https://www.buffaloex.com/support.html).
- **Standorte:** Hauptsitz Shanghai (Room 301, Building A, Lane 1156, Shenbin South Road, Minhang District, Shanghai), Büro Guangzhou (Room 401, Building 3, Site 5, Greenland Airport International Center, Huadu District, Guangzhou) und Niederlassung Johannesburg in Südafrika.

> Vorsicht vor gefälschten SMS, die den südafrikanischen Zoll oder BUFFALO mit ähnlichen Weblinks nachahmen. Prüfe offene Mehrwertsteuer- oder Zollbeträge vor jeder Zahlung immer anhand deines Status auf [buffaloex.com/tracks.html](https://www.buffaloex.com/tracks.html). Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein BUFFALO-Paket mit Peek verfolgen

[Peek](/) erkennt BUFFALO automatisch als einzigen Kandidaten, sobald du einen 17-stelligen `BUFZA...YQ`-Code eingibst (`BUFZA6482917305YQ` oder `BUFZA4SK73918BUYQ`, erfunden), und erkennt ebenso eingefügte Links von `buffaloex.com/tracks.html?order=`, `buffaloex.com/track.html?order=` und `buffaloex.com/express/record?order=`. Musst du BUFFALO einmal manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Sendungsansicht auf „Paketdienst ändern“ (auch die Suche nach „Buffaloex“, „Buffalo Express“ oder „BUFFALO International“ findet den Dienst).

Peek hat keine direkte BUFFALO-Abfrage und verfolgt BUFFALO-Pakete über Tracking-Dienste. Dabei wertet Peek deren Scans nach den eigenen BUFFALO-Textregeln aus, sodass ein abendliches `RETURNS` auf „Unterwegs“ bleibt, `SCAN VEHICLE DEPARTURE` als „In Zustellung“ und `Returned to BUFFALO warehouse[...]` als „Problem gemeldet“ erscheint; jeder Scan wird mit seiner Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“, „Problem gemeldet“, „Zugestellt“) angezeigt.

Während Peek das Paket über Tracking-Dienste verfolgt, fragt Peek stündlich nach, alle 3 Stunden nach einem Tag ohne neuen Scan und am Tag der Zustellung bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei BUFFALO nach.“, samt Link „Hol dir Hilfe bei BUFFALO“ zur Kontaktseite von BUFFALO. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei BUFFALO nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder SARS-Zollgebühren bezahlen noch Importeurcodes übermitteln oder BUFFALO für dich kontaktieren.

## Häufige Fragen zur BUFFALO-Sendungsverfolgung

### Wie sieht eine BUFFALO-Sendungsnummer aus?

BUFFALO-Sendungsnummern von China nach Südafrika sind 17 Zeichen lang, beginnen mit `BUFZA` und enden mit `YQ` — etwa `BUFZA6482917305YQ` oder `BUFZA4SK73918BUYQ` (erfunden).

### Wann liefert BUFFALO in Südafrika aus?

Laut [buffaloex.com/support.html](https://www.buffaloex.com/support.html) liefert BUFFALO Montag bis Samstag von **08:00 bis 17:00 Uhr** (außer an Sonn- und Feiertagen) und rechnet mit **1–5 Tagen** Lieferzeit, sobald das Paket im Tracking unterwegs ist.

### Warum hängt mein BUFFALO-Paket beim südafrikanischen Zoll?

Zeigt das Tracking `Customs VAT and duty can be paid now, please pay in time`, sind Einfuhr-MwSt. und Zoll fällig, bevor der südafrikanische Zoll das Paket freigibt. Das Anfrageformular von BUFFALO beantwortet Fragen zu Steuerzahlungen sowie zu Ausweis- oder Importeurcodes.

### Wie erreiche ich den BUFFALO-Kundenservice in Südafrika?

Schreibe an `support@buffaloex.com` mit deiner `BUFZA...YQ`-Auftragsnummer oder sende eine Anfrage über [buffaloex.com/contact.html](https://www.buffaloex.com/contact.html) (China-Hotline `+86-400-921-9066`).

:::sources
- [BUFFALO International Logistics: Offizielle Website](https://www.buffaloex.com/) – grenzüberschreitendes Logistiknetz China–Afrika
- [BUFFALO: Über uns](https://www.buffaloex.com/about.html) – seit April 2017 tätig, Hauptsitz Shanghai, Niederlassung Johannesburg, 35.000 m² Lager (6.500 m² Zolllager), 300 Fahrzeuge, Zoll- und Bankenintegration
- [BUFFALO: Support & Tracking-FAQ](https://www.buffaloex.com/support.html) – offizielle Statusbedeutungen (`Order Confirmed` bis `Client Received`), Lieferzeiten Mo.–Sa. 08:00–17:00 Uhr, 1–5 Tage Laufzeit und Fristen für Adressänderungen
- [BUFFALO: Kontakt](https://www.buffaloex.com/contact.html) – `support@buffaloex.com`, `service@bufflogistics.com`, `+86-400-921-9066` und Büro-/Lageradressen (von Peek verlinkte Hilfeseite)
- [BUFFALO: Paket-Tracker](https://www.buffaloex.com/tracks.html) – offizielle Tracking-Seite
- [Universal Parcel Scraper: buffalo-ex](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/buffalo-ex) – `BUFZA...YQ`-Erkennungsregel, Link-Regeln `buffaloex.com`, Statuskatalog (`RETURNS`, `SCAN VEHICLE DEPARTURE`, `Returned to BUFFALO warehouse`), universelles Tracking-Routing und Hilfe-URL
:::
