---
title: ZTO Express Sendungsverfolgung: 中通快递-Nummer und Status
description: ZTO-Express-Paket (中通快递) in China verfolgen: Aufbau der Sendungsnummern, Bedeutung von 已签收 und weiteren Status, Paketstationen, Laufzeiten und Kontakt.
slug: zto-express-sendungsverfolgung
published: 2026-11-21
updated: 2026-11-21
---

ZTO Express (`中通快递`, Zhongtong) ist ein chinesischer Expressdienst, der am 8. Mai 2002 in **Shanghai** gegründet wurde und 2025 insgesamt 38,52 Milliarden Pakete beförderte. Eine Sendungsnummer von ZTO Express gehört normalerweise zu einem Paket innerhalb **Festlandchinas** – häufig einer Online-Bestellung (seit 2008 transportiert ZTO Taobao-Pakete) auf dem Weg zu einer Adresse in China oder zum Sammellager eines Weiterleiters. Du kannst die Sendung auf dem [chinesischen Tracker von ZTO](https://www.zto.com/check) oder oben im Suchfeld verfolgen. Auslandssendungen der Gruppe laufen über ZTO International (`中通国际`) mit eigenen `771`-Nummern unter `ztoglobal.com`.

## ZTO-Express-Sendungsnummer: Aufbau und Beispiele

ZTO nennt den Code Frachtbriefnummer (`运单号`). Peek kennt für ZTO Express zwei Muster. Beispiele mit frei erfundenen Ziffern:

| Nummer (frei erfunden) | Bedeutung |
| --- | --- |
| `784920573280` | 12 Ziffern: Frachtbriefnummer von ZTO Express – ein Format, das auch [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [DHL](carrier:dhl), [BRT](carrier:brt) und andere nutzen. |
| `73184920573649` | 14 Ziffern mit `7` am Anfang: ebenfalls ZTO Express, geteilt mit [DPD Deutschland](carrier:dpd-de), [Hermes](carrier:hermes-de), [SEUR](carrier:seur), [BRT](carrier:brt) und weiteren Diensten. |
| `77148291057364` | `771` plus 11 Ziffern: das Format von ZTO International (`中通国际`). Hier fragt Peek zuerst ZTO International ab. |

Leerzeichen stören in Peek nicht. Der eigene Tracker von ZTO nimmt bis zu 10 Nummern gleichzeitig entgegen, getrennt durch Kommas oder Leerzeichen (`最多可查询10条，以逗号、空格隔开`).

**Wo du sie findest.** Der Absender (meist der Shop) behält den Absenderdurchschlag (`发件联`) und teilt dir die Nummer in den Versanddetails mit. Nicht gefunden? Siehe [Sendungsnummer finden](guide:find-tracking-number).

**Auf der ZTO-Website.** Gib die Nummer auf `zto.com/check` ein und klicke auf `查询` (Suchen). Zuvor kann ein Verifizierungs-Puzzle erscheinen; über den QR-Code auf derselben Seite lassen sich Status-Updates per WeChat abonnieren.

## Statusmeldungen von ZTO Express erklärt

Jeder Scan trägt bei ZTO eine kurze chinesische Phasenbezeichnung, die Peek auf Englisch anzeigt:

| ZTO meldet | Peek zeigt | Peek-Status | Was das für dein Paket bedeutet |
| --- | --- | --- | --- |
| `已取件` | `Collected` | „Beim Paketdienst eingegangen“ | Ein ZTO-Kurier hat die Sendung beim Absender abgeholt. |
| `已收件` | `Received` | „Beim Paketdienst eingegangen“ | Eine ZTO-Niederlassung hat das Paket entgegengenommen. |
| `运输中` | `In transit` | „Unterwegs“ | Unterwegs zwischen Sortierzentren und Niederlassungen von ZTO. |
| `派件中` | `Out for delivery` | „In Zustellung“ | Ein ZTO-Zusteller hat das Paket auf seiner Tour dabei. |
| `已代收` | `Held at a collection point for the recipient` | „Abholbereit“ | Eine Abholstelle wie eine Paketstation bewahrt das Paket zur Abholung auf. |
| `已签收` | `Signed for` | „Zugestellt“ | Jemand hat quittiert (`签收`): du selbst, eine vertretungsberechtigte Person oder das Empfängerlager. |
| `异常通知` | `Exception notice` | „Problem gemeldet“ | ZTO meldet eine Störung. Peek zeigt nur die Kurzbezeichnung; der vollständige Satz steht im ZTO-Tracker. |

Andere Einträge wie Rücksendungen (`退回件`) oder Umleitungen behalten in Peek ihren chinesischen Wortlaut. Mehr zu Versandphasen unter [Was Tracking-Status bedeuten](guide:tracking-statuses).

**An einen Weiterleiter adressiert?** Dann bedeutet `已签收`, dass das chinesische Lager des Weiterleiters das Paket angenommen hat und der Inlandsabschnitt von ZTO Express beendet ist – ab dort gilt die neue internationale Sendungsnummer des Weiterleiters. ZTO betreibt zudem einen eigenen internationalen Sammelversand (`中通国际集运`), der mehrere Pakete im Lager bündelt und gemeinsam verschickt (in China erreichbar über `95311` mit Weiterleitung zu International, `95311转国际`).

## Zustellung durch ZTO Express in China: Kuriere, Stationen und Paketfächer

Die folgenden Regeln gelten für Sendungen innerhalb **Festlandchinas**:

Ein ZTO-Kurier liefert an die Adresse oder legt das Paket in einer Paketstation (`驿站`), einer Annahmestelle (`代收点`) oder einem smarten Paketfach (`智能快件箱`) in der Nähe ab – der Servicevertrag von ZTO erlaubt diese Zustellarten. Chinas *Verwaltungsvorschriften für den Expressmarkt* (`快递市场管理办法`), in Kraft seit dem 1. März 2024, schreiben jedoch vor, dass für Paketfächer und Stationen deine Zustimmung vorliegen muss, und geben dir das Recht, das Paket bei Übergabe anhand des Frachtbriefs zu prüfen sowie bei sichtbar beschädigter Verpackung oder zerbrechlichem Inhalt hineinzuschauen oder die Annahme zu verweigern.

Bei nicht persönlich übergebenen Paketen (Haustür, Paketfach, Station) empfiehlt ZTOs Vertrag, Abholung, Öffnen und Inhaltsprüfung per Video (`录制视频`) festzuhalten; eine vereinbarungsgemäße Zustellung gilt als bestätigt, wenn binnen drei Tagen (`三日`) kein schriftlicher Einspruch erfolgt.

Aus den offiziellen FAQ von ZTO für China:

- **Kein Abholcode für das Paketfach erhalten?** Suche über die [Niederlassungssuche von ZTO](https://www.zto.com/service/station) (`服务网点查询`) die zuständige Station und rufe dort an, oder nenne die Nummer unter `95311`, um die Telefonnummer des Kuriers oder der Niederlassung zu erfragen.
- **Gebühr wegen langer Lagerzeit im Paketfach verlangt?** Laut ZTO tippst du beim Abholen am Bildschirm auf Überspringen (`跳过`) und zahlst nichts.
- **Zustellgebühr verlangt?** Die ausliefernde Niederlassung darf vom Empfänger keinerlei Zustellgebühr verlangen (`派件网点不可以向收件客户收取任何派送费`).
- **Adresse außerhalb des Zustellgebiets?** Hole das Paket selbst in der Niederlassung ab (`自提`) oder bitte den Absender um Weiterleitung mit einem anderen Dienst.
- **Falsche Adresse?** Sobald das Paket in Zustellung oder zugestellt ist, lässt sich die Adresse nicht mehr ändern; vorher muss der Absender bei der Einlieferungsstation anfragen.

## ZTO-Paket aktualisiert sich nicht, ist verloren oder geht zurück

**Noch kein Eintrag.** Findet der ZTO-Tracker nichts unter der Nummer, gleiche sie mit dem Verkäufer ab. Solange das Paket unter ZTO Express gespeichert ist, zeigt Peek „Warten auf ZTO Express“; ohne zugewiesenen Dienst steht dort „Noch kein Sendungsverlauf“.

**Keine neuen Scans.** Hat ein noch reisendes Paket seit 4 Tagen keinen neuen Scan erhalten, blendet Peek den Hinweis „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei ZTO Express nach.“ sowie den Link „Hol dir Hilfe bei ZTO Express“ zur Kontaktseite von ZTO ein. Siehe [Warum sich das Tracking nicht aktualisiert](guide:tracking-not-updating) und [Paket aus China verfolgen](guide:tracking-from-china).

**Verloren oder beschädigt (in China).** Wende dich an den Verkäufer: Nach dem Vertrag von ZTO muss der Absender (oder der von ihm benannte Berechtigte) binnen 30 Tagen (`三十日`) ab Kenntnis schriftlich reklamieren. Ohne Transportversicherung (`保价`) ersetzt ZTO höchstens 500 Yuan (`500元`), mit Versicherung bis zum versicherten Wert. Fehlender oder beschädigter Inhalt bei unversehrter Außenverpackung ist ausgeschlossen. Siehe [Paket verloren](guide:lost-parcel).

**Zurückgeschickt.** ZTO darf ein Paket zurücksenden, wenn die Adresse unklar ist, die Annahme verweigert wird oder zwei Zustellversuche ohne Verschulden von ZTO scheitern (`两次派送未成功的`). Siehe [Zurück an den Absender](guide:returned-to-sender).

**Als zugestellt gemeldet, aber nicht da.** `已代收` heißt, dass eine Abholstelle das Paket bereithält; `已签收` bedeutet, dass jemand unterschrieben hat (Empfang, Station oder Weiterleiter-Lager). Für Beschwerden in China nenne die Frachtbriefnummer beim Kundenservice unter `95311` oder nutze das [Beschwerdeformular von ZTO](https://www.zto.com/after/complaint/problemTypes) (`投诉反馈`, Login nötig); hilft das nicht oder bleibt eine fristgerechte Antwort aus, erlauben Chinas Vorschriften eine Schlichtungsbeschwerde (`申诉`) bei der Postaufsichtsbehörde (`邮政管理部门`). Siehe [Zugestellt, aber nicht erhalten](guide:delivered-not-received).

## ZTO Express Telefonnummer und Kundenservice

Offizielle Kontakte von ZTO Express in **China**:

- **`95311`:** landesweite Kundenhotline (`全国统一客服热线`, auf der englischen ZTO-Seite *Customer Service Hotline*), laut FAQ erreichbar von `08:30` bis `17:30` Uhr chinesischer Zeit.
- **`95720`:** feste ausgehende Rufnummer, von der aus ZTO-Kuriere Empfänger anrufen (`中通业务员专属外呼号码`).
- **Online-Kundenservice** (`在线客服`) auf `zto.com` sowie WeChat-Miniprogramm (`微信小程序`), offizielles WeChat-Konto und ZTO-App.

Für Fragen zur Bestellung selbst ist der jeweilige Shop dein erster Ansprechpartner.

## ZTO-Express-Paket mit Peek verfolgen

Füge die Nummer in [Peek](/) ein – unterstützt werden über 3.500 Paketdienste. Eine allein eingefügte ZTO-Nummer veranlasst Peek nicht, automatisch ZTO Express abzufragen: Peek prüft zunächst andere Dienste mit demselben Ziffernmuster (etwa [DHL](carrier:dhl) und [BRT](carrier:brt) bei 12 Ziffern; DPD, [Chronopost](carrier:chronopost), [Hermes](carrier:hermes-de), [SEUR](carrier:seur) und [BRT](carrier:brt) bei 14 Ziffern; zuerst ZTO International bei `771`-Nummern) und danach Tracking-Dienste mit dem Hinweis „Tracking-Dienste werden geprüft…“. Führen diese das Paket unter ZTO Express, speichert Peek es dort und fragt beim nächsten Lauf ZTO direkt ab; sonst zeigt die Zeile „noch nicht gefunden · Peek prüft weiter“. Damit Peek sofort den Tracker von ZTO Express liest, tippe auf „Ändern“ und wähle **ZTO Express** unter „Passt zu dieser Nummer“ oder suche nach `ZTO` bzw. `中通快递` (`中通` allein zeigt auch ZTO International); bei bereits gespeicherten Paketen über „Paketdienst ändern“. ZTO Express akzeptiert in Peek nur 12 Ziffern oder 14 Ziffern mit `7` am Anfang – bei anderen Nummern erscheint „vergibt keine Nummern wie diese“.

Peek liest den eigenen Tracker von ZTO aus und öffnet einen Browser, sobald ZTO eine Verifizierung verlangt. Pro Scan zeigt Peek die englische Phasenbezeichnung (Tabelle oben), die Stadt bzw. den chinesischen Stationsnamen sowie die Uhrzeit in deiner Geräte-Zeitzone (auf peektracker.com sowie in der iPhone- und Android-App steht darunter „Uhrzeiten in deiner Zeitzone: …“). Den vollständigen Begleittext jedes Scans samt Kurierkontakten, Telefonnummern sowie Empfängername und -adresse lässt Peek weg; ein voraussichtliches Lieferdatum gibt es nicht. Da ZTOs öffentlicher Web-Tracker frühe Auftragszeilen ausblenden kann, fehlen diese dann auch in Peek. Eine Weiterleiter-Nummer für den Auslandsversand legst du als eigenes Paket an.

Solange du das Paket geöffnet hast oder am Liefertag Mitteilungen aktiv sind, prüft Peek bis zu alle 10 Minuten – und ab 6 Uhr Ortszeit an dem Tag, an dem ZTO das Paket als „In Zustellung“ scannt, bis zu alle 2 Minuten (alle 5 Minuten, wenn die ZTO-Abfrage einen Browser geöffnet hat), solange ZTOs eigener Tracker antwortet (über Tracking-Dienste allein stündlich, alle 3 Stunden nach einem ruhigen Tag und am Liefertag bei geöffnetem Paket oder aktiven Mitteilungen bis zu alle 15 Minuten). Sonst sowie nach zwei Tagen ohne Bewegung (oder 12 Stunden auf „Etikett erstellt“ bzw. „Beim Paketdienst eingegangen“) prüft Peek stündlich. Benachrichtigungen gibt es für „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ / „Nur am Liefertag“. ZTO, den Weiterleiter oder den Shop kontaktieren kann Peek nicht.

## Häufige Fragen zur ZTO-Express-Sendungsverfolgung

### Ist ZTO Express seriös?

Ja. ZTO Express wurde am 8. Mai 2002 in Shanghai gegründet, ging am 27. Oktober 2016 in New York und am 29. September 2020 in Hongkong an die Börse (als erstes Expressunternehmen mit Doppellisting an beiden Börsen), zählte 2025 über 31.000 Servicestellen und erreicht über 99 % aller chinesischen Kreise und Bezirke.

### Sind ZTO Express und ZTO International dasselbe?

Beide gehören zur selben Gruppe, nutzen aber getrennte Netze und Tracker: Der Servicevertrag von ZTO Express gilt nur für Sendungen innerhalb Festlandchinas (ohne Hongkong, Macau und Taiwan) unter `zto.com/check`; ZTO International besteht seit 2014 als Auslandsarm für grenzüberschreitende Pakete mit `771`-Nummern auf `ztoglobal.com`.

### Wie lange dauert der Versand mit ZTO Express in China?

Als unverbindliche Richtwerte nennt ZTOs FAQ in der Regel Zustellung am nächsten Tag innerhalb einer Provinz sowie zwischen Jiangsu, Zhejiang, Shanghai und Anhui, 3 bis 5 Tage zwischen anderen Provinzen und 5 bis 7 Tage in abgelegene Gebiete. Geht die Ware an ein Sammellager, kommt danach die internationale Laufzeit des Weiterleiters hinzu.

### Was bedeutet „已签收“ im ZTO-Tracking?

Quittiert empfangen: Peek zeigt `Signed for` und den Status „Zugestellt“. Unterschrieben haben kannst du, eine Empfangsstelle oder das Lager eines Weiterleiters. Davon zu unterscheiden ist `已代收` („Abholbereit“): Das Paket wartet in einer Station oder Annahmestelle auf deine Abholung.

### Wie lautet die Telefonnummer von ZTO Express?

`95311` – die nationale Kundenhotline in China, besetzt von `08:30` bis `17:30` Uhr chinesischer Zeit. Anrufe von `95720` stammen von ZTO-Zustellern.

:::sources
- [ZTO Express: 运单查询 (Sendungssuche)](https://www.zto.com/check) – chinesischer Tracker, bis zu 10 Nummern und WeChat-Abo
- [ZTO Express: 关于中通 (Über ZTO)](https://www.zto.com/introduce/about/home) – Gründung 2002, Börsengänge, 38,52 Mrd. Pakete und Netz 2025, Taobao seit 2008
- [ZTO Express: 首页 (Startseite)](https://www.zto.com/) – 95311, 95720, App und WeChat
- [ZTO Express: Home (Englisch)](https://en.zto.com/) – Angabe Customer Service Hotline: 95311
- [ZTO Express: 联系我们 (Kontakt)](https://www.zto.com/introduce/about/contact) – Online-Kundenservice und offizielle Kanäle
- [ZTO Express: 常见问题 (FAQ)](https://www.zto.com/after/answers) – Servicezeiten 08:30–17:30, Paketfächer, Verbot von Zustellgebühren, Außerortsgebiete, Adressänderung und Laufzeiten
- [ZTO Express: 服务网点查询 (Niederlassungssuche)](https://www.zto.com/service/station) – zuständige Station für eine Adresse
- [ZTO Express: 投诉反馈 (Beschwerden)](https://www.zto.com/after/complaint/problemTypes) – Beschwerdeformular mit Login
- [ZTO Express: 快递服务协议 (Servicevertrag)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – Geltung Festlandchina, Zustellarten, Video- und 3-Tage-Regel, Rücksendung, 500-Yuan-Haftungsgrenze und 30-Tage-Frist
- [ZTO Express: 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – internationaler Zweig seit 2014
- [ZTO Express: 中通集运 (Sammelversand)](https://www.zto.com/introduce/service/concentrated) – internationale Konsolidierung und Kontakt 95311
- [Verkehrsministerium Chinas: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – Prüfung bei Übergabe, Zustimmung für Paketfächer/Stationen und Schlichtung
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Nummernformate, englische Phasennamen, Browser-Abfrage, keine automatische Erkennung und verworfene Felder
:::
