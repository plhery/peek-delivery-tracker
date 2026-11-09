---
title: USPS Sendungsverfolgung: Nummer, Status, Paket aus den USA
description: USPS-Paket aus den USA verfolgen: wo die Trackingnummer steht, was die Status bedeuten, Zoll, verlorene Pakete und wie du USPS erreichst.
slug: usps-sendungsverfolgung
published: 2026-11-03
updated: 2026-11-09
---

USPS, der United States Postal Service, ist die Post der USA. Bei vielen Sendungen ist die Sendungsverfolgung (USPS Tracking) ohne Aufpreis dabei, auch bei vielen, aber nicht allen internationalen Produkten. Gib die Trackingnummer in USPS Tracking auf usps.com ein oder füg sie oben ins Feld ein; in den USA kannst du sie auch per SMS an 28777 schicken. Die Nummer bekommst du vom Absender, meist in der Versand-E-Mail des Shops.

## USPS Trackingnummer: wo sie steht und wie sie aussieht

Auf dem Etikett ist es die Ziffernfolge unter dem Strichcode. Als Empfänger fragst du den Absender: Postfilialen und das Kundencenter von USPS können sie nicht für dich heraussuchen. Wer in den USA Informed Delivery nutzt, sieht die Nummern ankommender Pakete in seinem Dashboard. Mehr in [Sendungsnummer finden](guide:find-tracking-number).

USPS-Nummern sind 13 bis 34 Zeichen lang. Die häufigsten, mit erfundenen Ziffern:

| Nummer (erfunden) | Was es ist |
| --- | --- |
| `9400 1000 0000 0000 0000 06` | 22 Ziffern, beginnt mit `94`, `95`, `93`, `92` oder `91`: das übliche Paketetikett in den USA. |
| `9205 5900 0000 0000 0000 0000 02` | 26 Ziffern, beginnt mit `92`. |
| `420 12345 9400 1000 0000 0000 0000 06` | Der ganze Strichcode, wie ein Scanner ihn liest: `420`, die US-Postleitzahl (ZIP Code), dann die Nummer. Den Teil mit der Postleitzahl kannst du weglassen. |
| `CP000000005US` | Eine internationale Postnummer: 2 Buchstaben, 9 Ziffern, `US`. Für internationale Reklamationen nennt USPS Nummern, die mit `EA` bis `EZ`, `CA` bis `CZ`, `HC` bis `HZ` oder `RA` bis `RZ` beginnen. |

Auch die Nummer unter dem Strichcode der Benachrichtigungskarte PS Form 3849, die ein Zusteller in den USA hinterlässt, funktioniert in USPS Tracking.

Manche Nummernformen nutzen auch andere Paketdienste: 22-stellige Nummern, die mit `9261` oder `9361` beginnen, etwa DHL eCommerce, und 20-stellige Nummern. Findet USPS nichts, schau, welchen Paketdienst die Versand-E-Mail nennt. Siehe [Sendungsnummer: Welcher Paketdienst steckt dahinter?](guide:tracking-number-formats)

## USPS Sendungsverfolgung: was die Status bedeuten

USPS schreibt seine Sendungsverfolgung auf Englisch. Diese Status erklärt USPS in seiner Hilfe; sie beschreiben den Weg bis zur Zustellung in den USA, in der Reihenfolge, in der ein Paket sie meist bekommt:

| USPS schreibt | Was es für dich heißt | In Peek |
| --- | --- | --- |
| `Shipping Label Created, USPS Awaiting Item` | Der Absender hat ein Etikett erstellt, USPS hat das Paket aber noch nicht. Fragen gehen an den Shop. | „Etikett erstellt“ |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Ein Versandpartner hat das Paket und übergibt es an USPS. Ein Lieferdatum gibt es, sobald USPS es hat. | „Etikett erstellt“ |
| `USPS in Possession of Item` | USPS hat das Paket angenommen oder abgeholt. Du musst nichts tun. | „Beim Paketdienst eingegangen“ |
| `Arrived at USPS Facility` | Es wird sortiert oder zur zustellenden Postfiliale gebracht. Die Zeile kann sich wiederholen, vom selben Ort oder an verschiedenen Tagen. | „Unterwegs“ |
| `Arrived at Post Office` | Es ist in der zustellenden Postfiliale und wird für die Zustellung vorbereitet. Laut USPS sollte es heute oder am nächsten Werktag der Post ankommen. | „Unterwegs“ |
| `Out for Delivery` | Ein Zusteller bringt es heute, vielleicht nicht der, der sonst deine Post bringt, und zu einer anderen Zeit oder später am Tag. | „In Zustellung“ |
| `Delivered` | Zugestellt, mit Datum, Uhrzeit und Ort. | „Zugestellt“ |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Bis 21:00 Uhr Ortszeit am Tag von `Out for Delivery` kam kein Zustellscan. USPS rechnet jetzt mit dem nächsten Werktag. | `Awaiting Delivery`: „Unterwegs“; `Delivery Status Not Updated`: keine eigene Stufe, Peek bleibt bei der vorigen, meist „In Zustellung“ |
| `No Access to Delivery Location` | Der Zusteller kam nicht an Briefkasten oder Tür. USPS versucht es am nächsten Werktag automatisch noch einmal. | „Zustellversuch“ |
| `Notice Left (No Authorized Recipient Available)` | Eine Unterschrift war nötig, und niemand war da. Der Zusteller hat die Benachrichtigung PS Form 3849 hinterlassen; einen zweiten Zustellversuch gibt es nicht automatisch. | „Zustellversuch“ |
| `Available for Pickup` | Nicht zustellbar: Du kannst es ab dem nächsten Werktag in der Postfiliale abholen. | „Abholbereit“ |
| `Alert` | Eine Verzögerung, etwa durch Wetter, Naturkatastrophen oder andere Ereignisse. | „Problem gemeldet“ |

Bei `Available for Pickup` lässt sich laut USPS keine erneute Zustellung buchen: Du holst das Paket mit Lichtbildausweis ab. `Notice Left (No Secure Location Available)` heißt, dass der Zusteller keinen sicheren Ablageort fand und das Paket zurück in die Postfiliale gebracht hat; auch hier gibt es keinen zweiten Zustellversuch von selbst. `Insufficient Address` und `No Such Number` heißen laut USPS, dass das Paket an den Absender zurückgeht, der es mit neuem Porto noch einmal schicken muss. In Peek zählen `No Such Number` und `Insufficient Address` als „Zustellversuch“. Geht die Sendung ins Ausland, heißt `Held at Delivery Depot/Delivery Office`, dass die Post im Zielland sie in ihrer Filiale zur Abholung für dich bereithält; die Zeile erscheint wenige Minuten nach der Ankunft dort. In Peek: „Abholbereit“. `Payment of charges - Item being held, addressee being notified` heißt, dass die Post im Zielland die Sendung zurückhält, bis der Empfänger die Einfuhrabgaben bezahlt hat, und ihn darüber benachrichtigt. In Peek: „Beim Zoll“.

USPS nennt die Uhrzeit eines Scans in der Ortszeit des Orts, an dem er passiert ist.

## Paket aus den USA: Zustellung in Deutschland, Österreich und der Schweiz

**Wer zustellt.** Ist die Nummer deines Pakets eine internationale Postnummer, die auf `US` endet und deren Prüfziffer stimmt, fragt Peek mit derselben Nummer auch die Post deines Landes: [DHL Paket](carrier:dhl) in Deutschland, die [Österreichische Post](carrier:austrian-post) in Österreich und die [Schweizerische Post](carrier:swiss-post) in der Schweiz. Als dein Land gilt in der iPhone-App die eingestellte Region, im Browser das Land, aus dem du das Paket hinzugefügt hast. Zeigt deine Post einen Scan, der mindestens so neu ist wie der neueste von USPS, verfolgt Peek das Paket dort weiter und behält den Verlauf von USPS. Bei Nummern nur aus Ziffern und in Liechtenstein fragt Peek keine andere Post.

**Zoll.** Ein Paket aus den USA muss durch den Zoll. Was du wem zahlst, steht in [Paket beim Zoll](guide:customs). In Deutschland und Österreich gilt, wie in der ganzen EU: Für Online-Bestellungen von außerhalb der EU bis 150 € fällt seit dem 1. Juli 2026 ein pauschaler Zoll von 3 € je Warenkategorie an. Seit dem 1. November 2026 kommt eine Bearbeitungsgebühr der EU (Union handling fee) von 2 € je Warenkategorie dazu, egal wie viel die Ware wert ist. Die Schweiz gehört nicht zur EU, dort gelten eigene Regeln.

**In den USA verpasst.** Nach einem [gescheiterten Zustellversuch](guide:missed-delivery) liegt die pfirsichfarbene Karte PS Form 3849 im Briefkasten. Die erneute Zustellung (Redelivery) ist kostenlos, aber nicht überall verfügbar: online, per Telefon, über den QR-Code der Karte oder indem du die ausgefüllte Karte in den Briefkasten legst. Du kannst sie einmal beantragen, nur an die ursprüngliche Adresse und ohne Wunschuhrzeit. Oder du holst das Paket mit Lichtbildausweis und Karte in der Postfiliale ab; das kann auch jemand anderes, wenn du auf der Karte unterschreibst und seinen Namen einträgst. Bei den meisten Paketen kommt nach 5 Tagen eine zweite und letzte Benachrichtigung, nach 15 Tagen geht das Paket zurück. Priority Mail Express geht 5 Tage nach dem ersten Versuch zurück; die meisten Pakete aus dem Ausland warten 30 Tage.

## USPS-Paket verspätet, verloren oder zurück

**Aus den USA zu dir.** Eine Nachforschung für eine internationale Sendung kann laut USPS nur der Absender in den USA online starten; USPS klärt sie mit der Post im Zielland. Hast du online bestellt, wende dich an den Shop. Einen Verlust kann der Absender bei Priority Mail Express International frühestens 3 und spätestens 90 Tage nach dem Versand melden, bei Priority Mail International und Registered Mail frühestens nach 7 Tagen und bis 6 Monate nach dem Versand. Für First-Class Package International Service ohne Einschreiben gibt es keine Online-Nachforschung. Mehr in [Paket verloren](guide:lost-parcel) und [Sendungsverfolgung aktualisiert sich nicht](guide:tracking-not-updating).

**Von dir in die USA.** Geht dein Paket in den USA verloren oder kommt es beschädigt an, steht die Entschädigung laut USPS dir als Absender zu, außer du trittst sie schriftlich an den Empfänger ab. USPS rät dem Empfänger, dich um eine Nachforschung bei der Post zu bitten, bei der du das Paket aufgegeben hast; ein beschädigtes Paket legt der Empfänger sofort in einer Postfiliale vor.

**In den USA.** Erst die Laufzeiten: Priority Mail braucht 2 bis 3 Werktage, Ground Advantage 2 bis 5, Parcel Select 2 bis 8; garantiert ist nur Priority Mail Express. Zugestellt wird montags bis samstags, laut USPS in der Regel bis 17:00 Uhr Ortszeit. Hat sich `Out for Delivery`, `Arrived at Post Office` oder `Delivery Status Not Updated` einen ganzen Werktag lang nicht bewegt, stell mit der Trackingnummer eine Serviceanfrage (service request) bei USPS. Ebenso bei `Delivered`, wenn das Paket mehr als einen Werktag später nicht vor der Tür, im Briefkasten, in der Garage, bei Mitbewohnern oder der Hausverwaltung ist; siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received). Ab 7 Tagen nach dem Versand können Absender oder Empfänger auf usps.com eine Suche nach verlorener Post (Missing Mail) starten, mit Adressen, Beschreibung und Fotos.

**Entschädigung im Inland.** Nur versicherte Sendungen. Absender oder Empfänger können reklamieren, brauchen aber den Einlieferungsbeleg. Schäden meldest du innerhalb von 60 Tagen nach dem Versand, einen Verlust ab 15 Tagen (bei Priority Mail Express ab 7) bis 60 Tage. Heb Karton und Inhalt auf, bis alles geklärt ist.

**Zurück.** Geht ein Paket an den Absender zurück, siehe [Paket zurück an Absender](guide:returned-to-sender).

## USPS Kundenservice: Telefonnummer und Kontakt

Stand 2. November 2026 nennt USPS auf seiner Kontaktseite „Contact Us“:

- **1-800-ASK-USPS** (1-800-275-8777), Montag bis Freitag 8:00 bis 20:30 Uhr, Samstag 8:00 bis 18:00 Uhr.
- **1-800-222-1811** für die Sendungsverfolgung im In- und Ausland, zu denselben Zeiten. USPS schreibt, dass die Mitarbeiter dieselben Daten sehen wie du in USPS Tracking.
- das Formular „Email Customer Service“ und einen Chat für Probleme mit usps.com.
- in den USA eine Vermittlung über 7-1-1 für gehörlose, schwerhörige und sprachbehinderte Menschen.

Automatische Auskünfte zur Sendungsverfolgung gibt es am Telefon laut USPS rund um die Uhr. Die Zeiten oben gelten an der Ostküste der USA (ET), meist 6 Stunden hinter der mitteleuropäischen Zeit: 8:00 Uhr ET ist dann 14:00 Uhr in Berlin, Wien und Zürich. Ob die 1-800-Nummern aus dem Ausland erreichbar sind, sagt USPS nicht. Für ein Paket aus den USA ist meist ohnehin der Absender dein Ansprechpartner.

Die Seiten von USPS, die hier zitiert sind, gelesen am 2. November 2026: auf faq.usps.com „Where is my package? Tracking Status Help“, „USPS Tracking - The Basics“, „How to find your tracking number“, „Redelivery - The Basics“, „What are the Second and Final Notice and Return Dates for Redelivery?“ und „Delayed mail and packages?“; auf www.usps.com „Missing Mail and Lost Packages“, „File a Claim“, „File a USPS Claim: International“ und „Contact Us“.

## USPS Paket verfolgen mit Peek

[Peek](/) erkennt USPS sofort an den meisten 22-stelligen Etikettnummern, deren Prüfziffer stimmt, an den meisten 26-stelligen Nummern mit `92` und an gescannten `420`-Strichcodes, deren Postleitzahl Peek entfernt. Internationale Nummern auf `US` erkennt Peek sofort, wenn sie mit `C` (außer `CE` und `CV`), `E`, `L` oder `R` beginnen und die Prüfziffer stimmt. Nummern mit `9261` oder `9361` und 20-stellige Nummern passen zu mehreren Paketdiensten: Dann zeigt Peek „Tracking-Dienste werden geprüft…“, fragt zuerst die anderen infrage kommenden Paketdienste und öffnet die Seite von USPS zuletzt, das kann einen Moment dauern. Erscheint USPS nicht, tipp auf „Ändern“ und wähl es unter „Passt zu dieser Nummer“, oder nutz später „Paketdienst ändern“ beim Paket. Einen Tracking-Link von usps.com mit der Nummer kannst du auch einfügen.

Peek öffnet die Tracking-Seite von USPS in einem Browser auf seinem Server. Das dauert etwas länger. Schickt USPS die Abfragen auf seine Störungsseite, fragt Peek Tracking-Dienste, die nicht immer den ganzen Verlauf haben. Peek zeigt den Status, den Verlauf mit dem Ort jedes Scans und den voraussichtlichen Liefertag. Die Uhrzeit eines Scans liest Peek in der Zeitzone des Bundesstaats, in dem er passiert ist, und zeigt sie in der Zeitzone deines Geräts. Den Namen dessen, der unterschrieben hat, und Zustellanweisungen lässt Peek weg. Die Scans zeigt Peek auf Englisch, so wie USPS sie schreibt; nur `Delivered` erscheint im Verlauf auf Deutsch, als „Zugestellt“. Kommt ein zurückgeschicktes Paket wieder beim Absender an, steht im Verlauf statt „Zugestellt“ auf Englisch „Returned to the sender“ (zurück beim Absender).

Peek schaut alle 30 Minuten nach, auch wenn das Paket in Zustellung ist oder ein Tracking-Dienst antwortet, und nach einer gescheiterten Abfrage erst nach einer Stunde wieder. Nach zwei Tagen ohne neuen Scan (nach 12 Stunden, solange es „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ zeigt) schaut Peek nur noch stündlich nach. Für Mitteilungen wählst du „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

Übergeben [UPS](carrier:ups) oder [FedEx](carrier:fedex) ein Paket an USPS, kann Peek es bei USPS weiterverfolgen, sobald USPS Fortschritt zeigt. Geht ein Paket mit internationaler Postnummer auf `DE`, `AT` oder `CH` in die USA, fragt Peek mit derselben Nummer auch USPS: sobald DHL oder die Schweizerische Post die USA als Ziel nennt, oder wenn dein Land für Peek die USA sind, etwa beim Empfänger dort. Die Österreichische Post nennt kein Ziel; bei ihr zählt nur dein Land. Für Nummern der Schweizerischen Post, die mit `L` beginnen, fragt Peek USPS nicht. Erneute Zustellung, Informed Delivery und Nachforschungen bleiben bei USPS: Kontaktieren kann Peek USPS oder den Shop nicht für dich.

## Fragen zur USPS Sendungsverfolgung

### Was bedeutet „Tracking Not Available“ bei USPS?

USPS findet zu der Nummer nichts: Sie ist unbekannt oder abgelaufen. Prüf jede Ziffer. Den Verlauf speichert USPS bei USPS Tracking 120 Tage, bei nummerierten internationalen Sendungen 2 Jahre.

### Kann man jedes Paket aus den USA verfolgen?

Nein. Laut USPS ist die Sendungsverfolgung bei vielen, aber nicht allen internationalen Produkten dabei, und je nach Zielland kann es Einschränkungen geben. Frag den Shop, ob dein Paket eine Trackingnummer hat.

### Liefert USPS auch sonntags?

Nur Priority Mail Express und Amazon-Pakete, schreibt USPS. Alles andere kommt montags bis samstags.

### Zeigt USPS eine genaue Lieferzeit?

Nein. USPS kann ein Zeitfenster von 2 Stunden nennen (Expected Delivery Window), das aber nicht garantiert ist, und weiß nach eigenen Worten nicht, wann ein Zusteller an einer Adresse ankommt. Peek zeigt den voraussichtlichen Tag und, wenn USPS eines nennt, auch das Zeitfenster.

:::sources
- [Europäische Kommission: EU customs reform (Englisch)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zoll je Warenkategorie bis 150 € seit 1. Juli 2026, 2 € Union handling fee seit 1. November 2026
- [Europäische Kommission: Customs Guidance on EUR 3 customs duty (Englisch)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – je Warenkategorie, Bearbeitungsgebühr unabhängig vom Wert
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformen, Wortlaute und ihre Stufen, Uhrzeiten, was Peek zeigt und wie es an die Post deines Landes übergibt
:::
