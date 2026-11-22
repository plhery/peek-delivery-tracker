---
title: Chunghwa Post (Taiwan Post) Tracking: TW-Nummern & Status
description: Chunghwa-Post-Paket aus oder nach Taiwan verfolgen: Aufbau der TW-Nummer, chinesische Statusmeldungen, Übergabe an DHL, Post AT oder Post CH und Zoll.
slug: chunghwa-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Chunghwa Post (中華郵政, Chunghwa Post Co., Ltd.) ist die staatliche Postgesellschaft Taiwans im Alleineigentum des Ministeriums für Verkehr und Kommunikation; neben Briefen und Paketen betreibt sie auch Postsparkonten und Lebensversicherungen. Eine Sendung lässt sich im offiziellen [Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) auf Englisch oder Chinesisch abfragen – oder direkt oben im Eingabefeld. Die Sendungsnummer steht auf dem Einlieferungsbeleg des Absenders; bei Bestellungen aus Taiwan erhältst du sie vom Händler. Das Portal verlangt einen Bildcode (Captcha) und speichert nur Sendungen der letzten sechs Monate.

## Chunghwa Post Sendungsnummer: Aufbau und Beispiele

Internationale Sendungen aus Taiwan tragen einen 13-stelligen Postcode aus zwei Buchstaben, neun Ziffern (die letzte ist eine Prüfziffer) und `TW`. Die Hinweise im Chunghwa-Post-Portal erklären den Anfangsbuchstaben. Beispiele mit frei erfundenen Ziffern:

| Beispiel (erfunden) | Versandart |
| --- | --- |
| `EE482910576TW` | `E`: EMS, der internationale Expressdienst (bzw. `EE`/`EZ` und `FT`/`FZ` wie `FT482910576TW` Richtung Festlandchina). |
| `RR482910576TW` | `R`: internationaler eingeschriebener Brief. |
| `LX482910576TW` | `L`: internationales ePacket (verfolgbare Kleinwarensendung bis 2 kg mit Zustellung an die Anschrift ohne Unterschrift). |
| `CP482910576TW` | `C`: internationales Postpaket. |
| `48291057364918` | 14 Ziffern (oder 20): Inlandssendung innerhalb Taiwans im nationalen Tracker von Chunghwa Post. |

Gewöhnliche internationale Päckchen mit dem Anfangsbuchstaben `U` (etwa `UH482910576TW`) lassen sich laut Chunghwa Post online nicht verfolgen. Fehlt dir die Nummer noch, hilft [Sendungsnummer finden](guide:find-tracking-number).

## Chunghwa Post Statusmeldungen auf Chinesisch erklärt

Diese Scans im traditionellen Chinesisch von Chunghwa Post ordnet Peek den jeweiligen Versandphasen zu:

| Chunghwa Post meldet | Bedeutung für deine Sendung |
| --- | --- |
| `交寄郵件` | Am Postschalter eingeliefert („Beim Paketdienst eingegangen“). |
| `運輸途中` | Unterwegs zwischen Ämtern und Sortierzentren von Chunghwa Post („Unterwegs“). |
| `離開進口局` | Hat ein Postamt auf dem Leitweg verlassen. |
| `到達出口互換局` | Im Ausgangs-Auswechslungsamt eingetroffen, das die Sendung ins Ausland weiterleitet. |
| `已出口－資料詳如右方` | Exportiert: Die Sendung hat Taiwan verlassen. |
| `郵袋接收` | Der Postsack mit der Sendung wurde übernommen. |
| `到達寄達國互換局` | Im Auswechslungsamt des Ziellandes angekommen. |
| `海關查驗` | Zur Zollprüfung vorgelegt („Beim Zoll“). |
| `離開寄達國互換局` | Hat das Auswechslungsamt des Ziellandes verlassen. |
| `到達寄達國投遞局` | Im Zustellpostamt des Ziellandes eingetroffen. |
| `安排投遞中` | Im Zustellamt eingetroffen und für die Zustelltour eingeplant. |
| `郵件投遞中` | Mit der Zustellung unterwegs („In Zustellung“). |
| `投遞不成功` | Zustellung fehlgeschlagen („Zustellversuch“). Grund oder eventuelle Rücksendung stehen in einer Bemerkungsspalte, die Peek ausblendet – prüfe dafür das Portal von Chunghwa Post. |
| `投遞成功` | Zugestellt („Zugestellt“). |

`安排投遞中` und `郵件投遞中` sind Zustellschritte von Chunghwa Post für Sendungen nach Taiwan. Bei Paketen aus Taiwan nach Europa stammen die letzten Schritte von der Post deines Ziellandes.

Meldet das Portal `查無此號碼資料！！` (keine Daten zu dieser Nummer), prüfe alle 13 Zeichen und ob die Einlieferung höchstens sechs Monate zurückliegt.

## Chunghwa-Post-Sendungen nach Deutschland, Österreich und in die Schweiz

Chunghwa Post befördert die Sendung aus Taiwan heraus; danach übernimmt die Landespost im Zielland unter derselben `TW`-Nummer. In der Auslandsliste des Chunghwa-Post-Trackers sind verlinkt:

- **Deutschland**: [DHL](carrier:dhl) (`dhl.de`) für EMS und internationale Pakete, Deutsche Post (`deutschepost.de`) für internationale Briefe.
- **Österreich**: [Österreichische Post](carrier:austrian-post) (`post.at`) für EMS, Pakete und Briefe.
- **Schweiz**: [Schweizerische Post](carrier:swiss-post) (`post.ch`) für EMS, Pakete und Briefe.

Sobald `已出口－資料詳如右方` erscheint, kannst du dieselbe `TW`-Nummer dort eingeben. Anders als etwa Frankreich, Italien, die Niederlande oder Großbritannien stehen Deutschland, Österreich und die Schweiz nicht auf Chunghwa Posts Liste jener dreizehn Länder, die das Online-Tracking für internationale Einschreiben eingestellt haben. Abholfristen und Benachrichtigungskarten richten sich nach der jeweiligen Landespost; mehr unter [Zustellung verpasst](guide:missed-delivery).

**Besonderheit bei EMS nach Deutschland.** Wegen Verzögerungen bei der Datenübermittlung mit der Deutschen Post blendet Chunghwa Post auf ihrer eigenen Website derzeit keine Detailscans für EMS-Sendungen nach Deutschland ein und verweist direkt auf `dhl.de`. In den chinesischen Hinweisen ergänzt Chunghwa Post, dass die Seite der Deutschen Post sowohl bei der endgültigen Zustellung als auch bei der Übergabe an den lokalen Zoll zur Prüfung den Status `Delivered` anzeigt.

**Zoll und Einfuhrabgaben.** Taiwan gehört weder zur EU noch zum Schweizer Zollgebiet:

- **Deutschland und Österreich (EU)**: Einfuhrumsatzsteuer fällt ab dem ersten Euro an (sofern sie nicht schon beim Kauf über IOSS eingezogen wurde). Seit dem 1. Juli 2026 gilt für Online-Bestellungen von außerhalb der EU bis 150 € ein pauschaler EU-Zollsatz von 3 € pro Warenart; seit dem 1. November 2026 kommt unabhängig vom Bestellwert eine EU-Bearbeitungsgebühr von 2 € pro Warenart hinzu. Helgoland und Büsingen gehören weder zum Zoll- noch zum Mehrwertsteuergebiet der EU.
- **Schweiz**: Laut Bundesamt für Zoll und Grenzsicherheit (BAZG) beträgt die Einfuhrsteuer 8,1 % (2,6 % reduzierter Satz etwa für Bücher, Lebensmittel oder Medikamente); Steuer- und Zollbeträge bis 5 CHF werden nicht erhoben (entspricht rund 62 CHF Warenwert inklusive Versand bei 8,1 % bzw. 193 CHF bei 2,6 %).

Details findest du unter [Paket hängt beim Zoll](guide:customs).

## Paket nach Taiwan senden: Zustellung und Zoll

Sendungen aus Europa nach Taiwan stellt Chunghwa Post zu. Jedes importierte Paket durchläuft laut Chunghwa Post die taiwanische Zollkontrolle; ob Abgaben anfallen, entscheidet allein der Zoll. Taipei Customs nennt für Postpakete drei Stufen:

- **Bis 2.000 NT$** (CIF-Wert aus Ware, Versicherung und Fracht): zoll- und steuerfrei.
- **2.000 NT$ bis 5.000 US$**: Der Zoll lässt die Abgaben in der Regel bei der Zustellung durch das Postamt gegen ein *Duty Memo for Foreign Imports by Parcel Post* einziehen.
- **Über 5.000 US$** (FOB-Wert): Empfänger fertigen die Sendung direkt beim Zollamt ab und begleichen die Abgaben dort.

Streng reglementiert oder verboten sind laut Taipei Customs unter anderem Medizinprodukte (etwa Hyaluronsäure-Präparate oder Kosmetikgeräte), Pflanzensamen, fleischhaltige Waren, Tabakerhitzer, E-Zigaretten und Markenpiraterie.

## Sendungsverfolgung steht still, Paket verspätet oder verloren

**Keine neuen Scans nach `已出口－資料詳如右方`.** Die Sendung ist unterwegs ins Zielland oder liegt dort beim Eingangszoll; bei EMS nach Deutschland zeigt Chunghwa Post planmäßig keine weiteren Details an. Prüfe dieselbe Nummer bei [DHL](carrier:dhl), der [Österreichischen Post](carrier:austrian-post) oder der [Schweizerischen Post](carrier:swiss-post) und lies [Sendungsverfolgung aktualisiert nicht](guide:tracking-not-updating).

**Nachforschung bei Verlust oder Verspätung.** Einen schriftlichen Nachforschungsantrag kann der Absender mit dem Original-Einlieferungsbeleg beim Aufgabepostamt stellen: bei EMS innerhalb von **4 Monaten** ab dem Folgetag der Einlieferung, bei Paketen und Einschreiben innerhalb von **6 Monaten**. Nach Ablauf dieser Frist kann Chunghwa Post die Nachforschung ablehnen und leistet keine Entschädigung. Bitte daher den Verkäufer rechtzeitig darum und siehe [Paket verloren](guide:lost-parcel).

**Als zugestellt gemeldet, aber nicht da.** In Europa stellt deine Landespost zu – prüfe dort den Zustellnachweis (und beachte bei EMS nach Deutschland den Zoll-Hinweis oben). Weiteres unter [zugestellt, aber nicht erhalten](guide:delivered-not-received).

## Chunghwa Post Kontakt und Kundenservice

Alle Uhrzeiten gelten in Taiwan-Zeit (`UTC+8`, ohne Sommerzeit):

- **24-Stunden-Kundenservice**: `0800-700-365` (innerhalb Taiwans vom Festnetz, Taste `3` für Englisch). Vom Mobiltelefon gilt die kostenpflichtige Nummer `(04) 2354-2030` – international `+886 4 2354 2030` (ohne führende `0` der Ortsvorwahl).
- **Eingehende Auslandspost nach Taiwan**: `(03) 3180-700`, Durchwahl `2431` (EMS) und `2432` (Pakete) Montag bis Freitag außer an Feiertagen von 8:00 bis 12:00 und 13:30 bis 16:30 Uhr; Durchwahl `2430` (Luftpost-Einschreiben) von 8:00 bis 12:00 und 13:00 bis 16:00 Uhr.
- **Ausgehende Luftpostpakete ab Taiwan**: `(03) 383-3776`, Durchwahl `271` oder `272`, Montag bis Freitag außer an Feiertagen von 8:00 bis 16:30 Uhr.
- **Online**: Formular *Opinion Box* auf [Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) mit Antwort innerhalb von 7 Tagen (Sendungsnummer sowie Namen und Anschriften von Absender und Empfänger angeben und die Bestätigungs-E-Mail anklicken).

## Chunghwa-Post-Paket mit Peek verfolgen

[Peek](/) erkennt 13-stellige Codes aus zwei Buchstaben, neun Ziffern und `TW` sofort als Chunghwa Post, wenn der erste Buchstabe `C`, `E`, `L` oder `R` ist (oder das Präfix `FT` bzw. `FZ` lautet) und die Prüfziffer stimmt; bei falscher Prüfziffer kontrolliere den Beleg. Tracking-Links mit `CSController?MAILNO=` funktionieren ebenfalls. Eine `UH…TW`-Nummer ordnet Peek anhand der Form der internationalen Briefpost zu (über „Ändern“ bzw. „Paketdienst ändern“ lässt sich Chunghwa Post manuell auswählen), während andere `U…TW`-Codes und 14- oder 20-stellige Inlandsnummern vom Chunghwa-Post-Adapter abgelehnt werden („vergibt keine Nummern wie diese“).

Peek liest das internationale Tracking von Chunghwa Post und zeigt jeden Scan im chinesischen Originalwortlaut samt bearbeitendem Amt (chinesische Amtsnamen werden nach Möglichkeit dem Ort zugeordnet). Eigene Scans aus Taiwan interpretiert Peek in `Asia/Taipei` und rechnet sie in deine Zeitzone um („Uhrzeiten in deiner Zeitzone: …“); Auslandsscans liest Peek in der Ortszeit des angegebenen Landes und rechnet sie ebenfalls um. Ein voraussichtliches Lieferdatum oder Gewicht liefert Chunghwa Post nicht, und die Bemerkungsspalte lässt Peek weg.

Bei einer `TW`-Nummer fragt Peek zusätzlich die Landespost des Landes ab, aus dem du nachsiehst – [DHL](carrier:dhl) aus Deutschland, die [Österreichische Post](carrier:austrian-post) aus Österreich, die [Schweizerische Post](carrier:swiss-post) aus der Schweiz – und wechselt unter Beibehaltung der bisherigen Historie dorthin, sobald aktuelle Scans vorliegen. Umgekehrt fragt Peek bei Sendungen mit Zielangabe Taiwan automatisch Chunghwa Post unter deiner Einlieferungsnummer an, sofern sie nicht mit `U` beginnt.

Solange du die Sendung geöffnet hast oder am Liefertag mit aktivierten Mitteilungen, prüft Peek den direkten Tracker bis zu alle 10 Minuten und bis zu alle 2 Minuten, sobald das Paket „In Zustellung“ ist (an diesem Tag ab 6 Uhr Ortszeit, solange Chunghwa Posts eigener Tracker antwortet); sonst sowie nach zwei Tagen ohne Neuigkeiten (12 Stunden bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“) stündlich. Über Tracking-Dienste prüft Peek an diesem Zustelltag unter denselben Bedingungen bis zu alle 15 Minuten, sonst stündlich bzw. alle 3 Stunden nach einem Tag ohne Update. Nach der Übergabe an die Zielpost gilt deren Abfragetakt. Benachrichtigungen lassen sich auf „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ stellen; Nachforschungen bei Chunghwa Post kann Peek nicht für dich einreichen.

## Häufige Fragen zur Chunghwa Post Sendungsverfolgung

### Ist Chunghwa Post dasselbe wie Taiwan Post?

Ja. Chunghwa Post (中華郵政) ist die nationale Post Taiwans, und Peek findet sie unter beiden Namen. Ihre internationalen Sendungsnummern enden auf `TW`.

### Warum zeigt Chunghwa Post bei meinem EMS-Paket nach Deutschland keine Details?

Wegen einer Übertragungsverzögerung mit der Deutschen Post blendet Chunghwa Post detaillierte EMS-Scans nach Deutschland vorübergehend aus und verweist mit derselben `E…TW`-Nummer auf [DHL](carrier:dhl). Beachte dabei, dass die Deutsche Post laut Chunghwa Post auch die Übergabe an den deutschen Zoll als `Delivered` kennzeichnen kann.

### Was bedeutet `海關查驗`?

Die Sendung liegt zur Beschau beim Zoll („Beim Zoll“). Welche Einfuhrumsatzsteuer, EU-Pauschalen (3 € Zoll bis 150 € und 2 € Bearbeitungsgebühr je Warenart) oder Schweizer Freigrenzen gelten, erklärt [Paket hängt beim Zoll](guide:customs).

### Wie lange sind Chunghwa-Post-Nummern abrufbar?

Das Online-Portal zeigt nur Sendungen der letzten sechs Monate. Schriftliche Nachforschungen muss der Absender bei EMS binnen 4 Monaten, bei Paketen und Einschreiben binnen 6 Monaten ab dem Tag nach der Einlieferung stellen.

:::sources
- [Chunghwa Post: Company Profile](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=1637282383451) – staatliches Postunternehmen, Brief-/Paketdienst, Postsparen und Lebensversicherung
- [Chunghwa Post: Tracking Service](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=351402) – Übersicht der Tracking-Zugänge
- [Chunghwa Post: Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) – Captcha, 6 Monate Abruffrist, E/R/L/C/U-Nummern, Nachforschungsfristen, EMS-Hinweis Deutschland, Zielland-Links, 14-/20-stellige Inlandsnummern
- [Chunghwa Post: 國際e小包 (ePacket)](https://www.post.gov.tw/post/internet/Postal/index.jsp?ID=1499060698896) – 2-kg-Grenze, Zustellung ohne Unterschrift, kein Nachforschungsanspruch
- [Chunghwa Post: 國際郵件查詢電話](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160101) – Telefonnummern, Durchwahlen und Sprechzeiten für Auslandspost
- [Chunghwa Post: English home page](https://www.post.gov.tw/post/internet/U_english2/) – Hotline 0800-700-365, Mobilnummer und +886-Schreibweise
- [Chunghwa Post: 客服專線語音操作說明](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160103) – 24-Stunden-Hotline, Auswahl 3 für Englisch
- [Chunghwa Post: Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) – Opinion Box, 7 Tage Antwortfrist und Pflichtangaben
- [Chunghwa Post: 國際包裹 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020307&defaultAllOpen=1&sn=24FA12A9-11A6-4365-A8F5-C154C379AF02) – Zollprüfung jedes Importpakets in Taiwan
- [Chunghwa Post: 郵件查詢的期限 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020315&defaultAllOpen=1&sn=44431DCF-5197-43D5-95B6-5EFE13F5D67B) – Ausschluss von Nachforschung und Entschädigung nach Fristablauf
- [Taipei Customs: International Parcel](https://web.customs.gov.tw/etaipei/singlehtml/1342) – Wertgrenzen 2.000 NT$ und 5.000 US$, Duty Memo
- [Taipei Customs: Cross-border online purchases](https://web.customs.gov.tw/etaipei/singlehtml/1373?cntId=1a74ff69fdd0483085a6a8c9eda54b07) – Einfuhrbeschränkungen und -verbote
- [Your Europe: Versand und Lieferung](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_de.htm) – 3 € EU-Zoll pro Warenart bei Bestellungen bis 150 €
- [Europäische Kommission: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – Anwendung des 3-€-Zolls seit 1. Juli 2026
- [Europäische Kommission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 2 € EU-Bearbeitungsgebühr pro Warenart seit 1. November 2026
- [Europäische Kommission: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Zoll- und MwSt.-Status von Helgoland und Büsingen
- [BAZG: Empfangen von Briefen und Paketen](https://www.bazg.admin.ch/de/empfangen-von-briefen-und-paketen) – Schweizer Einfuhrsteuer 8,1 % / 2,6 % und 5-CHF-Freigrenze
- [Universal Parcel Scraper: Chunghwa Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chunghwa-post) – Nummernerkennung, chinesische Statuscodes und Postübergabe
:::
