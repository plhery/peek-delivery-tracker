---
title: YDH Sendungsverfolgung: YDH...YQ & AZ-Codes & Hilfe
description: YDH-Express-Paket aus China verfolgen: YDH...YQ- und YDH...AZ-Nummern, Codes wie DSD und CARR, lokale Übergabe, Zoll und Kundenservice.
slug: ydh-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

YDH (*YDH Express* / *YDH Cross Border*, chinesisch *义达跨境*) ist ein 2005 gegründeter chinesischer E-Commerce-Logistikdienstleister mit Hauptsitz in Shanghai. Mit über 20 chinesischen E-Commerce-Drehkreuzen – darunter Shenzhen, Guangzhou, Hangzhou, Ningbo und Hongkong – sowie Niederlassungen in Japan, den USA, Großbritannien und Belgien befördert YDH Kleinsendungen und Handelsfracht in mehr als 200 Länder und Regionen und übergibt sie vor Ort an lokale Zusteller. Prüfe eine Sendung im [YDH-Tracking-Portal](https://track.ydhex.com/track_query.aspx) oder füge deine Nummer oben ein.

## YDH-Sendungsnummern: YQ- und AZ-Formate

Deine Frachtbriefnummer findest du in der Bestellübersicht des Marktplatzes oder in der Versand-E-Mail. YDH-Sendungsnummern sind 15 Zeichen lang, beginnen mit `YDH`, gefolgt von 10 Ziffern und enden auf `YQ` oder `AZ`:

| Format (erfunden) | Was es ist |
| --- | --- |
| `YDH4829173640YQ` | Grenzüberschreitende YDH-Paketnummer: `YDH` + 10 Ziffern + `YQ`. |
| `YDH4829173640AZ` | Grenzüberschreitende YDH-Paketnummer: `YDH` + 10 Ziffern + `AZ`. |

Nach der Einfuhrverzollung im Zielland übergibt YDH das Paket an einen lokalen Dienstleister: [USPS](carrier:usps) oder [GOFO](carrier:gofo) in den USA und [Poste Italiane](carrier:poste-italiane) in Italien gehören zu den Partnern, deren Scans YDH unter deiner `YDH`-Nummer weiterreicht. YDH nennt teils auch die eigene Nummer des lokalen Dienstes. Weitere internationale Muster findest du in [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was YDH-Sendungsstatus und -Codes bedeuten

YDH versieht eigene Lager-, Export- und Flugscans (auf Chinesisch oder Englisch) mit kurzen Statuscodes und gibt zusätzlich die Meldungen des lokalen Zustellpartners in dessen Wortlaut weiter.

| YDH-Code / Meldung | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Elektronische Auftragsdaten vom Verkäufer erhalten, bevor YDH das physische Paket scannt. | „Etikett erstellt“ |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Paket im YDH-Lager oder Sortierzentrum in China eingetroffen. | „Unterwegs“ |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Für den Lufttransport eingeplant, aus China abgeflogen (oder Flug verspätet) und am Zielflughafen gelandet. | „Unterwegs“ |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Im Zielland verzollt, an den lokalen Zusteller übergeben, wartend auf dessen ersten Scan (`D-SCAN DELAY`) oder von ihm abgeholt. | „Unterwegs“ |
| `OD` (`Out for Delivery`) | Vom Zustelldienst im Zielland für die Endzustellung geladen. | „In Zustellung“ |
| `DD` | Zustellproblem oder fehlgeschlagener Zustellversuch vom lokalen Partner gemeldet. | „Zustellversuch“ / „Problem gemeldet“ |
| `CC` | Vom Zielpartner zugestellt (mit Namen des Empfängers, sofern angegeben). | „Zugestellt“ |

**Was `D-SCAN DELAY` (`DSD`) bedeutet.** Übergibt YDH eine Sammelsendung an die Post oder einen Paketdienst im Zielland (`CARR`), vergehen oft mehrere Tage, bis der lokale Partner jedes Paket einzeln einscannt. `D-SCAN DELAY` markiert genau diese Übergabeverzögerung – das Paket ist weiter unterwegs und nicht verloren.

## So läuft der grenzüberschreitende YDH-Versand ab

Auf [ydhex.com](https://www.ydhex.com/) nennt YDH über 50 gebuchte Flug- und Schiffslinien sowie eigene Zollabfertigung in 12 Ländern. Die [Kontaktseite](https://www.ydhex.com/product/59/) listet Büros in Japan (Tokio, Narita, Osaka, Kansai und Nagoya), den USA (La Puente, Kalifornien), Großbritannien (Hounslow) und Belgien (Grâce-Hollogne bei Lüttich).

**Zustellung auf der letzten Meile.** YDH übernimmt Abholung im Exportland, Luftfracht und Verzollung und übergibt das Paket (`CARR`) an eine lokale Post oder einen Paketdienst in deinem Land für Haustürzustellung, Abholshop oder Rücksendungen. Zustellversuche, Ablageorte und Lagerfristen legt der lokale Dienst fest.

**Zoll und Einfuhrabgaben.** Grenzüberschreitende YDH-Bestellungen unterliegen Einfuhrumsatzsteuer, Zöllen und etwaigen lokalen Gebühren deines Landes, sofern sie nicht vom Verkäufer oder Marktplatz beim Kauf beglichen wurden. Siehe [Paket beim Zoll](guide:customs).

## YDH-Paket steckt fest, bewegt sich nicht oder kam nicht an

Da der Transportvertrag zwischen YDH und dem Händler oder Marktplatz besteht, wende dich bei Verspätungen, Adressfehlern oder Erstattungen immer zuerst an den Verkäufer oder die Plattform.

- **Funkstille während Luftfracht oder Übergabe:** Mehrtägige Pausen sind beim Warten auf Flugkapazität (`HBYP`), im Zoll (`RC`) oder vor dem ersten Scan des lokalen Partners (`DSD` / `D-SCAN DELAY`) normal. Siehe [Warum sich die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Lokale Nummer nach Übergabe nutzen:** Sobald YDH die Nummer des lokalen Zustellers nennt, bietet dessen Website oft Zustelloptionen, die YDH nicht zeigt.
- **Zugestellt, beschädigt oder vermisst:** Steht `CC` / zugestellt ohne Paket, frag zuerst im Haushalt, bei Nachbarn, am Empfang und beim lokalen Dienst nach und informiere rasch den Verkäufer. Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received), [Beschädigtes Paket](guide:damaged-parcel), [Verlorenes Paket](guide:lost-parcel) und [Zurück an Absender](guide:returned-to-sender).

## Kundenservice und Kontakte von YDH

Offizielle Büroadressen und Nummern auf der [Kontaktseite von YDH](https://www.ydhex.com/product/59/):

- **Kundenservice-Hotlines in China:** `800-988-1378` / `400-888-1378`.
- **Kundenservice Shanghai:** `(+86) 21-52277600` / `(+86) 21-52277688` (Zentrale: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Shanghai 201100, China).
- **Büro Hongkong:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Büro Japan (Tokio):** `+81 3-5644-5733`.
- **Büro Belgien (Lüttich):** `+32 470 588 200`.

> Vorsicht bei unverlangten SMS oder E-Mails, die eine kleine Kartenzahlung zum „Verzollen“ oder „Aktualisieren deiner Adresse“ für ein Paket aus China verlangen. Prüfe echte Abgabenforderungen immer auf deiner Marktplatz-Bestellseite oder der offiziellen Website deiner Landespost. Siehe [Betrügerische Paket-SMS](guide:delivery-scam-texts).

## YDH-Paket mit Peek verfolgen

[Peek](/) erkennt `YDH` + 10 Ziffern + `YQ` oder `AZ` (`YDH4829173640YQ` und `YDH4829173640AZ`, Ziffern erfunden) automatisch mit hoher Sicherheit als YDH sowie eingefügte Links `track.ydhex.com/track_query.aspx?track_number=`. Musst du YDH manuell wählen, tippe beim Hinzufügen auf „Ändern“ oder in der Paketansicht auf „Paketdienst ändern“ (die Suche nach „YDH Express“, „YDH Cross Border“ oder „义达跨境“ findet ihn ebenfalls).

Peek liest den YDH-Tracker direkt aus und zeigt die Phase („Etikett erstellt“, „Unterwegs“, „In Zustellung“, „Zustellversuch“, „Problem gemeldet“, „Zugestellt“), den Scan-Text – von YDH oder vom lokalen Dienst – samt Ort sowie das Zielland. YDH-Codes wie `CARR` und `DSD` bestimmen die Phase; Peek zeigt sie nicht an. YDH datiert Scans in China auf Shanghaier Zeit und Scans des lokalen Dienstes auf dessen Ortszeit; Peek rechnet sie auf dein Gerät um. Nennt YDH die eigene Nummer des lokalen Dienstes und kennt Peek diesen Dienst, prüft Peek, ob er das Paket hat: Die Ansicht zeigt dann „Zustellung durch“ mit dem Namen des Dienstes und listet beide Nummern. Der Name der unterzeichnenden Person erscheint in keiner Peek-App, und Peek lässt Kundenreferenz und Partner-Etikett weg. Der YDH-Tracker liefert kein voraussichtliches Lieferdatum, Gewicht, keinen Dienstnamen, keinen Abholpunkt und kein Zustellfoto.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag mit aktivierten Benachrichtigungen prüft Peek bis zu alle 10 Minuten; an dem Tag, an dem YDH `OD` meldet, alle 2 Minuten ab 6 Uhr nach der Uhrzeit des neuesten YDH-Scans, solange der YDH-Tracker antwortet. Sonst prüft Peek stündlich. Nach 12 Stunden mit nur „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft es unabhängig davon stündlich, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der YDH-Tracker nicht und verfolgt Peek das Paket allein über Tracking-Dienste, prüft es stündlich, nach einem stillen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiv sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Datum dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann: „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei YDH nach.“, mit dem Link „Hol dir Hilfe bei YDH“ zur Kontaktseite von YDH. Nach drei Wochen ohne neuen Scan heißt es: „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei YDH nach.“

Als Benachrichtigungsstufe kannst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur das Tracking und kann weder YDH kontaktieren noch verzollen oder beim lokalen Zusteller umleiten.

## Fragen zur YDH-Sendungsverfolgung

### Was ist YDH Express?

YDH (*义达跨境*) ist ein 2005 in Shanghai gegründeter grenzüberschreitender E-Commerce-Logistiker, der Kleinsendungen aus China in über 200 Länder befördert und dort an lokale Post- oder Paketdienste zur Endzustellung übergibt.

### Was bedeuten `YDH...YQ`- und `YDH...AZ`-Sendungsnummern?

Beide sind 15-stellige YDH-Express-Frachtbriefe aus `YDH`, 10 Ziffern und `YQ` oder `AZ` (zum Beispiel `YDH4829173640YQ` oder `YDH4829173640AZ`, Ziffern erfunden).

### Was bedeutet `D-SCAN DELAY` (`DSD`) bei YDH?

`D-SCAN DELAY` heißt, dass YDH das Paket an den lokalen Zusteller im Zielland übergeben hat, dieser aber noch keinen eigenen Eingangsscan gebucht hat. Das Paket ist weiter unterwegs und wartet auf die lokale Erfassung.

### Wie erreiche ich den Kundenservice von YDH?

Wende dich bei Erstattungen oder Zustellproblemen zuerst an Verkäufer oder Marktplatz. Für Rückfragen nennt YDH auf [ydhex.com/product/59/](https://www.ydhex.com/product/59/) die China-Hotlines (`800-988-1378` / `400-888-1378`), Shanghai (`+86 21-52277600` / `7688`), Hongkong (`+852 2690-1686`, `hk@ydhexpress.com`), Japan (`+81 3-5644-5733`) und Belgien (`+32 470 588 200`).

:::sources
- [YDH: Offizielle Website](https://www.ydhex.com/) – gegründet 2005, 20+ chinesische Hubs, 200+ Länder/Regionen, 50+ Flug- und Schifffahrtslinien, Zollabfertigung in 12 Ländern
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – Zentrale Shanghai, China-Hotlines (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), Büros in Hongkong, Japan, USA, UK und Belgien (Hilfe-URL in Peek)
- [YDH: Tracking-Portal](https://track.ydhex.com/track_query.aspx) – öffentliche YDH-Sendungsverfolgung
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – Erkennung `YDH` + 10 Ziffern + `YQ`/`AZ`, Linkregel `track.ydhex.com`, Meilensteincodes (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), Nummer des lokalen Zustellers, Scan-Zeitzonen und Hilfe-URL
:::
