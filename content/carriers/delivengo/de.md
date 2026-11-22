---
title: Delivengo Sendungsverfolgung: Paket von La Poste verfolgen
description: Delivengo-Paket aus Frankreich verfolgen: FR-Sendungsnummer, La-Poste-Status, Zustellung in Deutschland, Österreich oder der Schweiz und Kontakt.
slug: delivengo-tracking
published: 2026-11-21
updated: 2026-11-21
---

Delivengo ist der Exportdienst der französischen [La Poste](carrier:la-poste) für kleine internationale Warensendungen. Versendet ein französischer Online-Shop deine Bestellung per Delivengo, transportiert La Poste das Päckchen bis zum Export aus Frankreich; die letzte Meile übernimmt die Landespost deines Ziellandes – in Deutschland [DHL](carrier:dhl) bzw. Deutsche Post, in Österreich die [Österreichische Post](carrier:austrian-post) und in der Schweiz die [Schweizerische Post](carrier:swiss-post). Du verfolgst die Sendung im Tracking von La Poste auf `laposte.fr` mit der Nummer aus dem Shop oder direkt oben im Eingabefeld.

## Deine Delivengo Sendungsnummer: Aufbau und Beispiele

Delivengo vergibt keine eigenen Sonderformate, sondern internationale Postnummern aus zwei Buchstaben, neun Ziffern (die letzte ist eine Prüfziffer) und der Länderkennung `FR`.

**Woher du sie bekommst.** Den Code teilt dir der Händler mit, meist in der Versandbestätigung per E-Mail. Das Portal `mydelivengo.laposte.fr` (MyDelivengo) ist ausschließlich das Geschäftskundenkonto des Versenders – Empfänger können dort keine Sendungen nachschlagen. Fehlt dir die Nummer, sieh unter [Sendungsnummer finden](guide:find-tracking-number) nach.

**Ähnlich aussehende Postnummern** (alle Ziffern frei erfunden):

| Beispiel (erfunden) | Zu welcher Post die Nummer gehört |
| --- | --- |
| `LD482910576FR` | [La Poste](carrier:la-poste): Delivengo, Colissimo und französisches Einschreiben/Tracking-Briefgut teilen sich dieses Muster. |
| `RR482910576DE` | [DHL](carrier:dhl) / Deutsche Post (Kennung `DE`), nicht Delivengo. |
| `RR482910576AT` | [Österreichische Post](carrier:austrian-post) (Kennung `AT`), nicht Delivengo. |
| `LL482910576CH` | [Schweizerische Post](carrier:swiss-post) (Kennung `CH`), nicht Delivengo. |

Die letzten beiden Buchstaben verraten die ausgebende Post. Innerhalb einer `…FR`-Nummer lässt sich Delivengo äußerlich nicht von anderen La-Poste-Sendungen unterscheiden – für die Verfolgung spielt das keine Rolle, da derselbe Datenfeed antwortet. Wegen der Prüfziffer macht schon ein Zahlendreher die Nummer ungültig, und Peek erkennt sie nicht mehr: kopiere sie am besten direkt.

## Delivengo Status: Was die Schritte von La Poste bedeuten

La Poste beantwortet Delivengo-Codes wie jede andere Postsendung. Peek ordnet jeden französischen Scan einer klaren Versandstufe zu:

| Stufe in Peek | Was die Meldung von La Poste bedeutet |
| --- | --- |
| „Etikett erstellt“ | Die Sendung ist elektronisch angekündigt: Der Shop hat das Label erzeugt, La Poste hat das Päckchen aber noch nicht. |
| „Beim Paketdienst eingegangen“ | La Poste hat die Sendung übernommen. |
| „Unterwegs“ | Unterwegs durch Frankreich, versandbereit ins Ausland oder bereits im Zielland eingetroffen. |
| „Beim Zoll“ | Dem Zoll vorgelegt (bei Sendungen außerhalb der EU-Zollunion); nach der Freigabe wechselt der Status wieder auf „Unterwegs“. |
| „In Zustellung“ | Am Zustellstandort für die heutige Tour eingeplant oder unterwegs zu dir. |
| „Zustellversuch“ / „Abholbereit“ | Zustellung war nicht möglich oder die Sendung liegt in einer Postfiliale bzw. Abholstelle bereit. |
| „Zugestellt“ | Zugestellt. Liegt nichts im Briefkasten oder vor Ort, lies [zugestellt, aber nicht erhalten](guide:delivered-not-received). |
| „Problem gemeldet“ | Störung (Beschädigung, Annahmeverweigerung, unvollständige Adresse, abgelaufene Abholfrist) oder laufender Rücktransport zum Händler. |
| „Zurück an Absender“ | Die Sendung ist wieder beim Shop eingetroffen. |

Nach dem Export aus Frankreich übernimmt die Zielpost die Zustellung; der Verlauf auf `laposte.fr` kann ab dem Ausgangsscan ohne weitere Einträge bleiben.

## Zustellung, Abwesenheit und Zoll in Deutschland, Österreich und der Schweiz

**Wer zustellt und was bei Abwesenheit gilt.** Nicht La Poste klingelt bei dir, sondern deine Landespost ([DHL](carrier:dhl)/Deutsche Post, [Österreichische Post](carrier:austrian-post), [Schweizerische Post](carrier:swiss-post)). Deren Regeln bestimmen Benachrichtigungskarte, Zweitzustellung und Lagerfrist in der Filiale; mehr unter [Zustellung verpasst](guide:missed-delivery).

**Zoll und Einfuhrsteuer.** Zwischen dem französischen Mutterland, Deutschland und Österreich fallen innerhalb der EU-Zollunion keine Zölle an. Ausnahmen gelten für Gebiete außerhalb des EU-Zoll- oder Mehrwertsteuergebiets: Helgoland und Büsingen auf deutscher Seite sowie französische Überseegebiete (Guadeloupe, Martinique, Französisch-Guayana, Réunion, Mayotte und Saint-Martin liegen außerhalb des EU-MwSt.-Gebiets, Frankreichs überseeische Länder und Gebiete auch außerhalb des Zollgebiets). In der **Schweiz** erhebt das BAZG auf Importe aus Frankreich 8,1 % Mehrwertsteuer (2,6 % ermäßigt u. a. für Bücher, Lebensmittel und Medikamente), zieht Abgabenbeträge bis 5 CHF jedoch nicht ein; siehe [Paket hängt beim Zoll](guide:customs).

## Delivengo-Sendung verspätet, steht still oder ging verloren

Dein erster Ansprechpartner ist immer der Online-Shop, der den Versand über sein Delivengo-Konto beauftragt hat.

**Tagelang kein Update.** Nach dem Export aus Frankreich kann La Postes Historie verstummen, während deine Landespost weiterbefördert. Prüfe dieselbe `…FR`-Nummer bei deiner Post und lies [Sendungsverfolgung aktualisiert nicht](guide:tracking-not-updating).

**Verloren, beschädigt oder zurückgesandt.** Bitte den Shop um Ersatz oder Erstattung. Für Käufer in der EU hält Your Europe fest, dass der Händler vom Versand bis zur Übergabe an dich für Transportschäden haftet; trifft die Ware nicht innerhalb von 30 Tagen (oder zum vereinbarten Termin) ein, setzt du eine angemessene Nachfrist und kannst danach vom Vertrag zurücktreten. Mehr unter [Paket verloren](guide:lost-parcel) und [zurück an den Absender](guide:returned-to-sender).

## Delivengo Kontakt

- **Zuerst der Shop**: Er führt das MyDelivengo-Konto und kann Nachforschungen anstoßen.
- **La Poste bei Beschwerden**: Laut der französischen Postregulierungsbehörde Arcep nimmt La Poste Reklamationen telefonisch unter `36 31` (Montag–Freitag 8:30–19:00 Uhr, Samstag 8:30–13:00 Uhr) oder unter `+33 810 821 821` (Montag–Freitag 8:00–19:00 Uhr französischer Zeit), über ihre Website, per Brief oder in einer Poststelle entgegen; Arcep weist darauf hin, dass Absender- und Empfängerrechte nicht identisch sind, und führt Delivengo nicht gesondert auf.
- **Deine Landespost**, sobald sich die Sendung mit derselben `…FR`-Nummer im Zielland befindet.

## Delivengo-Sendung mit Peek verfolgen

[Peek](/) nutzt für Delivengo keinen separaten Zugang, sondern liest das gemeinsame Tracking von [La Poste](carrier:la-poste). Fügst du eine gültige `…FR`-Nummer ein, erkennt Peek sofort „La Poste / Colissimo“; über „Ändern“ bzw. „Paketdienst ändern“ kannst du „Delivengo“ wählen – das Ergebnis ist identisch. Auch Tracking-Links von `laposte.fr` (einschließlich Links aus `mydelivengo.laposte.fr`) werden erkannt.

Peek zeigt Status, Verlauf mit dem Land jedes Scans sowie das voraussichtliche Lieferdatum, sofern La Poste eines angibt, und blendet deinen Namen sowie deine Adresse aus. Die Scan-Texte bleiben im französischen Wortlaut von La Poste; Uhrzeiten erscheinen in deiner Zeitzone („Uhrzeiten in deiner Zeitzone: …“). Sobald La Poste das Zielland oder einen Partner nennt, fragt Peek automatisch auch die dort unterstützte Post an – darunter [DHL](carrier:dhl), [Österreichische Post](carrier:austrian-post), [Schweizerische Post](carrier:swiss-post), [USPS](carrier:usps) oder [Canada Post](carrier:canada-post) – und wechselt bei neueren Scans unter Erhalt der bisherigen Historie dorthin.

Solange du das Paket geöffnet hast oder am Liefertag mit aktivierten Mitteilungen, prüft Peek den direkten Tracker bis zu alle 10 Minuten und bis zu alle 2 Minuten, sobald die Sendung „In Zustellung“ ist (an diesem Tag ab 6 Uhr Ortszeit, solange La Postes eigener Tracker antwortet); sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Über Tracking-Dienste prüft Peek an diesem Zustelltag unter denselben Bedingungen bis zu alle 15 Minuten, sonst stündlich bzw. alle 3 Stunden nach einem Tag ohne Update. Nach der Übergabe an deine Landespost folgt Peek deren Abfragetakt. Mitteilungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“.

## Häufige Fragen zur Delivengo Sendungsverfolgung

### Ist Delivengo dasselbe wie Colissimo?

Beide gehören zu [La Poste](carrier:la-poste) und teilen sich deren Sendungsverfolgung, sind aber getrennte Produkte: Delivengo ist der Geschäftskunden-Exportdienst für kleine Auslandssendungen.

### Warum stoppt mein Delivengo-Tracking nach Frankreich?

Nach dem Export stellt die Post deines Ziellandes zu, und La Poste erhält nicht von jedem Partner lückenlose Folgescans. Prüfe dieselbe `…FR`-Nummer bei [DHL](carrier:dhl), der [Österreichischen Post](carrier:austrian-post) oder der [Schweizerischen Post](carrier:swiss-post) – Peek erledigt diese Abfrage automatisch, sobald das Zielland feststeht.

### Wo melde ich ein verlorenes Delivengo-Päckchen?

Beim Online-Shop, bei dem du bestellt hast: Er hält den Versandvertrag über MyDelivengo und schuldet dir in der EU die unversehrte Lieferung bis zur Übergabe.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – La Postes Exportdienst, Zustellung durch die Zielpost, MyDelivengo-Kundenbereich und gemeinsames Tracking
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – S10-Nummernaufbau mit Prüfziffer, Ereigniscodes und Partnerübergabe
- [Europäische Union: Die EU-Zollunion in der Praxis](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_de) – keine Zölle im Warenverkehr zwischen EU-Staaten
- [Europäische Kommission: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Sonderstatus französischer Überseegebiete sowie von Helgoland und Büsingen
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und 5-CHF-Freigrenze
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – Händlerhaftung für Schäden bis zur Übergabe und 30-Tage-Lieferfrist
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – Beschwerdewege bei La Poste (36 31, +33 810 821 821, Zeiten und Rechte)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – was Peek ausliest und anzeigt
:::
