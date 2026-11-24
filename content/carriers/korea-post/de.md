---
title: Korea Post Sendungsverfolgung: EMS-KR-Codes, Status & Hilfe
description: Verfolge ein Korea-Post-Paket (ePOST): S10-Codes mit KR, 13-stellige Inlandscodes, EMS- und koreanische Status, Zollübergabe, Laufzeiten und Kontakt.
slug: korea-post-sendungsverfolgung
published: 2026-11-24
updated: 2026-11-24
---

Korea Post (*우정사업본부*, Online-Portal *ePOST* / *인터넷우체국*) ist die nationale Post Südkoreas unter dem Ministerium für Wissenschaft und IKT. Sie übernimmt die Zustellung von Inlandspaketen und Einschreiben in ganz Südkorea sowie den internationalen Versand per **EMS** (Express Mail Service in über 100 Ziele plus über 70 Länder über EMS Premium), **K-Packet** und internationale Luftpost-Einschreiben und -Pakete. Internationale Sendungen kannst du im [englischen EMS-Tracker von Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) prüfen, Inlandssendungen im [ePOST-Inlandstracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) nachschlagen oder deine Sendungsnummer einfach in das Feld oben einfügen.

## Korea-Post-Sendungsnummern: KR-S10-Codes und 13-stellige Inlandscodes

Schau auf deine Postquittung, in die Bestellübersicht des koreanischen Onlineshops oder in die Versandbestätigung per E-Mail. Korea Post nutzt zwei Hauptformate, je nachdem, ob die Sendung international oder innerhalb Südkoreas läuft:

| Format (erfunden) | Worum es sich handelt |
| --- | --- |
| `EM482917367KR` | Internationale **EMS**-Warensendung (UPU-S10-Format mit Endung `KR`; Dokumente beginnen in der Regel mit `EE`). |
| `RR482917367KR` | Internationales Einschreiben oder K-Packet (UPU-S10-Format: 2 Buchstaben + 9 Ziffern mit Prüfziffer + `KR`). |
| `6482917364058` | 13-stellige südkoreanische Inlandsnummer für Einschreiben oder Pakete (beginnt mit den Ziffern `1`–`6`). Teilt das 13-stellige Ziffernformat mit [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) und weiteren Diensten. |

Auf der [EMS-Übersichtsseite von Korea Post](https://ems.epost.go.kr/front.Introduction01.postal) kennzeichnet `EE` EMS-Dokumente (ohne Warenzollprüfung), während `EM` für EMS-Warensendungen steht. Weitere internationale Postformate findest du unter [Sendungsnummern-Formate](guide:tracking-number-formats).

## Was die Korea-Post-Sendungsstatus bedeuten

Internationale `KR`-Sendungen zeigen englische UPU-/EMS-Scanzeilen mit der jeweiligen Ortszeit, wie sie die Post des betreffenden Landes meldet; 13-stellige Inlandssendungen zeigen koreanische Scan-Einträge in koreanischer Standardzeit (`Asia/Seoul`, ganzjährig UTC+9):

| Korea Post meldet (international / Inland) | Was das für dich bedeutet | Peek zeigt |
| --- | --- | --- |
| `운송장출력` *(Inland)* | Versandschein gedruckt, bevor die Sendung in der Filiale eingeliefert wurde. | „Etikett erstellt“ |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Am Schalter einer koreanischen Postfiliale eingeliefert oder von einem Korea-Post-Boten abgeholt. | „Beim Paketdienst eingegangen“ |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Im koreanischen Auswechselungsamt (z. B. Incheon) bearbeitet, ausgeflogen, an die Post im Zielland übergeben oder zwischen Inlands-Sortierzentren befördert. | „Unterwegs“ |
| `Ready for customs clearance` | Dem Zoll im Zielland vorgelegt (oder Ausfuhr-/Einfuhrzollprüfung). | „Beim Zoll“ |
| `배달준비` *(Inland)* | Vom Zusteller der örtlichen koreanischen Postfiliale für die Zustellung vorbereitet. | „In Zustellung“ |
| `Unsuccessful delivery` | Der Zustellversuch im Zielland konnte nicht abgeschlossen werden. | „Zustellversuch“ |
| `Delivery complete` / `배달완료` | Zugestellt; der Korea-Post-Tracker kann angeben, wer die Sendung entgegengenommen hat. | „Zugestellt“ |

## EMS-Laufzeiten, Zoll und Übergabe an die örtliche Post

Laut der [EMS-Serviceübersicht von Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), den [Versandbedingungen nach Land](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) und dem [Laufzeit- und Preisrechner](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **EMS-Laufzeiten:** Für Dokumente (`EE`-Nummern), die bis mittags in Seoul eingeliefert werden, nennt Korea Post den nächsten Tag oder 3–4 Tage in Städte mit Direktflügen wie Tokio, Hongkong und Singapur sowie 4–6 Tage für andere Ziele; die Übersichtsseite nennt 2–3 und 3–5 Tage für vormittags in Seoul eingelieferte Dokumente. Bei Einlieferung nach Mittag oder außerhalb Seouls sowie bei Adressen außerhalb der Stadt des Ziel-Auswechselungsamts kommt ein Tag hinzu; ab Berg- oder Inselfilialen kommen die Tage bis zum International Mail Logistics Center dazu. Warensendungen (`EM`-Nummern) warten zusätzlich auf die Zollprüfung im Zielland. Einlieferungstag und Feiertage zählen nicht mit.
- **Zollerklärungen:** Für Dokumente und Drucksachen wird eine CN22-Erklärung genutzt, für Waren und zollpflichtige Artikel ist eine CN23-Erklärung erforderlich (zuzüglich Handelsrechnung und nötiger Genehmigungen bei gewerblichen Sendungen). Verbotene und eingeschränkte Gegenstände listet der [ePOST-Leitfaden für verbotene und eingeschränkte Artikel](https://ems.epost.go.kr/front.Introduction04.postal).
- **Übergabe an die Zielpost:** Sobald deine `KR`-Sendung dein Land erreicht (`Delivered to Destination Post` / `Arrival at inward office of exchange`), übernimmt die jeweilige Landespost (etwa [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) oder [Australia Post](carrier:australia-post)) den Einzug von Einfuhrabgaben, Zustellversuche und die Filialaufbewahrung nach ihren eigenen Regeln. Prüfe dieselbe `...KR`-Nummer auch direkt bei deiner Landespost für genauere Zustelloptionen. Siehe [Pakete beim Zoll](guide:customs).

## Korea-Post-Paket verspätet, unterwegs steckengeblieben oder verschwunden

- **Funkstille nach `Departure from the Airport`:** Internationale EMS- und K-Packet-Postsäcke bleiben zwischen dem Abflug aus Südkorea und dem Eingangsscan im Auswechselungsamt des Ziellands (`Arrival at inward office of exchange`) häufig mehrere Tage bis über eine Woche ohne neuen Scan. Siehe [warum die Sendungsverfolgung nicht aktualisiert](guide:tracking-not-updating).
- **Stillstand bei `Ready for customs clearance`:** Prüfe dieselbe `...KR`-Nummer auf der Website deiner Landespost, ob Einfuhrumsatzsteuer, Zoll oder eine offene Auslagepauschale die Freigabe aufhält.
- **Verlorene, beschädigte oder fehlende Sendungen:** Kontaktiere bei Online-Bestellungen zuerst den koreanischen Händler oder Marktplatz; ePOST nimmt Nachforschungsaufträge (*행방조사청구*) online oder schriftlich entgegen und veröffentlicht seine Entschädigungsregeln (*손해배상규정*). Siehe [zugestellt, aber nicht erhalten](guide:delivered-not-received), [beschädigtes Paket](guide:damaged-parcel), [verlorenes Paket](guide:lost-parcel) und [zurück an Absender](guide:returned-to-sender).

## Korea Post Kundenservice und Kontakt

Offizielle Kontakte aus dem [ePOST-Kundenzentrum](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), dem [ePOST-Hauptportal](https://www.epost.go.kr/main.retrieveMainPage.comm) und der [EMS-Serviceübersicht](https://ems.epost.go.kr/front.Introduction01.postal):

- **Korea Post Kundenservice (innerhalb Südkoreas):** `1588-1300` (werktags `09:00–18:00` KST, samstags und an Feiertagen geschlossen; kostenpflichtig).
- **Aus dem Ausland:** `+82-42-609-4295`, zu denselben Zeiten.
- **E-Mail:** `help@posa.or.kr`.
- **Online-FAQ & Anfragen:** [ePOST-Kundenzentrum](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) sowie die Option *e-mail EMS Inquiry* auf [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Adresse im ePOST-Footer:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Südkorea.

> Ignoriere gefälschte SMS, die behaupten, dein Korea-Post-Paket könne nur nach einer kleinen Zahlung über einen inoffiziellen Link zugestellt werden. Echte Einfuhrabgaben für ein eingehendes `KR`-Paket erhebt deine eigene Landespost oder das Zollportal, niemals ein fremder SMS-Link. Siehe [Betrugs-SMS zu Paketlieferungen](guide:delivery-scam-texts).

## Ein Korea-Post-Paket mit Peek verfolgen

[Peek](/) erkennt jede UPU-S10-Sendungsnummer mit der Endung `KR` (`EM482917367KR` oder `RR482917367KR`, Ziffern erfunden) automatisch mit hoher Sicherheit als Korea Post und erkennt ebenso eingefügte Links von `trace.epost.go.kr` mit `POST_CODE=` und von `service.epost.go.kr` mit `sid1=`. Eine 13-stellige Inlandsnummer (`6482917364058`) passt zu mehreren Paketdiensten: Peek nennt Korea Post als eine Möglichkeit und lässt dich wählen. Du kannst den Dienst auch später über „Paketdienst ändern“ in der Sendungsansicht auswählen (auch die Suche nach „South Korea Post“ oder „ePOST“ findet ihn) oder stattdessen den ePOST-Link einfügen.

Peek liest den englischen EMS-Tracker von Korea Post für `KR`-Nummern und den koreanischen ePOST-Inlandstracker für 13-stellige Nummern. Angezeigt werden die Phase („Etikett erstellt“, „Beim Paketdienst eingegangen“, „Unterwegs“, „Beim Zoll“, „In Zustellung“ bei Inlandspaketen, „Zustellversuch“, „Zugestellt“), der Scan-Text mit Postfiliale oder Flughafen sowie die Zustellzeit bei Inlandspaketen. Inlandsscans sind nach koreanischer Zeit datiert, internationale Scans nach der Zeitzone ihres jeweiligen Ortes (etwa New Yorker Zeit bei einem Scan in New York); Peek rechnet sie auf die Uhrzeit deines Geräts um. Der von Korea Post gemeldete Empfängername erscheint auf deiner eigenen Paketseite unter „Empfänger“, niemals auf einem geteilten Link; der Name des Unterzeichners bleibt aus jeder Peek-App heraus, und Peek lässt den Tarifnamen, den Absendernamen und die Telefonnummern der Filialen weg. Der Tracker von Korea Post nennt kein voraussichtliches Zustelldatum, kein Gewicht und keinen Abholpunkt.

Solange du die Sendung in Peek geöffnet hast oder am Zustelltag Benachrichtigungen aktiviert sind, fragt Peek bis zu alle 10 Minuten nach; an dem Tag, an dem ein Inlandspaket `배달준비` zeigt, alle 2 Minuten ab 6 Uhr koreanischer Zeit, solange der eigene Korea-Post-Tracker antwortet. Sonst fragt Peek stündlich ab. Nach 12 Stunden nur bei „Etikett erstellt“ oder „Beim Paketdienst eingegangen“ oder nach zwei Tagen ohne Neuigkeiten prüft Peek unabhängig davon stündlich und nach sieben Tagen bei „Etikett erstellt“ alle 6 Stunden. Antwortet der Korea-Post-Tracker nicht und Peek verfolgt die Sendung allein über Tracking-Dienste, fragt Peek stündlich nach, nach einem ruhigen Tag alle 3 Stunden und am Zustelltag bis zu alle 15 Minuten, solange du die Sendung geöffnet hast oder Benachrichtigungen aktiviert sind.

Ist der neueste Scan vier Tage alt und das Paket steht noch auf „Etikett erstellt“, „Beim Paketdienst eingegangen“ oder „Unterwegs“, markiert Peek es mit dem Tag dieses Scans, etwa „Keine Aktualisierung seit Mo., 12. Okt.“. Auf der Paketseite steht dann „Auf langen Flügen, beim Zoll oder übers Wochenende wird es oft still um ein Paket. Peek fragt weiter bei Korea Post nach.“, samt Link „Hol dir Hilfe bei Korea Post“ zum ePOST-Kundenzentrum. Nach drei Wochen ohne neuen Scan heißt es „Seit über drei Wochen nichts Neues. Ist das Paket noch nicht angekommen, frag beim Verkäufer nach. Peek fragt weiter bei Korea Post nach.“

Bei den Benachrichtigungen kannst du zwischen „Jeden Scan“, „Wichtige Schritte“ oder „Nur die Zustellung“ wählen. Peek beobachtet ausschließlich Tracking-Updates und kann weder die Verzollung übernehmen noch ein Paket umleiten oder eine EMS-Nachforschung bei Korea Post für dich einreichen.

## Häufige Fragen zur Korea-Post-Sendungsverfolgung

### Was bedeuten `EE...KR`, `EM...KR` und `RR...KR`?

Das sind 13-stellige internationale UPU-S10-Postnummern aus Südkorea: `EE` steht für EMS-Dokumente, `EM` für EMS-Warensendungen und `RR` (oder andere Präfixe mit `R`/`C`/`L`) für Einschreiben, K-Packet und internationale Pakete.

### Warum aktualisiert sich mein Korea-Post-EMS-Paket nach dem Abflug nicht mehr?

Sobald in Südkorea `Departure from the Airport` erscheint, bleibt das Tracking ruhig, bis die Post deines Ziellands den ankommenden Postsack im Eingangs-Auswechselungsamt scannt (`Arrival at inward office of exchange`). Prüfe dieselbe `...KR`-Nummer zusätzlich auf der Website deiner Landespost.

### Stellt Korea Post international selbst zu?

Korea Post übernimmt Annahme, Exportsortierung und Luftversand aus Südkorea und übergibt das Paket anschließend (`Delivered to Destination Post`) an die nationale Post deines Ziellands für Einfuhrverzollung und Haustürzustellung.

### Wie erreiche ich den Korea-Post-Kundenservice?

Rufe innerhalb Südkoreas `1588-1300` an (Mo.–Fr. `09:00–18:00` KST, samstags und feiertags geschlossen), schreibe an `help@posa.or.kr` oder nutze das [ePOST-Kundenzentrum](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: Englisches EMS-Tracking](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – internationales EMS-Trackingportal und Partnerpostdienste im Zielland
- [Korea Post (ePOST): Inlands-Sendungsverfolgung](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – 13-stellige Verfolgung von Inlandspaketen und Einschreiben
- [Korea Post (ePOST): EMS-Serviceübersicht](https://ems.epost.go.kr/front.Introduction01.postal) – `EE`- vs. `EM`-Präfixe, EMS-Laufzeiten, CN22/CN23-Zollregeln, 100+ EMS- / 70+ EMS-Premium-Ziele und `1588-1300`
- [Korea Post (ePOST): Versandbedingungen nach Land](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – länderspezifische EMS-Grenzen und Zollhinweise
- [Korea Post (ePOST): Laufzeit- und Preisrechner](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – Rechner für EMS, EMS Premium und Pakete
- [Korea Post (ePOST): Verbotene & eingeschränkte Gegenstände](https://ems.epost.go.kr/front.Introduction04.postal) – internationale Versandbeschränkungen
- [Korea Post (ePOST): Kundenzentrum](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, Zeiten werktags `09:00–18:00` KST und Adresse in Naju (von Peek verlinkte Hilfeseite)
- [Korea Post (ePOST): Hauptportal](https://www.epost.go.kr/main.retrieveMainPage.comm) – Überblick zu Inlandspaketen, Einschreiben, EMS und K-Packet, `+82-42-609-4295` aus dem Ausland
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – `KR`-S10- und 13-Ziffern-Erkennung, Link-Regeln für `trace.epost.go.kr` / `service.epost.go.kr`, englische EMS- und koreanische Inlands-Statustabellen, Scan-Zeitzonen, verworfene Felder und Hilfe-URL
:::
