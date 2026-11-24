---
title: Thailand Post Sendungsverfolgung: TH-Codes, EMS & 1545
description: Verfolge ein Thailand-Post-Paket (ไปรษณีย์ไทย): 13-stellige TH-S10-Codes, nationale & EMS-Status, Nachnahme (COD), Zoll und Contact Center 1545.
slug: thailand-post-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

**Thailand Post** (*Thailand Post Co., Ltd.*, thailändisch *บริษัท ไปรษณีย์ไทย จำกัด*) ist der nationale Postbetreiber Thailands ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Das Unternehmen übernimmt nationale Briefe, Einschreiben, EMS, Same Day, schwere LogisPost-Sendungen und Nachnahme (COD) innerhalb Thailands sowie internationale [EMS](carrier:ems)-World-, Registered-, ePacket-, Courier-Post- (bis 200 kg) und Courier-Post-Plus-Sendungen weltweit. Die öffentliche Sendungsverfolgung liegt auf [track.thailandpost.co.th](https://track.thailandpost.co.th/), internationale Hinweise stehen auf [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Du kannst eine thailändische Postsendung dort prüfen oder deine 13-stellige `TH`-Sendungsnummer oben einfügen.

## Thailand Post Sendungsnummern (13-stelliges `TH`-S10-Format)

Verfolgte nationale und internationale Sendungen von Thailand Post tragen 13-stellige Weltpostcodes (UPU S10): zwei Großbuchstaben für den Dienst, 8 laufende Ziffern, 1 S10-Prüfziffer und das Länderkürzel `TH`:

| Format (erfunden) | Was es ist |
| --- | --- |
| `EE482917367TH` | 13-stellige [EMS](carrier:ems)-Expresssendung von Thailand Post (`EE` + 9 Ziffern mit gültiger S10-Prüfziffer + `TH`). |
| `RR739184627TH` | 13-stelliges Einschreiben oder Päckchen von Thailand Post (`RR` + 9 Ziffern mit gültiger S10-Prüfziffer + `TH`). |

Inlands-Express/EMS, Einschreiben (`R...TH`), ePacket (`L...TH`) und nationale Paketpräfixe (`E`, `R`, `C`, `W` usw. mit Endung `TH`) folgen alle derselben 13-stelligen S10-Prüfziffernstruktur. Unverfolgte Standardbriefe ohne 13-stelligen Barcode lassen sich online nicht verfolgen. Siehe den Leitfaden zu [Sendungsnummern-Formaten](guide:tracking-number-formats).

## Bedeutung der Thailand Post Statusmeldungen

Der Tracker von Thailand Post kombiniert UPU-nahe numerische `statusID`-Codes mit englischen Beschreibungen (und thailändischen Filialnamen) — sowohl für Scans in Thailand als auch für übermittelte internationale EDI-Scans ausländischer Postgesellschaften:

| Thailand Post Code & Wortlaut | Was er für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `3` `Posting/Collection` | Am Schalter von Thailand Post eingeliefert oder beim Absender abgeholt. | „Beim Paketdienst eingegangen“ |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | In einem internationalen Auswechslungsamt für Export oder Import bearbeitet. | „Unterwegs“ |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | Unterwegs zwischen Brief-/Paketzentren und Ankunft im örtlichen Zustellpostamt. | „Unterwegs“ |
| `31` `Item out for physical delivery` | Heute mit dem Postzusteller von Thailand Post auf Zustelltour. | „In Zustellung“ |
| `57` `Contact recipient` | Der Zusteller versuchte, den Empfänger anzurufen (keine Ortsbewegung; behält die aktuelle Stufe). | Behält vorherige Stufe |
| `32` `Item arrival at collection point for pick-up (by recipient)` | In einem Thailand-Post-Paketfach oder Abholpunkt zur Abholung hinterlegt. | „Abholbereit“ |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (Gruppe 4) | Zustellversuch fehlgeschlagen (z. B. Empfänger nicht angetroffen oder Zoll-/COD-Betrag offen). | „Zustellversuch“ |
| `28` `Return to the origin post office` | Ausnahme vermerkt; Sendung geht zurück Richtung Ursprungspostamt. | „Problem gemeldet“ |
| `35/L` `The sender received the returned item` | Zurückgesandtes Paket wieder beim Absender eingegangen. | „Zurück an Absender“ |
| `35/S` `Final delivery` | An den Empfänger zugestellt (bei Nachnahme kann `36` `Successfully transfer money to seller` folgen, ohne die Zustellzeit zu ändern). | „Zugestellt“ |

## Nachnahme (COD), internationale Übergaben und Zoll

- **Internationale Exporte (`...TH` ins Ausland):** Nach `15` (`Departure From Outward OE`) übernimmt die nationale Post deines Landes — etwa [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post oder [Australia Post](carrier:australia-post) — Einfuhrverzollung und Zustellung und meldet EDI-Scans an Thailand Post zurück.
- **Zoll und Einfuhrumsatzsteuer:** Pakete aus oder nach Thailand unterliegen den Zoll-, Steuer- und Abfertigungsregeln des Ziellandes (einschließlich EU-Abgaben auf Nicht-EU-Bestellungen). Meldet der Tracker `34` (`Item held, addressee notified due to Payment of charges`), prüfe Briefkasten oder örtliches Postamt auf eine Zahlungs- oder Zollbenachrichtigung. Siehe den Leitfaden zu [Paketen beim Zoll](guide:customs).
- **Nachnahme (COD) in Thailand:** Bei nationalen COD-Sendungen setzt Thailand Post `35/S` (`Final delivery`), sobald der Empfänger annimmt und zahlt, gefolgt von `36` (`Successfully transfer money to seller`) nach Auszahlung an den Verkäufer.

## Thailand-Post-Paket verspätet, festgehalten oder verschwunden

- **Stille Phase nach Abgang aus Thailand:** Internationale Luftpost (besonders Einschreiben `R...TH` und Economy-Pakete) pausiert häufig mehrere Tage oder Wochen zwischen `Departure From Outward OE` und `Arrival at inward office of exchange`. Dieselbe 13-stellige `TH`-Nummer auf der Website deiner Landespost liefert oft zusätzliche Zoll- oder Zustellhinweise. Siehe [stehengebliebene Sendungsverfolgung](guide:tracking-not-updating).
- **Verlorene, beschädigte oder zurückgesandte Sendungen:** Wende dich zuerst an den Absender oder Marktplatz und bewahre deinen 13-stelligen Barcode samt Einlieferungsbeleg auf. Siehe auch [verlorenes Paket](guide:lost-parcel), [beschädigtes Paket](guide:damaged-parcel), [zugestellt, aber nicht erhalten](guide:delivered-not-received) und [zurück an den Absender](guide:returned-to-sender).

## Kundenservice von Thailand Post und Contact Center 1545

Offizielle Supportwege auf [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) und [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Thailand):** **`1545`** (allgemeine Fragen, Nachforschungen und Beschwerden).
- **Offizielles Kontakt- und Anfrageportal:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Online-Tracker & internationale Hinweise:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) und [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Vorsicht vor gefälschten SMS oder LINE-Nachrichten, die für ein Thailand-Post-Paket eine kleine Zahlung über einen fremden Link fordern. Prüfe Tracking oder Zollgebühren ausschließlich auf [track.thailandpost.co.th](https://track.thailandpost.co.th/) oder über das THP Contact Center `1545`. Siehe [Betrugs-SMS zu Paketen](guide:delivery-scam-texts).

## Thailand Post mit Peek verfolgen

[Peek](/) erkennt Thailand Post automatisch als einzigen Kandidaten bei jedem gültigen 13-stelligen `TH`-S10-Code (`EE482917367TH` oder `RR739184627TH`, erfunden) und erkennt eingefügte Links `track.thailandpost.co.th/?trackNumber=` direkt. Willst du manuell wechseln (etwa um nach Ankunft im Ausland direkt deine Landespost abzufragen), tippe beim Hinzufügen auf „Ändern“ oder auf der Paketseite auf „Paketdienst ändern“.

Peek ruft den Tracker von Thailand Post ab — sowie dessen zweiten Server bei Ausfall des ersten — und zeigt die Stufe („Beim Paketdienst eingegangen“, „Unterwegs“, „In Zustellung“, „Abholbereit“, „Zustellversuch“, „Problem gemeldet“, „Zurück an Absender“, „Zugestellt“), den englischen Wortlaut des Scans, das Postamt und die Zustellzeit. Die Statuscodes bestimmen die Stufe und bleiben in jeder Peek-App ausgeblendet; der Name des Adressaten steht auf deiner eigenen Paketseite unter „Empfänger“, niemals auf einem geteilten Link, und keine App nennt den Namen des Unterzeichners. Eigene Scans von Thailand Post tragen Bangkoks Zeitzonenversatz. Ein EDI-Scan einer ausländischen Post enthält deren Ortszeit, die Thailand Post wie Bangkok-Zeit abspeichert — Peek datiert ihn daher auf die Zeitzone jenes Amtes (Tokio-Zeit für `TOKYO INTERNATIONAL`), einen Scan ohne Ort nach dem Nachbar-Scan, und zeigt alle Zeiten in der Uhrzeit deines Geräts an. Fahrernamen, Telefonnummern, Unterschrift und maskierte Kontakthinweise blendet Peek aus; ein geschätztes Lieferdatum nennt Thailand Post nicht.

Solange du das Paket in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem Thailand Post `31` (`Item out for physical delivery`) meldet, ab 6 Uhr auf der Uhr des neuesten Scans alle 2 Minuten, solange der Tracker von Thailand Post antwortet. Andernfalls prüft Peek stündlich. Nach 12 Stunden mit lediglich „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek stündlich, egal wie du die Sendung verfolgst, und nach sieben Tagen auf „Etikett erstellt“ alle 6 Stunden. Antwortet der Tracker von Thailand Post nicht und Peek verfolgt das Paket allein über Tracking-Dienste, prüft Peek stündlich, nach einem ruhigen Tag alle 3 Stunden und an dem Tag, an dem das Paket in Zustellung ist, bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo 12. Okt“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Thailand Post nach.“ samt dem Link „Hol dir Hilfe bei Thailand Post“ zur Kontaktseite von Thailand Post. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Thailand Post nach.“

Du kannst Benachrichtigungen für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek überwacht nur Sendungsaktualisierungen und kann weder verzollen noch Nachnahmebeträge zahlen oder Thailand Post für dich kontaktieren.

## Fragen zur Sendungsverfolgung bei Thailand Post

### Wie sieht eine Thailand-Post-Sendungsnummer aus?

Verfolgte Sendungen von Thailand Post nutzen 13-stellige UPU-S10-Codes mit zwei Dienstbuchstaben, 9 Ziffern (mit Prüfziffer am Ende) und `TH` — zum Beispiel `EE482917367TH` für EMS oder `RR739184627TH` für Einschreiben (erfunden).

### Was bedeutet Code `36` (`Successfully transfer money to seller`)?

Bei thailändischen Nachnahme-Bestellungen (COD) bestätigt Code `36`, dass Thailand Post den kassierten Betrag nach der endgültigen Zustellung (`35/S`) an den Verkäufer überwiesen hat. Es ist ein Finanzereignis und keine Paketbewegung.

### Warum aktualisiert sich mein Thailand-Post-Paket nach `Departure From Outward OE` nicht mehr?

Das Paket hat das thailändische Ausgangs-Auswechslungsamt verlassen und befindet sich im internationalen Lufttransport oder wartet auf die Eingangsregistrierung beim Postbetreiber des Ziellandes.

### Wie erreiche ich den Kundenservice von Thailand Post?

Rufe in Thailand das THP Contact Center unter **`1545`** an oder nutze das offizielle Kontaktportal auf [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, nationale/internationale EMS-, Einschreiben-, Courier-Post-, LogisPost- und COD-Dienste
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – offizieller Kundenservice, Beschwerdeportal und Hotline `1545` (von Peek verlinkte Hilfeseite)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – offizielle öffentliche Online-Sendungsverfolgung
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – internationale Tracking- und Servicehinweise
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – `TH`-S10-Prüfziffer, Dual-Host-Ausfallsicherheit, UPU-`statusID`-Zuordnung (`3`–`36`, `57`), Bangkok- vs. ausländische EDI-Zeiten und Empfängerdaten
:::
