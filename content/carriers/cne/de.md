---
title: CNE Express Sendungsverfolgung: 3A5V-Nummern & Status
description: Verfolge ein CNE-Express-Paket (递一物流) aus Festlandchina: 3A5V-Nummern, englische Statusmeldungen, lokaler Zusteller, EU-Zoll und Tracking mit Peek.
slug: cne-express-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

CNE Express (`递一物流`, `CNE EXPRESS CO LTD` / `递一国际物流有限公司`) ist ein 2003 in Shanghai (Festlandchina) gegründeter grenzüberschreitender Logistikdienstleister mit Linien in über 100 Länder und Regionen. Er verschickt Bestellungen von Händlern auf Amazon, eBay, Walmart, Wish, DHgate, TikTok, Temu, Shopify oder SHEIN aus Festlandchina per Flugzeug und übergibt sie im Zielland an lokale Partner. Prüfe deine Nummer im [englischen CNE-Tracker](https://www.cne.com/en/track) oder füge sie oben ein; in Peeks Katalog findest du den Dienst auch über `CNE` oder `递一物流`.

## CNE-Express-Sendungsnummer: Aufbau und Beispiele

CNE veröffentlicht kein festes Nummernmuster. Dieses Format erkennt Peek sofort als CNE Express (mit erfundenen Ziffern):

| Nummer (erfunden) | Bedeutung |
| --- | --- |
| `3A5V482910573` | `3A5V` und 9 Ziffern: eine Sendungsnummer von CNE Express. |
| `3A5V48291057` | Nur 8 Ziffern: nicht CNEs `3A5V`-Muster. Peek ordnet nicht CNE zu; da das Muster keines Paketdienstes passt, sucht Peek die Nummer nach dem Hinzufügen über Tracking-Dienste. Prüfe, ob eine Ziffer fehlt. |
| `LX482910576CN` | Zwei Buchstaben, 9 Ziffern und `CN`: eine Postsendung von [China Post](carrier:china-post), deren Tarife (*International E-packet*, *China Post Packet*) CNE ebenfalls vertreibt. |

Kleinschreibung, Leerzeichen und Bindestriche ignoriert Peek. CNEs eigener Tracker nimmt mehrere durch Leerzeichen oder Kommas getrennte Nummern gleichzeitig an.

**Wo du die Nummer findest.** In der Versandbestätigung des Händlers oder in deiner Bestellübersicht – siehe [Sendungsnummer finden](guide:find-tracking-number), [Temu-Bestellungen](guide:temu-orders) und [SHEIN-Bestellungen](guide:shein-orders). Unter `Shipment Information` kann CNEs Tracker außerdem die Bestellreferenz des Händlers (`Order number`) sowie die `Last mile number` des Zustellers samt Website und Telefon anzeigen.

## CNE-Express-Tracking-Status erklärt

Über den Verlaufszeilen zeigt CNEs Tracker einen Gesamtstatus auf Englisch:

| CNE-Status | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `Shipping` | Unterwegs. | den Status der neuesten Zeile |
| `Customs inspection` | Vom Zoll angehalten (`扣关` auf CNEs chinesischer Seite). | „Beim Zoll“ |
| `Delivered` | Zugestellt. | „Zugestellt“ |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | Falsche Adresse, verlorenes oder zerstörtes Paket oder eine sonstige Störung. | „Problem gemeldet“ |
| `Returned` | An den Verkäufer zurückgeschickt (`退件` auf Chinesisch). | „Zurück an Absender“ |
| `Not delivered` | Auf CNEs chinesischer Seite `未发送` („nicht verschickt“): Das Paket ist noch nicht abgegangen. | den Status der neuesten Zeile |

Auch `Time out` und `Unknown` kommen vor; Peek richtet sich dann nach den Verlaufszeilen. Jede Zeile nennt Uhrzeit, Wortlaut und Ort:

| CNE meldet | Was das für dein Paket bedeutet | Peek zeigt |
| --- | --- | --- |
| `Parcel Infomation Received` | Der Händler hat die Paketdaten übermittelt (Schreibweise von CNE); CNE hat das Paket noch nicht. | „Etikett erstellt“ |
| `Picked Up by Courier` | CNE hat das Paket beim Händler in Festlandchina abgeholt. | „Beim Paketdienst eingegangen“ |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | Eingang, Sortierung und Verladung in einem CNE-Zentrum. | „Unterwegs“ |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | Abfahrt aus einem Logistikzentrum. | „Unterwegs“ |
| `Arrived Export Airport`, `Released From Export Customs` | Am Abflughafen in Festlandchina, danach durch den Ausfuhrzoll. | „Unterwegs“ |
| `Shipment Departed From Original Country` | Der Flug hat Festlandchina verlassen. | „Unterwegs“ |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | Zwischenstopp an einem Transitflughafen. | „Unterwegs“ |
| `Arrived at Import Airport`, `Arrived at destination` | Im Zielland gelandet: Sortierung und Einfuhrzoll folgen erst noch (noch nicht in deiner Zustellbasis). | „Unterwegs“ |
| `The shipment is picked up by customs broker` | Ein Zollagent hat die Sendung zur Einfuhrverzollung übernommen. | „Beim Zoll“ |
| `Released From Import Customs`, `Pickup from airport` | Durch den Einfuhrzoll und vom Flughafen abgeholt. | „Unterwegs“ |
| `Handled by local carrier` | Von CNE an das Zustellnetz im Zielland übergeben; dessen eigener Eingangsscan folgt. | „Unterwegs“ |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | Beim Endzusteller oder in dessen Sortierzentrum eingetroffen. | „Unterwegs“ |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Im Zielland sortiert und an das lokale Zustelldepot vor der Tour weitergeleitet. | „Unterwegs“ |
| `The shipment has been processed in the parcel center of origin.` | Von CNE übernommener DHL-Sortierscan in Deutschland nach der Übergabe. | „Unterwegs“ |
| `Pendiente de recepción en CTT Express`, `Enviado`, `En reparto`, `Entregado` | Spanische Meldungen von [CTT Express](carrier:ctt-express) mit Erklärung hinter einem Komma: Vorankündigung („Etikett erstellt“, ohne Rückstufung nach Transportbeginn), Versand („Unterwegs“), Zustelltour („In Zustellung“) und Zustellung („Zugestellt“). | je nach Schritt |
| `On Board with Courier`, `Your shipment is out for delivery` | Im Zustellfahrzeug auf der heutigen Tour. | „In Zustellung“ |
| `Your shipment delivery attempt failed [Recipient not at home]` | Niemand zu Hause angetroffen; ein weiterer Versuch folgt. | „Zustellversuch“, oder „Problem gemeldet“, solange oben `Exception` steht |
| `Sorting failed []` | Sortierfehler (die leeren Klammern stammen von CNE). | „Problem gemeldet“ |
| `It was returned to customer's address by CNE` | Von CNE an die Adresse des Verkäufers zurückgesandt. | „Zurück an Absender“ |

Nach der Übergabe stehen Partnerzeilen oft als „Kurztext；Erklärung“ (oder mit Komma bei CTT Express): Peek wertet nur den Teil davor aus, belässt eine als `Shipping` codierte Partner-Annahme auf „Unterwegs“ und nutzt bei unbekanntem Wortlaut CNEs Statuscode. Ein abschließender oder blockierender Kopfstatus (`Delivered`, `Returned`, `Customs inspection`, `Invalid address`, `Lost`, `Exception`, `Destroyed`) überstimmt die letzte Zeile.

## Wer liefert CNE-Express-Pakete in Deutschland, Österreich, Luxemburg und der Schweiz?

CNE betreibt eigene Linien nach Europa, Amerika, Australien oder Brasilien und setzt für die letzte Meile auf lokale Partner: Die Partnerseite nennt unter anderem [DHL](carrier:dhl), [GLS](carrier:gls-de), [DPD](carrier:dpd-de), [Österreichische Post](carrier:austrian-post), [POST Luxembourg](carrier:post-luxembourg), [La Poste](carrier:la-poste), [Royal Mail](carrier:royal-mail), [Evri](carrier:evri), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane) und [USPS](carrier:usps).

Drei Subunternehmer-Codes von CNE ordnet Peek samt Transfernummer direkt einem Paketdienst zu: [DHL](carrier:dhl) (`DE DHL`), [Royal Mail](carrier:royal-mail) und [USPS](carrier:usps). Auf der Karte steht dann „Zustellung durch …“ (bzw. „Bei … angekündigt, noch nicht eingetroffen“, solange dort noch kein eigener Scan vorliegt). Bei anderen Codes mit Transfernummer (wie `US SPX`, dem kein einzelner Anbieter zugeordnet ist) fragt Peek bei einer internationalen Postnummer die Landespost des Ziellands ([DHL](carrier:dhl) in Deutschland, [Österreichische Post](carrier:austrian-post) in Österreich, [Schweizerische Post](carrier:swiss-post) in der Schweiz) oder den über das Nummernformat eindeutigen Dienst ab; ohne benannten Subunternehmer wird eine Zweitnummer als mögliche Händlerreferenz nicht weiterverfolgt. Zustellversuche und Abholstellen richten sich nach dem Endzusteller ([Zustellung verpasst](guide:missed-delivery)).

**Zoll in der EU (Deutschland, Österreich, Luxemburg) und der Schweiz.** In der **Europäischen Union** gilt für online außerhalb der EU gekaufte Waren in Paketen mit Sachwert bis 150 € seit dem 1. Juli 2026 ein EU-Zoll von 3 € pro Position (*per item*) und seit dem 1. November 2026 unabhängig vom Wert zusätzlich eine EU-Bearbeitungsgebühr (*Union handling fee*) von 2 € pro Position (gleiche Zolltarifnummer, Beschreibung und – falls erforderlich – gleicher Ursprung; über 150 € regulärer Zollsatz). Laut CNE-Mitteilung vom 22. Oktober 2026 zieht CNE auf seinen Routen in die 27 EU-Staaten seit dem 26. Oktober 2026 (`00:00` Pekinger Zeit) diese 2 € pro Paket und HS-Code vorab vom Händler ein – zusätzlich zu den seit Juli 2026 ebenso vorab erhobenen 3 € – und verzollt anhand der Händlerangaben. Zur Einfuhrumsatzsteuer in **Deutschland** (laut Europäischer Kommission ohne **Helgoland** und **Büsingen**, die weder zum EU-Zollgebiet noch zum EU-Mehrwertsteuergebiet gehören), **Österreich** und **Luxemburg** (fällig, sofern nicht bereits beim Kauf über IOSS beglichen) oder etwaigen Auslagenpauschalen des Zustellers sagt die Mitteilung nichts. In der **Schweiz** erhebt das BAZG 8,1 % Einfuhrsteuer (2,6 % ermäßigter Satz), wobei „Zollabgaben bis 5 Schweizerfranken nicht erhoben“ und „Steuerbeträge bis 5 Schweizerfranken nicht erhoben“ werden. Siehe [Paket hängt beim Zoll](guide:customs). Routenverzögerungen meldet CNE auf Chinesisch unter `新闻公告`.

## CNE-Express-Paket hängt fest, fehlt oder geht zurück

CNEs Auftraggeber sind die Händler: Kontaktiere bei Verspätung, Verlust, Schaden oder Rücksendung zuerst den Shop oder Marktplatz.

**„Warten auf CNE Express“.** Meldet CNE, dass der Auftrag nicht existiert (`订单不存在`), zeigt Peek „Warten auf CNE Express“; kennt CNE die Nummer auch nach zwei Tagen nicht und antwortet kein Tracking-Dienst, erscheint: „Auch nach zwei Tagen kennt der Paketdienst diese Nummer nicht. Prüfe sie, wähle einen anderen Paketdienst oder lösche das Paket.“ Bei `Not delivered` (`未发送`) oder `Parcel Infomation Received` frag nach einigen Tagen beim Händler nach.

**Vier Tage ohne Update.** Hat ein Paket unterwegs vier Tage keinen neuen Eintrag, nennt Peek den Tag des letzten Eintrags („Keine Aktualisierung seit Mo 12. Okt“) und zeigt den Hinweis auf Flüge, Zoll und Wochenenden (nach über drei Wochen ohne Neues rät er stattdessen, beim Verkäufer nachzufragen) sowie den Button „Hol dir Hilfe bei CNE Express“ zu CNEs Kontaktseite. Siehe [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating) und [Pakete aus China verfolgen](guide:tracking-from-china).

**Zoll, Störung, Rücksendung oder als zugestellt markiert.** Bei `Customs inspection` (`扣关`), `The shipment is picked up by customs broker`, `Lost`, `Destroyed`, `Invalid address` oder `Returned` wende dich an den Verkäufer ([Paket verloren](guide:lost-parcel), [Zurück an den Absender](guide:returned-to-sender)). Auf CNEs Tracker öffnet `View POD` den Zustellnachweis nach Eingabe von Empfängername oder Postleitzahl ([Zugestellt, aber nicht erhalten](guide:delivered-not-received)).

## CNE Express Kundenservice und Kontakt

Auf CNEs Seite [Contact us](https://www.cne.com/en/about/contact):

- **Hotline**: `400-021-5600` (*National Service Hotline*, Servicenummer in Festlandchina).
- **Hauptsitz Shanghai**: `021-5109 6677`, `15/F, Block G, Lane 1777, Hualong Road, QingPu District, Shanghai` (Festlandchina).
- **Zeiten**: `08:30-18:00 Mon-Sun` ohne genannte Zeitzone (die Sortierzentren in Jiaxing und Dongguan in Festlandchina arbeiten `7 x 24 Hours`).
- **Online-Formular**: *Consulting form* auf derselben Seite – als Typ `Complaint` wählen und Telefon, E-Mail, Name, Sendungsnummer sowie Nachricht eintragen (eine Support-E-Mail-Adresse nennt CNE nicht).

**Vorsicht vor Phishing-SMS.** Die offizielle Domain von CNE Express lautet `www.cne.com` (`cne.com`). Prüfe Zahlungs- oder Adresslinks vorab mit [Gefälschte Paket-SMS erkennen](guide:delivery-scam-texts).

## CNE-Express-Pakete mit Peek verfolgen

[Peek](/) erkennt CNE Express sofort an einer `3A5V`-Nummer („Erkannter Paketdienst“), und jeder `cne.com`-Link mit `?no=` legt das Paket unter CNE Express an (bei mehreren Nummern zählt die erste). Für andere 6- bis 40-stellige alphanumerische CNE-Nummern zeigt Peek bei mehreren Treffern „Tracking-Dienste werden geprüft…“ – sonst wähle über „Ändern“ unter „Alle Paketdienste“ CNE Express aus (oder später „Paketdienst ändern“); außerhalb von 6 bis 40 Buchstaben/Ziffern meldet Peek, dass CNE „vergibt keine Nummern wie diese“.

Peek liest Status, jeden Scan mit seinem Ort, das Zielland sowie den benannten Zusteller samt Transfernummer. `Arrived at destination` und `Handled by local carrier` liest Peek als Pekinger Zeit (`UTC+8`), weitergeleitete spanische [CTT-Express](carrier:ctt-express)-Zeilen in `UTC+02:00` (und auf Sendungen nach Portugal die Zustellschritte von [CTT](carrier:ctt) in `UTC+01:00`), die übrigen Zeilen auf der Uhr ihres Orts oder des Abschnitts, auf dem das Paket gerade war, und zeigt jede Zeit in deiner Gerätezeitzone („Uhrzeiten in deiner Zeitzone: …“); Bestellnummer, zeitzonenloses Lieferdatum im Kopf und `View POD` übernimmt Peek nicht (Lieferprognose und Gewicht fehlen bei CNE). „Website von CNE Express öffnen“ öffnet deine Nummer auf `cne.com`. Sobald der abgefragte Endzusteller einen mindestens ebenso aktuellen Scan wie CNE zeigt, wechselt Peek dorthin und behält CNEs Verlauf.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiv sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit am Tag mit Status „In Zustellung“ bis zu alle 2 Minuten, solange CNEs Tracker antwortet (nach Übergabe an den Endzusteller folgt Peek dessen Takt; über Tracking-Dienste allein: stündlich, alle 3 Stunden nach einem ruhigen Tag und bis zu alle 15 Minuten am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen); sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Benachrichtigungen: „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“. CNE oder den Händler kontaktieren kann Peek nicht.

## Häufige Fragen zur CNE-Express-Sendungsverfolgung

### Ist CNE Express seriös?

Ja. `CNE EXPRESS CO LTD` (`递一国际物流有限公司`) wurde 2003 in Shanghai (Festlandchina) gegründet, unterhält ein Betriebszentrum in Shenzhen sowie Sortierzentren in Jiaxing und Dongguan mit einer Tageskapazität von über 2 Millionen Sendungen seit 2020 und versendet für Händler auf Amazon, eBay, Walmart, TikTok, Temu und SHEIN.

### Wer stellt CNE-Express-Pakete zu?

Ein lokaler Post- oder Paketdienst, den CNE auswählt – auf seiner Partnerseite stehen etwa DHL, GLS, DPD, Österreichische Post, POST Luxembourg, La Poste, Royal Mail und Evri. Im CNE-Tracker kann dazu die `Last mile number` erscheinen.

### Was bedeutet `Arrived at destination` bei CNE Express?

Das Paket hat das Zielland erreicht, durchläuft aber noch Sortierung und Einfuhrzoll – es liegt noch nicht in deinem lokalen Zustelldepot. CNE stempelt diese Zeile in Pekinger Zeit (`UTC+8`, im Winter 7 Stunden vor Berlin, Wien oder Zürich), während benachbarte Auslandszeilen oft Ortszeit ohne Zeitzone tragen und dadurch vertauscht wirken können.

### Wie lange dauert der Versand mit CNE Express?

CNE nennt Händlern streckenabhängige Richtwerte: `3-8 business days` für *CNE Priority Express* (in die USA, nach Großbritannien, Frankreich und Deutschland) und `5-20 business days` für *CNE Standard Express*. Verlasse dich vorrangig auf die Angabe des Shops.

### Wie erreiche ich CNE Express?

Telefonisch über `400-021-5600` (Servicenummer in Festlandchina) oder `021-5109 6677` in Shanghai sowie über das *Consulting form* auf `cne.com/en/about/contact` mit der Auswahl `Complaint`. Bei Fragen zur Bestellung wende dich zuerst an den Verkäufer.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – englischer Tracker, Statusbezeichnungen, Shipment Information und View POD
- [CNE: 运单追踪 (Tracking Chinesisch)](https://www.cne.com/track) – chinesische Statuswerte 未发送 und 扣关
- [CNE: Home](https://www.cne.com/en) – Gründung 2003 in Shanghai, Zielmärkte, Plattformpartner und 400-Hotline
- [CNE: 首页 (Startseite Chinesisch)](https://www.cne.com/) – 递一物流 und 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – Sortierzentren Dongguan und Jiaxing, Kapazität über 2 Millionen Sendungen/Tag seit 2020
- [CNE: Partner](https://www.cne.com/en/about/partner) – globale Logistikpartner
- [CNE: Contact us](https://www.cne.com/en/about/contact) – 400-Hotline, Büro Shanghai, Zeiten 08:30-18:00 und Consulting form (Complaint)
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – von CNE angebotene China-Post-Produkte
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – Richtlaufzeit 3-8 business days und Routen US/GB/FR/DE
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – Richtlaufzeit 5-20 business days
- [CNE: 新闻公告 (Mitteilungen)](https://www.cne.com/news/notice) – Verzögerungshinweise nach Zielland
- [CNE: 关于欧盟国家2026年11月税改通知 (EU-Zollmitteilung)](https://www.cne.com/news/article/rDe4ANa6KYOW) – Vorabeinzug der 2 € ab 26. Oktober 2026 zusätzlich zu den 3 € pro Paket und HS-Code sowie Verzollung nach Händlerangaben
- [Europäische Kommission: EU-Zollreform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € Zollsatz seit 1. Juli 2026 und 2 € Bearbeitungsgebühr seit 1. November 2026
- [Europäische Kommission: Leitlinien zum 3-€-Zoll und zur Bearbeitungsgebühr (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € unabhängig vom Wert, Positionsbegriff und IOSS-Befreiung
- [Europäische Kommission: Territorialer Geltungsbereich von MwSt. und Zoll](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Helgoland und Büsingen außerhalb des EU-Zoll- und Mehrwertsteuergebiets
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und Nichterhebung bis 5 Franken
- [Universal Parcel Scraper: CNE Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cne) – 3A5V-Muster, ?no=-Links, Statuszeilen und Codes, Subunternehmer, Pekinger / CTT-Express- / Ortszeiten und Übergabe
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – China-Post-Erkennung sowie Wechsel zum Endzusteller
:::
